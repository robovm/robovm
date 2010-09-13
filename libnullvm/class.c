#include <nullvm.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

Class* java_lang_Object;
Class* java_lang_Class;
Class* java_lang_String;
Class* java_lang_Cloneable;
Class* java_io_Serializable;

Class* java_lang_OutOfMemoryError;
Class* java_lang_NoClassDefFoundError;
Class* java_lang_IllegalAccessError;
Class* java_lang_NoSuchFieldError;
Class* java_lang_NoSuchMethodError;
Class* java_lang_IncompatibleClassChangeError;
Class* java_lang_ClassCastException;
Class* java_lang_NullPointerException;
Class* java_lang_AbstractMethodError;
Class* java_lang_ArrayIndexOutOfBoundsException;
Class* java_lang_ClassNotFoundException;
Class* java_lang_NegativeArraySizeException;
Class* java_lang_UnsatisfiedLinkError;

Class* array_Z;
Class* array_B;
Class* array_C;
Class* array_S;
Class* array_I;
Class* array_J;
Class* array_F;
Class* array_D;

// TODO: Protect these with locks
static Map* nameToClassMap = NULL;
static Map* idToClassMap = NULL;

static jint nextClassId = 0;

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

void* j_get_method_impl(Class* clazz, char* name, char* desc, Class* caller) {
    int vtableIndex = j_get_vtable_index(clazz, name, desc, caller);
    return vtableIndex != -1 ? clazz->vtable[vtableIndex] : NULL;
}

jboolean nvmInitClasses(Env* env) {
    nameToClassMap = nvmNewMapWithStringKeys(env, 1024);
    if (!nameToClassMap) return FALSE;
    idToClassMap = nvmNewMapWithIntKeys(env, 1024);
    if (!idToClassMap) return FALSE;

    // Cache important classes in java.lang.
    java_lang_ClassNotFoundException = nvmFindClass(env, "java/lang/ClassNotFoundException");
    if (!java_lang_ClassNotFoundException) return FALSE;
    java_lang_NoClassDefFoundError = nvmFindClass(env, "java/lang/NoClassDefFoundError");
    if (!java_lang_NoClassDefFoundError) return FALSE;
    java_lang_Object = nvmFindClass(env, "java/lang/Object");
    if (!java_lang_Object) return FALSE;
    java_lang_Class = nvmFindClass(env, "java/lang/Class");
    if (!java_lang_Class) return FALSE;
    java_lang_Object->object.clazz = java_lang_Class; // Fix object.clazz pointer for java_lang_Object
    java_lang_String = nvmFindClass(env, "java/lang/String");
    if (!java_lang_String) return FALSE;
    java_lang_Cloneable = nvmFindClass(env, "java/lang/Cloneable");
    if (!java_lang_Cloneable) return FALSE;
    java_io_Serializable = nvmFindClass(env, "java/io/Serializable");
    if (!java_io_Serializable) return FALSE;

    java_lang_OutOfMemoryError = nvmFindClass(env, "java/lang/OutOfMemoryError");
    if (!java_lang_OutOfMemoryError) return FALSE;
    java_lang_IllegalAccessError = nvmFindClass(env, "java/lang/IllegalAccessError");
    if (!java_lang_IllegalAccessError) return FALSE;
    java_lang_NoSuchFieldError = nvmFindClass(env, "java/lang/NoSuchFieldError");
    if (!java_lang_NoSuchFieldError) return FALSE;
    java_lang_NoSuchMethodError = nvmFindClass(env, "java/lang/NoSuchMethodError");
    if (!java_lang_NoSuchMethodError) return FALSE;
    java_lang_IncompatibleClassChangeError = nvmFindClass(env, "java/lang/IncompatibleClassChangeError");
    if (!java_lang_IncompatibleClassChangeError) return FALSE;
    java_lang_ClassCastException = nvmFindClass(env, "java/lang/ClassCastException");
    if (!java_lang_ClassCastException) return FALSE;
    java_lang_NullPointerException = nvmFindClass(env, "java/lang/NullPointerException");
    if (!java_lang_NullPointerException) return FALSE;
    java_lang_AbstractMethodError = nvmFindClass(env, "java/lang/AbstractMethodError");
    if (!java_lang_AbstractMethodError) return FALSE;
    java_lang_ArrayIndexOutOfBoundsException = nvmFindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (!java_lang_ArrayIndexOutOfBoundsException) return FALSE;
    java_lang_NegativeArraySizeException = nvmFindClass(env, "java/lang/NegativeArraySizeException");
    if (!java_lang_NegativeArraySizeException) return FALSE;
    java_lang_UnsatisfiedLinkError = nvmFindClass(env, "java/lang/UnsatisfiedLinkError");
    if (!java_lang_UnsatisfiedLinkError) return FALSE;

    array_Z = nvmFindClass(env, "[Z");
    if (!array_Z) return FALSE;
    array_B = nvmFindClass(env, "[B");
    if (!array_B) return FALSE;
    array_C = nvmFindClass(env, "[C");
    if (!array_C) return FALSE;
    array_S = nvmFindClass(env, "[S");
    if (!array_S) return FALSE;
    array_I = nvmFindClass(env, "[I");
    if (!array_I) return FALSE;
    array_J = nvmFindClass(env, "[J");
    if (!array_J) return FALSE;
    array_F = nvmFindClass(env, "[F");
    if (!array_F) return FALSE;
    array_D = nvmFindClass(env, "[D");
    if (!array_D) return FALSE;

    return TRUE;
}

