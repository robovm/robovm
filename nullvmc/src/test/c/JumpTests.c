#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* JumpTests = nvmGetClass("org/nullvm/compiler/tests/opcode/JumpOpcodes", "org_nullvm_compiler_tests_opcode_JumpOpcodes", NULL);
    jint (*_goto)(jclass*) = j_get_method_impl(JumpTests, "_goto", "()I", JumpTests);
    jint (*ifeq)(jclass*, jint) = j_get_method_impl(JumpTests, "ifeq", "(I)I", JumpTests);
    jint (*ifne)(jclass*, jint) = j_get_method_impl(JumpTests, "ifne", "(I)I", JumpTests);
    jint (*iflt)(jclass*, jint) = j_get_method_impl(JumpTests, "iflt", "(I)I", JumpTests);
    jint (*ifgt)(jclass*, jint) = j_get_method_impl(JumpTests, "ifgt", "(I)I", JumpTests);
    jint (*ifle)(jclass*, jint) = j_get_method_impl(JumpTests, "ifle", "(I)I", JumpTests);
    jint (*ifge)(jclass*, jint) = j_get_method_impl(JumpTests, "ifge", "(I)I", JumpTests);
    jint (*if_icmpeq)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmpeq", "(II)I", JumpTests);
    jint (*if_icmpne)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmpne", "(II)I", JumpTests);
    jint (*if_icmplt)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmplt", "(II)I", JumpTests);
    jint (*if_icmpgt)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmpgt", "(II)I", JumpTests);
    jint (*if_icmple)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmple", "(II)I", JumpTests);
    jint (*if_icmpge)(jclass*, jint, jint) = j_get_method_impl(JumpTests, "if_icmpge", "(II)I", JumpTests);
    jint (*ifnull)(jclass*, jobject*) = j_get_method_impl(JumpTests, "ifnull", "(Ljava/lang/Object;)I", JumpTests);
    jint (*ifnonnull)(jclass*, jobject*) = j_get_method_impl(JumpTests, "ifnonnull", "(Ljava/lang/Object;)I", JumpTests);
    jint (*if_acmpeq)(jclass*, jobject*, jobject*) = j_get_method_impl(JumpTests, "if_acmpeq", "(Ljava/lang/Object;Ljava/lang/Object;)I", JumpTests);
    jint (*if_acmpne)(jclass*, jobject*, jobject*) = j_get_method_impl(JumpTests, "if_acmpne", "(Ljava/lang/Object;Ljava/lang/Object;)I", JumpTests);
    jint (*tableswitch)(jclass*, jint) = j_get_method_impl(JumpTests, "tableswitch", "(I)I", JumpTests);
    jint (*lookupswitch)(jclass*, jint) = j_get_method_impl(JumpTests, "lookupswitch", "(I)I", JumpTests);
    jint (*jsr)(jclass*) = j_get_method_impl(JumpTests, "jsr", "()I", JumpTests);

    jobject* a = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));
    jobject* b = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));

    assertEqualsInt("goto", 5, _goto(JumpTests));

    assertEqualsInt("ifeq", 1, ifeq(JumpTests, 0));
    assertEqualsInt("ifeq", 0, ifeq(JumpTests, 0x7fffffff));
    assertEqualsInt("ifeq", 0, ifeq(JumpTests, 0x80000000));

    assertEqualsInt("ifne", 0, ifne(JumpTests, 0));
    assertEqualsInt("ifne", 1, ifne(JumpTests, 0x7fffffff));
    assertEqualsInt("ifeq", 1, ifne(JumpTests, 0x80000000));

    assertEqualsInt("iflt", 0, iflt(JumpTests, 0));
    assertEqualsInt("iflt", 0, iflt(JumpTests, 0x7fffffff));
    assertEqualsInt("iflt", 1, iflt(JumpTests, 0x80000000));

    assertEqualsInt("ifgt", 0, ifgt(JumpTests, 0));
    assertEqualsInt("ifgt", 1, ifgt(JumpTests, 0x7fffffff));
    assertEqualsInt("ifgt", 0, ifgt(JumpTests, 0x80000000));

    assertEqualsInt("ifle", 1, ifle(JumpTests, 0));
    assertEqualsInt("ifle", 0, ifle(JumpTests, 0x7fffffff));
    assertEqualsInt("ifle", 1, ifle(JumpTests, 0x80000000));

    assertEqualsInt("ifge", 1, ifge(JumpTests, 0));
    assertEqualsInt("ifge", 1, ifge(JumpTests, 0x7fffffff));
    assertEqualsInt("ifge", 0, ifge(JumpTests, 0x80000000));

    assertEqualsInt("if_icmpeq", 1, if_icmpeq(JumpTests, 0, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(JumpTests, 0, 0x80000000));

    assertEqualsInt("if_icmpne", 0, if_icmpne(JumpTests, 0, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmpne", 1, if_icmpne(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(JumpTests, 0, 0x80000000));

    assertEqualsInt("if_icmplt", 0, if_icmplt(JumpTests, 0, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmplt", 1, if_icmplt(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmplt", 1, if_icmplt(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(JumpTests, 0, 0x80000000));

    assertEqualsInt("if_icmpgt", 0, if_icmpgt(JumpTests, 0, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(JumpTests, 0, 0x80000000));

    assertEqualsInt("if_icmple", 1, if_icmple(JumpTests, 0, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmple", 1, if_icmple(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmple", 1, if_icmple(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(JumpTests, 0, 0x80000000));

    assertEqualsInt("if_icmpge", 1, if_icmpge(JumpTests, 0, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(JumpTests, 0x7fffffff, 0));
    assertEqualsInt("if_icmpge", 0, if_icmpge(JumpTests, 0, 0x7fffffff));
    assertEqualsInt("if_icmpge", 0, if_icmpge(JumpTests, 0x80000000, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(JumpTests, 0, 0x80000000));

    assertEqualsInt("ifnull", 1, ifnull(JumpTests, NULL));
    assertEqualsInt("ifnull", 0, ifnull(JumpTests, a));

    assertEqualsInt("ifnonnull", 0, ifnonnull(JumpTests, NULL));
    assertEqualsInt("ifnonnull", 1, ifnonnull(JumpTests, a));

    assertEqualsInt("if_acmpeq", 1, if_acmpeq(JumpTests, NULL, NULL));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(JumpTests, a, b));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(JumpTests, b, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(JumpTests, a, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(JumpTests, b, b));

    assertEqualsInt("if_acmpne", 0, if_acmpne(JumpTests, NULL, NULL));
    assertEqualsInt("if_acmpne", 1, if_acmpne(JumpTests, a, b));
    assertEqualsInt("if_acmpne", 1, if_acmpne(JumpTests, b, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(JumpTests, a, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(JumpTests, b, b));

    assertEqualsInt("tableswitch", 100, tableswitch(JumpTests, 0));
    assertEqualsInt("tableswitch", 200, tableswitch(JumpTests, 1));
    assertEqualsInt("tableswitch", 300, tableswitch(JumpTests, 2));
    assertEqualsInt("tableswitch", 0, tableswitch(JumpTests, -1));
    assertEqualsInt("tableswitch", 0, tableswitch(JumpTests, 3));

    assertEqualsInt("lookupswitch", 0, lookupswitch(JumpTests, 0));
    assertEqualsInt("lookupswitch", 100, lookupswitch(JumpTests, 1));
    assertEqualsInt("lookupswitch", 200, lookupswitch(JumpTests, 10));
    assertEqualsInt("lookupswitch", 0, lookupswitch(JumpTests, -1));
    assertEqualsInt("lookupswitch", 0, lookupswitch(JumpTests, 3));

    assertEqualsInt("jsr", 2, jsr(JumpTests));

    print_test_result();

    return 0;
}


