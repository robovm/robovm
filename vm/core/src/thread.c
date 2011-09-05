#include <nullvm.h>
#include <hythread.h>

static hythread_monitor_t monitorsLock;

static jint attachThread(VM* vm, Env** envPtr, char* name, Object* group) {
    Env* env = *envPtr; // env is NULL if nvmAttachCurrentThread() was called. If non NULL nvmInitThreads() was called.
    hythread_t hyThread = hythread_self();
    if (!env && hyThread != NULL) return JNI_OK;

    if (!env) {
        env = nvmCreateEnv(vm);
        if (!env) goto error;
    }

    if (hythread_attach(&hyThread) < 0) goto error;

    Thread* thread = (Thread*) nvmAllocateObject(env, java_lang_Thread);
    if (!thread) goto error;

    thread->threadPtr = (jlong) hyThread;
    env->currentThread = thread;

    Object* threadName = NULL;
    if (name) {
        threadName = nvmNewStringUTF(env, name, -1);
        if (!threadName) goto error;
    }

    Method* threadConstructor = nvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(JLjava/lang/String;Ljava/lang/ThreadGroup;)V");
    if (!threadConstructor) goto error;

    nvmCallNonvirtualVoidInstanceMethod(env, (Object*) thread, threadConstructor, (jlong) hyThread, threadName, group);
    if (nvmExceptionOccurred(env)) goto error;

    *envPtr = env;
    return JNI_OK;

error:
    if (hyThread) hythread_detach(hyThread);
    if (env) env->currentThread = NULL;
    return JNI_ERR;
}

static void monitorEnter(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_enter_using_threadId(monitor, (hythread_t) env->currentThread->threadPtr);
    if (result != 0) {
        if (result == HYTHREAD_PRIORITY_INTERRUPTED) {
            // TODO: What?
        }
        nvmAbort("Unexpected value returned by hythread_monitor_enter_using_threadId(): %d", result);
    }
}

static void monitorExit(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_exit_using_threadId(monitor, (hythread_t) env->currentThread->threadPtr);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            nvmThrowIllegalMonitorStateException(env);
            return;
        }
        nvmAbort("Unexpected value returned by hythread_monitor_exit_using_threadId(): %d", result);
    }
}

static void monitorWait(Env* env, hythread_monitor_t monitor, jlong millis, jint nanos) {
    IDATA result = hythread_monitor_wait_interruptable(monitor, millis, nanos);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            nvmThrowIllegalMonitorStateException(env);
            return;
        }
        if (result == HYTHREAD_INTERRUPTED || result == HYTHREAD_PRIORITY_INTERRUPTED) {
            nvmThrowInterruptedException(env);
            return;
        }
        if (result != HYTHREAD_TIMED_OUT) {
            nvmAbort("Unexpected value returned by hythread_monitor_wait_interruptable(): %d", result);
        }
    }
}

jboolean nvmInitThreads(Env* env) {
    if (hythread_monitor_init_with_name(&monitorsLock, 0, NULL) < 0) {
        return FALSE;
    }
    return attachThread(env->vm, &env, "main", NULL) == JNI_OK;
}

jint nvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group) {
    *env = NULL;
    return attachThread(vm, env, name, group);
}

static int startThreadEntryPoint(void* entryArgs) {
    Env* env = (Env*) entryArgs;
    Thread* thread = env->currentThread;

    Method* run = nvmGetInstanceMethod(env, java_lang_Thread, "run", "()V");
    if (!run) goto finish;

    jvalue emptyArgs[0];
    nvmCallVoidInstanceMethodA(env, (Object*) thread, run, emptyArgs);

finish:

    if (nvmExceptionOccurred(env)) {
        Object* throwable = nvmExceptionClear(env);
        Method* printStackTrace = nvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
        if (printStackTrace) {
            jvalue args[1];
            args[0].l = (jobject) throwable;
            nvmCallVoidInstanceMethodA(env, (Object*) thread, printStackTrace, args);
        }
    }

    thread->threadPtr = 0; // Clear threadPtr. The thread is not alive anymore.
    hythread_monitor_t monitor = thread->object.monitor;
    if (monitor) {
        // Notify all other threads waiting on this thread.
        // We can't use nvmMonitor* functions here since threadPtr has been set to 0.
        hythread_monitor_enter(monitor);
        hythread_monitor_notify_all(monitor);
        hythread_monitor_exit(monitor);
    }

    hythread_exit(NULL);

    return 0;
}

