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

Class* Java_java_lang_reflect_Field_getDeclaringClass(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return field->clazz;
}

Object* Java_java_lang_reflect_Field_getName(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return rvmNewStringUTF(env, field->name, -1);
}

jint Java_java_lang_reflect_Field_getModifiers(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return (field->access & FIELD_ACCESS_MASK) & ~(ACC_SYNTHETIC);
}

Class* Java_java_lang_reflect_Field_getType(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return rvmFindClassByDescriptor(env, field->desc, field->clazz->classLoader);
}

Object* Java_java_lang_reflect_Field_getSignatureAttribute(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return rvmAttributeGetFieldSignature(env, field);
}

ObjectArray* Java_java_lang_reflect_Field_getDeclaredAnnotations(Env* env, Class* clazz, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    return rvmAttributeGetFieldRuntimeVisibleAnnotations(env, field);
}

jboolean Java_java_lang_reflect_Field_getZ(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetBooleanClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetBooleanInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jbyte Java_java_lang_reflect_Field_getB(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetByteClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetByteInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jchar Java_java_lang_reflect_Field_getC(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetCharClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetCharInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jshort Java_java_lang_reflect_Field_getS(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetShortClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetShortInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jint Java_java_lang_reflect_Field_getI(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetIntClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetIntInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jlong Java_java_lang_reflect_Field_getJ(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetLongClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetLongInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jfloat Java_java_lang_reflect_Field_getF(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetFloatClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetFloatInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

jdouble Java_java_lang_reflect_Field_getD(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetDoubleClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetDoubleInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

Object* Java_java_lang_reflect_Field_getL(Env* env, Class* clazz, Object* o, jlong fieldPtr) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        return rvmGetObjectClassFieldValue(env, field->clazz, (ClassField*) field);
    } else {
        assert(o != NULL);
        return rvmGetObjectInstanceFieldValue(env, o, (InstanceField*) field);
    }
}

void Java_java_lang_reflect_Field_setZ(Env* env, Class* clazz, Object* o, jlong fieldPtr, jboolean value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetBooleanClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetBooleanInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setB(Env* env, Class* clazz, Object* o, jlong fieldPtr, jbyte value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetByteClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetByteInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setC(Env* env, Class* clazz, Object* o, jlong fieldPtr, jchar value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetCharClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetCharInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setS(Env* env, Class* clazz, Object* o, jlong fieldPtr, jshort value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetShortClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetShortInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setI(Env* env, Class* clazz, Object* o, jlong fieldPtr, jint value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetIntClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetIntInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setJ(Env* env, Class* clazz, Object* o, jlong fieldPtr, jlong value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetLongClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetLongInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setF(Env* env, Class* clazz, Object* o, jlong fieldPtr, jfloat value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetFloatClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetFloatInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setD(Env* env, Class* clazz, Object* o, jlong fieldPtr, jdouble value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetDoubleClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetDoubleInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}

void Java_java_lang_reflect_Field_setL(Env* env, Class* clazz, Object* o, jlong fieldPtr, Object* value) {
    Field* field = (Field*) LONG_TO_PTR(fieldPtr);
    assert(field != NULL);
    if (FIELD_IS_STATIC(field)) {
        rvmSetObjectClassFieldValue(env, field->clazz, (ClassField*) field, value);
    } else {
        assert(o != NULL);
        rvmSetObjectInstanceFieldValue(env, o, (InstanceField*) field, value);
    }
}
