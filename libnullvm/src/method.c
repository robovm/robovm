#include <nullvm.h>
#include <string.h>
#include <unwind.h>
#include "log.h"

// Defined in init.c
extern DynamicLib* nativeLibs;

typedef union IntValue {
    jint i;
    jlong j;
    Env* env;
    void* ptr;
} IntValue;

typedef union FpValue {
    jdouble d;
    jfloat f;
} FpValue;

typedef union StackValue {
    jdouble d;
    jfloat f;
    jint i;
    jlong j;
    Env* env;
    void* ptr;
} StackValue;

typedef struct CallInfo {
    void* function;
    void* intArgs[6];
    double fpArgs[8];
    jint stackArgsCount;
    StackValue* stackArgs;
} CallInfo;

extern void _nvmCall0(CallInfo*);

static jvalue emptyJValueArgs[1];

static Method* getMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method;
    for (method = clazz->methods; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            return method;
        }
    }

    if (clazz->superclass && strcmp("<init>", name) && strcmp("<clinit>", name)) {
        /* 
         * Check with the superclass. Note that constructors and static 
         * initializers are not inherited.
         */
        return nvmGetMethod(env, clazz->superclass, name, desc);
    }

    return NULL;
}

Method* nvmGetMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method = getMethod(env, clazz, name, desc);
    if (!method) {
        nvmThrowNoSuchMethodError(env, name);
        return NULL;
    }
    return method;
}

Method* nvmGetClassMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (!(method->access & ACC_STATIC)) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

Method* nvmGetClassInitializer(Env* env, Class* clazz) {
    return getMethod(env, clazz, "<clinit>", "()V");
}

Method* nvmGetInstanceMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method = nvmGetMethod(env, clazz, name, desc);
    if (!method) return NULL;
    if (method->access & ACC_STATIC) {
        // TODO: JNI spec doesn't say anything about throwing this
        nvmThrowIncompatibleClassChangeErrorMethod(env, clazz, name, desc);
        return NULL;
    }
    return method;
}

typedef struct FindMethodAtAddressData {
    void* address;
    Method* method;
} FindMethodAtAddressData;

static jboolean findMethodAtAddressIterator(Class* clazz, void* d) {
    FindMethodAtAddressData* data = (FindMethodAtAddressData*) d;
    void* address = data->address;
    Method* method;
    for (method = clazz->methods; method != NULL; method = method->next) {
        if (method->impl && address == method->impl) {
            data->method = method;
            return FALSE;
        }
    }
    return TRUE;
}

Method* nvmFindMethodAtAddress(Env* env, void* address) {
    FindMethodAtAddressData data = {0};
    data.address = address;
    nvmIterateLoadedClasses(env, findMethodAtAddressIterator, (void*) &data);
    return data.method;
}

static _Unwind_Reason_Code unwindCallStack(struct _Unwind_Context* ctx, void* _d) {
    void** d = (void**) _d;
    Env* env = (Env*) d[0];
    CallStackEntry* first = (CallStackEntry*) d[1];
    CallStackEntry* last = (CallStackEntry*) d[2];
    void* address = (void*) _Unwind_GetIP(ctx);
    void* func = _Unwind_FindEnclosingFunction(address);
    Method* method = nvmFindMethodAtAddress(env, func);
    if (method) {
        CallStackEntry* entry = nvmAllocateMemory(env, sizeof(CallStackEntry));
        if (entry) {
            entry->method = method;
            entry->offset = (jint) (address - method->impl);
            if (!first) d[1] = entry;
            if (last) last->next = entry;
            d[2] = entry;
        }
    }
    return _URC_NO_REASON;
}

CallStackEntry* nvmGetCallStack(Env* env) {
    void* d[3];
    d[0] = env;
    d[1] = NULL;
    d[2] = NULL;
    _Unwind_Backtrace(unwindCallStack, d);
    if (nvmExceptionOccurred(env)) return NULL;
    return (CallStackEntry*) d[1];
}

char* nvmGetReturnType(char* desc) {
    while (*desc != ')') desc++;
    desc++;
    return desc;
}

char* nvmGetNextArgumentType(char** desc) {
    char* s = *desc;
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
        nvmGetNextArgumentType(desc);
        return s;
    case 'L':
        while (**desc != ';') (*desc)++;
        (*desc)++;
        return s;
    case '(':
        return nvmGetNextArgumentType(desc);
    }
    return 0;
}

static inline jboolean isFpType(char type) {
    return type == 'F' || type == 'D';
}

