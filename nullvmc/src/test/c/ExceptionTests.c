#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* ExceptionOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ExceptionOpcodes", "org_nullvm_compiler_tests_opcode_ExceptionOpcodes", NULL);
    jint (*_throw)(jclass*, jint) = j_get_method_impl(ExceptionOpcodes, "_throw", "(I)I", ExceptionOpcodes);

    assertEqualsInt("_throw(0)", -1, _throw(ExceptionOpcodes, 0));
    assertEqualsInt("_throw(1)", 1, _throw(ExceptionOpcodes, 1));

    print_test_result();

    return 0;
}

