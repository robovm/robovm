#include <nullvm.h>

jboolean nvmInitThreads(Env* env) {
    Thread* thread = nvmAllocateMemory(env, sizeof(Thread));
    if (!thread) return FALSE;

    if (hythread_attach(&thread->hyThread) < 0) return FALSE;

    Method* threadConstructor = nvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(J)V");
    if (!threadConstructor) return FALSE;
    Object* threadObj = nvmNewObject(env, java_lang_Thread, threadConstructor, (jlong) thread);
    if (!threadObj) return FALSE;

    thread->threadObj = threadObj;
    env->currentThread = thread;

    return TRUE;
}

jint nvmMonitorEnter(Env* env, Object* obj) {
    // TODO: Implement me!
    return 0;
}

jint nvmMonitorExit(Env* env, Object* obj) {
    // TODO: Implement me!
    return 0;
}

