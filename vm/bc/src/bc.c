/*
 * Copyright (C) 2012 Trillian AB
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
#include "uthash.h"
#include "utlist.h"
#include "MurmurHash3.h"
#include "classinfo.h"

#define LOG_TAG "bc"

#define ALLOC_NATIVE_FRAMES_SIZE 8

typedef struct {
    ClassInfoHeader* classInfoHeader;
    void* start;
    void* end;
} AddressClassLookup;

typedef struct {
    ClassInfoHeader* exHeader;
    jint landingPadId;
} LandingPad;

typedef struct {
    TrycatchContext tc;
    LandingPad** landingPads;
} BcTrycatchContext;

const char* __attribute__ ((weak)) _bcMainClass = NULL;
extern jboolean _bcDynamicJNI;
extern char** _bcBootclasspath;
extern char** _bcClasspath;
extern void* _bcBootClassesHash;
extern void* _bcClassesHash;
static Class* loadBootClass(Env*, const char*, ClassLoader*);
static Class* loadUserClass(Env*, const char*, ClassLoader*);
static void classInitialized(Env*, Class*);
static Interface* loadInterfaces(Env*, Class*);
static Field* loadFields(Env*, Class*);
static Method* loadMethods(Env*, Class*);
static Class* findClassAt(Env*, void*);
static Class* createClass(Env*, ClassInfoHeader*, ClassLoader*);
static jboolean exceptionMatch(Env* env, TrycatchContext*);
static Options options = {0};
static VM* vm = NULL;
static jint addressClassLookupsCount = 0;
static AddressClassLookup* addressClassLookups = NULL;

int main(int argc, char* argv[]) {
    options.mainClass = (char*) _bcMainClass;
    options.rawBootclasspath = _bcBootclasspath;
    options.rawClasspath = _bcClasspath;
    options.loadBootClass = loadBootClass;
    options.loadUserClass = loadUserClass;
    options.classInitialized = classInitialized;
    options.loadInterfaces = loadInterfaces;
    options.loadFields = loadFields;
    options.loadMethods = loadMethods;
    options.findClassAt = findClassAt;
    options.exceptionMatch = exceptionMatch;
    options.dynamicJNI = _bcDynamicJNI;
    if (!rvmInitOptions(argc, argv, &options, FALSE)) {
        fprintf(stderr, "rvmInitOptions(...) failed!\n");
        return 1;
    }
    Env* env = rvmStartup(&options);
    if (!env) {
        fprintf(stderr, "rvmStartup(...) failed!\n");
        return 1;
    }
    vm = env->vm;
    jint result = rvmRun(env) ? 0 : 1;
    rvmShutdown(env, result);
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

static void iterateClassInfos(Env* env, jboolean (*callback)(Env*, ClassInfoHeader*, MethodInfo*, void*), void* hash, void* data) {
    ClassInfoHeader** base = getClassInfosBase(hash);
    jint count = getClassInfosCount(hash);
    jint i = 0;
    for (i = 0; i < count; i++) {
        ClassInfoHeader* header = base[i];
        if ((header->flags & CI_ERROR) == 0) {
            ClassInfo ci;
            void* p = base[i];
            readClassInfo(&p, &ci);
            skipInterfaceNames(&p, &ci);
            skipFieldInfos(&p, &ci);
            jint j;
            for (j = 0; j < ci.methodCount; j++) {
                MethodInfo mi;
                readMethodInfo(&p, &mi);
                if (!callback(env, base[i], &mi, data)) {
                    break;
                }
            }
        }
    }
}

static Class* loadClass(Env* env, const char* className, ClassLoader* classLoader, void* hash) {
    ClassInfoHeader* header = lookupClassInfo(env, className, hash);
    if (!header) return NULL;
    if (header->flags & CI_ERROR) {
        ClassInfoError* error = (ClassInfoError*) header;
        switch (error->errorType) {
        case CI_ERROR_TYPE_NO_CLASS_DEF_FOUND:
            rvmThrowNoClassDefFoundError(env, error->errorMessage);
            break;
        case CI_ERROR_TYPE_ILLEGAL_ACCESS:
            rvmThrowIllegalAccessError(env, error->errorMessage);
            break;
        case CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE:
            rvmThrowIncompatibleClassChangeError(env, error->errorMessage);
            break;
        }
        return NULL;
    }
    if (header->clazz) return header->clazz;
    return createClass(env, header, classLoader);
}

static Class* loadBootClass(Env* env, const char* className, ClassLoader* classLoader) {
    return loadClass(env, className, classLoader, _bcBootClassesHash);
}

static Class* loadUserClass(Env* env, const char* className, ClassLoader* classLoader) {
    return loadClass(env, className, classLoader, _bcClassesHash);
}

static void wrapClassNotFoundException(Env* env, const char* className) {
    Object* exception = rvmExceptionOccurred(env);
    if (exception && exception->clazz == java_lang_ClassNotFoundException) {
        // If ClassNotFoundException is thrown we have to wrap it in a NoClassDefFoundError
        exception = rvmExceptionClear(env);
        Method* constructor = rvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "<init>", "(Ljava/lang/String;)V");
        if (!constructor) return;
        Object* message = rvmNewStringUTF(env, className, -1);
        if (!message) return;
        Object* wrappedException = rvmNewObject(env, java_lang_NoClassDefFoundError, constructor, message);
        if (!wrappedException) return;
        Class* java_lang_StackTraceElement = rvmFindClassUsingLoader(env, "java/lang/StackTraceElement", NULL);
        if (!java_lang_StackTraceElement) return;
        ObjectArray* stackTrace = rvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);
        if (!stackTrace) return;
        Method* setStackTrace = rvmGetInstanceMethod(env, java_lang_Throwable, "setStackTrace", "([Ljava/lang/StackTraceElement;)V");
        if (!setStackTrace) return;
        rvmCallVoidInstanceMethod(env, wrappedException, setStackTrace, stackTrace);
        if (rvmExceptionCheck(env)) return;
        Method* initCause = rvmGetInstanceMethod(env, java_lang_NoClassDefFoundError, "initCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;");
        if (!initCause) return;
        rvmCallObjectInstanceMethod(env, wrappedException, initCause, exception);
        if (!rvmExceptionCheck(env)) rvmThrow(env, wrappedException);
    }
}

static Class* createClass(Env* env, ClassInfoHeader* header, ClassLoader* classLoader) {
    ClassInfo ci;
    void* p = header;
    readClassInfo(&p, &ci);

    Class* superclass = NULL;
    if (ci.superclassName) {
        superclass = rvmFindClassUsingLoader(env, ci.superclassName, classLoader);
        if (!superclass) return NULL;
    }

    Class* clazz = rvmAllocateClass(env, header->className, superclass, classLoader, ci.access, header->typeInfo, header->vitable, header->itables,
            header->classDataSize, header->instanceDataSize, header->instanceDataOffset, header->classRefCount, 
            header->instanceRefCount, ci.attributes, header->initializer);

    if (clazz) {
        if (!rvmRegisterClass(env, clazz)) return NULL;
        header->clazz = clazz;
    }

    return clazz;
}

static void classInitialized(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _bcBootClassesHash : _bcClassesHash);
    if (!header) return;
    rvmAtomicStoreInt(&header->flags, header->flags | CI_INITIALIZED);
}

static Interface* loadInterfaces(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _bcBootClassesHash : _bcClassesHash);
    if (!header) return NULL;

    ClassInfo ci;
    jint i;
    void* p = header;
    readClassInfo(&p, &ci);

    Interface* first = NULL;
    for (i = 0; i < ci.interfaceCount; i++) {
        const char* interfaceName = readInterfaceName(&p);
        Class* interfaceClass = rvmFindClassUsingLoader(env, interfaceName, clazz->classLoader);
        if (!interfaceClass) goto error;
        Interface* interf = rvmAllocateInterface(env, interfaceClass);
        if (!interf) goto error;
        LL_APPEND(first, interf); // Interfaces has to be in the correct order so we need to use the slower LL_APPEND
    }
    return first;
error:
    while (first) {
        Interface* next = first->next;
        rvmFreeMemoryUncollectable(env, first);
        first = next;
    }
    return NULL;
}

static Field* loadFields(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _bcBootClassesHash : _bcClassesHash);
    if (!header) return NULL;

    ClassInfo ci;
    jint i;
    void* p = header;
    readClassInfo(&p, &ci);

    skipInterfaceNames(&p, &ci);

    Field* first = NULL;
    for (i = 0; i < ci.fieldCount; i++) {
        FieldInfo fi;
        readFieldInfo(&p, &fi);
        Field* f = rvmAllocateField(env, clazz, fi.name, fi.desc, fi.access, fi.offset, fi.attributes);
        if (!f) goto error;
        LL_PREPEND(first, f);
    }
    return first;
error:
    while (first) {
        Field* next = first->next;
        rvmFreeMemoryUncollectable(env, first);
        first = next;
    }
    return NULL;
}

static Method* loadMethods(Env* env, Class* clazz) {
    ClassInfoHeader* header = lookupClassInfo(env, clazz->name, 
        !clazz->classLoader || !clazz->classLoader->parent ? _bcBootClassesHash : _bcClassesHash);
    if (!header) return NULL;

    ClassInfo ci;
    jint i;
    void* p = header;
    readClassInfo(&p, &ci);
    
    skipInterfaceNames(&p, &ci);
    skipFieldInfos(&p, &ci);

    Method* first = NULL;
    for (i = 0; i < ci.methodCount; i++) {
        MethodInfo mi;
        readMethodInfo(&p, &mi);
        Method* m = NULL;
        if (mi.targetFnPtr) {
            m = (Method*) rvmAllocateBridgeMethod(env, clazz, mi.name, mi.desc, mi.vtableIndex, mi.access, mi.size, mi.impl, mi.synchronizedImpl, mi.targetFnPtr, mi.attributes);
        } else if (mi.callbackImpl) {
            m = (Method*) rvmAllocateCallbackMethod(env, clazz, mi.name, mi.desc, mi.vtableIndex, mi.access, mi.size, mi.impl, mi.synchronizedImpl, mi.callbackImpl, mi.attributes);
        } else {
            m = rvmAllocateMethod(env, clazz, mi.name, mi.desc, mi.vtableIndex, mi.access, mi.size, mi.impl, mi.synchronizedImpl, mi.attributes);
        }
        if (!m) goto error;
        LL_PREPEND(first, m);
    }
    return first;
error:
    while (first) {
        Method* next = first->next;
        rvmFreeMemoryUncollectable(env, first);
        first = next;
    }
    return NULL;
}

static jboolean countClassesWithConcreteMethodsCallback(Env* env, ClassInfoHeader* header, MethodInfo* mi, void* d) {
    if (mi->impl) {
        jint* count = (jint*) d;
        *count = *count + 1;
        return FALSE;
    }
    return TRUE;
}

static jboolean initAddressClassLookupsCallback(Env* env, ClassInfoHeader* header, MethodInfo* mi, void* d) {
    if (mi->impl) {
        AddressClassLookup** lookupPtr = (AddressClassLookup**) d;
        AddressClassLookup* lookup = *lookupPtr;
        if (lookup->classInfoHeader != header) {
            if (lookup->classInfoHeader) {
                *lookupPtr += 1;
                lookup = *lookupPtr;
            }
            lookup->classInfoHeader = header;
            lookup->start = mi->impl;
            lookup->end = mi->impl + mi->size;
        } else if (lookup->start > mi->impl) {
            lookup->start = mi->impl;
        } else if (lookup->end <= mi->impl) {
            lookup->end = mi->impl + mi->size;
        }
    }
    return TRUE;
}

static int addressClassLookupCompareBSearch(const void* _a, const void* _b) {
    AddressClassLookup* needle = (AddressClassLookup*) _a;
    AddressClassLookup* el = (AddressClassLookup*) _b;
    void* pc = needle->start;
    return (pc >= el->start && pc < el->end) ? 0 : ((pc < el->start) ? -1 : 1);
}

static int addressClassLookupCompareQSort(const void* _a, const void* _b) {
    AddressClassLookup* a = (AddressClassLookup*) _a;
    AddressClassLookup* b = (AddressClassLookup*) _b;
    return (a->end <= b->start) ? -1 : 1;
}

static AddressClassLookup* getAddressClassLookups(Env* env) {
    if (!addressClassLookups) {
        jint count = 0;
        iterateClassInfos(env, countClassesWithConcreteMethodsCallback, _bcBootClassesHash, &count);
        iterateClassInfos(env, countClassesWithConcreteMethodsCallback, _bcClassesHash, &count);
        AddressClassLookup* lookups = rvmAllocateMemoryAtomicUncollectable(env, sizeof(AddressClassLookup) * count);
        if (!lookups) return NULL;
        AddressClassLookup* _lookups = lookups;
        iterateClassInfos(env, initAddressClassLookupsCallback, _bcBootClassesHash, &_lookups);
        iterateClassInfos(env, initAddressClassLookupsCallback, _bcClassesHash, &_lookups);
        qsort(lookups, count, sizeof(AddressClassLookup), addressClassLookupCompareQSort);
        addressClassLookupsCount = count;
        addressClassLookups = lookups;
    }
    return addressClassLookups;
}

Class* findClassAt(Env* env, void* pc) {
    AddressClassLookup* lookups = getAddressClassLookups(env);
    if (!lookups) return NULL;
    AddressClassLookup needle = {NULL, pc, pc};
    AddressClassLookup* result = bsearch(&needle, lookups, addressClassLookupsCount, sizeof(AddressClassLookup), addressClassLookupCompareBSearch);
    if (!result) return NULL;
    ClassInfoHeader* header = result->classInfoHeader;
    Class* clazz = header->clazz;
    if (!clazz) {
        ClassLoader* loader = NULL;
        if (lookupClassInfo(env, header->className, _bcClassesHash) == header) {
            loader = systemClassLoader;
        }
        clazz = rvmFindClassUsingLoader(env, header->className, loader);
    }
    return clazz;
}

jboolean exceptionMatch(Env* env, TrycatchContext* _tc) {
    BcTrycatchContext* tc = (BcTrycatchContext*) _tc;
    LandingPad* lps = tc->landingPads[tc->tc.sel - 1];
    Object* throwable = rvmExceptionOccurred(env);
    jint i;
    for (i = 0; lps[i].landingPadId > 0; i++) {
        ClassInfoHeader* header = lps[i].exHeader;
        if (!header) {
            // NULL means java.lang.Throwable which always matches
            tc->tc.sel = lps[i].landingPadId;
            return TRUE;
        }
        if (!header->clazz) {
            // Exception class not yet loaded so it cannot match.
            continue;
        }
        Class* clazz = header->clazz;
        Class* c = throwable->clazz;
        while (c && c != clazz) {
            c = c->superclass;
        }
        if (c == clazz) {
            tc->tc.sel = lps[i].landingPadId;
            return TRUE;
        }
    }
    return FALSE;
}

#define ENTER rvmPushGatewayFrame(env)
#define LEAVEV \
    rvmPopGatewayFrame(env); \
    if (rvmExceptionCheck(env)) rvmRaiseException(env, rvmExceptionOccurred(env))
#define LEAVE(result) \
    rvmPopGatewayFrame(env); \
    if (rvmExceptionCheck(env)) rvmRaiseException(env, rvmExceptionOccurred(env)); \
    return result

static Class* ldcClass(Env* env, ClassInfoHeader* header) {
    Class* clazz = header->clazz;
    if (!clazz) {
        ClassLoader* loader = NULL;
        if (lookupClassInfo(env, header->className, _bcClassesHash) == header) {
            loader = systemClassLoader;
        }
        clazz = rvmFindClassUsingLoader(env, header->className, loader);
        if (!clazz) wrapClassNotFoundException(env, header->className);
    }
    return clazz;
}
static void initializeClass(Env* env, ClassInfoHeader* header) {
    Class* clazz = ldcClass(env, header);
    if (clazz) rvmInitialize(env, clazz);
}
void _bcInitializeClass(Env* env, ClassInfoHeader* header) {
    ENTER;
    initializeClass(env, header);
    LEAVEV;
}

static void* lookupVirtualMethod(Env* env, Object* thiz, char* name, char* desc) {
    Method* method = rvmGetMethod(env, thiz->clazz, name, desc);
    if (!method) return NULL;
    if (METHOD_IS_ABSTRACT(method)) {
        rvmThrowAbstractMethodError(env, ""); // TODO: Message
        return NULL;
    }
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
}
void* _bcLookupVirtualMethod(Env* env, Object* thiz, char* name, char* desc) {
    ENTER;
    void* result = lookupVirtualMethod(env, thiz, name, desc);
    LEAVE(result);
}

void* lookupInterfaceMethod(Env* env, ClassInfoHeader* header, Object* thiz, char* name, char* desc) {
    initializeClass(env, header);
    if (rvmExceptionCheck(env)) return NULL;
    Class* ownerInterface = header->clazz;
    if (!rvmIsInstanceOf(env, thiz, ownerInterface)) {
        char message[256];
        snprintf(message, 256, "Class %s does not implement the requested interface %s", 
            rvmToBinaryClassName(env, thiz->clazz->name), rvmToBinaryClassName(env, ownerInterface->name));
        rvmThrowIncompatibleClassChangeError(env, message);
        return NULL;
    }
    Method* method = rvmGetInstanceMethod(env, thiz->clazz, name, desc);
    Object* throwable = rvmExceptionClear(env);
    if (!method && throwable->clazz != java_lang_NoSuchMethodError) { 
        rvmThrow(env, throwable);
        return NULL;
    }
    if (!method || METHOD_IS_ABSTRACT(method)) {
        rvmThrowAbstractMethodError(env, ""); // TODO: Message
        return NULL;
    }
    if (!METHOD_IS_PUBLIC(method)) {
        rvmThrowIllegalAccessError(env, ""); // TODO: Message
        return NULL;
    }
    return method->synchronizedImpl ? method->synchronizedImpl : method->impl;
}
void* _bcLookupInterfaceMethod(Env* env, ClassInfoHeader* header, Object* thiz, char* name, char* desc) {
    ENTER;
    void* result = lookupInterfaceMethod(env, header, thiz, name, desc);
    LEAVE(result);
}

void* _bcLookupInterfaceMethodImpl(Env* env, ClassInfoHeader* header, Object* thiz, uint32_t index) {
    TypeInfo* typeInfo = header->typeInfo;
    ITables* itables = thiz->clazz->itables;
    ITable* itable = itables->cache;
    if (itable->typeInfo == typeInfo) {
        return itable->table.table[index];
    }
    uint32_t i;
    for (i = 0; i < itables->count; i++) {
        itable = itables->table[i];
        if (itable->typeInfo == typeInfo) {
            itables->cache = itable;
            return itable->table.table[index];
        }
    }

    ENTER;
    initializeClass(env, header);
    Class* ownerInterface = header->clazz;
    char message[256];
    snprintf(message, 256, "Class %s does not implement the requested interface %s", 
        rvmToBinaryClassName(env, thiz->clazz->name), rvmToBinaryClassName(env, ownerInterface->name));
    rvmThrowIncompatibleClassChangeError(env, message);
    LEAVE(NULL);
}

void _bcAbstractMethodCalled(Env* env, Object* thiz) {
    ENTER;
    char msg[256];
    char* name = env->reserved0;
    char* desc = env->reserved1;
    snprintf(msg, sizeof(msg), "%s.%s%s", thiz->clazz->name, name, desc);
    char* s = msg;
    while (*s != '\0') {
        if (*s == '/') *s = '.';
        s++;
    }
    rvmThrowAbstractMethodError(env, msg);
    LEAVEV;
}

void _bcNonPublicMethodCalled(Env* env, Object* thiz) {
    ENTER;
    char msg[256];
    char* name = env->reserved0;
    char* desc = env->reserved1;
    snprintf(msg, sizeof(msg), "%s.%s%s not public", thiz->clazz->name, name, desc);
    char* s = msg;
    while (*s != '\0') {
        if (*s == '/') *s = '.';
        s++;
    }
    rvmThrowIllegalAccessError(env, msg);
    LEAVEV;
}

void _bcMoveMemory16(void* dest, const void* src, jlong n) {
    rvmMoveMemory16(dest, src, n);
}

void _bcMoveMemory32(void* dest, const void* src, jlong n) {
    rvmMoveMemory32(dest, src, n);
}

void _bcTrycatchLeave(Env* env) {
    rvmTrycatchLeave(env);
}

void _bcThrow(Env* env, Object* throwable) {
    rvmRaiseException(env, throwable);
}

void _bcThrowIfExceptionOccurred(Env* env) {
    Object* throwable = rvmExceptionOccurred(env);
    if (throwable) rvmRaiseException(env, throwable);
}

Object* _bcExceptionClear(Env* env) {
    return rvmExceptionClear(env);
}

void _bcThrowNullPointerException(Env* env) {
    ENTER;
    rvmThrowNullPointerException(env);
    LEAVEV;
}

void _bcThrowArrayIndexOutOfBoundsException(Env* env, jint length, jint index) {
    ENTER;
    rvmThrowArrayIndexOutOfBoundsException(env, length, index);
    LEAVEV;
}

void _bcThrowArithmeticException(Env* env) {
    ENTER;
    rvmThrowArithmeticException(env);
    LEAVEV;
}

void _bcThrowUnsatisfiedLinkError(Env* env, char* msg) {
    ENTER;
    rvmThrowUnsatisfiedLinkError(env, msg);
    LEAVEV;
}

void _bcThrowNoClassDefFoundError(Env* env, char* msg) {
    ENTER;
    rvmThrowNoClassDefFoundError(env, msg);
    LEAVEV;
}

void _bcThrowNoSuchFieldError(Env* env, char* msg) {
    ENTER;
    rvmThrowNoSuchFieldError(env, msg);
    LEAVEV;
}

void _bcThrowNoSuchMethodError(Env* env, char* msg) {
    ENTER;
    rvmThrowNoSuchMethodError(env, msg);
    LEAVEV;
}

void _bcThrowIllegalAccessError(Env* env, char* msg) {
    ENTER;
    rvmThrowIllegalAccessError(env, msg);
    LEAVEV;
}

void _bcThrowInstantiationError(Env* env, char* msg) {
    ENTER;
    rvmThrowInstantiationError(env, msg);
    LEAVEV;
}

void _bcThrowAbstractMethodError(Env* env, char* msg) {
    ENTER;
    rvmThrowAbstractMethodError(env, msg);
    LEAVEV;
}

void _bcThrowIncompatibleClassChangeError(Env* env, char* msg) {
    ENTER;
    rvmThrowIncompatibleClassChangeError(env, msg);
    LEAVEV;
}

void _bcThrowClassCastException(Env* env, ClassInfoHeader* header, Object* o) {
    ENTER;
    Class* clazz = ldcClass(env, header);
    rvmThrowClassCastException(env, clazz, o->clazz);
    LEAVEV;
}

void _bcThrowClassCastExceptionArray(Env* env, Class* arrayClass, Object* o) {
    ENTER;
    rvmThrowClassCastException(env, arrayClass, o->clazz);
    LEAVEV;
}

Object* _bcAllocate(Env* env, ClassInfoHeader* header) {
    ENTER;
    Object* obj = rvmAllocateObject(env, header->clazz);
    LEAVE(obj);
}

void _bcRegisterFinalizer(Env* env, Object* obj) {
    ENTER;
    rvmRegisterFinalizer(env, obj);
    LEAVEV;
}

BooleanArray* _bcNewBooleanArray(Env* env, jint length) {
    ENTER;
    BooleanArray* array = rvmNewBooleanArray(env, length);
    LEAVE(array);
}

ByteArray* _bcNewByteArray(Env* env, jint length) {
    ENTER;
    ByteArray* array = rvmNewByteArray(env, length);
    LEAVE(array);
}

CharArray* _bcNewCharArray(Env* env, jint length) {
    ENTER;
    CharArray* array = rvmNewCharArray(env, length);
    LEAVE(array);
}

ShortArray* _bcNewShortArray(Env* env, jint length) {
    ENTER;
    ShortArray* array = rvmNewShortArray(env, length);
    LEAVE(array);
}

IntArray* _bcNewIntArray(Env* env, jint length) {
    ENTER;
    IntArray* array = rvmNewIntArray(env, length);
    LEAVE(array);
}

LongArray* _bcNewLongArray(Env* env, jint length) {
    ENTER;
    LongArray* array = rvmNewLongArray(env, length);
    LEAVE(array);
}

FloatArray* _bcNewFloatArray(Env* env, jint length) {
    ENTER;
    FloatArray* array = rvmNewFloatArray(env, length);
    LEAVE(array);
}

DoubleArray* _bcNewDoubleArray(Env* env, jint length) {
    ENTER;
    DoubleArray* array = rvmNewDoubleArray(env, length);
    LEAVE(array);
}

ObjectArray* _bcNewObjectArray(Env* env, jint length, Class* arrayClass) {
    ENTER;
    ObjectArray* array = rvmNewObjectArray(env, length, NULL, arrayClass, NULL);
    LEAVE(array);
}

Array* _bcNewMultiArray(Env* env, jint dims, jint* lengths, Class* arrayClass) {
    ENTER;
    Array* array = rvmNewMultiArray(env, dims, lengths, arrayClass);
    LEAVE(array);
}

void _bcSetObjectArrayElement(Env* env, ObjectArray* array, jint index, Object* value) {
    if (!value) {
        array->values[index] = value;
        return;        
    }
    ENTER;
    Class* componentType = array->object.clazz->componentType;
    jboolean assignable = rvmIsAssignableFrom(env, value->clazz, componentType);
    if (!rvmExceptionCheck(env) && !assignable) {
        rvmThrowArrayStoreException(env, value->clazz, array->object.clazz);
    }
    if (!rvmExceptionCheck(env)) array->values[index] = value;
    LEAVEV;
}


Object* _bcLdcString(Env* env, Object** ptr, char* s, jint length) {
    Object* o = *ptr;
    if (o) return o;
    ENTER;
    o = rvmNewInternedStringUTF(env, s, length);
    if (!rvmExceptionCheck(env)) {
        *ptr = o;
        rvmRegisterDisappearingLink(env, (void**) ptr, o);
    }
    LEAVE(o);
}

Object* _bcLdcArrayBootClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (arrayClass) return (Object*) arrayClass;
    ENTER;    
    arrayClass = rvmFindClassUsingLoader(env, name, NULL);
    wrapClassNotFoundException(env, name);
    if (!rvmExceptionCheck(env)) {
        *arrayClassPtr = arrayClass;
    }
    LEAVE((Object*) arrayClass);
}

Object* _bcLdcArrayClass(Env* env, Class** arrayClassPtr, char* name) {
    Class* arrayClass = *arrayClassPtr;
    if (arrayClass) return (Object*) arrayClass;
    ENTER;
    arrayClass = rvmFindClassUsingLoader(env, name, systemClassLoader);
    wrapClassNotFoundException(env, name);
    if (!rvmExceptionCheck(env)) {
        *arrayClassPtr = arrayClass;
    }
    LEAVE((Object*) arrayClass);
}

Object* _bcLdcClass(Env* env, ClassInfoHeader* header) {
    Class* clazz = header->clazz;
    if (clazz) return (Object*) clazz;
    ENTER;
    clazz = ldcClass(env, header);
    LEAVE((Object*) clazz);
}


void _bcMonitorEnter(Env* env, Object* obj) {
    ENTER;
    rvmLockObject(env, obj);
    LEAVEV;
}

void _bcMonitorExit(Env* env, Object* obj) {
    ENTER;
    rvmUnlockObject(env, obj);
    LEAVEV;
}

Object* _bcCheckcast(Env* env, ClassInfoHeader* header, Object* o) {
    if (!o) return o;
    ENTER;
    Class* clazz = ldcClass(env, header);
    if (clazz) {
        jboolean b = rvmIsAssignableFrom(env, o->clazz, clazz);
        if (!rvmExceptionCheck(env) && !b) {
            rvmThrowClassCastException(env, clazz, o->clazz);
        }
    }
    LEAVE(o);
}

Object* _bcCheckcastArray(Env* env, Class* arrayClass, Object* o) {
    if (!o) return o;
    ENTER;
    jboolean b = rvmIsAssignableFrom(env, o->clazz, arrayClass);
    if (!rvmExceptionCheck(env) && !b) {
        rvmThrowClassCastException(env, arrayClass, o->clazz);
    }
    LEAVE(o);
}

jint _bcInstanceof(Env* env, ClassInfoHeader* header, Object* o) {
    if (!o) return (jint) FALSE;
    ENTER;
    Class* clazz = ldcClass(env, header);
    jboolean b = FALSE;
    if (clazz) {
        b = rvmIsInstanceOf(env, o, clazz);
    }
    LEAVE((jint) b);
}

jint _bcInstanceofArray(Env* env, Class* arrayClass, Object* o) {
    if (!o) return (jint) FALSE;
    ENTER;
    jboolean b = rvmIsInstanceOf(env, o, arrayClass);
    LEAVE((jint) b);
}

void _bcPushNativeFrame(Env* env, GatewayFrame* gwFrame, void* frameAddress) {
    rvmPushGatewayFrame0(env, gwFrame, frameAddress, NULL);
}

void _bcPopNativeFrame(Env* env) {
    rvmPopGatewayFrame(env);
}

void _bcPushCallbackFrame(Env* env, GatewayFrame* gwFrame, void* frameAddress) {
    rvmPushGatewayFrame0(env, gwFrame, frameAddress, NULL);
}

void _bcPopCallbackFrame(Env* env) {
    rvmPopGatewayFrame(env);
}

void* _bcResolveNative(Env* env, Class* clazz, char* name, char* desc, char* shortMangledName, char* longMangledName, void** ptr) {
    if (*ptr != NULL) return *ptr;
    ENTER;
    TRACEF("_bcResolveNative: owner=%s, name=%s, desc=%s, shortMangledName=%s, longMangledName=%s", 
        clazz->name, name, desc, shortMangledName, longMangledName);
    NativeMethod* method = (NativeMethod*) rvmGetMethod(env, clazz, name, desc);
    void* impl = NULL;
    if (method) {
        impl = rvmResolveNativeMethodImpl(env, method, shortMangledName, longMangledName, clazz->classLoader, ptr);
    }
    LEAVE(impl);
}

Env* _bcAttachThreadFromCallback(void) {
    Env* env = NULL;
    if (rvmAttachCurrentThread(vm, &env, NULL, NULL) != JNI_OK) {
        rvmAbort("Failed to attach thread in callback");
    }
    return env;
}

void _bcDetachThreadFromCallback(Env* env) {
    rvmDetachCurrentThread(env->vm, FALSE, TRUE);
}
