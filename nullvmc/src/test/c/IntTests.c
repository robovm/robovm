#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* IntTests = nvmGetClass("org/nullvm/compiler/tests/opcode/IntOpcodes", "org_nullvm_compiler_tests_opcode_IntOpcodes", NULL);
    jint (*ireturn)(jclass*, jint) = j_get_method_impl(IntTests, "ireturn", "(I)I", IntTests);
    jint (*istore)(jclass*, jint) = j_get_method_impl(IntTests, "istore", "(I)I", IntTests);
    jint (*iconst_m1)(jclass*) = j_get_method_impl(IntTests, "iconst_m1", "()I", IntTests);
    jint (*iconst_0)(jclass*) = j_get_method_impl(IntTests, "iconst_0", "()I", IntTests);
    jint (*iconst_1)(jclass*) = j_get_method_impl(IntTests, "iconst_1", "()I", IntTests);
    jint (*iconst_2)(jclass*) = j_get_method_impl(IntTests, "iconst_2", "()I", IntTests);
    jint (*iconst_3)(jclass*) = j_get_method_impl(IntTests, "iconst_3", "()I", IntTests);
    jint (*iconst_4)(jclass*) = j_get_method_impl(IntTests, "iconst_4", "()I", IntTests);
    jint (*iconst_5)(jclass*) = j_get_method_impl(IntTests, "iconst_5", "()I", IntTests);
    jint (*iadd)(jclass*, jint, jint) = j_get_method_impl(IntTests, "iadd", "(II)I", IntTests);
    jint (*isub)(jclass*, jint, jint) = j_get_method_impl(IntTests, "isub", "(II)I", IntTests);
    jint (*imul)(jclass*, jint, jint) = j_get_method_impl(IntTests, "imul", "(II)I", IntTests);
    jint (*idiv)(jclass*, jint, jint) = j_get_method_impl(IntTests, "idiv", "(II)I", IntTests);
    jint (*irem)(jclass*, jint, jint) = j_get_method_impl(IntTests, "irem", "(II)I", IntTests);
    jint (*ineg)(jclass*, jint) = j_get_method_impl(IntTests, "ineg", "(I)I", IntTests);
    jint (*ishl)(jclass*, jint, jint) = j_get_method_impl(IntTests, "ishl", "(II)I", IntTests);
    jint (*ishr)(jclass*, jint, jint) = j_get_method_impl(IntTests, "ishr", "(II)I", IntTests);
    jint (*iushr)(jclass*, jint, jint) = j_get_method_impl(IntTests, "iushr", "(II)I", IntTests);
    jint (*iand)(jclass*, jint, jint) = j_get_method_impl(IntTests, "iand", "(II)I", IntTests);
    jint (*ior)(jclass*, jint, jint) = j_get_method_impl(IntTests, "ior", "(II)I", IntTests);
    jint (*ixor)(jclass*, jint, jint) = j_get_method_impl(IntTests, "ixor", "(II)I", IntTests);
    jint (*i2b)(jclass*, jint) = j_get_method_impl(IntTests, "i2b", "(I)I", IntTests);
    jint (*i2c)(jclass*, jint) = j_get_method_impl(IntTests, "i2c", "(I)I", IntTests);
    jint (*i2s)(jclass*, jint) = j_get_method_impl(IntTests, "i2s", "(I)I", IntTests);
    jint (*bipush_m1)(jclass*) = j_get_method_impl(IntTests, "bipush_m1", "()I", IntTests);
    jint (*bipush_127)(jclass*) = j_get_method_impl(IntTests, "bipush_127", "()I", IntTests);
    jint (*sipush_m1)(jclass*) = j_get_method_impl(IntTests, "sipush_m1", "()I", IntTests);
    jint (*sipush_32767)(jclass*) = j_get_method_impl(IntTests, "sipush_32767", "()I", IntTests);
    jint (*ldc_min_int)(jclass*) = j_get_method_impl(IntTests, "ldc_min_int", "()I", IntTests);
    jint (*ldc_max_int)(jclass*) = j_get_method_impl(IntTests, "ldc_max_int", "()I", IntTests);
    jint (*iinc_m128)(jclass*, jint) = j_get_method_impl(IntTests, "iinc_m128", "(I)I", IntTests);
    jint (*iinc_127)(jclass*, jint) = j_get_method_impl(IntTests, "iinc_127", "(I)I", IntTests);

    assertEqualsInt("ireturn", -100, ireturn(IntTests, -100));
    assertEqualsInt("ireturn", 0x7fffffff, ireturn(IntTests, 0x7fffffff));
    assertEqualsInt("ireturn", 0x80000000, ireturn(IntTests, 0x80000000));

    assertEqualsInt("istore", -1000000, istore(IntTests, -1000000));
    assertEqualsInt("istore", 1000000, istore(IntTests, 1000000));

    assertEqualsInt("iconst_m1", -1, iconst_m1(IntTests));
    assertEqualsInt("iconst_0", 0, iconst_0(IntTests));
    assertEqualsInt("iconst_1", 1, iconst_1(IntTests));
    assertEqualsInt("iconst_2", 2, iconst_2(IntTests));
    assertEqualsInt("iconst_3", 3, iconst_3(IntTests));
    assertEqualsInt("iconst_4", 4, iconst_4(IntTests));
    assertEqualsInt("iconst_5", 5, iconst_5(IntTests));

    assertEqualsInt("iadd", 0, iadd(IntTests, 0, 0));
    assertEqualsInt("iadd", -2, iadd(IntTests, -1, -1));
    assertEqualsInt("iadd", 30, iadd(IntTests, 10, 20));
    assertEqualsInt("iadd", 30, iadd(IntTests, 20, 10));
    assertEqualsInt("iadd", 4, iadd(IntTests, 5, -1));
    assertEqualsInt("iadd", 0x80000000, iadd(IntTests, 0x7fffffff, 1));

    assertEqualsInt("isub", 0, isub(IntTests, 0, 0));
    assertEqualsInt("isub", 0, isub(IntTests, -1, -1));
    assertEqualsInt("isub", -10, isub(IntTests, 10, 20));
    assertEqualsInt("isub", 10, isub(IntTests, 20, 10));
    assertEqualsInt("isub", 6, isub(IntTests, 5, -1));
    assertEqualsInt("isub", 0x7fffffff, isub(IntTests, 0x80000000, 1));

    assertEqualsInt("imul", 0, imul(IntTests, 0, 0));
    assertEqualsInt("imul", 0, imul(IntTests, 10, 0));
    assertEqualsInt("imul", 0, imul(IntTests, 0, 10));
    assertEqualsInt("imul", 39483, imul(IntTests, 123, 321));
    assertEqualsInt("imul", 39483, imul(IntTests, 321, 123));
    assertEqualsInt("imul", -242, imul(IntTests, -11, 22));
    assertEqualsInt("imul", -242, imul(IntTests, 11, -22));
    assertEqualsInt("imul", 242, imul(IntTests, -11, -22));
    assertEqualsInt("imul", 1, imul(IntTests, 0x7fffffff, 0x7fffffff));
    assertEqualsInt("imul", -164888576, imul(IntTests, -2000000000, -1500000000));

    assertEqualsInt("idiv", 0, idiv(IntTests, 0, 10));
    assertEqualsInt("idiv", 0, idiv(IntTests, 0, -10));
    assertEqualsInt("idiv", 5, idiv(IntTests, 10, 2));
    assertEqualsInt("idiv", 5, idiv(IntTests, 11, 2));
    assertEqualsInt("idiv", -5, idiv(IntTests, 10, -2));
    assertEqualsInt("idiv", -5, idiv(IntTests, -10, 2));
    assertEqualsInt("idiv", 5, idiv(IntTests, -10, -2));
    assertEqualsInt("idiv", 0, idiv(IntTests, 0xffffffff, 2));
    assertEqualsInt("idiv", 0x3fffffff, idiv(IntTests, 0x7fffffff, 2));
