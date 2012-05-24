#include <robovm.h>

Thread* Java_java_lang_Thread_currentThread(Env* env, Class* cls) {
    return env->currentThread;
}

jlong Java_java_lang_Thread_internalStart(Env* env, Class* cls, Thread* t, jint priority) {
    jlong threadPtr = rvmStartThread(env, t, priority);
    return threadPtr;
}

void Java_java_lang_Thread_internalSleep(Env* env, Class* cls, jlong millis, jint nanos) {
    rvmThreadSleep(env, millis, nanos);
}

jboolean Java_java_lang_Thread_internalInterrupted(Env* env, Class* cls) {
    return rvmThreadClearInterrupted(env);
}

jboolean Java_java_lang_Thread_internalIsInterrupted(Env* env, Class* cls, Thread* thread) {
    return rvmThreadIsInterrupted(env, thread);
}

void Java_java_lang_Thread_internalInterrupt(Env* env, Class* cls, Thread* thread) {
    rvmThreadInterrupt(env, thread);
}

jboolean Java_java_lang_Thread_internalHoldsLock(Env* env, Class* cls, Object* obj) {
    return rvmThreadHoldsLock(env, obj);
}

void Java_java_lang_Thread_internalYield(Env* env, Class* cls) {
    rvmThreadYield(env);
}

