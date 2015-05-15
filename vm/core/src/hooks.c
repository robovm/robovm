/*
 * Copyright (C) 2014 RoboVM AB
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
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <errno.h>
#include <netinet/tcp.h>
#include <robovm/types.h>
#include "private.h"

#define LOG_TAG "hooks"

#define HANDSHAKE_QUESTION 0x526f626f564d3f3fLL // RoboVM??
#define HANDSHAKE_ANSWER   0x526f626f564d2121LL // RoboVM!!

// memory operations
#define CMD_READ_MEMORY 1
#define CMD_READ_CSTRING 2
#define CMD_WRITE_MEMORY 3
#define CMD_WRITE_AND_BITS 4
#define CMD_WRITE_OR_BITS 5
#define CMD_ALLOCATE 6
#define CMD_FREE 7

// thread operations
#define CMD_THREAD_SUSPEND 50
#define CMD_THREAD_RESUME 51
#define CMD_THREAD_STEP 52
#define CMD_THREAD_INVOKE 53
#define CMD_THREAD_NEWSTRING 54
#define CMD_THREAD_NEWARRAY 55
#define CMD_THREAD_NEWINSTANCE 56

// class operations
#define CMD_CLASS_FILTER 70

// events
#define EVT_THREAD_ATTACHED 100
#define EVT_THREAD_STARTED 101
#define EVT_THREAD_DETTACHED 102
#define EVT_THREAD_SUSPENDED 103
#define EVT_THREAD_RESUMED 104
#define EVT_BREAKPOINT 105
#define EVT_THREAD_STEPPED 106
#define EVT_CLASS_LOAD 107
#define EVT_EXCEPTION 108

typedef struct {
    int errorCode;
    char* message;
} ChannelError;

typedef struct ClassFilter {
    char* className;
    struct ClassFilter* next;
} ClassFilter;

Mutex classFilterMutex;
ClassFilter* classFilters = 0;

// set when receiving a vm suspend cmd
jboolean resumeFlag = FALSE;

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
static void* channelLoop(void* data);

// used to offset in PIE/ASLR mode
void* robovmBaseSymbol = NULL;

static void nsleep(jlong millis) {
    struct timespec time;
    time.tv_sec = 0;
    time.tv_nsec = millis * 1000000l;
    nanosleep(&time, 0);
}

static jint swap32(jint val) {
    return htonl(val);
}

static jlong swap64(jlong val) {
    unsigned long long high_part = (unsigned long long)htonl((unsigned int)(val >> 32));
    unsigned long long low_part = (unsigned long long)htonl((unsigned int)(val & 0xFFFFFFFFLL));
    return (jlong)((low_part << 32) | high_part);
}

static inline jboolean checkBit(jbyte* tbl, jint offset) {
    jbyte b = tbl[offset >> 3];
    jbyte mask = 1 << (offset & 0x7);
    return (b & mask) != 0 ? TRUE : FALSE;
}

static void writeChannel(int socket, void* buf, int numBytes, ChannelError* error) {
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
}

static void writeChannelByte(int socket, char val, ChannelError* error) {
    // DEBUGF("Writting byte %d", val);
    writeChannel(socket, &val, 1, error);
}

static void writeChannelInt(int socket, jint val, ChannelError* error) {
    // DEBUGF("Writting int %d", val);
    val = swap32(val);
    writeChannel(socket, &val, 4, error);
}

static void writeChannelLong(int socket, jlong val, ChannelError* error) {
    // DEBUGF("Writting long %llx", val);
    val = swap64(val);
    writeChannel(socket, &val, 8, error);
}

static void readChannel(int socket, void* buf, int numBytes, ChannelError* error) {
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

static char readChannelByte(int socket, ChannelError* error) {
    char val = 0;
    readChannel(socket, &val, 1, error);
    return val;
}

static jint readChannelInt(int socket, ChannelError* error) {
    jint val = 0;
    readChannel(socket, &val, 4, error);
    return swap32(val);
}

static jlong readChannelLong(int socket, ChannelError* error) {
    jlong val = 0;
    readChannel(socket, &val, 8, error);
    return swap64(val);
}

static jboolean checkError(ChannelError* error) {
    if(error->errorCode) {
        DEBUGF("Error: %s, errno: ", error->message, error->errorCode);
        DEBUG("Closing client socket");
        close(clientSocket);
        return TRUE;
    } else {
        return FALSE;
    }
}

void rvmHookWaitForResume(Options* options) {
    // resumeFlag is modified in handleVmResume()
    // we sync on the write mutex to ensure
    // caches are flushed
    DEBUG("Waiting for debugger to resume");
    while(TRUE) {
        rvmLockMutex(&writeMutex);
        jboolean isResumed = resumeFlag;
        rvmUnlockMutex(&writeMutex);
        if (isResumed == FALSE) {
            nsleep(50);
        } else {
            DEBUG("Debugger attached");
            break;
        }
    }
}

void _rvmHookBeforeMainThreadAttached(Env* env) {
    DEBUG("Before main thread attached");
}

void _rvmHookBeforeAppEntryPoint(Env* env, char* mainClass) {
    DEBUGF("Before app entry point %s", mainClass);
    if(env->vm->options->waitForResume) {
        rvmHookWaitForResume(env->vm->options);
    }
}

void _rvmHookThreadCreated(Env* env, JavaThread* threadObj) {
    DEBUGF("Thread %lld created", threadObj->id);
}

void _rvmHookThreadAttached(Env* env, JavaThread* threadObj, Thread* thread) {
    DEBUGF("Thread %p attached, threadObj: %p, thread: %p", threadObj->id, threadObj, thread);
    rvmLockMutex(&writeMutex);
    ChannelError error = { 0 };
    writeChannelByte(clientSocket, EVT_THREAD_ATTACHED, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, (jlong)threadObj, &error);
    writeChannelLong(clientSocket, (jlong)thread, &error);
    rvmUnlockMutex(&writeMutex);
}

void _rvmHookThreadStarting(Env* env, JavaThread* threadObj, Thread* thread) {
    DEBUGF("Thread %lld starting, threadObj: %p, thread: %p", threadObj->id, threadObj, thread);
    rvmLockMutex(&writeMutex);
    ChannelError error = { 0 };
    writeChannelByte(clientSocket, EVT_THREAD_STARTED, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, (jlong)threadObj, &error);
    writeChannelLong(clientSocket, (jlong)thread, &error);
    rvmUnlockMutex(&writeMutex);
}

void _rvmHookThreadDetaching(Env* env, JavaThread* threadObj, Thread* thread, Object* throwable) {
    DEBUGF("Thread %p detaching, threadObj: %p, thread: %p, throwable: %p", threadObj->id, threadObj, thread, throwable);
    rvmLockMutex(&writeMutex);
    ChannelError error = { 0 };
    writeChannelByte(clientSocket, EVT_THREAD_DETTACHED, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, 24, &error);
    writeChannelLong(clientSocket, (jlong)threadObj, &error);
    writeChannelLong(clientSocket, (jlong)thread, &error);
    writeChannelLong(clientSocket, (jlong)throwable, &error);
    rvmUnlockMutex(&writeMutex);
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

jboolean _rvmHookHandshake(Options* options) {
    DEBUG("Performing handshake");
    if (!listeningSocket) {
        DEBUG("Can't perform handshake, no listening socket");
        return FALSE;
    }

    // setup our write and class filter mutex
    rvmInitMutex(&writeMutex);
    rvmInitMutex(&classFilterMutex);

    DEBUG("Waiting for client connection");
    struct sockaddr_storage clientAddr;
    clientSocket = 0;
    socklen_t len = sizeof(clientAddr);
    while(TRUE) {
        clientSocket = accept(listeningSocket, (struct sockaddr *) &clientAddr, &len);
        if(clientSocket == -1) {
            if(errno == EINTR) {
                continue;
            } else {
                DEBUGF("Couldn't accept TCP debug connection, errno: %d", errno);
                close(listeningSocket);
                return FALSE;
            }
        } else {
            break;
        }
    }
    int yes = 1;
    setsockopt(clientSocket, IPPROTO_TCP, TCP_NODELAY, &yes, sizeof(int));

    // Let's see if we are taking to a RoboVM debug client
    rvmLockMutex(&writeMutex);
    ChannelError error = { 0 };
    writeChannelLong(clientSocket, HANDSHAKE_QUESTION, &error);
    jlong response = readChannelLong(clientSocket, &error);
    if(checkError(&error) || response != HANDSHAKE_ANSWER) {
        DEBUG("Handshake failed");
        close(clientSocket);
        return FALSE;
    }

    // send base symbol address to deal with PIE/ASLR
    writeChannelLong(clientSocket, (jlong) &robovmBaseSymbol, &error);
    rvmUnlockMutex(&writeMutex);
    DEBUG("Handshake complete");

    // setup
    int result = pthread_create(&debugThread, 0, channelLoop, 0);
    if(result) {
        DEBUGF("Couldn't start debug thread, error code: %d", result);
        close(clientSocket);
        return FALSE;
    }

    return TRUE;
}

static void handleReadMemory(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    // DEBUGF("Reading memory: %p, %u bytes", addr, numBytes);
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_READ_MEMORY, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, numBytes, error);
    writeChannel(clientSocket, addr, numBytes, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleReadString(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    // DEBUGF("Reading string: %p, %s", addr, (char*)addr);
    rvmLockMutex(&writeMutex);
    size_t len = strlen((char*)addr);
    writeChannelByte(clientSocket, CMD_READ_CSTRING, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, len, error);
    writeChannel(clientSocket, addr, len, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleWriteMemory(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    // DEBUGF("Writing to memory %p, num bytes: %u", addr, numBytes);
    rvmLockMutex(&writeMutex);
    readChannel(clientSocket, addr, numBytes, error);
    writeChannelByte(clientSocket, CMD_WRITE_MEMORY, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleAndBits(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jbyte mask = readChannelByte(clientSocket, error);
    if(checkError(error)) return;

    rvmLockMutex(&writeMutex);
    char orig = *((char*)addr);
    char value = orig & mask;
    *((char*)addr) = value;
    // DEBUGF("And-ing bits at %p (=%x) with %x = %x", addr, orig, mask, value);
    writeChannelByte(clientSocket, CMD_WRITE_AND_BITS, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleOrBits(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jbyte mask = readChannelByte(clientSocket, error);
    if(checkError(error)) return;

    rvmLockMutex(&writeMutex);
    char orig = *((char*)addr);
    char value = orig | mask;
    *((char*)addr) = value;
    // DEBUGF("Or-ing bits at %p (=%x) with %x = %x", addr, orig, mask, value);
    writeChannelByte(clientSocket, CMD_WRITE_OR_BITS, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleAllocate(jlong reqId, ChannelError* error) {
    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    rvmLockMutex(&writeMutex);
    void* addr = calloc(numBytes, 1);
    // DEBUGF("Allocated %u bytes, at %p", numBytes, addr);
    writeChannelByte(clientSocket, CMD_ALLOCATE, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 8, error);
    writeChannelLong(clientSocket, (jlong)addr, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleFree(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    rvmLockMutex(&writeMutex);
    free(addr);
    // DEBUGF("Freed memory at %p", addr);
    writeChannelByte(clientSocket, CMD_FREE, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleClassFilter(jlong reqId, ChannelError* error) {
    jboolean isSet = readChannelByte(clientSocket, error) == -1? TRUE: FALSE;
    jint classNameLength = readChannelInt(clientSocket, error);

    char* className = (char*)malloc(classNameLength + 1);
    className[classNameLength] = 0;
    readChannel(clientSocket, className, classNameLength, error);
    if(checkError(error)) {
        free(className);
        return;
    }

    rvmLockMutex(&classFilterMutex);
    if(isSet) {
        ClassFilter* filter = (ClassFilter*)malloc(sizeof(ClassFilter));
        filter->className = className;
        filter->next = 0;

        if(classFilters == 0) {
            classFilters = filter;
        } else {
            filter->next = classFilters;
            classFilters = filter;
        }
    } else {
        ClassFilter* prev = 0;
        for(ClassFilter* f = classFilters; f; prev = f, f = f->next) {
            if(!strcmp(f->className, className)) {
                free(f->className);
                free(f);
                if(!prev) {
                    classFilters = f->next;
                } else {
                    prev->next = f->next;
                }
            }
        }
    }
    rvmUnlockMutex(&classFilterMutex);

    rvmLockMutex(&writeMutex);
    // DEBUGF("Set/removed class filter, req id: %d", reqId);
    writeChannelByte(clientSocket, CMD_CLASS_FILTER, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleThreadSuspend(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    DebugEnv* debugEnv = (DebugEnv*) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    // the order of the next few lines is important
    // as we don't use a lock in getSuspendedEvent()
    // around stepping and suspended.
    debugEnv->stepping = FALSE;
    debugEnv->pclow = 0;
    debugEnv->pchigh = 0;
    debugEnv->pclow2 = 0;
    debugEnv->pchigh2 = 0;
    debugEnv->suspended = TRUE;
    rvmUnlockMutex(&debugEnv->suspendMutex);

    DEBUGF("Suspending thread %p, id %u", thread, thread->threadId);
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_SUSPEND, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleThreadResume(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    // special case: if thread is 0, we set the resumeFlag
    // to true so rvmHookWaitForResume exits
    if(thread == 0) {
        DEBUG("Resuming VM");
        rvmLockMutex(&writeMutex);
        resumeFlag = TRUE;
        rvmUnlockMutex(&writeMutex);
    } else {
        rvmLockMutex(&writeMutex);
        resumeFlag = TRUE;
        rvmUnlockMutex(&writeMutex);
        DEBUGF("Resuming thread %p, id %u", thread, thread->threadId);
        DebugEnv *debugEnv = (DebugEnv *) thread->env;
        rvmLockMutex(&debugEnv->suspendMutex);
        debugEnv->suspended = FALSE;
        pthread_cond_signal(&debugEnv->suspendCond);
        rvmUnlockMutex(&debugEnv->suspendMutex);
    }

    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_RESUME, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleThreadStep(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jlong pclow = readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jlong pchigh = readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jlong pclow2 = readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jlong pchigh2 = readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    DEBUGF("Stepping thread %p, id %u, pclow: %p, pchigh: %p, pclow2: %p, pchigh2: %p", thread, thread->threadId, pclow, pchigh, pclow2, pchigh2);
    DebugEnv *debugEnv = (DebugEnv *) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    // the order of the next few lines is important
    // as we don't use a lock in getSuspendedEvent()
    // around stepping.
    debugEnv->pclow = (void*)pclow;
    debugEnv->pchigh = (void*)pchigh;
    debugEnv->pclow2 = (void*)pclow2;
    debugEnv->pchigh2 = (void*)pchigh2;
    debugEnv->stepping = TRUE;
    rvmUnlockMutex(&debugEnv->suspendMutex);

    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_STEP, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleThreadInvoke(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    void* classOrObjectPtr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint methodNameLen = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    char* methodName = (char*)malloc(methodNameLen + 1);
    methodName[methodNameLen] = 0;
    readChannel(clientSocket, methodName, methodNameLen, error);
    if(checkError(error)) {
        free(methodName);
        return;
    }

    jint descriptorLen = readChannelInt(clientSocket, error);
    if(checkError(error)) {
        free(methodName);
        return;
    }

    char* descriptor = (char*)malloc(descriptorLen + 1);
    descriptor[descriptorLen] = 0;
    readChannel(clientSocket, descriptor, descriptorLen, error);
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    jboolean isClassMethod = readChannelByte(clientSocket, error)? TRUE:FALSE;
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    jbyte returnType = readChannelByte(clientSocket, error);
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    jvalue* arguments = (jvalue*)readChannelLong(clientSocket, error);
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    DebugEnv *debugEnv = (DebugEnv *) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->command = CMD_THREAD_INVOKE;
    debugEnv->reqId = reqId;
    debugEnv->classOrObjectPtr = classOrObjectPtr;
    debugEnv->methodName = methodName;
    debugEnv->descriptor = descriptor;
    debugEnv->isClassMethod = isClassMethod;
    debugEnv->returnType = returnType;
    debugEnv->arguments = arguments;
    pthread_cond_signal(&debugEnv->suspendCond);
    rvmUnlockMutex(&debugEnv->suspendMutex);
}

static void handleNewInstance(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    void* classPtr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint methodNameLen = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    char* methodName = (char*)malloc(methodNameLen + 1);
    methodName[methodNameLen] = 0;
    readChannel(clientSocket, methodName, methodNameLen, error);
    if(checkError(error)) {
        free(methodName);
        return;
    }

    jint descriptorLen = readChannelInt(clientSocket, error);
    if(checkError(error)) {
        free(methodName);
        return;
    }

    char* descriptor = (char*)malloc(descriptorLen + 1);
    descriptor[descriptorLen] = 0;
    readChannel(clientSocket, descriptor, descriptorLen, error);
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    jvalue* arguments = (jvalue*)readChannelLong(clientSocket, error);
    if(checkError(error)) {
        free(descriptor);
        free(methodName);
        return;
    }

    DebugEnv *debugEnv = (DebugEnv *) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->command = CMD_THREAD_NEWINSTANCE;
    debugEnv->reqId = reqId;
    debugEnv->classOrObjectPtr = classPtr;
    debugEnv->methodName = methodName;
    debugEnv->descriptor = descriptor;
    debugEnv->isClassMethod = FALSE;
    debugEnv->returnType = 0;
    debugEnv->arguments = arguments;
    pthread_cond_signal(&debugEnv->suspendCond);
    rvmUnlockMutex(&debugEnv->suspendMutex);
}

static void handleNewString(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint stringLength = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    char* string = (char*)malloc(stringLength + 1);
    string[stringLength] = 0;
    readChannel(clientSocket, string, stringLength, error);
    if(checkError(error)) {
        free(string);
        return;
    }

    DebugEnv *debugEnv = (DebugEnv *) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->command = CMD_THREAD_NEWSTRING;
    debugEnv->reqId = reqId;
    debugEnv->string = string;
    debugEnv->stringLength = stringLength;
    pthread_cond_signal(&debugEnv->suspendCond);
    rvmUnlockMutex(&debugEnv->suspendMutex);
}

static void handleNewArray(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint arrayLength = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    jint elementNameLength = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    char* elementName = (char*)malloc(elementNameLength + 1);
    elementName[elementNameLength] = 0;
    readChannel(clientSocket, elementName, elementNameLength, error);
    if(checkError(error)) {
        free(elementName);
        return;
    }

    DebugEnv *debugEnv = (DebugEnv *) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->command = CMD_THREAD_NEWARRAY;
    debugEnv->reqId = reqId;
    debugEnv->arrayLength = arrayLength;
    debugEnv->elementName = elementName;
    debugEnv->elementNameLength = elementNameLength;
    pthread_cond_signal(&debugEnv->suspendCond);
    rvmUnlockMutex(&debugEnv->suspendMutex);
}

static void addGcRoot(DebugEnv* debugEnv, Object* obj) {
    if(obj == NULL) {
        return;
    }
    if(rvmExceptionCheck((Env*)debugEnv)) {
        return;
    }

    DebugGcRoot* root = (DebugGcRoot*) gcAllocate(sizeof(DebugGcRoot));
    root->root = obj;
    root->next = NULL;
    if(debugEnv->gcRoot == NULL) {
        debugEnv->gcRoot = root;
    } else {
        root->next = debugEnv->gcRoot;
        debugEnv->gcRoot = root;
    }
    gcAddRoot(obj);
    DEBUGF("Added %p, class %s, as GC root", obj, obj->clazz->name);
}

static void removeGcRoots(DebugEnv* debugEnv) {
    // DebugEnv (Env) is allocated via the GC
    // the roots should be scanned if DebugEnv
    // is scanned. By nulling out the roots
    // the GC should free the root nodes.
    debugEnv->gcRoot = NULL;
}

static jlong invokeClassMethod(DebugEnv* debugEnv, Method* method) {
    Env* env = (Env*)debugEnv;
    Class* clazz = (Class*)debugEnv->classOrObjectPtr;
    jlong result = 0;

    DEBUGF("Invoking class method %s%s on class %s", method->name, method->desc, clazz->name);
    switch(debugEnv->returnType) {
        case 'Z':
            *((jboolean*)&result) = rvmCallBooleanClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'B':
            *((jbyte*)&result) = rvmCallByteClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'C':
            *((jchar*)&result) = rvmCallCharClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'S':
            *((jshort*)&result) = rvmCallShortClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'I':
            *((jint*)&result) = rvmCallIntClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'J':
            *((jlong*)&result) = rvmCallLongClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'F':
            *((jfloat*)&result) = rvmCallFloatClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'D':
            *((jdouble*)&result) = rvmCallDoubleClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        case 'L':
            result = (jlong)rvmCallObjectClassMethodA(env, clazz, method, debugEnv->arguments);
            addGcRoot(debugEnv, (Object*)result);
            break;
        case 'V':
            rvmCallVoidClassMethodA(env, clazz, method, debugEnv->arguments);
            break;
        default:
            rvmThrowIllegalArgumentException(env, "Unknown return type");
    }

    if(rvmExceptionCheck(env)) {
        return 0;
    } else {
        return result;
    }
}

static jlong invokeInstanceMethod(DebugEnv* debugEnv, Method* method) {
    Env* env = (Env*)debugEnv;
    Object* obj = (Object*)debugEnv->classOrObjectPtr;
    jlong result = 0;

    DEBUGF("Invoking instance method %s%s on object %p", method->name, method->desc, obj);
    switch(debugEnv->returnType) {
        case 'Z':
            *((jboolean*)&result) = rvmCallBooleanInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'B':
            *((jbyte*)&result) = rvmCallByteInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'C':
            *((jchar*)&result) = rvmCallCharInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'S':
            *((jshort*)&result) = rvmCallShortInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'I':
            *((jint*)&result) = rvmCallIntInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'J':
            *((jlong*)&result) = rvmCallLongInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'F':
            *((jfloat*)&result) = rvmCallFloatInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'D':
            *((jdouble*)&result) = rvmCallDoubleInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        case 'L':
            result = (jlong)rvmCallObjectInstanceMethodA(env, obj, method, debugEnv->arguments);
            addGcRoot(debugEnv, (Object*)result);
            break;
        case 'V':
            rvmCallVoidInstanceMethodA(env, obj, method, debugEnv->arguments);
            break;
        default:
            rvmThrowIllegalArgumentException(env, "Unknown return type");
    }

    if(rvmExceptionCheck(env)) {
        return 0;
    } else {
        return result;
    }
}

static void invokeMethod(DebugEnv* debugEnv) {
    Class* clazz = debugEnv->isClassMethod? (Class*)debugEnv->classOrObjectPtr: ((Object*)debugEnv->classOrObjectPtr)->clazz;
    Method* method = rvmGetMethod((Env*)debugEnv, clazz, debugEnv->methodName, debugEnv->descriptor);
    if(!method) {
        ChannelError error;
        rvmLockMutex(&writeMutex);
        writeChannelByte(clientSocket, CMD_THREAD_INVOKE, &error);
        writeChannelLong(clientSocket, debugEnv->reqId, &error);
        writeChannelLong(clientSocket, 16, &error);
        writeChannelLong(clientSocket, 0, &error);
        writeChannelLong(clientSocket, (jlong)rvmExceptionClear((Env*)debugEnv), &error);
        rvmUnlockMutex(&writeMutex);
        debugEnv->reqId = 0;
        free(debugEnv->methodName);
        free(debugEnv->descriptor);
        return;
    }

    jlong result = 0;
    if(debugEnv->isClassMethod) {
        result = invokeClassMethod(debugEnv, method);
    } else {
        result = invokeInstanceMethod(debugEnv, method);
    }

    ChannelError error;
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_INVOKE, &error);
    writeChannelLong(clientSocket, debugEnv->reqId, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, result, &error);
    writeChannelLong(clientSocket, (jlong) rvmExceptionClear((Env*)debugEnv), &error);
    rvmUnlockMutex(&writeMutex);
    debugEnv->reqId = 0;
    free(debugEnv->methodName);
    free(debugEnv->descriptor);
}

static void newInstance(DebugEnv* debugEnv) {
    Class* clazz = debugEnv->classOrObjectPtr;
    Method* method = rvmGetMethod((Env*)debugEnv, clazz, debugEnv->methodName, debugEnv->descriptor);
    if(!method) {
        ChannelError error;
        rvmLockMutex(&writeMutex);
        writeChannelByte(clientSocket, CMD_THREAD_NEWINSTANCE, &error);
        writeChannelLong(clientSocket, debugEnv->reqId, &error);
        writeChannelLong(clientSocket, 16, &error);
        writeChannelLong(clientSocket, 0, &error);
        writeChannelLong(clientSocket, (jlong)rvmExceptionClear((Env*)debugEnv), &error);
        rvmUnlockMutex(&writeMutex);
        debugEnv->reqId = 0;
        free(debugEnv->methodName);
        free(debugEnv->descriptor);
        return;
    }

    Object* obj = rvmAllocateObject((Env*)debugEnv, clazz);
    DEBUGF("Allocated new object of type %s (%p), using constructor %s%s", obj->clazz->name, obj, debugEnv->methodName, debugEnv->descriptor);
    if(!obj) {
        ChannelError error;
        rvmLockMutex(&writeMutex);
        writeChannelByte(clientSocket, CMD_THREAD_NEWINSTANCE, &error);
        writeChannelLong(clientSocket, debugEnv->reqId, &error);
        writeChannelLong(clientSocket, 16, &error);
        writeChannelLong(clientSocket, 0, &error);
        writeChannelLong(clientSocket, (jlong) rvmExceptionClear((Env *) debugEnv), &error);
        rvmUnlockMutex(&writeMutex);
        debugEnv->reqId = 0;
        free(debugEnv->methodName);
        free(debugEnv->descriptor);
        return;
    }
    debugEnv->classOrObjectPtr = obj;
    debugEnv->returnType = 'V';
    invokeInstanceMethod(debugEnv, method);

    addGcRoot(debugEnv, obj);

    ChannelError error;
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_NEWINSTANCE, &error);
    writeChannelLong(clientSocket, debugEnv->reqId, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, (jlong)obj, &error);
    writeChannelLong(clientSocket, (jlong)rvmExceptionClear((Env*)debugEnv), &error);
    rvmUnlockMutex(&writeMutex);
    debugEnv->reqId = 0;
    free(debugEnv->methodName);
    free(debugEnv->descriptor);
}

static void newString(DebugEnv* debugEnv) {
    DEBUGF("Creating new string \"%s\"", debugEnv->string);
    ChannelError error;

    Object* str = rvmNewStringUTF((Env*)debugEnv, debugEnv->string, debugEnv->stringLength);
    addGcRoot(debugEnv, str);

    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_NEWSTRING, &error);
    writeChannelLong(clientSocket, debugEnv->reqId, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, (jlong) str, &error);
    writeChannelLong(clientSocket, (jlong) rvmExceptionClear((Env*)debugEnv), &error);
    rvmUnlockMutex(&writeMutex);
    free(debugEnv->string);
    debugEnv->reqId = 0;
}

static void newArray(DebugEnv* debugEnv) {
    DEBUGF("Creating new array, length: %d, element type: %s", debugEnv->arrayLength, debugEnv->elementName);
    ChannelError error;
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_NEWARRAY, &error);
    writeChannelLong(clientSocket, debugEnv->reqId, &error);
    writeChannelLong(clientSocket, 16, &error);
    Object* result = NULL;
    if (!strcmp(debugEnv->elementName, "Z")) {
        result = (Object*)rvmNewBooleanArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "B")) {
        result = (Object*)rvmNewByteArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "C")) {
        result = (Object*)rvmNewCharArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "S")) {
        result = (Object*)rvmNewShortArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "I")) {
        result = (Object*)rvmNewIntArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "J")) {
        result = (Object*)rvmNewLongArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "F")) {
        result = (Object*)rvmNewFloatArray((Env *) debugEnv, debugEnv->arrayLength);
    } else if (!strcmp(debugEnv->elementName, "D")) {
        result = (Object*)rvmNewDoubleArray((Env *) debugEnv, debugEnv->arrayLength);
    } else {
        Class* elementClass = rvmFindClass((Env *) debugEnv, debugEnv->elementName);
        result = (Object*)rvmNewObjectArray((Env *) debugEnv, debugEnv->arrayLength, elementClass, NULL, NULL);
    }

    if (!result) {
        rvmThrowInstantiationError((Env*)debugEnv, "Couldn't instantiate array");
    }

    addGcRoot(debugEnv, result);

    writeChannelLong(clientSocket, (jlong) result, &error);
    writeChannelLong(clientSocket, (jlong) rvmExceptionClear((Env *) debugEnv), &error);
    rvmUnlockMutex(&writeMutex);
    free(debugEnv->elementName);
    debugEnv->reqId = 0;
}

static void handleRequest(char req, jlong reqId, jlong payloadSize, ChannelError* error) {
    // DEBUGF("req: %d, reqId: %llu, payloadSize: %llu", req, reqId, payloadSize);
    switch(req) {
        case CMD_READ_MEMORY:
            handleReadMemory(reqId, error);
            break;
        case CMD_READ_CSTRING:
            handleReadString(reqId, error);
            break;
        case CMD_WRITE_MEMORY:
            handleWriteMemory(reqId, error);
            break;
        case CMD_WRITE_AND_BITS:
            handleAndBits(reqId, error);
            break;
        case CMD_WRITE_OR_BITS:
            handleOrBits(reqId, error);
            break;
        case CMD_ALLOCATE:
            handleAllocate(reqId, error);
            break;
        case CMD_FREE:
            handleFree(reqId, error);
            break;
        case CMD_CLASS_FILTER:
            handleClassFilter(reqId, error);
            break;
        case CMD_THREAD_SUSPEND:
            handleThreadSuspend(reqId, error);
            break;
        case CMD_THREAD_RESUME:
            handleThreadResume(reqId, error);
            break;
        case CMD_THREAD_STEP:
            handleThreadStep(reqId, error);
            break;
        case CMD_THREAD_INVOKE:
            handleThreadInvoke(reqId, error);
            break;
        case CMD_THREAD_NEWSTRING:
            handleNewString(reqId, error);
            break;
        case CMD_THREAD_NEWARRAY:
            handleNewArray(reqId, error);
            break;
        case CMD_THREAD_NEWINSTANCE:
            handleNewInstance(reqId, error);
            break;
        default:
            error->errorCode = -1;
            error->message = "Unknown request";
    }
}

static void* channelLoop(void* data) {
    ChannelError error = { 0 };

    DEBUG("Starting debug thread loop");
    while(1) {
        char req = readChannelByte(clientSocket, &error);
        if(checkError(&error)) break;

        jlong reqId = readChannelLong(clientSocket, &error);
        if(checkError(&error)) break;

        jlong payLoadSize = readChannelLong(clientSocket, &error);
        if(checkError(&error)) break;

        handleRequest(req, reqId, payLoadSize, &error);
        if(checkError(&error)) break;
    }
    DEBUG("Terminating debug thread");
    pthread_exit(0);
}

static inline char getSuspendedEvent(DebugEnv* debugEnv, jint lineNumberOffset, jbyte* bptable, void* pc) {
    if (debugEnv->suspended) {
        return EVT_THREAD_SUSPENDED;
    }
    if (debugEnv->stepping && ((pc >= debugEnv->pclow && pc < debugEnv->pchigh) || (pc >= debugEnv->pclow2 && pc < debugEnv->pchigh2))) {
        return EVT_THREAD_STEPPED;
    }

    // we only check for breakpoints if we aren't invoking
    // lineNumberOffset may be < 0 if the instrumented unit
    // is part of a single line multi-statement
    if (!debugEnv->reqId && lineNumberOffset >= 0) {
        if (checkBit(bptable, lineNumberOffset)) {
            return EVT_BREAKPOINT;
        }
    }
    return 0;
}

static inline void suspendLoop(DebugEnv* debugEnv) {
    ChannelError error;
    debugEnv->reqId = 0;
    debugEnv->stepping = FALSE;
    debugEnv->pclow = debugEnv->pclow2 = 0;
    debugEnv->pchigh = debugEnv->pchigh2 = 0;
    debugEnv->suspended = TRUE;
    while (debugEnv->suspended) {
        pthread_cond_wait(&debugEnv->suspendCond, &debugEnv->suspendMutex);

        // If reqId is set, we have  method invocation request (see handleThreadInvoke),
        // or a new instance request (see handleNewInstance, handleNewString, handleNewArray)
        // we temporarily reset the suspend flag, set the ignore exceptions flag, invoke the
        // method, then go back into waiting for being woken up again
        if(debugEnv->reqId != 0) {
            debugEnv->suspended = FALSE;
            debugEnv->ignoreExceptions = TRUE;
            switch(debugEnv->command) {
                case CMD_THREAD_INVOKE:
                    invokeMethod(debugEnv);
                    break;
                case CMD_THREAD_NEWSTRING:
                    newString(debugEnv);
                    break;
                case CMD_THREAD_NEWARRAY:
                    newArray(debugEnv);
                    break;
                case CMD_THREAD_NEWINSTANCE:
                    newInstance(debugEnv);
                    break;
                default:
                    DEBUGF("Unknown invoke/newinstance command %d", debugEnv->command);
            }
            debugEnv->ignoreExceptions = FALSE;
            debugEnv->suspended = TRUE;
        }
    }

    removeGcRoots(debugEnv);

    DEBUGF("Thread %p, id %u resumed", debugEnv->env.currentThread, debugEnv->env.currentThread->threadId);
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, EVT_THREAD_RESUMED, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, 16, &error);
    writeChannelLong(clientSocket, (jlong)debugEnv->env.currentThread->threadObj, &error);
    writeChannelLong(clientSocket, (jlong)debugEnv->env.currentThread, &error);
    rvmUnlockMutex(&writeMutex);
}

static jboolean prepareCallStack(Env* env, char event, CallStack** callStack, jint* length, jint* payloadSize, CallStackFrame** firstFrame) {
    DebugEnv* debugEnv = (DebugEnv*)env;
    // need to ignore exceptions, as the callstack unwinding
    // might throw one, resulting in infinite recursion in
    // rvmHookExceptionRaised
    jboolean oldIgnoreException = debugEnv->ignoreExceptions;
    debugEnv->ignoreExceptions = TRUE;

    *callStack = rvmCaptureCallStack(env);
    if (rvmExceptionCheck(env)) {
        Object* ex = rvmExceptionClear(env);
        ERRORF("Failed to get a call stack for thread %p due to event %d. Got an exception of type: %s",
                env->currentThread, event, ex->clazz->name);
        return FALSE;
    }

    jint index = 0;
    jint classNamesSize = 0;
    CallStackFrame* frame = NULL;
    *firstFrame = NULL;
    while ((frame = rvmGetNextCallStackMethod(env, *callStack, &index)) != NULL) {
        if(!*firstFrame) {
            *firstFrame = frame;
        }
        (*length)++;
        classNamesSize += strlen(frame->method->clazz->name);
    }
    *payloadSize = sizeof(jint) + (*length) * (sizeof(jlong) * 2 + sizeof(jint) * 2) + classNamesSize;

    // reset old exception handling
    debugEnv->ignoreExceptions = oldIgnoreException;

    if(length == 0) {
        ERRORF("No frames on callstack of thread %p for event %d.", env->currentThread, event);
        return FALSE;
    }
    return TRUE;
}

static void writeCallstack(Env* env, jint length, CallStack* callStack ) {
    ChannelError error;
    writeChannelInt(clientSocket, length, &error);
    jint index = 0;
    CallStackFrame* frame = NULL;
    while ((frame = rvmGetNextCallStackMethod(env, callStack, &index)) != NULL) {
        // TODO: Handle proxy methods
        writeChannelLong(clientSocket, (jlong)(frame->method->impl), &error);
        writeChannelInt(clientSocket, frame->lineNumber, &error);
        writeChannelLong(clientSocket, (jlong)(frame->fp), &error);
        jint strLen = strlen(frame->method->clazz->name);
        writeChannelInt(clientSocket, strLen, &error);
        writeChannel(clientSocket, (void*)frame->method->clazz->name, strLen, &error);
    }
}

static void writeStopOrExceptionEvent(Env* env, char event, Object* throwable, jboolean isCaught, CallStack* callStack, jint callStackLength, jint callStackPayloadSize) {
    ChannelError error = { 0 };
    int payLoadSize = sizeof(jlong) * (event == EVT_EXCEPTION? 3: 2) + (event == EVT_EXCEPTION? 1: 0) + callStackPayloadSize;

    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, event, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, payLoadSize, &error);
    writeChannelLong(clientSocket, (jlong)env->currentThread->threadObj, &error);
    writeChannelLong(clientSocket, (jlong)env->currentThread, &error);
    if(event == EVT_EXCEPTION) {
        writeChannelLong(clientSocket, (jlong)throwable, &error);
        writeChannelByte(clientSocket, isCaught? -1: 0, &error);
    }
    writeCallstack(env, callStackLength, callStack);
    rvmUnlockMutex(&writeMutex);
}

void _rvmHookClassLoaded(Env* env, Class* clazz, void* classInfo) {
    JavaThread* javaThread = NULL;
    Thread* thread = NULL;

    // check if there's a filter for that class
    rvmLockMutex(&classFilterMutex);
    for(ClassFilter* f = classFilters; f; f = f->next) {
        if(!strcmp(f->className, clazz->name)) {            
            if(env->currentThread) {
                javaThread = env->currentThread->threadObj;
                thread = env->currentThread;
            }
            break;
        }
    }
    rvmUnlockMutex(&classFilterMutex);

    CallStack* callStack = NULL;
    jint callStackLength = 0;
    jint callStackPayloadSize = 0;
    CallStackFrame* frame = NULL;
    // only report the callstack if the class is
    // filtered
    if(javaThread) {
        if (!prepareCallStack(env, EVT_CLASS_LOAD, &callStack, &callStackLength, &callStackPayloadSize, &frame)) {
            ERROR("Couldn't prepare callstack for class load event, not reporting callstack");
            javaThread = NULL;
            thread = NULL;
            callStackPayloadSize = 0;
        }

        if (IS_DEBUG_ENABLED) {
            DEBUGF("Suspending thread %p with id %u due to class load event %s", env->currentThread,
                env->currentThread->threadId, clazz->name);
        }
    } else {
        if (IS_DEBUG_ENABLED) {
            DEBUGF("Not suspending thread due to class load event %s", clazz->name);
        }
    }

    // DEBUGF("Loaded class %s, Class*: %p, ClassInfo*: %p, Thread*: %p", clazz->name, clazz, classInfo, thread);
    DebugEnv* debugEnv = (DebugEnv*)env;
    rvmLockMutex(&writeMutex);
    ChannelError error = { 0 };
    writeChannelByte(clientSocket, EVT_CLASS_LOAD, &error);
    writeChannelLong(clientSocket, 0, &error);
    writeChannelLong(clientSocket, 32 + callStackPayloadSize, &error);
    writeChannelLong(clientSocket, (jlong)javaThread, &error);
    writeChannelLong(clientSocket, (jlong)thread, &error);
    writeChannelLong(clientSocket, (jlong)clazz, &error);
    writeChannelLong(clientSocket, (jlong)classInfo, &error);
    if(javaThread) writeCallstack(env, callStackLength, callStack);
    rvmUnlockMutex(&writeMutex);

    if(javaThread) {
        rvmLockMutex(&debugEnv->suspendMutex);
        suspendLoop(debugEnv);
        rvmUnlockMutex(&debugEnv->suspendMutex);
    }
}

void _rvmHookExceptionRaised(Env* env, Object* throwable, jboolean isCaught) {
    DebugEnv* debugEnv = (DebugEnv*)env;
    // we might be invoking a function due to a debugger
    // request while suspended. Don't report the exception
    // in that case.
    if(debugEnv->ignoreExceptions) {
        return;
    }

    // we need to temporarily clear the exception
    // for the code below to not get upset.
    Object* exception = rvmExceptionClear(env);

    CallStack* callStack = NULL;
    jint callStackLength = 0;
    jint callStackPayloadSize = 0;
    CallStackFrame* frame = NULL;
    jboolean error = !prepareCallStack(env, EVT_EXCEPTION, &callStack, &callStackLength, &callStackPayloadSize, &frame);
    if(error) {
        rvmThrow(env, exception);
        return;
    }

    // special case for VMClassLoader, we don't
    // report exceptions thrown by it.
    if(!strcmp(frame->method->name, "findClassInClasspathForLoader") &&
            !strcmp(frame->method->clazz->name, "java/lang/VMClassLoader")) {
        rvmThrow(env, exception);
        return;
    }

    rvmPushGatewayFrame(env);
    rvmLockMutex(&debugEnv->suspendMutex);

    if (IS_DEBUG_ENABLED) {
        DEBUGF("Suspending thread %p with id %u due to exception %s", env->currentThread,
                env->currentThread->threadId, throwable->clazz->name);
    }

    if (rvmExceptionCheck(env)) {
        Object* ex = rvmExceptionClear(env);
        ERRORF("Failed to get a call stack for thread %p due to exception event. Got an exception of type: %s",
                env->currentThread, ex->clazz->name);
    } else {
        writeStopOrExceptionEvent(env, EVT_EXCEPTION, throwable, isCaught, callStack, callStackLength, callStackPayloadSize);
        suspendLoop(debugEnv);
    }

    rvmUnlockMutex(&debugEnv->suspendMutex);
    rvmPopGatewayFrame(env);

    rvmThrow(env, exception);
}

void _rvmHookInstrumented(DebugEnv* debugEnv, jint lineNumber, jint lineNumberOffset, jbyte* bptable, void* pc) {
    Env* env = (Env*) debugEnv;

    char event = 0;
    if ((event = getSuspendedEvent(debugEnv, lineNumberOffset, bptable, pc)) != 0) {

        rvmLockMutex(&debugEnv->suspendMutex);

        if (IS_DEBUG_ENABLED) {
            Method* method = rvmFindMethodAtAddress(env, pc);
            DEBUGF("Suspending thread %p with id %u due to event %d at %s.%s%s:%d (pc=%p)", env->currentThread,
                    env->currentThread->threadId, event, method->clazz->name,
                    method->name, method->desc, lineNumber, pc);
        }

        CallStack* callStack = NULL;
        jint callStackLength = 0;
        jint callStackPayloadSize = 0;
        CallStackFrame* frame = NULL;
        if(!prepareCallStack(env, event, &callStack, &callStackLength, &callStackPayloadSize, &frame)) {
            rvmUnlockMutex(&debugEnv->suspendMutex);
            return;
        }

        writeStopOrExceptionEvent(env, event, NULL, 0, callStack, callStackLength, callStackPayloadSize);
        suspendLoop(debugEnv);

        rvmUnlockMutex(&debugEnv->suspendMutex);
    }
}
