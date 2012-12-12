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
#include <robovm.h>

Class* Java_java_lang_Object_getClass(Env* env, Object* thiz) {
    return thiz->clazz;
}

Object* Java_java_lang_Object_internalClone(Env* env, Object* thiz) {
    return rvmCloneObject(env, thiz);
}

jint Java_java_lang_Object_hashCode(Env* env, Object* thiz) {
    return (jint) thiz;
}

void Java_java_lang_Object_notify(Env* env, Object* thiz) {
    rvmObjectNotify(env, thiz);
}

void Java_java_lang_Object_notifyAll(Env* env, Object* thiz) {
    rvmObjectNotifyAll(env, thiz);
}

void Java_java_lang_Object_wait(Env* env, Object* thiz, jlong millis, jint nanos) {
    rvmObjectWait(env, thiz, millis, nanos, TRUE);
}