int nvmIsSubClass(Class* superclass, Class* clazz) {
    while (clazz && clazz != superclass) {
        clazz = clazz->superclass;
    }
    return clazz == superclass;
}

int nvmIsSamePackage(Class* c1, Class* c2) {
    char* s1 = strrchr(c1->name, '/');
    char* s2 = strrchr(c2->name, '/');
    if (!s1 || !s2) {
        return !s1 && !s2;
    }
    int l1 = c1->name - s1;
    int l2 = c2->name - s2;
    if (l1 != l2) {
        return 0;
    }
    return strncmp(c1->name, c2->name, l1);
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
        Class* s1 = nvmFindClass(env, &s->name[1]);
        Class* t1 = nvmFindClass(env, &t->name[1]);
        return nvmIsAssignableFrom(env, s1, t1);
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

jint j_get_vtable_index(Class* clazz, char* name, char* desc, Class* caller) {
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

    for (method = clazz->methods; method != NULL; method = method->next) {
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
            result[j++] = hexChars[(c >> 8) & 0xf];
            result[j++] = hexChars[c & 0xf];
        }
    }

    return result;
}

static Class* createArrayClass(Env* env, char* className) {
    // TODO: Throw ClassNotFoundException if base class not found
    // TODO: What access should an array class have?
    // TODO: Add clone() method.
    Class* clazz = nvmAllocateClass(env, className, java_lang_Object, ACC_PUBLIC, 0, 0);
    if (!clazz) return NULL;
    if (!nvmAddInterface(env, clazz, java_lang_Cloneable)) return NULL;
    if (!nvmAddInterface(env, clazz, java_io_Serializable)) return NULL;
    if (!nvmRegisterClass(env, clazz)) return NULL;
    return clazz;
}

Class* nvmFindClass(Env* env, char* className) {
    // TODO: Implement me properly using ClassLoader

    Class* clazz = getLoadedClassByName(env, className);
    if (clazz != NULL) {
        return clazz;
    }

    if (className[0] == '[') {
        return createArrayClass(env, className);
    }

    LOG("Class '%s' not loaded\n", className);
    char* funcName = mangleClassName(env, className);
    if (!funcName) return NULL;

    LOG("Searching for class loader function '%s()'\n", funcName);
    void* handle = dlopen(NULL, RTLD_LAZY);
    Class* (*loader)(Env*) = dlsym(handle, funcName);
    dlclose(handle);
    if (loader) {
        LOG("Calling class loader function '%s()'\n", funcName);
        clazz = loader(env);
        if (!clazz) return NULL;
    }
    if (clazz == NULL) {
        if (!strcmp(className, "java/lang/ClassNotFoundException")) {
            nvmAbort("Fatal error: java.lang.ClassNotFoundException not found!");
        }
        nvmThrowClassNotFoundException(env, className);
    }
    return clazz;
}

