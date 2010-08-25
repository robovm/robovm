#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* DoubleTests = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/DoubleOpcodes");
    jdouble (*dreturn)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dreturn", "(D)D")->impl;
    jdouble (*dstore)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dstore", "(D)D")->impl;
    jdouble (*dconst_0)(Env*) = nvmGetClassMethod(env, DoubleTests, "dconst_0", "()D")->impl;
    jdouble (*dconst_1)(Env*) = nvmGetClassMethod(env, DoubleTests, "dconst_1", "()D")->impl;
    jdouble (*dadd)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dadd", "(DD)D")->impl;
    jdouble (*dsub)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dsub", "(DD)D")->impl;
    jdouble (*dmul)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dmul", "(DD)D")->impl;
    jdouble (*ddiv)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "ddiv", "(DD)D")->impl;
    jdouble (*drem)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "drem", "(DD)D")->impl;
    jdouble (*dneg)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dneg", "(D)D")->impl;
    jdouble (*ldc_min_double)(Env*) = nvmGetClassMethod(env, DoubleTests, "ldc_min_double", "()D")->impl;
    jdouble (*ldc_max_double)(Env*) = nvmGetClassMethod(env, DoubleTests, "ldc_max_double", "()D")->impl;
    jdouble (*i2d)(Env*, jint) = nvmGetClassMethod(env, DoubleTests, "i2d", "(I)D")->impl;
    jint (*d2i)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2i", "(D)I")->impl;
    jdouble (*l2d)(Env*, jlong) = nvmGetClassMethod(env, DoubleTests, "l2d", "(J)D")->impl;
    jlong (*d2l)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2l", "(D)J")->impl;
    jdouble (*f2d)(Env*, jfloat) = nvmGetClassMethod(env, DoubleTests, "f2d", "(F)D")->impl;
    jfloat (*d2f)(Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2f", "(D)F")->impl;
    jint (*dcmpl)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dcmpl", "(DD)I")->impl;
    jint (*dcmpg)(Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dcmpg", "(DD)I")->impl;

    assertEqualsDouble("dreturn", 0.0, dreturn(env, 0.0));
    assertEqualsDouble("dreturn", 0x1.fffffffffffffP+1023, dreturn(env, 0x1.fffffffffffffP+1023));

    assertEqualsDouble("dstore", -0.12345, dstore(env, -0.12345));
    assertEqualsDouble("dstore", 0.12345, dstore(env, 0.12345));

    assertEqualsDouble("dconst_0", 0.0, dconst_0(env));
    assertEqualsDouble("dconst_1", 1.0, dconst_1(env));

    assertEqualsDouble("dadd", 0.0, dadd(env, 0.0, 0.0));
    assertEqualsDouble("dadd", -0.5, dadd(env, -0.25, -0.25));
    assertEqualsDouble("dadd", 0.75, dadd(env, 0.25, 0.5));
    assertEqualsDouble("dadd", 0.75, dadd(env, 0.5, 0.25));
    assertEqualsDouble("dadd", 0.25, dadd(env, 0.5, -0.25));

    assertEqualsDouble("dsub", 0.0, dsub(env, 0.0, 0.0));
    assertEqualsDouble("dsub", 0.0, dsub(env, -0.25, -0.25));
    assertEqualsDouble("dsub", -0.25, dsub(env, 0.25, 0.5));
    assertEqualsDouble("dsub", 0.25, dsub(env, 0.5, 0.25));
    assertEqualsDouble("dsub", 0.75, dsub(env, 0.5, -0.25));

    assertEqualsDouble("dmul", 0.0, dmul(env, 0.0, 0.0));
    assertEqualsDouble("dmul", 1.25, dmul(env, 0.5, 2.5));
    assertEqualsDouble("dmul", 1.25, dmul(env, 2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(env, -2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(env, 2.5, -0.5));
    assertEqualsDouble("dmul", 1.25, dmul(env, -2.5, -0.5));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(env, 0x1.fffffffffffffP+1023, 2.0));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(env, 1.0f/0.0, 2.0));
    assertEqualsDouble("dmul", -1.0/0.0, dmul(env, 2.0, -1.0f/0.0));
    assertEqualsDouble("dmul", 0.0/0.0, dmul(env, 2.0, 0.0f/0.0));

    assertEqualsDouble("ddiv", 0.0/0.0, ddiv(env, 0.0, 0.0));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(env, 2.0, 0.0));
    assertEqualsDouble("ddiv", -1.0/0.0, ddiv(env, -2.0, 0.0));
    assertEqualsDouble("ddiv", 0.2, ddiv(env, 0.5, 2.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(env, 2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(env, -2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(env, 2.5, -0.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(env, -2.5, -0.5));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(env, 0x1.fffffffffffffP+1023, 0.5));

    assertEqualsDouble("drem", 0.0, drem(env, 0.0, 1.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(env, 0.0, 0.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(env, 1.0, 0.0));
    assertEqualsDouble("drem", 1.25, drem(env, 5.75, 1.5));
    assertEqualsDouble("drem", -1.25, drem(env, -5.75, 1.5));
    assertEqualsDouble("drem", 1.25, drem(env, 5.75, -1.5));
    assertEqualsDouble("drem", -1.25, drem(env, -5.75, -1.5));

    assertEqualsDouble("dneg", -0.0, dneg(env, 0.0));
    assertEqualsDouble("dneg", -1.0, dneg(env, 1.0));
    assertEqualsDouble("dneg", 1.0, dneg(env, -1.0));
    assertEqualsDouble("dneg", -0x0.0000000000001P-1022, dneg(env, 0x0.0000000000001P-1022));
    assertEqualsDouble("dneg", -0x1.fffffffffffffP+1023, dneg(env, 0x1.fffffffffffffP+1023));
    assertEqualsDouble("dneg", -1.0/0.0, dneg(env, 1.0/0.0));
    assertEqualsDouble("dneg", 0.0/0.0, dneg(env, 0.0/0.0));

    assertEqualsDouble("ldc(double)", 4.9e-324, ldc_min_double(env));
    assertEqualsDouble("ldc(double)", 1.7976931348623157e+308, ldc_max_double(env));

    assertEqualsDouble("i2d", 0.0, i2d(env, 0));
    assertEqualsDouble("i2d", -0x1.0p31, i2d(env, 0x80000000));
    assertEqualsDouble("i2d", 0x1.fffffffcp30, i2d(env, 0x7fffffff));

    assertEqualsDouble("l2d", 0.0, l2d(env, 0));
    assertEqualsDouble("l2d", -0x1.0p63, l2d(env, 0x8000000000000000L));
    assertEqualsDouble("l2d", 0x1.0p63, l2d(env, 0x7fffffffffffffffL));

    assertEqualsDouble("f2d", 0.0, f2d(env, 0.0f));
    assertEqualsDouble("f2d", 0.0/0.0, f2d(env, 0.0f/0.0f));
    assertEqualsDouble("f2d", -1.0/0.0, f2d(env, -1.0f/0.0f));
    assertEqualsDouble("f2d", 1.0/0.0, f2d(env, 1.0f/0.0f));
    assertEqualsDouble("f2d", -0x1.fffffeP+127, f2d(env, -0x1.fffffeP+127f));
    assertEqualsDouble("f2d", 0x1.fffffeP+127, f2d(env, 0x1.fffffeP+127f));

    assertEqualsInt("d2i", 0, d2i(env, 0.0));
    assertEqualsInt("d2i", 0, d2i(env, 0.0f/0.0));
    assertEqualsInt("d2i", -128, d2i(env, -128.75));
    assertEqualsInt("d2i", 128, d2i(env, 128.75));
    assertEqualsInt("d2i", 0x80000000, d2i(env, -1.0/0.0));
    assertEqualsInt("d2i", 0x7fffffff, d2i(env, 1.0/0.0));
    assertEqualsInt("d2i", 0x80000000, d2i(env, -0x1.fffffffffffffP+1023));
    assertEqualsInt("d2i", 0x7fffffff, d2i(env, 0x1.fffffffffffffP+1023));

    assertEqualsLong("d2l", 0L, d2l(env, 0.0));
    assertEqualsLong("d2l", 0L, d2l(env, 0.0f/0.0));
    assertEqualsLong("d2l", -128L, d2l(env, -128.75));
    assertEqualsLong("d2l", 128L, d2l(env, 128.75));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(env, -1.0/0.0));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(env, 1.0/0.0));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(env, -0x1.fffffffffffffP+1023));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(env, 0x1.fffffffffffffP+1023));

    assertEqualsFloat("d2f", 0.0f, d2f(env, 0.0));
    assertEqualsFloat("d2f", 0.0f/0.0f, d2f(env, 0.0/0.0));
    assertEqualsFloat("d2f", -0.0f, d2f(env, -0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", 0.0f, d2f(env, 0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(env, -1.0/0.0));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(env, 1.0/0.0));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(env, -0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(env, 0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(env, -0x1.ffffffP+127));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(env, 0x1.ffffffP+127));
    assertEqualsFloat("d2f", -0x1.fffffeP+127f, d2f(env, -0x1.fffffeP+127));
    assertEqualsFloat("d2f", 0x1.fffffeP+127f, d2f(env, 0x1.fffffeP+127));

    assertEqualsInt("dcmpl", 0, dcmpl(env, 0.0, 0.0));
    assertEqualsInt("dcmpl", 0, dcmpl(env, 0.0, -0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(env, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(env, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(env, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(env, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(env, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(env, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(env, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(env, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", 1, dcmpl(env, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpl", 1, dcmpl(env, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(env, 0.0, 0x0.0000000000001P-1022));

    assertEqualsInt("dcmpg", 0, dcmpg(env, 0.0, 0.0));
    assertEqualsInt("dcmpg", 0, dcmpg(env, 0.0, -0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(env, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(env, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(env, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(env, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpg", 1, dcmpg(env, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(env, 0.0, 0x0.0000000000001P-1022));

    print_test_result();

    return 0;
}


