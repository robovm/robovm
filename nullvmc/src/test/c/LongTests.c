#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* LongOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/LongOpcodes");
    jlong (*lreturn)(void*, Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lreturn", "(J)J")->impl;
    jlong (*lstore)(void*, Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lstore", "(J)J")->impl;
    jlong (*lconst_0)(void*, Env*) = nvmGetClassMethod(env, LongOpcodes, "lconst_0", "()J")->impl;
    jlong (*lconst_1)(void*, Env*) = nvmGetClassMethod(env, LongOpcodes, "lconst_1", "()J")->impl;
    jlong (*ladd)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "ladd", "(JJ)J")->impl;
    jlong (*lsub)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lsub", "(JJ)J")->impl;
    jlong (*lmul)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lmul", "(JJ)J")->impl;
    jlong (*ldiv)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "ldiv", "(JJ)J")->impl;
    jlong (*lrem)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lrem", "(JJ)J")->impl;
    jlong (*lneg)(void*, Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "lneg", "(J)J")->impl;
    jlong (*lshl)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lshl", "(JI)J")->impl;
    jlong (*lshr)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lshr", "(JI)J")->impl;
    jlong (*lushr)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lushr", "(JI)J")->impl;
    jlong (*land)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "land", "(JJ)J")->impl;
    jlong (*lor)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lor", "(JJ)J")->impl;
    jlong (*lxor)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lxor", "(JJ)J")->impl;
    jlong (*i2l)(void*, Env*, jint) = nvmGetClassMethod(env, LongOpcodes, "i2l", "(I)J")->impl;
    jint (*l2i)(void*, Env*, jlong) = nvmGetClassMethod(env, LongOpcodes, "l2i", "(J)I")->impl;
    jint (*lcmp)(void*, Env*, jlong, jlong) = nvmGetClassMethod(env, LongOpcodes, "lcmp", "(JJ)I")->impl;
    jlong (*ldc_min_long)(void*, Env*) = nvmGetClassMethod(env, LongOpcodes, "ldc_min_long", "()J")->impl;
    jlong (*ldc_max_long)(void*, Env*) = nvmGetClassMethod(env, LongOpcodes, "ldc_max_long", "()J")->impl;

    assertEqualsLong("lreturn", -100, lreturn(NULL, env, -100));
    assertEqualsLong("lreturn", 0x7fffffffffffffff, lreturn(NULL, env, 0x7fffffffffffffff));
    assertEqualsLong("lreturn", 0x8000000000000000, lreturn(NULL, env, 0x8000000000000000));

    assertEqualsLong("lstore", -1000000000000, lstore(NULL, env, -1000000000000));
    assertEqualsLong("lstore", 1000000000000, lstore(NULL, env, 1000000000000));

    assertEqualsLong("lconst_0", 0, lconst_0(NULL, env));
    assertEqualsLong("lconst_1", 1, lconst_1(NULL, env));

    assertEqualsLong("ladd", 0, ladd(NULL, env, 0, 0));
    assertEqualsLong("ladd", 30, ladd(NULL, env, 20, 10));
    assertEqualsLong("ladd", -10, ladd(NULL, env, -20, 10));
    assertEqualsLong("ladd", 10, ladd(NULL, env, 20, -10));
    assertEqualsLong("ladd", -30, ladd(NULL, env, -20, -10));
    assertEqualsLong("ladd", 0x7fffffffffffffff, ladd(NULL, env, 0x8000000000000000, -1));  
    assertEqualsLong("ladd", 0x8000000000000000, ladd(NULL, env, 0x7fffffffffffffff, 1));  

    assertEqualsLong("lsub", 0, lsub(NULL, env, 0, 0));
    assertEqualsLong("lsub", 10, lsub(NULL, env, 20, 10));
    assertEqualsLong("lsub", -30, lsub(NULL, env, -20, 10));
    assertEqualsLong("lsub", 30, lsub(NULL, env, 20, -10));
    assertEqualsLong("lsub", -10, lsub(NULL, env, -20, -10));
    assertEqualsLong("lsub", 0x7fffffffffffffffL, lsub(NULL, env, 0x8000000000000000L, 1));  
    assertEqualsLong("lsub", 0x8000000000000000L, lsub(NULL, env, 0x7fffffffffffffffL, -1));  

    assertEqualsLong("lmul", 0, lmul(NULL, env, 0, 0));
    assertEqualsLong("lmul", 0, lmul(NULL, env, 10, 0));
    assertEqualsLong("lmul", 0, lmul(NULL, env, 0, 10));
    assertEqualsLong("lmul", 39483, lmul(NULL, env, 123, 321));
    assertEqualsLong("lmul", 39483, lmul(NULL, env, 321, 123));
    assertEqualsLong("lmul", -242, lmul(NULL, env, -11, 22));
    assertEqualsLong("lmul", -242, lmul(NULL, env, 11, -22));
    assertEqualsLong("lmul", 242, lmul(NULL, env, -11, -22));
    assertEqualsLong("lmul", 1, lmul(NULL, env, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsLong("lmul", 3000000000000000000L, lmul(NULL, env, -2000000000, -1500000000));
    assertEqualsLong("lmul", 6011292615620689920L, lmul(NULL, env, -2000000000000L, -1500000000000L));

    assertEqualsLong("ldiv", 0, ldiv(NULL, env, 0, 10));
    assertEqualsLong("ldiv", 0, ldiv(NULL, env, 0, -10));
    assertEqualsLong("ldiv", 5, ldiv(NULL, env, 10, 2));
    assertEqualsLong("ldiv", 5, ldiv(NULL, env, 11, 2));
    assertEqualsLong("ldiv", -5, ldiv(NULL, env, 10, -2));
    assertEqualsLong("ldiv", -5, ldiv(NULL, env, -10, 2));
    assertEqualsLong("ldiv", 5, ldiv(NULL, env, -10, -2));
    assertEqualsLong("ldiv", 0, ldiv(NULL, env, 0xffffffffffffffffL, 2));
    assertEqualsLong("ldiv", 0x1999999999999999L, ldiv(NULL, env, 0x6666666666666666L, 4));
    assertEqualsLong("ldiv", 0x3fffffffffffffffL, ldiv(NULL, env, 0x7fffffffffffffffL, 2));
//    assertEqualsLong("ldiv", 0, ldiv(NULL, env, 10, 0));

    assertEqualsLong("lrem", 0, lrem(NULL, env, 0, 10));
    assertEqualsLong("lrem", 1, lrem(NULL, env, 11, 10));
    assertEqualsLong("lrem", 9, lrem(NULL, env, 19, 10));
    assertEqualsLong("lrem", -9, lrem(NULL, env, -19, 10));
    assertEqualsLong("lrem", 9, lrem(NULL, env, 19, -10));
    assertEqualsLong("lrem", -9, lrem(NULL, env, -19, -10));
    assertEqualsLong("lrem", 0, lrem(NULL, env, 20, 10));
    assertEqualsLong("lrem", 100, lrem(NULL, env, 4000000000100L, 4000000000000L));
//    assertEqualsLong("lrem", 0, lrem(NULL, env, 100, 0));

    assertEqualsLong("lneg", 0, lneg(NULL, env, 0));
    assertEqualsLong("lneg", -1, lneg(NULL, env, 1));
    assertEqualsLong("lneg", 1, lneg(NULL, env, -1));
    assertEqualsLong("lneg", 0x8000000000000001L, lneg(NULL, env, 0x7fffffffffffffffL));
    assertEqualsLong("lneg", 0x8000000000000000L, lneg(NULL, env, 0x8000000000000000L));

    assertEqualsLong("lshl", 0, lshl(NULL, env, 0, 0));
    assertEqualsLong("lshl", 0, lshl(NULL, env, 0, 10));
    assertEqualsLong("lshl", 1, lshl(NULL, env, 1, 0));
    assertEqualsLong("lshl", 2, lshl(NULL, env, 1, 1));
    assertEqualsLong("lshl", 4, lshl(NULL, env, 1, 2));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(NULL, env, 1, 63));
    assertEqualsLong("lshl", 0xffffffff80000000L, lshl(NULL, env, 0xffffffffffffffffL, 31));
    assertEqualsLong("lshl", 1, lshl(NULL, env, 1, 64));
    assertEqualsLong("lshl", 2, lshl(NULL, env, 1, 65));
    assertEqualsLong("lshl", 4, lshl(NULL, env, 1, 66));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(NULL, env, 1, -1));

    assertEqualsLong("lshr", 0, lshr(NULL, env, 0, 0));
    assertEqualsLong("lshr", 0, lshr(NULL, env, 0, 1));
    assertEqualsLong("lshr", 1, lshr(NULL, env, 1, 0));
    assertEqualsLong("lshr", 0, lshr(NULL, env, 1, 1));
    assertEqualsLong("lshr", 16, lshr(NULL, env, 32, 1));
    assertEqualsLong("lshr", 8, lshr(NULL, env, 32, 2));
    assertEqualsLong("lshr", 4, lshr(NULL, env, 32, 3));
    assertEqualsLong("lshr", -1, lshr(NULL, env, -1, 1));
    assertEqualsLong("lshr", -1, lshr(NULL, env, -1, 2));
    assertEqualsLong("lshr", -4, lshr(NULL, env, -16, 2));
    assertEqualsLong("lshr", 0xf800000000000000L, lshr(NULL, env, 0x8000000000000000L, 4));
    assertEqualsLong("lshr", 0x8000000000000000L, lshr(NULL, env, 0x8000000000000000L, 64));
    assertEqualsLong("lshr", 0xc000000000000000L, lshr(NULL, env, 0x8000000000000000L, 65));
    assertEqualsLong("lshr", 0xe000000000000000L, lshr(NULL, env, 0x8000000000000000L, 66));
    assertEqualsLong("lshr", -1L, lshr(NULL, env, 0x8000000000000000L, -1));

    assertEqualsLong("lushr", 0, lushr(NULL, env, 0, 0));
    assertEqualsLong("lushr", 0, lushr(NULL, env, 0, 1));
    assertEqualsLong("lushr", 1, lushr(NULL, env, 1, 0));
    assertEqualsLong("lushr", 0, lushr(NULL, env, 1, 1));
    assertEqualsLong("lushr", 16, lushr(NULL, env, 32, 1));
    assertEqualsLong("lushr", 8, lushr(NULL, env, 32, 2));
    assertEqualsLong("lushr", 4, lushr(NULL, env, 32, 3));
    assertEqualsLong("lushr", 0x7fffffffffffffffL, lushr(NULL, env, -1, 1));
    assertEqualsLong("lushr", 0x0800000000000000L, lushr(NULL, env, 0x8000000000000000L, 4));
    assertEqualsLong("lushr", 0x8000000000000000L, lushr(NULL, env, 0x8000000000000000L, 64));
    assertEqualsLong("lushr", 0x4000000000000000L, lushr(NULL, env, 0x8000000000000000L, 65));
    assertEqualsLong("lushr", 0x2000000000000000L, lushr(NULL, env, 0x8000000000000000L, 66));
    assertEqualsLong("lushr", 1, lushr(NULL, env, 0x8000000000000000L, -1));

    assertEqualsLong("land", 0, land(NULL, env, 0, 0));
    assertEqualsLong("land", 0, land(NULL, env, 0, 0xffffffffffffffffL));
    assertEqualsLong("land", 0, land(NULL, env, 0xffffffffffffffffL, 0));
    assertEqualsLong("land", 0xffffffffffffffffL, land(NULL, env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("land", 0x2222222222222222L, land(NULL, env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("land", 0x0000000110000000L, land(NULL, env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lor", 0, lor(NULL, env, 0, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(NULL, env, 0, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(NULL, env, 0xffffffffffffffffL, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(NULL, env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0x7777777777777777L, lor(NULL, env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(NULL, env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lxor", 0, lxor(NULL, env, 0, 0));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(NULL, env, 0, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(NULL, env, 0xffffffffffffffffL, 0));
    assertEqualsLong("lxor", 0, lxor(NULL, env, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0x5555555555555555L, lxor(NULL, env, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lxor", 0xfffffffeefffffffL, lxor(NULL, env, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("i2l", 0L, i2l(NULL, env, 0));
    assertEqualsLong("i2l", 0x7fffffffL, i2l(NULL, env, 0x7fffffff));
    assertEqualsLong("i2l", 0xffffffffffffffffL, i2l(NULL, env, 0xffffffff));
    assertEqualsLong("i2l", 0xffffffff80000000L, i2l(NULL, env, 0x80000000));

    assertEqualsInt("l2i", 0, l2i(NULL, env, 0));
    assertEqualsInt("l2i", 0x7fffffff, l2i(NULL, env, 0x7fffffff));
    assertEqualsInt("l2i", 0xffffffff, l2i(NULL, env, 0xffffffffffffffff));
    assertEqualsInt("l2i", 0x9abcdef0, l2i(NULL, env, 0x123456789abcdef0));

    assertEqualsInt("lcmp", 0, lcmp(NULL, env, 0, 0));
    assertEqualsInt("lcmp", 0, lcmp(NULL, env, 0x8000000000000000L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 0, lcmp(NULL, env, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsInt("lcmp", 1, lcmp(NULL, env, 0x8000000000000001L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 1, lcmp(NULL, env, 0x7fffffffffffffffL, 0x7ffffffffffffffeL));
    assertEqualsInt("lcmp", -1, lcmp(NULL, env, 0x8000000000000000L, 0x8000000000000001L));
    assertEqualsInt("lcmp", -1, lcmp(NULL, env, 0x7ffffffffffffffeL, 0x7fffffffffffffffL));

    assertEqualsLong("ldc(long)", 0x8000000000000000L, ldc_min_long(NULL, env));
    assertEqualsLong("ldc(long)", 0x7fffffffffffffffL, ldc_max_long(NULL, env));

    print_test_result();

    return 0;
}


