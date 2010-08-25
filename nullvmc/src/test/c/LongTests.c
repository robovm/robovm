#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* LongOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/LongOpcodes");
    jlong (*lreturn)(Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lreturn", "(J)J")->impl;
    jlong (*lstore)(Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lstore", "(J)J")->impl;
    jlong (*lconst_0)(Env*) = nvmGetClassMethod(env, LongOpcodes, "lconst_0", "()J")->impl;
    jlong (*lconst_1)(Env*) = nvmGetClassMethod(env, LongOpcodes, "lconst_1", "()J")->impl;
    jlong (*ladd)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "ladd", "(JJ)J")->impl;
    jlong (*lsub)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lsub", "(JJ)J")->impl;
    jlong (*lmul)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lmul", "(JJ)J")->impl;
    jlong (*ldiv)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "ldiv", "(JJ)J")->impl;
    jlong (*lrem)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lrem", "(JJ)J")->impl;
    jlong (*lneg)(Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lneg", "(J)J")->impl;
    jlong (*lshl)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lshl", "(JI)J")->impl;
    jlong (*lshr)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lshr", "(JI)J")->impl;
    jlong (*lushr)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lushr", "(JI)J")->impl;
    jlong (*land)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "land", "(JJ)J")->impl;
    jlong (*lor)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lor", "(JJ)J")->impl;
    jlong (*lxor)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lxor", "(JJ)J")->impl;
    jlong (*i2l)(Env*, jint) = nvmGetClassMethod(env, LongOpcodes, "i2l", "(I)J")->impl;
    jint (*l2i)(Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "l2i", "(J)I")->impl;
    jint (*lcmp)(Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lcmp", "(JJ)I")->impl;
    jlong (*ldc_min_long)(Env*) = nvmGetClassMethod(env, LongOpcodes, "ldc_min_long", "()J")->impl;
    jlong (*ldc_max_long)(Env*) = nvmGetClassMethod(env, LongOpcodes, "ldc_max_long", "()J")->impl;

    assertEqualsLong("lreturn", -100, lreturn(env, -100));
    assertEqualsLong("lreturn", 0x7fffffffffffffff, lreturn(env, 0x7fffffffffffffff));
    assertEqualsLong("lreturn", 0x8000000000000000, lreturn(env, 0x8000000000000000));

    assertEqualsLong("lstore", -1000000000000, lstore(env, -1000000000000));
    assertEqualsLong("lstore", 1000000000000, lstore(env, 1000000000000));

    assertEqualsLong("lconst_0", 0, lconst_0(env));
    assertEqualsLong("lconst_1", 1, lconst_1(env));

    assertEqualsLong("ladd", 0, ladd(env, 0, 0));
    assertEqualsLong("ladd", 30, ladd(env, 20, 10));
    assertEqualsLong("ladd", -10, ladd(env, -20, 10));
    assertEqualsLong("ladd", 10, ladd(env, 20, -10));
    assertEqualsLong("ladd", -30, ladd(env, -20, -10));
    assertEqualsLong("ladd", 0x7fffffffffffffff, ladd(env, 0x8000000000000000, -1));  
    assertEqualsLong("ladd", 0x8000000000000000, ladd(env, 0x7fffffffffffffff, 1));  

    assertEqualsLong("lsub", 0, lsub(env, 0, 0));
    assertEqualsLong("lsub", 10, lsub(env, 20, 10));
    assertEqualsLong("lsub", -30, lsub(env, -20, 10));
    assertEqualsLong("lsub", 30, lsub(env, 20, -10));
    assertEqualsLong("lsub", -10, lsub(env, -20, -10));
    assertEqualsLong("lsub", 0x7fffffffffffffffL, lsub(env, 0x8000000000000000L, 1));  
    assertEqualsLong("lsub", 0x8000000000000000L, lsub(env, 0x7fffffffffffffffL, -1));  

    assertEqualsLong("lmul", 0, lmul(env, 0, 0));
    assertEqualsLong("lmul", 0, lmul(env, 10, 0));
    assertEqualsLong("lmul", 0, lmul(env, 0, 10));
    assertEqualsLong("lmul", 39483, lmul(env, 123, 321));
    assertEqualsLong("lmul", 39483, lmul(env, 321, 123));
    assertEqualsLong("lmul", -242, lmul(env, -11, 22));
    assertEqualsLong("lmul", -242, lmul(env, 11, -22));
    assertEqualsLong("lmul", 242, lmul(env, -11, -22));
    assertEqualsLong("lmul", 1, lmul(env, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsLong("lmul", 3000000000000000000L, lmul(env, -2000000000, -1500000000));
    assertEqualsLong("lmul", 6011292615620689920L, lmul(env, -2000000000000L, -1500000000000L));

    assertEqualsLong("ldiv", 0, ldiv(env, 0, 10));
    assertEqualsLong("ldiv", 0, ldiv(env, 0, -10));
    assertEqualsLong("ldiv", 5, ldiv(env, 10, 2));
    assertEqualsLong("ldiv", 5, ldiv(env, 11, 2));
    assertEqualsLong("ldiv", -5, ldiv(env, 10, -2));
    assertEqualsLong("ldiv", -5, ldiv(env, -10, 2));
    assertEqualsLong("ldiv", 5, ldiv(env, -10, -2));
    assertEqualsLong("ldiv", 0, ldiv(env, 0xffffffffffffffffL, 2));
    assertEqualsLong("ldiv", 0x1999999999999999L, ldiv(env, 0x6666666666666666L, 4));
    assertEqualsLong("ldiv", 0x3fffffffffffffffL, ldiv(env, 0x7fffffffffffffffL, 2));
//    assertEqualsLong("ldiv", 0, ldiv(env, 10, 0));

    assertEqualsLong("lrem", 0, lrem(env, 0, 10));
    assertEqualsLong("lrem", 1, lrem(env, 11, 10));
    assertEqualsLong("lrem", 9, lrem(env, 19, 10));
    assertEqualsLong("lrem", -9, lrem(env, -19, 10));
    assertEqualsLong("lrem", 9, lrem(env, 19, -10));
    assertEqualsLong("lrem", -9, lrem(env, -19, -10));
    assertEqualsLong("lrem", 0, lrem(env, 20, 10));
    assertEqualsLong("lrem", 100, lrem(env, 4000000000100L, 4000000000000L));
//    assertEqualsLong("lrem", 0, lrem(env, 100, 0));

    assertEqualsLong("lneg", 0, lneg(env, 0));
    assertEqualsLong("lneg", -1, lneg(env, 1));
    assertEqualsLong("lneg", 1, lneg(env, -1));
    assertEqualsLong("lneg", 0x8000000000000001L, lneg(env, 0x7fffffffffffffffL));
    assertEqualsLong("lneg", 0x8000000000000000L, lneg(env, 0x8000000000000000L));

    assertEqualsLong("lshl", 0, lshl(env, 0, 0));
    assertEqualsLong("lshl", 0, lshl(env, 0, 10));
    assertEqualsLong("lshl", 1, lshl(env, 1, 0));
    assertEqualsLong("lshl", 2, lshl(env, 1, 1));
    assertEqualsLong("lshl", 4, lshl(env, 1, 2));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(env, 1, 63));
    assertEqualsLong("lshl", 0xffffffff80000000L, lshl(env, 0xffffffffffffffffL, 31));
    assertEqualsLong("lshl", 1, lshl(env, 1, 64));
    assertEqualsLong("lshl", 2, lshl(env, 1, 65));
    assertEqualsLong("lshl", 4, lshl(env, 1, 66));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(env, 1, -1));

    assertEqualsLong("lshr", 0, lshr(env, 0, 0));
    assertEqualsLong("lshr", 0, lshr(env, 0, 1));
    assertEqualsLong("lshr", 1, lshr(env, 1, 0));
    assertEqualsLong("lshr", 0, lshr(env, 1, 1));
    assertEqualsLong("lshr", 16, lshr(env, 32, 1));
    assertEqualsLong("lshr", 8, lshr(env, 32, 2));
    assertEqualsLong("lshr", 4, lshr(env, 32, 3));
    assertEqualsLong("lshr", -1, lshr(env, -1, 1));
    assertEqualsLong("lshr", -1, lshr(env, -1, 2));
    assertEqualsLong("lshr", -4, lshr(env, -16, 2));
    assertEqualsLong("lshr", 0xf800000000000000L, lshr(env, 0x8000000000000000L, 4));
    assertEqualsLong("lshr", 0x8000000000000000L, lshr(env, 0x8000000000000000L, 64));
    assertEqualsLong("lshr", 0xc000000000000000L, lshr(env, 0x8000000000000000L, 65));
    assertEqualsLong("lshr", 0xe000000000000000L, lshr(env, 0x8000000000000000L, 66));
    assertEqualsLong("lshr", -1L, lshr(env, 0x8000000000000000L, -1));

    assertEqualsLong("lushr", 0, lushr(env, 0, 0));
    assertEqualsLong("lushr", 0, lushr(env, 0, 1));
    assertEqualsLong("lushr", 1, lushr(env, 1, 0));
    assertEqualsLong("lushr", 0, lushr(env, 1, 1));
    assertEqualsLong("lushr", 16, lushr(env, 32, 1));
    assertEqualsLong("lushr", 8, lushr(env, 32, 2));
    assertEqualsLong("lushr", 4, lushr(env, 32, 3));
    assertEqualsLong("lushr", 0x7fffffffffffffffL, lushr(env, -1, 1));
    assertEqualsLong("lushr", 0x0800000000000000L, lushr(env, 0x8000000000000000L, 4));
    assertEqualsLong("lushr", 0x8000000000000000L, lushr(env, 0x8000000000000000L, 64));
    assertEqualsLong("lushr", 0x4000000000000000L, lushr(env, 0x8000000000000000L, 65));
    assertEqualsLong("lushr", 0x2000000000000000L, lushr(env, 0x8000000000000000L, 66));
    assertEqualsLong("lushr", 1, lushr(env, 0x8000000000000000L, -1));

    assertEqualsLong("land", 0, land(env, 0, 0));
    assertEqualsLong("land", 0, land(env, 0, 0xffffffffffffffffL));
    assertEqualsLong("land", 0, land(env, 0xffffffffffffffffL, 0));
    assertEqualsLong("land", 0xffffffffffffffffL, land(env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("land", 0x2222222222222222L, land(env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("land", 0x0000000110000000L, land(env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lor", 0, lor(env, 0, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(env, 0, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(env, 0xffffffffffffffffL, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0x7777777777777777L, lor(env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lxor", 0, lxor(env, 0, 0));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(env, 0, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(env, 0xffffffffffffffffL, 0));
    assertEqualsLong("lxor", 0, lxor(env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0x5555555555555555L, lxor(env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lxor", 0xfffffffeefffffffL, lxor(env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("i2l", 0L, i2l(env, 0));
    assertEqualsLong("i2l", 0x7fffffffL, i2l(env, 0x7fffffff));
    assertEqualsLong("i2l", 0xffffffffffffffffL, i2l(env, 0xffffffff));
    assertEqualsLong("i2l", 0xffffffff80000000L, i2l(env, 0x80000000));

    assertEqualsInt("l2i", 0, l2i(env, 0));
    assertEqualsInt("l2i", 0x7fffffff, l2i(env, 0x7fffffff));
    assertEqualsInt("l2i", 0xffffffff, l2i(env, 0xffffffffffffffff));
    assertEqualsInt("l2i", 0x9abcdef0, l2i(env, 0x123456789abcdef0));

    assertEqualsInt("lcmp", 0, lcmp(env, 0, 0));
    assertEqualsInt("lcmp", 0, lcmp(env, 0x8000000000000000L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 0, lcmp(env, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsInt("lcmp", 1, lcmp(env, 0x8000000000000001L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 1, lcmp(env, 0x7fffffffffffffffL, 0x7ffffffffffffffeL));
    assertEqualsInt("lcmp", -1, lcmp(env, 0x8000000000000000L, 0x8000000000000001L));
    assertEqualsInt("lcmp", -1, lcmp(env, 0x7ffffffffffffffeL, 0x7fffffffffffffffL));

    assertEqualsLong("ldc(long)", 0x8000000000000000L, ldc_min_long(env));
    assertEqualsLong("ldc(long)", 0x7fffffffffffffffL, ldc_max_long(env));

    print_test_result();

    return 0;
}


