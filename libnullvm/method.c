#include <nullvm.h>
#include <string.h>

Method* nvmGetMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method;
    for (method = clazz->methods; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return method;
        }
    }

    if (clazz->superclass && strcmp("<init>", name) && strcmp("<clinit>", name)) {
        /* 
         * Check with the superclass. Note that constructors and static 
         * initializers are not inherited.
         */
        return nvmGetMethod(env, clazz->superclass, name, desc);
    }

    nvmThrowNoSuchMethodError(env, name);
    return NULL;
}

Method* nvmGetClassMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (!(method->access & ACC_STATIC)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* nvmGetInstanceMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (method->access & ACC_STATIC) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

/*
Method* nvmGetMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method;
    int sameClass = caller == NULL || clazz == caller;
    int subClass = caller == NULL || nvmIsSubClass(clazz, caller);
    int samePackage = caller == NULL || nvmIsSamePackage(clazz, caller);

    for (method = clazz->methods; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            jint access = method->access;
            if (IS_PRIVATE(access) && !sameClass) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            if (IS_PROTECTED(access) && !subClass) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            if (IS_PACKAGE_PRIVATE(access) && !samePackage) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            return method;
        }
    }

    if (clazz->superclass && strcmp("<init>", name) && strcmp("<clinit>", name)) {
        /* 
         * Check with the superclass. Note that constructors and static 
         * initializers are not inherited.
         */
       /* return nvmGetMethod(clazz, name, desc);
    }

    nvmThrowNoSuchMethodError(name);
}*/

void* nvmGetNativeMethod(Env* env, char* shortMangledName, char* longMangledName) {
    void* handle = dlopen(NULL, RTLD_LAZY);
    LOG("Searching for native method using short name: %s\n", shortMangledName);
    void* f = dlsym(handle, shortMangledName);
    if (!f) {
        LOG("Searching for native method using long name: %s\n", longMangledName);
        f = dlsym(handle, longMangledName);
        if (f) {
            LOG("Found native method using long name: %s\n", longMangledName);
        }
    } else {
        LOG("Found native method using short name: %s\n", shortMangledName);
    }
    dlclose(handle);
    if (!f) {
        nvmThrowUnsatisfiedLinkError(env);
        return NULL;
    }
    return f;
}

