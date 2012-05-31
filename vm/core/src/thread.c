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
#include <jvmti_types.h>
#include <open/hythread_ext.h>
#include "private.h"

static Mutex monitorsLock;
static hythread_tls_key_t tlsEnvKey;
static hythread_library_t lib;

static inline void setThreadPtr(Thread* thread, hythread_t hyThread) {
    while (!rvmCompareAndSwapLong(&thread->threadPtr, thread->threadPtr, PTR_TO_LONG(hyThread)))
        ;
}

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

    setThreadPtr(thread, hyThread);
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

    if (!daemon) {
        IDATA status = hythread_increase_nondaemon_threads_count(hyThread);
        assert(status == TM_ERROR_NONE);
    }

    return JNI_OK;

error:
    if (hyThread) hythread_detach(hyThread);
    if (env) env->currentThread = NULL;
    return JNI_ERR;
}

static jint detachThread(Env* env, jboolean ignoreAttachCount) {
    env->attachCount--;
    if (!ignoreAttachCount && env->attachCount > 0) {
        return JNI_OK;
    }

    Thread* thread = env->currentThread;
    hythread_t hyThread = (hythread_t) LONG_TO_PTR(thread->threadPtr);

    if (!thread->daemon) {
        IDATA status = hythread_decrease_nondaemon_threads_count(hyThread, 1);
        assert(status == TM_ERROR_NONE);
    }

    setThreadPtr(thread, NULL);
    rvmMonitorEnter(env, thread->lock);
    rvmMonitorNotifyAll(env, thread->lock);
    rvmMonitorExit(env, thread->lock);

    hythread_tls_set(hyThread, tlsEnvKey, NULL);
    hythread_set_state(hyThread, TM_THREAD_STATE_TERMINATED);
    hythread_detach_ex(hyThread);

    // What if this was called from JNI code which was called from Java and that JNI code returns to Java and doesn't kill the thread? 
    // Should we prevent that from happening some how?
    return JNI_OK;
}

static IDATA monitorEnter(Env* env, Object* obj) {
    // Start (C) DRLVM
    IDATA state;
    hythread_t native_thread;

    assert(obj);
    hythread_suspend_disable();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    IDATA status = hythread_thin_monitor_try_enter(lockword);
    if (status != TM_ERROR_EBUSY) {
        goto entered;
    }

    native_thread = hythread_self();
    hythread_thread_lock(native_thread);
    state = hythread_get_state(native_thread);
    state &= ~TM_THREAD_STATE_RUNNABLE;
    state |= TM_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER;
    status = hythread_set_state(native_thread, state);
    assert(status == TM_ERROR_NONE);
    hythread_thread_unlock(native_thread);

    // busy wait and inflate
    // reload pointer after safepoints
    lockword = &obj->monitor;
    while ((status =
            hythread_thin_monitor_try_enter(lockword)) == TM_ERROR_EBUSY)
    {
        hythread_safe_point();
        hythread_exception_safe_point();
        lockword = &obj->monitor;

        if (hythread_is_fat_lock(*lockword)) {
            status = hythread_thin_monitor_enter(lockword);
            if (status != TM_ERROR_NONE) {
                hythread_suspend_enable();
                assert(0);
                return status;
            }
            goto contended_entered;
        }
        hythread_yield();
    }
    assert(status == TM_ERROR_NONE);
    if (!hythread_is_fat_lock(*lockword)) {
        hythread_inflate_lock(lockword);
    }

contended_entered:
    hythread_thread_lock(native_thread);
    state = hythread_get_state(native_thread);
    state &= ~TM_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER;
    state |= TM_THREAD_STATE_RUNNABLE;
    status = hythread_set_state(native_thread, state);
    assert(status == TM_ERROR_NONE);
    hythread_thread_unlock(native_thread);

entered:
    hythread_suspend_enable();
    return TM_ERROR_NONE;
    // End (C) DRLVM
}

static IDATA monitorWait(Env* env, Object* obj, jlong millis, jint nanos) {
    // Start (C) DRLVM
    assert(obj);

    hythread_suspend_disable();
    hythread_t native_thread = hythread_self();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    if (!hythread_is_fat_lock(*lockword)) {
        if (!hythread_owns_thin_lock(native_thread, *lockword)) {
            hythread_suspend_enable();
            return TM_ERROR_ILLEGAL_STATE;
        }
        hythread_inflate_lock(lockword);
    }

    hythread_thread_lock(native_thread);
    IDATA state = hythread_get_state(native_thread);
    state &= ~TM_THREAD_STATE_RUNNABLE;
    state |= TM_THREAD_STATE_WAITING | TM_THREAD_STATE_IN_MONITOR_WAIT;
    if ((millis > 0) || (nanos > 0)) {
        state |= TM_THREAD_STATE_WAITING_WITH_TIMEOUT;
    }
    else {
        state |= TM_THREAD_STATE_WAITING_INDEFINITELY;
    }
    IDATA status = hythread_set_state(native_thread, state);
    assert(status == TM_ERROR_NONE);
    hythread_thread_unlock(native_thread);

    status =
        hythread_thin_monitor_wait_interruptable(lockword, millis, nanos);

    hythread_thread_lock(native_thread);
    state = hythread_get_state(native_thread);
    if ((millis > 0) || (nanos > 0)) {
        state &= ~TM_THREAD_STATE_WAITING_WITH_TIMEOUT;
    }
    else {
        state &= ~TM_THREAD_STATE_WAITING_INDEFINITELY;
    }
    state &= ~(TM_THREAD_STATE_WAITING | TM_THREAD_STATE_IN_MONITOR_WAIT);
    state |= TM_THREAD_STATE_RUNNABLE;
    hythread_set_state(native_thread, state);
    hythread_thread_unlock(native_thread);

    hythread_suspend_enable();
    return status;
    // End (C) DRLVM
}

