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
 * @file thread_native_latch.c
 * @brief Hythread latch related functions
 */

#include <open/hythread_ext.h>
#include "port_mutex.h"
#include "thread_private.h"


/**
 * Creates new latch.
 *
 * Latch allows one or more threads to wait until a set of operations 
 * being performed in other threads are complete. Latch serves as a gate for the threads 
 * which are waiting until it is opened. Latch initialized to N counts can be used 
 * to make one thread wait until N threads have completed some action, or some
 * action has been performed N times.
 * The key difference between latch and traditional semaphore is that latch notifies 
 * every waiting threads when it reaches zero, while semaphore notifies only one waiting thread.
 *
 * @param[out] latch the memory address where the newly created latch 
 *        will be stored
 * @param[in] count the created key
 * @sa java.util.concurrent.CountDownLatch 
 */
IDATA VMCALL hylatch_create(hylatch_t *latch_ptr, IDATA count) {
    int res;
    hylatch_t latch;
    
    latch = malloc(sizeof(HyLatch));
    if (latch == NULL) {
        return TM_ERROR_OUT_OF_MEMORY;
    }
    res = port_mutex_create(&latch->mutex, APR_THREAD_MUTEX_DEFAULT);
    if (res) {
        goto cleanup;
    }
    res = hycond_create(&latch->condition);
    if (res) {
        goto cleanup_mutex;
    }
    latch->count = count;
    *latch_ptr = latch;
    return TM_ERROR_NONE;

cleanup_mutex:
    port_mutex_destroy(&latch->mutex);

cleanup:
    free(latch);
    return res;
}

//wait method implementation
////
static IDATA latch_wait_impl(hylatch_t latch, I_64 ms, IDATA nano, IDATA interruptable) {
    IDATA status;
        
    status = port_mutex_lock(&latch->mutex);
    if (status != TM_ERROR_NONE) {
        return status;
    }
    while (latch->count) {
        status = condvar_wait_impl(&latch->condition, &latch->mutex, ms, nano, interruptable);
        //check interruption and other problems
        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&latch->mutex);
            return status;
        }

        if (ms || nano) break;
    }
    status = port_mutex_unlock(&latch->mutex);

    return status;
}

/**
 * Instructs the current thread to wait until the latch is opened.
 * 
 * @param[in] latch the latch to wait for
 * @sa java.util.concurrent.CountDownLatch.await() 
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hylatch_wait(hylatch_t latch) {
    return latch_wait_impl(latch, 0, 0, WAIT_NONINTERRUPTABLE);       
}
/**
 * Instructs the current thread to wait until the latch is opened or
 * the specified timeout is elapsed.
 *
 * @param[in] latch the latch to wait for
 * @param[in] ms amount of time in milliseconds to wait
 * @param[in] nano amount of time in nanoseconds to wait 
 * @sa java.util.concurrent.CountDownLatch.await()
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hylatch_wait_timed(hylatch_t latch, I_64 ms, IDATA nano) {
    return latch_wait_impl(latch, ms, nano, WAIT_NONINTERRUPTABLE);       
}

/**
 * Instructs the current thread to wait until the latch is opened or
 * the specified timeout is elapsed.
 *
 * @param[in] latch the latch to wait for
 * @param[in] ms amount of time in milliseconds to wait
 * @param[in] nano amount of time in nanoseconds to wait 
 * @sa java.util.concurrent.CountDownLatch.await()
 * @return  
 *      TM_NO_ERROR on success 
 *      TM_THREAD_INTERRUPTED in case thread was interrupted during wait.
 */
IDATA VMCALL hylatch_wait_interruptable(hylatch_t latch, I_64 ms, IDATA nano) {
    return latch_wait_impl(latch, ms, nano, WAIT_INTERRUPTABLE);       
}

/**
 * Sets the count for latch to the specific value.
 *
 * @param[in] latch the latch 
 * @param[in] count new count value
 */
IDATA VMCALL hylatch_set(hylatch_t latch, IDATA count) {
    IDATA status;
    
    status = port_mutex_lock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;
    latch->count = count;
    status = port_mutex_unlock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;

    return TM_ERROR_NONE;       
}

/**
 * Decreases the count for latch.
 *
 * If the count reaches zero, all threads awaiting on the latch are unblocked.
 * @param[in] latch the latch 
 * @sa java.util.concurrent.CountDownLatch.countDown()
 */
IDATA VMCALL hylatch_count_down(hylatch_t latch) {
    IDATA status;
    
    status = port_mutex_lock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;
    if (latch->count <= 0) {
        status = port_mutex_unlock(&latch->mutex);
        if (status != TM_ERROR_NONE) return status;
        return TM_ERROR_ILLEGAL_STATE;
    }
    latch->count--;
    if (latch->count == 0) {
        status = hycond_notify_all(&latch->condition); 
        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&latch->mutex);
            return status;
        }
    }
            
    status = port_mutex_unlock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;
        
    return TM_ERROR_NONE;       
}

/**
 * Returns the count for this latch.
 *
 * The count value for the latch determines how many times it needs to be counted down
 * before the threads awaiting on the latch can be unblocked.
 * @param[out] count count value
 * @param[in] latch the latch 
 */
IDATA VMCALL hylatch_get_count(IDATA *count, hylatch_t latch) {
    IDATA status;
    
    status = port_mutex_lock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;
    *count = latch->count;
    status = port_mutex_unlock(&latch->mutex);
    if (status != TM_ERROR_NONE) return status;

    return TM_ERROR_NONE;       
}

/**
 * Destroys the latch and releases the associated memory.
 * 
 * @param[in] latch the latch 
 */
IDATA VMCALL hylatch_destroy(hylatch_t latch) {
    IDATA status = port_mutex_destroy(&latch->mutex);
    status |= hycond_destroy(&latch->condition);
    free(latch);
    return status;
}

