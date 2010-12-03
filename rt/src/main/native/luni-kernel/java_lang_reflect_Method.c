#include <nullvm.h>
#include <string.h>

Class* Java_java_lang_reflect_Method_getDeclaringClass(JNIEnv* _env, Class* clazz, jlong methodPtr) {
    Env* env = (Env*) _env;
    Method* method = (Method*) methodPtr;
    return method->clazz;
}

jint Java_java_lang_reflect_Method_getModifiers(JNIEnv* _env, Class* clazz, jlong methodPtr) {
    Env* env = (Env*) _env;
    Method* method = (Method*) methodPtr;
    return method->access;
}

Object* Java_java_lang_reflect_Method_getName(JNIEnv* _env, Class* clazz, jlong methodPtr) {
    Env* env = (Env*) _env;
    Method* method = (Method*) methodPtr;
    return nvmNewStringUTF(env, method->name, -1);
}

ObjectArray* Java_java_lang_reflect_Method_getParameterTypes(JNIEnv* _env, Class* clazz, jlong methodPtr) {
    Env* env = (Env*) _env;
    Method* method = (Method*) methodPtr;

    char* s;
    char* desc = method->desc;
    jint argsCount = 0;
    while (s = nvmGetNextArgumentType(&desc)) {
        argsCount++;
    }

    Class* array_java_lang_Class = nvmFindClass(env, "[Ljava/lang/Class;");
    ObjectArray* paramTypes = nvmNewObjectArray(env, argsCount, NULL, array_java_lang_Class, NULL);
    if (!paramTypes) return NULL;

    desc = method->desc;
    jint i = 0;
    while (s = nvmGetNextArgumentType(&desc)) {
        char* paramTypeName = nvmAllocateMemory(env, desc - s + 1);
        strncpy(paramTypeName, s, desc - s);
        Class* paramType = nvmFindClassByDescriptor(env, paramTypeName);
        if (!paramType) return NULL;
        paramTypes->values[i++] = (Object*) paramType;
    }

    return paramTypes;
}

