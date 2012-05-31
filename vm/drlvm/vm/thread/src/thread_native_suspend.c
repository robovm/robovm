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
 * @file thread_native_suspend.c
 * @brief Hythread suspend/resume related functions
 */

#define LOG_DOMAIN "tm.suspend"

#include <open/hythread_ext.h>
#include <apr_atomic.h>
#include "port_barriers.h"
#include "port_mutex.h"
#include "thread_private.h"

static void thread_safe_point_impl(hythread_t thread);

/** @name Safe suspension support
 */
//@{

/**
 * Denotes a single point where safe exception throwing is possible.
 */
void VMCALL hythread_exception_safe_point()
{
    hythread_t self = tm_self_tls;
    int gc_disable_count;
    hythread_event_callback_proc callback_func;

    if (!self->safepoint_callback) {
        return;
    }

    /**
     * There is a contract between disable_count value and Java code:
     * during Java code execution disable_count should be equal to 1.
     * It means that garbage collection while in native code can only
     * take place when the native code makes disable_count equal to zero.
     * To do this, the native code must guarantee that the GC is able to
     * see all live references the native code is working with. And that
     * the native code will not read/write live references while disable_count
     * is equal to zero. This happens within suspension safe points or
     * within VM helpers safe regions.
     *
     * Safepoint callback function could call Java code. If this happens,
     * disable_count must be set to 1 before callback function execution.
     *
     * Field disable_count cannot be changed by another thread. Its value
     * changes only by the same thread. So there is no race condition or
     * separate usage in setting disable_count to 1.
     */
    gc_disable_count = self->disable_count;
    self->disable_count = 1;

    // Clear callback (this is one-time event)
    callback_func = self->safepoint_callback;
    self->safepoint_callback = NULL;

    // remove safe point callback request
    apr_atomic_dec32(&self->request);

    callback_func();

    // restore disable_count
    self->disable_count = gc_disable_count;

    return;
}


/**
 * Denotes a single point where safe suspension is possible.
 *
 * If there was a suspension request set for this thread, this method notifies
 * the requesting thread and then blocks until someone calls the
 * hythread_resume() function for this thread.
 * <p>
 * A thread marks itself with functions hythread_suspend_enable()
 * and hythread_suspend_disable() in order to denote a safe region of code.
 * A thread may also call hythread_safe_point() method to denote a selected
 * point where safe suspension is possible.
 */
void VMCALL hythread_safe_point()
{
    thread_safe_point_impl(tm_self_tls);
}

/**
 * Same as hythread_safe_point, but inserts safe point for given thread 
 * other thread.
 */
void VMCALL hythread_safe_point_other(hythread_t thread)
{
    thread_safe_point_impl(thread);
}


/**
 * Denotes a single point where safe suspension is
 * possible for a given thread.
 */
static void thread_safe_point_impl(hythread_t thread)
{
    int gc_disable_count;

    if (!thread->suspend_count) {
        return;
    }

    // the thread is entering to the safe region,
    // set disable count to 0 (safe region value)
    gc_disable_count = thread->disable_count;
    thread->disable_count = 0;
    port_rw_barrier();

    do {
        CTRACE(("safe point enter: thread: %p, suspend_count: %d, request: %d",
               thread, thread->suspend_count, thread->request));

        // wait for resume event
        hysem_wait(thread->resume_event);

        CTRACE(("safe point exit: thread: %p, suspend_count: %d, request: %d",
               thread, thread->suspend_count, thread->request));
    } while (thread->suspend_count);

    // restore disable_count value
    hythread_set_suspend_disable(gc_disable_count);

    return;
} // thread_safe_point_impl


/**
 * Starts suspension of a given thread.
 * The method increases suspend_count and sets request.
 * Calling wait_safe_region_event() could be called
 * to wait for safe region or safe point.
 */
void VMCALL hythread_send_suspend_request(hythread_t thread)
{
    // increment suspend count
    apr_atomic_inc32(&thread->suspend_count);
    apr_atomic_inc32(&thread->request);

    if (thread != hythread_self()) {
        // notify target thread about suspend request change
        hythread_yield_other(thread);
    }
}

/**
 * Waits until a given thread reaches a safe region.
 * The method is called after hythread_send_suspend_request() function
 * as the second part of suspension.
 */
