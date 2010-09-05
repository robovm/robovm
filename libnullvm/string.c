#include <nullvm.h>
#include <string.h>

Object* nvmNewStringAscii(Env* env, char* s) {
    jint length = strlen(s);
    Object* string = nvmAllocateObject(env, java_lang_String);
    if (!string) return NULL;
    CharArray* value = nvmNewCharArray(env, length);
    if (!value) return NULL;
    Method* constructor = nvmGetInstanceMethod(env, java_lang_String, "<init>", "(II[C)V");
    if (!constructor) return NULL;

    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (s[i] & 0xff);
    }

    nvmCallVoidInstanceMethod(env, string, constructor, 0, length, value);
    if (nvmExceptionOccurred(env)) return NULL;

//    // TODO: Call through catch all trampoline
//    void (*f)(void*, Env*, Object*, jint, jint, CharArray*) = constructor->impl;
//    f(NULL, env, string, 0, length, value);

    return string;
/*
    Method* method = nvmGetClassMethod(env, java_lang_String, "ldcAscii", "(Ljava/lang/Object;I)Ljava/lang/String;");
    Method* method = nvmGetClassMethod(env, java_lang_String, "ldcAscii", "(Ljava/lang/Object;I)Ljava/lang/String;");
    if (!method) return NULL;
    Object* (*f)(void*, Env*, char*, jint) = method->impl;
    // TODO: Get length as argument
    return f(NULL, env, s, strlen(s));*/
}

