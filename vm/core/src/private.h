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
#ifndef PRIVATE_H
#define PRIVATE_H

#include <pthread.h>
#include <robovm.h>

/* memory.c */
#define MAKE_GC_BITMAP(offset) ((offset>>2) > 30 ? -1 : (1 << (31 - (offset>>2))))

typedef void (*CleanupHandler)(Env*, Object*);

extern jboolean initGC(Options* options);
extern void gcRegisterCurrentThread();
extern void gcUnregisterCurrentThread();
extern void gcAddRoot(void* ptr);
extern void gcAddRoots(void* start, void* end);
extern uint32_t gcNewDirectBitmapKind(uint32_t bitmap);
extern void* gcAllocate(size_t size);
extern void* allocateMemoryOfKind(Env* env, size_t size, uint32_t kind);
extern void registerCleanupHandler(Env* env, Object* object, CleanupHandler handler);

/* unwind.c */
typedef struct Frame {
    struct Frame* prev;
    void* returnAddress;
} Frame;

typedef struct UnwindContext {
    Frame* fp;
    void* pc;
    Frame* newFrame;
} UnwindContext;

extern void unwindBacktrace(void* fp, jboolean (*it)(UnwindContext*, void*), void* data);
extern void* unwindGetIP(UnwindContext* context);
extern jint unwindRaiseException(Env* env);
extern jint unwindReraiseException(Env* env, void* exInfo);
extern void unwindIterateCallStack(Env* env, void* fp, jboolean (*iterator)(Env*, void*, ProxyMethod*, void*), void* data);

/* method.c */
extern void captureCallStack(Env* env, Frame* fp, CallStack* data, jint maxLength);
extern CallStack* captureCallStackFromFrame(Env* env, Frame* fp);

/* signal.c */
extern void dumpThreadStackTrace(Env* env, Thread* thread, CallStack* callStack);

/* class.c */
extern uint32_t nextClassId();
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

#ifdef RVM_X86_64

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
} CallInfo;

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = rvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + longArgsCount + floatArgsCount + doubleArgsCount;
    stackArgsSize -= MIN(ptrArgsCount + intArgsCount + longArgsCount, MAX_INT_ARGS);
    stackArgsSize -= MIN(floatArgsCount + doubleArgsCount, MAX_FP_ARGS);
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = rvmAllocateMemory(env, stackArgsSize * sizeof(void*));
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


#elif RVM_X86

typedef struct CallInfo {
    void* function;
    jint stackArgsSize;
    jint stackArgsIndex;
    void** stackArgs;
    FpIntValue returnValue;
    jint returnType;
} CallInfo;

/*
 * Each slot on the stack occupies 32-bits. longs and doubles are split in two and each part is pushed separately.
 * sizeof(void*) == 4 bytes.
 */

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = rvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + (longArgsCount << 1) + floatArgsCount + (doubleArgsCount << 1);
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = rvmAllocateMemory(env, stackArgsSize * sizeof(void*));
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

#elif IOS && (RVM_THUMBV6 || RVM_THUMBV7)

#define MAX_REG_ARGS 4

typedef struct CallInfo {
    void* function;
    jint regArgsIndex;
    jint regArgs[MAX_REG_ARGS];
    jint stackArgsSize;
    jint stackArgsIndex;
    void** stackArgs;
    FpIntValue returnValue;
    jint returnType;
} CallInfo;

/*
 * Each slot on the stack occupies 32-bits. longs and doubles are split in two and each part is pushed separately.
 * sizeof(void*) == 4 bytes.
 */

static inline CallInfo* call0AllocateCallInfo(Env* env, void* function, jint ptrArgsCount, jint intArgsCount, jint longArgsCount, jint floatArgsCount, jint doubleArgsCount) {
    CallInfo* ci = rvmAllocateMemory(env, sizeof(CallInfo));
    if (!ci) return NULL;
    ci->function = function;
    jint stackArgsSize = ptrArgsCount + intArgsCount + (longArgsCount << 1) + floatArgsCount + (doubleArgsCount << 1);
    stackArgsSize -= MIN(stackArgsSize, MAX_REG_ARGS);
    if (stackArgsSize > 0) {
        ci->stackArgsSize = stackArgsSize;
        ci->stackArgs = rvmAllocateMemory(env, stackArgsSize * sizeof(void*));
        if (!ci->stackArgs) return NULL;
    }
    return ci;
}

static inline void call0AddInt(CallInfo* ci, jint i) {
    if (ci->regArgsIndex < MAX_REG_ARGS) {
        ci->regArgs[ci->regArgsIndex++] = i;
        return;
    }
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
    if (ci->regArgsIndex < MAX_REG_ARGS) {
        return ci->regArgs[ci->regArgsIndex++];
    }
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

