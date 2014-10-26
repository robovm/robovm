/*
 * Copyright (C) 2012 Trillian Mobile AB
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

static inline jint getElementSize(Env* env, Class* arrayType) {
    assert(arrayType != NULL);
    assert(CLASS_IS_ARRAY(arrayType));
    assert(arrayType->componentType != NULL);
    if (CLASS_IS_PRIMITIVE(arrayType->componentType)) {
        // Array of primitives
        switch (arrayType->componentType->name[0]) {
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
    }
    return sizeof(Object*);
}

static inline jint getElementAlignment(Env* env, Class* arrayType) {
    assert(arrayType != NULL);
    assert(CLASS_IS_ARRAY(arrayType));
    assert(arrayType->componentType != NULL);
    if (CLASS_IS_PRIMITIVE(arrayType->componentType)) {
        // Array of primitives
        switch (arrayType->componentType->name[0]) {
        case 'Z':
            return __alignof__(jboolean);
        case 'B':
            return __alignof__(jbyte);
        case 'C':
            return __alignof__(jchar);
        case 'S':
            return __alignof__(jshort);
        case 'I':
            return __alignof__(jint);
        case 'J':
            return __alignof__(jlong);
        case 'F':
            return __alignof__(jfloat);
        case 'D':
            return __alignof__(jdouble);
        }
    }
    return __alignof__(Object*);
}

static Array* newArray(Env* env, Class* arrayType, jint dims, jint* lengths) {
    jint length = lengths[0];
    if (length < 0) {
        rvmThrowNegativeArraySizeException(env);
        return NULL;
    }

    Array* array = rvmAllocateMemoryForArray(env, arrayType, length);
    if (!array) return NULL;

    ((Object*) array)->clazz = arrayType;
    array->length = length;

    if (length > 0 && dims > 1) {
        int i;
        Object** values = ((ObjectArray*) array)->values;
        for (i = 0; i < length; i++) {
            Object* o = (Object*) newArray(env, arrayType->componentType, dims - 1, &lengths[1]);
            if (!o) return NULL;
            values[i] = o;
        }
    }

    return array;
}

jint rvmGetArrayElementSize(Env* env, Class* arrayClass) {
    return getElementSize(env, arrayClass);
}

jlong rvmGetArraySize(Env* env, Class* arrayClass, jint length) {
    jint elementSize = getElementSize(env, arrayClass);
    jint elementAlignment = getElementAlignment(env, arrayClass);
    jlong baseSize = (sizeof(Array) + elementAlignment - 1) & ~(elementAlignment - 1);
    jlong size = baseSize + (jlong) length * (jlong) elementSize;
    return size;
}

BooleanArray* rvmNewBooleanArray(Env* env, jint length) {
    return (BooleanArray*) newArray(env, array_Z, 1, &length);
}

ByteArray* rvmNewByteArray(Env* env, jint length) {
    return (ByteArray*) newArray(env, array_B, 1, &length);
}

CharArray* rvmNewCharArray(Env* env, jint length) {
    return (CharArray*) newArray(env, array_C, 1, &length);
}

ShortArray* rvmNewShortArray(Env* env, jint length) {
    return (ShortArray*) newArray(env, array_S, 1, &length);
}

IntArray* rvmNewIntArray(Env* env, jint length) {
    return (IntArray*) newArray(env, array_I, 1, &length);
}

LongArray* rvmNewLongArray(Env* env, jint length) {
    return (LongArray*) newArray(env, array_J, 1, &length);
}

FloatArray* rvmNewFloatArray(Env* env, jint length) {
    return (FloatArray*) newArray(env, array_F, 1, &length);
}

DoubleArray* rvmNewDoubleArray(Env* env, jint length) {
    return (DoubleArray*) newArray(env, array_D, 1, &length);
}

ObjectArray* rvmNewObjectArray(Env* env, jint length, Class* elementClass, Class* arrayClass, Object* init) {
    if (!arrayClass) {
        char* name = NULL;
        if (CLASS_IS_ARRAY(elementClass)) {
            name = rvmAllocateMemoryAtomic(env, strlen(elementClass->name) + 2);
            if (!name) return NULL;
            strcpy(name, "[");
            strcat(name, elementClass->name);
        } else {
            name = rvmAllocateMemoryAtomic(env, strlen(elementClass->name) + 4);
            if (!name) return NULL;
            strcpy(name, "[L");
            strcat(name, elementClass->name);
            strcat(name, ";");
        }
        arrayClass = rvmFindClassUsingLoader(env, name, elementClass->classLoader);
        if (!arrayClass) return NULL;
    }

    ObjectArray *array = (ObjectArray*) newArray(env, arrayClass, 1, &length);
    if (init) {
        jint i;
        for (i = 0; i < length; i++) {
            array->values[i] = init;
        }
    }
    return array;
}

Array* rvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* clazz) {
    return newArray(env, clazz, dims, lengths);
}

Array* rvmCloneArray(Env* env, Array* array) {
    jint elementSize = getElementSize(env, array->object.clazz);
    Array* copy = rvmAllocateMemoryForArray(env, array->object.clazz, array->length);
    if (!copy) return NULL;
    jlong size = rvmGetArraySize(env, array->object.clazz, array->length);
    memcpy(copy, array, (size_t) size);
    copy->object.lock = 0;
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