jlong nvmStartThread(Env* env, Thread* thread, jint priority) {
    Env* newEnv = nvmCreateEnv(env->vm);
    if (!newEnv) {
        nvmThrowOutOfMemoryError(env);
        return 0;
    }

    newEnv->currentThread = thread;

    hythread_t hyThread;
    IDATA result = hythread_create(&hyThread, 0, priority, 1, startThreadEntryPoint, newEnv);
    if (result < 0) {
        // TODO: What can we do here?
        nvmAbort("Failed to start thread");
    }
    thread->threadPtr = (jlong) hyThread;
    hythread_resume(hyThread);

    return thread->threadPtr;
}

void nvmMonitorEnter(Env* env, Object* obj) {
    if (!obj->monitor) {
        monitorEnter(env, monitorsLock);
        if (!obj->monitor) {
            // TODO: Double checked locking. Is this ok?
            IDATA result = hythread_monitor_init_with_name(&obj->monitor, 0, NULL);
            if (result < 0) {
                monitorExit(env, monitorsLock);
                nvmAbort("Failed to initialize monitor: %d", result);
            }
        }
        monitorExit(env, monitorsLock);
    }
    monitorEnter(env, obj->monitor);
}

void nvmMonitorExit(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        nvmThrowIllegalMonitorStateException(env);
        return;
    }
    monitorExit(env, obj->monitor);
}

void nvmMonitorNotify(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        nvmThrowIllegalMonitorStateException(env);
        return;
    }
    IDATA result = hythread_monitor_notify(obj->monitor);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            nvmThrowIllegalMonitorStateException(env);
            return;
        }
        nvmAbort("Unexpected value returned by hythread_monitor_notify(): %d", result);
    }
}

void nvmMonitorNotifyAll(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        nvmThrowIllegalMonitorStateException(env);
        return;
    }
    IDATA result = hythread_monitor_notify_all(obj->monitor);
    if (result != 0) {
        if (result == HYTHREAD_ILLEGAL_MONITOR_STATE) {
            nvmThrowIllegalMonitorStateException(env);
            return;
        }
        nvmAbort("Unexpected value returned by hythread_monitor_notify_all(): %d", result);
    }
}

void nvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos) {
    if (millis < 0) {
        nvmThrowIllegalArgumentException(env, "milliseconds value is negative");
        return;
    }
    if (nanos < 0 || nanos > 999999) {
        nvmThrowIllegalArgumentException(env, "nanoseconds value is out of range");
        return;
    }
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        nvmThrowIllegalMonitorStateException(env);
        return;
    }
    monitorWait(env, obj->monitor, millis, nanos);
}

void nvmThreadSleep(Env* env, jlong millis, jint nanos) {
    if (millis < 0) {
        nvmThrowIllegalArgumentException(env, "milliseconds value is negative");
        return;
    }
    if (nanos < 0 || nanos > 999999) {
        nvmThrowIllegalArgumentException(env, "nanoseconds value is out of range");
        return;
    }
    if (millis == 0 && nanos == 0) nanos = 1;
    hythread_sleep_interruptable(millis, nanos);
}

jboolean nvmThreadHoldsLock(Env* env, Object* obj) {
    // TODO: Protect access to monitor with global (spin?)lock
    if (!obj->monitor) {
        return FALSE;
    }
    return hythread_monitor_owner(obj->monitor) == (hythread_t) env->currentThread->threadPtr;
}

jboolean nvmThreadClearInterrupted(Env* env) {
    return hythread_clear_interrupted() != 0;
}

jboolean nvmThreadIsInterrupted(Env* env, Thread* thread) {
    return hythread_interrupted((hythread_t) thread->threadPtr) != 0;
}

void nvmThreadInterrupt(Env* env, Thread* thread) {
    hythread_interrupt((hythread_t) thread->threadPtr);
}

void nvmThreadYield(Env* env) {
    hythread_yield();
}

