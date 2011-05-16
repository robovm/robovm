#include <nullvm.h>
#include <stdlib.h>
#include <string.h>
#include <hyport.h>
#include "log.h"

Class* java_lang_Object;
Class* java_lang_Class;
Class* java_lang_String;
Class* java_lang_Boolean;
Class* java_lang_Byte;
Class* java_lang_Character;
Class* java_lang_Short;
Class* java_lang_Integer;
Class* java_lang_Long;
Class* java_lang_Float;
Class* java_lang_Double;
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

static DynamicLib* bootSoHandles = NULL;
static DynamicLib* mainSoHandles = NULL;

// TODO: Protect these with locks
static Map* nameToClassMap = NULL;
static Map* idToClassMap = NULL;

static jint nextClassId = 0;

static Class* findClassByDescriptor(Env* env, char* desc, ClassLoader* classLoader, DynamicLib* dlib);
static Class* findClass(Env* env, char* className, ClassLoader* classLoader, DynamicLib* dlib);
static Class* findBootClass(Env* env, char* className);

static inline jint getNextClassId(void) {
    return __sync_fetch_and_add(&nextClassId, 1);
}

static inline Class* getLoadedClassByName(Env* env, char* className) {
    return nvmMapGet(env, nameToClassMap, (MapKey) {.p = className});
}

static inline Class* getLoadedClassById(Env* env, jint id) {
    return (Class*) nvmMapGet(env, idToClassMap, (MapKey) {.i = id});
}

static inline jboolean addLoadedClass(Env* env, Class* clazz) {
    nvmMapPut(env, nameToClassMap, (MapKey) {.p = clazz->name}, clazz);
    if (nvmExceptionOccurred(env)) return FALSE;
    nvmMapPut(env, idToClassMap, (MapKey) {.i = clazz->id}, clazz);
    if (nvmExceptionOccurred(env)) return FALSE;
    return TRUE;
}

static void loadSoHandles(Env* env, ClasspathEntry* entry, DynamicLib** first) {
    while (entry) {
        DynamicLib* dlib = nvmLoadDynamicLib(env, entry->soPath, first);
        if (!dlib) {
            nvmAbort("Fatal error: Failed to load %s", entry->soPath);
        }
        entry = entry->next;
    }
}

static void initSoHandles(Env* env) {
    loadSoHandles(env, env->options->bootclasspath, &bootSoHandles);
    loadSoHandles(env, env->options->classpath, &mainSoHandles);
}

static jint j_get_vtable_index(Class* clazz, char* name, char* desc, Class* caller) {
    Method* method;
    int same_class;
    int sub_class;
    int same_package;

    if (clazz->superclass && strcmp("<init>", name) && strcmp("<clinit>", name)) {
        /* 
         * Check with the superclass first. Note that constructors and static 
         * initializers are not inherited.
         */
        jint index = j_get_vtable_index(clazz->superclass, name, desc, caller);
        if (index != -1) {
            return index;
        }
    }

    same_class = clazz == caller;
    sub_class = nvmIsSubClass(clazz, caller);
    same_package = nvmIsSamePackage(clazz, caller);

    for (method = clazz->methods->first; method != NULL; method = method->next) {
        jint access = method->access;
        if (access & ACC_PRIVATE && !same_class) {
            continue;
        }
        if (access & ACC_PROTECTED && !sub_class) {
            continue;
        }
        if (!(access & ACC_PRIVATE) && !(access & ACC_PROTECTED) && !(access & ACC_PUBLIC) && !same_package) {
            // Package private
            continue;
        }
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return method->vtableIndex;
        }
    }
    return -1;
}

static char* hexChars = "0123456789ABCDEF";

