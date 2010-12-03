#include <nullvm.h>

typedef struct ClassResCommon {
    void* resolve;
    char* name;
    Class* clazz;
} ClassResCommon;

typedef struct NewRes {
    void* function;
    ClassResCommon* common;
    Class** caller;
} NewRes;

typedef struct CheckcastRes {
    void* function;
    ClassResCommon* common;
    Class** caller;
} CheckcastRes;

typedef struct InstanceofRes {
    void* function;
    ClassResCommon* common;
    Class** caller;
} InstanceofRes;

typedef struct LdcClassRes {
    void* function;
    ClassResCommon* common;
    Class** caller;
} LdcClassRes;

typedef struct GetPutStaticCommon {
    void* resolve;
    void* address;
    char* owner;
    char* name;
    char* desc;
} GetPutStaticCommon;

typedef struct GetStatic {
    void* function;
    GetPutStaticCommon* common;
    Class** caller;
    void* address;
} GetStatic;

typedef struct PutStatic {
    void* function;
    GetPutStaticCommon* common;
    Class** caller;
    void* address;
} PutStatic;

typedef struct GetPutFieldCommon {
    void* resolve;
    jint offset;
    char* owner;
    char* name;
    char* desc;
} GetPutFieldCommon;

typedef struct GetField {
    void* function;
    GetPutFieldCommon* common;
    Class** caller;
    jint offset;
} GetField;

typedef struct PutField {
    void* function;
    GetPutFieldCommon* common;
    Class** caller;
    jint offset;
} PutField;

// Shared by all call sites
typedef struct InvokeStaticCommon {
    void* resolve;
    char* owner;
    char* name;
    char* desc;
    void* function;
} InvokeStaticCommon;

// Class private (a single instance is shared by all call sites in a class)
typedef struct InvokeStatic {
    void* function;
    InvokeStaticCommon* common;
    Class** caller;
} InvokeStatic;

// Shared by all call sites
typedef struct InvokeVirtualCommon {
    void* resolve;
    char* owner;
    char* name;
    char* desc;
    void* function;
    jint vtableIndex;
} InvokeVirtualCommon;

// Class private (a single instance is shared by all call sites in a class)
typedef struct InvokeVirtual {
    void* function;
    InvokeVirtualCommon* common;
    Class** caller;
    jint vtableIndex;
} InvokeVirtual;

// Shared by all call sites
typedef struct InvokeSpecialCommon {
    void* resolve;
    char* owner;
    char* name;
    char* desc;
    void* function;
} InvokeSpecialCommon;

// Class private (a single instance is shared by all call sites in a class)
typedef struct InvokeSpecial {
    void* function;
    InvokeSpecialCommon* common;
    Class** caller;
} InvokeSpecial;

// Shared by all call sites
typedef struct InvokeInterfaceCommon {
    void* resolve;
    char* owner;
    char* name;
    char* desc;
    void* function;
} InvokeInterfaceCommon;

// Class private (a single instance is shared by all call sites in a class)
typedef struct InvokeInterface {
    void* function;
    InvokeInterfaceCommon* common;
    Class** caller;
} InvokeInterface;

typedef struct InvokeNative {
    void* resolve;
    void* function;
    char* shortMangledName;
    char* longMangledName;
} InvokeNative;

extern jbyte _nvmBcGetStatic8(GetStatic* g, Env* env);
extern jshort _nvmBcGetStatic16(GetStatic* g, Env* env);
extern jint _nvmBcGetStatic32(GetStatic* g, Env* env);
extern jlong _nvmBcGetStatic64(GetStatic* g, Env* env);
extern jfloat _nvmBcGetStaticFloat(GetStatic* g, Env* env);
extern jdouble _nvmBcGetStaticDouble(GetStatic* g, Env* env);
extern void _nvmBcPutStatic8(PutStatic* p, Env* env, jbyte value);
extern void _nvmBcPutStatic16(PutStatic* p, Env* env, jshort value);
extern void _nvmBcPutStatic32(PutStatic* p, Env* env, jint value);
extern void _nvmBcPutStatic64(PutStatic* p, Env* env, jlong value);
extern void _nvmBcPutStaticFloat(PutStatic* p, Env* env, jfloat value);
extern void _nvmBcPutStaticDouble(PutStatic* p, Env* env, jdouble value);

