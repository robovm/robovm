#include <nullvm.h>
#include <string.h>

static char classFieldGetter8TemplateX86_64[] = {
  // mov    0x123456789abcdef,%al
  0xa0, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldSetter8TemplateX86_64[] = {
  // mov    %edi,%eax
  0x89, 0xf8,
  // mov    %al,0x123456789abcdef
  0xa2, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldGetter16TemplateX86_64[] = {
  // mov    0x123456789abcdef,%ax
  0x66, 0xa1, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldSetter16TemplateX86_64[] = {
  // mov    %edi,%eax
  0x89, 0xf8,
  // mov    %ax,0x123456789abcdef
  0x66, 0xa3, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldGetter32TemplateX86_64[] = {
  // mov    0x123456789abcdef,%eax
  0xa1, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldSetter32TemplateX86_64[] = {
  // mov    %edi,%eax
  0x89, 0xf8,
  // mov    %eax,0x123456789abcdef
  0xa3, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldGetter64TemplateX86_64[] = {
  // mov    0x123456789abcdef,%rax
  0x48, 0xa1, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldSetter64TemplateX86_64[] = {
  // mov    %rdi,%rax
  0x48, 0x89, 0xf8,
  // mov    %rax,0x123456789abcdef
  0x48, 0xa3, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01, 
  // retq
  0xc3
};

static char classFieldGetterFloatTemplateX86_64[] = {
  // mov    $0x0123456789abcdef,%rax
  0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // movss  (%rax),%xmm0
  0xf3, 0x0f, 0x10, 0x00,
  // retq
  0xc3
};

static char classFieldSetterFloatTemplateX86_64[] = {
  // mov    $0x0123456789abcdef,%rax
  0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // movss  %xmm0,(%rax)
  0xf3, 0x0f, 0x11, 0x00,
  // retq
  0xc3
};

static char classFieldGetterDoubleTemplateX86_64[] = {
  // mov    $0x0123456789abcdef,%rax
  0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // movsd  (%rax),%xmm0
  0xf2, 0x0f, 0x10, 0x00,
  // retq
  0xc3
};

static char classFieldSetterDoubleTemplateX86_64[] = {
  // mov    $0x0123456789abcdef,%rax
  0x48, 0xb8, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // movsd  %xmm0,(%rax)
  0xf2, 0x0f, 0x11, 0x00,
  // retq
  0xc3
};

static char instanceFieldGetter8TemplateX86_64[] = {
  // movzbl 0x1234567(%rdi),%eax
  0x0f, 0xb6, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter8TemplateX86_64[] = {
  // mov    %sil,0x1234567(%rdi)
  0x40, 0x88, 0xb7, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter16TemplateX86_64[] = {
  // movzwl 0x1234567(%rdi),%eax
  0x0f, 0xb7, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter16TemplateX86_64[] = {
  // mov    %si,0x1234567(%rdi)
  0x66, 0x89, 0xb7, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter32TemplateX86_64[] = {
  // mov    0x1234567(%rdi),%eax
  0x8b, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter32TemplateX86_64[] = {
  // mov    %esi,0x1234567(%rdi)
  0x89, 0xb7, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter64TemplateX86_64[] = {
  // mov    0x1234567(%rdi),%rax
  0x48, 0x8b, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter64TemplateX86_64[] = {
  // mov    %rsi,0x1234567(%rdi)
  0x48, 0x89, 0xb7, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetterFloatTemplateX86_64[] = {
  // movss  0x1234567(%rdi),%xmm0
  0xf3, 0x0f, 0x10, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetterFloatTemplateX86_64[] = {
  // movss  %xmm0,0x1234567(%rdi)
  0xf3, 0x0f, 0x11, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetterDoubleTemplateX86_64[] = {
  // movsd  0x1234567(%rdi),%xmm0
  0xf2, 0x0f, 0x10, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetterDoubleTemplateX86_64[] = {
  // movsd  %xmm0,0x1234567(%rdi)
  0xf2, 0x0f, 0x11, 0x87, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static void* allocateMemoryForFunction(int size, char* templatePtr) {
  void* m = nvmAllocateExecutableMemory(size);
  memcpy(m, templatePtr, size);
  return m;
}

jbyte (*nvmCreateClassFieldGetter8(jbyte* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetter8TemplateX86_64), classFieldGetter8TemplateX86_64);
  *((jbyte**)(m + 1)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetter8(jbyte* ptr))(jbyte) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetter8TemplateX86_64), classFieldSetter8TemplateX86_64);
  *((jbyte**)(m + 3)) = ptr;
  return m;
}

jshort (*nvmCreateClassFieldGetter16(jshort* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetter16TemplateX86_64), classFieldGetter16TemplateX86_64);
  *((jshort**)(m + 2)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetter16(jshort* ptr))(jshort) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetter16TemplateX86_64), classFieldSetter16TemplateX86_64);
  *((jshort**)(m + 4)) = ptr;
  return m;
}

jint (*nvmCreateClassFieldGetter32(jint* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetter32TemplateX86_64), classFieldGetter32TemplateX86_64);
  *((jint**)(m + 1)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetter32(jint* ptr))(jint) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetter32TemplateX86_64), classFieldSetter32TemplateX86_64);
  *((jint**)(m + 3)) = ptr;
  return m;
}

jlong (*nvmCreateClassFieldGetter64(jlong* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetter64TemplateX86_64), classFieldGetter64TemplateX86_64);
  *((jlong**)(m + 2)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetter64(jlong* ptr))(jlong) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetter64TemplateX86_64), classFieldSetter64TemplateX86_64);
  *((jlong**)(m + 5)) = ptr;
  return m;
}

jfloat (*nvmCreateClassFieldGetterFloat(jfloat* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetterFloatTemplateX86_64), classFieldGetterFloatTemplateX86_64);
  *((jfloat**)(m + 2)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetterFloat(jfloat* ptr))(jfloat) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetterFloatTemplateX86_64), classFieldSetterFloatTemplateX86_64);
  *((jfloat**)(m + 2)) = ptr;
  return m;
}

jdouble (*nvmCreateClassFieldGetterDouble(jdouble* ptr))(void) {
  void* m = allocateMemoryForFunction(sizeof(classFieldGetterDoubleTemplateX86_64), classFieldGetterDoubleTemplateX86_64);
  *((jdouble**)(m + 2)) = ptr;
  return m;
}

void (*nvmCreateClassFieldSetterDouble(jdouble* ptr))(jdouble) {
  void* m = allocateMemoryForFunction(sizeof(classFieldSetterDoubleTemplateX86_64), classFieldSetterDoubleTemplateX86_64);
  *((jdouble**)(m + 2)) = ptr;
  return m;
}

jbyte (*nvmCreateInstanceFieldGetter8(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetter8TemplateX86_64), instanceFieldGetter8TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetter8(jint offset))(jobject*, jbyte) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetter8TemplateX86_64), instanceFieldSetter8TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

jshort (*nvmCreateInstanceFieldGetter16(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetter16TemplateX86_64), instanceFieldGetter16TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetter16(jint offset))(jobject*, jshort) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetter16TemplateX86_64), instanceFieldSetter16TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

jint (*nvmCreateInstanceFieldGetter32(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetter32TemplateX86_64), instanceFieldGetter32TemplateX86_64);
  *((jint*)(m + 2)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetter32(jint offset))(jobject*, jint) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetter32TemplateX86_64), instanceFieldSetter32TemplateX86_64);
  *((jint*)(m + 2)) = offset;
  return m;
}

