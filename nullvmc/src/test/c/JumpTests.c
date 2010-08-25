#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* JumpOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/JumpOpcodes");
    jint (*_goto)(Env*) = nvmGetClassMethod(env, JumpOpcodes, "_goto", "()I")->impl;
    jint (*ifeq)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifeq", "(I)I")->impl;
    jint (*ifne)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifne", "(I)I")->impl;
    jint (*iflt)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "iflt", "(I)I")->impl;
    jint (*ifgt)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifgt", "(I)I")->impl;
    jint (*ifle)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifle", "(I)I")->impl;
    jint (*ifge)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifge", "(I)I")->impl;
    jint (*if_icmpeq)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpeq", "(II)I")->impl;
    jint (*if_icmpne)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpne", "(II)I")->impl;
    jint (*if_icmplt)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmplt", "(II)I")->impl;
    jint (*if_icmpgt)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpgt", "(II)I")->impl;
    jint (*if_icmple)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmple", "(II)I")->impl;
    jint (*if_icmpge)(Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpge", "(II)I")->impl;
    jint (*ifnull)(Env*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "ifnull", "(Ljava/lang/Object;)I")->impl;
    jint (*ifnonnull)(Env*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "ifnonnull", "(Ljava/lang/Object;)I")->impl;
    jint (*if_acmpeq)(Env*, Object*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "if_acmpeq", "(Ljava/lang/Object;Ljava/lang/Object;)I")->impl;
    jint (*if_acmpne)(Env*, Object*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "if_acmpne", "(Ljava/lang/Object;Ljava/lang/Object;)I")->impl;
    jint (*tableswitch)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "tableswitch", "(I)I")->impl;
    jint (*lookupswitch)(Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "lookupswitch", "(I)I")->impl;
    jint (*jsr)(Env*) = nvmGetClassMethod(env, JumpOpcodes, "jsr", "()I")->impl;

    Object* a = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));
    Object* b = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));

    assertEqualsInt("goto", 5, _goto(env));

    assertEqualsInt("ifeq", 1, ifeq(env, 0));
    assertEqualsInt("ifeq", 0, ifeq(env, 0x7fffffff));
    assertEqualsInt("ifeq", 0, ifeq(env, 0x80000000));

    assertEqualsInt("ifne", 0, ifne(env, 0));
    assertEqualsInt("ifne", 1, ifne(env, 0x7fffffff));
    assertEqualsInt("ifeq", 1, ifne(env, 0x80000000));

    assertEqualsInt("iflt", 0, iflt(env, 0));
    assertEqualsInt("iflt", 0, iflt(env, 0x7fffffff));
    assertEqualsInt("iflt", 1, iflt(env, 0x80000000));

    assertEqualsInt("ifgt", 0, ifgt(env, 0));
    assertEqualsInt("ifgt", 1, ifgt(env, 0x7fffffff));
    assertEqualsInt("ifgt", 0, ifgt(env, 0x80000000));

    assertEqualsInt("ifle", 1, ifle(env, 0));
    assertEqualsInt("ifle", 0, ifle(env, 0x7fffffff));
    assertEqualsInt("ifle", 1, ifle(env, 0x80000000));

    assertEqualsInt("ifge", 1, ifge(env, 0));
    assertEqualsInt("ifge", 1, ifge(env, 0x7fffffff));
    assertEqualsInt("ifge", 0, ifge(env, 0x80000000));

    assertEqualsInt("if_icmpeq", 1, if_icmpeq(env, 0, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(env, 0x80000000, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(env, 0, 0x80000000));

    assertEqualsInt("if_icmpne", 0, if_icmpne(env, 0, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpne", 1, if_icmpne(env, 0x80000000, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(env, 0, 0x80000000));

    assertEqualsInt("if_icmplt", 0, if_icmplt(env, 0, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmplt", 1, if_icmplt(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmplt", 1, if_icmplt(env, 0x80000000, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(env, 0, 0x80000000));

    assertEqualsInt("if_icmpgt", 0, if_icmpgt(env, 0, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(env, 0x80000000, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(env, 0, 0x80000000));

    assertEqualsInt("if_icmple", 1, if_icmple(env, 0, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmple", 1, if_icmple(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmple", 1, if_icmple(env, 0x80000000, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(env, 0, 0x80000000));

    assertEqualsInt("if_icmpge", 1, if_icmpge(env, 0, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpge", 0, if_icmpge(env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpge", 0, if_icmpge(env, 0x80000000, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(env, 0, 0x80000000));

    assertEqualsInt("ifnull", 1, ifnull(env, NULL));
    assertEqualsInt("ifnull", 0, ifnull(env, a));

    assertEqualsInt("ifnonnull", 0, ifnonnull(env, NULL));
    assertEqualsInt("ifnonnull", 1, ifnonnull(env, a));

    assertEqualsInt("if_acmpeq", 1, if_acmpeq(env, NULL, NULL));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(env, a, b));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(env, b, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(env, a, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(env, b, b));

    assertEqualsInt("if_acmpne", 0, if_acmpne(env, NULL, NULL));
    assertEqualsInt("if_acmpne", 1, if_acmpne(env, a, b));
    assertEqualsInt("if_acmpne", 1, if_acmpne(env, b, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(env, a, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(env, b, b));

    assertEqualsInt("tableswitch", 100, tableswitch(env, 0));
    assertEqualsInt("tableswitch", 200, tableswitch(env, 1));
    assertEqualsInt("tableswitch", 300, tableswitch(env, 2));
    assertEqualsInt("tableswitch", 0, tableswitch(env, -1));
    assertEqualsInt("tableswitch", 0, tableswitch(env, 3));

    assertEqualsInt("lookupswitch", 0, lookupswitch(env, 0));
    assertEqualsInt("lookupswitch", 100, lookupswitch(env, 1));
    assertEqualsInt("lookupswitch", 200, lookupswitch(env, 10));
    assertEqualsInt("lookupswitch", 0, lookupswitch(env, -1));
    assertEqualsInt("lookupswitch", 0, lookupswitch(env, 3));

    assertEqualsInt("jsr", 2, jsr(env));

    print_test_result();

    return 0;
}


