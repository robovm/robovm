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
 * @brief Zip Support for VM
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "hyport.h"
#include "zipsup.h"
#include "hypool.h"

#define UDATA_TOP_BIT    (((UDATA)1)<<(sizeof(UDATA)*8-1))
#define ISCLASS_BIT    UDATA_TOP_BIT
#define NOT_FOUND	((UDATA) (~0))
#define OFFSET_MASK	(~ISCLASS_BIT)
#define	IMPLICIT_ENTRY	(~ISCLASS_BIT)

/* This should be a multiple of the page size, minus a few UDATAs in case
   the OS allocator needs header space (so we don't waste a page).
   If the OS provides a fine-grain allocator (e.g. Windows) then it doesn't really
   matter if we don't fit in one page, but the KISS principle applies.. */
#define ACTUAL_CHUNK_SIZE	(4096 - 4*sizeof(UDATA) )

typedef struct HaZipChunkHeader
{
  struct HaZipChunkHeader *next;
  U_8 *beginFree;               /* UDATA-aligned, points to first free byte */
  U_8 *endFree;                 /* unaligned, points to the byte after the last free byte */
#if defined(ATOMIC_LONG_ACCESS)
  UDATA padding;                /* align to 64 */
#endif

} HaZipChunkHeader;

typedef struct HyZipFileEntry
{
  char *name;
  UDATA nameLength;
  UDATA zipFileOffset;
} HyZipFileEntry;

/* a file record can hold a variable number of file entries. */
typedef struct HyZipFileRecord
{
  struct HyZipFileRecord *next;
  UDATA entryCount;
  HyZipFileEntry entry[1];
} HyZipFileRecord;

typedef struct HaZipDirEntry
{
  struct HaZipDirEntry *next;
  struct HyZipFileRecord *fileList;
  struct HaZipDirEntry *dirList;
  char *name;
  UDATA zipFileOffset;
#if defined(ATOMIC_LONG_ACCESS)
  UDATA padding;                /* align to 64 */
#endif

} HaZipDirEntry;

/* trick: a HyZipCache * is a pointer to a HyZipCacheEntry which is the first entry
	in the first chunk of the cache.  This saves us one hymem_allocate_memory
	(or probably two if the zipName isn't huge) */

typedef struct HyZipCacheEntry
{
  HyZipCache info;              /* publicly visible part */
  HaZipChunkHeader *currentChunk;
  HaZipDirEntry *chunkActiveDir;
  HaZipDirEntry root;
} HyZipCacheEntry;

typedef struct HyZipCacheTraversal
{
  HyZipCache *zipCache;
  HyPortLibrary *portLib;
  HaZipDirEntry *dirEntry;
  HyZipFileRecord *fileRecord;
  UDATA fileRecordPos;
} HyZipCacheTraversal;

void zipCache_freeChunk
PROTOTYPE ((HyPortLibrary * portLib, HaZipChunkHeader * chunk));
HaZipDirEntry *zipCache_searchDirListCaseInsensitive
PROTOTYPE ((HaZipDirEntry * dirEntry, const char *namePtr, UDATA nameSize,
            BOOLEAN isClass));
HaZipChunkHeader *zipCache_allocateChunk
PROTOTYPE ((HyPortLibrary * portLib));
HyZipFileEntry *zipCache_addToFileList
PROTOTYPE ((HyZipCacheEntry * zce, HaZipDirEntry * dirEntry,
            const char *namePtr, IDATA nameSize, BOOLEAN isClass,
            UDATA elementOffset));
UDATA *zipCache_reserveEntry
PROTOTYPE ((HaZipChunkHeader * chunk, UDATA entryBytes, UDATA stringBytes));
HyZipFileEntry *zipCache_searchFileList
PROTOTYPE ((HaZipDirEntry * dirEntry, const char *namePtr, UDATA nameSize,
            BOOLEAN isClass));
HaZipDirEntry *zipCache_addToDirList
PROTOTYPE ((HyZipCacheEntry * zce, HaZipDirEntry * dirEntry,
            const char *namePtr, int nameSize, BOOLEAN isClass));
HaZipDirEntry *zipCache_searchDirList
PROTOTYPE ((HaZipDirEntry * dirEntry, const char *namePtr, UDATA nameSize,
            BOOLEAN isClass));