jlong (*nvmCreateInstanceFieldGetter64(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetter64TemplateX86_64), instanceFieldGetter64TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetter64(jint offset))(jobject*, jlong) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetter64TemplateX86_64), instanceFieldSetter64TemplateX86_64);
  *((jint*)(m + 3)) = offset;
  return m;
}

jfloat (*nvmCreateInstanceFieldGetterFloat(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetterFloatTemplateX86_64), instanceFieldGetterFloatTemplateX86_64);
  *((jint*)(m + 4)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetterFloat(jint offset))(jobject*, jlong) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetterFloatTemplateX86_64), instanceFieldSetterFloatTemplateX86_64);
  *((jint*)(m + 4)) = offset;
  return m;
}

jdouble (*nvmCreateInstanceFieldGetterDouble(jint offset))(jobject*) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldGetterDoubleTemplateX86_64), instanceFieldGetterDoubleTemplateX86_64);
  *((jint*)(m + 4)) = offset;
  return m;
}

void (*nvmCreateInstanceFieldSetterDouble(jint offset))(jobject*, jdouble) {
  void* m = allocateMemoryForFunction(sizeof(instanceFieldSetterDoubleTemplateX86_64), instanceFieldSetterDoubleTemplateX86_64);
  *((jint*)(m + 4)) = offset;
  return m;
}

