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
 * @brief Pool-capacity functions
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
 * Ensures that the pool is large enough for newCapacity elements.
 * This has the side effect of setting the POOL_NEVER_FREE_PUDDLES flag.
 * Without this, the pool could shrink back down to its original size.
 * Note that this does not take into account the number of elements already
 * used in the pool.
 *
 * @param[in] aPool The pool
 * @param[in] newCapacity The desired new-size of the pool
 *
 * @return 0 on success
 * @return -1 on failure
 * 
 */
UDATA VMCALL
pool_ensureCapacity (HyPool * aPool, UDATA newCapacity)
{
  UDATA numElements = pool_capacity (aPool);

  /* find the last pool and mark each pool as POOL_NEVER_FREE_PUDDLES */
  for (;;)
    {
      aPool->flags |= POOL_NEVER_FREE_PUDDLES;
      if (aPool->nextPool == NULL)
        break;
      aPool = aPool->nextPool;
    }

  if (newCapacity > numElements)
    {
      UDATA newSize = newCapacity - numElements;
      if (newSize < aPool->numberOfElements)
        {
          newSize = aPool->numberOfElements;
        }

      aPool->nextPool =
        pool_new (aPool->elementSize, newSize, aPool->alignment, aPool->flags,
                  aPool->memAlloc, aPool->memFree, aPool->userData);
      if (aPool->nextPool == NULL)
        return -1;
    }

  return 0;
}

/**
 * Returns the total capacity of a pool
 *
 * @param[in] aPool The pool
 *
 * @return 0 on error
 * @return numElements in aPool otherwise
 *
 */
UDATA VMCALL
pool_capacity (HyPool * aPool)
{
  UDATA numElements = 0;
  while (aPool)
    {
      numElements += aPool->numberOfElements;
      aPool = aPool->nextPool;
    }
  return numElements;
}
