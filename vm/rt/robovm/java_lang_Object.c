#include <robovm.h>

Class* Java_java_lang_Object_getClass(Env* env, Object* thiz) {
    return thiz->clazz;
}

Object* Java_java_lang_Object_internalClone(Env* env, Object* thiz) {
    return rvmCloneObject(env, thiz);
}

jint Java_java_lang_Object_hashCode(Env* env, Object* thiz) {
    return (jint) thiz;
}

void Java_java_lang_Object_notify(Env* env, Object* thiz) {
    rvmMonitorNotify(env, thiz);
}

void Java_java_lang_Object_notifyAll(Env* env, Object* thiz) {
    rvmMonitorNotifyAll(env, thiz);
}

void Java_java_lang_Object_wait(Env* env, Object* thiz, jlong millis, jint nanos) {
    rvmMonitorWait(env, thiz, millis, nanos);
}

