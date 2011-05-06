#include <nullvm.h>
#include <string.h>

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

ClassField* nvmGetClassField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = getField(env, clazz, name, desc);
    if (!field) return NULL;
    if (!(field->access & ACC_STATIC)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorClassField(env, clazz, name, desc);
        return NULL;
    }
    return (ClassField*) field;
}

InstanceField* nvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = getField(env, clazz, name, desc);
    if (!field) return NULL;
    if (field->access & ACC_STATIC) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorInstanceField(env, clazz, name, desc);
        return NULL;
    }
    return (InstanceField*) field;
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

static inline void* getFieldAddress(Object* obj, InstanceField* field) {
    return (void*) ((jbyte*) obj + field->offset);
}

Object* nvmGetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(Object**) getFieldAddress(obj, field);
}

jboolean nvmGetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jboolean*) getFieldAddress(obj, field);
}

jbyte nvmGetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jbyte*) getFieldAddress(obj, field);
}

jchar nvmGetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jchar*) getFieldAddress(obj, field);
}

jshort nvmGetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jshort*) getFieldAddress(obj, field);
}

jint nvmGetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jint*) getFieldAddress(obj, field);
}

jlong nvmGetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jlong*) getFieldAddress(obj, field);
}

jfloat nvmGetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jfloat*) getFieldAddress(obj, field);
}

jdouble nvmGetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jdouble*) getFieldAddress(obj, field);
}

void nvmSetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field, Object* value) {
    *(Object**) getFieldAddress(obj, field) = value;
}

void nvmSetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jboolean value) {
    *(jboolean*) getFieldAddress(obj, field) = value;
}

void nvmSetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jbyte value) {
    *(jbyte*) getFieldAddress(obj, field) = value;
}

void nvmSetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jchar value) {
    *(jchar*) getFieldAddress(obj, field) = value;
}

void nvmSetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jshort value) {
    *(jshort*) getFieldAddress(obj, field) = value;
}

void nvmSetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jint value) {
    *(jint*) getFieldAddress(obj, field) = value;
}

void nvmSetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jlong value) {
    *(jlong*) getFieldAddress(obj, field) = value;
}

void nvmSetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jfloat value) {
    *(jfloat*) getFieldAddress(obj, field) = value;
}

void nvmSetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jdouble value) {
    *(jdouble*) getFieldAddress(obj, field) = value;
}

Object* nvmGetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(Object**) field->address;
}

jboolean nvmGetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jboolean*) field->address;
}

jbyte nvmGetByteClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jbyte*) field->address;
}

jchar nvmGetCharClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jchar*) field->address;
}

jshort nvmGetShortClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jshort*) field->address;
}

jint nvmGetIntClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jint*) field->address;
}

jlong nvmGetLongClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jlong*) field->address;
}

jfloat nvmGetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jfloat*) field->address;
}

jdouble nvmGetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    nvmInitialize(env, field->field.clazz);
    return *(jdouble*) field->address;
}

void nvmSetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field, Object* value) {
    nvmInitialize(env, field->field.clazz);
    *(Object**) field->address = value;
}

void nvmSetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field, jboolean value) {
    nvmInitialize(env, field->field.clazz);
    *(jboolean*) field->address = value;
}

void nvmSetByteClassFieldValue(Env* env, Class* clazz, ClassField* field, jbyte value) {
    nvmInitialize(env, field->field.clazz);
    *(jbyte*) field->address = value;
}

void nvmSetCharClassFieldValue(Env* env, Class* clazz, ClassField* field, jchar value) {
    nvmInitialize(env, field->field.clazz);
    *(jchar*) field->address = value;
}

void nvmSetShortClassFieldValue(Env* env, Class* clazz, ClassField* field, jshort value) {
    nvmInitialize(env, field->field.clazz);
    *(jshort*) field->address = value;
}

void nvmSetIntClassFieldValue(Env* env, Class* clazz, ClassField* field, jint value) {
    nvmInitialize(env, field->field.clazz);
    *(jint*) field->address = value;
}

void nvmSetLongClassFieldValue(Env* env, Class* clazz, ClassField* field, jlong value) {
    nvmInitialize(env, field->field.clazz);
    *(jlong*) field->address = value;
}

void nvmSetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field, jfloat value) {
    nvmInitialize(env, field->field.clazz);
    *(jfloat*) field->address = value;
}

void nvmSetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field, jdouble value) {
    nvmInitialize(env, field->field.clazz);
    *(jdouble*) field->address = value;
}

