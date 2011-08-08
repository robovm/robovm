/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file
 * @ingroup Pool
 * @brief Pool primitives (creation, iteration, deletion, etc.)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "hypool.h"

#define ROUND_TO(granularity, number) ( (((number) % (granularity)) ? ((number) + (granularity) - ((number) % (granularity))) : (number)))

#define MIN_GRANULARITY		sizeof(UDATA)

#define MALLOC_ALIGNMENT sizeof(UDATA)

/* read this if a port library call becomes available that gives out the OS page size */
#if 0
#define OS_PAGE_SIZE		(EsGetAddressSpacePageSize())
#else

#define OS_PAGE_SIZE		4096
#endif

/**
 * @section pool_new
 * @fn pool_new(U_32 structSize, U_32 minNumberElements, U_32 elementAlignment, UDATA poolFlags, void *(VMCALL * memAlloc) (void *, U_32), void (VMCALL * memFree) (void *, void *), void *userData)
 *	Returns a handle to a variable sized pool of structures.
 *	This handle should be passed into all other pool functions.
 *
 * @param[in] structSize Size of the pool-elements
 * @param[in] minNumberElements If zero, will default to 1
 * @param[in] elementAlignment If zero will default to MIN_GRANULARITY
 * @param[in] poolFlags
 * @param[in] memAlloc Allocate function pointer
 * @param[in] memFree	Free function pointer
 * @param[in] userData
 *
 * @return pointer to a new pool
*/
HyPool *VMCALL
pool_new (U_32 structSize, U_32 minNumberElements, U_32 elementAlignment,
          UDATA poolFlags, void *(VMCALL * memAlloc) (void *, U_32),
          void (VMCALL * memFree) (void *, void *), void *userData)
{
  U_64 roundedStructSize, tempAllocSize, finalAllocSize;
  U_32 finalNumberOfElements, tempNumElems;
  UDATA freeLocation, *oldFreeLocation = NULL;
  HyPool *newHandle;

  if (minNumberElements == 0)
    {
      minNumberElements = 1;
    }

  if (elementAlignment == 0)
    {
      elementAlignment = MIN_GRANULARITY;
    }

  roundedStructSize = ROUND_TO (elementAlignment, structSize);

  tempAllocSize = roundedStructSize * minNumberElements
    + ROUND_TO (elementAlignment, sizeof (HyPool))
    + (elementAlignment - MALLOC_ALIGNMENT);
  finalAllocSize = ROUND_TO (OS_PAGE_SIZE, tempAllocSize);
  finalNumberOfElements = minNumberElements;
  finalNumberOfElements +=
    (U_32)((finalAllocSize - tempAllocSize) / roundedStructSize);

  /* 
   * finalAllocSize is a U_64 so that we can detect pool sizes which overflow 32-bits. 
   */
  if (finalAllocSize > (U_64) 0xFFFFFFFF)
    {
      return NULL;
    }

  newHandle = memAlloc (userData, (UDATA) finalAllocSize);

  if (newHandle)
    {
      memset ((void *) newHandle, 0, (size_t) finalAllocSize);
      newHandle->elementSize = (UDATA)roundedStructSize;
      newHandle->alignment = (U_16) elementAlignment;   /* we assume no alignment is > 64k */
      newHandle->flags = poolFlags | POOL_SORTED;       /* list starts sorted */
      newHandle->numberOfElements = finalNumberOfElements;
      newHandle->usedElements = 0;
      newHandle->firstElementAddress = (void *) ROUND_TO (elementAlignment,
                                                          ((UDATA) newHandle)
                                                          + sizeof (HyPool));
      newHandle->firstFreeSlot = (UDATA *) newHandle->firstElementAddress;
      newHandle->activePuddle = newHandle;
      newHandle->nextPool = NULL;
      newHandle->memAlloc = memAlloc;
      newHandle->memFree = memFree;
      newHandle->userData = userData;

      /* now stuff the free list pointers in */
      freeLocation = (UDATA) newHandle->firstElementAddress;
      tempNumElems = newHandle->numberOfElements;
      while (tempNumElems--)
        {
          oldFreeLocation = (UDATA *) freeLocation;
	  freeLocation += (UDATA)roundedStructSize;
          *oldFreeLocation = freeLocation;
        }
      *oldFreeLocation = 0;     /* end of list */
    }
  return newHandle;
}

/**
 * @fn pool_kill(HyPool * aPool)
 *	Deallocates all memory associated with a pool.
 *
 * @param[in] aPool Pool to be deallocated
 *
 * @return none
 *
*/
void VMCALL
pool_kill (HyPool * aPool)
{
  HyPool *tmpPool;

  while (aPool)
    {
      tmpPool = aPool;
      aPool = aPool->nextPool;
      tmpPool->memFree (tmpPool->userData, tmpPool);
    }
}

