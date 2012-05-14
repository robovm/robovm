#include <nullvm.h>
#include "reflection_helpers.h"

Object* Java_java_lang_reflect_Constructor_internalNewInstance(Env* env, Class* clazz, jlong methodPtr, ObjectArray* parameterTypes, ObjectArray* args) {
    Method* method = (Method*) methodPtr;

    /*
     * The Java code has already checked that the constructor is accessible
     * to the the caller, that it can be instatiated and that the number 
     * of arguments are correct.
     */

    jvalue* jvalueArgs = validateAndUnwrapArgs(env, parameterTypes, args);
    if (!jvalueArgs) return NULL;

    Object* o = nvmNewObjectA(env, method->clazz, method, jvalueArgs);
    if (!o) {
        throwInvocationTargetException(env, nvmExceptionOccurred(env));
        return NULL;
    }
    return o;
}

