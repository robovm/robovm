#include <nullvm.h>

Object* Java_java_lang_Thread_currentThread(Env* env, Class* cls) {
    return env->currentThread->threadObj;
}

jlong Java_java_lang_Thread_internalStart(Env* env, Class* cls, Object* t, jint priority) {
    jlong threadPtr = nvmStartThread(env, t, priority);
    return threadPtr;
}

void Java_java_lang_Thread_internalSleep(Env* env, Class* cls, jlong millis, jint nanos) {
    nvmThreadSleep(env, millis, nanos);
}

void Java_java_lang_Thread_internalStop(Env* env, Class* cls, jlong threadPtr, Object* throwable) {
    CallStackEntry* callStack = nvmGetCallStack(env);
    while (callStack && callStack->method->clazz == java_lang_Thread) {
        callStack = callStack->next;
    }
    Method* caller = callStack->method;
    nvmLogError(env, "Thread.stop() is not supported. Called from %s.%s%s.\n.", caller->clazz->name, caller->name, caller->desc);
}

void Java_java_lang_Thread_internalSuspend(Env* env, Class* cls, jlong threadPtr) {
    CallStackEntry* callStack = nvmGetCallStack(env);
    while (callStack && callStack->method->clazz == java_lang_Thread) {
        callStack = callStack->next;
    }
    Method* caller = callStack->method;
    nvmLogError(env, "Thread.suspend() is not supported. Called from %s.%s%s.\n.", caller->clazz->name, caller->name, caller->desc);
}

void Java_java_lang_Thread_internalResume(Env* env, Class* cls, jlong threadPtr) {
    CallStackEntry* callStack = nvmGetCallStack(env);
    while (callStack && callStack->method->clazz == java_lang_Thread) {
        callStack = callStack->next;
    }
    Method* caller = callStack->method;
    nvmLogError(env, "Thread.resume() is not supported. Called from %s.%s%s.\n.", caller->clazz->name, caller->name, caller->desc);
}

jboolean Java_java_lang_Thread_internalHoldsLock(Env* env, Class* cls, Object* obj) {
    return nvmThreadHoldsLock(env, obj);
}

