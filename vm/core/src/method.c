/*
 * Copyright (C) 2012 RoboVM AB
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
#include <unwind.h>
#include "private.h"
#include "utlist.h"

#define LOG_TAG "core.method"

// Line numbers greater or equal to this value is used
// for methods which have no line number info.
#define FIRST_NO_LINE_NUMBERS_LINE 0x00100000

DynamicLib* bootNativeLibs = NULL;
DynamicLib* mainNativeLibs = NULL;

static Mutex nativeLibsLock;
static Mutex threadStackTraceLock;
static jvalue emptyJValueArgs[1];
static Class* java_lang_StackTraceElement = NULL;
static Method* java_lang_StackTraceElement_constructor = NULL;
static ObjectArray* empty_java_lang_StackTraceElement_array = NULL;


// A shared CallStack struct used by rvmCaptureCallStackForThread() that can store at most MAX_CALL_STACK_LENGTH 
// frames. dumpThreadStackTrace() assumes MAX_CALL_STACK_LENGTH.
static CallStack* shared_callStack = NULL;

static inline void obtainNativeLibsLock() {
    rvmLockMutex(&nativeLibsLock);
}

static inline void releaseNativeLibsLock() {
    rvmUnlockMutex(&nativeLibsLock);
}

static inline void obtainThreadStackTraceLock() {
    rvmLockMutex(&threadStackTraceLock);
}

static inline void releaseThreadStackTraceLock() {
    rvmUnlockMutex(&threadStackTraceLock);
}

static Method* findMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = rvmGetMethods(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;
    for (; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return method;
        }
    }
    return NULL;
}

static Method* getMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    if (!strcmp("<init>", name) || !strcmp("<clinit>", name)) {
        // Constructors and static initializers are not inherited so we shouldn't check with the superclasses.
        return findMethod(env, clazz, name, desc);
    }

    Class* c = clazz;
    for (c = clazz; c != NULL; c = c->superclass) {
        Method* method = findMethod(env, c, name, desc);
        if (rvmExceptionCheck(env)) return NULL;
        if (method) return method;
    }

    /*
     * Check with interfaces.
     * TODO: Should we really do this? Does the JNI GetMethodID() function do this?
     */
    for (c = clazz; c != NULL; c = c->superclass) {
        Interface* interfaze = rvmGetInterfaces(env, c);
        if (rvmExceptionCheck(env)) return NULL;
        for (; interfaze != NULL; interfaze = interfaze->next) {
            Method* method = getMethod(env, interfaze->interfaze, name, desc);
            if (rvmExceptionCheck(env)) return NULL;
            if (method) return method;
        }
    }

    if (CLASS_IS_INTERFACE(clazz)) {
        /*
         * Class is an interface so check with java.lang.Object.
         * TODO: Should we really do this? Does the JNI GetMethodID() function do this?
         */
        return getMethod(env, java_lang_Object, name, desc);
    }

    return NULL;
}

jboolean rvmInitMethods(Env* env) {
    if (rvmInitMutex(&nativeLibsLock) != 0) {
        return FALSE;
    }
    if (rvmInitMutex(&threadStackTraceLock) != 0) {
        return FALSE;
    }
    java_lang_StackTraceElement = rvmFindClassUsingLoader(env, "java/lang/StackTraceElement", NULL);
    if (!java_lang_StackTraceElement) {
        return FALSE;
    }
    java_lang_StackTraceElement_constructor = rvmGetInstanceMethod(env, java_lang_StackTraceElement, "<init>", 
                                      "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V");
    if (!java_lang_StackTraceElement_constructor) {
        return FALSE;
    }
    empty_java_lang_StackTraceElement_array = rvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);
    if (!empty_java_lang_StackTraceElement_array) {
        return FALSE;
    }
    if (!rvmAddObjectGCRoot(env, (Object*) empty_java_lang_StackTraceElement_array)) {
        return FALSE;
    }

    return TRUE;
}

jboolean rvmHasMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = getMethod(env, clazz, name, desc);
    if (rvmExceptionCheck(env)) return FALSE;
    return method ? TRUE : FALSE;
}

Method* rvmGetMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = getMethod(env, clazz, name, desc);
    if (rvmExceptionCheck(env)) return NULL;
    if (!method) {
        rvmThrowNoSuchMethodError(env, name);
        return NULL;
    }
    return method;
}

