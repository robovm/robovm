#include <nullvm.h>

Class* Java_java_lang_Object_getClass(JNIEnv* _env, Object* thiz) {
    return thiz->clazz;
}

Object* Java_java_lang_Object_nativeClone(JNIEnv* _env, Object* thiz) {
    Env* env = (Env*) _env;
    return nvmCloneObject(env, thiz);
}

jint Java_java_lang_Object_hashCode(JNIEnv* _env, Object* thiz) {
    return (jint) thiz;
}

