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
 * @author Nikolay Kuznetsov
 */

/**
 * @file thread_native_basic.c
 * @brief hythread basic functions
 */

#define LOG_DOMAIN "tm.native"

#ifdef PLATFORM_POSIX
#   define hy_inline inline
#else
#   define hy_inline
#endif //PLATFORM_POSIX

#include <apr_atomic.h>
#include <open/hythread_ext.h>
#include "port_thread.h"
#include "port_mutex.h"
#include "thread_private.h"

extern hythread_group_t TM_DEFAULT_GROUP;
extern hythread_library_t TM_LIBRARY;
static int HYTHREAD_PROC hythread_wrapper_start_proc(void *arg);

#define NAKED __declspec(naked)

#if !defined (APR_TLS_USE)
    #if !defined(_WIN32)
        __thread hythread_t tm_self_tls HYTHREAD_FAST_TLS_ATTRIBUTE;
    #elif !defined(HYTHREAD_FAST_TLS)
        __declspec(thread) hythread_t tm_self_tls = NULL;
    #endif
#endif

#define MAX_ID 0x8000
hythread_t fast_thread_array[MAX_ID];
short next_free_thread_id[MAX_ID];
int next_id = 1;

/**
 * Creates a new thread in a given group.
 *
 * @param[in] new_thread a new allocated thread.
 * @param[in] group      a thread group or NULL
 *                       in case of NULL this thread will go to the default group.
 * @param[in] stacksize  a new thread stack size or 0
 *                       in case of 0 the thread will be set the default stack size
 * @param[in] priority   a new thread priority or 0
 *                       in case of 0 the thread will be set HYTHREAD_PRIORITY_NORMAL priority
 * @param[in] func       a function to run in the new thread
 * @param[in] data       an argument to be passed to starting function
 */
IDATA VMCALL hythread_create_ex(hythread_t new_thread,
                                hythread_group_t group,
                                UDATA stacksize,
                                UDATA priority,
                                hythread_wrapper_t wrapper,
                                hythread_entrypoint_t func,
                                void *data)
{
    int result;
    hythread_t self;

    assert(new_thread);
    hythread_struct_init(new_thread);

    self = hythread_self();
    new_thread->library = self ? self->library : TM_LIBRARY;
    new_thread->priority = priority ? priority : HYTHREAD_PRIORITY_NORMAL;
    
    if (!wrapper) {
        hythread_start_proc_data_t start_proc_data;

        // No need to zero allocated memory because all fields are initilized below.
        start_proc_data =
            (hythread_start_proc_data_t) malloc(sizeof(hythread_start_proc_data));
        if (start_proc_data == NULL) {
            return TM_ERROR_OUT_OF_MEMORY;
        }

        // Set up thread body procedure 
        start_proc_data->thread = new_thread;
        start_proc_data->group = group == NULL ? TM_DEFAULT_GROUP : group;
        start_proc_data->proc = func;
        start_proc_data->proc_args = data;

        data = (void*)start_proc_data;

        // Set wrapper procedure
        wrapper = hythread_wrapper_start_proc;
    }

    // Need to make sure thread will not register itself with a thread group
    // until port_thread_create returned and initialized thread->os_handle properly.
    hythread_global_lock();
    result = port_thread_create(&new_thread->os_handle,
            stacksize ? stacksize : TM_DEFAULT_STACKSIZE,
            priority, wrapper, data);
    assert(/* error */ result || new_thread->os_handle /* or thread created ok */);
    hythread_global_unlock();

    return result;
}

/**
 * Create a new OS thread.
 * 
 * The created thread is attached to the threading library.<br>
 * <br>
 * Unlike POSIX, this doesn't require an attributes structure.
 * Instead, any interesting attributes (e.g. stacksize) are
 * passed in with the arguments.
 *
 * @param[out] ret_thread a pointer to a hythread_t which will point to the thread (if successfully created)
 * @param[in] stacksize the size of the new thread's stack (bytes)<br>
 *                      0 indicates use default size
 * @param[in] priority priorities range from HYTHREAD_PRIORITY_MIN to HYTHREAD_PRIORITY_MAX (inclusive)
 * @param[in] suspend set to non-zero to create the thread in a suspended state.
 * @param[in] func pointer to the function which the thread will run
 * @param[in] data a value to pass to the entrypoint function
 *
 * @return  0 on success or negative value on failure
 *
 * @see hythread_exit, hythread_resume
 */
