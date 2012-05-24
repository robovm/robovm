/*
 * Copyright (C) 2012 RoboVM
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
#include <robovm.h>
#include "reflection_helpers.h"

static Class* java_lang_reflect_Method = NULL;
static Method* java_lang_reflect_Method_init = NULL;
static Class* java_lang_reflect_Constructor = NULL;
static Method* java_lang_reflect_Constructor_init = NULL;
static Class* java_lang_reflect_Field = NULL;
static Method* java_lang_reflect_Field_init = NULL;
static InstanceField* java_lang_reflect_Method_method = NULL;
static InstanceField* java_lang_reflect_Field_field = NULL;
static Class* java_lang_reflect_InvocationTargetException = NULL;
static Method* java_lang_reflect_InvocationTargetException_init = NULL;

Object* createMethodObject(Env* env, Method* method) {
    if (!java_lang_reflect_Method) {
        java_lang_reflect_Method = rvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
        if (!java_lang_reflect_Method) return NULL;
    }
    if (!java_lang_reflect_Method_init) {
        java_lang_reflect_Method_init = rvmGetInstanceMethod(env, java_lang_reflect_Method, "<init>", "(J)V");
        if (!java_lang_reflect_Method_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, java_lang_reflect_Method, java_lang_reflect_Method_init, initArgs);
}

Object* createFieldObject(Env* env, Field* field) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = rvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_init) {
        java_lang_reflect_Field_init = rvmGetInstanceMethod(env, java_lang_reflect_Field, "<init>", "(J)V");
        if (!java_lang_reflect_Field_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(field);
    return rvmNewObjectA(env, java_lang_reflect_Field, java_lang_reflect_Field_init, initArgs);
}

Object* createConstructorObject(Env* env, Method* method) {
    if (!java_lang_reflect_Constructor) {
        java_lang_reflect_Constructor = rvmFindClassUsingLoader(env, "java/lang/reflect/Constructor", NULL);
        if (!java_lang_reflect_Constructor) return NULL;
    }
    if (!java_lang_reflect_Constructor_init) {
        java_lang_reflect_Constructor_init = rvmGetInstanceMethod(env, java_lang_reflect_Constructor, "<init>", "(J)V");
        if (!java_lang_reflect_Constructor_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = PTR_TO_LONG(method);
    return rvmNewObjectA(env, java_lang_reflect_Constructor, java_lang_reflect_Constructor_init, initArgs);
}

Method* getMethodFromMethodObject(Env* env, Object* methodObject) {
    if (!java_lang_reflect_Method) {
        java_lang_reflect_Method = rvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
        if (!java_lang_reflect_Method) return NULL;
    }
    if (!java_lang_reflect_Method_method) {
        java_lang_reflect_Method_method = rvmGetInstanceField(env, java_lang_reflect_Method, "method", "J");
        if (!java_lang_reflect_Method_method) return NULL;
    }
    return (Method*) LONG_TO_PTR(rvmGetLongInstanceFieldValue(env, methodObject, java_lang_reflect_Method_method));
}

Field* getFieldFromFieldObject(Env* env, Object* fieldObject) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = rvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_field) {
        java_lang_reflect_Field_field = rvmGetInstanceField(env, java_lang_reflect_Field, "field", "J");
        if (!java_lang_reflect_Field_field) return NULL;
    }
    return (Field*) LONG_TO_PTR(rvmGetLongInstanceFieldValue(env, fieldObject, java_lang_reflect_Field_field));
}

void throwInvocationTargetException(Env* env, Object* throwable) {
    rvmExceptionClear(env);
    if (!java_lang_reflect_InvocationTargetException) {
        java_lang_reflect_InvocationTargetException = rvmFindClassUsingLoader(env, "java/lang/reflect/InvocationTargetException", NULL);
        if (!java_lang_reflect_InvocationTargetException) return;
    }
    if (!java_lang_reflect_InvocationTargetException_init) {
        java_lang_reflect_InvocationTargetException_init = rvmGetMethod(env, java_lang_reflect_InvocationTargetException, "<init>", "(Ljava/lang/Throwable;)V");
        if (!java_lang_reflect_InvocationTargetException_init) return;
    }
    jvalue initArgs[1];
    initArgs[0].l = (jobject) throwable;
    Object* exception = rvmNewObjectA(env, java_lang_reflect_InvocationTargetException, java_lang_reflect_InvocationTargetException_init, initArgs);
    if (!exception) return;
    rvmThrow(env, exception);
}

// TODO: Some of the code here may have been copied from Android.

static jvalue emptyJValueArgs[1];
jvalue* validateAndUnwrapArgs(Env* env, ObjectArray* parameterTypes, ObjectArray* args) {
    jint length = args->length;
    jvalue* jvalueArgs = length > 0 ? (jvalue*) rvmAllocateMemory(env, sizeof(jvalue) * length) : emptyJValueArgs;
    if (!jvalueArgs) return NULL;

    jint i;
    for (i = 0; i < length; i++) {
        Object* arg = args->values[i];
        Class* type = (Class*) parameterTypes->values[i];
        if (CLASS_IS_PRIMITIVE(type)) {
            if (!unwrapPrimitive(env, arg, type, &jvalueArgs[i])) return NULL;
        } else {
            if (arg && !rvmIsInstanceOf(env, arg, type)) {
                rvmThrowIllegalArgumentException(env, "argument type mismatch");
                return NULL;
            }
            jvalueArgs[i].l = (jobject) arg;
        }
    }
    return jvalueArgs;
}

jboolean unwrapBoolean(Env* env, Object* arg, jvalue* value) {
    if (arg->clazz != java_lang_Boolean) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Boolean, "value", "Z");
    if (!f) return FALSE;
    value->z = rvmGetBooleanInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapByte(Env* env, Object* arg, jvalue* value) {
    if (arg->clazz != java_lang_Byte) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Byte, "value", "B");
    if (!f) return FALSE;
    value->b = rvmGetByteInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapChar(Env* env, Object* arg, jvalue* value) {
    if (arg->clazz != java_lang_Character) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Character, "value", "C");
    if (!f) return FALSE;
    value->c = rvmGetCharInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapShort(Env* env, Object* arg, jvalue* value) {
    if (unwrapByte(env, arg, value)) {
        value->s = value->b;
        return TRUE;
    } else if (arg->clazz != java_lang_Short) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Short, "value", "S");
    if (!f) return FALSE;
    value->s = rvmGetShortInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapInt(Env* env, Object* arg, jvalue* value) {
    if (unwrapShort(env, arg, value)) {
        value->i = value->s;
        return TRUE;
    } else if (arg->clazz != java_lang_Integer) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Integer, "value", "I");
    if (!f) return FALSE;
    value->i = rvmGetIntInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapLong(Env* env, Object* arg, jvalue* value) {
    if (unwrapInt(env, arg, value)) {
        value->j = value->i;
        return TRUE;
    } else if (arg->clazz != java_lang_Long) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Long, "value", "J");
    if (!f) return FALSE;
    value->j = rvmGetLongInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapFloat(Env* env, Object* arg, jvalue* value) {
    if (unwrapLong(env, arg, value)) {
        value->f = value->j;
        return TRUE;
    } else if (arg->clazz != java_lang_Float) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Float, "value", "F");
    if (!f) return FALSE;
    value->f = rvmGetFloatInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapDouble(Env* env, Object* arg, jvalue* value) {
    if (unwrapFloat(env, arg, value)) {
        value->d = value->f;
        return TRUE;
    } else if (arg->clazz != java_lang_Double) {
        return FALSE;
    }
    InstanceField* f = rvmGetInstanceField(env, java_lang_Double, "value", "D");
    if (!f) return FALSE;
    value->d = rvmGetDoubleInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapPrimitive(Env* env, Object* arg, Class* parameterType, jvalue* value) {
    if (arg == NULL) {
        rvmThrowIllegalArgumentException(env, "");
        return FALSE;
    }

    jboolean (*unwrapFunc)(Env*, Object*, jvalue*) = NULL;
    if (parameterType == prim_Z) {
        unwrapFunc = unwrapBoolean;
    } else if (parameterType == prim_B) {
        unwrapFunc = unwrapByte;
    } else if (parameterType == prim_C) {
        unwrapFunc = unwrapChar;
    } else if (parameterType == prim_S) {
        unwrapFunc = unwrapShort;
    } else if (parameterType == prim_I) {
        unwrapFunc = unwrapInt;
    } else if (parameterType == prim_J) {
        unwrapFunc = unwrapLong;
    } else if (parameterType == prim_F) {
        unwrapFunc = unwrapFloat;
    } else if (parameterType == prim_D) {
        unwrapFunc = unwrapDouble;
    }

    if (!unwrapFunc(env, arg, value)) {
        rvmThrowIllegalArgumentException(env, "argument type mismatch");
        return FALSE;
    }

    return TRUE;
}

Object* wrapBoolean(Env* env, jboolean v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Boolean, "valueOf", "(Z)Ljava/lang/Boolean;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].z = v;
    return rvmCallObjectClassMethodA(env, java_lang_Boolean, m, args);
}

Object* wrapByte(Env* env, jbyte v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Byte, "valueOf", "(B)Ljava/lang/Byte;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].b = v;
    return rvmCallObjectClassMethodA(env, java_lang_Byte, m, args);
}

Object* wrapChar(Env* env, jchar v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Character, "valueOf", "(C)Ljava/lang/Character;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].c = v;
    return rvmCallObjectClassMethodA(env, java_lang_Character, m, args);
}

Object* wrapShort(Env* env, jshort v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Short, "valueOf", "(S)Ljava/lang/Short;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].s = v;
    return rvmCallObjectClassMethodA(env, java_lang_Short, m, args);
}

Object* wrapInt(Env* env, jint v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Integer, "valueOf", "(I)Ljava/lang/Integer;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].i = v;
    return rvmCallObjectClassMethodA(env, java_lang_Integer, m, args);
}

Object* wrapLong(Env* env, jlong v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Long, "valueOf", "(J)Ljava/lang/Long;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].j = v;
    return rvmCallObjectClassMethodA(env, java_lang_Long, m, args);
}

Object* wrapFloat(Env* env, jfloat v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Float, "valueOf", "(F)Ljava/lang/Float;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].f = v;
    return rvmCallObjectClassMethodA(env, java_lang_Float, m, args);
}

Object* wrapDouble(Env* env, jdouble v) {
    if (rvmExceptionCheck(env)) return NULL;
    Method* m = rvmGetClassMethod(env, java_lang_Double, "valueOf", "(D)Ljava/lang/Double;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].d = v;
    return rvmCallObjectClassMethodA(env, java_lang_Double, m, args);
}