/**
 * @fn pool_newElement (HyPool * aPool)
 *	Asks for the address of a new pool element.
 *	If it succeeds, the address returned will have space for
 *	one element of the correct structure size.
 *	The contents of the element are undefined.
 *	If the current pool is full, a new one will be grafted onto the
 *	end of the pool chain and memory from there will be used.
 *
 * @param[in] aPool
 *
 * @return NULL on error
 * @return pointer to a new element otherwise
 *
*/
void *VMCALL
pool_newElement (HyPool * aPool)
{
  void *newElement = NULL;
  BOOLEAN searchedActive = FALSE;
  HyPool *head = aPool;

  aPool = head->activePuddle;   /* peek at current active puddle */
  while (aPool)
    {
      if (aPool->firstFreeSlot != NULL)
        {
          newElement = aPool->firstFreeSlot;
          aPool->firstFreeSlot = (UDATA *) * ((UDATA *) newElement);
          aPool->usedElements += 1;
          /* if we were sorted before getting the new element, the free list is still in order afterwards.  If not,
             it's unlikely to have become sorted.  Thus, the POOL_SORTED bit should only be flipped in
             pool_removeElement. */
          head->activePuddle = aPool;   /* sets to most recently active puddle */
          break;
        }
      else if (!searchedActive)
        {                       /* allocate a new pool to flow into */
          searchedActive = TRUE;
          aPool = head;         /* no space in active puddle - walk the chain from the head */
        }
      else
        {
          if (aPool->nextPool)
            {
              aPool = aPool->nextPool;
            }
          else
            {
              aPool->nextPool =
                pool_new (aPool->elementSize, aPool->numberOfElements,
                          aPool->alignment, aPool->flags, aPool->memAlloc,
                          aPool->memFree, aPool->userData);
              if (!(aPool->nextPool))
                return NULL;
              aPool = aPool->nextPool;  /* let the next iteration do the address calc. */
            }
        }
    }
  return newElement;
}

/**
 * @fn pool_removeElement (HyPool * aPool, void *anElement)
 *	Deallocates an element from a pool
 *
 * It is safe to call pool_removeElement() while looping over
 * the pool with @ref pool_startDo / @ref pool_nextDo on the element 
 * returned by those calls.  This is because the free element
 * is always inserted at either the head of the free list or 
 * before the nextFree element in the pool_state.
 *
 * @param[in] aPool
 * @param[in] anElement Pointer to the element to be removed
 *
 * @return none
*/
void VMCALL
pool_removeElement (HyPool * aPool, void *anElement)
{
  UDATA tempHead, tmp2;
  BOOLEAN foundPool = FALSE;
  BOOLEAN searchedActive = FALSE;
  HyPool *oldPool = aPool;
  HyPool *head = aPool;

  if (!aPool)
    return;

  /* Peek at active puddle first. If not suitable, walk whole chain. */
  aPool = head->activePuddle;
  do
    {
      tmp2 =
        (UDATA) aPool->firstElementAddress +
        aPool->elementSize * aPool->numberOfElements;
      if (((UDATA) anElement < tmp2) && ((UDATA) anElement > (UDATA) aPool))
        {

          /* If we've already walked the chain, FOUND. If we're peeking at the active puddle
             and there's only one element left, walk the chain from the start. */
          if (searchedActive
              || (!searchedActive
                  && (aPool == head || aPool->usedElements > 1)))
            {
              foundPool = TRUE;
              break;            /* this is the right pool */
            }
        }
      if (!searchedActive)
        {
          searchedActive = TRUE;
          aPool = head;         /* start from the beginning */
        }
      else
        {
          oldPool = aPool;
          aPool = aPool->nextPool;
        }
    }
  while (aPool);

  if (!foundPool)
    return;                     /* this is an error...  we were passed an invalid data pointer. */

  head->activePuddle = aPool;   /* set this pool to the active pool */

  /* we delete from the list differently, depending on if we want to preserve sort order */
  if (aPool->flags & POOL_ALWAYS_KEEP_SORTED)
    {
      /*      in this case, we walk the free list, looking for where to insert the newly freed item. */
      UDATA *lastElem = (UDATA *) & (aPool->firstFreeSlot);
      UDATA *current = (UDATA *) aPool->firstFreeSlot;
      while (current && (UDATA) current < (UDATA) anElement)
        {
          lastElem = current;
          current = (UDATA *) * current;
        }
      *((UDATA *) anElement) = (UDATA) current;
      *lastElem = (UDATA) anElement;
      aPool->usedElements -= 1;
    }
  else
    {
      tempHead = (UDATA) aPool->firstFreeSlot;
      aPool->firstFreeSlot = (UDATA *) anElement;
      *((UDATA *) anElement) = tempHead;
      aPool->usedElements -= 1;
      aPool->flags &= ~POOL_SORTED;     /* reset sorted flag */
    }

  if ((oldPool != aPool) && (aPool->usedElements == 0)
      && !(aPool->flags & POOL_NEVER_FREE_PUDDLES))
    {
      oldPool->nextPool = aPool->nextPool;
      head->activePuddle = oldPool;
      aPool->memFree (aPool->userData, aPool);
    }
}

