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
#include <robovm.h>
#include <hythread.h>

static hythread_monitor_t monitorsLock;
static hythread_tls_key_t tlsEnvKey;

static jint attachThread(VM* vm, Env** envPtr, char* name, Object* group, jboolean daemon) {
    Env* env = *envPtr; // env is NULL if rvmAttachCurrentThread() was called. If non NULL rvmInitThreads() was called.
    hythread_t hyThread = NULL;
    // Try to attach. If already attached this will simply set hyThread.
    if (hythread_attach(&hyThread) < 0) goto error;
    if (!env) {
        // If the thread was already attached there's an Env* associated with the thread.
        env = (Env*) hythread_tls_get(hyThread, tlsEnvKey);
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
    hythread_tls_set(hyThread, tlsEnvKey, env);

    Thread* thread = (Thread*) rvmAllocateObject(env, java_lang_Thread);
    if (!thread) goto error;

    thread->threadPtr = PTR_TO_LONG(hyThread);
    thread->daemon = daemon;
    env->currentThread = thread;

    Object* threadName = NULL;
    if (name) {
        threadName = rvmNewStringUTF(env, name, -1);
        if (!threadName) goto error;
    }

    Method* threadConstructor = rvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(JLjava/lang/String;Ljava/lang/ThreadGroup;Z)V");
    if (!threadConstructor) goto error;

    rvmCallNonvirtualVoidInstanceMethod(env, (Object*) thread, threadConstructor, PTR_TO_LONG(hyThread), threadName, group, daemon);
    if (rvmExceptionOccurred(env)) goto error;

    *envPtr = env;
    env->attachCount = 1;
    return JNI_OK;

error:
    if (hyThread) hythread_detach(hyThread);
    if (env) env->currentThread = NULL;
    return JNI_ERR;
}

static jint detachThread(VM* vm, jboolean ignoreAttachCount) {
    hythread_t hyThread = hythread_self();
    if (!hyThread) return JNI_EDETACHED;
    // If the thread is attached there's an Env* associated with the thread.
    Env* env = (Env*) hythread_tls_get(hyThread, tlsEnvKey);
    if (!env) return JNI_EDETACHED;
    env->attachCount--;
    if (!ignoreAttachCount && env->attachCount > 0) {
        return JNI_OK;
    }
    hythread_tls_set(hyThread, tlsEnvKey, NULL);
    hythread_detach(hyThread); // TODO: Will this release all monitors and notify waiting threads?
    // What if this was called from JNI code which was called from Java and that JNI code returns to Java and doesn't kill the thread? 
    // Should we prevent that from happening some how?
    return JNI_OK;
}

static void monitorEnter(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_enter_using_threadId(monitor, (hythread_t) LONG_TO_PTR(env->currentThread->threadPtr));
    if (result != 0) {
        if (result == HYTHREAD_PRIORITY_INTERRUPTED) {
            // TODO: What?
        }
        rvmAbort("Unexpected value returned by hythread_monitor_enter_using_threadId(): %d", result);
    }
}

static void monitorExit(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_exit_using_threadId(monitor, (hythread_t) LONG_TO_PTR(env->currentThread->threadPtr));
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        rvmAbort("Unexpected value returned by hythread_monitor_exit_using_threadId(): %d", result);
    }
}

static void monitorWait(Env* env, hythread_monitor_t monitor, jlong millis, jint nanos) {
    IDATA result = hythread_monitor_wait_interruptable(monitor, millis, nanos);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        if (result == HYTHREAD_INTERRUPTED || result == HYTHREAD_PRIORITY_INTERRUPTED) {
            rvmThrowInterruptedException(env);
            return;
        }
        if (result != HYTHREAD_TIMED_OUT) {
            rvmAbort("Unexpected value returned by hythread_monitor_wait_interruptable(): %d", result);
        }
    }
}

jboolean rvmInitThreads(Env* env) {
    if (hythread_monitor_init_with_name(&monitorsLock, 0, NULL) < 0) return FALSE;
    if (hythread_tls_alloc(&tlsEnvKey) < 0) return FALSE;
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

jint rvmGetEnv(VM* vm, Env** env) {
    *env = NULL;
    hythread_t hyThread = hythread_self();
    if (!hyThread) return JNI_EDETACHED;
    // If the thread is attached there's an Env* associated with the thread.
    *env = (Env*) hythread_tls_get(hyThread, tlsEnvKey);
    if (*env) return JNI_OK;
    return JNI_EDETACHED; // TODO: What should we do here?
}

jint rvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount) {
    return detachThread(vm, ignoreAttachCount);
}

