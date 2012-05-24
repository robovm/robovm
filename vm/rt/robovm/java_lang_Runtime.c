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

void Java_java_lang_Runtime_nativeExit(Env* env, Class* clazz, jint code, jboolean isExit) {
    // TODO: What about other threads? Should we stop them before shutting down?
    rvmShutdown(env, code);
}

void Java_java_lang_Runtime_nativeLoad(Env* env, Class* clazz, Object* filename, ClassLoader* classLoader) {
    char* path = rvmGetStringUTFChars(env, filename);
    if (!path) return;
    rvmLoadNativeLibrary(env, path, classLoader);
}

