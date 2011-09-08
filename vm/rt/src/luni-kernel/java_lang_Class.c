#include <nullvm.h>
#include "reflection_helpers.h"
#include "utlist.h"

jboolean Java_java_lang_Class_desiredAssertionStatus(Env* env, Class* thiz) {
    return JNI_FALSE;
}

jboolean Java_java_lang_Class_isPrimitive(Env* env, Class* thiz) {
    return thiz->primitive;
}

jboolean Java_java_lang_Class_isAnonymousClass(Env* env, Class* thiz) {
    return nvmAttributeIsAnonymousClass(env, thiz);
}

jboolean Java_java_lang_Class_isArray(Env* env, Class* thiz) {
    return CLASS_IS_ARRAY(thiz);
}

jboolean Java_java_lang_Class_isInterface(Env* env, Class* thiz) {
    return CLASS_IS_INTERFACE(thiz) > 0;
}

jboolean Java_java_lang_Class_isAssignableFrom(Env* env, Class* thiz, Class* that) {
    if (!that) {
        nvmThrowNullPointerException(env);
        return FALSE;
    }
    return nvmIsAssignableFrom(env, that, thiz);
}

jboolean Java_java_lang_Class_isInstance(Env* env, Class* thiz, Object* object) {
    return nvmIsInstanceOf(env, object, thiz);
}

jint Java_java_lang_Class_getModifiers(Env* env, Class* c, Class* thiz, jboolean ignoreInnerClassesAttrib) {
    return thiz->access;
}

Object* Java_java_lang_Class_getSignatureAttribute(Env* env, Class* thiz) {
    return nvmAttributeGetClassSignature(env, thiz);
}

Object* Java_java_lang_Class_getName0(Env* env, Class* thiz) {
    return nvmNewStringUTF(env, thiz->name, -1);
}

Class* Java_java_lang_Class_getDeclaringClass(Env* env, Class* thiz) {
    return nvmAttributeGetDeclaringClass(env, thiz);
}

Class* Java_java_lang_Class_getEnclosingClass(Env* env, Class* thiz) {
    Class* enclosingClass = nvmAttributeGetEnclosingClass(env, thiz);
    if (nvmExceptionCheck(env) && nvmExceptionOccurred(env)->clazz != java_lang_ClassNotFoundException) {
        return NULL;
    }
    if (!enclosingClass) {
        nvmExceptionClear(env);
        return nvmAttributeGetDeclaringClass(env, thiz);
    }
    return enclosingClass;
}

Object* Java_java_lang_Class_getEnclosingMethod(Env* env, Class* thiz) {
    Method* method = nvmAttributeGetEnclosingMethod(env, thiz);
    if (!method || METHOD_IS_CONSTRUCTOR(method)) return NULL;
    Class* jlr_Method = nvmFindClass(env, "java/lang/reflect/Method");
    if (!jlr_Method) return NULL;
    Method* constructor = nvmGetInstanceMethod(env, jlr_Method, "<init>", "(J)V");
    if (!constructor) return NULL;
    jvalue args[1];
    args[0].j = (jlong) method;
    return nvmNewObjectA(env, jlr_Method, constructor, args);
}

Object* Java_java_lang_Class_getEnclosingConstructor(Env* env, Class* thiz) {
    Method* method = nvmAttributeGetEnclosingMethod(env, thiz);
    if (!method || !METHOD_IS_CONSTRUCTOR(method)) return NULL;
    Class* jlr_Constructor = nvmFindClass(env, "java/lang/reflect/Constructor");
    if (!jlr_Constructor) return NULL;
    Method* constructor = nvmGetInstanceMethod(env, jlr_Constructor, "<init>", "(J)V");
    if (!constructor) return NULL;
    jvalue args[1];
    args[0].j = (jlong) method;
    return nvmNewObjectA(env, jlr_Constructor, constructor, args);
}

ObjectArray* Java_java_lang_Class_getInterfaces(Env* env, Class* thiz) {
    Interface* interface;
    jint length = 0;
    LL_FOREACH(thiz->interfaces, interface) {
        length++;
    }
    ObjectArray* result = nvmNewObjectArray(env, length, java_lang_Class, NULL, NULL);
    if (!result) return NULL;
    jint i = 0;
    LL_FOREACH(thiz->interfaces, interface) {
        result->values[i++] = (Object*) interface->interface;
    }
    return result;
}

Class* Java_java_lang_Class_getComponentType(Env* env, Class* thiz) {
    if (!CLASS_IS_ARRAY(thiz)) {
        return NULL;
    }
    return nvmGetComponentType(env, thiz);
}

Class* Java_java_lang_Class_getSuperclass(Env* env, Class* thiz) {
    return thiz->superclass;
}

ClassLoader* Java_java_lang_Class_getClassLoader(Env* env, Class* c, Class* clazz) {
    return clazz->classLoader;
}

void Java_java_lang_Class_initializeClass0(Env* env, Class* c, Class* clazz) {
    nvmInitialize(env, clazz);
}

ObjectArray* Java_java_lang_Class_getDeclaredConstructors0(Env* env, Class* clazz, jboolean publicOnly) {
    if (clazz->primitive || CLASS_IS_ARRAY(clazz)) return NULL;

    Method* method;
    jint length = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                Object* c = createConstructorObject(env, method);
                if (!c) return NULL;
                if (!result) {
                    result = nvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                    if (!result) return NULL;
                }
                result->values[i++] = c;
            }
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredMethods0(Env* env, Class* clazz, jboolean publicOnly) {
    if (clazz->primitive || CLASS_IS_ARRAY(clazz)) return NULL;

    Method* method;
    jint length = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                Object* c = createMethodObject(env, method);
                if (!c) return NULL;
                if (!result) {
                    result = nvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                    if (!result) return NULL;
                }
                result->values[i++] = c;
            }
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredFields0(Env* env, Class* clazz, jboolean publicOnly) {
    if (clazz->primitive || CLASS_IS_ARRAY(clazz)) return NULL;

    Field* field;
    jint length = 0;
    for (field = clazz->fields; field != NULL; field = field->next) {
        if (!publicOnly || FIELD_IS_PUBLIC(field)) {
            length++;
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (field = clazz->fields; field != NULL; field = field->next) {
        if (!publicOnly || FIELD_IS_PUBLIC(field)) {
            Object* c = createFieldObject(env, field);
            if (!c) return NULL;
            if (!result) {
                result = nvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                if (!result) return NULL;
            }
            result->values[i++] = c;
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredAnnotations(Env* env, Class* clazz) {
    return nvmAttributeGetClassRuntimeVisibleAnnotations(env, clazz);
}

Object* Java_java_lang_Class_newInstanceImpl(Env* env, Class* clazz) {
    if (CLASS_IS_PRIMITIVE(clazz) || CLASS_IS_INTERFACE(clazz) || CLASS_IS_ARRAY(clazz) || CLASS_IS_ABSTRACT(clazz)) {
        nvmThrowNew(env, java_lang_InstantiationException, clazz->name);
        return NULL;
    }
    Method* constructor = nvmGetInstanceMethod(env, clazz, "<init>", "()V");
    if (!constructor) {
        nvmThrowNew(env, java_lang_InstantiationException, clazz->name);
        return NULL;
    }

    // TODO: Access checks

    jvalue args[1];
    Object* o = nvmNewObjectA(env, clazz, constructor, args);
    if (!o) {
        throwInvocationTargetException(env, nvmExceptionOccurred(env));
        return NULL;
    }
    return o;
}

