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

#if !defined(HYTHREAD_H)
#define HYTHREAD_H

#ifdef _WIN32
#   if (_MSC_VER >= 1400)
#       include <intrin.h>
#   endif
#endif

#if defined(__cplusplus)
extern "C" {
#endif
#include <open/types.h>


#include <stddef.h>
#include "hycomp.h"

/* 
 * Idea behind these declarations is to make some functions static inlined for 
 * all files that include hythread_ext.h/hythread.h except one, that 
 * will export this functions in DLL 
 */
   
#ifndef hy_inline
#  ifdef PLATFORM_POSIX
#    define hy_inline inline static
#  else
#     define hy_inline static __forceinline 
#  endif //PLATFORM_POSIX
#endif // hy_inline

typedef UDATA hythread_tls_key_t;

#define HYTHREAD_PROC VMCALL

typedef IDATA (HYTHREAD_PROC* hythread_entrypoint_t)(void*);
typedef void (HYTHREAD_PROC* hythread_tls_finalizer_t)(void*);
typedef struct HyThread *hythread_t;
typedef struct HyThreadGroup *hythread_group_t;
typedef struct HyThreadMonitor *hythread_monitor_t;
typedef struct HySemaphore *hysem_t;

struct HyPortLibrary;

#define HYTHREAD_PRIORITY_MIN  0
#define HYTHREAD_PRIORITY_USER_MIN  1
#define HYTHREAD_PRIORITY_NORMAL  5
#define HYTHREAD_PRIORITY_USER_MAX  10

#define HYTHREAD_PRIORITY_MAX  11

#define HYTHREAD_LOCKING_DEFAULT    0 /* default locking policy for platform */ 
#define HYTHREAD_LOCKING_NO_DATA    (-1)    /* if no policy data is provided */
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

typedef struct HyThreadMonitorTracing {
    const char* monitor_name;
    UDATA enter_count;
    UDATA slow_count;
    UDATA recursive_count;
    UDATA spin2_count;
    UDATA yield_count;
} HyThreadMonitorTracing;

#ifdef HY_NO_THR

/* Thread library version defines */
#define HYTHREAD_MAJOR_VERSION_NUMBER  1
#define HYTHREAD_MINOR_VERSION_NUMBER  0
#define HYTHREAD_CAPABILITY_BASE  0
#define HYTHREAD_CAPABILITY_STANDARD  1
#define HYTHREAD_CAPABILITY_MASK ((U_64)(HYTHREAD_CAPABILITY_STANDARD))
#define HYTHREAD_SET_VERSION(threadLibraryVersion, capabilityMask) \
  (threadLibraryVersion)->majorVersionNumber = HYTHREAD_MAJOR_VERSION_NUMBER; \
  (threadLibraryVersion)->minorVersionNumber = HYTHREAD_MINOR_VERSION_NUMBER; \
  (threadLibraryVersion)->capabilities = (capabilityMask)
#define HYTHREAD_SET_VERSION_DEFAULT(threadLibraryVersion) \
  (threadLibraryVersion)->majorVersionNumber = HYTHREAD_MAJOR_VERSION_NUMBER; \
  (threadLibraryVersion)->minorVersionNumber = HYTHREAD_MINOR_VERSION_NUMBER; \
  (threadLibraryVersion)->capabilities = HYTHREAD_CAPABILITY_MASK

typedef struct HyThreadLibraryVersion
{
  U_16 majorVersionNumber;
  U_16 minorVersionNumber;
  U_32 padding;
  U_64 capabilities;
} HyThreadLibraryVersion;

/* Thread library function table */
typedef struct HyThreadLibrary {
  struct HyThreadLibraryVersion threadVersion;

  IDATA (PVMCALL sem_destroy) (struct HyThreadLibrary *threadLibraryFuncs, hysem_t s);
  IDATA (PVMCALL sem_init) (struct HyThreadLibrary *threadLibraryFuncs, hysem_t * sp, I_32 initValue);
  IDATA (PVMCALL sem_post) (struct HyThreadLibrary *threadLibraryFuncs, hysem_t s);
  IDATA (PVMCALL sem_wait) (struct HyThreadLibrary *threadLibraryFuncs, hysem_t s);

  IDATA (PVMCALL thread_attach) (struct HyThreadLibrary *threadLibraryFuncs, hythread_t * handle);
  IDATA (PVMCALL thread_create) (struct HyThreadLibrary *threadLibraryFuncs, hythread_t * handle, UDATA stacksize, UDATA priority,
                UDATA suspend, hythread_entrypoint_t entrypoint,
                void *entryarg);
  void  (PVMCALL thread_detach) (struct HyThreadLibrary *threadLibraryFuncs, hythread_t thread);
  void  (PVMCALL NORETURN thread_exit) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  UDATA *(PVMCALL thread_global) (struct HyThreadLibrary *threadLibraryFuncs, const char *name);
  IDATA (PVMCALL thread_monitor_destroy) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  IDATA (PVMCALL thread_monitor_enter) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  IDATA (PVMCALL thread_monitor_exit) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  IDATA (PVMCALL thread_monitor_init_with_name) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t * handle, UDATA flags, const char *name);
  IDATA (PVMCALL thread_monitor_notify) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  IDATA (PVMCALL thread_monitor_notify_all) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  IDATA (PVMCALL thread_monitor_wait) (struct HyThreadLibrary *threadLibraryFuncs, hythread_monitor_t monitor);
  hythread_t (PVMCALL thread_self) (struct HyThreadLibrary *threadLibraryFuncs);
  IDATA (PVMCALL thread_sleep) (struct HyThreadLibrary *threadLibraryFuncs, I_64 millis);
  IDATA (PVMCALL thread_tls_alloc) (struct HyThreadLibrary *threadLibraryFuncs, hythread_tls_key_t * handle);
  IDATA (PVMCALL thread_tls_free) (struct HyThreadLibrary *threadLibraryFuncs, hythread_tls_key_t key);
  void *(PVMCALL thread_tls_get) (struct HyThreadLibrary *threadLibraryFuncs, hythread_t thread, hythread_tls_key_t key);
  IDATA (PVMCALL thread_tls_set) (struct HyThreadLibrary *threadLibraryFuncs, hythread_t thread, hythread_tls_key_t key, void *value);

  void *self_handle;
} HyThreadLibrary;

