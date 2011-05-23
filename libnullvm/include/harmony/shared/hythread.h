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

#if !defined(HYTHREAD_H)
#define HYTHREAD_H

#if defined(__cplusplus)
extern "C"
{
#endif
#include <stddef.h>
#include "hycomp.h"
  typedef UDATA hythread_tls_key_t;
#define HYTHREAD_PROC VMCALL
  typedef int (HYTHREAD_PROC * hythread_entrypoint_t) (void *);
  typedef void (HYTHREAD_PROC * hythread_tls_finalizer_t) (void *);
  typedef struct HyThread *hythread_t;
  typedef struct HyThreadMonitor *hythread_monitor_t;
  typedef struct HySemaphore *hysem_t;
  struct HyPortLibrary;
#define HYTHREAD_PRIORITY_MIN  0
#define HYTHREAD_PRIORITY_USER_MIN  1
#define HYTHREAD_PRIORITY_NORMAL  5
#define HYTHREAD_PRIORITY_USER_MAX  10

#define HYTHREAD_PRIORITY_MAX  11

#define HYTHREAD_LOCKING_DEFAULT	0       /* default locking policy for platform */
#define HYTHREAD_LOCKING_NO_DATA	(-1)    /* if no policy data is provided */
#define HYTHREAD_FLAG_BLOCKED  1
#define HYTHREAD_ALREADY_INITIALIZED  4
#define HYTHREAD_TIMED_OUT  3
#define HYTHREAD_FLAG_STARTED  0x800
#define HYTHREAD_FLAG_JLM_HAS_BEEN_ENABLED  0x20000
#define HYTHREAD_ILLEGAL_MONITOR_STATE  1
#define HYTHREAD_FLAG_PRIORITY_INTERRUPTED  0x100
#define HYTHREAD_FLAG_JLMHST_ENABLED  0x10000
#define HYTHREAD_FLAG_JLM_ENABLED  0x4000
#define HYTHREAD_FLAG_INTERRUPTED  4
#define HYTHREAD_INVALID_ARGUMENT  7
#define HYTHREAD_FLAG_DETACHED  0x80
#define HYTHREAD_PRIORITY_INTERRUPTED  5
#define HYTHREAD_FLAG_CANCELED  0x400
#define HYTHREAD_FLAG_NOTIFIED  16
#define HYTHREAD_FLAG_ATTACHED  0x200
#define HYTHREAD_WOULD_BLOCK  8
#define HYTHREAD_FLAG_DEAD  32
#define HYTHREAD_FLAG_WAITING  2
#define HYTHREAD_FLAG_PARKED  0x40000
#define HYTHREAD_FLAG_UNPARKED  0x80000
#define HYTHREAD_FLAG_INTERRUPTABLE  0x2000
#define HYTHREAD_FLAG_TIMER_SET  0x100000
#define HYTHREAD_FLAG_JLM_ENABLED_ALL  0x1C000
#define HYTHREAD_FLAG_JLMTS_ENABLED  0x8000
#define HYTHREAD_INTERRUPTED  2
#define HYTHREAD_FLAG_BLOCKED_AFTER_WAIT  0x1000
#define HYTHREAD_ALREADY_ATTACHED  6
#define HYTHREAD_FLAG_SUSPENDED  8
#define HYTHREAD_FLAG_SLEEPING  64
#define HYTHREAD_MONITOR_INFLATED  0x10000
#define HYTHREAD_MONITOR_INTERRUPTABLE  0x20000
#define HYTHREAD_MONITOR_PRIORITY_INTERRUPTABLE  0x40000
#define HYTHREAD_MONITOR_SYSTEM  0
#define HYTHREAD_MONITOR_OBJECT  0x60000
#define HYTHREAD_MONITOR_MUTEX_UNINITIALIZED  0x80000
#define HYTHREAD_MONITOR_SUPPRESS_CONTENDED_EXIT  0x100000
#define HYTHREAD_MONITOR_MUTEX_IN_USE  0x200000
#define HYTHREAD_MONITOR_SPINLOCK_UNOWNED  0
#define HYTHREAD_MONITOR_SPINLOCK_OWNED  1
#define HYTHREAD_MONITOR_SPINLOCK_EXCEEDED  2
#define HYTHREAD_LIB_FLAG_JLMHST_ENABLED  0x10000
#define HYTHREAD_LIB_FLAG_JLM_ENABLED  0x4000
#define HYTHREAD_LIB_FLAG_JLM_ENABLED_ALL  0x1C000
#define HYTHREAD_LIB_FLAG_JLM_HAS_BEEN_ENABLED  0x20000
#define HYTHREAD_LIB_FLAG_JLMTS_ENABLED  0x8000

  typedef struct HyThreadMonitorTracing
  {
    const char *monitor_name;
    UDATA enter_count;
    UDATA slow_count;
    UDATA recursive_count;
    UDATA spin2_count;
    UDATA yield_count;
  } HyThreadMonitorTracing;

  extern HY_CFUNC void VMCALL hythread_detach PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC UDATA VMCALL
    hythread_lib_set_flags PROTOTYPE ((UDATA flags));
  extern HY_CFUNC IDATA VMCALL
    hythread_tls_alloc PROTOTYPE ((hythread_tls_key_t * handle));
  extern HY_CFUNC IDATA VMCALL
    hythread_sleep_interruptable PROTOTYPE ((I_64 millis, IDATA nanos));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_enter_using_threadId
    PROTOTYPE ((hythread_monitor_t monitor, hythread_t threadId));
  extern HY_CFUNC void VMCALL hythread_cancel PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC UDATA VMCALL hythread_clear_interrupted PROTOTYPE ((void));
  extern HY_CFUNC void VMCALL
    hythread_lib_unlock PROTOTYPE ((hythread_t self));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_enter PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_notify_all PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL
    hythread_attach PROTOTYPE ((hythread_t * handle));
  extern HY_CFUNC HyThreadMonitorTracing *VMCALL
    hythread_jlm_get_gc_lock_tracing PROTOTYPE (());
  extern HY_CFUNC UDATA VMCALL
    hythread_priority_interrupted PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_destroy PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hysem_post PROTOTYPE ((hysem_t s));
  extern HY_CFUNC UDATA VMCALL
    hythread_monitor_num_waiting PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC UDATA VMCALL
    hythread_interrupted PROTOTYPE ((hythread_t thread));

  extern HY_CFUNC void VMCALL
    hythread_monitor_lock
    PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hythread_park
    PROTOTYPE ((I_64 millis, IDATA nanos));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_init_with_name
    PROTOTYPE ((hythread_monitor_t * handle, UDATA flags, const char *name));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_try_enter
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC void VMCALL hythread_jlm_thread_clear
    PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC hythread_t VMCALL hythread_self PROTOTYPE ((void));
  extern HY_CFUNC IDATA VMCALL
    hythread_tls_free PROTOTYPE ((hythread_tls_key_t key));
  extern HY_CFUNC UDATA VMCALL
    hythread_clear_priority_interrupted PROTOTYPE ((void));
  extern HY_CFUNC void VMCALL
    hythread_jlm_thread_init PROTOTYPE ((hythread_t thread));

  extern HY_CFUNC void VMCALL hythread_jlm_gc_lock_init PROTOTYPE (());
  extern HY_CFUNC void VMCALL
    hythread_monitor_unlock
    PROTOTYPE ((hythread_t self, hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hysem_wait PROTOTYPE ((hysem_t s));
  extern HY_CFUNC void VMCALL hythread_yield PROTOTYPE ((void));
  extern HY_CFUNC void VMCALL hythread_suspend PROTOTYPE ((void));
  extern HY_CFUNC void VMCALL
    hythread_interrupt PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC IDATA VMCALL
    hythread_tls_set
    PROTOTYPE ((hythread_t thread, hythread_tls_key_t key, void *value));
  extern HY_CFUNC IDATA VMCALL hythread_create
    PROTOTYPE ((hythread_t * handle, UDATA stacksize, UDATA priority,
                UDATA suspend, hythread_entrypoint_t entrypoint,
                void *entryarg));
  extern HY_CFUNC IDATA VMCALL hysem_init
    PROTOTYPE ((hysem_t * sp, I_32 initValue));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_wait
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_init_policy
    PROTOTYPE ((hythread_monitor_t * handle, UDATA flags, IDATA policy,
                IDATA policyData));
  extern HY_CFUNC void VMCALL hythread_jlm_monitor_init
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hysem_destroy PROTOTYPE ((hysem_t s));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_wait_interruptable
    PROTOTYPE ((hythread_monitor_t monitor, I_64 millis, IDATA nanos));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_try_enter_using_threadId
    PROTOTYPE ((hythread_monitor_t monitor, hythread_t threadId));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_exit
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hythread_set_priority
    PROTOTYPE ((hythread_t thread, UDATA priority));
  extern HY_CFUNC void VMCALL hythread_unpark PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC void VMCALL hythread_lib_lock PROTOTYPE ((hythread_t self));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_init
    PROTOTYPE ((hythread_monitor_t * handle, UDATA flags));
  extern HY_CFUNC IDATA VMCALL hythread_sleep PROTOTYPE ((I_64 millis));
  extern HY_CFUNC UDATA *VMCALL hythread_global PROTOTYPE ((const char *name));
  extern HY_CFUNC IDATA VMCALL
    hythread_tls_alloc_with_finalizer
    PROTOTYPE ((hythread_tls_key_t * handle,
                hythread_tls_finalizer_t finalizer));
  extern HY_CFUNC void VMCALL hythread_jlm_monitor_clear
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL hythread_monitor_wait_timed
    PROTOTYPE ((hythread_monitor_t monitor, I_64 millis, IDATA nanos));
  extern HY_CFUNC void VMCALL hythread_resume PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC UDATA VMCALL
    hythread_lib_clear_flags PROTOTYPE ((UDATA flags));
  extern HY_CFUNC void VMCALL
    hythread_priority_interrupt PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC void VMCALL NORETURN
    hythread_exit PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_exit_using_threadId
    PROTOTYPE ((hythread_monitor_t monitor, hythread_t threadId));

  extern HY_CFUNC UDATA VMCALL hythread_lib_get_flags PROTOTYPE (());
  extern HY_CFUNC IDATA VMCALL
    hythread_monitor_notify PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC void VMCALL
    hythread_enable_stack_usage PROTOTYPE ((UDATA enable));

  extern HY_CFUNC UDATA VMCALL
    hythread_get_handle PROTOTYPE ((hythread_t thread));

  extern HY_CFUNC UDATA VMCALL
    hythread_get_stack_usage PROTOTYPE ((hythread_t thread));

  extern HY_CFUNC I_64 VMCALL
    hythread_get_cpu_time PROTOTYPE ((hythread_t thread));

  extern HY_CFUNC UDATA VMCALL
    hythread_get_stack_size PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC I_64 VMCALL
    hythread_get_user_time PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC IDATA VMCALL hythread_get_os_priority
    PROTOTYPE ((hythread_t thread, IDATA * policy, IDATA * priority));
  extern HY_CFUNC UDATA VMCALL hythread_get_flags
    PROTOTYPE ((hythread_t thread, hythread_monitor_t * blocker));
  extern HY_CFUNC HyThreadMonitorTracing *VMCALL hythread_monitor_get_tracing
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC UDATA VMCALL hythread_get_priority
    PROTOTYPE ((hythread_t thread));
  extern HY_CFUNC void *VMCALL hythread_tls_get
    PROTOTYPE ((hythread_t thread, hythread_tls_key_t key));
  extern HY_CFUNC const char *VMCALL hythread_monitor_get_name
    PROTOTYPE ((hythread_monitor_t monitor));
  extern HY_CFUNC hythread_monitor_t VMCALL hythread_monitor_walk
    PROTOTYPE ((hythread_monitor_t monitor));
  struct RWMutex;
  typedef struct RWMutex *hythread_rwmutex_t;
  extern HY_CFUNC IDATA VMCALL
    hythread_rwmutex_enter_read PROTOTYPE ((hythread_rwmutex_t mutex));
  extern HY_CFUNC IDATA VMCALL
    hythread_rwmutex_destroy PROTOTYPE ((hythread_rwmutex_t mutex));
  extern HY_CFUNC IDATA VMCALL
    hythread_rwmutex_exit_read PROTOTYPE ((hythread_rwmutex_t mutex));
  extern HY_CFUNC IDATA VMCALL
    hythread_rwmutex_exit_write PROTOTYPE ((hythread_rwmutex_t mutex));
  extern HY_CFUNC IDATA VMCALL
    hythread_rwmutex_init
    PROTOTYPE ((hythread_rwmutex_t * handle, UDATA flags, const char *name));
  extern HY_CFUNC IDATA VMCALL hythread_rwmutex_enter_write
    PROTOTYPE ((hythread_rwmutex_t mutex));
/* HyVMThreadHelpers*/
#if !defined(_HYVMTHREADHELPERS_)
#define _HYVMTHREADHELPERS_
  extern HY_CFUNC UDATA VMCALL current_stack_depth ();
  extern HY_CFUNC void VMCALL hythread_monitor_unpin (hythread_monitor_t
                                                      monitor,
                                                      hythread_t osThread);
  extern HY_CFUNC void VMCALL hythread_monitor_pin (hythread_monitor_t
                                                    monitor,
                                                    hythread_t osThread);
#endif  /* _HYVMTHREADHELPERS_ */
/* HyVMThreadSpinlocks*/
#if !defined(_HYVMTHREADSPINLOCKS_)
#define _HYVMTHREADSPINLOCKS_
  extern HY_CFUNC IDATA VMCALL hythread_spinlock_acquire (hythread_t self,
                                                          hythread_monitor_t
                                                          monitor);
  extern HY_CFUNC UDATA VMCALL hythread_spinlock_swapState (hythread_monitor_t
                                                            monitor,
                                                            UDATA newState);
#endif /* _HYVMTHREADSPINLOCKS_ */
#define hythread_global_monitor() (*(hythread_monitor_t*)hythread_global("global_monitor"))
#define hythread_monitor_init(pMon,flags)  hythread_monitor_init_with_name(pMon,flags, #pMon)
#define hythread_monitor_set_name(pMon,pName) 

/* Start NullVM changes */
  extern HY_CFUNC hythread_t VMCALL
    hythread_monitor_owner
    PROTOTYPE ((hythread_monitor_t monitor));
/* End NullVM changes */

#if defined(__cplusplus)
}
#endif

#endif /* HYTHREAD_H */