IDATA helper_memicmp
PROTOTYPE ((const void *src1, const void *src2, UDATA length));

/** 
 * Creates a new, empty zip cache for the provided zip file.
 *
 * @param[in] portLib the port library
 * @param[in] zipName the zip file name
 * @param[in] zipNameLength
 *
 * @return the new zip cache if one was successfully created, NULL otherwise
 *
*/

HyZipCache *
zipCache_new (HyPortLibrary * portLib, char *zipName, IDATA zipNameLength)
{
  HaZipChunkHeader *chunk;
  HyZipCacheEntry *zce;

  PORT_ACCESS_FROM_PORT (portLib);

  chunk = zipCache_allocateChunk (portLib);
  if (!chunk)
    return NULL;

  zce =
    (HyZipCacheEntry *) zipCache_reserveEntry (chunk,
                                               sizeof (HyZipCacheEntry), 0);
  if (!zce)
    {
      /* ACTUAL_CHUNK_SIZE is so small it can't hold one HyZipCacheEntry?? */
      zipCache_freeChunk (portLib, chunk);
      return NULL;
    }

  zce->info.portLib = portLib;
  zce->currentChunk = chunk;

  /* Try to put the name string in this chunk.  If it won't fit, we'll allocate it separately */
  if (zipCache_reserveEntry (chunk, 0, zipNameLength + 1))
    {
      zce->info.zipFileName = chunk->endFree;
    }
  else
    {
      zce->info.zipFileName = hymem_allocate_memory (zipNameLength + 1);
      if (!zce->info.zipFileName)
        {
          zipCache_freeChunk (portLib, chunk);
          return NULL;
        }
    }
  memcpy (zce->info.zipFileName, zipName, zipNameLength);
  zce->info.zipFileName[zipNameLength] = '\0';
  zce->info.zipFileSize = zce->info.startCentralDir = -1;
  zce->info.zipTimeStamp = -1;
  /* zce->info.cachePool is already NULL */
  /* zce->info.cachePoolEntry is already NULL */
  zce->root.zipFileOffset = 1;

  return (HyZipCache *) zce;
}

/**
 * Add an association between a file or directory named elementName and offset elementOffset to the zip cache provided
 *
 * @param[in] zipCache the zip cache being added to
 * @param[in] elementName the name of the file or directory element
 * @param[in] elementOffset the corresponding offset of the element
 *
 * @return TRUE if the association was made, FALSE otherwise
 *
*/

BOOLEAN
zipCache_addElement (HyZipCache * zipCache, char *elementName,
                     UDATA elementOffset)
{
  HyZipCacheEntry *zce = (HyZipCacheEntry *) zipCache;
  HaZipDirEntry *dirEntry;
  HyZipFileEntry *fileEntry;
  char *curName;
  IDATA curSize;
  IDATA prefixSize;
  BOOLEAN isClass;

  if (!zipCache || !elementName || !elementName[0]
      || (elementOffset & ISCLASS_BIT)
      || ((elementOffset & OFFSET_MASK) == IMPLICIT_ENTRY))
    return FALSE;

  dirEntry = &zce->root;

  curName = elementName;
  for (;;)
    {
      HaZipDirEntry *d;

      /* scan forwards in curName until '/' or NUL */
      for (curSize = 0; curName[curSize] && (curName[curSize] != '/');
           curSize++)
        /* nothing */ ;

      prefixSize = curSize + 1;
      isClass = FALSE;

      if ((curSize >= 6) && !memcmp (&curName[curSize - 6], ".class", 6))
        {
          isClass = TRUE;
          curSize -= 6;
        }

      if (!*curName)
        {
          /* We ran out of string, which means the elementName was */
          /* a directory name---in fact, it was the subdir we parsed */
          /* last time through the loop. */

          if ((dirEntry->zipFileOffset & OFFSET_MASK) != IMPLICIT_ENTRY)
            {
              /* Can't add the same directory more than once! */
              return TRUE;
            }
          dirEntry->zipFileOffset =
            elementOffset | (isClass ? ISCLASS_BIT : 0);
          return TRUE;
        }

      if (curName[curSize] != '/')
        {
          /* The prefix we're looking at doesn't end with a '/', which means */
          /* it is really the suffix of the elementName, and it's a filename. */

          fileEntry =
            zipCache_searchFileList (dirEntry, curName, curSize, isClass);
          if (fileEntry)
            {
              /* We've seen this file before...update the entry to the new offset. */
              fileEntry->zipFileOffset =
                elementOffset | (isClass ? ISCLASS_BIT : 0);
            }
          else
            {
              if (!zipCache_addToFileList
                  (zce, dirEntry, curName, curSize, isClass, elementOffset))
                return FALSE;
            }
          return TRUE;
        }

      /* If we got here, we're looking at a prefix which ends with '/' */
      /* Treat that prefix as a subdirectory.  If it doesn't exist, create it implicitly */

      if (!(d = zipCache_searchDirList (dirEntry, curName, curSize, isClass)))
        {
          if (!
              (d =
               zipCache_addToDirList (zce, dirEntry, curName, curSize,
                                      isClass)))
            {
              return FALSE;
            }
        }
      dirEntry = d;
      curName += prefixSize;
    }
}

