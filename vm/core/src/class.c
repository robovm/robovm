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
#include <stdlib.h>
#include <string.h>
#include "utlist.h"
#include "private.h"
#include "uthash.h"

#define LOG_TAG "core.class"

Class* java_lang_Object;
Class* java_lang_Class;
Class* java_lang_ClassLoader;
Class* java_lang_String;
Class* java_lang_Boolean;
Class* java_lang_Byte;
Class* java_lang_Character;
Class* java_lang_Short;
Class* java_lang_Integer;
Class* java_lang_Long;
Class* java_lang_Float;
Class* java_lang_Double;
Class* java_lang_Enum;
Class* java_lang_Cloneable;
Class* java_lang_Thread;
Class* java_lang_Runtime;
Class* java_lang_ThreadGroup;
Class* java_io_Serializable;

Class* java_lang_Error;
Class* java_lang_OutOfMemoryError;
Class* java_lang_NoClassDefFoundError;
Class* java_lang_IllegalAccessError;
Class* java_lang_NoSuchFieldError;
Class* java_lang_NoSuchMethodError;
Class* java_lang_IncompatibleClassChangeError;
Class* java_lang_AbstractMethodError;
Class* java_lang_UnsatisfiedLinkError;
Class* java_lang_ExceptionInInitializerError;
Class* java_lang_VerifyError;
Class* java_lang_LinkageError;
Class* java_lang_InstantiationError;
Class* java_lang_StackOverflowError;
Class* java_lang_InternalError;

Class* java_lang_Throwable;
Class* java_lang_RuntimeException;
Class* java_lang_ClassCastException;
Class* java_lang_NullPointerException;
Class* java_lang_ArrayIndexOutOfBoundsException;
Class* java_lang_ArrayStoreException;
Class* java_lang_ClassNotFoundException;
Class* java_lang_NegativeArraySizeException;
Class* java_lang_IllegalArgumentException;
Class* java_lang_ArithmeticException;
Class* java_lang_UnsupportedOperationException;
Class* java_lang_IllegalMonitorStateException;
Class* java_lang_InstantiationException;
Class* java_lang_InterruptedException;
Class* java_lang_IllegalStateException;
Class* java_lang_InterruptedException;

Class* java_lang_ref_Reference;

Class* prim_Z;
Class* prim_B;
Class* prim_C;
Class* prim_S;
Class* prim_I;
Class* prim_J;
Class* prim_F;
Class* prim_D;
Class* prim_V;

Class* array_Z;
Class* array_B;
Class* array_C;
Class* array_S;
Class* array_I;
Class* array_J;
Class* array_F;
Class* array_D;

static Field FIELDS_NOT_LOADED = {0};
static Method METHODS_NOT_LOADED = {0};
static Interface INTERFACES_NOT_LOADED = {0};
static Boolean* java_lang_Boolean_TRUE = NULL;
static Boolean* java_lang_Boolean_FALSE = NULL;
static Method* java_lang_Byte_valueOf = NULL;
static ObjectArray* bytesCache = NULL;
static Method* java_lang_Short_valueOf = NULL;
static ObjectArray* shortsCache = NULL;
static Method* java_lang_Character_valueOf = NULL;
static ObjectArray* charactersCache = NULL;
static Method* java_lang_Integer_valueOf = NULL;
static ObjectArray* integersCache = NULL;
static Method* java_lang_Long_valueOf = NULL;
static ObjectArray* longsCache = NULL;
static Method* java_lang_Float_valueOf = NULL;
static Method* java_lang_Double_valueOf = NULL;

static Mutex classLock;

typedef struct LoadedClassEntry {
    const char* key;      // The class name
    Class* clazz;
    UT_hash_handle hh;
} LoadedClassEntry;
static LoadedClassEntry* loadedClasses = NULL;

// Class id counter used for dynamically created classes. We assume
// that linked in classes never have class ids above about 250 million.
static uint32_t classIdCounter = 0x10000000;

static ITable emptyITable = {NULL, {0}};
static ITables emptyITables = {0, &emptyITable};

static Class* findClassByDescriptor(Env* env, const char* desc, ClassLoader* classLoader, Class* (*loaderFunc)(Env*, const char*, ClassLoader*));
static Class* findClass(Env* env, const char* className, ClassLoader* classLoader, Class* (*loaderFunc)(Env*, const char*, ClassLoader*));
static Class* findBootClass(Env* env, const char* className);

inline uint32_t nextClassId(void) {
    return __sync_fetch_and_add(&classIdCounter, 1);
}

static Class* getLoadedClass(Env* env, const char* className) {
    LoadedClassEntry* entry;
    HASH_FIND_STR(loadedClasses, className, entry);
    return entry ? entry->clazz : NULL;
}

static jboolean addLoadedClass(Env* env, Class* clazz) {
    // LoadedClassEntrys are allocated atomically. Classes are always GC roots 
    // which means that the class and it's name will be reachable regardless of 
    // whether this is allocated atomically or not.
    LoadedClassEntry* entry = rvmAllocateMemoryAtomicUncollectable(env, sizeof(LoadedClassEntry));
    if (!entry) return FALSE;
    entry->key = clazz->name;
    entry->clazz = clazz;
    HASH_ADD_KEYPTR(hh, loadedClasses, entry->key, strlen(entry->key), entry);
    return TRUE;
}

static inline void obtainClassLock() {
    rvmLockMutex(&classLock);
}

static inline void releaseClassLock() {
    rvmUnlockMutex(&classLock);
}

static Class* createPrimitiveClass(Env* env, const char* desc) {
    uint32_t classId = nextClassId();
    TypeInfo* typeInfo = rvmAllocateMemoryAtomic(env, sizeof(TypeInfo) + sizeof(uint32_t));
    if (!typeInfo) return NULL;
    typeInfo->id = classId;
    typeInfo->offset = sizeof(TypeInfo);
    typeInfo->cache = 0xffffffff;
    typeInfo->classCount = 1;
    typeInfo->types[0] = classId;

    Class* clazz = rvmAllocateClass(env, desc, NULL, NULL,
        CLASS_TYPE_PRIMITIVE | ACC_PUBLIC | ACC_FINAL | ACC_ABSTRACT, typeInfo, NULL, NULL,
        sizeof(Class), sizeof(Object), sizeof(Object), 0, 0, NULL, NULL);
    if (!clazz) return NULL;
    clazz->_interfaces = NULL;
    clazz->_fields = NULL;
    clazz->_methods = NULL;
    if (!rvmAddObjectGCRoot(env, (Object*) clazz)) return NULL;
    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_INITIALIZED;
    return clazz;
}

