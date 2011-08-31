#ifndef PRIVATE_H
#define PRIVATE_H

#include <nullvm.h>

#define MAX_INT_ARGS 0
#define MAX_FP_ARGS 0

#ifdef NVM_X86_64
#define MAX_INT_ARGS 6
#define MAX_FP_ARGS 8
#endif

#define RETURN_TYPE_INT    0
#define RETURN_TYPE_LONG   1
#define RETURN_TYPE_FLOAT  2
#define RETURN_TYPE_DOUBLE 3

typedef union IntValue {
    jint i;
    jlong j;
    void* p;
} IntValue;

typedef union FpValue {
    jdouble d;
    jfloat f;
} FpValue;

typedef union FpIntValue {
    jint i;
    jlong j;
    jdouble d;
    jfloat f;
} FpIntValue;

typedef struct CallInfo {
    void* function;
#if MAX_INT_ARGS > 0
    jint intArgsSize;
    jint intArgsIndex;
    IntValue intArgs[MAX_INT_ARGS];
#endif
#if MAX_FP_ARGS > 0
    jint fpArgsSize;
    jint fpArgsIndex;
    FpValue fpArgs[MAX_FP_ARGS];
#endif
    jint stackArgsSize;
    jint stackArgsIndex;
    void* stackArgs;
    FpIntValue returnValue;
    jint returnType;
} CallInfo;

extern void _call0(CallInfo*);
extern void _proxy0(void);

#ifdef NVM_X86_64

#else

/*
 * Each slot on the stack occupies 32-bits. longs and doubles are split in two and each part is pushed separately.
 * sizeof(void*) == 4 bytes.
 * If registers are used to pass args they are 32-bits in size.
 */

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = nvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + (longArgsCount << 1) + floatArgsCount + (doubleArgsCount << 1);
#if MAX_INT_ARGS > 0
    ci->intArgsSize = ptrArgsCount + intArgsCount + (longArgsCount << 1);
    if (ci->intArgsSize > MAX_INT_ARGS) ci->intArgsSize = MAX_INT_ARGS;
    stackArgsSize -= ci->intArgsSize;
#endif
#if MAX_FP_ARGS > 0
    ci->fpArgsSize = floatArgsCount + (doubleArgsCount << 1);
    if (ci->fpArgsSize > MAX_FP_ARGS) ci->fpArgsSize = MAX_FP_ARGS;
    stackArgsSize -= ci->fpArgsSize;
#endif
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = nvmAllocateMemory(env, stackArgsSize * sizeof(void*));
        if (!ci->stackArgs) return NULL;
    }
    return ci;
}

static inline void call0AddInt(CallInfo* ci, jint i) {
#if MAX_INT_ARGS > 0
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        ci->intArgs[ci->intArgsIndex++].i = i;
        return;
    }
#endif
    void* p = ci->stackArgs + sizeof(void*) * ci->stackArgsIndex;
    ci->stackArgsIndex++;
    *((jint*) p) = i;
}

static inline void call0AddLong(CallInfo* ci, jlong j) {
    call0AddInt(ci, (jint) j);
    call0AddInt(ci, (jint) (j >> 32));
}

static inline void call0AddPtr(CallInfo* ci, void* p) {
    call0AddInt(ci, (jint) p);
}

static inline void call0AddFloat(CallInfo* ci, jfloat f) {
    FpIntValue fi;
    fi.f = f;
    call0AddInt(ci, fi.i);
}

static inline void call0AddDouble(CallInfo* ci, jdouble d) {
    FpIntValue fi;
    fi.d = d;
    call0AddLong(ci, fi.j);
}

static inline jint proxy0NextInt(CallInfo* ci) {
#if MAX_INT_ARGS > 0
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        return ci->intArgs[ci->intArgsIndex++].i;
    }
#endif
    void* p = ci->stackArgs + sizeof(void*) * ci->stackArgsIndex;
    ci->stackArgsIndex++;
    return *((jint*) p);
}

static inline jlong proxy0NextLong(CallInfo* ci) {
    jlong l = (jlong) proxy0NextInt(ci) & 0xffffffff;
    l |= (jlong) proxy0NextInt(ci) << 32;
    return l;
}

static inline void* proxy0NextPtr(CallInfo* ci) {
    return (void*) proxy0NextInt(ci);
}

static inline jfloat proxy0NextFloat(CallInfo* ci) {
    FpIntValue fi;
    fi.i = proxy0NextInt(ci);
    return fi.f;
}

static inline jdouble proxy0NextDouble(CallInfo* ci) {
    FpIntValue fi;
    fi.j = proxy0NextLong(ci);
    return fi.d;
}

static inline void proxy0ReturnInt(CallInfo* ci, jint i) {
    ci->returnValue.i = i;
    ci->returnType = RETURN_TYPE_INT;
}

static inline void proxy0ReturnPtr(CallInfo* ci, void* p) {
    proxy0ReturnInt(ci, (jint) p);
}

static inline void proxy0ReturnLong(CallInfo* ci, jlong j) {
    ci->returnValue.j = j;
    ci->returnType = RETURN_TYPE_LONG;
}

static inline void proxy0ReturnFloat(CallInfo* ci, jfloat f) {
    ci->returnValue.f = f;
    ci->returnType = RETURN_TYPE_FLOAT;
}

static inline void proxy0ReturnDouble(CallInfo* ci, jdouble d) {
    ci->returnValue.d = d;
    ci->returnType = RETURN_TYPE_DOUBLE;
}

#endif

#endif