IDATA VMCALL hythread_create(hythread_t *handle, UDATA stacksize, UDATA priority, UDATA suspend, hythread_entrypoint_t func, void *data) {
    hythread_t thread = (hythread_t)calloc(1, hythread_get_struct_size());
    thread->need_to_free = 1;
    assert(thread);
    if (handle) {
        *handle = thread;
    }
    return hythread_create_ex(thread, NULL, stacksize, priority, NULL, func, data);
}

/**
 * Registers the current OS thread with the threading subsystem.
 *
 * @param[in] new_thread a new thread to register
 * @param[in] lib        a new thread library or NULL
 *                       in case of NULL this thread will go to the default group
 * @param[in] group      a new thread group or NULL
 *                       in case of NULL this thread will go to the default group
 */
IDATA hythread_attach_ex(hythread_t new_thread,
                         hythread_library_t lib,
                         hythread_group_t group)
{
    int res;
    IDATA status;
    hythread_t self = hythread_self();

    assert(new_thread);

    hythread_struct_init(new_thread);
    assert(lib == NULL);

    new_thread->library = TM_LIBRARY;
    if (self) {
        // to avoid creating multiple OS handles
        new_thread->os_handle = self->os_handle;
    } else {
        new_thread->os_handle = port_thread_current();
    }
    assert(new_thread->os_handle);

    res = port_thread_attach();
    // It's OK to have an error here when Port shared library
    // is not available yet; only signals/crash handling will
    // not be available for the thread
    //assert(res == 0);

    CTRACE(("TM: native attached: native: %p ", new_thread));

    status = hythread_set_to_group(new_thread,
        (group == NULL ? TM_DEFAULT_GROUP : group));
    hythread_set_self(new_thread);
    assert(new_thread == hythread_self());

    if (self) {
        // remove old attached thread
        hythread_remove_from_group(self);
        self->thread_id = new_thread->thread_id;
    }
    return status;
}

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
 * @note (*handle) should be NULL or point to hythread_t structure  
 * @see hythread_detach
 */
IDATA VMCALL hythread_attach(hythread_t *handle) {
    hythread_t thread;
    IDATA status = TM_ERROR_NONE;
    hythread_t self = hythread_self();

    if (self) {
        // thread is already attached, nothing to do
        thread = self;
    } else {
        // create thread
        thread = (hythread_t)calloc(1, hythread_get_struct_size());
        assert(thread);
        // attach thread
        status = hythread_attach_ex(thread, NULL, NULL);
    }
    if (handle) {
        *handle = thread;
    }
    return status;
}

/**
 * Detaches a thread from the threading library.
 * Assumes that the thread is being detached is already attached.
 * Frees a given thread pointer.
 * 
 * @param[in] thread A hythread_t representing the thread to be detached.
 *                   If this is NULL, the current thread is detached.
 */
void VMCALL hythread_detach(hythread_t thread) {
    hythread_detach_ex(thread);

    // FIXME - uncomment after TM state transition complete
    // release thread data
    //if (thread->need_to_free) {
    //    free(thread);
    //}
}

/**
 * Detaches a thread from the threading library.
 * Assumes that the thread is being detached is already attached.
 *
 * @param[in] thread A hythread_t representing the thread to be detached.
 *                   If this is NULL, the current thread is detached.
 */
void VMCALL hythread_detach_ex(hythread_t thread)
{
    IDATA status;

    // Acquire global TM lock to prevent concurrent access to thread list
    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    if (thread == NULL) {
        thread = hythread_self();
    }
    assert(thread);

    // Detach if thread is attached to group.
    hythread_remove_from_group(thread);

    if (thread == hythread_self()) // Detach current thread only
        port_thread_detach();

    // FIXME - uncomment after TM state transition complete
    // release thread data
    //hythread_struct_release(thread);
    
    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);
}

/**
 * Yield the processor.
 * 
 * @return none
 */
void VMCALL hythread_yield() {
    apr_thread_yield();
}

/**
 * Yield the processor for another thread.
 *
 * @return none
 */
