#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* IntTests = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/IntOpcodes");
    jint (*ireturn)(Env*, jint) = nvmGetClassMethod(env, IntTests, "ireturn", "(I)I")->impl;
    jint (*istore)(Env*, jint) = nvmGetClassMethod(env, IntTests, "istore", "(I)I")->impl;
    jint (*iconst_m1)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_m1", "()I")->impl;
    jint (*iconst_0)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_0", "()I")->impl;
    jint (*iconst_1)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_1", "()I")->impl;
    jint (*iconst_2)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_2", "()I")->impl;
    jint (*iconst_3)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_3", "()I")->impl;
    jint (*iconst_4)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_4", "()I")->impl;
    jint (*iconst_5)(Env*) = nvmGetClassMethod(env, IntTests, "iconst_5", "()I")->impl;
    jint (*iadd)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "iadd", "(II)I")->impl;
    jint (*isub)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "isub", "(II)I")->impl;
    jint (*imul)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "imul", "(II)I")->impl;
    jint (*idiv)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "idiv", "(II)I")->impl;
    jint (*irem)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "irem", "(II)I")->impl;
    jint (*ineg)(Env*, jint) = nvmGetClassMethod(env, IntTests, "ineg", "(I)I")->impl;
    jint (*ishl)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "ishl", "(II)I")->impl;
    jint (*ishr)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "ishr", "(II)I")->impl;
    jint (*iushr)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "iushr", "(II)I")->impl;
    jint (*iand)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "iand", "(II)I")->impl;
    jint (*ior)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "ior", "(II)I")->impl;
    jint (*ixor)(Env*, jint, jint) = nvmGetClassMethod(env, IntTests, "ixor", "(II)I")->impl;
    jint (*i2b)(Env*, jint) = nvmGetClassMethod(env, IntTests, "i2b", "(I)I")->impl;
    jint (*i2c)(Env*, jint) = nvmGetClassMethod(env, IntTests, "i2c", "(I)I")->impl;
    jint (*i2s)(Env*, jint) = nvmGetClassMethod(env, IntTests, "i2s", "(I)I")->impl;
    jint (*bipush_m1)(Env*) = nvmGetClassMethod(env, IntTests, "bipush_m1", "()I")->impl;
    jint (*bipush_127)(Env*) = nvmGetClassMethod(env, IntTests, "bipush_127", "()I")->impl;
    jint (*sipush_m1)(Env*) = nvmGetClassMethod(env, IntTests, "sipush_m1", "()I")->impl;
    jint (*sipush_32767)(Env*) = nvmGetClassMethod(env, IntTests, "sipush_32767", "()I")->impl;
    jint (*ldc_min_int)(Env*) = nvmGetClassMethod(env, IntTests, "ldc_min_int", "()I")->impl;
    jint (*ldc_max_int)(Env*) = nvmGetClassMethod(env, IntTests, "ldc_max_int", "()I")->impl;
    jint (*iinc_m128)(Env*, jint) = nvmGetClassMethod(env, IntTests, "iinc_m128", "(I)I")->impl;
    jint (*iinc_127)(Env*, jint) = nvmGetClassMethod(env, IntTests, "iinc_127", "(I)I")->impl;

    assertEqualsInt("ireturn", -100, ireturn(env, -100));
    assertEqualsInt("ireturn", 0x7fffffff, ireturn(env, 0x7fffffff));
    assertEqualsInt("ireturn", 0x80000000, ireturn(env, 0x80000000));

    assertEqualsInt("istore", -1000000, istore(env, -1000000));
    assertEqualsInt("istore", 1000000, istore(env, 1000000));

    assertEqualsInt("iconst_m1", -1, iconst_m1(env));
    assertEqualsInt("iconst_0", 0, iconst_0(env));
    assertEqualsInt("iconst_1", 1, iconst_1(env));
    assertEqualsInt("iconst_2", 2, iconst_2(env));
    assertEqualsInt("iconst_3", 3, iconst_3(env));
    assertEqualsInt("iconst_4", 4, iconst_4(env));
    assertEqualsInt("iconst_5", 5, iconst_5(env));

    assertEqualsInt("iadd", 0, iadd(env, 0, 0));
    assertEqualsInt("iadd", -2, iadd(env, -1, -1));
    assertEqualsInt("iadd", 30, iadd(env, 10, 20));
    assertEqualsInt("iadd", 30, iadd(env, 20, 10));
    assertEqualsInt("iadd", 4, iadd(env, 5, -1));
    assertEqualsInt("iadd", 0x80000000, iadd(env, 0x7fffffff, 1));

    assertEqualsInt("isub", 0, isub(env, 0, 0));
    assertEqualsInt("isub", 0, isub(env, -1, -1));
    assertEqualsInt("isub", -10, isub(env, 10, 20));
    assertEqualsInt("isub", 10, isub(env, 20, 10));
    assertEqualsInt("isub", 6, isub(env, 5, -1));
    assertEqualsInt("isub", 0x7fffffff, isub(env, 0x80000000, 1));

    assertEqualsInt("imul", 0, imul(env, 0, 0));
    assertEqualsInt("imul", 0, imul(env, 10, 0));
    assertEqualsInt("imul", 0, imul(env, 0, 10));
    assertEqualsInt("imul", 39483, imul(env, 123, 321));
    assertEqualsInt("imul", 39483, imul(env, 321, 123));
    assertEqualsInt("imul", -242, imul(env, -11, 22));
    assertEqualsInt("imul", -242, imul(env, 11, -22));
    assertEqualsInt("imul", 242, imul(env, -11, -22));
    assertEqualsInt("imul", 1, imul(env, 0x7fffffff, 0x7fffffff));
    assertEqualsInt("imul", -164888576, imul(env, -2000000000, -1500000000));

    assertEqualsInt("idiv", 0, idiv(env, 0, 10));
    assertEqualsInt("idiv", 0, idiv(env, 0, -10));
    assertEqualsInt("idiv", 5, idiv(env, 10, 2));
    assertEqualsInt("idiv", 5, idiv(env, 11, 2));
    assertEqualsInt("idiv", -5, idiv(env, 10, -2));
    assertEqualsInt("idiv", -5, idiv(env, -10, 2));
    assertEqualsInt("idiv", 5, idiv(env, -10, -2));
    assertEqualsInt("idiv", 0, idiv(env, 0xffffffff, 2));
    assertEqualsInt("idiv", 0x3fffffff, idiv(env, 0x7fffffff, 2));
