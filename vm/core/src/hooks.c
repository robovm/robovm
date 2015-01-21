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

#define LOG_TAG "hooks"

// memory operations
#define CMD_READ_MEMORY 1
#define CMD_READ_CSTRING 2
#define CMD_WRITE_MEMORY 3
#define CMD_WRITE_AND_BITS 4
#define CMD_WRITE_OR_BITS 5
#define CMD_ALLOCATE 6
#define CMD_FREE 7

// thread/vm operations
#define CMD_THREAD_SUSPEND 50
#define CMD_THREAD_RESUME 51
#define CMD_VM_SUSPEND 52
#define CMD_VM_RESUME 53

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
    nanosleep(&time , 0);
}

static jint swap32(jint val) {
    val = ((val << 8) & 0xff00ff00 ) | ((val >> 8) & 0xff00ff );
    return (val << 16) | (val >> 16);
}

static jlong swap64(jlong val) {
    val = ((val << 8) & 0xff00ff00ff00ff00ULL ) | ((val >> 8) & 0x00ff00ff00ff00ffULL );
    val = ((val << 16) & 0xffff0000ffff0000ULL ) | ((val >> 16) & 0x0000ffff0000ffffULL );
    return (val << 32) | (val >> 32);
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

    if(options->waitForResume) {
        rvmHookWaitForResume(options);
    }

    return TRUE;
}

static void handleReadMemory(jlong reqId, ChannelError* error) {
    void* addr = (void*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    DEBUGF("Reading memory: %p, %u bytes", addr, numBytes);
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

    DEBUGF("Reading string: %p, %s", addr, (char*)addr);
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

    DEBUGF("Writing to memory %p, num bytes: %u", addr, numBytes);
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
    DEBUGF("And-ing bits at %p (=%x) with %x = %x", addr, orig, mask, value);
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
    DEBUGF("Or-ing bits at %p (=%x) with %x = %x", addr, orig, mask, value);
    writeChannelByte(clientSocket, CMD_WRITE_OR_BITS, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleAllocate(jlong reqId, ChannelError* error) {
    jint numBytes = readChannelInt(clientSocket, error);
    if(checkError(error)) return;

    rvmLockMutex(&writeMutex);
    void* addr = malloc(numBytes);
    DEBUGF("Allocated %u bytes, at %p", numBytes, addr);
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
    DEBUGF("Freed memory at %p", addr);
    writeChannelByte(clientSocket, CMD_FREE, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleVmSuspend(jlong reqId, ChannelError* error) {
    // FIXME
    DEBUG("Suspending VM");
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_VM_SUSPEND, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleVmResume(jlong reqId, ChannelError* error) {
    // FIXME
    DEBUG("Resuming VM");
    rvmLockMutex(&writeMutex);
    resumeFlag = TRUE; // will end the loop in rvmHookWaitForResume
    writeChannelByte(clientSocket, CMD_VM_RESUME, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);
}

static void handleThreadSuspend(jlong reqId, ChannelError* error) {
    Thread* thread = (Thread*)readChannelLong(clientSocket, error);
    if(checkError(error)) return;

    // FIXME
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

    DEBUGF("Resuming thread %p, id %u", thread, thread->threadId);
    rvmLockMutex(&writeMutex);
    writeChannelByte(clientSocket, CMD_THREAD_RESUME, error);
    writeChannelLong(clientSocket, reqId, error);
    writeChannelLong(clientSocket, 0, error);
    rvmUnlockMutex(&writeMutex);

    DebugEnv* debugEnv = (DebugEnv*) thread->env;
    rvmLockMutex(&debugEnv->suspendMutex);
    debugEnv->suspended = FALSE;
    pthread_cond_signal(&debugEnv->suspendCond);
    rvmUnlockMutex(&debugEnv->suspendMutex);
}

static void handleRequest(char req, jlong reqId, jlong payloadSize, ChannelError* error) {
    DEBUGF("req: %d, reqId: %llu, payloadSize: %llu", req, reqId, payloadSize);
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
        case CMD_VM_SUSPEND:
            handleVmSuspend(reqId, error);
            break;
        case CMD_VM_RESUME:
            handleVmResume(reqId, error);
            break;
        case CMD_THREAD_SUSPEND:
            handleThreadSuspend(reqId, error);
            break;
        case CMD_THREAD_RESUME:
            handleThreadResume(reqId, error);
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
    if (debugEnv->stepping && pc >= debugEnv->pclow && pc < debugEnv->pchigh) {
        return EVT_THREAD_STEPPED;
    }
    if (checkBit(bptable, lineNumberOffset)) {
        return EVT_BREAKPOINT;
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

            debugEnv->suspended = TRUE;
            while (debugEnv->suspended) {
                pthread_cond_wait(&debugEnv->suspendCond, &debugEnv->suspendMutex);
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
