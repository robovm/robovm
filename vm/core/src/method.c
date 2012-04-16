#include <nullvm.h>
#include <string.h>
#include <unwind.h>
#include <hythread.h>
#include "private.h"
#include "log.h"
#include "utlist.h"

DynamicLib* bootNativeLibs = NULL;
DynamicLib* mainNativeLibs = NULL;

static hythread_monitor_t nativeLibsLock;
static jvalue emptyJValueArgs[1];

static inline void obtainNativeLibsLock() {
    hythread_monitor_enter(nativeLibsLock);
}

static inline void releaseNativeLibsLock() {
    hythread_monitor_exit(nativeLibsLock);
}

static Method* findMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = nvmGetMethods(env, clazz);
    if (nvmExceptionCheck(env)) return NULL;
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
        if (nvmExceptionCheck(env)) return NULL;
        if (method) return method;
    }

    /*
     * Check with interfaces.
     * TODO: Should we really do this? Does the JNI GetMethodID() function do this?
     */
    for (c = clazz; c != NULL; c = c->superclass) {
        Interface* interface = nvmGetInterfaces(env, c);
        if (nvmExceptionCheck(env)) return NULL;
        for (; interface != NULL; interface = interface->next) {
            Method* method = getMethod(env, interface->interface, name, desc);
            if (nvmExceptionCheck(env)) return NULL;
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

jboolean nvmInitMethods(Env* env) {
    if (hythread_monitor_init_with_name(&nativeLibsLock, 0, NULL) < 0) {
        return FALSE;
    }
    return TRUE;
}

jboolean nvmHasMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = getMethod(env, clazz, name, desc);
    if (nvmExceptionCheck(env)) return FALSE;
    return method ? TRUE : FALSE;
}

Method* nvmGetMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = getMethod(env, clazz, name, desc);
    if (nvmExceptionCheck(env)) return NULL;
    if (!method) {
        nvmThrowNoSuchMethodError(env, name);
        return NULL;
    }
    return method;
}

Method* nvmGetClassMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (!METHOD_IS_STATIC(method)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* nvmGetClassInitializer(Env* env, Class* clazz) {
    return getMethod(env, clazz, "<clinit>", "()V");
}

Method* nvmGetInstanceMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (METHOD_IS_STATIC(method)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* nvmFindMethodAtAddress(Env* env, void* address) {
    Class* clazz = env->vm->options->findClassAt(env, address);
    if (!clazz) return NULL;
    Method* method = nvmGetMethods(env, clazz);
    if (nvmExceptionCheck(env)) return NULL;
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

static jboolean getCallingMethodIterator(Env* env, void* pc, ProxyMethod* proxyMethod, void* data) {
    Method** result = data;

    Method* method = nvmFindMethodAtAddress(env, pc);
    if (method) {
        *result = method;
        return FALSE; // Stop iterating
    }
    return TRUE;
}

static jboolean getCallStackIterator(Env* env, void* pc, ProxyMethod* proxyMethod, void* data) {
    CallStackEntry** head = (CallStackEntry**) data;
    Method* method = NULL;
    if (proxyMethod) {
        method = (Method*) proxyMethod;
    } else {
        method = nvmFindMethodAtAddress(env, pc);
    }
    if (method) {
        CallStackEntry* entry = nvmAllocateMemory(env, sizeof(CallStackEntry));
        if (!entry) {
            return FALSE; // Stop iterating
        }
        entry->method = method;
        entry->offset = proxyMethod ? 0 : pc - method->impl;
        DL_APPEND(*head, entry);
    }
    return TRUE;
}

Method* nvmGetCallingMethod(Env* env) {
    Method* result = NULL;
    unwindIterateCallStack(env, getCallingMethodIterator, &result);
    return result;
}

CallStackEntry* nvmGetCallStack(Env* env) {
    CallStackEntry* data = NULL;
    unwindIterateCallStack(env, getCallStackIterator, &data);
    if (nvmExceptionOccurred(env)) return NULL;
    return data;
}

const char* nvmGetReturnType(const char* desc) {
    while (*desc != ')') desc++;
    desc++;
    return desc;
}

const char* nvmGetNextParameterType(const char** desc) {
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
        nvmGetNextParameterType(desc);
        return s;
    case 'L':
        while (**desc != ';') (*desc)++;
        (*desc)++;
        return s;
    case '(':
        return nvmGetNextParameterType(desc);
    }
    return 0;
}

jint nvmGetParameterCount(Method* method) {
    const char* desc = method->desc;
    jint count = 0;
    while (nvmGetNextParameterType(&desc)) {
        count++;
    }
    return count;
}

static inline jboolean isFpType(char type) {
    return type == 'F' || type == 'D';
}

CallInfo* initCallInfo(Env* env, Object* obj, Method* method, jboolean virtual, jvalue* args) {
    if (virtual && !(method->access & ACC_PRIVATE)) {
        // Lookup the real method to be invoked
        method = nvmGetMethod(env, obj->clazz, method->name, method->desc);
        if (!method) return NULL;
    }

    jint ptrArgsCount = 0, intArgsCount = 0, longArgsCount = 0, floatArgsCount = 0, doubleArgsCount = 0;

    ptrArgsCount = 1; // First arg is always the Env*
    if (!(method->access & ACC_STATIC)) {
        // Non-static methods takes the receiver object (this) as arg 2
        ptrArgsCount++;
    }    

    const char* desc = method->desc;
    const char* c;
    while ((c = nvmGetNextParameterType(&desc))) {
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

    void* function = method->synchronizedImpl ? method->synchronizedImpl : method->impl;

    CallInfo* callInfo = call0AllocateCallInfo(env, function, ptrArgsCount, intArgsCount, longArgsCount, floatArgsCount, doubleArgsCount);
    if (!callInfo) return NULL;

    call0AddPtr(callInfo, env);
    if (!(method->access & ACC_STATIC)) {
        call0AddPtr(callInfo, obj);
    }    

    desc = method->desc;
    jint i = 0;
    while ((c = nvmGetNextParameterType(&desc))) {
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

    return callInfo;
}

static jvalue* va_list2jargs(Env* env, Method* method, va_list args) {
    jint argsCount = nvmGetParameterCount(method);

    if (argsCount == 0) {
        return emptyJValueArgs;
    }

    jvalue *jvalueArgs = (jvalue*) nvmAllocateMemory(env, sizeof(jvalue) * argsCount);
    if (!jvalueArgs) return NULL;

    const char* desc = method->desc;
    const char* c;
    jint i = 0;
    while ((c = nvmGetNextParameterType(&desc))) {
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

void nvmCallVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return;
    void (*f)(CallInfo*) = _call0;
    nvmPushGatewayFrame(env);
    f(callInfo);
    nvmPopGatewayFrame(env);
}

void nvmCallVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    nvmCallVoidInstanceMethodA(env, obj, method, jargs);
}

void nvmCallVoidInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    nvmCallVoidInstanceMethodV(env, obj, method, args);
}

Object* nvmCallObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    Object* result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

Object* nvmCallObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return nvmCallObjectInstanceMethodA(env, obj, method, jargs);
}

Object* nvmCallObjectInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallObjectInstanceMethodV(env, obj, method, args);
}

jboolean nvmCallBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jboolean result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jboolean nvmCallBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return nvmCallBooleanInstanceMethodA(env, obj, method, jargs);
}

