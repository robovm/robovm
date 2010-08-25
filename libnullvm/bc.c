#include <nullvm.h>

extern void _nvmBcAllocateClass(Env* env, char* className, char* superclassName, jint access, jint classDataSize, jint instanceDataSize);
extern void _nvmBcAddInterface(Env* env, Class* clazz, char* interfaceName);
extern void _nvmBcAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset);
extern void _nvmBcAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl);
extern void _nvmBcRegisterClass(Env* env, Class* clazz);

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

