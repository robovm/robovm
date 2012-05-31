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

#ifndef _PORT_MUTEX_H_
#define _PORT_MUTEX_H_

/**
 * @file port_mutex.h
 * @brief PORT mutex support
 */

/* error codes */
#include "apr_errno.h"
/* mutex flags */
#include "apr_thread_mutex.h"
/* osmutex_t type */
#include "open/hythread_ext.h"

#include "port_general.h"

#ifdef __cplusplus
extern "C" {
#endif

#if defined(FREEBSD)
#define PTHREAD_MUTEX_RECURSIVE_NP PTHREAD_MUTEX_RECURSIVE
#endif

/**
 * @defgroup port_mutex Basic mutexes
 * @ingroup port_apr
 * @{
 */

/**
 * Initializes a mutex.
 *
 * A memory for mutex must be preallocated.
 *
 * @param[in] mutex the address of the mutex to be initialized
 * @param[in] flags Or'ed value of:
 * <PRE>
 *           APR_THREAD_MUTEX_DEFAULT   platform-optimal lock behavior.
 *           APR_THREAD_MUTEX_NESTED    enable nested (recursive) locks.
 *           APR_THREAD_MUTEX_UNNESTED  disable nested locks (non-recursive).
 * </PRE>
 */
PORT_INLINE apr_status_t port_mutex_create(osmutex_t *mutex, UDATA flags) {
#if defined(WIN32)
    int r = 0;
    if (flags & APR_THREAD_MUTEX_UNNESTED) {
        assert(!"not implemented");
        return -1;
    }
    InitializeCriticalSection(mutex);
    return r;
#else /* PTHREADS */
    int r = 0;
    if (flags & APR_THREAD_MUTEX_NESTED) {
        pthread_mutexattr_t attr;
        pthread_mutexattr_init(&attr);
        r = pthread_mutexattr_settype(&attr, PTHREAD_MUTEX_RECURSIVE_NP);
        if (r) return r;
        r = pthread_mutex_init(mutex, &attr);
        pthread_mutexattr_destroy(&attr);
    } else {
        r = pthread_mutex_init(mutex, NULL);
    }
    return r;
#endif
}

/**
 * Acquires the lock for the given mutex. If the mutex is already locked,
 * the current thread will be put to sleep until the lock becomes available.
 *
 * @param[in] mutex the mutex on which to acquire the lock.
 * @sa apr_thread_mutex_lock()
 */
PORT_INLINE apr_status_t port_mutex_lock(osmutex_t *mutex) {
#if defined(WIN32)
    EnterCriticalSection(mutex);
    return 0;
#else /* PTHREADS */
    return pthread_mutex_lock(mutex);
#endif
}

/**
 * Attempts to acquire the lock for the given mutex.
 *
 * @param[in] mutex the mutex on which to attempt the lock acquiring.
 * @sa apr_thread_mutex_trylock()
 */
PORT_INLINE apr_status_t port_mutex_trylock(osmutex_t *mutex) {
#if defined(WIN32)
    int r;
    r = TryEnterCriticalSection(mutex);
    // Return code is non-zero on success
    if (r == 0) return TM_ERROR_EBUSY;
    return 0;
#else /* PTHREADS */
    int r;
    r = pthread_mutex_trylock(mutex);
    if (r == EBUSY) return TM_ERROR_EBUSY;
    return r;
#endif
}

/**
 * Releases the lock for the given mutex.
 *
 * @param[in] mutex the mutex from which to release the lock.
 * @sa apr_thread_mutex_unlock()
 */
PORT_INLINE apr_status_t port_mutex_unlock(osmutex_t *mutex) {
#if defined(WIN32)
    LeaveCriticalSection(mutex);
    return 0;
#else /* PTHREADS */
    return pthread_mutex_unlock(mutex);
#endif
}

/**
 * Destroys the mutex.
 *
 * @param[in] mutex the mutex to destroy.
 * @sa apr_thread_mutex_destroy()
 */
PORT_INLINE apr_status_t port_mutex_destroy(osmutex_t *mutex) {
#if defined(WIN32)
    DeleteCriticalSection(mutex);
    return 0;
#else /* PTHREADS */
    return pthread_mutex_destroy(mutex);
#endif
}

/** @} */

#ifdef __cplusplus
}
#endif

#endif  /* _PORT_MUTEX_H_ */
