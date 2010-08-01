#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* LongTests = nvmGetClass("org/nullvm/compiler/tests/opcode/LongOpcodes", "org_nullvm_compiler_tests_opcode_LongOpcodes", NULL);
    jlong (*lreturn)(jclass*, jlong) = j_get_method_impl(LongTests, "lreturn", "(J)J", LongTests);
    jlong (*lstore)(jclass*, jlong) = j_get_method_impl(LongTests, "lstore", "(J)J", LongTests);
    jlong (*lconst_0)(jclass*) = j_get_method_impl(LongTests, "lconst_0", "()J", LongTests);
    jlong (*lconst_1)(jclass*) = j_get_method_impl(LongTests, "lconst_1", "()J", LongTests);
    jlong (*ladd)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "ladd", "(JJ)J", LongTests);
    jlong (*lsub)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lsub", "(JJ)J", LongTests);
    jlong (*lmul)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lmul", "(JJ)J", LongTests);
    jlong (*ldiv)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "ldiv", "(JJ)J", LongTests);
    jlong (*lrem)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lrem", "(JJ)J", LongTests);
    jlong (*lneg)(jclass*, jlong) = j_get_method_impl(LongTests, "lneg", "(J)J", LongTests);
    jlong (*lshl)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lshl", "(JI)J", LongTests);
    jlong (*lshr)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lshr", "(JI)J", LongTests);
    jlong (*lushr)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lushr", "(JI)J", LongTests);
    jlong (*land)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "land", "(JJ)J", LongTests);
    jlong (*lor)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lor", "(JJ)J", LongTests);
    jlong (*lxor)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lxor", "(JJ)J", LongTests);
    jlong (*i2l)(jclass*, jint) = j_get_method_impl(LongTests, "i2l", "(I)J", LongTests);
    jint (*l2i)(jclass*, jlong) = j_get_method_impl(LongTests, "l2i", "(J)I", LongTests);
    jint (*lcmp)(jclass*, jlong, jlong) = j_get_method_impl(LongTests, "lcmp", "(JJ)I", LongTests);
    jlong (*ldc_min_long)(jclass*) = j_get_method_impl(LongTests, "ldc_min_long", "()J", LongTests);
    jlong (*ldc_max_long)(jclass*) = j_get_method_impl(LongTests, "ldc_max_long", "()J", LongTests);

    assertEqualsLong("lreturn", -100, lreturn(LongTests, -100));
    assertEqualsLong("lreturn", 0x7fffffffffffffff, lreturn(LongTests, 0x7fffffffffffffff));
    assertEqualsLong("lreturn", 0x8000000000000000, lreturn(LongTests, 0x8000000000000000));

    assertEqualsLong("lstore", -1000000000000, lstore(LongTests, -1000000000000));
    assertEqualsLong("lstore", 1000000000000, lstore(LongTests, 1000000000000));

    assertEqualsLong("lconst_0", 0, lconst_0(LongTests));
    assertEqualsLong("lconst_1", 1, lconst_1(LongTests));

    assertEqualsLong("ladd", 0, ladd(LongTests, 0, 0));
    assertEqualsLong("ladd", 30, ladd(LongTests, 20, 10));
    assertEqualsLong("ladd", -10, ladd(LongTests, -20, 10));
    assertEqualsLong("ladd", 10, ladd(LongTests, 20, -10));
    assertEqualsLong("ladd", -30, ladd(LongTests, -20, -10));
    assertEqualsLong("ladd", 0x7fffffffffffffff, ladd(LongTests, 0x8000000000000000, -1));  
    assertEqualsLong("ladd", 0x8000000000000000, ladd(LongTests, 0x7fffffffffffffff, 1));  

    assertEqualsLong("lsub", 0, lsub(LongTests, 0, 0));
    assertEqualsLong("lsub", 10, lsub(LongTests, 20, 10));
    assertEqualsLong("lsub", -30, lsub(LongTests, -20, 10));
    assertEqualsLong("lsub", 30, lsub(LongTests, 20, -10));
    assertEqualsLong("lsub", -10, lsub(LongTests, -20, -10));
    assertEqualsLong("lsub", 0x7fffffffffffffffL, lsub(LongTests, 0x8000000000000000L, 1));  
    assertEqualsLong("lsub", 0x8000000000000000L, lsub(LongTests, 0x7fffffffffffffffL, -1));  

    assertEqualsLong("lmul", 0, lmul(LongTests, 0, 0));
    assertEqualsLong("lmul", 0, lmul(LongTests, 10, 0));
    assertEqualsLong("lmul", 0, lmul(LongTests, 0, 10));
    assertEqualsLong("lmul", 39483, lmul(LongTests, 123, 321));
    assertEqualsLong("lmul", 39483, lmul(LongTests, 321, 123));
    assertEqualsLong("lmul", -242, lmul(LongTests, -11, 22));
    assertEqualsLong("lmul", -242, lmul(LongTests, 11, -22));
    assertEqualsLong("lmul", 242, lmul(LongTests, -11, -22));
    assertEqualsLong("lmul", 1, lmul(LongTests, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsLong("lmul", 3000000000000000000L, lmul(LongTests, -2000000000, -1500000000));
    assertEqualsLong("lmul", 6011292615620689920L, lmul(LongTests, -2000000000000L, -1500000000000L));

    assertEqualsLong("ldiv", 0, ldiv(LongTests, 0, 10));
    assertEqualsLong("ldiv", 0, ldiv(LongTests, 0, -10));
    assertEqualsLong("ldiv", 5, ldiv(LongTests, 10, 2));
    assertEqualsLong("ldiv", 5, ldiv(LongTests, 11, 2));
    assertEqualsLong("ldiv", -5, ldiv(LongTests, 10, -2));
    assertEqualsLong("ldiv", -5, ldiv(LongTests, -10, 2));
    assertEqualsLong("ldiv", 5, ldiv(LongTests, -10, -2));
    assertEqualsLong("ldiv", 0, ldiv(LongTests, 0xffffffffffffffffL, 2));
    assertEqualsLong("ldiv", 0x1999999999999999L, ldiv(LongTests, 0x6666666666666666L, 4));
    assertEqualsLong("ldiv", 0x3fffffffffffffffL, ldiv(LongTests, 0x7fffffffffffffffL, 2));
//    assertEqualsLong("ldiv", 0, ldiv(LongTests, 10, 0));

    assertEqualsLong("lrem", 0, lrem(LongTests, 0, 10));
    assertEqualsLong("lrem", 1, lrem(LongTests, 11, 10));
    assertEqualsLong("lrem", 9, lrem(LongTests, 19, 10));
    assertEqualsLong("lrem", -9, lrem(LongTests, -19, 10));
    assertEqualsLong("lrem", 9, lrem(LongTests, 19, -10));
    assertEqualsLong("lrem", -9, lrem(LongTests, -19, -10));
    assertEqualsLong("lrem", 0, lrem(LongTests, 20, 10));
    assertEqualsLong("lrem", 100, lrem(LongTests, 4000000000100L, 4000000000000L));
//    assertEqualsLong("lrem", 0, lrem(LongTests, 100, 0));

    assertEqualsLong("lneg", 0, lneg(LongTests, 0));
    assertEqualsLong("lneg", -1, lneg(LongTests, 1));
    assertEqualsLong("lneg", 1, lneg(LongTests, -1));
    assertEqualsLong("lneg", 0x8000000000000001L, lneg(LongTests, 0x7fffffffffffffffL));
    assertEqualsLong("lneg", 0x8000000000000000L, lneg(LongTests, 0x8000000000000000L));

    assertEqualsLong("lshl", 0, lshl(LongTests, 0, 0));
    assertEqualsLong("lshl", 0, lshl(LongTests, 0, 10));
    assertEqualsLong("lshl", 1, lshl(LongTests, 1, 0));
    assertEqualsLong("lshl", 2, lshl(LongTests, 1, 1));
    assertEqualsLong("lshl", 4, lshl(LongTests, 1, 2));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(LongTests, 1, 63));
    assertEqualsLong("lshl", 0xffffffff80000000L, lshl(LongTests, 0xffffffffffffffffL, 31));
    assertEqualsLong("lshl", 1, lshl(LongTests, 1, 64));
    assertEqualsLong("lshl", 2, lshl(LongTests, 1, 65));
    assertEqualsLong("lshl", 4, lshl(LongTests, 1, 66));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(LongTests, 1, -1));

    assertEqualsLong("lshr", 0, lshr(LongTests, 0, 0));
    assertEqualsLong("lshr", 0, lshr(LongTests, 0, 1));
    assertEqualsLong("lshr", 1, lshr(LongTests, 1, 0));
    assertEqualsLong("lshr", 0, lshr(LongTests, 1, 1));
    assertEqualsLong("lshr", 16, lshr(LongTests, 32, 1));
    assertEqualsLong("lshr", 8, lshr(LongTests, 32, 2));
    assertEqualsLong("lshr", 4, lshr(LongTests, 32, 3));
    assertEqualsLong("lshr", -1, lshr(LongTests, -1, 1));
    assertEqualsLong("lshr", -1, lshr(LongTests, -1, 2));
    assertEqualsLong("lshr", -4, lshr(LongTests, -16, 2));
    assertEqualsLong("lshr", 0xf800000000000000L, lshr(LongTests, 0x8000000000000000L, 4));
    assertEqualsLong("lshr", 0x8000000000000000L, lshr(LongTests, 0x8000000000000000L, 64));
    assertEqualsLong("lshr", 0xc000000000000000L, lshr(LongTests, 0x8000000000000000L, 65));
    assertEqualsLong("lshr", 0xe000000000000000L, lshr(LongTests, 0x8000000000000000L, 66));
    assertEqualsLong("lshr", -1L, lshr(LongTests, 0x8000000000000000L, -1));

    assertEqualsLong("lushr", 0, lushr(LongTests, 0, 0));
    assertEqualsLong("lushr", 0, lushr(LongTests, 0, 1));
    assertEqualsLong("lushr", 1, lushr(LongTests, 1, 0));
    assertEqualsLong("lushr", 0, lushr(LongTests, 1, 1));
    assertEqualsLong("lushr", 16, lushr(LongTests, 32, 1));
    assertEqualsLong("lushr", 8, lushr(LongTests, 32, 2));
    assertEqualsLong("lushr", 4, lushr(LongTests, 32, 3));
    assertEqualsLong("lushr", 0x7fffffffffffffffL, lushr(LongTests, -1, 1));
    assertEqualsLong("lushr", 0x0800000000000000L, lushr(LongTests, 0x8000000000000000L, 4));
    assertEqualsLong("lushr", 0x8000000000000000L, lushr(LongTests, 0x8000000000000000L, 64));
    assertEqualsLong("lushr", 0x4000000000000000L, lushr(LongTests, 0x8000000000000000L, 65));
    assertEqualsLong("lushr", 0x2000000000000000L, lushr(LongTests, 0x8000000000000000L, 66));
    assertEqualsLong("lushr", 1, lushr(LongTests, 0x8000000000000000L, -1));

    assertEqualsLong("land", 0, land(LongTests, 0, 0));
    assertEqualsLong("land", 0, land(LongTests, 0, 0xffffffffffffffffL));
    assertEqualsLong("land", 0, land(LongTests, 0xffffffffffffffffL, 0));
    assertEqualsLong("land", 0xffffffffffffffffL, land(LongTests, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("land", 0x2222222222222222L, land(LongTests, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("land", 0x0000000110000000L, land(LongTests, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lor", 0, lor(LongTests, 0, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(LongTests, 0, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(LongTests, 0xffffffffffffffffL, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(LongTests, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0x7777777777777777L, lor(LongTests, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(LongTests, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lxor", 0, lxor(LongTests, 0, 0));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(LongTests, 0, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(LongTests, 0xffffffffffffffffL, 0));
    assertEqualsLong("lxor", 0, lxor(LongTests, 0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0x5555555555555555L, lxor(LongTests, 0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lxor", 0xfffffffeefffffffL, lxor(LongTests, 0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("i2l", 0L, i2l(LongTests, 0));
    assertEqualsLong("i2l", 0x7fffffffL, i2l(LongTests, 0x7fffffff));
    assertEqualsLong("i2l", 0xffffffffffffffffL, i2l(LongTests, 0xffffffff));
    assertEqualsLong("i2l", 0xffffffff80000000L, i2l(LongTests, 0x80000000));

    assertEqualsInt("l2i", 0, l2i(LongTests, 0));
    assertEqualsInt("l2i", 0x7fffffff, l2i(LongTests, 0x7fffffff));
    assertEqualsInt("l2i", 0xffffffff, l2i(LongTests, 0xffffffffffffffff));
    assertEqualsInt("l2i", 0x9abcdef0, l2i(LongTests, 0x123456789abcdef0));

    assertEqualsInt("lcmp", 0, lcmp(LongTests, 0, 0));
    assertEqualsInt("lcmp", 0, lcmp(LongTests, 0x8000000000000000L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 0, lcmp(LongTests, 0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsInt("lcmp", 1, lcmp(LongTests, 0x8000000000000001L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 1, lcmp(LongTests, 0x7fffffffffffffffL, 0x7ffffffffffffffeL));
    assertEqualsInt("lcmp", -1, lcmp(LongTests, 0x8000000000000000L, 0x8000000000000001L));
    assertEqualsInt("lcmp", -1, lcmp(LongTests, 0x7ffffffffffffffeL, 0x7fffffffffffffffL));

    assertEqualsLong("ldc(long)", 0x8000000000000000L, ldc_min_long(LongTests));
    assertEqualsLong("ldc(long)", 0x7fffffffffffffffL, ldc_max_long(LongTests));

    print_test_result();

    return 0;
}


