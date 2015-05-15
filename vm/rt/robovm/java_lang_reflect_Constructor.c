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
#include "reflection_helpers.h"

Object* Java_java_lang_reflect_Constructor_internalNewInstance(Env* env, Class* clazz, jlong methodPtr, ObjectArray* parameterTypes, ObjectArray* args) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);

    /*
     * The Java code has already checked that the constructor is accessible
     * to the the caller, that the class can be instatiated and that the number 
     * of arguments are correct. The args array is never null.
     */

    jvalue* jvalueArgs = validateAndUnwrapArgs(env, parameterTypes, args);
    if (!jvalueArgs) return NULL;

    Object* o = rvmNewObjectA(env, method->clazz, method, jvalueArgs);
    if (!o) {
        Object* exception = rvmExceptionOccurred(env);
        if (exception->clazz != java_lang_ExceptionInInitializerError) {
            throwInvocationTargetException(env, rvmExceptionOccurred(env));
        }
        return NULL;
    }
    return o;
}

