#include <nullvm.h>
#include <unwind.h>
#include "uthash.h"
#include "utlist.h"
#include "MurmurHash3.h"
#include "classinfo.h"

#define ALLOC_NATIVE_FRAMES_SIZE 8

extern _Unwind_Reason_Code _nvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

const char* __attribute__ ((weak)) _nvmBcMainClass = NULL;
extern char** _nvmBcBootclasspath;
extern char** _nvmBcClasspath;
extern void* _nvmBcBootClassesHash;
extern void* _nvmBcClassesHash;
static Class* loadBootClass(Env*, const char*, ClassLoader*);
static Class* loadUserClass(Env*, const char*, ClassLoader*);
static void classInitialized(Env*, Class*);
static void loadInterfaces(Env*, Class*);
static void loadFields(Env*, Class*);
static void loadMethods(Env*, Class*);
static Class* createClass(Env*, ClassInfoHeader*, ClassLoader*);
static Options options = {0};
static VM* vm = NULL;
static ClassLoader* systemClassLoader = NULL;

int main(int argc, char* argv[]) {
    options.mainClass = (char*) _nvmBcMainClass;
    options.rawBootclasspath = _nvmBcBootclasspath;
    options.rawClasspath = _nvmBcClasspath;
    options.loadBootClass = loadBootClass;
    options.loadUserClass = loadUserClass;
    options.classInitialized = classInitialized;
    options.loadInterfaces = loadInterfaces;
    options.loadFields = loadFields;
    options.loadMethods = loadMethods;
    if (!nvmInitOptions(argc, argv, &options, FALSE)) {
        fprintf(stderr, "nvmInitOptions(...) failed!\n");
        return 1;
    }
    Env* env = nvmStartup(&options);
    if (!env) {
        fprintf(stderr, "nvmStartup(...) failed!\n");
        return 1;
    }
    vm = env->vm;
    jint result = nvmRun(env) ? 0 : 1;
    nvmShutdown(env, result);
    return result;
}

static ClassLoader* getSystemClassLoader(Env* env) {
    if (systemClassLoader) return systemClassLoader;
    Class* holder = nvmFindClass(env, "java/lang/ClassLoader$SystemClassLoaderHolder");
    if (!holder) return NULL;
    ClassField* field = nvmGetClassField(env, holder, "loader", "Ljava/lang/ClassLoader;");
    if (!field) return NULL;
    return (ClassLoader*) nvmGetObjectClassFieldValue(env, holder, field);
}

static ClassInfoHeader* lookupClassInfo(Env* env, const char* className, void* hash) {
    jint h = 0;
    MurmurHash3_x86_32(className, strlen(className) + 1, 0x1ce79e5c, &h);
    jint size = ((jshort*) hash)[0];
    h &= size - 1;
    jint start = ((jshort*) hash)[h + 1];
    jint end = ((jshort*) hash)[h + 1 + 1];
#ifdef _LP64
    void** base  = hash + (size << 1) + 4 + 4;
#else
    void** base  = hash + (size << 1) + 4;
#endif
    jint i;
    for (i = start; i < end; i++) {
        ClassInfoHeader* header = base[i];
        if (header && !strcmp(header->className, className)) {
            return header;
        }
    }
    return NULL;
}

static Class* loadClass(Env* env, const char* className, ClassLoader* classLoader, void* hash) {
    ClassInfoHeader* header = lookupClassInfo(env, className, hash);
    if (!header) return NULL;
    if (header->flags & CI_ERROR) {
        ClassInfoError* error = (ClassInfoError*) header;
        switch (error->errorType) {
        case CI_ERROR_TYPE_NO_CLASS_DEF_FOUND:
            nvmThrowNoClassDefFoundError(env, error->errorMessage);
            break;
        case CI_ERROR_TYPE_ILLEGAL_ACCESS:
            nvmThrowIllegalAccessError(env, error->errorMessage);
            break;
        case CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE:
            nvmThrowIncompatibleClassChangeError(env, error->errorMessage);
            break;
        }
        return NULL;
    }
    if (header->clazz) return header->clazz;
    return createClass(env, header, classLoader);
}

static Class* loadBootClass(Env* env, const char* className, ClassLoader* classLoader) {
    return loadClass(env, className, classLoader, _nvmBcBootClassesHash);
}

static Class* loadUserClass(Env* env, const char* className, ClassLoader* classLoader) {
    return loadClass(env, className, classLoader, _nvmBcClassesHash);
}

