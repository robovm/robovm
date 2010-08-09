#include <nullvm.h>
#include <string.h>

static void* allocateMemoryForFunction(int size, char* templatePtr) {
  void* m = nvmAllocateExecutableMemory(size);
  memcpy(m, templatePtr, size);
  return m;
}

jmethod* nvmGetMethod(jclass* clazz, char* name, char* desc, jclass* caller) {
    jmethod* method;
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
        return nvmGetMethod(clazz, name, desc, caller);
    }

    nvmThrowNoSuchMethodError(name);
}

void* nvmGetInvokeStaticFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    // TODO: Throw something if methodName is <clinit>
    jmethod* method = nvmGetMethod(clazz, methodName, methodDesc, caller);
    if (!(method->access & ACC_STATIC)) {
        nvmThrowIncompatibleClassChangeErrorMethod(clazz, methodName, methodDesc);
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* nvmGetInvokeVirtualFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    // TODO: Throw something if methodName is <clinit>
    jmethod* method = nvmGetMethod(clazz, methodName, methodDesc, caller);
    if (method->access & ACC_STATIC) {
        nvmThrowIncompatibleClassChangeErrorMethod(clazz, methodName, methodDesc);
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* nvmGetInvokeInterfaceFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    // TODO: Implement me properly!
    LOG("nvmGetInvokeInterfaceFunction not implemented!");
    nvmThrowNoSuchMethodError(methodName);
    return NULL;
}

void* nvmGetInvokeSpecialFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    // TODO: Throw something if methodName is <clinit>
    // TODO: Check caller has ACC_SUPER access?
    jmethod* method = nvmGetMethod(clazz, methodName, methodDesc, caller);
    if (method->access & ACC_STATIC) {
        nvmThrowIncompatibleClassChangeErrorMethod(clazz, methodName, methodDesc);
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* nvmCreateMethodWrapper(jclass* clazz, jmethod* method) {
    // TODO: Should we create wrappers for abstract methods and throw AbstractMethodError?
    // TODO: Handle synchronized methods
    if (IS_NATIVE(method->access)) {
    }
    if (!IS_NATIVE(method->access) && (IS_PRIVATE(method->access) || IS_FINAL(method->access))) {
        return method->impl;
    }
    return method->impl;
}

void* nvmGetNativeMethod(char* shortMangledName, char* longMangledName, void** functionPtr) {
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
        nvmThrowUnsatisfiedLinkError();
    }
    *functionPtr = f;
    return f;
}