Method* rvmGetClassMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = rvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (!METHOD_IS_STATIC(method)) {
        // TODO: JNI spec doesn't say anything about throwing this
        rvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* rvmGetClassInitializer(Env* env, Class* clazz) {
    return getMethod(env, clazz, "<clinit>", "()V");
}

Method* rvmGetInstanceMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = rvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (METHOD_IS_STATIC(method)) {
        // TODO: JNI spec doesn't say anything about throwing this
        rvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* rvmFindMethodAtAddress(Env* env, void* address) {
    Class* clazz = env->vm->options->findClassAt(env, address);
    if (!clazz) return NULL;
    Method* method = rvmGetMethods(env, clazz);
    if (rvmExceptionCheck(env)) return NULL;
    for (; method != NULL; method = method->next) {
        void* start = method->impl;
        void* end = start + method->size;
        if (start && address >= start && address < end) {
            return method;
        }
    }
    // TODO: We should never end up here
    return NULL;
}

static jboolean getCallingMethodIterator(Env* env, void* pc, void* fp, ProxyMethod* proxyMethod, void* data) {
    Method** result = data;

    Method* method = rvmFindMethodAtAddress(env, pc);
    if (method) {
        *result = method;
        return FALSE; // Stop iterating
    }
    return TRUE;
}

Method* rvmGetCallingMethod(Env* env) {
    Method* result = NULL;
    unwindIterateCallStack(env, NULL, getCallingMethodIterator, &result);
    return result;
}

static jboolean captureCallStackCountFramesIterator(Env* env, void* pc, void* fp, ProxyMethod* proxyMethod, void* data) {
    jint* countPtr = (jint*) data;
    *countPtr += 1;
    return *countPtr < MAX_CALL_STACK_LENGTH ? TRUE : FALSE;
}

typedef struct {
    CallStack* data;
    jint maxLength;
} CaptureCallStackArgs;

static jboolean captureCallStackIterator(Env* env, void* pc, void* fp, ProxyMethod* proxyMethod, void* _args) {
    CaptureCallStackArgs* args =  (CaptureCallStackArgs*) _args;
    CallStack* data = args->data;
    data->frames[data->length].pc = pc;
    data->frames[data->length].fp = fp;
    data->frames[data->length].method = (Method*) proxyMethod;
    data->length++;
    return data->length < args->maxLength ? TRUE : FALSE;
}

jint countCallStackFrames(Env* env, Frame* fp) {
    jint count = 0;
    unwindIterateCallStack(env, fp, captureCallStackCountFramesIterator, &count);
    return count;
}

CallStack* allocateCallStackFrames(Env* env, jint maxLength) {
    return rvmAllocateMemoryAtomic(env, sizeof(CallStack) + sizeof(CallStackFrame) * maxLength);
}

void captureCallStack(Env* env, Frame* fp, CallStack* data, jint maxLength) {
    CaptureCallStackArgs args = {data, maxLength};
    unwindIterateCallStack(env, fp, captureCallStackIterator, &args);
}

CallStack* captureCallStackFromFrame(Env* env, Frame* fp) {
    jint count = countCallStackFrames(env, fp);
    if (rvmExceptionOccurred(env)) return NULL;
    CallStack* data = allocateCallStackFrames(env, count);
    if (!data) return NULL;
    captureCallStack(env, fp, data, count);
    if (rvmExceptionOccurred(env)) return NULL;
    return data;
}

CallStack* rvmCaptureCallStack(Env* env) {
    return captureCallStackFromFrame(env, NULL);
}

CallStack* rvmCaptureCallStackForThread(Env* env, Thread* thread) {
    if (thread == env->currentThread) {
        return rvmCaptureCallStack(env);
    }

    // dumpThreadStackTrace() must not be called concurrently
    obtainThreadStackTraceLock();
    
    if (!shared_callStack) {
        shared_callStack = rvmAllocateMemoryAtomicUncollectable(env, sizeof(CallStack) + sizeof(CallStackFrame) * MAX_CALL_STACK_LENGTH);
        if (!shared_callStack) {
            releaseThreadStackTraceLock();
            return NULL;
        }
    }

    memset(shared_callStack, 0, sizeof(CallStack) + sizeof(CallStackFrame) * MAX_CALL_STACK_LENGTH);
    dumpThreadStackTrace(env, thread, shared_callStack);
    if (rvmExceptionOccurred(env)) {
        releaseThreadStackTraceLock();
        return NULL;
    }

    // Make a copy of the CallStack that is just big enough
    CallStack* copy = allocateCallStackFrames(env, shared_callStack->length);
    if (!copy) {
        releaseThreadStackTraceLock();
        return NULL;
    }
    memcpy(copy, shared_callStack, sizeof(CallStack) + sizeof(CallStackFrame) * shared_callStack->length);

    releaseThreadStackTraceLock();

    return copy;
}

static inline jint getLineTableEntryB(uint8_t* table, jint index) {
    return table[index];
}
static inline jint getLineTableEntryS(uint16_t* table, jint index) {
    return table[index];
}
static inline jint getLineTableEntryI(jint* table, jint index) {
    return table[index];
}
static inline jint getLineTableEntry(void* table, jint entrySize, jint index) {
    if (entrySize == 1) {
        return getLineTableEntryB((uint8_t*) table, index);
    } else if (entrySize == 2) {
        return getLineTableEntryS((uint16_t*) table, index);
    }
    return getLineTableEntryI((jint*) table, index);
}

static jint getLinesIndex(void* addressOffsets, jint addressOffsetSize, jint size, jint frameOffset) {
    for (jint i = 0; i < size; i++) {
        jint entry = getLineTableEntry(addressOffsets, addressOffsetSize, i);
        if (frameOffset < entry) {
            return i - 1;
        }
    }
    return size - 1;
}

static jint getLineNumber(CallStackFrame* frame) {
    if (!frame->method || !frame->method->linetable) {
        return -1;
    }
    uint32_t* linetable = (uint32_t*) frame->method->linetable;
    if (*linetable == 0xffffffff) {
        return -1;
    }
    jint size = *linetable & 0xfffffff;
    if (size == 0) {
        return -1;
    }
    jint frameOffset = frame->pc - frame->method->impl;
    jint addressOffsetSize = ((*linetable >> 30) & 0x3) + 1;
    jint lineOffsetSize = ((*linetable >> 28) & 0x3) + 1;
    linetable++;
    jint firstLineNumber = *linetable;
    if (firstLineNumber >= FIRST_NO_LINE_NUMBERS_LINE) {
        return -1;
    }
    linetable++;

    jint index = getLinesIndex(linetable, addressOffsetSize, size, frameOffset);
    if (index == -1) {
        return firstLineNumber;
    }

    uint8_t* lineOffsets = ((uint8_t*) linetable) + size * addressOffsetSize;
    // Adjust lineOffsets for proper alignment
    lineOffsets += (lineOffsetSize - ((ptrdiff_t) lineOffsets & (lineOffsetSize - 1))) & (lineOffsetSize - 1);
    return firstLineNumber + getLineTableEntry(lineOffsets, lineOffsetSize, index);
}

CallStackFrame* rvmResolveCallStackFrame(Env* env, CallStackFrame* frame) {
    if (frame->pc == NULL && frame->method == NULL) {
        // We've already tried to resolve this frame but 
        // it doesn't correspond to any method
        return NULL;
    }
    if (frame->method != NULL) {
        // We've already resolved this frame successfully or
        // the method is a ProxyMethod so no call to rvmFindMethodAtAddress()
        // is required
        return frame;
    }
    frame->method = rvmFindMethodAtAddress(env, frame->pc);
    if (!frame->method) {
        frame->pc = NULL;
        return NULL;
    }
    frame->lineNumber = METHOD_IS_NATIVE(frame->method) ? -2 : getLineNumber(frame);
    return frame;
}

ObjectArray* rvmCallStackToStackTraceElements(Env* env, CallStack* callStack, jint first) {
    if (!callStack || callStack->length == 0) {
        return empty_java_lang_StackTraceElement_array;
    }

    // Count the number of methods
    jint index = first;
    jint length = 0;
    while (rvmGetNextCallStackMethod(env, callStack, &index)) {
        length++;
    }

    if (length == 0) {
        return empty_java_lang_StackTraceElement_array;
    }

    ObjectArray* array = rvmNewObjectArray(env, length, java_lang_StackTraceElement, NULL, NULL);
    if (!array) return NULL;

    if (length > 0) {
        jvalue args[4];
        index = first;
        jint i;
        for (i = 0; i < length; i++) {
            CallStackFrame* frame = rvmGetNextCallStackMethod(env, callStack, &index);
            Method* m = frame->method;
            args[0].l = (jobject) m->clazz;
            args[1].l = (jobject) rvmNewStringUTF(env, m->name, -1);
            if (!args[1].l) return NULL;
            args[2].l = (jobject) rvmAttributeGetClassSourceFile(env, m->clazz);
            if (rvmExceptionOccurred(env)) {
                return NULL;
            }
            args[3].i = frame->lineNumber;
            array->values[i] = rvmNewObjectA(env, java_lang_StackTraceElement, 
                java_lang_StackTraceElement_constructor, args);
            if (!array->values[i]) return NULL;
        }
    }

    return array;
}

const char* rvmGetReturnType(const char* desc) {
    while (*desc != ')') desc++;
    desc++;
    return desc;
}

const char* rvmGetNextParameterType(const char** desc) {
    const char* s = *desc;
    (*desc)++;
    switch (s[0]) {
    case 'B':
    case 'Z':
    case 'S':
    case 'C':
    case 'I':
    case 'J':
    case 'F':
    case 'D':
        return s;
    case '[':
        rvmGetNextParameterType(desc);
        return s;
    case 'L':
        while (**desc != ';') (*desc)++;
        (*desc)++;
        return s;
    case '(':
        return rvmGetNextParameterType(desc);
    }
    return 0;
}

jint rvmGetParameterCount(Method* method) {
    const char* desc = method->desc;
    jint count = 0;
    while (rvmGetNextParameterType(&desc)) {
        count++;
    }
    return count;
}

typedef struct {
    jint ptrArgsCount, intArgsCount, longArgsCount, floatArgsCount, doubleArgsCount;
} ArgsCount;

static void countArgs(Env* env, Method* method, ArgsCount* argsCount) {
    jint ptrArgsCount = 0, intArgsCount = 0, longArgsCount = 0, floatArgsCount = 0, doubleArgsCount = 0;
    ptrArgsCount = 1; // First arg is always the Env*
    if (!(method->access & ACC_STATIC)) {
        // Non-static methods takes the receiver object (this) as arg 2
        ptrArgsCount++;
    }    

    const char* desc = method->desc;
    const char* c;
    while ((c = rvmGetNextParameterType(&desc))) {
        switch (c[0]) {
        case 'Z':
        case 'B':
        case 'S':
        case 'C':
        case 'I':
            intArgsCount++;
            break;
        case 'J':
            longArgsCount++;
            break;
        case 'F':
            floatArgsCount++;
            break;
        case 'D':
            doubleArgsCount++;
            break;
        case 'L':
        case '[':
            ptrArgsCount++;
            break;
        }
    }

    argsCount->ptrArgsCount = ptrArgsCount;
    argsCount->intArgsCount = intArgsCount;
    argsCount->longArgsCount = longArgsCount;
    argsCount->floatArgsCount = floatArgsCount;
    argsCount->doubleArgsCount = doubleArgsCount;
}

static void setArgs(Env* env, Object* obj, Method* method, CallInfo* callInfo, jvalue* args) {
    call0AddPtr(callInfo, env);
    if (!(method->access & ACC_STATIC)) {
        call0AddPtr(callInfo, obj);
    }    

    const char* desc = method->desc;
    const char* c;
    jint i = 0;
    while ((c = rvmGetNextParameterType(&desc))) {
        switch (c[0]) {
        case 'Z':
            call0AddInt(callInfo, (jint) args[i++].z);
            break;
        case 'B':
            call0AddInt(callInfo, (jint) args[i++].b);
            break;
        case 'S':
            call0AddInt(callInfo, (jint) args[i++].s);
            break;
        case 'C':
            call0AddInt(callInfo, (jint) args[i++].c);
            break;
        case 'I':
            call0AddInt(callInfo, args[i++].i);
            break;
        case 'J':
            call0AddLong(callInfo, args[i++].j);
            break;
        case 'F':
            call0AddFloat(callInfo, args[i++].f);
            break;
        case 'D':
            call0AddDouble(callInfo, args[i++].d);
            break;
        case 'L':
        case '[':
            call0AddPtr(callInfo, args[i++].l);
            break;
        }
    }
}

#define /* CallInfo* */ INIT_CALL_INFO(/* Env* */ _env, /* Object* */ _obj, /* Method* */ _method, /* jboolean */ _virtual, /* jvalue* */ _args) ({ \
    CallInfo* _callInfo = NULL; \
    if (_virtual && !(_method->access & ACC_PRIVATE)) { \
        /* Lookup the real method to be invoked */ \
        _method = rvmGetMethod(_env, ((Object*) _obj)->clazz, _method->name, _method->desc); \
    } \
    if (_method) { \
        ArgsCount _argsCount; \
        countArgs(_env, _method, &_argsCount); \
        void* _function = _method->synchronizedImpl ? _method->synchronizedImpl : _method->impl; \
        _callInfo = CALL0_ALLOCATE_CALL_INFO(_env, _function, _argsCount.ptrArgsCount, _argsCount.intArgsCount, _argsCount.longArgsCount, _argsCount.floatArgsCount, _argsCount.doubleArgsCount); \
        setArgs(_env, _obj, _method, _callInfo, _args); \
    } \
    _callInfo; \
})