static IDATA wait_safe_region_event(hythread_t thread)
{
    hythread_t self = tm_self_tls;

    assert(thread->suspend_count);
    assert(thread != self);

    // We need to wait for notification only in case the thread is in
    // the unsafe/disable region. All threads should be stoped in
    // safepoints or be in safe region.
    while (thread->disable_count != 0) {
        // HIT cyclic suspend
        if (self->request) {
            return TM_ERROR_EBUSY;
        }
        hythread_yield();
    }
    CTRACE(("suspend wait exit safe region thread: "
           "%p, suspend_count: %d, request: %d",
           thread, thread->suspend_count, thread->request));
    port_mutex_lock(&thread->mutex);
    thread->state |= TM_THREAD_STATE_SUSPENDED;
    port_mutex_unlock(&thread->mutex);
    return TM_ERROR_NONE;
}

/**
 * Suspends the current thread. 
 * 
 * Stop the current thread from executing until it is resumed.
 *
 * @see hythread_resume
 */
void VMCALL hythread_suspend()
{
    hythread_t self = tm_self_tls;

    hythread_send_suspend_request(self);

    port_mutex_lock(&self->mutex);
    self->state |= TM_THREAD_STATE_SUSPENDED;
    port_mutex_unlock(&self->mutex);

    thread_safe_point_impl(self);
}

/**
 * Safely suspends the <code>thread</code> execution.
 *
 * The safe suspension acts as follows:
 * <ul>
 * <li>
 * If the <code>thread</code> is currently running in safe code region, this
 * method immediately returns back.
 * The <code>thread</code> itself runs until it reaches the end of safe region
 * and then blocks until someone calls hythread_resume() for it.
 * <li>
 * If the <code>thread</code> is currently in unsafe region, this
 * method blocks until the <code>thread</code> either reaches the beginning 
 * of a safe region, or reaches a safe point. 
 * Once reached safe point or end of safe region, the<code>thread</code> blocks
 * until someone calls hythread_resume() for it.
 * </ul>
 * A thread marks itself with functions hythread_suspend_enable()
 * and hythread_suspend_disable() in order to denote a safe region of code.
 * A thread may also call hythread_safe_point() method to denote a selected
 * point where safe suspension is possible.
 *
 * @param[in] thread thread to be suspended
 * @return TM_ERROR_EBUSY if deadlock, TM_ERROR_NONE if OK  
 */
IDATA VMCALL hythread_suspend_other(hythread_t thread)
{
    hythread_t self = tm_self_tls;

    CTRACE(("suspend enter, self: %p, thread: %p, "
           "suspend_count: %d, request: %d", self, thread,
           thread->suspend_count, thread->request));

    if (self == thread) {
        // suspend self
        hythread_suspend();
        return TM_ERROR_NONE;
    }

    // suspend another thread
    hythread_send_suspend_request(thread);
    if (wait_safe_region_event(thread) != TM_ERROR_NONE) {
        hythread_resume(thread);
        return TM_ERROR_EBUSY;
    }
    CTRACE(("suspend exit, self: %p, thread: %p, "
           "suspend_count: %d, request: %d", self, thread,
           thread->suspend_count, thread->request));
    return TM_ERROR_NONE;
}

/**
 * Resumes a thread.
 *
 * Take a threads out of the suspended state.
 * If the thread is not suspended, no action is taken.
 *
 * @param[in] thread a thread to be resumed
 *
 * @see hythread_create, hythread_suspend
 */
void VMCALL hythread_resume(hythread_t thread)
{
    int count;
    hythread_t self = tm_self_tls;

    CTRACE(("start resume, self: %p, thread: %p, "
           "suspend_count: %d, request: %d", self, thread,
           thread->suspend_count, thread->request));

    do {
        count = thread->suspend_count;
        if (!count) {
            return;
        }
    } while (apr_atomic_cas32(&thread->suspend_count, count - 1, count) != count);

    // If there was request for suspension,
    // decrease the suspend counter and remove request
    apr_atomic_dec32(&thread->request);

    if (thread->suspend_count) {
        return;
    }

    // change thread state
    port_mutex_lock(&thread->mutex);
    thread->state &= ~TM_THREAD_STATE_SUSPENDED;
    port_mutex_unlock(&thread->mutex);

    CTRACE(("sent resume, self: %p, thread: %p, "
           "suspend_count: %d, request: %d", self, thread,
           thread->suspend_count, thread->request));

    // notify the thread that it may wake up now
    hysem_post(thread->resume_event);
    return;
}