jboolean rvmInitThreads(Env* env) {
    if (hythread_lib_create(&lib) < 0) return FALSE;
    if (!initMutex(&monitorsLock)) return FALSE;
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
    Env* env = NULL;
    jint status = rvmGetEnv(vm, &env);
    if (status != JNI_OK) return status;
    return detachThread(env, ignoreAttachCount);
}

static int startThreadEntryPoint(void* entryArgs) {
    Env* env = (Env*) entryArgs;
    Thread* thread = env->currentThread;

    // Start (C) DRLVM
    // get hythread global lock
    IDATA status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);

    // get native thread
    hythread_t native_thread = LONG_TO_PTR(thread->threadPtr);

    // check hythread library state
    if (hythread_lib_state() != TM_LIBRARY_STATUS_INITIALIZED) {
        // set TERMINATED state
        status = hythread_set_state(native_thread, TM_THREAD_STATE_TERMINATED);
        assert(status == TM_ERROR_NONE);

        // set hythread_self()
        hythread_set_self(native_thread);
        assert(native_thread == hythread_self());

        // release thread structure data
        hythread_detach_ex(native_thread);

        // zero hythread_self() because we don't do it in hythread_detach_ex()
        hythread_set_self(NULL);

        // release hythread global lock
        status = hythread_global_unlock();
        assert(status == TM_ERROR_NONE);

        goto finish;
    }

    // register to group and set ALIVE & RUNNABLE states
    status = hythread_set_to_group(native_thread, get_java_thread_group());
    assert(status == TM_ERROR_NONE);

    // set hythread_self()
    hythread_set_self(native_thread);
    assert(native_thread == hythread_self());

    // set priority
    status = hythread_set_priority(native_thread,
                                   hythread_get_priority(native_thread));
    // FIXME - cannot set priority
    //assert(status == TM_ERROR_NONE);

    if (!thread->daemon) {
        status = hythread_increase_nondaemon_threads_count(native_thread);
        assert(status == TM_ERROR_NONE);
    }

    // release hythread global lock
    status = hythread_global_unlock();
    assert(status == TM_ERROR_NONE);
    // End (C) DRLVM

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

    detachThread(env, TRUE);

    return 0;
}

jlong rvmStartThread(Env* env, Thread* thread) {
    Env* newEnv = rvmCreateEnv(env->vm);
    if (!newEnv) {
        rvmThrowOutOfMemoryError(env);
        return 0;
    }

    newEnv->currentThread = thread;
    newEnv->attachCount = 1;

    hythread_t hyThread = rvmAllocateMemory(env, hythread_get_struct_size());
    if (!hyThread) {
        rvmThrowOutOfMemoryError(env);
        return 0;
    }

    // TODO: Default stack size is 512 kB which is a bit too much on iOS. Android seems to be using 8 kB as default stack size.
    setThreadPtr(thread, hyThread);
    IDATA status = hythread_create_ex(hyThread, NULL, thread->stackSize, thread->priority, startThreadEntryPoint, NULL, newEnv);

    if (status != TM_ERROR_NONE) {
        // TODO: What can we do here?
        rvmAbort("Failed to start thread");
    }

    return thread->threadPtr;
}

void rvmMonitorEnter(Env* env, Object* obj) {
    lockMutex(&monitorsLock);
    if (!obj->monitor) {
        hythread_suspend_disable();
        hythread_thin_monitor_t* lockword = &obj->monitor;
        IDATA status = hythread_thin_monitor_create(lockword);
        hythread_suspend_enable();
        if (status != TM_ERROR_NONE) {
            unlockMutex(&monitorsLock);
            rvmAbort("Failed to initialize monitor: %d", status);
        }
    }
    unlockMutex(&monitorsLock);
    monitorEnter(env, obj);
}

void rvmMonitorExit(Env* env, Object* obj) {
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }

    // Start (C) DRLVM
    hythread_suspend_disable();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    IDATA status = hythread_thin_monitor_exit(lockword);
    hythread_suspend_enable();
    // End (C) DRLVM

    if (status == TM_ERROR_ILLEGAL_STATE) {
        rvmThrowIllegalMonitorStateException(env);
    }
}

