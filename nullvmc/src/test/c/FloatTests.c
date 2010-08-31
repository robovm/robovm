#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* FloatOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/FloatOpcodes");
    jfloat (*freturn)(void*, Env*, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "freturn", "(F)F")->impl;
    jfloat (*fstore)(void*, Env*, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fstore", "(F)F")->impl;
    jfloat (*fconst_0)(void*, Env*) = nvmGetClassMethod(env, FloatOpcodes, "fconst_0", "()F")->impl;
    jfloat (*fconst_1)(void*, Env*) = nvmGetClassMethod(env, FloatOpcodes, "fconst_1", "()F")->impl;
    jfloat (*fconst_2)(void*, Env*) = nvmGetClassMethod(env, FloatOpcodes, "fconst_2", "()F")->impl;
    jfloat (*fadd)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fadd", "(FF)F")->impl;
    jfloat (*fsub)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fsub", "(FF)F")->impl;
    jfloat (*fmul)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fmul", "(FF)F")->impl;
    jfloat (*fdiv)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fdiv", "(FF)F")->impl;
    jfloat (*frem)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "frem", "(FF)F")->impl;
    jfloat (*fneg)(void*, Env*, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fneg", "(F)F")->impl;
    jfloat (*ldc_min_float)(void*, Env*) = nvmGetClassMethod(env, FloatOpcodes, "ldc_min_float", "()F")->impl;
    jfloat (*ldc_max_float)(void*, Env*) = nvmGetClassMethod(env, FloatOpcodes, "ldc_max_float", "()F")->impl;
    jfloat (*i2f)(void*, Env*, jint) = nvmGetClassMethod(env, FloatOpcodes, "i2f", "(I)F")->impl;
    jint (*f2i)(void*, Env*, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "f2i", "(F)I")->impl;
    jfloat (*l2f)(void*, Env*, jlong) = nvmGetClassMethod(env, FloatOpcodes, "l2f", "(J)F")->impl;
    jlong (*f2l)(void*, Env*, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "f2l", "(F)J")->impl;
    jint (*fcmpl)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fcmpl", "(FF)I")->impl;
    jint (*fcmpg)(void*, Env*, jfloat, jfloat) = nvmGetClassMethod(env, FloatOpcodes, "fcmpg", "(FF)I")->impl;

    assertEqualsFloat("freturn", 0.0f, freturn(NULL, env, 0.0f));
    assertEqualsFloat("freturn", 0x1.fffffeP+127f, freturn(NULL, env, 0x1.fffffeP+127f));

    assertEqualsFloat("fstore", -0.12345f, fstore(NULL, env, -0.12345f));
    assertEqualsFloat("fstore", 0.12345f, fstore(NULL, env, 0.12345f));

    assertEqualsFloat("fconst_0", 0.0f, fconst_0(NULL, env));
    assertEqualsFloat("fconst_1", 1.0f, fconst_1(NULL, env));
    assertEqualsFloat("fconst_2", 2.0f, fconst_2(NULL, env));

    assertEqualsFloat("fadd", 0.0f, fadd(NULL, env, 0.0f, 0.0f));
    assertEqualsFloat("fadd", -0.5f, fadd(NULL, env, -0.25f, -0.25f));
    assertEqualsFloat("fadd", 0.75f, fadd(NULL, env, 0.25f, 0.5f));
    assertEqualsFloat("fadd", 0.75f, fadd(NULL, env, 0.5f, 0.25f));
    assertEqualsFloat("fadd", 0.25f, fadd(NULL, env, 0.5f, -0.25f));
    assertEqualsFloat("fadd", -1.0f/0.0f, fadd(NULL, env, -0x1.fffffeP+127f, -0x1.0P+127f));
    assertEqualsFloat("fadd", 1.0f/0.0f, fadd(NULL, env, 0x1.fffffeP+127f, 0x1.0P+127f));

    assertEqualsFloat("fsub", 0.0f, fsub(NULL, env, 0.0f, 0.0f));
    assertEqualsFloat("fsub", 0.0f, fsub(NULL, env, -0.25f, -0.25f));
    assertEqualsFloat("fsub", -0.25f, fsub(NULL, env, 0.25f, 0.5f));
    assertEqualsFloat("fsub", 0.25f, fsub(NULL, env, 0.5f, 0.25f));
    assertEqualsFloat("fsub", 0.75f, fsub(NULL, env, 0.5f, -0.25f));
    assertEqualsFloat("fsub", -1.0f/0.0f, fsub(NULL, env, -0x1.fffffeP+127f, 0x1.0P+127f));
    assertEqualsFloat("fsub", 1.0f/0.0f, fsub(NULL, env, 0x1.fffffeP+127f, -0x1.0P+127f));

    assertEqualsFloat("fmul", 0.0f, fmul(NULL, env, 0.0f, 0.0f));
    assertEqualsFloat("fmul", 1.25f, fmul(NULL, env, 0.5f, 2.5f));
    assertEqualsFloat("fmul", 1.25f, fmul(NULL, env, 2.5f, 0.5f));
    assertEqualsFloat("fmul", -1.25f, fmul(NULL, env, -2.5f, 0.5f));
    assertEqualsFloat("fmul", -1.25f, fmul(NULL, env, 2.5f, -0.5f));
    assertEqualsFloat("fmul", 1.25f, fmul(NULL, env, -2.5f, -0.5f));
    assertEqualsFloat("fmul", 1.0f/0.0f, fmul(NULL, env, 0x1.fffffeP+127f, 2.0f));
    assertEqualsFloat("fmul", 1.0f/0.0f, fmul(NULL, env, 1.0f/0.0f, 2.0f));
    assertEqualsFloat("fmul", -1.0f/0.0f, fmul(NULL, env, 2.0f, -1.0f/0.0f));
    assertEqualsFloat("fmul", 0.0f/0.0f, fmul(NULL, env, 2.0f, 0.0f/0.0f));

    assertEqualsFloat("fdiv", 0.0f/0.0f, fdiv(NULL, env, 0.0f, 0.0f));
    assertEqualsFloat("fdiv", 1.0f/0.0f, fdiv(NULL, env, 2.0f, 0.0f));
    assertEqualsFloat("fdiv", -1.0f/0.0f, fdiv(NULL, env, -2.0f, 0.0f));
    assertEqualsFloat("fdiv", 0.2f, fdiv(NULL, env, 0.5f, 2.5f));
    assertEqualsFloat("fdiv", 5.0f, fdiv(NULL, env, 2.5f, 0.5f));
    assertEqualsFloat("fdiv", -5.0f, fdiv(NULL, env, -2.5f, 0.5f));
    assertEqualsFloat("fdiv", -5.0f, fdiv(NULL, env, 2.5f, -0.5f));
    assertEqualsFloat("fdiv", 5.0f, fdiv(NULL, env, -2.5f, -0.5f));
    assertEqualsFloat("fdiv", 1.0f/0.0f, fdiv(NULL, env, 0x1.fffffeP+127f, 0.5f));

    assertEqualsFloat("frem", 0.0f, frem(NULL, env, 0.0f, 1.0f));
    assertEqualsFloat("frem", 0.0f/0.0f, frem(NULL, env, 0.0f, 0.0f));
    assertEqualsFloat("frem", 0.0f/0.0f, frem(NULL, env, 1.0f, 0.0f));
    assertEqualsFloat("frem", 1.25f, frem(NULL, env, 5.75f, 1.5f));
    assertEqualsFloat("frem", -1.25f, frem(NULL, env, -5.75f, 1.5f));
    assertEqualsFloat("frem", 1.25f, frem(NULL, env, 5.75f, -1.5f));
    assertEqualsFloat("frem", -1.25f, frem(NULL, env, -5.75f, -1.5f));

    assertEqualsFloat("fneg", 0.0f, fneg(NULL, env, 0.0f));
    assertEqualsFloat("fneg", -1.0f, fneg(NULL, env, 1.0f));
    assertEqualsFloat("fneg", 1.0f, fneg(NULL, env, -1.0f));
    assertEqualsFloat("fneg", -0x0.000002P-126f, fneg(NULL, env, 0x0.000002P-126f));
    assertEqualsFloat("fneg", -0x1.fffffeP+127f, fneg(NULL, env, 0x1.fffffeP+127f));
    assertEqualsFloat("fneg", -1.0f/0.0f, fneg(NULL, env, 1.0f/0.0f));
    assertEqualsFloat("fneg", 0.0f/0.0f, fneg(NULL, env, 0.0f/0.0f));

    assertEqualsFloat("ldc(float)", 1.4e-45f, ldc_min_float(NULL, env));
    assertEqualsFloat("ldc(float)", 3.4028235e+38f, ldc_max_float(NULL, env));

    assertEqualsFloat("i2f", 0.0f, i2f(NULL, env, 0));
    assertEqualsFloat("i2f", -0x1.0p31f, i2f(NULL, env, 0x80000000));
    assertEqualsFloat("i2f", 0x1.0p31f, i2f(NULL, env, 0x7fffffff));

    assertEqualsInt("f2i", 0, f2i(NULL, env, 0.0f));
    assertEqualsInt("f2i", 0, f2i(NULL, env, 0.0f/0.0f));
    assertEqualsInt("f2i", -128, f2i(NULL, env, -128.75f));
    assertEqualsInt("f2i", 128, f2i(NULL, env, 128.75f));
    assertEqualsInt("f2i", 0x80000000, f2i(NULL, env, -1.0f/0.0f));
    assertEqualsInt("f2i", 0x7fffffff, f2i(NULL, env, 1.0f/0.0f));
    assertEqualsInt("f2i", 0x80000000, f2i(NULL, env, -0x1.fffffeP+127f));
    assertEqualsInt("f2i", 0x7fffffff, f2i(NULL, env, 0x1.fffffeP+127f));

    assertEqualsFloat("l2f", 0.0f, l2f(NULL, env, 0));
    assertEqualsFloat("l2f", -0x1.0p63f, l2f(NULL, env, 0x8000000000000000L));
    assertEqualsFloat("l2f", 0x1.0p63f, l2f(NULL, env, 0x7fffffffffffffffL));

    assertEqualsLong("f2l", 0L, f2l(NULL, env, 0.0f));
    assertEqualsLong("f2l", 0L, f2l(NULL, env, 0.0f/0.0f));
    assertEqualsLong("f2l", -128L, f2l(NULL, env, -128.75f));
    assertEqualsLong("f2l", 128L, f2l(NULL, env, 128.75f));
    assertEqualsLong("f2l", 0x8000000000000000L, f2l(NULL, env, -1.0f/0.0f));
    assertEqualsLong("f2l", 0x7fffffffffffffffL, f2l(NULL, env, 1.0f/0.0f));
    assertEqualsLong("f2l", 0x8000000000000000L, f2l(NULL, env, -0x1.fffffeP+127f));
    assertEqualsLong("f2l", 0x7fffffffffffffffL, f2l(NULL, env, 0x1.fffffeP+127f));

    assertEqualsInt("fcmpl", 0, fcmpl(NULL, env, 0.0f, 0.0f));
    assertEqualsInt("fcmpl", 0, fcmpl(NULL, env, 0.0f, -0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, 0.0f/0.0f, 0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, 0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, 0.0f/0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, -0x1.fffffeP+127f, -0x1.fffffdP+127f));
    assertEqualsInt("fcmpl", 1, fcmpl(NULL, env, -0x1.fffffdP+127f, -0x1.fffffeP+127f));
    assertEqualsInt("fcmpl", 1, fcmpl(NULL, env, 0x1.fffffeP+127f, 0x1.fffffdP+127f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, 0x1.fffffdP+127f, 0x1.fffffeP+127f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, -0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpl", 1, fcmpl(NULL, env, 0.0f, -0x0.000002P-126f));
    assertEqualsInt("fcmpl", 1, fcmpl(NULL, env, 0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpl", -1, fcmpl(NULL, env, 0.0f, 0x0.000002P-126f));

    assertEqualsInt("fcmpg", 0, fcmpg(NULL, env, 0.0f, 0.0f));
    assertEqualsInt("fcmpg", 0, fcmpg(NULL, env, 0.0f, -0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0.0f/0.0f, 0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0.0f/0.0f, 0.0f/0.0f));
    assertEqualsInt("fcmpg", -1, fcmpg(NULL, env, -0x1.fffffeP+127f, -0x1.fffffdP+127f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, -0x1.fffffdP+127f, -0x1.fffffeP+127f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0x1.fffffeP+127f, 0x1.fffffdP+127f));
    assertEqualsInt("fcmpg", -1, fcmpg(NULL, env, 0x1.fffffdP+127f, 0x1.fffffeP+127f));
    assertEqualsInt("fcmpg", -1, fcmpg(NULL, env, -0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0.0f, -0x0.000002P-126f));
    assertEqualsInt("fcmpg", 1, fcmpg(NULL, env, 0x0.000002P-126f, 0.0f));
    assertEqualsInt("fcmpg", -1, fcmpg(NULL, env, 0.0f, 0x0.000002P-126f));

    print_test_result();

    return 0;
}


