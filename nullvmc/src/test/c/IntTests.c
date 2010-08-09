#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* IntTests = nvmGetClass("org/nullvm/compiler/tests/opcode/IntOpcodes", "org_nullvm_compiler_tests_opcode_IntOpcodes", NULL);
    jint (*ireturn)(jint) = j_get_method_impl(IntTests, "ireturn", "(I)I", IntTests);
    jint (*istore)(jint) = j_get_method_impl(IntTests, "istore", "(I)I", IntTests);
    jint (*iconst_m1)(void) = j_get_method_impl(IntTests, "iconst_m1", "()I", IntTests);
    jint (*iconst_0)(void) = j_get_method_impl(IntTests, "iconst_0", "()I", IntTests);
    jint (*iconst_1)(void) = j_get_method_impl(IntTests, "iconst_1", "()I", IntTests);
    jint (*iconst_2)(void) = j_get_method_impl(IntTests, "iconst_2", "()I", IntTests);
    jint (*iconst_3)(void) = j_get_method_impl(IntTests, "iconst_3", "()I", IntTests);
    jint (*iconst_4)(void) = j_get_method_impl(IntTests, "iconst_4", "()I", IntTests);
    jint (*iconst_5)(void) = j_get_method_impl(IntTests, "iconst_5", "()I", IntTests);
    jint (*iadd)(jint, jint) = j_get_method_impl(IntTests, "iadd", "(II)I", IntTests);
    jint (*isub)(jint, jint) = j_get_method_impl(IntTests, "isub", "(II)I", IntTests);
    jint (*imul)(jint, jint) = j_get_method_impl(IntTests, "imul", "(II)I", IntTests);
    jint (*idiv)(jint, jint) = j_get_method_impl(IntTests, "idiv", "(II)I", IntTests);
    jint (*irem)(jint, jint) = j_get_method_impl(IntTests, "irem", "(II)I", IntTests);
    jint (*ineg)(jint) = j_get_method_impl(IntTests, "ineg", "(I)I", IntTests);
    jint (*ishl)(jint, jint) = j_get_method_impl(IntTests, "ishl", "(II)I", IntTests);
    jint (*ishr)(jint, jint) = j_get_method_impl(IntTests, "ishr", "(II)I", IntTests);
    jint (*iushr)(jint, jint) = j_get_method_impl(IntTests, "iushr", "(II)I", IntTests);
    jint (*iand)(jint, jint) = j_get_method_impl(IntTests, "iand", "(II)I", IntTests);
    jint (*ior)(jint, jint) = j_get_method_impl(IntTests, "ior", "(II)I", IntTests);
    jint (*ixor)(jint, jint) = j_get_method_impl(IntTests, "ixor", "(II)I", IntTests);
    jint (*i2b)(jint) = j_get_method_impl(IntTests, "i2b", "(I)I", IntTests);
    jint (*i2c)(jint) = j_get_method_impl(IntTests, "i2c", "(I)I", IntTests);
    jint (*i2s)(jint) = j_get_method_impl(IntTests, "i2s", "(I)I", IntTests);
    jint (*bipush_m1)(void) = j_get_method_impl(IntTests, "bipush_m1", "()I", IntTests);
    jint (*bipush_127)(void) = j_get_method_impl(IntTests, "bipush_127", "()I", IntTests);
    jint (*sipush_m1)(void) = j_get_method_impl(IntTests, "sipush_m1", "()I", IntTests);
    jint (*sipush_32767)(void) = j_get_method_impl(IntTests, "sipush_32767", "()I", IntTests);
    jint (*ldc_min_int)(void) = j_get_method_impl(IntTests, "ldc_min_int", "()I", IntTests);
    jint (*ldc_max_int)(void) = j_get_method_impl(IntTests, "ldc_max_int", "()I", IntTests);
    jint (*iinc_m128)(jint) = j_get_method_impl(IntTests, "iinc_m128", "(I)I", IntTests);
    jint (*iinc_127)(jint) = j_get_method_impl(IntTests, "iinc_127", "(I)I", IntTests);

    assertEqualsInt("ireturn", -100, ireturn(-100));
    assertEqualsInt("ireturn", 0x7fffffff, ireturn(0x7fffffff));
    assertEqualsInt("ireturn", 0x80000000, ireturn(0x80000000));

    assertEqualsInt("istore", -1000000, istore(-1000000));
    assertEqualsInt("istore", 1000000, istore(1000000));

    assertEqualsInt("iconst_m1", -1, iconst_m1());
    assertEqualsInt("iconst_0", 0, iconst_0());
    assertEqualsInt("iconst_1", 1, iconst_1());
    assertEqualsInt("iconst_2", 2, iconst_2());
    assertEqualsInt("iconst_3", 3, iconst_3());
    assertEqualsInt("iconst_4", 4, iconst_4());
    assertEqualsInt("iconst_5", 5, iconst_5());

    assertEqualsInt("iadd", 0, iadd(0, 0));
    assertEqualsInt("iadd", -2, iadd(-1, -1));
    assertEqualsInt("iadd", 30, iadd(10, 20));
    assertEqualsInt("iadd", 30, iadd(20, 10));
    assertEqualsInt("iadd", 4, iadd(5, -1));
    assertEqualsInt("iadd", 0x80000000, iadd(0x7fffffff, 1));

    assertEqualsInt("isub", 0, isub(0, 0));
    assertEqualsInt("isub", 0, isub(-1, -1));
    assertEqualsInt("isub", -10, isub(10, 20));
    assertEqualsInt("isub", 10, isub(20, 10));
    assertEqualsInt("isub", 6, isub(5, -1));
    assertEqualsInt("isub", 0x7fffffff, isub(0x80000000, 1));

    assertEqualsInt("imul", 0, imul(0, 0));
    assertEqualsInt("imul", 0, imul(10, 0));
    assertEqualsInt("imul", 0, imul(0, 10));
    assertEqualsInt("imul", 39483, imul(123, 321));
    assertEqualsInt("imul", 39483, imul(321, 123));
    assertEqualsInt("imul", -242, imul(-11, 22));
    assertEqualsInt("imul", -242, imul(11, -22));
    assertEqualsInt("imul", 242, imul(-11, -22));
    assertEqualsInt("imul", 1, imul(0x7fffffff, 0x7fffffff));
    assertEqualsInt("imul", -164888576, imul(-2000000000, -1500000000));

    assertEqualsInt("idiv", 0, idiv(0, 10));
    assertEqualsInt("idiv", 0, idiv(0, -10));
    assertEqualsInt("idiv", 5, idiv(10, 2));
    assertEqualsInt("idiv", 5, idiv(11, 2));
    assertEqualsInt("idiv", -5, idiv(10, -2));
    assertEqualsInt("idiv", -5, idiv(-10, 2));
    assertEqualsInt("idiv", 5, idiv(-10, -2));
    assertEqualsInt("idiv", 0, idiv(0xffffffff, 2));
    assertEqualsInt("idiv", 0x3fffffff, idiv(0x7fffffff, 2));
