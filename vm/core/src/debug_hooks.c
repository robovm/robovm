/*
 * Copyright (C) 2013 Trillian Mobile AB
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

#define LOG_TAG "debug.hooks"
jboolean attachFlag = FALSE;

void debugHookWaitForAttach(void) {
    int i = 0;
    while(attachFlag == FALSE && (i++) < 15) {
        sleep(1);
        fprintf(stderr, "[DEBUG] %s: Waiting for debugger to attach\n", LOG_TAG);
    }
}

void debugHookBeforeThreads(void) {
    fprintf(stderr, "[DEBUG] %s: Before thread initialization\n", LOG_TAG);
}

void debugHookMain(void) {
    fprintf(stderr, "[DEBUG] %s: VM Initialized, waiting for debugger to resume process\n", LOG_TAG);
}

void debugHookThreadAttach(JavaThread* threadObj, Thread* thread) {
    fprintf(stderr, "[DEBUG] %s: Thread attached\n", LOG_TAG);
}

void debugHookThreadStart(JavaThread* threadObj, Thread* thread) {
    fprintf(stderr, "[DEBUG] %s: Thread started\n", LOG_TAG);
}

void debugHookThreadDetach(JavaThread* threadObj, Thread* thread) {
    fprintf(stderr, "[DEBUG] %s: Thread detached\n", LOG_TAG);
}