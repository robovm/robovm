/*
 * Copyright (C) 2014 Trillian Mobile AB
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
#include <stdio.h>
#include <unistd.h>

#define LOG_TAG "hooks"
jboolean attachFlag = FALSE;
static jint waitForAttachTime = 0;

void rvmHookDebuggerAttached(Options* options) {
    fprintf(stderr, "[DEBUG] %s: Debugger attached\n", LOG_TAG);
}

void rvmHookWaitForAttach(Options* options) {
    if (attachFlag == FALSE && waitForAttachTime < 15) {
        waitForAttachTime++;
        sleep(1);
        fprintf(stderr, "[DEBUG] %s: Waiting for debugger to attach\n", LOG_TAG);
        rvmHookWaitForAttach(options);
    } else {
        if (attachFlag) {
            rvmHookDebuggerAttached(options);
        }
    }
}

void _rvmHookBeforeMainThreadAttached(Env* env) {
    fprintf(stderr, "[DEBUG] %s: Before main thread attached\n", LOG_TAG);
}

void _rvmHookBeforeAppEntryPoint(Env* env, Class* clazz, Method* method, ObjectArray* args) {
    fprintf(stderr, "[DEBUG] %s: Before app entry point %s.%s%s\n", LOG_TAG, clazz->name, method->name, method->desc);
}

void _rvmHookThreadCreated(Env* env, JavaThread* threadObj) {
    fprintf(stderr, "[DEBUG] %s: Thread %lld created\n", LOG_TAG, threadObj->id);
}

void _rvmHookThreadAttached(Env* env, JavaThread* threadObj, Thread* thread) {
    fprintf(stderr, "[DEBUG] %s: Thread %lld attached\n", LOG_TAG, threadObj->id);
}

void _rvmHookThreadStarting(Env* env, JavaThread* threadObj, Thread* thread) {
    fprintf(stderr, "[DEBUG] %s: Thread %lld starting\n", LOG_TAG, threadObj->id);
}

void _rvmHookThreadDetaching(Env* env, JavaThread* threadObj, Thread* thread, Object* throwable) {
    fprintf(stderr, "[DEBUG] %s: Thread %lld detaching\n", LOG_TAG, threadObj->id);
}
