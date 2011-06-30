#include <nullvm.h>

Object* Java_java_lang_ClassCache_loadReflectionAccess(Env* env, Class* c) {
    Class* ao = nvmFindClassUsingLoader(env, "java/lang/reflect/AccessibleObject", NULL);
    if (!ao) return NULL;
    ClassField* f = nvmGetClassField(env, ao, "REFLECTION_ACCESS", "Lorg/nullvm/rt/ReflectionAccess;");
    if (!f) return NULL;
    return nvmGetObjectClassFieldValue(env, ao, f);
}

