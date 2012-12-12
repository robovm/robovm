/*
 * Copyright (C) 2012 Trillian AB
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
    return OSAtomicCompareAndSwap64(oldval, newval, ptr) ? TRUE : FALSE;
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

static inline jint rvmAtomicGetAndSetInt(jint* ptr, jint newval) {
    while (TRUE) {
        jint oldval = *ptr;
        if (rvmAtomicCompareAndSwapInt(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

static inline jlong rvmAtomicGetAndSetLong(jlong* ptr, jlong newval) {
    while (TRUE) {
        jlong oldval = *ptr;
        if (rvmAtomicCompareAndSwapLong(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

static inline void* rvmAtomicGetAndSetPtr(void** ptr, void* newval) {
    while (TRUE) {
        void* oldval = *ptr;
        if (rvmAtomicCompareAndSwapPtr(ptr, oldval, newval)) {
            return oldval;
        }
    }
}

#endif