static Class* createArrayClass(Env* env, Class* componentType) {
    // Create a TypeInfo for the new array class. This TypeInfo is incomplete.
    // It only contains C[], Object, Cloneable and Serializable. rvmIsAssignableFrom
    // will also check the componentType if no match can be found in the TypeInfo for
    // annary classes.
    TypeInfo* typeInfo = NULL;
    uint32_t classId = nextClassId();
    typeInfo = rvmAllocateMemoryAtomic(env, sizeof(TypeInfo) + sizeof(uint32_t) * 4);
    if (!typeInfo) return NULL;
    typeInfo->id = classId;
    typeInfo->offset = sizeof(TypeInfo) + sizeof(uint32_t);
    typeInfo->cache = 0xffffffff;
    typeInfo->classCount = 2;
    typeInfo->interfaceCount = 2;
    typeInfo->types[0] = java_lang_Object->typeInfo->id;
    typeInfo->types[1] = classId;
    typeInfo->types[2] = java_lang_Cloneable->typeInfo->id;
    typeInfo->types[3] = java_io_Serializable->typeInfo->id;

    jint length = strlen(componentType->name);
    char* desc = NULL;

    if (CLASS_IS_ARRAY(componentType) || CLASS_IS_PRIMITIVE(componentType)) {
        desc = rvmAllocateMemoryAtomic(env, length + 2);
        if (!desc) return NULL;
        desc[0] = '[';
        strcat(desc, componentType->name);
    } else {
        desc = rvmAllocateMemoryAtomic(env, length + 4);
        if (!desc) return NULL;
        desc[0] = '[';
        desc[1] = 'L';
        strcat(desc, componentType->name);
        desc[length + 2] = ';';
    }

    Class* clazz = rvmAllocateClass(env, desc, java_lang_Object, componentType->classLoader,
        CLASS_TYPE_ARRAY | ACC_PUBLIC | ACC_FINAL | ACC_ABSTRACT, typeInfo, java_lang_Object->vitable, NULL,
        sizeof(Class), sizeof(Object), sizeof(Object), 0, 0, NULL, NULL);
    if (!clazz) return NULL;
    clazz->componentType = componentType;
    // Initialize methods to NULL to prevent rvmGetMethods() from trying to load the methods if called with this array class
    clazz->_methods = NULL;
    if (!rvmAddInterface(env, clazz, java_lang_Cloneable)) return NULL;
    if (!rvmAddInterface(env, clazz, java_io_Serializable)) return NULL;
    if (!rvmRegisterClass(env, clazz)) return NULL;

    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_INITIALIZED;

    return clazz;
}

static Class* findClass(Env* env, const char* className, ClassLoader* classLoader, Class* (*loaderFunc)(Env*, const char*, ClassLoader*)) {
    obtainClassLock();
    Class* clazz = getLoadedClass(env, className);
    if (clazz != NULL) {
        releaseClassLock();
        return clazz;
    }

    if (className[0] == '[') {
        Class* componentType = findClassByDescriptor(env, &className[1], classLoader, loaderFunc);
        if (componentType == prim_V) {
            componentType = NULL;
        }
        if (!componentType)  {
            releaseClassLock();
            Object* exception = rvmExceptionOccurred(env);
            if (exception && exception->clazz == java_lang_ClassNotFoundException) {
                // Assume that the ClassNotFoundException was thrown because the component
                // type could not be found. Throw a new ClassNotFoundException with className
                // as message.
                rvmExceptionClear(env);
                rvmThrowClassNotFoundException(env, className);
            } else if (!strcmp("[V", className)) {
                // Array of void is not possible
                rvmThrowClassNotFoundException(env, className);
            }
            return NULL;
        }
        clazz = createArrayClass(env, componentType);
        releaseClassLock();
        return clazz;
    }

    TRACEF("Class '%s' not loaded", className);

    clazz = loaderFunc(env, className, classLoader);
    if (rvmExceptionOccurred(env)) {
        releaseClassLock();
        return NULL;
    }

    if (clazz == NULL) {
        if (!strcmp(className, "java/lang/ClassNotFoundException")) {
            rvmAbort("Fatal error: java.lang.ClassNotFoundException not found!");
        }
        rvmThrowClassNotFoundException(env, className);
    }

    TRACEF("Class '%s' loaded successfully", className);
    releaseClassLock();
    return clazz;
}

static Class* findBootClass(Env* env, const char* className) {
    Class* clazz = findClass(env, className, NULL, env->vm->options->loadBootClass);
    if (rvmExceptionOccurred(env)) return NULL;
    if (clazz != NULL) {
        if (clazz->classLoader != NULL) {
            // Not a boot class
            rvmThrowClassNotFoundException(env, className);
            return NULL;
        }
    }
    return clazz;
}


static Class* findClassByDescriptor(Env* env, const char* desc, ClassLoader* classLoader, Class* (*loaderFunc)(Env*, const char*, ClassLoader*)) {
    switch (desc[0]) {
    case 'Z':
        return prim_Z;
    case 'B':
        return prim_B;
    case 'C':
        return prim_C;
    case 'S':
        return prim_S;
    case 'I':
        return prim_I;
    case 'J':
        return prim_J;
    case 'F':
        return prim_F;
    case 'D':
        return prim_D;
    case 'V':
        return prim_V;
    case '[':
        return findClass(env, desc, classLoader, loaderFunc);
    }
    assert(desc[0] == 'L');
    jint length = strlen(desc);
    char* className = rvmAllocateMemoryAtomic(env, length - 2 + 1);
    if (!className) return NULL;
    strncpy(className, &desc[1], length - 2);
    return findClass(env, className, classLoader, loaderFunc);
}

Class* rvmFindClassByDescriptor(Env* env, const char* desc, ClassLoader* classLoader) {
    switch (desc[0]) {
    case 'Z':
        return prim_Z;
    case 'B':
        return prim_B;
    case 'C':
        return prim_C;
    case 'S':
        return prim_S;
    case 'I':
        return prim_I;
    case 'J':
        return prim_J;
    case 'F':
        return prim_F;
    case 'D':
        return prim_D;
    case 'V':
        return prim_V;
    case '[':
        return rvmFindClassUsingLoader(env, desc, classLoader);
    }
    assert(desc[0] == 'L');
    jint length = strlen(desc);
    char* className = rvmAllocateMemoryAtomic(env, length - 2 + 1);
    if (!className) return NULL;
    strncpy(className, &desc[1], length - 2);
    return rvmFindClassUsingLoader(env, className, classLoader);
}

char* rvmToBinaryClassName(Env* env, const char* className) {
    char* binName = rvmCopyMemoryAtomicZ(env, className);
    if (!binName) return NULL;
    jint i = 0;
    for (i = 0; binName[i] != '\0'; i++) {
        if (binName[i] == '/') binName[i] = '.';
    }
    return binName;
}

char* rvmFromBinaryClassName(Env* env, const char* binaryClassName) {
    char* className = rvmCopyMemoryAtomicZ(env, binaryClassName);
    if (!className) return NULL;
    jint i = 0;
    for (i = 0; className[i] != '\0'; i++) {
        if (className[i] == '.') className[i] = '/';
    }
    return className;
}

const char* rvmGetHumanReadableClassName(Env* env, Class* clazz) {
    // This code is a port of the dvmHumanReadableDescriptor() function in Android's Exception.cpp

    // Count the number of '['s to get the dimensionality.
    const char* c = clazz->name;
    size_t dim = 0;
    while (*c == '[') {
        dim++;
        c++;
    }

    // Reference or primitive?
    if (dim > 0 && *c == 'L') {
        // "[[La/b/C;" -> "a.b.C[][]".
        c++; // Skip the 'L'.
    } else if (dim > 0 || CLASS_IS_PRIMITIVE(clazz)) {
        // "[[B" -> "byte[][]".
        // To make life easier, we make primitives look like unqualified
        // reference types.
        switch (*c) {
        case 'B': c = "byte;"; break;
        case 'C': c = "char;"; break;
        case 'D': c = "double;"; break;
        case 'F': c = "float;"; break;
        case 'I': c = "int;"; break;
        case 'J': c = "long;"; break;
        case 'S': c = "short;"; break;
        case 'Z': c = "boolean;"; break;
        default: return clazz->name;
        }
    }

    // At this point, 'c' is a string of the form "fully/qualified/Type;"
    // or "primitive;". Rewrite the type with '.' instead of '/':
    char* result = rvmAllocateMemoryAtomic(env, strlen(c) + dim * 2 + 1);
    if (!result) return NULL;
    const char* p = c;
    jint index = 0;
    while (*p != ';' && *p != '\0') {
        char ch = *p++;
        if (ch == '/') {
            ch = '.';
        }
        result[index++] = ch;
    }
    // ...and replace the semicolon with 'dim' "[]" pairs:
    while (dim--) {
        result[index++] = '[';
        result[index++] = ']';
    }
    return result;
}