jboolean initCallInfo(CallInfo* callInfo, Env* env, Class* clazz, Object* obj, Method* method, jboolean virtual, jvalue* args) {
    if (virtual && !(method->access & ACC_PRIVATE)) {
        // Lookup the real method to be invoked
        method = nvmGetMethod(env, obj->clazz, method->name, method->desc);
        if (!method) return FALSE;
    }

    jint argsCount = 0, intArgsCount = 0, fpArgsCount = 0, stackArgsCount = 0;

    intArgsCount = 1; // First arg is always the Env*
    if (!(method->access & ACC_STATIC)) {
        // Non-static methods takes the receiver object (this) as arg 2
        intArgsCount++;
    }    

    char* desc = method->desc;
    char* c;
    while (c = nvmGetNextArgumentType(&desc)) {
        argsCount++;
        if (isFpType(c[0]) && fpArgsCount < 8) {
            fpArgsCount++;
        } else if (intArgsCount < 6) {
            intArgsCount++;
        } else {
            stackArgsCount++;
        }
    }

    callInfo->function = method->impl;
    callInfo->stackArgsCount = stackArgsCount;
    if (stackArgsCount > 0) {
        callInfo->stackArgs = nvmAllocateMemory(env, sizeof(StackValue) * stackArgsCount);
        if (!callInfo->stackArgs) return FALSE;
    }

    jint intArgsIndex = 0, fpArgsIndex = 0, stackArgsIndex = 0;

    callInfo->intArgs[intArgsIndex++] = env;
    if (!(method->access & ACC_STATIC)) {
        callInfo->intArgs[intArgsIndex++] = obj;
    }    

    desc = method->desc;
    jint i = 0;
    while (c = nvmGetNextArgumentType(&desc)) {
        if (isFpType(c[0])) {
            if (fpArgsIndex < fpArgsCount) {
                switch (c[0]) {
                case 'F':
                    callInfo->fpArgs[fpArgsIndex++] = (double) args[i++].f;
                    break;
                case 'D':
                    callInfo->fpArgs[fpArgsIndex++] = args[i++].d;
                    break;
                }
            } else {
                switch (c[0]) {
                case 'F':
                    callInfo->stackArgs[stackArgsIndex++].f = args[i++].f;
                    break;
                case 'D':
                    callInfo->stackArgs[stackArgsIndex++].d = args[i++].d;
                    break;
                }
            }
        } else {
            if (intArgsIndex < intArgsCount) {
                callInfo->intArgs[intArgsIndex++] = (void*) args[i++].j;
            } else {
                callInfo->stackArgs[stackArgsIndex++].j = args[i++].j;
            }
        }
    }

    return TRUE;
}

static jvalue* va_list2jargs(Env* env, Method* method, va_list args) {
    jint argsCount = 0;
    char* desc = method->desc;
    char* c;
    while (c = nvmGetNextArgumentType(&desc)) {
        argsCount++;
    }

    if (argsCount == 0) {
        return emptyJValueArgs;
    }

    jvalue *jvalueArgs = (jvalue*) nvmAllocateMemory(env, sizeof(jvalue) * argsCount);
    if (!jvalueArgs) return NULL;

    desc = method->desc;
    jint i = 0;
    while (c = nvmGetNextArgumentType(&desc)) {
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
            jvalueArgs[i++].f = (jdouble) va_arg(args, jdouble);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return;
    void (*f)(CallInfo*) = _nvmCall0;
    f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, TRUE, args)) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return;
    void (*f)(CallInfo*) = _nvmCall0;
    f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, obj->clazz, obj, method, FALSE, args)) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return;
    void (*f)(CallInfo*) = _nvmCall0;
    f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return NULL;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return NULL;
    Object* (*f)(CallInfo*) = (Object* (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return FALSE;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return FALSE;
    jboolean (*f)(CallInfo*) = (jboolean (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jchar (*f)(CallInfo*) = (jchar (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jshort (*f)(CallInfo*) = (jshort (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0;
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0.0f;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0.0f;
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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
    CallInfo callInfo = {0};
    if (!initCallInfo(&callInfo, env, clazz, NULL, method, FALSE, args)) return 0.0;
    nvmInitialize(env, clazz);
    if (nvmExceptionOccurred(env)) return 0.0;
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _nvmCall0;
    return f(&callInfo);
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

Object* nvmCallReflectedMethod(Env* env, Object* obj, ObjectArray* args, Class* declaringClass, ObjectArray* parameterTypes, Class* returnType, jint slot, jboolean noAccessCheck) {
}

/*
Method* nvmGetMethod(Env* env, Class* clazz, char* name, char* desc) {
    Method* method;
    int sameClass = caller == NULL || clazz == caller;
    int subClass = caller == NULL || nvmIsSubClass(clazz, caller);
    int samePackage = caller == NULL || nvmIsSamePackage(clazz, caller);

    for (method = clazz->methods; method != NULL; method = method->next) {
        if (!strcmp(method->name, name) && !strcmp(method->desc, desc)) {
            jint access = method->access;
            if (IS_PRIVATE(access) && !sameClass) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            if (IS_PROTECTED(access) && !subClass) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            if (IS_PACKAGE_PRIVATE(access) && !samePackage) {
                nvmThrowIllegalAccessErrorMethod(clazz, name, desc, caller);
            }
            return method;
        }
    }

    if (clazz->superclass && strcmp("<init>", name) && strcmp("<clinit>", name)) {
        /* 
         * Check with the superclass. Note that constructors and static 
         * initializers are not inherited.
         */
       /* return nvmGetMethod(clazz, name, desc);
    }

    nvmThrowNoSuchMethodError(name);
}*/

void* nvmGetNativeMethod(Env* env, char* shortMangledName, char* longMangledName) {
    TRACE("Searching for native method using short name: %s\n", shortMangledName);
    void* f = nvmFindDynamicLibSymbol(env, nativeLibs, NULL, shortMangledName);
    if (f) {
        TRACE("Found native method using short name: %s\n", shortMangledName);
    } else if (!strcmp(shortMangledName, longMangledName)) {
        TRACE("Searching for native method using long name: %s\n", longMangledName);
        void* f = nvmFindDynamicLibSymbol(env, nativeLibs, NULL, longMangledName);
        if (f) {
            TRACE("Found native method using long name: %s\n", longMangledName);
        }
    }
    if (!f) {
        nvmThrowUnsatisfiedLinkError(env);
        return NULL;
    }
    return f;
}

