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
 * @brief Shared Resource Mutex
 *
 * The HyIPCMutex is used to protect a shared resource from 
 * simultaneous access by processes or threads executing in 
 * the same or different VMs.
 * Each process/thread must request and wait for the ownership 
 * of the shared resource before it can use that resource. 
 * It must also release the ownership
 * of the resource as soon as it has finished using it so that 
 * other processes competing for the same resource are not delayed.
 */

#undef CDEV_CURRENT_FUNCTION

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <string.h>
#include <errno.h>
#include "hyport.h"

#if defined(_SEM_SEMUN_UNDEFINED) || defined(AIX) || defined(ZOS)
/* arg for semctl semaphore system calls. */
union semun
{
  int val;
  struct semid_ds *buf;
  U_16 *array;
};
#endif

#define CDEV_CURRENT_FUNCTION _prototypes_private

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyipcmutex_acquire
/**
 * Acquires a named mutex for the calling process.
 *
 * If a Mutex with the same Name already exists, the function opens the existing 
 * Mutex and tries to lock it.
 * If another process already has the Mutex locked, the function will block indefinitely. 
 * If there is no Mutex with the same Name, the function will create it and lock
 * it for the calling process of this function.
 *
 * @param[in] portLibrary The port library
 * @param[in] name Mutex to be acquired
 *
 * @return 0 on success, -1 on error.
 *
 * @note The Mutex must be explicitly released by calling the @ref hyipcmutex_release function as 
 * soon as the lock is no longer required.
 */
I_32 VMCALL
hyipcmutex_acquire (struct HyPortLibrary *portLibrary, const char *name)
{
  int sid;
  int nsops;                    /* number of operations to perform on semaphore */
  int nsems = 1;                /* number of semaphores */
  key_t sKey;                   /* semaphore identifier key */
  int nameLen;                  /* length of semaphore name */
  char *sPath;                  /* semaphore path (used in ftok) */
  int sPathLen;                 /* length of semaphore path */
  union semun arg;              /* initialization options structure */
  struct sembuf sLock;          /* operations buffer */
  I_32 mutexFD;                 /* mutex file descriptor */

  nameLen = strlen (name);
  /* check if length of semaphore name is empty */
  if (nameLen == 0)
    {
      return -1;
    }

  /* get size required for semaphore path and name */
  sPathLen = nameLen + sizeof ("/tmp/");

  sPath = portLibrary->mem_allocate_memory (portLibrary, sPathLen);
  if (!sPath)
    {
      return -1;
    }

  /* initialize semaphore path */
  strcpy (sPath, "/tmp/");
  strcat (sPath, name);

  /* create file to be used by semaphore */
  mutexFD =
    portLibrary->file_open (portLibrary, sPath,
                            HyOpenCreate | HyOpenRead | HyOpenWrite, 0666);
  if (mutexFD == -1)
    {
      return -1;
    }

  /* close handle */
  portLibrary->file_close (portLibrary, mutexFD);

  /* build unique semaphore key */
  sKey = ftok (sPath, 's');

  /* free allocated memory no longer needed */
  portLibrary->mem_free_memory (portLibrary, sPath);

  if (sKey == ((key_t) - 1))
    {
      return -1;
    }

  /* check if semaphore already exists */
  sid = semget (sKey, 0, 0666);
  if (sid == -1)
    {
      /* semaphore doesn't exist, create it */
      sid = semget (sKey, nsems, IPC_CREAT | 0666);
      if (sid == -1)
        {
          return -1;
        }

      /* sempahore created, set initial value */
      arg.val = 1;
      if (semctl (sid, 0, SETVAL, arg) == -1)
        {
          semctl (sid, 0, IPC_RMID, arg);       /* cleanup semaphore from system */
          return -1;
        }
    }

  /* initialize operation structure to lock mutex */
  sLock.sem_num = 0;
  sLock.sem_op = -1;
  sLock.sem_flg = 0;

  /* set operation to acquire semaphore */
  nsops = 1;                    /* one operation to be performed (acquire) */

  /* if semaphore was acquired successfully, return 0 */
  /* otherwise, return -1 */
  return (I_32) semop (sid, &sLock, nsops);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyipcmutex_release
/**
 * Releases a named Mutex from the calling process.
 *
 * If a Mutex with the same Name already exists, the function opens the existing Mutex and tries to unlock it.
 * This function will fail if a Mutex with the given Name is not found or if the Mutex cannot be unlocked.
 *
 * @param[in] portLibrary The port library
 * @param[in] name Mutex to be released.
 *
 * @return 0 on success, -1 on error.
 *
 * @note Callers of this function must have called the function @ref hyipcmutex_acquire prior to calling this function.
 */
I_32 VMCALL
hyipcmutex_release (struct HyPortLibrary * portLibrary, const char *name)
{
  int sid;
  int nsops;                    /* number of operations to perform on semaphore */
  key_t sKey = 439;             /* semaphore identifier key */
  int nameLen;                  /* length of semaphore name */
  char *sPath;                  /* semaphore path (used in ftok) */
  int sPathLen;                 /* length of semaphore path */
  struct sembuf sUnlock;        /* operations buffer */

  nameLen = strlen (name);

  /* get size required for semaphore path and name */
  sPathLen = nameLen + sizeof ("/tmp/");

  /* check if length of semaphore name is empty or if it exeeds max size for path */
  if (nameLen == 0)
    {
      return -1;
    }

  sPath = portLibrary->mem_allocate_memory (portLibrary, sPathLen);
  if (!sPath)
    {
      return -1;
    }

  /* build unique semaphore key */
  strcpy (sPath, "/tmp/");
  strcat (sPath, name);
  sKey = ftok (sPath, 's');

  /* free allocated memory no longer needed */
  portLibrary->mem_free_memory (portLibrary, sPath);

  if (sKey == (key_t) - 1)
    {
      return -1;
    }

  /* open existing semaphore */
  sid = semget (sKey, 0, 0666);
  if (sid == -1)
    {
      return -1;
    }

  /* initialize operation structure to unlock mutex */
  sUnlock.sem_num = 0;
  sUnlock.sem_op = 1;
  sUnlock.sem_flg = 0;

  /* set operations to wait and acquire semaphore */
  nsops = 1;                    /* one operation to be performed (release) */

  /* if semaphore was unlocked successfully, return 0 */
  /* otherwise, return -1 */
  return (I_32) semop (sid, &sUnlock, nsops);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyipcmutex_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyipcmutex_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyipcmutex_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyipcmutex_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the IPC mutex operations may be created here.  All resources created here should be destroyed
 * in @ref hyipcmutex_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_IPCMUTEX
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyipcmutex_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION

#undef CDEV_CURRENT_FUNCTION
