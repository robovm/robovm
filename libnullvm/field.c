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

Object* nvmGetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(Object**) ((jbyte*) obj + field->offset);
}

jboolean nvmGetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jboolean*) ((jbyte*) obj + field->offset);
}

jbyte nvmGetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jbyte*) ((jbyte*) obj + field->offset);
}

jchar nvmGetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jchar*) ((jbyte*) obj + field->offset);
}

jshort nvmGetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jshort*) ((jbyte*) obj + field->offset);
}

jint nvmGetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jint*) ((jbyte*) obj + field->offset);
}

jlong nvmGetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jlong*) ((jbyte*) obj + field->offset);
}

jfloat nvmGetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jfloat*) ((jbyte*) obj + field->offset);
}

jdouble nvmGetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jdouble*) ((jbyte*) obj + field->offset);
}

void nvmSetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field, Object* value) {
    *(Object**) ((jbyte*) obj + field->offset) = value;
}

void nvmSetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jboolean value) {
    *(jboolean*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jbyte value) {
    *(jbyte*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jchar value) {
    *(jchar*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jshort value) {
    *(jshort*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jint value) {
    *(jint*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jlong value) {
    *(jlong*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jfloat value) {
    *(jfloat*) ((jbyte*) obj + field->offset) = value;
}

void nvmSetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jdouble value) {
    *(jdouble*) ((jbyte*) obj + field->offset) = value;
}

