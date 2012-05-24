#include <robovm.h>

Object* Java_java_lang_String_intern(Env* env, Object* thiz) {
    return rvmInternString(env, thiz);
}

