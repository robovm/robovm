#include <robovm.h>
#include "reflection_helpers.h"

static Class* java_lang_reflect_InvocationHandler = NULL;
static Method* java_lang_reflect_InvocationHandler_invoke = NULL;
static InstanceField* java_lang_reflect_Proxy_h = NULL;
static Class* java_lang_reflect_UndeclaredThrowableException = NULL;
static Method* java_lang_reflect_UndeclaredThrowableException_init = NULL;

static void handler(Env* env, Object* receiver, ProxyMethod* method, jvalue* args, jvalue* returnValue) {
    Class* proxyClass = receiver->clazz;
    Class* java_lang_reflect_Proxy = proxyClass->superclass;
    if (!java_lang_reflect_InvocationHandler) {
        java_lang_reflect_InvocationHandler = nvmFindClassUsingLoader(env, "java/lang/reflect/InvocationHandler", NULL);
        if (!java_lang_reflect_InvocationHandler) return;
    }
    if (!java_lang_reflect_InvocationHandler_invoke) {
        java_lang_reflect_InvocationHandler_invoke = nvmGetInstanceMethod(env, java_lang_reflect_InvocationHandler, 
                "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;");
        if (!java_lang_reflect_InvocationHandler_invoke) return;
    }
    if (!java_lang_reflect_Proxy_h) {
        java_lang_reflect_Proxy_h = nvmGetInstanceField(env, java_lang_reflect_Proxy, "h", "Ljava/lang/reflect/InvocationHandler;");
        if (!java_lang_reflect_Proxy_h) return;
    }
    Object* h = nvmGetObjectInstanceFieldValue(env, receiver, java_lang_reflect_Proxy_h);
    if (nvmExceptionCheck(env)) return;
    if (!h) {
        nvmThrowNullPointerException(env);
        return;
    }

    // TODO: Reuse java.lang.reflect.Method objects?    
    Object* methodObject = createMethodObject(env, method->proxiedMethod);
    if (!methodObject) return;

    const char* desc = method->method.desc;
    const char* c;
    jint i = 0;
    while ((c = nvmGetNextParameterType(&desc))) {
        if (c[0] != 'L' && c[0] != '[') {
            // Primitive. Needs wrapping.
            char typeName[2] = {c[0], 0};
            Class* type = nvmFindClassByDescriptor(env, typeName, NULL);
            if (!type) return;
            args[i].l = (jobject) nvmWrapPrimitive(env, type, &args[i]);
        }
        i++;
    }

    jint length = i;
    ObjectArray* argsArray = NULL;
    if (length > 0) {
        argsArray = nvmNewObjectArray(env, length, java_lang_Object, NULL, NULL);
        if (!argsArray) return;
        for (i = 0; i < length; i++) {
            argsArray->values[i] = (Object*) args[i].l;
        }
    }

    jvalue invokeArgs[3];
    invokeArgs[0].l = (jobject) receiver;
    invokeArgs[1].l = (jobject) methodObject;
    invokeArgs[2].l = (jobject) argsArray;
    Object* result = nvmCallObjectInstanceMethodA(env, h, java_lang_reflect_InvocationHandler_invoke, invokeArgs);
    if (nvmExceptionCheck(env)) {
        Object* throwable = nvmExceptionClear(env);
        if (nvmIsSubClass(java_lang_RuntimeException, throwable->clazz) || nvmIsSubClass(java_lang_Error, throwable->clazz)) {
            // Instances of java.lang.RuntimeException and java.lang.Error can always be thrown
            return;
        }
        nvmExceptionClear(env);
        ObjectArray* exceptionTypes = nvmAttributeGetExceptions(env, method->proxiedMethod);
        if (!exceptionTypes) return;
        for (i = 0; i < exceptionTypes->length; i++) {
            Class* exceptionType = (Class*) exceptionTypes->values[i];
            if (nvmIsSubClass(exceptionType, throwable->clazz)) {
                nvmThrow(env, throwable);
                return;
            }
        }

        if (!java_lang_reflect_UndeclaredThrowableException) {
            java_lang_reflect_UndeclaredThrowableException = nvmFindClassUsingLoader(env, 
                "java/lang/reflect/UndeclaredThrowableException", NULL);
            if (!java_lang_reflect_UndeclaredThrowableException) return;
        }
        if (!java_lang_reflect_UndeclaredThrowableException_init) {
            java_lang_reflect_UndeclaredThrowableException_init = nvmGetInstanceMethod(env,
                java_lang_reflect_UndeclaredThrowableException, 
                "<init>", "(Ljava/lang/Throwable;)V");
            if (!java_lang_reflect_UndeclaredThrowableException_init) return;
        }
        throwable = nvmNewObject(env, java_lang_reflect_UndeclaredThrowableException, java_lang_reflect_UndeclaredThrowableException_init, throwable);
        if (!throwable) return;
        nvmThrow(env, throwable);
        return;
    }

    const char* returnTypeDesc = nvmGetReturnType(method->method.desc);
    if (returnTypeDesc[0] == 'V') {
        // void method. Just return.
        return;
    }

    if (returnTypeDesc[0] == 'L' || returnTypeDesc[0] == '[') {
        if (!result) {
            returnValue->l = NULL;
            return;
        }
        Class* type = nvmFindClassByDescriptor(env, returnTypeDesc, proxyClass->classLoader);
        if (nvmIsInstanceOf(env, result, type)) {
            returnValue->l = (jobject) result;
            return;
        }
        nvmThrowClassCastException(env, type, result->clazz);
        return;
    }

    // Must be primitive. Cannot be NULL.
    if (!result) {
        nvmThrowNullPointerException(env);
        return;
    }

    // Unwrap primitive.
    Class* type = nvmFindClassByDescriptor(env, returnTypeDesc, NULL);
    switch (type->name[0]) {
    case 'Z':
        if (result->clazz == java_lang_Boolean) {
            returnValue->z = ((Boolean*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Boolean, result->clazz);
        break;
    case 'B':
        if (result->clazz == java_lang_Byte) {
            returnValue->b = ((Byte*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Byte, result->clazz);
        break;
    case 'S':
        if (result->clazz == java_lang_Short) {
            returnValue->s = ((Short*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Short, result->clazz);
        break;
    case 'C':
        if (result->clazz == java_lang_Character) {
            returnValue->c = ((Character*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Character, result->clazz);
        break;
    case 'I':
        if (result->clazz == java_lang_Integer) {
            returnValue->i = ((Integer*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Integer, result->clazz);
        break;
    case 'J':
        if (result->clazz == java_lang_Long) {
            returnValue->j = ((Long*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Long, result->clazz);
        break;
    case 'F':
        if (result->clazz == java_lang_Float) {
            returnValue->f = ((Float*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Float, result->clazz);
        break;
    case 'D':
        if (result->clazz == java_lang_Double) {
            returnValue->d = ((Double*) result)->value;
            return;
        }
        nvmThrowClassCastException(env, java_lang_Double, result->clazz);
        break;
    }
}

struct ProxyInstance {
    Object object;
    Object* h;
    void* data[0];
};

Class* Java_java_lang_reflect_Proxy_generateProxy(Env* env, Class* java_lang_reflect_Proxy, 
      Object* name, ObjectArray* interfaces, ClassLoader* loader) {

    char* cname = nvmGetStringUTFChars(env, name);
    if (!cname) return NULL;

    return nvmProxyCreateProxyClass(env, java_lang_reflect_Proxy, loader, cname, interfaces->length, (Class**) interfaces->values, 
                sizeof(struct ProxyInstance), offsetof(struct ProxyInstance, data), handler);
}

