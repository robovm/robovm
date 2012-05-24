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
 * @ingroup Thread
 * @brief Threading and synchronization support
 */

#include "threaddef.h"
#include <stdlib.h>

#define CDEV_CURRENT_FUNCTION _prototypes_private
static hythread_t allocate_thread PROTOTYPE ((int globalIsLocked));
static void free_thread
PROTOTYPE ((hythread_t thread, int globalAlreadyLocked));
static IDATA interrupt_waiting_thread
PROTOTYPE ((hythread_t self, hythread_t threadToInterrupt));
static void remove_from_queue
PROTOTYPE ((hythread_t volatile *queue, hythread_t thread));
static IDATA destroy_thread
PROTOTYPE ((hythread_t thread, int globalAlreadyLocked));
void VMCALL hythread_shutdown PROTOTYPE ((void));
static void HYTHREAD_PROC tls_null_finalizer PROTOTYPE ((void *entry));
static I_32 HYTHREAD_PROC interruptServer PROTOTYPE ((void *entryArg));
static hythread_monitor_t VMCALL hythread_monitor_acquire
PROTOTYPE ((hythread_t self, IDATA policy, IDATA policyData));
void VMCALL hythread_init PROTOTYPE ((hythread_library_t lib));
static IDATA init_global_monitor PROTOTYPE ((hythread_library_t lib));
static void tls_finalize PROTOTYPE ((hythread_t thread));
static void free_monitor_pools PROTOTYPE ((void));
static void *VMCALL thread_malloc PROTOTYPE ((void *unused, U_32 size));
static void NORETURN internal_exit PROTOTYPE ((void));
static IDATA monitor_wait
PROTOTYPE ((hythread_monitor_t monitor, I_64 millis, IDATA nanos,
            IDATA interruptable));
static IDATA monitor_enter
PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));
static UDATA init_monitor
PROTOTYPE ((hythread_monitor_t monitor, UDATA flags));

static IDATA monitor_enter_three_tier
PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));

static hytime_t getCurrentCycles PROTOTYPE ((void));
static hythread_monitor_pool_t allocate_monitor_pool PROTOTYPE ((void));
static IDATA create_thread
PROTOTYPE ((hythread_t * handle, UDATA stacksize, UDATA priority,
            UDATA suspend, hythread_entrypoint_t entrypoint, void *entryarg,
            int globalIsLocked));
static void interrupt_thread
PROTOTYPE ((hythread_t thread, UDATA interruptFlag));

static void unblock_spinlock_threads
PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));

static void notify_thread
PROTOTYPE ((hythread_t threadToNotify, int setNotifiedFlag));
static IDATA monitor_exit
PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));
static WRAPPER_TYPE thread_wrapper PROTOTYPE ((WRAPPER_ARG arg));
static void VMCALL thread_free PROTOTYPE ((void *unused, void *ptr));
static void enqueue_thread
PROTOTYPE ((hythread_t * queue, hythread_t thread));
static IDATA monitor_notify_one_or_all
PROTOTYPE ((hythread_monitor_t monitor, int notifyall));

#undef CDEV_CURRENT_FUNCTION

#if defined(THREAD_ASSERTS)
/*
 * Helper variable for asserts.
 * We use this to keep track of when the global lock is owned.
 * We don't want to do a re-entrant enter on the global lock 
 */
hythread_t global_lock_owner = UNOWNED;
#endif

#define BOUNDED_I64_TO_IDATA(longValue) ((longValue) > 0x7FFFFFFF ? 0x7FFFFFFF : (IDATA)(longValue))

#define HYTHREAD_MAX_TLS_KEYS (sizeof( ((HyThreadLibrary*)NULL)->tls_finalizers ) / sizeof( ((HyThreadLibrary*)NULL)->tls_finalizers[0] ))

#define CDEV_CURRENT_FUNCTION hythread_init
/**
 * Initialize a Hy threading library.
 * 
 * @note This must only be called once.
 * 
 * If any OS threads were created before calling this function, they must be attached using
 * hythread_attach before accessing any Hy thread library functions. 
 * 
 * @param[in] lib pointer to the Hy thread library to be initialized (non-NULL)
 * @return The Hy thread library's initStatus will be set to 0 on success or 
 * a negative value on failure.
 * 
 * @see hythread_attach, hythread_shutdown
 */
