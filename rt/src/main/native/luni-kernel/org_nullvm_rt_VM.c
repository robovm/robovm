#include <nullvm.h>

ObjectArray* Java_org_nullvm_rt_VM_getStackClasses(Env* env, Class* c, jint skipNum, jint maxDepth) {
    CallStackEntry* first = nvmGetCallStack(env);
    if (!first) return NULL;
    first = first->next; // Skip VM.getStackClasses()
    if (!first) return NULL;
    first = first->next; // Skip caller of VM.getStackClasses()
    if (!first) return NULL;

    while (skipNum > 0) {
        first = first->next; // Skip
        if (!first) return NULL;
        skipNum--;
    }

    jint depth = 0;
    CallStackEntry* entry = first;
    while (entry) {
        depth++;
        entry = entry->next;
    }
    if (maxDepth > -1 && maxDepth < depth) {
        depth = maxDepth;
    }
    
    ObjectArray* result = nvmNewObjectArray(env, depth, java_lang_Class, NULL, NULL);
    if (!result) return NULL;
    jint i;
    entry = first;
    for (i = 0; i < depth; i++) {
        result->values[i] = (Object*) entry->method->clazz;
        entry = entry->next;
    }
    return result;
}

Object* Java_org_nullvm_rt_VM_intern(Env* env, Class* c, Object* string) {
    return nvmInternString(env, string);
}

ClassLoader* Java_org_nullvm_rt_VM_getBootClassLoader(Env* env, Class* c) {
    Class* holder = nvmFindClass(env, "java/lang/ClassLoader$BootClassLoaderHolder");
    if (!holder) return NULL;
    ClassField* field = nvmGetClassField(env, holder, "loader", "Ljava/lang/ClassLoader;");
    if (!field) return NULL;
    return (ClassLoader*) nvmGetObjectClassFieldValue(env, holder, field);
}

