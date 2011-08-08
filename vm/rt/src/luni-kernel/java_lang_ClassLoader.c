#include <nullvm.h>

static char* toBinaryName(Env* env, Object* className) {
    char* classNameUTF = nvmGetStringUTFChars(env, className);
    if (!classNameUTF) return NULL;
    jint i;
    for (i = 0; classNameUTF[i] != '\0'; i++) {
        if (classNameUTF[i] == '.') classNameUTF[i] = '/';
    }
    return classNameUTF;
}

Class* Java_java_lang_ClassLoader_nativeFindLoadedClass(Env* env, Class* cls, Object* className, ClassLoader* loader) {
    char* classNameUTF = toBinaryName(env, className);
    if (!classNameUTF) return NULL;
    return nvmFindLoadedClass(env, classNameUTF, loader);
}

Class* Java_java_lang_ClassLoader_nativeFindClassInClasspathForLoader(Env* env, Class* cls, Object* className, ClassLoader* loader) {
    char* classNameUTF = toBinaryName(env, className);
    if (!classNameUTF) return NULL;
    Class* clazz = nvmFindClassInClasspathForLoader(env, classNameUTF, loader);
    if (!clazz) return NULL;
    return clazz;
}

Class* Java_java_lang_ClassLoader_nativeFindClassUsingLoader(Env* env, Class* cls, Object* className, ClassLoader* loader) {
    char* classNameUTF = toBinaryName(env, className);
    if (!classNameUTF) return NULL;
    Class* clazz = nvmFindClassUsingLoader(env, classNameUTF, loader);
    if (!clazz) return NULL;
    return clazz;
}

