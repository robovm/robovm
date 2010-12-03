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
* Zip Support Header
*/

#if !defined(ZIPSUP_H)
#define ZIPSUP_H
#if defined(__cplusplus)
extern "C"
{
#endif
#include "hyport.h"
  typedef struct HyZipCachePool HyZipCachePool;
  
#if defined(HY_LOCAL_ZLIB)
#define HY_ZIP_DLL_NAME "z"
#else
#define HY_ZIP_DLL_NAME "hyzlib"
#endif

#define ZIP_INTERNAL_MAX  80
#define ZIP_CM_Reduced1  2
#define ZIP_Unknown  0
#define ZIP_GZIP  2
#define ZIP_ERR_OUT_OF_MEMORY  -3
#define ZIP_ERR_FILE_CORRUPT  -6
#define ZIP_ERR_INTERNAL_ERROR  -11
#define ZIP_CM_Imploded  6
#define ZIP_CM_Reduced4  5
#define ZIP_CM_Shrunk  1
#define ZIP_CM_Reduced2  3
#define ZIP_ERR_FILE_READ_ERROR  -1
#define ZIP_CentralHeader  0x2014B50
#define ZIP_ERR_FILE_CLOSE_ERROR  -10
#define ZIP_ERR_BUFFER_TOO_SMALL  -7
#define ZIP_CM_Reduced3  4
#define ZIP_CM_Deflated  8
#define ZIP_LocalHeader  0x4034B50
#define ZIP_CM_Tokenized  7
#define ZIP_PKZIP  1
#define ZIP_CM_Stored  0
#define ZIP_ERR_UNSUPPORTED_FILE_TYPE  -5
#define ZIP_ERR_NO_MORE_ENTRIES  -2
#define ZIP_CentralEnd  0x6054B50
#define ZIP_ERR_FILE_OPEN_ERROR  -9
#define ZIP_ERR_UNKNOWN_FILE_TYPE  -4
#define ZIP_ERR_ENTRY_NOT_FOUND  -8
#define ZIP_DataDescriptor  0x8074B50

  typedef struct HyZipCache
  {
    U_8 *zipFileName;
    IDATA zipFileSize;
    I_64 zipTimeStamp;
    IDATA startCentralDir;
    struct HyPortLibrary *portLib;
    void *cachePool;
    void *cachePoolEntry;
  } HyZipCache;


  typedef struct HyZipCentralEnd
  {
    U_16 diskNumber;
    U_16 dirStartDisk;
    U_16 thisDiskEntries;
    U_16 totalEntries;
    U_32 dirSize;
    U_32 dirOffset;
    U_16 commentLength;
    char _hypadding0012[2];   /* 2 bytes of automatic padding */
    U_8 *comment;
  } HyZipCentralEnd;


  typedef struct HyZipDataDescriptor
  {
    U_32 crc32;
    U_32 compressedSize;
    U_32 uncompressedSize;
  } HyZipDataDescriptor;


  typedef struct HyZipEntry
  {
    U_8 *data;
    U_8 *filename;
    U_8 *extraField;
    U_8 *fileComment;
    I_32 dataPointer;
    I_32 filenamePointer;
    I_32 extraFieldPointer;
    I_32 fileCommentPointer;
    U_32 compressedSize;
    U_32 uncompressedSize;
    U_32 crc32;
    U_16 filenameLength;
    U_16 extraFieldLength;
    U_16 fileCommentLength;
    U_16 internalAttributes;
    U_16 versionCreated;
    U_16 versionNeeded;
    U_16 flags;
    U_16 compressionMethod;
    U_16 lastModTime;
    U_16 lastModDate;
    U_8 internalFilename[80];
  } HyZipEntry;


  typedef struct HyZipFile
  {
    U_8 *filename;
    struct HyZipCache *cache;
    void *cachePool;
    I_32 fd;
    I_32 pointer;
    U_8 internalFilename[80];
    U_8 type;
    char _hypadding0065[3];  /* 3 bytes of automatic padding */
  } HyZipFile;

/* HySourceZipSupport*/
  extern HY_CFUNC I_32 zip_getZipEntryData
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
  extern HY_CFUNC I_32 zip_getZipEntryFromOffset
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, IDATA offset));
  extern HY_CFUNC I_32 zip_establishCache
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile));
  extern HY_CFUNC void zip_resetZipFile
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                IDATA * nextEntryPointer));
  extern HY_CFUNC I_32 zip_getNextZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * zipEntry, IDATA * nextEntryPointer));
  extern HY_CFUNC I_32 zip_getZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, const char *filename,
                BOOLEAN findDirectory));
  extern HY_CFUNC I_32 zip_getZipEntryExtraField
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
  extern HY_CFUNC void zip_initZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
  extern HY_CFUNC I_32 zip_openZipFile
    PROTOTYPE ((HyPortLibrary * portLib, char *filename, HyZipFile * zipFile,
                HyZipCachePool * cachePool));
  extern HY_CFUNC void zip_freeZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
  struct HyZipFile;
  extern HY_CFUNC I_32 VMCALL zip_closeZipFile
    PROTOTYPE ((HyPortLibrary * portLib, struct HyZipFile * zipFile));
  extern HY_CFUNC I_32 zip_getZipEntryComment
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
/* HySourceZipCache*/
  extern HY_CFUNC UDATA zipCache_findElement
    PROTOTYPE ((HyZipCache * zipCache, const char *elementName,
                BOOLEAN searchDirList));
  extern HY_CFUNC void zipCache_kill PROTOTYPE ((HyZipCache * zipCache));
  extern HY_CFUNC IDATA zipCache_enumGetDirName
    PROTOTYPE ((void *handle, char *nameBuf, UDATA nameBufSize));
  extern HY_CFUNC HyZipCache *zipCache_new
    PROTOTYPE ((HyPortLibrary * portLib, char *zipName, IDATA zipNameLength));
  extern HY_CFUNC IDATA zipCache_enumNew
    PROTOTYPE ((HyZipCache * zipCache, char *directoryName, void **handle));
  extern HY_CFUNC IDATA zipCache_enumElement
    PROTOTYPE ((void *handle, char *nameBuf, UDATA nameBufSize,
                UDATA * offset));
  extern HY_CFUNC void zipCache_enumKill PROTOTYPE ((void *handle));
  extern HY_CFUNC BOOLEAN zipCache_addElement
    PROTOTYPE ((HyZipCache * zipCache, char *elementName,
                UDATA elementOffset));
/* HySourceZipCachePool*/
  extern HY_CFUNC BOOLEAN zipCachePool_release
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
  extern HY_CFUNC void zipCachePool_kill PROTOTYPE ((HyZipCachePool * zcp));
  extern HY_CFUNC HyZipCache *zipCachePool_findCache
    PROTOTYPE ((HyZipCachePool * zcp, char const *zipFileName,
                IDATA zipFileNameLength, IDATA zipFileSize,
                I_64 zipTimeStamp));
  extern HY_CFUNC HyZipCachePool *zipCachePool_new
    PROTOTYPE ((HyPortLibrary * portLib));
  extern HY_CFUNC BOOLEAN zipCachePool_addCache
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
  extern HY_CFUNC BOOLEAN zipCachePool_addRef
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
#if defined(__cplusplus)
}
#endif
#endif /* ZIPSUP_H */
