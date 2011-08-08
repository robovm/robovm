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

#include <string.h>

#include "hyport.h"
#include "zipsup.h"
#include "hyzipnls.h"

#include "zlib.h"

/* Globals for the zip library */
UDATA zipDLLDescriptor = 0;
int (*inflateInit2Func) (void *, int, const char *, int);
int (*inflateFunc) (void *, int);
int (*inflateEndFunc) (void *);

#define ZIP_NEXT_U8(value, index) (value = *(index++))
#define ZIP_NEXT_U16(value, index) ((value = (index[1] << 8) | index[0]), index += 2, value)
#define ZIP_NEXT_U32(value, index) ((value = ((U_32)index[3] << 24) | ((U_32)index[2] << 16) | ((U_32)index[1] << 8) | (U_32)index[0]), index += 4, value)

#define WORK_BUFFER_SIZE 64000

#define SCAN_CHUNK_SIZE 1024

struct workBuffer
{
  HyPortLibrary *portLib;
  UDATA *bufferStart;
  UDATA *bufferEnd;
  UDATA *currentAlloc;
  UDATA cntr;
};

#define CDEV_CURRENT_FUNCTION _prototypes_private
I_32 zip_populateCache
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile));

static I_32 inflateData
PROTOTYPE ((struct workBuffer * workBuf, U_8 * inputBuffer,
            U_32 inputBufferSize, U_8 * outputBuffer, U_32 outputBufferSize));

I_32 checkZipLibrary PROTOTYPE ((HyPortLibrary * portLib));

I_32 scanForDataDescriptor
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
            HyZipEntry * zipEntry));
void zdatafree PROTOTYPE ((void *opaque, void *address));
static I_32 readZipEntry
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
            HyZipEntry * zipEntry, const char *filename,
            IDATA * enumerationPointer, IDATA * entryStart,
            BOOLEAN findDirectory));
I_32 scanForCentralEnd
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
            HyZipCentralEnd * endEntry));
void *zdataalloc PROTOTYPE ((void *opaque, U_32 items, U_32 size));

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION _prototypes_public
I_32 zip_getZipEntryData
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile, HyZipEntry * entry,
            U_8 * buffer, U_32 bufferSize));
I_32 zip_getZipEntryFromOffset
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile, HyZipEntry * entry,
            IDATA offset));
I_32 zip_establishCache
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile));
void zip_resetZipFile
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
            IDATA * nextEntryPointer));
I_32 zip_getNextZipEntry
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
            HyZipEntry * zipEntry, IDATA * nextEntryPointer));
I_32 zip_getZipEntry
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile, HyZipEntry * entry,
            const char *filename, BOOLEAN findDirectory));
I_32 zip_getZipEntryExtraField
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile, HyZipEntry * entry,
            U_8 * buffer, U_32 bufferSize));
void zip_initZipEntry
PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
I_32 zip_openZipFile
PROTOTYPE ((HyPortLibrary * portLib, char *filename, HyZipFile * zipFile,
            HyZipCachePool * cachePool));
void zip_freeZipEntry
PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
I_32 VMCALL zip_closeZipFile
PROTOTYPE ((HyPortLibrary * portLib, struct HyZipFile * zipFile));
I_32 zip_getZipEntryComment
PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile, HyZipEntry * entry,
            U_8 * buffer, U_32 bufferSize));

#undef CDEV_CURRENT_FUNCTION

#include "hythread.h"
#define ENTER() hythread_monitor_enter(hythread_global_monitor())
#define EXIT() hythread_monitor_exit(hythread_global_monitor())

#define CDEV_CURRENT_FUNCTION checkZipLibrary

