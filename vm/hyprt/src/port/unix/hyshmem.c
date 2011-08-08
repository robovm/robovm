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

#define CDEV_CURRENT_FUNCTION _comment_
/**
 * @file
 * @ingroup Port
 * @brief Shared Memory Semaphores
 */

#undef CDEV_CURRENT_FUNCTION

#include "hyport.h"
#include "ut_hyprt.h"

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <errno.h>
#include <string.h>

#include "portnls.h"
#include "portpriv.h"
#include "hysharedhelper.h"
#define CDEV_CURRENT_FUNCTION include_header
#include "hyshmem.h"
#undef CDEV_CURRENT_FUNCTION

#define CREATE_ERROR -10
#define OPEN_ERROR  -11
#define WRITE_ERROR -12
#define READ_ERROR -13
#define MALLOC_ERROR -14

#define HYSH_NO_DATA -21
#define HYSH_BAD_DATA -22

#define RETRY_COUNT 10
#define SUCCESS 0
#define FAILED -1
#define RETRY -2

#define SHMFLAGS (IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR)
#define SHMFLAGS_GROUP (IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP)

#define HYSH_MEMORY_ID "_memory_"

#define __errno2() 0

#define CDEV_CURRENT_FUNCTION _prototypes_private
static IDATA createFile (HyPortLibrary * portLibrary,
                         const char *controlFile);
static I_32 createSharedMemory (HyPortLibrary * portLibrary,
                                struct hyshmem_handle **handle,
                                const char *controlFile, I_32 size,
                                I_32 perm);
static void getNameFromControlFileName (struct HyPortLibrary *portLibrary,
                                        char *buffer, UDATA size,
                                        const char *controlFileName);
static IDATA readControlFile (HyPortLibrary * portLibrary,
                              const char *filename,
                              struct hyshmem_controlFileFormat
                              **controlfileinfo);
static I_32 findError_shmget (I_32 errorCode, I_32 errorCode2);
static I_32 findError_shmctl (I_32 errorCode, I_32 errorCode2);
static void getControlFilePath (struct HyPortLibrary *portLibrary,
                                char *buffer, UDATA size, const char *name);
static I_32 findError_shmat (I_32 errorCode, I_32 errorCode2);
static struct hyshmem_handle *createshmHandle (HyPortLibrary * portLibrary,
                                               I_32 shmid,
                                               const char *controlFile);
static IDATA writeControlFile (HyPortLibrary * portLibrary,
                               const char *filename, I_32 proj_id, key_t key,
                               I_32 size, I_32 shmid);
static IDATA openSharedMemory (HyPortLibrary * portLibrary,
                               struct hyshmem_handle **handle,
                               const char *controlFile);
static int isControlFileName (struct HyPortLibrary *portLibrary,
                              char *buffer);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_open
/**
 * Creates/open a shared memory region
 * 
 * The rootname will uniquely identify the shared memory region, 
 * and is valid across different JVM instance. 
 * 
 * The shared memory region should persist across process, until OS reboots 
 * or destroy call is being made.
 * 
 * @param[in] portLibrary The port Library
 * @param[out] handle This handle is required for further attach/destroy of the memory region
 * @param[in] rootname Shared name for the region, which used to identify the region. 
 * @param[in] size Size of the region in bytes
 * @param[in] perm permission for the region.
 * 
 * @return
 * \arg HYPORT_ERROR_SHMEM_OPFAILED Failure - Cannot open the shared memory region
 * \arg HYPORT_INFO_SHMEM_OPENED Success - Existing memory region has been opened
 * \arg HYPORT_INFO_SHMEM_CREATED Success - A new shared memory region has been created
 * 
 */