const char* rvmGetClassDescriptor(Env* env, Class* clazz) {
    jint length = strlen(clazz->name);
    char* desc = NULL;

    if (CLASS_IS_ARRAY(clazz) || CLASS_IS_PRIMITIVE(clazz)) {
        desc = (char*) clazz->name;
    } else {
        desc = rvmAllocateMemoryAtomic(env, length + 3);
        if (!desc) return NULL;
        desc[0] = 'L';
        strcat(desc, clazz->name);
        desc[length + 1] = ';';
    }

    return (const char*) desc;
}

jboolean rvmIsSubClass(Class* superclass, Class* clazz) {
    // TODO: Array types
    while (clazz && clazz != superclass) {
        clazz = clazz->superclass;
    }
    return clazz == superclass;
}

jboolean rvmIsSamePackage(Class* c1, Class* c2) {
    if (c1 == c2) return TRUE;
    if (c1->classLoader != c2->classLoader) return FALSE;
    // TODO: Array types
    char* s1 = strrchr(c1->name, '/');
    char* s2 = strrchr(c2->name, '/');
    if (!s1 || !s2) {
        return !s1 && !s2;
    }
    int l1 = s1 - c1->name;
    int l2 = s2 - c2->name;
    if (l1 != l2) {
        return FALSE;
    }
    return strncmp(c1->name, c2->name, l1) == 0;
}

jboolean rvmIsAssignableFrom(Env* env, Class* s, Class* t) {
    // TODO: What if s or t are NULL?
    if (s == t || t == java_lang_Object) {
        return TRUE;
    }

    TypeInfo* sti = s->typeInfo;
    TypeInfo* tti = t->typeInfo;
    uint32_t id = tti->id;
    if (sti->cache == id) {
        return TRUE;
    }

    if (CLASS_IS_INTERFACE(t)) {
        uint32_t ifCount = sti->interfaceCount;
        uint32_t* base = (uint32_t*) ((((char*) sti) + sti->offset) + sizeof(uint32_t));
        uint32_t i;
        for (i = 0; i < ifCount; i++) {
            if (*base == id) goto found;
            base++;
        }
        return FALSE;
    }

    // t must be a class or array class
    if (tti->offset <= sti->offset) {
        uint32_t* base = (uint32_t*) (((char*) sti) + tti->offset);
        if (*base == id) goto found;
    }

    // The TypeInfo of array classes doesn't give the complete information.
    if (CLASS_IS_ARRAY(t) && CLASS_IS_ARRAY(s) 
            && !CLASS_IS_PRIMITIVE(t->componentType) && !CLASS_IS_PRIMITIVE(s->componentType)) {
        return rvmIsAssignableFrom(env, s->componentType, t->componentType);
    }

    return FALSE;

found:
    sti->cache = id;
    return TRUE;
}

jboolean rvmIsInstanceOf(Env* env, Object* obj, Class* clazz) {
    if (!obj) return FALSE;
    return rvmIsAssignableFrom(env, obj->clazz, clazz);
}

static jboolean fixClassPointer(Env* env, Class* c, void* data) {
    c->object.clazz = java_lang_Class;
    return TRUE;
}