/**	
 * @fn pool_do (HyPool * aPool, void (*aFunction) (void *anElement, void *userData), void *userData)
 *	Calls a user provided function for each element in the list.
 *
 * @param[in] aPool The pool to "do" things to
 * @param[in] aFunction Pointer to function which will "do" things to the elements of aPool
 * @param[in] userData Pointer to data to be passed to "do" function, along with each pool-element
 *
 * @return none
 *
 * @see pool_startDo, pool_nextDo
 *
 */
void VMCALL
pool_do (HyPool * aPool, void (*aFunction) (void *anElement, void *userData),
         void *userData)
{
  void *anElement;
  pool_state aState;
  anElement = pool_startDo (aPool, &aState);

  while (anElement)
    {
      (aFunction) (anElement, userData);
      anElement = pool_nextDo (&aState);
    }
}

/**
 * @fn pool_numElements (HyPool * aPool)
 *	Returns the number of elements in a given pool.
 *
 * @param[in] aPool
 *
 * @return 0 on error
 * @return the number of elements in the pool otherwise
 *
*/
UDATA VMCALL
pool_numElements (HyPool * aPool)
{
  UDATA numElements = 0;

  while (aPool)
    {
      numElements += aPool->usedElements;
      aPool = aPool->nextPool;
    }
  return numElements;
}

/**
 * @section pool_startDo
 * @fn pool_startDo (HyPool * aPool, pool_state * lastHandle)
 *	Start of an iteration set that will return when code is to be executed.
 *	This is based strongly on pool_sortFreeAndIterateUsed.
 *	Pass in a pointer to an empty pool_state and it will be filled in.
 *
 * @param[in] aPool The pool to "do" things to
 * @param[in] lastHandle 
 *
 * @return NULL
 * @return pointer to element otherwise
 *
 * @see pool_do, pool_nextDo
 */
void *VMCALL
pool_startDo (HyPool * aPool, pool_state * lastHandle)
{
  UDATA tmpInc;
  UDATA *currAddr, **nextFreeAddr;

  if (!aPool)
    return NULL;

  if (!(aPool->flags & POOL_SORTED))
    {                           /* sort if not pure */
      pool_sortFree (aPool);
    }

  if (0 == aPool->usedElements)
    {                           /* this pool is empty */
      if (aPool->nextPool)
        {
          return pool_startDo (aPool->nextPool, lastHandle);
        }
      else
        {
          return NULL;          /* totally empty */
        }
    }

  /* walk, iterating. */
  tmpInc = aPool->elementSize;
  currAddr = (UDATA *) aPool->firstElementAddress;
  nextFreeAddr = (UDATA **) aPool->firstFreeSlot;

  while (currAddr == (UDATA *) nextFreeAddr)
    {
      nextFreeAddr = (UDATA **) * nextFreeAddr;
      currAddr = (UDATA *) ((U_8 *) currAddr + tmpInc);
    }

  lastHandle->currPool = aPool;
  lastHandle->nextFree = nextFreeAddr;
  lastHandle->lastAddr = (UDATA *) ((U_8 *) currAddr + tmpInc); /* set up for next time in. */
  lastHandle->leftToDo = aPool->usedElements - 1;
  return (void *) currAddr;
}

/**
 * @section pool_nextDo
 * @fn pool_nextDo (pool_state * lastHandle)
 *	Continue an iteration based on state passed in by lastHandle.
 *	It is safe to stop an iteration midway through.
 *
 * @param[in] lastHandle pointer for current iteration state
 *
 * @return NULL nothing more to be done
 * @return pointer to next element to be processed otherwise
 *
 * @see pool_do, pool_startDo
 *
 */