IDATA VMCALL
hyshmem_open (HyPortLibrary * portLibrary, struct hyshmem_handle **handle,
              const char *rootname, I_32 size, I_32 perm)
{
  /*TODO: Do we need the length to be longer? */
  char controlFile[HYSH_MAXPATH];
  int retry = RETRY_COUNT;

  Trc_PRT_shmem_hyshmem_open_Entry (rootname, size, perm);

  if (ensureDirectory (portLibrary) == FAILED)
    {
      portLibrary->error_set_last_error (portLibrary, errno,
                                         HYPORT_ERROR_SHMEM_DATA_DIRECTORY_FAILED);
      Trc_PRT_shmem_hyshmem_open_Exit3 ();
      return HYPORT_ERROR_SHSEM_OPFAILED;
    }

  getControlFilePath (portLibrary, controlFile, HYSH_MAXPATH, rootname);

  while (retry)
    {
      I_32 rc;

      rc = portLibrary->file_attr (portLibrary, controlFile);
      if (HyIsFile != rc)
        {
          Trc_PRT_shmem_hyshmem_open_Event1 (controlFile);
          rc =
            createSharedMemory (portLibrary, handle, controlFile, size, perm);
        }
      else
        {
          Trc_PRT_shmem_hyshmem_open_Event2 (controlFile);
          rc = openSharedMemory (portLibrary, handle, controlFile);
        }

      switch (rc)
        {
        case RETRY:
          Trc_PRT_shmem_hyshmem_open_Event3 (retry);
          retry--;
          usleep (100);
          continue;
        case FAILED:
          Trc_PRT_shmem_hyshmem_open_Exit1 ();
          return HYPORT_ERROR_SHMEM_OPFAILED;
        default:
          Trc_PRT_shmem_hyshmem_open_Exit (rc, *handle);
          return rc;
        }
    }

  /* max number of retry count reach, return failure */
  portLibrary->file_unlink (portLibrary, controlFile);
  Trc_PRT_shmem_hyshmem_open_Exit2 ();
  return HYPORT_ERROR_SHMEM_OPFAILED;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_close
/**
 * Detach, Close and remove the shared memory handle.
 * 
 * @note This method does not remove the shared memory region from the OS
 *	 use @ref hyshmem_destroy instead. However this will free all the memory
 * resources used by the handle, and detach the region specified by the handle
 *
 * @param[in] portLibrary The port Library
 * @param[in] handle Pointer to a valid shared memory handle
 * 
 */
void VMCALL
hyshmem_close (struct HyPortLibrary *portLibrary,
               struct hyshmem_handle **handle)
{
  Trc_PRT_shmem_hyshmem_close_Entry (*handle);
  portLibrary->shmem_detach (portLibrary, handle);
  portLibrary->mem_free_memory (portLibrary, (*handle)->baseFileName);
  portLibrary->mem_free_memory (portLibrary, *handle);

  *handle = NULL;
  Trc_PRT_shmem_hyshmem_close_Exit ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_attach
/**
 * Attaches the shared memory represented by the handle
 * 
 * @param[in] portLibrary The port Library
 * @param[in] handle A valid shared memory handle
 * 
 * @return: A pointer to the shared memory region, NULL on failure
 */
void *VMCALL
hyshmem_attach (struct HyPortLibrary *portLibrary,
                struct hyshmem_handle *handle)
{
  void *region;
  Trc_PRT_shmem_hyshmem_attach_Entry (handle);

  if (NULL == handle)
    {
      Trc_PRT_shmem_hyshmem_attach_Exit1 ();
      return NULL;
    }

  Trc_PRT_shmem_hyshmem_attach_Debug1 (handle->shmid);

  if (NULL != handle->regionStart)
    {
      Trc_PRT_shmem_hyshmem_attach_Exit (handle->regionStart);
      return handle->regionStart;
    }

  if (handle->shmid > 0)
    {
      region = shmat (handle->shmid, 0, 0);
      /* Do not do shmctl to release on event of ^C or crash */
      if ((void *) -1 != region)
        {
          handle->regionStart = region;
          Trc_PRT_shmem_hyshmem_attach_Exit (region);
          return region;
        }
    }

  portLibrary->error_set_last_error (portLibrary, errno,
                                     findError_shmat (errno, __errno2 ()));
  Trc_PRT_shmem_hyshmem_attach_Exit2 (errno);
  return NULL;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_destroy
/**
 * Destroy and removes the shared memory region from OS.
 * 
 * The timing of which OS removes the memory is OS dependent. However when 
 * you make a call you can considered that you can no longer access the region through
 * the handle. Memory allocated for handle structure is freed as well.
 * 
 * @param[in] portLibrary The port Library
 * @param[in] handle Pointer to a valid shared memory handle
 * 
 * @return 0 on success, -1 on failure.
 */
IDATA VMCALL
hyshmem_destroy (struct HyPortLibrary * portLibrary,
                 struct hyshmem_handle ** handle)
{
  IDATA rc;

  Trc_PRT_shmem_hyshmem_destroy_Entry (*handle);

  if (NULL == *handle)
    {
      Trc_PRT_shmem_hyshmem_destroy_Exit ();
      return SUCCESS;
    }

  portLibrary->shmem_detach (portLibrary, handle);

  rc = portLibrary->file_unlink (portLibrary, (*handle)->baseFileName);
  Trc_PRT_shmem_hyshmem_destroy_Debug1 ((*handle)->baseFileName, rc, errno);

  if (-1 == shmctl ((*handle)->shmid, IPC_RMID, NULL))
    {
      portLibrary->error_set_last_error (portLibrary, errno,
                                         findError_shmctl (errno,
                                                           __errno2 ()));
      Trc_PRT_shmem_hyshmem_destroy_Exit1 ();
      return FAILED;
    }

  portLibrary->shmem_close (portLibrary, handle);
  Trc_PRT_shmem_hyshmem_destroy_Exit ();
  return SUCCESS;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_detach
/**
 * Detaches the shared memory region from the caller's process address space
 * Use @ref hyshmem_destroy to actually remove the memory region from the Operating system
 *
 * @param[in] portLibrary the Port Library.
 * @param[in] handle Pointer to the shared memory region.
 * 
 * @return 0 on success, -1 on failure.
 */
IDATA VMCALL
hyshmem_detach (struct HyPortLibrary * portLibrary,
                struct hyshmem_handle ** handle)
{
  Trc_PRT_shmem_hyshmem_detach_Entry (*handle);
  if ((*handle)->regionStart == NULL)
    {
      Trc_PRT_shmem_hyshmem_detach_Exit ();
      return SUCCESS;
    }

  if (-1 == shmdt ((*handle)->regionStart))
    {
      Trc_PRT_shmem_hyshmem_detach_Exit1 ();
      return FAILED;
    }

  (*handle)->regionStart = NULL;
  Trc_PRT_shmem_hyshmem_detach_Exit ();
  return SUCCESS;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_findclose
/**
 * Close the handle returned from @ref hyshmem_findfirst.
 *
 * @param[in] portLibrary The port library
 * @param[in] findhandle  Handle returned from @ref hyshmem_findfirst.
 */
void VMCALL
hyshmem_findclose (struct HyPortLibrary *portLibrary, UDATA findhandle)
{
  Trc_PRT_shmem_hyshmem_findclose_Entry (findhandle);
  portLibrary->file_findclose (portLibrary, findhandle);
  Trc_PRT_shmem_hyshmem_findclose_Exit ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_findfirst
/**
 * Find the name of a shared memory region on the system. Answers a handle
 * to be used in subsequent calls to @ref hyshmem_findnext and @ref hyshmem_findclose. 
 *
 * @param[in] portLibrary The port library
 * @param[out] resultbuf filename and path matching path.
 *
 * @return valid handle on success, -1 on failure.
 */
UDATA VMCALL
hyshmem_findfirst (struct HyPortLibrary *portLibrary, char *resultbuf)
{
  UDATA findHandle;
  char file[HyMaxPath];

  Trc_PRT_shmem_hyshmem_findfirst_Entry ();
  findHandle = portLibrary->file_findfirst (portLibrary, HYSH_BASEDIR, file);

  if (findHandle == -1)
    {
      Trc_PRT_shmem_hyshmem_findfirst_Exit1 ();
      return -1;
    }

  while (!isControlFileName (portLibrary, file))
    {
      if (-1 == portLibrary->file_findnext (portLibrary, findHandle, file))
        {
          portLibrary->file_findclose (portLibrary, findHandle);
          Trc_PRT_shmem_hyshmem_findfirst_Exit2 ();
          return -1;
        }
    }

  /*TODO: We want to do some verifying here */
  getNameFromControlFileName (portLibrary, resultbuf, HyMaxPath, file);
  Trc_PRT_shmem_hyshmem_findfirst_Exit ();
  return findHandle;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_findnext
/**
 * Find the name of the next shared memory region.
 *
 * @param[in] portLibrary The port library
 * @param[in] findhandle handle returned from @ref hyshmem_findfirst.
 * @param[out] resultbuf next filename and path matching findhandle.
 *
 * @return 0 on success, -1 on failure or if no matching entries.
 */
I_32 VMCALL
hyshmem_findnext (struct HyPortLibrary * portLibrary, UDATA findHandle,
                  char *resultbuf)
{
  char file[HyMaxPath];

  Trc_PRT_shmem_hyshmem_findnext_Entry (findHandle);

  if (-1 == portLibrary->file_findnext (portLibrary, findHandle, file))
    {
      Trc_PRT_shmem_hyshmem_findnext_Exit1 ();
      return -1;
    }

  while (!isControlFileName (portLibrary, file))
    {
      if (-1 == portLibrary->file_findnext (portLibrary, findHandle, file))
        {
          Trc_PRT_shmem_hyshmem_findnext_Exit2 ();
          return -1;
        }
    }

  /* TODO: needs code to verify whether the file is a valid control file */
  getNameFromControlFileName (portLibrary, resultbuf, HyMaxPath, file);
  Trc_PRT_shmem_hyshmem_findnext_Exit ();
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_stat
/**
 * Return the statistic for a shared memory region
 *
 * @note notice that the implementation can decided to put -1 in the fields of @ref statbuf
 * if it does not make sense on this platform, or it is impossible to obtain.
 * 
 * @param[in] portLibrary The port library
 * @param[in] name The name of the shared memory area.
 * @param[out] statbuf the statistics returns by the operating system
 *
 * @return 0 on success, -1 on failure or if there is no matching entries.
 */
UDATA VMCALL
hyshmem_stat (struct HyPortLibrary * portLibrary, const char *name,
              struct HyPortShmemStatistic * statbuf)
{
  I_32 rc;
  char controlFile[HYSH_MAXPATH];
  struct hyshmem_controlFileFormat *fileContents;
  struct shmid_ds shminfo;

  Trc_PRT_shmem_hyshmem_stat_Entry (name);
  if (statbuf == NULL)
    {
      return -1;
    }

  getControlFilePath (portLibrary, controlFile, HYSH_MAXPATH, name);

  rc = portLibrary->file_attr (portLibrary, controlFile);
  if (HyIsFile != rc)
    {
      Trc_PRT_shmem_hyshmem_stat_Exit1 (controlFile);
      return -1;
    }

  if (readControlFile (portLibrary, controlFile, &fileContents) != SUCCESS)
    {
      Trc_PRT_shmem_hyshmem_stat_Exit2 (controlFile);
      return -1;
    }

  statbuf->shmid = fileContents->shmid;
  statbuf->file = NULL;         /* We probably don't need it */

  rc = shmctl (statbuf->shmid, IPC_STAT, &shminfo);

  if (-1 == rc)
    {
      Trc_PRT_shmem_hyshmem_stat_Exit3 (errno);
      return -1;
    }

  statbuf->atime = shminfo.shm_atime;
  statbuf->dtime = shminfo.shm_dtime;
  statbuf->chtime = shminfo.shm_ctime;
  statbuf->nattach = shminfo.shm_nattch;
  statbuf->perm = 0;            /* Not used yet, but we probably want to convert into portable ones later */

  Trc_PRT_shmem_hyshmem_stat_Exit ();
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyshsem_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyshmem_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshmem_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the file operations may be created here.  All resources created here should be destroyed
 * in @ref hyshsem_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_SHMEM
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyshmem_startup (struct HyPortLibrary *portLibrary)
{
  portLibrary->portGlobals->control.shmem_group_perm = 0;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION openSharedMemory
static IDATA
openSharedMemory (HyPortLibrary * portLibrary, struct hyshmem_handle **handle,
                  const char *controlFile)
{
  struct hyshmem_controlFileFormat *info;
  void *region;
  UDATA retryCount = RETRY_COUNT;
  IDATA rc;

  while (retryCount > 0)
    {
      rc = readControlFile (portLibrary, controlFile, &info);
      switch (rc)
        {
        case OPEN_ERROR:
          return FAILED;
        case READ_ERROR:
          return RETRY;
        case HYSH_NO_DATA:
          retryCount--;
          usleep (100);
          continue;
        case HYSH_BAD_DATA:
          return FAILED;
        default:
          retryCount = 0;
          break;
        }
    }

  if (NULL == info)
    {
      /* Can't get anything from the control file */
      /* We will just unlink the control file and retry */
#if defined(HYSHMEM_DEBUG)
      portLibrary->tty_printf (portLibrary,
                               "info is not right, back to top\n");
#endif /* HYSHMEM_DEBUG */

      if (portLibrary->file_unlink (portLibrary, controlFile) < 0)
        {
          return FAILED;
        }
      else
        {
          return RETRY;
        }
    }

  /* check that the modlevel and version is okay */
  if (info->version != HYSH_VERSION || info->modlevel != HYSH_MODLEVEL)
    {
      portLibrary->mem_free_memory (portLibrary, info);
      return FAILED;
    }

  *handle = createshmHandle (portLibrary, info->shmid, controlFile);

  /* Check that we can actually attach to *a* region */
  region = hyshmem_attach (portLibrary, *handle);
  if (NULL == region)
    {
      /* controlFile's shmid is bad, or in zOS case we are using the wrong key! */

#if defined(HYSHMEM_DEBUG)
      portLibrary->tty_printf (portLibrary, "shmid not valid, back to top\n");
#endif /* HYSHMEM_DEBUG */

      portLibrary->mem_free_memory (portLibrary, info);
      if (portLibrary->error_last_error_number (portLibrary) ==
          HYPORT_ERROR_SHMEM_INVALID_INPUT)
        {
          if (portLibrary->file_unlink (portLibrary, controlFile) == 0)
            {
              return RETRY;
            }
        }
      return FAILED;
    }

  /* everything rosy, so just detach the region */
  hyshmem_detach (portLibrary, handle);

  /* now setup the handle */

  (*handle)->timestamp = portLibrary->file_lastmod (portLibrary, controlFile);
  portLibrary->mem_free_memory (portLibrary, info);

  return HYPORT_INFO_SHMEM_OPENED;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createSharedMemory
static I_32
createSharedMemory (HyPortLibrary * portLibrary,
                    struct hyshmem_handle **handle, const char *controlFile,
                    I_32 size, I_32 perm)
{
  I_32 projid = 1;
  key_t fkey;
  I_32 shmid;
  IDATA rc;

  int group_perm = portLibrary->portGlobals->control.shmem_group_perm;
  int shmflags = group_perm ? SHMFLAGS_GROUP : SHMFLAGS;

  Trc_PRT_shmem_createSharedMemory_Entry ();

  /* Control file doesn't exist, so we create it along with shared memory region.
   * If the control file exists we might be in a creation race, return to the top
   */
  rc = createFile (portLibrary, controlFile);
  if (HYPORT_ERROR_FILE_EXIST == rc)
    {
      Trc_PRT_shmem_createSharedMemory_Exit1 ();
      return RETRY;
    }
  else if (CREATE_ERROR == rc)
    {
      Trc_PRT_shmem_createSharedMemory_Exit2 ();
      return FAILED;
    }

  /*created control file, now create shared memory */
  while (1)
    {
      /* first ftok to get key */

      Trc_PRT_shmem_createSharedMemory_ftok (controlFile, projid);
      fkey = ftok (controlFile, projid);
      if (-1 == fkey)
        {
          if (errno == ENOENT || errno == ENOTDIR)
            {
              /* TODO: Someone removed our directory!
               * We will need a new return code here and tell user
               * of this lib to restart at top */
              Trc_PRT_shmem_createSharedMemory_Exit3 ();
              return RETRY;
            }
          /* any other error code is bad */
          Trc_PRT_shmem_createSharedMemory_Exit4 ();
          return FAILED;
        }

      /* now we do semget */
      /* TODO: we need to round up size to nearest M */
      Trc_PRT_shmem_createSharedMemory_shmget (fkey, size, SHMFLAGS);
      shmid = shmget (fkey, size, shmflags);

      if (-1 == shmid)
        {
          if (EEXIST == errno)
            {
              if (projid >= HYSH_MAX_PROJ_ID)
                {
                  portLibrary->error_set_last_error (portLibrary, errno,
                                                     findError_shmget (errno,
                                                                       __errno2
                                                                       ()));
                  Trc_PRT_shmem_createSharedMemory_Exit5 (HYSH_MAX_PROJ_ID);
                  return FAILED;
                }
              else
                {
                  Trc_PRT_shmem_createSharedMemory_shmget_Event1 (projid);
                  projid++;
                  continue;
                }
            }

          /* If we get here it means some OS error stop us getting shm segments */
          /* Here we would want to capture the zOS errno2 - in order to see whether
             We are trying to create in different storage key */
          portLibrary->error_set_last_error (portLibrary, errno,
                                             findError_shmget (errno,
                                                               __errno2 ()));
          portLibrary->file_unlink (portLibrary, controlFile);
          Trc_PRT_shmem_createSharedMemory_Exit6 (errno, __errno2 ());
          return FAILED;
        }
      else
        {
          /* shmid is good, we can stop loop */
          break;
        }
    }

  Trc_PRT_shmem_createSharedMemory_Event1 (shmid);

  if (writeControlFile (portLibrary, controlFile, projid, fkey, size, shmid)
      == CREATE_ERROR)
    {
      Trc_PRT_shmem_createSharedMemory_Exit7 ();
      portLibrary->file_unlink (portLibrary, controlFile);
      return FAILED;
    }

  *handle = createshmHandle (portLibrary, shmid, controlFile);
  if (NULL == *handle)
    {
      Trc_PRT_shmem_createSharedMemory_Exit8 ();
      return FAILED;
    }

  (*handle)->timestamp = portLibrary->file_lastmod (portLibrary, controlFile);

  Trc_PRT_shmem_createSharedMemory_Exit ();
  return HYPORT_INFO_SHMEM_CREATED;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createFile
static IDATA
createFile (HyPortLibrary * portLibrary, const char *controlFile)
{
  I_32 fd;

  Trc_PRT_shmem_createFile_Entry ();

  fd =
    portLibrary->file_open (portLibrary, controlFile,
                            HyOpenWrite | HyOpenCreateNew, HYSH_BASEFILEPERM);
  if (-1 == fd)
    {
      I_32 errorno = portLibrary->error_last_error_number (portLibrary);

      /*special handling for file exist case */
      if (HYPORT_ERROR_FILE_EXIST == errorno)
        {
          Trc_PRT_shmem_createFile_Exit1 ();
          return errorno;
        }

      Trc_PRT_shmem_createFile_Exit2 (portLibrary->
                                      error_last_error_message (portLibrary));
      return CREATE_ERROR;
    }

  Trc_PRT_shmem_createFile_Exit ();
  portLibrary->file_close (portLibrary, fd);
  return SUCCESS;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createshmHandle
static struct hyshmem_handle *
createshmHandle (HyPortLibrary * portLibrary, I_32 shmid,
                 const char *controlFile)
{
  struct hyshmem_handle *result;
  IDATA cfstrLength = strlen (controlFile);

  result =
    portLibrary->mem_allocate_memory (portLibrary,
                                      sizeof (struct hyshmem_handle));
  if (NULL == result)
    {
#if defined(HYSHMEM_DEBUG)
      portLibrary->tty_printf (portLibrary, "Malloc error!");
#endif /* HYSHMEM_DEBUG */

      return NULL;              /*malloc error! */
    }

  result->shmid = shmid;
  result->baseFileName =
    portLibrary->mem_allocate_memory (portLibrary, cfstrLength + 1);
  if (NULL == result->baseFileName)
    {
      portLibrary->mem_free_memory (portLibrary, result);
      return NULL;
    }

  portLibrary->str_printf (portLibrary, result->baseFileName, cfstrLength + 1,
                           controlFile);
  result->regionStart = NULL;
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION writeControlFile
static IDATA
writeControlFile (HyPortLibrary * portLibrary, const char *filename,
                  I_32 proj_id, key_t key, I_32 size, I_32 shmid)
{
  I_32 fd, rc;
  struct hyshmem_controlFileFormat *info;

  fd =
    portLibrary->file_open (portLibrary, filename,
                            HyOpenTruncate | HyOpenWrite, HYSH_BASEFILEPERM);
  if (-1 == fd)
    {
      return CREATE_ERROR;
    }

  info =
    portLibrary->mem_allocate_memory (portLibrary,
                                      sizeof (struct
                                              hyshmem_controlFileFormat));
  if (info == NULL)
    {
      /*malloc failure! */
      return CREATE_ERROR;
    }

  info->version = HYSH_VERSION;
  info->modlevel = HYSH_MODLEVEL;
  info->proj_id = proj_id;
  info->ftok_key = key;
  info->shmid = shmid;
  info->size = size;
  info->uid = getuid ();
  info->gid = getgid ();

#if defined(HYSHMEM_DEBUG)
  portLibrary->tty_printf (portLibrary, "writing: shmid = %x\n", shmid);
#endif /* HYSHMEM_DEBUG */

  rc =
    portLibrary->file_write (portLibrary, fd, info,
                             sizeof (struct hyshmem_controlFileFormat));
  if (rc < 0)
    {
      rc = CREATE_ERROR;
    }
  else
    {
      rc = SUCCESS;
    }

  portLibrary->file_close (portLibrary, fd);
  portLibrary->mem_free_memory (portLibrary, info);
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION readControlFile
static IDATA
readControlFile (HyPortLibrary * portLibrary, const char *filename,
                 struct hyshmem_controlFileFormat **controlfileinfo)
{
  I_32 fd, rc;
  struct hyshmem_controlFileFormat *info;

  fd = portLibrary->file_open (portLibrary, filename, HyOpenRead, 0);
  if (-1 == fd)
    {
      return OPEN_ERROR;
    }

  info =
    portLibrary->mem_allocate_memory (portLibrary,
                                      sizeof (struct
                                              hyshmem_controlFileFormat));
  if (NULL == info)
    {
      /* malloc failure! */
      return MALLOC_ERROR;
    }

  rc =
    portLibrary->file_read (portLibrary, fd, info,
                            sizeof (struct hyshmem_controlFileFormat));
  portLibrary->file_close (portLibrary, fd);
  if (rc < 0)
    {
      rc = READ_ERROR;
    }
  else if (rc == 0)
    {
      rc = HYSH_NO_DATA;
    }
  else if (rc < sizeof (struct hyshmem_controlFileFormat))
    {
      rc = HYSH_BAD_DATA;
    }
  else
    {
      rc = SUCCESS;
    }

  if (SUCCESS != rc)
    {
      portLibrary->mem_free_memory (portLibrary, info);
      (*controlfileinfo) = NULL;
    }
  else
    {
      (*controlfileinfo) = info;
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION isControlFileName
static int
isControlFileName (struct HyPortLibrary *portLibrary, char *buffer)
{
  char versionStr[30];

  GET_VERSION_STRING (portLibrary, versionStr);

  if (NULL != strstr (buffer, versionStr))
    {
      if (NULL != strstr (buffer, HYSH_MEMORY_ID))
        {
          return 1;
        }
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getNameFromControlFileName
static void
getNameFromControlFileName (struct HyPortLibrary *portLibrary, char *buffer,
                            UDATA size, const char *controlFileName)
{
  char *name = strstr (controlFileName, HYSH_MEMORY_ID);

  if (name == NULL)
    {
      return;
    }

  name = name + strlen (HYSH_MEMORY_ID);

  portLibrary->str_printf (portLibrary, buffer, size, name);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getControlFilePath
/**
 * @internal
 * Get the full path for a shared memory area
 *
 * @param[in] portLibrary The port library
 * @param[out] buffer where the path will be stored
 * @param[in] size size of the buffer
 * @param[in] name of the shared memory area
 *
 */
static void
getControlFilePath (struct HyPortLibrary *portLibrary, char *buffer,
                    UDATA size, const char *name)
{
  char versionStr[256];

  GET_VERSION_STRING (portLibrary, versionStr);
  portLibrary->str_printf (portLibrary, buffer, size, "%s%s%s%s",
                           HYSH_BASEDIR, versionStr, HYSH_MEMORY_ID, name);

#if defined(HYSHMEM_DEBUG)
  portLibrary->tty_printf (portLibrary, "getControlFilePath returns: %s\n",
                           buffer);
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findError_shmget
/**
 * @internal
 * Determines the proper portable error code to return given a native error code, for shmget calls
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError_shmget (I_32 errorCode, I_32 errorCode2)
{
  switch (errorCode)
    {
    case EPERM:
    case EACCES:
      return HYPORT_ERROR_SHMEM_NOPERMISSION;
    case EEXIST:
      return HYPORT_ERROR_SHMEM_ALREADY_EXIST;
    case EINVAL:
      return HYPORT_ERROR_SHMEM_TOOBIG;
    case ENOMEM:
    case ENOSPC:
    case EMFILE:
      return HYPORT_ERROR_SHMEM_NOSPACE;
    case ENOENT:
    default:
      return HYPORT_ERROR_SHMEM_OPFAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findError_shmat
/**
 * @internal
 * Determines the proper portable error code to return given a native error code
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError_shmat (I_32 errorCode, I_32 errorCode2)
{
  switch (errorCode)
    {
    case EPERM:
    case EACCES:
      return HYPORT_ERROR_SHMEM_NOPERMISSION;
    case EEXIST:
      return HYPORT_ERROR_SHMEM_ALREADY_EXIST;
    case ENOMEM:
    case ENOSPC:
    case EMFILE:
      return HYPORT_ERROR_SHMEM_NOSPACE;
    case EINVAL:
      return HYPORT_ERROR_SHMEM_INVALID_INPUT;
    case ENOENT:
    default:
      return HYPORT_ERROR_SHMEM_ATTACH_FAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findError_shmctl
/**
 * @internal
 * Determines the proper portable error code to return given a native error code
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError_shmctl (I_32 errorCode, I_32 errorCode2)
{
  switch (errorCode)
    {
    case EPERM:
    case EACCES:
      return HYPORT_ERROR_SHMEM_NOPERMISSION;
    default:
      return HYPORT_ERROR_SHMEM_OPFAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION
