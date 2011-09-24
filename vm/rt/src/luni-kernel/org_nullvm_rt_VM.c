#include <string.h>
#include <nullvm.h>

ObjectArray* Java_org_nullvm_rt_VM_getStackClasses(Env* env, Class* c, jint skipNum, jint maxDepth) {
    CallStackEntry* first = nvmGetCallStack(env);
    if (!first) return NULL;
    first = first->next; // Skip VM.getStackClasses()
    if (!first) return NULL;
    first = first->next; // Skip caller of VM.getStackClasses()
    if (!first) return NULL;

    while (skipNum > 0) {
        first = first->next; // Skip
        if (!first) return NULL;
        skipNum--;
    }

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

Object* Java_org_nullvm_rt_VM_intern(Env* env, Class* c, Object* string) {
    return nvmInternString(env, string);
}

ClassLoader* Java_org_nullvm_rt_VM_getBootClassLoader(Env* env, Class* c) {
    Class* holder = nvmFindClass(env, "java/lang/ClassLoader$BootClassLoaderHolder");
    if (!holder) return NULL;
    ClassField* field = nvmGetClassField(env, holder, "loader", "Ljava/lang/ClassLoader;");
    if (!field) return NULL;
    return (ClassLoader*) nvmGetObjectClassFieldValue(env, holder, field);
}

jlong Java_org_nullvm_rt_VM_getObjectAddress(Env* env, Class* c, Object* object) {
    return (jlong) object;
}
    
Object* Java_org_nullvm_rt_VM_castAddressToObject(Env* env, Class* c, jlong address) {
    return (Object*) address;
}

jint Java_org_nullvm_rt_VM_getInstanceFieldOffset(Env* env, Class* c, jlong fieldPtr) {
    InstanceField* field = (InstanceField*) fieldPtr;
    return field->offset;
}

jlong Java_org_nullvm_rt_VM_getClassFieldAddress(Env* env, Class* c, jlong fieldPtr) {
    ClassField* field = (ClassField*) fieldPtr;
    return (jlong) field->address;
}
    
Object* Java_org_nullvm_rt_VM_getObject(Env* env, Class* c, jlong address) {
    return *((Object**) address);
}

jdouble Java_org_nullvm_rt_VM_getDouble(Env* env, Class* c, jlong address) {
    return *((jdouble*) address);
}

jfloat Java_org_nullvm_rt_VM_getFloat(Env* env, Class* c, jlong address) {
    return *((jfloat*) address);
}

jlong Java_org_nullvm_rt_VM_getLong(Env* env, Class* c, jlong address) {
    return *((jlong*) address);
}

jint Java_org_nullvm_rt_VM_getInt(Env* env, Class* c, jlong address) {
    return *((jint*) address);
}

jchar Java_org_nullvm_rt_VM_getChar(Env* env, Class* c, jlong address) {
    return *((jchar*) address);
}

jshort Java_org_nullvm_rt_VM_getShort(Env* env, Class* c, jlong address) {
    return *((jshort*) address);
}

jbyte Java_org_nullvm_rt_VM_getByte(Env* env, Class* c, jlong address) {
    return *((jbyte*) address);
}

jboolean Java_org_nullvm_rt_VM_getBoolean(Env* env, Class* c, jlong address) {
    return *((jboolean*) address);
}

void Java_org_nullvm_rt_VM_setObject(Env* env, Class* c, jlong address, Object* value) {
    *((Object**) address) = value;
}

void Java_org_nullvm_rt_VM_setDouble(Env* env, Class* c, jlong address, jdouble value) {
    *((jdouble*) address) = value;
}

void Java_org_nullvm_rt_VM_setFloat(Env* env, Class* c, jlong address, jfloat value) {
    *((jfloat*) address) = value;
}

void Java_org_nullvm_rt_VM_setLong(Env* env, Class* c, jlong address, jlong value) {
    *((jlong*) address) = value;
}

void Java_org_nullvm_rt_VM_setInt(Env* env, Class* c, jlong address, jint value) {
    *((jint*) address) = value;
}

void Java_org_nullvm_rt_VM_setChar(Env* env, Class* c, jlong address, jchar value) {
    *((jchar*) address) = value;
}

void Java_org_nullvm_rt_VM_setShort(Env* env, Class* c, jlong address, jshort value) {
    *((jshort*) address) = value;
}

void Java_org_nullvm_rt_VM_setByte(Env* env, Class* c, jlong address, jbyte value) {
    *((jbyte*) address) = value;
}

void Java_org_nullvm_rt_VM_setBoolean(Env* env, Class* c, jlong address, jboolean value) {
    *((jboolean*) address) = value;
}

jlong Java_org_nullvm_rt_VM_getPointer(Env* env, Class* c, jlong address) {
    return (jlong) *((void**) address);
}

void Java_org_nullvm_rt_VM_setPointer(Env* env, Class* c, jlong address, jlong value) {
    *((void**) address) = (void*) value;
}

jlong Java_org_nullvm_rt_VM_getStringUTFChars(Env* env, Class* c, Object* s) {
    return (jlong) nvmGetStringUTFChars(env, s);
}

Object* Java_org_nullvm_rt_VM_newStringUTF(Env* env, Class* c, jlong address) {
    return nvmNewStringUTF(env, (char*) address, -1);
}

jlong Java_org_nullvm_rt_VM_getBooleanArrayElements(Env* env, Class* c, BooleanArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getByteArrayElements(Env* env, Class* c, ByteArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getCharArrayElements(Env* env, Class* c, CharArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getShortArrayElements(Env* env, Class* c, ShortArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getIntArrayElements(Env* env, Class* c, IntArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getLongArrayElements(Env* env, Class* c, LongArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getFloatArrayElements(Env* env, Class* c, FloatArray* array) {
    return (jlong) array->values;
}

jlong Java_org_nullvm_rt_VM_getDoubleArrayElements(Env* env, Class* c, DoubleArray* array) {
    return (jlong) array->values;
}

BooleanArray* Java_org_nullvm_rt_VM_newBooleanArray(Env* env, Class* c, jlong address, jint size) {
    BooleanArray* array = nvmNewBooleanArray(env, size);
    if (array) {
        jbyte* data = (jbyte*) address;
        jint i = 0;
        for (i = 0; i < size; i++) {
            array->values[i] = *data ? TRUE : FALSE;
            data++;
        }
    }
    return array;
}

ByteArray* Java_org_nullvm_rt_VM_newByteArray(Env* env, Class* c, jlong address, jint size) {
    ByteArray* array = nvmNewByteArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jbyte));
    }
    return array;
}

CharArray* Java_org_nullvm_rt_VM_newCharArray(Env* env, Class* c, jlong address, jint size) {
    CharArray* array = nvmNewCharArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jchar));
    }
    return array;
}

ShortArray* Java_org_nullvm_rt_VM_newShortArray(Env* env, Class* c, jlong address, jint size) {
    ShortArray* array = nvmNewShortArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jshort));
    }
    return array;
}

