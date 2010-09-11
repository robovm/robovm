#include <stdio.h>
#include <nullvm.h>

void Java_org_nullvm_compiler_Echo_print__Ljava_lang_String_2(JNIEnv* jniEnv, Class* clazz, Object* s) {
    Env* env = *(Env**) jniEnv;
    InstanceField* field = nvmGetInstanceField(env, s->clazz, "value", "[C");
    if (!field) return;
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, s, field);
    jint i;
    for (i = 0; i < value->length; i++) {
        putchar((char) value->values[i]);
    }
}

void Java_org_nullvm_compiler_Echo_print__I(JNIEnv* jniEnv, Class* clazz, jint n) {
    printf("%d", n);
}