static char* mangleClassName(Env* env, char* s) {
    jint i, j;
    jint k = strlen(s);
    jint l = k + strlen("NullVM_");

    // Determine the length of the mangled string
    for (i = 0; i < k; i++) {
        char c = s[i];
        if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '/')) {
            l += 2;
        }
    }

    char* result = nvmAllocateMemory(env, l + 1);
    if (!result) return NULL;

    strcpy(result, "NullVM_");
    for (i = 0, j = strlen("NullVM_"); i < k; i++) {
        char c = s[i];
        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            result[j++] = c;
        } else if (c == '/') {
            result[j++] = '_';
        } else {
            result[j++] = '$';
            result[j++] = hexChars[(c >> 4) & 0xf];
            result[j++] = hexChars[c & 0xf];
        }
    }

    return result;
}

static Class* createPrimitiveClass(Env* env, char* desc) {
    Class* clazz = nvmAllocateMemory(env, sizeof(Class));
    if (!clazz) return NULL;
    clazz->name = desc;
    clazz->state = CLASS_INITIALIZED;
    clazz->object.clazz = java_lang_Class;
    clazz->access = ACC_PUBLIC | ACC_FINAL | ACC_ABSTRACT;
    clazz->primitive = TRUE;
    return clazz;
}

static Class* createArrayClass(Env* env, Class* componentType, DynamicLib* dlib) {
    jint length = strlen(componentType->name);
    char* desc = NULL;

    if (CLASS_IS_ARRAY(componentType) || componentType->primitive) {
        desc = nvmAllocateMemory(env, length + 2);
        if (!desc) return NULL;
        desc[0] = '[';
        strcat(desc, componentType->name);
    } else {
        desc = nvmAllocateMemory(env, length + 4);
        if (!desc) return NULL;
        desc[0] = '[';
        desc[1] = 'L';
        strcat(desc, componentType->name);
        desc[length + 2] = ';';
    }

    // TODO: Add clone() method.
    Class* clazz = nvmAllocateClass(env, desc, java_lang_Object, componentType->classLoader, ACC_PUBLIC | ACC_FINAL | ACC_ABSTRACT, 0, 0);
    if (!clazz) return NULL;
    if (!nvmAddInterface(env, clazz, java_lang_Cloneable)) return NULL;
    if (!nvmAddInterface(env, clazz, java_io_Serializable)) return NULL;
    if (!nvmRegisterClass(env, clazz)) return NULL;
    clazz->state = CLASS_INITIALIZED;

    return clazz;
}

static Class* (*findClassLoaderFunction(Env* env, char* funcName, DynamicLib* dlib))(Env*, ClassLoader*) {
    Class* (*loader)(Env*, ClassLoader*) = nvmFindDynamicLibSymbol(env, dlib, funcName);
    return loader;
}

static Class* findClass(Env* env, char* className, ClassLoader* classLoader, DynamicLib* dlib) {
    Class* clazz = getLoadedClassByName(env, className);
    if (clazz != NULL) {
        return clazz;
    }

    if (className[0] == '[') {
        Class* componentType = findClassByDescriptor(env, &className[1], classLoader, dlib);
        return createArrayClass(env, componentType, dlib);
    }

    TRACE("Class '%s' not loaded\n", className);
    char* funcName = mangleClassName(env, className);
    if (!funcName) return NULL;

    TRACE("Searching for class loader function '%s()'\n", funcName);

    Class* (*loader)(Env*, ClassLoader*) = findClassLoaderFunction(env, funcName, dlib);
    if (loader) {
        TRACE("Calling class loader function '%s()'\n", funcName);
        // TODO: Catch exceptions thrown by the loader function
        clazz = loader(env, classLoader);
    }

    if (clazz) return clazz;
    if (nvmExceptionOccurred(env)) return NULL;

    if (clazz == NULL) {
        if (!strcmp(className, "java/lang/ClassNotFoundException")) {
            nvmAbort("Fatal error: java.lang.ClassNotFoundException not found!");
        }
        nvmThrowClassNotFoundException(env, className);
    }

    return clazz;
}

