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

void* rvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryUncollectable(Env* env, int size) {
    void* m = GC_MALLOC_UNCOLLECTABLE(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryAtomic(Env* env, int size) {
    void* m = GC_MALLOC_ATOMIC(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void rvmFreeMemory(void* m) {
    GC_FREE(m);
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
