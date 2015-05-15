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
#include <robovm.h>

Object* Java_java_lang_ClassCache_loadReflectionAccess(Env* env, Class* c) {
    Class* ao = rvmFindClassUsingLoader(env, "java/lang/reflect/AccessibleObject", NULL);
    if (!ao) return NULL;
    ClassField* f = rvmGetClassField(env, ao, "REFLECTION_ACCESS", "Lorg/robovm/rt/ReflectionAccess;");
    if (!f) return NULL;
    return rvmGetObjectClassFieldValue(env, ao, f);
}