/**
 * Returns the offset associated with a file or directory element named elementName 
 * in a zipCache.
 *
 * @param[in] zipCache the zip cache we are searching
 * @param[in] elementName the name of the element of which we want the offset
 * @param[in] searchDirList when TRUE, search the dir list even if elementName does not end in '/'
 *
 * @return the zipCache if a match is found
 * @return -1 if no element of that name has been explicitly added to the cache.
 *
*/

UDATA
zipCache_findElement (HyZipCache * zipCache, const char *elementName,
                      BOOLEAN searchDirList)
{
  HyZipCacheEntry *zce = (HyZipCacheEntry *) zipCache;
  HaZipDirEntry *dirEntry;
  HyZipFileEntry *fileEntry;
  const char *curName;
  IDATA curSize;
  IDATA prefixSize;
  BOOLEAN isClass;

  if (!zipCache || !elementName || !elementName[0])
    return NOT_FOUND;

  dirEntry = &zce->root;

  curName = elementName;
  for (;;)
    {

      /* scan forwards in curName until '/' or NUL */
      for (curSize = 0; curName[curSize] && (curName[curSize] != '/');
           curSize++)
        /* nothing */ ;

      prefixSize = curName[curSize] ? curSize + 1 : curSize;
      isClass = FALSE;

      if ((curSize >= 6) && !memcmp (&curName[curSize - 6], ".class", 6))
        {
          isClass = TRUE;
          curSize -= 6;
        }

      if (!*curName)
        {
          /* We ran out of string, which means the elementName was */
          /* a directory name---in fact, it was the subdir we parsed */
          /* last time through the loop. */

          /* directory may have been implicitly but not explicitly added */
          if ((dirEntry->zipFileOffset & OFFSET_MASK) == IMPLICIT_ENTRY)
            return NOT_FOUND;   /* if it was never added, it doesn't "really" exist! */

          return dirEntry->zipFileOffset & OFFSET_MASK;
        }

      if (curName[curSize] != '/')
        {
          /* The prefix we're looking at doesn't end with a '/', which means */
          /* it is really the suffix of the elementName, and it's a filename. */

          fileEntry =
            zipCache_searchFileList (dirEntry, curName, curSize, isClass);
          if (fileEntry)
            {
              return fileEntry->zipFileOffset & OFFSET_MASK;
            }
          if (!searchDirList)
            {
              return NOT_FOUND;
            }
        }

      /* If we got here, we're looking at a prefix which ends with '/', or searchDirList is TRUE */
      /* Treat that prefix as a subdirectory.  It will exist if elementName was added before. */

      dirEntry = zipCache_searchDirList (dirEntry, curName, curSize, isClass);
      if (!dirEntry)
        return NOT_FOUND;
      curName += prefixSize;
    }
}

/** 
 * Deletes a zip cache and frees its resources
 *
 * @param[in] zipCache the zip cache to be freed
 *
 * @return none
 *
 * @see zipCache_new
 *
*/