jboolean rvmInitClasses(Env* env) {

    if (rvmInitMutex(&classLock) != 0) {
        return FALSE;
    }

    gcAddRoot(&loadedClasses);

    // Cache important classes in java.lang.
    java_lang_Object = findBootClass(env, "java/lang/Object");
    if (!java_lang_Object) return FALSE;
    java_lang_Class = findBootClass(env, "java/lang/Class");
    if (!java_lang_Class) return FALSE;

    // Fix object.clazz pointers for the classes loaded so far
    rvmIterateLoadedClasses(env, fixClassPointer, NULL);

    java_lang_ClassLoader = findBootClass(env, "java/lang/ClassLoader");
    if (!java_lang_ClassLoader) return FALSE;
    java_lang_String = findBootClass(env, "java/lang/String");
    if (!java_lang_String) return FALSE;
    java_lang_Boolean = findBootClass(env, "java/lang/Boolean");
    if (!java_lang_Boolean) return FALSE;
    java_lang_Byte = findBootClass(env, "java/lang/Byte");
    if (!java_lang_Byte) return FALSE;
    java_lang_Character = findBootClass(env, "java/lang/Character");
    if (!java_lang_Character) return FALSE;
    java_lang_Short = findBootClass(env, "java/lang/Short");
    if (!java_lang_Short) return FALSE;
    java_lang_Integer = findBootClass(env, "java/lang/Integer");
    if (!java_lang_Integer) return FALSE;
    java_lang_Long = findBootClass(env, "java/lang/Long");
    if (!java_lang_Long) return FALSE;
    java_lang_Float = findBootClass(env, "java/lang/Float");
    if (!java_lang_Float) return FALSE;
    java_lang_Double = findBootClass(env, "java/lang/Double");
    if (!java_lang_Double) return FALSE;
    java_lang_Enum = findBootClass(env, "java/lang/Enum");
    if (!java_lang_Enum) return FALSE;
    java_lang_Cloneable = findBootClass(env, "java/lang/Cloneable");
    if (!java_lang_Cloneable) return FALSE;
    java_lang_Thread = findBootClass(env, "java/lang/Thread");
    if (!java_lang_Thread) return FALSE;
    java_lang_ThreadGroup = findBootClass(env, "java/lang/ThreadGroup");
    if (!java_lang_ThreadGroup) return FALSE;
    java_io_Serializable = findBootClass(env, "java/io/Serializable");
    if (!java_io_Serializable) return FALSE;
    java_lang_Runtime = findBootClass(env, "java/lang/Runtime");
    if (!java_lang_Runtime) return FALSE;

    java_lang_ClassNotFoundException = findBootClass(env, "java/lang/ClassNotFoundException");
    if (!java_lang_ClassNotFoundException) return FALSE;
    java_lang_NoClassDefFoundError = findBootClass(env, "java/lang/NoClassDefFoundError");
    if (!java_lang_NoClassDefFoundError) return FALSE;
    java_lang_Error = findBootClass(env, "java/lang/Error");
    if (!java_lang_Error) return FALSE;
    java_lang_OutOfMemoryError = findBootClass(env, "java/lang/OutOfMemoryError");
    if (!java_lang_OutOfMemoryError) return FALSE;
    java_lang_IllegalAccessError = findBootClass(env, "java/lang/IllegalAccessError");
    if (!java_lang_IllegalAccessError) return FALSE;
    java_lang_NoSuchFieldError = findBootClass(env, "java/lang/NoSuchFieldError");
    if (!java_lang_NoSuchFieldError) return FALSE;
    java_lang_NoSuchMethodError = findBootClass(env, "java/lang/NoSuchMethodError");
    if (!java_lang_NoSuchMethodError) return FALSE;
    java_lang_IncompatibleClassChangeError = findBootClass(env, "java/lang/IncompatibleClassChangeError");
    if (!java_lang_IncompatibleClassChangeError) return FALSE;
    java_lang_AbstractMethodError = findBootClass(env, "java/lang/AbstractMethodError");
    if (!java_lang_AbstractMethodError) return FALSE;
    java_lang_UnsatisfiedLinkError = findBootClass(env, "java/lang/UnsatisfiedLinkError");
    if (!java_lang_UnsatisfiedLinkError) return FALSE;
    java_lang_ExceptionInInitializerError = findBootClass(env, "java/lang/ExceptionInInitializerError");
    if (!java_lang_ExceptionInInitializerError) return FALSE;
    java_lang_VerifyError = findBootClass(env, "java/lang/VerifyError");
    if (!java_lang_VerifyError) return FALSE;
    java_lang_LinkageError = findBootClass(env, "java/lang/LinkageError");
    if (!java_lang_LinkageError) return FALSE;
    java_lang_InstantiationError = findBootClass(env, "java/lang/InstantiationError");
    if (!java_lang_InstantiationError) return FALSE;
    java_lang_StackOverflowError = findBootClass(env, "java/lang/StackOverflowError");
    if (!java_lang_StackOverflowError) return FALSE;
    java_lang_InternalError = findBootClass(env, "java/lang/InternalError");
    if (!java_lang_InternalError) return FALSE;

    java_lang_Throwable = findBootClass(env, "java/lang/Throwable");
    if (!java_lang_Throwable) return FALSE;
    java_lang_RuntimeException = findBootClass(env, "java/lang/RuntimeException");
    if (!java_lang_RuntimeException) return FALSE;
    java_lang_ClassCastException = findBootClass(env, "java/lang/ClassCastException");
    if (!java_lang_ClassCastException) return FALSE;
    java_lang_NullPointerException = findBootClass(env, "java/lang/NullPointerException");
    if (!java_lang_NullPointerException) return FALSE;
    java_lang_ArrayIndexOutOfBoundsException = findBootClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (!java_lang_ArrayIndexOutOfBoundsException) return FALSE;
    java_lang_ArrayStoreException = findBootClass(env, "java/lang/ArrayStoreException");
    if (!java_lang_ArrayStoreException) return FALSE;
    java_lang_NegativeArraySizeException = findBootClass(env, "java/lang/NegativeArraySizeException");
    if (!java_lang_NegativeArraySizeException) return FALSE;
    java_lang_IllegalArgumentException = findBootClass(env, "java/lang/IllegalArgumentException");
    if (!java_lang_IllegalArgumentException) return FALSE;
    java_lang_ArithmeticException = findBootClass(env, "java/lang/ArithmeticException");
    if (!java_lang_ArithmeticException) return FALSE;
    java_lang_UnsupportedOperationException = findBootClass(env, "java/lang/UnsupportedOperationException");
    if (!java_lang_UnsupportedOperationException) return FALSE;
    java_lang_IllegalMonitorStateException = findBootClass(env, "java/lang/IllegalMonitorStateException");
    if (!java_lang_IllegalMonitorStateException) return FALSE;
    java_lang_InstantiationException = findBootClass(env, "java/lang/InstantiationException");
    if (!java_lang_InstantiationException) return FALSE;
    java_lang_InterruptedException = findBootClass(env, "java/lang/InterruptedException");
    if (!java_lang_InterruptedException) return FALSE;
    java_lang_IllegalStateException = findBootClass(env, "java/lang/IllegalStateException");
    if (!java_lang_IllegalStateException) return FALSE;

    java_lang_ref_Reference = findBootClass(env, "java/lang/ref/Reference");
    if (!java_lang_ref_Reference) return FALSE;

    prim_Z = createPrimitiveClass(env, "Z");
    if (!prim_Z) return FALSE;
    prim_B = createPrimitiveClass(env, "B");
    if (!prim_B) return FALSE;
    prim_C = createPrimitiveClass(env, "C");
    if (!prim_C) return FALSE;
    prim_S = createPrimitiveClass(env, "S");
    if (!prim_S) return FALSE;
    prim_I = createPrimitiveClass(env, "I");
    if (!prim_I) return FALSE;
    prim_J = createPrimitiveClass(env, "J");
    if (!prim_J) return FALSE;
    prim_F = createPrimitiveClass(env, "F");
    if (!prim_F) return FALSE;
    prim_D = createPrimitiveClass(env, "D");
    if (!prim_D) return FALSE;
    prim_V = createPrimitiveClass(env, "V");
    if (!prim_V) return FALSE;

    array_Z = findBootClass(env, "[Z");
    if (!array_Z) return FALSE;
    array_B = findBootClass(env, "[B");
    if (!array_B) return FALSE;
    array_C = findBootClass(env, "[C");
    if (!array_C) return FALSE;
    array_S = findBootClass(env, "[S");
    if (!array_S) return FALSE;
    array_I = findBootClass(env, "[I");
    if (!array_I) return FALSE;
    array_J = findBootClass(env, "[J");
    if (!array_J) return FALSE;
    array_F = findBootClass(env, "[F");
    if (!array_F) return FALSE;
    array_D = findBootClass(env, "[D");
    if (!array_D) return FALSE;

    return TRUE;
}

