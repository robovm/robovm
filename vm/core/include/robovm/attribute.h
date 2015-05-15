/*
 * Copyright (C) 2012 RoboVM AB
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
#ifndef ROBOVM_ATTRIBUTE_H
#define ROBOVM_ATTRIBUTE_H

extern jboolean rvmInitAttributes(Env* env);
extern Class* rvmAttributeGetDeclaringClass(Env* env, Class* clazz);
extern Class* rvmAttributeGetEnclosingClass(Env* env, Class* clazz);
extern Method* rvmAttributeGetEnclosingMethod(Env* env, Class* clazz);
extern jboolean rvmAttributeIsAnonymousClass(Env* env, Class* clazz);
extern Object* rvmAttributeGetClassSignature(Env* env, Class* clazz);
extern Object* rvmAttributeGetClassSourceFile(Env* env, Class* clazz);
extern Object* rvmAttributeGetMethodSignature(Env* env, Method* method);
extern Object* rvmAttributeGetFieldSignature(Env* env, Field* field);
extern Object* rvmAttributeGetAnnotationDefault(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetExceptions(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetClassRuntimeVisibleAnnotations(Env* env, Class* clazz);
extern ObjectArray* rvmAttributeGetMethodRuntimeVisibleAnnotations(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetFieldRuntimeVisibleAnnotations(Env* env, Field* field);
extern ObjectArray* rvmAttributeGetMethodRuntimeVisibleParameterAnnotations(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetDeclaredClasses(Env* env, Class* clazz);
extern jboolean rvmAttributeGetInnerClass(Env* env, Class* clazz, Object** innerClassName, jint* access);

#endif