jboolean nvmCallBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallBooleanInstanceMethodV(env, obj, method, args);
}

jbyte nvmCallByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jbyte result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jbyte nvmCallByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallByteInstanceMethodA(env, obj, method, jargs);
}

jbyte nvmCallByteInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallByteInstanceMethodV(env, obj, method, args);
}

jchar nvmCallCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jchar result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jchar nvmCallCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallCharInstanceMethodA(env, obj, method, jargs);
}

jchar nvmCallCharInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallCharInstanceMethodV(env, obj, method, args);
}

jshort nvmCallShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jshort result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jshort nvmCallShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallShortInstanceMethodA(env, obj, method, jargs);
}

jshort nvmCallShortInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallShortInstanceMethodV(env, obj, method, args);
}

jint nvmCallIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jint result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jint nvmCallIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallIntInstanceMethodA(env, obj, method, jargs);
}

jint nvmCallIntInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallIntInstanceMethodV(env, obj, method, args);
}

jlong nvmCallLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jlong result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jlong nvmCallLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallLongInstanceMethodA(env, obj, method, jargs);
}

jlong nvmCallLongInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallLongInstanceMethodV(env, obj, method, args);
}

jfloat nvmCallFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jfloat result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jfloat nvmCallFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return nvmCallFloatInstanceMethodA(env, obj, method, jargs);
}

jfloat nvmCallFloatInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallFloatInstanceMethodV(env, obj, method, args);
}

jdouble nvmCallDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, TRUE, args);
    if (!callInfo) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jdouble result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jdouble nvmCallDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return nvmCallDoubleInstanceMethodA(env, obj, method, jargs);
}

jdouble nvmCallDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallDoubleInstanceMethodV(env, obj, method, args);
}

void nvmCallNonvirtualVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return;
    void (*f)(CallInfo*) = _call0;
    nvmPushGatewayFrame(env);
    f(callInfo);
    nvmPopGatewayFrame(env);
}

void nvmCallNonvirtualVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    nvmCallNonvirtualVoidInstanceMethodA(env, obj, method, jargs);
}

void nvmCallNonvirtualVoidInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    nvmCallNonvirtualVoidInstanceMethodV(env, obj, method, args);
}

Object* nvmCallNonvirtualObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    Object* result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

Object* nvmCallNonvirtualObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return nvmCallNonvirtualObjectInstanceMethodA(env, obj, method, jargs);
}

Object* nvmCallNonvirtualObjectInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualObjectInstanceMethodV(env, obj, method, args);
}

jboolean nvmCallNonvirtualBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jboolean result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jboolean nvmCallNonvirtualBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return nvmCallNonvirtualBooleanInstanceMethodA(env, obj, method, jargs);
}

jboolean nvmCallNonvirtualBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualBooleanInstanceMethodV(env, obj, method, args);
}

jbyte nvmCallNonvirtualByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jbyte result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jbyte nvmCallNonvirtualByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallNonvirtualByteInstanceMethodA(env, obj, method, jargs);
}

jbyte nvmCallNonvirtualByteInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualByteInstanceMethodV(env, obj, method, args);
}

jchar nvmCallNonvirtualCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jchar result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jchar nvmCallNonvirtualCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallNonvirtualCharInstanceMethodA(env, obj, method, jargs);
}

jchar nvmCallNonvirtualCharInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualCharInstanceMethodV(env, obj, method, args);
}

jshort nvmCallNonvirtualShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jshort result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jshort nvmCallNonvirtualShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallNonvirtualShortInstanceMethodA(env, obj, method, jargs);
}

jshort nvmCallNonvirtualShortInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualShortInstanceMethodV(env, obj, method, args);
}

jint nvmCallNonvirtualIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jint result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jint nvmCallNonvirtualIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallNonvirtualIntInstanceMethodA(env, obj, method, jargs);
}

jint nvmCallNonvirtualIntInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualIntInstanceMethodV(env, obj, method, args);
}

jlong nvmCallNonvirtualLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jlong result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jlong nvmCallNonvirtualLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallNonvirtualLongInstanceMethodA(env, obj, method, jargs);
}

jlong nvmCallNonvirtualLongInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualLongInstanceMethodV(env, obj, method, args);
}

jfloat nvmCallNonvirtualFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jfloat result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jfloat nvmCallNonvirtualFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return nvmCallNonvirtualFloatInstanceMethodA(env, obj, method, jargs);
}

jfloat nvmCallNonvirtualFloatInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualFloatInstanceMethodV(env, obj, method, args);
}

jdouble nvmCallNonvirtualDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, obj, method, FALSE, args);
    if (!callInfo) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jdouble result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jdouble nvmCallNonvirtualDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return nvmCallNonvirtualDoubleInstanceMethodA(env, obj, method, jargs);
}

jdouble nvmCallNonvirtualDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallNonvirtualDoubleInstanceMethodV(env, obj, method, args);
}

void nvmCallVoidClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return;
    void (*f)(CallInfo*) = _call0;
    nvmPushGatewayFrame(env);
    f(callInfo);
    nvmPopGatewayFrame(env);
}

void nvmCallVoidClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return;
    nvmCallVoidClassMethodA(env, clazz, method, jargs);
}

void nvmCallVoidClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    nvmCallVoidClassMethodV(env, clazz, method, args);
}

Object* nvmCallObjectClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return NULL;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    Object* result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

Object* nvmCallObjectClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return NULL;
    return nvmCallObjectClassMethodA(env, clazz, method, jargs);
}

Object* nvmCallObjectClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallObjectClassMethodV(env, clazz, method, args);
}

jboolean nvmCallBooleanClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return FALSE;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jboolean result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jboolean nvmCallBooleanClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return FALSE;
    return nvmCallBooleanClassMethodA(env, clazz, method, jargs);
}

jboolean nvmCallBooleanClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallBooleanClassMethodV(env, clazz, method, args);
}

jbyte nvmCallByteClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jbyte result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jbyte nvmCallByteClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallByteClassMethodA(env, clazz, method, jargs);
}

jbyte nvmCallByteClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallByteClassMethodV(env, clazz, method, args);
}

jchar nvmCallCharClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jchar result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jchar nvmCallCharClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallCharClassMethodA(env, clazz, method, jargs);
}

jchar nvmCallCharClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallCharClassMethodV(env, clazz, method, args);
}

jshort nvmCallShortClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jshort result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jshort nvmCallShortClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallShortClassMethodA(env, clazz, method, jargs);
}

jshort nvmCallShortClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallShortClassMethodV(env, clazz, method, args);
}

jint nvmCallIntClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jint result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jint nvmCallIntClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallIntClassMethodA(env, clazz, method, jargs);
}

jint nvmCallIntClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallIntClassMethodV(env, clazz, method, args);
}

jlong nvmCallLongClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jlong result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jlong nvmCallLongClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0;
    return nvmCallLongClassMethodA(env, clazz, method, jargs);
}

