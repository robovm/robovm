#include <string.h>
#include <nullvm.h>

Object* j_ldc_string_asciiz(char* s) {
    Class* String = nvmGetClass("java/lang/String", "java_lang_String", NULL);
    Method* method = nvmGetMethod(String, "ldcAscii", "(Ljava/lang/Object;I)Ljava/lang/String;", NULL);
    Object* (*f)(char* cptr, jint length) = method->impl;
    // TODO: Get length as argument
    return f(s, strlen(s));
}

Object* j_ldc_string_utf8z(char* s) {
    return NULL;
}

//Object* j_get_string_utf16z(char* s) {
//    return NULL;
//}

