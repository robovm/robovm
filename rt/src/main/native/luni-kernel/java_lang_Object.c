#include <nullvm.h>

Class* Java_java_lang_Object_getClass(Env* env, Object* thiz) {
    return thiz->clazz;
}

Object* Java_java_lang_Object_nativeClone(Env* env, Object* thiz) {
    return nvmCloneObject(env, thiz);
}

jint Java_java_lang_Object_hashCode(Env* env, Object* thiz) {
    return (jint) thiz;
}

void Java_java_lang_Object_notify(Env* env, Object* thiz) {
    nvmMonitorNotify(env, thiz);
}

void Java_java_lang_Object_notifyAll(Env* env, Object* thiz) {
    nvmMonitorNotifyAll(env, thiz);
}

void Java_java_lang_Object_wait(Env* env, Object* thiz, jlong millis, jint nanos) {
    nvmMonitorWait(env, thiz, millis, nanos);
}

