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
 * @file thread_native_interrupt.c
 * @brief Hythread interruption related functions
 */

#include <open/hythread_ext.h>
#include <apr_atomic.h>
#include "thread_private.h"

static IDATA HYTHREAD_PROC hythread_interrupter(void *args)
{
    IDATA status;
    hythread_monitor_t mon = (hythread_monitor_t)args;

    status = hythread_monitor_enter(mon);
    assert(status == TM_ERROR_NONE);
    status = hycond_notify_all(&mon->condition);
    assert(status == TM_ERROR_NONE);
    hythread_exit(mon);
    return 0;
}

/** 
 * Interrupt a thread.
 * 
 * If the thread is currently blocked (i.e. waiting on a monitor_wait or sleeping)
 * resume the thread and cause it to return from the blocking function with
 * HYTHREAD_INTERRUPTED.
 * 
 * @param[in] thread a thread to be interrupted
 * @return none
 */
void VMCALL hythread_interrupt(hythread_t thread) {
    IDATA status;
    hythread_monitor_t mon;

    apr_atomic_set32(&thread->interrupted, TRUE);

    mon = thread->waited_monitor;
    if (mon) {
        // If thread was doing any kind of wait, notify it.
        if (hythread_monitor_try_enter(mon) == TM_ERROR_NONE) {
            status = hycond_notify_all(&mon->condition);
            assert(status == TM_ERROR_NONE);
            status = hythread_monitor_exit(mon);
            assert(status == TM_ERROR_NONE);
        } else {
            status = hythread_create(NULL, 0, 0, 0,
                hythread_interrupter, (void *)mon);
            assert (status == TM_ERROR_NONE);
        }
    }
} // hythread_interrupt

/** 
 * Returns interrupted status and clear interrupted flag.
 *
 * @param[in] thread where to clear interrupt flag
 * @returns TM_ERROR_INTERRUPT if thread was interrupted, TM_ERROR_NONE otherwise
 * @see java.lang.Thread.interrupted()
 */
UDATA VMCALL hythread_clear_interrupted_other(hythread_t thread) {
    int interrupted;
    assert(thread);
    interrupted = thread->interrupted;
    apr_atomic_set32(&thread->interrupted, FALSE);
    return interrupted ? TM_ERROR_INTERRUPT : TM_ERROR_NONE;
} // hythread_clear_interrupted_other

/**
 * Clear the interrupted flag of the current thread and return its previous value.
 * 
 * @return  previous value of interrupted flag: non-zero if the thread had been interrupted.
 * @see java.lang.Thread.interrupted()
 */
UDATA VMCALL hythread_clear_interrupted() {
    return hythread_clear_interrupted_other(tm_self_tls);
} // hythread_clear_interrupted

/**
 * Return the value of a thread's interrupted flag.
 * 
 * @param[in] thread thread to be queried
 * @return 0 if not interrupted, non-zero if interrupted
 * @see java.lang.Thread.isInterrupted()
 */
UDATA VMCALL hythread_interrupted(hythread_t thread) {
    return thread->interrupted ? TM_ERROR_INTERRUPT : TM_ERROR_NONE;
} // hythread_interrupted

