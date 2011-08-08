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

#include <pthread.h>
#include <stdlib.h>
#include "hycomp.h"
#include "hymutex.h"
/* ostypes */

typedef pthread_t OSTHREAD;
typedef pthread_key_t TLSKEY;
typedef pthread_cond_t COND;
#define WRAPPER_TYPE void*
typedef void *WRAPPER_ARG;
#define WRAPPER_RETURN() return NULL
#if defined(LINUX)
#include <semaphore.h>
typedef sem_t OSSEMAPHORE;
#else

typedef IDATA OSSEMAPHORE;

#endif

#include "thrtypes.h"

#if defined(LINUX) && defined(HYX86)
#include <fpu_control.h>
#endif

#if !defined(HY_PRIORITY_MAP)
extern void initialize_priority_map (void);
#endif

int linux_pthread_cond_timedwait
PROTOTYPE ((pthread_cond_t * cond, pthread_mutex_t * mutex,
            const struct timespec * abstime));
IDATA VMCALL hythread_sigthreadmask_sigQuit PROTOTYPE ((void));
IDATA init_thread_library PROTOTYPE ((void));
IDATA VMCALL hythread_signalThread_sigQuit
PROTOTYPE ((hythread_t sigQuitThread));
IDATA VMCALL hythread_sigwait_sigQuit PROTOTYPE ((int *sig));
IDATA nto_cond_init PROTOTYPE ((pthread_cond_t * cond));

IDATA VMCALL sem_getvalue_zos PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_init_zos PROTOTYPE ((hysem_t s, int pShared, int initValue));
IDATA VMCALL sem_trywait_aix PROTOTYPE ((hysem_t s));
void call_hythread_init PROTOTYPE ((void));
IDATA VMCALL sem_wait_zos PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_trywait_zos PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_wait_aix PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_init_aix PROTOTYPE ((hysem_t s, int pShared, int initValue));
IDATA VMCALL sem_post_zos PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_post_aix PROTOTYPE ((hysem_t s));
IDATA VMCALL sem_destroy_aix PROTOTYPE ((hysem_t s));

void VMCALL hythread_init (struct HyThreadLibrary *lib);
void VMCALL hythread_shutdown (void);

struct HyThreadLibrary default_library;

pthread_once_t init_once = PTHREAD_ONCE_INIT;

void
call_hythread_init (void)
{
  hythread_library_t lib = GLOBAL_DATA (default_library);

#if !defined(HY_PRIORITY_MAP)
  initialize_priority_map ();
#endif

  hythread_init (lib);
}

IDATA
init_thread_library (void)
{
  hythread_library_t lib = GLOBAL_DATA (default_library);
  pthread_once (&init_once, call_hythread_init);

  return lib->initStatus != 1;
}

#if defined(LINUX) && defined(HYX86)
int
linux_pthread_cond_timedwait (pthread_cond_t * cond, pthread_mutex_t * mutex,
                              const struct timespec *abstime)
{
  /* This is a wrapper around the pthread_cond_timedwait which restores the
     fpu control word. The libpthread-0.9 version pthread_cond_timedwait on return resets the fpu control word to 0x37f
   */

  int rValue, oldCW;
  _FPU_GETCW (oldCW);
  rValue = pthread_cond_timedwait (cond, mutex, abstime);
  oldCW &= 0xffff;
  _FPU_SETCW (oldCW);
  return rValue;
}
#endif

#include <signal.h>
/* waits for SIGQUIT to arrive
	Used by the dedicated SIGQUIT handler thread
*/
IDATA VMCALL
hythread_sigwait_sigQuit (int *sig)
{

  return 0;
}

/* Block all signals except SIGQUIT 
	Used by the dedicated SIGQUIT handler thread
*/
IDATA VMCALL
hythread_sigthreadmask_sigQuit (void)
{

  return 0;
}

/* 
	Used to send a signal to a specific thread
*/
IDATA VMCALL
hythread_signalThread_sigQuit (hythread_t sigQuitThread)
{

  return 0;
}
