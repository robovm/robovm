#include <nullvm.h>

Class* Java_java_lang_reflect_Proxy_createProxyClass(Env* env, Class* java_lang_reflect_Proxy, 
      ClassLoader* classLoader, Object* className, ObjectArray* interfaces) {

    char* name = nvmGetStringUTFChars(env, className);
    if (!name) return NULL;

    Class* proxyClass = nvmAllocateClass(env, name, java_lang_reflect_Proxy, classLoader, ACC_PUBLIC | ACC_FINAL, 0, sizeof(Object*));
    if (!proxyClass) return NULL;

    jint i;
    for (i = 0; i < interfaces->length; i++) {
        if (!nvmAddInterface(env, proxyClass, (Class*) interfaces->values[i])) return NULL;
    }

    if (!nvmRegisterClass(env, proxyClass)) return NULL;

    return proxyClass;
}

