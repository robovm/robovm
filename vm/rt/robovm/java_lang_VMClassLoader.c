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

char* toBinaryName(Env* env, Object* className) {
    if (!className) {
        rvmThrowNew(env, java_lang_NullPointerException, "className");
        return NULL;
    }
    char* classNameUTF = rvmGetStringUTFChars(env, className);
    if (!classNameUTF) return NULL;
    jint i;
    for (i = 0; classNameUTF[i] != '\0'; i++) {
        if (classNameUTF[i] == '.') classNameUTF[i] = '/';
    }
    return classNameUTF;
}

Class* Java_java_lang_VMClassLoader_findLoadedClass(Env* env, Class* cls, ClassLoader* cl, Object* name) {
    char* classNameUTF = toBinaryName(env, name);
    if (!classNameUTF) return NULL;
    return rvmFindLoadedClass(env, classNameUTF, cl);
}

Class* Java_java_lang_VMClassLoader_findClassInClasspathForLoader(Env* env, Class* cls, ClassLoader* cl, Object* name) {
    char* classNameUTF = toBinaryName(env, name);
    if (!classNameUTF) return NULL;
    Class* clazz = rvmFindClassInClasspathForLoader(env, classNameUTF, cl);
    if (!clazz) return NULL;
    return clazz;
}

