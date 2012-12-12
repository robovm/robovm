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

