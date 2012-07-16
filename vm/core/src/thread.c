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
#define _GNU_SOURCE
#include <pthread.h>
#include <robovm.h>
#include "utlist.h"

/*
 * This code has been heavily inspired by Android's dalvik/vm/Thread.cpp code.
 */

static Mutex threadsLock;
static pthread_cond_t threadStartCond;
static Thread* threads = NULL;
static pthread_key_t tlsEnvKey;
static jint nextThreadId = 1;
static Method* getUncaughtExceptionHandlerMethod;
static Method* uncaughtExceptionMethod;
static Method* removeThreadMethod;


inline void rvmLockThreadsList() {
    rvmLockMutex(&threadsLock);
}

inline void rvmUnlockThreadsList() {
    rvmUnlockMutex(&threadsLock);
}

/**
 * Determines the stack address of the current thread
 */
static void* getStackAddress(void) {
    void* result = NULL;
    size_t stackSize = 0;
    pthread_t self = pthread_self();
#if defined(DARWIN)
    result = pthread_get_stackaddr_np(self);
    stackSize = pthread_get_stacksize_np(self);
    // pthread_get_stackaddr_np returns the beginning (highest address) of the stack
    // while we want the address of the memory area allocated for the stack (lowest address).
    result -= stackSize;
#else
    size_t guardSize = 0;
    pthread_attr_t attr;
    pthread_getattr_np(self, &attr);
    pthread_attr_getstack(&attr, &result, &stackSize);
    pthread_attr_getguardsize(&attr, &guardSize);
    // On Linux pthread_attr_getstack() returns the address of the memory area allocated for the stack
    // including the guard page (except for the main thread which returns the correct stack address and 
    // pthread_attr_getguardsize() returns 0 even if there is a guard page).
    result += guardSize;
#endif
    return result;
}

static Thread* allocThread(Env* env) {
    Thread* thread = (Thread*) rvmAllocateMemory(env, sizeof(Thread));
    if (!thread) return NULL;
    thread->status = THREAD_INITIALIZING;
    return thread;
}

static jboolean initThread(Env* env, Thread* thread, JavaThread* threadObj) {
    // NOTE: threadsLock must be held
    int err = 0;
    pthread_cond_init(&thread->waitCond, NULL);
    if ((err = rvmInitMutex(&thread->waitMutex)) != 0) {
        rvmThrowInternalErrorErrno(env, err);
        return FALSE;
    }
    thread->threadId = nextThreadId++;
    thread->threadObj = threadObj;
    threadObj->threadPtr = PTR_TO_LONG(thread);
    env->currentThread = thread;
    env->attachCount = 1;
    return TRUE;
}

static jint attachThread(VM* vm, Env** envPtr, char* name, Object* group, jboolean daemon) {
    Env* env = *envPtr; // env is NULL if rvmAttachCurrentThread() was called. If non NULL rvmInitThreads() was called.
    if (!env) {
        // If the thread was already attached there's an Env* associated with the thread.
        env = (Env*) pthread_getspecific(tlsEnvKey);
        if (env) {
            env->attachCount++;
            *envPtr = env;
            return JNI_OK;
        }
    }
    
    if (!env) {
        env = rvmCreateEnv(vm);
        if (!env) goto error;
    }

    // Associate the current Env* with the thread
    int err = 0;
    if ((err = pthread_setspecific(tlsEnvKey, env)) != 0) {
        rvmThrowInternalErrorErrno(env, err);
        goto error;
    }

    Thread* thread = allocThread(env);
    if (!thread) goto error;
    JavaThread* threadObj = (JavaThread*) rvmAllocateObject(env, java_lang_Thread);
    if (!threadObj) goto error;

    rvmLockThreadsList();
    if (!initThread(env, thread, threadObj)) {
        rvmUnlockThreadsList();
        goto error;
    }
    if (!rvmSetupSignals(env)) {
        rvmUnlockThreadsList();
        goto error;
    }
    thread->stackAddr = getStackAddress();
    thread->pThread = pthread_self();
    DL_PREPEND(threads, thread);
    rvmUnlockThreadsList();

    rvmChangeThreadStatus(env, thread, THREAD_RUNNING);

    Object* threadName = NULL;
    if (name) {
        threadName = rvmNewStringUTF(env, name, -1);
        if (!threadName) goto error_remove;
    }

    Method* threadConstructor = rvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(JLjava/lang/String;Ljava/lang/ThreadGroup;Z)V");
    if (!threadConstructor) goto error_remove;

    rvmCallNonvirtualVoidInstanceMethod(env, (Object*) threadObj, threadConstructor, PTR_TO_LONG(thread), threadName, group, daemon);
    if (rvmExceptionOccurred(env)) goto error_remove;

    *envPtr = env;

    return JNI_OK;

error_remove:
    rvmLockThreadsList();
    DL_DELETE(threads, thread);
    rvmTearDownSignals(env);
    rvmUnlockThreadsList();
error:
    if (env) env->currentThread = NULL;
    pthread_setspecific(tlsEnvKey, NULL);
    return JNI_ERR;
}

