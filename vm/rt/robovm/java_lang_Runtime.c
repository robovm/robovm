/*
 * Copyright (C) 2012 Trillian Mobile AB
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

void Java_java_lang_Runtime_nativeExit(Env* env, Class* clazz, jint code) {
    // TODO: What about other threads? Should we stop them before shutting down?
    rvmShutdown(env, code);
}

void Java_java_lang_Runtime_nativeLoad(Env* env, Class* clazz, Object* filename, ClassLoader* classLoader) {
    if (!env->vm->options->dynamicJNI) {
        // Static JNI. Just return. If the library is available it has already been linked in statically.
        return;
    }
    char* path = rvmGetStringUTFChars(env, filename);
    if (!path) return;
    rvmLoadNativeLibrary(env, path, classLoader);
}

void Java_java_lang_Runtime_gc(Env* env, Object* thiz) {
    rvmGCCollect(env);
}

jlong Java_java_lang_Runtime_freeMemory(Env* env, Object* thiz) {
    return rvmGetFreeMemory(env);
}

jlong Java_java_lang_Runtime_totalMemory(Env* env, Object* thiz) {
    return rvmGetTotalMemory(env);
}

jlong Java_java_lang_Runtime_maxMemory(Env* env, Object* thiz) {
    return rvmGetMaxMemory(env);
}
