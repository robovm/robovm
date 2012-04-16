#include <nullvm.h>
#include <unwind.h>
#include "uthash.h"
#include "utlist.h"
#include "MurmurHash3.h"
#include "classinfo.h"

#define ALLOC_NATIVE_FRAMES_SIZE 8

typedef struct {
    ClassInfoHeader* classInfoHeader;
    void* start;
    void* end;
} AddressClassLookup;

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
static Class* findClassAt(Env*, void*);
static Class* createClass(Env*, ClassInfoHeader*, ClassLoader*);
static Options options = {0};
static VM* vm = NULL;
static jint addressClassLookupsCount = 0;
static AddressClassLookup* addressClassLookups = NULL;

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
    options.findClassAt = findClassAt;
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

static ClassInfoHeader** getClassInfosBase(void* hash) {
    hash += sizeof(jint); // Skip count
    jint size = ((jshort*) hash)[0];
#ifdef _LP64
    ClassInfoHeader** base  = hash + (size << 1) + 4;
#else
    ClassInfoHeader** base  = hash + (size << 1) + 4;
#endif
    return base;
}

static jint getClassInfosCount(void* hash) {
    return ((jint*) hash)[0];
}

static ClassInfoHeader* lookupClassInfo(Env* env, const char* className, void* hash) {
    ClassInfoHeader** base = getClassInfosBase(hash);
    jint h = 0;
    MurmurHash3_x86_32(className, strlen(className) + 1, 0x1ce79e5c, &h);
    hash += sizeof(jint); // Skip count
    jint size = ((jshort*) hash)[0];
    h &= size - 1;
    jint start = ((jshort*) hash)[h + 1];
    jint end = ((jshort*) hash)[h + 1 + 1];
    jint i;
    for (i = start; i < end; i++) {
        ClassInfoHeader* header = base[i];
        if (header && !strcmp(header->className, className)) {
            return header;
        }
    }
    return NULL;
}

static void iterateClassInfos(Env* env, ParseClassInfoCallbacks* callbacks, void* hash, void* data) {
    ClassInfoHeader** base = getClassInfosBase(hash);
    jint count = getClassInfosCount(hash);
    jint i = 0;
    for (i = 0; i < count; i++) {
        parseClassInfo(env, base[i], callbacks, data);        
    }
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

static void wrapClassNotFoundException(Env* env, const char* className) {
    Object* exception = nvmExceptionOccurred(env);
    if (exception && exception->clazz == java_lang_ClassNotFoundException) {
        // If ClassNotFoundException is thrown we have to wrap it in a NoClassDefFoundError
        exception = nvmExceptionClear(env);
        Method* constructor = nvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "<init>", "(Ljava/lang/String;)V");
        if (!constructor) return;
        Object* message = nvmNewStringUTF(env, className, -1);
        if (!message) return;
        Object* wrappedException = nvmNewObject(env, java_lang_NoClassDefFoundError, constructor, message);
        if (!wrappedException) return;
        Class* java_lang_StackTraceElement = nvmFindClassUsingLoader(env, "java/lang/StackTraceElement", NULL);
        if (!java_lang_StackTraceElement) return;
        ObjectArray* stackTrace = nvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);
        if (!stackTrace) return;
        Method* setStackTrace = nvmGetInstanceMethod(env, java_lang_Throwable, "setStackTrace", "([Ljava/lang/StackTraceElement;)V");
        if (!setStackTrace) return;
        nvmCallVoidInstanceMethod(env, wrappedException, setStackTrace, stackTrace);
        if (nvmExceptionCheck(env)) return;
        Method* initCause = nvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "initCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;");
        if (!initCause) return;
        nvmCallObjectInstanceMethod(env, wrappedException, initCause, exception);
        if (!nvmExceptionCheck(env)) nvmThrow(env, wrappedException);
    }
}

typedef struct {
    Class* clazz;
    ClassLoader* classLoader;
} CreateClassData;

