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
 * @file thread_native_fat_monitor.c
 * @brief Hythread fat monitors related functions
 */

/** @name Fat monitors support. Implement thin-fat scheme.
 */
//@{

#define LOG_DOMAIN "tm.locks"

#include <open/hythread_ext.h>
#include "port_mutex.h"
#include "thread_private.h"

/**
 * Acquire and initialize a new monitor from the threading library.
 *
 * @param[out] mon_ptr pointer to a hythread_monitor_t to be set to point to the new monitor
 * @param[in] flags initial flag values for the monitor
 * @param[in] name pointer to a C string with a description of how the monitor will be used (may be NULL)<br>
 * If non-NULL, the C string must be valid for the entire life of the monitor
 *
 * @return  0 on success or negative value on failure
 *
 * @see hythread_monitor_destroy
 *
 */
IDATA VMCALL hythread_monitor_init_with_name(hythread_monitor_t *mon_ptr, UDATA flags, const char *name) {
    int r;
    hythread_monitor_t mon;

    mon = calloc(1, sizeof(HyThreadMonitor));
    if (mon == NULL) {
        return TM_ERROR_OUT_OF_MEMORY;
    }
    r = port_mutex_create(&mon->mutex, APR_THREAD_MUTEX_NESTED);
    if (r) {
        goto cleanup;
    }
    r = hycond_create(&mon->condition);
    if (r) {
        goto cleanup;
    }

    mon->flags = flags;
    mon->name  = name;

    *mon_ptr = mon;
    return TM_ERROR_NONE;

cleanup:
    free(mon);
    return r;
}

/**
 * Enter a monitor.
 *
 * A thread may re-enter a monitor it owns multiple times, but must
 * exit the monitor the same number of times before any other thread
 * wanting to enter the monitor is permitted to continue.
 *
 * @param[in] mon_ptr a monitor to be entered
 * @return 0 on success<br>
 * HYTHREAD_PRIORITY_INTERRUPTED if the thread was priority interrupted while blocked
 *
 * @see hythread_monitor_enter_using_threadId, hythread_monitor_exit, hythread_monitor_exit_using_threadId
 */
IDATA VMCALL hythread_monitor_enter(hythread_monitor_t mon_ptr) {
    IDATA status;
    hythread_t  self = tm_self_tls;
    if (mon_ptr->owner != self) {
        status = port_mutex_lock(&mon_ptr->mutex);
        mon_ptr->owner = self;
        assert(status == TM_ERROR_NONE);
    } else {
        assert(mon_ptr->recursion_count >=0);
        mon_ptr->recursion_count++;
        status = TM_ERROR_NONE;
    }
    return status;
}


/**
 * Attempt to enter a monitor without blocking.
 *
 * If the thread must block before it enters the monitor this function
 * returns immediately with a negative value to indicate failure.
 *
 * @param[in] mon_ptr a monitor
 * @return  0 on success or negative value on failure
 *
 * @see hythread_monitor_try_enter_using_threadId
 *
 */
IDATA VMCALL hythread_monitor_try_enter(hythread_monitor_t mon_ptr) {
    IDATA status;
    hythread_t self = tm_self_tls;
    if (mon_ptr->owner != self) {
        status = port_mutex_trylock(&mon_ptr->mutex);
        if (status == TM_ERROR_NONE) {
            mon_ptr->owner = tm_self_tls;
        }
        return status;
    } else {
        assert(mon_ptr->recursion_count >=0);
        mon_ptr->recursion_count++;
        return TM_ERROR_NONE;
    }
}

/**
 * Exit a monitor.
 *
 * Exit a monitor, and if the owning count is zero, release it.
 *
 * @param[in] mon_ptr a monitor to be exited
 * @return 0 on success, <br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 *
 * @see hythread_monitor_exit_using_threadId, hythread_monitor_enter, hythread_monitor_enter_using_threadId
 */