/**
 * Sets safepoint callback function.
 * 
 * Callback function is executed at safepoint in case there was
 * a safepoint callback request.
 *  
 * @param[in] thread thread where callback needs to be executed
 * @param[in] callback callback function
 */
IDATA hythread_set_safepoint_callback(hythread_t thread,
                                      tm_thread_event_callback_proc callback)
{
    hythread_t self = tm_self_tls;

    while (apr_atomic_casptr((volatile void **) &thread->safepoint_callback,
                             callback, NULL) != NULL)
    {
        thread_safe_point_impl(self);
        hythread_yield();
    }

    // set safe point callback request
    apr_atomic_inc32(&thread->request);

    if (self == thread) {
        hythread_exception_safe_point();
    } else {
        // sent an interupt signal for waiting threads
        if (thread->current_condition || thread->waited_monitor) {
            hythread_interrupt(thread);
        }
    }
    return TM_ERROR_NONE;
}

/**
 * Helps to safely suspend the threads in the selected group.
 *
 * The method sets a suspend request for the every thread in the group
 * and then returns the iterator that can be used to traverse through
 * the suspended threads.
 * Each invocation of the hythread_iterator_next() method on the iterator
 * will return the next suspended thread.
 *
 * @param[out] iter_ptr iterator
 * @param[in] group thread group to be suspended
 */
IDATA VMCALL hythread_suspend_all(hythread_iterator_t* iter_ptr,
                                  hythread_group_t group)
{
    hythread_t self = tm_self_tls;
    hythread_t next;
    hythread_iterator_t iter;
    CTRACE(("suspend all threads"));

    assert(hythread_is_suspend_enabled());

    // try to prevent cyclic suspend dead-lock
    thread_safe_point_impl(self);

    // get global lock to prevent new thread creation
    hythread_global_lock();

    // send suspend requests to all threads
    CTRACE(("send suspend requests"));
    iter = hythread_iterator_create(group);
    while ((next = hythread_iterator_next(&iter)) != NULL) {
        if (next != self) {
            hythread_send_suspend_request(next);
        }
    }
    hythread_iterator_reset(&iter);

    // all threads should be stoped in safepoints or be in safe region.
    CTRACE(("wait suspend responses"));
    while ((next = hythread_iterator_next(&iter)) != NULL) {
        if (next == self) {
            continue;
        }
        while (wait_safe_region_event(next) != TM_ERROR_NONE) {
            thread_safe_point_impl(self);
            hythread_yield();
        }
    }

    hythread_iterator_reset(&iter);
    hythread_iterator_release(&iter);
    if (iter_ptr) {
        *iter_ptr = iter;
    }

    return TM_ERROR_NONE;
}

/**
 * Resumes all threads in the selected group.
 *
 * @param[in] group thread group to be resumed
 */
IDATA VMCALL hythread_resume_all(hythread_group_t group)
{
    hythread_t self = tm_self_tls;
    hythread_t next;
    hythread_iterator_t iter;

    CTRACE(("resume all threads"));

    // send resume requests to all threads
    iter = hythread_iterator_create(group);
    while ((next = hythread_iterator_next(&iter)) != NULL) {
        if (next != self) {
            hythread_resume(next);
        }
    }
    hythread_iterator_release(&iter);

    // release global lock which was got in hythread_suspend_all()
    hythread_global_unlock();

    return TM_ERROR_NONE;
}

/**
 * Reset disable_count for currect thread.
 * The method begins suspension safe region.
 * Field disable_count is restored in hythread_set_suspend_disable() function.
 */
int VMCALL hythread_reset_suspend_disable()
{
    hythread_t self = tm_self_tls;
    int disable_count = self->disable_count;
    self->disable_count = 0;

    return disable_count;
}

/**
 * Restores disable_count for current thread,
 * which was reset in hythread_reset_suspend_disable() function
 * If restored value ends suspension safe region
 * and there was a suspension request set for this thread,
 * the method invokes hythread_safe_point() function.
 */
void VMCALL hythread_set_suspend_disable(int count)
{
    hythread_t self = tm_self_tls;

    assert(count >= 0);
    self->disable_count = count;

    port_rw_barrier();

    if (count && self->suspend_count) {
        thread_safe_point_impl(self);
    }
}

//@}