static jboolean createClassCallback(Env* env, ClassInfoHeader* header, const char* className, const char* superclassName, jint flags, jint classDataSize, jint instanceDataSize, void* attributes, void* initializer, void* d) {
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

static jboolean loadInterfacesCallback(Env* env, ClassInfoHeader* header, const char* interfaceName, void* d) {
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

static jboolean loadFieldsCallback(Env* env, ClassInfoHeader* header, const char* name, const char* desc, jint access, jint offset, void* attributes, void* d) {
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

static jboolean loadMethodsCallback(Env* env, ClassInfoHeader* header, const char* name, const char* desc, jint access, jint size, void* impl, void* synchronizedImpl, void** targetFnPtr, 
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

static jboolean countClassesWithConcreteMethodsCallback(Env* env, ClassInfoHeader* header, const char* name, const char* desc, jint access, jint size, void* impl, void* synchronizedImpl, void** targetFnPtr, 
        void* callbackImpl, void* attributes, void* d) {

    if (impl) {
        jint* count = (jint*) d;
        *count = *count + 1;
        return FALSE;
    }
    return TRUE;
}

static jboolean initAddressClassLookupsCallback(Env* env, ClassInfoHeader* header, const char* name, const char* desc, jint access, jint size, void* impl, void* synchronizedImpl, void** targetFnPtr, 
        void* callbackImpl, void* attributes, void* d) {

    if (impl) {
        AddressClassLookup** lookupPtr = (AddressClassLookup**) d;
        AddressClassLookup* lookup = *lookupPtr;
        if (lookup->classInfoHeader != header) {
            if (lookup->classInfoHeader) {
                *lookupPtr += 1;
                lookup = *lookupPtr;
            }
            lookup->classInfoHeader = header;
            lookup->start = impl;
            lookup->end = impl + size;
        } else if (lookup->start > impl) {
            lookup->start = impl;
        } else if (lookup->end <= impl) {
            lookup->end = impl + size;
        }
    }
    return TRUE;
}

static int addressClassLookupCompare(const void* _a, const void* _b) {
    AddressClassLookup* a = (AddressClassLookup*) _a;
    AddressClassLookup* b = (AddressClassLookup*) _b;
    if (a->start == a->end) {
        void* pc = a->start;
        return (pc >= b->start && pc < b->end) ? 0 : ((pc < b->start) ? -1 : 1);
    }
    if (b->start == b->end) {
        void* pc = b->start;
        return (pc >= a->start && pc < a->end) ? 0 : ((pc >= a->end) ? -1 : 1);
    }
    return (a->end < b->start) ? -1 : 1;
}

static AddressClassLookup* getAddressClassLookups(Env* env) {
    if (!addressClassLookups) {
        ParseClassInfoCallbacks callbacks = {0};
        callbacks.methodCallback = countClassesWithConcreteMethodsCallback;
        jint count = 0;
        iterateClassInfos(env, &callbacks, _nvmBcBootClassesHash, &count);
        iterateClassInfos(env, &callbacks, _nvmBcClassesHash, &count);
        AddressClassLookup* lookups = nvmAllocateMemory(env, sizeof(AddressClassLookup) * count);
        if (!lookups) return NULL;
        AddressClassLookup* _lookups = lookups;
        callbacks.methodCallback = initAddressClassLookupsCallback;
        iterateClassInfos(env, &callbacks, _nvmBcBootClassesHash, &_lookups);
        iterateClassInfos(env, &callbacks, _nvmBcClassesHash, &_lookups);
        qsort(lookups, count, sizeof(AddressClassLookup), addressClassLookupCompare);
        addressClassLookupsCount = count;
        addressClassLookups = lookups;
    }
    return addressClassLookups;
}

Class* findClassAt(Env* env, void* pc) {
    AddressClassLookup* lookups = getAddressClassLookups(env);
    if (!lookups) return NULL;
    AddressClassLookup needle = {NULL, pc, pc};
    AddressClassLookup* result = bsearch(&needle, lookups, addressClassLookupsCount, sizeof(AddressClassLookup), addressClassLookupCompare);
    if (!result) return NULL;
    ClassInfoHeader* header = result->classInfoHeader;
    Class* clazz = header->clazz;
    if (!clazz) {
        ClassLoader* loader = NULL;
        if (lookupClassInfo(env, header->className, _nvmBcClassesHash) == header) {
            loader = systemClassLoader;
        }
        clazz = nvmFindClassUsingLoader(env, header->className, loader);
    }
    return clazz;
}

_Unwind_Reason_Code _nvmBcPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {
    return _nvmPersonality(version, actions, exception_class, exception_info, context);
}

#define ENTER nvmPushGatewayFrame(env)
#define LEAVEV \
    nvmPopGatewayFrame(env); \
    if (nvmExceptionCheck(env)) nvmRaiseException(env, nvmExceptionOccurred(env))
#define LEAVE(result) \
    nvmPopGatewayFrame(env); \
    if (nvmExceptionCheck(env)) nvmRaiseException(env, nvmExceptionOccurred(env)); \
    return result

static void initializeClass(Env* env, ClassInfoHeader* header) {
    Class* clazz = header->clazz;
    if (!clazz) {
        ClassLoader* loader = NULL;
        if (lookupClassInfo(env, header->className, _nvmBcClassesHash) == header) {
            loader = systemClassLoader;
        }
        clazz = nvmFindClassUsingLoader(env, header->className, loader);
        if (!clazz) wrapClassNotFoundException(env, header->className);
    }
    if (clazz) nvmInitialize(env, clazz);
}
void _nvmBcInitializeClass(Env* env, ClassInfoHeader* header) {
    ENTER;
    initializeClass(env, header);
    LEAVEV;
}

static void* lookupVirtualMethod(Env* env, Object* thiz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, thiz->clazz, name, desc);
    if (!method) return NULL;
    if (METHOD_IS_ABSTRACT(method)) {
        nvmThrowAbstractMethodError(env, ""); // TODO: Message
        return NULL;
    }
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
}
void* _nvmBcLookupVirtualMethod(Env* env, Object* thiz, char* name, char* desc) {
    ENTER;
    void* result = lookupVirtualMethod(env, thiz, name, desc);
    LEAVE(result);
}

void* lookupInterfaceMethod(Env* env, ClassInfoHeader* header, Object* thiz, char* name, char* desc) {
    initializeClass(env, header);
    if (nvmExceptionCheck(env)) return NULL;
    Class* ownerInterface = header->clazz;
    if (!nvmIsInstanceOf(env, thiz, ownerInterface)) {
        nvmThrowLinkageError(env);
        return NULL;
    }
    Method* method = nvmGetInstanceMethod(env, thiz->clazz, name, desc);
    Object* throwable = nvmExceptionClear(env);
    if (!method && throwable->clazz != java_lang_NoSuchMethodError) { 
        nvmThrow(env, throwable);
        return NULL;
    }
    if (!method || METHOD_IS_ABSTRACT(method)) {
        nvmThrowAbstractMethodError(env, ""); // TODO: Message
        return NULL;
    }
    if (!METHOD_IS_PUBLIC(method)) {
        nvmThrowIllegalAccessError(env, ""); // TODO: Message
        return NULL;
    }
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
}
void* _nvmBcLookupInterfaceMethod(Env* env, ClassInfoHeader* header, Object* thiz, char* name, char* desc) {
    ENTER;
    void* result = lookupInterfaceMethod(env, header, thiz, name, desc);
    LEAVE(result);
}

void _nvmBcThrow(Env* env, Object* throwable) {
    nvmRaiseException(env, throwable);
}

void _nvmBcRethrow(Env* env, void* exInfo) {
    nvmReraiseException(env, exInfo);
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
    ENTER;
    nvmThrowNullPointerException(env);
    LEAVEV;
}

void _nvmBcThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    ENTER;
    nvmThrowArrayIndexOutOfBoundsException(env, index);
    LEAVEV;
}

void _nvmBcThrowArithmeticException(Env* env) {
    ENTER;
    nvmThrowArithmeticException(env);
    LEAVEV;
}

void _nvmBcThrowUnsatisfiedLinkError(Env* env) {
    ENTER;
    nvmThrowUnsatisfiedLinkError(env);
    LEAVEV;
}

void _nvmBcThrowNoClassDefFoundError(Env* env, char* msg) {
    ENTER;
    nvmThrowNoClassDefFoundError(env, msg);
    LEAVEV;
}

void _nvmBcThrowNoSuchFieldError(Env* env, char* msg) {
    ENTER;
    nvmThrowNoSuchFieldError(env, msg);
    LEAVEV;
}

void _nvmBcThrowNoSuchMethodError(Env* env, char* msg) {
    ENTER;
    nvmThrowNoSuchMethodError(env, msg);
    LEAVEV;
}

void _nvmBcThrowIllegalAccessError(Env* env, char* msg) {
    ENTER;
    nvmThrowIllegalAccessError(env, msg);
    LEAVEV;
}

void _nvmBcThrowInstantiationError(Env* env, char* msg) {
    ENTER;
    nvmThrowInstantiationError(env, msg);
    LEAVEV;
}

void _nvmBcThrowAbstractMethodError(Env* env, char* msg) {
    ENTER;
    nvmThrowAbstractMethodError(env, msg);
    LEAVEV;
}

void _nvmBcThrowIncompatibleClassChangeError(Env* env, char* msg) {
    ENTER;
    nvmThrowIncompatibleClassChangeError(env, msg);
    LEAVEV;
}

Object* _nvmBcAllocate(Env* env, ClassInfoHeader* header) {
    ENTER;
    Object* obj = nvmAllocateObject(env, header->clazz);
    LEAVE(obj);
}

BooleanArray* _nvmBcNewBooleanArray(Env* env, jint length) {
    ENTER;
    BooleanArray* array = nvmNewBooleanArray(env, length);
    LEAVE(array);
}

ByteArray* _nvmBcNewByteArray(Env* env, jint length) {
    ENTER;
    ByteArray* array = nvmNewByteArray(env, length);
    LEAVE(array);
}

CharArray* _nvmBcNewCharArray(Env* env, jint length) {
    ENTER;
    CharArray* array = nvmNewCharArray(env, length);
    LEAVE(array);
}

ShortArray* _nvmBcNewShortArray(Env* env, jint length) {
    ENTER;
    ShortArray* array = nvmNewShortArray(env, length);
    LEAVE(array);
}

IntArray* _nvmBcNewIntArray(Env* env, jint length) {
    ENTER;
    IntArray* array = nvmNewIntArray(env, length);
    LEAVE(array);
}

LongArray* _nvmBcNewLongArray(Env* env, jint length) {
    ENTER;
    LongArray* array = nvmNewLongArray(env, length);
    LEAVE(array);
}

FloatArray* _nvmBcNewFloatArray(Env* env, jint length) {
    ENTER;
    FloatArray* array = nvmNewFloatArray(env, length);
    LEAVE(array);
}

DoubleArray* _nvmBcNewDoubleArray(Env* env, jint length) {
    ENTER;
    DoubleArray* array = nvmNewDoubleArray(env, length);
    LEAVE(array);
}

ObjectArray* _nvmBcNewObjectArray(Env* env, jint length, Class* arrayClass) {
    ENTER;
    ObjectArray* array = nvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    LEAVE(array);
}

Array* _nvmBcNewMultiArray(Env* env, jint dims, jint* lengths, Class* arrayClass) {
    ENTER;
    Array* array = nvmNewMultiArray(env, dims, lengths, arrayClass);
    LEAVE(array);
}

void _nvmBcSetObjectArrayElement(Env* env, ObjectArray* array, jint index, Object* value) {
    if (!value) {
        array->values[index] = value;
        return;        
    }
    ENTER;
    Class* componentType = array->object.clazz->componentType;
    jboolean assignable = nvmIsAssignableFrom(env, value->clazz, componentType);
    if (!nvmExceptionCheck(env) && !assignable) {
        nvmThrowArrayStoreException(env);
    }
    if (!nvmExceptionCheck(env)) array->values[index] = value;
    LEAVEV;
}


Object* _nvmBcLdcString(Env* env, char* s) {
    ENTER;
    // TODO: The caller knows the length of the string in Java chars
    // TODO: Use nvmNewStringAscii if string only contains ASCII
    Object* o = nvmNewInternedStringUTF(env, s, -1);
    LEAVE(o);
}

Object* _nvmBcLdcArrayBootClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (arrayClass) return (Object*) arrayClass;
    ENTER;    
    arrayClass = nvmFindClassUsingLoader(env, name, NULL);
    wrapClassNotFoundException(env, name);
    if (!nvmExceptionCheck(env)) {
        *arrayClassPtr = arrayClass;
    }
    LEAVE((Object*) arrayClass);
}

Object* _nvmBcLdcArrayClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (arrayClass) return (Object*) arrayClass;
    ENTER;
    arrayClass = nvmFindClassUsingLoader(env, name, systemClassLoader);
    wrapClassNotFoundException(env, name);
    if (!nvmExceptionCheck(env)) {
        *arrayClassPtr = arrayClass;
    }
    LEAVE((Object*) arrayClass);
}

