#include <nullvm.h>

jboolean nvmInitThreads(Env* env) {
    Thread* thread = nvmAllocateMemory(env, sizeof(Thread));
    if (!thread) return FALSE;

    // Create the java.lang.Thread object representing the main thread
    ClassField* groupField = nvmGetClassField(env, java_lang_ThreadGroup, "mMain", "Ljava/lang/ThreadGroup;");
    if (!groupField) return FALSE;
    Object* group = nvmGetObjectClassFieldValue(env, java_lang_ThreadGroup, groupField);
    if (!group) return FALSE;
    Object* name = nvmNewStringAscii(env, "main", -1);
    if (!name) return FALSE;
    Method* threadConstructor = nvmGetInstanceMethod(env, java_lang_Thread, "<init>", "(Ljava/lang/ThreadGroup;Ljava/lang/String;IZ)V");
    if (!threadConstructor) return FALSE;
    Object* threadObj = nvmNewObject(env, java_lang_Thread, threadConstructor, group, name, 5, FALSE);
    if (!threadObj) return FALSE;

    // Create the java.lang.VMThread object representing the main thread
    Method* vmThreadConstructor = nvmGetInstanceMethod(env, java_lang_VMThread, "<init>", "(Ljava/lang/Thread;)V");
    if (!vmThreadConstructor) return FALSE;
    Object* vmThreadObj = nvmNewObject(env, java_lang_VMThread, vmThreadConstructor, threadObj);
    if (!vmThreadObj) return FALSE;

    // Set the vmData field of the VMThread to point to the Thread*
    InstanceField* vmDataField = nvmGetInstanceField(env, java_lang_VMThread, "vmData", "J");
    if (!vmDataField) return FALSE;
    nvmSetLongInstanceFieldValue(env, vmThreadObj, vmDataField, (jlong) thread);
    if (nvmExceptionCheck(env)) return FALSE;

    // Set the hasBeenStarted field of threadObj to true
    InstanceField* hasBeenStartedField = nvmGetInstanceField(env, java_lang_Thread, "hasBeenStarted", "Z");
    if (!hasBeenStartedField) return FALSE;
    nvmSetBooleanInstanceFieldValue(env, threadObj, hasBeenStartedField, TRUE);
    if (nvmExceptionCheck(env)) return FALSE;

    // Set the vmThread field of threadObj to point to vmThreadObj
    InstanceField* vmThreadField = nvmGetInstanceField(env, java_lang_Thread, "vmThread", "Ljava/lang/VMThread;");
    if (!vmThreadField) return FALSE;
    nvmSetObjectInstanceFieldValue(env, threadObj, vmThreadField, vmThreadObj);
    if (nvmExceptionCheck(env)) return FALSE;

    thread->id = pthread_self();
    thread->threadObj = threadObj;
    thread->vmThreadObj = vmThreadObj;
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

