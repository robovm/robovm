#include <nullvm.h>
#include <unwind.h>
#include "uthash.h"
#include "utlist.h"

typedef struct ClassFunc {
    char* name;
    Class* (*f)(Env*, ClassLoader*);
} ClassFunc;

typedef struct ClassFuncLookupEntry {
    char* key;      // The class name
    Class* (*f)(Env*, ClassLoader*);
    UT_hash_handle hh;
} ClassFuncLookupEntry;

static ClassFuncLookupEntry* bootclasspathLookup = NULL;
static ClassFuncLookupEntry* classpathLookup = NULL;

extern _Unwind_Reason_Code _nvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

const char* __attribute__ ((weak)) _nvmBcMainClass = NULL;
extern ClassFunc* _nvmBcBootclasspathEntries;
extern ClassFunc* _nvmBcClasspathEntries;
static Class* loadBootclasspathClass(Env*, char*, ClassLoader*);
static Class* loadClasspathClass(Env*, char*, ClassLoader*);
static Options options = {0};

static jboolean initClassLookup(ClassFunc* cf, ClassFuncLookupEntry** lookup) {
    ClassFunc* first = cf;
    jint length = 0;
    while (cf->name != NULL) {
        length++;
        cf++;
    }
    ClassFuncLookupEntry* table = calloc(length, sizeof(ClassFuncLookupEntry));
    if (!table) {
        return FALSE;
    }
    cf = first;
    while (cf->name != NULL) {
        table->key = cf->name;
        table->f = cf->f;
        HASH_ADD_KEYPTR(hh, *lookup, table->key, strlen(table->key), table);
        table++;
        cf++;
    }
    return TRUE;
}

int main(int argc, char* argv[]) {
    if (!initClassLookup(_nvmBcBootclasspathEntries, &bootclasspathLookup)) {
        fprintf(stderr, "Failed to allocate class function table!\n");
        return 1;
    }
    if (!initClassLookup(_nvmBcClasspathEntries, &classpathLookup)) {
        fprintf(stderr, "Failed to allocate class function table!\n");
        return 1;
    }

    options.mainClass = (char*) _nvmBcMainClass;
    options.bootclasspathFunc = loadBootclasspathClass;
    options.classpathFunc = loadClasspathClass;
    if (!nvmInitOptions(argc, argv, &options, FALSE)) {
        fprintf(stderr, "nvmInitOptions(...) failed!\n");
        return 1;
    }
    Env* env = nvmStartup(&options);
    if (!env) {
        fprintf(stderr, "nvmStartup(...) failed!\n");
        return 1;
    }
    jint result = nvmRun(env) ? 0 : 1;
    nvmShutdown(env, result);
    return result;
}

static Class* loadBootclasspathClass(Env* env, char* className, ClassLoader* classLoader) {
    ClassFuncLookupEntry* entry;
    HASH_FIND_STR(bootclasspathLookup, className, entry);
    if (!entry) return NULL;
    return entry->f(env, classLoader);
}

static Class* loadClasspathClass(Env* env, char* className, ClassLoader* classLoader) {
    ClassFuncLookupEntry* entry;
    HASH_FIND_STR(classpathLookup, className, entry);
    if (!entry) return NULL;
    return entry->f(env, classLoader);
}

static Class* findClassInLoader(Env* env, char* className, ClassLoader* classLoader) {
    Class* clazz = nvmFindClassUsingLoader(env, className, classLoader);
    if (clazz) return clazz;
    if (nvmExceptionOccurred(env)->clazz == java_lang_ClassNotFoundException) {
        // If ClassNotFoundException is thrown we have to wrap it in a NoClassDefFoundError
        Object* exception = nvmExceptionClear(env);
        Method* constructor = nvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "<init>", "(Ljava/lang/String;)V");
        if (!constructor) goto error;
        Object* message = nvmNewStringUTF(env, className, -1);
        if (!message) goto error;
        Object* wrappedException = nvmNewObject(env, java_lang_NoClassDefFoundError, constructor, message);
        if (!wrappedException) goto error;
        Class* java_lang_StackTraceElement = nvmFindClassUsingLoader(env, "java/lang/StackTraceElement", classLoader);
        if (!java_lang_StackTraceElement) goto error;
        ObjectArray* stackTrace = nvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);
        if (!stackTrace) goto error;
        Method* setStackTrace = nvmGetInstanceMethod(env, java_lang_Throwable, "setStackTrace", "([Ljava/lang/StackTraceElement;)V");
        if (!setStackTrace) goto error;
        nvmCallVoidInstanceMethod(env, wrappedException, setStackTrace, stackTrace);
        if (nvmExceptionCheck(env)) goto error;
        Method* initCause = nvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "initCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;");
        if (!initCause) goto error;
        nvmCallObjectInstanceMethod(env, wrappedException, initCause, exception);
        if (!nvmExceptionCheck(env)) nvmRaiseException(env, wrappedException);
    }