void _nvmBcMonitorEnter(Env* env, Object* obj) {
    ENTER;
    nvmMonitorEnter(env, obj);
    LEAVEV;
}

void _nvmBcMonitorExit(Env* env, Object* obj) {
    ENTER;
    nvmMonitorExit(env, obj);
    LEAVEV;
}

Object* _nvmBcCheckcast(Env* env, ClassInfoHeader* header, Object* o) {
    if (!o) return o;
    ENTER;
    Class* clazz = header->clazz;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, clazz);
    if (!nvmExceptionCheck(env) && !b) {
        nvmThrowClassCastException(env, clazz, o->clazz);
    }
    LEAVE(o);
}

Object* _nvmBcCheckcastArray(Env* env, Class* arrayClass, Object* o) {
    if (!o) return o;
    ENTER;
    jboolean b = nvmIsAssignableFrom(env, o->clazz, arrayClass);
    if (!nvmExceptionCheck(env) && !b) {
        nvmThrowClassCastException(env, arrayClass, o->clazz);
    }
    LEAVE(o);
}

jint _nvmBcInstanceof(Env* env, ClassInfoHeader* header, Object* o) {
    if (!o) return (jint) FALSE;
    ENTER;
    Class* clazz = header->clazz;
    jboolean b = nvmIsInstanceOf(env, o, clazz);
    LEAVE((jint) b);
}

