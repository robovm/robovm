#include <stdio.h>
#include <nullvm.h>

Object* Java_java_lang_String_ldcAscii(Env* env, Class* clazz, char* cptr, jint length) {
    CharArray* value = nvmNewCharArray(env, length);
    if (!value) return NULL;
    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (cptr[i] & 0xff);
    }
    Field* field = nvmGetInstanceField(env, clazz, "value", "[C");
    if (!field) return NULL;
    void (*setter)(Env*, Object*, Object*) = field->setter;
    Object* str = nvmAllocateObject(env, clazz);
    if (!str) return NULL;
    setter(env, str, (Object*) value);
    return str;
}

void Java_org_nullvm_compiler_Echo_print__Ljava_lang_String_2(Env* env, Class* clazz, Object* s) {
    Field* field = nvmGetInstanceField(env, s->clazz, "value", "[C");
    if (!field) return;
    Object* (*getter)(Env*, Object*) = field->getter;
    CharArray* value = (CharArray*) getter(env, s);
    jint i;
    for (i = 0; i < value->length; i++) {
        putchar((char) value->values[i]);
    }
}

void Java_org_nullvm_compiler_Echo_print__I(Env* env, Class* clazz, jint n) {
    printf("%d", n);
}

