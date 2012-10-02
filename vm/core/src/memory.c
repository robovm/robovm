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

static Class* java_nio_ReadWriteDirectByteBuffer = NULL;
static Method* java_nio_ReadWriteDirectByteBuffer_init = NULL;
static InstanceField* java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress = NULL;

jboolean rvmInitMemory(Env* env) {
    java_nio_ReadWriteDirectByteBuffer = rvmFindClassUsingLoader(env, "java/nio/ReadWriteDirectByteBuffer", NULL);
    if (!java_nio_ReadWriteDirectByteBuffer) return FALSE;
    java_nio_ReadWriteDirectByteBuffer_init = rvmGetInstanceMethod(env, java_nio_ReadWriteDirectByteBuffer, "<init>", "(II)V");
    if (!java_nio_ReadWriteDirectByteBuffer_init) return FALSE;
    java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress = rvmGetInstanceField(env, java_nio_ReadWriteDirectByteBuffer, "effectiveDirectAddress", "I");
    if (!java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress) return FALSE;
    return TRUE;
}

void* rvmAllocateMemory(Env* env, jint size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryUncollectable(Env* env, jint size) {
    void* m = GC_MALLOC_UNCOLLECTABLE(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryAtomic(Env* env, jint size) {
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

void* rvmCopyMemory(Env* env, const void* src, jint size) {
    void* dest = rvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* rvmCopyMemoryZ(Env* env, const char* src) {
    return rvmCopyMemory(env, src, strlen(src) + 1);
}

Object* rvmNewDirectByteBuffer(Env* env, void* address, jlong capacity) {
    jvalue args[2];
    args[0].i = (jint) address;
    args[1].i = (jint) capacity;
    return rvmNewObjectA(env, java_nio_ReadWriteDirectByteBuffer, java_nio_ReadWriteDirectByteBuffer_init, args);
}
