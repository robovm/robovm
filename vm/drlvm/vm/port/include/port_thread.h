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

#ifndef _PORT_THREAD_H_
#define _PORT_THREAD_H_

/**
 * @file port_thread.h
 * @brief PORT thread support
 */

/* osthread_t and thread_context_t types, and proper windows.h inclusion */
#include "open/hythread_ext.h"

#include "port_general.h"

/* Thread context definition for UNIX-like systems */
#if defined(LINUX) || defined(FREEBSD) 
#if defined(LINUX)

#include <sys/types.h>
#include <linux/unistd.h>
#include <errno.h>

#ifdef _syscall0
static _syscall0(pid_t, gettid)/* static definition */
#else /* _syscall0 */
#include <sys/syscall.h>
#include <unistd.h>
#define gettid() ((pid_t)syscall(__NR_gettid))
#endif /* _syscall0 */

#else /* !LINUX */
#include <sys/types.h>
#include <unistd.h>
#define gettid() getpid()
#endif

#endif /* LINUX || FREEBSD */


/* To skip platform_types.h inclusion */
typedef struct Registers Registers;

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */


/** @name OS thread operations
 */
//@{

PORT_INLINE int port_gettid()
{
#ifdef PLATFORM_POSIX
    return gettid();
#else
    return (int)GetCurrentThreadId();
#endif
}

/**
 * The type of pointer to thread start function
 * Function receives single argument - user-defined pointer
 */
typedef int (PORT_CDECL *port_threadfunc_t)(void*);

/**
 * Creates new thread.
 *
 * @param[out] handle on success, thread handle is stored in memory pointed by handle
 * @param stacksize size of stack to be allocated for a new thread
 * @param priority priority of a new thread
 * @param func function to be started on a new thread
 * @param data value to be passed to a function started on a new thread
 *
 * @return 0 on success, system error otherwise.
 */
int port_thread_create(osthread_t* phandle, size_t stacksize, int priority,
        port_threadfunc_t func, void *data);

/**
 * Attaches current thread to the porting library.
 *
 * This includes setting guard page in thread's stack memory and setting
 * alternative stack for stack overflow processing.
 * This function should be called for any thread which can produce expected
 * hardware signals/exceptions, to guarantee proper signals processing.
 *
 * @return 0 on success, system error otherwise.
 *
 * @sa port_thread_detach()
 */
int port_thread_attach();

/**
 * Detaches current thread from the porting library.
 * This includes restoring thread's stack settings.
 *
 * @return 0 on success, system error otherwise.
 *
 * @sa port_thread_attach()
 */
int port_thread_detach();

/**
 * Restores guard page after stack overflow processing, when
 * <code>port_thread_postpone_guard_page()</code> function was used
 * to postpone authomatic gurd page restoring.
 *
 * @return 0 on success, system error otherwise.
 *
 * @sa port_thread_postpone_guard_page()
 * @sa port_thread_clear_guard_page()
 */
int port_thread_restore_guard_page();

/**
 * Clears guard page in thread's stack on demand.
 * Guard page is cleared automatically for stack overflow processing; this
 * function allows clearing it when needed for other purposes.
 * Guard page can be restored with
 * <code>port_thread_postpone_guard_page()</code> function.
 *
 * @return 0 on success, system error otherwise.
 *
 * @sa port_thread_restore_guard_page()
 */
int port_thread_clear_guard_page();

/**
 * Postpones automatic guard page restoring after stack overflow processing.
 *
 * This function can be used inside of STACK_OVERFLOW signal callback to
 * disable automatic guard poge restoring. This is useful when the program
 * expects to consume some more stack before unwinding to SO handler.
 *
 * @sa port_thread_restore_guard_page()
 */
void port_thread_postpone_guard_page();


/**
 * Returns stack bottom address for the current thread.
 *
 * @return              NULL on error
 */
void* port_thread_get_stack_address();


/**
 * Returns total thread's stack size for the current thread.
 *
 * @return              0 on error
 */