/*
	Ensure that the zip library is loaded.
	Return 0 on success, -1 on failure.
*/
I_32
checkZipLibrary (HyPortLibrary * portLib)
{
  PORT_ACCESS_FROM_PORT (portLib);

  /* if the library has already been loaded return success/failure */
  if (zipDLLDescriptor > 1)
    return 0;
  if (zipDLLDescriptor == 1)
    return -1;

  /* open up the zip library by name */

  if (hysl_open_shared_library (HY_ZIP_DLL_NAME, &zipDLLDescriptor, TRUE))
    goto openFailed;

  /* look up the functions */
  if (hysl_lookup_name
      (zipDLLDescriptor, "inflateInit2_", (void *) &inflateInit2Func,
       "ILILI"))
    goto loadFailed;
  if (hysl_lookup_name
      (zipDLLDescriptor, "inflate", (void *) &inflateFunc, "IPI"))
    goto loadFailed;
  if (hysl_lookup_name
      (zipDLLDescriptor, "inflateEnd", (void *) &inflateEndFunc, "IP"))
    goto loadFailed;

  /* good to go */
  return 0;

loadFailed:
  hysl_close_shared_library (zipDLLDescriptor);

  /* mark the descriptor as a failed load. only report the error once */
  zipDLLDescriptor = 1;

  /* Unable to open %s (Missing export) */
  hynls_printf (PORTLIB, HYNLS_WARNING, HYNLS_ZIP_MISSING_EXPORT,
                HY_ZIP_DLL_NAME);

  return -1;

openFailed:
  /* mark the descriptor as a failed load. only report the error once */
  zipDLLDescriptor = 1;

  /* Unable to open %s (%s) */
  hynls_printf (PORTLIB, HYNLS_WARNING, HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL,
                HY_ZIP_DLL_NAME, hyerror_last_error_message ());
  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION inflateData

/*
	Returns 0 on success or one of the following:
			ZIP_ERR_UNSUPPORTED_FILE_TYPE
			ZIP_ERR_FILE_CORRUPT
			ZIP_ERR_OUT_OF_MEMORY
			ZIP_ERR_INTERNAL_ERROR
*/
static I_32
inflateData (struct workBuffer *workBuf, U_8 * inputBuffer,
             U_32 inputBufferSize, U_8 * outputBuffer, U_32 outputBufferSize)
{
  PORT_ACCESS_FROM_PORT (workBuf->portLib);

  z_stream stream;
  I_32 err;

  stream.next_in = inputBuffer;
  stream.avail_in = inputBufferSize;
  stream.next_out = outputBuffer;
  stream.avail_out = outputBufferSize;

  stream.opaque = workBuf;
  stream.zalloc = zdataalloc;
  stream.zfree = zdatafree;

  /* Initialize stream. Pass "-15" as max number of window bits, negated
     to indicate that no zlib header is present in the data. */
  err = inflateInit2Func (&stream, -15, ZLIB_VERSION, sizeof (z_stream));
  if (err != Z_OK)
    return -1;

  /* Inflate the data. */
  err = inflateFunc (&stream, Z_SYNC_FLUSH);

  /* Clean up the stream. */
  inflateEndFunc (&stream);

  /* Check the return code. Did we complete the inflate? */
  if ((err == Z_STREAM_END) || (err == Z_OK))
    {
      if (stream.total_out == outputBufferSize)
        {
          return 0;
        }
    }

  switch (err)
    {
    case Z_OK:                 /* an error if file is incomplete */
    case Z_STREAM_END:         /* an error if file is incomplete */
    case Z_ERRNO:              /* a random error */
    case Z_STREAM_ERROR:       /* stream inconsistent */
    case Z_DATA_ERROR:         /* corrupted zip */
      return ZIP_ERR_FILE_CORRUPT;

    case Z_VERSION_ERROR:      /* wrong zlib version */
    case Z_NEED_DICT:          /* needs a preset dictionary that we can't provide */
      return ZIP_ERR_UNSUPPORTED_FILE_TYPE;

    case Z_MEM_ERROR:          /* out of memory */
      return ZIP_ERR_OUT_OF_MEMORY;

    case Z_BUF_ERROR:          /* no progress / out of output buffer */
    default:                   /* jic */
      return ZIP_ERR_INTERNAL_ERROR;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION scanForCentralEnd
/*
	Scan backward from end of file for a central end header. Read from zipFile and update the HyZipCentralEnd provided.

	Returns 0 on success or one of the following:
			ZIP_ERR_FILE_READ_ERROR
			ZIP_ERR_FILE_CORRUPT
*/
I_32
scanForCentralEnd (HyPortLibrary * portLib, HyZipFile * zipFile,
                   HyZipCentralEnd * endEntry)
{
  U_8 *current;
  U_8 buffer[SCAN_CHUNK_SIZE];
  I_32 i, size, state;
  U_32 dataSize = 0;
  I_64 seekResult;
  I_32 fileSize;
  I_32 bytesAlreadyRead = 0;

  PORT_ACCESS_FROM_PORT (portLib);

  /* Haven't seen anything yet. */
  state = 0;

  seekResult = hyfile_seek (zipFile->fd, 0, HySeekEnd);
  if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
    {
      zipFile->pointer = -1;
      return ZIP_ERR_FILE_READ_ERROR;
    }
  fileSize = (I_32) seekResult;
  zipFile->pointer = fileSize;

  while (TRUE)
    {
      /* Fill the buffer. */
      if (bytesAlreadyRead == fileSize)
        {
          zipFile->pointer = -1;
          return ZIP_ERR_FILE_CORRUPT;
        }

      size = SCAN_CHUNK_SIZE;
      if (size > fileSize - bytesAlreadyRead)
        size = fileSize - bytesAlreadyRead;
      bytesAlreadyRead += size;
      seekResult =
        hyfile_seek (zipFile->fd, fileSize - bytesAlreadyRead, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer = (I_32) seekResult;

      if (hyfile_read (zipFile->fd, buffer, size) != size)
        {
          zipFile->pointer = -1;
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer += size;

      /* Scan the buffer (backwards) for CentralEnd signature = PK^E^F. */
      for (i = size; i--; dataSize++)
        {
          switch (state)
            {
            case 0:
              /* Nothing yet. */
              if (buffer[i] == 6)
                state = 1;
              break;

            case 1:
              /* Seen ^F */
              if (buffer[i] == 5)
                state = 2;
              else
                state = 0;
              break;

            case 2:
              /* Seen ^E^F */
              if (buffer[i] == 'K')
                state = 3;
              else
                state = 0;
              break;

            case 3:
              /* Seen K^E^F */
              if (buffer[i] == 'P' && dataSize >= 21)
                {
                  /* Found it.  Read the data from the end-of-central-dir record. */
                  current = buffer + i + 4;
                  (void) ZIP_NEXT_U16 (endEntry->diskNumber, current);
                  (void) ZIP_NEXT_U16 (endEntry->dirStartDisk, current);
                  (void) ZIP_NEXT_U16 (endEntry->thisDiskEntries, current);
                  (void) ZIP_NEXT_U16 (endEntry->totalEntries, current);
                  (void) ZIP_NEXT_U32 (endEntry->dirSize, current);
                  (void) ZIP_NEXT_U32 (endEntry->dirOffset, current);
                  (void) ZIP_NEXT_U16 (endEntry->commentLength, current);

                  /* Quick test to ensure that the header isn't invalid. 
                     Current dataSize is the number of bytes of data scanned, up to the ^H in the stream. */
                  if (dataSize >= (U_32) (21 + endEntry->commentLength))
                    return 0;

                  /* Header looked invalid. Pretend we didn't see it and keep scanning.. */
                }
              state = 0;
              break;
            }
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION scanForDataDescriptor
/*
	Scan ahead for a data descriptor. Read from zipFile and update the HyZipLocalHeader provided.

	Returns 0 on success or one of the following:
			ZIP_ERR_FILE_READ_ERROR
			ZIP_ERR_FILE_CORRUPT
*/
I_32
scanForDataDescriptor (HyPortLibrary * portLib, HyZipFile * zipFile,
                       HyZipEntry * zipEntry)
{
  U_8 *current;
  U_8 buffer[SCAN_CHUNK_SIZE], descriptor[16];
  I_32 i, size, state;
  U_32 dataSize, blockPointer;
  I_64 seekResult;

  PORT_ACCESS_FROM_PORT (portLib);

  /* Skip ahead and read the data descriptor. The compressed size should be 0. */
  if (zipFile->pointer !=
      (IDATA) (zipEntry->dataPointer + zipEntry->compressedSize))
    {
      seekResult =
        hyfile_seek (zipFile->fd,
                     zipEntry->dataPointer + zipEntry->compressedSize,
                     HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer = (I_32) seekResult;
    }

  /* Haven't seen anything yet. */
  blockPointer = dataSize = zipEntry->compressedSize;
  state = 0;

  /* Scan until we find PK^G^H (otherwise it's an error). */
  while (1)
    {
      /* Fill the buffer. */
      size = hyfile_read (zipFile->fd, buffer, SCAN_CHUNK_SIZE);
      if (size == 0)
        {
          return ZIP_ERR_FILE_CORRUPT;
        }
      else if (size < 0)
        {
          zipFile->pointer = -1;
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer += size;
      blockPointer += size;

      /* Scan the buffer. */
      for (i = 0; i < size; i++, dataSize++)
        {
          switch (state)
            {
            case 0:
              /* Nothing yet. */
              if (buffer[i] == 'P')
                {
                  state = 1;
                }
              break;

            case 1:
              /* Seen P */
              if (buffer[i] == 'K')
                {
                  state = 2;
                }
              else
                state = 0;
              break;

            case 2:
              /* Seen PK */
              if (buffer[i] == 7)
                {
                  state = 3;
                }
              else
                {
                  state = 0;
                }
              break;

            case 3:
              /* Seen PK^G */
              if (buffer[i] == 8)
                {
                  /* Found it! Read the descriptor */
                  if (i + 12 < size)
                    {
                      current = &buffer[i + 1];
                    }
                  else
                    {
                      seekResult =
                        hyfile_seek (zipFile->fd,
                                     zipEntry->dataPointer + dataSize + 1,
                                     HySeekSet);
                      if ((seekResult < 0)
                          || (seekResult > HYCONST64 (0x7FFFFFFF)))
                        {
                          zipFile->pointer = -1;
                          return ZIP_ERR_FILE_READ_ERROR;
                        }
                      zipFile->pointer = (I_32) seekResult;
                      if (hyfile_read (zipFile->fd, descriptor, 12) != 12)
                        {
                          zipFile->pointer = -1;
                          return ZIP_ERR_FILE_READ_ERROR;
                        }
                      zipFile->pointer += 12;
                      current = descriptor;
                    }

                  /* Read the data from the descriptor. */
                  (void) ZIP_NEXT_U32 (zipEntry->crc32, current);
                  (void) ZIP_NEXT_U32 (zipEntry->compressedSize, current);
                  (void) ZIP_NEXT_U32 (zipEntry->uncompressedSize, current);

                  /* Quick test to ensure that the header isn't invalid. 
                     Current dataSize is the number of bytes of data scanned, up to the ^H in the stream. */
                  if (dataSize - 3 == zipEntry->compressedSize)
                    {
                      return 0;
                    }

                  /* Header looked invalid. Reset the pointer and continue scanning. */
                  seekResult =
                    hyfile_seek (zipFile->fd,
                                 zipEntry->dataPointer + blockPointer,
                                 HySeekSet);
                  if ((seekResult < 0)
                      || (seekResult > HYCONST64 (0x7FFFFFFF)))
                    {
                      zipFile->pointer = -1;
                      return ZIP_ERR_FILE_READ_ERROR;
                    }
                  zipFile->pointer = (I_32) seekResult;
                }
              else
                state = 0;
              break;
            }
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_populateCache
/*
	Fill in the cache of a given zip file.  This should only be called once during zip_openZipFile!

	Returns 0 on success or one of the following:
			ZIP_ERR_FILE_READ_ERROR
			ZIP_ERR_FILE_OPEN_ERROR
			ZIP_ERR_UNKNOWN_FILE_TYPE
			ZIP_ERR_UNSUPPORTED_FILE_TYPE
			ZIP_ERR_OUT_OF_MEMORY
			ZIP_ERR_INTERNAL_ERROR
*/
I_32
zip_populateCache (HyPortLibrary * portLib, HyZipFile * zipFile)
{
  PORT_ACCESS_FROM_PORT (portLib);

  I_32 result = 0;
  IDATA bufferSize = 65536;
  IDATA unreadSize = 0;
  IDATA bufferedSize = 0;
  IDATA bytesToRead = 0;
  IDATA filenameCopied;
  HyZipEntry entry;
  HyZipCentralEnd endEntry;
  U_8 *buffer = NULL;
  U_8 *filename = NULL;
  IDATA filenameSize = 256;     /* Should be sufficient for most filenames */
  U_8 *current;
  U_32 sig;
  U_32 localHeaderOffset;
  IDATA startCentralDir;
  I_64 seekResult;

  if (!zipFile->cache)
    return ZIP_ERR_INTERNAL_ERROR;

  /* Find and read the end-of-central-dir record. */
  result = scanForCentralEnd (portLib, zipFile, &endEntry);
  if (result != 0)
    return result;

  unreadSize = endEntry.dirSize + 4 /* slop */ ;
  zipFile->cache->startCentralDir = startCentralDir =
    (IDATA) ((UDATA) endEntry.dirOffset);

  if (zipFile->pointer != startCentralDir)
    {
      seekResult = hyfile_seek (zipFile->fd, startCentralDir, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }

      zipFile->pointer = (I_32) seekResult;
      if (zipFile->pointer != startCentralDir)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          zipFile->pointer = -1;
          goto finished;
        }
    }

  /* No point in allocating more than we'll actually need.. */
  if (bufferSize > unreadSize)
    bufferSize = unreadSize;

  filename = hymem_allocate_memory (filenameSize);
  if (!filename)
    {
      result = ZIP_ERR_OUT_OF_MEMORY;
      goto finished;
    }

  /* Allocate some space to hold central directory goo as we eat through it */
  buffer = hymem_allocate_memory (bufferSize);
  if (!buffer && (bufferSize > 4096))
    {
      /* Not enough memory, fall back to a smaller buffer! */
      bufferSize = 4096;
      buffer = hymem_allocate_memory (bufferSize);
    }
  if (!buffer)
    {
      result = ZIP_ERR_OUT_OF_MEMORY;
      goto finished;
    }

  while (unreadSize)
    {

      /* Read as much as needed into buffer. */
      bytesToRead = bufferSize - bufferedSize;
      if (bytesToRead > unreadSize)
        bytesToRead = unreadSize;
      result = hyfile_read (zipFile->fd, buffer + bufferedSize, bytesToRead);
      if (result < 0)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          zipFile->pointer = -1;
          goto finished;
        }
      zipFile->pointer += result;
      unreadSize -= result;
      bufferedSize += result;
      current = buffer;

      /* consume entries until we run out. */
      while (current + 46 < buffer + bufferedSize)
        {
          IDATA entryPointer;

          entryPointer =
            zipFile->pointer + (current - (buffer + bufferedSize));

          sig = 0;
          (void) ZIP_NEXT_U32 (sig, current);
          if (sig == ZIP_CentralEnd)
            {
              /* We're done here. */
              result = 0;
              goto finished;
            }
          if (sig != ZIP_CentralHeader)
            {
              /* Umm...What the Hell? */
              result = ZIP_ERR_FILE_CORRUPT;
              goto finished;
            }

          /* Read ZIP_CentralHeader entry */
          (void) ZIP_NEXT_U16 (entry.versionCreated, current);
          (void) ZIP_NEXT_U16 (entry.versionNeeded, current);
          (void) ZIP_NEXT_U16 (entry.flags, current);
          (void) ZIP_NEXT_U16 (entry.compressionMethod, current);
          (void) ZIP_NEXT_U16 (entry.lastModTime, current);
          (void) ZIP_NEXT_U16 (entry.lastModDate, current);
          (void) ZIP_NEXT_U32 (entry.crc32, current);
          (void) ZIP_NEXT_U32 (entry.compressedSize, current);
          (void) ZIP_NEXT_U32 (entry.uncompressedSize, current);
          (void) ZIP_NEXT_U16 (entry.filenameLength, current);
          (void) ZIP_NEXT_U16 (entry.extraFieldLength, current);
          (void) ZIP_NEXT_U16 (entry.fileCommentLength, current);
          current += sizeof (U_16);     /* skip disk number field */
          (void) ZIP_NEXT_U16 (entry.internalAttributes, current);
          current += sizeof (U_32);     /* skip external attributes field */
          (void) ZIP_NEXT_U32 (localHeaderOffset, current);

          /* Increase filename buffer size if necessary. */
          if (filenameSize < entry.filenameLength + 1)
            {
              hymem_free_memory (filename);
              filenameSize = entry.filenameLength + 1;
              filename = hymem_allocate_memory (filenameSize);
              if (!filename)
                {
                  result = ZIP_ERR_OUT_OF_MEMORY;
                  goto finished;
                }
            }

          filenameCopied = 0;
          while (filenameCopied < entry.filenameLength)
            {
              IDATA size;
              /* Copy as much of the filename as we can see in the buffer (probably the whole thing). */

              size = entry.filenameLength - filenameCopied;
              if (size > bufferedSize - (current - buffer))
                {
                  size = bufferedSize - (current - buffer);
                }
              memcpy (filename + filenameCopied, current, size);
              filenameCopied += size;
              current += size;
              if (filenameCopied >= entry.filenameLength)
                break;          /* done */

              /* Otherwise, we ran out of source string.  Load another chunk.. */
              bufferedSize = 0;
              if (!unreadSize)
                {
                  /* Central header is supposedly done?  Bak */
                  result = ZIP_ERR_FILE_CORRUPT;
                  goto finished;
                }
              bytesToRead = bufferSize - bufferedSize;
              if (bytesToRead > unreadSize)
                bytesToRead = unreadSize;
              result =
                hyfile_read (zipFile->fd, buffer + bufferedSize, bytesToRead);
              if (result < 0)
                {
                  result = ZIP_ERR_FILE_READ_ERROR;
                  zipFile->pointer = -1;
                  goto finished;
                }
              zipFile->pointer += result;
              unreadSize -= result;
              bufferedSize += result;
              current = buffer;
            }
          filename[entry.filenameLength] = '\0';        /* null-terminate */

          if (((entry.compressionMethod == ZIP_CM_Deflated)
               && (entry.flags & 0x8)) || (entry.fileCommentLength != 0))
            {
              /* Either local header doesn't know the compressedSize, or this entry has a file
                 comment.  In either case, cache the central header instead of the local header
                 so we can find the information we need later. */

              result =
                zipCache_addElement (zipFile->cache, (char *) filename,
                                     entryPointer);

            }
          else
            {
              result =
                zipCache_addElement (zipFile->cache, (char *) filename,
                                     localHeaderOffset);
            }

          if (!result)
            {
              result = ZIP_ERR_OUT_OF_MEMORY;
              goto finished;
            }

          /* Skip the data and comment. */
          bytesToRead = entry.extraFieldLength + entry.fileCommentLength;
          if (bufferedSize - (current - buffer) >= bytesToRead)
            {
              current += bytesToRead;
            }
          else
            {
              /* The rest of the buffer is uninteresting.  Skip ahead to where the good stuff is */
              bytesToRead -= (bufferedSize - (current - buffer));
              current = buffer + bufferedSize;
              unreadSize -= bytesToRead;

              seekResult = hyfile_seek (zipFile->fd, bytesToRead, HySeekCur);
              if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
                {
                  zipFile->pointer = -1;
                  result = ZIP_ERR_FILE_READ_ERROR;
                  goto finished;
                }
              zipFile->pointer = (I_32) seekResult;
            }
        }
      bufferedSize -= (current - buffer);
      memmove (buffer, current, bufferedSize);
    }

  result = 0;

finished:
  if (filename)
    hymem_free_memory (filename);
  if (buffer)
    hymem_free_memory (buffer);
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION readZipEntry
/*
	Read the next zip entry for the zipFile into the zipEntry provided.  If filename is non-NULL, it is expected to match
	the filename read for the entry.  If (cachePointer != -1) the filename of the entry will be looked up in the cache (assuming
	there is one) to help detect use of an invalid cache.  If enumerationPointer is non-NULL, sequential access is assumed and
	either a local zip entry or a data descriptor will be accepted, but a central zip entry will cause ZIP_ERR_NO_MORE_ENTRIES
	to be returned.  If enumerationPointer is NULL, random access is assumed and either a local zip entry or a central zip
	entry will be accepted.

	Returns 0 on success or one of the following:
			ZIP_ERR_FILE_READ_ERROR
			ZIP_ERR_FILE_CORRUPT
			ZIP_ERR_OUT_OF_MEMORY
			ZIP_ERR_NO_MORE_ENTRIES
*/
static I_32
readZipEntry (HyPortLibrary * portLib, HyZipFile * zipFile,
              HyZipEntry * zipEntry, const char *filename,
              IDATA * enumerationPointer, IDATA * entryStart,
              BOOLEAN findDirectory)
{
  PORT_ACCESS_FROM_PORT (portLib);

  I_32 result;
  U_8 buffer[46 + 128];
  U_8 *current;
  U_32 sig;
  IDATA readLength;
  I_64 seekResult;
  U_8 *readBuffer;
  IDATA currentEntryPointer, localEntryPointer;
  IDATA headerSize;
  IDATA filenameLength = filename ? strlen (filename) : 0;

retry:
  if (entryStart)
    *entryStart = zipFile->pointer;
  readBuffer = NULL;
  /* Guess how many bytes we'll need to read.  If we guess correctly we will do fewer I/O operations */
  headerSize = 30;              /* local zip header size */
  if (zipFile->cache && (zipFile->pointer >= zipFile->cache->startCentralDir))
    {
      headerSize = 46;          /* central zip header size */
    }
  readLength = headerSize + (filename ? filenameLength : 128);
  if (findDirectory)
    {
      /* Extra byte for possible trailing '/' */
      readLength++;
    }

  /* Allocate some memory if necessary */
  if (readLength <= sizeof (buffer))
    {
      current = buffer;
    }
  else
    {
      current = readBuffer = hymem_allocate_memory (readLength);
      if (!readBuffer)
        return ZIP_ERR_OUT_OF_MEMORY;
    }

  currentEntryPointer = localEntryPointer = zipFile->pointer;

  result = hyfile_read (zipFile->fd, current, readLength);
  if ((result < 22)
      || (filename
          && !(result == readLength
               || (findDirectory && result == (readLength - 1)))))
    {
      /* We clearly didn't get enough bytes */
      result = ZIP_ERR_FILE_READ_ERROR;
      goto finished;
    }
  zipFile->pointer += result;
  readLength = result;          /* If it's not enough, we'll catch that later */
  (void) ZIP_NEXT_U32 (sig, current);

  if (enumerationPointer)
    {
      if ((sig == ZIP_CentralEnd))
        {
          result = ZIP_ERR_NO_MORE_ENTRIES;
          goto finished;
        }
    }
  if ((enumerationPointer || (!zipFile->cache))
      && (sig == ZIP_DataDescriptor))
    {
      /* We failed to predict a data descriptor here.  This should be an error (i.e. only happens in malformed zips?) 
         but, but we will silently skip over it */
      seekResult =
        hyfile_seek (zipFile->fd, currentEntryPointer + 16, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer = (I_32) seekResult;

      if (zipFile->pointer == currentEntryPointer + 16)
        {
          if (readBuffer)
            {
              hymem_free_memory (readBuffer);
            }
          goto retry;
        }
      result = ZIP_ERR_FILE_READ_ERROR;
      goto finished;
    }

  if ((sig != ZIP_CentralHeader) && (sig != ZIP_LocalHeader))
    {
      /* Unexpected. */
      result = ZIP_ERR_FILE_CORRUPT;
      goto finished;
    }
  headerSize = ((sig == ZIP_CentralHeader) ? 46 : 30);
  if (readLength < headerSize)
    {
      /* We didn't get the whole header (and none of the filename).. */
      /* NOTE: this could happen in normal use if the assumed filename length above is <16.  Since it's 128, we don't 
         handle the impossible case where we would have to read more header.  It could also happen if the caller
         supplied a filename of length <16 but that only happens when we have a cache (so we'll know the header size) 
       */
      result = ZIP_ERR_FILE_READ_ERROR;
    }
  readLength -= headerSize;

  if (sig == ZIP_CentralHeader)
    {
      current += 2;             /* skip versionCreated field */
    }
  (void) ZIP_NEXT_U16 (zipEntry->versionNeeded, current);
  (void) ZIP_NEXT_U16 (zipEntry->flags, current);
  (void) ZIP_NEXT_U16 (zipEntry->compressionMethod, current);
  (void) ZIP_NEXT_U16 (zipEntry->lastModTime, current);
  (void) ZIP_NEXT_U16 (zipEntry->lastModDate, current);
  (void) ZIP_NEXT_U32 (zipEntry->crc32, current);
  (void) ZIP_NEXT_U32 (zipEntry->compressedSize, current);
  (void) ZIP_NEXT_U32 (zipEntry->uncompressedSize, current);
  (void) ZIP_NEXT_U16 (zipEntry->filenameLength, current);
  (void) ZIP_NEXT_U16 (zipEntry->extraFieldLength, current);
  zipEntry->fileCommentLength = 0;

  if (sig == ZIP_CentralHeader)
    {
      (void) ZIP_NEXT_U16 (zipEntry->fileCommentLength, current);
      current += 8;             /* skip disk number start + internal attrs + external attrs */
      (void) ZIP_NEXT_U32 (localEntryPointer, current);
    }

  if (filename)
    {
      if (zipFile->cache)
        {
          if (!
              (readLength == zipEntry->filenameLength
               || (findDirectory
                   && (readLength - 1) == zipEntry->filenameLength)))
            {
              /* We knew exactly how much we were supposed to read, and this wasn't it */
              result = ZIP_ERR_FILE_CORRUPT;
              goto finished;
            }
        }
    }

  /* Allocate space for filename */
  if (zipEntry->filenameLength >= ZIP_INTERNAL_MAX)
    {
      zipEntry->filename =
        hymem_allocate_memory (zipEntry->filenameLength + 1);
      if (!zipEntry->filename)
        {
          result = ZIP_ERR_OUT_OF_MEMORY;
          goto finished;
        }
    }
  else
    {
      zipEntry->filename = zipEntry->internalFilename;
    }
  if (readLength > zipEntry->filenameLength)
    {
      readLength = zipEntry->filenameLength;
    }
  memcpy (zipEntry->filename, current, readLength);

  /* Read the rest of the filename if necessary.  Allocate space in HyZipEntry for it! */
  if (readLength < zipEntry->filenameLength)
    {
      result =
        hyfile_read (zipFile->fd, zipEntry->filename + readLength,
                     zipEntry->filenameLength - readLength);
      if (result != (zipEntry->filenameLength - readLength))
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer += result;
    }
  zipEntry->filename[zipEntry->filenameLength] = '\0';

  /* If we know what filename is supposed to be, compare it and make sure it matches */
  /* Note: CASE-SENSITIVE COMPARE because filenames in zips are case sensitive (even on platforms with
     case-insensitive file systems) */
  if (filename)
    {
      if (!
          ((findDirectory && zipEntry->filenameLength == (filenameLength + 1)
            && zipEntry->filename[filenameLength] == '/'
            && !strncmp ((char *) zipEntry->filename, (const char *) filename,
                         filenameLength))
           || !strcmp ((const char *) zipEntry->filename,
                       (const char *) filename)))
        {
          /* We seem to have read something totally invalid.. */
          result = ZIP_ERR_FILE_CORRUPT;
          goto finished;
        }
    }

  zipEntry->filenamePointer = currentEntryPointer + headerSize;
  zipEntry->extraFieldPointer =
    localEntryPointer + 30 + zipEntry->filenameLength;
  zipEntry->dataPointer =
    zipEntry->extraFieldPointer + zipEntry->extraFieldLength;
  zipEntry->extraField = NULL;
  zipEntry->fileCommentPointer = 0;
  zipEntry->fileComment = NULL;
  zipEntry->data = NULL;

  if (sig == ZIP_CentralHeader)
    {
      U_8 buf[2];
      U_8 *buf2 = buf;
      U_16 lost;
      /* Also, we know where the comment is */
      zipEntry->fileCommentPointer = currentEntryPointer + headerSize +
        zipEntry->filenameLength + zipEntry->extraFieldLength;
      if (hyfile_seek (zipFile->fd, localEntryPointer + 28, HySeekSet) ==
          localEntryPointer + 28)
        {
          if (hyfile_read (zipFile->fd, buf, 2) == 2)
            {
              (void) ZIP_NEXT_U16 (lost, buf2);
              zipEntry->dataPointer = zipEntry->extraFieldPointer + lost;
              zipFile->pointer = localEntryPointer + 30;
            }
        }
    }

  if ((sig == ZIP_LocalHeader)
      && (zipEntry->compressionMethod == ZIP_CM_Deflated)
      && (zipEntry->flags & 0x8))
    {
      /* What we just read doesn't tell us how big the compressed data is.  We have to do a heuristic search for a
         valid data descriptor at the end of the compressed text */
      result = scanForDataDescriptor (portLib, zipFile, zipEntry);
      if (result < 0)
        goto finished;
    }

  /* Entry read successfully */

  if (enumerationPointer)
    {
      /* Work out where the next entry is supposed to be */
      *enumerationPointer =
        zipEntry->fileCommentPointer + zipEntry->fileCommentLength;
    }

  if (readBuffer)
    hymem_free_memory (readBuffer);
  return 0;

finished:
  if (readBuffer)
    {
      hymem_free_memory (readBuffer);
    }
  if ((zipEntry->filename)
      && (zipEntry->filename != zipEntry->internalFilename))
    {
      hymem_free_memory (zipEntry->filename);
    }
  zipEntry->filename = NULL;
  if (result == ZIP_ERR_FILE_READ_ERROR)
    {
      zipFile->pointer = -1;
    }
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_closeZipFile
/**
 * Attempt to close the zipfile. 
 *
 * @param[in] portLib the port library
 * @param[in] zipFile The zip file to be closed
 *
 * @return 0 on success
 * @return 	ZIP_ERR_FILE_CLOSE_ERROR if there is an error closing the file
 * @return 	ZIP_ERR_INTERNAL_ERROR if there is an internal error
 *
*/
I_32 VMCALL
zip_closeZipFile (HyPortLibrary * portLib, struct HyZipFile * zipFile)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */
  IDATA fd;

  ENTER ();

  fd = zipFile->fd;
  zipFile->fd = -1;

  if (zipFile->cache && zipFile->cachePool)
    {
      zipCachePool_release (zipFile->cachePool, zipFile->cache);
      zipFile->cache = NULL;
    }
  if ((zipFile->filename) && (zipFile->filename != zipFile->internalFilename))
    {
      hymem_free_memory (zipFile->filename);
    }
  zipFile->filename = NULL;

  if (fd == -1)
    {
      EXIT ();
      return ZIP_ERR_INTERNAL_ERROR;
    }
  if (hyfile_close (fd))
    {
      EXIT ();
      return ZIP_ERR_FILE_CLOSE_ERROR;
    }
  EXIT ();
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_establishCache
/** 
 * Called to set up a cache when a zip file is opened with a cachePool but without a cache, or when the
 *	current cache is found to be invalid in some way.
 * 
 * @param[in] portLib the port library
 * @param[in] zipFile the zip file for which we want to establish a cache
 * 
 * The current cache is marked as invalid such that new instances of zip files 
 * won't try to use it and an attempt is made to establish a new cache.
 *
 * @return 0 on success
 * @return 	ZIP_ERR_FILE_READ_ERROR if there is an error reading from zipFile 
 * @return	ZIP_ERR_FILE_OPEN_ERROR if is there is an error opening the file 
 * @return	ZIP_ERR_UNKNOWN_FILE_TYPE if the file type is unknown
 * @return	ZIP_ERR_UNSUPPORTED_FILE_TYPE if the file type is unsupported
 * @return	ZIP_ERR_OUT_OF_MEMORY  if there is not enough memory to complete this call
 * @return	ZIP_ERR_INTERNAL_ERROR if there was an internal error
*/

I_32
zip_establishCache (HyPortLibrary * portLib, HyZipFile * zipFile)
{
  PORT_ACCESS_FROM_PORT (portLib);
  I_32 result;
  I_64 timeStamp, actualFileSize;
  IDATA fileSize, filenameLength;

  if (zipFile->cache)
    {
      if (zipFile->cachePool)
        {
          /* Whack cache timestamp to keep other people from starting to use it (we will create a new one for them
             to start to use instead).  Once all the current users of the cache have stopped using it, it will go away */
          zipFile->cache->zipTimeStamp = -2;
          zipCachePool_release (zipFile->cachePool, zipFile->cache);
        }
      zipFile->cache = NULL;
    }
  if (!zipFile->cachePool)
    {
      return ZIP_ERR_INTERNAL_ERROR;
    }

  /* Check the cachePool for a suitable cache. */
  filenameLength = strlen ((const char *) zipFile->filename);
  timeStamp = hyfile_lastmod ((const char *) zipFile->filename);
  actualFileSize = hyfile_length ((const char *) zipFile->filename);
  if ((actualFileSize < 0) || (actualFileSize > HYCONST64 (0x7FFFFFFF)))
    {
      return ZIP_ERR_INTERNAL_ERROR;
    }
  fileSize = (IDATA) actualFileSize;

  zipFile->cache =
    zipCachePool_findCache (zipFile->cachePool,
                            (const char *) zipFile->filename, filenameLength,
                            fileSize, timeStamp);
  if (!zipFile->cache)
    {
      /* Build a new cache.  Because caller asked for a cache, fail if we can't provide one */
      zipFile->cache =
        zipCache_new (portLib, (char *) zipFile->filename, filenameLength);
      if (!zipFile->cache)
        return ZIP_ERR_OUT_OF_MEMORY;

      zipFile->cache->zipFileSize = fileSize;
      zipFile->cache->zipTimeStamp = timeStamp;

      result = zip_populateCache (portLib, zipFile);
      if (result != 0)
        {
          zipCache_kill (zipFile->cache);
          zipFile->cache = NULL;
          return result;
        }
      if (!zipCachePool_addCache (zipFile->cachePool, zipFile->cache))
        {
          zipCache_kill (zipFile->cache);
          zipFile->cache = NULL;
          return ZIP_ERR_OUT_OF_MEMORY;
        }
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_initZipEntry
/**
 * Initialize a zip entry.
 * 
 * Should be called before the entry is passed to any other zip support functions 
 *
 * @param[in] portLib the port library
 * @param[in] entry the zip entry to init
 *
 * @return none
*/

void
zip_initZipEntry (HyPortLibrary * portLib, HyZipEntry * entry)
{
  memset (entry, 0, sizeof (*entry));
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_freeZipEntry
/**
 * Free any memory associated with a zip entry.
 * 
 * @param[in] portLib the port library
 * @param[in] entry the zip entry we are freeing 
 * 
 * @return none 
 * 
 * @note This does not free the entry itself.
*/

void
zip_freeZipEntry (HyPortLibrary * portLib, HyZipEntry * entry)
{
  PORT_ACCESS_FROM_PORT (portLib);

  if ((entry->filename) && (entry->filename != entry->internalFilename))
    {
      hymem_free_memory (entry->filename);
    }
  entry->filename = NULL;
  if (entry->extraField)
    {
      hymem_free_memory (entry->extraField);
      entry->extraField = NULL;
    }
  if (entry->data)
    {
      hymem_free_memory (entry->data);
      entry->data = NULL;
    }
  if (entry->fileComment)
    {
      hymem_free_memory (entry->fileComment);
      entry->fileComment = NULL;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getNextZipEntry
/**
 *	Read the next zip entry at nextEntryPointer into zipEntry.
 *	
 * Any memory held onto by zipEntry may be lost, and therefore
 *	MUST be freed with @ref zip_freeZipEntry first.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile The zip file being read
 * @param[out] zipEntry compressed data is placed here
 * @param[in] nextEntryPointer
 * 
 * @return 0 on success
 * @return 	ZIP_ERR_FILE_READ_ERROR if there is an error reading zipFile
 * @return ZIP_ERR_FILE_CORRUPT if zipFile is corrupt
 * @return 	ZIP_ERR_NO_MORE_ENTRIES if there are no more entries in zipFile
 * @return 	ZIP_ERR_OUT_OF_MEMORY if there is not enough memory to complete this call
 *
 * @see zip_freeZipEntry
 *
*/
I_32
zip_getNextZipEntry (HyPortLibrary * portLib, HyZipFile * zipFile,
                     HyZipEntry * zipEntry, IDATA * nextEntryPointer)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */
  IDATA result;
  BOOLEAN retryAllowed = TRUE;
  IDATA pointer;
  IDATA entryStart;
  I_64 seekResult;

  ENTER ();

retry:
  pointer = *nextEntryPointer;

  /* Seek to the entry's position in the file. */
  if (pointer != zipFile->pointer)
    {
      seekResult = hyfile_seek (zipFile->fd, pointer, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          EXIT ();
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer = (I_32) seekResult;

      if (pointer != zipFile->pointer)
        {
          zipFile->pointer = -1;
          EXIT ();
          return ZIP_ERR_FILE_READ_ERROR;
        }
    }

  /* Read the entry */
  entryStart = *nextEntryPointer;
  result =
    readZipEntry (portLib, zipFile, zipEntry, NULL, &pointer, &entryStart,
                  FALSE);
  if (result != 0)
    {
      if (!retryAllowed || (result == ZIP_ERR_NO_MORE_ENTRIES))
        {
          EXIT ();
          return result;
        }
      zip_establishCache (portLib, zipFile);
      retryAllowed = FALSE;
      goto retry;
    }

  if (zipFile->cache)
    {
      /* Validity check: look up filename in the cache... */
      result =
        (IDATA) zipCache_findElement (zipFile->cache,
                                      (const char *) zipEntry->filename,
                                      FALSE);
      if (result != entryStart)
        {
          if (result >= zipFile->cache->startCentralDir)
            {
              /* ! Cache contents are not valid.  Invalidate it and make a new one */
              if (!retryAllowed)
                {
                  EXIT ();
                  return ZIP_ERR_FILE_CORRUPT;  /* should never happen! */
                }
              result = zip_establishCache (portLib, zipFile);
              if (!result)
                {
                  /* (silently start operating without a cache if we couldn't make a new one) */
                }
              else
                {
                  retryAllowed = FALSE;
                  goto retry;
                }
            }
          else
            {
              /* We know that the central header for this entry contains info that the
                 local header is missing (comment length and/or uncompressed size) */
              zipEntry->fileCommentPointer = -1;
            }
        }
    }

  *nextEntryPointer = pointer;
  EXIT ();
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntry
/**
 *	Attempt to find and read the zip entry corresponding to filename.
 *	If found, read the entry into the parameter entry. 
 *
 * If an uncached entry is found, the filename field will be filled in. This
 * memory will have to be freed with @ref zip_freeZipEntry.
 * 
 * @param[in] portLib the port library
 * @param[in] zipFile the file being read from
 * @param[out] entry the zip entry found in zipFile is read to here
 * @param[in] filename the name of the file that corresponds to entry
 * @param[in] findDirectory when true, match a directory even if filename does not end in '/'.
 *			Note findDirectory is only supported (for the JCL) when there is a cache
 *
 * @return 0 on success or one of the following:
 * @return	ZIP_ERR_FILE_READ_ERROR if there is an error reading from zipFile
 * @return	ZIP_ERR_FILE_CORRUPT if zipFile is corrupt
 * @return	ZIP_ERR_ENTRY_NOT_FOUND if a zip entry with name filename was not found
 * @return 	ZIP_ERR_OUT_OF_MEMORY if there is not enough memory to complete this call
 *
 * @see zip_freeZipEntry
*/

I_32
zip_getZipEntry (HyPortLibrary * portLib, HyZipFile * zipFile,
                 HyZipEntry * entry, const char *filename,
                 BOOLEAN findDirectory)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */
  IDATA result, position;
  BOOLEAN retryAllowed = TRUE;
  I_64 seekResult;

  ENTER ();

retry:
  if (zipFile->cache)
    {
      /* Look up filename in the cache. */
      position =
        (IDATA) zipCache_findElement (zipFile->cache, filename,
                                      findDirectory);
      if (position == -1)
        {
          /* Note: we assume the cache is still valid here */
          EXIT ();
          return ZIP_ERR_ENTRY_NOT_FOUND;
        }

      /* Seek to the entry's position in the file. */
      if (zipFile->pointer != position)
        {
          seekResult = hyfile_seek (zipFile->fd, position, HySeekSet);
          if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
            {
              zipFile->pointer = -1;
              EXIT ();
              return ZIP_ERR_FILE_READ_ERROR;
            }
          zipFile->pointer = (I_32) seekResult;

          if (zipFile->pointer != position)
            {
              zipFile->pointer = -1;
              EXIT ();
              return ZIP_ERR_FILE_READ_ERROR;
            }
        }

      /* Read the entry */
      result =
        readZipEntry (portLib, zipFile, entry, filename, NULL, NULL,
                      findDirectory);
      if (result != 0)
        {
          if (!retryAllowed)
            {
              EXIT ();
              return result;
            }
          result = zip_establishCache (portLib, zipFile);       /* invalidate existing cache */
          if (result)
            {
              EXIT ();
              return result;
            }
          retryAllowed = FALSE;
          goto retry;
        }
      EXIT ();
      return 0;
    }
  else
    {
      /* Uh oh -- random access without a cache (SLOW!) */
      position = 0;
      zip_resetZipFile (PORTLIB, zipFile, &position);
      while (TRUE)
        {

          if (zipFile->pointer != position)
            {
              seekResult = hyfile_seek (zipFile->fd, position, HySeekSet);
              if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
                {
                  zipFile->pointer = -1;
                  EXIT ();
                  return ZIP_ERR_FILE_READ_ERROR;
                }
              zipFile->pointer = (I_32) seekResult;

              if (zipFile->pointer != position)
                {
                  zipFile->pointer = -1;
                  EXIT ();
                  return ZIP_ERR_FILE_READ_ERROR;
                }
            }

          result =
            readZipEntry (portLib, zipFile, entry, NULL, &position, NULL,
                          FALSE);
          if (result || !strcmp ((const char *) entry->filename, filename))
            {
              EXIT ();
              return result;
            }

          /* No match.  Reset for next entry */
          zip_freeZipEntry (portLib, entry);
          zip_initZipEntry (portLib, entry);
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntryData
/** 
 *	Attempt to read and uncompress the data for the zip entry entry.
 * 
 *	If buffer is non-NULL it is used, but not explicitly held onto by the entry.
 *	If buffer is NULL, memory is allocated and held onto by the entry, and thus
 *	should later be freed with @ref zip_freeZipEntry.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile the zip file being read from.
 * @param[in,out] entry the zip entry
 * @param[in] buffer may or may not be NULL
 * @param[in] bufferSize

 * @return 0 on success
 * @return	ZIP_ERR_FILE_READ_ERROR if there is an error reading from zipEntry
 * @return	ZIP_ERR_FILE_CORRUPT if zipFile is corrupt
 * @return	ZIP_ERR_ENTRY_NOT_FOUND if entry is not found
 * @return 	ZIP_ERR_OUT_OF_MEMORY  if there is not enough memory to complete this call
 * @return 	ZIP_ERR_BUFFER_TOO_SMALL if buffer is too small to hold the comment for zipFile
 *
 * @see zip_freeZipEntry
 *
*/
I_32
zip_getZipEntryData (HyPortLibrary * portLib, HyZipFile * zipFile,
                     HyZipEntry * entry, U_8 * buffer, U_32 bufferSize)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */

  I_32 result;
  U_8 *dataBuffer;
  struct workBuffer wb;
  I_64 seekResult;

  ENTER ();

  wb.portLib = portLib;
  wb.bufferStart = wb.bufferEnd = wb.currentAlloc = 0;

  if (buffer)
    {
      if (bufferSize < entry->uncompressedSize)
        {
          EXIT ();
          return ZIP_ERR_BUFFER_TOO_SMALL;
        }
      dataBuffer = buffer;
    }
  else
    {
      /* Note that this is the first zalloc. This memory must be available to the calling method and is freed explicitly in zip_freeZipEntry. */
      /* Note that other allocs freed in zip_freeZipEntry are not alloc'd using zalloc */
      dataBuffer = zdataalloc (&wb, 1, entry->uncompressedSize);
      if (!dataBuffer)
        {
          EXIT ();
          return ZIP_ERR_OUT_OF_MEMORY;
        }
      entry->data = dataBuffer;
    }

  if (entry->compressionMethod == ZIP_CM_Stored)
    {
      /* No compression - just read the data in. */
      if (zipFile->pointer != entry->dataPointer)
        {
          seekResult =
            hyfile_seek (zipFile->fd, entry->dataPointer, HySeekSet);
          if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
            {
              zipFile->pointer = -1;
              result = ZIP_ERR_FILE_READ_ERROR;
              goto finished;
            }
          zipFile->pointer = (I_32) seekResult;

          if (zipFile->pointer != entry->dataPointer)
            {
              result = ZIP_ERR_FILE_READ_ERROR;
              goto finished;
            }
        }
      result = hyfile_read (zipFile->fd, dataBuffer, entry->compressedSize);
      if (result != (I_32) entry->compressedSize)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer += result;
      EXIT ();
      return 0;
    }

  if (entry->compressionMethod == ZIP_CM_Deflated)
    {
      U_8 *readBuffer;

      /* Ensure that the library is loaded. */
      if (checkZipLibrary (portLib))
        {
          result = ZIP_ERR_UNSUPPORTED_FILE_TYPE;
          goto finished;
        }

      /* Read the file contents. */
      readBuffer = zdataalloc (&wb, 1, entry->compressedSize);
      if (!readBuffer)
        {
          result = ZIP_ERR_OUT_OF_MEMORY;
          goto finished;
        }
      if (zipFile->pointer != entry->dataPointer)
        {
          seekResult =
            hyfile_seek (zipFile->fd, entry->dataPointer, HySeekSet);
          if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
            {
              zipFile->pointer = -1;
              zdatafree (&wb, readBuffer);
              result = ZIP_ERR_FILE_READ_ERROR;
              goto finished;
            }
          zipFile->pointer = (I_32) seekResult;

          if (zipFile->pointer != entry->dataPointer)
            {
              zdatafree (&wb, readBuffer);
              result = ZIP_ERR_FILE_READ_ERROR;
              goto finished;
            }
        }
      if (hyfile_read (zipFile->fd, readBuffer, entry->compressedSize) !=
          (I_32) entry->compressedSize)
        {
          zdatafree (&wb, readBuffer);
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer += (I_32) entry->compressedSize;

      /* Deflate the data. */
      result =
        inflateData (&wb, readBuffer, entry->compressedSize, dataBuffer,
                     entry->uncompressedSize);
      zdatafree (&wb, readBuffer);
      if (result)
        goto finished;
      EXIT ();
      return 0;
    }

  /* Whatever this is, we can't decompress it */
  result = ZIP_ERR_UNSUPPORTED_FILE_TYPE;

finished:
  if (!buffer)
    {
      entry->data = NULL;
      zdatafree (&wb, dataBuffer);
    }
  if (result == ZIP_ERR_FILE_READ_ERROR)
    {
      zipFile->pointer = -1;
    }
  EXIT ();
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntryExtraField
/** 
 *	Read the extra field of entry from the zip file filename. 
 *
 * buffer is used if non-NULL, but is not held onto by entry. 
 *
 * If buffer is NULL, memory is allocated and held onto by entry, and MUST be freed later with
 *	@ref zip_freeZipEntry.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile the zip file being read from.
 * @param[in,out] entry the zip entry concerned
 * @param[in] buffer may or may not be NULL
 * @param[in] bufferSize
 *
 * @return 0 on success or one of the following:
 * @return ZIP_ERR_FILE_READ_ERROR if there is an error reading from zipFile
 * @return 	ZIP_ERR_FILE_CORRUPT if zipFile is corrupt
 * @return 	ZIP_ERR_OUT_OF_MEMORY if there is not enough memory to complete this call
 * @return 	ZIP_ERR_BUFFER_TOO_SMALL if the buffer was non-Null but not large enough to hold the contents of entry
 *
 * @see zip_freeZipEntry
*/
I_32
zip_getZipEntryExtraField (HyPortLibrary * portLib, HyZipFile * zipFile,
                           HyZipEntry * entry, U_8 * buffer, U_32 bufferSize)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */

  I_32 result;
  U_8 *extraFieldBuffer;
  I_64 seekResult;

  ENTER ();

  if (entry->extraFieldLength == 0)
    {
      EXIT ();
      return 0;
    }

  if (buffer)
    {
      if (bufferSize < entry->extraFieldLength)
        {
          EXIT ();
          return ZIP_ERR_BUFFER_TOO_SMALL;
        }
      extraFieldBuffer = buffer;
    }
  else
    {
      extraFieldBuffer = hymem_allocate_memory (entry->extraFieldLength);
      if (!extraFieldBuffer)
        {
          EXIT ();
          return ZIP_ERR_OUT_OF_MEMORY;
        }
      entry->extraField = extraFieldBuffer;
    }

  if (zipFile->pointer != entry->extraFieldPointer)
    {
      seekResult =
        hyfile_seek (zipFile->fd, entry->extraFieldPointer, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer = (I_32) seekResult;
      if (zipFile->pointer != entry->extraFieldPointer)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
    }
  result =
    hyfile_read (zipFile->fd, extraFieldBuffer, entry->extraFieldLength);
  if (result != (I_32) entry->extraFieldLength)
    {
      result = ZIP_ERR_FILE_READ_ERROR;
      goto finished;
    }
  zipFile->pointer += result;
  EXIT ();
  return 0;

finished:
  if (!buffer)
    {
      entry->extraField = NULL;
      hymem_free_memory (extraFieldBuffer);
    }
  if (result == ZIP_ERR_FILE_READ_ERROR)
    zipFile->pointer = -1;
  EXIT ();
  return result;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntryFilename

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntryComment
/**
 *	Read the file comment for entry. 
 *
 * If buffer is non-NULL, it is used, but not held onto by entry. 
 *
 * If buffer is NULL, memory is allocated and
 *	held onto by entry, and thus should later be freed with @ref zip_freeZipEntry.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile the zip file concerned
 * @param[in] entry the entry who's comment we want
 * @param[in] buffer may or may not be NULL
 * @param[in] bufferSize

 * @return 0 on success or one of the following
 * @return	ZIP_ERR_FILE_READ_ERROR if there is an error reading the file comment from zipEntry
 * @return	ZIP_ERR_FILE_CORRUPT if zipFile is corrupt
 * @return	ZIP_ERR_ENTRY_NOT_FOUND if entry is not found
 * @return 	ZIP_ERR_OUT_OF_MEMORY  if there is not enough memory to complete this call
 * @return 	ZIP_ERR_BUFFER_TOO_SMALL if buffer is too small to hold the comment for zipFile
*/

I_32
zip_getZipEntryComment (HyPortLibrary * portLib, HyZipFile * zipFile,
                        HyZipEntry * entry, U_8 * buffer, U_32 bufferSize)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */

  I_32 result;
  U_8 *fileCommentBuffer;
  I_64 seekResult;

  ENTER ();

  if (entry->fileCommentLength == 0)
    {
      if (entry->fileCommentPointer == -1)
        {
          /* TODO: we don't know where the comment is (or even if there is one)! This only happens if you're running
             without a cache, so too bad for now */
        }
      EXIT ();
      return 0;
    }

  if (buffer)
    {
      if (bufferSize < entry->fileCommentLength)
        {
          EXIT ();
          return ZIP_ERR_BUFFER_TOO_SMALL;
        }
      fileCommentBuffer = buffer;
    }
  else
    {
      fileCommentBuffer = hymem_allocate_memory (entry->fileCommentLength);
      if (!fileCommentBuffer)
        {
          EXIT ();
          return ZIP_ERR_OUT_OF_MEMORY;
        }
      entry->fileComment = fileCommentBuffer;
    }

  if (zipFile->pointer != entry->fileCommentPointer)
    {
      seekResult =
        hyfile_seek (zipFile->fd, entry->fileCommentPointer, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
      zipFile->pointer = (I_32) seekResult;

      if (zipFile->pointer != entry->fileCommentPointer)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }
    }
  result =
    hyfile_read (zipFile->fd, fileCommentBuffer, entry->fileCommentLength);
  if (result != (I_32) entry->fileCommentLength)
    {
      result = ZIP_ERR_FILE_READ_ERROR;
      goto finished;
    }
  zipFile->pointer += result;
  EXIT ();
  return 0;

finished:
  if (!buffer)
    {
      entry->fileComment = NULL;
      hymem_free_memory (fileCommentBuffer);
    }
  if (result == ZIP_ERR_FILE_READ_ERROR)
    {
      zipFile->pointer = -1;
    }
  EXIT ();
  return result;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_openZipFile
/**
 * Attempt to open a zip file. 
 * 
 * If the cache pool is non-NULL, the cachePool will be used to find a suitable cache, and if none can be found it will create one and add it to cachePool.
 * Zip support is responsible for managing the lifetime of the cache.  
 *
 * @note If cachePool is NULL, zipFile will be opened without a cache.
 *
 * @param[in] portLib the port library
 * @param[in] filename the zip file to open
 * @param[out] zipFile the zip file structure to populate
 * @param[in] cachePool the cache pool
 * 
 * @return 0 on success
 * @return 	ZIP_ERR_FILE_OPEN_ERROR if is there is an error opening the file
 * @return	ZIP_ERR_FILE_READ_ERROR if there is an error reading the file
 * @return	ZIP_ERR_FILE_CORRUPT if the file is corrupt
 * @return	ZIP_ERR_UNKNOWN_FILE_TYPE if the file type is not known
 * @return	ZIP_ERR_UNSUPPORTED_FILE_TYPE if the file type is unsupported
 * @return	ZIP_ERR_OUT_OF_MEMORY if we are out of memory
*/
I_32
zip_openZipFile (HyPortLibrary * portLib, char *filename, HyZipFile * zipFile,
                 HyZipCachePool * cachePool)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */

  IDATA fd = -1;
  I_32 result = 0;
  I_64 seekResult;
  U_8 buffer[4];
  I_32 len;

  ENTER ();

  len = strlen (filename);
  zipFile->fd = -1;
  zipFile->type = ZIP_Unknown;
  zipFile->cache = NULL;
  zipFile->cachePool = NULL;
  zipFile->pointer = -1;
  /* Allocate space for filename */
  if (len >= ZIP_INTERNAL_MAX)
    {
      zipFile->filename = hymem_allocate_memory (len + 1);
      if (!zipFile->filename)
        {
          EXIT ();
          return ZIP_ERR_OUT_OF_MEMORY;
        }
    }
  else
    {
      zipFile->filename = zipFile->internalFilename;
    }

  strcpy ((char *) zipFile->filename, filename);

  fd = hyfile_open (filename, HyOpenRead, 0);
  if (fd == -1)
    {
      result = ZIP_ERR_FILE_OPEN_ERROR;
      goto finished;
    }

  if (hyfile_read (fd, buffer, 4) != 4)
    {
      result = ZIP_ERR_FILE_READ_ERROR;
      goto finished;
    }

  if ((buffer[0] == 'P') && (buffer[1] == 'K'))
    {
      /* If not the central header or local file header, its corrupt */
      if (!
          ((buffer[2] == 1 && buffer[3] == 2)
           || (buffer[2] == 3 && buffer[3] == 4)))
        {
          result = ZIP_ERR_FILE_CORRUPT;
          goto finished;
        }
      /* PKZIP file. Back up the pointer to the beginning. */
      seekResult = hyfile_seek (fd, 0, HySeekSet);
      if (seekResult != 0)
        {
          result = ZIP_ERR_FILE_READ_ERROR;
          goto finished;
        }

      zipFile->fd = fd;
      zipFile->type = ZIP_PKZIP;
      zipFile->pointer = 0;
    }

  if ((buffer[0] == 0x1F) && (buffer[1] == 0x8B))
    {
      /* GZIP - currently unsupported. 
         zipFile->fd = fd;
         zipFile->type = ZIP_GZIP;
         zipFile->pointer = 2;
       */
      result = ZIP_ERR_UNSUPPORTED_FILE_TYPE;
      goto finished;
    }

  if (zipFile->type == ZIP_Unknown)
    {
      result = ZIP_ERR_UNKNOWN_FILE_TYPE;
      goto finished;
    }

  result = 0;

  if (cachePool)
    {
      zipFile->cachePool = cachePool;
      result = zip_establishCache (portLib, zipFile);
    }

finished:
  if (result == 0)
    {
      zipFile->fd = fd;
      EXIT ();
      return 0;
    }
  if (fd != -1)
    {
      hyfile_close (fd);
    }
  if ((zipFile->filename) && (zipFile->filename != zipFile->internalFilename))
    {
      hymem_free_memory (zipFile->filename);
    }
  zipFile->filename = NULL;
  EXIT ();
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_resetZipFile
/**
 * Reset nextEntryPointer to the first entry in the file.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile the zip being read from
 * @param[out] nextEntryPointer will be reset to the first entry in the file
 *
 * @return none
 *
 * 
*/
void
zip_resetZipFile (HyPortLibrary * portLib, HyZipFile * zipFile,
                  IDATA * nextEntryPointer)
{
  *nextEntryPointer = 0;
  if (zipFile)
    {
      if (zipFile->cache)
        *nextEntryPointer = zipFile->cache->startCentralDir;
      else
        {
          I_32 result;
          HyZipCentralEnd endEntry;
          result = scanForCentralEnd (portLib, zipFile, &endEntry);
          if (result != 0)
            return;
          *nextEntryPointer = (IDATA) ((UDATA) endEntry.dirOffset);
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zip_getZipEntryFromOffset
/**
 * Attempt to read a zip entry at offset from the zip file provided.
 *	If found, read into entry.
 *
 * @note If an uncached entry is found, the filename field will be filled in. This
 *	memory MUST be freed with @ref zip_freeZipEntry.
 *
 * @param[in] portLib the port library
 * @param[in] zipFile the zip file being read
 * @param[in] offset the offset into the zipFile of the desired zip entry
 * @param[out] entry the compressed data
 *
 * @return 0 on success
 * @return ZIP_ERR_FILE_READ_ERROR if there is an error reading from @ref zipFile
 * @return 	ZIP_ERR_FILE_CORRUPT if @ref zipFile is corrupt
 * @return 	ZIP_ERR_ENTRY_NOT_FOUND if the entry is not found 
 * @return 	ZIP_ERR_OUT_OF_MEMORY if there is not enough memory to complete this call
 *
 * @see zip_freeZipEntry
*/
I_32
zip_getZipEntryFromOffset (HyPortLibrary * portLib, HyZipFile * zipFile,
                           HyZipEntry * entry, IDATA offset)
{
  PORT_ACCESS_FROM_PORT (portLib);
#if defined(HY_NO_THR)
  THREAD_ACCESS_FROM_PORT(portLib);
#endif /* HY_NO_THR */
  I_32 result;
  I_64 seekResult;

  ENTER ();

  if (zipFile->pointer != offset)
    {
      seekResult = hyfile_seek (zipFile->fd, offset, HySeekSet);
      if ((seekResult < 0) || (seekResult > HYCONST64 (0x7FFFFFFF)))
        {
          zipFile->pointer = -1;
          EXIT ();
          return ZIP_ERR_FILE_READ_ERROR;
        }
      zipFile->pointer = (I_32) seekResult;
      if (zipFile->pointer != offset)
        {
          zipFile->pointer = -1;
          EXIT ();
          return ZIP_ERR_FILE_READ_ERROR;
        }
    }

  result = readZipEntry (portLib, zipFile, entry, NULL, NULL, NULL, FALSE);
  EXIT ();
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zdataalloc
/*
	cached alloc management for zip_getZipEntryData.
*/
void *
zdataalloc (void *opaque, U_32 items, U_32 size)
{
  UDATA *returnVal = 0;
  U_32 byteSize = items * size;
  U_32 allocSize = WORK_BUFFER_SIZE;
  struct workBuffer *wb = (struct workBuffer *) opaque;

  PORT_ACCESS_FROM_PORT (wb->portLib);

  /* Round to UDATA multiple */
  byteSize = (byteSize + (sizeof (UDATA) - 1)) & ~(sizeof (UDATA) - 1);

  if (wb->bufferStart == 0)
    {
      if (byteSize > WORK_BUFFER_SIZE)
        {
          allocSize = byteSize;
        }
      wb->bufferStart = hymem_allocate_memory (allocSize);
      if (wb->bufferStart)
        {
          wb->bufferEnd = (UDATA *) ((UDATA) wb->bufferStart + allocSize);
          wb->currentAlloc = wb->bufferStart;
          wb->cntr = 0;
        }
    }

  if ((wb->bufferStart == 0)
      || (((UDATA) wb->currentAlloc + byteSize) > (UDATA) wb->bufferEnd))
    {
      returnVal = hymem_allocate_memory (byteSize);
    }
  else
    {
      ++(wb->cntr);
      returnVal = wb->currentAlloc;
      wb->currentAlloc = (UDATA *) ((UDATA) wb->currentAlloc + byteSize);
    }
  return returnVal;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zdatafree
/*
	cached alloc management for zip_getZipEntryData.
*/
void
zdatafree (void *opaque, void *address)
{
  struct workBuffer *wb = (struct workBuffer *) opaque;

  PORT_ACCESS_FROM_PORT (wb->portLib);

  if ((address < (void *) wb->bufferStart)
      || (address >= (void *) wb->bufferEnd))
    {
      hymem_free_memory (address);
    }
  else if (--(wb->cntr) == 0)
    {
      hymem_free_memory (wb->bufferStart);
      wb->bufferStart = wb->bufferEnd = wb->currentAlloc = 0;
    }

}

#undef CDEV_CURRENT_FUNCTION