static jvalue* va_list2jargs(Env* env, Method* method, va_list args) {
    jint argsCount = rvmGetParameterCount(method);

    if (argsCount == 0) {
        return emptyJValueArgs;
    }

    jvalue *jvalueArgs = (jvalue*) rvmAllocateMemory(env, sizeof(jvalue) * argsCount);
    if (!jvalueArgs) return NULL;

    const char* desc = method->desc;
    const char* c;
    jint i = 0;
    while ((c = rvmGetNextParameterType(&desc))) {
        switch (c[0]) {
        case 'B':
            jvalueArgs[i++].b = (jbyte) va_arg(args, jint);
            break;
        case 'Z':
            jvalueArgs[i++].z = (jboolean) va_arg(args, jint);
            break;
        case 'S':
            jvalueArgs[i++].s = (jshort) va_arg(args, jint);
            break;
        case 'C':
            jvalueArgs[i++].c = (jchar) va_arg(args, jint);
            break;
        case 'I':
            jvalueArgs[i++].i = va_arg(args, jint);
            break;
        case 'J':
            jvalueArgs[i++].j = va_arg(args, jlong);
            break;
        case 'F':
            jvalueArgs[i++].f = (jfloat) va_arg(args, jdouble);
            break;
        case 'D':
            jvalueArgs[i++].d = va_arg(args, jdouble);
            break;
        case '[':
        case 'L':
            jvalueArgs[i++].l = va_arg(args, jobject);
            break;
        }
    }

    return jvalueArgs;
}

