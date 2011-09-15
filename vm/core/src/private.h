#ifndef PRIVATE_H
#define PRIVATE_H

#include <nullvm.h>

/* unwind.c */
#define UNWIND_UNHANDLED_EXCEPTION 1
#define UNWIND_FATAL_ERROR 2

extern jint unwindRaiseException(Env* env);
extern void unwindIterateCallStack(Env* env, jboolean (*iterator)(Env*, void*, jint, void*), void* data);

/* class.c */
extern ProxyMethod* addProxyMethod(Env* env, Class* clazz, Method* proxiedMethod, jint access, void* impl);

/* call0-<os>-<arch>.s and proxy0-<os>-<arch>.s */
#define RETURN_TYPE_INT    0
#define RETURN_TYPE_LONG   1
#define RETURN_TYPE_FLOAT  2
#define RETURN_TYPE_DOUBLE 3

#define MIN(a,b) ((a < b) ?  (a) : (b))

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

struct CallInfo;

extern void _call0(struct CallInfo*);
extern void _proxy0(void);

#ifdef NVM_X86_64

#define MAX_INT_ARGS 6
#define MAX_FP_ARGS 8

typedef struct CallInfo {
    void* function;
    jint intArgsIndex;
    IntValue intArgs[MAX_INT_ARGS];
    jint fpArgsIndex;
    FpValue fpArgs[MAX_FP_ARGS];
    jint stackArgsSize;
    jint stackArgsIndex;
    void** stackArgs;
    FpIntValue returnValue;
    jint returnType;
    void* returnAddress;
} CallInfo;

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = nvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + longArgsCount + floatArgsCount + doubleArgsCount;
    stackArgsSize -= MIN(ptrArgsCount + intArgsCount + longArgsCount, MAX_INT_ARGS);
    stackArgsSize -= MIN(floatArgsCount + doubleArgsCount, MAX_FP_ARGS);
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = nvmAllocateMemory(env, stackArgsSize * sizeof(void*));
        if (!ci->stackArgs) return NULL;
    }
    return ci;
}

static inline void call0AddLong(CallInfo* ci, jlong j) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        ci->intArgs[ci->intArgsIndex++].j = j;
        return;
    }
    *((jlong*) &(ci->stackArgs[ci->stackArgsIndex++])) = j;
}

static inline void call0AddInt(CallInfo* ci, jint i) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        ci->intArgs[ci->intArgsIndex++].i = i;
        return;
    }
    *((jint*) &(ci->stackArgs[ci->stackArgsIndex++])) = i;
}

static inline void call0AddPtr(CallInfo* ci, void* p) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        ci->intArgs[ci->intArgsIndex++].p = p;
        return;
    }
    ci->stackArgs[ci->stackArgsIndex++] = p;
}

static inline void call0AddFloat(CallInfo* ci, jfloat f) {
    if (ci->fpArgsIndex < MAX_FP_ARGS) {
        ci->fpArgs[ci->fpArgsIndex++].f = f;
        return;
    }
    *((jfloat*) &(ci->stackArgs[ci->stackArgsIndex++])) = f;
}

static inline void call0AddDouble(CallInfo* ci, jdouble d) {
    if (ci->fpArgsIndex < MAX_FP_ARGS) {
        ci->fpArgs[ci->fpArgsIndex++].d = d;
        return;
    }
    *((jdouble*) &(ci->stackArgs[ci->stackArgsIndex++])) = d;
}

static inline jint proxy0NextInt(CallInfo* ci) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        return ci->intArgs[ci->intArgsIndex++].i;
    }
    return *((jint*) &(ci->stackArgs[ci->stackArgsIndex++]));
}

static inline jlong proxy0NextLong(CallInfo* ci) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        return ci->intArgs[ci->intArgsIndex++].j;
    }
    return *((jlong*) &(ci->stackArgs[ci->stackArgsIndex++]));
}

static inline void* proxy0NextPtr(CallInfo* ci) {
    if (ci->intArgsIndex < MAX_INT_ARGS) {
        return ci->intArgs[ci->intArgsIndex++].p;
    }
    return ci->stackArgs[ci->stackArgsIndex++];
}

static inline jfloat proxy0NextFloat(CallInfo* ci) {
    if (ci->fpArgsIndex < MAX_FP_ARGS) {
        return ci->fpArgs[ci->fpArgsIndex++].f;
    }
    return *((jfloat*) &(ci->stackArgs[ci->stackArgsIndex++]));
}

static inline jdouble proxy0NextDouble(CallInfo* ci) {
    if (ci->fpArgsIndex < MAX_FP_ARGS) {
        return ci->fpArgs[ci->fpArgsIndex++].d;
    }
    return *((jdouble*) &(ci->stackArgs[ci->stackArgsIndex++]));
}

static inline void proxy0ReturnInt(CallInfo* ci, jint i) {
    ci->returnValue.i = i;
    ci->returnType = RETURN_TYPE_INT;
}

static inline void proxy0ReturnLong(CallInfo* ci, jlong j) {
    ci->returnValue.j = j;
    ci->returnType = RETURN_TYPE_LONG;
}

static inline void proxy0ReturnPtr(CallInfo* ci, void* p) {
    proxy0ReturnLong(ci, (jlong) p);
}

static inline void proxy0ReturnFloat(CallInfo* ci, jfloat f) {
    ci->returnValue.f = f;
    ci->returnType = RETURN_TYPE_FLOAT;
}

static inline void proxy0ReturnDouble(CallInfo* ci, jdouble d) {
    ci->returnValue.d = d;
    ci->returnType = RETURN_TYPE_DOUBLE;
}


#elif NVM_I386

typedef struct CallInfo {
    void* function;
    jint stackArgsSize;
    jint stackArgsIndex;
    void** stackArgs;
    FpIntValue returnValue;
    jint returnType;
    void* returnAddress;
} CallInfo;

/*
 * Each slot on the stack occupies 32-bits. longs and doubles are split in two and each part is pushed separately.
 * sizeof(void*) == 4 bytes.
 */

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = nvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + (longArgsCount << 1) + floatArgsCount + (doubleArgsCount << 1);
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = nvmAllocateMemory(env, stackArgsSize * sizeof(void*));
        if (!ci->stackArgs) return NULL;
    }
    return ci;
}

static inline void call0AddInt(CallInfo* ci, jint i) {
    *((jint*) &(ci->stackArgs[ci->stackArgsIndex++])) = i;
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
    return *((jint*) &(ci->stackArgs[ci->stackArgsIndex++]));
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

