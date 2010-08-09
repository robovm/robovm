#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* LongTests = nvmGetClass("org/nullvm/compiler/tests/opcode/LongOpcodes", "org_nullvm_compiler_tests_opcode_LongOpcodes", NULL);
    jlong (*lreturn)(jlong) = j_get_method_impl(LongTests, "lreturn", "(J)J", LongTests);
    jlong (*lstore)(jlong) = j_get_method_impl(LongTests, "lstore", "(J)J", LongTests);
    jlong (*lconst_0)(void) = j_get_method_impl(LongTests, "lconst_0", "()J", LongTests);
    jlong (*lconst_1)(void) = j_get_method_impl(LongTests, "lconst_1", "()J", LongTests);
    jlong (*ladd)(jlong, jlong) = j_get_method_impl(LongTests, "ladd", "(JJ)J", LongTests);
    jlong (*lsub)(jlong, jlong) = j_get_method_impl(LongTests, "lsub", "(JJ)J", LongTests);
    jlong (*lmul)(jlong, jlong) = j_get_method_impl(LongTests, "lmul", "(JJ)J", LongTests);
    jlong (*ldiv)(jlong, jlong) = j_get_method_impl(LongTests, "ldiv", "(JJ)J", LongTests);
    jlong (*lrem)(jlong, jlong) = j_get_method_impl(LongTests, "lrem", "(JJ)J", LongTests);
    jlong (*lneg)(jlong) = j_get_method_impl(LongTests, "lneg", "(J)J", LongTests);
    jlong (*lshl)(jlong, jlong) = j_get_method_impl(LongTests, "lshl", "(JI)J", LongTests);
    jlong (*lshr)(jlong, jlong) = j_get_method_impl(LongTests, "lshr", "(JI)J", LongTests);
    jlong (*lushr)(jlong, jlong) = j_get_method_impl(LongTests, "lushr", "(JI)J", LongTests);
    jlong (*land)(jlong, jlong) = j_get_method_impl(LongTests, "land", "(JJ)J", LongTests);
    jlong (*lor)(jlong, jlong) = j_get_method_impl(LongTests, "lor", "(JJ)J", LongTests);
    jlong (*lxor)(jlong, jlong) = j_get_method_impl(LongTests, "lxor", "(JJ)J", LongTests);
    jlong (*i2l)(jint) = j_get_method_impl(LongTests, "i2l", "(I)J", LongTests);
    jint (*l2i)(jlong) = j_get_method_impl(LongTests, "l2i", "(J)I", LongTests);
    jint (*lcmp)(jlong, jlong) = j_get_method_impl(LongTests, "lcmp", "(JJ)I", LongTests);
    jlong (*ldc_min_long)(void) = j_get_method_impl(LongTests, "ldc_min_long", "()J", LongTests);
    jlong (*ldc_max_long)(void) = j_get_method_impl(LongTests, "ldc_max_long", "()J", LongTests);

    assertEqualsLong("lreturn", -100, lreturn(-100));
    assertEqualsLong("lreturn", 0x7fffffffffffffff, lreturn(0x7fffffffffffffff));
    assertEqualsLong("lreturn", 0x8000000000000000, lreturn(0x8000000000000000));

    assertEqualsLong("lstore", -1000000000000, lstore(-1000000000000));
    assertEqualsLong("lstore", 1000000000000, lstore(1000000000000));

    assertEqualsLong("lconst_0", 0, lconst_0());
    assertEqualsLong("lconst_1", 1, lconst_1());

    assertEqualsLong("ladd", 0, ladd(0, 0));
    assertEqualsLong("ladd", 30, ladd(20, 10));
    assertEqualsLong("ladd", -10, ladd(-20, 10));
    assertEqualsLong("ladd", 10, ladd(20, -10));
    assertEqualsLong("ladd", -30, ladd(-20, -10));
    assertEqualsLong("ladd", 0x7fffffffffffffff, ladd(0x8000000000000000, -1));  
    assertEqualsLong("ladd", 0x8000000000000000, ladd(0x7fffffffffffffff, 1));  

    assertEqualsLong("lsub", 0, lsub(0, 0));
    assertEqualsLong("lsub", 10, lsub(20, 10));
    assertEqualsLong("lsub", -30, lsub(-20, 10));
    assertEqualsLong("lsub", 30, lsub(20, -10));
    assertEqualsLong("lsub", -10, lsub(-20, -10));
    assertEqualsLong("lsub", 0x7fffffffffffffffL, lsub(0x8000000000000000L, 1));  
    assertEqualsLong("lsub", 0x8000000000000000L, lsub(0x7fffffffffffffffL, -1));  

    assertEqualsLong("lmul", 0, lmul(0, 0));
    assertEqualsLong("lmul", 0, lmul(10, 0));
    assertEqualsLong("lmul", 0, lmul(0, 10));
    assertEqualsLong("lmul", 39483, lmul(123, 321));
    assertEqualsLong("lmul", 39483, lmul(321, 123));
    assertEqualsLong("lmul", -242, lmul(-11, 22));
    assertEqualsLong("lmul", -242, lmul(11, -22));
    assertEqualsLong("lmul", 242, lmul(-11, -22));
    assertEqualsLong("lmul", 1, lmul(0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsLong("lmul", 3000000000000000000L, lmul(-2000000000, -1500000000));
    assertEqualsLong("lmul", 6011292615620689920L, lmul(-2000000000000L, -1500000000000L));

    assertEqualsLong("ldiv", 0, ldiv(0, 10));
    assertEqualsLong("ldiv", 0, ldiv(0, -10));
    assertEqualsLong("ldiv", 5, ldiv(10, 2));
    assertEqualsLong("ldiv", 5, ldiv(11, 2));
    assertEqualsLong("ldiv", -5, ldiv(10, -2));
    assertEqualsLong("ldiv", -5, ldiv(-10, 2));
    assertEqualsLong("ldiv", 5, ldiv(-10, -2));
    assertEqualsLong("ldiv", 0, ldiv(0xffffffffffffffffL, 2));
    assertEqualsLong("ldiv", 0x1999999999999999L, ldiv(0x6666666666666666L, 4));
    assertEqualsLong("ldiv", 0x3fffffffffffffffL, ldiv(0x7fffffffffffffffL, 2));
//    assertEqualsLong("ldiv", 0, ldiv(10, 0));

    assertEqualsLong("lrem", 0, lrem(0, 10));
    assertEqualsLong("lrem", 1, lrem(11, 10));
    assertEqualsLong("lrem", 9, lrem(19, 10));
    assertEqualsLong("lrem", -9, lrem(-19, 10));
    assertEqualsLong("lrem", 9, lrem(19, -10));
    assertEqualsLong("lrem", -9, lrem(-19, -10));
    assertEqualsLong("lrem", 0, lrem(20, 10));
    assertEqualsLong("lrem", 100, lrem(4000000000100L, 4000000000000L));
//    assertEqualsLong("lrem", 0, lrem(100, 0));

    assertEqualsLong("lneg", 0, lneg(0));
    assertEqualsLong("lneg", -1, lneg(1));
    assertEqualsLong("lneg", 1, lneg(-1));
    assertEqualsLong("lneg", 0x8000000000000001L, lneg(0x7fffffffffffffffL));
    assertEqualsLong("lneg", 0x8000000000000000L, lneg(0x8000000000000000L));

    assertEqualsLong("lshl", 0, lshl(0, 0));
    assertEqualsLong("lshl", 0, lshl(0, 10));
    assertEqualsLong("lshl", 1, lshl(1, 0));
    assertEqualsLong("lshl", 2, lshl(1, 1));
    assertEqualsLong("lshl", 4, lshl(1, 2));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(1, 63));
    assertEqualsLong("lshl", 0xffffffff80000000L, lshl(0xffffffffffffffffL, 31));
    assertEqualsLong("lshl", 1, lshl(1, 64));
    assertEqualsLong("lshl", 2, lshl(1, 65));
    assertEqualsLong("lshl", 4, lshl(1, 66));
    assertEqualsLong("lshl", 0x8000000000000000L, lshl(1, -1));

    assertEqualsLong("lshr", 0, lshr(0, 0));
    assertEqualsLong("lshr", 0, lshr(0, 1));
    assertEqualsLong("lshr", 1, lshr(1, 0));
    assertEqualsLong("lshr", 0, lshr(1, 1));
    assertEqualsLong("lshr", 16, lshr(32, 1));
    assertEqualsLong("lshr", 8, lshr(32, 2));
    assertEqualsLong("lshr", 4, lshr(32, 3));
    assertEqualsLong("lshr", -1, lshr(-1, 1));
    assertEqualsLong("lshr", -1, lshr(-1, 2));
    assertEqualsLong("lshr", -4, lshr(-16, 2));
    assertEqualsLong("lshr", 0xf800000000000000L, lshr(0x8000000000000000L, 4));
    assertEqualsLong("lshr", 0x8000000000000000L, lshr(0x8000000000000000L, 64));
    assertEqualsLong("lshr", 0xc000000000000000L, lshr(0x8000000000000000L, 65));
    assertEqualsLong("lshr", 0xe000000000000000L, lshr(0x8000000000000000L, 66));
    assertEqualsLong("lshr", -1L, lshr(0x8000000000000000L, -1));

    assertEqualsLong("lushr", 0, lushr(0, 0));
    assertEqualsLong("lushr", 0, lushr(0, 1));
    assertEqualsLong("lushr", 1, lushr(1, 0));
    assertEqualsLong("lushr", 0, lushr(1, 1));
    assertEqualsLong("lushr", 16, lushr(32, 1));
    assertEqualsLong("lushr", 8, lushr(32, 2));
    assertEqualsLong("lushr", 4, lushr(32, 3));
    assertEqualsLong("lushr", 0x7fffffffffffffffL, lushr(-1, 1));
    assertEqualsLong("lushr", 0x0800000000000000L, lushr(0x8000000000000000L, 4));
    assertEqualsLong("lushr", 0x8000000000000000L, lushr(0x8000000000000000L, 64));
    assertEqualsLong("lushr", 0x4000000000000000L, lushr(0x8000000000000000L, 65));
    assertEqualsLong("lushr", 0x2000000000000000L, lushr(0x8000000000000000L, 66));
    assertEqualsLong("lushr", 1, lushr(0x8000000000000000L, -1));

    assertEqualsLong("land", 0, land(0, 0));
    assertEqualsLong("land", 0, land(0, 0xffffffffffffffffL));
    assertEqualsLong("land", 0, land(0xffffffffffffffffL, 0));
    assertEqualsLong("land", 0xffffffffffffffffL, land(0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("land", 0x2222222222222222L, land(0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("land", 0x0000000110000000L, land(0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lor", 0, lor(0, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(0, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(0xffffffffffffffffL, 0));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lor", 0x7777777777777777L, lor(0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lor", 0xffffffffffffffffL, lor(0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("lxor", 0, lxor(0, 0));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(0, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0xffffffffffffffffL, lxor(0xffffffffffffffffL, 0));
    assertEqualsLong("lxor", 0, lxor(0xffffffffffffffffL, 0xffffffffffffffffL));
    assertEqualsLong("lxor", 0x5555555555555555L, lxor(0x3333333333333333L, 0x6666666666666666L));
    assertEqualsLong("lxor", 0xfffffffeefffffffL, lxor(0xffffffff10000000L, 0x00000001ffffffffL));

    assertEqualsLong("i2l", 0L, i2l(0));
    assertEqualsLong("i2l", 0x7fffffffL, i2l(0x7fffffff));
    assertEqualsLong("i2l", 0xffffffffffffffffL, i2l(0xffffffff));
    assertEqualsLong("i2l", 0xffffffff80000000L, i2l(0x80000000));

    assertEqualsInt("l2i", 0, l2i(0));
    assertEqualsInt("l2i", 0x7fffffff, l2i(0x7fffffff));
    assertEqualsInt("l2i", 0xffffffff, l2i(0xffffffffffffffff));
    assertEqualsInt("l2i", 0x9abcdef0, l2i(0x123456789abcdef0));

    assertEqualsInt("lcmp", 0, lcmp(0, 0));
    assertEqualsInt("lcmp", 0, lcmp(0x8000000000000000L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 0, lcmp(0x7fffffffffffffffL, 0x7fffffffffffffffL));
    assertEqualsInt("lcmp", 1, lcmp(0x8000000000000001L, 0x8000000000000000L));
    assertEqualsInt("lcmp", 1, lcmp(0x7fffffffffffffffL, 0x7ffffffffffffffeL));
    assertEqualsInt("lcmp", -1, lcmp(0x8000000000000000L, 0x8000000000000001L));
    assertEqualsInt("lcmp", -1, lcmp(0x7ffffffffffffffeL, 0x7fffffffffffffffL));

    assertEqualsLong("ldc(long)", 0x8000000000000000L, ldc_min_long());
    assertEqualsLong("ldc(long)", 0x7fffffffffffffffL, ldc_max_long());

    print_test_result();

    return 0;
}