IntArray* Java_org_nullvm_rt_VM_newIntArray(Env* env, Class* c, jlong address, jint size) {
    IntArray* array = nvmNewIntArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jint));
    }
    return array;
}

LongArray* Java_org_nullvm_rt_VM_newLongArray(Env* env, Class* c, jlong address, jint size) {
    LongArray* array = nvmNewLongArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jlong));
    }
    return array;
}

FloatArray* Java_org_nullvm_rt_VM_newFloatArray(Env* env, Class* c, jlong address, jint size) {
    FloatArray* array = nvmNewFloatArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jfloat));
    }
    return array;
}

DoubleArray* Java_org_nullvm_rt_VM_newDoubleArray(Env* env, Class* c, jlong address, jint size) {
    DoubleArray* array = nvmNewDoubleArray(env, size);
    if (array) {
        memcpy(array->values, (void*) address, size * sizeof(jdouble));
    }
    return array;
}

jlong Java_org_nullvm_rt_VM_getPointerArrayElements(Env* env, Class* c, LongArray* array) {
#ifdef NVM_X86_64
    return (jlong) array->values;
#else
    void** data = nvmAllocateMemory(env, array->length * sizeof(void*));
    if (!data) return 0;
    jint i = 0;
    for (i = 0; i < array->length; i++) {
        data[i] = (void*) array->values[i];
    }
    return (jlong) data;
#endif
}

LongArray* Java_org_nullvm_rt_VM_newPointerArray(Env* env, Class* c, jlong address, jint size) {
#ifdef NVM_X86_64
    return Java_org_nullvm_rt_VM_newLongArray(env, c, address, size);
#else
    LongArray* array = nvmNewLongArray(env, size);
    if (array) {
        void** data = (void**) address;
        jint i = 0;
        for (i = 0; i < size; i++) {
            array->values[i] = (jlong) data[i];
        }
    }
    return array;
#endif
}

