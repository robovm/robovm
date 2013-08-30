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

static char* createClasspathFromClasspathEntries(Env* env, ClasspathEntry* first) {
    jint length = 0;
    ClasspathEntry* entry = first;
    while (entry) {
        length += strlen(entry->jarPath);
        entry = entry->next;
        if (entry) length++; // Make room for the :
    }

    char* p = rvmAllocateMemoryAtomic(env, length + 1);
    if (!p) return NULL;

    entry = first;
    while (entry) {
        strcat(p, entry->jarPath);
        entry = entry->next;
        if (entry) strcat(p, ":");
    }

    return p;
}

Object* Java_org_robovm_rt_VM_bootClassPath(Env* env, Class* c) {
    char* bootclasspath = createClasspathFromClasspathEntries(env, env->vm->options->bootclasspath);
    if (!bootclasspath) return NULL;
    return rvmNewStringUTF(env, bootclasspath, -1);
}

Object* Java_org_robovm_rt_VM_classPath(Env* env, Class* c) {
    char* classpath = createClasspathFromClasspathEntries(env, env->vm->options->classpath);
    if (!classpath) return NULL;
    return rvmNewStringUTF(env, classpath, -1);
}

Object* Java_org_robovm_rt_VM_basePath(Env* env, Class* c) {
    return rvmNewStringUTF(env, env->vm->options->basePath, -1);
}

Object* Java_org_robovm_rt_VM_executablePath(Env* env, Class* c) {
    return rvmNewStringUTF(env, env->vm->options->executablePath, -1);
}

ObjectArray* Java_org_robovm_rt_VM_getStackClasses(Env* env, Class* c, jint skipNum, jint maxDepth) {
    CallStack* callStack = rvmCaptureCallStack(env);
    if (!callStack) return NULL;

    jint index = 0;
    rvmGetNextCallStackMethod(env, callStack, &index); // Skip VM.getStackClasses()
    rvmGetNextCallStackMethod(env, callStack, &index); // Skip caller of VM.getStackClasses()

    while (skipNum > 0) {
        Method* m = rvmGetNextCallStackMethod(env, callStack, &index);
        if (!m) return NULL;
        skipNum--;
    }

    jint first = index;

    jint depth = 0;
    while (rvmGetNextCallStackMethod(env, callStack, &index)) {
        depth++;
    }
    if (maxDepth > -1 && maxDepth < depth) {
        depth = maxDepth;
    }
    
    ObjectArray* result = rvmNewObjectArray(env, depth, java_lang_Class, NULL, NULL);
    if (!result) return NULL;
    jint i;
    index = first;
    for (i = 0; i < depth; i++) {
        Method* m = rvmGetNextCallStackMethod(env, callStack, &index);
        result->values[i] = (Object*) m->clazz;
    }
    return result;
}

jlong Java_org_robovm_rt_VM_allocateMemory(Env* env, Class* c, jint size) {
    return PTR_TO_LONG(rvmAllocateMemory(env, size));
}

jlong Java_org_robovm_rt_VM_allocateMemoryUncollectable(Env* env, Class* c, jint size) {
    return PTR_TO_LONG(rvmAllocateMemoryUncollectable(env, size));
}

jlong Java_org_robovm_rt_VM_allocateMemoryAtomic(Env* env, Class* c, jint size) {
    return PTR_TO_LONG(rvmAllocateMemoryAtomic(env, size));
}

void Java_org_robovm_rt_VM_freeMemoryUncollectable(Env* env, Class* c, jlong address) {
    rvmFreeMemoryUncollectable(env, LONG_TO_PTR(address));
}

void Java_org_robovm_rt_VM_registerDisappearingLink(Env* env, Class* c, jlong address, Object* obj) {
    rvmRegisterDisappearingLink(env, LONG_TO_PTR(address), obj);
}

void Java_org_robovm_rt_VM_unregisterDisappearingLink(Env* env, Class* c, jlong address) {
    rvmUnregisterDisappearingLink(env, LONG_TO_PTR(address));
}

jlong Java_org_robovm_rt_VM_malloc(Env* env, Class* c, jint size) {
    void* m = malloc(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return 0;
    }
    memset(m, 0, size);
    return PTR_TO_LONG(m);
}

void Java_org_robovm_rt_VM_free(Env* env, Class* c, jlong address) {
    free(LONG_TO_PTR(address));
}

