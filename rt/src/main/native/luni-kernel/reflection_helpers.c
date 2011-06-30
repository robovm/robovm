#include <nullvm.h>
#include "reflection_helpers.h"

static Class* java_lang_reflect_Method = NULL;
static Method* java_lang_reflect_Method_init = NULL;
static Class* java_lang_reflect_Constructor = NULL;
static Method* java_lang_reflect_Constructor_init = NULL;
static Class* java_lang_reflect_Field = NULL;
static Method* java_lang_reflect_Field_init = NULL;
static InstanceField* java_lang_reflect_Field_field = NULL;

Object* createMethodObject(Env* env, Method* method) {
    if (!java_lang_reflect_Method) {
        java_lang_reflect_Method = nvmFindClassUsingLoader(env, "java/lang/reflect/Method", NULL);
        if (!java_lang_reflect_Method) return NULL;
    }
    if (!java_lang_reflect_Method_init) {
        java_lang_reflect_Method_init = nvmGetInstanceMethod(env, java_lang_reflect_Method, "<init>", "(J)V");
        if (!java_lang_reflect_Method_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = (jlong) method;
    return nvmNewObjectA(env, java_lang_reflect_Method, java_lang_reflect_Method_init, initArgs);
}

Object* createFieldObject(Env* env, Field* field) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = nvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_init) {
        java_lang_reflect_Field_init = nvmGetInstanceMethod(env, java_lang_reflect_Field, "<init>", "(J)V");
        if (!java_lang_reflect_Field_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = (jlong) field;
    return nvmNewObjectA(env, java_lang_reflect_Field, java_lang_reflect_Field_init, initArgs);
}

Object* createConstructorObject(Env* env, Method* method) {
    if (!java_lang_reflect_Constructor) {
        java_lang_reflect_Constructor = nvmFindClassUsingLoader(env, "java/lang/reflect/Constructor", NULL);
        if (!java_lang_reflect_Constructor) return NULL;
    }
    if (!java_lang_reflect_Constructor_init) {
        java_lang_reflect_Constructor_init = nvmGetInstanceMethod(env, java_lang_reflect_Constructor, "<init>", "(J)V");
        if (!java_lang_reflect_Constructor_init) return NULL;
    }
    jvalue initArgs[1];
    initArgs[0].j = (jlong) method;
    return nvmNewObjectA(env, java_lang_reflect_Constructor, java_lang_reflect_Constructor_init, initArgs);
}

Field* getFieldFromFieldObject(Env* env, Object* fieldObject) {
    if (!java_lang_reflect_Field) {
        java_lang_reflect_Field = nvmFindClassUsingLoader(env, "java/lang/reflect/Field", NULL);
        if (!java_lang_reflect_Field) return NULL;
    }
    if (!java_lang_reflect_Field_field) {
        java_lang_reflect_Field_field = nvmGetInstanceField(env, java_lang_reflect_Field, "field", "J");
        if (!java_lang_reflect_Field_field) return NULL;
    }
    return (Field*) nvmGetLongInstanceFieldValue(env, fieldObject, java_lang_reflect_Field_field);
}

// TODO: Some of the code here may have been copied from Android.

static jvalue emptyJValueArgs[1];
jvalue* validateAndUnwrapArgs(Env* env, ObjectArray* parameterTypes, ObjectArray* args) {
    jint length = args->length;
    jvalue* jvalueArgs = length > 0 ? (jvalue*) nvmAllocateMemory(env, sizeof(jvalue) * length) : emptyJValueArgs;
    if (!jvalueArgs) return NULL;

    jint i;
    for (i = 0; i < length; i++) {
        Object* arg = args->values[i];
        Class* type = (Class*) parameterTypes->values[i];
        if (type->primitive) {
            if (!unwrapPrimitive(env, arg, type, &jvalueArgs[i])) return NULL;
        } else {
            if (arg && !nvmIsInstanceOf(env, arg, type)) {
                nvmThrowIllegalArgumentException(env, "argument type mismatch");
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
    InstanceField* f = nvmGetInstanceField(env, java_lang_Boolean, "value", "Z");
    if (!f) return FALSE;
    value->z = nvmGetBooleanInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapByte(Env* env, Object* arg, jvalue* value) {
    if (arg->clazz != java_lang_Byte) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Byte, "value", "B");
    if (!f) return FALSE;
    value->b = nvmGetByteInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapChar(Env* env, Object* arg, jvalue* value) {
    if (arg->clazz != java_lang_Character) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Character, "value", "C");
    if (!f) return FALSE;
    value->c = nvmGetCharInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapShort(Env* env, Object* arg, jvalue* value) {
    if (unwrapByte(env, arg, value)) {
        value->s = value->b;
        return TRUE;
    } else if (arg->clazz != java_lang_Short) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Short, "value", "S");
    if (!f) return FALSE;
    value->s = nvmGetShortInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapInt(Env* env, Object* arg, jvalue* value) {
    if (unwrapShort(env, arg, value)) {
        value->i = value->s;
        return TRUE;
    } else if (arg->clazz != java_lang_Integer) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Integer, "value", "I");
    if (!f) return FALSE;
    value->i = nvmGetIntInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapLong(Env* env, Object* arg, jvalue* value) {
    if (unwrapInt(env, arg, value)) {
        value->j = value->i;
        return TRUE;
    } else if (arg->clazz != java_lang_Long) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Long, "value", "J");
    if (!f) return FALSE;
    value->j = nvmGetLongInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapFloat(Env* env, Object* arg, jvalue* value) {
    if (unwrapLong(env, arg, value)) {
        value->f = value->j;
        return TRUE;
    } else if (arg->clazz != java_lang_Float) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Float, "value", "F");
    if (!f) return FALSE;
    value->f = nvmGetFloatInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapDouble(Env* env, Object* arg, jvalue* value) {
    if (unwrapFloat(env, arg, value)) {
        value->d = value->f;
        return TRUE;
    } else if (arg->clazz != java_lang_Double) {
        return FALSE;
    }
    InstanceField* f = nvmGetInstanceField(env, java_lang_Double, "value", "D");
    if (!f) return FALSE;
    value->d = nvmGetDoubleInstanceFieldValue(env, arg, f);
    return TRUE;
}

jboolean unwrapPrimitive(Env* env, Object* arg, Class* parameterType, jvalue* value) {
    if (arg == NULL) {
        nvmThrowIllegalArgumentException(env, "");
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
        nvmThrowIllegalArgumentException(env, "argument type mismatch");
        return FALSE;
    }

    return TRUE;
}

Object* wrapBoolean(Env* env, jboolean v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Boolean, "valueOf", "(Z)Ljava/lang/Boolean;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].z = v;
    return nvmCallObjectClassMethodA(env, java_lang_Boolean, m, args);
}

Object* wrapByte(Env* env, jbyte v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Byte, "valueOf", "(B)Ljava/lang/Byte;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].b = v;
    return nvmCallObjectClassMethodA(env, java_lang_Byte, m, args);
}

Object* wrapChar(Env* env, jchar v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Character, "valueOf", "(C)Ljava/lang/Character;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].c = v;
    return nvmCallObjectClassMethodA(env, java_lang_Character, m, args);
}

Object* wrapShort(Env* env, jshort v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Short, "valueOf", "(S)Ljava/lang/Short;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].s = v;
    return nvmCallObjectClassMethodA(env, java_lang_Short, m, args);
}

Object* wrapInt(Env* env, jint v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Integer, "valueOf", "(I)Ljava/lang/Integer;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].i = v;
    return nvmCallObjectClassMethodA(env, java_lang_Integer, m, args);
}

Object* wrapLong(Env* env, jlong v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Long, "valueOf", "(J)Ljava/lang/Long;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].j = v;
    return nvmCallObjectClassMethodA(env, java_lang_Long, m, args);
}

Object* wrapFloat(Env* env, jfloat v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Float, "valueOf", "(F)Ljava/lang/Float;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].f = v;
    return nvmCallObjectClassMethodA(env, java_lang_Float, m, args);
}

Object* wrapDouble(Env* env, jdouble v) {
    if (nvmExceptionCheck(env)) return NULL;
    Method* m = nvmGetClassMethod(env, java_lang_Double, "valueOf", "(D)Ljava/lang/Double;");
    if (!m) return NULL;
    jvalue args[1];
    args[0].d = v;
    return nvmCallObjectClassMethodA(env, java_lang_Double, m, args);
}