static Class* findBootClass(Env* env, char* className) {
    Class* clazz = findClass(env, className, NULL, bootSoHandles);
    if (nvmExceptionOccurred(env)) return NULL;
    if (clazz != NULL) {
        if (clazz->classLoader != NULL) {
            // Not a boot class
            nvmThrowClassNotFoundException(env, className);
            return NULL;
        }
    }
    return clazz;
}


static Class* findClassByDescriptor(Env* env, char* desc, ClassLoader* classLoader, DynamicLib* dlib) {
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
        return findClass(env, desc, classLoader, dlib);
    }
    // desc[0] == 'L'
    jint length = strlen(desc);
    char* className = nvmAllocateMemory(env, length - 2 + 1);
    if (!className) return NULL;
    strncpy(className, &desc[1], length - 2);
    return findClass(env, className, classLoader, dlib);
}

Class* nvmFindClassByDescriptor(Env* env, char* desc, ClassLoader* classLoader) {
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
        return nvmFindClassInLoader(env, desc, classLoader);
    }
    // desc[0] == 'L'
    jint length = strlen(desc);
    char* className = nvmAllocateMemory(env, length - 2 + 1);
    if (!className) return NULL;
    strncpy(className, &desc[1], length - 2);
    return nvmFindClassInLoader(env, className, classLoader);
}


Class* nvmGetComponentType(Env* env, Class* arrayClass) {
    return nvmFindClassByDescriptor(env, &arrayClass->name[1], arrayClass->classLoader);
}

jboolean nvmIsSubClass(Class* superclass, Class* clazz) {
    // TODO: Array types
    while (clazz && clazz != superclass) {
        clazz = clazz->superclass;
    }
    return clazz == superclass;
}