static void callVoidMethod(Env* env, CallInfo* callInfo) {
    void (*f)(CallInfo*) = _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
}

static Object* callObjectMethod(Env* env, CallInfo* callInfo) {
    Object* result = NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jboolean callBooleanMethod(Env* env, CallInfo* callInfo) {
    jboolean result = FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jbyte callByteMethod(Env* env, CallInfo* callInfo) {
    jbyte result = 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jchar callCharMethod(Env* env, CallInfo* callInfo) {
    jchar result = 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jshort callShortMethod(Env* env, CallInfo* callInfo) {
    jshort result = 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jint callIntMethod(Env* env, CallInfo* callInfo) {
    jint result = 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jlong callLongMethod(Env* env, CallInfo* callInfo) {
    jlong result = 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jfloat callFloatMethod(Env* env, CallInfo* callInfo) {
    jfloat result = 0;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

static jdouble callDoubleMethod(Env* env, CallInfo* callInfo) {
    jdouble result = 0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _call0;
    rvmPushGatewayFrame(env);
    TrycatchContext tc = {0};
    tc.sel = CATCH_ALL_SEL;
    if (!rvmTrycatchEnter(env, &tc)) {
        result = f(callInfo);
    }
    rvmTrycatchLeave(env);
    rvmPopGatewayFrame(env);
    return result;
}

void rvmCallVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    callVoidMethod(env, callInfo);
}

void rvmCallVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    rvmCallVoidInstanceMethodA(env, obj, method, jargs);
}

void rvmCallVoidInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    rvmCallVoidInstanceMethodV(env, obj, method, args);
}

Object* rvmCallObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return NULL;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callObjectMethod(env, callInfo);
}

Object* rvmCallObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return rvmCallObjectInstanceMethodA(env, obj, method, jargs);
}

Object* rvmCallObjectInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallObjectInstanceMethodV(env, obj, method, args);
}

jboolean rvmCallBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return FALSE;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callBooleanMethod(env, callInfo);
}

jboolean rvmCallBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return rvmCallBooleanInstanceMethodA(env, obj, method, jargs);
}

