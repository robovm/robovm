#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* DoubleTests = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/DoubleOpcodes");
    jdouble (*dreturn)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dreturn", "(D)D")->impl;
    jdouble (*dstore)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dstore", "(D)D")->impl;
    jdouble (*dconst_0)(void*, Env*) = nvmGetClassMethod(env, DoubleTests, "dconst_0", "()D")->impl;
    jdouble (*dconst_1)(void*, Env*) = nvmGetClassMethod(env, DoubleTests, "dconst_1", "()D")->impl;
    jdouble (*dadd)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dadd", "(DD)D")->impl;
    jdouble (*dsub)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dsub", "(DD)D")->impl;
    jdouble (*dmul)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dmul", "(DD)D")->impl;
    jdouble (*ddiv)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "ddiv", "(DD)D")->impl;
    jdouble (*drem)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "drem", "(DD)D")->impl;
    jdouble (*dneg)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "dneg", "(D)D")->impl;
    jdouble (*ldc_min_double)(void*, Env*) = nvmGetClassMethod(env, DoubleTests, "ldc_min_double", "()D")->impl;
    jdouble (*ldc_max_double)(void*, Env*) = nvmGetClassMethod(env, DoubleTests, "ldc_max_double", "()D")->impl;
    jdouble (*i2d)(void*, Env*, jint) = nvmGetClassMethod(env, DoubleTests, "i2d", "(I)D")->impl;
    jint (*d2i)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2i", "(D)I")->impl;
    jdouble (*l2d)(void*, Env*, jlong) = nvmGetClassMethod(env, DoubleTests, "l2d", "(J)D")->impl;
    jlong (*d2l)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2l", "(D)J")->impl;
    jdouble (*f2d)(void*, Env*, jfloat) = nvmGetClassMethod(env, DoubleTests, "f2d", "(F)D")->impl;
    jfloat (*d2f)(void*, Env*, jdouble) = nvmGetClassMethod(env, DoubleTests, "d2f", "(D)F")->impl;
    jint (*dcmpl)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dcmpl", "(DD)I")->impl;
    jint (*dcmpg)(void*, Env*, jdouble, jdouble) = nvmGetClassMethod(env, DoubleTests, "dcmpg", "(DD)I")->impl;

    assertEqualsDouble("dreturn", 0.0, dreturn(NULL, env, 0.0));
    assertEqualsDouble("dreturn", 0x1.fffffffffffffP+1023, dreturn(NULL, env, 0x1.fffffffffffffP+1023));

    assertEqualsDouble("dstore", -0.12345, dstore(NULL, env, -0.12345));
    assertEqualsDouble("dstore", 0.12345, dstore(NULL, env, 0.12345));

    assertEqualsDouble("dconst_0", 0.0, dconst_0(NULL, env));
    assertEqualsDouble("dconst_1", 1.0, dconst_1(NULL, env));

    assertEqualsDouble("dadd", 0.0, dadd(NULL, env, 0.0, 0.0));
    assertEqualsDouble("dadd", -0.5, dadd(NULL, env, -0.25, -0.25));
    assertEqualsDouble("dadd", 0.75, dadd(NULL, env, 0.25, 0.5));
    assertEqualsDouble("dadd", 0.75, dadd(NULL, env, 0.5, 0.25));
    assertEqualsDouble("dadd", 0.25, dadd(NULL, env, 0.5, -0.25));

    assertEqualsDouble("dsub", 0.0, dsub(NULL, env, 0.0, 0.0));
    assertEqualsDouble("dsub", 0.0, dsub(NULL, env, -0.25, -0.25));
    assertEqualsDouble("dsub", -0.25, dsub(NULL, env, 0.25, 0.5));
    assertEqualsDouble("dsub", 0.25, dsub(NULL, env, 0.5, 0.25));
    assertEqualsDouble("dsub", 0.75, dsub(NULL, env, 0.5, -0.25));

    assertEqualsDouble("dmul", 0.0, dmul(NULL, env, 0.0, 0.0));
    assertEqualsDouble("dmul", 1.25, dmul(NULL, env, 0.5, 2.5));
    assertEqualsDouble("dmul", 1.25, dmul(NULL, env, 2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(NULL, env, -2.5, 0.5));
    assertEqualsDouble("dmul", -1.25, dmul(NULL, env, 2.5, -0.5));
    assertEqualsDouble("dmul", 1.25, dmul(NULL, env, -2.5, -0.5));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(NULL, env, 0x1.fffffffffffffP+1023, 2.0));
    assertEqualsDouble("dmul", 1.0/0.0, dmul(NULL, env, 1.0f/0.0, 2.0));
    assertEqualsDouble("dmul", -1.0/0.0, dmul(NULL, env, 2.0, -1.0f/0.0));
    assertEqualsDouble("dmul", 0.0/0.0, dmul(NULL, env, 2.0, 0.0f/0.0));

    assertEqualsDouble("ddiv", 0.0/0.0, ddiv(NULL, env, 0.0, 0.0));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(NULL, env, 2.0, 0.0));
    assertEqualsDouble("ddiv", -1.0/0.0, ddiv(NULL, env, -2.0, 0.0));
    assertEqualsDouble("ddiv", 0.2, ddiv(NULL, env, 0.5, 2.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(NULL, env, 2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(NULL, env, -2.5, 0.5));
    assertEqualsDouble("ddiv", -5.0, ddiv(NULL, env, 2.5, -0.5));
    assertEqualsDouble("ddiv", 5.0, ddiv(NULL, env, -2.5, -0.5));
    assertEqualsDouble("ddiv", 1.0/0.0, ddiv(NULL, env, 0x1.fffffffffffffP+1023, 0.5));

    assertEqualsDouble("drem", 0.0, drem(NULL, env, 0.0, 1.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(NULL, env, 0.0, 0.0));
    assertEqualsDouble("drem", 0.0/0.0, drem(NULL, env, 1.0, 0.0));
    assertEqualsDouble("drem", 1.25, drem(NULL, env, 5.75, 1.5));
    assertEqualsDouble("drem", -1.25, drem(NULL, env, -5.75, 1.5));
    assertEqualsDouble("drem", 1.25, drem(NULL, env, 5.75, -1.5));
    assertEqualsDouble("drem", -1.25, drem(NULL, env, -5.75, -1.5));

    assertEqualsDouble("dneg", -0.0, dneg(NULL, env, 0.0));
    assertEqualsDouble("dneg", -1.0, dneg(NULL, env, 1.0));
    assertEqualsDouble("dneg", 1.0, dneg(NULL, env, -1.0));
    assertEqualsDouble("dneg", -0x0.0000000000001P-1022, dneg(NULL, env, 0x0.0000000000001P-1022));
    assertEqualsDouble("dneg", -0x1.fffffffffffffP+1023, dneg(NULL, env, 0x1.fffffffffffffP+1023));
    assertEqualsDouble("dneg", -1.0/0.0, dneg(NULL, env, 1.0/0.0));
    assertEqualsDouble("dneg", 0.0/0.0, dneg(NULL, env, 0.0/0.0));

    assertEqualsDouble("ldc(double)", 4.9e-324, ldc_min_double(NULL, env));
    assertEqualsDouble("ldc(double)", 1.7976931348623157e+308, ldc_max_double(NULL, env));

    assertEqualsDouble("i2d", 0.0, i2d(NULL, env, 0));
    assertEqualsDouble("i2d", -0x1.0p31, i2d(NULL, env, 0x80000000));
    assertEqualsDouble("i2d", 0x1.fffffffcp30, i2d(NULL, env, 0x7fffffff));

    assertEqualsDouble("l2d", 0.0, l2d(NULL, env, 0));
    assertEqualsDouble("l2d", -0x1.0p63, l2d(NULL, env, 0x8000000000000000L));
    assertEqualsDouble("l2d", 0x1.0p63, l2d(NULL, env, 0x7fffffffffffffffL));

    assertEqualsDouble("f2d", 0.0, f2d(NULL, env, 0.0f));
    assertEqualsDouble("f2d", 0.0/0.0, f2d(NULL, env, 0.0f/0.0f));
    assertEqualsDouble("f2d", -1.0/0.0, f2d(NULL, env, -1.0f/0.0f));
    assertEqualsDouble("f2d", 1.0/0.0, f2d(NULL, env, 1.0f/0.0f));
    assertEqualsDouble("f2d", -0x1.fffffeP+127, f2d(NULL, env, -0x1.fffffeP+127f));
    assertEqualsDouble("f2d", 0x1.fffffeP+127, f2d(NULL, env, 0x1.fffffeP+127f));

    assertEqualsInt("d2i", 0, d2i(NULL, env, 0.0));
    assertEqualsInt("d2i", 0, d2i(NULL, env, 0.0f/0.0));
    assertEqualsInt("d2i", -128, d2i(NULL, env, -128.75));
    assertEqualsInt("d2i", 128, d2i(NULL, env, 128.75));
    assertEqualsInt("d2i", 0x80000000, d2i(NULL, env, -1.0/0.0));
    assertEqualsInt("d2i", 0x7fffffff, d2i(NULL, env, 1.0/0.0));
    assertEqualsInt("d2i", 0x80000000, d2i(NULL, env, -0x1.fffffffffffffP+1023));
    assertEqualsInt("d2i", 0x7fffffff, d2i(NULL, env, 0x1.fffffffffffffP+1023));

    assertEqualsLong("d2l", 0L, d2l(NULL, env, 0.0));
    assertEqualsLong("d2l", 0L, d2l(NULL, env, 0.0f/0.0));
    assertEqualsLong("d2l", -128L, d2l(NULL, env, -128.75));
    assertEqualsLong("d2l", 128L, d2l(NULL, env, 128.75));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(NULL, env, -1.0/0.0));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(NULL, env, 1.0/0.0));
    assertEqualsLong("d2l", 0x8000000000000000L, d2l(NULL, env, -0x1.fffffffffffffP+1023));
    assertEqualsLong("d2l", 0x7fffffffffffffffL, d2l(NULL, env, 0x1.fffffffffffffP+1023));

    assertEqualsFloat("d2f", 0.0f, d2f(NULL, env, 0.0));
    assertEqualsFloat("d2f", 0.0f/0.0f, d2f(NULL, env, 0.0/0.0));
    assertEqualsFloat("d2f", -0.0f, d2f(NULL, env, -0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", 0.0f, d2f(NULL, env, 0x0.0000000000001P-1022));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(NULL, env, -1.0/0.0));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(NULL, env, 1.0/0.0));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(NULL, env, -0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(NULL, env, 0x1.fffffffffffffP+1023));
    assertEqualsFloat("d2f", -1.0f/0.0f, d2f(NULL, env, -0x1.ffffffP+127));
    assertEqualsFloat("d2f", 1.0f/0.0f, d2f(NULL, env, 0x1.ffffffP+127));
    assertEqualsFloat("d2f", -0x1.fffffeP+127f, d2f(NULL, env, -0x1.fffffeP+127));
    assertEqualsFloat("d2f", 0x1.fffffeP+127f, d2f(NULL, env, 0x1.fffffeP+127));

    assertEqualsInt("dcmpl", 0, dcmpl(NULL, env, 0.0, 0.0));
    assertEqualsInt("dcmpl", 0, dcmpl(NULL, env, 0.0, -0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(NULL, env, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", 1, dcmpl(NULL, env, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", 1, dcmpl(NULL, env, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpl", 1, dcmpl(NULL, env, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpl", -1, dcmpl(NULL, env, 0.0, 0x0.0000000000001P-1022));

    assertEqualsInt("dcmpg", 0, dcmpg(NULL, env, 0.0, 0.0));
    assertEqualsInt("dcmpg", 0, dcmpg(NULL, env, 0.0, -0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0.0/0.0, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0.0/0.0, 0.0/0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(NULL, env, -0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, -0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(NULL, env, 0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
    assertEqualsInt("dcmpg", -1, dcmpg(NULL, env, -0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0.0, -0x0.0000000000001P-1022));
    assertEqualsInt("dcmpg", 1, dcmpg(NULL, env, 0x0.0000000000001P-1022, 0.0));
    assertEqualsInt("dcmpg", -1, dcmpg(NULL, env, 0.0, 0x0.0000000000001P-1022));

    print_test_result();

    return 0;
}


