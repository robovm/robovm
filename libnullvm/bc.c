#include <nullvm.h>

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


extern jbyte _nvmBcGetStatic8(GetStatic* desc, Env* env);
extern jshort _nvmBcGetStatic16(GetStatic* desc, Env* env);
extern jint _nvmBcGetStatic32(GetStatic* desc, Env* env);
extern jlong _nvmBcGetStatic64(GetStatic* desc, Env* env);
extern jfloat _nvmBcGetStaticFloat(GetStatic* desc, Env* env);
extern jdouble _nvmBcGetStaticDouble(GetStatic* desc, Env* env);
extern void _nvmBcPutStatic8(PutStatic* desc, Env* env, jbyte value);
extern void _nvmBcPutStatic16(PutStatic* desc, Env* env, jshort value);
extern void _nvmBcPutStatic32(PutStatic* desc, Env* env, jint value);
extern void _nvmBcPutStatic64(PutStatic* desc, Env* env, jlong value);
extern void _nvmBcPutStaticFloat(PutStatic* desc, Env* env, jfloat value);
extern void _nvmBcPutStaticDouble(PutStatic* desc, Env* env, jdouble value);

extern void _nvmEmptyFunction(void);

void _nvmBcAllocateClass(Env* env, char* className, char* superclassName, jint access, jint classDataSize, jint instanceDataSize);
void _nvmBcAddInterface(Env* env, Class* clazz, char* interfaceName);
void _nvmBcAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset);
void _nvmBcAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl);
void _nvmBcRegisterClass(Env* env, Class* clazz);

Class* _nvmBcFindClass(Env* env, char* name, Class* caller);
void _nvmBcThrow(Env* env, Object* throwable);
void _nvmBcThrowNullPointerException(Env* env);
void _nvmBcThrowArrayIndexOutOfBoundsException(Env* env, jint index);

void* _nvmBcGetCheckcastFunction(Env* env, char* className, Class* caller, void** functionPtr);
void* _nvmBcGetInstanceofFunction(Env* env, char* className, Class* caller, void** functionPtr);
void* _nvmBcGetAllocateObjectFunction(Env* env, char* className, Class* caller, void** functionPtr);

Object* _nvmBcNewString(Env* env, jchar* s);
Object* _nvmBcNewStringUTF(Env* env, char* s);
Object* _nvmBcNewStringAscii(Env* env, char* s);

BooleanArray* _nvmBcNewBooleanArray(Env* env, jint length);
ByteArray* _nvmBcNewByteArray(Env* env, jint length);
CharArray* _nvmBcNewCharArray(Env* env, jint length);
ShortArray* _nvmBcNewShortArray(Env* env, jint length);
IntArray* _nvmBcNewIntArray(Env* env, jint length);
LongArray* _nvmBcNewLongArray(Env* env, jint length);
FloatArray* _nvmBcNewFloatArray(Env* env, jint length);
DoubleArray* _nvmBcNewDoubleArray(Env* env, jint length);
ObjectArray* _nvmBcNewObjectArray(Env* env, jint length, char* arrayType, Class* caller);
Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, char* arrayType, Class* caller);

void _nvmBcResolveGetStatic(GetStatic* desc, Env* env);
void _nvmBcResolvePutStatic(PutStatic* desc, Env* env);

void* _nvmBcGetClassFieldGetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
void* _nvmBcGetClassFieldSetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
void* _nvmBcGetInstanceFieldGetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
void* _nvmBcGetInstanceFieldSetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);

void* _nvmBcGetInvokeStaticFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr);
void* _nvmBcGetInvokeVirtualFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr);
void* _nvmBcGetInvokeInterfaceFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr);
void* _nvmBcGetInvokeSpecialFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr);

void* _nvmBcGetNativeMethod(Env* env, char* shortMangledName, char* longMangledName, void** functionPtr);