static void threadExitUncaughtException(Env* env, Thread* thread) {
    Object* throwable = rvmExceptionClear(env);
    Object* handler = rvmCallObjectInstanceMethod(env, (Object*) thread->threadObj, getUncaughtExceptionHandlerMethod);
    // Ignore exception thrown by getUncaughtException()
    rvmExceptionClear(env);
    if (!handler) {
        handler = thread->threadObj->group;
    }
    if (handler) {
        rvmCallVoidInstanceMethod(env, handler, uncaughtExceptionMethod, (Object*) thread->threadObj, throwable);
    } else {
        rvmPrintStackTrace(env, throwable);
    }
    // Ignore exception thrown by uncaughtException() or rvmPrintStackTrace()
    rvmExceptionClear(env);
}


static jint detachThread(Env* env, jboolean ignoreAttachCount) {
    env->attachCount--;
    if (!ignoreAttachCount && env->attachCount > 0) {
        return JNI_OK;
    }

    if (env->gatewayFrames) {
        rvmAbort("Cannot detach thread when there are non native frames on the call stack");
    }

    // TODO: Release all monitors still held by this thread (should only be monitors acquired from JNI code)

    Thread* thread = env->currentThread;

    if (rvmExceptionOccurred(env)) {
        threadExitUncaughtException(env, thread);
    }

    if (thread->threadObj->group) {
        rvmCallVoidInstanceMethod(env, thread->threadObj->group, removeThreadMethod, thread->threadObj);
        rvmExceptionClear(env);
    }

    // Set threadPtr to null
    rvmAtomicGetAndSetLong(&thread->threadObj->threadPtr, 0);

    // Notify anyone waiting on this thread (using Thread.join())
    rvmLockObject(env, thread->threadObj->lock);
    rvmObjectNotifyAll(env, thread->threadObj->lock);
    rvmUnlockObject(env, thread->threadObj->lock);

    rvmLockThreadsList();
    thread->status = THREAD_ZOMBIE;
    DL_DELETE(threads, thread);
    rvmTearDownSignals(env);
    env->currentThread = NULL;
    pthread_setspecific(tlsEnvKey, NULL);
    rvmUnlockThreadsList();

    return JNI_OK;
}

jboolean rvmInitThreads(Env* env) {
    if (rvmInitMutex(&threadsLock) != 0) return FALSE;
    if (pthread_key_create(&tlsEnvKey, NULL) != 0) return FALSE;
    if (pthread_cond_init(&threadStartCond, NULL) != 0) return FALSE;
    getUncaughtExceptionHandlerMethod = rvmGetInstanceMethod(env, java_lang_Thread, "getUncaughtExceptionHandler", "()Ljava/lang/Thread$UncaughtExceptionHandler;");
    if (!getUncaughtExceptionHandlerMethod) return FALSE;
    Class* uncaughtExceptionHandler = rvmFindClassInClasspathForLoader(env, "java/lang/Thread$UncaughtExceptionHandler", NULL);
    if (!uncaughtExceptionHandler) return FALSE;
    uncaughtExceptionMethod = rvmGetInstanceMethod(env, uncaughtExceptionHandler, "uncaughtException", "(Ljava/lang/Thread;Ljava/lang/Throwable;)V");
    if (!uncaughtExceptionMethod) return FALSE;
    removeThreadMethod = rvmGetInstanceMethod(env, java_lang_ThreadGroup, "removeThread", "(Ljava/lang/Thread;)V");
    if (!removeThreadMethod) return FALSE;
    return attachThread(env->vm, &env, "main", NULL, FALSE) == JNI_OK;
}

jint rvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group) {
    *env = NULL;
    return attachThread(vm, env, name, group, FALSE);
}

jint rvmAttachCurrentThreadAsDaemon(VM* vm, Env** env, char* name, Object* group) {
    *env = NULL;
    return attachThread(vm, env, name, group, TRUE);
}

