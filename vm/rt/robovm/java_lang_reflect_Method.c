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
#include "reflection_helpers.h"

Object* Java_java_lang_reflect_Method_internalInvoke(Env* env, Class* clazz, jlong methodPtr, ObjectArray* parameterTypes, Object* receiver, ObjectArray* args) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);

    /*
     * The Java code has already checked that the method is accessible
     * to the the caller, that the receiver isn't null (for non static methods),
     * that the receiver is an instance of the declaring class (for non static methods)
     * and that the number of arguments are correct. The args array is never null.
     */

    jvalue* jvalueArgs = validateAndUnwrapArgs(env, parameterTypes, args);
    if (!jvalueArgs) return NULL;

    const char* retDesc = rvmGetReturnType(method->desc);

    jvalue jvalueRet[1];
    if (METHOD_IS_STATIC(method)) {
        switch (retDesc[0]) {
        case 'V':
            rvmCallVoidClassMethodA(env, method->clazz, method, jvalueArgs);
            jvalueRet->l = NULL;
            break;
        case 'Z':
            jvalueRet->z = rvmCallBooleanClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'B':
            jvalueRet->b = rvmCallByteClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'S':
            jvalueRet->s = rvmCallShortClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'C':
            jvalueRet->c = rvmCallCharClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'I':
            jvalueRet->i = rvmCallIntClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'J':
            jvalueRet->j = rvmCallLongClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'F':
            jvalueRet->f = rvmCallFloatClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        case 'D':
            jvalueRet->d = rvmCallDoubleClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        default:
            jvalueRet->l = (jobject) rvmCallObjectClassMethodA(env, method->clazz, method, jvalueArgs);
            break;
        }
    } else {
        switch (retDesc[0]) {
        case 'V':
            rvmCallVoidInstanceMethodA(env, receiver, method, jvalueArgs);
            jvalueRet->l = NULL;
            break;
        case 'Z':
            jvalueRet->z = rvmCallBooleanInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'B':
            jvalueRet->b = rvmCallByteInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'S':
            jvalueRet->s = rvmCallShortInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'C':
            jvalueRet->c = rvmCallCharInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'I':
            jvalueRet->i = rvmCallIntInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'J':
            jvalueRet->j = rvmCallLongInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'F':
            jvalueRet->f = rvmCallFloatInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        case 'D':
            jvalueRet->d = rvmCallDoubleInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        default:
            jvalueRet->l = (jobject) rvmCallObjectInstanceMethodA(env, receiver, method, jvalueArgs);
            break;
        }
    }

    if (rvmExceptionCheck(env)) {
        throwInvocationTargetException(env, rvmExceptionOccurred(env));
        return NULL;
    }

    if (retDesc[0] != 'L' && retDesc[0] != '[') {
        // Return type is primitive. Box it.
        Class* retType = rvmFindClassByDescriptor(env, retDesc, NULL);
        return rvmBox(env, retType, jvalueRet);
    } else {
        return (Object*) jvalueRet->l;
    }
}

Class* Java_java_lang_reflect_Method_getDeclaringClass(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return method->clazz;
}

jint Java_java_lang_reflect_Method_getModifiers(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return method->access & METHOD_ACCESS_MASK;
}

Object* Java_java_lang_reflect_Method_getName(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return rvmNewStringUTF(env, method->name, -1);
}

Class* Java_java_lang_reflect_Method_getReturnType(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return rvmFindClassByDescriptor(env, rvmGetReturnType(method->desc), method->clazz->classLoader);
}

Object* Java_java_lang_reflect_Method_getSignatureAttribute(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    Class* java_lang_reflect_Proxy = rvmFindClassUsingLoader(env, "java/lang/reflect/Proxy", NULL);
    if (method->clazz->superclass == java_lang_reflect_Proxy) {
        return rvmAttributeGetMethodSignature(env, ((ProxyMethod*) method)->proxiedMethod);
    }
    return rvmAttributeGetMethodSignature(env, method);
}

ObjectArray* Java_java_lang_reflect_Method_getParameterTypes(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);

    jint argsCount = rvmGetParameterCount(method);

    Class* array_java_lang_Class = rvmFindClassUsingLoader(env, "[Ljava/lang/Class;", NULL);
    if (!array_java_lang_Class) return NULL;
    ObjectArray* paramTypes = rvmNewObjectArray(env, argsCount, NULL, array_java_lang_Class, NULL);
    if (!paramTypes) return NULL;

    const char* desc = method->desc;
    const char* s;
    jint i = 0;
    while ((s = rvmGetNextParameterType(&desc))) {
        char* paramTypeName = rvmAllocateMemoryAtomic(env, desc - s + 1);
        if (!paramTypeName) return NULL;
        strncpy(paramTypeName, s, desc - s);
        Class* paramType = rvmFindClassByDescriptor(env, paramTypeName, method->clazz->classLoader);
        if (!paramType) return NULL;
        paramTypes->values[i++] = (Object*) paramType;
    }

    return paramTypes;
}

ObjectArray* Java_java_lang_reflect_Method_getExceptionTypes(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    Class* java_lang_reflect_Proxy = rvmFindClassUsingLoader(env, "java/lang/reflect/Proxy", NULL);
    if (method->clazz->superclass == java_lang_reflect_Proxy) {
        return rvmAttributeGetExceptions(env, ((ProxyMethod*) method)->proxiedMethod);
    }
    return rvmAttributeGetExceptions(env, method);
}

Object* Java_java_lang_reflect_Method_getDefaultValue(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return rvmAttributeGetAnnotationDefault(env, method);
}

ObjectArray* Java_java_lang_reflect_Method_getDeclaredAnnotations(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return rvmAttributeGetMethodRuntimeVisibleAnnotations(env, method);
}

ObjectArray* Java_java_lang_reflect_Method_getParameterAnnotations(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) LONG_TO_PTR(methodPtr);
    return rvmAttributeGetMethodRuntimeVisibleParameterAnnotations(env, method);
}

