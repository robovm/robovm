#include <nullvm.h>
#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

struct _class_cache_entry;
typedef struct _class_cache_entry class_cache_entry;
struct _class_cache_entry {
  class_cache_entry* next;
  jclass* clazz;
};

static class_cache_entry* class_cache = NULL;

static jclass* class_cache_get(char* className) {
    class_cache_entry* entry;
    for (entry = class_cache; entry != NULL; entry = entry->next) {
        if (!strcmp(entry->clazz->name, className)) {
            return entry->clazz;
        }
    }
    return NULL;
}

static void class_cache_put(jclass* clazz) {
    class_cache_entry* entry = nvmAllocateMemory(sizeof(class_cache_entry));
    entry->clazz = clazz;
    entry->next = class_cache;
    class_cache = entry;
}

void* j_get_method_impl(jclass* clazz, char* name, char* desc, jclass* caller) {
    int vtableIndex = j_get_vtable_index(clazz, name, desc, caller);
    return vtableIndex != -1 ? clazz->vtable[vtableIndex] : NULL;
}

int nvmIsSubClass(jclass* superclass, jclass* clazz) {
    while (clazz && clazz != superclass) {
        clazz = clazz->superclass;
    }
    return clazz == superclass;
}

int nvmIsSamePackage(jclass* c1, jclass* c2) {
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

jint j_get_vtable_index(jclass* clazz, char* name, char* desc, jclass* caller) {
    jmethod* method;
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

jclass* nvmGetClass(char* className, char* mangledClassName, jclass* caller) {
    // TODO: Implement me properly using ClassLoader
    // TODO: Access checks

    if (className[0] == '[') {
        return nvmGetArrayClass(className);
    }
    jclass* clazz = class_cache_get(className);
    if (clazz == NULL) {
        LOG("Class '%s' not loaded\n", className);
        char* funcName = nvmAllocateMemory(strlen(mangledClassName) + 4);
        strcpy(funcName, "jc_");
        strcat(funcName, mangledClassName);
        LOG("Searching for class loader function '%s()'\n", funcName);
        void* handle = dlopen(NULL, RTLD_LAZY);
        jclass* (*loader)(void) = dlsym(handle, funcName);
        dlclose(handle);
        if (loader) {
            LOG("Calling loader function '%s()'\n", funcName);
        }
        if (loader) {
            clazz = loader();
        }
    }
    if (clazz == NULL) {
        if (!strcmp(className, "java/lang/ClassNotFoundException")) {
            nvmAbort("Fatal error: java.lang.ClassNotFoundException not found!");
        }
        nvmThrowClassNotFoundException(className);
    }
    return clazz;
}

jclass* nvmGetArrayClass(char* className) {
    // TODO: Implement me properly using ClassLoader
    // TODO: Access checks

    jclass* clazz = class_cache_get(className);
    if (clazz == NULL) {
        // TODO: What access should an array class have?
        clazz = nvmAllocateClass(className, nvmGetClass("java/lang/Object", "java_lang_Object", NULL), ACC_PUBLIC, 0, 0);
        // TODO: Add Cloneable interface and clone() method.
        nvmRegisterClass(clazz);
    }
    // TODO: Throw ClassNotFoundException if base class not found
    return clazz;
}

jclass* nvmAllocateClass(char* className, jclass* superclass, jint access, jint classDataSize, jint instanceDataSize) {
    jclass* clazz;
    clazz = nvmAllocateMemory(sizeof(jclass) + classDataSize);
    clazz->name = className;
    clazz->superclass = superclass;
    clazz->access = access;
    clazz->classDataSize = classDataSize;
    clazz->instanceDataSize = instanceDataSize;
    return clazz;
}

void nvmAddInterface(jclass* clazz, char* interfaceName) {
}

void nvmAddMethod(jclass* clazz, char* name, char* desc, jint access, void* impl) {
    jmethod* method = nvmAllocateMemory(sizeof(jmethod));
    method->name = name;
    method->desc = desc;
    method->access = access;
    method->impl = impl;
    method->next = clazz->methods;
    method->vtableIndex = -1;
    clazz->methods = method;
}

void nvmAddField(jclass* clazz, char* name, char* desc, jint access, jint offset) {
    jfield* field = nvmAllocateMemory(sizeof(jfield));
    field->name = name;
    field->desc = desc;
    field->access = access;
    field->offset = offset;
    field->next = clazz->fields;
    clazz->fields = field;
}

void nvmRegisterClass(jclass* clazz) {
    int vtableSize;
    jmethod* method;
    jfield* field;
    int offset;
    int size;

    vtableSize = clazz->superclass != NULL ? clazz->superclass->vtableSize : 0;

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
        clazz->vtable = nvmAllocateMemory(vtableSize * sizeof(void*));
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
        method->wrapper = nvmCreateMethodWrapper(clazz, method);
    }

    // Set up setters and getters for fields
    for (field = clazz->fields; field != NULL; field = field->next) {
        field->getter = nvmCreateFieldGetter(clazz, field);
        field->setter = nvmCreateFieldSetter(clazz, field);
    }

//(void*) (((char*) clazz->fields_data) + field->offset);

    // TODO: Call <clinit>()
    class_cache_put(clazz);
}

jobject* nvmNewInstance(jclass* clazz) {
    jint dataSize = clazz->instanceDataOffset + clazz->instanceDataSize;
    jobject* obj = nvmAllocateMemory(sizeof(jobject) + dataSize);
    obj->clazz = clazz;
    return obj;
}

void nvmCheckcast(jobject* o, jclass* clazz) {
    // TODO: Check interfaces
    // TODO: Handle arrays
    if (o && o->clazz != clazz && !nvmIsSubClass(clazz, o->clazz)) {
        nvmThrowClassCastException(clazz, o->clazz);
    }
}

jboolean nvmInstanceof(jobject* o, jclass* clazz) {
    // TODO: Check interfaces
    // TODO: Handle arrays
    return o && (o->clazz == clazz || nvmIsSubClass(clazz, o->clazz));
}

void* nvmGetCheckcastFunction(char* className, char* mangledClassName, jclass* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    *functionPtr = clazz->checkcast;
    return clazz->checkcast;
}

void* nvmGetInstanceofFunction(char* className, char* mangledClassName, jclass* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    *functionPtr = clazz->instanceof;
    return clazz->instanceof;
}