Object* Java_org_robovm_rt_VM_allocateObject(Env* env, Class* c, Class* cls) {
    Object *o = rvmAllocateObject(env, cls);
    if (o && CLASS_IS_FINALIZABLE(cls)) {
        rvmRegisterFinalizer(env, o);
    }
    return o;
}

Object* Java_org_robovm_rt_VM_newDirectByteBuffer(Env* env, Class* c, jlong address, jlong capacity) {
    return rvmNewDirectByteBuffer(env, LONG_TO_PTR(address), capacity);
}

void Java_org_robovm_rt_VM_memcpy(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    memcpy(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

void Java_org_robovm_rt_VM_memmove8(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    memmove(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

void Java_org_robovm_rt_VM_memmove16(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    rvmMoveMemory16(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

void Java_org_robovm_rt_VM_memmove32(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    rvmMoveMemory32(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) n);
}

void Java_org_robovm_rt_VM_memmove64(Env* env, Class* c, jlong s1, jlong s2, jlong n) {
    rvmMoveMemory32(LONG_TO_PTR(s1), LONG_TO_PTR(s2), (size_t) (n << 1));
}

void Java_org_robovm_rt_VM_memset(Env* env, Class* cls, jlong s, jbyte c, jlong n) {
    memset(LONG_TO_PTR(s), (unsigned char) c, (size_t) n);
}

jlong Java_org_robovm_rt_VM_getCallbackMethodImpl(Env* env, Class* c, Object* methodObject) {
    Method* method = getMethodFromMethodObject(env, methodObject);
    return PTR_TO_LONG(((CallbackMethod*) method)->callbackImpl);
}

void Java_org_robovm_rt_VM_bindBridgeMethod(Env* env, Class* c, Object* methodObject, jlong impl) {
    BridgeMethod* method = (BridgeMethod*) getMethodFromMethodObject(env, methodObject);
    if (!method) return;
    *method->targetFnPtr = LONG_TO_PTR(impl);
}

jboolean Java_org_robovm_rt_VM_isBridgeMethodBound(Env* env, Class* c, Object* methodObject) {
    BridgeMethod* method = (BridgeMethod*) getMethodFromMethodObject(env, methodObject);
    if (!method) return FALSE;
    return *method->targetFnPtr == NULL ? FALSE : TRUE;
}

jlong Java_org_robovm_rt_VM_getObjectAddress(Env* env, Class* c, Object* object) {
    return PTR_TO_LONG(object);
}
    
Object* Java_org_robovm_rt_VM_castAddressToObject(Env* env, Class* c, jlong address) {
    return (Object*) LONG_TO_PTR(address);
}

jlong Java_org_robovm_rt_VM_getFieldAddress(Env* env, Class* c, Object* fieldObject) {
    Field* field = (Field*) getFieldFromFieldObject(env, fieldObject);
    return PTR_TO_LONG(field);
}

jint Java_org_robovm_rt_VM_getInstanceFieldOffset(Env* env, Class* c, jlong fieldPtr) {
    InstanceField* field = (InstanceField*) LONG_TO_PTR(fieldPtr);
    return field->offset;
}

jlong Java_org_robovm_rt_VM_getClassFieldAddress(Env* env, Class* c, jlong fieldPtr) {
    ClassField* field = (ClassField*) LONG_TO_PTR(fieldPtr);
    return PTR_TO_LONG(field->address);
}
    
Object* Java_org_robovm_rt_VM_getObject(Env* env, Class* c, jlong address) {
    return *((Object**) LONG_TO_PTR(address));
}

jdouble Java_org_robovm_rt_VM_getDouble(Env* env, Class* c, jlong address) {
    return *((jdouble*) LONG_TO_PTR(address));
}

jfloat Java_org_robovm_rt_VM_getFloat(Env* env, Class* c, jlong address) {
    return *((jfloat*) LONG_TO_PTR(address));
}

jlong Java_org_robovm_rt_VM_getLong(Env* env, Class* c, jlong address) {
    return *((jlong*) LONG_TO_PTR(address));
}

jint Java_org_robovm_rt_VM_getInt(Env* env, Class* c, jlong address) {
    return *((jint*) LONG_TO_PTR(address));
}

jchar Java_org_robovm_rt_VM_getChar(Env* env, Class* c, jlong address) {
    return *((jchar*) LONG_TO_PTR(address));
}

jshort Java_org_robovm_rt_VM_getShort(Env* env, Class* c, jlong address) {
    return *((jshort*) LONG_TO_PTR(address));
}

jbyte Java_org_robovm_rt_VM_getByte(Env* env, Class* c, jlong address) {
    return *((jbyte*) LONG_TO_PTR(address));
}

jboolean Java_org_robovm_rt_VM_getBoolean(Env* env, Class* c, jlong address) {
    return *((jboolean*) LONG_TO_PTR(address));
}

void Java_org_robovm_rt_VM_setObject(Env* env, Class* c, jlong address, Object* value) {
    *((Object**) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setDouble(Env* env, Class* c, jlong address, jdouble value) {
    *((jdouble*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setFloat(Env* env, Class* c, jlong address, jfloat value) {
    *((jfloat*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setLong(Env* env, Class* c, jlong address, jlong value) {
    *((jlong*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setInt(Env* env, Class* c, jlong address, jint value) {
    *((jint*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setChar(Env* env, Class* c, jlong address, jchar value) {
    *((jchar*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setShort(Env* env, Class* c, jlong address, jshort value) {
    *((jshort*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setByte(Env* env, Class* c, jlong address, jbyte value) {
    *((jbyte*) LONG_TO_PTR(address)) = value;
}

void Java_org_robovm_rt_VM_setBoolean(Env* env, Class* c, jlong address, jboolean value) {
    *((jboolean*) LONG_TO_PTR(address)) = value;
}

jlong Java_org_robovm_rt_VM_getPointer(Env* env, Class* c, jlong address) {
    return PTR_TO_LONG(*((void**) LONG_TO_PTR(address)));
}

void Java_org_robovm_rt_VM_setPointer(Env* env, Class* c, jlong address, jlong value) {
    *((void**) LONG_TO_PTR(address)) = LONG_TO_PTR(value);
}

jlong Java_org_robovm_rt_VM_getStringUTFChars(Env* env, Class* c, Object* s) {
    return PTR_TO_LONG(rvmGetStringUTFChars(env, s));
}

Object* Java_org_robovm_rt_VM_newStringUTF(Env* env, Class* c, jlong address) {
    return rvmNewStringUTF(env, (char*) LONG_TO_PTR(address), -1);
}

Object* Java_org_robovm_rt_VM_newStringNoCopy(Env* env, Class* c, CharArray* value, jint offset, jint length) {
    return rvmNewStringNoCopy(env, value, offset, length);
}

jlong Java_org_robovm_rt_VM_getArrayValuesAddress(Env* env, Class* c, Array* array) {
    return PTR_TO_LONG(array->values);
}

BooleanArray* Java_org_robovm_rt_VM_newBooleanArray(Env* env, Class* c, jlong address, jint size) {
    BooleanArray* array = rvmNewBooleanArray(env, size);
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

ByteArray* Java_org_robovm_rt_VM_newByteArray(Env* env, Class* c, jlong address, jint size) {
    ByteArray* array = rvmNewByteArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jbyte));
    }
    return array;
}

CharArray* Java_org_robovm_rt_VM_newCharArray(Env* env, Class* c, jlong address, jint size) {
    CharArray* array = rvmNewCharArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jchar));
    }
    return array;
}

ShortArray* Java_org_robovm_rt_VM_newShortArray(Env* env, Class* c, jlong address, jint size) {
    ShortArray* array = rvmNewShortArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jshort));
    }
    return array;
}

IntArray* Java_org_robovm_rt_VM_newIntArray(Env* env, Class* c, jlong address, jint size) {
    IntArray* array = rvmNewIntArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jint));
    }
    return array;
}

LongArray* Java_org_robovm_rt_VM_newLongArray(Env* env, Class* c, jlong address, jint size) {
    LongArray* array = rvmNewLongArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jlong));
    }
    return array;
}

FloatArray* Java_org_robovm_rt_VM_newFloatArray(Env* env, Class* c, jlong address, jint size) {
    FloatArray* array = rvmNewFloatArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jfloat));
    }
    return array;
}

DoubleArray* Java_org_robovm_rt_VM_newDoubleArray(Env* env, Class* c, jlong address, jint size) {
    DoubleArray* array = rvmNewDoubleArray(env, size);
    if (array) {
        memcpy(array->values, LONG_TO_PTR(address), size * sizeof(jdouble));
    }
    return array;
}
