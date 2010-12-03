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
 * @brief Pool Header
 */

#if !defined(HYPOOL_H)
#define HYPOOL_H

#if defined(__cplusplus)
extern "C"
{
#endif
#include "hycomp.h"
#include "hyport.h"

typedef void *(VMCALL * hymemAlloc_fptr_t) (void *, U_32);
typedef void (VMCALL * hymemFree_fptr_t) (void *, void *);

typedef struct HyPool
  {
    UDATA elementSize;
    UDATA numberOfElements;
    UDATA usedElements;
    void *firstElementAddress;
    UDATA *firstFreeSlot;
    struct HyPool *activePuddle;
    struct HyPool *nextPool;
    void *(PVMCALL memAlloc) (void *userData, U_32 byteAmount);
    void (PVMCALL memFree) (void *userData, void *ptr);
    void *userData;
    U_16 alignment;
    U_16 flags;
  } HyPool;

#define POOL_FOR_PORT(portLib)  (hymemAlloc_fptr_t)(portLib)->mem_allocate_memory, (hymemFree_fptr_t)(portLib)->mem_free_memory, portLib
#define POOL_ALWAYS_KEEP_SORTED  4
#define POOL_NEVER_FREE_PUDDLES  2
#define POOL_SORTED              1

typedef struct HyPoolState
  {
    UDATA leftToDo;
    struct HyPool *currPool;
    UDATA *lastAddr;
    UDATA **nextFree;
  } HyPoolState;

#define pool_state HyPoolState

/* HySourcePool*/
extern HY_CFUNC void VMCALL pool_do
    PROTOTYPE ((HyPool * aPool,
                void (*aFunction) (void *anElement, void *userData),
                void *userData));
extern HY_CFUNC HyPool *VMCALL pool_new
    PROTOTYPE ((U_32 structSize, U_32 minNumberElements,
                U_32 elementAlignment, UDATA poolFlags,
                void *(VMCALL * memAlloc) (void *, U_32),
                void (VMCALL * memFree) (void *, void *), void *userData));
extern HY_CFUNC void *VMCALL pool_startDo
    PROTOTYPE ((HyPool * aPool, pool_state * lastHandle));
extern HY_CFUNC void VMCALL pool_removeElement
    PROTOTYPE ((HyPool * aPool, void *anElement));
extern HY_CFUNC UDATA VMCALL pool_numElements PROTOTYPE ((HyPool * aPool));
extern HY_CFUNC void *VMCALL pool_nextDo
    PROTOTYPE ((pool_state * lastHandle));
extern HY_CFUNC void *VMCALL pool_newElement PROTOTYPE ((HyPool * aPool));
extern HY_CFUNC HyPool *VMCALL pool_forPortLib
    PROTOTYPE ((U_32 structSize, HyPortLibrary * portLibrary));
extern HY_CFUNC void VMCALL pool_kill PROTOTYPE ((HyPool * aPool));
extern HY_CFUNC void VMCALL pool_sortFree PROTOTYPE ((HyPool * aPool));
extern HY_CFUNC void VMCALL pool_clear PROTOTYPE ((HyPool * aPool));

/* HySourcePoolCapacity*/
extern HY_CFUNC UDATA VMCALL pool_capacity PROTOTYPE ((HyPool * aPool));
extern HY_CFUNC UDATA VMCALL pool_ensureCapacity
    PROTOTYPE ((HyPool * aPool, UDATA newCapacity));

#if defined(__cplusplus)
}
#endif

#endif  /* HYPOOL_H */