void VMCALL hythread_yield_other(hythread_t thread) {
    assert(thread);
    if (hythread_is_alive(thread)) {
        port_thread_yield_other(thread->os_handle);
    }
}

/** 
 * Return the hythread_t for the current thread.
 * 
 * @note Must be called only by an attached thread
 * 
 * @return hythread_t for the current thread
 */
#ifdef APR_TLS_USE
hythread_t hythread_self_slow() {
    hythread_t  thread;
    apr_status_t UNUSED apr_status;

    // Extract hythread_t from TLS
    apr_status = apr_threadkey_private_get((void **)(&thread), TM_THREAD_KEY);
    assert(apr_status == APR_SUCCESS);

    return thread;
}

void VMCALL hythread_set_self(hythread_t  thread) {
    apr_threadkey_private_set(thread, TM_THREAD_KEY);
}
#else 
#if defined(_WIN32) && defined(HYTHREAD_FAST_TLS)

hythread_t hythread_self_slow() {
    return hythread_self();
}

void VMCALL hythread_set_self(hythread_t thread) {
#ifndef _WIN64
#   if (_MSC_VER >= 1400)
        __writefsdword(offsetof(NT_TIB, ArbitraryUserPointer), (unsigned long)thread);
#   else
        _asm{
            mov eax, thread
            mov fs:[0x14], eax
        }
#   endif
#else
    __writegsqword(offsetof(NT_TIB, ArbitraryUserPointer), thread);
#endif
}

#else // defined(_WIN32) && defined(HYTHREAD_FAST_TLS)

hythread_t hythread_self_slow() {
    return hythread_self();
}

void VMCALL hythread_set_self(hythread_t  thread) {
    tm_self_tls = thread;
}
#endif // defined(_WIN32) && defined(HYTHREAD_FAST_TLS)
#endif // defined APR_TLS_USE

IDATA thread_sleep_impl(I_64 millis, IDATA nanos, IDATA interruptable) {
    IDATA status;
    IDATA result;
    hythread_t self;
    hythread_monitor_t mon;

    if (nanos == 0 && millis == 0) {
        hythread_yield();
        return TM_ERROR_NONE;
    }
    if (!(self = hythread_self())) {
        // Report error in case current thread is not attached
        return TM_ERROR_UNATTACHED_THREAD;
    }

    // Grab thread monitor
    mon = self->monitor;
    status = hythread_monitor_enter(mon);
    assert(status == TM_ERROR_NONE);
    assert(mon->recursion_count == 0);
    mon->owner = NULL;
    mon->wait_count++;

    // Set thread state
    status = port_mutex_lock(&self->mutex);
    assert(status == TM_ERROR_NONE);
    self->waited_monitor = mon;
    self->state |= TM_THREAD_STATE_SLEEPING;
    status = port_mutex_unlock(&self->mutex);
    assert(status == TM_ERROR_NONE);

    do {
        apr_time_t start;
        assert(mon->notify_count >= 0);
        assert(mon->notify_count < mon->wait_count);
        start = apr_time_now();

        result = condvar_wait_impl(&mon->condition, &mon->mutex, millis, nanos, interruptable);
        if (result != TM_ERROR_NONE) {
            break;
        }
        // we should not change millis and nanos if both are 0 (meaning "no timeout")
        if (millis || nanos) {
            apr_interval_time_t elapsed = apr_time_now() - start;
            nanos -= (IDATA)((elapsed % 1000) * 1000);
            if (nanos < 0) {
                millis -= elapsed/1000 + 1;
                nanos += 1000000;
            } else {
                millis -= elapsed/1000;
            }
            if (millis < 0) {
                assert(status == TM_ERROR_NONE);
                status = TM_ERROR_TIMEOUT;
                break;
            }
            assert(0 <= nanos && nanos < 1000000);
        }
    } while(1);

    // Restore thread state
    status = port_mutex_lock(&self->mutex);
    assert(status == TM_ERROR_NONE);
    self->state &= ~TM_THREAD_STATE_SLEEPING;
    self->waited_monitor = NULL;
    status = port_mutex_unlock(&self->mutex);
    assert(status == TM_ERROR_NONE);

    // Release thread monitor
    mon->wait_count--;
    mon->owner = self;
    assert(mon->notify_count <= mon->wait_count);
    status = hythread_monitor_exit(mon);
    assert(status == TM_ERROR_NONE);

    if (self->request) {
        hythread_safe_point();
        hythread_exception_safe_point();
    }

    return (result == TM_ERROR_INTERRUPT && interruptable)
        ? TM_ERROR_INTERRUPT : TM_ERROR_NONE;
}

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
IDATA VMCALL hythread_sleep_interruptable(I_64 millis, IDATA nanos) {    
    return thread_sleep_impl(millis, nanos, WAIT_INTERRUPTABLE);
}

