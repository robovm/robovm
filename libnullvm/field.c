#include <nullvm.h>
#include <string.h>

static char classFieldGetter8TemplateX86_64[] = {
  // mov    0x123456789abcdef,%al
  0xa0, 0xef, 0xcd, 0xab, 0x89, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char classFieldSetter8TemplateX86_64[] = {
  // mov    %esi,%eax
  0x89, 0xf0,
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
  // mov    %esi,%eax
  0x89, 0xf0,
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
  // mov    %esi,%eax
  0x89, 0xf0,
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
  // mov    %rsi,%rax
  0x48, 0x89, 0xf0,
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
  // movzbl 0x1234567(%rsi),%eax
  0x0f, 0xb6, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter8TemplateX86_64[] = {
  // mov    %dl,0x1234567(%rsi)
  0x88, 0x96, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter16TemplateX86_64[] = {
  // movzwl 0x1234567(%rsi),%eax
  0x0f, 0xb7, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter16TemplateX86_64[] = {
  // mov    %dx,0x1234567(%rsi)
  0x66, 0x89, 0x96, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter32TemplateX86_64[] = {
  // mov    0x1234567(%rsi),%eax
  0x8b, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter32TemplateX86_64[] = {
  // mov    %edx,0x1234567(%rsi)
  0x89, 0x96, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetter64TemplateX86_64[] = {
  // mov    0x1234567(%rsi),%rax
  0x48, 0x8b, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetter64TemplateX86_64[] = {
  // mov    %rdx,0x1234567(%rsi)
  0x48, 0x89, 0x96, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetterFloatTemplateX86_64[] = {
  // movss  0x1234567(%rsi),%xmm0
  0xf3, 0x0f, 0x10, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetterFloatTemplateX86_64[] = {
  // movss  %xmm0,0x1234567(%rsi)
  0xf3, 0x0f, 0x11, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldGetterDoubleTemplateX86_64[] = {
  // movsd  0x1234567(%rsi),%xmm0
  0xf2, 0x0f, 0x10, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static char instanceFieldSetterDoubleTemplateX86_64[] = {
  // movsd  %xmm0,0x1234567(%rsi)
  0xf2, 0x0f, 0x11, 0x86, 0x67, 0x45, 0x23, 0x01,
  // retq
  0xc3
};

static void* allocateMemoryForFunction(Env* env, int size, char* templatePtr) {
    void* m = nvmAllocateExecutableMemory(env, size);
    if (!m) return NULL;
    memcpy(m, templatePtr, size);
    return m;
}

jbyte (*nvmCreateClassFieldGetter8(Env* env, jbyte* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetter8TemplateX86_64), classFieldGetter8TemplateX86_64);
    if (!m) return NULL;
    *((jbyte**)(m + 1)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetter8(Env* env, jbyte* ptr))(jbyte) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetter8TemplateX86_64), classFieldSetter8TemplateX86_64);
    if (!m) return NULL;
    *((jbyte**)(m + 3)) = ptr;
    return m;
}

jshort (*nvmCreateClassFieldGetter16(Env* env, jshort* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetter16TemplateX86_64), classFieldGetter16TemplateX86_64);
    if (!m) return NULL;
    *((jshort**)(m + 2)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetter16(Env* env, jshort* ptr))(jshort) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetter16TemplateX86_64), classFieldSetter16TemplateX86_64);
    if (!m) return NULL;
    *((jshort**)(m + 4)) = ptr;
    return m;
}

jint (*nvmCreateClassFieldGetter32(Env* env, jint* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetter32TemplateX86_64), classFieldGetter32TemplateX86_64);
    if (!m) return NULL;
    *((jint**)(m + 1)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetter32(Env* env, jint* ptr))(jint) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetter32TemplateX86_64), classFieldSetter32TemplateX86_64);
    if (!m) return NULL;
    *((jint**)(m + 3)) = ptr;
    return m;
}

jlong (*nvmCreateClassFieldGetter64(Env* env, jlong* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetter64TemplateX86_64), classFieldGetter64TemplateX86_64);
    if (!m) return NULL;
    *((jlong**)(m + 2)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetter64(Env* env, jlong* ptr))(jlong) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetter64TemplateX86_64), classFieldSetter64TemplateX86_64);
    if (!m) return NULL;
    *((jlong**)(m + 5)) = ptr;
    return m;
}

jfloat (*nvmCreateClassFieldGetterFloat(Env* env, jfloat* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetterFloatTemplateX86_64), classFieldGetterFloatTemplateX86_64);
    if (!m) return NULL;
    *((jfloat**)(m + 2)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetterFloat(Env* env, jfloat* ptr))(jfloat) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetterFloatTemplateX86_64), classFieldSetterFloatTemplateX86_64);
    if (!m) return NULL;
    *((jfloat**)(m + 2)) = ptr;
    return m;
}

jdouble (*nvmCreateClassFieldGetterDouble(Env* env, jdouble* ptr))(void) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldGetterDoubleTemplateX86_64), classFieldGetterDoubleTemplateX86_64);
    if (!m) return NULL;
    *((jdouble**)(m + 2)) = ptr;
    return m;
}

void (*nvmCreateClassFieldSetterDouble(Env* env, jdouble* ptr))(jdouble) {
    void* m = allocateMemoryForFunction(env, sizeof(classFieldSetterDoubleTemplateX86_64), classFieldSetterDoubleTemplateX86_64);
    if (!m) return NULL;
    *((jdouble**)(m + 2)) = ptr;
    return m;
}