jboolean rvmInitPrimitiveWrapperClasses(Env* env) {
    Class* c = NULL;
    ClassField* f = NULL;

    f = rvmGetClassField(env, java_lang_Boolean, "TRUE", "Ljava/lang/Boolean;");
    if (!f) return FALSE;
    java_lang_Boolean_TRUE = (Boolean*) rvmGetObjectClassFieldValue(env, java_lang_Boolean, f);
    if (!java_lang_Boolean_TRUE) return FALSE;

    f = rvmGetClassField(env, java_lang_Boolean, "FALSE", "Ljava/lang/Boolean;");
    if (!f) return FALSE;
    java_lang_Boolean_FALSE = (Boolean*) rvmGetObjectClassFieldValue(env, java_lang_Boolean, f);
    if (!java_lang_Boolean_FALSE) return FALSE;

    java_lang_Byte_valueOf = rvmGetClassMethod(env, java_lang_Byte, "valueOf", "(B)Ljava/lang/Byte;");
    if (!java_lang_Byte_valueOf) return FALSE;
    f = rvmGetClassField(env, java_lang_Byte, "VALUES", "[Ljava/lang/Byte;");
    if (!f) return FALSE;
    bytesCache = (ObjectArray*) rvmGetObjectClassFieldValue(env, java_lang_Byte, f);
    if (!bytesCache) return FALSE;

    java_lang_Short_valueOf = rvmGetClassMethod(env, java_lang_Short, "valueOf", "(S)Ljava/lang/Short;");
    if (!java_lang_Short_valueOf) return FALSE;
    f = rvmGetClassField(env, java_lang_Short, "SMALL_VALUES", "[Ljava/lang/Short;");
    if (!f) return FALSE;
    shortsCache = (ObjectArray*) rvmGetObjectClassFieldValue(env, c, f);
    if (!shortsCache) return FALSE;

    java_lang_Character_valueOf = rvmGetClassMethod(env, java_lang_Character, "valueOf", "(C)Ljava/lang/Character;");
    if (!java_lang_Character_valueOf) return FALSE;
    f = rvmGetClassField(env, java_lang_Character, "SMALL_VALUES", "[Ljava/lang/Character;");
    if (!f) return FALSE;
    charactersCache = (ObjectArray*) rvmGetObjectClassFieldValue(env, c, f);
    if (!charactersCache) return FALSE;

    java_lang_Integer_valueOf = rvmGetClassMethod(env, java_lang_Integer, "valueOf", "(I)Ljava/lang/Integer;");
    if (!java_lang_Integer_valueOf) return FALSE;
    f = rvmGetClassField(env, java_lang_Integer, "SMALL_VALUES", "[Ljava/lang/Integer;");
    if (!f) return FALSE;
    integersCache = (ObjectArray*) rvmGetObjectClassFieldValue(env, c, f);
    if (!integersCache) return FALSE;

    java_lang_Long_valueOf = rvmGetClassMethod(env, java_lang_Long, "valueOf", "(J)Ljava/lang/Long;");
    if (!java_lang_Long_valueOf) return FALSE;
    f = rvmGetClassField(env, java_lang_Long, "SMALL_VALUES", "[Ljava/lang/Long;");
    if (!f) return FALSE;
    longsCache = (ObjectArray*) rvmGetObjectClassFieldValue(env, c, f);
    if (!longsCache) return FALSE;

    java_lang_Float_valueOf = rvmGetClassMethod(env, java_lang_Float, "valueOf", "(F)Ljava/lang/Float;");
    if (!java_lang_Float_valueOf) return FALSE;

    java_lang_Double_valueOf = rvmGetClassMethod(env, java_lang_Double, "valueOf", "(D)Ljava/lang/Double;");
    if (!java_lang_Double_valueOf) return FALSE;

    return TRUE;
}

Class* rvmFindClass(Env* env, const char* className) {
    Method* method = rvmGetCallingMethod(env);
    if (rvmExceptionOccurred(env)) return NULL;
    ClassLoader* classLoader = method ? method->clazz->classLoader : NULL;
    return rvmFindClassUsingLoader(env, className, classLoader);
}

Class* rvmFindClassInClasspathForLoader(Env* env, const char* className, ClassLoader* classLoader) {
    if (!classLoader || classLoader->parent == NULL) {
        // This is the bootstrap classloader
        return findBootClass(env, className);
    }
    if (classLoader->parent->parent == NULL && classLoader->object.clazz->classLoader == NULL) {
        // This is the system classloader
        Class* clazz = findClass(env, className, classLoader, env->vm->options->loadUserClass);
        if (rvmExceptionOccurred(env)) return NULL;
        return clazz;
    }
    rvmThrowClassNotFoundException(env, className);
    return NULL;
}

Class* rvmFindClassUsingLoader(Env* env, const char* className, ClassLoader* classLoader) {
    if (!classLoader || classLoader->parent == NULL) {
        // This is the bootstrap classloader. No need to call ClassLoader.loadClass()
        return findBootClass(env, className);
    }
    char* binaryClassName = rvmToBinaryClassName(env, className);
    if (!binaryClassName) return NULL;
    Object* binaryClassNameString = rvmNewInternedStringUTF(env, binaryClassName, -1);
    if (!binaryClassNameString) return NULL;
    Method* loadClassMethod = rvmGetInstanceMethod(env, java_lang_ClassLoader, "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;");
    if (!loadClassMethod) return NULL;
    Object* clazz = rvmCallObjectInstanceMethod(env, (Object*) classLoader, loadClassMethod, binaryClassNameString);
    if (rvmExceptionOccurred(env)) return NULL;
    return (Class*) clazz;
}

Class* rvmFindLoadedClass(Env* env, const char* className, ClassLoader* classLoader) {
    Class* clazz = getLoadedClass(env, className);
    if (rvmExceptionOccurred(env)) return NULL;
    return clazz;
}

ClassLoader* rvmGetSystemClassLoader(Env* env) {
    Class* holder = rvmFindClassUsingLoader(env, "java/lang/ClassLoader$SystemClassLoader", NULL);
    if (!holder) return NULL;
    ClassField* field = rvmGetClassField(env, holder, "loader", "Ljava/lang/ClassLoader;");
    if (!field) return NULL;
    return (ClassLoader*) rvmGetObjectClassFieldValue(env, holder, field);
}

Class* rvmAllocateClass(Env* env, const char* className, Class* superclass, ClassLoader* classLoader, jint flags, TypeInfo* typeInfo,
        VITable* vitable, ITables* itables, jint classDataSize, jint instanceDataSize, jint instanceDataOffset, unsigned short classRefCount, 
        unsigned short instanceRefCount, void* attributes, void* initializer) {

    assert((flags & CLASS_STATE_MASK) == 0);

    if (superclass && CLASS_IS_INTERFACE(superclass)) {
        // TODO: Message should look like ?
        rvmThrowIncompatibleClassChangeError(env, "");
        return NULL;
    }

    Class* clazz = rvmAllocateMemoryForClass(env, classDataSize);
    if (!clazz) return NULL;

    /*
     * NOTE: All classes we load before we have cached java.lang.Class will have NULL here so it is 
     * important that we cache java.lang.Class as soon as possible. However, we have to cache
     * java.lang.Object first since it is the superclass of java.lang.Class. This means that
     * the java_lang_Object global variable will actually have NULL as clazz until we fix this in
     * rvmInitClasses().
     */
    clazz->object.clazz = java_lang_Class;
    clazz->name = className;
    clazz->superclass = superclass;
    clazz->classLoader = classLoader;
    clazz->typeInfo = typeInfo;
    clazz->vitable = vitable;
    clazz->itables = itables ? itables : &emptyITables;
    clazz->flags = flags;
    clazz->classDataSize = classDataSize;
    clazz->instanceDataSize = instanceDataSize;
    clazz->instanceDataOffset = instanceDataOffset;
    clazz->classRefCount = classRefCount;
    clazz->instanceRefCount = instanceRefCount;
    clazz->_interfaces = &INTERFACES_NOT_LOADED;
    clazz->_fields = &FIELDS_NOT_LOADED;
    clazz->_methods = &METHODS_NOT_LOADED;
    clazz->attributes = attributes;
    clazz->initializer = initializer;

    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_ALLOCATED;

    // Inherit the CLASS_FLAG_FINALIZABLE flag from the superclass
    if (superclass && !CLASS_IS_FINALIZABLE(clazz) && CLASS_IS_FINALIZABLE(superclass)) {
        clazz->flags |= CLASS_FLAG_FINALIZABLE;
    }
    // Inherit the CLASS_FLAG_REFERENCE flag from the superclass
    if (superclass && (superclass == java_lang_ref_Reference || CLASS_IS_REFERENCE(superclass))) {
        clazz->flags |= CLASS_FLAG_REFERENCE;
    }
    // Set CLASS_FLAG_REF_FREE if the superclass has this flag set and this class has 0
    // instance reference fields
    if ((!superclass || CLASS_IS_REF_FREE(superclass)) && instanceRefCount == 0) {
        clazz->flags |= CLASS_FLAG_REF_FREE;
    }

    return clazz;
}

