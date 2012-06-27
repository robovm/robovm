/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef ROBOVM_MUTEX_H
#define ROBOVM_MUTEX_H

#include <pthread.h>

/* Mutex wrappers around pthread mutexes */
static inline jint rvmInitMutex(Mutex* mutex) {
    pthread_mutexattr_t mutexAttrs;
    pthread_mutexattr_init(&mutexAttrs);
    pthread_mutexattr_settype(&mutexAttrs, PTHREAD_MUTEX_RECURSIVE);
    return pthread_mutex_init(mutex, &mutexAttrs);
}
static inline jint rvmDestroyMutex(Mutex* mutex) {
    return pthread_mutex_destroy(mutex);
}
static inline jint rvmLockMutex(Mutex* mutex) {
    return pthread_mutex_lock(mutex);
}
static inline jint rvmTryLockMutex(Mutex* mutex) {
    return pthread_mutex_trylock(mutex);
}
static inline jint rvmUnlockMutex(Mutex* mutex) {
    return pthread_mutex_unlock(mutex);
}

#endif
