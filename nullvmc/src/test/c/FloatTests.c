#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* FloatTests = nvmGetClass("org/nullvm/compiler/tests/opcode/FloatOpcodes", "org_nullvm_compiler_tests_opcode_FloatOpcodes", NULL);
    jfloat (*freturn)(jclass*, jfloat) = j_get_method_impl(FloatTests, "freturn", "(F)F", FloatTests);
    jfloat (*fstore)(jclass*, jfloat) = j_get_method_impl(FloatTests, "fstore", "(F)F", FloatTests);
    jfloat (*fconst_0)(jclass*) = j_get_method_impl(FloatTests, "fconst_0", "()F", FloatTests);
    jfloat (*fconst_1)(jclass*) = j_get_method_impl(FloatTests, "fconst_1", "()F", FloatTests);
    jfloat (*fconst_2)(jclass*) = j_get_method_impl(FloatTests, "fconst_2", "()F", FloatTests);
    jfloat (*fadd)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fadd", "(FF)F", FloatTests);
    jfloat (*fsub)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fsub", "(FF)F", FloatTests);
    jfloat (*fmul)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fmul", "(FF)F", FloatTests);
    jfloat (*fdiv)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fdiv", "(FF)F", FloatTests);
    jfloat (*frem)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "frem", "(FF)F", FloatTests);
    jfloat (*fneg)(jclass*, jfloat) = j_get_method_impl(FloatTests, "fneg", "(F)F", FloatTests);
    jfloat (*ldc_min_float)(jclass*) = j_get_method_impl(FloatTests, "ldc_min_float", "()F", FloatTests);
    jfloat (*ldc_max_float)(jclass*) = j_get_method_impl(FloatTests, "ldc_max_float", "()F", FloatTests);
    jfloat (*i2f)(jclass*, jint) = j_get_method_impl(FloatTests, "i2f", "(I)F", FloatTests);
    jint (*f2i)(jclass*, jfloat) = j_get_method_impl(FloatTests, "f2i", "(F)I", FloatTests);
    jfloat (*l2f)(jclass*, jlong) = j_get_method_impl(FloatTests, "l2f", "(J)F", FloatTests);
    jlong (*f2l)(jclass*, jfloat) = j_get_method_impl(FloatTests, "f2l", "(F)J", FloatTests);
    jint (*fcmpl)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fcmpl", "(FF)I", FloatTests);
    jint (*fcmpg)(jclass*, jfloat, jfloat) = j_get_method_impl(FloatTests, "fcmpg", "(FF)I", FloatTests);

    assertEqualsFloat("freturn", 0.0f, freturn(FloatTests, 0.0f));
    assertEqualsFloat("freturn", 0x1.fffffeP+127f, freturn(FloatTests, 0x1.fffffeP+127f));

    assertEqualsFloat("fstore", -0.12345f, fstore(FloatTests, -0.12345f));
    assertEqualsFloat("fstore", 0.12345f, fstore(FloatTests, 0.12345f));

    assertEqualsFloat("fconst_0", 0.0f, fconst_0(FloatTests));
    assertEqualsFloat("fconst_1", 1.0f, fconst_1(FloatTests));
    assertEqualsFloat("fconst_2", 2.0f, fconst_2(FloatTests));

    assertEqualsFloat("fadd", 0.0f, fadd(FloatTests, 0.0f, 0.0f));
    assertEqualsFloat("fadd", -0.5f, fadd(FloatTests, -0.25f, -0.25f));
    assertEqualsFloat("fadd", 0.75f, fadd(FloatTests, 0.25f, 0.5f));
    assertEqualsFloat("fadd", 0.75f, fadd(FloatTests, 0.5f, 0.25f));
    assertEqualsFloat("fadd", 0.25f, fadd(FloatTests, 0.5f, -0.25f));
    assertEqualsFloat("fadd", -1.0f/0.0f, fadd(FloatTests, -0x1.fffffeP+127f, -0x1.0P+127f));
    assertEqualsFloat("fadd", 1.0f/0.0f, fadd(FloatTests, 0x1.fffffeP+127f, 0x1.0P+127f));

    assertEqualsFloat("fsub", 0.0f, fsub(FloatTests, 0.0f, 0.0f));
    assertEqualsFloat("fsub", 0.0f, fsub(FloatTests, -0.25f, -0.25f));
    assertEqualsFloat("fsub", -0.25f, fsub(FloatTests, 0.25f, 0.5f));
    assertEqualsFloat("fsub", 0.25f, fsub(FloatTests, 0.5f, 0.25f));
    assertEqualsFloat("fsub", 0.75f, fsub(FloatTests, 0.5f, -0.25f));
    assertEqualsFloat("fsub", -1.0f/0.0f, fsub(FloatTests, -0x1.fffffeP+127f, 0x1.0P+127f));
    assertEqualsFloat("fsub", 1.0f/0.0f, fsub(FloatTests, 0x1.fffffeP+127f, -0x1.0P+127f));

    assertEqualsFloat("fmul", 0.0f, fmul(FloatTests, 0.0f, 0.0f));
    assertEqualsFloat("fmul", 1.25f, fmul(FloatTests, 0.5f, 2.5f));
    assertEqualsFloat("fmul", 1.25f, fmul(FloatTests, 2.5f, 0.5f));
    assertEqualsFloat("fmul", -1.25f, fmul(FloatTests, -2.5f, 0.5f));
    assertEqualsFloat("fmul", -1.25f, fmul(FloatTests, 2.5f, -0.5f));
    assertEqualsFloat("fmul", 1.25f, fmul(FloatTests, -2.5f, -0.5f));
    assertEqualsFloat("fmul", 1.0f/0.0f, fmul(FloatTests, 0x1.fffffeP+127f, 2.0f));
    assertEqualsFloat("fmul", 1.0f/0.0f, fmul(FloatTests, 1.0f/0.0f, 2.0f));
    assertEqualsFloat("fmul", -1.0f/0.0f, fmul(FloatTests, 2.0f, -1.0f/0.0f));
    assertEqualsFloat("fmul", 0.0f/0.0f, fmul(FloatTests, 2.0f, 0.0f/0.0f));

    assertEqualsFloat("fdiv", 0.0f/0.0f, fdiv(FloatTests, 0.0f, 0.0f));
    assertEqualsFloat("fdiv", 1.0f/0.0f, fdiv(FloatTests, 2.0f, 0.0f));
    assertEqualsFloat("fdiv", -1.0f/0.0f, fdiv(FloatTests, -2.0f, 0.0f));
    assertEqualsFloat("fdiv", 0.2f, fdiv(FloatTests, 0.5f, 2.5f));
    assertEqualsFloat("fdiv", 5.0f, fdiv(FloatTests, 2.5f, 0.5f));
    assertEqualsFloat("fdiv", -5.0f, fdiv(FloatTests, -2.5f, 0.5f));
    assertEqualsFloat("fdiv", -5.0f, fdiv(FloatTests, 2.5f, -0.5f));
    assertEqualsFloat("fdiv", 5.0f, fdiv(FloatTests, -2.5f, -0.5f));
    assertEqualsFloat("fdiv", 1.0f/0.0f, fdiv(FloatTests, 0x1.fffffeP+127f, 0.5f));

    assertEqualsFloat("frem", 0.0f, frem(FloatTests, 0.0f, 1.0f));
    assertEqualsFloat("frem", 0.0f/0.0f, frem(FloatTests, 0.0f, 0.0f));
    assertEqualsFloat("frem", 0.0f/0.0f, frem(FloatTests, 1.0f, 0.0f));
    assertEqualsFloat("frem", 1.25f, frem(FloatTests, 5.75f, 1.5f));
    assertEqualsFloat("frem", -1.25f, frem(FloatTests, -5.75f, 1.5f));
    assertEqualsFloat("frem", 1.25f, frem(FloatTests, 5.75f, -1.5f));
    assertEqualsFloat("frem", -1.25f, frem(FloatTests, -5.75f, -1.5f));

    assertEqualsFloat("fneg", 0.0f, fneg(FloatTests, 0.0f));
    assertEqualsFloat("fneg", -1.0f, fneg(FloatTests, 1.0f));
    assertEqualsFloat("fneg", 1.0f, fneg(FloatTests, -1.0f));
    assertEqualsFloat("fneg", -0x0.000002P-126f, fneg(FloatTests, 0x0.000002P-126f));
    assertEqualsFloat("fneg", -0x1.fffffeP+127f, fneg(FloatTests, 0x1.fffffeP+127f));
    assertEqualsFloat("fneg", -1.0f/0.0f, fneg(FloatTests, 1.0f/0.0f));
    assertEqualsFloat("fneg", 0.0f/0.0f, fneg(FloatTests, 0.0f/0.0f));

    assertEqualsFloat("ldc(float)", 1.4e-45f, ldc_min_float(FloatTests));
    assertEqualsFloat("ldc(float)", 3.4028235e+38f, ldc_max_float(FloatTests));

    assertEqualsFloat("i2f", 0.0f, i2f(FloatTests, 0));
    assertEqualsFloat("i2f", -0x1.0p31f, i2f(FloatTests, 0x80000000));
    assertEqualsFloat("i2f", 0x1.0p31f, i2f(FloatTests, 0x7fffffff));

    assertEqualsInt("f2i", 0, f2i(FloatTests, 0.0f));
    assertEqualsInt("f2i", 0, f2i(FloatTests, 0.0f/0.0f));
    assertEqualsInt("f2i", -128, f2i(FloatTests, -128.75f));
    assertEqualsInt("f2i", 128, f2i(FloatTests, 128.75f));
    assertEqualsInt("f2i", 0x80000000, f2i(FloatTests, -1.0f/0.0f));
    assertEqualsInt("f2i", 0x7fffffff, f2i(FloatTests, 1.0f/0.0f));
    assertEqualsInt("f2i", 0x80000000, f2i(FloatTests, -0x1.fffffeP+127f));
    assertEqualsInt("f2i", 0x7fffffff, f2i(FloatTests, 0x1.fffffeP+127f));

    assertEqualsFloat("l2f", 0.0f, l2f(FloatTests, 0));
    assertEqualsFloat("l2f", -0x1.0p63f, l2f(FloatTests, 0x8000000000000000L));
    assertEqualsFloat("l2f", 0x1.0p63f, l2f(FloatTests, 0x7fffffffffffffffL));

    assertEqualsLong("f2l", 0L, f2l(FloatTests, 0.0f));
    assertEqualsLong("f2l", 0L, f2l(FloatTests, 0.0f/0.0f));
    assertEqualsLong("f2l", -128L, f2l(FloatTests, -128.75f));
    assertEqualsLong("f2l", 128L, f2l(FloatTests, 128.75f));
    assertEqualsLong("f2l", 0x8000000000000000L, f2l(FloatTests, -1.0f/0.0f));
    assertEqualsLong("f2l", 0x7fffffffffffffffL, f2l(FloatTests, 1.0f/0.0f));
    assertEqualsLong("f2l", 0x8000000000000000L, f2l(FloatTests, -0x1.fffffeP+127f));
    assertEqualsLong("f2l", 0x7fffffffffffffffL, f2l(FloatTests, 0x1.fffffeP+127f));

    assertEqualsInt("fcmpl", 0, fcmpl(FloatTests, 0.0f, 0.0f));
    assertEqualsInt("fcmpl", 0, fcmpl(FloatTests, 0.0f, -0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, 0.0f/0.0f, 0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, 0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, 0.0f/0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, -0x1.fffffeP+127f, -0x1.fffffdP+127f));
    assertEqualsInt("fcmpl", 1, fcmpl(FloatTests, -0x1.fffffdP+127f, -0x1.fffffeP+127f));
    assertEqualsInt("fcmpl", 1, fcmpl(FloatTests, 0x1.fffffeP+127f, 0x1.fffffdP+127f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, 0x1.fffffdP+127f, 0x1.fffffeP+127f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, -0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpl", 1, fcmpl(FloatTests, 0.0f, -0x0.000002P-126f));
    assertEqualsInt("fcmpl", 1, fcmpl(FloatTests, 0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(FloatTests, 0.0f, 0x0.000002P-126f));

    assertEqualsInt("fcmpg", 0, fcmpg(FloatTests, 0.0f, 0.0f));
    assertEqualsInt("fcmpg", 0, fcmpg(FloatTests, 0.0f, -0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0.0f/0.0f, 0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0.0f/0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpg", -1, fcmpg(FloatTests, -0x1.fffffeP+127f, -0x1.fffffdP+127f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, -0x1.fffffdP+127f, -0x1.fffffeP+127f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0x1.fffffeP+127f, 0x1.fffffdP+127f));
    assertEqualsInt("fcmpg", -1, fcmpg(FloatTests, 0x1.fffffdP+127f, 0x1.fffffeP+127f));
    assertEqualsInt("fcmpg", -1, fcmpg(FloatTests, -0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0.0f, -0x0.000002P-126f));
    assertEqualsInt("fcmpg", 1, fcmpg(FloatTests, 0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpg", -1, fcmpg(FloatTests, 0.0f, 0x0.000002P-126f));

    print_test_result();

    return 0;
}