Env* rvmGetEnv(void) {
    // If the thread is attached there's an Env* associated with the thread.
    return (Env*) pthread_getspecific(tlsEnvKey);
}

jint rvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount) {
    Env* env = rvmGetEnv();
    if (!env) return JNI_EDETACHED;
    return detachThread(env, ignoreAttachCount);
}

typedef struct ThreadEntryPointArgs {
    Env* env;
    Thread* thread;
    JavaThread* threadObj;
} ThreadEntryPointArgs;

static void* startThreadEntryPoint(void* _args) {
    ThreadEntryPointArgs* args = (ThreadEntryPointArgs*) _args;
    Env* env = args->env;
    Thread* thread = args->thread;
    JavaThread* threadObj = args->threadObj;

    rvmLockThreadsList();
    jboolean failure = TRUE;
    if (initThread(env, thread, threadObj)) {
        if (rvmSetupSignals(env)) {
            failure = FALSE;
            thread->stackAddr = getStackAddress();
        }
    }
    thread->status = THREAD_STARTING;
    pthread_cond_broadcast(&threadStartCond);
    while (thread->status != THREAD_VMWAIT) {
        pthread_cond_wait(&threadStartCond, &threadsLock);
    }
    rvmUnlockThreadsList();

    if (!failure) {
        rvmChangeThreadStatus(env, thread, THREAD_RUNNING);

        rvmChangeThreadPriority(env, thread, thread->threadObj->priority);

        Method* run = rvmGetInstanceMethod(env, java_lang_Thread, "run", "()V");
        if (run) {
            jvalue emptyArgs[0];
            rvmCallVoidInstanceMethodA(env, (Object*) threadObj, run, emptyArgs);
        }
    }

    detachThread(env, TRUE);

    return NULL;
}

jlong rvmStartThread(Env* env, JavaThread* threadObj) {
    Env* newEnv = rvmCreateEnv(env->vm);
    if (!newEnv) {
        rvmThrowOutOfMemoryError(env); // rvmCreateEnv() doesn't throw OutOfMemoryError if allocation fails
        return 0;
    }

    rvmLockThreadsList();
    if (threadObj->threadPtr != 0) {
        rvmThrowIllegalStateException(env, "thread has already been started");
        rvmUnlockThreadsList();
        return 0;
    }
    Thread* thread = allocThread(env);
    if (!thread) {
        rvmUnlockThreadsList();
        return 0;
    }

    size_t stackSize = (size_t) threadObj->stackSize;
    if (stackSize == 0) {
        stackSize = THREAD_DEFAULT_STACK_SIZE;
    } else if (stackSize < THREAD_MIN_STACK_SIZE) {
        stackSize = THREAD_MIN_STACK_SIZE;
    }
    stackSize += THREAD_SIGNAL_STACK_SIZE;
    stackSize = (stackSize + THREAD_STACK_SIZE_MULTIPLE - 1) & ~(THREAD_STACK_SIZE_MULTIPLE - 1);

    pthread_attr_t threadAttr;
    pthread_attr_init(&threadAttr);
    pthread_attr_setdetachstate(&threadAttr, PTHREAD_CREATE_DETACHED);
    pthread_attr_setstacksize(&threadAttr, stackSize);
    pthread_attr_setguardsize(&threadAttr, THREAD_STACK_GUARD_SIZE);

    ThreadEntryPointArgs args = {0};
    args.env = newEnv;
    args.thread = thread;
    args.threadObj = threadObj;
    int err = 0;
    if ((err = pthread_create(&thread->pThread, &threadAttr, startThreadEntryPoint, &args)) != 0) {
        rvmUnlockThreadsList();
        rvmThrowInternalErrorErrno(env, err);
        return 0;
    }

    while (thread->status != THREAD_STARTING) {
        pthread_cond_wait(&threadStartCond, &threadsLock);
    }

    DL_PREPEND(threads, thread);
    
    thread->status = THREAD_VMWAIT;
    pthread_cond_broadcast(&threadStartCond);
    rvmUnlockThreadsList();

    return PTR_TO_LONG(thread);
}

jint rvmChangeThreadStatus(Env* env, Thread* thread, jint newStatus) {
    jint oldStatus = thread->status;
    if (oldStatus == newStatus) return newStatus;
    rvmAtomicGetAndSetInt(&thread->status, newStatus);
    return oldStatus;
}

void rvmChangeThreadPriority(Env* env, Thread* thread, jint priority) {
    // TODO: Implement rvmChangeThreadPriority()
}
