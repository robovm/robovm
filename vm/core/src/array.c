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
#include <string.h>

static jint getElementSize(const char* typeName) {
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
        rvmThrowNegativeArraySizeException(env);
        return NULL;
    }

    Array* array = rvmAllocateMemory(env, sizeof(Array) + length * elementSize);
    if (!array) return NULL;

    ((Object*) array)->clazz = arrayType;
    array->length = length;

    if (length > 0 && dims > 1) {
        int i;
        Class* subArrayType = arrayType->componentType;
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

BooleanArray* rvmNewBooleanArray(Env* env, jint length) {
    return (BooleanArray*) newArray(env, array_Z, sizeof(jboolean), 1, &length);
}

ByteArray* rvmNewByteArray(Env* env, jint length) {
    return (ByteArray*) newArray(env, array_B, sizeof(jbyte), 1, &length);
}

CharArray* rvmNewCharArray(Env* env, jint length) {
    return (CharArray*) newArray(env, array_C, sizeof(jchar), 1, &length);
}

ShortArray* rvmNewShortArray(Env* env, jint length) {
    return (ShortArray*) newArray(env, array_S, sizeof(jshort), 1, &length);
}

IntArray* rvmNewIntArray(Env* env, jint length) {
    return (IntArray*) newArray(env, array_I, sizeof(jint), 1, &length);
}

LongArray* rvmNewLongArray(Env* env, jint length) {
    return (LongArray*) newArray(env, array_J, sizeof(jlong), 1, &length);
}

FloatArray* rvmNewFloatArray(Env* env, jint length) {
    return (FloatArray*) newArray(env, array_F, sizeof(jfloat), 1, &length);
}

DoubleArray* rvmNewDoubleArray(Env* env, jint length) {
    return (DoubleArray*) newArray(env, array_D, sizeof(jdouble), 1, &length);
}

ObjectArray* rvmNewObjectArray(Env* env, jint length, Class* elementClass, Class* arrayClass, Object* init) {
    if (!arrayClass) {
        char* name = NULL;
        if (CLASS_IS_ARRAY(elementClass)) {
            name = rvmAllocateMemory(env, strlen(elementClass->name) + 2);
            if (!name) return NULL;
            strcpy(name, "[");
            strcat(name, elementClass->name);
        } else {
            name = rvmAllocateMemory(env, strlen(elementClass->name) + 4);
            if (!name) return NULL;
            strcpy(name, "[L");
            strcat(name, elementClass->name);
            strcat(name, ";");
        }
        arrayClass = rvmFindClassUsingLoader(env, name, elementClass->classLoader);
        if (!arrayClass) return NULL;
    }

    ObjectArray *array = (ObjectArray*) newArray(env, arrayClass, sizeof(Object*), 1, &length);
    if (init) {
        jint i;
        for (i = 0; i < length; i++) {
            array->values[i] = init;
        }
    }
    return array;
}

Array* rvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* clazz) {
    return newArray(env, clazz, sizeof(Object*), dims, lengths);
}

Array* rvmCloneArray(Env* env, Array* array) {
    jint elementSize = getElementSize(array->object.clazz->name);
    jint size = sizeof(Array) + array->length * elementSize;
    Array* copy = rvmAllocateMemory(env, size);
    if (!copy) return NULL;
    memcpy(copy, array, size);
    copy->object.monitor = NULL;
    return copy;
}

jint rvmGetArrayDimensions(Env* env, Array* array) {
    jint i = 1;
    const char* desc = array->object.clazz->name;
    while (desc[i] == '[') {
        i++;
    }
    return i;
}

