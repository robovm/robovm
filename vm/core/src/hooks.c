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
#include <netinet/tcp.h>

#define LOG_TAG "hooks"

typedef struct {
    int errorCode;
    char* message;
} ChannelError;

// set by debugger when connecting to the inferior
jboolean attachFlag = FALSE;

// timeout counter
static jint waitForAttachTime = 0;

// socket on which we wait for a TCP connection
// from the debugger
int listeningSocket = 0;

// port on which we listen for a TCP connection
// read by the debugger
jint debugPort = 0;

// socket with which we talk to the debugger
int clientSocket = 0;

// thread handling requests from the debugger
pthread_t debugThread;
Mutex writeMutex;
void* channelLoop(void* data);

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

jboolean _rvmHookSetupTCPChannel(Options* options) {
    DEBUG("Setting up TCP channel");
    listeningSocket = socket(AF_INET, SOCK_STREAM, 0);
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
        close(listeningSocket);
        return FALSE;
    }
    if (listen(listeningSocket, 1)) {
        DEBUGF("Couldn't listen on debug socket, errno: %d", errno);
        close(listeningSocket);
        return FALSE;
    }
    socklen_t len = sizeof(serverAddr);
    getsockname(listeningSocket, (struct sockaddr *) &serverAddr, &len);
    debugPort = ntohs(serverAddr.sin_port);
    DEBUGF("Listening for debug client on port %u", debugPort);
    if (options->printDebugPort) {
        if (options->debugPortFile) {
            FILE *f = fopen(options->debugPortFile, "w");
            if (!f) return FALSE;
            fprintf(f, "%d", debugPort);
            fclose(f);
        } else {
            fprintf(stderr, "[DEBUG] %s: debugPort=%d\n", LOG_TAG, debugPort);
        }
    }
    return TRUE;
}

jint swap32(jint val) {
    val = ((val << 8) & 0xff00ff00 ) | ((val >> 8) & 0xff00ff );
    return (val << 16) | (val >> 16);
}

jlong swap64(jlong val) {
    val = ((val << 8) & 0xff00ff00ff00ff00ULL ) | ((val >> 8) & 0x00ff00ff00ff00ffULL );
    val = ((val << 16) & 0xffff0000ffff0000ULL ) | ((val >> 16) & 0x0000ffff0000ffffULL );
    return (val << 32) | (val >> 32);
}

void writeChannel(int socket, void* buf, int numBytes, ChannelError* error) {
    if(numBytes == 0) {
        return;
    }
    size_t totalWrittenBytes = 0;
    while(totalWrittenBytes != numBytes) {
        size_t writtenBytes = send(socket, buf, numBytes - totalWrittenBytes, 0);
        if(writtenBytes == -1) {
            if(errno == EINTR) {
                continue;
            }
            error->errorCode = errno;
            error->message = "Couldn't write to channel";
            break;
        }
        if(writtenBytes == 0) {
            error->errorCode = -1;
            error->message = "Connection closed by client";
            break;
        }
        totalWrittenBytes += writtenBytes;
        buf += writtenBytes;
    }
    DEBUGF("Wrote %u bytes", totalWrittenBytes);
}

void writeChannelByte(int socket, char val, ChannelError* error) {
    writeChannel(socket, &val, 1, error);
}

void writeChannelInt(int socket, jint val, ChannelError* error) {
    val = swap32(val);
    DEBUGF("Writing %x (big endian)", val);
    writeChannel(socket, &val, 4, error);
}

void writeChannelLong(int socket, jlong val, ChannelError* error) {
    val = swap64(val);
    writeChannel(socket, &val, 8, error);
}

void readChannel(int socket, void* buf, int numBytes, ChannelError* error) {
    if(numBytes == 0) {
        return;
    }
    size_t totalReadBytes = 0;
    while(totalReadBytes != numBytes) {
        size_t readBytes = recv(socket, buf, numBytes - totalReadBytes, 0);
        if(readBytes == -1) {
            if(errno == EINTR) {
                continue;
            }
            error->errorCode = errno;
            error->message = "Couldn't read from channel";
            break;
        }
        if(readBytes == 0) {
            error->errorCode = -1;
            error->message = "Connection closed by client";
            break;
        }
        totalReadBytes += readBytes;
        buf += readBytes;
    }
}

char readChannelByte(int socket, ChannelError* error) {
    char val = 0;
    readChannel(socket, &val, 1, error);
    return val;
}

jint readChannelInt(int socket, ChannelError* error) {
    jint val = 0;
    readChannel(socket, &val, 4, error);
    return swap32(val);
}

jlong readChannelLong(int socket, ChannelError* error) {
    jlong val = 0;
    readChannel(socket, &val, 8, error);
    return swap64(val);
}

jboolean checkError(ChannelError* error) {
    if(error->errorCode) {
        DEBUGF("Error: %s, errno: ", error->message, error->errorCode);
        DEBUG("Closing client socket");
        close(clientSocket);
        return TRUE;
    } else {
        return FALSE;
    }
}

void handleReadMemory(char req, jlong reqId, jlong payloadSize, ChannelError* error) {
    jlong addr = readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    DEBUGF("Reading memory: %p, %lu bytes", addr, numBytes);
    rvmLockMutex(&writeMutex);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, numBytes, error);
    writeChannel(clientSocket, (void*)addr, numBytes, error);
    rvmUnlockMutex(&writeMutex);
}

void handleRequest(char req, jlong reqId, jlong payloadSize, ChannelError* error) {
    DEBUGF("req: %c, reqId: %lu, payloadSize: %lu", req, reqId, payloadSize);
    switch(req) {
        case 'r':
            handleReadMemory(req, reqId, payloadSize, error);
            break;
        default:
            error->errorCode = -1;
            error->message = "Unknown request";
    }
}

jboolean _rvmHookHandshake() {
    DEBUG("Performing handshake");
    if (!listeningSocket) {
        DEBUG("Can't perform handshake, no listening socket");
        return FALSE;
    }

    DEBUG("Waiting for client connection");
    struct sockaddr_storage clientAddr;
    clientSocket = 0;
    socklen_t len = sizeof(clientAddr);
    if ((clientSocket = accept(listeningSocket, (struct sockaddr *) &clientAddr, &len)) == -1) {
        DEBUGF("Couldn't accept TCP debug connection, errno: %d", errno);
        close(listeningSocket);
        return FALSE;
    }
    int yes = 1;
    setsockopt(clientSocket, IPPROTO_TCP, TCP_NODELAY, &yes, sizeof(int));

    DEBUG("Starting channel thread");
    rvmInitMutex(&writeMutex);
    int result = pthread_create(&debugThread, 0, channelLoop, 0);
    if(result) {
        DEBUGF("Couldn't start debug thread, error code: %d", result);
        close(clientSocket);
        return FALSE;
    }

    return TRUE;
}

void* channelLoop(void* data) {
    ChannelError error;
    error.errorCode = 0;
    error.message = 0;

    DEBUG("Starting debug thread loop");
    while(1) {
        char req = readChannelByte(clientSocket, &error);
        DEBUGF("Read request: '%c'", req);
        if(checkError(&error)) break;

        jlong reqId = readChannelLong(clientSocket, &error);
        DEBUGF("Read request id: %lu", reqId);
        if(checkError(&error)) break;

        jlong payLoadSize = readChannelLong(clientSocket, &error);
        DEBUGF("Read payload size: %lu", payLoadSize);
        if(checkError(&error)) break;

        handleRequest(req, reqId, payLoadSize, &error);
        if(checkError(&error)) break;
    }
    DEBUG("Terminating debug thread");
    pthread_exit(0);
}