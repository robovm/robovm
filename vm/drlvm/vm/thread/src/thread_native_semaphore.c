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
 * @author Artem Aliev
 */  

/**
 * @file thread_native_semaphore.c
 * @brief Hythread semaphore related functions
 */

#include <open/hythread_ext.h>
#include "port_mutex.h"
#include "thread_private.h"

/**
 * Initializes unnamed process-private semaphore.
 *
 * @param[out] sem new semaphore
 * @param[in] initial_count initial semaphore count
 * @param[in] max_count  maximum semaphore count
 */
IDATA VMCALL hysem_create(hysem_t *sem, UDATA initial_count, UDATA max_count) {
    int r;
    hysem_t l;
    
    l = malloc(sizeof(HySemaphore));
    if (l == NULL) {
        return TM_ERROR_OUT_OF_MEMORY;
    }
    r = port_mutex_create(&l->mutex, APR_THREAD_MUTEX_DEFAULT);
    if (r) goto cleanup;
    r = hycond_create(&l->condition);
    if (r) goto cleanup;
        
    l->count = initial_count;
    l->max_count = max_count;
    *sem = l;
    return TM_ERROR_NONE;

cleanup:
    free(l);
    return r;
}


IDATA sem_wait_impl(hysem_t sem, I_64 ms, IDATA nano, IDATA interruptable) {
    IDATA status;
        
    status = port_mutex_lock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;
    //printf("wait %x %d\n", sem, sem->count);
    //fflush(NULL);
    while (sem->count <= 0) {
        status = condvar_wait_impl(&sem->condition, &sem->mutex, ms, nano, interruptable);
        //check interruption and timeout
        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&sem->mutex);
            return status;
        }

        if (nano || ms) break;
    }
    //should we check here if timeout is not supposed to happen
    if (sem->count == 0 /*&& (ms || nano)*/) {
        if (ms || nano) {
            port_mutex_unlock(&sem->mutex);
            return TM_ERROR_TIMEOUT;
        } else {
            assert(0);
        }
    }
    sem->count--;
    status = port_mutex_unlock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;

    return TM_ERROR_NONE;
}

/**
 * Wait on a semaphore.
 *
 * @param[in] sem semaphore to be waited on
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 *
 * @see hysem_init, hysem_destroy, hysem_wait
 *
 */
IDATA VMCALL hysem_wait(hysem_t sem) {   
    return sem_wait_impl(sem, 0, 0, WAIT_NONINTERRUPTABLE);
}

/**
 * Decreases the count of the semaphore and waits until the semaphore reaches zero with the specified timeout.
 *
 *
 * @param[in] sem semaphore
 * @param[in] ms timeout millis
 * @param[in] nano timeout nanos
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hysem_wait_timed(hysem_t sem, I_64 ms, IDATA nano) {
    return sem_wait_impl(sem, ms, nano, WAIT_NONINTERRUPTABLE);
}

/**
 * Decreases the count of the semaphore and waits until the semaphore reaches zero with the specified timeout.
 *
 *
 * @param[in] sem semaphore
 * @param[in] ms timeout millis
 * @param[in] nano timeout nanos
 * @return  
 *      TM_NO_ERROR on success 
 *      TM_THREAD_INTERRUPTED in case thread was interrupted during wait.
 */
IDATA VMCALL hysem_wait_interruptable(hysem_t sem, I_64 ms, IDATA nano) {
    return sem_wait_impl(sem, ms, nano, WAIT_INTERRUPTABLE);
}

/**
 * Release a semaphore by 1.
 *
 * @param[in] sem semaphore to be released by 1
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 *
 * @see hysem_init, hysem_destroy, hysem_wait
 */
IDATA VMCALL hysem_post(hysem_t sem) {
    IDATA status;
    //printf("post %x %d\n", sem, sem->count);
    //fflush(NULL);
    status = port_mutex_lock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;
    if (sem->count >= sem->max_count) {
        port_mutex_unlock(&sem->mutex);
        //printf("illegal state %d : %d \n", sem->count, sem->max_count);
        //fflush(NULL);
        return TM_ERROR_ILLEGAL_STATE;
    }
    sem->count++;
    if (sem->count > 0) {
        hycond_notify(&sem->condition);
    }
            
    status = port_mutex_unlock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;
    return TM_ERROR_NONE;
}

/**
 * Resets current semaphore count to the specified numbers.
 *
 * @param[in] count new semaphore count
 * @param[in] sem semaphore
 */
IDATA VMCALL hysem_set(hysem_t sem, IDATA count) {
    IDATA status;
    
    status = port_mutex_lock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;
    if (count > sem->max_count) {
        port_mutex_unlock(&sem->mutex);
        if (status != TM_ERROR_NONE) return status;
        return TM_ERROR_ILLEGAL_STATE;
    }
    sem->count = count;
    if (count > 0) {
        status = hycond_notify_all(&sem->condition); 
        if (status != TM_ERROR_NONE) {
            port_mutex_unlock(&sem->mutex);
            return status;
        }
    }
    status = port_mutex_unlock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;

    return TM_ERROR_NONE;       
}

/**
 * Returns current count for semaphore.
 *
 * @param[out] count semaphore count
 * @param[in] sem semaphore
 */
IDATA VMCALL hysem_getvalue(IDATA *count, hysem_t sem) {
    IDATA status;
    
    status = port_mutex_lock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;
    *count = sem->count;
    status = port_mutex_unlock(&sem->mutex);
    if (status != TM_ERROR_NONE) return status;

    return TM_ERROR_NONE;      
}

/**
 * Destroy a semaphore.
 *
 * Returns the resources associated with a semaphore back to the Hy threading library.
 *
 * @param[in] sem semaphore to be destroyed
 * @return  0 on success or negative value on failure
 *
 * @deprecated Semaphores are no longer supported.
 *
 * @see hysem_init, hysem_wait, hysem_post
 */
IDATA VMCALL hysem_destroy(hysem_t sem) {
    IDATA status = port_mutex_destroy(&sem->mutex);
    status |= hycond_destroy(&sem->condition);
    free(sem);
    return status;
}