jboolean rvmCallBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallBooleanInstanceMethodV(env, obj, method, args);
}

jbyte rvmCallByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callByteMethod(env, callInfo);
}

jbyte rvmCallByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallByteInstanceMethodA(env, obj, method, jargs);
}

jbyte rvmCallByteInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallByteInstanceMethodV(env, obj, method, args);
}

jchar rvmCallCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callCharMethod(env, callInfo);
}

jchar rvmCallCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallCharInstanceMethodA(env, obj, method, jargs);
}

jchar rvmCallCharInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallCharInstanceMethodV(env, obj, method, args);
}

jshort rvmCallShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callShortMethod(env, callInfo);
}

jshort rvmCallShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallShortInstanceMethodA(env, obj, method, jargs);
}

jshort rvmCallShortInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallShortInstanceMethodV(env, obj, method, args);
}

jint rvmCallIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callIntMethod(env, callInfo);
}

jint rvmCallIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallIntInstanceMethodA(env, obj, method, jargs);
}

jint rvmCallIntInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallIntInstanceMethodV(env, obj, method, args);
}

jlong rvmCallLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callLongMethod(env, callInfo);
}

jlong rvmCallLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallLongInstanceMethodA(env, obj, method, jargs);
}

jlong rvmCallLongInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallLongInstanceMethodV(env, obj, method, args);
}

