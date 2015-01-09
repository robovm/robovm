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
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <errno.h>

#define LOG_TAG "hooks"
jboolean attachFlag = FALSE;
static jint waitForAttachTime = 0;
int listeningSocket = 0;
jint debugPort = 0;

void rvmHookDebuggerAttached(Options *options) {
    DEBUG("Debugger attached");
}

void rvmHookWaitForAttach(Options *options) {
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

void _rvmHookBeforeMainThreadAttached(Env *env) {
    DEBUG("Before main thread attached");
}

void _rvmHookBeforeAppEntryPoint(Env *env, Class *clazz, Method *method, ObjectArray *args) {
    DEBUGF("Before app entry point %s.%s%s", clazz->name, method->name, method->desc);
}

void _rvmHookThreadCreated(Env *env, JavaThread *threadObj) {
    DEBUGF("Thread %lld created", threadObj->id);
}

void _rvmHookThreadAttached(Env *env, JavaThread *threadObj, Thread *thread) {
    DEBUGF("Thread %lld attached", threadObj->id);
}

void _rvmHookThreadStarting(Env *env, JavaThread *threadObj, Thread *thread) {
    DEBUGF("Thread %lld starting", threadObj->id);
}

void _rvmHookThreadDetaching(Env *env, JavaThread *threadObj, Thread *thread, Object *throwable) {
    DEBUGF("Thread %lld detaching", threadObj->id);
}

jboolean _rvmHookSetupTCPChannel() {
    DEBUG("Setting up TCP channel");
    int listeningSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (listeningSocket < 0) {
        return FALSE;
    }
    int yes = 1;
    setsockopt(listeningSocket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int));

    struct sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    if (bind(listeningSocket, (struct sockaddr *) &serverAddr, sizeof(serverAddr))) {
        DEBUGF("Couldn't bind debug socket, errno: %d", errno);
        return FALSE;
    }
    if (listen(listeningSocket, 1)) {
        DEBUGF("Couldn't listen on debug socket, errno: %d", errno);
        return FALSE;
    }
    socklen_t len;
    getsockname(listeningSocket, (struct sockaddr *) &serverAddr, &len);
    debugPort = ntohs(serverAddr.sin_port);
    return TRUE;
}

jboolean _rvmHookHandshake() {
    DEBUG("Performing handshake");
    if (!listeningSocket) {
        DEBUG("Can't perform handshake, no listening socket");
        return FALSE;
    }
    
    return TRUE;
}

void _rvmHookDestroyTCPChannel() {
    DEBUG("Destroying TCP channel");
}