//    assertEqualsInt("idiv", 0, idiv(IntTests, 10, 0));

    assertEqualsInt("irem", 0, irem(IntTests, 0, 10));
    assertEqualsInt("irem", 1, irem(IntTests, 11, 10));
    assertEqualsInt("irem", 9, irem(IntTests, 19, 10));
    assertEqualsInt("irem", -9, irem(IntTests, -19, 10));
    assertEqualsInt("irem", 9, irem(IntTests, 19, -10));
    assertEqualsInt("irem", -9, irem(IntTests, -19, -10));
    assertEqualsInt("irem", 0, irem(IntTests, 20, 10));
//    assertEqualsInt("irem", 0, irem(IntTests, 100, 0));

    assertEqualsInt("ineg", 0, ineg(IntTests, 0));
    assertEqualsInt("ineg", -1, ineg(IntTests, 1));
    assertEqualsInt("ineg", 1, ineg(IntTests, -1));
    assertEqualsInt("ineg", 0x80000001, ineg(IntTests, 0x7fffffff));
    assertEqualsInt("ineg", 0x80000000, ineg(IntTests, 0x80000000));

    assertEqualsInt("ishl", 0, ishl(IntTests, 0, 0));
    assertEqualsInt("ishl", 0, ishl(IntTests, 0, 10));
    assertEqualsInt("ishl", 1, ishl(IntTests, 1, 0));
    assertEqualsInt("ishl", 2, ishl(IntTests, 1, 1));
    assertEqualsInt("ishl", 4, ishl(IntTests, 1, 2));
    assertEqualsInt("ishl", 0x80000000, ishl(IntTests, 1, 31));
    assertEqualsInt("ishl", 0xffff8000, ishl(IntTests, 0xffffffff, 15));
    assertEqualsInt("ishl", 1, ishl(IntTests, 1, 32));
    assertEqualsInt("ishl", 2, ishl(IntTests, 1, 33));
    assertEqualsInt("ishl", 4, ishl(IntTests, 1, 34));
    assertEqualsInt("ishl", 0x80000000, ishl(IntTests, 1, -1));

    assertEqualsInt("ishr", 0, ishr(IntTests, 0, 0));
    assertEqualsInt("ishr", 0, ishr(IntTests, 0, 1));
    assertEqualsInt("ishr", 1, ishr(IntTests, 1, 0));
    assertEqualsInt("ishr", 0, ishr(IntTests, 1, 1));
    assertEqualsInt("ishr", 16, ishr(IntTests, 32, 1));
    assertEqualsInt("ishr", 8, ishr(IntTests, 32, 2));
    assertEqualsInt("ishr", 4, ishr(IntTests, 32, 3));
    assertEqualsInt("ishr", -1, ishr(IntTests, -1, 1));
    assertEqualsInt("ishr", -1, ishr(IntTests, -1, 2));
    assertEqualsInt("ishr", -4, ishr(IntTests, -16, 2));
    assertEqualsInt("ishr", 0xf8000000, ishr(IntTests, 0x80000000, 4));
    assertEqualsInt("ishr", 0x80000000, ishr(IntTests, 0x80000000, 32));
    assertEqualsInt("ishr", 0xc0000000, ishr(IntTests, 0x80000000, 33));
    assertEqualsInt("ishr", 0xe0000000, ishr(IntTests, 0x80000000, 34));
    assertEqualsInt("ishr", -1, ishr(IntTests, 0x80000000, -1));

    assertEqualsInt("iushr", 0, iushr(IntTests, 0, 0));
    assertEqualsInt("iushr", 0, iushr(IntTests, 0, 1));
    assertEqualsInt("iushr", 1, iushr(IntTests, 1, 0));
    assertEqualsInt("iushr", 0, iushr(IntTests, 1, 1));
    assertEqualsInt("iushr", 16, iushr(IntTests, 32, 1));
    assertEqualsInt("iushr", 8, iushr(IntTests, 32, 2));
    assertEqualsInt("iushr", 4, iushr(IntTests, 32, 3));
    assertEqualsInt("iushr", 0x7fffffff, iushr(IntTests, -1, 1));
    assertEqualsInt("iushr", 0x08000000, iushr(IntTests, 0x80000000, 4));
    assertEqualsInt("iushr", 0x80000000, iushr(IntTests, 0x80000000, 32));
    assertEqualsInt("iushr", 0x40000000, iushr(IntTests, 0x80000000, 33));
    assertEqualsInt("iushr", 0x20000000, iushr(IntTests, 0x80000000, 34));
    assertEqualsInt("iushr", 1, iushr(IntTests, 0x80000000, -1));

    assertEqualsInt("iand", 0, iand(IntTests, 0, 0));
    assertEqualsInt("iand", 0, iand(IntTests, 0, 0xffffffff));
    assertEqualsInt("iand", 0, iand(IntTests, 0xffffffff, 0));
    assertEqualsInt("iand", 0xffffffff, iand(IntTests, 0xffffffff, 0xffffffff));
    assertEqualsInt("iand", 0x22222222, iand(IntTests, 0x33333333, 0x66666666));
    assertEqualsInt("iand", 0x00011000, iand(IntTests, 0xffff1000, 0x0001ffff));

    assertEqualsInt("ior", 0, ior(IntTests, 0, 0));
    assertEqualsInt("ior", 0xffffffff, ior(IntTests, 0, 0xffffffff));
    assertEqualsInt("ior", 0xffffffff, ior(IntTests, 0xffffffff, 0));
    assertEqualsInt("ior", 0xffffffff, ior(IntTests, 0xffffffff, 0xffffffff));
    assertEqualsInt("ior", 0x77777777, ior(IntTests, 0x33333333, 0x66666666));
    assertEqualsInt("ior", 0xffffffff, ior(IntTests, 0xffff1000, 0x0001ffff));

    assertEqualsInt("ixor", 0, ixor(IntTests, 0, 0));
    assertEqualsInt("ixor", 0xffffffff, ixor(IntTests, 0, 0xffffffff));
    assertEqualsInt("ixor", 0xffffffff, ixor(IntTests, 0xffffffff, 0));
    assertEqualsInt("ixor", 0, ixor(IntTests, 0xffffffff, 0xffffffff));
    assertEqualsInt("ixor", 0x55555555, ixor(IntTests, 0x33333333, 0x66666666));
    assertEqualsInt("ixor", 0xfffeefff, ixor(IntTests, 0xffff1000, 0x0001ffff));

    assertEqualsInt("i2b", 0, i2b(IntTests, 0));
    assertEqualsInt("i2b", 0x7f, i2b(IntTests, 0x7f));
    assertEqualsInt("i2b", 0xffffffff, i2b(IntTests, 0xff));
    assertEqualsInt("i2b", 0xffffff80, i2b(IntTests, 0x80));
    assertEqualsInt("i2b", 0x78, i2b(IntTests, 0x12345678));
    assertEqualsInt("i2b", 0xfffffffb, i2b(IntTests, 0x123456fb));

    assertEqualsInt("i2c", 0, i2c(IntTests, 0));
    assertEqualsInt("i2c", 0x7fff, i2c(IntTests, 0x7fff));
    assertEqualsInt("i2c", 0xffff, i2c(IntTests, 0xffff));
    assertEqualsInt("i2c", 0x8000, i2c(IntTests, 0x8000));
    assertEqualsInt("i2c", 0x5678, i2c(IntTests, 0x12345678));
    assertEqualsInt("i2c", 0xfffb, i2c(IntTests, 0x1234fffb));

    assertEqualsInt("i2s", 0, i2s(IntTests, 0));
    assertEqualsInt("i2s", 0x7fff, i2s(IntTests, 0x7fff));
    assertEqualsInt("i2s", 0xffffffff, i2s(IntTests, 0xffff));
    assertEqualsInt("i2s", 0xffff8000, i2s(IntTests, 0x8000));
    assertEqualsInt("i2s", 0x5678, i2s(IntTests, 0x12345678));
    assertEqualsInt("i2s", 0xfffffffb, i2s(IntTests, 0x1234fffb));

    assertEqualsInt("bipush", -1, bipush_m1(IntTests));
    assertEqualsInt("bipush", 127, bipush_127(IntTests));

    assertEqualsInt("sipush", -1, sipush_m1(IntTests));
    assertEqualsInt("sipush", 32767, sipush_32767(IntTests));

    assertEqualsInt("ldc(int)", 0x80000000, ldc_min_int(IntTests));
    assertEqualsInt("ldc(int)", 0x7fffffff, ldc_max_int(IntTests));

    assertEqualsInt("iinc", 127, iinc_m128(IntTests, 255));
    assertEqualsInt("iinc", 255, iinc_127(IntTests, 128));

    print_test_result();

    return 0;
}