jint _nvmBcInstanceofArray(Env* env, Class* arrayClass, Object* o) {
    if (!o) return (jint) FALSE;
    ENTER;
    jboolean b = nvmIsInstanceOf(env, o, arrayClass);
    LEAVE((jint) b);
}

void _nvmBcPushNativeFrame(Env* env, GatewayFrame* gwFrame, void* frameAddress) {
    nvmPushGatewayFrame0(env, gwFrame, frameAddress, NULL);
}

void _nvmBcPopNativeFrame(Env* env) {
    nvmPopGatewayFrame(env);
}

void _nvmBcPushCallbackFrame(Env* env, GatewayFrame* gwFrame, void* frameAddress) {
    nvmPushGatewayFrame0(env, gwFrame, frameAddress, NULL);
}

void _nvmBcPopCallbackFrame(Env* env) {
    nvmPopGatewayFrame(env);
}

void* _nvmBcResolveNative(Env* env, Class* clazz, char* name, char* desc, char* shortMangledName, char* longMangledName, void** ptr) {
    if (*ptr != NULL) return *ptr;
    ENTER;
    nvmLogTrace(env, "nvmBcResolveNative: owner=%s, name=%s, desc=%s, shortMangledName=%s, longMangledName=%s\n", 
        clazz->name, name, desc, shortMangledName, longMangledName);
    NativeMethod* method = (NativeMethod*) nvmGetMethod(env, clazz, name, desc);
    void* impl = NULL;
    if (method) {
        impl = nvmResolveNativeMethodImpl(env, method, shortMangledName, longMangledName, clazz->classLoader, ptr);
    }
    LEAVE(impl);
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

void* _nvmBcGetStructHandle(Env* env, Object* object) {
    if (!object) return NULL;
    return *((void**) (((void*) object) + sizeof(Object)));
}

void* _nvmBcByValueGetStructHandle(Env* env, Object* object) {
    ENTER;
    void* result = NULL;
    if (!object) {
        nvmThrowNullPointerException(env);
    } else {
        result = *((void**) (((void*) object) + sizeof(Object)));
    }
    LEAVE(result);
}

void _nvmBcCopyStruct(Env* env, Object* object, void* dest, jint length) {
    ENTER;
    if (!object) {
        nvmThrowNullPointerException(env);
    } else {
        void* src = *((void**) (((void*) object) + sizeof(Object)));
        memcpy(dest, src, length);
    }
    LEAVEV;
}