error:
    nvmRaiseException(env, nvmExceptionOccurred(env));
    return NULL;
}

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

static jboolean checkMethodAccessible(Env* env, char* runtimeClassName, Method* method, Class* caller) {
    if (METHOD_IS_PUBLIC(method)) return TRUE;

    if (METHOD_IS_PRIVATE(method)) {
        if (method->clazz == caller) {
            return TRUE;
        }
    } else if (METHOD_IS_PROTECTED(method)) {
        if (METHOD_IS_STATIC(method)) {
            if (nvmIsSubClass(method->clazz, caller)) return TRUE;
        } else {
            Class* runtimeClass = findClassInLoader(env, runtimeClassName, caller->classLoader);
            if (!runtimeClass) return FALSE;
            if (CLASS_IS_ARRAY(runtimeClass) && !strcmp("clone", method->name) && !strcmp("()Ljava/lang/Object;", method->desc)) {
                return TRUE;
            }
            if (nvmIsSubClass(method->clazz, caller) && nvmIsSubClass(caller, runtimeClass)) {
                return TRUE;
            }
        }
        if (nvmIsSamePackage(method->clazz, caller)) {
            return TRUE;
        }
    } else if (nvmIsSamePackage(method->clazz, caller)) {
        return TRUE;
    }

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

_Unwind_Reason_Code _nvmBcPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {
    _nvmPersonality(version, actions, exception_class, exception_info, context);
}

Class* _nvmBcFindClassInLoader(Env* env, char* className, ClassLoader* classLoader) {
    // TODO: Check accessible
    Class* clazz = findClassInLoader(env, className, classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    return clazz;
}

Class* _nvmBcAllocateClass(Env* env, char* className, char* superclassName, ClassLoader* classLoader, jint access, jint classDataSize, jint instanceDataSize) {
    // TODO: Check superclass accessible
    Class* superclass = NULL;
    if (superclassName) {
        superclass = findClassInLoader(env, superclassName, classLoader);
        if (!superclass) nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    Class* c = nvmAllocateClass(env, className, superclass, classLoader, access, classDataSize, instanceDataSize);
    if (!c) nvmRaiseException(env, nvmExceptionOccurred(env));
    return c;
}

void _nvmBcAddInterface(Env* env, Class* clazz, char* interfaceName) {
    // TODO: Check interface accessible
    Class* interface = findClassInLoader(env, interfaceName, clazz->classLoader);
    if (!interface) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!nvmAddInterface(env, clazz, interface)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

Method* _nvmBcAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl, void* synchronizedImpl, void* lookup) {
    Method* method = nvmAddMethod(env, clazz, name, desc, access, impl, synchronizedImpl, lookup);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    return method;
}

Field* _nvmBcAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset, void* getter, void* setter) {
    Field* field = nvmAddField(env, clazz, name, desc, access, offset, getter, setter);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    return field;
}

void _nvmBcSetClassAttributes(Env* env, Class* clazz, void* attributes) {
    clazz->attributes = attributes;
}

void _nvmBcSetMethodAttributes(Env* env, Method* method, void* attributes) {
    method->attributes = attributes;
}

void _nvmBcSetFieldAttributes(Env* env, Field* field, void* attributes) {
    field->attributes = attributes;
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
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
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
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
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
    Class* clazz = findClassInLoader(env, className, caller->classLoader);
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (CLASS_IS_ABSTRACT(clazz) || CLASS_IS_INTERFACE(clazz)) {
        // TODO: Message
        nvmThrowNew(env, java_lang_InstantiationError, "");
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
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
    Class* arrayClass = findClassInLoader(env, arrayClassName, caller->classLoader);
    if (!checkClassAccessible(env, arrayClass, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ObjectArray* array = nvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, char* arrayClassName, Class* caller) {
    // TODO: Check that caller has access to the base class
    Class* clazz = findClassInLoader(env, arrayClassName, caller->classLoader);
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Array* array = nvmNewMultiArray(env, dims, lengths, clazz);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

void _nvmBcSetObjectArrayElement(Env* env, ObjectArray* array, jint index, Object* value) {
    if (value) {
        Class* componentType = nvmGetComponentType(env, array->object.clazz);
        if (!componentType) nvmRaiseException(env, nvmExceptionOccurred(env));
        jboolean assignable = nvmIsAssignableFrom(env, value->clazz, componentType);
        if (nvmExceptionCheck(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
        if (!assignable) {
            nvmThrowArrayStoreException(env);
            nvmRaiseException(env, nvmExceptionOccurred(env));
        }
    }
    array->values[index] = value;
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
    Class* clazz = findClassInLoader(env, name, caller->classLoader);
    if (!clazz) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (Object*) clazz;
}

void _nvmBcMonitorEnter(Env* env, Object* obj) {
    nvmMonitorEnter(env, obj);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcMonitorExit(Env* env, Object* obj) {
    nvmMonitorExit(env, obj);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

Object* _nvmBcCheckcast(Env* env, Object* o, char* className, Class* caller) {
    // TODO: Check that caller has access to the class
    Class* clazz = findClassInLoader(env, className, caller->classLoader);
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
    Class* clazz = findClassInLoader(env, className, caller->classLoader);
    if (!checkClassAccessible(env, clazz, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    jboolean b = nvmIsInstanceOf(env, o, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (jint) b;
}

void* _nvmBcResolveGetstatic(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    nvmLogTrace(env, "nvmBcResolveGetstatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ClassField* field = nvmGetClassField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkFieldAccessible(env, (Field*) field, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, field->field.clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.getter;
    return field->field.getter;
}

void* _nvmBcResolvePutstatic(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    nvmLogTrace(env, "nvmBcResolvePutstatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    ClassField* field = nvmGetClassField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkFieldAccessible(env, (Field*) field, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (FIELD_IS_FINAL((Field*) field) && field->field.clazz != caller) {
        nvmThrowIllegalAccessErrorField(env, field->field.clazz, name, desc, caller);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    nvmInitialize(env, field->field.clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.setter;
    return field->field.setter;
}

void* _nvmBcResolveGetfield(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    char* runtimeClassName = env->reserved2;
    nvmLogTrace(env, "nvmBcResolveGetfield: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    InstanceField* field = nvmGetInstanceField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkFieldAccessible(env, (Field*) field, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = field->field.getter;
    return field->field.getter;
}

void* _nvmBcResolvePutfield(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    char* runtimeClassName = env->reserved2;
    nvmLogTrace(env, "nvmBcResolvePutfield: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    // TODO: Check that caller has access to the field
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    InstanceField* field = nvmGetInstanceField(env, clazz, name, desc);
    if (!field) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkFieldAccessible(env, (Field*) field, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (FIELD_IS_FINAL((Field*) field) && field->field.clazz != caller) {
        nvmThrowIllegalAccessErrorField(env, field->field.clazz, name, desc, caller);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    *ptr = field->field.setter;
    return field->field.setter;
}

void* _nvmBcResolveInvokespecial(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    char* runtimeClassName = env->reserved2;
    nvmLogTrace(env, "nvmBcResolveInvokespecial: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkMethodAccessible(env, runtimeClassName, method, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    void* impl = method->synchronizedImpl ? method->synchronizedImpl : method->impl;
    *ptr = impl;
    return impl;
}

void* _nvmBcResolveInvokestatic(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    nvmLogTrace(env, "nvmBcResolveInvokestatic: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Throw something if methodName is <clinit>
    Method* method = nvmGetClassMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkMethodAccessible(env, NULL, method, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    void* impl = method->synchronizedImpl ? method->synchronizedImpl : method->impl;
    *ptr = impl;
    return impl;
}

void* _nvmBcResolveInvokevirtual(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    char* runtimeClassName = env->reserved2;
    nvmLogTrace(env, "nvmBcResolveInvokevirtual: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    // TODO: Check that clazz isn't an interface
    // TODO: Throw something if methodName is <init>
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    // TODO: Throw something if method is abstract
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    checkMethodAccessible(env, runtimeClassName, method, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = method->lookup;
    return method->lookup;
}

void* _nvmBcResolveInvokeinterface(Env* env, char* owner, char* name, char* desc) {
    void** ptr = env->reserved0;
    Class* caller = env->reserved1;
    nvmLogTrace(env, "nvmBcResolveInvokeinterface: owner=%s, name=%s, desc=%s\n", owner, name, desc);
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    checkClassAccessible(env, clazz, caller);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!CLASS_IS_INTERFACE(clazz)) {
        nvmThrowIncompatibleClassChangeError(env, "");
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    Method* method = nvmGetInstanceMethod(env, clazz, name, desc);
    // TODO: Throw something if methodName is <init>
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
//    if (!checkMethodAccessible(env, clazz, method, caller)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    *ptr = method->lookup;
    return method->lookup;
}

void* _nvmBcResolveNative(Env* env, char* owner, char* name, char* desc, char* shortMangledName, char* longMangledName, Class* caller, void** ptr) {
    nvmLogTrace(env, "nvmBcResolveNative: owner=%s, name=%s, desc=%s, shortMangledName=%s, longMangledName=%s\n", owner, name, desc, shortMangledName, longMangledName);
    Class* clazz = findClassInLoader(env, owner, caller->classLoader);
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    void* impl = nvmResolveNativeMethodImpl(env, method, shortMangledName, longMangledName, caller->classLoader, ptr);
    if (!impl) nvmRaiseException(env, nvmExceptionOccurred(env));
    return impl;
}