//    assertEqualsInt("idiv", 0, idiv(10, 0));

    assertEqualsInt("irem", 0, irem(0, 10));
    assertEqualsInt("irem", 1, irem(11, 10));
    assertEqualsInt("irem", 9, irem(19, 10));
    assertEqualsInt("irem", -9, irem(-19, 10));
    assertEqualsInt("irem", 9, irem(19, -10));
    assertEqualsInt("irem", -9, irem(-19, -10));
    assertEqualsInt("irem", 0, irem(20, 10));
//    assertEqualsInt("irem", 0, irem(100, 0));

    assertEqualsInt("ineg", 0, ineg(0));
    assertEqualsInt("ineg", -1, ineg(1));
    assertEqualsInt("ineg", 1, ineg(-1));
    assertEqualsInt("ineg", 0x80000001, ineg(0x7fffffff));
    assertEqualsInt("ineg", 0x80000000, ineg(0x80000000));

    assertEqualsInt("ishl", 0, ishl(0, 0));
    assertEqualsInt("ishl", 0, ishl(0, 10));
    assertEqualsInt("ishl", 1, ishl(1, 0));
    assertEqualsInt("ishl", 2, ishl(1, 1));
    assertEqualsInt("ishl", 4, ishl(1, 2));
    assertEqualsInt("ishl", 0x80000000, ishl(1, 31));
    assertEqualsInt("ishl", 0xffff8000, ishl(0xffffffff, 15));
    assertEqualsInt("ishl", 1, ishl(1, 32));
    assertEqualsInt("ishl", 2, ishl(1, 33));
    assertEqualsInt("ishl", 4, ishl(1, 34));
    assertEqualsInt("ishl", 0x80000000, ishl(1, -1));

    assertEqualsInt("ishr", 0, ishr(0, 0));
    assertEqualsInt("ishr", 0, ishr(0, 1));
    assertEqualsInt("ishr", 1, ishr(1, 0));
    assertEqualsInt("ishr", 0, ishr(1, 1));
    assertEqualsInt("ishr", 16, ishr(32, 1));
    assertEqualsInt("ishr", 8, ishr(32, 2));
    assertEqualsInt("ishr", 4, ishr(32, 3));
    assertEqualsInt("ishr", -1, ishr(-1, 1));
    assertEqualsInt("ishr", -1, ishr(-1, 2));
    assertEqualsInt("ishr", -4, ishr(-16, 2));
    assertEqualsInt("ishr", 0xf8000000, ishr(0x80000000, 4));
    assertEqualsInt("ishr", 0x80000000, ishr(0x80000000, 32));
    assertEqualsInt("ishr", 0xc0000000, ishr(0x80000000, 33));
    assertEqualsInt("ishr", 0xe0000000, ishr(0x80000000, 34));
    assertEqualsInt("ishr", -1, ishr(0x80000000, -1));

    assertEqualsInt("iushr", 0, iushr(0, 0));
    assertEqualsInt("iushr", 0, iushr(0, 1));
    assertEqualsInt("iushr", 1, iushr(1, 0));
    assertEqualsInt("iushr", 0, iushr(1, 1));
    assertEqualsInt("iushr", 16, iushr(32, 1));
    assertEqualsInt("iushr", 8, iushr(32, 2));
    assertEqualsInt("iushr", 4, iushr(32, 3));
    assertEqualsInt("iushr", 0x7fffffff, iushr(-1, 1));
    assertEqualsInt("iushr", 0x08000000, iushr(0x80000000, 4));
    assertEqualsInt("iushr", 0x80000000, iushr(0x80000000, 32));
    assertEqualsInt("iushr", 0x40000000, iushr(0x80000000, 33));
    assertEqualsInt("iushr", 0x20000000, iushr(0x80000000, 34));
    assertEqualsInt("iushr", 1, iushr(0x80000000, -1));

    assertEqualsInt("iand", 0, iand(0, 0));
    assertEqualsInt("iand", 0, iand(0, 0xffffffff));
    assertEqualsInt("iand", 0, iand(0xffffffff, 0));
    assertEqualsInt("iand", 0xffffffff, iand(0xffffffff, 0xffffffff));
    assertEqualsInt("iand", 0x22222222, iand(0x33333333, 0x66666666));
    assertEqualsInt("iand", 0x00011000, iand(0xffff1000, 0x0001ffff));

    assertEqualsInt("ior", 0, ior(0, 0));
    assertEqualsInt("ior", 0xffffffff, ior(0, 0xffffffff));
    assertEqualsInt("ior", 0xffffffff, ior(0xffffffff, 0));
    assertEqualsInt("ior", 0xffffffff, ior(0xffffffff, 0xffffffff));
    assertEqualsInt("ior", 0x77777777, ior(0x33333333, 0x66666666));
    assertEqualsInt("ior", 0xffffffff, ior(0xffff1000, 0x0001ffff));

    assertEqualsInt("ixor", 0, ixor(0, 0));
    assertEqualsInt("ixor", 0xffffffff, ixor(0, 0xffffffff));
    assertEqualsInt("ixor", 0xffffffff, ixor(0xffffffff, 0));
    assertEqualsInt("ixor", 0, ixor(0xffffffff, 0xffffffff));
    assertEqualsInt("ixor", 0x55555555, ixor(0x33333333, 0x66666666));
    assertEqualsInt("ixor", 0xfffeefff, ixor(0xffff1000, 0x0001ffff));

    assertEqualsInt("i2b", 0, i2b(0));
    assertEqualsInt("i2b", 0x7f, i2b(0x7f));
    assertEqualsInt("i2b", 0xffffffff, i2b(0xff));
    assertEqualsInt("i2b", 0xffffff80, i2b(0x80));
    assertEqualsInt("i2b", 0x78, i2b(0x12345678));
    assertEqualsInt("i2b", 0xfffffffb, i2b(0x123456fb));

    assertEqualsInt("i2c", 0, i2c(0));
    assertEqualsInt("i2c", 0x7fff, i2c(0x7fff));
    assertEqualsInt("i2c", 0xffff, i2c(0xffff));
    assertEqualsInt("i2c", 0x8000, i2c(0x8000));
    assertEqualsInt("i2c", 0x5678, i2c(0x12345678));
    assertEqualsInt("i2c", 0xfffb, i2c(0x1234fffb));

    assertEqualsInt("i2s", 0, i2s(0));
    assertEqualsInt("i2s", 0x7fff, i2s(0x7fff));
    assertEqualsInt("i2s", 0xffffffff, i2s(0xffff));
    assertEqualsInt("i2s", 0xffff8000, i2s(0x8000));
    assertEqualsInt("i2s", 0x5678, i2s(0x12345678));
    assertEqualsInt("i2s", 0xfffffffb, i2s(0x1234fffb));

    assertEqualsInt("bipush", -1, bipush_m1());
    assertEqualsInt("bipush", 127, bipush_127());

    assertEqualsInt("sipush", -1, sipush_m1());
    assertEqualsInt("sipush", 32767, sipush_32767());

    assertEqualsInt("ldc(int)", 0x80000000, ldc_min_int());
    assertEqualsInt("ldc(int)", 0x7fffffff, ldc_max_int());

    assertEqualsInt("iinc", 127, iinc_m128(255));
    assertEqualsInt("iinc", 255, iinc_127(128));

    print_test_result();

    return 0;
}