static Object* wrapClassNotFoundException(Env* env, const char* className) {
    if (nvmExceptionOccurred(env)->clazz == java_lang_ClassNotFoundException) {
        // If ClassNotFoundException is thrown we have to wrap it in a NoClassDefFoundError
        Object* exception = nvmExceptionClear(env);
        Method* constructor = nvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "<init>", "(Ljava/lang/String;)V");
        if (!constructor) goto error;
        Object* message = nvmNewStringUTF(env, className, -1);
        if (!message) goto error;
        Object* wrappedException = nvmNewObject(env, java_lang_NoClassDefFoundError, constructor, message);
        if (!wrappedException) goto error;
        Class* java_lang_StackTraceElement = nvmFindClassUsingLoader(env, "java/lang/StackTraceElement", NULL);
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
        if (!nvmExceptionCheck(env)) nvmThrow(env, wrappedException);
    }
error:
    return nvmExceptionOccurred(env);
}

static Class* findClassInLoader(Env* env, const char* className, ClassLoader* classLoader) {
    Class* clazz = nvmFindClassUsingLoader(env, className, classLoader);
    if (clazz) return clazz;
    nvmRaiseException(env, wrapClassNotFoundException(env, className));
    return NULL;
}

typedef struct {
    Class* clazz;
    ClassLoader* classLoader;
} CreateClassData;

static jboolean createClassCallback(Env* env, const char* className, const char* superclassName, jint flags, jint classDataSize, jint instanceDataSize, void* attributes, void* initializer, void* d) {
    CreateClassData* data = (CreateClassData*) d;

    Class* superclass = NULL;
    if (superclassName) {
        superclass = nvmFindClassUsingLoader(env, superclassName, data->classLoader);
        if (!superclass) return FALSE;
    }

    Class* clazz = nvmAllocateClass(env, className, superclass, data->classLoader, flags, classDataSize, instanceDataSize, attributes, initializer);
    if (!clazz) return FALSE;
    data->clazz = clazz;
    return TRUE;
}

static Class* createClass(Env* env, ClassInfoHeader* header, ClassLoader* classLoader) {
    ParseClassInfoCallbacks callbacks = {0};
    callbacks.classCallback = createClassCallback;
    CreateClassData data = {0};
    data.classLoader = classLoader;
    parseClassInfo(env, header, &callbacks, &data);
    if (data.clazz) {
        if (!nvmRegisterClass(env, data.clazz)) return NULL;
    }
    header->clazz = data.clazz;
    return data.clazz;
}

static void classInitialized(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _nvmBcBootClassesHash : _nvmBcClassesHash);
    if (!header) return;
    header->flags |= CI_INITIALIZED;
}

static jboolean loadInterfacesCallback(Env* env, const char* interfaceName, void* d) {
    Class* clazz = (Class*) d;
    Class* interface = nvmFindClassUsingLoader(env, interfaceName, clazz->classLoader);
    if (nvmExceptionCheck(env)) return FALSE;
    nvmAddInterface(env, clazz, interface);
    if (nvmExceptionCheck(env)) return FALSE;
    return TRUE;
}

static void loadInterfaces(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _nvmBcBootClassesHash : _nvmBcClassesHash);
    if (!header) return;
    ParseClassInfoCallbacks callbacks = {0};
    callbacks.interfaceCallback = loadInterfacesCallback;
    parseClassInfo(env, header, &callbacks, clazz);
}

static jboolean loadFieldsCallback(Env* env, const char* name, const char* desc, jint access, jint offset, void* attributes, void* d) {
    Class* clazz = (Class*) d;
    if (!nvmAddField(env, clazz, name, desc, access, offset, attributes)) return FALSE;
    return TRUE;
}

static void loadFields(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _nvmBcBootClassesHash : _nvmBcClassesHash);
    if (!header) return;
    ParseClassInfoCallbacks callbacks = {0};
    callbacks.fieldCallback = loadFieldsCallback;
    parseClassInfo(env, header, &callbacks, clazz);
}

static jboolean loadMethodsCallback(Env* env, const char* name, const char* desc, jint access, jint size, void* impl, void* synchronizedImpl, void** targetFnPtr, 
        void* callbackImpl, void* attributes, void* d) {

    Class* clazz = (Class*) d;
    if (targetFnPtr) {
        if (!nvmAddBridgeMethod(env, clazz, name, desc, access, size, impl, synchronizedImpl, targetFnPtr, attributes)) return FALSE;
    } else if (callbackImpl) {
        if (!nvmAddCallbackMethod(env, clazz, name, desc, access, size, impl, synchronizedImpl, callbackImpl, attributes)) return FALSE;
    } else {
        if (!nvmAddMethod(env, clazz, name, desc, access, size, impl, synchronizedImpl, attributes)) return FALSE;
    }
    return TRUE;
}

static void loadMethods(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _nvmBcBootClassesHash : _nvmBcClassesHash);
    if (!header) return;
    ParseClassInfoCallbacks callbacks = {0};
    callbacks.methodCallback = loadMethodsCallback;
    parseClassInfo(env, header, &callbacks, clazz);
}