void
zipCache_kill (HyZipCache * zipCache)
{
  HaZipChunkHeader *chunk, *chunk2;
  HyZipCacheEntry *zce = (HyZipCacheEntry *) zipCache;
  HyPortLibrary *portLib = zce->info.portLib;

  PORT_ACCESS_FROM_PORT (portLib);

  chunk =
    (HaZipChunkHeader *) (((U_8 *) zipCache) - sizeof (HaZipChunkHeader));

  if (((UDATA) ((U_8 *) zce->info.zipFileName - (U_8 *) chunk)) >=
      ACTUAL_CHUNK_SIZE)
    {
      /* zce->info.zipFileName points outside the first chunk, therefore it was allocated
         separately rather than being reserved from the chunk */
      hymem_free_memory (zce->info.zipFileName);
    }

  chunk = zce->currentChunk;
  while (chunk)
    {
      chunk2 = chunk->next;
      zipCache_freeChunk (portLib, chunk);
      chunk = chunk2;
    }
}

/* Allocate a new HaZipDirEntry and insert into dirEntry's dirList. */

HaZipDirEntry *
zipCache_addToDirList (HyZipCacheEntry * zce, HaZipDirEntry * dirEntry,
                       const char *namePtr, int nameSize, BOOLEAN isClass)
{
  HaZipDirEntry *entry;
  HaZipChunkHeader *chunk = zce->currentChunk;
  zce->chunkActiveDir = NULL;

  entry =
    (HaZipDirEntry *) zipCache_reserveEntry (chunk, sizeof (*entry),
                                             nameSize + 1);
  if (!entry)
    {
      if (!(chunk = zipCache_allocateChunk (zce->info.portLib)))
        return NULL;
      chunk->next = zce->currentChunk;
      zce->currentChunk = chunk;
      entry =
        (HaZipDirEntry *) zipCache_reserveEntry (chunk, sizeof (*entry),
                                                 nameSize + 1);
      if (!entry)
        {
          /* ACTUAL_CHUNK_SIZE is so small it can't hold one HaZipDirEntry?? */
          return NULL;
        }
    }
  entry->next = dirEntry->dirList;
  dirEntry->dirList = entry;
  entry->zipFileOffset = IMPLICIT_ENTRY | (isClass ? ISCLASS_BIT : 0);
  entry->name = (char *) chunk->endFree;
  memcpy (entry->name, namePtr, nameSize);
  /* chunk->endFree[nameSize] is already zero (NUL) */
  return entry;
}

/* Allocate a new zipFileEntry and insert it into dirEntry's fileList. */
/* If possible, the new file entry will be appended to the active zipFileRecord. */
/* Otherwise, a new zipFileRecord will be allocated to hold the new zipFileEntry. */

HyZipFileEntry *
zipCache_addToFileList (HyZipCacheEntry * zce, HaZipDirEntry * dirEntry,
                        const char *namePtr, IDATA nameSize, BOOLEAN isClass,
                        UDATA elementOffset)
{
  HyZipFileEntry *entry;
  HyZipFileRecord *record;
  HaZipChunkHeader *chunk = zce->currentChunk;

  if (zce->chunkActiveDir == dirEntry)
    {
      if ((entry =
          (HyZipFileEntry *) zipCache_reserveEntry (chunk, sizeof (*entry),
                                                    nameSize + 1)))
        {
          /* add to end of existing entry */
          zce->chunkActiveDir->fileList->entryCount++;
          goto haveEntry;
        }
    }

  record =
    (HyZipFileRecord *) zipCache_reserveEntry (chunk, sizeof (*record),
                                               nameSize + 1);
  if (!record)
    {
      if (!(chunk = zipCache_allocateChunk (zce->info.portLib)))
        return NULL;
      chunk->next = zce->currentChunk;
      zce->currentChunk = chunk;
      zce->chunkActiveDir = NULL;
      record =
        (HyZipFileRecord *) zipCache_reserveEntry (chunk, sizeof (*record),
                                                   nameSize + 1);
      if (!record)
        {
          /* ACTUAL_CHUNK_SIZE is so small it can't hold one zipFileRecord?? */
          return NULL;
        }
    }
  record->next = dirEntry->fileList;
  dirEntry->fileList = record;

  zce->chunkActiveDir = dirEntry;
  record->entryCount = 1;
  entry = record->entry;

haveEntry:
  entry->name = (char *) chunk->endFree;
  memcpy (entry->name, namePtr, nameSize);
  /* chunk->endFree[nameSize] is already zero (NUL) */
  entry->nameLength = nameSize;
  entry->zipFileOffset = elementOffset | (isClass ? ISCLASS_BIT : 0);
  return entry;
}

