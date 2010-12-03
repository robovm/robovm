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
 * @brief Shared Semaphores
 */

#undef CDEV_CURRENT_FUNCTION

#include "portpriv.h"
#include "hyport.h"
#include "portnls.h"
#include "ut_hyprt.h"
#include "hysharedhelper.h"
#include <string.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>

#define CDEV_CURRENT_FUNCTION include_header
#include "hyshsem.h"
#undef CDEV_CURRENT_FUNCTION

/*These flags are only used internally*/
#define CREATE_ERROR -10
#define OPEN_ERROR  -11
#define WRITE_ERROR -12
#define READ_ERROR -13
#define MALLOC_ERROR -14

#define HYSH_NO_DATA -21
#define HYSH_BAD_DATA -22

#define RETRY_COUNT 10
#define SLEEP_TIME 5

#define SEMAPHORE_OPENED 2
#define SEMAPHORE_CREATED 1
#define SUCCESS 0
#define FAILED -1
#define RETRY -2

#define SEMMARKER 769

#define CDEV_CURRENT_FUNCTION _prototypes_private
static IDATA checkMarker (hyshsem_handle * handle, int semsetsize);
static IDATA createbaseFile (struct HyPortLibrary *portLibrary,
                             char *filename);
static IDATA createSemaphore (struct HyPortLibrary *portLibrary,
                              char *baseFile, I_32 setSize,
                              hyshsem_handle ** handle);
static IDATA openSemaphore (struct HyPortLibrary *portLibrary, char *baseFile,
                            I_64 timestamp, hyshsem_handle ** handle);
static hyshsem_handle *createsemHandle (struct HyPortLibrary *portLibrary,
                                        int semid, int nsems, char *baseFile);
static IDATA readbaseFile (struct HyPortLibrary *portLibrary, char *filename,
                           hyshsem_baseFileFormat ** basefileInfo);
static I_32 setMarker (hyshsem_handle * handle, int semsetsize);
static I_32 findError (I_32 errorCode, I_32 errorCode2);
static IDATA writebaseFile (struct HyPortLibrary *portLibrary, char *filename,
                            int proj_id, key_t key, int semid, int setSize);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_open
/**
 * Open an existing semaphore set, or create a new one if it does not exist
 * 
 * @param[in] portLibrary The port library.
 * @param[out] handle A semaphore handle is allocated and initialised for use with further calls, NULL on failure.
 * @param[in] semname Unique identifier of the semaphore.
 * @param[in] setSize Size of the semaphore set.
 * @param[in] permission Permission to the semaphore set.
 *
 * @return
 * \arg HYPORT_ERROR_SHSEM_OPFAILED   Failure - Error opening the semaphore
 * \arg HYPORT_INFO_SHSEM_CREATED Success - Semaphore has been created
 * \arg HYPORT_INFO_SHSEM_OPENED  Success - Existing semaphore has been opened
 * \arg HYPORT_INFO_SHSEM_SEMID_DIFF Success - Existing semaphore opened, but OS Semaphore key is different
 */