_Unwind_Reason_Code _nvmBcPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {
    return _nvmPersonality(version, actions, exception_class, exception_info, context);
}

void _nvmBcInitializeClass(Env* env, ClassInfoHeader* header) {
    Class* clazz = header->clazz;
    if (!clazz) {
        ClassLoader* loader = NULL;
        if (lookupClassInfo(env, header->className, _nvmBcClassesHash) == header) {
            loader = getSystemClassLoader(env);
            if (!loader) nvmRaiseException(env, nvmExceptionOccurred(env));
        }
        clazz = nvmFindClassUsingLoader(env, header->className, loader);
        if (!clazz) nvmRaiseException(env, wrapClassNotFoundException(env, header->className));
    }
    nvmInitialize(env, clazz);
    if (nvmExceptionCheck(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void* _nvmBcLookupVirtualMethod(Env* env, Object* thiz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, thiz->clazz, name, desc);
    if (!method) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (METHOD_IS_ABSTRACT(method)) {
        nvmThrowAbstractMethodError(env, ""); // TODO: Message
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
}

void* _nvmBcLookupInterfaceMethod(Env* env, ClassInfoHeader* header, Object* thiz, char* name, char* desc) {
    _nvmBcInitializeClass(env, header);
    Class* ownerInterface = header->clazz;
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
        nvmThrowAbstractMethodError(env, ""); // TODO: Message
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    if (!METHOD_IS_PUBLIC(method)) {
        nvmThrowIllegalAccessError(env, ""); // TODO: Message
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

jint _nvmBcExceptionMatch(Env* env, ClassInfoHeader* header) {
    if (!header->clazz) {
        // Exception class not yet loaded so it cannot match.
        return 0;
    }
    Class* clazz = header->clazz;
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

void _nvmBcThrowUnsatisfiedLinkError(Env* env) {
    nvmThrowUnsatisfiedLinkError(env);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowNoClassDefFoundError(Env* env, char* msg) {
    nvmThrowNoClassDefFoundError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowNoSuchFieldError(Env* env, char* msg) {
    nvmThrowNoSuchFieldError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowNoSuchMethodError(Env* env, char* msg) {
    nvmThrowNoSuchMethodError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowIllegalAccessError(Env* env, char* msg) {
    nvmThrowIllegalAccessError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowInstantiationError(Env* env, char* msg) {
    nvmThrowInstantiationError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowAbstractMethodError(Env* env, char* msg) {
    nvmThrowAbstractMethodError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

void _nvmBcThrowIncompatibleClassChangeError(Env* env, char* msg) {
    nvmThrowIncompatibleClassChangeError(env, msg);
    _nvmBcThrow(env, nvmExceptionOccurred(env));
}

Object* _nvmBcAllocate(Env* env, ClassInfoHeader* header) {
    Object* obj = nvmAllocateObject(env, header->clazz);
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

ObjectArray* _nvmBcNewObjectArray(Env* env, jint length, Class* arrayClass) {
    ObjectArray* array = nvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, Class* arrayClass) {
    Array* array = nvmNewMultiArray(env, dims, lengths, arrayClass);
    if (!array) nvmRaiseException(env, nvmExceptionOccurred(env));
    return array;
}

void _nvmBcSetObjectArrayElement(Env* env, ObjectArray* array, jint index, Object* value) {
    if (value) {
        Class* componentType = array->object.clazz->componentType;
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

Object* _nvmBcLdcArrayBootClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (!arrayClass) {
        arrayClass = nvmFindClassUsingLoader(env, name, NULL);
        if (nvmExceptionCheck(env)) nvmRaiseException(env, wrapClassNotFoundException(env, name));
        *arrayClassPtr = arrayClass;
    }
    return (Object*) arrayClass;
}

Object* _nvmBcLdcArrayClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (!arrayClass) {
        ClassLoader* loader = getSystemClassLoader(env);
        if (!loader) nvmRaiseException(env, nvmExceptionOccurred(env));
        arrayClass = nvmFindClassUsingLoader(env, name, loader);
        if (nvmExceptionCheck(env)) nvmRaiseException(env, wrapClassNotFoundException(env, name));
        *arrayClassPtr = arrayClass;
    }
    return (Object*) arrayClass;
}

void _nvmBcMonitorEnter(Env* env, Object* obj) {
    nvmMonitorEnter(env, obj);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

void _nvmBcMonitorExit(Env* env, Object* obj) {
    nvmMonitorExit(env, obj);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
}

Object* _nvmBcCheckcast(Env* env, ClassInfoHeader* header, Object* o) {
    if (!o) return o;
    Class* clazz = header->clazz;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!b) {
        nvmThrowClassCastException(env, clazz, o->clazz);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return o;
}

Object* _nvmBcCheckcastArray(Env* env, Class* arrayClass, Object* o) {
    if (!o) return o;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, arrayClass);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    if (!b) {
        nvmThrowClassCastException(env, arrayClass, o->clazz);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
    return o;
}

jint _nvmBcInstanceof(Env* env, ClassInfoHeader* header, Object* o) {
    Class* clazz = header->clazz;
    jboolean b = nvmIsInstanceOf(env, o, clazz);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (jint) b;
}

jint _nvmBcInstanceofArray(Env* env, Class* arrayClass, Object* o) {
    jboolean b = nvmIsInstanceOf(env, o, arrayClass);
    if (nvmExceptionOccurred(env)) nvmRaiseException(env, nvmExceptionOccurred(env));
    return (jint) b;
}

void _nvmBcPushNativeFrame(Env* env, void* cfa) {
    // We don't need to check if NativeFrames.base is NULL since it is always setup with a few slots when an Env is created.
    NativeFrames* f = &env->nativeFrames;
    if (f->top == (f->base + f->size)) {
        // Make room for more frames.
        void* oldBase = f->base;
        jint oldSize = f->size;
        jint newSize = oldSize + ALLOC_NATIVE_FRAMES_SIZE;
        f->base = nvmAllocateMemory(env, sizeof(void*) * newSize);
        if (!f->base) nvmRaiseException(env, nvmExceptionOccurred(env));
        memcpy(f->base, oldBase, sizeof(void*) * oldSize);
        f->top = f->base + oldSize;
        f->size = newSize;
    }
    *f->top = cfa;
    f->top++;
}

void _nvmBcPopNativeFrame(Env* env) {
    env->nativeFrames.top--;
}

void* _nvmBcResolveNative(Env* env, Class* clazz, char* name, char* desc, char* shortMangledName, char* longMangledName, void** ptr) {
    if (*ptr != NULL) return *ptr;
    nvmLogTrace(env, "nvmBcResolveNative: owner=%s, name=%s, desc=%s, shortMangledName=%s, longMangledName=%s\n", 
        clazz->name, name, desc, shortMangledName, longMangledName);
    NativeMethod* method = (NativeMethod*) nvmGetMethod(env, clazz, name, desc);
    if (!method) goto error;
    void* impl = nvmResolveNativeMethodImpl(env, method, shortMangledName, longMangledName, clazz->classLoader, ptr);
    if (!impl) goto error;
    return impl;
error:
    // The native function trampoline has pushed the native frame. Pop it before we raise the 
    // exception since the trampoline doesn't pop if an exception is raised during resolution.
    _nvmBcPopNativeFrame(env);
    nvmRaiseException(env, nvmExceptionOccurred(env));
    return NULL;
}

Env* _nvmBcAttachThreadFromCallback(void) {
    Env* env = NULL;
    if (nvmAttachCurrentThread(vm, &env, NULL, NULL) != JNI_OK) {
        nvmAbort("Failed to attach thread in callback");
    }
    return env;
}

void _nvmBcDetachThreadFromCallback(Env* env) {
    nvmDetachCurrentThread(env->vm, FALSE);
}

Object* _nvmBcNewStruct(Env* env, char* className, Class* caller, void* handle) {
    // TODO: Check access
    if (!handle) return NULL;
    Class* clazz = findClassInLoader(env, className, caller->classLoader);
    if (!clazz) goto error;
    Method* constructor = (Method*) nvmGetInstanceMethod(env, clazz, "<init>", "(J)V");
    if (!constructor) goto error;
    jvalue args[1];
    args[0].j = (jlong) handle;
    Object* o = nvmNewObjectA(env, clazz, constructor, args);
    if (!o) goto error;
    return o;
error:
    nvmRaiseException(env, wrapClassNotFoundException(env, className));
    return NULL;
}

void* _nvmBcGetStructHandle(Env* env, Object* object) {
    if (!object) return NULL;
    return *((void**) (((void*) object) + sizeof(Object)));
}

void* _nvmBcByValueGetStructHandle(Env* env, Object* object) {
    if (!object) {
        nvmThrowNullPointerException(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
        return NULL;
    }
    return *((void**) (((void*) object) + sizeof(Object)));
}

void _nvmBcCopyStruct(Env* env, Object* object, void* dest, jint length) {
    if (!object) {
        nvmThrowNullPointerException(env);
        nvmRaiseException(env, nvmExceptionOccurred(env));
        return;
    }
    void* src = *((void**) (((void*) object) + sizeof(Object)));
    memcpy(dest, src, length);
}