IDATA VMCALL hythread_monitor_exit(hythread_monitor_t mon_ptr) {
    IDATA status = TM_ERROR_NONE;
    assert(mon_ptr->recursion_count >= 0);

    if (mon_ptr->owner != tm_self_tls) {
        CTRACE(("exit TM_ERROR_ILLEGAL_STATE  owner: %d self: %d, rec: %d\n",
                mon_ptr->owner ? mon_ptr->owner->thread_id : 0,
                tm_self_tls->thread_id, mon_ptr->recursion_count));
        return TM_ERROR_ILLEGAL_STATE;
    }
    if (mon_ptr->recursion_count == 0) {
        mon_ptr->owner = NULL;
        status = port_mutex_unlock(&mon_ptr->mutex);
    } else {
        mon_ptr->recursion_count--;
    }
    assert(status == TM_ERROR_NONE);
    return status;
}


IDATA monitor_wait_impl(hythread_monitor_t mon_ptr, I_64 ms, IDATA nano, IDATA interruptable) {
    IDATA status;
    int saved_recursion;
    //int saved_disable_count;
    hythread_t self = tm_self_tls;
    if (mon_ptr->owner != self) {
        return TM_ERROR_ILLEGAL_STATE;
    }

    saved_recursion = mon_ptr->recursion_count;

    assert(saved_recursion>=0);

    mon_ptr->owner = NULL;
    mon_ptr->recursion_count =0;
    mon_ptr->wait_count++;
    port_mutex_lock(&self->mutex);
    self->state |= TM_THREAD_STATE_IN_MONITOR_WAIT;
    self->waited_monitor = mon_ptr;
    port_mutex_unlock(&self->mutex);

    do {
        apr_time_t start;
        assert(mon_ptr->notify_count >= 0);
        assert(mon_ptr->notify_count < mon_ptr->wait_count);
        start = apr_time_now();
        status = condvar_wait_impl(&mon_ptr->condition, &mon_ptr->mutex, ms, nano, interruptable);
        if (status != TM_ERROR_NONE || mon_ptr->notify_count) {
            break;
        }
        // we should not change ms and nano if both are 0 (meaning "no timeout")
        if (ms || nano) {
            apr_interval_time_t elapsed;
            elapsed = apr_time_now() - start; // microseconds
            nano -= (IDATA)((elapsed % 1000) * 1000);
            if (nano < 0) {
                ms -= elapsed/1000 + 1;
                nano += 1000000;
            } else {
                ms -= elapsed/1000;
            }
            if (ms < 0) {
                assert(status == TM_ERROR_NONE);
                status = TM_ERROR_TIMEOUT;
                break;
            }
            assert(0 <= nano && nano < 1000000);
        }
    } while (1);

    // consume the notify_count unless we got an error (or were interrupted)
    if (mon_ptr->notify_count > 0
        && (status == TM_ERROR_NONE || mon_ptr->notify_count == mon_ptr->wait_count))
    {
        mon_ptr->notify_count--;
    }

    port_mutex_lock(&self->mutex);
    self->state &= ~TM_THREAD_STATE_IN_MONITOR_WAIT;
    self->waited_monitor = NULL;
    port_mutex_unlock(&self->mutex);

    mon_ptr->wait_count--;
    assert(mon_ptr->notify_count <= mon_ptr->wait_count);

    if (self->request) {
        int save_count;
        port_mutex_unlock(&mon_ptr->mutex);
        hythread_safe_point();
        hythread_exception_safe_point();
        save_count = hythread_reset_suspend_disable();
        port_mutex_lock(&mon_ptr->mutex);
        hythread_set_suspend_disable(save_count);
    }

    mon_ptr->recursion_count = saved_recursion;
    mon_ptr->owner = self;
    assert(mon_ptr->owner);
    return status;
}

/**
 * Wait on a monitor until notified.
 *
 * Release the monitor, wait for a signal (notification), then re-acquire the monitor.
 *
 * @param[in] mon_ptr a monitor to be waited on
 * @return 0 if the monitor has been waited on, notified, and reobtained<br>
 * HYTHREAD_INVALID_ARGUMENT if millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 *
 * @see hythread_monitor_wait_interruptable, hythread_monitor_wait_timed, hythread_monitor_enter
 *
 */
IDATA VMCALL hythread_monitor_wait(hythread_monitor_t mon_ptr) {
    return monitor_wait_impl(mon_ptr,0, 0, WAIT_NONINTERRUPTABLE);
}

