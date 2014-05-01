/*
 * Copyright (C) 2012 Trillian Mobile AB
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

static Class* java_lang_reflect_Method = NULL;
static Method* java_lang_reflect_Method_init = NULL;
static Class* java_lang_reflect_Constructor = NULL;
static Method* java_lang_reflect_Constructor_init = NULL;
static Class* java_lang_reflect_Field = NULL;
static Method* java_lang_reflect_Field_init = NULL;
static InstanceField* java_lang_reflect_Method_method = NULL;
static InstanceField* java_lang_reflect_Field_field = NULL;
static Class* java_lang_reflect_InvocationTargetException = NULL;
static Method* java_lang_reflect_InvocationTargetException_init = NULL;

static jvalue emptyJValueArgs[1];
jvalue* validateAndUnwrapArgs(Env* env, ObjectArray* parameterTypes, ObjectArray* args) {
    jint length = args->length;
    jvalue* jvalueArgs = length > 0 ? (jvalue*) rvmAllocateMemory(env, sizeof(jvalue) * length) : emptyJValueArgs;
    if (!jvalueArgs) return NULL;

    jint i;
    for (i = 0; i < length; i++) {
        Object* arg = args->values[i];
        Class* type = (Class*) parameterTypes->values[i];
        if (CLASS_IS_PRIMITIVE(type)) {
            if (arg == NULL) {
                const char* typeName = rvmGetHumanReadableClassName(env, type);
                if (typeName) {
                    rvmThrowNewf(env, java_lang_IllegalArgumentException, 
                        "argument %d should have type %s, got null", i + 1, typeName);
                }
                return NULL;
            }
            if (!rvmUnbox(env, arg, type, &jvalueArgs[i])) {
                if (rvmExceptionOccurred(env)->clazz == java_lang_ClassCastException) {
                    rvmExceptionClear(env);
                    const char* argTypeName = rvmGetHumanReadableClassName(env, arg->clazz);
                    const char* typeName = argTypeName ? rvmGetHumanReadableClassName(env, type) : NULL;
                    if (argTypeName && typeName) {
                        rvmThrowNewf(env, java_lang_IllegalArgumentException, 
                            "argument %d should have type %s, got %s", i + 1, typeName, argTypeName);
                    }
                }
                return NULL;
            }
        } else {
            if (arg && !rvmIsInstanceOf(env, arg, type)) {
                const char* argTypeName = rvmGetHumanReadableClassName(env, arg->clazz);
                const char* typeName = argTypeName ? rvmGetHumanReadableClassName(env, type) : NULL;
                if (argTypeName && typeName) {
                    rvmThrowNewf(env, java_lang_IllegalArgumentException, 
                        "argument %d should have type %s, got %s", i + 1, typeName, argTypeName);
                }
                return NULL;
            }
            jvalueArgs[i].l = (jobject) arg;
        }
    }
    return jvalueArgs;
}

Object* createMethodObject(Env* env, Method* method) {
    if (!java_lang_reflect_Method) {
        java_lang_reflect_Method = rvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
        if (!java_lang_reflect_Method) return NULL;
    }
    if (!java_lang_reflect_Method_init) {
        java_lang_reflect_Method_init = rvmGetInstanceMethod(env, java_lang_reflect_Method, "<init>", "(J)V");
        if (!java_lang_reflect_Method_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, java_lang_reflect_Method, java_lang_reflect_Method_init, initArgs);
}

Object* createFieldObject(Env* env, Field* field) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = rvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_init) {
        java_lang_reflect_Field_init = rvmGetInstanceMethod(env, java_lang_reflect_Field, "<init>", "(J)V");
        if (!java_lang_reflect_Field_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(field);
    return rvmNewObjectA(env, java_lang_reflect_Field, java_lang_reflect_Field_init, initArgs);
}

Object* createConstructorObject(Env* env, Method* method) {
    if (!java_lang_reflect_Constructor) {
        java_lang_reflect_Constructor = rvmFindClassUsingLoader(env, "java/lang/reflect/Constructor", NULL);
        if (!java_lang_reflect_Constructor) return NULL;
    }
    if (!java_lang_reflect_Constructor_init) {
        java_lang_reflect_Constructor_init = rvmGetInstanceMethod(env, java_lang_reflect_Constructor, "<init>", "(J)V");
        if (!java_lang_reflect_Constructor_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, java_lang_reflect_Constructor, java_lang_reflect_Constructor_init, initArgs);
}

Method* getMethodFromMethodObject(Env* env, Object* methodObject) {
    if (!java_lang_reflect_Method) {
        java_lang_reflect_Method = rvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
        if (!java_lang_reflect_Method) return NULL;
    }
    if (!java_lang_reflect_Method_method) {
        java_lang_reflect_Method_method = rvmGetInstanceField(env, java_lang_reflect_Method, "method", "J");
        if (!java_lang_reflect_Method_method) return NULL;
    }
    return (Method*) LONG_TO_PTR(rvmGetLongInstanceFieldValue(env, methodObject, java_lang_reflect_Method_method));
}

Field* getFieldFromFieldObject(Env* env, Object* fieldObject) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = rvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_field) {
        java_lang_reflect_Field_field = rvmGetInstanceField(env, java_lang_reflect_Field, "field", "J");
        if (!java_lang_reflect_Field_field) return NULL;
    }
    return (Field*) LONG_TO_PTR(rvmGetLongInstanceFieldValue(env, fieldObject, java_lang_reflect_Field_field));
}

void throwInvocationTargetException(Env* env, Object* throwable) {
    rvmExceptionClear(env);
    if (!java_lang_reflect_InvocationTargetException) {
        java_lang_reflect_InvocationTargetException = rvmFindClassUsingLoader(env, "java/lang/reflect/InvocationTargetException", NULL);
        if (!java_lang_reflect_InvocationTargetException) return;
    }
    if (!java_lang_reflect_InvocationTargetException_init) {
        java_lang_reflect_InvocationTargetException_init = rvmGetMethod(env, java_lang_reflect_InvocationTargetException, "<init>", "(Ljava/lang/Throwable;)V");
        if (!java_lang_reflect_InvocationTargetException_init) return;
    }
    jvalue initArgs[1];
    initArgs[0].l = (jobject) throwable;
    Object* exception = rvmNewObjectA(env, java_lang_reflect_InvocationTargetException, java_lang_reflect_InvocationTargetException_init, initArgs);
    if (!exception) return;
    rvmThrow(env, exception);
}