/* Allocate a new chunk and initialize its zipChunkHeader. */

HaZipChunkHeader *
zipCache_allocateChunk (HyPortLibrary * portLib)
{
  HaZipChunkHeader *chunk;
  PORT_ACCESS_FROM_PORT (portLib);

  chunk = (HaZipChunkHeader *) hymem_allocate_memory (ACTUAL_CHUNK_SIZE);
  if (!chunk)
    return NULL;
  memset (chunk, 0, ACTUAL_CHUNK_SIZE);
  chunk->beginFree = ((U_8 *) chunk) + sizeof (HaZipChunkHeader);
  chunk->endFree = ((U_8 *) chunk) + ACTUAL_CHUNK_SIZE;
  return chunk;
}

/* Frees a chunk which is no longer used. */
/* portLib must be the original portLib which was passed to zipCache_allocateChunk. */

void
zipCache_freeChunk (HyPortLibrary * portLib, HaZipChunkHeader * chunk)
{
  PORT_ACCESS_FROM_PORT (portLib);

  hymem_free_memory (chunk);
}

/* Tries to reserve storage in a chunk for entryBytes of header data, and */
/* stringBytes of string data.  If there is not enough storage, NULL is */
/* returned and no storage is reserved.  If there is enough storage, a */
/* pointer is returned to the allocated entryBytes, and chunk->bottom points */
/* to the allocated stringBytes. */

UDATA *
zipCache_reserveEntry (HaZipChunkHeader * chunk, UDATA entryBytes,
                       UDATA stringBytes)
{
  UDATA *entry;

  if (!chunk)
    return NULL;

  if ((chunk->endFree - chunk->beginFree) <
      (IDATA) (entryBytes + stringBytes))
    return NULL;

  entry = (UDATA *) (chunk->beginFree);
  chunk->beginFree += entryBytes;
  chunk->endFree -= stringBytes;
  return entry;
}

/* Searches the dirList in dirEntry for a directory entry named */
/* namePtr[0..nameSize-1] with the specified isClass value. */

HaZipDirEntry *
zipCache_searchDirList (HaZipDirEntry * dirEntry, const char *namePtr,
                        UDATA nameSize, BOOLEAN isClass)
{
  HaZipDirEntry *entry;

  if (!dirEntry || !namePtr)
    return NULL;

  entry = dirEntry->dirList;
  while (entry)
    {
      if (!memcmp (entry->name, namePtr, nameSize) && !entry->name[nameSize])
        {
          if (isClass && (entry->zipFileOffset & ISCLASS_BIT))
            return entry;
          if (!isClass && !(entry->zipFileOffset & ISCLASS_BIT))
            return entry;
        }
      entry = entry->next;
    }
  return NULL;
}

/* Searches the fileList in dirEntry for a file entry named */
/* namePtr[0..nameSize-1] with the specified isClass value. */

HyZipFileEntry *
zipCache_searchFileList (HaZipDirEntry * dirEntry, const char *namePtr,
                         UDATA nameSize, BOOLEAN isClass)
{
  HyZipFileRecord *record;
  HyZipFileEntry *entry;
  IDATA i;

  if (!dirEntry || !namePtr)
    return NULL;

  record = dirEntry->fileList;
  while (record)
    {
      for (i = record->entryCount; i--;)
        {
          entry = &record->entry[i];
          if (entry->nameLength == nameSize)
            {
              if (!memcmp (entry->name, namePtr, nameSize))
                {
                  if (isClass && (entry->zipFileOffset & ISCLASS_BIT))
                    return &record->entry[i];
                  if (!isClass && !(entry->zipFileOffset & ISCLASS_BIT))
                    return &record->entry[i];
                }
            }
        }
      record = record->next;
    }
  return NULL;
}