/**
 * Wait on a monitor until notified or timed out.
 *
 * A timeout of 0 (0ms, 0ns) indicates wait indefinitely.
 *
 * @param[in] mon_ptr a monitor to be waited on
 * @param[in] ms >=0
 * @param[in] nano >=0
 *
 * @return  0 the monitor has been waited on, notified, and reobtained<br>
 * HYTHREAD_INVALID_ARGUMENT millis or nanos is out of range (millis or nanos < 0, or nanos >= 1E6)<br>
 * HYTHREAD_ILLEGAL_MONITOR_STATE the current thread does not own the monitor<br>
 * HYTHREAD_TIMED_OUT the timeout expired
 *
 * @see hythread_monitor_wait, hythread_monitor_wait_interruptable, hythread_monitor_enter
 *
 */
IDATA VMCALL hythread_monitor_wait_timed(hythread_monitor_t mon_ptr, I_64 ms, IDATA nano) {
    return monitor_wait_impl(mon_ptr, ms, nano, WAIT_NONINTERRUPTABLE);
}

/**
 * Wait on a monitor until notified, interrupted (priority or normal), or timed out.
 *
 * A timeout of 0 (0ms, 0ns) indicates wait indefinitely.
 *
 *
 * @param[in] mon_ptr a monitor to be waited on
 * @param[in] ms >=0
 * @param[in] nano >=0
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
IDATA VMCALL hythread_monitor_wait_interruptable(hythread_monitor_t mon_ptr, I_64 ms, IDATA nano) {
    return monitor_wait_impl(mon_ptr, ms, nano, WAIT_INTERRUPTABLE);
}

/**
 * Returns how many threads are currently waiting on a monitor.
 *
 * @note This can only be called by the owner of this monitor.
 *
 * @param[in] mon_ptr a monitor
 * @return number of threads waiting on the monitor (>=0)
 */
UDATA VMCALL hythread_monitor_num_waiting(hythread_monitor_t mon_ptr) {
    return mon_ptr->wait_count;
}

/**
 * Notify all threads waiting on a monitor.
 *
 * A thread is considered to be waiting on the monitor if
 * it is currently blocked while executing hythread_monitor_wait on the monitor.
 *
 * If no threads are waiting, no action is taken.
 *
 *
 * @param[in] mon_ptr a monitor to be signaled
 * @return  0 once the monitor has been signaled<br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 *
 * @see hythread_monitor_notify, hythread_monitor_enter, hythread_monitor_wait
 */
IDATA VMCALL hythread_monitor_notify_all(hythread_monitor_t mon_ptr) {
    if (mon_ptr->owner != tm_self_tls) {
        return TM_ERROR_ILLEGAL_STATE;
    }
    mon_ptr->notify_count = mon_ptr->wait_count;
    return hycond_notify_all(&mon_ptr->condition);
}


/**
 * Notify a single thread waiting on a monitor.
 *
 * A thread is considered to be waiting on the monitor if
 * it is currently blocked while executing hythread_monitor_wait on the monitor.
 *
 * If no threads are waiting, no action is taken.
 *
 * @param[in] mon_ptr a monitor to be signaled
 * @return  0 once the monitor has been signaled<br>HYTHREAD_ILLEGAL_MONITOR_STATE if the current thread does not own the monitor
 *
 * @see hythread_monitor_notify_all, hythread_monitor_enter, hythread_monitor_wait
 */
IDATA VMCALL hythread_monitor_notify(hythread_monitor_t mon_ptr) {
    if (mon_ptr->owner != tm_self_tls) {
        return TM_ERROR_ILLEGAL_STATE;
    }
    if (mon_ptr->notify_count < mon_ptr->wait_count)
        mon_ptr->notify_count++;
    return hycond_notify(&mon_ptr->condition);
}


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
IDATA VMCALL hythread_monitor_destroy(hythread_monitor_t monitor) {
    if (monitor->owner != NULL || monitor->wait_count > 0) {
        return TM_ERROR_ILLEGAL_STATE;
    }

    port_mutex_destroy(&monitor->mutex);
    hycond_destroy(&monitor->condition);
    free(monitor);
    return TM_ERROR_NONE;
}

//@}