void _nvmBcAllocateClass(Env* env, char* className, char* superclassName, jint access, jint classDataSize, jint instanceDataSize) {
    Class* superclass = superclassName ? nvmFindClass(env, superclassName) : NULL;
    Class* c = nvmAllocateClass(env, className, superclass, access, classDataSize, instanceDataSize);
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

void _nvmBcThrow(Env* env, Object* throwable) {
    nvmExceptionClear(env);
    nvmRaiseException(env, throwable);
}

void _nvmBcThrowNullPointerException(Env* env) {
    nvmThrowNullPointerException(env);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    nvmThrowArrayIndexOutOfBoundsException(env, index);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

Object* _nvmBcNewStringAscii(Env* env, char* s) {
    Object* o = nvmNewStringAscii(env, s);
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
    Class* clazz = _nvmBcFindClass(env, arrayClassName, caller);
    ObjectArray* array = nvmNewObjectArray(env, length, clazz);
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

void* _nvmBcGetCheckcastFunction(Env* env, char* className, Class* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    *functionPtr = clazz->checkcast;
    return clazz->checkcast;
}

void* _nvmBcGetInstanceofFunction(Env* env, char* className, Class* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    *functionPtr = clazz->instanceof;
    return clazz->instanceof;
}

void* _nvmBcGetAllocateObjectFunction(Env* env, char* className, Class* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    *functionPtr = clazz->newInstance;
    return clazz->newInstance;
}

void _nvmBcResolveGetPutStaticCommon(GetPutStaticCommon* common, Env* env) {
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    Field* field = nvmGetClassField(env, clazz, common->name, common->desc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    common->address = (jbyte*) clazz->data + field->offset;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveGetStatic(GetStatic* g, Env* env) {
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
#if __SIZEOF_POINTER__ == 64
        getter = _nvmBcGetStatic64;
#else
        getter = _nvmBcGetStatic32;
#endif
        break;
    }
    g->address = g->common->address;
    g->function = getter;
}

void _nvmBcResolvePutStatic(PutStatic* p, Env* env) {
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
#if __SIZEOF_POINTER__ == 64
        setter = _nvmBcPutStatic64;
#else
        setter = _nvmBcPutStatic32;
#endif
        break;
    }
    p->address = p->common->address;
    p->function = setter;
}

void *_nvmBcGetClassFieldGetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    Field* field = nvmGetClassField(env, clazz, fieldName, fieldDesc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the field
    *functionPtr = field->getter;
    return field->getter;
}

void *_nvmBcGetClassFieldSetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    Field* field = nvmGetClassField(env, clazz, fieldName, fieldDesc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the field
    if (field->access & ACC_FINAL && caller != clazz) {
        nvmThrowIllegalAccessError(env);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    *functionPtr = field->setter;
    return field->setter;
}

void *_nvmBcGetInstanceFieldGetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    Field* field = nvmGetInstanceField(env, clazz, fieldName, fieldDesc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the field
    *functionPtr = field->getter;
    return field->getter;
}

void *_nvmBcGetInstanceFieldSetter(Env* env, char* className, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    Field* field = nvmGetInstanceField(env, clazz, fieldName, fieldDesc);
    if (!field) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the field
    if (field->access & ACC_FINAL && caller != clazz) {
        nvmThrowIllegalAccessError(env);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    *functionPtr = field->setter;
    return field->setter;
}



void* _nvmBcGetInvokeStaticFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetMethod(env, clazz, methodName, methodDesc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the method
    if (!(method->access & ACC_STATIC)) {
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, methodName, methodDesc);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* _nvmBcGetInvokeVirtualFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetMethod(env, clazz, methodName, methodDesc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the method
    if (method->access & ACC_STATIC) {
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, methodName, methodDesc);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* _nvmBcGetInvokeInterfaceFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    // TODO: Implement me properly!
    LOG("nvmGetInvokeInterfaceFunction not implemented!");
    nvmThrowNoSuchMethodError(env, methodName);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
    return NULL;
}

void* _nvmBcGetInvokeSpecialFunction(Env* env, char* className, char* methodName, char* methodDesc, void* caller, void** functionPtr) {
    Class* clazz = _nvmBcFindClass(env, className, caller);
    // TODO: Throw something if methodName is <clinit>
    // TODO: Check caller has ACC_SUPER access?
    Method* method = nvmGetMethod(env, clazz, methodName, methodDesc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Check that caller has access to the method
    if (method->access & ACC_STATIC) {
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, methodName, methodDesc);
        _nvmBcThrow(env, nvmExceptionOccurred(env));
    }
    *functionPtr = method->wrapper;
    return method->wrapper;
}

void* _nvmBcGetNativeMethod(Env* env, char* shortMangledName, char* longMangledName, void** functionPtr) {
    void* f = nvmGetNativeMethod(env, shortMangledName, longMangledName);
    if (!f) _nvmBcThrow(env, nvmExceptionOccurred(env));
    *functionPtr = f;
    return f;
}

void _nvmBcResolveMethodForInvokeStaticCommon(InvokeStaticCommon* common, Env* env) {
    LOG("nvmBcResolveMethodForInvokeStaticCommon: %s/%s%s\n", common->owner, common->name, common->desc);
    Class* clazz = nvmFindClass(env, common->owner);
    if (!clazz) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetClassMethod(env, clazz, common->name, common->desc);
    if (!method) _nvmBcThrow(env, nvmExceptionOccurred(env));
    // TODO: Wrap synchronized method?
    common->function = method->impl;
    common->resolve = _nvmEmptyFunction;
}

void _nvmBcResolveMethodForInvokeStatic(InvokeStatic* i, Env* env) {
    LOG("nvmBcResolveMethodForInvokeStatic: %s/%s%s\n", i->common->owner, i->common->name, i->common->desc);
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = i->common->function;
}

void _nvmBcResolveMethodForInvokeVirtualCommon(InvokeVirtualCommon* common, Env* env) {
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
    // TODO: Check that *i->caller has access to the class and method being called
    i->vtableIndex = i->common->vtableIndex;
    i->function = i->common->function;
}

void _nvmBcResolveMethodForInvokeSpecialCommon(InvokeSpecialCommon* common, Env* env) {
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
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = i->common->function;
}

void _nvmBcResolveMethodForInvokeInterfaceCommon(InvokeInterfaceCommon* common, Env* env) {
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
    // TODO: Check that *i->caller has access to the class and method being called
    i->function = i->common->function;
}