jlong nvmCallLongClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallLongClassMethodV(env, clazz, method, args);
}

jfloat nvmCallFloatClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0.0f;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jfloat result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jfloat nvmCallFloatClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0f;
    return nvmCallFloatClassMethodA(env, clazz, method, jargs);
}

jfloat nvmCallFloatClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallFloatClassMethodV(env, clazz, method, args);
}

jdouble nvmCallDoubleClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args) {
    CallInfo* callInfo = initCallInfo(env, NULL, method, FALSE, args);
    if (!callInfo) return 0.0;
    nvmInitialize(env, method->clazz);
    if (nvmExceptionOccurred(env)) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _call0;
    nvmPushGatewayFrame(env);
    jdouble result = f(callInfo);
    nvmPopGatewayFrame(env);
    return result;
}

jdouble nvmCallDoubleClassMethodV(Env* env, Class* clazz, Method* method, va_list args) {
    jvalue* jargs = va_list2jargs(env, method, args);
    if (!jargs) return 0.0;
    return nvmCallDoubleClassMethodA(env, clazz, method, jargs);
}

jdouble nvmCallDoubleClassMethod(Env* env, Class* clazz, Method* method, ...) {
    va_list args;
    va_start(args, method);
    return nvmCallDoubleClassMethodV(env, clazz, method, args);
}

jboolean nvmRegisterNative(Env* env, NativeMethod* method, void* impl) {
    method->nativeImpl = impl;
    return TRUE;
}

jboolean nvmUnregisterNative(Env* env, NativeMethod* method) {
    method->nativeImpl = NULL;
    return TRUE;
}

void* nvmResolveNativeMethodImpl(Env* env, NativeMethod* method, const char* shortMangledName, const char* longMangledName, ClassLoader* classLoader, void** ptr) {
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
            nvmThrowUnsatisfiedLinkError(env);
            return NULL;
        }

        obtainNativeLibsLock();

        TRACE("Searching for native method using short name: %s\n", shortMangledName);
        f = nvmFindDynamicLibSymbol(env, nativeLibs, shortMangledName, TRUE);
        if (f) {
            TRACE("Found native method using short name: %s\n", shortMangledName);
        } else if (!strcmp(shortMangledName, longMangledName)) {
            TRACE("Searching for native method using long name: %s\n", longMangledName);
            void* f = nvmFindDynamicLibSymbol(env, nativeLibs, longMangledName, TRUE);
            if (f) {
                TRACE("Found native method using long name: %s\n", longMangledName);
            }
        }

        method->nativeImpl = f;

        releaseNativeLibsLock();
    }

    if (!f) {
        nvmThrowUnsatisfiedLinkError(env);
        return NULL;
    }
    // TODO: Remember ptr to allow it to be reset when the JNI RegisterNatives/UnregisterNatives functions are called
    *ptr = f;
    return f;
}


jboolean nvmLoadNativeLibrary(Env* env, const char* path, ClassLoader* classLoader) {
    DynamicLib** nativeLibs = NULL;
    if (!classLoader || classLoader->parent == NULL) {
        // This is the bootstrap classloader
        nativeLibs = &bootNativeLibs;
    } else if (classLoader->parent->parent == NULL && classLoader->object.clazz->classLoader == NULL) {
        // This is the system classloader
        nativeLibs = &mainNativeLibs;
    } else {
        // Unknown classloader
        if (bootNativeLibs) {
            // if bootNativeLibs is NULL we're being called from nvmStartup() and we cannot throw exceptions.
            nvmThrowUnsatisfiedLinkError(env);
        }
        return FALSE;
    }

    DynamicLib* lib = nvmOpenDynamicLib(env, path);
    if (!lib) {
        if (!nvmExceptionOccurred(env)) {
            if (bootNativeLibs) {
                // if bootNativeLibs is NULL we're being called from nvmStartup() and we cannot throw exceptions.
                nvmThrowUnsatisfiedLinkError(env);
            }
        }
        return FALSE;
    }

    obtainNativeLibsLock();

    if (nvmHasDynamicLib(env, lib, *nativeLibs)) {
        // The lib is already in nativeLibs
        nvmCloseDynamicLib(env, lib);
        releaseNativeLibsLock();
        return TRUE;
    }

    jint (*JNI_OnLoad)(JavaVM*, void*) = nvmFindDynamicLibSymbol(env, lib, "JNI_OnLoad", FALSE);
    if (JNI_OnLoad) {
        // TODO: Check that JNI_OnLoad returns a supported JNI version?
        JNI_OnLoad(&env->vm->javaVM, NULL);
        if (nvmExceptionOccurred(env)) {
            releaseNativeLibsLock();
            return FALSE;
        }
    }

    nvmAddDynamicLib(env, lib, nativeLibs);

    releaseNativeLibsLock();

    return TRUE;
}

