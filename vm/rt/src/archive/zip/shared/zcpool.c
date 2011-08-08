/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @file
 * @ingroup ZipSupport
 * @brief Zip Support for Java VM
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "hyport.h"
#include "zipsup.h"
#include "hypool.h"

#include "hymutex.h"

typedef struct HyZipCachePoolEntry
{
  HyZipCache *cache;
  UDATA referenceCount;
} HyZipCachePoolEntry;

/* No typedef because an opaque typedef appears in zipsup.h (already included) */
struct HyZipCachePool
{
  HyPool *pool;
  HyZipCache *desiredCache;
  I_64 zipTimeStamp;
  char const *zipFileName;
  IDATA zipFileNameLength;
  IDATA zipFileSize;
  MUTEX mutex;
};

void zipCachePool_doFindHandler
PROTOTYPE ((HyZipCachePoolEntry * entry, HyZipCachePool * zcp));
void zipCachePool_doKillHandler
PROTOTYPE ((HyZipCachePoolEntry * entry, HyZipCachePool * zcp));

/**
 * Add a new cache to the pool with reference count of 1.
 * 
 * When reference count reaches zero 	the pool will automatically be freed.
 *
 * @param[in] zcp the zip cache pool that is being added to.
 * @param[in] zipCache the zip cache being added.
 *
 * @return TRUE if successful, FALSE otherwise.
 *
 * @note A cache may only reside in one pool (read: multiple VMs may not share caches with each other). 
*/

BOOLEAN
zipCachePool_addCache (HyZipCachePool * zcp, HyZipCache * zipCache)
{
  HyZipCachePoolEntry *entry;

  if (!zcp || !zipCache)
    return FALSE;

  MUTEX_ENTER (zcp->mutex);

  entry = pool_newElement (zcp->pool);
  if (!entry)
    {
      MUTEX_EXIT (zcp->mutex);
      return FALSE;
    }

  zipCache->cachePool = zcp;
  zipCache->cachePoolEntry = entry;

  entry->cache = zipCache;
  entry->referenceCount = 1;

  MUTEX_EXIT (zcp->mutex);
  return TRUE;
}

/** 
 * Increment the reference count of a cache in the pool.
 * 
 * @note Result is undefined if the cache is not actually in the pool!
 *
 * @param[in] zcp the zip cache pool that is being added to.
 * @param[in] zipCache the zip cache being added.
 *
 * @return TRUE if successful, FALSE otherwise.
*/

BOOLEAN
zipCachePool_addRef (HyZipCachePool * zcp, HyZipCache * zipCache)
{
  HyZipCachePoolEntry *entry;

  if (!zcp || !zipCache)
    return FALSE;

  MUTEX_ENTER (zcp->mutex);

  entry = (HyZipCachePoolEntry *) zipCache->cachePoolEntry;
  if (!entry)
    {
      MUTEX_EXIT (zcp->mutex);
      return FALSE;
    }

  entry->referenceCount++;

  MUTEX_EXIT (zcp->mutex);
  return TRUE;
}

/**
 * Scans the pool for a cache with matching zipFileName, zipFileSize and zipTimeStamp.
 *
 * The reference count is incremented and the cache is returned if a match is found. 
 *
 * @param[in] zcp the zip cache pool to search
 * @param[in] zipFileName the name to test for match
 * @param[in] zipFileNameLength the length of zipFileName
 * @param[in] zipFileSize the size to test for match
 * @param[in] zipTimeStamp the time stamp to test for match 
 *
 * @return the matching zip cache
 * @return NULL if no match is found.
 */

HyZipCache *
zipCachePool_findCache (HyZipCachePool * zcp, char const *zipFileName,
                        IDATA zipFileNameLength, IDATA zipFileSize,
                        I_64 zipTimeStamp)
{
  HyZipCache *zipCache;
  HyZipCachePoolEntry *entry;

  if (!zcp || !zipFileName)
    return NULL;

  MUTEX_ENTER (zcp->mutex);

  /* Find a suitable cache */
  zcp->desiredCache = NULL;
  zcp->zipFileName = zipFileName;
  zcp->zipFileSize = zipFileSize;
  zcp->zipTimeStamp = zipTimeStamp;
  zcp->zipFileNameLength = zipFileNameLength;

  pool_do (zcp->pool, (void (*)(void *, void *)) zipCachePool_doFindHandler,
           zcp);
  zipCache = zcp->desiredCache;

  if (zipCache)
    {
      entry = (HyZipCachePoolEntry *) zipCache->cachePoolEntry;
      entry->referenceCount++;
    }

  MUTEX_EXIT (zcp->mutex);
  return zipCache;
}

