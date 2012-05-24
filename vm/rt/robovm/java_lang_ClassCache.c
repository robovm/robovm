#include <robovm.h>

Object* Java_java_lang_ClassCache_loadReflectionAccess(Env* env, Class* c) {
    Class* ao = rvmFindClassUsingLoader(env, "java/lang/reflect/AccessibleObject", NULL);
    if (!ao) return NULL;
    ClassField* f = rvmGetClassField(env, ao, "REFLECTION_ACCESS", "Lorg/robovm/rt/ReflectionAccess;");
    if (!f) return NULL;
    return rvmGetObjectClassFieldValue(env, ao, f);
}

