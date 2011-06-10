#include <nullvm.h>
#include "utlist.h"

static Class* getEnclosingClass(Env* env, Class* thiz) {
    if (thiz->enclosingMethod) {
        return nvmFindClassUsingLoader(env, thiz->enclosingMethod->className, thiz->classLoader);
    }
    return NULL;
}

static Method* getEnclosingMethod(Env* env, Class* thiz) {
    if (!thiz->enclosingMethod || !thiz->enclosingMethod->methodName) return NULL;
    Class* clazz = getEnclosingClass(env, thiz);
    if (!clazz) return NULL;
    return nvmGetMethod(env, clazz, thiz->enclosingMethod->methodName, thiz->enclosingMethod->methodDesc);
}

jboolean Java_java_lang_Class_desiredAssertionStatus(Env* env, Object* thiz) {
    return JNI_FALSE;
}

jboolean Java_java_lang_Class_isPrimitive(Env* env, Class* thiz) {
    return thiz->primitive;
}

jboolean Java_java_lang_Class_isAnonymousClass(Env* env, Class* thiz) {
    InnerClass* innerClass;
    LL_FOREACH(thiz->innerClasses, innerClass) {
        if (innerClass->innerClass && !strcmp(innerClass->innerClass, thiz->name)) {
            return innerClass->innerName == NULL ? TRUE : FALSE;
        }
    }
    return FALSE;
}

jboolean Java_java_lang_Class_isInterface(Env* env, Class* thiz) {
    return CLASS_IS_INTERFACE(thiz) > 0;
}

jboolean Java_java_lang_Class_getModifiers(Env* env, Class* thiz, jboolean ignoreInnerClassesAttrib) {
    return thiz->access;
}

Object* Java_java_lang_Class_getSignatureAttribute(Env* env, Class* thiz) {
    return thiz->signature ? nvmNewStringUTF(env, thiz->signature, -1) : NULL;
}

Object* Java_java_lang_Class_getName0(Env* env, Class* thiz) {
    return nvmNewStringUTF(env, thiz->name, -1);
}

Class* Java_java_lang_Class_getDeclaringClass(Env* env, Class* thiz) {
    InnerClass* innerClass;
    LL_FOREACH(thiz->innerClasses, innerClass) {
        if (innerClass->innerClass && innerClass->outerClass && !strcmp(innerClass->innerClass, thiz->name)) {
            return nvmFindClassUsingLoader(env, innerClass->outerClass, thiz->classLoader);
        }
    }
    return NULL;
}

Class* Java_java_lang_Class_getEnclosingClass(Env* env, Class* thiz) {
    Class* enclosingClass = getEnclosingClass(env, thiz);
    if (nvmExceptionCheck(env) && nvmExceptionOccurred(env)->clazz != java_lang_ClassNotFoundException) {
        return NULL;
    }
    if (!enclosingClass) {
        nvmExceptionClear(env);
        return Java_java_lang_Class_getDeclaringClass(env, thiz);
    }
    return enclosingClass;
}

Object* Java_java_lang_Class_getEnclosingMethod(Env* env, Class* thiz) {
    Method* method = getEnclosingMethod(env, thiz);
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
    Method* method = getEnclosingMethod(env, thiz);
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

ObjectArray* Java_java_lang_Class_getStackClasses(Env* env, Class* c, jint maxDepth, jboolean stopAtPrivileged) {
    CallStackEntry* first = nvmGetCallStack(env);
    if (!first) return NULL;
    first = first->next; // Skip Class.getStackClasses()
    if (!first) return NULL;
    first = first->next; // Skip caller of Class.getStackClasses()
    if (!first) return NULL;
    jint depth = 0;
    CallStackEntry* entry = first;
    while (entry) {
        depth++;
        entry = entry->next;
    }
    if (maxDepth > -1 && maxDepth < depth) {
        depth = maxDepth;
    }
    
    ObjectArray* result = nvmNewObjectArray(env, depth, java_lang_Class, NULL, NULL);
    if (!result) return NULL;
    jint i;
    entry = first;
    for (i = 0; i < depth; i++) {
        result->values[i] = (Object*) entry->method->clazz;
        entry = entry->next;
    }
    return result;
}

Class* Java_java_lang_Class_classForName(Env* env, Class* c, Object* className, jboolean initializeBoolean, ClassLoader* classLoader) {
    char* classNameUTF = nvmGetStringUTFChars(env, className);
    if (!classNameUTF) return NULL;
    jint i;
    for (i = 0; classNameUTF[i] != '\0'; i++) {
        if (classNameUTF[i] == '.') classNameUTF[i] = '/';
    }
    Class* clazz = nvmFindClassInClasspathForLoader(env, classNameUTF, classLoader);
    if (!clazz) return NULL;
    if (initializeBoolean) {
        nvmInitialize(env, clazz);
        if (nvmExceptionOccurred(env)) {
            return NULL;
        }
    }
    return clazz;
}

ClassLoader* Java_java_lang_Class_getClassLoader(Env* env, Class* c, Class* clazz) {
    return clazz->classLoader;
}

ObjectArray* Java_java_lang_Class_getDeclaredConstructors0(Env* env, Class* clazz, jboolean publicOnly) {
    Method* method;
    jint length = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }
    Class* jlr_Constructor = nvmFindClass(env, "java/lang/reflect/Constructor");
    if (!jlr_Constructor) return NULL;
    ObjectArray* result = nvmNewObjectArray(env, length, jlr_Constructor, NULL, NULL);
    if (!result) return NULL;

    Method* constructor = nvmGetInstanceMethod(env, jlr_Constructor, "<init>", "(J)V");
    if (!constructor) return NULL;

    jint i = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                jvalue constructorArgs[1];
                constructorArgs[0].j = (jlong) method;
                Object* c = nvmNewObjectA(env, jlr_Constructor, constructor, constructorArgs);
                if (!c) return NULL;
                result->values[i++] = c;
            }
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredMethods0(Env* env, Class* clazz, jboolean publicOnly) {
    Method* method;
    jint length = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }
    Class* jlr_Method = nvmFindClass(env, "java/lang/reflect/Method");
    if (!jlr_Method) return NULL;
    ObjectArray* result = nvmNewObjectArray(env, length, jlr_Method, NULL, NULL);
    if (!result) return NULL;

    Method* constructor = nvmGetInstanceMethod(env, jlr_Method, "<init>", "(J)V");
    if (!constructor) return NULL;

    jint i = 0;
    for (method = clazz->methods->first; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                jvalue constructorArgs[1];
                constructorArgs[0].j = (jlong) method;
                Object* c = nvmNewObjectA(env, jlr_Method, constructor, constructorArgs);
                if (!c) return NULL;
                result->values[i++] = c;
            }
        }
    }

    return result;
}