Class* nvmAllocateClass(Env* env, char* className, Class* superclass, jint access, jint classDataSize, jint instanceDataSize) {
    Class* clazz = nvmAllocateMemory(env, sizeof(Class) + classDataSize);
    if (!clazz) return NULL;
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
    clazz->access = access;
    clazz->classDataSize = classDataSize;
    clazz->instanceDataSize = instanceDataSize;
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

jboolean nvmAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl) {
    Method* method = nvmAllocateMemory(env, sizeof(Method));
    if (!method) return FALSE;
    method->name = name;
    method->desc = desc;
    method->access = access;
    method->impl = impl;
    method->next = clazz->methods;
    method->vtableIndex = -1;
    clazz->methods = method;
    return TRUE;
}

jboolean nvmAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset) {
    Field* field = nvmAllocateMemory(env, (access & ACC_STATIC) ? sizeof(ClassField) : sizeof(InstanceField));
    if (!field) return FALSE;
    field->name = name;
    field->desc = desc;
    field->access = access;
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
    int offset;
    int size;

    clazz->id = getNextClassId();

    vtableSize = clazz->superclass != NULL ? clazz->superclass->vtableSize : 0;

    // TODO: Check that the superclass and all interfaces are accessible to the new class
    // TODO: Verify the class hierarchy (class doesn't override final methods, changes public -> private, etc)

    for (method = clazz->methods; method != NULL; method = method->next) {
        int vtableIndex = -1;
        if (clazz->superclass != NULL && strcmp("<init>", method->name) && strcmp("<clinit>", method->name)) {
            vtableIndex = j_get_vtable_index(clazz->superclass, method->name, method->desc, clazz);
        }
        if (vtableIndex == -1) {
          vtableIndex = vtableSize++;
        }
        method->vtableIndex = vtableIndex;
        LOG("vtable index for method %s%s in class %s: %d\n", method->name, method->desc, clazz->name, vtableIndex);
    }
    if (vtableSize > 0) {
        clazz->vtable = nvmAllocateMemory(env, vtableSize * sizeof(void*));
        if (!clazz->vtable) return FALSE;
        clazz->vtableSize = vtableSize;
        if (clazz->superclass != NULL && clazz->superclass->vtableSize > 0) {
            memcpy(clazz->vtable, clazz->superclass->vtable, clazz->superclass->vtableSize);
        }
    }
    LOG("vtable size for %s: %d\n", clazz->name, vtableSize);

    for (method = clazz->methods; method != NULL; method = method->next) {
        clazz->vtable[method->vtableIndex] = method->impl;
    }

    offset = clazz->superclass 
               ? clazz->superclass->instanceDataOffset + clazz->superclass->instanceDataSize
               : 0;
    LOG("instanceDataOffset for %s: %d\n", clazz->name, offset);
    clazz->instanceDataOffset = offset;

    clazz->state = CLASS_VERIFIED;
    clazz->state = CLASS_PREPARED;

    addLoadedClass(env, clazz);

    return TRUE;
}

void nvmInitialize(Env* env, Class* clazz) {
    if (clazz->state != CLASS_INITIALIZED && clazz->state != CLASS_INITIALIZING && clazz->state != CLASS_ERROR) {
        clazz->state = CLASS_INITIALIZING;
        Method* clinit = nvmGetClassMethod(env, clazz, "<clinit>", "()V");
        if (!clinit) {
            nvmExceptionClear(env);
            return;
        }
        nvmCallVoidClassMethod(env, clazz, clinit);
        if (nvmExceptionOccurred(env)) {
            clazz->state = CLASS_ERROR;
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

