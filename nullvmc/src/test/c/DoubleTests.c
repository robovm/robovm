#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* DoubleTests = nvmGetClass("org/nullvm/compiler/tests/opcode/DoubleOpcodes", "org_nullvm_compiler_tests_opcode_DoubleOpcodes", NULL);
    jdouble (*dreturn)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "dreturn", "(D)D", DoubleTests);
    jdouble (*dstore)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "dstore", "(D)D", DoubleTests);
    jdouble (*dconst_0)(jclass*) = j_get_method_impl(DoubleTests, "dconst_0", "()D", DoubleTests);
    jdouble (*dconst_1)(jclass*) = j_get_method_impl(DoubleTests, "dconst_1", "()D", DoubleTests);
    jdouble (*dconst_2)(jclass*) = j_get_method_impl(DoubleTests, "dconst_2", "()D", DoubleTests);
    jdouble (*dadd)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "dadd", "(DD)D", DoubleTests);
    jdouble (*dsub)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "dsub", "(DD)D", DoubleTests);
    jdouble (*dmul)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "dmul", "(DD)D", DoubleTests);
    jdouble (*ddiv)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "ddiv", "(DD)D", DoubleTests);
    jdouble (*drem)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "drem", "(DD)D", DoubleTests);
    jdouble (*dneg)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "dneg", "(D)D", DoubleTests);
    jdouble (*ldc_min_double)(jclass*) = j_get_method_impl(DoubleTests, "ldc_min_double", "()D", DoubleTests);
    jdouble (*ldc_max_double)(jclass*) = j_get_method_impl(DoubleTests, "ldc_max_double", "()D", DoubleTests);
    jdouble (*i2d)(jclass*, jint) = j_get_method_impl(DoubleTests, "i2d", "(I)D", DoubleTests);
    jint (*d2i)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "d2i", "(D)I", DoubleTests);
    jdouble (*l2d)(jclass*, jlong) = j_get_method_impl(DoubleTests, "l2d", "(J)D", DoubleTests);
    jlong (*d2l)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "d2l", "(D)J", DoubleTests);
    jdouble (*f2d)(jclass*, jfloat) = j_get_method_impl(DoubleTests, "f2d", "(F)D", DoubleTests);
    jfloat (*d2f)(jclass*, jdouble) = j_get_method_impl(DoubleTests, "d2f", "(D)F", DoubleTests);
    jint (*dcmpl)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "dcmpl", "(DD)I", DoubleTests);
    jint (*dcmpg)(jclass*, jdouble, jdouble) = j_get_method_impl(DoubleTests, "dcmpg", "(DD)I", DoubleTests);

    assertEqualsDouble("dreturn", 0.0, dreturn(DoubleTests, 0.0));
    assertEqualsDouble("dreturn", 0x1.fffffffffffffP+1023, dreturn(DoubleTests, 0x1.fffffffffffffP+1023));

    assertEqualsDouble("dstore", -0.12345, dstore(DoubleTests, -0.12345));
    assertEqualsDouble("dstore", 0.12345, dstore(DoubleTests, 0.12345));

    assertEqualsDouble("dconst_0", 0.0, dconst_0(DoubleTests));
    assertEqualsDouble("dconst_1", 1.0, dconst_1(DoubleTests));

    assertEqualsDouble("dadd", 0.0, dadd(DoubleTests, 0.0, 0.0));
    assertEqualsDouble("dadd", -0.5, dadd(DoubleTests, -0.25, -0.25));
    assertEqualsDouble("dadd", 0.75, dadd(DoubleTests, 0.25, 0.5));
    assertEqualsDouble("dadd", 0.75, dadd(DoubleTests, 0.5, 0.25));
    assertEqualsDouble("dadd", 0.25, dadd(DoubleTests, 0.5, -0.25));

    assertEqualsDouble("dsub", 0.0, dsub(DoubleTests, 0.0, 0.0));
    assertEqualsDouble("dsub", 0.0, dsub(DoubleTests, -0.25, -0.25));
    assertEqualsDouble("dsub", -0.25, dsub(DoubleTests, 0.25, 0.5));
    assertEqualsDouble("dsub", 0.25, dsub(DoubleTests, 0.5, 0.25));
    assertEqualsDouble("dsub", 0.75, dsub(DoubleTests, 0.5, -0.25));

    assertEqualsDouble("dmul", 0.0, dmul(DoubleTests, 0.0, 0.0));
    assertEqualsDouble("dmul", 1.25, dmul(DoubleTests, 0.5, 2.5));
    assertEqualsDouble("dmul", 1.25, dmul(DoubleTests, 2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(DoubleTests, -2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(DoubleTests, 2.5, -0.5));
    assertEqualsDouble("dmul", 1.25, dmul(DoubleTests, -2.5, -0.5));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(DoubleTests, 0x1.fffffffffffffP+1023, 2.0));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(DoubleTests, 1.0f/0.0, 2.0));
    assertEqualsDouble("dmul", -1.0/0.0, dmul(DoubleTests, 2.0, -1.0f/0.0));
    assertEqualsDouble("dmul", 0.0/0.0, dmul(DoubleTests, 2.0, 0.0f/0.0));

    assertEqualsDouble("ddiv", 0.0/0.0, ddiv(DoubleTests, 0.0, 0.0));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(DoubleTests, 2.0, 0.0));
    assertEqualsDouble("ddiv", -1.0/0.0, ddiv(DoubleTests, -2.0, 0.0));
    assertEqualsDouble("ddiv", 0.2, ddiv(DoubleTests, 0.5, 2.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(DoubleTests, 2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(DoubleTests, -2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(DoubleTests, 2.5, -0.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(DoubleTests, -2.5, -0.5));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(DoubleTests, 0x1.fffffffffffffP+1023, 0.5));

    assertEqualsDouble("drem", 0.0, drem(DoubleTests, 0.0, 1.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(DoubleTests, 0.0, 0.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(DoubleTests, 1.0, 0.0));
    assertEqualsDouble("drem", 1.25, drem(DoubleTests, 5.75, 1.5));
    assertEqualsDouble("drem", -1.25, drem(DoubleTests, -5.75, 1.5));
    assertEqualsDouble("drem", 1.25, drem(DoubleTests, 5.75, -1.5));
    assertEqualsDouble("drem", -1.25, drem(DoubleTests, -5.75, -1.5));

    assertEqualsDouble("dneg", -0.0, dneg(DoubleTests, 0.0));
    assertEqualsDouble("dneg", -1.0, dneg(DoubleTests, 1.0));
    assertEqualsDouble("dneg", 1.0, dneg(DoubleTests, -1.0));
    assertEqualsDouble("dneg", -0x0.0000000000001P-1022, dneg(DoubleTests, 0x0.0000000000001P-1022));
    assertEqualsDouble("dneg", -0x1.fffffffffffffP+1023, dneg(DoubleTests, 0x1.fffffffffffffP+1023));
    assertEqualsDouble("dneg", -1.0/0.0, dneg(DoubleTests, 1.0/0.0));
    assertEqualsDouble("dneg", 0.0/0.0, dneg(DoubleTests, 0.0/0.0));

    assertEqualsDouble("ldc(double)", 4.9e-324, ldc_min_double(DoubleTests));
    assertEqualsDouble("ldc(double)", 1.7976931348623157e+308, ldc_max_double(DoubleTests));

    assertEqualsDouble("i2d", 0.0, i2d(DoubleTests, 0));
    assertEqualsDouble("i2d", -0x1.0p31, i2d(DoubleTests, 0x80000000));
    assertEqualsDouble("i2d", 0x1.fffffffcp30, i2d(DoubleTests, 0x7fffffff));

    assertEqualsDouble("l2d", 0.0, l2d(DoubleTests, 0));
    assertEqualsDouble("l2d", -0x1.0p63, l2d(DoubleTests, 0x8000000000000000L));
    assertEqualsDouble("l2d", 0x1.0p63, l2d(DoubleTests, 0x7fffffffffffffffL));

    assertEqualsDouble("f2d", 0.0, f2d(DoubleTests, 0.0f));
    assertEqualsDouble("f2d", 0.0/0.0, f2d(DoubleTests, 0.0f/0.0f));
    assertEqualsDouble("f2d", -1.0/0.0, f2d(DoubleTests, -1.0f/0.0f));
    assertEqualsDouble("f2d", 1.0/0.0, f2d(DoubleTests, 1.0f/0.0f));
    assertEqualsDouble("f2d", -0x1.fffffeP+127, f2d(DoubleTests, -0x1.fffffeP+127f));
    assertEqualsDouble("f2d", 0x1.fffffeP+127, f2d(DoubleTests, 0x1.fffffeP+127f));

    assertEqualsInt("d2i", 0, d2i(DoubleTests, 0.0));
    assertEqualsInt("d2i", 0, d2i(DoubleTests, 0.0f/0.0));
    assertEqualsInt("d2i", -128, d2i(DoubleTests, -128.75));
    assertEqualsInt("d2i", 128, d2i(DoubleTests, 128.75));
    assertEqualsInt("d2i", 0x80000000, d2i(DoubleTests, -1.0/0.0));
    assertEqualsInt("d2i", 0x7fffffff, d2i(DoubleTests, 1.0/0.0));
    assertEqualsInt("d2i", 0x80000000, d2i(DoubleTests, -0x1.fffffffffffffP+1023));
    assertEqualsInt("d2i", 0x7fffffff, d2i(DoubleTests, 0x1.fffffffffffffP+1023));

    assertEqualsLong("d2l", 0L, d2l(DoubleTests, 0.0));
    assertEqualsLong("d2l", 0L, d2l(DoubleTests, 0.0f/0.0));
    assertEqualsLong("d2l", -128L, d2l(DoubleTests, -128.75));
    assertEqualsLong("d2l", 128L, d2l(DoubleTests, 128.75));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(DoubleTests, -1.0/0.0));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(DoubleTests, 1.0/0.0));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(DoubleTests, -0x1.fffffffffffffP+1023));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(DoubleTests, 0x1.fffffffffffffP+1023));

    assertEqualsFloat("d2f", 0.0f, d2f(DoubleTests, 0.0));
    assertEqualsFloat("d2f", 0.0f/0.0f, d2f(DoubleTests, 0.0/0.0));
    assertEqualsFloat("d2f", -0.0f, d2f(DoubleTests, -0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", 0.0f, d2f(DoubleTests, 0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(DoubleTests, -1.0/0.0));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(DoubleTests, 1.0/0.0));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(DoubleTests, -0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(DoubleTests, 0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(DoubleTests, -0x1.ffffffP+127));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(DoubleTests, 0x1.ffffffP+127));
    assertEqualsFloat("d2f", -0x1.fffffeP+127f, d2f(DoubleTests, -0x1.fffffeP+127));
    assertEqualsFloat("d2f", 0x1.fffffeP+127f, d2f(DoubleTests, 0x1.fffffeP+127));

    assertEqualsInt("dcmpl", 0, dcmpl(DoubleTests, 0.0, 0.0));
    assertEqualsInt("dcmpl", 0, dcmpl(DoubleTests, 0.0, -0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(DoubleTests, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(DoubleTests, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", 1, dcmpl(DoubleTests, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpl", 1, dcmpl(DoubleTests, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(DoubleTests, 0.0, 0x0.0000000000001P-1022));

    assertEqualsInt("dcmpg", 0, dcmpg(DoubleTests, 0.0, 0.0));
    assertEqualsInt("dcmpg", 0, dcmpg(DoubleTests, 0.0, -0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(DoubleTests, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(DoubleTests, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(DoubleTests, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpg", 1, dcmpg(DoubleTests, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(DoubleTests, 0.0, 0x0.0000000000001P-1022));

    print_test_result();

    return 0;
}


