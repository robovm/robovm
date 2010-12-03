#include <nullvm.h>
#include "reflection_helpers.h"

Object* Java_java_lang_reflect_Constructor_internalNewInstance(JNIEnv* _env, Class* clazz, long methodPtr, ObjectArray* parameterTypes, ObjectArray* args) {
    Env* env = (Env*) _env;
    Method* method = (Method*) methodPtr;

    /*
     * The Java code has already checked that the constructor is accessible
     * to the the caller, that it can be instatiated and that the number 
     * of arguments are correct.
     */

    jint length = args->length;
    jvalue* jvalueArgs = validateAndUnwrapArgs(env, parameterTypes, args);
    if (!jvalueArgs) return NULL;

    Object* o = nvmNewObjectA(env, method->clazz, method, jvalueArgs);
    if (!o) {
        // TODO: Cache InvocationTargetException in libnullvm?
        Class* java_lang_ITE = nvmFindClass(env, "java/lang/InvocationTargetException");
        if (!java_lang_ITE) return NULL;
        Method* java_lang_ITE_constructor = nvmGetMethod(env, java_lang_ITE, "<init>", "(Ljava/lang/Throwable;)V");
        if (!java_lang_ITE_constructor) return NULL;
        Object* exception = nvmNewObject(env, java_lang_ITE, java_lang_ITE_constructor, nvmExceptionOccurred(env));
        if (!exception) return NULL;
        nvmThrow(env, exception);
        return NULL;
    }
    return o;
}