jfloat rvmCallFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0.0f;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callFloatMethod(env, callInfo);
}

jfloat rvmCallFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return rvmCallFloatInstanceMethodA(env, obj, method, jargs);
}

jfloat rvmCallFloatInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallFloatInstanceMethodV(env, obj, method, args);
}

jdouble rvmCallDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, TRUE, args);
    if (!callInfo) return 0.0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callDoubleMethod(env, callInfo);
}

jdouble rvmCallDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return rvmCallDoubleInstanceMethodA(env, obj, method, jargs);
}

jdouble rvmCallDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallDoubleInstanceMethodV(env, obj, method, args);
}

void rvmCallNonvirtualVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    callVoidMethod(env, callInfo);
}

void rvmCallNonvirtualVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    rvmCallNonvirtualVoidInstanceMethodA(env, obj, method, jargs);
}

void rvmCallNonvirtualVoidInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    rvmCallNonvirtualVoidInstanceMethodV(env, obj, method, args);
}

Object* rvmCallNonvirtualObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return NULL;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callObjectMethod(env, callInfo);
}

Object* rvmCallNonvirtualObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return rvmCallNonvirtualObjectInstanceMethodA(env, obj, method, jargs);
}

Object* rvmCallNonvirtualObjectInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualObjectInstanceMethodV(env, obj, method, args);
}

jboolean rvmCallNonvirtualBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return FALSE;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callBooleanMethod(env, callInfo);
}

jboolean rvmCallNonvirtualBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return rvmCallNonvirtualBooleanInstanceMethodA(env, obj, method, jargs);
}

jboolean rvmCallNonvirtualBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualBooleanInstanceMethodV(env, obj, method, args);
}

jbyte rvmCallNonvirtualByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callByteMethod(env, callInfo);
}

jbyte rvmCallNonvirtualByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallNonvirtualByteInstanceMethodA(env, obj, method, jargs);
}

jbyte rvmCallNonvirtualByteInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualByteInstanceMethodV(env, obj, method, args);
}

jchar rvmCallNonvirtualCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callCharMethod(env, callInfo);
}

jchar rvmCallNonvirtualCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallNonvirtualCharInstanceMethodA(env, obj, method, jargs);
}

jchar rvmCallNonvirtualCharInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualCharInstanceMethodV(env, obj, method, args);
}

jshort rvmCallNonvirtualShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callShortMethod(env, callInfo);
}

jshort rvmCallNonvirtualShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallNonvirtualShortInstanceMethodA(env, obj, method, jargs);
}

jshort rvmCallNonvirtualShortInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualShortInstanceMethodV(env, obj, method, args);
}

jint rvmCallNonvirtualIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callIntMethod(env, callInfo);
}