void rvmMonitorNotify(Env* env, Object* obj) {
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }

    // Start (C) DRLVM
    hythread_suspend_disable();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    IDATA status = hythread_thin_monitor_notify(lockword);
    hythread_suspend_enable();
    // End (C) DRLVM

    if (status != TM_ERROR_NONE) {
        if (status == TM_ERROR_ILLEGAL_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        rvmAbort("Unexpected value returned by hythread_thin_monitor_notify(): %d", status);
    }
}

void rvmMonitorNotifyAll(Env* env, Object* obj) {
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }

    // Start (C) DRLVM
    hythread_suspend_disable();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    IDATA status = hythread_thin_monitor_notify_all(lockword);
    hythread_suspend_enable();
    // End (C) DRLVM

    if (status != TM_ERROR_NONE) {
        if (status == TM_ERROR_ILLEGAL_STATE) {
            rvmThrowIllegalMonitorStateException(env);
            return;
        }
        rvmAbort("Unexpected value returned by hythread_thin_monitor_notify_all(): %d", status);
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
    if (!obj->monitor) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    IDATA status = monitorWait(env, obj, millis, nanos);
    if (status == TM_ERROR_ILLEGAL_STATE) {
        rvmThrowIllegalMonitorStateException(env);
        return;
    }
    if (status == TM_ERROR_INTERRUPT) {
        rvmThrowInterruptedException(env);
        return;
    }
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

    // Start (C) DRLVM
    hythread_t native_thread = hythread_self();
    hythread_thread_lock(native_thread);
    IDATA state = hythread_get_state(native_thread);
    state &= ~TM_THREAD_STATE_RUNNABLE;
    state |= TM_THREAD_STATE_WAITING | TM_THREAD_STATE_SLEEPING
                | TM_THREAD_STATE_WAITING_WITH_TIMEOUT;
    IDATA status = hythread_set_state(native_thread, state);
    assert(status == TM_ERROR_NONE);
    hythread_thread_unlock(native_thread);

    status = hythread_sleep_interruptable(millis, nanos);

    hythread_thread_lock(native_thread);
    state = hythread_get_state(native_thread);
    state &= ~(TM_THREAD_STATE_WAITING | TM_THREAD_STATE_SLEEPING
                    | TM_THREAD_STATE_WAITING_WITH_TIMEOUT);
    state |= TM_THREAD_STATE_RUNNABLE;
    hythread_set_state(native_thread, state);
    hythread_thread_unlock(native_thread);
    // End (C) DRLVM
}

jboolean rvmThreadHoldsLock(Env* env, Object* obj) {
    if (!obj->monitor) {
        return FALSE;
    }

    hythread_suspend_disable();
    hythread_thin_monitor_t* lockword = &obj->monitor;
    hythread_t owner_thread = hythread_thin_monitor_get_owner(lockword);
    hythread_suspend_enable();

    return owner_thread == (hythread_t) LONG_TO_PTR(env->currentThread->threadPtr);
}

jboolean rvmThreadClearInterrupted(Env* env, Thread* thread) {
    return (hythread_clear_interrupted_other((hythread_t) LONG_TO_PTR(thread->threadPtr)) == TM_ERROR_INTERRUPT) ? TRUE : FALSE;
}

jboolean rvmThreadIsInterrupted(Env* env, Thread* thread) {
    return hythread_interrupted((hythread_t) LONG_TO_PTR(thread->threadPtr)) > 0 ? TRUE : FALSE;
}

void rvmThreadInterrupt(Env* env, Thread* thread) {
    hythread_interrupt((hythread_t) LONG_TO_PTR(thread->threadPtr));
}

void rvmThreadYield(Env* env) {
    hythread_yield();
}

jint rvmThreadGetState(Env* env, Thread* thread) {
    hythread_t hyThread = LONG_TO_PTR(thread->threadPtr);
    if (!hyThread) {
        return thread->started ? THREAD_STATE_TERMINATED : THREAD_STATE_NEW;
    }

    if (hythread_is_terminated(hyThread)) {
        return THREAD_STATE_TERMINATED;
    }
    if (hythread_is_waiting_with_timeout(hyThread)) {
        return THREAD_STATE_TIMED_WAITING;
    }
    if (hythread_is_waiting(hyThread)) {
        return THREAD_STATE_WAITING;
    }
    if (hythread_is_waiting_indefinitely(hyThread)) {
        return THREAD_STATE_WAITING;
    }
    if (hythread_is_parked(hyThread)) {
        return THREAD_STATE_WAITING;
    }
    if (hythread_is_blocked_on_monitor_enter(hyThread)) {
        return THREAD_STATE_BLOCKED;
    }
    if (hythread_is_runnable(hyThread)) {
        return THREAD_STATE_RUNNABLE;
    }
    if (hythread_is_alive(hyThread)) {
        return THREAD_STATE_RUNNABLE;
    }
    return THREAD_STATE_NEW;
}