/** 
 * Searches for a directory named elementName in zipCache and if found provides 
 * a handle to it that can be used to enumerate through all of the directory's files.
 * 
 * @note The search is CASE-INSENSITIVE (contrast with @ref zipCache_findElement, which is case-sensitive).
 * @note The search is NOT recursive.
 *
 * @param[in] zipCache the zip cache that is being searched
 * @param[in] directoryName the directory we want to enumerate
 * @param[out] handle enumerate all the files in directory directoryName on this handle
 *
 * @return 0 on success and sets handle
 * @return -1 if the directory is not found
 * @return -2 if there is not enough memory to complete this call
 *
 * @see zipCache_findElement
 */

IDATA
zipCache_enumNew (HyZipCache * zipCache, char *directoryName, void **handle)
{
  HyZipCacheEntry *zce = (HyZipCacheEntry *) zipCache;
  HaZipDirEntry *dirEntry;
  char *curName;
  IDATA curSize;
  IDATA prefixSize;
  BOOLEAN isClass;

  if (!zipCache || !directoryName || !directoryName[0] || !handle)
    {
      return -3;
    }
  else
    {
      PORT_ACCESS_FROM_PORT (zce->info.portLib);

      dirEntry = &zce->root;

      curName = directoryName;
      for (;;)
        {

          /* scan forwards in curName until '/' or NUL */
          for (curSize = 0; curName[curSize] && (curName[curSize] != '/');
               curSize++)
            /* nothing */ ;

          prefixSize = curSize + 1;
          isClass = FALSE;

          /* Note: CASE-INSENSITIVE HERE */
          if ((curSize >= 6)
              && !helper_memicmp (&curName[curSize - 6], ".class", 6))
            {
              isClass = TRUE;
              curSize -= 6;
            }

          if (!*curName)
            {
              /* We ran out of string, which means directoryName was */
              /* the subdir we parsed last time through the loop.  Begin the traversal here. */
              HyZipCacheTraversal *traversal =
                hymem_allocate_memory (sizeof (*traversal));
              if (!traversal)
                {
                  return -2;
                }
              traversal->zipCache = zipCache;
              traversal->portLib = zce->info.portLib;
              traversal->dirEntry = dirEntry;
              traversal->fileRecord = dirEntry->fileList;
              traversal->fileRecordPos = 0;

              /* ensure an automatically-managed cache doesn't go away while enumerating */
              if (zce->info.cachePool)
                {
                  zipCachePool_addRef (zce->info.cachePool, zipCache);
                }

              *handle = traversal;
              return 0;
            }

          if (curName[curSize] != '/')
            {
              /* The prefix we're looking at doesn't end with a '/', which means */
              /* it is really the suffix of the directoryName, and it's a filename. */

              return -1;        /* We're not interested in filenames */
            }

          /* If we got here, we're looking at a prefix which ends with '/' */
          /* Treat that prefix as a subdirectory.  It will exist if directoryName has been
             added or if any file or directory inside directoryName has been added. */

          dirEntry =
            zipCache_searchDirListCaseInsensitive (dirEntry, curName, curSize,
                                                   isClass);
          if (!dirEntry)
            {
              return -1;
            }
          curName += prefixSize;
        }
    }
}

/** 
 * Gets the name and offset of the next element in the directory being enumerated.
 *
 *	If nameBufSize is insufficient to hold the entire name, returns the required size for nameBuf.

 * @note Does NOT skip the element if nameBufSize buffer is of insufficient size to hold the entire name.
 *
 * @param[in] handle returned from @ref zipCache_enumNew. Used to enumerate the elements corresponding to the directory name returned by @ref zipCache_enumGetDirName
 * @param[out] nameBuf holder for element in the directory being enumerated
 * @param[in] nameBufSize
 * @param[out] offset the offset of the next element
 *
 * @return 0 on success
 * @return -1 if all the directories have been returned already
 * @return the required size of nameBuf if nameBufSize is insufficient to hold the entire name (does not skip the element)
 *
 * @see zipCache_enumNew
*
*/
IDATA
zipCache_enumElement (void *handle, char *nameBuf, UDATA nameBufSize,
                      UDATA * offset)
{
  HyZipCacheTraversal *traversal = (HyZipCacheTraversal *) handle;
  HyZipFileEntry *fileEntry;
  UDATA nameLen;

  if (!traversal || !nameBuf || !nameBufSize)
    return -3;

  if (!traversal->fileRecord)
    return -1;                  /* No more elements */

  fileEntry = &traversal->fileRecord->entry[traversal->fileRecordPos];

  nameLen = fileEntry->nameLength + 1;
  if (fileEntry->zipFileOffset & ISCLASS_BIT)
    nameLen += 6;
  if (nameBufSize < nameLen)
    {
      /* Buffer is too small.  Return size the caller must allocate to try again. */
      return nameLen;
    }

  memcpy (nameBuf, fileEntry->name, fileEntry->nameLength);
  if (fileEntry->zipFileOffset & ISCLASS_BIT)
    memcpy (nameBuf + fileEntry->nameLength, ".class", 6);
  nameBuf[nameLen - 1] = 0;
  if (offset)
    *offset = fileEntry->zipFileOffset & OFFSET_MASK;

  /* Advance to the next element */
  ++traversal->fileRecordPos;
  if (traversal->fileRecordPos >= traversal->fileRecord->entryCount)
    {
      traversal->fileRecord = traversal->fileRecord->next;
      traversal->fileRecordPos = 0;
    }
  return 0;
}

