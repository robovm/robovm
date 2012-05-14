/*
 * Copyright (C) 2010 The Android Open Source Project
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

#ifndef SCOPED_PTHREAD_MUTEX_LOCK_H_included
#define SCOPED_PTHREAD_MUTEX_LOCK_H_included

#include <pthread.h>

/**
 * Locks and unlocks a pthread_mutex_t as it goes in and out of scope.
 */
class ScopedPthreadMutexLock {
public:
    explicit ScopedPthreadMutexLock(pthread_mutex_t* mutex) : mMutexPtr(mutex) {
        pthread_mutex_lock(mMutexPtr);
    }

    ~ScopedPthreadMutexLock() {
        pthread_mutex_unlock(mMutexPtr);
    }

private:
    pthread_mutex_t* mMutexPtr;

    // Disallow copy and assignment.
    ScopedPthreadMutexLock(const ScopedPthreadMutexLock&);
    void operator=(const ScopedPthreadMutexLock&);
};

#endif  // SCOPED_PTHREAD_MUTEX_LOCK_H_included
