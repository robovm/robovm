#include <stdio.h>
#include <nullvm.h>

Object* Java_java_lang_String_ldcAscii(void* env, Class* clazz, char* cptr, jint length) {
    CharArray* value = (CharArray*) nvmNewArray(5, length);
    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (cptr[i] & 0xff);
    }
    Field* field = nvmGetInstanceField(clazz, "value", "[C", clazz);
    void (*setter)(Object*, Object*) = field->setter;
    Object* str = nvmNewInstance(clazz);
    setter(str, (Object*) value);
    return str;
}

void Java_org_nullvm_compiler_Echo_print__Ljava_lang_String_2(void* env, Class* clazz, Object* s) {
    Field* field = nvmGetInstanceField(s->clazz, "value", "[C", s->clazz);
    Object* (*getter)(Object*) = field->getter;
    CharArray* value = (CharArray*) getter(s);
    jint i;
    for (i = 0; i < value->length; i++) {
        putchar((char) value->values[i]);
    }
}

void Java_org_nullvm_compiler_Echo_print__I(void* env, Class* clazz, jint n) {
    printf("%d", n);
}

