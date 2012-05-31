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
 * @file thread_native_condvar.c
 * @brief Hythread condvar related functions
 */

#include "thread_private.h"
#include <apr_atomic.h>
#include <open/hythread_ext.h>

/** @name Conditional variable
 */
//@{

/**
 * Waits on a conditional, handling interruptions and thread state.
 */
IDATA condvar_wait_impl(hycond_t *cond, osmutex_t *mutex, I_64 ms, IDATA nano, IDATA interruptable) {
    int r;
    int disable_count;
    hythread_t self;
    
    self = tm_self_tls;

    // check interrupted flag
    if (interruptable && self->interrupted) {
        // clean interrupted flag
        IDATA status = hythread_clear_interrupted_other(self);
        assert(status == TM_ERROR_INTERRUPT);
        return TM_ERROR_INTERRUPT;
    }

    // Store provided cond into current thread cond
    self->current_condition = interruptable ? cond : NULL;

    disable_count = hythread_reset_suspend_disable();

    r = os_cond_timedwait(cond, mutex, ms, nano);

    hythread_set_suspend_disable(disable_count);

    self->current_condition = NULL;
   
    // check interrupted flag
    if (interruptable &&  self->interrupted) {
        // clean interrupted flag
        IDATA status = hythread_clear_interrupted_other(self);
        assert(status == TM_ERROR_INTERRUPT);
        return TM_ERROR_INTERRUPT;
    }

    return r;
}

/**
 * Instructs the current thread to wait until it is signaled to wake up.
 * 
 * @param[in] cond the condition variable on which to block
 * @param[in] mutex the mutex that must be locked upon entering this function
 * @sa apr_thread_cond_wait()
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hycond_wait(hycond_t *cond, osmutex_t *mutex) {
    return condvar_wait_impl(cond, mutex, 0, 0, WAIT_NONINTERRUPTABLE);
}

/**
 * Instructs the current thread to wait until signaled to wake up or
 * the specified timeout is elapsed.
 *
 * @param[in] cond the condition variable on which to block.
 * @param[in] mutex the mutex that must be locked upon entering this function
 * @param[in] ms amount of time in milliseconds to wait
 * @param[in] nano amount of time in nanoseconds to wait
 * @sa apr_thread_cond_timedwait()
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hycond_wait_timed(hycond_t *cond, osmutex_t *mutex, I_64 ms, IDATA nano) {
    return condvar_wait_impl(cond, mutex, ms, nano, WAIT_NONINTERRUPTABLE);
}

/**
 * Instructs the current thread to wait until signaled to wake up or timeout.
 * Directly using OS interfaces.
 * This function does not implement interruptability and thread state functionality.
 */
IDATA VMCALL hycond_wait_timed_raw(hycond_t *cond, osmutex_t *mutex, I_64 ms, IDATA nano) {
    return os_cond_timedwait(cond, mutex, ms, nano);
}

/**
 * Instructs the current thread to wait until signaled to wake up or
 * the specified timeout is elapsed.
 *
 * @param[in] cond the condition variable on which to block.
 * @param[in] mutex the mutex that must be locked upon entering this function
 * @param[in] ms amount of time in milliseconds to wait
 * @param[in] nano amount of time in nanoseconds to wait
 * @sa apr_thread_cond_timedwait()
 * @return  
 *      TM_NO_ERROR on success 
 *      TM_THREAD_INTERRUPTED in case thread was interrupted during wait.
 */
IDATA VMCALL hycond_wait_interruptable(hycond_t *cond, osmutex_t *mutex, I_64 ms, IDATA nano) {
    return condvar_wait_impl(cond, mutex, ms, nano, WAIT_INTERRUPTABLE);
}

//@}
