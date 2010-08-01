#include <string.h>
#include <nullvm.h>

extern jobject* jm_java_lang_String_ldcAscii__Ljava_lang_Object_2I__Ljava_lang_String_2(jclass* clazz, char* cptr, jint length);

jobject* j_ldc_string_asciiz(char* s) {
    // TODO: Get length as argument
    return jm_java_lang_String_ldcAscii__Ljava_lang_Object_2I__Ljava_lang_String_2(nvmGetClass("java/lang/String", "java_lang_String", NULL), s, strlen(s));
}

jobject* j_ldc_string_utf8z(char* s) {
    return NULL;
}

//jobject* j_get_string_utf16z(char* s) {
//    return NULL;
//}