IDATA VMCALL
hyshsem_open (struct HyPortLibrary *portLibrary,
              struct hyshsem_handle **handle, const char *semname,
              int setSize, int permission)
{
  /* TODO: needs to be longer? dynamic? */
  char baseFile[HYSH_MAXPATH];
  char versionStr[32];
  I_8 retry = RETRY_COUNT;

  Trc_PRT_shsem_hyshsem_open_Entry (semname, setSize, permission);

  if (ensureDirectory (portLibrary) == FAILED)
    {
      portLibrary->error_set_last_error (portLibrary, errno,
                                         HYPORT_ERROR_SHSEM_DATA_DIRECTORY_FAILED);
      Trc_PRT_shsem_hyshsem_open_Exit3 ();
      return HYPORT_ERROR_SHSEM_OPFAILED;
    }

  GET_VERSION_STRING (portLibrary, versionStr);
  portLibrary->str_printf (portLibrary, baseFile, HYSH_MAXPATH,
                           "%s%s_semaphore_%s", HYSH_BASEDIR, versionStr,
                           semname);

  Trc_PRT_shsem_hyshsem_open_Debug1 (baseFile);

  while (retry > 0)
    {
      I_64 timestamp = 0;
      I_32 rc = 0;

      /* check and see whether the basefile exist */
      rc = portLibrary->file_attr (portLibrary, baseFile);

      if (HyIsFile == rc)
        {
          /* baseFile exist, we should try and open the semaphore */
          Trc_PRT_shsem_hyshsem_open_Event1 (baseFile);
          rc = openSemaphore (portLibrary, baseFile, timestamp, handle);
        }
      else
        {
          /* baseFile probably not exist, we should try and create the semaphore */
          /* For new semaphore setsize can't be 0 - failure */
          if (setSize == 0)
            {
              return HYPORT_ERROR_SHSEM_OPFAILED;
            }

          Trc_PRT_shsem_hyshsem_open_Event2 (baseFile);
          rc = createSemaphore (portLibrary, baseFile, setSize, handle);
        }

      switch (rc)
        {
        case RETRY:
          Trc_PRT_shsem_hyshsem_open_Event3 (retry);
          usleep (100);
          retry--;
          break;
        case SEMAPHORE_OPENED:
          Trc_PRT_shsem_hyshsem_open_Exit (HYPORT_INFO_SHSEM_OPENED, *handle);
          return HYPORT_INFO_SHSEM_OPENED;
        case HYPORT_INFO_SHSEM_SEMID_DIFF:
          Trc_PRT_shsem_hyshsem_open_Exit (HYPORT_INFO_SHSEM_SEMID_DIFF,
                                           *handle);
          return HYPORT_INFO_SHSEM_SEMID_DIFF;
        case SEMAPHORE_CREATED:
          Trc_PRT_shsem_hyshsem_open_Exit (HYPORT_INFO_SHSEM_CREATED,
                                           *handle);
          return HYPORT_INFO_SHSEM_CREATED;
        case FAILED:
        default:
          Trc_PRT_shsem_hyshsem_open_Exit1 ();
          return HYPORT_ERROR_SHSEM_OPFAILED;
        }
    }                           /*end while */

  /* we have retried too many times, return failed */
  portLibrary->file_unlink (portLibrary, baseFile);
  Trc_PRT_shsem_hyshsem_open_Exit2 ();
  return HYPORT_ERROR_SHSEM_OPFAILED;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_post
/**
 * post operation increments the counter in the semaphore by 1 if there is no one in wait for the semaphore. 
 * if there are other processes suspended by wait then one of them will become runnable and 
 * the counter remains the same. 
 * 
 * @param[in] portLibrary The port library.
 * @param[in] handle Semaphore set handle.
 * @param[in] semset The no of semaphore in the semaphore set that you want to post.
 * @param[in] flag The semaphore operation flag:
 * \arg HYPORT_SHSEM_MODE_DEFAULT The default operation flag, same as 0
 * \arg HYPORT_SHSEM_MODE_UNDO The changes made to the semaphore will be undone when this process finishes.
 *
 * @return 0 on success, -1 on failure.
 */
IDATA VMCALL
hyshsem_post (struct HyPortLibrary * portLibrary,
              struct hyshsem_handle * handle, UDATA semset, UDATA flag)
{
  struct sembuf buffer;
  IDATA rc;

  Trc_PRT_shsem_hyshsem_post_Entry (handle, semset, flag);
  if (handle == NULL)
    {
      Trc_PRT_shsem_hyshsem_post_Exit1 ();
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }

  if (semset < 0 || semset >= handle->nsems)
    {
      Trc_PRT_shsem_hyshsem_post_Exit2 ();
      return HYPORT_ERROR_SHSEM_SEMSET_INVALID;
    }

  buffer.sem_num = semset;
  buffer.sem_op = 1;            /* post */
  if (flag & HYPORT_SHSEM_MODE_UNDO)
    {
      buffer.sem_flg = SEM_UNDO;
    }
  else
    {
      buffer.sem_flg = 0;
    }

  rc = semop (handle->semid, &buffer, 1);

  if (-1 == rc)
    {
      Trc_PRT_shsem_hyshsem_post_Exit3 (rc, errno);
    }
  else
    {
      Trc_PRT_shsem_hyshsem_post_Exit (rc);
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_wait
/**
 * Wait operation decrements the counter in the semaphore set if the counter > 0
 * if counter == 0 then the caller will be suspended.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] handle Semaphore set handle.
 * @param[in] semset The no of semaphore in the semaphore set that you want to post.
 * @param[in] flag The semaphore operation flag:
 * \arg HYPORT_SHSEM_MODE_DEFAULT The default operation flag, same as 0
 * \arg HYPORT_SHSEM_MODE_UNDO The changes made to the semaphore will be undone when this process finishes.
 * \arg HYPORT_SHSEM_MODE_NOWAIT The caller will not be suspended if sem == 0, a -1 is returned instead.
 * 
 * @return 0 on success, -1 on failure.
 */
IDATA VMCALL
hyshsem_wait (struct HyPortLibrary * portLibrary,
              struct hyshsem_handle * handle, UDATA semset, UDATA flag)
{
  struct sembuf buffer;
  IDATA rc;

  Trc_PRT_shsem_hyshsem_wait_Entry (handle, semset, flag);
  if (handle == NULL)
    {
      Trc_PRT_shsem_hyshsem_wait_Exit1 ();
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }

  if (semset < 0 || semset >= handle->nsems)
    {
      Trc_PRT_shsem_hyshsem_wait_Exit2 ();
      return HYPORT_ERROR_SHSEM_SEMSET_INVALID;
    }

  buffer.sem_num = semset;
  buffer.sem_op = -1;           /* wait */

  if (flag & HYPORT_SHSEM_MODE_UNDO)
    {
      buffer.sem_flg = SEM_UNDO;
    }
  else
    {
      buffer.sem_flg = 0;
    }

  if (flag & HYPORT_SHSEM_MODE_NOWAIT)
    {
      buffer.sem_flg = buffer.sem_flg | IPC_NOWAIT;
    }

  rc = semop (handle->semid, &buffer, 1);
  if (-1 == rc)
    {
      Trc_PRT_shsem_hyshsem_wait_Exit3 (rc, errno);
    }
  else
    {
      Trc_PRT_shsem_hyshsem_wait_Exit (rc);
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_getVal
/**
 * reading the value of the semaphore in the set. This function
 * uses no synchronisation prmitives
  * 
 * @pre caller has to deal with synchronisation issue.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Semaphore set handle.
 * @param[in] semset The number of semaphore in the semaphore set that you want to post.
 * 
 * @return -1 on failure, the value of the semaphore on success
 * 
 * @warning: The user will need to make sure locking is done correctly when
 * accessing semaphore values. This is because getValue simply reads the semaphore
 * value without stopping the access to the semaphore. Therefore the value of the semaphore
 * can change before the function returns. 
 */
IDATA VMCALL
hyshsem_getVal (struct HyPortLibrary * portLibrary,
                struct hyshsem_handle * handle, UDATA semset)
{
  IDATA rc;
  Trc_PRT_shsem_hyshsem_getVal_Entry (handle, semset);
  if (handle == NULL)
    {
      Trc_PRT_shsem_hyshsem_getVal_Exit1 ();
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }

  if (semset < 0 || semset >= handle->nsems)
    {
      Trc_PRT_shsem_hyshsem_getVal_Exit2 ();
      return HYPORT_ERROR_SHSEM_SEMSET_INVALID;
    }

  rc = semctl (handle->semid, semset, GETVAL);
  if (-1 == rc)
    {
      Trc_PRT_shsem_hyshsem_getVal_Exit3 (rc, errno);
    }
  else
    {
      Trc_PRT_shsem_hyshsem_getVal_Exit (rc);
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_setVal
/**
 * 
 * setting the value of the semaphore specified in semset. This function
 * uses no synchronisation prmitives
 * 
 * @pre Caller has to deal with synchronisation issue.
 * 
 * @param[in] portLibrary The port Library.
 * @param[in] handle Semaphore set handle.
 * @param[in] semset The no of semaphore in the semaphore set that you want to post.
 * @param[in] value The value that you want to set the semaphore to
 * 
 * @warning The user will need to make sure locking is done correctly when
 * accessing semaphore values. This is because setValue simply set the semaphore
 * value without stopping the access to the semaphore. Therefore the value of the semaphore
 * can change before the function returns. 
 *
 * @return 0 on success, -1 on failure.
 */
IDATA VMCALL
hyshsem_setVal (struct HyPortLibrary * portLibrary,
                struct hyshsem_handle * handle, UDATA semset, IDATA value)
{
  union semun sem_union;
  IDATA rc;

  Trc_PRT_shsem_hyshsem_setVal_Entry (handle, semset, value);

  if (handle == NULL)
    {
      Trc_PRT_shsem_hyshsem_setVal_Exit1 ();
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }
  if (semset < 0 || semset >= handle->nsems)
    {
      Trc_PRT_shsem_hyshsem_setVal_Exit2 ();
      return HYPORT_ERROR_SHSEM_SEMSET_INVALID;
    }

  sem_union.val = value;

  rc = semctl (handle->semid, semset, SETVAL, sem_union);

  if (-1 == rc)
    {
      Trc_PRT_shsem_hyshsem_setVal_Exit3 (rc, errno);
    }
  else
    {
      Trc_PRT_shsem_hyshsem_setVal_Exit (rc);
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_close
/**
 * Release the resources allocated for the semaphore handles.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] handle Semaphore set handle.
 * 
 * @note The actual semaphore is not destroyed.  Once the close operation has been performed 
 * on the semaphore handle, it is no longer valid and user needs to reissue @ref hyshsem_open call.
 */
void VMCALL
hyshsem_close (struct HyPortLibrary *portLibrary,
               struct hyshsem_handle **handle)
{
  Trc_PRT_shsem_hyshsem_close_Entry (*handle);
  /* On Unix you don't need to close the semaphore handles */
  if (NULL == *handle)
    {
      return;
    }
  portLibrary->mem_free_memory (portLibrary, (*handle)->baseFile);
  portLibrary->mem_free_memory (portLibrary, *handle);
  *handle = NULL;
  Trc_PRT_shsem_hyshsem_close_Exit ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_destroy
/**
 * Destroy the semaphore and release the resources allocated for the semaphore handles.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] handle Semaphore set handle.
 * 
 * @return 0 on success, -1 on failure.
 * @note Due to operating system restriction we may not be able to destroy the semaphore
 */
IDATA VMCALL
hyshsem_destroy (struct HyPortLibrary * portLibrary,
                 struct hyshsem_handle ** handle)
{
  /*pre: user has not closed the handle, and assume user has perm to remove */
  IDATA rc, rcunlink;

  Trc_PRT_shsem_hyshsem_destroy_Entry (*handle);
  if (*handle == NULL)
    {
      return SUCCESS;
    }

  if (semctl ((*handle)->semid, 0, IPC_RMID, 0))
    {
      /* EINVAL is okay - we just had a reboot so the semaphore id is no good */
      if (errno != EINVAL)
        {
          rc = -1;
        }
    }
  else
    {
      rc = 0;
    }

  rcunlink = portLibrary->file_unlink (portLibrary, (*handle)->baseFile);

  Trc_PRT_shsem_hyshsem_destroy_Debug1 ((*handle)->baseFile, rcunlink, errno);

  hyshsem_close (portLibrary, handle);

  if (rc == 0)
    {
      Trc_PRT_shsem_hyshsem_destroy_Exit ();
    }
  else
    {
      Trc_PRT_shsem_hyshsem_destroy_Exit1 ();
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_startup
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
 * \arg HYPORT_ERROR_STARTUP_SHSEM
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyshsem_startup (struct HyPortLibrary * portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyshsem_shutdown
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
hyshsem_shutdown (struct HyPortLibrary *portLibrary)
{
  /* Don't need to do anything for now, but maybe we will need to clean up
   * some directories, etc over here.
   */
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createbaseFile
static IDATA
createbaseFile (struct HyPortLibrary *portLibrary, char *filename)
{
  IDATA fd;
  I_32 flags = HyOpenCreate | HyOpenWrite | HyOpenCreateNew;

  fd =
    portLibrary->file_open (portLibrary, filename, flags, HYSH_BASEFILEPERM);

  if (fd == -1)
    {
      I_32 errorno = portLibrary->error_last_error_number (portLibrary);

      if (HYPORT_ERROR_FILE_EXIST == errorno)
        {
          return errorno;
        }

      return CREATE_ERROR;
    }

  portLibrary->file_close (portLibrary, fd);
  return SUCCESS;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION writebaseFile
static IDATA
writebaseFile (struct HyPortLibrary *portLibrary, char *filename, int proj_id,
               key_t key, int semid, int setSize)
{
  I_32 fd, rc, flags, perm;
  hyshsem_baseFileFormat info;

  flags = HyOpenWrite | HyOpenTruncate;
  perm = HYSH_BASEFILEPERM;

  fd = portLibrary->file_open (portLibrary, filename, flags, perm);
  if (-1 == fd)
    {
      return OPEN_ERROR;
    }

  info.version = HYSH_VERSION;
  info.modlevel = HYSH_MODLEVEL;
  info.proj_id = proj_id;
  info.ftok_key = key;
  info.semid = semid;
  info.creator_pid = portLibrary->sysinfo_get_pid (portLibrary);
  info.semsetSize = setSize;

  rc =
    portLibrary->file_write (portLibrary, fd, &info,
                             sizeof (hyshsem_baseFileFormat));
  if (rc < 0)
    {
      rc = WRITE_ERROR;
    }
  else
    {
      rc = SUCCESS;
    }

  portLibrary->file_close (portLibrary, fd);
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION readbaseFile
static IDATA
readbaseFile (struct HyPortLibrary *portLibrary, char *filename,
              hyshsem_baseFileFormat ** basefileInfo)
{
  I_32 fd, rc;
  hyshsem_baseFileFormat *info;

  fd = portLibrary->file_open (portLibrary, filename, HyOpenRead, 0);
  if (-1 == fd)
    {
      return OPEN_ERROR;
    }

  info =
    portLibrary->mem_allocate_memory (portLibrary,
                                      sizeof (hyshsem_baseFileFormat));
  if (info == NULL)
    {
      /* malloc failure! */
      return MALLOC_ERROR;
    }

  rc =
    portLibrary->file_read (portLibrary, fd, info,
                            sizeof (hyshsem_baseFileFormat));

  if (rc == 0)
    {
      rc = HYSH_NO_DATA;
    }
  else if (rc < 0)
    {
      rc = READ_ERROR;
    }
  else if (rc < sizeof (struct hyshsem_baseFileFormat))
    {
      rc = HYSH_BAD_DATA;
    }
  else
    {
      rc = SUCCESS;
    }

  portLibrary->file_close (portLibrary, fd);

  if (rc != SUCCESS)
    {
      portLibrary->mem_free_memory (portLibrary, info);
      *basefileInfo = NULL;
    }
  else
    {
      *basefileInfo = info;
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createsemHandle
static hyshsem_handle *
createsemHandle (struct HyPortLibrary *portLibrary, int semid, int nsems,
                 char *baseFile)
{
  hyshsem_handle *result;
  IDATA baseFileLength = strlen (baseFile) + 1;

  result =
    portLibrary->mem_allocate_memory (portLibrary, sizeof (hyshsem_handle));
  if (result == NULL)
    {
      return NULL;              /* malloc error! */
    }

  result->semid = semid;
  result->nsems = nsems;

  result->baseFile =
    portLibrary->mem_allocate_memory (portLibrary, baseFileLength);
  if (result->baseFile == NULL)
    {
      portLibrary->mem_free_memory (portLibrary, result);
      return NULL;
    }

  portLibrary->str_printf (portLibrary, result->baseFile, baseFileLength,
                           "%s", baseFile);
  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION setMarker
static I_32
setMarker (hyshsem_handle * handle, int semsetsize)
{
  union semun sem_union;
  if (NULL == handle)
    {
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }

  sem_union.val = SEMMARKER;
  return semctl (handle->semid, semsetsize, SETVAL, sem_union);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION checkMarker
static IDATA
checkMarker (hyshsem_handle * handle, int semsetsize)
{
  IDATA rc;

  if (handle == NULL)
    {
      return HYPORT_ERROR_SHSEM_HANDLE_INVALID;
    }

  rc = semctl (handle->semid, semsetsize, GETVAL);
  if (rc == -1)
    {
      /* can't getval, so return failed */
      return 0;
    }

  return rc == SEMMARKER;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION createSemaphore
static IDATA
createSemaphore (struct HyPortLibrary *portLibrary, char *baseFile,
                 I_32 setSize, hyshsem_handle ** handle)
{
  IDATA rc;
  int projid = 1;
  key_t fkey;
  int semid;
  int semflags =
    portLibrary->portGlobals->control.
    shmem_group_perm ? HYSHSEM_SEMFLAGS_GROUP : HYSHSEM_SEMFLAGS;

  /* base file not exist, so create it now */
  rc = createbaseFile (portLibrary, baseFile);

  /*
   * TODO: Here we are rely on port layer not to mess around with errno
   * we need a proper mechanism to return error code from port layer
   */
  if (HYPORT_ERROR_FILE_EXIST == rc)
    {
      /* creation race condition - need to go back to stat */
      return RETRY;
    }
  else if (rc == CREATE_ERROR)
    {
      return FAILED;
    }

  /*successfully created base file, now create semaphores */

  while (projid < HYSH_MAX_PROJ_ID)
    {
      /* first ftok to get key */
      fkey = ftok (baseFile, projid);

      if (fkey == -1)
        {
          if (errno == ENOENT || errno == ENOTDIR)
            {
              /*
               * race condition: another JVM tries to clean up
               * while we trying to connect to it
               * just return and ask caller to retry
               */
              return RETRY;
            }
          return FAILED;
        }

#if defined(HYSHSEM_DEBUG)
      portLibrary->tty_printf (portLibrary,
                               "creating semaphore, setSize = %d\n",
                               setSize + 1);
#endif

      /* 
       * Notice that we create with setSize+1 - we create an extra semaphore
       * so that we can put a "Marker" in there to say this is one of us 
       */
      semid = semget (fkey, setSize + 1, semflags);

      if (semid == -1)
        {
          if (errno == EEXIST)
            {
              /* EEXIST means that there is a semaphore key that's already being used.
               * It could means that someone has delete the semaphore control file
               * and as a result we couldn't reopen the semaphore
               * We should just bumped the projid, which will give us a different
               * key and we can try to create again.
               */
              projid++;
              continue;
            }

          portLibrary->error_set_last_error (portLibrary, errno,
                                             findError (errno, __errno2 ()));
          return FAILED;
        }
      /* if we get here it means that we have success with getting a semaphore! */
      break;
    }

  if (semid == -1)
    {
      /* 
       * If we get here it means that we have tried to ftok for a while and still can't get a unique id
       * tell user and return
       */
      return FAILED;
    }

  *handle = createsemHandle (portLibrary, semid, setSize, baseFile);
  if (*handle == NULL)
    {
      return FAILED;
    }

  /* set the last value with our marker */
  rc = setMarker (*handle, setSize);

  rc = writebaseFile (portLibrary, baseFile, projid, fkey, semid, setSize);
  if (rc != SUCCESS)
    {
      /*failed to write base file - fatal! */
      return FAILED;
    }

  /* set the timestamp */
  (*handle)->timestamp = portLibrary->file_lastmod (portLibrary, baseFile);

  return SEMAPHORE_CREATED;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION openSemaphore
static IDATA
openSemaphore (struct HyPortLibrary *portLibrary, char *baseFile,
               I_64 timestamp, hyshsem_handle ** handle)
{
  /* base file exist - process the file and get sem info */
  hyshsem_baseFileFormat *info;
  I_8 retrycount = RETRY_COUNT;
  I_32 rc;

  while (retrycount > 0)
    {

      rc = readbaseFile (portLibrary, baseFile, &info);
      switch (rc)
        {
        case OPEN_ERROR:
          return FAILED;
        case READ_ERROR:
          return RETRY;
        case HYSH_BAD_DATA:
          /* basefile info bad! */
          return FAILED;
        case HYSH_NO_DATA:
          /* retry? if file empty */
          usleep (SLEEP_TIME);
          retrycount--;
          break;
        default:
          /*basefile info OK, stop retrying */
          retrycount = 0;
          break;
        }
    }                           /* end while */

  if (info == NULL)
    {
      /*retry already but still can't get data */
      if (portLibrary->file_unlink (portLibrary, baseFile) != 0)
        {
          return FAILED;
        }
      return RETRY;
    }

  /* check that the modlevel and version is okay */
  if (info->version != HYSH_VERSION || info->modlevel != HYSH_MODLEVEL)
    {
      return FAILED;
    }

  *handle =
    createsemHandle (portLibrary, info->semid, info->semsetSize, baseFile);

  (*handle)->timestamp = timestamp;

  /* need to see whether the semid is still valid */
  if (!checkMarker (*handle, info->semsetSize))
    {
      /* we had a reboot, and as a result semaphore ID is not valid */
      /* Just report to the user - we should not try to recover from here */
      rc = HYPORT_INFO_SHSEM_SEMID_DIFF;
    }
  else
    {
      rc = SEMAPHORE_OPENED;
    }

  portLibrary->mem_free_memory (portLibrary, info);
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findError
/**
 * @internal
 * Determines the proper portable error code to return given a native error code
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError (I_32 errorCode, I_32 errorCode2)
{
  switch (errorCode)
    {
    case EPERM:
    case EACCES:
      return HYPORT_ERROR_SHSEM_NOPERMISSION;
    case EEXIST:
      return HYPORT_ERROR_SHSEM_ALREADY_EXIST;
    case ENOMEM:
    case ENOSPC:
    case EMFILE:
      return HYPORT_ERROR_SHSEM_NOSPACE;
    case ENOENT:
    case EINVAL:
    default:
      return HYPORT_ERROR_SHSEM_OPFAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION
