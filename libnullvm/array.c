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
    case 'B':
        return sizeof(jbyte);
    case 'C':
        return sizeof(jchar);
    case 'S':
        return sizeof(jshort);
    case 'I':
        return sizeof(jint);
    case 'J':
        return sizeof(jlong);
    case 'F':
        return sizeof(jfloat);
    case 'D':
        return sizeof(jdouble);
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

    ((Object*) array)->clazz = arrayType;
    array->length = length;

    if (length > 0 && dims > 1) {
        int i;
        Class* subArrayType = nvmGetComponentType(env, arrayType);
        jint subElementSize = getElementSize(subArrayType->name);
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

ObjectArray* nvmNewObjectArray(Env* env, jint length, Class* elementClass, Class* clazz) {
    if (!clazz) {
        if (CLASS_IS_ARRAY(elementClass)) {
            char* name = nvmAllocateMemory(env, strlen(elementClass->name) + 2);
            if (!name) return NULL;
            strcpy(name, "[");
            strcat(name, elementClass->name);
            clazz = nvmFindClass(env, name);
            if (!clazz) return NULL;
        } else {
            char* name = nvmAllocateMemory(env, strlen(elementClass->name) + 4);
            if (!name) return NULL;
            strcpy(name, "[L");
            strcat(name, elementClass->name);
            strcat(name, ";");
            clazz = nvmFindClass(env, name);
            if (!clazz) return NULL;
        }
    }
    return (ObjectArray*) newArray(env, clazz, sizeof(Object*), 1, &length);
}

Array* nvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* clazz) {
    return newArray(env, clazz, sizeof(Object*), dims, lengths);
}

Array* nvmCloneArray(Env* env, Array* array) {
    jint elementSize = getElementSize(array->object.clazz->name);
    jint size = sizeof(Array) + array->length * elementSize;
    Array* copy = nvmAllocateMemory(env, size);
    if (!copy) return NULL;
    memcpy(copy, array, size);
    // TODO: Once every Object has a lock we need to assign a new one to the copy
    return copy;
}

jint nvmGetArrayDimensions(Env* env, Array* array) {
    jint i = 1;
    char* desc = array->object.clazz->name;
    while (desc[i] == '[') {
        i++;
    }
    return i;
}

