#include <nullvm.h>

jboolean Java_java_lang_Class_desiredAssertionStatus(JNIEnv* env, Object* thiz) {
    return JNI_FALSE;
}

jboolean Java_java_lang_Class_isPrimitive(JNIEnv* env, Class* thiz) {
    return thiz->primitive;
}

jboolean Java_java_lang_Class_getModifiers(JNIEnv* env, Class* thiz) {
    return thiz->access;
}

Class* Java_java_lang_Class_getComponentType(JNIEnv* _env, Class* thiz) {
    Env* env = (Env*) _env;
    if (!CLASS_IS_ARRAY(thiz)) {
        return NULL;
    }
    return nvmGetComponentType(env, thiz);
}

ObjectArray* Java_java_lang_Class_getStackClasses(JNIEnv* _env, Class* c, jint maxDepth, jboolean stopAtPrivileged) {
    Env* env = (Env*) _env;
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

Class* Java_java_lang_Class_forName(JNIEnv* _env, Class* c, Object* className, jboolean initializeBoolean, Object* classLoader) {
    // TODO: Implement Class.forName().
    // TODO: If classLoader == null search bootclasspath otherwise search bootclasspath and classpath
    Env* env = (Env*) _env;
    char* classNameUTF = nvmGetStringUTFChars(env, className);
    jint i;
    for (i = 0; classNameUTF[i] != '\0'; i++) {
        if (classNameUTF[i] == '.') classNameUTF[i] = '/';
    }
    if (!classNameUTF) return NULL;
    Class* clazz = nvmFindClass(env, classNameUTF);
    if (!clazz) return NULL;
    if (initializeBoolean) {
        nvmInitialize(env, clazz);
        if (nvmExceptionOccurred(env)) {
            return NULL;
        }
    }
    return clazz;
}

Object* Java_java_lang_Class_getClassLoader(JNIEnv* _env, Class* thiz) {
    // TODO: Implement Class.getClassLoader().
    return NULL;
}

ObjectArray* Java_java_lang_Class_getDeclaredConstructors0(JNIEnv* _env, Class* clazz, jboolean publicOnly) {
    Env* env = (Env*) _env;
    Method* method;
    jint length = 0;
    for (method = clazz->methods; method != NULL; method = method->next) {
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
    for (method = clazz->methods; method != NULL; method = method->next) {
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

ObjectArray* Java_java_lang_Class_getDeclaredMethods0(JNIEnv* _env, Class* clazz, jboolean publicOnly) {
    Env* env = (Env*) _env;
    Method* method;
    jint length = 0;
    for (method = clazz->methods; method != NULL; method = method->next) {
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
    for (method = clazz->methods; method != NULL; method = method->next) {
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