void VMCALL
hythread_init (hythread_library_t lib)
{
  ASSERT (lib);

  lib->spinlock = 0;
  lib->threadCount = 0;
  lib->globals = NULL;
  lib->stack_usage = 0;

  /* set all TLS finalizers to NULL. This indicates that the key is unused */
  memset (lib->tls_finalizers, 0, sizeof (lib->tls_finalizers));

  STATIC_ASSERT (CALLER_LAST_INDEX <= MAX_CALLER_INDEX);

  if (TLS_ALLOC (lib->self_ptr))
    goto init_cleanup1;

  lib->monitor_pool = allocate_monitor_pool ();
  if (lib->monitor_pool == NULL)
    goto init_cleanup2;

  if (!MUTEX_INIT (lib->monitor_mutex))
    goto init_cleanup3;
  if (!MUTEX_INIT (lib->tls_mutex))
    goto init_cleanup4;
  if (!MUTEX_INIT (lib->global_mutex))
    goto init_cleanup5;

  lib->thread_pool =
    pool_new (sizeof (HyThread), 0, 0, 0, thread_malloc, thread_free, NULL);
  if (lib->thread_pool == NULL)
    goto init_cleanup6;

  lib->global_pool =
    pool_new (sizeof (HyThreadGlobal), 0, 0, 0, thread_malloc, thread_free,
              NULL);
  if (lib->global_pool == NULL)
    goto init_cleanup7;

  if (init_global_monitor (lib))
    goto init_cleanup8;

  lib->initStatus = 1;
  return;

init_cleanup8:pool_kill (lib->global_pool);
init_cleanup7:pool_kill (lib->thread_pool);
init_cleanup6:MUTEX_DESTROY (lib->global_mutex);
init_cleanup5:MUTEX_DESTROY (lib->tls_mutex);
init_cleanup4:MUTEX_DESTROY (lib->monitor_mutex);
init_cleanup3:free_monitor_pools ();
init_cleanup2:TLS_DESTROY (lib->self_ptr);
init_cleanup1:lib->initStatus = -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_self
/** 
 * Return the hythread_t for the current thread.
 * 
 * @note Must be called only by an attached thread
 * 
 * @return hythread_t for the current thread
 *
 * @see hythread_attach
 * 
 */
hythread_t VMCALL
hythread_self (void)
{
  return MACRO_SELF ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_create
/**
 * Create a new OS thread.
 * 
 * The created thread is attached to the threading library.<br>
 * <br>
 * Unlike POSIX, this doesn't require an attributes structure.
 * Instead, any interesting attributes (e.g. stacksize) are
 * passed in with the arguments.
 *
 * @param[out] handle a pointer to a hythread_t which will point to the thread (if successfully created)
 * @param[in] stacksize the size of the new thread's stack (bytes)<br>
 *			0 indicates use default size
 * @param[in] priority priorities range from HYTHREAD_PRIORITY_MIN to HYTHREAD_PRIORITY_MAX (inclusive)
 * @param[in] suspend set to non-zero to create the thread in a suspended state.
 * @param[in] entrypoint pointer to the function which the thread will run
 * @param[in] entryarg a value to pass to the entrypoint function
 *
 * @return  0 on success or negative value on failure
 *
 * @see hythread_exit, hythread_resume
 */
IDATA VMCALL
hythread_create (hythread_t * handle, UDATA stacksize, UDATA priority,
                 UDATA suspend, hythread_entrypoint_t entrypoint,
                 void *entryarg)
{
  return create_thread (handle, stacksize, priority, suspend, entrypoint,
                        entryarg, GLOBAL_NOT_LOCKED);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION create_thread
/*
 * Create a new OS thread and attach it.
 */
static IDATA
create_thread (hythread_t * handle, UDATA stacksize, UDATA priority,
               UDATA suspend, hythread_entrypoint_t entrypoint,
               void *entryarg, int globalIsLocked)
{
  hythread_t thread;
  hythread_library_t lib = GLOBAL_DATA (default_library);

  ASSERT (lib->initStatus);

  if (priority > HYTHREAD_PRIORITY_MAX)
    {
      goto cleanup0;
    }

  if (stacksize == 0)
    {
      stacksize = STACK_DEFAULT_SIZE;
    }

  thread = allocate_thread (globalIsLocked);
  if (!thread)
    {
      goto cleanup0;
    }
  if (handle)
    {
      *handle = thread;
    }
  thread->library = lib;
  thread->priority = priority;
  thread->attachcount = 0;
  thread->stacksize = stacksize;

  memset (thread->tls, 0, sizeof (thread->tls));

  thread->interrupter = NULL;

  if (!COND_INIT (thread->condition))
    {
      goto cleanup1;
    }
  if (!MUTEX_INIT (thread->mutex))
    {
      goto cleanup2;
    }

  thread->flags = suspend ? HYTHREAD_FLAG_SUSPENDED : 0;
  thread->entrypoint = entrypoint;
  thread->entryarg = entryarg;

  if (IS_JLM_ENABLED (thread))
    {
      hythread_jlm_thread_init (thread);
    }

#if defined(LINUX)
  thread->jumpBuffer = NULL;
#endif

  if (!THREAD_CREATE (thread, stacksize, priority, thread_wrapper, thread))
    {
      goto cleanup3;
    }

  return 0;

/* Cleanup points */
cleanup3:MUTEX_DESTROY (thread->mutex);
cleanup2:COND_DESTROY (thread->condition);
cleanup1:free_thread (thread, GLOBAL_NOT_LOCKED);
cleanup0:if (handle)
    *handle = NULL;
  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_attach
/**
 * Attach an OS thread to the threading library.
 * 
 * Create a new hythread_t to represent the existing OS thread.
 * Attaching a thread is required when a thread was created 
 * outside of the Hy threading library wants to use any of the 
 * Hy threading library functionality.
 *
 * If the OS thread is already attached, handle is set to point 
 * to the existing hythread_t.
 *
 * @param[out] handle pointer to a hythread_t to be set (will be ignored if null)
 * @return  0 on success or negative value on failure
 *
 * @see hythread_detach
 */
IDATA VMCALL
hythread_attach (hythread_t * handle)
{
  hythread_t thread;

  if (init_thread_library ())
    {
      goto cleanup0;
    }

  if ((thread = MACRO_SELF ()) != NULL)
    {
      if (handle)
        {
          *handle = thread;
        }
      THREAD_LOCK (thread, thread, CALLER_ATTACH);
      thread->attachcount++;
      THREAD_UNLOCK (thread, thread);
      return 0;
    }

  thread = allocate_thread (GLOBAL_NOT_LOCKED);
  if (!thread)
    {
      goto cleanup0;
    }

  thread->library = GLOBAL_DATA (default_library);
  thread->attachcount = 1;
  thread->priority = HYTHREAD_PRIORITY_NORMAL;
  thread->flags = HYTHREAD_FLAG_ATTACHED;

  if (!COND_INIT (thread->condition))
    {
      goto cleanup1;
    }

  if (!MUTEX_INIT (thread->mutex))
    {
      goto cleanup2;
    }

#if defined(WIN32)
  {
    DuplicateHandle (GetCurrentProcess (), GetCurrentThread (),
                     GetCurrentProcess (), &thread->handle, 0, TRUE,
                     DUPLICATE_SAME_ACCESS);
  }
#else
  thread->handle = THREAD_SELF ();
#endif

  initialize_thread_priority (thread);

  TLS_SET (thread->library->self_ptr, thread);

  thread->tid = RAS_THREAD_ID ();

  if (handle)
    {
      *handle = thread;
    }
  return 0;

/* failure points */
cleanup2:COND_DESTROY (thread->condition);
cleanup1:free_thread (thread, GLOBAL_NOT_LOCKED);
cleanup0:return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_suspend
/**
 * Suspend the current thread. 
 * 
 * Stop the current thread from executing until it is resumed.
 * 
 * @return none
 *
 * @see hythread_resume
 */
void VMCALL
hythread_suspend (void)
{
  hythread_t self = MACRO_SELF ();
  ASSERT (self);

  THREAD_LOCK (self, self, CALLER_SUSPEND);
  self->flags |= HYTHREAD_FLAG_SUSPENDED;

  COND_WAIT (self->condition, self->mutex);
  if ((self->flags & HYTHREAD_FLAG_SUSPENDED) == 0)
    break;
  COND_WAIT_LOOP ();

  THREAD_UNLOCK (self, self);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_resume
/**
 * Resume a thread. 
 *
 * Take a threads out of the suspended state. 
 * 
 * If the thread is not suspended, no action is taken.
 *
 * @param[in] thread a thread to be resumed
 * @return none
 * 
 * @see hythread_create, hythread_suspend
 */
void VMCALL
hythread_resume (hythread_t thread)
{
  hythread_t self;

  ASSERT (thread);

  if ((thread->flags & HYTHREAD_FLAG_SUSPENDED) == 0)
    {
      /* it wasn't suspended! */
      return;
    }

  self = MACRO_SELF ();
  ASSERT (self);

  THREAD_LOCK (self, thread, CALLER_RESUME);

  /* 
   * The thread _should_ only be OS suspended once, but
   * handle the case where it's suspended more than once anyway.
   */
  COND_NOTIFY_ALL (thread->condition);
  thread->flags &= ~HYTHREAD_FLAG_SUSPENDED;

  THREAD_UNLOCK (self, thread);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_yield
/** 
 * Yield the processor.
 * 
 * @return none
 */
void VMCALL
hythread_yield (void)
{
  THREAD_YIELD ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_init
/*
 * Acquire and initialize a new monitor from the threading library.
 *
 * @param[out] handle pointer to a hythread_monitor_t to be set to point to the new monitor
 * @param[in] flags initial flag values for the monitor
 * @return 0 on success, negative value on failure
 * 
 * @deprecated This has been replaced by hythread_monitor_init_with_name
 * @see hythread_monitor_init_with_name
 */
IDATA VMCALL
hythread_monitor_init (hythread_monitor_t * handle, UDATA flags)
{
  IDATA rc;

  /* Initialize monitor with default locking policy */
  rc =
    hythread_monitor_init_policy (handle, flags, HYTHREAD_LOCKING_DEFAULT,
                                  HYTHREAD_LOCKING_NO_DATA);
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_init_policy
/*
 * Acquire and initialize a new monitor with given locking policy from the threading library.
 *
 * @param[out] handle pointer to a hythread_monitor_t to be set to point to the new monitor
 * @param[in] flags initial flag values for the monitor
 * @param[in] locking policy for the monitor 
 * @param[in] data associated with locking policy or HYTHREAD_LOCKING_NO_DATA
 * @return 0 on success, negative value on failure
 * 
 */
IDATA VMCALL
hythread_monitor_init_policy (hythread_monitor_t * handle, UDATA flags,
                              IDATA policy, IDATA policyData)
{
  hythread_monitor_t monitor;

  hythread_t self = MACRO_SELF ();
  ASSERT (self);
  ASSERT (handle);

  monitor = hythread_monitor_acquire (self, policy, policyData);
  if (NULL == monitor)
    {
      return -1;
    }

  if (init_monitor (monitor, flags) != 0)
    {
      return -1;
    }

  if (IS_JLM_ENABLED (self))
    {
      hythread_jlm_monitor_init (monitor);
    }

  *handle = monitor;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_destroy
/**
 * Destroy a monitor.
 * 
 * Destroying a monitor frees the internal resources associated
 * with it.
 *
 * @note A monitor must NOT be destroyed if threads are waiting on
 * it, or if it is currently owned.
 *
 * @param[in] monitor a monitor to be destroyed
 * @return  0 on success or non-0 on failure (the monitor is in use)
 * 
 * @see hythread_monitor_init_with_name
 */
IDATA VMCALL
hythread_monitor_destroy (hythread_monitor_t monitor)
{
  hythread_t self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (monitor);

  GLOBAL_LOCK (self, CALLER_MONITOR_DESTROY);

  if (monitor->owner || monitor->waiting)
    {
      /* This monitor is in use! It was probably abandoned when a thread was cancelled.
       * There's actually a very small timing hole here -- if the thread had just locked the 
       * mutex and not yet set the owner field when it was cancelled, we have no way of
       * knowing that the mutex may be in an invalid state. The same thing can happen
       * if the thread has just cleared the field and is about to unlock the mutex.
       * Hopefully the OS takes care of this for us, but it might not.
       */
      GLOBAL_UNLOCK (self);
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  monitor->owner = (hythread_t) self->library->monitor_pool->next_free;
  monitor->count = FREE_TAG;
  monitor->userData = 0;
  self->library->monitor_pool->next_free = monitor;

  GLOBAL_UNLOCK (self);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_enter
/**
 * Enter a monitor.
 * 
 * A thread may re-enter a monitor it owns multiple times, but must
 * exit the monitor the same number of times before any other thread
 * wanting to enter the monitor is permitted to continue.
 *
 * @param[in] monitor a monitor to be entered
 * @return 0 on success<br>
 * HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority interrupted while blocked
 * 
 * @see hythread_monitor_enter_using_threadId, hythread_monitor_exit, hythread_monitor_exit_using_threadId
 */
IDATA VMCALL
hythread_monitor_enter (hythread_monitor_t monitor)
{
  hythread_t self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (monitor);
  ASSERT (FREE_TAG != monitor->count);

  if (monitor->owner == self)
    {
      ASSERT (monitor->count >= 1);
      monitor->count++;

      if (IS_JLM_ENABLED (self))
        {
          ASSERT (monitor->tracing);
          monitor->tracing->recursive_count++;
          monitor->tracing->enter_count++;
        }                       /* if (IS_JLM_ENABLED(self)) */

      return 0;
    }

  return monitor_enter (self, monitor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_exit
/**
 * Exit a monitor.
 * 
 * Exit a monitor, and if the owning count is zero, release it.
 *
 * @param[in] monitor a monitor to be exited
 * @return 0 on success, <br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 * 
 * @see hythread_monitor_exit_using_threadId, hythread_monitor_enter, hythread_monitor_enter_using_threadId
 */
IDATA VMCALL
hythread_monitor_exit (hythread_monitor_t monitor)
{
  hythread_t self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (monitor);
  ASSERT (FREE_TAG != monitor->count);

  if (monitor->owner != self)
    {
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  return monitor_exit (self, monitor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_wait
/** 
 * Wait on a monitor until notified.
 *
 * Release the monitor, wait for a signal (notification), then re-acquire the monitor.
 * 
 * @param[in] monitor a monitor to be waited on
 * @return 0 if the monitor has been waited on, notified, and reobtained<br>
 * HYTHREAD_INVALID_ARGUMENT if millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 * 
 * @see hythread_monitor_wait_interruptable, hythread_monitor_wait_timed, hythread_monitor_enter
 * 
 */
IDATA VMCALL
hythread_monitor_wait (hythread_monitor_t monitor)
{
  return monitor_wait (monitor, 0, 0, WAIT_UNINTERRUPTABLE);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_notify
/**
 * Notify a single thread waiting on a monitor.
 * 
 * A thread is considered to be waiting on the monitor if 
 * it is currently blocked while executing hythread_monitor_wait on the monitor.
 * 
 * If no threads are waiting, no action is taken.
 *
 * @param[in] monitor a monitor to be signaled
 * @return  0 once the monitor has been signaled<br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 * 
 * @see hythread_monitor_notify_all, hythread_monitor_enter, hythread_monitor_wait
 */
IDATA VMCALL
hythread_monitor_notify (hythread_monitor_t monitor)
{
  return monitor_notify_one_or_all (monitor, NOTIFY_ONE);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_notify_all
/**
 * Notify all threads waiting on a monitor.
 * 
 * A thread is considered to be waiting on the monitor if 
 * it is currently blocked while executing hythread_monitor_wait on the monitor.
 * 
 * If no threads are waiting, no action is taken.
 *
 *
 * @param[in] monitor a monitor to be signaled
 * @return  0 once the monitor has been signaled<br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 * 
 * @see hythread_monitor_notify, hythread_monitor_enter, hythread_monitor_wait
 */
IDATA VMCALL
hythread_monitor_notify_all (hythread_monitor_t monitor)
{
  return monitor_notify_one_or_all (monitor, NOTIFY_ALL);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_tls_alloc
/**
 * Allocate a thread local storage (TLS) key.
 * 
 * Create and return a new, unique key for thread local storage.  
 * 
 * @note The hande returned will be >=0, so it is safe to test the handle against 0 to see if it's been
 * allocated yet.
 * 
 * @param[out] handle pointer to a key to be initialized with a key value
 * @return 0 on success or negative value if a key could not be allocated (i.e. all TLS has been allocated)
 * 
 * @see hythread_tls_free, hythread_tls_set
 */
IDATA VMCALL
hythread_tls_alloc (hythread_tls_key_t * handle)
{
  return hythread_tls_alloc_with_finalizer (handle, tls_null_finalizer);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_tls_free
/**
 * Release a TLS key.
 * 
 * Release a TLS key previously allocated by hythread_tls_alloc.
 * 
 * @param[in] key TLS key to be freed
 * @return 0 on success or negative value on failure
 *
 * @see hythread_tls_alloc, hythread_tls_set
 *
 */
IDATA VMCALL
hythread_tls_free (hythread_tls_key_t key)
{
  HyPoolState state;
  hythread_t each;
  hythread_library_t lib = GLOBAL_DATA (default_library);
  ASSERT (lib);

  /* clear the TLS in every existing thread */
  GLOBAL_LOCK_SIMPLE (lib);
  each = pool_startDo (lib->thread_pool, &state);
  while (each)
    {
      each->tls[key - 1] = NULL;
      each = pool_nextDo (&state);
    }
  GLOBAL_UNLOCK_SIMPLE (lib);

  /* now return the key to the free set */
  MUTEX_ENTER (lib->tls_mutex);
  lib->tls_finalizers[key - 1] = NULL;
  MUTEX_EXIT (lib->tls_mutex);

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_tls_set
/**
 * Set a thread's TLS value.
 *
 * @param[in] thread a thread 
 * @param[in] key key to have TLS value set (any value returned by hythread_alloc)
 * @param[in] value value to be stored in TLS
 * @return 0 on success or negative value on failure
 *  
 * @see hythread_tls_alloc, hythread_tls_free, hythread_tls_get
 */
IDATA VMCALL
hythread_tls_set (hythread_t thread, hythread_tls_key_t key, void *value)
{
  thread->tls[key - 1] = value;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_set_priority
/**
 * Set a thread's execution priority.
 * 
 * @param[in] thread a thread
 * @param[in] priority
 * Use the following symbolic constants for priorities:<br>
 *				HYTHREAD_PRIORITY_MAX<br>
 *				HYTHREAD_PRIORITY_USER_MAX<br>
 *				HYTHREAD_PRIORITY_NORMAL<br>
 *				HYTHREAD_PRIORITY_USER_MIN<br>
 *				HYTHREAD_PRIORITY_MIN<br>
 * 
 * @return 0 on success or negative value on failure (priority wasn't changed)
 * 
 * 
 */
IDATA VMCALL
hythread_set_priority (hythread_t thread, UDATA priority)
{
  ASSERT (thread);

  if (priority > HYTHREAD_PRIORITY_MAX)
    {
      return -1;
    }

  if (THREAD_SET_PRIORITY (thread->handle, priority_map[priority]))
    {
      return -1;
    }

  thread->priority = priority;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_interrupt
/** 
 * Interrupt a thread.
 * 
 * If the thread is currently blocked (i.e. waiting on a monitor_wait or sleeping)
 * resume the thread and cause it to return from the blocking function with
 * HYTHREAD_INTERRUPTED.
 * 
 * @param[in] thread a thead to be interrupted
 * @return none
 */
void VMCALL
hythread_interrupt (hythread_t thread)
{
  interrupt_thread (thread, HYTHREAD_FLAG_INTERRUPTED);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_interrupted
/**
 * Return the value of a thread's interrupted flag.
 * 
 * @param[in] thread thread to be queried
 * @return 0 if not interrupted, non-zero if interrupted
 */
UDATA VMCALL
hythread_interrupted (hythread_t thread)
{
  ASSERT (thread);
  return (thread->flags & HYTHREAD_FLAG_INTERRUPTED) != 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_clear_interrupted
/**
 * Clear the interrupted flag of the current thread and return its previous value.
 * 
 * @return  previous value of interrupted flag: non-zero if the thread had been interrupted.
 */
UDATA VMCALL
hythread_clear_interrupted (void)
{
  UDATA oldFlags;
  hythread_t self = MACRO_SELF ();
  ASSERT (self);

  THREAD_LOCK (self, self, CALLER_CLEAR_INTERRUPTED);
  oldFlags = self->flags;
  self->flags = oldFlags & ~HYTHREAD_FLAG_INTERRUPTED;
  THREAD_UNLOCK (self, self);

  return (oldFlags & HYTHREAD_FLAG_INTERRUPTED) != 0;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION remove_from_queue
/*
 * Remove a thread from a monitor's queue.
 * 
 * @param[in] queue head of a monitor's queue
 * @param[in] thread thread to be removed from queue
 * @return none
 */
static void
remove_from_queue (hythread_t volatile *queue, hythread_t thread)
{
  hythread_t queued, next;

  ASSERT (thread);

  if ((queued = *queue) == NULL)
    return;

  if (queued == thread)
    {
      *queue = thread->next;
      thread->next = NULL;
    }
  else
    {
      while ((next = queued->next) != NULL && next != thread)
        {
          queued = next;
        }
      if (next != NULL)
        {
          queued->next = thread->next;
          thread->next = NULL;
        }
    }

  ASSERT (NULL == thread->next);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION allocate_monitor_pool
/*
 * Create and initialize a pool of monitors.
 * 
 * @return pointer to a new monitor pool on success, NULL on failure
 * 
 */
static hythread_monitor_pool_t
allocate_monitor_pool (void)
{
  int i;
  hythread_monitor_t entry;
  hythread_monitor_pool_t pool =
    (hythread_monitor_pool_t) malloc (sizeof (HyThreadMonitorPool));
  if (pool == NULL)
    {
      return NULL;
    }
  memset (pool, 0, sizeof (HyThreadMonitorPool));

  pool->next_free = entry = &pool->entries[0];
  for (i = 0; i < MONITOR_POOL_SIZE - 1; i++, entry++)
    {
      entry->count = FREE_TAG;
      entry->owner = (hythread_t) (entry + 1);
      /* entry->waiting = entry->blocked = NULL; *//* (unnecessary) */
      entry->flags = HYTHREAD_MONITOR_MUTEX_UNINITIALIZED;
    }
  /* initialize the last monitor */
  entry->count = FREE_TAG;
  entry->flags = HYTHREAD_MONITOR_MUTEX_UNINITIALIZED;

  return pool;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION thread_wrapper
/*
 * Function we pass to the OS for new threads.
 * 
 * @param arg pointer to the hythread_t for the new thread
 * @return none
 */
static WRAPPER_TYPE
thread_wrapper (WRAPPER_ARG arg)
{
  hythread_t thread = (hythread_t) arg;
  hythread_library_t lib = thread->library;
  UDATA flags;

  ASSERT (thread);
  ASSERT (lib);

  thread->tid = RAS_THREAD_ID ();

  TLS_SET (lib->self_ptr, thread);

  if (lib->stack_usage)
    {
      paint_stack (thread);
    }

  if (thread->flags & HYTHREAD_FLAG_CANCELED)
    {
      internal_exit ();
    }

  /* Handle the create-suspended case */
  /* (This code is basically the same as hythread_suspend, but we need to
     test the condition under mutex or else there's a timing hole) */
  THREAD_LOCK (thread, thread, CALLER_THREAD_WRAPPER);
  if (thread->flags & HYTHREAD_FLAG_SUSPENDED)
    {
      COND_WAIT (thread->condition, thread->mutex);
      if ((thread->flags & HYTHREAD_FLAG_SUSPENDED) == 0)
        break;
      COND_WAIT_LOOP ();
    }
  thread->flags |= HYTHREAD_FLAG_STARTED;
  flags = thread->flags;
  THREAD_UNLOCK (thread, thread);

  if (thread->flags & HYTHREAD_FLAG_CANCELED)
    {
      internal_exit ();
    }

#if defined(LINUX)
  /* Workaround for NPTL bug on Linux. See hythread_exit() */
  {
    jmp_buf jumpBuffer;
    if (0 == setjmp (jumpBuffer))
      {
        thread->jumpBuffer = &jumpBuffer;
        thread->entrypoint (thread->entryarg);
      }
    thread->jumpBuffer = NULL;
  }
#else
  thread->entrypoint (thread->entryarg);
#endif

  internal_exit ();
  /* UNREACHABLE */
  WRAPPER_RETURN ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION monitor_notify_one_or_all
/*
 * Signal one or all threads waiting on the monitor.
 * 
 * If no threads are waiting, this does nothing.
 * 
 * @param[in] monitor monitor to be notified on
 * @param[in] notifyall 0 to notify one, non-zero to notify all
 * @return 0 once the monitor has been signalled<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not 
 * own the monitor
 * 
 */
static IDATA
monitor_notify_one_or_all (hythread_monitor_t monitor, int notifyall)
{

  hythread_t self = MACRO_SELF ();
  hythread_t queue, next;
  int someoneNotified = 0;

  ASSERT (self);
  ASSERT (monitor);

  if (monitor->owner != self)
    {
      ASSERT_DEBUG (0);
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  MONITOR_LOCK (self, monitor, CALLER_NOTIFY_ONE_OR_ALL);

  next = monitor->waiting;

  while (next)
    {
      queue = next;
      next = queue->next;
      THREAD_LOCK (self, queue, CALLER_NOTIFY_ONE_OR_ALL);
      if (queue->flags & HYTHREAD_FLAG_WAITING)
        {
          notify_thread (queue, SET_NOTIFIED_FLAG);
          someoneNotified = 1;
        }
      THREAD_UNLOCK (self, queue);

      if ((someoneNotified) && (!notifyall))
        {
          break;
        }
    }

  MONITOR_UNLOCK (self, monitor);

  return 0;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION notify_thread
/*
 * Notify a thread.
 * 
 * Helper routine because we notify a thread in 
 * a couple of places.
 * @note: assumes the caller has THREAD_LOCK'd the 
 * thread being notified (and owns the monitor being notified on)
 * @param[in] threadToNotify thread to notify
 * @param[in] setNotifiedFlag indicates whether to set the notified thread's notified flag.
 * @return none
 */
static void
notify_thread (hythread_t threadToNotify, int setNotifiedFlag)
{
  ASSERT (threadToNotify);
  ASSERT (threadToNotify->flags & HYTHREAD_FLAG_WAITING);

  threadToNotify->flags &= ~HYTHREAD_FLAG_WAITING;
  threadToNotify->flags |= HYTHREAD_FLAG_BLOCKED;
  if (setNotifiedFlag)
    {
      threadToNotify->flags |= HYTHREAD_FLAG_NOTIFIED;
    }
  COND_NOTIFY_ALL (threadToNotify->condition);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_sleep_interruptable
/** 
 * Suspend the current thread from executing 
 * for at least the specified time.
 *
 * @param[in] millis
 * @param[in] nanos 
 * @return  0 on success<br>
 *    HYTHREAD_INVALID_ARGUMENT if the arguments are invalid<br>
 *    HYTHREAD_INTERRUPTED if the sleep was interrupted
 *
 * @see hythread_sleep
 */
IDATA VMCALL
hythread_sleep_interruptable (I_64 millis, IDATA nanos)
{
  hythread_t self = MACRO_SELF ();
  IDATA boundedMillis = BOUNDED_I64_TO_IDATA (millis);

  ASSERT (self);

  if ((millis < 0) || (nanos < 0) || (nanos >= 1000000))
    {
      return HYTHREAD_INVALID_ARGUMENT;
    }

  THREAD_LOCK (self, self, CALLER_SLEEP_INTERRUPTABLE);

  if (self->flags & HYTHREAD_FLAG_INTERRUPTED)
    {
      self->flags &= ~HYTHREAD_FLAG_INTERRUPTED;
      THREAD_UNLOCK (self, self);
      return HYTHREAD_INTERRUPTED;
    }

  if (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED)
    {
      self->flags &= ~HYTHREAD_FLAG_PRIORITY_INTERRUPTED;
      THREAD_UNLOCK (self, self);
      return HYTHREAD_PRIORITY_INTERRUPTED;
    }

  self->flags |=
    HYTHREAD_FLAG_SLEEPING | HYTHREAD_FLAG_INTERRUPTABLE |
    HYTHREAD_FLAG_TIMER_SET;

  COND_WAIT_IF_TIMEDOUT (self->condition, self->mutex, boundedMillis, nanos)
  {
    break;
  }
  else
  {
    if (self->flags & HYTHREAD_FLAG_INTERRUPTED)
      {
        self->flags &=
          ~(HYTHREAD_FLAG_INTERRUPTED | HYTHREAD_FLAG_SLEEPING |
            HYTHREAD_FLAG_INTERRUPTABLE | HYTHREAD_FLAG_TIMER_SET);
        THREAD_UNLOCK (self, self);
        return HYTHREAD_INTERRUPTED;
      }
    if (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED)
      {
        self->flags &=
          ~(HYTHREAD_FLAG_PRIORITY_INTERRUPTED | HYTHREAD_FLAG_SLEEPING |
            HYTHREAD_FLAG_INTERRUPTABLE | HYTHREAD_FLAG_TIMER_SET);
        THREAD_UNLOCK (self, self);
        return HYTHREAD_PRIORITY_INTERRUPTED;
      }
  }
  COND_WAIT_TIMED_LOOP ();

  self->flags &=
    ~(HYTHREAD_FLAG_SLEEPING | HYTHREAD_FLAG_INTERRUPTABLE |
      HYTHREAD_FLAG_TIMER_SET);
  THREAD_UNLOCK (self, self);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_sleep
/** 
 * Suspend the current thread from executing 
 * for at least the specified time.
 *
 * @param[in] millis minimum number of milliseconds to sleep
 * @return  0 on success<br> HYTHREAD_INVALID_ARGUMENT if millis < 0
 *
 * @see hythread_sleep_interruptable
 */
IDATA VMCALL
hythread_sleep (I_64 millis)
{
  hythread_t self = MACRO_SELF ();

  IDATA boundedMillis = (millis > 0x7FFFFFFF ? 0x7FFFFFFF : (IDATA) millis);

  ASSERT (self);

  if (millis < 0)
    {
      return HYTHREAD_INVALID_ARGUMENT;
    }

  THREAD_LOCK (self, self, CALLER_SLEEP);

  self->flags |= HYTHREAD_FLAG_SLEEPING | HYTHREAD_FLAG_TIMER_SET;

  COND_WAIT_IF_TIMEDOUT (self->condition, self->mutex, boundedMillis, 0)
  {
    break;
  }
  COND_WAIT_TIMED_LOOP ();

  self->flags &= ~(HYTHREAD_FLAG_SLEEPING | HYTHREAD_FLAG_TIMER_SET);
  THREAD_UNLOCK (self, self);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_lock
/*
 * Acquire the threading library's global lock.
 * 
 * @param[in] self hythread_t for the current thread
 * @param[in] monitor must be NULL
 * @return none
 * 
 * @deprecated This has been replaced by hythread_lib_lock.
 * @see hythread_lib_lock, hythread_lib_unlock
 */
void VMCALL
hythread_monitor_lock (hythread_t self, hythread_monitor_t monitor)
{
  ASSERT (self);

  if (monitor == NULL)
    {
      GLOBAL_LOCK (self, CALLER_GLOBAL_LOCK);
    }
  else
    {
      ASSERT (0);
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_unlock
/*
 * Release the threading library's global lock.
 * 
 * @param[in] self hythread_t for the current thread
 * @param[in] monitor
 * @return none
 * 
 * @deprecated This has been replaced by hythread_lib_unlock.
 * @see hythread_lib_lock, hythread_lib_unlock
 */
void VMCALL
hythread_monitor_unlock (hythread_t self, hythread_monitor_t monitor)
{
  ASSERT (self);

  if (monitor == NULL)
    {
      GLOBAL_UNLOCK (self);
    }
  else
    {
      ASSERT (0);
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_acquire
/*
 * Acquire a monitor from the threading library. (Private)
 * 
 * @param[in] self current thread
 * @param[in] locking policy 
 * @param[in] locking policy data or HYTHREAD_LOCKING_NO_DATA
 * @return NULL on failure, non-NULL on success
 *
 * @see hythread_monitor_init_with_name, hythread_monitor_destroy
 *
 * 
 */
static hythread_monitor_t VMCALL
hythread_monitor_acquire (hythread_t self, IDATA policy, IDATA policyData)
{
  hythread_monitor_t entry;
  hythread_monitor_pool_t pool = self->library->monitor_pool;
  IDATA rc;

  ASSERT (self);
  ASSERT (pool);

  GLOBAL_LOCK (self, CALLER_MONITOR_ACQUIRE);

  entry = pool->next_free;
  if (entry == NULL)
    {
      hythread_monitor_pool_t last_pool = pool;
      while (last_pool->next != NULL)
        last_pool = last_pool->next;
      last_pool->next = allocate_monitor_pool ();
      if (last_pool->next == NULL)
        {
          /* failed to grow monitor pool */
          GLOBAL_UNLOCK (self);
          return NULL;
        }
      entry = last_pool->next->next_free;
    }

  /* the first time that a mutex is acquired from the pool, we need to 
   * initialize its mutex
   */
  if (entry->flags == HYTHREAD_MONITOR_MUTEX_UNINITIALIZED)
    {

      rc = MUTEX_INIT (entry->mutex);

      if (!rc)
        {
          /* failed to initialize mutex */
          ASSERT_DEBUG (0);
          GLOBAL_UNLOCK (self);
          return NULL;
        }

      entry->flags = 0;

    }

  pool->next_free = (hythread_monitor_t) entry->owner;
  entry->count = 0;

  GLOBAL_UNLOCK (self);

  return entry;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_cancel
/** 
 * Terminate a running thread.
 * 
 * @note This should only be used as a last resort.  The system may be in
 * an unpredictable state once a thread is cancelled.  In addition, the thread
 * may not even stop running if it refuses to cancel.
 * 
 * @param[in] thread a thread to be terminated 
 * @return none
 */
void VMCALL
hythread_cancel (hythread_t thread)
{
  hythread_monitor_t monitor = NULL;
  hythread_t self = MACRO_SELF ();

  ASSERT (thread);
  THREAD_LOCK (self, thread, CALLER_CANCEL);

  /* Special case -- we can cancel cleanly if the thread hasn't started yet */
  if ((thread->flags & HYTHREAD_FLAG_STARTED) == 0)
    {
      thread->flags |= HYTHREAD_FLAG_CANCELED;
      THREAD_UNLOCK (self, thread);
      hythread_resume (thread);
      return;
    }

  (void)THREAD_CANCEL (thread->handle);

  thread->flags |= HYTHREAD_FLAG_CANCELED;

  THREAD_UNLOCK (self, thread);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_detach
/**
 * Detaches the current thread from the threading library.
 * 
 * Detach must only be called by an attached thread.  The actual parameter
 * must be the current thread's hythread_t, or NULL (in which case this
 * function retrieves and uses the current thread's hythread_t).  This
 * function cannot be used to detach an arbitrary thread.
 *
 * When detached, internal resources associated with the thread are freed
 * and the hythread_t structure becomes invalid.
 * 
 * @param[in] thread
 *            the hythread_t of the current thread to be detached, or NULL
 *            meaning the current thread struct is looked-up and detached.
 * @return none
 * 
 * @see hythread_attach
 */
void VMCALL
hythread_detach (hythread_t thread)
{
  UDATA destroy = 0;
  UDATA attached = 0;
  hythread_t self = MACRO_SELF ();

  if (thread == NULL)
    {
      thread = self;
    }
  ASSERT (thread);

  THREAD_LOCK (self, thread, CALLER_DETACH);
  if (thread->attachcount < 1)
    {
      /* error! */
    }
  else
    {
      if (--thread->attachcount == 0)
        {
          if (thread->flags & HYTHREAD_FLAG_ATTACHED)
            {
              /* this is an attached thread, and it is now fully
                 detached.  Mark it dead so that it can be destroyed */
              thread->flags |= HYTHREAD_FLAG_DEAD;
              attached = destroy = 1;
            }
          else
            {
              destroy = thread->flags & HYTHREAD_FLAG_DEAD;
            }
        }
    }
  THREAD_UNLOCK (self, thread);

  if (destroy)
    {
      hythread_library_t library = thread->library;

      tls_finalize (thread);

      destroy_thread (thread, GLOBAL_NOT_LOCKED);
      if (attached)
        {
          TLS_SET (library->self_ptr, NULL);
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_exit
/**
 * Exit the current thread.
 * 
 * If the thread has been detached, it is destroyed.
 * 
 * If monitor is not NULL, the monitor will be exited before the thread terminates. 
 * This is useful if the thread wishes to signal its termination to a watcher, since
 * it exits the monitor and terminates the thread without ever returning control
 * to the thread's routine, which might be running in a DLL which is about to
 * be closed.
 *
 * @param[in] monitor monitor to be exited before exiting (ignored if NULL)
 * @return none
 */
void VMCALL NORETURN
hythread_exit (hythread_monitor_t monitor)
{
  hythread_t self = MACRO_SELF ();

  if (monitor)
    {
      hythread_monitor_exit (monitor);
    }

  /* Walk all monitors: if this thread owns a monitor, exit it */
  monitor = NULL;
  while ((monitor = hythread_monitor_walk (monitor)) != NULL)
    {
      if (monitor->owner == self)
        {
          monitor->count = 1;   /* exit n-1 times */
          hythread_monitor_exit (monitor);
        }
    }

#if defined(LINUX)
  /* NPTL calls __pthread_unwind() from pthread_exit(). That walks the stack.
   * We can't allow that to happen, since our caller might have already been 
   * unloaded. Walking the calling frame could cause a crash. Instead, we
   * longjmp back out to the initial frame.
   */
  if (self->jumpBuffer)
    {
      longjmp (*(jmp_buf *) self->jumpBuffer, 1);
    }
#endif

  internal_exit ();

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_priority_interrupt
/** 
 * Priority interrupt a thread.
 *
 * If the thread is currently blocked (i.e. waiting on a monitor_wait or sleeping)
 * resume the thread and return from the blocking function with
 * HYTHREAD_PRIORITY_INTERRUPTED
 * 
 * @param[in] thread a thead to be priority interrupted
 * @return none
 */
void VMCALL
hythread_priority_interrupt (hythread_t thread)
{
  interrupt_thread (thread, HYTHREAD_FLAG_PRIORITY_INTERRUPTED);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_priority_interrupted
/**
 * Return the value of a thread's priority interrupted flag.
 * 
 * @param[in] thread thread to be queried
 * @return 0 if not priority interrupted, non-zero if priority interrupted flag set
 */
UDATA VMCALL
hythread_priority_interrupted (hythread_t thread)
{
  return (thread->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED) != 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_clear_priority_interrupted
/**
 * Clear the priority interrupted flag of the current thread and return its previous value.
 * 
 * @return  previous value of priority interrupted flag: nonzero if the thread had been priority interrupted.
 */
UDATA VMCALL
hythread_clear_priority_interrupted (void)
{
  UDATA oldFlags;
  hythread_t self = MACRO_SELF ();
  ASSERT (self);

  THREAD_LOCK (self, self, CALLER_CLEAR_PRIORITY_INTERRUPTED);
  oldFlags = self->flags;
  self->flags = oldFlags & ~HYTHREAD_FLAG_PRIORITY_INTERRUPTED;
  THREAD_UNLOCK (self, self);

  return (oldFlags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED) != 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_try_enter
/** 
 * Attempt to enter a monitor without blocking.
 * 
 * If the thread must block before it enters the monitor this function
 * returns immediately with a negative value to indicate failure.
 * 
 * @param[in] monitor a monitor
 * @return  0 on success or negative value on failure
 *
 * @see hythread_monitor_try_enter_using_threadId
 *
 */
IDATA VMCALL
hythread_monitor_try_enter (hythread_monitor_t monitor)
{
  return hythread_monitor_try_enter_using_threadId (monitor, MACRO_SELF ());
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_shutdown
/**
 * Shut down the Hy threading library associated with the current thread.
 * 
 * @return none
 * 
 * @see hythread_init
 */
void VMCALL
hythread_shutdown (void)
{
  hythread_library_t lib = GLOBAL_DATA (default_library);
  ASSERT (lib);
  MUTEX_DESTROY (lib->tls_mutex);
  MUTEX_DESTROY (lib->monitor_mutex);
  MUTEX_DESTROY (lib->global_mutex);
  pool_kill (lib->global_pool);
  free_monitor_pools ();
#if !defined(LINUX)
  TLS_DESTROY (lib->self_ptr);
  pool_kill (lib->thread_pool);
#endif /* LINUX */

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION allocate_thread
/*
 * Allocate a hythread_t from the hythread_t pool.
 * 
 * @note assumes the threading library's thread pool is already initialized
 * @param[in] globalIsLocked indicates whether the threading library global mutex is already locked.
 * @return a new hythread_t on success, NULL on failure.
 * 
 */
static hythread_t
allocate_thread (int globalIsLocked)
{
  hythread_t result;
  hythread_library_t lib = GLOBAL_DATA (default_library);
  ASSERT (lib);

  if (!globalIsLocked)
    {
      GLOBAL_LOCK_SIMPLE (lib);
    }
  lib->threadCount++;
  result = pool_newElement (lib->thread_pool);
  if (!globalIsLocked)
    {
      GLOBAL_UNLOCK_SIMPLE (lib);
    }

  if (result)
    {
      memset (result, 0, sizeof (HyThread));
    }

  return result;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION free_thread
/*
 * Return a hythread_t to the threading library's monitor pool.
 *
 * @param[in] thread thread to be returned to the pool
 * @param[in] globalAlreadyLocked indicated whether the threading library global 
 * mutex is already locked
 * @return none
 */
static void
free_thread (hythread_t thread, int globalAlreadyLocked)
{
  hythread_library_t lib = thread->library;

  ASSERT (thread);

  if (!globalAlreadyLocked)
    {
      GLOBAL_LOCK_SIMPLE (lib);
    }
  pool_removeElement (lib->thread_pool, thread);
  lib->threadCount--;

  if (!globalAlreadyLocked)
    {
      GLOBAL_UNLOCK_SIMPLE (lib);
    }

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION thread_malloc
/*
 * Malloc a thread (hythread_t struct).
 * 
 * Helper function used by the library's thread pool
 * 
 * @param unused ignored
 * @param size size of struct to be alloc'd
 * @return pointer to the malloc'd memory<br>
 * 0 on failure
 *
 */
static void *VMCALL
thread_malloc (void *unused, U_32 size)
{
  return malloc (size);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION thread_free
/*
 * Free a thread (hythread_t struct)
 * Function used by the library's thread pool
 * 
 * @param unused ignored
 * @param prt pointer to hythread_t to be freed
 * @return none
 *
 */
static void VMCALL
thread_free (void *unused, void *ptr)
{
  free (ptr);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION free_monitor_pools
/*
 * Free the Hy threading library's monitor pool.
 * 
 * This requires destroying each and every one of the 
 * monitors in the pool.
 * 
 * @return none
 */
static void
free_monitor_pools (void)
{
  hythread_library_t lib = GLOBAL_DATA (default_library);
  hythread_monitor_pool_t pool = lib->monitor_pool;

  ASSERT (lib);
  ASSERT (pool);

  while (pool)
    {
      int i;
      hythread_monitor_pool_t next = pool->next;
      hythread_monitor_t entry = &pool->entries[0];
      for (i = 0; i < MONITOR_POOL_SIZE - 1; i++, entry++)
        {
          if (entry->flags != HYTHREAD_MONITOR_MUTEX_UNINITIALIZED)
            {
              MUTEX_DESTROY (entry->mutex);
            }
        }
      free (pool);
      pool = next;
    }

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION init_global_monitor
/* 
 * Initialize the mutex used to synchronize access 
 * to thread library global data.
 *
 * @param[in] lib pointer to the thread library
 * @return 0 on success or negative value on failure
 * 
 */
static IDATA
init_global_monitor (hythread_library_t lib)
{
  hythread_monitor_pool_t pool = lib->monitor_pool;
  hythread_monitor_t monitor = pool->next_free;
  ASSERT (monitor);
  pool->next_free = (hythread_monitor_t) monitor->owner;

  if (init_monitor (monitor, 0) != 0)
    {
      return -1;
    }
  if (!MUTEX_INIT (monitor->mutex))
    {
      return -1;
    }

  monitor->name = "Thread global";

  *hythread_global ("global_monitor") = (UDATA) monitor;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION internal_exit
/*
 * Exit from the current thread.
 * 
 * If the thread has been detached it is destroyed.
 */
static void NORETURN
internal_exit (void)
{

  hythread_t self = MACRO_SELF ();
  hythread_library_t lib = self->library;
  int detached;

  ASSERT (self);
  ASSERT (lib);

  tls_finalize (self);

  GLOBAL_LOCK (self, CALLER_INTERNAL_EXIT1);
  THREAD_LOCK (self, self, CALLER_INTERNAL_EXIT1);
  self->flags |= HYTHREAD_FLAG_DEAD;
  detached = self->attachcount == 0;

  /* 
   * Is there an interruptServer thread out there
   * trying to interrupt us? Its services are 
   * no longer required.
   */
  if (self->interrupter)
    {
      THREAD_LOCK (self, self->interrupter, CALLER_INTERNAL_EXIT1);
      self->interrupter->flags |= HYTHREAD_FLAG_CANCELED;
      THREAD_UNLOCK (self, self->interrupter);
      self->interrupter = NULL;
    }

  THREAD_UNLOCK (self, self);

  /* On z/OS we create the thread in the detached state, so the */
  /* call to pthread_detach is not required.                     @dfa1 */
  THREAD_DETACH (self->handle);

  if (detached)
    {
      destroy_thread (self, GLOBAL_IS_LOCKED);
    }

  GLOBAL_UNLOCK_SIMPLE (lib);
  THREAD_EXIT ();
  ASSERT (0);
  /* UNREACHABLE */

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION enqueue_thread
/*
 * Add a thread to a monitor's wait queue.
 * 
 * @note The calling thread must be the current owner of the monitor
 * @param[in] queue head of the monitor's wait queue
 * @param[in] thread thread to be added
 * @return none
 * 
 */
static void
enqueue_thread (hythread_t * queue, hythread_t thread)
{
  hythread_t qthread = *queue;

  ASSERT (thread);
  /* can't be on two queues at the same time */
  ASSERT (NULL == thread->next);

  if (qthread != NULL)
    {
      while (qthread->next)
        {
          qthread = qthread->next;
        }
      qthread->next = thread;
    }
  else
    {
      *queue = thread;
    }

  ASSERT (*queue != NULL);
  ASSERT (NULL == thread->next);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_wait_timed
/** 
 * Wait on a monitor until notified or timed out.
 *
 * A timeout of 0 (0ms, 0ns) indicates wait indefinitely.
 * 
 * @param[in] monitor a monitor to be waited on
 * @param[in] millis >=0
 * @param[in] nanos >=0
 *
 * @return  0 the monitor has been waited on, notified, and reobtained<br>
 * HYTHREAD_INVALID_ARGUMENT millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE the current thread does not own the monitor<br>
 * HYTHREAD_TIMED_OUT the timeout expired
 * 
 * @see hythread_monitor_wait, hythread_monitor_wait_interruptable, hythread_monitor_enter
 * 
 */
IDATA VMCALL
hythread_monitor_wait_timed (hythread_monitor_t monitor, I_64 millis,
                             IDATA nanos)
{
  return monitor_wait (monitor, millis, nanos, WAIT_UNINTERRUPTABLE);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_dump_trace

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_dump_all

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_dump_trace

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_global
/** 
 * Fetch or create a 'named global'.
 *
 * Return a pointer to the data associated with a named global with the specified name.<br>
 * A new named global is created if a named global with the specified name can't be found.
 *
 * @param[in] name name of named global to read/create
 * @return a pointer to a UDATA associated with name<br>
 * 0 on failure.
 * 
 */
UDATA *VMCALL
hythread_global (const char *name)
{
  HyThreadGlobal *global;
  hythread_library_t lib = GLOBAL_DATA (default_library);

  MUTEX_ENTER (lib->global_mutex);

  global = lib->globals;

  while (global)
    {
      if (strcmp (global->name, name) == 0)
        {
          MUTEX_EXIT (lib->global_mutex);
          return &global->data;
        }
      global = global->next;
    }

  /*
   * If we got here, we couldn't find it, therefore
   * we will create a new one
   */

  global = pool_newElement (lib->global_pool);
  if (global == NULL)
    {
      MUTEX_EXIT (lib->global_mutex);
      return NULL;
    }

  global->next = lib->globals;
  global->name = name;
  global->data = 0;
  lib->globals = global;

  MUTEX_EXIT (lib->global_mutex);

  return &global->data;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysem_init
/*
 * Initialize a semaphore.
 *
 * Acquire a semaphore from the threading library.
 * 
 * @param[out] sp pointer to semaphore to be initialized
 * @param[in] initValue initial count value (>=0) for the semaphore
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 * 
 * @see hysem_destroy, hysem_init, hysem_post
 */
IDATA VMCALL
hysem_init (hysem_t * sp, I_32 initValue)
{
  hysem_t s;
  IDATA rc = -1;

  (*sp) = s = SEM_CREATE (initValue);
  if (s)
    {
      rc = SEM_INIT (s, 0, initValue);
    }
  return rc;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysem_destroy
/*
 * Destroy a semaphore.
 *
 * Returns the resources associated with a semaphore back to the Hy threading library.
 * 
 * @param[in] s semaphore to be destroyed
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 * 
 * @see hysem_init, hysem_wait, hysem_post
 */
IDATA VMCALL
hysem_destroy (hysem_t s)
{
  int rval = 0;
  if (s)
    {
      rval = SEM_DESTROY (s);
      SEM_FREE (s);
    }
  return rval;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysem_post
/*
 * Release a semaphore by 1.
 * 
 * @param[in] s semaphore to be released by 1
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 *
 * @see hysem_init, hysem_destroy, hysem_wait
 */
IDATA VMCALL
hysem_post (hysem_t s)
{
  if (s)
    {
      return SEM_POST (s);
    }
  return -1;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysem_wait
/*
 * Wait on a semaphore.
 * 
 * @param[in] s semaphore to be waited on 
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 *
 * @see hysem_init, hysem_destroy, hysem_wait
 *
 */
IDATA VMCALL
hysem_wait (hysem_t s)
{
  if (s)
    {
      while (SEM_WAIT (s) != 0)
        {
          /* loop until success */
        }
      return 0;
    }
  else
    {
      return -1;
    }

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION error

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION monitor_enter
/*
 * Enter a monitor.
 * 
 * A thread may enter a monitor it owns multiple times, but must
 * exit the monitor the same number of times before other threads
 * waiting on the monitor are permitted to continue
 * 
 * @param[in] self current thread
 * @param[in] monitor monitor to enter
 * @return 0 on success<br>
 * HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority 
 * interrupted while blocked
 */
static IDATA
monitor_enter (hythread_t self, hythread_monitor_t monitor)
{

  ASSERT (self);
  ASSERT (0 == self->monitor);
  ASSERT (monitor);
  ASSERT (monitor->owner != self);
  ASSERT (FREE_TAG != monitor->count);

  return monitor_enter_three_tier (self, monitor);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION monitor_enter_three_tier

/*
 * Enter a three-tier monitor.
 * 
 * Spin on a spinlock. Block when that fails, and repeat.
 * 
 * @param[in] self current thread
 * @param[in] monitor monitor to enter
 * @return 0 on success
 */

static IDATA
monitor_enter_three_tier (hythread_t self, hythread_monitor_t monitor)
{
  int firstTimeBlocking = 1;

  while (1)
    {

      if (hythread_spinlock_acquire (self, monitor) == 0)
        {
          monitor->owner = self;
          monitor->count = 1;
          ASSERT (monitor->spinlockState !=
                  HYTHREAD_MONITOR_SPINLOCK_UNOWNED);
          break;
        }

      MONITOR_LOCK (self, monitor, CALLER_MONITOR_ENTER_THREE_TIER1);

      if (HYTHREAD_MONITOR_SPINLOCK_UNOWNED ==
          hythread_spinlock_swapState (monitor,
                                       HYTHREAD_MONITOR_SPINLOCK_EXCEEDED))
        {
          MONITOR_UNLOCK (self, monitor);
          monitor->owner = self;
          monitor->count = 1;
          ASSERT (monitor->spinlockState !=
                  HYTHREAD_MONITOR_SPINLOCK_UNOWNED);
          break;
        }

      THREAD_LOCK (self, self, CALLER_MONITOR_ENTER_THREE_TIER2);
      self->flags |= (HYTHREAD_FLAG_BLOCKED);
      self->monitor = monitor;
      THREAD_UNLOCK (self, self);

      /* 
       * First time we've had to block? 
       * If so, record the info for JLM.
       */
      if (IS_JLM_ENABLED (self))
        {
          if (firstTimeBlocking)
            {
              firstTimeBlocking = 0;

            }
        }

      enqueue_thread (&monitor->blocking, self);
      COND_WAIT (self->condition, monitor->mutex);
      break;
      COND_WAIT_LOOP ();
      remove_from_queue (&monitor->blocking, self);

      MONITOR_UNLOCK (self, monitor);

    }

  /* We now own the monitor */

  /*
   * If the monitor field is set, we must have blocked on it
   * at some point. We're no longer blocked, so clear this.
   */
  if (self->monitor != 0)
    {
      THREAD_LOCK (self, self, CALLER_MONITOR_ENTER_THREE_TIER3);
      self->flags &= ~(HYTHREAD_FLAG_BLOCKED);
      self->monitor = 0;
      THREAD_UNLOCK (self, self);
    }

  /* Did we block? If so, finish up the JLM calcs */
  /* TODO: this is pretty much the same as in monitor_enter.... */
  if (IS_JLM_ENABLED (self))
    {
      monitor->tracing->enter_count++;
      if (0 == firstTimeBlocking)
        {
          monitor->tracing->slow_count++;

        }
    }

  ASSERT (!(self->flags & HYTHREAD_FLAG_BLOCKED));
  ASSERT (0 == self->monitor);

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION monitor_exit
/*
 * Exit a monitor.
 * 
 * If the current thread is not the owner of the monitor, the
 * mutex is unaffected, and an error is returned. This should be
 * tested to determine if IllegalMonitorState should be
 * thrown.
 * 
 * @param[in] self current thread
 * @param[in] monitor monitor to be exited
 * @return 0 on success<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not 
 * own the monitor
 */
static IDATA
monitor_exit (hythread_t self, hythread_monitor_t monitor)
{

  ASSERT (monitor);
  ASSERT (self);
  ASSERT (0 == self->monitor);

  if (monitor->owner != self)
    {
      ASSERT_DEBUG (0);
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  monitor->count--;
  ASSERT (monitor->count >= 0);

  if (monitor->count == 0)
    {
      monitor->owner = NULL;

      if (HYTHREAD_MONITOR_SPINLOCK_EXCEEDED ==
          hythread_spinlock_swapState (monitor,
                                       HYTHREAD_MONITOR_SPINLOCK_UNOWNED))
        {
          MONITOR_LOCK (self, monitor, CALLER_MONITOR_EXIT1);
          unblock_spinlock_threads (self, monitor);
          MONITOR_UNLOCK (self, monitor);
        }

    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION unblock_spinlock_threads

/*
 * Notify all threads blocked on the monitor's mutex, waiting
 * to be told that it's ok to try again to get the spinlock.
 * 
 * Assumes that the caller already owns the monitor's mutex.
 *
 */
static void
unblock_spinlock_threads (hythread_t self, hythread_monitor_t monitor)
{
  hythread_t queue, next;

  ASSERT (self);
  ASSERT (monitor);

  next = monitor->blocking;
  while (next)
    {
      queue = next;
      next = queue->next;
      COND_NOTIFY_ALL (queue->condition);
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_reset_tracing

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_wait_interruptable
/** 
 * Wait on a monitor until notified, interrupted (priority or normal), or timed out.
 *
 * A timeout of 0 (0ms, 0ns) indicates wait indefinitely.
 * 
 * If 'interruptable' is non-zero, the wait may be interrupted by one of the 
 * interrupt functions. (i.e. hythread_interrupt, hythread_priority_interrupt);
 *
 * @param[in] monitor a monitor to be waited on
 * @param[in] millis >=0
 * @param[in] nanos >=0
 * @param[in] interruptable non-zero if the wait is to be interruptable
 *
 * @return   0 the monitor has been waited on, notified, and reobtained<br>
 * HYTHREAD_INVALID_ARGUMENT if millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor<br>
 * HYTHREAD_INTERRUPTED if the thread was interrupted while waiting<br>
 * HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority interrupted while waiting, or while re-obtaining the monitor<br>
 * HYTHREAD_TIMED_OUT if the timeout expired<br>
 * 
 * @see hythread_monitor_wait, hythread_monitor_wait_timed, hythread_monitor_enter
 * @see hythread_interrupt, hythread_priority_interrupt *
 */
IDATA VMCALL
hythread_monitor_wait_interruptable (hythread_monitor_t monitor, I_64 millis,
                                     IDATA nanos)
{
  return monitor_wait (monitor, millis, nanos, WAIT_INTERRUPTABLE);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_num_waiting
/**
 * Returns how many threads are currently waiting on a monitor.
 * 
 * @note This can only be called by the owner of this monitor.
 *
 * @param[in] monitor a monitor
 * @return number of threads waiting on the monitor (>=0)
 */
UDATA VMCALL
hythread_monitor_num_waiting (hythread_monitor_t monitor)
{
  UDATA numWaiting = 0;
  hythread_t curr;
  hythread_t self = MACRO_SELF ();

  ASSERT (monitor);

  MONITOR_LOCK (self, monitor, CALLER_MONITOR_NUM_WAITING);

  curr = monitor->waiting;
  while (curr != NULL)
    {
      numWaiting++;
      curr = curr->next;
    }

  MONITOR_UNLOCK (self, monitor);

  return numWaiting;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION monitor_wait
/*
 * 
 * Wait on a monitor.
 * 
 * Release the monitor, wait for a signal (notification), then re-acquire the monitor.
 * 
 * In this function, we 'unwind' any recursive hold (monitor->count) the thread has 
 * on the monitor and release the OS monitor. When the monitor is re-acquired, 
 * the recursive count is restored to its original value.
 *
 * A timeout of 0 (0ms, 0ns) indicates wait indefinitely.
 * 
 * If 'interruptable' is non-zero, the wait may be interrupted by one of the 
 * interrupt functions. (i.e. hythread_interrupt, hythread_priority_interrupt);
 *
 * @param[in] monitor monitor to be waited on
 * @param[in] millis >=0
 * @param[in] nanos >=0
 * @param[in] interruptable non-zero if the wait is to be interruptable
 *
 * @return HYTHREAD_INVALID_ARGUMENT      - if millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)
 *          HYTHREAD_ILLEGAL_MONITOR_STATE - the current thread does not own the monitor
 *          0                              - the monitor has been waited on, notified, and reobtained
 *          HYTHREAD_INTERRUPTED           - the thread was interrupted while waiting
 *          HYTHREAD_PRIORITY_INTERRUPTED  - if the thread was priority interrupted while waiting, or while re-obtaining the monitor
 *          HYTHREAD_TIMED_OUT             - the timeout expired
 * 
 * @see hythread_monitor_wait, hythread_monitor_wait_interruptable, hythread_monitor_enter
 * @see hythread_interrupt, hythread_priority_interrupt
 */
static IDATA
monitor_wait (hythread_monitor_t monitor, I_64 millis, IDATA nanos,
              IDATA interruptable)
{
  hythread_t self = MACRO_SELF ();
  IDATA count = -1;
  UDATA flags;
  UDATA interrupted = 0, notified = 0, priorityinterrupted = 0;
  UDATA timedOut = 0;

  ASSERT (monitor);
  ASSERT (FREE_TAG != monitor->count);

  if (monitor->owner != self)
    {
      ASSERT_DEBUG (0);
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  if ((millis < 0) || (nanos < 0) || (nanos >= 1000000))
    {
      ASSERT_DEBUG (0);
      return HYTHREAD_INVALID_ARGUMENT;
    }

  count = monitor->count;
  flags = monitor->flags;

  THREAD_LOCK (self, self, CALLER_MONITOR_WAIT1);
  ASSERT (0 == self->monitor);

  /*
   * Before we wait, check if we've already been either interrupted or pri interrupted
   */
  if (interruptable && (self->flags & HYTHREAD_FLAG_INTERRUPTED))
    {
      self->flags &= ~HYTHREAD_FLAG_INTERRUPTED;
      THREAD_UNLOCK (self, self);
      return HYTHREAD_INTERRUPTED;
    }

  if (interruptable && (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED))
    {
      self->flags &= ~HYTHREAD_FLAG_PRIORITY_INTERRUPTED;
      THREAD_UNLOCK (self, self);
      return HYTHREAD_PRIORITY_INTERRUPTED;
    }

  self->flags |=
    (HYTHREAD_FLAG_WAITING |
     (interruptable ? HYTHREAD_FLAG_INTERRUPTABLE : 0));
  if (millis || nanos)
    {
      self->flags |= HYTHREAD_FLAG_TIMER_SET;
    }
  self->monitor = monitor;
  THREAD_UNLOCK (self, self);

  ASSERT (self->flags & HYTHREAD_FLAG_WAITING);
  monitor->owner = NULL;
  monitor->count = 0;

  MONITOR_LOCK (self, monitor, CALLER_MONITOR_WAIT);
  if (HYTHREAD_MONITOR_SPINLOCK_EXCEEDED ==
      hythread_spinlock_swapState (monitor,
                                   HYTHREAD_MONITOR_SPINLOCK_UNOWNED))
    {
      unblock_spinlock_threads (self, monitor);
    }

  enqueue_thread (&monitor->waiting, self);

  if (millis || nanos)
    {
      IDATA boundedMillis = BOUNDED_I64_TO_IDATA (millis);

      COND_WAIT_IF_TIMEDOUT (self->condition, monitor->mutex, boundedMillis,
                             nanos)
      {

        THREAD_LOCK (self, self, CALLER_MONITOR_WAIT2);
        interrupted = interruptable
          && ((self->flags & HYTHREAD_FLAG_INTERRUPTED) != 0);
        priorityinterrupted = interruptable
          && ((self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED) != 0);
        notified = self->flags & HYTHREAD_FLAG_NOTIFIED;
        if (!(interrupted || priorityinterrupted || notified))
          {
            timedOut = 1;
          }
        break;
      }
      else
      {

        THREAD_LOCK (self, self, CALLER_MONITOR_WAIT2);
        interrupted = interruptable
          && ((self->flags & HYTHREAD_FLAG_INTERRUPTED) != 0);
        priorityinterrupted = interruptable
          && ((self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED) != 0);
        notified = self->flags & HYTHREAD_FLAG_NOTIFIED;
        if (interrupted || priorityinterrupted || notified)
          {
            break;
          }
        /* must have been spurious */
        ASSERT_DEBUG (0);
        THREAD_UNLOCK (self, self);
      }
      COND_WAIT_TIMED_LOOP ();
    }
  else
    {
      /*
       * WAIT UNTIL NOTIFIED
       */

      COND_WAIT (self->condition, monitor->mutex);

      THREAD_LOCK (self, self, CALLER_MONITOR_WAIT2);
      interrupted = interruptable
        && ((self->flags & HYTHREAD_FLAG_INTERRUPTED) != 0);
      priorityinterrupted = interruptable
        && ((self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED) != 0);
      notified = self->flags & HYTHREAD_FLAG_NOTIFIED;
      if (interrupted || priorityinterrupted || notified)
        {
          break;
        }
      /* must have been spurious */
      ASSERT_DEBUG (0);
      THREAD_UNLOCK (self, self);
      COND_WAIT_LOOP ();
    }

  /* DONE WAITING AT THIS POINT */

  /* we have to remove self from the wait queue */
  remove_from_queue (&monitor->waiting, self);

  MONITOR_UNLOCK (self, monitor);

  /* at this point, this thread should already be locked */
  ASSERT (notified || interrupted || priorityinterrupted || timedOut);
  ASSERT (!interrupted || interruptable);       /* if we were interrupted, then we'd better have been interruptable */

  self->flags &=
    ~(HYTHREAD_FLAG_WAITING | HYTHREAD_FLAG_NOTIFIED |
      HYTHREAD_FLAG_PRIORITY_INTERRUPTED | HYTHREAD_FLAG_INTERRUPTABLE |
      HYTHREAD_FLAG_TIMER_SET);

  if (interrupted && !(notified || priorityinterrupted))
    self->flags &= ~HYTHREAD_FLAG_INTERRUPTED;

  /* 
   * Is there an interruptServer thread out there
   * trying to interrupt us? Its services are 
   * no longer required.
   */
  if (self->interrupter)
    {
      ASSERT (interrupted || priorityinterrupted);
      THREAD_LOCK (self, self->interrupter, CALLER_MONITOR_WAIT2);
      self->interrupter->flags |= HYTHREAD_FLAG_CANCELED;
      THREAD_UNLOCK (self, self->interrupter);
      self->interrupter = NULL;
    }

  THREAD_UNLOCK (self, self);

  monitor_enter_three_tier (self, monitor);

  monitor->count = count;

  ASSERT (monitor->owner == self);
  ASSERT (monitor->count == count);
  ASSERT (monitor->count >= 1);
  ASSERT (0 == self->monitor);
  ASSERT (!(monitor->flags & HYTHREAD_FLAG_WAITING));
  ASSERT (!(monitor->flags & HYTHREAD_FLAG_TIMER_SET));
  ASSERT (!(monitor->flags & HYTHREAD_FLAG_BLOCKED));
  ASSERT (!(monitor->flags & HYTHREAD_FLAG_NOTIFIED));
  ASSERT (!(monitor->flags & HYTHREAD_FLAG_INTERRUPTABLE));
  ASSERT (NULL == self->next);

  if (priorityinterrupted)
    return HYTHREAD_PRIORITY_INTERRUPTED;
  if (notified)
    return 0;
  if (interrupted)
    return HYTHREAD_INTERRUPTED;
  if (timedOut)
    return HYTHREAD_TIMED_OUT;
  ASSERT (0);
  return 0;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION interrupt_thread
/*
 * Interrupt a thread.
 *
 * If the thread is currently blocked (e.g. waiting on monitor_wait) 
 * resume the thread and return from the blocking function with
 * HYTHREAD_INTERRUPTED or HYTHREAD_PRIORITY_INTERRUPTED
 * 
 * If it can't be resumed (it's not in an interruptable state)
 * then just set the appropriate interrupt flag.
 * 
 * @param[in] thread thread to be interrupted
 * @param[in] interruptFlag indicated whether to priority interrupt or just normally interrupt.
 * @return none
 */
static void
interrupt_thread (hythread_t thread, UDATA interruptFlag)
{
  UDATA currFlags, newFlags;
  hythread_t self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (thread);

  GLOBAL_LOCK (self, CALLER_INTERRUPT_THREAD);
  THREAD_LOCK (self, thread, CALLER_INTERRUPT_THREAD);
  if (thread->flags & interruptFlag)
    {
      THREAD_UNLOCK (self, thread);
      GLOBAL_UNLOCK (self);
      return;
    }

  currFlags = thread->flags;
  newFlags = currFlags | interruptFlag;
  if (currFlags & HYTHREAD_FLAG_INTERRUPTABLE)
    {
      if (currFlags & (HYTHREAD_FLAG_SLEEPING | HYTHREAD_FLAG_PARKED))
        {
          COND_NOTIFY_ALL (thread->condition);
        }
      else if (currFlags & HYTHREAD_FLAG_WAITING)
        {
          if (interrupt_waiting_thread (self, thread))
            {
              newFlags |= HYTHREAD_FLAG_BLOCKED;
            }
        }
    }

  thread->flags = newFlags;
  THREAD_UNLOCK (self, thread);
  GLOBAL_UNLOCK (self);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION interrupt_waiting_thread
/*
 * Interrupt a waiting thread.
 * 
 * @param[in] self current thread
 * @param[in] threadToInterrupt
 * @return 1 if the thread was immediately interrupted<br>
 * 0 if the thread will be interrupted asap by a special thread.
 * @note: Assumes caller has locked the global mutex
 */
static IDATA
interrupt_waiting_thread (hythread_t self, hythread_t threadToInterrupt)
{

  IDATA retVal = 0;
  hythread_monitor_t monitor;

  ASSERT (self);
  ASSERT (threadToInterrupt);
  ASSERT (self != threadToInterrupt);
  ASSERT (threadToInterrupt->flags & HYTHREAD_FLAG_INTERRUPTABLE);
  ASSERT (threadToInterrupt->monitor);
  ASSERT (NULL == threadToInterrupt->interrupter);

#if !defined(ALWAYS_SPAWN_THREAD_TO_INTERRUPT)
  /*
   * If we can enter the monitor without blocking, we don't need the
   * interruptServer thread
   */
  monitor = threadToInterrupt->monitor;
  if (hythread_monitor_try_enter_using_threadId (monitor, self) == 0)
    {
      ASSERT (monitor->owner == self);
      COND_NOTIFY_ALL (threadToInterrupt->condition);
      hythread_monitor_exit_using_threadId (monitor, self);
      retVal = 1;
    }
  else
#endif

    {
      /*
       * spawn a thread to do it for us, because it's possible that
       * having this thread lock the waiting thread's monitor may
       * cause deadlock
       */
      create_thread (&threadToInterrupt->interrupter, 0,
                     HYTHREAD_PRIORITY_NORMAL, 0, interruptServer,
                     (void *) threadToInterrupt, GLOBAL_IS_LOCKED);
    }
  return retVal;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION interruptServer
/*
 * Interrupt a thread waiting on a monitor.
 *  
 * This function serves as the entry point for a 
 * thread whose sole purpose is to interrupt another
 * thread.
 * 
 * @param[in] entryArg pointer to the thread to interrupt (non-NULL)
 * @return 0
 */
static I_32 HYTHREAD_PROC
interruptServer (void *entryArg)
{
  hythread_t self = MACRO_SELF ();
  hythread_t threadToInterrupt = (hythread_t) entryArg;
  hythread_monitor_t monitor;

  ASSERT (threadToInterrupt);
  ASSERT (self);

  GLOBAL_LOCK (self, CALLER_INTERRUPT_SERVER);

  /*
   * Did the thread to interrupt die or come out of wait already?
   * If it did, it cancelled this thread (set our CANCELED bit)
   */
  if (self->flags & HYTHREAD_FLAG_CANCELED)
    {
      GLOBAL_UNLOCK (self);
      hythread_exit (NULL);     /* this should not return */
    }

  THREAD_LOCK (self, threadToInterrupt, CALLER_INTERRUPT_SERVER);

  if (threadToInterrupt->interrupter != self)
    {
      THREAD_UNLOCK (self, threadToInterrupt);
      GLOBAL_UNLOCK (self);
      hythread_exit (NULL);     /* this should not return */
    }

  monitor = threadToInterrupt->monitor;
  ASSERT (monitor);
  ASSERT (threadToInterrupt->flags & HYTHREAD_FLAG_WAITING);

  hythread_monitor_pin (monitor, self);
  THREAD_UNLOCK (self, threadToInterrupt);
  GLOBAL_UNLOCK (self);

  /* try to take the monitor so that we can notify the thread to interrupt */
  hythread_monitor_enter (monitor);

  GLOBAL_LOCK (self, CALLER_INTERRUPT_SERVER);
  hythread_monitor_unpin (monitor, self);

  /* Did the thread to interrupt die or come out of wait already? */
  if (self->flags & HYTHREAD_FLAG_CANCELED)
    {
      GLOBAL_UNLOCK (self);
      hythread_exit (monitor);  /* this should not return */
      ASSERT (0);
    }

  THREAD_LOCK (self, threadToInterrupt, CALLER_INTERRUPT_SERVER);
  if ((threadToInterrupt->interrupter == self)
      && (threadToInterrupt->flags & HYTHREAD_FLAG_WAITING))
    {
      notify_thread (threadToInterrupt, DONT_SET_NOTIFIED_FLAG);
    }
  threadToInterrupt->interrupter = NULL;
  ASSERT (threadToInterrupt->flags & HYTHREAD_FLAG_INTERRUPTED);
  THREAD_UNLOCK (self, threadToInterrupt);

  GLOBAL_UNLOCK (self);
  hythread_exit (monitor);

  ASSERT (0);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_exit_using_threadId
/**
 * Exit a monitor.
 * 
 * This is a slightly faster version of hythread_monitor_exit because
 * the hythread_t for the current thread doesn't have to be looked up
 * 
 * @param[in] monitor a monitor to be exited
 * @param[in] threadId hythread_t for the current thread
 * @return 0 on success<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 * 
 * @see hythread_monitor_exit, hythread_monitor_enter, hythread_monitor_enter_using_threadId
 */
IDATA VMCALL
hythread_monitor_exit_using_threadId (hythread_monitor_t monitor,
                                      hythread_t threadId)
{
  ASSERT (threadId == MACRO_SELF ());
  ASSERT (monitor);
  ASSERT (FREE_TAG != monitor->count);

  if (monitor->owner != threadId)
    {
      ASSERT_DEBUG (0);
      return HYTHREAD_ILLEGAL_MONITOR_STATE;
    }

  return monitor_exit (threadId, monitor);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_enter_using_threadId
/**
 * Enter a monitor.
 * 
 * This is a slightly faster version of hythread_monitor_enter because
 * the hythread_t for the current thread doesn't have to be looked up
 *
 * @param[in] monitor a monitor to be entered
 * @param[in] threadId hythread_t for the current thread
 * @return 0 on success<br>
 * 				 HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority interrupted while blocked
 * 
 * @see hythread_monitor_enter, hythread_monitor_exit, hythread_monitor_exit_using_threadId
 *
 */
IDATA VMCALL
hythread_monitor_enter_using_threadId (hythread_monitor_t monitor,
                                       hythread_t threadId)
{
  ASSERT (threadId != 0);
  ASSERT (threadId == MACRO_SELF ());
  ASSERT (monitor);
  ASSERT (FREE_TAG != monitor->count);

  if (monitor->owner == threadId)
    {
      ASSERT (monitor->count >= 1);
      monitor->count++;

      if (IS_JLM_ENABLED (threadId))
        {
          ASSERT (monitor->tracing);
          monitor->tracing->recursive_count++;
          monitor->tracing->enter_count++;
        }                       /* if (IS_JLM_ENABLED(threadId)) */

      return 0;
    }
  return monitor_enter (threadId, monitor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_try_enter_using_threadId
/** 
 * Attempt to enter a monitor without blocking.
 * 
 * If the thread must block before it enters the monitor this function
 * returns immediately with a negative value to indicate failure.<br>
 *  
 * This is a slightly faster version of hythread_monitor_try_enter because
 * the current thread's hythread_t doesn't have to be looked up.
 * 
 * @param[in] monitor a monitor
 * @param[in] threadId the current thread
 * @return  0 on success or negative value on failure
 *
 * @see hythread_monitor_try_enter
 *
 */
IDATA VMCALL
hythread_monitor_try_enter_using_threadId (hythread_monitor_t monitor,
                                           hythread_t threadId)
{

  ASSERT (threadId != 0);
  ASSERT (threadId == MACRO_SELF ());
  ASSERT (FREE_TAG != monitor->count);

  /* Are we already the owner? */
  if (monitor->owner == threadId)
    {
      ASSERT (monitor->count >= 1);
      monitor->count++;

      if (IS_JLM_ENABLED (threadId))
        {
          monitor->tracing->recursive_count++;
          monitor->tracing->enter_count++;
        }                       /* if (IS_JLM_ENABLED(threadId)) */

      return 0;
    }

  if (hythread_spinlock_acquire (threadId, monitor) == 0)

    {
      ASSERT (NULL == monitor->owner);
      ASSERT (0 == monitor->count);

      monitor->owner = threadId;
      monitor->count = 1;

      if (IS_JLM_ENABLED (threadId))
        {
          monitor->tracing->enter_count++;

        }                       /* if (IS_JLM_ENABLED(threadId)) */

      return 0;
    }

  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_probe

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION destroy_thread
/*
 * Destroy the resources associated with the thread.
 * 
 * If the thread is not already dead, the function fails.
 *
 * @param[in] thread thread to be destroyed
 * @param[in] globalAlreadyLocked indicated whether the thread library global mutex is already locked 
 * @return 0 on success or negative value on failure.
 */
static IDATA
destroy_thread (hythread_t thread, int globalAlreadyLocked)
{

  hythread_t self = MACRO_SELF ();
  hythread_library_t lib = self->library;

  ASSERT (thread);
  ASSERT (lib);

  THREAD_LOCK (self, thread, CALLER_DESTROY);
  if ((thread->flags & HYTHREAD_FLAG_DEAD) == 0)
    {
      THREAD_UNLOCK (self, thread);
      return -1;
    }
  THREAD_UNLOCK (self, thread);

  COND_DESTROY (thread->condition);

  MUTEX_DESTROY (thread->mutex);

#if defined(WIN32)
  if (thread->flags & HYTHREAD_FLAG_ATTACHED)
    {
      CloseHandle (thread->handle);
    }
#endif

  free_thread (thread, globalAlreadyLocked);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_thread_init

/*
 * Initialize and clear a thread's JLM tracing information. 
 * 
 * Tracing information for the thread is allocated if not already allocated
 * The library's thread tracing pool is initialized if not already initialized.
 * 
 * @param[in] thread thread to be initialized
 * @return none
 *
 */
void VMCALL
hythread_jlm_thread_init (hythread_t thread)
{
  hythread_library_t library = thread->library;

  ASSERT (thread);
  ASSERT (library);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_thread_clear

/*
 * Clear (reset) a thread's JLM tracing structure.
 * 
 * Assumes the thread's tracing structure has already been allocated.
 *
 * @param[in] thread thread to be initialized (non-NULL)
 * @return none
 *
 */
void VMCALL
hythread_jlm_thread_clear (hythread_t thread)
{
  ASSERT (thread);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_monitor_init

/*
 * Initialize and clear a monitor's JLM tracing information. 
 * 
 * Tracing information for the monitor is allocated if not already allocated
 * The library's monitor tracing pool is initialized if not already initialized.
 * 
 * @param[in] monitor monitor to be initialized
 * @return none
 *
 */
void VMCALL
hythread_jlm_monitor_init (hythread_monitor_t monitor)
{
  hythread_t self = MACRO_SELF ();
  hythread_library_t library;
  ASSERT (self);
  ASSERT (monitor);
  library = self->library;
  ASSERT (library);

  if (library->monitor_tracing_pool == NULL)
    {
      library->monitor_tracing_pool =
        pool_new (sizeof (HyThreadMonitorTracing), 0, 0, 0, thread_malloc,
                  thread_free, NULL);
    }

  if (monitor->tracing == NULL)
    {
      /* cannot have been set, so set it now */
      monitor->tracing = pool_newElement (library->monitor_tracing_pool);
    }

  hythread_jlm_monitor_clear (monitor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_monitor_clear

/*
 * Clear (reset) a monitor's JLM tracing structure.
 * 
 * Assumes the monitor's tracing structure has already been allocated.
 *
 * @param[in] monitor a monitor to be initialized
 * @return none
 */
void VMCALL
hythread_jlm_monitor_clear (hythread_monitor_t monitor)
{
  ASSERT (monitor);
  ASSERT (monitor->tracing);
  memset (monitor->tracing, 0, sizeof (*monitor->tracing));
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION init_monitor
/*
 * Re-initialize the 'simple' fields of a monitor
 * that has been initialized previously, but is now
 * being re-used. 
 *
 * @param[in] monitor monitor to be initialized
 * @return 0 on success or negative value on failure.
 * @see hythread_monitor_init_with_name
 * 
 */
static UDATA
init_monitor (hythread_monitor_t monitor, UDATA flags)
{
  ASSERT (monitor);
  monitor->count = 0;
  monitor->owner = NULL;
  monitor->waiting = NULL;
  monitor->flags = flags;
  monitor->userData = 0;
  monitor->name = 0;
  monitor->pinCount = 0;

  monitor->proDeflationCount = 0;
  monitor->antiDeflationCount = 0;

  monitor->blocking = NULL;
  monitor->spinlockState = HYTHREAD_MONITOR_SPINLOCK_UNOWNED;
  monitor->lockingWord = 0;
  /* these numbers are taken from the GC spinlock. They probably need to be tuned more (dynamically?) */
  /* note that every spinCount must be > 0! */

  monitor->spinCount1 = 256;
  monitor->spinCount2 = 32;
  monitor->spinCount3 = 45;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getCurrentCycles
/*
 * Return the cycle count on the current processor. 
 * 
 * Units will be platform dependent. 
 * @todo This will be implmented in builder
 */
static hytime_t
getCurrentCycles (void)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_lib_get_flags

/** 
 * Get threading library global flags.
 * 
 * Returns the flags for the threading library associated with the current thread.
 * 
 * @note: assumes caller has global lock
 * 
 * @see hythread_lib_clear_flags, hythread_lib_set_flags, hythread_lib_lock
 * @return current flags value
 */
UDATA VMCALL
hythread_lib_get_flags ()
{
  hythread_t self;
  self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (self->library);

  return self->library->flags;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_lib_set_flags

/**
 * Set threading library global flags.
 * 
 * Sets the flags for the threading library associated with the current thread.
 *
 * @param[in] flags flags to be set (bit vector: 1 means set the flag, 0 means ignore)
 * @return old flags values
 * @see hythread_lib_clear_flags, hythread_lib_get_flags
 *
 */
UDATA VMCALL
hythread_lib_set_flags (UDATA flags)
{
  hythread_t self;
  UDATA oldFlags;
  self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (self->library);

  GLOBAL_LOCK (self, CALLER_LIB_SET_FLAGS);
  oldFlags = self->library->flags;
  self->library->flags |= flags;
  GLOBAL_UNLOCK (self);

  return oldFlags;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_lib_clear_flags

/** 
 * Clear specified threading library global flags.
 *
 * @see hythread_lib_set_flags, hythread_lib_get_flags
 * @param[in] flags flags to be cleared (bit vector: 1 means clear the flag, 0 means ignore)
 * @return old flags values
 */
UDATA VMCALL
hythread_lib_clear_flags (UDATA flags)
{
  hythread_t self;
  UDATA oldFlags;
  self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (self->library);

  GLOBAL_LOCK (self, CALLER_LIB_CLEAR_FLAGS);
  oldFlags = self->library->flags;
  self->library->flags &= ~flags;
  GLOBAL_UNLOCK (self);

  return oldFlags;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_init_with_name
/**
 * Acquire and initialize a new monitor from the threading library.
 *
 * @param[out] handle pointer to a hythread_monitor_t to be set to point to the new monitor
 * @param[in] flags initial flag values for the monitor
 * @param[in] name pointer to a C string with a description of how the monitor will be used (may be NULL)<br>
 * If non-NULL, the C string must be valid for the entire life of the monitor
 * 
 * @return  0 on success or negative value on failure
 * 
 * @see hythread_monitor_destroy
 * 
 */
IDATA VMCALL
hythread_monitor_init_with_name (hythread_monitor_t * handle, UDATA flags,
                                 const char *name)
{
  ASSERT (handle);

  if (hythread_monitor_init (handle, flags) == 0)
    {
      (*handle)->name = name;
      return 0;
    }

  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_gc_lock_init

/*
 * Initialize pools and tracing structures for JLM tracing.
 * 
 * Can be called multiple times.
 * 
 * @return none
 */
void VMCALL
hythread_jlm_gc_lock_init ()
{
  hythread_t self = MACRO_SELF ();
  hythread_library_t library;
  ASSERT (self);
  library = self->library;
  ASSERT (library);

  /* If no monitor_tracing pool yet, create it */
  if (library->monitor_tracing_pool == NULL)
    {
      library->monitor_tracing_pool =
        pool_new (sizeof (HyThreadMonitorTracing), 0, 0, 0, thread_malloc,
                  thread_free, NULL);
    }

  /* If no GC lock tracing pool yet, create it */
  if (library->gc_lock_tracing == NULL)
    {
      library->gc_lock_tracing =
        pool_newElement (library->monitor_tracing_pool);
    }

  /* Init the tracing structure */
  memset (library->gc_lock_tracing, 0, sizeof (*library->gc_lock_tracing));
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_jlm_get_gc_lock_tracing

/*
 * Return tracing info.
 *
 * @return pointer to GC lock tracing structure. 0 of not yet initialized
 */
HyThreadMonitorTracing *VMCALL
hythread_jlm_get_gc_lock_tracing ()
{
  hythread_t self = MACRO_SELF ();
  ASSERT (self);
  ASSERT (self->library);
  return self->library->gc_lock_tracing;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_lib_lock
/**
 * Acquire the threading library's global lock.
 * 
 * @note This must not be called recursively by a thread that already owns the global lock.
 * @param[in] self hythread_t for the current thread
 * @return none
 * 
 * @see hythread_lib_unlock
 */
void VMCALL
hythread_lib_lock (hythread_t self)
{
  ASSERT (self);
  GLOBAL_LOCK (self, CALLER_GLOBAL_LOCK);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_lib_unlock
/**
 * Release the threading library's global lock.
 * 
 * @note This must be called only by the thread that currently has the global lock locked. 
 * @param[in] self hythread_t for the current thread
 * @return none
 * 
 * @see hythread_lib_lock
 */
void VMCALL
hythread_lib_unlock (hythread_t self)
{
  ASSERT (self);
  GLOBAL_UNLOCK (self);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_park
/**
 * 'Park' the current thread. 
 * 
 * Stop the current thread from executing until it is unparked, interrupted, or the specified timeout elapses.
 * 
 * Unlike wait or sleep, the interrupted flag is NOT cleared by this API.
 *
 * @param[in] millis
 * @param[in] nanos 
 * 
 * @return 0 if the thread is unparked
 * HYTHREAD_INTERRUPTED if the thread was interrupted while parked<br>
 * HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority interrupted while parked<br>
 * HYTHREAD_TIMED_OUT if the timeout expired<br>
 *
 * @see hythread_unpark
 */
IDATA VMCALL
hythread_park (I_64 millis, IDATA nanos)
{
  IDATA rc = 0;
  hythread_t self = MACRO_SELF ();
  ASSERT (self);

  THREAD_LOCK (self, self, CALLER_PARK);

  if (self->flags & HYTHREAD_FLAG_UNPARKED)
    {
      self->flags &= ~HYTHREAD_FLAG_UNPARKED;
    }
  else if (self->flags & HYTHREAD_FLAG_INTERRUPTED)
    {
      rc = HYTHREAD_INTERRUPTED;
    }
  else if (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED)
    {
      rc = HYTHREAD_PRIORITY_INTERRUPTED;
    }
  else
    {
      self->flags |= HYTHREAD_FLAG_PARKED | HYTHREAD_FLAG_INTERRUPTABLE;

      if (millis || nanos)
        {
          IDATA boundedMillis = BOUNDED_I64_TO_IDATA (millis);

          self->flags |= HYTHREAD_FLAG_TIMER_SET;

          COND_WAIT_IF_TIMEDOUT (self->condition, self->mutex, boundedMillis,
                                 nanos)
          {
            rc = HYTHREAD_TIMED_OUT;
            break;
          }
          else
        if (self->flags & HYTHREAD_FLAG_UNPARKED)
          {
            self->flags &= ~HYTHREAD_FLAG_UNPARKED;
            break;
          }
        else if (self->flags & HYTHREAD_FLAG_INTERRUPTED)
          {
            rc = HYTHREAD_INTERRUPTED;
            break;
          }
        else if (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED)
          {
            rc = HYTHREAD_PRIORITY_INTERRUPTED;
            break;
          }
          COND_WAIT_TIMED_LOOP ();
        }
      else
        {
          COND_WAIT (self->condition, self->mutex);
          if (self->flags & HYTHREAD_FLAG_UNPARKED)
            {
              self->flags &= ~HYTHREAD_FLAG_UNPARKED;
              break;
            }
          else if (self->flags & HYTHREAD_FLAG_INTERRUPTED)
            {
              rc = HYTHREAD_INTERRUPTED;
              break;
            }
          else if (self->flags & HYTHREAD_FLAG_PRIORITY_INTERRUPTED)
            {
              rc = HYTHREAD_PRIORITY_INTERRUPTED;
              break;
            }
          COND_WAIT_LOOP ();
        }
    }

  self->flags &=
    ~(HYTHREAD_FLAG_PARKED | HYTHREAD_FLAG_INTERRUPTABLE |
      HYTHREAD_FLAG_TIMER_SET);

  THREAD_UNLOCK (self, self);

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_unpark
/**
 * 'Unpark' the specified thread. 
 * 
 * If the thread is parked, it will return from park.
 * If the thread is not parked, its 'UNPARKED' flag will be set, and it will return immediately the next time it is parked.
 *
 * Note that unparks are not counted. Unparking a thread once is the same as unparking it n times.
 * 
 * @see hythread_park
 */
void VMCALL
hythread_unpark (hythread_t thread)
{
  hythread_t self = MACRO_SELF ();

  ASSERT (self);
  ASSERT (thread);

  /* TODO: is GLOBAL_LOCK/GLOBAl_UNLOCK required here? */

  GLOBAL_LOCK (self, CALLER_UNPARK_THREAD);
  THREAD_LOCK (self, thread, CALLER_UNPARK_THREAD);

  thread->flags |= HYTHREAD_FLAG_UNPARKED;

  if (thread->flags & HYTHREAD_FLAG_PARKED)
    {
      COND_NOTIFY_ALL (thread->condition);
    }

  THREAD_UNLOCK (self, thread);
  GLOBAL_UNLOCK (self);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_tls_alloc_with_finalizer
/**
 * Allocate a thread local storage (TLS) key.
 * 
 * Create and return a new, unique key for thread local storage.  
 * 
 * @note The hande returned will be >=0, so it is safe to test the handle against 0 to see if it's been
 * allocated yet.
 * 
 * @param[out] handle pointer to a key to be initialized with a key value
 * @param[in] a finalizer function which will be invoked when a thread is detached or terminates if the thread's TLS entry for this key is non-NULL
 * @return 0 on success or negative value if a key could not be allocated (i.e. all TLS has been allocated)
 * 
 * @see hythread_tls_free, hythread_tls_set
 */
IDATA VMCALL
hythread_tls_alloc_with_finalizer (hythread_tls_key_t * handle,
                                   hythread_tls_finalizer_t finalizer)
{
  IDATA index;
  hythread_library_t lib = GLOBAL_DATA (default_library);
  ASSERT (lib);

  *handle = 0;

  MUTEX_ENTER (lib->tls_mutex);

  for (index = 0; index < HYTHREAD_MAX_TLS_KEYS; index++)
    {
      if (lib->tls_finalizers[index] == NULL)
        {
          *handle = index + 1;
          lib->tls_finalizers[index] = finalizer;
          break;
        }
    }

  MUTEX_EXIT (lib->tls_mutex);

  return index < HYTHREAD_MAX_TLS_KEYS ? 0 : -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION tls_finalize
/*
 * Run finalizers on any non-NULL TLS values for the current thread
 *
 * @param[in] thread current thread
 * @return none
 */
static void
tls_finalize (hythread_t thread)
{
  IDATA index;
  hythread_library_t lib = thread->library;

  for (index = 0; index < HYTHREAD_MAX_TLS_KEYS; index++)
    {
      if (thread->tls[index] != NULL)
        {
          void *value;
          hythread_tls_finalizer_t finalizer;

          /* read the value and finalizer together under mutex to be sure that they belong together */
          MUTEX_ENTER (lib->tls_mutex);
          value = thread->tls[index];
          finalizer = lib->tls_finalizers[index];
          MUTEX_EXIT (lib->tls_mutex);

          if (value)
            {
              finalizer (value);
            }
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION tls_null_finalizer
static void HYTHREAD_PROC
tls_null_finalizer (void *entry)
{
  /* do nothing */
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_current_stack_free
/**
 * Return the remaining useable bytes of the current thread's OS stack.
 *
 * @return OS stack free size in bytes, 0 if it cannot be determined.
 */
UDATA VMCALL
hythread_current_stack_free(void)
{
#if defined(WIN32)
  MEMORY_BASIC_INFORMATION memInfo;
  SYSTEM_INFO sysInfo;
  UDATA stackFree;
  UDATA guardPageSize;

  GetSystemInfo(&sysInfo);
  VirtualQuery(&memInfo, &memInfo, sizeof(MEMORY_BASIC_INFORMATION));
  stackFree = ((UDATA) &memInfo - (UDATA) memInfo.AllocationBase) & ~sizeof(UDATA);

  /* By observation, Win32 reserves 3 pages at the low end of the stack for guard pages, so omit them */

  guardPageSize = 3 * (UDATA) sysInfo.dwPageSize;
  return (stackFree < guardPageSize) ? 0 : stackFree - guardPageSize;
#else
  return 0;
#endif
}

/* Start RoboVM changes */
#define CDEV_CURRENT_FUNCTION hythread_current_stack_free
hythread_t VMCALL
hythread_monitor_owner(hythread_monitor_t monitor)
{
  return monitor->owner;
}
#undef CDEV_CURRENT_FUNCTION
/* End RoboVM changes */

