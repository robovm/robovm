#include <nullvm.h>

static hythread_monitor_t monitorsLock;

static jint attachThread(VM* vm, Env** envPtr, char* name, Object* group) {
    Env* env = *envPtr;
    hythread_t hyThread = hythread_self();
    if (!env && hyThread != NULL) return JNI_OK;

    if (!env) {
        env = nvmCreateEnv(vm);
        if (!env) goto error;
    }

    if (hythread_attach(&hyThread) < 0) goto error;

    Thread* thread = nvmAllocateMemory(env, sizeof(Thread));
    if (!thread) goto error;

    thread->hyThread = hyThread;
    env->currentThread = thread;

    Method* threadConstructor = nvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(JLjava/lang/String;Ljava/lang/ThreadGroup;)V");
    if (!threadConstructor) goto error;
    Object* threadName = NULL;
    if (name) {
        threadName = nvmNewStringUTF(env, name, -1);
        if (!threadName) goto error;
    }
    Object* threadObj = nvmNewObject(env, java_lang_Thread, threadConstructor, (jlong) thread, threadName, group);
    if (!threadObj) goto error;

    thread->threadObj = threadObj;

    *envPtr = env;
    return JNI_OK;

error:
    if (hyThread) hythread_detach(hyThread);
    return JNI_ERR;
}

static void monitorEnter(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_enter_using_threadId(monitor, env->currentThread->hyThread);
    if (result != 0) {
        if (result == HYTHREAD_PRIORITY_INTERRUPTED) {
            // TODO: What?
        }
        nvmAbort("Unexpected value returned by hythread_monitor_enter_using_threadId(): %d", result);
    }
}

static void monitorExit(Env* env, hythread_monitor_t monitor) {
    IDATA result = hythread_monitor_exit_using_threadId(monitor, env->currentThread->hyThread);
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
    Object* threadObj = env->currentThread->threadObj;

    InstanceField* threadPtr = nvmGetInstanceField(env, java_lang_Thread, "threadPtr", "J");
    if (!threadPtr) goto finish;

    Method* run = nvmGetInstanceMethod(env, java_lang_Thread, "run", "()V");
    if (!run) goto finish;

    jvalue emptyArgs[0];
    nvmCallVoidInstanceMethodA(env, threadObj, run, emptyArgs);

finish:

    if (nvmExceptionOccurred(env)) {
        Object* throwable = nvmExceptionClear(env);
        Method* printStackTrace = nvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
        if (printStackTrace) {
            jvalue args[1];
            args[0].l = (jobject) throwable;
            nvmCallVoidInstanceMethodA(env, threadObj, printStackTrace, args);
        }
    }

    if (threadPtr) nvmSetLongInstanceFieldValue(env, threadObj, threadPtr, 0);

    nvmMonitorEnter(env, threadObj);
    nvmMonitorNotifyAll(env, threadObj);
    nvmMonitorExit(env, threadObj);

    hythread_exit(NULL);

    return 0;
}

jlong nvmStartThread(Env* env, Object* threadObj, jint priority) {
    Env* newEnv = nvmCreateEnv(env->vm);
    if (!newEnv) {
        nvmThrowOutOfMemoryError(env);
        return 0;
    }

    Thread* thread = nvmAllocateMemory(env, sizeof(Thread));
    if (!thread) return 0;

    thread->threadObj = threadObj;
    newEnv->currentThread = thread;

    IDATA result = hythread_create(&thread->hyThread, 0, priority, 0, startThreadEntryPoint, newEnv);
    if (result < 0) {
        // TODO: What can we do here?
        nvmAbort("Failed to start thread");
    }

    return (jlong) thread->hyThread;
}

void nvmMonitorEnter(Env* env, Object* obj) {
    // TODO: Protect initialization with global (spin?)lock
    if (!obj->monitor) {
        IDATA result = hythread_monitor_init_with_name(&obj->monitor, 0, NULL);
        if (result < 0) {
            nvmAbort("Failed to initialize monitor: %d", result);
        }
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
    return hythread_monitor_owner(obj->monitor) == env->currentThread->hyThread;
}

