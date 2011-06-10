#include <nullvm.h>
#include <string.h>
#include "utlist.h"

Class* Java_java_lang_reflect_Method_getDeclaringClass(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;
    return method->clazz;
}

jint Java_java_lang_reflect_Method_getModifiers(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;
    return method->access;
}

Object* Java_java_lang_reflect_Method_getName(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;
    return nvmNewStringUTF(env, method->name, -1);
}

Class* Java_java_lang_reflect_Method_getReturnType(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;
    return nvmFindClassByDescriptor(env, nvmGetReturnType(method->desc), method->clazz->classLoader);
}

ObjectArray* Java_java_lang_reflect_Method_getParameterTypes(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;

    char* s;
    char* desc = method->desc;
    jint argsCount = 0;
    while (s = nvmGetNextArgumentType(&desc)) {
        argsCount++;
    }

    Class* array_java_lang_Class = nvmFindClass(env, "[Ljava/lang/Class;");
    if (!array_java_lang_Class) return NULL;
    ObjectArray* paramTypes = nvmNewObjectArray(env, argsCount, NULL, array_java_lang_Class, NULL);
    if (!paramTypes) return NULL;

    desc = method->desc;
    jint i = 0;
    while (s = nvmGetNextArgumentType(&desc)) {
        char* paramTypeName = nvmAllocateMemory(env, desc - s + 1);
        if (!paramTypeName) return NULL;
        strncpy(paramTypeName, s, desc - s);
        Class* paramType = nvmFindClassByDescriptor(env, paramTypeName, method->clazz->classLoader);
        if (!paramType) return NULL;
        paramTypes->values[i++] = (Object*) paramType;
    }

    return paramTypes;
}

ObjectArray* Java_java_lang_reflect_Method_getExceptionTypes(Env* env, Class* clazz, jlong methodPtr) {
    Method* method = (Method*) methodPtr;

    jint i = 0;
    jint length = 0;
    Exception* ex;
    LL_FOREACH(method->exceptions, ex) length++;
    Class* array_java_lang_Class = nvmFindClass(env, "[Ljava/lang/Class;");
    if (!array_java_lang_Class) return NULL;
    ObjectArray* exceptionTypes = nvmNewObjectArray(env, length, NULL, array_java_lang_Class, NULL);
    if (!exceptionTypes) return NULL;
    LL_FOREACH(method->exceptions, ex) {
        Class* exType = nvmFindClassUsingLoader(env, ex->name, method->clazz->classLoader);
        if (!exType ) return NULL;
        exceptionTypes->values[i++] = (Object*) exType;
    }
    return exceptionTypes;
}

