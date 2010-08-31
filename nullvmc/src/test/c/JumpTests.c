#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* JumpOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/JumpOpcodes");
    jint (*_goto)(void*, Env*) = nvmGetClassMethod(env, JumpOpcodes, "_goto", "()I")->impl;
    jint (*ifeq)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifeq", "(I)I")->impl;
    jint (*ifne)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifne", "(I)I")->impl;
    jint (*iflt)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "iflt", "(I)I")->impl;
    jint (*ifgt)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifgt", "(I)I")->impl;
    jint (*ifle)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifle", "(I)I")->impl;
    jint (*ifge)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "ifge", "(I)I")->impl;
    jint (*if_icmpeq)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpeq", "(II)I")->impl;
    jint (*if_icmpne)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpne", "(II)I")->impl;
    jint (*if_icmplt)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmplt", "(II)I")->impl;
    jint (*if_icmpgt)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpgt", "(II)I")->impl;
    jint (*if_icmple)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmple", "(II)I")->impl;
    jint (*if_icmpge)(void*, Env*, jint, jint) = nvmGetClassMethod(env, JumpOpcodes, "if_icmpge", "(II)I")->impl;
    jint (*ifnull)(void*, Env*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "ifnull", "(Ljava/lang/Object;)I")->impl;
    jint (*ifnonnull)(void*, Env*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "ifnonnull", "(Ljava/lang/Object;)I")->impl;
    jint (*if_acmpeq)(void*, Env*, Object*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "if_acmpeq", "(Ljava/lang/Object;Ljava/lang/Object;)I")->impl;
    jint (*if_acmpne)(void*, Env*, Object*, Object*) = nvmGetClassMethod(env, JumpOpcodes, "if_acmpne", "(Ljava/lang/Object;Ljava/lang/Object;)I")->impl;
    jint (*tableswitch)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "tableswitch", "(I)I")->impl;
    jint (*lookupswitch)(void*, Env*, jint) = nvmGetClassMethod(env, JumpOpcodes, "lookupswitch", "(I)I")->impl;
    jint (*jsr)(void*, Env*) = nvmGetClassMethod(env, JumpOpcodes, "jsr", "()I")->impl;

    Object* a = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));
    Object* b = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));

    assertEqualsInt("goto", 5, _goto(NULL, env));

    assertEqualsInt("ifeq", 1, ifeq(NULL, env, 0));
    assertEqualsInt("ifeq", 0, ifeq(NULL, env, 0x7fffffff));
    assertEqualsInt("ifeq", 0, ifeq(NULL, env, 0x80000000));

    assertEqualsInt("ifne", 0, ifne(NULL, env, 0));
    assertEqualsInt("ifne", 1, ifne(NULL, env, 0x7fffffff));
    assertEqualsInt("ifeq", 1, ifne(NULL, env, 0x80000000));

    assertEqualsInt("iflt", 0, iflt(NULL, env, 0));
    assertEqualsInt("iflt", 0, iflt(NULL, env, 0x7fffffff));
    assertEqualsInt("iflt", 1, iflt(NULL, env, 0x80000000));

    assertEqualsInt("ifgt", 0, ifgt(NULL, env, 0));
    assertEqualsInt("ifgt", 1, ifgt(NULL, env, 0x7fffffff));
    assertEqualsInt("ifgt", 0, ifgt(NULL, env, 0x80000000));

    assertEqualsInt("ifle", 1, ifle(NULL, env, 0));
    assertEqualsInt("ifle", 0, ifle(NULL, env, 0x7fffffff));
    assertEqualsInt("ifle", 1, ifle(NULL, env, 0x80000000));

    assertEqualsInt("ifge", 1, ifge(NULL, env, 0));
    assertEqualsInt("ifge", 1, ifge(NULL, env, 0x7fffffff));
    assertEqualsInt("ifge", 0, ifge(NULL, env, 0x80000000));

    assertEqualsInt("if_icmpeq", 1, if_icmpeq(NULL, env, 0, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmpeq", 0, if_icmpeq(NULL, env, 0, 0x80000000));

    assertEqualsInt("if_icmpne", 0, if_icmpne(NULL, env, 0, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpne", 1, if_icmpne(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmpne", 1, if_icmpne(NULL, env, 0, 0x80000000));

    assertEqualsInt("if_icmplt", 0, if_icmplt(NULL, env, 0, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmplt", 1, if_icmplt(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmplt", 1, if_icmplt(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmplt", 0, if_icmplt(NULL, env, 0, 0x80000000));

    assertEqualsInt("if_icmpgt", 0, if_icmpgt(NULL, env, 0, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpgt", 0, if_icmpgt(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmpgt", 1, if_icmpgt(NULL, env, 0, 0x80000000));

    assertEqualsInt("if_icmple", 1, if_icmple(NULL, env, 0, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmple", 1, if_icmple(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmple", 1, if_icmple(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmple", 0, if_icmple(NULL, env, 0, 0x80000000));

    assertEqualsInt("if_icmpge", 1, if_icmpge(NULL, env, 0, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(NULL, env, 0x7fffffff, 0));
    assertEqualsInt("if_icmpge", 0, if_icmpge(NULL, env, 0, 0x7fffffff));
    assertEqualsInt("if_icmpge", 0, if_icmpge(NULL, env, 0x80000000, 0));
    assertEqualsInt("if_icmpge", 1, if_icmpge(NULL, env, 0, 0x80000000));

    assertEqualsInt("ifnull", 1, ifnull(NULL, env, NULL));
    assertEqualsInt("ifnull", 0, ifnull(NULL, env, a));

    assertEqualsInt("ifnonnull", 0, ifnonnull(NULL, env, NULL));
    assertEqualsInt("ifnonnull", 1, ifnonnull(NULL, env, a));

    assertEqualsInt("if_acmpeq", 1, if_acmpeq(NULL, env, NULL, NULL));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(NULL, env, a, b));
    assertEqualsInt("if_acmpeq", 0, if_acmpeq(NULL, env, b, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(NULL, env, a, a));
    assertEqualsInt("if_acmpeq", 1, if_acmpeq(NULL, env, b, b));

    assertEqualsInt("if_acmpne", 0, if_acmpne(NULL, env, NULL, NULL));
    assertEqualsInt("if_acmpne", 1, if_acmpne(NULL, env, a, b));
    assertEqualsInt("if_acmpne", 1, if_acmpne(NULL, env, b, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(NULL, env, a, a));
    assertEqualsInt("if_acmpne", 0, if_acmpne(NULL, env, b, b));

    assertEqualsInt("tableswitch", 100, tableswitch(NULL, env, 0));
    assertEqualsInt("tableswitch", 200, tableswitch(NULL, env, 1));
    assertEqualsInt("tableswitch", 300, tableswitch(NULL, env, 2));
    assertEqualsInt("tableswitch", 0, tableswitch(NULL, env, -1));
    assertEqualsInt("tableswitch", 0, tableswitch(NULL, env, 3));

    assertEqualsInt("lookupswitch", 0, lookupswitch(NULL, env, 0));
    assertEqualsInt("lookupswitch", 100, lookupswitch(NULL, env, 1));
    assertEqualsInt("lookupswitch", 200, lookupswitch(NULL, env, 10));
    assertEqualsInt("lookupswitch", 0, lookupswitch(NULL, env, -1));
    assertEqualsInt("lookupswitch", 0, lookupswitch(NULL, env, 3));

    assertEqualsInt("jsr", 2, jsr(NULL, env));

    print_test_result();

    return 0;
}