/** 
 * Suspend the current thread from executing 
 * for at least the specified time.
 *
 * @param[in] millis minimum number of milliseconds to sleep
 * @return  0 on success<br> HYTHREAD_INVALID_ARGUMENT if millis < 0
 *
 * @see hythread_sleep_interruptable
 */
IDATA VMCALL hythread_sleep(I_64 millis) {
    return thread_sleep_impl(millis, 0, WAIT_NONINTERRUPTABLE);
}

/**
 * Returns the id of the specific thread.
 * 
 * @return  0 on success
 */
IDATA VMCALL hythread_get_id(hythread_t t) {
    assert(t);
    return (IDATA)t->thread_id;
}

/**
 * Returns the id of the current thread.
 * @return  0 on success
 */
IDATA VMCALL hythread_get_self_id() {
    return (IDATA)tm_self_tls->thread_id;
}
/**
 * Returns the thread given the specific id.
 */
hythread_t VMCALL hythread_get_thread(IDATA id) {
    return fast_thread_array[id];
}

/**
 * Get thread group. 
 *
 * @param[out] group hythread_group_t* pointer to group
 * @param[in] thread hythread_t thread
 * @return  0 on success
 */
IDATA VMCALL hythread_get_group(hythread_group_t *group, hythread_t thread) {
    (*group) = thread->group;
    return TM_ERROR_NONE;
}

/** 
 * Terminates a running thread.
 * 
 * @note This should only be used as a last resort.  The system may be in
 * an unpredictable state once a thread is cancelled.  In addition, the thread
 * may not even stop running if it refuses to cancel.
 * 
 * @param[in] thread a thread to be terminated 
 * @return none
 */
void VMCALL hythread_cancel(hythread_t thread) {
    osthread_t os_handle = thread->os_handle;
    hythread_detach(thread);
    port_thread_cancel(os_handle);
    port_thread_join(os_handle);
}

/** 
 * Terminates all running threads in the given group.
 * 
 * @param[in] group thread group
 * @see hythread_cancel
 */
IDATA VMCALL hythread_cancel_all(hythread_group_t group) {
    hythread_iterator_t iter;
    hythread_t next;
    hythread_t self = tm_self_tls;

    if (!group) {
        group = TM_DEFAULT_GROUP;
    }
    
    iter = hythread_iterator_create(group);
    while ((next = hythread_iterator_next (&iter)) != NULL) {
        if (next != self) {
            hythread_cancel(next);
            //since this method being used at shutdown it does not
            //make any sense to exit on error, but continue terminating threads
        }       
    }
    hythread_iterator_release(&iter);

    return TM_ERROR_NONE;
}

//==============================================================================
// Private functions

/*
 */
IDATA VMCALL hythread_set_to_group(hythread_t thread, hythread_group_t group) {
    IDATA status;
    hythread_t cur, prev;

    assert(thread);
    assert(group);
    
    // Acquire global TM lock to prevent concurrent access to thread list
    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    assert(thread->os_handle);
    
    if (!thread->thread_id) {
        char free_slot_found = 0;

        unsigned int i;
        for(i = 0; i < MAX_ID; i++) {
            // increase next_id to allow thread_id change 
            next_id++;
            if (next_id == MAX_ID) {
	            next_id = 1;
            }
            if (fast_thread_array[next_id] == NULL) {
                thread->thread_id = next_id;
	            free_slot_found = 1;
                break;
            }
        }

        if (!free_slot_found) {
            status = hythread_global_unlock();
            assert(status == TM_ERROR_NONE);
            return TM_ERROR_OUT_OF_MEMORY;
        }
    }

    assert(thread->thread_id);
    fast_thread_array[thread->thread_id] = thread;

    thread->group = group;
    group->threads_count++;
    cur  = group->thread_list->next;
    prev = cur->prev;
    thread->next = cur;
    thread->prev = prev;
    prev->next = cur->prev = thread;

    port_mutex_lock(&thread->mutex);
    thread->state |= TM_THREAD_STATE_ALIVE | TM_THREAD_STATE_RUNNABLE;
    port_mutex_unlock(&thread->mutex);

    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);

    return TM_ERROR_NONE;
}

