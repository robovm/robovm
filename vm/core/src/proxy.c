#include <nullvm.h>
#include "trampolines.h"
#include "uthash.h"

typedef struct LookupEntry {
    void* lookup;
    Method* method;
    UT_hash_handle hh;
} LookupEntry;

typedef struct ProxyClassData {
    ProxyHandler handler;
    LookupEntry* lookupsHash;
} ProxyClassData;

static jboolean hasMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return TRUE;
        }
    }
    return FALSE;
}

static jboolean addProxyMethods(Env* env, Class* proxyClass, Class* clazz, ProxyClassData* proxyClassData) {
    // Add constructors from the super class and override all overridable methods. Constructors will use 
    // the same impl as the superclass. Overridden methods will have _nvmProxy0 as its impl.

    if (clazz->superclass) {
        if (!addProxyMethods(env, proxyClass, clazz->superclass, proxyClassData)) return FALSE;
    }

    Method* method;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!METHOD_IS_STATIC(method) && !METHOD_IS_PRIVATE(method) && !METHOD_IS_FINAL(method) 
                && (!METHOD_IS_CONSTRUCTOR(method) || clazz == proxyClass->superclass)) {

            void* impl = NULL;
            jint access = method->access & (~ACC_ABSTRACT);
            if (METHOD_IS_CONSTRUCTOR(method)) {
                impl = method->impl;
                // TODO: For now we make all constructors public to satisfy java.lang.reflect.Proxy. 
                access = ACC_PUBLIC;
            } else {
                impl = _nvmProxy0;
            }
            if (METHOD_IS_CONSTRUCTOR(method) || !hasMethod(env, proxyClass, method->name, method->desc)) { 
                if (nvmAddMethod(env, proxyClass, method->name, method->desc, access, impl, NULL, method->lookup) == NULL) {
                    return FALSE;
                }
                if (!METHOD_IS_CONSTRUCTOR(method)) {
                    // Record the lookup function in proxyClassData
                    LookupEntry* entry = nvmAllocateMemory(env, sizeof(LookupEntry));
                    if (!entry) return FALSE;
                    entry->lookup = method->lookup;
                    entry->method = method;
                    HASH_ADD_PTR(proxyClassData->lookupsHash, lookup, entry);
                }
            }
        }
    }

    return TRUE;
}

/**
 * Implements all abstract methods in {@code proxyClass}. This should be called after
 * {@link #addProxyMethods()} which will override all methods defined by the proxy's
 * ancestor classes (abstract or concrete).
 */
static jboolean implementAbstractInterfaceMethods(Env* env, Class* proxyClass, Interface* interface, ProxyClassData* proxyClassData) {
    if (!interface) return TRUE;

    Method* method;
    for (method = interface->interface->methods->first; method != NULL; method = method->next) {
        if (!hasMethod(env, proxyClass, method->name, method->desc)) { 
            jint access = method->access & (~ACC_ABSTRACT);
            if (nvmAddMethod(env, proxyClass, method->name, method->desc, access, _nvmProxy0, NULL, method->lookup) == NULL) {
                return FALSE;
            }
            // Record the lookup function in proxyClassData
            LookupEntry* entry = nvmAllocateMemory(env, sizeof(LookupEntry));
            if (!entry) return FALSE;
            entry->lookup = method->lookup;
            entry->method = method;
            HASH_ADD_PTR(proxyClassData->lookupsHash, lookup, entry);
        }
    }

    if (!implementAbstractInterfaceMethods(env, proxyClass, interface->next, proxyClassData)) return FALSE;
    if (!implementAbstractInterfaceMethods(env, proxyClass, interface->interface->interfaces, proxyClassData)) return FALSE;

    return TRUE;
}

Class* nvmProxyCreateProxyClass(Env* env, Class* superclass, ClassLoader* classLoader, char* className, jint interfacesCount, Class** interfaces, jint instanceDataSize, ProxyHandler handler) {

    // Allocate the proxy class.
    Class* proxyClass = nvmAllocateClass(env, className, superclass, classLoader, ACC_PUBLIC | ACC_FINAL, sizeof(ProxyClassData), instanceDataSize);
    if (!proxyClass) return NULL;

    ProxyClassData* proxyClassData = (ProxyClassData*) proxyClass->data;
    proxyClassData->handler = handler;

    // Add interfaces
    jint i;
    for (i = 0; i < interfacesCount; i++) {
        if (!nvmAddInterface(env, proxyClass, (Class*) interfaces[i])) return NULL;
    }

    if (!addProxyMethods(env, proxyClass, superclass, proxyClassData)) return NULL;

    Class* c = proxyClass;
    while (c) {
        if (!implementAbstractInterfaceMethods(env, proxyClass, c->interfaces, proxyClassData)) return NULL;
        c = c->superclass;
    }

    if (!nvmRegisterClass(env, proxyClass)) return NULL;

    return proxyClass;
}

void _nvmProxyHandler(ProxyArgs* proxyArgs) {
    ProxyArgsIterator argsIt;
    initProxyArgsIterator(&argsIt, proxyArgs);

    Env* env = (Env*) getNextPtrProxyArg(&argsIt);
    Object* receiver = (Object*) getNextPtrProxyArg(&argsIt);
    Class* proxyClass = receiver->clazz;
    ProxyClassData* proxyClassData = (ProxyClassData*) proxyClass->data;

    void* lookup = nvmUnwindGetCallerAtDepth(env, 1, NULL);
    LookupEntry* entry;

    HASH_FIND_PTR(proxyClassData->lookupsHash, &lookup, entry);

    if (!entry) {
        nvmAbort("Failed to determine which method was called on proxy class. proxyClass=%s, lookup=%p\n", proxyClass->name, lookup);
    }

    Method*  method = entry->method;

    jint argsCount = nvmGetParameterCount(method);
    jvalue *jvalueArgs = NULL;
    if (argsCount > 0) {
        jvalueArgs = (jvalue*) nvmAllocateMemory(env, sizeof(jvalue) * argsCount);
        if (!jvalueArgs) return;

        char* desc = method->desc;
        char* c;
        jint i = 0;
        while (c = nvmGetNextParameterType(&desc)) {
            switch (c[0]) {
            case 'B':
                jvalueArgs[i++].b = getNextByteProxyArg(&argsIt);
                break;
            case 'Z':
                jvalueArgs[i++].z = getNextBooleanProxyArg(&argsIt);
                break;
            case 'S':
                jvalueArgs[i++].s = getNextShortProxyArg(&argsIt);
                break;
            case 'C':
                jvalueArgs[i++].c = getNextCharProxyArg(&argsIt);
                break;
            case 'I':
                jvalueArgs[i++].i = getNextIntProxyArg(&argsIt);
                break;
            case 'J':
                jvalueArgs[i++].j = getNextLongProxyArg(&argsIt);
                break;
            case 'F':
                jvalueArgs[i++].f = getNextFloatProxyArg(&argsIt);
                break;
            case 'D':
                jvalueArgs[i++].d = getNextDoubleProxyArg(&argsIt);
                break;
            case '[':
            case 'L':
                jvalueArgs[i++].l = (jobject) getNextPtrProxyArg(&argsIt);
                break;
            }
        }
    }

    // _nvmProxy0 expects the return value to be written to the same location as proxyArgs points to
    proxyClassData->handler(env, receiver, method, jvalueArgs, (jvalue*) proxyArgs);

    if (nvmExceptionCheck(env)) {
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
}

