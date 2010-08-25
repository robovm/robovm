#include <nullvm.h>
#include <string.h>

Object* nvmNewStringAscii(Env* env, char* s) {
    Method* method = nvmGetClassMethod(env, java_lang_String, "ldcAscii", "(Ljava/lang/Object;I)Ljava/lang/String;");
    if (!method) return NULL;
    Object* (*f)(Env*, char*, jint) = method->impl;
    // TODO: Get length as argument
    return f(env, s, strlen(s));
}