//    assertEqualsInt("idiv", 0, idiv(env, 10, 0));

    assertEqualsInt("irem", 0, irem(env, 0, 10));
    assertEqualsInt("irem", 1, irem(env, 11, 10));
    assertEqualsInt("irem", 9, irem(env, 19, 10));
    assertEqualsInt("irem", -9, irem(env, -19, 10));
    assertEqualsInt("irem", 9, irem(env, 19, -10));
    assertEqualsInt("irem", -9, irem(env, -19, -10));
    assertEqualsInt("irem", 0, irem(env, 20, 10));
//    assertEqualsInt("irem", 0, irem(1env, 00, 0));

    assertEqualsInt("ineg", 0, ineg(env, 0));
    assertEqualsInt("ineg", -1, ineg(env, 1));
    assertEqualsInt("ineg", 1, ineg(env, -1));
    assertEqualsInt("ineg", 0x80000001, ineg(env, 0x7fffffff));
    assertEqualsInt("ineg", 0x80000000, ineg(env, 0x80000000));

    assertEqualsInt("ishl", 0, ishl(env, 0, 0));
    assertEqualsInt("ishl", 0, ishl(env, 0, 10));
    assertEqualsInt("ishl", 1, ishl(env, 1, 0));
    assertEqualsInt("ishl", 2, ishl(env, 1, 1));
    assertEqualsInt("ishl", 4, ishl(env, 1, 2));
    assertEqualsInt("ishl", 0x80000000, ishl(env, 1, 31));
    assertEqualsInt("ishl", 0xffff8000, ishl(env, 0xffffffff, 15));
    assertEqualsInt("ishl", 1, ishl(env, 1, 32));
    assertEqualsInt("ishl", 2, ishl(env, 1, 33));
    assertEqualsInt("ishl", 4, ishl(env, 1, 34));
    assertEqualsInt("ishl", 0x80000000, ishl(env, 1, -1));

    assertEqualsInt("ishr", 0, ishr(env, 0, 0));
    assertEqualsInt("ishr", 0, ishr(env, 0, 1));
    assertEqualsInt("ishr", 1, ishr(env, 1, 0));
    assertEqualsInt("ishr", 0, ishr(env, 1, 1));
    assertEqualsInt("ishr", 16, ishr(env, 32, 1));
    assertEqualsInt("ishr", 8, ishr(env, 32, 2));
    assertEqualsInt("ishr", 4, ishr(env, 32, 3));
    assertEqualsInt("ishr", -1, ishr(env, -1, 1));
    assertEqualsInt("ishr", -1, ishr(env, -1, 2));
    assertEqualsInt("ishr", -4, ishr(env, -16, 2));
    assertEqualsInt("ishr", 0xf8000000, ishr(env, 0x80000000, 4));
    assertEqualsInt("ishr", 0x80000000, ishr(env, 0x80000000, 32));
    assertEqualsInt("ishr", 0xc0000000, ishr(env, 0x80000000, 33));
    assertEqualsInt("ishr", 0xe0000000, ishr(env, 0x80000000, 34));
    assertEqualsInt("ishr", -1, ishr(env, 0x80000000, -1));

    assertEqualsInt("iushr", 0, iushr(env, 0, 0));
    assertEqualsInt("iushr", 0, iushr(env, 0, 1));
    assertEqualsInt("iushr", 1, iushr(env, 1, 0));
    assertEqualsInt("iushr", 0, iushr(env, 1, 1));
    assertEqualsInt("iushr", 16, iushr(env, 32, 1));
    assertEqualsInt("iushr", 8, iushr(env, 32, 2));
    assertEqualsInt("iushr", 4, iushr(env, 32, 3));
    assertEqualsInt("iushr", 0x7fffffff, iushr(env, -1, 1));
    assertEqualsInt("iushr", 0x08000000, iushr(env, 0x80000000, 4));
    assertEqualsInt("iushr", 0x80000000, iushr(env, 0x80000000, 32));
    assertEqualsInt("iushr", 0x40000000, iushr(env, 0x80000000, 33));
    assertEqualsInt("iushr", 0x20000000, iushr(env, 0x80000000, 34));
    assertEqualsInt("iushr", 1, iushr(env, 0x80000000, -1));

    assertEqualsInt("iand", 0, iand(env, 0, 0));
    assertEqualsInt("iand", 0, iand(env, 0, 0xffffffff));
    assertEqualsInt("iand", 0, iand(env, 0xffffffff, 0));
    assertEqualsInt("iand", 0xffffffff, iand(env, 0xffffffff, 0xffffffff));
    assertEqualsInt("iand", 0x22222222, iand(env, 0x33333333, 0x66666666));
    assertEqualsInt("iand", 0x00011000, iand(env, 0xffff1000, 0x0001ffff));

    assertEqualsInt("ior", 0, ior(env, 0, 0));
    assertEqualsInt("ior", 0xffffffff, ior(env, 0, 0xffffffff));
    assertEqualsInt("ior", 0xffffffff, ior(env, 0xffffffff, 0));
    assertEqualsInt("ior", 0xffffffff, ior(env, 0xffffffff, 0xffffffff));
    assertEqualsInt("ior", 0x77777777, ior(env, 0x33333333, 0x66666666));
    assertEqualsInt("ior", 0xffffffff, ior(env, 0xffff1000, 0x0001ffff));

    assertEqualsInt("ixor", 0, ixor(env, 0, 0));
    assertEqualsInt("ixor", 0xffffffff, ixor(env, 0, 0xffffffff));
    assertEqualsInt("ixor", 0xffffffff, ixor(env, 0xffffffff, 0));
    assertEqualsInt("ixor", 0, ixor(env, 0xffffffff, 0xffffffff));
    assertEqualsInt("ixor", 0x55555555, ixor(env, 0x33333333, 0x66666666));
    assertEqualsInt("ixor", 0xfffeefff, ixor(env, 0xffff1000, 0x0001ffff));

    assertEqualsInt("i2b", 0, i2b(env, 0));
    assertEqualsInt("i2b", 0x7f, i2b(env, 0x7f));
    assertEqualsInt("i2b", 0xffffffff, i2b(env, 0xff));
    assertEqualsInt("i2b", 0xffffff80, i2b(env, 0x80));
    assertEqualsInt("i2b", 0x78, i2b(env, 0x12345678));
    assertEqualsInt("i2b", 0xfffffffb, i2b(env, 0x123456fb));

    assertEqualsInt("i2c", 0, i2c(env, 0));
    assertEqualsInt("i2c", 0x7fff, i2c(env, 0x7fff));
    assertEqualsInt("i2c", 0xffff, i2c(env, 0xffff));
    assertEqualsInt("i2c", 0x8000, i2c(env, 0x8000));
    assertEqualsInt("i2c", 0x5678, i2c(env, 0x12345678));
    assertEqualsInt("i2c", 0xfffb, i2c(env, 0x1234fffb));

    assertEqualsInt("i2s", 0, i2s(env, 0));
    assertEqualsInt("i2s", 0x7fff, i2s(env, 0x7fff));
    assertEqualsInt("i2s", 0xffffffff, i2s(env, 0xffff));
    assertEqualsInt("i2s", 0xffff8000, i2s(env, 0x8000));
    assertEqualsInt("i2s", 0x5678, i2s(env, 0x12345678));
    assertEqualsInt("i2s", 0xfffffffb, i2s(env, 0x1234fffb));

    assertEqualsInt("bipush", -1, bipush_m1(env));
    assertEqualsInt("bipush", 127, bipush_127(env));

    assertEqualsInt("sipush", -1, sipush_m1(env));
    assertEqualsInt("sipush", 32767, sipush_32767(env));

    assertEqualsInt("ldc(int)", 0x80000000, ldc_min_int(env));
    assertEqualsInt("ldc(int)", 0x7fffffff, ldc_max_int(env));

    assertEqualsInt("iinc", 127, iinc_m128(env, 255));
    assertEqualsInt("iinc", 255, iinc_127(env, 128));

    print_test_result();

    return 0;
}


