/*
 * Copyright (C) 2012 RoboVM AB
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
#ifndef ROBOVM_ATOMIC_H
#define ROBOVM_ATOMIC_H

#if defined(DARWIN)
#   include <libkern/OSAtomic.h>
#endif

static inline jboolean rvmAtomicCompareAndSwapInt(jint* ptr, jint oldval, jint newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwap32(oldval, newval, ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

static inline jboolean rvmAtomicCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwapLong(oldval, newval, (long *)ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

static inline jboolean rvmAtomicCompareAndSwapPtr(void** ptr, void* oldval, void* newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwapPtr(oldval, newval, ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

static inline jint rvmAtomicLoadInt(jint* ptr) {
    return __sync_fetch_and_or(ptr, 0);
}

static inline long rvmAtomicLoadLong(jlong* ptr) {
    return __sync_fetch_and_or((long *)ptr, 0L);
}

static inline void* rvmAtomicLoadPtr(void** ptr) {
    return __sync_fetch_and_or(ptr, NULL);
}

static inline jint rvmAtomicStoreInt(jint* ptr, jint newval) {
    while (TRUE) {
        jint oldval = *ptr;
        if (rvmAtomicCompareAndSwapInt(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

static inline jlong rvmAtomicStoreLong(jlong* ptr, jlong newval) {
    while (TRUE) {
        jlong oldval = *ptr;
        if (rvmAtomicCompareAndSwapLong(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

static inline void* rvmAtomicStorePtr(void** ptr, void* newval) {
    while (TRUE) {
        void* oldval = *ptr;
        if (rvmAtomicCompareAndSwapPtr(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

static inline void rvmAtomicSynchronize() {
    __sync_synchronize();
}

#endif