size_t port_thread_get_stack_size();


/**
 * Returns effective stack size for the current thread.
 * Effective stack size is an area from stack bottom to guard page.
 *
 * Return value depends thread's state. When the thread is not in signal,
 * return value is a total stack size excluding system page, i.e.
 * (port_thread_get_stack_size() - <guard page size>). When processing a signal,
 * return value is the same when guard page is cleared, and is a size of area
 * from stack bottom to Port's guard page otherwise.
 *
 * @return              0 on error
 */
size_t port_thread_get_effective_stack_size();


/**
 * Adjusts priority of the running thread.
 *
 * @param thread        handle of thread
 * @param priority      new priority value
 *
 * @return              0 on success, system error otherwise
 */
int port_thread_set_priority(osthread_t thread, int priority);

/**
 * Returns os handle of the current thread.
 *
 * @return current thread handle on success, NULL on error
 *
 * @note The handle returned need to be freed by port_thread_free_handle()
 * @sa port_thread_free_handle()
 */
osthread_t port_thread_current();

/**
 * Frees thread handle returned by port_thread_current()
 *
 * @param os_thread     thread handle
 *
 * @sa port_thread_current()
 */
int port_thread_free_handle(osthread_t os_thread);

/**
 * Joins the os thread.
 *
 * @param os_thread     thread handle
 *
 * @return              0 on success, systerm error otherwise
 */
int port_thread_join(osthread_t os_thread);

/**
 * Terminates the os thread.
 *
 * @param os_thread     thread to terminate
 */
int port_thread_cancel(osthread_t os_thread);

/**
 * Causes the current thread to stop execution.
 *
 * @param status        return status of a thread
 */
void port_thread_exit(int status);

/**
 * Queries amount of user and kernel times consumed by the thread,
 * in nanoseconds.
 *
 * @param os_thread     thread handle
 * @param[out] pkernel  a pointer to a variable to store kernel time to
 * @param[out] puser    a pointer to a variable to store user time to
 *
 * @return      0 on success, system error otherwise
 */
int port_get_thread_times(osthread_t os_thread, int64* pkernel, int64* puser);

/**
 * Causes the other thread to have a memory barrier by suspending
 * and resuming it.
 *
 * @param thread     thread handle
 */
void port_thread_yield_other(osthread_t thread);

/**
 * Suspend given thread
 *
 * @param thread The thread to suspend
 */
int port_thread_suspend(osthread_t thread);

/**
 * Resume given thread
 *
 * @param thread The thread to resume
 */
int port_thread_resume(osthread_t thread);

/**
 * Returnd suspend count for the given thread
 *
 * @param thread The thread to check
 *
 * @return suspend count of the thread, -1 if error
 */
int port_thread_get_suspend_count(osthread_t thread);

/**
 * Get context for given thread
 *
 * @param thread The thread to process
 * @param context Pointer to platform-dependent context structure
 *
 * @return 0 if successful, -1 otherwise
 *
 * @note The thread must be suspended
 */
int port_thread_get_context(osthread_t thread, thread_context_t* pcontext);

/**
 * Set context for given thread
 *
 * @param thread The thread to process
 * @param context Pointer to platform-dependent context structure
 *
 * @return 0 if successful, -1 otherwise
 *
 * @note The thread must be suspended
 */
int port_thread_set_context(osthread_t thread, thread_context_t* pcontext);

/**
 * Translates platform-dependent thread context to Registers structure
 *
 * @param regs Pointer to Registers structure
 * @param context Pointer to platform-dependent context structure
 */
void port_thread_context_to_regs(Registers* regs, thread_context_t* context);

/**
 * Translates Registers structure to platform-dependent thread context
 *
 * @param context Pointer to platform-dependent context structure
 * @param regs Pointer to Registers structure
 */
void port_thread_regs_to_context(thread_context_t* context, Registers* regs);



//@}

#ifdef __cplusplus
}
#endif

#endif  /* _PORT_THREAD_H_ */
