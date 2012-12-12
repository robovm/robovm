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
#ifndef ROBOVM_FIELD_H
#define ROBOVM_FIELD_H

#define FIELD_ACCESS_MASK   0x0000FFFF

#define FIELD_IS_PUBLIC(f) (IS_PUBLIC((f)->access))
#define FIELD_IS_PRIVATE(f) (IS_PRIVATE((f)->access))
#define FIELD_IS_PROTECTED(f) (IS_PROTECTED((f)->access))
#define FIELD_IS_STATIC(f) (IS_STATIC((f)->access))
#define FIELD_IS_FINAL(f) (IS_FINAL((f)->access))
#define FIELD_IS_PACKAGE_PRIVATE(f) (IS_PACKAGE_PRIVATE((f)->access))

extern Field* rvmGetField(Env* env, Class* clazz, char* name, char* desc);

/**
 * Returns the class field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IncompatibleClassChangeError if the field hasn't got the ACC_STATIC modifier.
 */
extern ClassField* rvmGetClassField(Env* env, Class* clazz, char* name, char* desc);

/**
 * Returns the instance field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IncompatibleClassChangeError if the field has got the ACC_STATIC modifier.
 */
extern InstanceField* rvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc);

extern Object* rvmGetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jboolean rvmGetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jbyte rvmGetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jchar rvmGetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jshort rvmGetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jint rvmGetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jlong rvmGetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jfloat rvmGetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jdouble rvmGetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern void rvmSetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field, Object* value);
extern void rvmSetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jboolean value);
extern void rvmSetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jbyte value);
extern void rvmSetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jchar value);
extern void rvmSetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jshort value);
extern void rvmSetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jint value);
extern void rvmSetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jlong value);
extern void rvmSetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jfloat value);
extern void rvmSetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jdouble value);

extern Object* rvmGetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jboolean rvmGetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jbyte rvmGetByteClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jchar rvmGetCharClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jshort rvmGetShortClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jint rvmGetIntClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jlong rvmGetLongClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jfloat rvmGetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jdouble rvmGetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern void rvmSetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field, Object* value);
extern void rvmSetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field, jboolean value);
extern void rvmSetByteClassFieldValue(Env* env, Class* clazz, ClassField* field, jbyte value);
extern void rvmSetCharClassFieldValue(Env* env, Class* clazz, ClassField* field, jchar value);
extern void rvmSetShortClassFieldValue(Env* env, Class* clazz, ClassField* field, jshort value);
extern void rvmSetIntClassFieldValue(Env* env, Class* clazz, ClassField* field, jint value);
extern void rvmSetLongClassFieldValue(Env* env, Class* clazz, ClassField* field, jlong value);
extern void rvmSetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field, jfloat value);
extern void rvmSetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field, jdouble value);

#endif

