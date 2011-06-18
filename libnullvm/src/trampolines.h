#include <nullvm.h>

// This only works on x86_64 so far

#define MAX_INT_ARGS 6
#define MAX_FP_ARGS 8

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

typedef struct ProxyArgs {
    void* intArgs[MAX_INT_ARGS];
    FpValue fpArgs[MAX_FP_ARGS];
    StackValue* stackArgs;
} ProxyArgs;

typedef struct ProxyArgsIterator {
    ProxyArgs* pa;
    jint intArgsIndex;
    jint fpArgsIndex;
    jint stackArgsIndex;
} ProxyArgsIterator;

typedef struct CallInfo {
    void* function;
    void* intArgs[MAX_INT_ARGS];
    FpValue fpArgs[MAX_FP_ARGS];
    jint stackArgsCount;
    StackValue* stackArgs;
} CallInfo;

extern void _nvmCall0(CallInfo*);
extern void _nvmProxy0(void);

static inline void initProxyArgsIterator(ProxyArgsIterator* it, ProxyArgs* pa) {
    it->pa = pa;
    it->intArgsIndex = 0;
    it->fpArgsIndex = 0;
    it->stackArgsIndex = 0;
}

static inline void* getNextPtrProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return it->pa->intArgs[it->intArgsIndex++];
    }
    return (it->pa->stackArgs++)->ptr;
}

static inline jbyte getNextBooleanProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jboolean) it->pa->intArgs[it->intArgsIndex++];
    }
    return (jboolean) (it->pa->stackArgs++)->i;
}

static inline jbyte getNextByteProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jbyte) it->pa->intArgs[it->intArgsIndex++];
    }
    return (jbyte) (it->pa->stackArgs++)->i;
}

static inline jshort getNextShortProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jshort) it->pa->intArgs[it->intArgsIndex++];
    }
    return (jshort) (it->pa->stackArgs++)->i;
}

static inline jshort getNextCharProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jchar) it->pa->intArgs[it->intArgsIndex++];
    }
    return (jchar) (it->pa->stackArgs++)->i;
}

static inline jint getNextIntProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jint) it->pa->intArgs[it->intArgsIndex++];
    }
    return (it->pa->stackArgs++)->i;
}

static inline jlong getNextLongProxyArg(ProxyArgsIterator* it) {
    if (it->intArgsIndex < MAX_INT_ARGS) {
        return (jlong) it->pa->intArgs[it->intArgsIndex++];
    }
    return (it->pa->stackArgs++)->j;
}

static inline jfloat getNextFloatProxyArg(ProxyArgsIterator* it) {
    if (it->fpArgsIndex < MAX_FP_ARGS) {
        return it->pa->fpArgs[it->fpArgsIndex++].f;
    }
    return (it->pa->stackArgs++)->f;
}

static inline jdouble getNextDoubleProxyArg(ProxyArgsIterator* it) {
    if (it->fpArgsIndex < MAX_FP_ARGS) {
        return it->pa->fpArgs[it->fpArgsIndex++].d;
    }
    return (it->pa->stackArgs++)->d;
}

