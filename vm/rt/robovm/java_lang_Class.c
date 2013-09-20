/*
 * Copyright (C) 2012 Trillian AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include <string.h>
#include <robovm.h>
#include "reflection_helpers.h"
#include "utlist.h"

#define LOG_TAG "java.lang.Class"

// Defined in java_lang_VMClassLoader.c
extern char* toBinaryName(Env* env, Object* className);

jboolean Java_java_lang_Class_desiredAssertionStatus(Env* env, Class* thiz) {
    return FALSE;
}

jboolean Java_java_lang_Class_isPrimitive(Env* env, Class* thiz) {
    return CLASS_IS_PRIMITIVE(thiz) ? TRUE : FALSE;
}

jboolean Java_java_lang_Class_isAnonymousClass(Env* env, Class* thiz) {
    return rvmAttributeIsAnonymousClass(env, thiz);
}

jboolean Java_java_lang_Class_isInterface(Env* env, Class* thiz) {
    return CLASS_IS_INTERFACE(thiz) ? TRUE : FALSE;
}

jboolean Java_java_lang_Class_isAssignableFrom(Env* env, Class* thiz, Class* that) {
    if (!that) {
        rvmThrowNullPointerException(env);
        return FALSE;
    }
    return rvmIsAssignableFrom(env, that, thiz);
}

jboolean Java_java_lang_Class_isInstance(Env* env, Class* thiz, Object* object) {
    return rvmIsInstanceOf(env, object, thiz);
}

jint Java_java_lang_Class_getModifiers(Env* env, Class* c, Class* thiz, jboolean ignoreInnerClassesAttrib) {
    jint modifiers = thiz->flags & CLASS_ACCESS_MASK;
    if (!ignoreInnerClassesAttrib) {
        jint innerModifiers = 0;
        if (rvmAttributeGetInnerClass(env, thiz, NULL, &innerModifiers)) {
            modifiers = innerModifiers & CLASS_ACCESS_MASK;
        }
    }
    return modifiers;
}

Object* Java_java_lang_Class_getSignatureAttribute(Env* env, Class* thiz) {
    return rvmAttributeGetClassSignature(env, thiz);
}

Object* Java_java_lang_Class_getInnerClassName(Env* env, Class* thiz) {
    Object* innerClassName = NULL;
    rvmAttributeGetInnerClass(env, thiz, &innerClassName, NULL);
    return innerClassName;
}

Object* Java_java_lang_Class_getName0(Env* env, Class* thiz) {
    return rvmNewStringUTF(env, thiz->name, -1);
}

Class* Java_java_lang_Class_getDeclaringClass(Env* env, Class* thiz) {
    return rvmAttributeGetDeclaringClass(env, thiz);
}

Class* Java_java_lang_Class_getEnclosingClass(Env* env, Class* thiz) {
    Class* enclosingClass = rvmAttributeGetEnclosingClass(env, thiz);
    if (rvmExceptionCheck(env) && rvmExceptionOccurred(env)->clazz != java_lang_ClassNotFoundException) {
        return NULL;
    }
    if (!enclosingClass) {
        rvmExceptionClear(env);
        return rvmAttributeGetDeclaringClass(env, thiz);
    }
    return enclosingClass;
}

Object* Java_java_lang_Class_getEnclosingMethod(Env* env, Class* thiz) {
    Method* method = rvmAttributeGetEnclosingMethod(env, thiz);
    if (!method || METHOD_IS_CONSTRUCTOR(method)) return NULL;
    Class* jlr_Method = rvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
    if (!jlr_Method) return NULL;
    Method* constructor = rvmGetInstanceMethod(env, jlr_Method, "<init>", "(J)V");
    if (!constructor) return NULL;
    jvalue args[1];
    args[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, jlr_Method, constructor, args);
}

Object* Java_java_lang_Class_getEnclosingConstructor(Env* env, Class* thiz) {
    Method* method = rvmAttributeGetEnclosingMethod(env, thiz);
    if (!method || !METHOD_IS_CONSTRUCTOR(method)) return NULL;
    Class* jlr_Constructor = rvmFindClassUsingLoader(env, "java/lang/reflect/Constructor", NULL);
    if (!jlr_Constructor) return NULL;
    Method* constructor = rvmGetInstanceMethod(env, jlr_Constructor, "<init>", "(J)V");
    if (!constructor) return NULL;
    jvalue args[1];
    args[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, jlr_Constructor, constructor, args);
}

ObjectArray* Java_java_lang_Class_getInterfaces(Env* env, Class* thiz) {
    Interface* interfaces = rvmGetInterfaces(env, thiz);
    if (rvmExceptionCheck(env)) return NULL;
    Interface* interface;
    jint length = 0;
    LL_FOREACH(interfaces, interface) {
        length++;
    }
    ObjectArray* result = rvmNewObjectArray(env, length, java_lang_Class, NULL, NULL);
    if (!result) return NULL;
    jint i = 0;
    LL_FOREACH(interfaces, interface) {
        result->values[i++] = (Object*) interface->interface;
    }
    return result;
}

Class* Java_java_lang_Class_getComponentType(Env* env, Class* thiz) {
    return thiz->componentType;
}

Class* Java_java_lang_Class_getSuperclass(Env* env, Class* thiz) {
    return thiz->superclass;
}

ClassLoader* Java_java_lang_Class_getClassLoader(Env* env, Class* c, Class* clazz) {
    return clazz->classLoader;
}

ObjectArray* Java_java_lang_Class_getDeclaredClasses0(Env* env, Class* clazz, jboolean publicOnly) {
    if (CLASS_IS_PRIMITIVE(clazz) || CLASS_IS_ARRAY(clazz)) return NULL;
    ObjectArray* result = rvmAttributeGetDeclaredClasses(env, clazz);
    if (!result || result->length == 0 || !publicOnly) {
        return result;
    }

    jint length = 0;
    jint i;
    for (i = 0; i < result->length; i++) {
        Class* c = (Class*) result->values[i];
        if (CLASS_IS_PUBLIC(c)) {
            length++;
        }
    }

    if (length == 0) return NULL;

    ObjectArray* publicResult = rvmNewObjectArray(env, length, java_lang_Class, NULL, NULL);
    if (!publicResult) return NULL;
    jint index = 0;
    for (i = 0; i < result->length; i++) {
        Class* c = (Class*) result->values[i];
        if (CLASS_IS_PUBLIC(c)) {
            publicResult->values[index++] = (Object*) c;
        }
    }

    return publicResult;
}

ObjectArray* Java_java_lang_Class_getDeclaredConstructors0(Env* env, Class* clazz, jboolean publicOnly) {
    if (CLASS_IS_PRIMITIVE(clazz) || CLASS_IS_ARRAY(clazz)) return NULL;

    Method* methods = rvmGetMethods(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;

    Method* method;
    jint length = 0;
    for (method = methods; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (method = methods; method != NULL; method = method->next) {
        if (METHOD_IS_CONSTRUCTOR(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                Object* c = createConstructorObject(env, method);
                if (!c) return NULL;
                if (!result) {
                    result = rvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                    if (!result) return NULL;
                }
                result->values[i++] = c;
            }
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredMethods0(Env* env, Class* clazz, jboolean publicOnly) {
    if (CLASS_IS_PRIMITIVE(clazz) || CLASS_IS_ARRAY(clazz)) return NULL;

    Method* methods = rvmGetMethods(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;

    Method* method;
    jint length = 0;
    for (method = methods; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                length++;
            }
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (method = methods; method != NULL; method = method->next) {
        if (!METHOD_IS_CONSTRUCTOR(method) && !METHOD_IS_CLASS_INITIALIZER(method)) {
            if (!publicOnly || METHOD_IS_PUBLIC(method)) {
                Object* c = createMethodObject(env, method);
                if (!c) return NULL;
                if (!result) {
                    result = rvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                    if (!result) return NULL;
                }
                result->values[i++] = c;
            }
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredFields0(Env* env, Class* clazz, jboolean publicOnly) {
    if (CLASS_IS_PRIMITIVE(clazz) || CLASS_IS_ARRAY(clazz)) return NULL;

    Field* fields = rvmGetFields(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;

    Field* field;
    jint length = 0;
    for (field = fields; field != NULL; field = field->next) {
        if (!publicOnly || FIELD_IS_PUBLIC(field)) {
            length++;
        }
    }

    ObjectArray* result = NULL;
    jint i = 0;
    for (field = fields; field != NULL; field = field->next) {
        if (!publicOnly || FIELD_IS_PUBLIC(field)) {
            Object* c = createFieldObject(env, field);
            if (!c) return NULL;
            if (!result) {
                result = rvmNewObjectArray(env, length, c->clazz, NULL, NULL);
                if (!result) return NULL;
            }
            result->values[i++] = c;
        }
    }

    return result;
}

ObjectArray* Java_java_lang_Class_getDeclaredAnnotations(Env* env, Class* clazz) {
    return rvmAttributeGetClassRuntimeVisibleAnnotations(env, clazz);
}

static jboolean endsWith(const char *str, const char *suffix) {
    size_t lenStr = strlen(str);
    size_t lenSuffix = strlen(suffix);
    if (lenSuffix > lenStr) return FALSE;
    return strncmp(str + lenStr - lenSuffix, suffix, lenSuffix) == 0;
}

Class* Java_java_lang_Class_classForName(Env* env, Class* cls, Object* className, jboolean initializeBoolean,
            ClassLoader* classLoader) {

    if (!className) {
        rvmThrowNullPointerException(env);
        return NULL;
    }
    char* classNameUTF = toBinaryName(env, className);
    if (!classNameUTF) return NULL;
    Class* clazz = rvmFindClassUsingLoader(env, classNameUTF, classLoader);
    if (!clazz) {
        // FIXME: Temp fix to prevent warnings from the ObjC bridge about missing XXX$Callbacks classes
        if (!endsWith(classNameUTF, "$Callbacks")) {
            char* p = classNameUTF;
            while (*p != '\0') {
                if (*p == '/') *p = '.';
                p++;
            }
            WARNF("Class.forName() failed to load '%s'. "
                  "Use the -forcelinkclasses command line option "
                  "or add <forceLinkClasses><pattern>%s</pattern></forceLinkClasses> "
                  "to your robovm.xml file to link it in.",
                  classNameUTF, classNameUTF);
        }
        return NULL;
    }
    if (initializeBoolean) {
        rvmInitialize(env, clazz);
        if (rvmExceptionCheck(env)) return NULL;
    }
    return clazz;
}