jboolean nvmIsSamePackage(Class* c1, Class* c2) {
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

jboolean nvmIsAssignableFrom(Env* env, Class* s, Class* t) {
    // TODO: What if s or t are NULL?
    if (s == t || t == java_lang_Object) {
        return TRUE;
    }

    if (CLASS_IS_ARRAY(s)) {
        if (t == java_io_Serializable) {
            return TRUE;
        }
        if (t == java_lang_Cloneable) {
            return TRUE;
        }
        if (!CLASS_IS_ARRAY(t)) {
            return FALSE;
        }
        if (CLASS_IS_ARRAY_OF_PRIMITIVE(s)) {
            // s is a primitive array and can only be assigned to 
            // class t if s == t or t == (Object|Serializable|Cloneable). But we 
            // already know that s != t and t != (Object|Serializable|Cloneable)
            return FALSE;
        }
        return nvmIsAssignableFrom(env, nvmGetComponentType(env, s), nvmGetComponentType(env, t));
    }

    if (CLASS_IS_INTERFACE(t)) {
        // s or any of its parents must implement the interface t
        for (; s; s = s->superclass) {
            Interface* interface;
            for (interface = s->interfaces; interface; interface = interface->next) {
                if (nvmIsAssignableFrom(env, interface->interface, t)) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    while (s && s != t) {
        s = s->superclass;
    }
    return s ? TRUE : FALSE;
}

jboolean nvmIsInstanceOf(Env* env, Object* obj, Class* clazz) {
    if (!obj) return FALSE;
    return nvmIsAssignableFrom(env, obj->clazz, clazz);
}

static jboolean fixClassPointer(Class* c, void* data) {
    c->object.clazz = java_lang_Class;
    return TRUE;
}

jboolean nvmInitClasses(Env* env) {
    nameToClassMap = nvmNewMapWithStringKeys(env, 1024);
    if (!nameToClassMap) return FALSE;
    idToClassMap = nvmNewMapWithIntKeys(env, 1024);
    if (!idToClassMap) return FALSE;

    initSoHandles(env);

    // Cache important classes in java.lang.
    java_lang_Object = findBootClass(env, "java/lang/Object");
    if (!java_lang_Object) return FALSE;
    java_lang_Class = findBootClass(env, "java/lang/Class");
    if (!java_lang_Class) return FALSE;

    // Fix object.clazz pointers for the classes loaded so far
    nvmIterateLoadedClasses(env, fixClassPointer, NULL);

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
    java_lang_Long = findBootClass(env, "java/lang/Long");
    if (!java_lang_Long) return FALSE;
    java_lang_Float = findBootClass(env, "java/lang/Float");
    if (!java_lang_Float) return FALSE;
    java_lang_Double = findBootClass(env, "java/lang/Double");
    if (!java_lang_Double) return FALSE;
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

Class* nvmFindClass(Env* env, char* className) {
    Method* method = nvmGetCallingMethod(env);
    if (nvmExceptionOccurred(env)) return NULL;
    ClassLoader* classLoader = method ? method->clazz->classLoader : NULL;
    return nvmFindClassInLoader(env, className, classLoader);
}

Class* nvmFindClassInLoader(Env* env, char* className, ClassLoader* classLoader) {
    if (!classLoader || classLoader->parent == NULL) {
        // This is the bootstrap classloader
        return findBootClass(env, className);
    }
    if (classLoader->parent->parent == NULL && classLoader->object.clazz->classLoader == NULL) {
        // This is the system classloader
        Class* clazz = findClass(env, className, classLoader, mainSoHandles);
        if (nvmExceptionOccurred(env)) return NULL;
        return clazz;
    }
    nvmThrowClassNotFoundException(env, className);
    return NULL;
}

Class* nvmAllocateClass(Env* env, char* className, Class* superclass, ClassLoader* classLoader, jint access, jint classDataSize, jint instanceDataSize) {
    Class* clazz = nvmAllocateMemory(env, sizeof(Class) + classDataSize);
    if (!clazz) return NULL;
    clazz->methods = nvmAllocateMemory(env, sizeof(Methods));
    if (!clazz->methods) return NULL;
    /*
     * NOTE: All classes we load before we have cached java.lang.Class will have NULL here so it is 
     * important that we cache java.lang.Class as soon as possible. However, we have to cache
     * java.lang.Object first since it is the superclass of java.lang.Class. This means that
     * the java_lang_Object global variable will actually have NULL as clazz until we fix this in
     * nvmInitClasses().
     */
    clazz->object.clazz = java_lang_Class;
    clazz->name = className;
    clazz->superclass = superclass;
    clazz->classLoader = classLoader;
    clazz->access = access;
    clazz->classDataSize = classDataSize;
    clazz->instanceDataSize = instanceDataSize;
    clazz->instanceDataOffset = clazz->superclass 
               ? clazz->superclass->instanceDataOffset + clazz->superclass->instanceDataSize
               : 0;
    // Make sure clazz->instanceDataOffset is aligned properly so that the GC will be able to find pointers
    // TODO: For now we assume that the alignment equals the size of pointers
    while (clazz->instanceDataOffset & (sizeof(void*) - 1)) clazz->instanceDataOffset++;

    return clazz;
}

jboolean nvmAddInterface(Env* env, Class* clazz, Class* interf) {
    Interface* interface = nvmAllocateMemory(env, sizeof(Interface));
    if (!interface) return FALSE;
    interface->next = clazz->interfaces;
    interface->interface = interf;
    clazz->interfaces = interface;
    return TRUE;
}

jboolean nvmAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl, void* synchronizedImpl, void* lookup) {
    Method* method = nvmAllocateMemory(env, sizeof(Method));
    if (!method) return FALSE;
    method->clazz = clazz;
    method->name = name;
    method->desc = desc;
    method->access = access;
    method->impl = impl;
    method->synchronizedImpl = synchronizedImpl;
    method->lookup = lookup;
    method->vtableIndex = -1;

    method->next = clazz->methods->first;
    clazz->methods->first  = method;

    if (method->impl) {
        if (clazz->methods->lo == NULL || method->impl < clazz->methods->lo) {
            clazz->methods->lo = method->impl;
        } else if (clazz->methods->hi == NULL || method->impl > clazz->methods->hi) {
            clazz->methods->hi = method->impl;
        }
    }
    return TRUE;
}

jboolean nvmAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset, void* getter, void* setter) {
    Field* field = nvmAllocateMemory(env, (access & ACC_STATIC) ? sizeof(ClassField) : sizeof(InstanceField));
    if (!field) return FALSE;
    field->clazz = clazz;
    field->name = name;
    field->desc = desc;
    field->access = access;
    field->getter = getter;
    field->setter = setter;
    field->next = clazz->fields;
    clazz->fields = field;
    if (access & ACC_STATIC) {
        ((ClassField*) field)->address = (jbyte*) clazz->data + offset;
    } else {
        ((InstanceField*) field)->offset = offsetof(DataObject, data) + clazz->instanceDataOffset + offset;
    }
    return TRUE;
}

jboolean nvmRegisterClass(Env* env, Class* clazz) {
    int vtableSize;
    Method* method;
    Field* field;
    int size;

    clazz->id = getNextClassId();

    vtableSize = clazz->superclass != NULL ? clazz->superclass->vtableSize : 0;

    // TODO: Check that the superclass and all interfaces are accessible to the new class
    // TODO: Verify the class hierarchy (class doesn't override final methods, changes public -> private, etc)

    for (method = clazz->methods->first; method != NULL; method = method->next) {
        int vtableIndex = -1;
        if (clazz->superclass != NULL && strcmp("<init>", method->name) && strcmp("<clinit>", method->name)) {
            vtableIndex = j_get_vtable_index(clazz->superclass, method->name, method->desc, clazz);
        }
        if (vtableIndex == -1) {
          vtableIndex = vtableSize++;
        }
        method->vtableIndex = vtableIndex;
        if (method->lookup == NULL) {
            if (!METHOD_IS_STATIC(method) && !METHOD_IS_PRIVATE(method) && !METHOD_IS_FINAL(method) && !METHOD_IS_CONSTRUCTOR(method)) {
                // Overridden non-final instance methods inherit the lookup function from the method it is overriding
                Method* superMethod = nvmGetMethod(env, clazz->superclass, method->name, method->desc);
                if (!superMethod) return FALSE;
                method->lookup = superMethod->lookup;
            } else {
                method->lookup = method->impl;
            }
        }
//        TRACE("vtable index for method %s%s in class %s: %d\n", method->name, method->desc, clazz->name, vtableIndex);
    }
    if (vtableSize > 0) {
        clazz->vtable = nvmAllocateMemory(env, vtableSize * sizeof(void*));
        if (!clazz->vtable) return FALSE;
        clazz->vtableSize = vtableSize;
        if (clazz->superclass != NULL && clazz->superclass->vtableSize > 0) {
            memcpy(clazz->vtable, clazz->superclass->vtable, clazz->superclass->vtableSize);
        }
    }
//    TRACE("vtable size for %s: %d\n", clazz->name, vtableSize);

    for (method = clazz->methods->first; method != NULL; method = method->next) {
        clazz->vtable[method->vtableIndex] = method->impl;
    }

    clazz->state = CLASS_VERIFIED;
    clazz->state = CLASS_PREPARED;

    addLoadedClass(env, clazz);

    return TRUE;
}

void nvmInitialize(Env* env, Class* clazz) {
    // TODO: Throw java.lang.NoClassDefFoundError if state == CLASS_ERROR?
    if (clazz->state == CLASS_ERROR) {
        // TODO: Add the class' binary name in the message
        nvmThrowNew(env, java_lang_NoClassDefFoundError, "Could not initialize class ??");
        return;
    }
    if (clazz->state != CLASS_INITIALIZED && clazz->state != CLASS_INITIALIZING) {
        jint oldState = clazz->state;
        clazz->state = CLASS_INITIALIZING;
        if (clazz->superclass) {
            nvmInitialize(env, clazz->superclass);
            if (nvmExceptionOccurred(env)) {
                clazz->state = oldState;
                return;
            }
        }
        TRACE("Initializing class %s\n", clazz->name);
        Method* clinit = nvmGetClassInitializer(env, clazz);
        if (!clinit) return;
        nvmCallVoidClassMethod(env, clazz, clinit);
        Object* exception = nvmExceptionClear(env);
        if (exception) {
            clazz->state = CLASS_ERROR;
            if (!nvmIsInstanceOf(env, exception, java_lang_Error)) {
                // If exception isn't an instance of java.lang.Error 
                // we must wrap it in a java.lang.ExceptionInInitializerError
                Method* constructor = nvmGetInstanceMethod(env, java_lang_ExceptionInInitializerError, "<init>", "(Ljava/lang/Throwable;)V");
                if (!constructor) return;
                Object* wrappedException = nvmNewObject(env, java_lang_ExceptionInInitializerError, constructor, exception);
                if (!wrappedException) return;
                exception = wrappedException;
            }
            nvmThrow(env, exception);
            return;
        }
        clazz->state = CLASS_INITIALIZED;
    }
}

Object* nvmAllocateObject(Env* env, Class* clazz) {
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return NULL;
    jint dataSize = clazz->instanceDataOffset + clazz->instanceDataSize;
    Object* obj = nvmAllocateMemory(env, sizeof(DataObject) + dataSize);
    if (!obj) return NULL;
    obj->clazz = clazz;
    return obj;
}

Object* nvmNewObject(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmNewObjectV(env, clazz, method, args);
}

Object* nvmNewObjectA(Env* env, Class* clazz, Method* method, jvalue *args) {
    Object* obj = nvmAllocateObject(env, clazz);
    if (!obj) return NULL;
    nvmCallNonvirtualVoidInstanceMethodA(env, obj, method, args);
    if (nvmExceptionOccurred(env)) return NULL;
    return obj;
}

Object* nvmNewObjectV(Env* env, Class* clazz, Method* method, va_list args) {
    Object* obj = nvmAllocateObject(env, clazz);
    if (!obj) return NULL;
    nvmCallVoidInstanceMethodV(env, obj, method, args);
    if (nvmExceptionOccurred(env)) return NULL;
    return obj;
}

Object* nvmCloneObject(Env* env, Object* obj) {
    if (CLASS_IS_ARRAY(obj->clazz)) {
        return (Object*) nvmCloneArray(env, (Array*) obj);
    }
    // Class is not cloneable so we assume that obj is a DataObject
    jint size = sizeof(DataObject) + obj->clazz->instanceDataOffset + obj->clazz->instanceDataSize;
    Object* copy = nvmAllocateMemory(env, size);
    if (!copy) return NULL;
    memcpy(copy, obj, size);
    // TODO: When every Object has a lock we need to assign a new one to the copy
    return copy;
}

static jboolean classIterator(MapEntry* entry, void* d) {
    jboolean (*f)(Class*, void*) = ((void**)d)[0];
    void* data = ((void**)d)[1];
    return f((Class*) entry->value, data);
}

void nvmIterateLoadedClasses(Env* env, jboolean (*f)(Class*, void*), void* data) {
    void* d[2];
    d[0] = f;
    d[1] = data;
    nvmMapForEach(env, nameToClassMap, classIterator, d);
}

static jboolean dumpClassesIterator(Class* clazz, void* d) {
    fprintf(stderr, "%p: %s\n", clazz, clazz->name);
    return TRUE;
}

void nvmDumpLoadedClasses(Env* env) {
    nvmIterateLoadedClasses(env, dumpClassesIterator, NULL);
}

