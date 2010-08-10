#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    Class* DoubleTests = nvmGetClass("org/nullvm/compiler/tests/opcode/DoubleOpcodes", "org_nullvm_compiler_tests_opcode_DoubleOpcodes", NULL);
    jdouble (*dreturn)(jdouble) = j_get_method_impl(DoubleTests, "dreturn", "(D)D", DoubleTests);
    jdouble (*dstore)(jdouble) = j_get_method_impl(DoubleTests, "dstore", "(D)D", DoubleTests);
    jdouble (*dconst_0)(void) = j_get_method_impl(DoubleTests, "dconst_0", "()D", DoubleTests);
    jdouble (*dconst_1)(void) = j_get_method_impl(DoubleTests, "dconst_1", "()D", DoubleTests);
    jdouble (*dconst_2)(void) = j_get_method_impl(DoubleTests, "dconst_2", "()D", DoubleTests);
    jdouble (*dadd)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "dadd", "(DD)D", DoubleTests);
    jdouble (*dsub)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "dsub", "(DD)D", DoubleTests);
    jdouble (*dmul)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "dmul", "(DD)D", DoubleTests);
    jdouble (*ddiv)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "ddiv", "(DD)D", DoubleTests);
    jdouble (*drem)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "drem", "(DD)D", DoubleTests);
    jdouble (*dneg)(jdouble) = j_get_method_impl(DoubleTests, "dneg", "(D)D", DoubleTests);
    jdouble (*ldc_min_double)(void) = j_get_method_impl(DoubleTests, "ldc_min_double", "()D", DoubleTests);
    jdouble (*ldc_max_double)(void) = j_get_method_impl(DoubleTests, "ldc_max_double", "()D", DoubleTests);
    jdouble (*i2d)(jint) = j_get_method_impl(DoubleTests, "i2d", "(I)D", DoubleTests);
    jint (*d2i)(jdouble) = j_get_method_impl(DoubleTests, "d2i", "(D)I", DoubleTests);
    jdouble (*l2d)(jlong) = j_get_method_impl(DoubleTests, "l2d", "(J)D", DoubleTests);
    jlong (*d2l)(jdouble) = j_get_method_impl(DoubleTests, "d2l", "(D)J", DoubleTests);
    jdouble (*f2d)(jfloat) = j_get_method_impl(DoubleTests, "f2d", "(F)D", DoubleTests);
    jfloat (*d2f)(jdouble) = j_get_method_impl(DoubleTests, "d2f", "(D)F", DoubleTests);
    jint (*dcmpl)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "dcmpl", "(DD)I", DoubleTests);
    jint (*dcmpg)(jdouble, jdouble) = j_get_method_impl(DoubleTests, "dcmpg", "(DD)I", DoubleTests);

    assertEqualsDouble("dreturn", 0.0, dreturn(0.0));
    assertEqualsDouble("dreturn", 0x1.fffffffffffffP+1023, dreturn(0x1.fffffffffffffP+1023));

    assertEqualsDouble("dstore", -0.12345, dstore(-0.12345));
    assertEqualsDouble("dstore", 0.12345, dstore(0.12345));

    assertEqualsDouble("dconst_0", 0.0, dconst_0());
    assertEqualsDouble("dconst_1", 1.0, dconst_1());

    assertEqualsDouble("dadd", 0.0, dadd(0.0, 0.0));
    assertEqualsDouble("dadd", -0.5, dadd(-0.25, -0.25));
    assertEqualsDouble("dadd", 0.75, dadd(0.25, 0.5));
    assertEqualsDouble("dadd", 0.75, dadd(0.5, 0.25));
    assertEqualsDouble("dadd", 0.25, dadd(0.5, -0.25));

    assertEqualsDouble("dsub", 0.0, dsub(0.0, 0.0));
    assertEqualsDouble("dsub", 0.0, dsub(-0.25, -0.25));
    assertEqualsDouble("dsub", -0.25, dsub(0.25, 0.5));
    assertEqualsDouble("dsub", 0.25, dsub(0.5, 0.25));
    assertEqualsDouble("dsub", 0.75, dsub(0.5, -0.25));

    assertEqualsDouble("dmul", 0.0, dmul(0.0, 0.0));
    assertEqualsDouble("dmul", 1.25, dmul(0.5, 2.5));
    assertEqualsDouble("dmul", 1.25, dmul(2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(-2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(2.5, -0.5));
    assertEqualsDouble("dmul", 1.25, dmul(-2.5, -0.5));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(0x1.fffffffffffffP+1023, 2.0));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(1.0f/0.0, 2.0));
    assertEqualsDouble("dmul", -1.0/0.0, dmul(2.0, -1.0f/0.0));
    assertEqualsDouble("dmul", 0.0/0.0, dmul(2.0, 0.0f/0.0));

    assertEqualsDouble("ddiv", 0.0/0.0, ddiv(0.0, 0.0));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(2.0, 0.0));
    assertEqualsDouble("ddiv", -1.0/0.0, ddiv(-2.0, 0.0));
    assertEqualsDouble("ddiv", 0.2, ddiv(0.5, 2.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(-2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(2.5, -0.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(-2.5, -0.5));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(0x1.fffffffffffffP+1023, 0.5));

    assertEqualsDouble("drem", 0.0, drem(0.0, 1.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(0.0, 0.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(1.0, 0.0));
    assertEqualsDouble("drem", 1.25, drem(5.75, 1.5));
    assertEqualsDouble("drem", -1.25, drem(-5.75, 1.5));
    assertEqualsDouble("drem", 1.25, drem(5.75, -1.5));
    assertEqualsDouble("drem", -1.25, drem(-5.75, -1.5));

    assertEqualsDouble("dneg", -0.0, dneg(0.0));
    assertEqualsDouble("dneg", -1.0, dneg(1.0));
    assertEqualsDouble("dneg", 1.0, dneg(-1.0));
    assertEqualsDouble("dneg", -0x0.0000000000001P-1022, dneg(0x0.0000000000001P-1022));
    assertEqualsDouble("dneg", -0x1.fffffffffffffP+1023, dneg(0x1.fffffffffffffP+1023));
    assertEqualsDouble("dneg", -1.0/0.0, dneg(1.0/0.0));
    assertEqualsDouble("dneg", 0.0/0.0, dneg(0.0/0.0));

    assertEqualsDouble("ldc(double)", 4.9e-324, ldc_min_double());
    assertEqualsDouble("ldc(double)", 1.7976931348623157e+308, ldc_max_double());

    assertEqualsDouble("i2d", 0.0, i2d(0));
    assertEqualsDouble("i2d", -0x1.0p31, i2d(0x80000000));
    assertEqualsDouble("i2d", 0x1.fffffffcp30, i2d(0x7fffffff));

    assertEqualsDouble("l2d", 0.0, l2d(0));
    assertEqualsDouble("l2d", -0x1.0p63, l2d(0x8000000000000000L));
    assertEqualsDouble("l2d", 0x1.0p63, l2d(0x7fffffffffffffffL));

    assertEqualsDouble("f2d", 0.0, f2d(0.0f));
    assertEqualsDouble("f2d", 0.0/0.0, f2d(0.0f/0.0f));
    assertEqualsDouble("f2d", -1.0/0.0, f2d(-1.0f/0.0f));
    assertEqualsDouble("f2d", 1.0/0.0, f2d(1.0f/0.0f));
    assertEqualsDouble("f2d", -0x1.fffffeP+127, f2d(-0x1.fffffeP+127f));
    assertEqualsDouble("f2d", 0x1.fffffeP+127, f2d(0x1.fffffeP+127f));

    assertEqualsInt("d2i", 0, d2i(0.0));
    assertEqualsInt("d2i", 0, d2i(0.0f/0.0));
    assertEqualsInt("d2i", -128, d2i(-128.75));
    assertEqualsInt("d2i", 128, d2i(128.75));
    assertEqualsInt("d2i", 0x80000000, d2i(-1.0/0.0));
    assertEqualsInt("d2i", 0x7fffffff, d2i(1.0/0.0));
    assertEqualsInt("d2i", 0x80000000, d2i(-0x1.fffffffffffffP+1023));
    assertEqualsInt("d2i", 0x7fffffff, d2i(0x1.fffffffffffffP+1023));

    assertEqualsLong("d2l", 0L, d2l(0.0));
    assertEqualsLong("d2l", 0L, d2l(0.0f/0.0));
    assertEqualsLong("d2l", -128L, d2l(-128.75));
    assertEqualsLong("d2l", 128L, d2l(128.75));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(-1.0/0.0));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(1.0/0.0));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(-0x1.fffffffffffffP+1023));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(0x1.fffffffffffffP+1023));

    assertEqualsFloat("d2f", 0.0f, d2f(0.0));
    assertEqualsFloat("d2f", 0.0f/0.0f, d2f(0.0/0.0));
    assertEqualsFloat("d2f", -0.0f, d2f(-0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", 0.0f, d2f(0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(-1.0/0.0));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(1.0/0.0));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(-0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(-0x1.ffffffP+127));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(0x1.ffffffP+127));
    assertEqualsFloat("d2f", -0x1.fffffeP+127f, d2f(-0x1.fffffeP+127));
    assertEqualsFloat("d2f", 0x1.fffffeP+127f, d2f(0x1.fffffeP+127));

    assertEqualsInt("dcmpl", 0, dcmpl(0.0, 0.0));
    assertEqualsInt("dcmpl", 0, dcmpl(0.0, -0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(0.0/0.0, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(-0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(-0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(-0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", 1, dcmpl(0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpl", 1, dcmpl(0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(0.0, 0x0.0000000000001P-1022));

    assertEqualsInt("dcmpg", 0, dcmpg(0.0, 0.0));
    assertEqualsInt("dcmpg", 0, dcmpg(0.0, -0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(0.0/0.0, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(-0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(-0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(-0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpg", 1, dcmpg(0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(0.0, 0x0.0000000000001P-1022));

    print_test_result();

    return 0;
}