/** 
 * Gets the name of the directory on which the enumeration is based.
 * 
 * @param[in] handle handle returned from @ref zipCache_enumNew.
 * @param[out] nameBuf buffer to hold the directory name
 * @param[in] nameBufSize
 *
 * @return 0 on success
 * @return -3 on param failures
 * @return the required size for nameBuf if nameBufSize is insufficient to hold the entire name
 * 
 */

IDATA
zipCache_enumGetDirName (void *handle, char *nameBuf, UDATA nameBufSize)
{
  HyZipCacheTraversal *traversal = (HyZipCacheTraversal *) handle;
  HaZipDirEntry *dirEntry;
  UDATA nameLen;

  if (!traversal || !nameBuf || !nameBufSize)
    return -3;

  dirEntry = traversal->dirEntry;
  nameLen = strlen (dirEntry->name) + 1 + 1;    /* for '/' and null */
  if (nameBufSize < nameLen)
    {
      /* Buffer is too small.  Return size the caller must allocate to try again. */
      return nameLen;
    }

  strcpy (nameBuf, dirEntry->name);
  strcat (nameBuf, "/");
  return 0;
}

/** 
 * Frees any resources allocated by @ref zipCache_enumNew.
 * 
 * @param[in] handle enumerate on this handle
 *
 * @return none
 *
 * @see zipCache_enumNew
 */

void
zipCache_enumKill (void *handle)
{
  HyZipCacheTraversal *traversal = (HyZipCacheTraversal *) handle;

  if (!traversal)
    {
      return;
    }
  else
    {
      PORT_ACCESS_FROM_PORT (traversal->portLib);

      if (traversal->zipCache)
        {
          zipCachePool_release (traversal->zipCache->cachePool,
                                traversal->zipCache);
        }
      hymem_free_memory (traversal);
    }
}

/* Searches the dirList in dirEntry for a directory entry named */
/* namePtr[0..nameSize-1] with the specified isClass value. */

HaZipDirEntry *
zipCache_searchDirListCaseInsensitive (HaZipDirEntry * dirEntry,
                                       const char *namePtr, UDATA nameSize,
                                       BOOLEAN isClass)
{
  HaZipDirEntry *entry;

  if (!dirEntry || !namePtr)
    return NULL;

  entry = dirEntry->dirList;
  while (entry)
    {
      if (!helper_memicmp (entry->name, namePtr, nameSize)
          && !entry->name[nameSize])
        {
          if (isClass && (entry->zipFileOffset & ISCLASS_BIT))
            return entry;
          if (!isClass && !(entry->zipFileOffset & ISCLASS_BIT))
            return entry;
        }
      entry = entry->next;
    }
  return NULL;
}

/* Returns zero if the two strings are equal over the first length characters.  Otherwise,
	returns 1 or -1 ala stricmp. */

IDATA
helper_memicmp (const void *src1, const void *src2, UDATA length)
{
  char const *s1 = (char const *) src1;
  char const *s2 = (char const *) src2;
  UDATA i;
  for (i = 0; i < length; i++)
    {
      if (toupper (s1[i]) > toupper (s2[i]))
        return 1;
      if (toupper (s1[i]) < toupper (s2[i]))
        return -1;
    }
  return 0;
}
