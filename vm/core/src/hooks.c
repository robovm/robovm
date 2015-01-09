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
    DEBUG("Debugger attached");
}

void rvmHookWaitForAttach(Options* options) {
    if (attachFlag == FALSE && waitForAttachTime < 15) {
        waitForAttachTime++;
        sleep(1);
        DEBUG("Waiting for debugger to attach");
        rvmHookWaitForAttach(options);
    } else {
        if (attachFlag) {
            rvmHookDebuggerAttached(options);
        }
    }
}

void _rvmHookBeforeMainThreadAttached(Env* env) {
    DEBUG("Before main thread attached");
}

void _rvmHookBeforeAppEntryPoint(Env* env, Class* clazz, Method* method, ObjectArray* args) {
    DEBUGF("Before app entry point %s.%s%s", clazz->name, method->name, method->desc);
}

void _rvmHookThreadCreated(Env* env, JavaThread* threadObj) {
    DEBUGF("Thread %lld created", threadObj->id);
}

void _rvmHookThreadAttached(Env* env, JavaThread* threadObj, Thread* thread) {
    DEBUGF("Thread %lld attached", threadObj->id);
}

void _rvmHookThreadStarting(Env* env, JavaThread* threadObj, Thread* thread) {
    DEBUGF("Thread %lld starting", threadObj->id);
}

void _rvmHookThreadDetaching(Env* env, JavaThread* threadObj, Thread* thread, Object* throwable) {
    DEBUGF("Thread %lld detaching", threadObj->id);
}

 void _rvmHookSetupTCPChannel() {
    DEBUG("Setting up TCP channel");
}

void _rvmHookHandshake() {
    DEBUG("Performing handshake");
}

void _rvmHookDestroyTCPChannel() {
    DEBUG("Destroying TCP channel");
}