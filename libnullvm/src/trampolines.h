#include <nullvm.h>

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
    FpValue fpArgs[8];
    jint stackArgsCount;
    StackValue* stackArgs;
} CallInfo;

extern void _nvmCall0(CallInfo*);