static int startThreadEntryPoint(void* entryArgs) {
    Env* env = (Env*) entryArgs;
    Thread* thread = env->currentThread;

    Method* run = rvmGetInstanceMethod(env, java_lang_Thread, "run", "()V");
    if (!run) goto finish;

    jvalue emptyArgs[0];
    rvmCallVoidInstanceMethodA(env, (Object*) thread, run, emptyArgs);

finish:

    if (rvmExceptionOccurred(env)) {
        Object* throwable = rvmExceptionClear(env);
        Method* printStackTrace = rvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
        if (printStackTrace) {
            jvalue args[1];
            args[0].l = (jobject) throwable;
            rvmCallVoidInstanceMethodA(env, (Object*) thread, printStackTrace, args);
        }
    }

    thread->threadPtr = 0; // Clear threadPtr. The thread is not alive anymore.
    hythread_monitor_t monitor = thread->object.monitor;
    if (monitor) {
        // Notify all other threads waiting on this thread.
        // We can't use rvmMonitor* functions here since threadPtr has been set to 0.
        hythread_monitor_enter(monitor);
        hythread_monitor_notify_all(monitor);
        hythread_monitor_exit(monitor);
    }

    hythread_exit(NULL);

    return 0;
}

jlong rvmStartThread(Env* env, Thread* thread, jint priority) {
    Env* newEnv = rvmCreateEnv(env->vm);
    if (!newEnv) {
        rvmThrowOutOfMemoryError(env);
        return 0;
    }

    newEnv->currentThread = thread;

    hythread_t hyThread;
    IDATA result = hythread_create(&hyThread, 0, priority, 1, startThreadEntryPoint, newEnv);
    if (result < 0) {
        // TODO: What can we do here?
        rvmAbort("Failed to start thread");
    }
    thread->threadPtr = PTR_TO_LONG(hyThread);
    hythread_resume(hyThread);

    return thread->threadPtr;
}

void rvmMonitorEnter(Env* env, Object* obj) {
    if (!obj->monitor) {
        monitorEnter(env, monitorsLock);
        if (!obj->monitor) {
            // TODO: Double checked locking. Is this ok?
            IDATA result = hythread_monitor_init_with_name(&obj->monitor, 0, NULL);
            if (result < 0) {
                monitorExit(env, monitorsLock);
                rvmAbort("Failed to initialize monitor: %d", result);
            }
        }
        monitorExit(env, monitorsLock);
    }
    monitorEnter(env, obj->monitor);
}

void rvmMonitorExit(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    monitorExit(env, obj->monitor);
}

void rvmMonitorNotify(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    IDATA result = hythread_monitor_notify(obj->monitor);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        rvmAbort("Unexpected value returned by hythread_monitor_notify(): %d", result);
    }
}

void rvmMonitorNotifyAll(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    IDATA result = hythread_monitor_notify_all(obj->monitor);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        rvmAbort("Unexpected value returned by hythread_monitor_notify_all(): %d", result);
    }
}

void rvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos) {
    if (millis < 0) {
        rvmThrowIllegalArgumentException(env, "milliseconds value is negative");
        return;
    }
    if (nanos < 0 || nanos > 999999) {
        rvmThrowIllegalArgumentException(env, "nanoseconds value is out of range");
        return;
    }
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    monitorWait(env, obj->monitor, millis, nanos);
}

void rvmThreadSleep(Env* env, jlong millis, jint nanos) {
    if (millis < 0) {
        rvmThrowIllegalArgumentException(env, "milliseconds value is negative");
        return;
    }
    if (nanos < 0 || nanos > 999999) {
        rvmThrowIllegalArgumentException(env, "nanoseconds value is out of range");
        return;
    }
    if (millis == 0 && nanos == 0) nanos = 1;
    hythread_sleep_interruptable(millis, nanos);
}

jboolean rvmThreadHoldsLock(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        return FALSE;
    }
    return hythread_monitor_owner(obj->monitor) == (hythread_t) LONG_TO_PTR(env->currentThread->threadPtr);
}

jboolean rvmThreadClearInterrupted(Env* env) {
    return hythread_clear_interrupted() != 0;
}

jboolean rvmThreadIsInterrupted(Env* env, Thread* thread) {
    return hythread_interrupted((hythread_t) LONG_TO_PTR(thread->threadPtr)) != 0;
}

void rvmThreadInterrupt(Env* env, Thread* thread) {
    hythread_interrupt((hythread_t) LONG_TO_PTR(thread->threadPtr));
}

void rvmThreadYield(Env* env) {
    hythread_yield();
}