#endif /* HY_NO_THR */


#define HYSIZEOF_HyThreadMonitorTracing 24


extern HY_CFUNC void VMCALL
hythread_detach PROTOTYPE((hythread_t thread));
extern HY_CFUNC UDATA VMCALL
hythread_lib_set_flags PROTOTYPE((UDATA flags));
extern HY_CFUNC IDATA VMCALL 
hythread_tls_alloc PROTOTYPE((hythread_tls_key_t* handle));
extern HY_CFUNC IDATA VMCALL 
hythread_sleep_interruptable PROTOTYPE((I_64 millis, IDATA nanos));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_enter_using_threadId PROTOTYPE((hythread_monitor_t monitor, hythread_t threadId));
extern HY_CFUNC void  VMCALL 
hythread_cancel PROTOTYPE((hythread_t thread));
extern HY_CFUNC UDATA VMCALL
hythread_clear_interrupted PROTOTYPE((void));
extern HY_CFUNC void VMCALL 
hythread_lib_unlock PROTOTYPE((hythread_t self));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_enter PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL
hythread_monitor_notify_all PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_attach PROTOTYPE((hythread_t* handle));
extern HY_CFUNC HyThreadMonitorTracing* VMCALL 
hythread_jlm_get_gc_lock_tracing PROTOTYPE(());
extern HY_CFUNC UDATA VMCALL 
hythread_priority_interrupted PROTOTYPE((hythread_t thread));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_destroy PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hysem_post PROTOTYPE((hysem_t s));
extern HY_CFUNC UDATA VMCALL 
hythread_monitor_num_waiting PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC UDATA VMCALL
hythread_interrupted PROTOTYPE((hythread_t thread));