jint rvmCallNonvirtualIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallNonvirtualIntInstanceMethodA(env, obj, method, jargs);
}

jint rvmCallNonvirtualIntInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualIntInstanceMethodV(env, obj, method, args);
}

jlong rvmCallNonvirtualLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callLongMethod(env, callInfo);
}

jlong rvmCallNonvirtualLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallNonvirtualLongInstanceMethodA(env, obj, method, jargs);
}

jlong rvmCallNonvirtualLongInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualLongInstanceMethodV(env, obj, method, args);
}

jfloat rvmCallNonvirtualFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0.0f;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callFloatMethod(env, callInfo);
}

jfloat rvmCallNonvirtualFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return rvmCallNonvirtualFloatInstanceMethodA(env, obj, method, jargs);
}

jfloat rvmCallNonvirtualFloatInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualFloatInstanceMethodV(env, obj, method, args);
}

jdouble rvmCallNonvirtualDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, obj, method, FALSE, args);
    if (!callInfo) return 0.0;
    if (obj && CLASS_IS_PROXY(obj->clazz)) {
        env->reserved0 = (void*) method->name;
        env->reserved1 = (void*) method->desc;
    }
    return callDoubleMethod(env, callInfo);
}

jdouble rvmCallNonvirtualDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return rvmCallNonvirtualDoubleInstanceMethodA(env, obj, method, jargs);
}

jdouble rvmCallNonvirtualDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallNonvirtualDoubleInstanceMethodV(env, obj, method, args);
}

void rvmCallVoidClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return;
    callVoidMethod(env, callInfo);
}

void rvmCallVoidClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    rvmCallVoidClassMethodA(env, clazz, method, jargs);
}

void rvmCallVoidClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    rvmCallVoidClassMethodV(env, clazz, method, args);
}

Object* rvmCallObjectClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return NULL;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return NULL;
    return callObjectMethod(env, callInfo);
}

Object* rvmCallObjectClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return rvmCallObjectClassMethodA(env, clazz, method, jargs);
}

Object* rvmCallObjectClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallObjectClassMethodV(env, clazz, method, args);
}

jboolean rvmCallBooleanClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return FALSE;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return FALSE;
    return callBooleanMethod(env, callInfo);
}

jboolean rvmCallBooleanClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return rvmCallBooleanClassMethodA(env, clazz, method, jargs);
}

jboolean rvmCallBooleanClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallBooleanClassMethodV(env, clazz, method, args);
}

jbyte rvmCallByteClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0;
    return callByteMethod(env, callInfo);
}

jbyte rvmCallByteClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallByteClassMethodA(env, clazz, method, jargs);
}

jbyte rvmCallByteClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallByteClassMethodV(env, clazz, method, args);
}

jchar rvmCallCharClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0;
    return callCharMethod(env, callInfo);
}

jchar rvmCallCharClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallCharClassMethodA(env, clazz, method, jargs);
}

jchar rvmCallCharClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallCharClassMethodV(env, clazz, method, args);
}

jshort rvmCallShortClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0;
    return callShortMethod(env, callInfo);
}

jshort rvmCallShortClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallShortClassMethodA(env, clazz, method, jargs);
}

jshort rvmCallShortClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallShortClassMethodV(env, clazz, method, args);
}

jint rvmCallIntClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0;
    return callIntMethod(env, callInfo);
}

jint rvmCallIntClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallIntClassMethodA(env, clazz, method, jargs);
}

jint rvmCallIntClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallIntClassMethodV(env, clazz, method, args);
}

jlong rvmCallLongClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0;
    return callLongMethod(env, callInfo);
}

jlong rvmCallLongClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return rvmCallLongClassMethodA(env, clazz, method, jargs);
}

jlong rvmCallLongClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallLongClassMethodV(env, clazz, method, args);
}

jfloat rvmCallFloatClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0.0f;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0.0f;
    return callFloatMethod(env, callInfo);
}

jfloat rvmCallFloatClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return rvmCallFloatClassMethodA(env, clazz, method, jargs);
}

jfloat rvmCallFloatClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallFloatClassMethodV(env, clazz, method, args);
}

jdouble rvmCallDoubleClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = INIT_CALL_INFO(env, NULL, method, FALSE, args);
    if (!callInfo) return 0.0;
    rvmInitialize(env, method->clazz);
    if (rvmExceptionOccurred(env)) return 0.0;
    return callDoubleMethod(env, callInfo);
}

