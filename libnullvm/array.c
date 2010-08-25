#include <nullvm.h>
#include <string.h>

static jint getElementSize(char* typeName) {
    // TODO: Use lookup table instead?
    if (typeName[1] != '\0') {
        return sizeof(Object*);
    }
    switch (typeName[0]) {
    case 'Z':
        return sizeof(jboolean);
    case 'C':
        return sizeof(jchar);
    case 'F':
        return sizeof(jfloat);
    case 'D':
        return sizeof(jdouble);
    case 'B':
        return sizeof(jbyte);
    case 'S':
        return sizeof(jshort);
    case 'I':
        return sizeof(jint);
    case 'J':
        return sizeof(jlong);
    }
    return sizeof(Object*);
}

static Array* newArray(Env* env, Class* arrayType, jint elementSize, jint dims, jint* lengths) {
    jint length = lengths[0];
    if (length < 0) {
        nvmThrowNegativeArraySizeException(env);
        return NULL;
    }

    Array* array = nvmAllocateMemory(env, sizeof(Array) + length * elementSize);
    if (!array) return NULL;

    array->clazz = arrayType;
    array->length = length;

    if (length > 0 && dims > 1) {
        int i;
        char* subName = &(arrayType->name[1]);
        Class* subArrayType = nvmFindClass(env, subName);
        if (!subArrayType) return NULL;
        jint subElementSize = getElementSize(subName);
        Object** values = ((ObjectArray*) array)->values;
        for (i = 0; i < length; i++) {
            Object* o = (Object*) newArray(env, subArrayType, subElementSize, dims - 1, &lengths[1]);
            if (!o) return NULL;
            values[i] = o;
        }
    }

    return array;
}

BooleanArray* nvmNewBooleanArray(Env* env, jint length) {
    return (BooleanArray*) newArray(env, array_Z, sizeof(jboolean), 1, &length);
}

ByteArray* nvmNewByteArray(Env* env, jint length) {
    return (ByteArray*) newArray(env, array_B, sizeof(jbyte), 1, &length);
}

CharArray* nvmNewCharArray(Env* env, jint length) {
    return (CharArray*) newArray(env, array_C, sizeof(jchar), 1, &length);
}

ShortArray* nvmNewShortArray(Env* env, jint length) {
    return (ShortArray*) newArray(env, array_S, sizeof(jshort), 1, &length);
}

IntArray* nvmNewIntArray(Env* env, jint length) {
    return (IntArray*) newArray(env, array_I, sizeof(jint), 1, &length);
}

LongArray* nvmNewLongArray(Env* env, jint length) {
    return (LongArray*) newArray(env, array_J, sizeof(jlong), 1, &length);
}

FloatArray* nvmNewFloatArray(Env* env, jint length) {
    return (FloatArray*) newArray(env, array_F, sizeof(jfloat), 1, &length);
}

DoubleArray* nvmNewDoubleArray(Env* env, jint length) {
    return (DoubleArray*) newArray(env, array_D, sizeof(jdouble), 1, &length);
}

ObjectArray* nvmNewObjectArray(Env* env, jint length, Class* clazz) {
    return (ObjectArray*) newArray(env, clazz, sizeof(Object*), 1, &length);
}

Array* nvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* clazz) {
    return newArray(env, clazz, sizeof(Object*), dims, lengths);
}