/**
 * Detach thread from group if it is attached to.
 */
IDATA VMCALL hythread_remove_from_group(hythread_t thread)
{
    IDATA status;

    if (!thread->group) {
        return TM_ERROR_NONE;
    }

    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    // The thread can be detached from the other thread in case
    // of forceful termination by hythread_cancel(), but thread
    // local storage can be zeroed only for current thread.
    if (thread == hythread_self() ) {
        hythread_set_self(NULL);
    }
    fast_thread_array[thread->thread_id] = NULL;

    thread->prev->next = thread->next;
    thread->next->prev = thread->prev;
    thread->group->threads_count--;
    thread->group = NULL;

    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);

    return TM_ERROR_NONE;
}

/**
 * Initializes a new thread structure.
 */
IDATA VMCALL hythread_struct_init(hythread_t new_thread) 
{
    char jstatus;
    IDATA status;

    assert(new_thread);
    jstatus = new_thread->java_status;
    if (!new_thread->os_handle) {
        // new thread, create thread primitives
        memset(new_thread, 0, sizeof(HyThread));
        status = hysem_create(&new_thread->resume_event, 0, 1);
        assert(status == TM_ERROR_NONE);
        status = port_mutex_create(&new_thread->mutex, APR_THREAD_MUTEX_NESTED);
        assert(status == TM_ERROR_NONE);
        status = hythread_monitor_init(&new_thread->monitor, 0);
        assert(status == TM_ERROR_NONE);
    } else {
        // old thread, reset structure
        int result;
        hysem_t resume;
        osmutex_t mutex;
        hythread_monitor_t monitor;

        // release thread OS handle
        result = port_thread_free_handle(new_thread->os_handle);
        assert(0 == result);

        resume = new_thread->resume_event;
        mutex = new_thread->mutex;
        monitor = new_thread->monitor;

        // zero new thread
        memset(new_thread, 0, sizeof(HyThread));

        new_thread->resume_event = resume;
        new_thread->mutex = mutex;
        new_thread->monitor = monitor;
    }
    assert(new_thread->os_handle == 0);

    new_thread->java_status = jstatus;
    new_thread->priority   = HYTHREAD_PRIORITY_NORMAL;

    port_mutex_lock(&new_thread->mutex);
    new_thread->state = TM_THREAD_STATE_NEW;
    port_mutex_unlock(&new_thread->mutex);

    status = hysem_set(new_thread->resume_event, 0);
    assert(status == TM_ERROR_NONE);

    return TM_ERROR_NONE;
}

/**
 * Releases thread structure.
 */
IDATA VMCALL hythread_struct_release(hythread_t thread)
{
    IDATA status;

    assert(thread);

    // Release thread primitives
    status = hysem_destroy(thread->resume_event);
    assert(status == TM_ERROR_NONE);
    status = port_mutex_destroy(&thread->mutex);
    assert(status == TM_ERROR_NONE);
    status = hythread_monitor_destroy(thread->monitor);
    assert(status == TM_ERROR_NONE);

    memset(thread, 0, hythread_get_struct_size());
    return TM_ERROR_NONE;
}

/**
 * Wrapper around user thread start proc.
 * Used to perform some duty jobs right after thread is started
 * and before thread is finished.
 */
