#include <nullvm.h>
#include "assert.h"

typedef struct JNIMethodCallDescriptor {
    void* f;
    jint* stackSize;
} JNIMethodCallDescriptor;

extern void* _nvmCallMethod(void);

void* foo(void* desc, Env* env, jint a, jint b, jint c, jint d, jint e, jint f) {
    printf("%p, %p, %d, %d, %d, %d, %d, %d\n", desc, env, a, b, c, d, e, f);
    nvmThrowNullPointerException(env);
    nvmRaiseException(env, nvmExceptionOccurred(env));
}

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    void* (*f)(JNIMethodCallDescriptor*, Env* env, jint, jint, jint, jint, jint, jint) = &_nvmCallMethod;
    JNIMethodCallDescriptor desc;
    desc.f = foo;
    desc.stackSize = 16;
    f(&desc, env, 5, 6, 7, 8, 9, 10);


    Class* ExceptionOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/ExceptionOpcodes");
    jint (*_throw)(Env*, jint) = nvmGetClassMethod(env, ExceptionOpcodes, "_throw", "(I)I")->impl;

    assertEqualsInt("_throw(0)", -1, _throw(env, 0));
    assertEqualsInt("_throw(1)", 1, _throw(env, 1));

    print_test_result();

    return 0;
}