Interface* rvmAllocateInterface(Env* env, Class* interf) {
    Interface* interface = rvmAllocateMemoryAtomicUncollectable(env, sizeof(Interface));
    if (!interface) return NULL;
    interface->interface = interf;
    return interface;
}

jboolean rvmAddInterface(Env* env, Class* clazz, Class* interf) {
    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    if (!CLASS_IS_INTERFACE(interf)) {
        // TODO: Message should look like ?
        rvmThrowIncompatibleClassChangeError(env, "");
        return FALSE;
    }
    Interface* interface = rvmAllocateInterface(env, interf);
    if (!interface) return FALSE;
    if (clazz->_interfaces == &INTERFACES_NOT_LOADED) {
        clazz->_interfaces = NULL;
    }
    LL_APPEND(clazz->_interfaces, interface);
    return TRUE;
}

Method* rvmAllocateMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, void* synchronizedImpl, void* attributes) {

    Method* method = rvmAllocateMemoryAtomicUncollectable(env, IS_NATIVE(access) ? sizeof(NativeMethod) : sizeof(Method));
    if (!method) return NULL;
    method->clazz = clazz;
    method->name = name;
    method->desc = desc;
    method->vitableIndex = vitableIndex;
    method->access = access;
    method->size = size;
    method->impl = impl;
    method->synchronizedImpl = synchronizedImpl;
    method->attributes = attributes;
    return method;
}

Method* rvmAddMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, void* synchronizedImpl, void* attributes) {

    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    Method* method = rvmAllocateMethod(env, clazz, name, desc, vitableIndex, access, size, impl, synchronizedImpl, attributes);
    if (!method) return NULL;

    if (clazz->_methods == &METHODS_NOT_LOADED) {
        clazz->_methods = NULL;
    }

    method->next = clazz->_methods;
    clazz->_methods = method;

    return method;
}

ProxyMethod* addProxyMethod(Env* env, Class* clazz, Method* proxiedMethod, jint access, void* impl) {
    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    ProxyMethod* method = rvmAllocateMemoryAtomicUncollectable(env, sizeof(ProxyMethod));
    if (!method) return NULL;
    method->method.clazz = clazz;
    method->method.name = proxiedMethod->name;
    method->method.desc = proxiedMethod->desc;
    method->method.vitableIndex = proxiedMethod->vitableIndex;
    method->method.access = access | METHOD_TYPE_PROXY;
    method->method.impl = impl;
    method->method.synchronizedImpl = NULL;
    method->proxiedMethod = proxiedMethod;

    if (clazz->_methods == &METHODS_NOT_LOADED) {
        clazz->_methods = NULL;
    }

    method->method.next = clazz->_methods;
    clazz->_methods = (Method*) method;

    return method;
}

BridgeMethod* rvmAllocateBridgeMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, 
        void* synchronizedImpl, void** targetFnPtr, void* attributes) {

    BridgeMethod* method = rvmAllocateMemoryAtomicUncollectable(env, sizeof(BridgeMethod));
    if (!method) return NULL;
    method->method.clazz = clazz;
    method->method.name = name;
    method->method.desc = desc;
    method->method.vitableIndex = vitableIndex;
    method->method.access = access | METHOD_TYPE_BRIDGE;
    method->method.size = size;
    method->method.impl = impl;
    method->method.synchronizedImpl = synchronizedImpl;
    method->method.attributes = attributes;
    method->targetFnPtr = targetFnPtr;
    return method;
}

BridgeMethod* rvmAddBridgeMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, 
        void* synchronizedImpl, void** targetFnPtr, void* attributes) {
    
    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    BridgeMethod* method = rvmAllocateBridgeMethod(env, clazz, name, desc, access, 
                                    vitableIndex, size, impl, synchronizedImpl, 
                                    targetFnPtr, attributes);
    if (!method) return NULL;

    if (clazz->_methods == &METHODS_NOT_LOADED) {
        clazz->_methods = NULL;
    }

    method->method.next = clazz->_methods;
    clazz->_methods = (Method*) method;

    return method;
}

CallbackMethod* rvmAllocateCallbackMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, 
        void* synchronizedImpl, void* callbackImpl, void* attributes) {

    CallbackMethod* method = rvmAllocateMemoryAtomicUncollectable(env, sizeof(CallbackMethod));
    if (!method) return NULL;
    method->method.clazz = clazz;
    method->method.name = name;
    method->method.desc = desc;
    method->method.vitableIndex = vitableIndex;
    method->method.access = access | METHOD_TYPE_CALLBACK;
    method->method.size = size;
    method->method.impl = impl;
    method->method.synchronizedImpl = synchronizedImpl;
    method->method.attributes = attributes;
    method->callbackImpl = callbackImpl;
    return method;
}

CallbackMethod* rvmAddCallbackMethod(Env* env, Class* clazz, const char* name, const char* desc, 
        jint vitableIndex, jint access, jint size, void* impl, 
        void* synchronizedImpl, void* callbackImpl, void* attributes) {
    
    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    CallbackMethod* method = rvmAllocateCallbackMethod(env, clazz, name, desc, access, 
                                        vitableIndex, size, impl, synchronizedImpl, 
                                        callbackImpl, attributes);
    if (!method) return NULL;

    if (clazz->_methods == &METHODS_NOT_LOADED) {
        clazz->_methods = NULL;
    }

    method->method.next = clazz->_methods;
    clazz->_methods = (Method*) method;

    return method;
}

Field* rvmAllocateField(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint offset, void* attributes) {
    Field* field = rvmAllocateMemoryAtomicUncollectable(env, IS_STATIC(access) ? sizeof(ClassField) : sizeof(InstanceField));
    if (!field) return NULL;
    field->clazz = clazz;
    field->name = name;
    field->desc = desc;
    field->access = access;
    field->attributes = attributes;
    if (IS_STATIC(access)) {
        ((ClassField*) field)->address = ((jbyte*) clazz) + offset;
    } else {
        ((InstanceField*) field)->offset = offset;
    }
    return field;
}

Field* rvmAddField(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint offset, void* attributes) {
    assert(CLASS_IS_STATE_ALLOCATED(clazz));
    Field* field = rvmAllocateField(env, clazz, name, desc, access, offset, attributes);
    if (!field) return NULL;

    if (clazz->_fields == &FIELDS_NOT_LOADED) {
        clazz->_fields = NULL;
    }

    field->next = clazz->_fields;
    clazz->_fields = field;

    return field;
}

Interface* rvmGetInterfaces(Env* env, Class* clazz) {
    if (clazz->_interfaces != &INTERFACES_NOT_LOADED) return clazz->_interfaces;

    obtainClassLock();
    if (clazz->_interfaces == &INTERFACES_NOT_LOADED) {
        Interface* interfaces = env->vm->options->loadInterfaces(env, clazz);
        if (!rvmExceptionCheck(env)) {
            rvmAtomicStorePtr((void**) &clazz->_interfaces, interfaces);
        }
    }
    releaseClassLock();
    if (rvmExceptionCheck(env)) return NULL;
    return clazz->_interfaces;
}

Field* rvmGetFields(Env* env, Class* clazz) {
    if (clazz->_fields != &FIELDS_NOT_LOADED) return clazz->_fields;

    obtainClassLock();
    if (clazz->_fields == &FIELDS_NOT_LOADED) {
        Field* fields = env->vm->options->loadFields(env, clazz);
        if (!rvmExceptionCheck(env)) {
            rvmAtomicStorePtr((void**) &clazz->_fields, fields);
        }
    }
    releaseClassLock();
    if (rvmExceptionCheck(env)) return NULL;
    return clazz->_fields;
}