void* nvmCreateFieldGetter(jclass* clazz, jfield* field) {
    void* ptr = clazz->data + field->offset;
    jint offset = offsetof(jobject, data) + clazz->instanceDataOffset + field->offset;

    switch (field->desc[0]) {
    case 'B':
    case 'Z':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter8(ptr) : (void*) nvmCreateInstanceFieldGetter8(offset);
    case 'S':
    case 'C':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter16(ptr) : (void*) nvmCreateInstanceFieldGetter16(offset);
    case 'F':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetterFloat(ptr) : (void*) nvmCreateInstanceFieldGetterFloat(offset);
    case 'D':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetterDouble(ptr) : (void*) nvmCreateInstanceFieldGetterDouble(offset);
    case 'I':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter32(ptr) : (void*) nvmCreateInstanceFieldGetter32(offset);
    case 'J':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter64(ptr) : (void*) nvmCreateInstanceFieldGetter64(offset);
    }
    // Reference
    // TODO: 32-bit pointers
    return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter64(ptr) : (void*) nvmCreateInstanceFieldGetter64(offset);
}

void* nvmCreateFieldSetter(jclass* clazz, jfield* field) {
    void* ptr = clazz->data + field->offset;
    jint offset = offsetof(jobject, data) + clazz->instanceDataOffset + field->offset;

    switch (field->desc[0]) {
    case 'B':
    case 'Z':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter8(ptr) : (void*) nvmCreateInstanceFieldSetter8(offset);
    case 'S':
    case 'C':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter16(ptr) : (void*) nvmCreateInstanceFieldSetter16(offset);
    case 'F':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetterFloat(ptr) : (void*) nvmCreateInstanceFieldSetterFloat(offset);
    case 'D':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetterDouble(ptr) : (void*) nvmCreateInstanceFieldSetterDouble(offset);
    case 'I':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter32(ptr) : (void*) nvmCreateInstanceFieldSetter32(offset);
    case 'J':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter64(ptr) : (void*) nvmCreateInstanceFieldSetter64(offset);
    }
    // Reference
    // TODO: 32-bit pointers
    return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter64(ptr) : (void*) nvmCreateInstanceFieldSetter64(offset);
}

jfield* nvmGetField(jclass* clazz, char* name, char* desc, jclass* caller) {
    jfield* field;
    int sameClass = caller == NULL || clazz == caller;
    int subClass = caller == NULL || nvmIsSubClass(clazz, caller);
    int samePackage = caller == NULL || nvmIsSamePackage(clazz, caller);

    for (field = clazz->fields; field != NULL; field = field->next) {
        if (!strcmp(field->name, name) && !strcmp(field->desc, desc)) {
            jint access = field->access;
            if (IS_PRIVATE(access) && !sameClass) {
                nvmThrowIllegalAccessErrorField(clazz, name, desc, caller);
            }
            if (IS_PROTECTED(access) && !subClass) {
                nvmThrowIllegalAccessErrorField(clazz, name, desc, caller);
            }
            if (IS_PACKAGE_PRIVATE(access) && !samePackage) {
                nvmThrowIllegalAccessErrorField(clazz, name, desc, caller);
            }
            return field;
        }
    }

    if (clazz->superclass) {
        return nvmGetField(clazz->superclass, name, desc, caller);
    }

    nvmThrowNoSuchFieldError(name);
}

jfield* nvmGetClassField(jclass* clazz, char* name, char* desc, jclass* caller) {
    jfield* field = nvmGetField(clazz, name, desc, caller);
    if (!(field->access & ACC_STATIC)) {
        nvmThrowIncompatibleClassChangeErrorClassField(clazz, name, desc);
    }
    return field;
}

jfield* nvmGetInstanceField(jclass* clazz, char* name, char* desc, jclass* caller) {
    jfield* field = nvmGetField(clazz, name, desc, caller);
    if (field->access & ACC_STATIC) {
        nvmThrowIncompatibleClassChangeErrorInstanceField(clazz, name, desc);
    }
    return field;
}

void *nvmGetClassFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    jfield* field = nvmGetClassField(nvmGetClass(className, mangledClassName, caller), fieldName, fieldDesc, caller);
    *functionPtr = field->getter;
    return field->getter;
}

void *nvmGetClassFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    jfield* field = nvmGetClassField(clazz, fieldName, fieldDesc, caller);
    if (caller && field->access & ACC_FINAL && caller != clazz) {
        nvmThrowIllegalAccessError();
    }
    *functionPtr = field->setter;
    return field->setter;
}

void *nvmGetInstanceFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    jfield* field = nvmGetInstanceField(nvmGetClass(className, mangledClassName, caller), fieldName, fieldDesc, caller);
    *functionPtr = field->getter;
    return field->getter;
}

void *nvmGetInstanceFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr) {
    jclass* clazz = nvmGetClass(className, mangledClassName, caller);
    jfield* field = nvmGetInstanceField(clazz, fieldName, fieldDesc, caller);
    if (caller && field->access & ACC_FINAL && caller != clazz) {
        nvmThrowIllegalAccessError();
    }
    *functionPtr = field->setter;
    return field->setter;
}

