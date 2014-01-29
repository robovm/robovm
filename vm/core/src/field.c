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
#include <string.h>

static Field* getField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = rvmGetFields(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;
    for (; field != NULL; field = field->next) {
        if (!strcmp(field->name, name) && !strcmp(field->desc, desc)) {
            return field;
        }
    }

    Interface* interfaze = rvmGetInterfaces(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;
    for (; interfaze != NULL; interfaze = interfaze->next) {
        field = getField(env, interfaze->interfaze, name, desc);
        if (rvmExceptionCheck(env)) return NULL;
        if (field) return field;
    }

    if (clazz->superclass) {
        return getField(env, clazz->superclass, name, desc);
    }

    return NULL;
}

Field* rvmGetField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = getField(env, clazz, name, desc);
    if (rvmExceptionCheck(env)) return NULL;
    if (!field) {
        rvmThrowNoSuchFieldError(env, name);
        return NULL;
    }
    return field;
}

ClassField* rvmGetClassField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = rvmGetField(env, clazz, name, desc);
    if (!field) return NULL;
    if (!(field->access & ACC_STATIC)) {
        // TODO: JNI spec doesn't say anything about throwing this
        rvmThrowIncompatibleClassChangeErrorClassField(env, clazz, name, desc);
        return NULL;
    }
    return (ClassField*) field;
}

InstanceField* rvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc) {
    Field* field = rvmGetField(env, clazz, name, desc);
    if (!field) return NULL;
    if (field->access & ACC_STATIC) {
        // TODO: JNI spec doesn't say anything about throwing this
        rvmThrowIncompatibleClassChangeErrorInstanceField(env, clazz, name, desc);
        return NULL;
    }
    return (InstanceField*) field;
}

static inline void* getFieldAddress(Object* obj, InstanceField* field) {
    return (void*) ((jbyte*) obj + field->offset);
}

Object* rvmGetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(Object**) getFieldAddress(obj, field);
}

jboolean rvmGetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jboolean*) getFieldAddress(obj, field);
}

jbyte rvmGetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jbyte*) getFieldAddress(obj, field);
}

jchar rvmGetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jchar*) getFieldAddress(obj, field);
}

jshort rvmGetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jshort*) getFieldAddress(obj, field);
}

jint rvmGetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jint*) getFieldAddress(obj, field);
}

jlong rvmGetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jlong*) getFieldAddress(obj, field);
}

jfloat rvmGetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jfloat*) getFieldAddress(obj, field);
}

jdouble rvmGetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field) {
    return *(jdouble*) getFieldAddress(obj, field);
}

void rvmSetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field, Object* value) {
    *(Object**) getFieldAddress(obj, field) = value;
}

void rvmSetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jboolean value) {
    *(jboolean*) getFieldAddress(obj, field) = value;
}

void rvmSetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jbyte value) {
    *(jbyte*) getFieldAddress(obj, field) = value;
}

void rvmSetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jchar value) {
    *(jchar*) getFieldAddress(obj, field) = value;
}

void rvmSetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jshort value) {
    *(jshort*) getFieldAddress(obj, field) = value;
}

void rvmSetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jint value) {
    *(jint*) getFieldAddress(obj, field) = value;
}

void rvmSetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jlong value) {
    *(jlong*) getFieldAddress(obj, field) = value;
}

void rvmSetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jfloat value) {
    *(jfloat*) getFieldAddress(obj, field) = value;
}

void rvmSetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jdouble value) {
    *(jdouble*) getFieldAddress(obj, field) = value;
}

Object* rvmGetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(Object**) field->address;
}

jboolean rvmGetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jboolean*) field->address;
}

jbyte rvmGetByteClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jbyte*) field->address;
}

jchar rvmGetCharClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jchar*) field->address;
}

jshort rvmGetShortClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jshort*) field->address;
}

jint rvmGetIntClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jint*) field->address;
}

jlong rvmGetLongClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jlong*) field->address;
}

jfloat rvmGetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jfloat*) field->address;
}

jdouble rvmGetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field) {
    rvmInitialize(env, field->field.clazz);
    return *(jdouble*) field->address;
}

void rvmSetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field, Object* value) {
    rvmInitialize(env, field->field.clazz);
    *(Object**) field->address = value;
}

void rvmSetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field, jboolean value) {
    rvmInitialize(env, field->field.clazz);
    *(jboolean*) field->address = value;
}

void rvmSetByteClassFieldValue(Env* env, Class* clazz, ClassField* field, jbyte value) {
    rvmInitialize(env, field->field.clazz);
    *(jbyte*) field->address = value;
}

void rvmSetCharClassFieldValue(Env* env, Class* clazz, ClassField* field, jchar value) {
    rvmInitialize(env, field->field.clazz);
    *(jchar*) field->address = value;
}

void rvmSetShortClassFieldValue(Env* env, Class* clazz, ClassField* field, jshort value) {
    rvmInitialize(env, field->field.clazz);
    *(jshort*) field->address = value;
}

void rvmSetIntClassFieldValue(Env* env, Class* clazz, ClassField* field, jint value) {
    rvmInitialize(env, field->field.clazz);
    *(jint*) field->address = value;
}

void rvmSetLongClassFieldValue(Env* env, Class* clazz, ClassField* field, jlong value) {
    rvmInitialize(env, field->field.clazz);
    *(jlong*) field->address = value;
}

void rvmSetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field, jfloat value) {
    rvmInitialize(env, field->field.clazz);
    *(jfloat*) field->address = value;
}

void rvmSetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field, jdouble value) {
    rvmInitialize(env, field->field.clazz);
    *(jdouble*) field->address = value;
}