static int HYTHREAD_PROC hythread_wrapper_start_proc(void *arg) {
    IDATA UNUSED status;
    hythread_t thread;
    hythread_start_proc_data start_proc_data;
    hythread_entrypoint_t start_proc;
    
    // store procedure arguments to local
    start_proc_data = *(hythread_start_proc_data_t) arg;
    free(arg);

    // get hythread global lock
    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    // get native thread
    thread = start_proc_data.thread;
    start_proc = start_proc_data.proc;

    CTRACE(("TM: native thread started: native: %p tm: %p",
        port_thread_current(), thread));

    // check hythread library state
    if (hythread_lib_state() != TM_LIBRARY_STATUS_INITIALIZED) {
        // set TERMINATED state
        port_mutex_lock(&thread->mutex);
        thread->state = TM_THREAD_STATE_TERMINATED;
        port_mutex_unlock(&thread->mutex);

        // set hythread_self()
        hythread_set_self(thread);
        assert(thread == hythread_self());

        // release thread structure data
        hythread_detach(thread);

        // zero hythread_self() because we don't do it in hythread_detach_ex()
        hythread_set_self(NULL);

        CTRACE(("TM: native thread terminated due to shutdown: native: %p tm: %p",
            port_thread_current(), thread));

        // release hythread global lock
        status = hythread_global_unlock();
        assert(status == TM_ERROR_NONE);

        return 0;
    }

    // register to group and set ALIVE & RUNNABLE states
    status = hythread_set_to_group(thread, start_proc_data.group);
    assert(status == TM_ERROR_NONE);

    // set hythread_self()
    hythread_set_self(thread);
    assert(thread == hythread_self());

    // set priority
    status = hythread_set_priority(thread, thread->priority);
    // FIXME - cannot set priority
    //assert(status == TM_ERROR_NONE);

    // release hythread global lock
    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);

    // Do actual call of the thread body supplied by the user.
    start_proc(start_proc_data.proc_args);

    assert(hythread_is_suspend_enabled());

    // get hythread global lock
    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    // set TERMINATED state
    port_mutex_lock(&thread->mutex);
    thread->state = TM_THREAD_STATE_TERMINATED;
    port_mutex_unlock(&thread->mutex);

    // detach and free thread
    hythread_detach(thread);

    // release hythread global lock
    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);

    return 0;
}

extern HY_CFUNC void VMCALL 
hythread_exit (hythread_monitor_t monitor) {
   
    if (monitor !=NULL && monitor->owner == hythread_self()) {
        monitor->recursion_count = 0;
        hythread_monitor_exit(monitor);
    }
    hythread_detach_ex(NULL);
    port_thread_exit(0);
    // unreachable statement
    abort();
}

/**
 * Queries user and kernel time of the thread, in nanoseconds.
 *
 * @param thread        thread block pointer
 * @param[out] pkernel  pointer to a variable to store kernel time into
 * @param[out] puser    pointer to a variable to store user time into
 *
 * @returns     0 on success, system error code otherwise
 */
UDATA hythread_get_thread_times(hythread_t thread, int64* pkernel, int64* puser) {
    return port_get_thread_times(thread->os_handle, pkernel, puser);
}


IDATA VMCALL hythread_thread_lock(hythread_t thread) {
    assert(thread);
    return port_mutex_lock(&thread->mutex);
} // hythread_thread_lock

IDATA VMCALL hythread_thread_unlock(hythread_t thread) {
    assert(thread);
    return port_mutex_unlock(&thread->mutex);
} // hythread_thread_unlock

IDATA VMCALL hythread_get_state(hythread_t thread) {
    IDATA state;
    assert(thread);
    port_mutex_lock(&thread->mutex);
    state = thread->state;
    port_mutex_unlock(&thread->mutex);
    return state;
} // hythread_get_state

IDATA VMCALL hythread_set_state(hythread_t thread, IDATA state) {
    assert(thread);
    port_mutex_lock(&thread->mutex);
    thread->state = state;
    port_mutex_unlock(&thread->mutex);
    return TM_ERROR_NONE;
} // hythread_set_state

IDATA VMCALL hythread_get_thread_id_offset() {
    return (U_32)(size_t)&((HyThread *)0)->thread_id;
} // hythread_get_thread_id_offset

