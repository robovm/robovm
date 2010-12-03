#include <nullvm.h>

Class* Java_org_apache_harmony_kernel_vm_VM_internalGetStackClass(JNIEnv* _env, Class* c, jint depth) {
    Env* env = (Env*) _env;
    CallStackEntry* first = nvmGetCallStack(env);
    if (!first) return NULL;
    first = first->next; // Skip VM.internalGetStackClass()
    if (!first) return NULL;

    CallStackEntry* entry = first;
    while (entry && depth > 0) {
        depth--;
        entry = entry->next;
    }
    return !entry ? NULL : entry->method->clazz;
}

