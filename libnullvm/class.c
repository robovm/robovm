#include <nullvm.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

Class* array_Z;
Class* array_B;
Class* array_C;
Class* array_S;
Class* array_I;
Class* array_J;
Class* array_F;
Class* array_D;
Class* java_lang_Object;
Class* java_lang_String;
Class* java_lang_OutOfMemoryError;
Class* java_lang_NoClassDefFoundError;
Class* java_lang_IllegalAccessError;
Class* java_lang_NoSuchFieldError;
Class* java_lang_IncompatibleClassChangeError;
Class* java_lang_ClassCastException;
Class* java_lang_NullPointerException;
Class* java_lang_AbstractMethodError;
Class* java_lang_ArrayIndexOutOfBoundsException;
Class* java_lang_ClassNotFoundException;
Class* java_lang_NegativeArraySizeException;
Class* java_lang_UnsatisfiedLinkError;

static uint8_t checkcastInstanceofTemplateX86_64[] = {
    // mov    %rsi,%rdx
    0x48,  0x89, 0xf2,
    // mov    $0x123456789abcdef,%rax
    0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01, 
    // mov    $0x1123456789abcdef,%rsi
    0x48, 0xbe, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x11, 
    // jmpq   *%rax
    0xff, 0xe0
};

static uint8_t allocateObjectTemplateX86_64[] = {
    // mov    $0x123456789abcdef,%rax
    0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01, 
    // mov    $0x1123456789abcdef,%rsi
    0x48, 0xbe, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x11, 
    // jmpq   *%rax
    0xff, 0xe0
};

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
    java_lang_String = nvmFindClass(env, "java/lang/String");
    if (!java_lang_String) return FALSE;
    java_lang_OutOfMemoryError = nvmFindClass(env, "java/lang/OutOfMemoryError");
    if (!java_lang_OutOfMemoryError) return FALSE;
    java_lang_IllegalAccessError = nvmFindClass(env, "java/lang/IllegalAccessError");
    if (!java_lang_IllegalAccessError) return FALSE;
    java_lang_NoSuchFieldError = nvmFindClass(env, "java/lang/NoSuchFieldError");
    if (!java_lang_NoSuchFieldError) return FALSE;
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

static void checkcastClass(Env* env, Class* clazz, Object* o) {
    // TODO: Handle arrays
    if (o && o->clazz != clazz && !nvmIsSubClass(clazz, o->clazz)) {
        nvmThrowClassCastException(env, clazz, o->clazz);
        nvmRaiseException(env, nvmExceptionOccurred(env));
    }
}

static void checkcastInterface(Class* clazz, Object* o) {
    // TODO: Implement me
}

static jint instanceofClass(Class* clazz, Object* o) {
    // TODO: Handle arrays
    return o && (o->clazz == clazz || nvmIsSubClass(clazz, o->clazz));
}

static jint instanceofInterface(Class* clazz, Object* o) {
    // TODO: Implement me
}

static void* allocateMemoryForFunction(Env* env, int size, uint8_t* templatePtr) {
    void* m = nvmAllocateExecutableMemory(env, size);
    if (!m) return NULL;
    memcpy(m, templatePtr, size);
    return m;
}

static void (*createCheckcastClassFunction(Env* env, Class* clazz))(Object*) {
  void* m = allocateMemoryForFunction(env, sizeof(checkcastInstanceofTemplateX86_64), checkcastInstanceofTemplateX86_64);
  if (!m) return NULL;
  *((void**)(m + 5)) = (void*) checkcastClass;
  *((Class**)(m + 15)) = clazz;
  return m;
}

static void (*createCheckcastInterfaceFunction(Env* env, Class* clazz))(Object*) {
  void* m = allocateMemoryForFunction(env, sizeof(checkcastInstanceofTemplateX86_64), checkcastInstanceofTemplateX86_64);
  if (!m) return NULL;
  *((void**)(m + 5)) = (void*) checkcastInterface;
  *((Class**)(m + 15)) = clazz;
  return m;
}

