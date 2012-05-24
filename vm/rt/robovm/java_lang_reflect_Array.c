#include <robovm.h>

Object* Java_java_lang_reflect_Array_createObjectArray(Env* env, Class* cls, Class* componentType, jint length) {
    return (Object*) rvmNewObjectArray(env, length, componentType, NULL, NULL);
}

