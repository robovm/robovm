#include <string.h>
#include <nullvm.h>
#include "reflection_helpers.h"

static char* createClasspathFromClasspathEntries(Env* env, ClasspathEntry* first) {
    jint length = 0;
    ClasspathEntry* entry = first;
    while (entry) {
        length += strlen(entry->jarPath);
        entry = entry->next;
        if (entry) length++; // Make room for the :
    }

    char* p = nvmAllocateMemory(env, length + 1);
    if (!p) return NULL;

    entry = first;
    while (entry) {
        strcat(p, entry->jarPath);
        entry = entry->next;
        if (entry) strcat(p, ":");
    }

    return p;
}

Object* Java_org_nullvm_rt_VM_bootClassPath(Env* env, Class* c) {
    char* bootclasspath = createClasspathFromClasspathEntries(env, env->vm->options->bootclasspath);
    if (!bootclasspath) return NULL;
    return nvmNewStringUTF(env, bootclasspath, -1);
}

Object* Java_org_nullvm_rt_VM_classPath(Env* env, Class* c) {
    char* classpath = createClasspathFromClasspathEntries(env, env->vm->options->classpath);
    if (!classpath) return NULL;
    return nvmNewStringUTF(env, classpath, -1);
}

Object* Java_org_nullvm_rt_VM_vmVersion(Env* env, Class* c) {
    // TODO: Use version from Maven pom
    return nvmNewStringUTF(env, "0.0.1-SNAPSHOT", -1);
}

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

jlong Java_org_nullvm_rt_VM_allocateMemory(Env* env, Class* c, jint size) {
    return PTR_TO_LONG(nvmAllocateMemory(env, size));
}

