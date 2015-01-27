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
#include <time.h>
#include <robovm/types.h>

#define LOG_TAG "hooks"

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

// events
#define EVT_THREAD_ATTACHED 100
#define EVT_THREAD_STARTED 101
#define EVT_THREAD_DETTACHED 102
#define EVT_THREAD_SUSPENDED 103
#define EVT_THREAD_RESUMED 104
#define EVT_BREAKPOINT 105
#define EVT_THREAD_STEPPED 106

typedef struct {
    int errorCode;
    char* message;
} ChannelError;

// set by debugger when connecting to the inferior
jboolean attachFlag = FALSE;

// timeout counter
static jint waitForAttachTime = 0;

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
    writeChannel(socket, &val, 1, error);
}

static void writeChannelInt(int socket, jint val, ChannelError* error) {
    val = swap32(val);
    writeChannel(socket, &val, 4, error);
}

static void writeChannelLong(int socket, jlong val, ChannelError* error) {
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
            rvmHookDebuggerAttached(options);
            break;
        }
    }
}

void _rvmHookBeforeMainThreadAttached(Env* env) {
    DEBUG("Before main thread attached");
}

void _rvmHookBeforeAppEntryPoint(Env* env, Class* clazz, Method* method, ObjectArray* args) {
    DEBUGF("Before app entry point %s.%s%s", clazz->name, method->name, method->desc);
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

static void handleThreadSuspend(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    DebugEnv* debugEnv = (DebugEnv*) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->suspended = TRUE;
    debugEnv->stepping = FALSE;
    debugEnv->pclow = 0;
    debugEnv->pchigh = 0;
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
    debugEnv->stepping = TRUE;
    debugEnv->pclow = (void*)pclow;
    debugEnv->pchigh = (void*)pchigh;
    debugEnv->pclow2 = (void*)pclow2;
    debugEnv->pchigh2 = (void*)pchigh2;
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
    if (!debugEnv->reqId) {
        if (checkBit(bptable, lineNumberOffset)) {
            return EVT_BREAKPOINT;
        }
    }
    return 0;
}

void _rvmHookInstrumented(DebugEnv* debugEnv, jint lineNumber, jint lineNumberOffset, jbyte* bptable, void* pc) {
    Env* env = (Env*) debugEnv;

    rvmLockMutex(&debugEnv->suspendMutex);

    char event = 0;
    if ((event = getSuspendedEvent(debugEnv, lineNumberOffset, bptable, pc)) != 0) {

        if (IS_DEBUG_ENABLED) {
            Method* method = rvmFindMethodAtAddress(env, pc);
            DEBUGF("Suspending thread %p with id %u due to event %d at %s.%s%s:%d (pc=%p)", env->currentThread, 
                env->currentThread->threadId, event, method->clazz->name, 
                method->name, method->desc, lineNumber, pc);
        }

        CallStack* callStack = rvmCaptureCallStack(env);
        if (rvmExceptionCheck(env)) {
            Object* ex = rvmExceptionClear(env);
            ERRORF("Failed to get a call stack for thread %p due to event %d (pc=%p). Got an exception of type: %s", 
                env->currentThread, event, pc, ex->clazz->name);
        } else {

            jint index = 0;
            jint length = 0;
            while (rvmGetNextCallStackMethod(env, callStack, &index)) {
                length++;
            }

            rvmLockMutex(&writeMutex);
            ChannelError error = { 0 };
            writeChannelByte(clientSocket, event, &error);
            writeChannelLong(clientSocket, 0, &error);
            writeChannelLong(clientSocket, sizeof(jlong) * 2 + sizeof(jint) + length * (sizeof(jlong) * 2 + sizeof(jint)), &error);
            writeChannelLong(clientSocket, (jlong)env->currentThread->threadObj, &error);
            writeChannelLong(clientSocket, (jlong)env->currentThread, &error);
            writeChannelInt(clientSocket, length, &error);
            index = 0;
            CallStackFrame* frame = NULL;
            while ((frame = rvmGetNextCallStackMethod(env, callStack, &index)) != NULL) {
                // TODO: Handle proxy methods
                writeChannelLong(clientSocket, (jlong)(frame->method->impl), &error);
                writeChannelInt(clientSocket, frame->lineNumber, &error);
                writeChannelLong(clientSocket, (jlong)(frame->fp), &error);
            }
            rvmUnlockMutex(&writeMutex);

            debugEnv->reqId = 0;
            debugEnv->stepping = FALSE;
            debugEnv->pclow = debugEnv->pclow2 = 0;
            debugEnv->pchigh = debugEnv->pchigh2 = 0;
            debugEnv->suspended = TRUE;
            while (debugEnv->suspended) {
                pthread_cond_wait(&debugEnv->suspendCond, &debugEnv->suspendMutex);

                // If reqId is set, we have  method invocation request (see handleThreadInvoke)
                // we temporarily reset the suspend flag, invoke the
                // method, then go back into waiting for being woken up again
                if(debugEnv->reqId != 0) {
                    debugEnv->suspended = FALSE;
                    invokeMethod(debugEnv);
                    debugEnv->suspended = TRUE;
                }
            }

            DEBUGF("Thread %p, id %u resumed", env->currentThread, env->currentThread->threadId);
            rvmLockMutex(&writeMutex);
            writeChannelByte(clientSocket, EVT_THREAD_RESUMED, &error);
            writeChannelLong(clientSocket, 0, &error);
            writeChannelLong(clientSocket, 16, &error);
            writeChannelLong(clientSocket, (jlong)env->currentThread->threadObj, &error);
            writeChannelLong(clientSocket, (jlong)env->currentThread, &error);
            rvmUnlockMutex(&writeMutex);

        }
    }

    rvmUnlockMutex(&debugEnv->suspendMutex);
}