extern jbyte _nvmBcGetField8(GetField* g, Env* env);
extern jshort _nvmBcGetField16(GetField* g, Env* env);
extern jint _nvmBcGetField32(GetField* g, Env* env);
extern jlong _nvmBcGetField64(GetField* g, Env* env);
extern jfloat _nvmBcGetFieldFloat(GetField* g, Env* env);
extern jdouble _nvmBcGetFieldDouble(GetField* g, Env* env);
extern void _nvmBcPutField8(PutField* p, Env* env, jbyte value);
extern void _nvmBcPutField16(PutField* p, Env* env, jshort value);
extern void _nvmBcPutField32(PutField* p, Env* env, jint value);
extern void _nvmBcPutField64(PutField* p, Env* env, jlong value);
extern void _nvmBcPutFieldFloat(PutField* p, Env* env, jfloat value);
extern void _nvmBcPutFieldDouble(PutField* p, Env* env, jdouble value);

extern void _nvmEmptyFunction(void);
extern void _nvmBcInvokeVirtual0(void);
extern void _nvmBcInvokeInterface0(void);


void _nvmBcThrow(Env* env, Object* throwable) {
    nvmRaiseException(env, throwable);
}

void _nvmBcAllocateClass(Env* env, char* className, char* superclassName, jint access, jint classDataSize, jint instanceDataSize) {
    Class* superclass = NULL;
    if (superclassName) {
        superclass = nvmFindClass(env, superclassName);
        if (!superclass) _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    Class* c = nvmAllocateClass(env, className, superclass, access, classDataSize, instanceDataSize);
    if (!c) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcAllocateSystemClass(Env* env, char* className, char* superclassName, jint access, jint classDataSize, jint instanceDataSize) {
    Class* superclass = NULL;
    if (superclassName) {
        superclass = nvmFindClass(env, superclassName);
        if (!superclass) _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    Class* c = nvmAllocateSystemClass(env, className, superclass, access, classDataSize, instanceDataSize);
    if (!c) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcAddInterface(Env* env, Class* clazz, char* interfaceName) {
    Class* interface = nvmFindClass(env, interfaceName);
    if (!nvmAddInterface(env, clazz, interface)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset) {
    if (!nvmAddField(env, clazz, name, desc, access, offset)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl) {
    if (!nvmAddMethod(env, clazz, name, desc, access, impl)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcRegisterClass(Env* env, Class* clazz) {
    if (!nvmRegisterClass(env, clazz)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

Class* _nvmBcFindClass(Env* env, char* name, Class* caller) {
    // TODO: Access checks
    Class* class = nvmFindClass(env, name);
    if (!class) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return class;
}

Object* _nvmBcExceptionClear(Env* env) {
    return nvmExceptionClear(env);
}

jint _nvmBcExceptionMatch(Env* env, Object* throwable, Class* clazz) {
    Class* c = throwable->clazz;
    while (c && c != clazz) {
        c = c->superclass;
    }
    return c == clazz ? 1 : 0;
}

void _nvmBcThrowNullPointerException(Env* env) {
    nvmThrowNullPointerException(env);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowIfExceptionOccurred(Env* env) {
    if (nvmExceptionOccurred(env)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    nvmThrowArrayIndexOutOfBoundsException(env, index);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

Object* _nvmBcNewStringAscii(Env* env, char* s) {
    Object* o = nvmNewStringAscii(env, s, -1);
    if (!o) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return o;
}

BooleanArray* _nvmBcNewBooleanArray(Env* env, jint length) {
    BooleanArray* array = nvmNewBooleanArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

ByteArray* _nvmBcNewByteArray(Env* env, jint length) {
    ByteArray* array = nvmNewByteArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

CharArray* _nvmBcNewCharArray(Env* env, jint length) {
    CharArray* array = nvmNewCharArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

ShortArray* _nvmBcNewShortArray(Env* env, jint length) {
    ShortArray* array = nvmNewShortArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

IntArray* _nvmBcNewIntArray(Env* env, jint length) {
    IntArray* array = nvmNewIntArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

LongArray* _nvmBcNewLongArray(Env* env, jint length) {
    LongArray* array = nvmNewLongArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

FloatArray* _nvmBcNewFloatArray(Env* env, jint length) {
    FloatArray* array = nvmNewFloatArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

DoubleArray* _nvmBcNewDoubleArray(Env* env, jint length) {
    DoubleArray* array = nvmNewDoubleArray(env, length);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

ObjectArray* _nvmBcNewObjectArray(Env* env, jint length, char* arrayClassName, Class* caller) {
    // TODO: Check that caller has access to the base class
    // arrayClassName must be the name of the array class to create an instance of (e.g. [Ljava/lang/Object;)
    Class* arrayClass = _nvmBcFindClass(env, arrayClassName, caller);
    ObjectArray* array = nvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, char* arrayClassName, Class* caller) {
    // TODO: Check that caller has access to the base class
    Class* clazz = _nvmBcFindClass(env, arrayClassName, caller);
    Array* array = nvmNewMultiArray(env, dims, lengths, clazz);
    if (!array) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return array;
}

Object* _nvmBcLdcClass(Env* env, char* name, Class* caller) {
    return (Object*) _nvmBcFindClass(env, name, caller);
}

Object* _nvmBcNew(NewRes* n, Env* env) {
    Object* obj = nvmAllocateObject(env, n->common->clazz);
    if (!obj) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return obj;
}

void _nvmBcCheckcast(CheckcastRes* n, Env* env, Object* o) {
    if (!o) return;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, n->common->clazz);
    if (nvmExceptionOccurred(env)) _nvmBcThrow(env, nvmExceptionOccurred(env));
    if (!b) {
        nvmThrowClassCastException(env, n->common->clazz, o->clazz);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
}

jint _nvmBcInstanceof(InstanceofRes* n, Env* env, Object* o) {
    jboolean b = nvmIsInstanceOf(env, o, n->common->clazz);
    if (nvmExceptionOccurred(env)) _nvmBcThrow(env, nvmExceptionOccurred(env));
    return (jint) b;
}

void _nvmBcResolveClassResCommon(ClassResCommon* common, Env* env) {
    LOG("nvmBcResolveClassResCommon: class=%s\n", common->name);
    Class* clazz = nvmFindClass(env, common->name);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    common->clazz = clazz;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveClassForNew(NewRes* r, Env* env) {
    LOG("nvmBcResolveClassForNew: class=%s, caller=%s\n", r->common->name, (*r->caller)->name);
    // TODO: Check that caller has access to the class
    r->function = _nvmBcNew;
}

void _nvmBcResolveClassForCheckcast(CheckcastRes* r, Env* env) {
    LOG("nvmBcResolveClassForCheckcast: class=%s, caller=%s\n", r->common->name, (*r->caller)->name);
    // TODO: Check that caller has access to the class
    // TODO: Use different optimized functions if r->common->clazz is an interface or a class
    r->function = _nvmBcCheckcast;
}

void _nvmBcResolveClassForInstanceof(InstanceofRes* r, Env* env) {
    LOG("nvmBcResolveClassForInstanceof: class=%s, caller=%s\n", r->common->name, (*r->caller)->name);
    // TODO: Check that caller has access to the class
    // TODO: Use different optimized functions if r->common->clazz is an interface or a class
    r->function = _nvmBcInstanceof;
}

void _nvmBcResolveFieldForGetPutStaticCommon(GetPutStaticCommon* common, Env* env) {
    LOG("nvmBcResolveFieldForGetPutStaticCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) _nvmBcThrow(env, nvmExceptionOccurred(env));
    ClassField* field = nvmGetClassField(env, clazz, common->name, common->desc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    common->address = field->address;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveFieldForGetStatic(GetStatic* g, Env* env) {
    LOG("nvmBcResolveFieldForGetStatic: owner=%s, name=%s, desc=%s\n", g->common->owner, g->common->name, g->common->desc);
    // TODO: Check that caller has access to the field
    void* getter = NULL;
    switch (g->common->desc[0]) {
    case 'Z':
    case 'B':
        getter = _nvmBcGetStatic8;
        break;
    case 'C':
    case 'S':
        getter = _nvmBcGetStatic16;
        break;
    case 'I':
        getter = _nvmBcGetStatic32;
        break;
    case 'J':
        getter = _nvmBcGetStatic64;
        break;
    case 'F':
        getter = _nvmBcGetStaticFloat;
        break;
    case 'D':
        getter = _nvmBcGetStaticDouble;
        break;
    default:
//#if __SIZEOF_POINTER__ == 64
        getter = _nvmBcGetStatic64;
//#else
//        getter = _nvmBcGetStatic32;
//#endif
        break;
    }
    g->address = g->common->address;
    g->function = getter;
}

void _nvmBcResolveFieldForPutStatic(PutStatic* p, Env* env) {
    LOG("nvmBcResolveFieldForPutStatic: owner=%s, name=%s, desc=%s\n", p->common->owner, p->common->name, p->common->desc);
    // TODO: Check that caller has access to the field
    void* setter = NULL;
    switch (p->common->desc[0]) {
    case 'Z':
    case 'B':
        setter = _nvmBcPutStatic8;
        break;
    case 'C':
    case 'S':
        setter = _nvmBcPutStatic16;
        break;
    case 'I':
        setter = _nvmBcPutStatic32;
        break;
    case 'J':
        setter = _nvmBcPutStatic64;
        break;
    case 'F':
        setter = _nvmBcPutStaticFloat;
        break;
    case 'D':
        setter = _nvmBcPutStaticDouble;
        break;
    default:
//#if __SIZEOF_POINTER__ == 64
        setter = _nvmBcPutStatic64;
//#else
//        setter = _nvmBcPutStatic32;
//#endif
        break;
    }
    p->address = p->common->address;
    p->function = setter;
}

void _nvmBcResolveFieldForGetPutFieldCommon(GetPutFieldCommon* common, Env* env) {
    LOG("nvmBcResolveFieldForGetPutFieldCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    InstanceField* field = nvmGetInstanceField(env, clazz, common->name, common->desc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    common->offset = field->offset;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveFieldForGetField(GetField* g, Env* env) {
    LOG("nvmBcResolveFieldForGetField: owner=%s, name=%s, desc=%s\n", g->common->owner, g->common->name, g->common->desc);
    // TODO: Check that caller has access to the field
    void* getter = NULL;
    switch (g->common->desc[0]) {
    case 'Z':
    case 'B':
        getter = _nvmBcGetField8;
        break;
    case 'C':
    case 'S':
        getter = _nvmBcGetField16;
        break;
    case 'I':
        getter = _nvmBcGetField32;
        break;
    case 'J':
        getter = _nvmBcGetField64;
        break;
    case 'F':
        getter = _nvmBcGetFieldFloat;
        break;
    case 'D':
        getter = _nvmBcGetFieldDouble;
        break;
    default:
//#if __SIZEOF_POINTER__ == 64
        getter = _nvmBcGetField64;
//#else
//        getter = _nvmBcGetField32;
//#endif
        break;
    }
    g->offset = g->common->offset;
    g->function = getter;
}

void _nvmBcResolveFieldForPutField(PutField* p, Env* env) {
    LOG("nvmBcResolveFieldForPutField: owner=%s, name=%s, desc=%s\n", p->common->owner, p->common->name, p->common->desc);
    // TODO: Check that caller has access to the field
    void* setter = NULL;
    switch (p->common->desc[0]) {
    case 'Z':
    case 'B':
        setter = _nvmBcPutField8;
        break;
    case 'C':
    case 'S':
        setter = _nvmBcPutField16;
        break;
    case 'I':
        setter = _nvmBcPutField32;
        break;
    case 'J':
        setter = _nvmBcPutField64;
        break;
    case 'F':
        setter = _nvmBcPutFieldFloat;
        break;
    case 'D':
        setter = _nvmBcPutFieldDouble;
        break;
    default:
//#if __SIZEOF_POINTER__ == 64
        setter = _nvmBcPutField64;
//#else
//        setter = _nvmBcPutField32;
//#endif
        break;
    }
    p->offset = p->common->offset;
    p->function = setter;
}

void _nvmBcResolveNativeMethod(InvokeNative* i, Env* env) {
    void* f = nvmGetNativeMethod(env, i->shortMangledName, i->longMangledName);
    if (!f) _nvmBcThrow(env, nvmExceptionOccurred(env));
    i->function = f;
    i->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeStaticCommon(InvokeStaticCommon* common, Env* env) {
    LOG("nvmBcResolveMethodForInvokeStaticCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetClassMethod(env, clazz, common->name, common->desc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    common->function = method->impl;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeStatic(InvokeStatic* i, Env* env) {
    LOG("nvmBcResolveMethodForInvokeStatic: owner=%s, name=%s, desc=%s, caller=%s\n", i->common->owner, i->common->name, i->common->desc, (*i->caller)->name);
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = i->common->function;
}

void* _nvmBcGetMethodImplForInvokeVirtual(InvokeVirtual* i, Env* env, Object* obj) {
    // TODO: Remove once we have fast virtual method dispatch in place
    return nvmGetInstanceMethod(env, obj->clazz, i->common->name, i->common->desc)->impl;
}

void* _nvmBcGetMethodImplForInvokeInterface(InvokeInterface* i, Env* env, Object* obj) {
    // TODO: Remove once we have fast interface method dispatch in place
    return nvmGetInstanceMethod(env, obj->clazz, i->common->name, i->common->desc)->impl;
}

void _nvmBcResolveMethodForInvokeVirtualCommon(InvokeVirtualCommon* common, Env* env) {
    LOG("nvmBcResolveMethodForInvokeVirtualCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetInstanceMethod(env, clazz, common->name, common->desc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    // TODO: Point i->common->function to function which resolves virtual methods. 
    // TODO: Point i->vtableIndex to vtable index of virtual method.
    common->function = method->impl;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeVirtual(InvokeVirtual* i, Env* env) {
    LOG("nvmBcResolveMethodForInvokeVirtual: owner=%s, name=%s, desc=%s, caller=%s\n", i->common->owner, i->common->name, i->common->desc, (*i->caller)->name);
    // TODO: Check that *i->caller has access to the class and method being called
    i->vtableIndex = i->common->vtableIndex;
    i->function = _nvmBcInvokeVirtual0;
}

void _nvmBcResolveMethodForInvokeSpecialCommon(InvokeSpecialCommon* common, Env* env) {
    LOG("nvmBcResolveMethodForInvokeSpecialCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetInstanceMethod(env, clazz, common->name, common->desc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    common->function = method->impl;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeSpecial(InvokeSpecial* i, Env* env) {
    LOG("nvmBcResolveMethodForInvokeSpecial: owner=%s, name=%s, desc=%s, caller=%s\n", i->common->owner, i->common->name, i->common->desc, (*i->caller)->name);
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = i->common->function;
}

void _nvmBcResolveMethodForInvokeInterfaceCommon(InvokeInterfaceCommon* common, Env* env) {
    LOG("nvmBcResolveMethodForInvokeInterfaceCommon: owner=%s, name=%s, desc=%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetInstanceMethod(env, clazz, common->name, common->desc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    common->function = method->impl;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeInterface(InvokeInterface* i, Env* env) {
    LOG("nvmBcResolveMethodForInvokeInterface: owner=%s, name=%s, desc=%s, caller=%s\n", i->common->owner, i->common->name, i->common->desc, (*i->caller)->name);
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = _nvmBcInvokeInterface0;
}

void _nvmBcMonitorEnter(Env* env, Object* obj) {
    if (nvmMonitorEnter(env, obj)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcMonitorExit(Env* env, Object* obj) {
    if (nvmMonitorExit(env, obj)) _nvmBcThrow(env, nvmExceptionOccurred(env));
}

