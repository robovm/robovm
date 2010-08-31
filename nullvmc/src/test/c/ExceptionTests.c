#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* ExceptionOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/ExceptionOpcodes");
    jint (*_throw)(void*, Env*, jint) = nvmGetClassMethod(env, ExceptionOpcodes, "_throw", "(I)I")->impl;

    assertEqualsInt("_throw(0)", -1, _throw(NULL, env, 0));
    assertEqualsInt("_throw(1)", 1, _throw(NULL, env, 1));

    print_test_result();

    return 0;
}