jdouble rvmCallDoubleClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return rvmCallDoubleClassMethodA(env, clazz, method, jargs);
}

jdouble rvmCallDoubleClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return rvmCallDoubleClassMethodV(env, clazz, method, args);
}

jboolean rvmRegisterNative(Env* env, NativeMethod* method, void* impl) {
    method->nativeImpl = impl;
    return TRUE;
}

jboolean rvmUnregisterNative(Env* env, NativeMethod* method) {
    method->nativeImpl = NULL;
    return TRUE;
}

void* rvmResolveNativeMethodImpl(Env* env, NativeMethod* method, const char* shortMangledName, const char* longMangledName, ClassLoader* classLoader, void** ptr) {
    void* f = method->nativeImpl;
    if (!f) {
        DynamicLib* nativeLibs = NULL;
        if (!classLoader || classLoader->parent == NULL) {
            // This is the bootstrap classloader
            nativeLibs = bootNativeLibs;
        } else if (classLoader->parent->parent == NULL && classLoader->object.clazz->classLoader == NULL) {
            // This is the system classloader
            nativeLibs = mainNativeLibs;
        } else {
            // Unknown classloader
            rvmThrowUnsatisfiedLinkError(env, "Unknown classloader");
            return NULL;
        }

        obtainNativeLibsLock();

        TRACEF("Searching for native method using short name: %s", shortMangledName);
        f = rvmFindDynamicLibSymbol(env, nativeLibs, shortMangledName, TRUE);
        if (f) {
            TRACEF("Found native method using short name: %s", shortMangledName);
        } else if (strcmp(shortMangledName, longMangledName)) {
            TRACEF("Searching for native method using long name: %s", longMangledName);
            f = rvmFindDynamicLibSymbol(env, nativeLibs, longMangledName, TRUE);
            if (f) {
                TRACEF("Found native method using long name: %s", longMangledName);
            }
        }

        method->nativeImpl = f;

        releaseNativeLibsLock();
    }

    if (!f) {
        char* className = rvmToBinaryClassName(env, method->method.clazz->name);
        if (className) {
            rvmThrowNewf(env, java_lang_UnsatisfiedLinkError, "%s.%s%s", className, method->method.name, method->method.desc);
        }
        return NULL;
    }
    // TODO: Remember ptr to allow it to be reset when the JNI RegisterNatives/UnregisterNatives functions are called
    *ptr = f;
    return f;
}


jboolean rvmLoadNativeLibrary(Env* env, const char* path, ClassLoader* classLoader) {
    DynamicLib** nativeLibs = NULL;
    if (!classLoader || classLoader->parent == NULL) {
        // This is the bootstrap classloader
        nativeLibs = &bootNativeLibs;
    } else if (classLoader->parent->parent == NULL && classLoader->object.clazz->classLoader == NULL) {
        // This is the system classloader
        nativeLibs = &mainNativeLibs;
    } else {
        // Unknown classloader
        rvmThrowUnsatisfiedLinkError(env, "Unknown classloader");
        return FALSE;
    }

    char* errorMsg = NULL;
    DynamicLib* lib = rvmOpenDynamicLib(env, path, &errorMsg);
    if (!lib) {
        if (!rvmExceptionOccurred(env)) {
            rvmThrowUnsatisfiedLinkError(env, errorMsg);
        }
        return FALSE;
    }

    obtainNativeLibsLock();

    if (rvmHasDynamicLib(env, lib, *nativeLibs)) {
        // The lib is already in nativeLibs
        rvmCloseDynamicLib(env, lib);
        releaseNativeLibsLock();
        return TRUE;
    }

    jint (*JNI_OnLoad)(JavaVM*, void*) = rvmFindDynamicLibSymbol(env, lib, "JNI_OnLoad", FALSE);
    if (JNI_OnLoad) {
        // TODO: Check that JNI_OnLoad returns a supported JNI version?
        JNI_OnLoad(&env->vm->javaVM, NULL);
        if (rvmExceptionOccurred(env)) {
            releaseNativeLibsLock();
            return FALSE;
        }
    }

    rvmAddDynamicLib(env, lib, nativeLibs);

    releaseNativeLibsLock();

    return TRUE;
}

