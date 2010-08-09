#include <stdio.h>
#include <nullvm.h>

jobject* Java_java_lang_String_ldcAscii(void* env, jclass* clazz, char* cptr, jint length) {
    jchar_array* value = (jchar_array*) nvmNewArray(5, length);
    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (cptr[i] & 0xff);
    }
    jfield* field = nvmGetInstanceField(clazz, "value", "[C", clazz);
    void (*setter)(jobject*, jobject*) = field->setter;
    jobject* str = nvmNewInstance(clazz);
    setter(str, (jobject*) value);
    return str;
}

void Java_org_nullvm_compiler_Echo_print__Ljava_lang_String_2(void* env, jclass* clazz, jobject* s) {
    jfield* field = nvmGetInstanceField(s->clazz, "value", "[C", s->clazz);
    jobject* (*getter)(jobject*) = field->getter;
    jchar_array* value = (jchar_array*) getter(s);
    jint i;
    for (i = 0; i < value->length; i++) {
        putchar((char) value->values[i]);
    }
}

void Java_org_nullvm_compiler_Echo_print__I(void* env, jclass* clazz, jint n) {
    printf("%d", n);
}

