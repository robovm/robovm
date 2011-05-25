#include <nullvm.h>

Class* Java_java_lang_ClassLoader_nativeFindLoadedClass(Env* env, Class* cls, ClassLoader* loader, Object* className) {
    char* classNameUTF = nvmGetStringUTFChars(env, className);
    if (!classNameUTF) return NULL;
    jint i;
    for (i = 0; classNameUTF[i] != '\0'; i++) {
        if (classNameUTF[i] == '.') classNameUTF[i] = '/';
    }
    return nvmFindLoadedClass(env, classNameUTF, loader);
}

