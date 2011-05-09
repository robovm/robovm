#include <nullvm.h>
#include "log.h"

static jboolean checkClassAccessible(Env* env, Class* clazz, Class* caller) {
    // TODO: Check that the ClassLoader of clazz is the same as or an ancestor of caller's ClassLoader?
    while (CLASS_IS_ARRAY(clazz)) {
        clazz = nvmGetComponentType(env, clazz);
    }
    if (clazz->primitive) return TRUE;
    if (caller == clazz) return TRUE; 
    if (CLASS_IS_PUBLIC(clazz)) return TRUE; 
    if (nvmIsSamePackage(clazz, caller)) return TRUE;
    nvmThrowIllegalAccessErrorClass(env, clazz, caller);
    return FALSE;
}

static jboolean checkMethodAccessible(Env* env, Method* method, Class* caller) {
    if (caller == method->clazz) return TRUE; 
    if (METHOD_IS_PUBLIC(method)) return TRUE;
    if (METHOD_IS_PROTECTED(method) && nvmIsSubClass(method->clazz, caller)) return TRUE;
    if ((METHOD_IS_PROTECTED(method) || METHOD_IS_PACKAGE_PRIVATE(method)) && nvmIsSamePackage(method->clazz, caller)) return TRUE;
    if (METHOD_IS_PRIVATE(method) && method->clazz == caller) return TRUE;
    nvmThrowIllegalAccessErrorMethod(env, method->clazz, method->name, method->desc, caller);
    return FALSE;
}

static jboolean checkFieldAccessible(Env* env, Field* field, Class* caller) {
    if (caller == field->clazz) return TRUE; 
    if (FIELD_IS_PUBLIC(field)) return TRUE;
    if (FIELD_IS_PROTECTED(field) && nvmIsSubClass(field->clazz, caller)) return TRUE;
    if ((FIELD_IS_PROTECTED(field) || FIELD_IS_PACKAGE_PRIVATE(field)) && nvmIsSamePackage(field->clazz, caller)) return TRUE;
    if (FIELD_IS_PRIVATE(field) && field->clazz == caller) return TRUE;
    nvmThrowIllegalAccessErrorField(env, field->clazz, field->name, field->desc, caller);
    return FALSE;
}