void Java_org_nullvm_rt_VM_memcpy(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    memcpy(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

void Java_org_nullvm_rt_VM_memmove(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    memmove(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

jlong Java_org_nullvm_rt_VM_getCallbackMethodImpl(Env* env, Class* c, Object* methodObject) {
    Method* method = getMethodFromMethodObject(env, methodObject);
    return PTR_TO_LONG(((CallbackMethod*) method)->callbackImpl);
}

jlong Java_org_nullvm_rt_VM_getObjectAddress(Env* env, Class* c, Object* object) {
    return PTR_TO_LONG(object);
}
    
Object* Java_org_nullvm_rt_VM_castAddressToObject(Env* env, Class* c, jlong address) {
    return (Object*) LONG_TO_PTR(address);
}

jint Java_org_nullvm_rt_VM_getInstanceFieldOffset(Env* env, Class* c, jlong fieldPtr) {
    InstanceField* field = (InstanceField*) LONG_TO_PTR(fieldPtr);
    return field->offset;
}

jlong Java_org_nullvm_rt_VM_getClassFieldAddress(Env* env, Class* c, jlong fieldPtr) {
    ClassField* field = (ClassField*) LONG_TO_PTR(fieldPtr);
    return PTR_TO_LONG(field->address);
}
    
Object* Java_org_nullvm_rt_VM_getObject(Env* env, Class* c, jlong address) {
    return *((Object**) LONG_TO_PTR(address));
}

jdouble Java_org_nullvm_rt_VM_getDouble(Env* env, Class* c, jlong address) {
    return *((jdouble*) LONG_TO_PTR(address));
}

jfloat Java_org_nullvm_rt_VM_getFloat(Env* env, Class* c, jlong address) {
    return *((jfloat*) LONG_TO_PTR(address));
}

jlong Java_org_nullvm_rt_VM_getLong(Env* env, Class* c, jlong address) {
    return *((jlong*) LONG_TO_PTR(address));
}

jint Java_org_nullvm_rt_VM_getInt(Env* env, Class* c, jlong address) {
    return *((jint*) LONG_TO_PTR(address));
}

jchar Java_org_nullvm_rt_VM_getChar(Env* env, Class* c, jlong address) {
    return *((jchar*) LONG_TO_PTR(address));
}

jshort Java_org_nullvm_rt_VM_getShort(Env* env, Class* c, jlong address) {
    return *((jshort*) LONG_TO_PTR(address));
}

jbyte Java_org_nullvm_rt_VM_getByte(Env* env, Class* c, jlong address) {
    return *((jbyte*) LONG_TO_PTR(address));
}

jboolean Java_org_nullvm_rt_VM_getBoolean(Env* env, Class* c, jlong address) {
    return *((jboolean*) LONG_TO_PTR(address));
}

void Java_org_nullvm_rt_VM_setObject(Env* env, Class* c, jlong address, Object* value) {
    *((Object**) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setDouble(Env* env, Class* c, jlong address, jdouble value) {
    *((jdouble*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setFloat(Env* env, Class* c, jlong address, jfloat value) {
    *((jfloat*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setLong(Env* env, Class* c, jlong address, jlong value) {
    *((jlong*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setInt(Env* env, Class* c, jlong address, jint value) {
    *((jint*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setChar(Env* env, Class* c, jlong address, jchar value) {
    *((jchar*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setShort(Env* env, Class* c, jlong address, jshort value) {
    *((jshort*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setByte(Env* env, Class* c, jlong address, jbyte value) {
    *((jbyte*) LONG_TO_PTR(address)) = value;
}

void Java_org_nullvm_rt_VM_setBoolean(Env* env, Class* c, jlong address, jboolean value) {
    *((jboolean*) LONG_TO_PTR(address)) = value;
}

jlong Java_org_nullvm_rt_VM_getPointer(Env* env, Class* c, jlong address) {
    return PTR_TO_LONG(*((void**) LONG_TO_PTR(address)));
}

void Java_org_nullvm_rt_VM_setPointer(Env* env, Class* c, jlong address, jlong value) {
    *((void**) LONG_TO_PTR(address)) = LONG_TO_PTR(value);
}

jlong Java_org_nullvm_rt_VM_getStringUTFChars(Env* env, Class* c, Object* s) {
    return PTR_TO_LONG(nvmGetStringUTFChars(env, s));
}

Object* Java_org_nullvm_rt_VM_newStringUTF(Env* env, Class* c, jlong address) {
    return nvmNewStringUTF(env, (char*) LONG_TO_PTR(address), -1);
}

jlong Java_org_nullvm_rt_VM_getArrayValuesAddress(Env* env, Class* c, Array* array) {
    return PTR_TO_LONG(array->values);
}

BooleanArray* Java_org_nullvm_rt_VM_newBooleanArray(Env* env, Class* c, jlong address, jint size) {
    BooleanArray* array = nvmNewBooleanArray(env, size);
    if (array) {
        jbyte* data = (jbyte*) LONG_TO_PTR(address);
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
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jbyte));
    }
    return array;
}

CharArray* Java_org_nullvm_rt_VM_newCharArray(Env* env, Class* c, jlong address, jint size) {
    CharArray* array = nvmNewCharArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jchar));
    }
    return array;
}

ShortArray* Java_org_nullvm_rt_VM_newShortArray(Env* env, Class* c, jlong address, jint size) {
    ShortArray* array = nvmNewShortArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jshort));
    }
    return array;
}

IntArray* Java_org_nullvm_rt_VM_newIntArray(Env* env, Class* c, jlong address, jint size) {
    IntArray* array = nvmNewIntArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jint));
    }
    return array;
}

LongArray* Java_org_nullvm_rt_VM_newLongArray(Env* env, Class* c, jlong address, jint size) {
    LongArray* array = nvmNewLongArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jlong));
    }
    return array;
}

FloatArray* Java_org_nullvm_rt_VM_newFloatArray(Env* env, Class* c, jlong address, jint size) {
    FloatArray* array = nvmNewFloatArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jfloat));
    }
    return array;
}

DoubleArray* Java_org_nullvm_rt_VM_newDoubleArray(Env* env, Class* c, jlong address, jint size) {
    DoubleArray* array = nvmNewDoubleArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jdouble));
    }
    return array;
}

jlong Java_org_nullvm_rt_VM_getPointerArrayValuesAddress(Env* env, Class* c, LongArray* array) {
#ifdef NVM_X86_64
    return PTR_TO_LONG(array->values);
#else
    void** data = nvmAllocateMemory(env, array->length * sizeof(void*));
    if (!data) return 0;
    jint i = 0;
    for (i = 0; i < array->length; i++) {
        data[i] = LONG_TO_PTR(array->values[i]);
    }
    return PTR_TO_LONG(data);
#endif
}

LongArray* Java_org_nullvm_rt_VM_newPointerArray(Env* env, Class* c, jlong address, jint size) {
#ifdef NVM_X86_64
    return Java_org_nullvm_rt_VM_newLongArray(env, c, LONG_TO_PTR(address), size);
#else
    LongArray* array = nvmNewLongArray(env, size);
    if (array) {
        void** data = (void**) LONG_TO_PTR(address);
        jint i = 0;
        for (i = 0; i < size; i++) {
            array->values[i] = PTR_TO_LONG(data[i]);
        }
    }
    return array;
#endif
}

