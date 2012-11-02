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
#include <unwind.h>
#include "private.h"
#include "uthash.h"
#include "utlist.h"

#define ALLOC_PROXY_FRAMES_SIZE 2

typedef struct {
    const char* name;
    const char* desc;
} LookupKey;

typedef struct {
    LookupKey key;
    ProxyMethod* method;
    UT_hash_handle hh;
} LookupEntry;

typedef struct {
    ProxyHandler handler;
    LookupEntry* lookupsHash;
} ProxyClassData;

static ProxyMethod* hasMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = clazz->_methods;
    for (; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return (ProxyMethod*) method;
        }
    }
    return NULL;
}

static jboolean addProxyMethods(Env* env, Class* proxyClass, Class* clazz, ProxyClassData* proxyClassData) {
    // Add constructors from the super class and override all overridable methods. Constructors will use 
    // the same impl as the superclass. Overridden methods will have _rvmProxy0 as its impl.

    if (clazz->superclass) {
        if (!addProxyMethods(env, proxyClass, clazz->superclass, proxyClassData)) return FALSE;
    }

    Method* method = rvmGetMethods(env, clazz);
    if (rvmExceptionOccurred(env)) return FALSE;
    for (; method != NULL; method = method->next) {
        if (!METHOD_IS_STATIC(method) && !METHOD_IS_PRIVATE(method) && !METHOD_IS_FINAL(method)
                && (!METHOD_IS_CONSTRUCTOR(method) || clazz == proxyClass->superclass)) {

            void* impl = NULL;
            jint access = (method->access & (~ACC_ABSTRACT & ~ACC_NATIVE)) | ACC_FINAL;
            if (METHOD_IS_CONSTRUCTOR(method)) {
                impl = method->impl;
                // TODO: For now we make all constructors public to satisfy java.lang.reflect.Proxy. 
                access = ACC_PUBLIC;
                if (!addProxyMethod(env, proxyClass, method, access, impl)) return FALSE;
            } else {
                impl = _proxy0;
                if (METHOD_IS_PUBLIC(method)) { 
                    ProxyMethod* proxyMethod = hasMethod(env, proxyClass, method->name, method->desc);
                    if (rvmExceptionOccurred(env)) return FALSE;
                    if (!proxyMethod) {
                        proxyMethod = addProxyMethod(env, proxyClass, method, access, impl);
                        if (!proxyMethod) return FALSE;
                    }
                    // Record the lookup function in proxyClassData
                    LookupEntry* entry = rvmAllocateMemory(env, sizeof(LookupEntry));
                    if (!entry) return FALSE;
                    entry->key.name = method->name;
                    entry->key.desc = method->desc;
                    entry->method = proxyMethod;
                    HASH_ADD(hh, proxyClassData->lookupsHash, key, sizeof(LookupKey), entry);
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

    Method* method = rvmGetMethods(env, interface->interface);
    if (rvmExceptionOccurred(env)) return FALSE;
    for (; method != NULL; method = method->next) {
        if (!METHOD_IS_CLASS_INITIALIZER(method)) {
            ProxyMethod* proxyMethod = hasMethod(env, proxyClass, method->name, method->desc);
            if (rvmExceptionOccurred(env)) return FALSE;
            if (!proxyMethod) { 
                jint access = (method->access & (~ACC_ABSTRACT)) | ACC_FINAL;
                proxyMethod = addProxyMethod(env, proxyClass, method, access, _proxy0);
                if (!proxyMethod) return FALSE;
            }
            // Record the lookup function in proxyClassData
            LookupEntry* entry = rvmAllocateMemory(env, sizeof(LookupEntry));
            if (!entry) return FALSE;
            entry->key.name = method->name;
            entry->key.desc = method->desc;
            entry->method = proxyMethod;
            HASH_ADD(hh, proxyClassData->lookupsHash, key, sizeof(LookupKey), entry);
        }
    }

    if (!implementAbstractInterfaceMethods(env, proxyClass, interface->next, proxyClassData)) return FALSE;
    Interface* interfaceInterfaces = rvmGetInterfaces(env, interface->interface);
    if (rvmExceptionCheck(env)) return FALSE;
    if (!implementAbstractInterfaceMethods(env, proxyClass, interfaceInterfaces, proxyClassData)) return FALSE;

    return TRUE;
}

Class* rvmProxyCreateProxyClass(Env* env, Class* superclass, ClassLoader* classLoader, char* className, jint interfacesCount, Class** interfaces, 
        jint instanceDataSize, jint instanceDataOffset, ProxyHandler handler) {

    // Allocate the proxy class.
    Class* proxyClass = rvmAllocateClass(env, className, superclass, classLoader, CLASS_TYPE_PROXY | ACC_PUBLIC | ACC_FINAL, 
        offsetof(Class, data) + sizeof(ProxyClassData), instanceDataSize, instanceDataOffset, NULL, NULL);
    if (!proxyClass) return NULL;

    ProxyClassData* proxyClassData = (ProxyClassData*) proxyClass->data;
    proxyClassData->handler = handler;

    // Add interfaces
    jint i;
    for (i = 0; i < interfacesCount; i++) {
        if (!rvmAddInterface(env, proxyClass, (Class*) interfaces[i])) return NULL;
    }

    // Initialize methods to NULL to prevent rvmGetMethods() from trying to load the methods if called with this proxy class
    proxyClass->_methods = NULL;

    Class* c = proxyClass;
    while (c) {
        Interface* interface = rvmGetInterfaces(env, c);
        if (rvmExceptionCheck(env)) return NULL;
        if (!implementAbstractInterfaceMethods(env, proxyClass, interface, proxyClassData)) return NULL;
        c = c->superclass;
    }

    if (!addProxyMethods(env, proxyClass, superclass, proxyClassData)) return NULL;

    if (!rvmRegisterClass(env, proxyClass)) return NULL;

    return proxyClass;
}

void _rvmProxyHandler(CallInfo* callInfo) {
    Env* env = (Env*) proxy0NextPtr(callInfo);
    Object* receiver = (Object*) proxy0NextPtr(callInfo);
    Class* proxyClass = receiver->clazz;
    ProxyClassData* proxyClassData = (ProxyClassData*) proxyClass->data;

    LookupKey key;
    memset(&key, 0, sizeof(LookupKey));
    key.name = (char*) env->reserved0;
    key.desc = (char*) env->reserved1;
    LookupEntry* entry;
    HASH_FIND(hh, proxyClassData->lookupsHash, &key, sizeof(LookupKey), entry);
    if (!entry) {
        rvmThrowNoSuchMethodError(env, "Failed to determine which method was called on proxy class");
        goto error;
    }

    ProxyMethod* method = entry->method;

    rvmPushGatewayFrameProxy(env, method);

    jint argsCount = rvmGetParameterCount((Method*) method);
    jvalue *jvalueArgs = NULL;
    if (argsCount > 0) {
        jvalueArgs = (jvalue*) rvmAllocateMemory(env, sizeof(jvalue) * argsCount);
        if (!jvalueArgs) goto errorPop;

        const char* desc = method->method.desc;
        const char* c;
        jint i = 0;
        while ((c = rvmGetNextParameterType(&desc))) {
            switch (c[0]) {
            case 'B':
                jvalueArgs[i++].b = (jbyte) proxy0NextInt(callInfo);
                break;
            case 'Z':
                jvalueArgs[i++].z = (jboolean) proxy0NextInt(callInfo);
                break;
            case 'S':
                jvalueArgs[i++].s = (jshort) proxy0NextInt(callInfo);
                break;
            case 'C':
                jvalueArgs[i++].c = (jchar) proxy0NextInt(callInfo);
                break;
            case 'I':
                jvalueArgs[i++].i = proxy0NextInt(callInfo);
                break;
            case 'J':
                jvalueArgs[i++].j = proxy0NextLong(callInfo);
                break;
            case 'F':
                jvalueArgs[i++].f = proxy0NextFloat(callInfo);
                break;
            case 'D':
                jvalueArgs[i++].d = proxy0NextDouble(callInfo);
                break;
            case '[':
            case 'L':
                jvalueArgs[i++].l = (jobject) proxy0NextPtr(callInfo);
                break;
            }
        }
    }

    jvalue returnValue;
    proxyClassData->handler(env, receiver, method, jvalueArgs, &returnValue);

    rvmPopGatewayFrame(env);

    if (rvmExceptionCheck(env)) goto error;

    proxy0ReturnInt(callInfo, 0);
    switch (rvmGetReturnType(method->method.desc)[0]) {
    case 'B':
        proxy0ReturnInt(callInfo, (jint) returnValue.b);
        break;
    case 'Z':
        proxy0ReturnInt(callInfo, (jint) returnValue.z);
        break;
    case 'S':
        proxy0ReturnInt(callInfo, (jint) returnValue.s);
        break;
    case 'C':
        proxy0ReturnInt(callInfo, (jint) returnValue.c);
        break;
    case 'I':
        proxy0ReturnInt(callInfo, returnValue.i);
        break;
    case 'J':
        proxy0ReturnLong(callInfo, returnValue.j);
        break;
    case 'F':
        proxy0ReturnFloat(callInfo, returnValue.f);
        break;
    case 'D':
        proxy0ReturnDouble(callInfo, returnValue.d);
        break;
    case '[':
    case 'L':
        proxy0ReturnPtr(callInfo, returnValue.l);
        break;
    }

    return;

errorPop:
    rvmPopGatewayFrame(env);
error:
    rvmRaiseException(env, rvmExceptionOccurred(env));
}

