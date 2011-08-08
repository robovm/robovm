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

#include <stdlib.h>
#include "threaddef.h"

#undef  ASSERT
#define ASSERT(x) /**/
  typedef struct RWMutex
{
  hythread_monitor_t syncMon;
  IDATA status;
  hythread_t writer;
} RWMutex;

#define ASSERT_RWMUTEX(m)\
    ASSERT((m));\
    ASSERT((m)->syncMon);

#define RWMUTEX_STATUS_IDLE(m)     ((m)->status == 0)
#define RWMUTEX_STATUS_READING(m)  ((m)->status > 0)
#define RWMUTEX_STATUS_WRITING(m)  ((m)->status < 0)

/**
 * Acquire and initialize a new read/write mutex from the threading library.
 *
 * @param[out] handle pointer to a hythread_rwmutex_t to be set to point to the new mutex
 * @param[in] flags initial flag values for the mutex
 * @return 0 on success, negative value on failure
 * 
 * @see hythread_rwmutex_destroy
 */
IDATA VMCALL
hythread_rwmutex_init (hythread_rwmutex_t * handle, UDATA flags,
                       const char *name)
{
  RWMutex *mutex = (RWMutex *) malloc (sizeof (RWMutex));
  hythread_monitor_init_with_name (&mutex->syncMon, 0, name);
  mutex->status = 0;
  mutex->writer = 0;

  ASSERT (handle);
  *handle = mutex;

  return 0;
}

/**
 * Destroy a read/write mutex.
 * 
 * Destroying a mutex frees the internal resources associated
 * with it.
 *
 * @note A mutex must NOT be destroyed if it is owned 
 * by any threads for either read or write access.
 *
 * @param[in] mutex a mutex to be destroyed
 * @return  0 on success or negative value on failure
 * 
 * @see hythread_rwmutex_init
 */
IDATA VMCALL
hythread_rwmutex_destroy (hythread_rwmutex_t mutex)
{
  ASSERT (mutex);
  ASSERT (mutex->syncMon);
  ASSERT (0 == mutex->status);
  ASSERT (0 == mutex->writer);
  hythread_monitor_destroy (mutex->syncMon);
  free (mutex);
  return 0;
}

/**
 * Enter a read/write mutex as a reader.
 * 
 * A thread may re-enter a mutex it owns multiple times, but
 * must exit the same number of times as a reader 
 * using hythread_rwmutex_exit_read.
 * 
 * A thread with writer access can enter a monitor
 * with reader access, but must exit the mutex in the 
 * opposite order. 
 * 
 * e.g. The following is acceptable
 * hythread_rwmutex_enter_write(mutex);
 * hythread_rwmutex_enter_read(mutex);
 * hythread_rwmutex_exit_read(mutex);
 * hythread_rwmutex_exit_write(mutex);
 * 
 * However, a thread with read access MUST NOT
 * ask for write access on the same mutex. 
 * 
 * @param[in] mutex a mutex to be entered for read access
 * @return 0 on success
 * 
 * @see hythread_rwmutex_exit_read
 */
IDATA VMCALL
hythread_rwmutex_enter_read (hythread_rwmutex_t mutex)
{
  ASSERT_RWMUTEX (mutex);

  if (mutex->writer == hythread_self ())
    {
      hythread_monitor_exit (mutex->syncMon);
      return 0;
    }

  hythread_monitor_enter (mutex->syncMon);

  while (mutex->status < 0)
    {
      hythread_monitor_wait (mutex->syncMon);
    }
  mutex->status++;

  hythread_monitor_exit (mutex->syncMon);
  return 0;

}

/**
 * Exit a read/write mutex as a reader.
 * 
 * @param[in] mutex a mutex to be exited 
 * @return 0 on success
 * 
 * @see hythread_rwmutex_enter_read
 * 
 */
IDATA VMCALL
hythread_rwmutex_exit_read (hythread_rwmutex_t mutex)
{
  ASSERT_RWMUTEX (mon);

  if (mutex->writer == hythread_self ())
    {
      return 0;
    }

  hythread_monitor_enter (mutex->syncMon);

  mutex->status--;
  if (0 == mutex->status)
    {
      hythread_monitor_notify (mutex->syncMon);
    }

  hythread_monitor_exit (mutex->syncMon);

  return 0;

}

/**
 * Enter a read/write mutex as a writer.
 * 
 * A thread may re-enter a mutex it owns multiple times, but
 * must exit the same number of times as a writer 
 * using hythread_rwmutex_exit_write.
 * 
 * A thread with writer access can enter a monitor
 * with reader access, but must exit the mutex in the 
 * opposite order. 
 * 
 * e.g. The following is acceptable
 * hythread_rwmutex_enter_write(mutex);
 * hythread_rwmutex_enter_read(mutex);
 * hythread_rwmutex_exit_read(mutex);
 * hythread_rwmutex_exit_write(mutex);
 * 
 * However, a thread with read access MUST NOT
 * ask for write access on the same mutex. 
 * 
 * @param[in] mutex a mutex to be entered for read access
 * @return 0 on success
 * 
 * @see hythread_rwmutex_exit_write
 */
IDATA VMCALL
hythread_rwmutex_enter_write (hythread_rwmutex_t mutex)
{

  hythread_t self = hythread_self ();
  ASSERT_RWMUTEX (mutex);

  /* recursive? */
  if (mutex->writer == self)
    {
      mutex->status--;
      return 0;
    }

  hythread_monitor_enter (mutex->syncMon);

  while (mutex->status != 0)
    {
      hythread_monitor_wait (mutex->syncMon);
    }
  mutex->status--;
  mutex->writer = self;

  ASSERT (RWMUTEX_STATUS_WRITING (mutex));

  hythread_monitor_exit (mutex->syncMon);

  return 0;

}

/**
 * Exit a read/write mutex as a writer.
 * 
 * @param[in] mutex a mutex to be exited
 * @return 0 on success
 * 
 * @see hythread_rwmutex_enter_write
 * 
 */
IDATA VMCALL
hythread_rwmutex_exit_write (hythread_rwmutex_t mutex)
{
  ASSERT_RWMUTEX (mon);

  ASSERT (mutex->writer == hythread_self ());
  ASSERT (RWMUTEX_STATUS_WRITING (mutex));
  hythread_monitor_enter (mutex->syncMon);

  mutex->status++;
  if (0 == mutex->status)
    {
      mutex->writer = NULL;
      hythread_monitor_notify_all (mutex->syncMon);
    }

  hythread_monitor_exit (mutex->syncMon);
  return 0;

}