Class* _nvmBcFindClassInLoader(Env* env, char* className, ClassLoader* classLoader) {
    // TODO: Check accessible
    Class* clazz = nvmFindClassInLoader(env, className, classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    return clazz;
}

void _nvmBcAllocateClass(Env* env, char* className, char* superclassName, ClassLoader* classLoader, jint access, jint classDataSize, jint instanceDataSize) {
    // TODO: Check superclass accessible
    Class* superclass = NULL;
    if (superclassName) {
        superclass = nvmFindClassInLoader(env, superclassName, classLoader);
        if (!superclass) nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    Class* c = nvmAllocateClass(env, className, superclass, classLoader, access, classDataSize, instanceDataSize);
    if (!c) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcAddInterface(Env* env, Class* clazz, char* interfaceName) {
    // TODO: Check interface accessible
    Class* interface = nvmFindClassInLoader(env, interfaceName, clazz->classLoader);
    if (!interface) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!nvmAddInterface(env, clazz, interface)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl, void* lookup) {
    if (!nvmAddMethod(env, clazz, name, desc, access, impl, lookup)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset, void* getter, void* setter) {
    if (!nvmAddField(env, clazz, name, desc, access, offset, getter, setter)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcRegisterClass(Env* env, Class* clazz) {
    if (!nvmRegisterClass(env, clazz)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void* _nvmBcLookupVirtualMethod(Env* env, Class* ownerClass, Object* thiz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, thiz->clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (METHOD_IS_ABSTRACT(method)) {
        nvmThrowIllegalAccessError(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return method->impl;
}

void* _nvmBcLookupInterfaceMethod(Env* env, Class* ownerInterface, Object* thiz, char* name, char* desc) {
    if (!nvmIsInstanceOf(env, thiz, ownerInterface)) {
        nvmThrowLinkageError(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    Method* method = nvmGetInstanceMethod(env, thiz->clazz, name, desc);
    Object* throwable = nvmExceptionClear(env);
    if (!method && throwable->clazz != java_lang_NoSuchMethodError) { 
        nvmRaiseException(env, throwable);
    }
    if (!method || METHOD_IS_ABSTRACT(method)) {
        nvmThrowAbstractMethodError(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    if (!METHOD_IS_PUBLIC(method)) {
        nvmThrowIllegalAccessError(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return method->impl;
}

void _nvmBcThrow(Env* env, Object* throwable) {
    nvmRaiseException(env, throwable);
}

void _nvmBcRethrow(Env* env) {
    nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowIfExceptionOccurred(Env* env) {
    Object* throwable = nvmExceptionOccurred(env);
    if (throwable) nvmRaiseException(env, throwable);
}

Object* _nvmBcExceptionClear(Env* env) {
    return nvmExceptionClear(env);
}

jint _nvmBcExceptionMatch(Env* env, Class* clazz) {
    Object* throwable = nvmExceptionOccurred(env);
    Class* c = throwable->clazz;
    while (c && c != clazz) {
        c = c->superclass;
    }
    return c == clazz ? 1 : 0;
}

void _nvmBcExceptionSet(Env* env, Object* throwable) {
    nvmThrow(env, throwable);
}

void _nvmBcThrowNullPointerException(Env* env) {
    nvmThrowNullPointerException(env);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    nvmThrowArrayIndexOutOfBoundsException(env, index);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowArithmeticException(Env* env) {
    nvmThrowArithmeticException(env);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

Object* _nvmBcNew(Env* env, char* className, Class* caller) {
    Class* clazz = nvmFindClassInLoader(env, className, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Object* obj = nvmAllocateObject(env, clazz);
    if (!obj) nvmRaiseException(env, nvmExceptionOccurred(env));
    return obj;
}

BooleanArray* _nvmBcNewBooleanArray(Env* env, jint length) {
    BooleanArray* array = nvmNewBooleanArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

ByteArray* _nvmBcNewByteArray(Env* env, jint length) {
    ByteArray* array = nvmNewByteArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

CharArray* _nvmBcNewCharArray(Env* env, jint length) {
    CharArray* array = nvmNewCharArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

ShortArray* _nvmBcNewShortArray(Env* env, jint length) {
    ShortArray* array = nvmNewShortArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

IntArray* _nvmBcNewIntArray(Env* env, jint length) {
    IntArray* array = nvmNewIntArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

LongArray* _nvmBcNewLongArray(Env* env, jint length) {
    LongArray* array = nvmNewLongArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

FloatArray* _nvmBcNewFloatArray(Env* env, jint length) {
    FloatArray* array = nvmNewFloatArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

DoubleArray* _nvmBcNewDoubleArray(Env* env, jint length) {
    DoubleArray* array = nvmNewDoubleArray(env, length);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

ObjectArray* _nvmBcNewObjectArray(Env* env, jint length, char* arrayClassName, Class* caller) {
    // arrayClassName must be the name of the array class to create an instance of (e.g. [Ljava/lang/Object;)
    Class* arrayClass = nvmFindClassInLoader(env, arrayClassName, caller->classLoader);
    if (!arrayClass) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, arrayClass, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ObjectArray* array = nvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, char* arrayClassName, Class* caller) {
    // TODO: Check that caller has access to the base class
    Class* clazz = nvmFindClassInLoader(env, arrayClassName, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Array* array = nvmNewMultiArray(env, dims, lengths, clazz);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

Object* _nvmBcLdcString(Env* env, char* s) {
    // TODO: The caller knows the length of the string in Java chars
    // TODO: Use nvmNewStringAscii if string only contains ASCII
    Object* o = nvmNewInternedStringUTF(env, s, -1);
    if (!o) nvmRaiseException(env, nvmExceptionOccurred(env));
    return o;
}

Object* _nvmBcLdcClass(Env* env, char* name, Class* caller) {
    // TODO: Check that caller has access to the class
    Class* clazz = nvmFindClassInLoader(env, name, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (Object*) clazz;
}

void _nvmBcMonitorEnter(Env* env, Object* obj) {
    if (nvmMonitorEnter(env, obj)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcMonitorExit(Env* env, Object* obj) {
    if (nvmMonitorExit(env, obj)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

Object* _nvmBcCheckcast(Env* env, Object* o, char* className, Class* caller) {
    // TODO: Check that caller has access to the class
    Class* clazz = nvmFindClassInLoader(env, className, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!o) return o;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!b) {
        nvmThrowClassCastException(env, clazz, o->clazz);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return o;
}

jint _nvmBcInstanceof(Env* env, Object* o, char* className, Class* caller) {
    // TODO: Check that caller has access to the class
    Class* clazz = nvmFindClassInLoader(env, className, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    jboolean b = nvmIsInstanceOf(env, o, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (jint) b;
}

void* _nvmBcResolveGetstatic(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveGetstatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ClassField* field = nvmGetClassField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkFieldAccessible(env, (Field*) field, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.getter;
    return field->field.getter;
}

void* _nvmBcResolvePutstatic(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolvePutstatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ClassField* field = nvmGetClassField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkFieldAccessible(env, (Field*) field, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.setter;
    return field->field.setter;
}

void* _nvmBcResolveGetfield(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveGetfield: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    InstanceField* field = nvmGetInstanceField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkFieldAccessible(env, (Field*) field, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.getter;
    return field->field.getter;
}

void* _nvmBcResolvePutfield(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolvePutfield: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    InstanceField* field = nvmGetInstanceField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkFieldAccessible(env, (Field*) field, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.setter;
    return field->field.setter;
}

void* _nvmBcResolveInvokespecial(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveInvokespecial: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkMethodAccessible(env, method, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    *ptr = method->impl;
    return method->impl;
}

void* _nvmBcResolveInvokestatic(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveInvokestatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetClassMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkMethodAccessible(env, method, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    *ptr = method->impl;
    return method->impl;
}

void* _nvmBcResolveInvokevirtual(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveInvokevirtual: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Check that clazz isn't an interface
    // TODO: Throw something if methodName is <init>
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    // TODO: Throw something if method is abstract
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkMethodAccessible(env, method, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    *ptr = method->lookup;
    return method->lookup;
}

void* _nvmBcResolveInvokeinterface(Env* env, char* owner, char* name, char* desc, Class* caller, void** ptr) {
    TRACE("nvmBcResolveInvokeinterface: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!CLASS_IS_INTERFACE(clazz)) {
        nvmThrowLinkageError(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    // TODO: Throw something if methodName is <init>
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkMethodAccessible(env, method, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    *ptr = method->lookup;
    return method->lookup;
}

void* _nvmBcResolveNative(Env* env, char* owner, char* name, char* desc, char* shortMangledName, char* longMangledName, Class* caller, void** ptr) {
    TRACE("nvmBcResolveNative: owner=%s, name=%s, desc=%s, shortMangledName=%s, longMangledName=%s\n", owner, name, desc, shortMangledName, longMangledName);
    Class* clazz = nvmFindClassInLoader(env, owner, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    void* impl = nvmResolveNativeMethodImpl(env, method, shortMangledName, longMangledName, caller->classLoader, ptr);
    if (!impl) nvmRaiseException(env, nvmExceptionOccurred(env));
    return impl;
}