/** 
 * Deletes a pool containing shareable zip caches.
 *
 * @param[in] zcp the zip cache pool that is being deleted
 *
 * @return none
 *
 * @note Warning: This also deletes remaining caches in the pool, regardless of their reference counts!
 * 
 */
void
zipCachePool_kill (HyZipCachePool * zcp)
{
  void (VMCALL * memFree) (void *, void *);
  void *userData;

  if (!zcp)
    return;

  pool_do (zcp->pool, (void (*)(void *, void *)) zipCachePool_doKillHandler,
           zcp);

  MUTEX_DESTROY (zcp->mutex);

  /* Grab the memFree and userData out of the pool BEFORE we destroy it. */
  memFree = zcp->pool->memFree;
  userData = zcp->pool->userData;
  pool_kill (zcp->pool);
  memFree (userData, zcp);
}

/**
 * Creates a pool to hold shareable zip caches with their reference counts. 
 * This should be called once per VM. 
 * 
 * @param[in] portLib the port library
 *
 * @return a zip cache pool or NULL if one cannot be created
 *
*/

HyZipCachePool *
zipCachePool_new (HyPortLibrary * portLib)
{
  PORT_ACCESS_FROM_PORT (portLib);

  HyZipCachePool *p = hymem_allocate_memory (sizeof (*p));
  HyZipCachePool *toReturn = NULL;

  if (p != NULL)
    {
      if (MUTEX_INIT (p->mutex))
        {
          p->pool = pool_forPortLib (sizeof (HyZipCachePoolEntry), portLib);
          if (p->pool)
            {
              /* All initialization worked so set up to return the pointer */
              toReturn = p;
            }
          else
            {
              /* pool discovery failed so give up the mutex */
              MUTEX_DESTROY (p->mutex);
            }
        }
      if (NULL == toReturn)
        {
          /* something went wrong so free the memory */
          hymem_free_memory (p);
        }
    }
  return toReturn;
}

/**
 * Decrements the reference count of a cache in the pool.
 * If the reference count reaches 0, the cache is removed from the pool and @ref zipCache_kill is called on it. 
 *
 * @param[in] zcp the zip cache pool
 * @param[in] zipCache the zip cache whose count is being decremented.
 *
 * @return TRUE if the cache was destroyed
 * @return FALSE if the cache is still in the pool.
 *
 */

BOOLEAN
zipCachePool_release (HyZipCachePool * zcp, HyZipCache * zipCache)
{
  HyZipCachePoolEntry *entry;

  if (!zcp || !zipCache)
    return FALSE;

  MUTEX_ENTER (zcp->mutex);

  entry = (HyZipCachePoolEntry *) zipCache->cachePoolEntry;
  if (!entry)
    {
      /* What the..? */
      MUTEX_EXIT (zcp->mutex);
      return FALSE;
    }

  if (--entry->referenceCount != 0)
    {
      MUTEX_EXIT (zcp->mutex);
      return FALSE;
    }

  /* Reference count is zero, get rid of the cache */
  zipCache_kill (entry->cache);
  pool_removeElement (zcp->pool, entry);

  MUTEX_EXIT (zcp->mutex);
  return TRUE;
}

void
zipCachePool_doFindHandler (HyZipCachePoolEntry * entry, HyZipCachePool * zcp)
{

  if (zcp->desiredCache)
    return;                     /* already done */

  if (entry->cache->zipTimeStamp != zcp->zipTimeStamp)
    return;
  if (entry->cache->zipFileSize != zcp->zipFileSize)
    return;
  if (memcmp
      (entry->cache->zipFileName, zcp->zipFileName, zcp->zipFileNameLength))
    return;
  if (entry->cache->zipFileName[zcp->zipFileNameLength] != '\0')
    return;

  /* Looks like we have a match. */
  zcp->desiredCache = entry->cache;
}

void
zipCachePool_doKillHandler (HyZipCachePoolEntry * entry, HyZipCachePool * zcp)
{
  zipCache_kill (entry->cache);
}