extern HY_CFUNC void VMCALL 
hythread_monitor_lock PROTOTYPE((hythread_t self, hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_park PROTOTYPE((I_64 millis, IDATA nanos));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_init_with_name PROTOTYPE((hythread_monitor_t* handle, UDATA flags, const char* name));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_try_enter PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC hythread_t VMCALL
hythread_self_slow PROTOTYPE(());
extern HY_CFUNC void VMCALL 
hythread_jlm_thread_clear PROTOTYPE((hythread_t thread));


extern HY_CFUNC IDATA VMCALL 
hythread_tls_free PROTOTYPE((hythread_tls_key_t key));
extern HY_CFUNC UDATA VMCALL
hythread_clear_priority_interrupted PROTOTYPE((void));
extern HY_CFUNC void VMCALL 
hythread_jlm_thread_init PROTOTYPE((hythread_t thread));

extern HY_CFUNC void VMCALL 
hythread_jlm_gc_lock_init PROTOTYPE(());
extern HY_CFUNC void VMCALL 
hythread_monitor_unlock PROTOTYPE((hythread_t self, hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hysem_wait PROTOTYPE((hysem_t s));
extern HY_CFUNC void VMCALL 
hythread_yield PROTOTYPE((void));
extern HY_CFUNC void VMCALL 
hythread_suspend PROTOTYPE((void));
extern HY_CFUNC void VMCALL 
hythread_interrupt PROTOTYPE((hythread_t thread));
extern HY_CFUNC IDATA VMCALL 
hythread_tls_set PROTOTYPE((hythread_t thread, hythread_tls_key_t key, void* value));
extern HY_CFUNC IDATA VMCALL 
hythread_create PROTOTYPE((hythread_t* handle, UDATA stacksize, UDATA priority, UDATA suspend, hythread_entrypoint_t entrypoint, void* entryarg));
extern HY_CFUNC IDATA VMCALL
hysem_init PROTOTYPE((hysem_t* sp, I_32 initValue));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_wait PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_init_policy PROTOTYPE((hythread_monitor_t* handle, UDATA flags, IDATA policy, IDATA policyData));
extern HY_CFUNC void VMCALL 
hythread_jlm_monitor_init PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL
hysem_destroy PROTOTYPE((hysem_t s));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_wait_interruptable PROTOTYPE((hythread_monitor_t monitor, I_64 millis, IDATA nanos));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_try_enter_using_threadId PROTOTYPE((hythread_monitor_t monitor, hythread_t threadId));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_exit PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_set_priority PROTOTYPE((hythread_t thread, UDATA priority));
extern HY_CFUNC void VMCALL 
hythread_unpark PROTOTYPE((hythread_t thread));
extern HY_CFUNC void VMCALL 
hythread_lib_lock PROTOTYPE((hythread_t self));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_init PROTOTYPE((hythread_monitor_t* handle, UDATA flags));
extern HY_CFUNC IDATA VMCALL 
hythread_sleep PROTOTYPE((I_64 millis));
extern HY_CFUNC UDATA* VMCALL 
hythread_global PROTOTYPE((const char* name));
extern HY_CFUNC IDATA VMCALL 
hythread_tls_alloc_with_finalizer PROTOTYPE((hythread_tls_key_t* handle, hythread_tls_finalizer_t finalizer));
extern HY_CFUNC void VMCALL 
hythread_jlm_monitor_clear PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_wait_timed PROTOTYPE((hythread_monitor_t monitor, I_64 millis, IDATA nanos));
extern HY_CFUNC void VMCALL
hythread_resume PROTOTYPE((hythread_t thread));
extern HY_CFUNC UDATA VMCALL 
hythread_lib_clear_flags PROTOTYPE((UDATA flags));
extern HY_CFUNC void VMCALL 
hythread_priority_interrupt PROTOTYPE((hythread_t thread));
extern HY_CFUNC void VMCALL NORETURN 
hythread_exit PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_exit_using_threadId PROTOTYPE((hythread_monitor_t monitor, hythread_t threadId));

extern HY_CFUNC UDATA VMCALL 
hythread_lib_get_flags PROTOTYPE(());
extern HY_CFUNC IDATA VMCALL 
hythread_monitor_notify PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC void VMCALL 
hythread_enable_stack_usage PROTOTYPE((UDATA enable));

extern HY_CFUNC UDATA VMCALL 
hythread_get_handle PROTOTYPE((hythread_t thread));

extern HY_CFUNC UDATA VMCALL 
hythread_get_stack_usage PROTOTYPE((hythread_t thread));

extern HY_CFUNC I_64 VMCALL 
hythread_get_cpu_time PROTOTYPE((hythread_t thread));

extern HY_CFUNC UDATA VMCALL 
hythread_get_stack_size PROTOTYPE((hythread_t thread));
extern HY_CFUNC I_64 VMCALL 
hythread_get_user_time PROTOTYPE((hythread_t thread));
extern HY_CFUNC IDATA VMCALL hythread_get_os_priority PROTOTYPE((hythread_t thread, IDATA* policy, IDATA *priority));
extern HY_CFUNC UDATA VMCALL 
hythread_get_flags PROTOTYPE((hythread_t thread, hythread_monitor_t* blocker));
extern HY_CFUNC HyThreadMonitorTracing* VMCALL 
hythread_monitor_get_tracing PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC UDATA VMCALL 
hythread_get_priority PROTOTYPE((hythread_t thread));
extern HY_CFUNC const char* VMCALL 
hythread_monitor_get_name PROTOTYPE((hythread_monitor_t monitor));
extern HY_CFUNC hythread_monitor_t VMCALL 
hythread_monitor_walk PROTOTYPE((hythread_monitor_t monitor));
struct RWMutex;
typedef struct RWMutex* hythread_rwmutex_t;
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_enter_read PROTOTYPE((hythread_rwmutex_t mutex));
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_destroy PROTOTYPE((hythread_rwmutex_t mutex));
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_exit_read PROTOTYPE((hythread_rwmutex_t mutex));
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_exit_write PROTOTYPE((hythread_rwmutex_t mutex));
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_init PROTOTYPE((hythread_rwmutex_t* handle, UDATA flags, const char* name));
extern HY_CFUNC IDATA VMCALL
hythread_rwmutex_enter_write PROTOTYPE((hythread_rwmutex_t mutex));

/* HyVMThreadHelpers*/
#if !defined(_HYVMTHREADHELPERS_)
#define _HYVMTHREADHELPERS_
extern HY_CFUNC UDATA  VMCALL current_stack_depth ();
extern HY_CFUNC void  VMCALL hythread_monitor_unpin ( hythread_monitor_t monitor, hythread_t osThread);
extern HY_CFUNC void  VMCALL hythread_monitor_pin ( hythread_monitor_t monitor, hythread_t osThread);
#endif /* _HYVMTHREADHELPERS_ */

/* HyVMThreadSpinlocks*/
#if !defined(_HYVMTHREADSPINLOCKS_)
#define _HYVMTHREADSPINLOCKS_
extern HY_CFUNC IDATA  VMCALL hythread_spinlock_acquire (hythread_t self, hythread_monitor_t monitor);
extern HY_CFUNC UDATA  VMCALL hythread_spinlock_swapState (hythread_monitor_t monitor, UDATA newState);
#endif /* _HYVMTHREADSPINLOCKS_ */

#define hythread_global_monitor() (*(hythread_monitor_t*)hythread_global("global_monitor"))
#define hythread_monitor_init(pMon,flags)  hythread_monitor_init_with_name(pMon,flags, #pMon)
#define hythread_monitor_set_name(pMon,pName)

/**
 * Native thread control structure's public fields.
 */

typedef struct HyThread_public {

#ifndef POSIX
    // This is dummy pointer for Microsoft Visual Studio debugging
    // If this is removed, Visual Studio, when attached to VM, will show
    // no symbolic information
    void* reserved;
#endif

// Public fields exported by HyThread_public. If you change these fields,
// please, check fields in thread_private.h/HyThread

    /**
     * Number of requests made for this thread, it includes both
     * suspend requests and safe point callback requests.
     * The field is modified by atomic operations.
     *
     * Increment in functions:
     *    1. send_suspend_request()
     *          - sets suspend request for a given thread
     *    2. hythread_set_safepoint_callback()
     *          - sets safe point callback request for a given thread
     *
     * Decrement in functions:
     *    1. hythread_resume()
     *          - removes suspend request for a given thread
     *    2. hythread_exception_safe_point()
     *          - removes safe point callback request for current thread
     */
    I_32 request;

    /**
     * Field indicating that thread can safely be suspended.
     * Safe suspension is enabled on value 0.
     *
     * The disable_count is increased/decreaded in
     * hythread_suspend_disable()/hythread_suspend_enable() function
     * for current thread only.
     *
     * Also disable_count could be reset to value 0 and restored in
     * hythread_reset_suspend_disable()/hythread_set_suspend_disable() function
     * for current thread only.
     *
     * Function hythread_exception_safe_point() sets disable_count to
     * value 1 before safe point callback function calling and restores
     * it after the call.
     *
     * Function thread_safe_point_impl() sets disable_count to
     * value 0 before entering to the safe point and restores it
     * after exitting.
     */
    int16 disable_count;


    /**
     * Group for this thread. Different groups are needed in order 
     * to be able to quickly iterate over the specific group.
     * Examples are: Java threads, GC private threads.
     * Equal to the address of the head of the list of threads for this group.
     */
    hythread_group_t  group; 

    /**
     * Array representing thread local storage
     */
    void *thread_local_storage[10];

} HyThread_public;



/** 
 * HYTHREAD_FAST_TLS
 * Enables platform-specific TLS access optimization, such as:
 *  - On Win32, free TIB slot is used via direct reference FS:[0x14]
 * (see http://www.microsoft.com/msj/archive/S2CE.aspx);
 *  - On Linuxes, initial-exec TLS model allows to address static TLS directly, 
 * via GS:[0] on IA32 and FS:[0] on x86_64
 * (see http://people.redhat.com/drepper/tls.pdf)
 * 
 *
 * APR_TLS_USE
 *
 *  When APR_TLS_USE is declared DRLVM uses APR functions for getting current
 *  thread. (currently it's used only on Windows 64-bit)
 *
 * If none of these defined, current thread id is kept in the tm_self_tls 
 * variable which is declared as __thread, thread local variable for gcc 
 * (*note Thread-Local:: in gcc.info), in thread_native_basic.c. This 
 * way it works on Linux.
 */
   


#ifdef _IPF_
    //don't use optimized asm monitor enter and exit helpers
#else
    //use optimized asm monitor enter and exit helpers
#   define ASM_MONITOR_HELPER
#endif


#if defined (_WIN32)
#   define HYTHREAD_FAST_TLS_ATTRIBUTE
#   if defined(_IA32_)
#       define HYTHREAD_FAST_TLS (1)
        // FIXME suggested to drop FS14_TLS_USE in favor of common HYTHREAD_FAST_TLS
#       define FS14_TLS_USE
#   else 
#       define APR_TLS_USE
#   endif
#elif defined(__linux__)
    // some kind of nix - the threads internals are known for IA32/Intel64
    // kernels, will use slow way on other HWs (TODO: add IPF support)
#   if defined(_IA32_) || defined(_EM64T_)
#       define HYTHREAD_FAST_TLS (1)
#       define HYTHREAD_FAST_TLS_ATTRIBUTE __attribute__((tls_model("initial-exec")))
#   endif
#elif defined(FREEBSD)
#   define APR_TLS_USE
#   define HYTHREAD_FAST_TLS_ATTRIBUTE   
#   undef HYTHREAD_FAST_TLS
#else
#   undef HYTHREAD_FAST_TLS
#endif

#if !defined(HYTHREAD_FAST_TLS)
#   define HYTHREAD_FAST_TLS_ATTRIBUTE   
#endif


#ifdef APR_TLS_USE

#ifdef __cplusplus
extern "C" {
#endif

hy_inline hythread_t VMCALL hythread_self() {
    extern hythread_t hythread_self_slow();
    return hythread_self_slow();
}


#define tm_self_tls (hythread_self_slow())

#ifdef __cplusplus
}
#endif

#elif defined FS14_TLS_USE

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

hy_inline hythread_t VMCALL hythread_self() {
#ifndef _WIN64
#   if (_MSC_VER >= 1400)
        // file winnt.h can't be included here
        // 0x14 = offsetof(NT_TIB, ArbitraryUserPointer)
        return (hythread_t) __readfsdword(0x14);
#   else
        register hythread_t t;
        _asm { mov eax, fs:[0x14]
               mov t, eax;
        }
        return t;
#   endif
#else
    // file winnt.h can't be included here
    // 0x28 = offsetof(NT_TIB, ArbitraryUserPointer)
    return (hythread_t) __readgsqword(0x28);
#endif
}

#define tm_self_tls (hythread_self())


#ifdef __cplusplus
}
#endif

#else

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#ifdef PLATFORM_POSIX
extern __thread hythread_t tm_self_tls;
#else
extern __declspec(thread) hythread_t tm_self_tls;
#endif //PLATFORM_POSIX


hy_inline hythread_t VMCALL hythread_self() {
    return tm_self_tls;
}

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/**
 * Returns a thread's TLS value.
 */
hy_inline void* VMCALL
hythread_tls_get(hythread_t thread, hythread_tls_key_t key) {
    return ((struct HyThread_public *)thread)->thread_local_storage[key];
}

#ifdef __cplusplus
}
#endif /* __cplusplus */



#if defined(__cplusplus)
}
#endif

#endif /* HYTHREAD_H */