static jint (*createInstanceofClassFunction(Env* env, Class* clazz))(Object*) {
  void* m = allocateMemoryForFunction(env, sizeof(checkcastInstanceofTemplateX86_64), checkcastInstanceofTemplateX86_64);
  if (!m) return NULL;
  *((void**)(m + 5)) = (void*) instanceofClass;
  *((Class**)(m + 15)) = clazz;
  return m;
}

static jint (*createInstanceofInterfaceFunction(Env* env, Class* clazz))(Object*) {
  void* m = allocateMemoryForFunction(env, sizeof(checkcastInstanceofTemplateX86_64), checkcastInstanceofTemplateX86_64);
  if (!m) return NULL;
  *((void**)(m + 5)) = (void*) instanceofInterface;
  *((Class**)(m + 15)) = clazz;
  return m;
}

static Object* (*createNewInstanceFunction(Env* env, Class* clazz))(void) {
  void* m = allocateMemoryForFunction(env, sizeof(allocateObjectTemplateX86_64), allocateObjectTemplateX86_64);
  if (!m) return NULL;
  *((void**)(m + 2)) = (void*) nvmAllocateObject;
  *((Class**)(m + 12)) = clazz;
  return m;
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
    // TODO: Add Cloneable interface and clone() method.
    Class* clazz = nvmAllocateClass(env, className, java_lang_Object, ACC_PUBLIC, 0, 0);
    if (!clazz) return NULL;
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
    clazz->name = className;
    clazz->superclass = superclass;
    clazz->access = access;
    clazz->classDataSize = classDataSize;
    clazz->instanceDataSize = instanceDataSize;
    return clazz;
}

jboolean nvmAddInterface(Env* env, Class* clazz, Class* interface) {
    if (!clazz->interfaces) {
        clazz->interfaces = nvmNewMapWithIntKeys(env, 16);
        if (!clazz->interfaces) return FALSE;
    }
    nvmMapPutWithIntKey(env, clazz->interfaces, interface->id, interface);
    if (nvmExceptionOccurred(env)) return FALSE;
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
    Field* field = nvmAllocateMemory(env, sizeof(Field));
    if (!field) return FALSE;
    field->name = name;
    field->desc = desc;
    field->access = access;
    field->offset = offset;
    field->next = clazz->fields;
    clazz->fields = field;
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

    // Set up method wrappers
    for (method = clazz->methods; method != NULL; method = method->next) {
        method->wrapper = method->impl; //nvmCreateMethodWrapper(clazz, method);
    }

    // Set up setters and getters for fields
    for (field = clazz->fields; field != NULL; field = field->next) {
        field->getter = nvmCreateFieldGetter(env, clazz, field);
        if (!field->getter) return FALSE;
        field->setter = nvmCreateFieldSetter(env, clazz, field);
        if (!field->setter) return FALSE;
    }

    if (clazz->access & ACC_INTERFACE) {
        clazz->checkcast = createCheckcastInterfaceFunction(env, clazz);
        if (!clazz->checkcast) return FALSE;
        clazz->instanceof = createInstanceofInterfaceFunction(env, clazz);
        if (!clazz->instanceof) return FALSE;
    } else {
        clazz->checkcast = createCheckcastClassFunction(env, clazz);
        if (!clazz->checkcast) return FALSE;
        clazz->instanceof = createInstanceofClassFunction(env, clazz);
        if (!clazz->instanceof) return FALSE;
        clazz->newInstance = createNewInstanceFunction(env, clazz);
        if (!clazz->newInstance) return FALSE;
    }

    // TODO: Call <clinit>()
    addLoadedClass(env, clazz);

    return TRUE;
}

Object* nvmAllocateObject(Env* env, Class* clazz) {
    jint dataSize = clazz->instanceDataOffset + clazz->instanceDataSize;
    Object* obj = nvmAllocateMemory(env, sizeof(Object) + dataSize);
    if (!obj) return NULL;
    obj->clazz = clazz;
    return obj;
}