jbyte (*nvmCreateInstanceFieldGetter8(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetter8TemplateX86_64), instanceFieldGetter8TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 3)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetter8(Env* env, jint offset))(Object*, jbyte) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetter8TemplateX86_64), instanceFieldSetter8TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 2)) = offset;
    return m;
}

jshort (*nvmCreateInstanceFieldGetter16(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetter16TemplateX86_64), instanceFieldGetter16TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 3)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetter16(Env* env, jint offset))(Object*, jshort) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetter16TemplateX86_64), instanceFieldSetter16TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 3)) = offset;
    return m;
}

jint (*nvmCreateInstanceFieldGetter32(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetter32TemplateX86_64), instanceFieldGetter32TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 2)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetter32(Env* env, jint offset))(Object*, jint) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetter32TemplateX86_64), instanceFieldSetter32TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 2)) = offset;
    return m;
}

jlong (*nvmCreateInstanceFieldGetter64(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetter64TemplateX86_64), instanceFieldGetter64TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 3)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetter64(Env* env, jint offset))(Object*, jlong) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetter64TemplateX86_64), instanceFieldSetter64TemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 3)) = offset;
    return m;
}

jfloat (*nvmCreateInstanceFieldGetterFloat(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetterFloatTemplateX86_64), instanceFieldGetterFloatTemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 4)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetterFloat(Env* env, jint offset))(Object*, jlong) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetterFloatTemplateX86_64), instanceFieldSetterFloatTemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 4)) = offset;
    return m;
}

jdouble (*nvmCreateInstanceFieldGetterDouble(Env* env, jint offset))(Object*) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldGetterDoubleTemplateX86_64), instanceFieldGetterDoubleTemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 4)) = offset;
    return m;
}

void (*nvmCreateInstanceFieldSetterDouble(Env* env, jint offset))(Object*, jdouble) {
    void* m = allocateMemoryForFunction(env, sizeof(instanceFieldSetterDoubleTemplateX86_64), instanceFieldSetterDoubleTemplateX86_64);
    if (!m) return NULL;
    *((jint*)(m + 4)) = offset;
    return m;
}

void* nvmCreateFieldGetter(Env* env, Class* clazz, Field* field) {
    void* ptr = (jbyte*) clazz->data + field->offset;
    jint offset = offsetof(Object, data) + clazz->instanceDataOffset + field->offset;

    switch (field->desc[0]) {
    case 'B':
    case 'Z':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter8(env, ptr) : (void*) nvmCreateInstanceFieldGetter8(env, offset);
    case 'S':
    case 'C':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter16(env, ptr) : (void*) nvmCreateInstanceFieldGetter16(env, offset);
    case 'F':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetterFloat(env, ptr) : (void*) nvmCreateInstanceFieldGetterFloat(env, offset);
    case 'D':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetterDouble(env, ptr) : (void*) nvmCreateInstanceFieldGetterDouble(env, offset);
    case 'I':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter32(env, ptr) : (void*) nvmCreateInstanceFieldGetter32(env, offset);
    case 'J':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter64(env, ptr) : (void*) nvmCreateInstanceFieldGetter64(env, offset);
    }
    // Reference
    // TODO: Return 32-bit getters on 32-bit archs
    return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldGetter64(env, ptr) : (void*) nvmCreateInstanceFieldGetter64(env, offset);
}

void* nvmCreateFieldSetter(Env* env, Class* clazz, Field* field) {
    void* ptr = (jbyte*) clazz->data + field->offset;
    jint offset = offsetof(Object, data) + clazz->instanceDataOffset + field->offset;

    switch (field->desc[0]) {
    case 'B':
    case 'Z':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter8(env, ptr) : (void*) nvmCreateInstanceFieldSetter8(env, offset);
    case 'S':
    case 'C':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter16(env, ptr) : (void*) nvmCreateInstanceFieldSetter16(env, offset);
    case 'F':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetterFloat(env, ptr) : (void*) nvmCreateInstanceFieldSetterFloat(env, offset);
    case 'D':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetterDouble(env, ptr) : (void*) nvmCreateInstanceFieldSetterDouble(env, offset);
    case 'I':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter32(env, ptr) : (void*) nvmCreateInstanceFieldSetter32(env, offset);
    case 'J':
        return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter64(env, ptr) : (void*) nvmCreateInstanceFieldSetter64(env, offset);
    }
    // Reference
    // TODO: Return 32-bit setters on 32-bit archs
    return field->access & ACC_STATIC ? (void*) nvmCreateClassFieldSetter64(env, ptr) : (void*) nvmCreateInstanceFieldSetter64(env, offset);
}

static Field* getField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field;
    for (field = clazz->fields; field != NULL; field = field->next) {
        if (!strcmp(field->name, name) && !strcmp(field->desc, desc)) {
            return field;
        }
    }

    if (clazz->superclass) {
        return getField(env, clazz->superclass, name, desc);
    }

    nvmThrowNoSuchFieldError(env, name);
    return NULL;
}

Field* nvmGetClassField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = getField(env, clazz, name, desc);
    if (!field) return NULL;
    if (!(field->access & ACC_STATIC)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorClassField(env, clazz, name, desc);
        return NULL;
    }
    return field;
}

Field* nvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = getField(env, clazz, name, desc);
    if (!field) return NULL;
    if (field->access & ACC_STATIC) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorInstanceField(env, clazz, name, desc);
        return NULL;
    }
    return field;
}

/*
Field* nvmGetField(Class* clazz, char* name, char* desc, Class* caller) {
    Field* field;
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
*/