IDATA VMCALL hythread_set_thread_stop_callback(hythread_t thread,
    tm_thread_event_callback_proc stop_callback)
{
    IDATA status = hythread_set_safepoint_callback(thread, stop_callback);

    while (thread->suspend_count > 0) {
        apr_atomic_dec32((volatile apr_uint32_t *)
            &thread->suspend_count);
        apr_atomic_dec32((volatile apr_uint32_t *)
            &thread->request);
    }

    // if there is no competition, it would be 1, but if someone else is
    // suspending the same thread simultaneously, it could be greater than 1
    // if safepoint callback isn't set it could be equal to 0.
    //
    // The following assertion may be false because at each time
    // one of the conditions is true, and the other is false, but
    // when checking the whole condition it may be failse in the result.
    // assert(thread->request > 0 || thread->safepoint_callback == NULL);

    // notify the thread that it may wake up now,
    // so that it would eventually reach exception safepoint
    // and execute callback
    hysem_post(thread->resume_event);

    if (thread->state &
            (TM_THREAD_STATE_SLEEPING | TM_THREAD_STATE_WAITING_WITH_TIMEOUT
                | TM_THREAD_STATE_WAITING | TM_THREAD_STATE_IN_MONITOR_WAIT
                | TM_THREAD_STATE_WAITING_INDEFINITELY | TM_THREAD_STATE_PARKED))
    {
        // This is needed for correct stopping of a thread blocked on monitor_wait.
        // The thread needs some flag to exit its waiting loop.
        // We piggy-back on interrupted status. A correct exception from TLS
        // will be thrown because the check of exception status on leaving
        // JNI frame comes before checking return status in Object.wait().
        // Interrupted status will be cleared by function returning TM_ERROR_INTERRUPT.
        // (though, in case of parked thread, it will not be cleared)
        hythread_interrupt(thread);
    }
    return status;
} // hythread_set_thread_stop_callback

IDATA VMCALL hythread_wait_for_nondaemon_threads(hythread_t thread, IDATA threads_to_keep)
{
    IDATA status;
    hythread_library_t lib;

    assert(thread);
    lib = thread->library;

    status = port_mutex_lock(&lib->TM_LOCK);
    if (status != TM_ERROR_NONE) {
        return status;
    }

    while (lib->nondaemon_thread_count - threads_to_keep > 0)
    {
        // check interruption and other problems
        status = hycond_wait(&lib->nondaemon_thread_cond, &lib->TM_LOCK);

        CTRACE(("TM wait for nondaemons notified, count: %d",
               lib->nondaemon_thread_count));

        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&lib->TM_LOCK);
            return status;
        }
    }

    status = port_mutex_unlock(&lib->TM_LOCK);
    return status;
} // hythread_wait_for_nondaemon_threads

IDATA VMCALL hythread_increase_nondaemon_threads_count(hythread_t thread)
{
    hythread_library_t lib = thread->library;
    IDATA status = port_mutex_lock(&lib->TM_LOCK);
    if (status != TM_ERROR_NONE) {
        return status;
    }
    lib->nondaemon_thread_count++;
    status = port_mutex_unlock(&lib->TM_LOCK);
    return status;
} // hythread_increase_nondaemon_threads_count_in_library

IDATA VMCALL hythread_decrease_nondaemon_threads_count(hythread_t thread, IDATA threads_to_keep)
{
    hythread_library_t lib = thread->library;
    IDATA status = port_mutex_lock(&lib->TM_LOCK);
    if (status != TM_ERROR_NONE) {
        return status;
    }

    if (lib->nondaemon_thread_count <= 0) {
        status = port_mutex_unlock(&lib->TM_LOCK);
        if (status != TM_ERROR_NONE) {
            return status;
        }
        return TM_ERROR_ILLEGAL_STATE;
    }

    CTRACE(("TM: nondaemons decreased, thread: %p count: %d\n", thread,
           lib->nondaemon_thread_count));

    lib->nondaemon_thread_count--;
    if (lib->nondaemon_thread_count - threads_to_keep <= 0) {
        status = hycond_notify_all(&lib->nondaemon_thread_cond);
        CTRACE(("TM: nondaemons all dead, thread: %p count: %d\n", thread,
               lib->nondaemon_thread_count));
        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&lib->TM_LOCK);
            return status;
        }
    }

    status = port_mutex_unlock(&lib->TM_LOCK);
    return status;
} // hythread_countdown_nondaemon_threads

IDATA VMCALL hythread_get_struct_size()
{
    return (IDATA)sizeof(HyThread);
} // hythread_get_struct_size

/**
 * Returns OS thread ID.
 */
IDATA hythread_get_os_id(hythread_t thread)
{
#if 1 || defined(PLATFORM_POSIX)
    return 0;
#else
    return (IDATA)GetThreadId(thread->os_handle);
#endif // PLATFORM_POSIX
} // hythread_get_os_id
