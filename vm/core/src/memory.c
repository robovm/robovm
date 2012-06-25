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
#include <robovm.h>
#include <string.h>
#if defined(DARWIN)
#   include <libkern/OSAtomic.h>
#endif

void* rvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmCopyMemory(Env* env, const void* src, int size) {
    void* dest = rvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* rvmCopyMemoryZ(Env* env, const char* src) {
    return rvmCopyMemory(env, src, strlen(src) + 1);
}

jboolean rvmCompareAndSwapInt(jint* ptr, jint oldval, jint newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwap32(oldval, newval, ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

jboolean rvmCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwap64(oldval, newval, ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

jboolean rvmCompareAndSwapPtr(void** ptr, void* oldval, void* newval) {
#if defined(DARWIN)
    return OSAtomicCompareAndSwapPtr(oldval, newval, ptr) ? TRUE : FALSE;
#else
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
#endif
}