void *VMCALL
pool_nextDo (pool_state * lastHandle)
{
  UDATA tmpInc;
  UDATA *currAddr, **nextFreeAddr;

  if (lastHandle->leftToDo == 0)
    {                           /* no more used elements, stop this pool. */
      if (lastHandle->currPool->nextPool)       /* check the next one. */
        return pool_startDo (lastHandle->currPool->nextPool, lastHandle);
      else
        return NULL;
    }

  /* there's at least one more used element */

  currAddr = lastHandle->lastAddr;
  nextFreeAddr = lastHandle->nextFree;
  tmpInc = lastHandle->currPool->elementSize;

  /* walk, iterating. */
  while (currAddr == (UDATA *) nextFreeAddr)
    {
      nextFreeAddr = (UDATA **) * nextFreeAddr;
      currAddr = (UDATA *) ((U_8 *) currAddr + tmpInc);
    }

  lastHandle->nextFree = nextFreeAddr;
  lastHandle->lastAddr = (UDATA *) ((U_8 *) currAddr + tmpInc); /* set up for next time in. */
  lastHandle->leftToDo -= 1;

  return (void *) currAddr;
}

/**
 * @fn pool_sortFree (HyPool * aPool)
 *	Sorts the free list of the current pool.
 *	(ie: does not follow nextPool pointers...)
 *	This is a O(n) most of the time.
 *
 * @param[in] aPool The pool to be sorted
 *
 * @return none
 */
void VMCALL
pool_sortFree (HyPool * aPool)
{
  BOOLEAN done = FALSE, conflict;
  UDATA *currAddr, *tmpAddr, **nextFreeAddr;
  UDATA tmpInc, x, numElements;
  U_32 flagVal = 0xDEADBEEE;

  tmpInc = aPool->elementSize;
  numElements = aPool->numberOfElements;

  currAddr = (UDATA *) aPool->firstElementAddress;
  while (!done)
    {
      conflict = FALSE;
      for (x = 0; x < numElements; x++)
        {
          if (*currAddr == flagVal)
            {
              conflict = TRUE;
              flagVal -= 1;
              break;
            }
          currAddr = (UDATA *) ((U_8 *) currAddr + tmpInc);
        }
      if (!conflict)
        {
          done = TRUE;
        }
    }
  /* now walk, slamming pointers as I go */
  currAddr = aPool->firstFreeSlot;
  while (currAddr)
    {
      tmpAddr = (UDATA *) * currAddr;
      *currAddr = flagVal;
      currAddr = tmpAddr;
    }
  /* walk, fixing pointers and iterating. */
  currAddr = (UDATA *) aPool->firstElementAddress;
  nextFreeAddr = (UDATA **) & (aPool->firstFreeSlot);

  for (x = 0; x < numElements; x++)
    {
      if (*currAddr == flagVal)
        {                       /* add to free list being built */
          *nextFreeAddr = currAddr;
          nextFreeAddr = (UDATA **) currAddr;
        }
      currAddr = (UDATA *) ((U_8 *) currAddr + tmpInc);
    }
  *nextFreeAddr = NULL;         /* end the free list cleanly */
  aPool->flags |= POOL_SORTED;  /* set sorted flag */
}

/**
 * @fn pool_forPortLib (U_32 structSize, HyPortLibrary * portLibrary)
 *	Shortcut for @ref pool_new, using the default malloc/free from the portLibrary
 *
 * @param[in] structSize size of pool-element
 * @param[in] portLibrary
 *
 * @return pointer to a Pool
 *
 * @see pool_new
*/
HyPool *VMCALL
pool_forPortLib (U_32 structSize, HyPortLibrary * portLibrary)
{
  return pool_new (structSize, 0, 0, 0, POOL_FOR_PORT (portLibrary));
}

/**
 * @fn pool_clear (HyPool * aPool)
 * Clear the contents of a pool but not delete it
 *
 * @note Make no assumptions about the contents of the pool after invoking
 *       this method (it currently does not zero the memory)
 *
 * @param[in] aPool The pool to clear
 *
 * @return none
*/

void VMCALL
pool_clear (HyPool * aPool)
{
  while (aPool)
    {
      UDATA freeLocation, *oldFreeLocation = NULL;
      U_32 tempNumElems;

      aPool->usedElements = 0;
      aPool->firstElementAddress =
        (void *) ROUND_TO (aPool->alignment,
                           ((UDATA) aPool) + sizeof (HyPool));
      aPool->firstFreeSlot = (UDATA *) aPool->firstElementAddress;
      freeLocation = (UDATA) aPool->firstElementAddress;
      tempNumElems = aPool->numberOfElements;
      while (tempNumElems--)
        {
          oldFreeLocation = (UDATA *) freeLocation;
          freeLocation += aPool->elementSize;
          *oldFreeLocation = freeLocation;
        }
      *oldFreeLocation = 0;     /* end of list */
      aPool->flags |= POOL_SORTED;      /* an empty list is sorted */
      aPool = aPool->nextPool;
    }
}