Method* rvmGetMethods(Env* env, Class* clazz) {
    if (clazz->_methods != &METHODS_NOT_LOADED) return clazz->_methods;

    obtainClassLock();
    if (clazz->_methods == &METHODS_NOT_LOADED) {
        Method* methods = env->vm->options->loadMethods(env, clazz);
        if (!rvmExceptionCheck(env)) {
            rvmAtomicStorePtr((void**) &clazz->_methods, methods);
        }
    }
    releaseClassLock();
    if (rvmExceptionCheck(env)) return NULL;
    return clazz->_methods;
}

jboolean rvmRegisterClass(Env* env, Class* clazz) {
    assert(CLASS_IS_STATE_ALLOCATED(clazz));

    // TODO: Check that the superclass and all interfaces are accessible to the new class
    // TODO: Verify the class hierarchy (class doesn't override final methods, changes public -> private, etc)

    obtainClassLock();
    if (!addLoadedClass(env, clazz)) {
        releaseClassLock();
        return FALSE;
    }

    if (!rvmAddObjectGCRoot(env, (Object*) clazz)) {
        releaseClassLock();
        return FALSE;
    }

    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_LOADED;

    releaseClassLock();
    return TRUE;
}

void rvmInitialize(Env* env, Class* clazz) {
    assert(env->currentThread != NULL);

    // The initialization of a class or interface is described in the JVMS JavaSE7 edition section 5.5
    rvmLockObject(env, (Object*) clazz);

    while (CLASS_IS_STATE_INITIALIZING(clazz)) {

         if (clazz->initThread != env->currentThread) {
            rvmObjectWait(env, (Object*) clazz, 0, 0, FALSE);
         } else {
            // Recursive initialization. Release lock and return normally.
            rvmUnlockObject(env, (Object*) clazz);
            return;
         }

    }

    if (CLASS_IS_STATE_INITIALIZED(clazz)) {
        // Initialized. Release lock and return normally.
        rvmUnlockObject(env, (Object*) clazz);
        return;
    }

    if (CLASS_IS_STATE_ERROR(clazz)) {
        rvmThrowNewf(env, java_lang_NoClassDefFoundError, "Could not initialize class %s", 
            rvmToBinaryClassName(env, clazz->name));
        rvmUnlockObject(env, (Object*) clazz);
        return;
    }

    assert(clazz->initThread == NULL);
    assert(CLASS_IS_STATE_LOADED(clazz));

    // Indicate that this thread is currently initializing the class and release the lock.
    clazz->initThread = env->currentThread;
    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_INITIALIZING;
    rvmUnlockObject(env, (Object*) clazz);

    TRACEF("Initializing class %s", clazz->name);

    // NOTE: The JVMS says to initialize static fields before initializing the 
    // superclass. Dalvik initializes the superclass first. We do what Dalvik does.
    if (clazz->superclass) {
        // Initialize the superclass
        rvmInitialize(env, clazz->superclass);
        if (rvmExceptionOccurred(env)) {
            rvmLockObject(env, (Object*) clazz);
            clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_ERROR;
            rvmObjectNotifyAll(env, (Object*) clazz);
            rvmUnlockObject(env, (Object*) clazz);
            return;
        }
    }

    void* initializer = clazz->initializer;
    if (!initializer) {
        // No <clinit> in class
        if (!CLASS_IS_ARRAY(clazz) && !CLASS_IS_PROXY(clazz) && !CLASS_IS_PRIMITIVE(clazz)) {
            env->vm->options->classInitialized(env, clazz);
        }
        rvmLockObject(env, (Object*) clazz);
        clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_INITIALIZED;
        rvmObjectNotifyAll(env, (Object*) clazz);
        rvmUnlockObject(env, (Object*) clazz);
        return;
    }

    CallInfo* callInfo = call0AllocateCallInfo(env, initializer, 1, 0, 0, 0, 0);
    call0AddPtr(callInfo, env);
    void (*f)(CallInfo*) = (void (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);

    Object* exception = rvmExceptionClear(env);
    if (!exception) {
        // Successful initialization
        if (!CLASS_IS_ARRAY(clazz) && !CLASS_IS_PROXY(clazz) && !CLASS_IS_PRIMITIVE(clazz)) {
            env->vm->options->classInitialized(env, clazz);
        }
        rvmLockObject(env, (Object*) clazz);
        clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_INITIALIZED;
        rvmObjectNotifyAll(env, (Object*) clazz);
        rvmUnlockObject(env, (Object*) clazz);
        return;
    }

    // <clinit> failed with an exception

    if (!rvmIsInstanceOf(env, exception, java_lang_Error)) {
        // If exception isn't an instance of java.lang.Error 
        // we must wrap it in a java.lang.ExceptionInInitializerError
        Method* constructor = rvmGetInstanceMethod(env, java_lang_ExceptionInInitializerError, "<init>", "(Ljava/lang/Throwable;)V");
        if (constructor) {
            Object* wrappedException = rvmNewObject(env, java_lang_ExceptionInInitializerError, constructor, exception);
            if (wrappedException) {
                rvmThrow(env, wrappedException);
            }
        }
    } else {
        rvmThrow(env, exception);
    }

    rvmLockObject(env, (Object*) clazz);
    clazz->flags = (clazz->flags & (~CLASS_STATE_MASK)) | CLASS_STATE_ERROR;
    rvmObjectNotifyAll(env, (Object*) clazz);
    rvmUnlockObject(env, (Object*) clazz);
}

Object* rvmAllocateObject(Env* env, Class* clazz) {
    if (CLASS_IS_ABSTRACT(clazz) || CLASS_IS_INTERFACE(clazz)) {
        // TODO: Message
        rvmThrowNew(env, java_lang_InstantiationException, "");
        return NULL;
    }
    rvmInitialize(env, clazz);
    if (rvmExceptionOccurred(env)) return NULL;
    Object* obj = rvmAllocateMemoryForObject(env, clazz);
    if (!obj) return NULL;
    obj->clazz = clazz;
    return obj;
}

Object* rvmNewObject(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmNewObjectV(env, clazz, method, args);
}

Object* rvmNewObjectA(Env* env, Class* clazz, Method* method, jvalue *args) {
    Object* obj = rvmAllocateObject(env, clazz);
    if (!obj) return NULL;
    rvmCallNonvirtualVoidInstanceMethodA(env, obj, method, args);
    if (rvmExceptionOccurred(env)) return NULL;
    return obj;
}

Object* rvmNewObjectV(Env* env, Class* clazz, Method* method, va_list args) {
    Object* obj = rvmAllocateObject(env, clazz);
    if (!obj) return NULL;
    rvmCallNonvirtualVoidInstanceMethodV(env, obj, method, args);
    if (rvmExceptionOccurred(env)) return NULL;
    return obj;
}

Boolean* rvmBoxBoolean(Env* env, jboolean value) {
    return value ? java_lang_Boolean_TRUE : java_lang_Boolean_FALSE;
}

Byte* rvmBoxByte(Env* env, jbyte value) {
    jint index = value + 128;
    if (index >= 0 && index < bytesCache->length && bytesCache->values[index] != NULL) {
        return (Byte*) bytesCache->values[index];
    }
    jvalue args[1];
    args[0].b = value;
    return (Byte*) rvmCallObjectClassMethodA(env, java_lang_Byte, java_lang_Byte_valueOf, args);
}

Short* rvmBoxShort(Env* env, jshort value) {
    jint index = value + 128;
    if (index >= 0 && index < shortsCache->length && shortsCache->values[index] != NULL) {
        return (Short*) shortsCache->values[index];
    }
    jvalue args[1];
    args[0].s = value;
    return (Short*) rvmCallObjectClassMethodA(env, java_lang_Short, java_lang_Short_valueOf, args);
}

Character* rvmBoxChar(Env* env, jchar value) {
    jint index = value;
    if (index >= 0 && index < charactersCache->length && charactersCache->values[index] != NULL) {
        return (Character*) charactersCache->values[index];
    }
    jvalue args[1];
    args[0].c = value;
    return (Character*) rvmCallObjectClassMethodA(env, java_lang_Character, java_lang_Character_valueOf, args);
}

Integer* rvmBoxInt(Env* env, jint value) {
    jint index = value + 128;
    if (index >= 0 && index < integersCache->length && integersCache->values[index] != NULL) {
        return (Integer*) integersCache->values[index];
    }
    jvalue args[1];
    args[0].i = value;
    return (Integer*) rvmCallObjectClassMethodA(env, java_lang_Integer, java_lang_Integer_valueOf, args);
}

Long* rvmBoxLong(Env* env, jlong value) {
    jint index = value + 128;
    if (index >= 0 && index < longsCache->length && longsCache->values[index] != NULL) {
        return (Long*) longsCache->values[index];
    }
    jvalue args[1];
    args[0].j = value;
    return (Long*) rvmCallObjectClassMethodA(env, java_lang_Long, java_lang_Long_valueOf, args);
}

Float* rvmBoxFloat(Env* env, jfloat value) {
    jvalue args[1];
    args[0].f = value;
    return (Float*) rvmCallObjectClassMethodA(env, java_lang_Float, java_lang_Float_valueOf, args);
}

Double* rvmBoxDouble(Env* env, jdouble value) {
    jvalue args[1];
    args[0].d = value;
    return (Double*) rvmCallObjectClassMethodA(env, java_lang_Double, java_lang_Double_valueOf, args);
}

Object* rvmBox(Env* env, Class* type, jvalue* value) {
    if (CLASS_IS_PRIMITIVE(type)) {
        switch (type->name[0]) {
        case 'Z':
            return (Object*) rvmBoxBoolean(env, value->z);
        case 'B':
            return (Object*) rvmBoxByte(env, value->b);
        case 'S':
            return (Object*) rvmBoxShort(env, value->s);
        case 'C':
            return (Object*) rvmBoxChar(env, value->c);
        case 'I':
            return (Object*) rvmBoxInt(env, value->i);
        case 'J':
            return (Object*) rvmBoxLong(env, value->j);
        case 'F':
            return (Object*) rvmBoxFloat(env, value->f);
        case 'D':
            return (Object*) rvmBoxDouble(env, value->d);
        }
    }
    return (Object*) value->l;
}

jboolean rvmUnboxBoolean(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Boolean) {
        rvmThrowClassCastException(env, java_lang_Boolean, arg->clazz);
        return FALSE;
    }
    value->z = ((Boolean*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxByte(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Byte) {
        rvmThrowClassCastException(env, java_lang_Byte, arg->clazz);
        return FALSE;
    }
    value->b = ((Byte*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxChar(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Character) {
        rvmThrowClassCastException(env, java_lang_Character, arg->clazz);
        return FALSE;
    }
    value->c = ((Character*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxShort(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Short) {
        if (rvmUnboxByte(env, arg, value)) {
            value->s = value->b;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        rvmThrowClassCastException(env, java_lang_Short, arg->clazz);
        return FALSE;
    }
    value->s = ((Short*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxInt(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Integer) {
        if (rvmUnboxChar(env, arg, value)) {
            value->i = value->c;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        if (rvmUnboxShort(env, arg, value)) {
            value->i = value->s;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        rvmThrowClassCastException(env, java_lang_Integer, arg->clazz);
        return FALSE;
    }
    value->i = ((Integer*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxLong(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Long) {
        if (rvmUnboxInt(env, arg, value)) {
            value->j = value->i;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        rvmThrowClassCastException(env, java_lang_Long, arg->clazz);
        return FALSE;
    }
    value->j = ((Long*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxFloat(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Float) {
        if (rvmUnboxLong(env, arg, value)) {
            value->f = value->j;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        rvmThrowClassCastException(env, java_lang_Float, arg->clazz);
        return FALSE;
    }
    value->f = ((Float*) arg)->value;
    return TRUE;
}

jboolean rvmUnboxDouble(Env* env, Object* arg, jvalue* value) {
    if (!arg) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    if (arg->clazz != java_lang_Double) {
        if (rvmUnboxLong(env, arg, value)) {
            value->d = value->j;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        if (rvmUnboxFloat(env, arg, value)) {
            value->d = value->f;
            return TRUE;
        } else {
            rvmExceptionClear(env);
        }
        rvmThrowClassCastException(env, java_lang_Double, arg->clazz);
        return FALSE;
    }
    value->d = ((Double*) arg)->value;
    return TRUE;
}

jboolean rvmUnbox(Env* env, Object* arg, Class* type, jvalue* value) {
    jboolean (*unboxFunc)(Env*, Object*, jvalue*) = NULL;
    if (type == prim_Z) {
        unboxFunc = rvmUnboxBoolean;
    } else if (type == prim_B) {
        unboxFunc = rvmUnboxByte;
    } else if (type == prim_C) {
        unboxFunc = rvmUnboxChar;
    } else if (type == prim_S) {
        unboxFunc = rvmUnboxShort;
    } else if (type == prim_I) {
        unboxFunc = rvmUnboxInt;
    } else if (type == prim_J) {
        unboxFunc = rvmUnboxLong;
    } else if (type == prim_F) {
        unboxFunc = rvmUnboxFloat;
    } else if (type == prim_D) {
        unboxFunc = rvmUnboxDouble;
    }

    if (!unboxFunc) {
        // No unboxing needed
        value->l = (jobject) arg;
        return TRUE;
    }
    return unboxFunc(env, arg, value);
}

Object* rvmCloneObject(Env* env, Object* obj) {
    if (CLASS_IS_ARRAY(obj->clazz)) {
        return (Object*) rvmCloneArray(env, (Array*) obj);
    }
    jint size = obj->clazz->instanceDataSize;
    Object* copy = rvmAllocateMemoryForObject(env, obj->clazz);
    if (!copy) return NULL;
    memcpy(copy, obj, size);
    copy->lock = 0;
    return copy;
}

void rvmIterateLoadedClasses(Env* env, jboolean (*f)(Env*, Class*, void*), void* data) {
    LoadedClassEntry* entry;
    for (entry = loadedClasses; entry != NULL; entry = entry->hh.next) {
        if (!f(env, entry->clazz, data)) return;
    }
}

static jboolean dumpClassesIterator(Env* env, Class* clazz, void* d) {
    fprintf(stderr, "%p: %s\n", clazz, clazz->name);
    return TRUE;
}

void rvmDumpLoadedClasses(Env* env) {
    rvmIterateLoadedClasses(env, dumpClassesIterator, NULL);
}

