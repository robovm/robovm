#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    Class* JumpTests = nvmGetClass("org/nullvm/compiler/tests/opcode/JumpOpcodes", "org_nullvm_compiler_tests_opcode_JumpOpcodes", NULL);
    jint (*_goto)(void) = j_get_method_impl(JumpTests, "_goto", "()I", JumpTests);
    jint (*ifeq)(jint) = j_get_method_impl(JumpTests, "ifeq", "(I)I", JumpTests);
    jint (*ifne)(jint) = j_get_method_impl(JumpTests, "ifne", "(I)I", JumpTests);
    jint (*iflt)(jint) = j_get_method_impl(JumpTests, "iflt", "(I)I", JumpTests);
    jint (*ifgt)(jint) = j_get_method_impl(JumpTests, "ifgt", "(I)I", JumpTests);
    jint (*ifle)(jint) = j_get_method_impl(JumpTests, "ifle", "(I)I", JumpTests);
    jint (*ifge)(jint) = j_get_method_impl(JumpTests, "ifge", "(I)I", JumpTests);
    jint (*if_icmpeq)(jint, jint) = j_get_method_impl(JumpTests, "if_icmpeq", "(II)I", JumpTests);
    jint (*if_icmpne)(jint, jint) = j_get_method_impl(JumpTests, "if_icmpne", "(II)I", JumpTests);
    jint (*if_icmplt)(jint, jint) = j_get_method_impl(JumpTests, "if_icmplt", "(II)I", JumpTests);
    jint (*if_icmpgt)(jint, jint) = j_get_method_impl(JumpTests, "if_icmpgt", "(II)I", JumpTests);
    jint (*if_icmple)(jint, jint) = j_get_method_impl(JumpTests, "if_icmple", "(II)I", JumpTests);
    jint (*if_icmpge)(jint, jint) = j_get_method_impl(JumpTests, "if_icmpge", "(II)I", JumpTests);
    jint (*ifnull)(Object*) = j_get_method_impl(JumpTests, "ifnull", "(Ljava/lang/Object;)I", JumpTests);
    jint (*ifnonnull)(Object*) = j_get_method_impl(JumpTests, "ifnonnull", "(Ljava/lang/Object;)I", JumpTests);
    jint (*if_acmpeq)(Object*, Object*) = j_get_method_impl(JumpTests, "if_acmpeq", "(Ljava/lang/Object;Ljava/lang/Object;)I", JumpTests);
    jint (*if_acmpne)(Object*, Object*) = j_get_method_impl(JumpTests, "if_acmpne", "(Ljava/lang/Object;Ljava/lang/Object;)I", JumpTests);
    jint (*tableswitch)(jint) = j_get_method_impl(JumpTests, "tableswitch", "(I)I", JumpTests);
    jint (*lookupswitch)(jint) = j_get_method_impl(JumpTests, "lookupswitch", "(I)I", JumpTests);
    jint (*jsr)(void) = j_get_method_impl(JumpTests, "jsr", "()I", JumpTests);

    Object* a = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));
    Object* b = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));

    assertEqualsInt("goto", 5, _goto());

    assertEqualsInt("ifeq", 1, ifeq(0));
    assertEqualsInt("ifeq", 0, ifeq(0x7fffffff));
    assertEqualsInt("ifeq", 0, ifeq(0x80000000));

    assertEqualsInt("ifne", 0, ifne(0));
    assertEqualsInt("ifne", 1, ifne(0x7fffffff));
    assertEqualsInt("ifeq", 1, ifne(0x80000000));

    assertEqualsInt("iflt", 0, iflt(0));
    assertEqualsInt("iflt", 0, iflt(0x7fffffff));
    assertEqualsInt("iflt", 1, iflt(0x80000000));

    assertEqualsInt("ifgt", 0, ifgt(0));
    assertEqualsInt("ifgt", 1, ifgt(0x7fffffff));
    assertEqualsInt("ifgt", 0, ifgt(0x80000000));

    assertEqualsInt("ifle", 1, ifle(0));
    assertEqualsInt("ifle", 0, ifle(0x7fffffff));
    assertEqualsInt("ifle", 1, ifle(0x80000000));

    assertEqualsInt("ifge", 1, ifge(0));
    assertEqualsInt("ifge", 1, ifge(0x7fffffff));
    assertEqualsInt("ifge", 0, ifge(0x80000000));

    assertEqualsInt("if_icmpeq", 1, if_icmpeq(0, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(0x7fffffff, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(0, 0x7fffffff));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(0x80000000, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(0, 0x80000000));

    assertEqualsInt("if_icmpne", 0, if_icmpne(0, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(0x7fffffff, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(0, 0x7fffffff));
    assertEqualsInt("if_icmpne", 1, if_icmpne(0x80000000, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(0, 0x80000000));

    assertEqualsInt("if_icmplt", 0, if_icmplt(0, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(0x7fffffff, 0));
    assertEqualsInt("if_icmplt", 1, if_icmplt(0, 0x7fffffff));
    assertEqualsInt("if_icmplt", 1, if_icmplt(0x80000000, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(0, 0x80000000));

    assertEqualsInt("if_icmpgt", 0, if_icmpgt(0, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(0x7fffffff, 0));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(0, 0x7fffffff));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(0x80000000, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(0, 0x80000000));

    assertEqualsInt("if_icmple", 1, if_icmple(0, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(0x7fffffff, 0));
    assertEqualsInt("if_icmple", 1, if_icmple(0, 0x7fffffff));
    assertEqualsInt("if_icmple", 1, if_icmple(0x80000000, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(0, 0x80000000));

    assertEqualsInt("if_icmpge", 1, if_icmpge(0, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(0x7fffffff, 0));
    assertEqualsInt("if_icmpge", 0, if_icmpge(0, 0x7fffffff));
    assertEqualsInt("if_icmpge", 0, if_icmpge(0x80000000, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(0, 0x80000000));

    assertEqualsInt("ifnull", 1, ifnull(NULL));
    assertEqualsInt("ifnull", 0, ifnull(a));

    assertEqualsInt("ifnonnull", 0, ifnonnull(NULL));
    assertEqualsInt("ifnonnull", 1, ifnonnull(a));

    assertEqualsInt("if_acmpeq", 1, if_acmpeq(NULL, NULL));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(a, b));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(b, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(a, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(b, b));

    assertEqualsInt("if_acmpne", 0, if_acmpne(NULL, NULL));
    assertEqualsInt("if_acmpne", 1, if_acmpne(a, b));
    assertEqualsInt("if_acmpne", 1, if_acmpne(b, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(a, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(b, b));

    assertEqualsInt("tableswitch", 100, tableswitch(0));
    assertEqualsInt("tableswitch", 200, tableswitch(1));
    assertEqualsInt("tableswitch", 300, tableswitch(2));
    assertEqualsInt("tableswitch", 0, tableswitch(-1));
    assertEqualsInt("tableswitch", 0, tableswitch(3));

    assertEqualsInt("lookupswitch", 0, lookupswitch(0));
    assertEqualsInt("lookupswitch", 100, lookupswitch(1));
    assertEqualsInt("lookupswitch", 200, lookupswitch(10));
    assertEqualsInt("lookupswitch", 0, lookupswitch(-1));
    assertEqualsInt("lookupswitch", 0, lookupswitch(3));

    assertEqualsInt("jsr", 2, jsr());

    print_test_result();

    return 0;
}


