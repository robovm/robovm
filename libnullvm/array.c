#include <nullvm.h>
#include <string.h>

#define T_BOOLEAN 4
#define T_CHAR 5
#define T_FLOAT 6
#define T_DOUBLE 7
#define T_BYTE 8
#define T_SHORT 9
#define T_INT 10
#define T_LONG 11

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

static Array* newArray(Class* array_type, jint elementSize, jint dims, jint* lengths) {
    // lengths are expected to contain dims non negative integers

    Array* array;
    jint length = lengths[0];

    array = nvmAllocateMemory(sizeof(Array) + length * elementSize);
    // TODO: Detect if we run out of memory and return NULL and let the caller throw OOME if needed?

    array->clazz = array_type;
    array->length = length;

    if (length > 0 && dims > 1) {
        int i;
        char* subName = &(array_type->name[1]);
        Class* subArrayType = nvmGetArrayClass(subName);
        jint subElementSize = getElementSize(subName);
        Object** values = ((ObjectArray*) array)->values;
        for (i = 0; i < length; i++) {
            values[i] = (Object*) newArray(subArrayType, subElementSize, dims - 1, &lengths[1]);
        }
    }

    return array;
}

Array* nvmNewArray(jint type, jint length) {

    if (length < 0) {
        nvmThrowNegativeArraySizeException();
    }

    // TODO: Create type specific versions of this function and inline it
    char* className;
    int elementSize = 0;
    Array* array;

    switch (type) {
    case T_BOOLEAN:
        className = "[Z";
        elementSize = sizeof(jboolean);
        break;
    case T_CHAR:
        className = "[C";
        elementSize = sizeof(jchar);
        break;
    case T_FLOAT:
        className = "[F";
        elementSize = sizeof(jfloat);
        break;
    case T_DOUBLE:
        className = "[D";
        elementSize = sizeof(jdouble);
        break;
    case T_BYTE:
        className = "[B";
        elementSize = sizeof(jbyte);
        break;
    case T_SHORT:
        className = "[S";
        elementSize = sizeof(jshort);
        break;
    case T_INT:
        className = "[I";
        elementSize = sizeof(jint);
        break;
    case T_LONG:
        className = "[J";
        elementSize = sizeof(jlong);
        break;
    }
/*    LOG("Allocating array of type %s with element size %d and length %d\n", className, elementSize, length);
    LOG("sizeof(Array) = %d\n", sizeof(Array));
    LOG("sizeof(jint_array) = %d\n", sizeof(jint_array));
    LOG("byte size: %d\n", sizeof(Array) + length * elementSize);*/
    
    return newArray(nvmGetArrayClass(className), elementSize, 1, &length);
}

Array* nvmANewArray(char* type, jint length) {
    // TODO: Create inline version
    // TODO: Precompute array class name in Java

    if (length < 0) {
        nvmThrowNegativeArraySizeException();
    }

    char* className = nvmAllocateMemory(strlen(type) + 2);
    strcpy(className, "[");
    strcat(className, type);
    Class* array_type = nvmGetArrayClass(className);

    return newArray(nvmGetArrayClass(className), sizeof(Object*), 1, &length);
}

Array* nvmMultiANewArray(char* type, jint dims, jint* lengths) {
    int i;
    for (i = 0; i < dims; i++) {
        if (lengths[i] < 0) {
            nvmThrowNegativeArraySizeException();
        }
    }
    return newArray(nvmGetArrayClass(type), sizeof(Object*), dims, lengths);
}

