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

Thread* Java_java_lang_Thread_currentThread(Env* env, Class* cls) {
    return env->currentThread;
}

jlong Java_java_lang_Thread_internalStart(Env* env, Class* cls, Thread* t) {
    jlong threadPtr = rvmStartThread(env, t);
    return threadPtr;
}

void Java_java_lang_Thread_internalSleep(Env* env, Class* cls, jlong millis, jint nanos) {
    rvmThreadSleep(env, millis, nanos);
}

jboolean Java_java_lang_Thread_internalInterrupted(Env* env, Class* cls) {
    return rvmThreadClearInterrupted(env, env->currentThread);
}

jboolean Java_java_lang_Thread_internalIsInterrupted(Env* env, Class* cls, Thread* thread) {
    return rvmThreadIsInterrupted(env, thread);
}

void Java_java_lang_Thread_internalInterrupt(Env* env, Class* cls, Thread* thread) {
    rvmThreadInterrupt(env, thread);
}

jboolean Java_java_lang_Thread_internalHoldsLock(Env* env, Class* cls, Object* obj) {
    return rvmThreadHoldsLock(env, obj);
}

void Java_java_lang_Thread_internalYield(Env* env, Class* cls) {
    rvmThreadYield(env);
}

jint Java_java_lang_Thread_internalGetState(Env* env, Class* cls, Thread* t) {
    return rvmThreadGetState(env, t);
}
