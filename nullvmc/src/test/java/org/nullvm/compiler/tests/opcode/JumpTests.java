/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode;

/**
 *
 * @version $Id$
 */
public class JumpTests extends AbstractOpcodeTests {
    
    public String run() {
        
        Object a = new Object();
        Object b = new Object();

        assertEqualsInt("goto", 5, JumpOpcodes._goto());

        assertEqualsInt("ifeq", 1, JumpOpcodes.ifeq(0));
        assertEqualsInt("ifeq", 0, JumpOpcodes.ifeq(0x7fffffff));
        assertEqualsInt("ifeq", 0, JumpOpcodes.ifeq(0x80000000));

        assertEqualsInt("ifne", 0, JumpOpcodes.ifne(0));
        assertEqualsInt("ifne", 1, JumpOpcodes.ifne(0x7fffffff));
        assertEqualsInt("ifeq", 1, JumpOpcodes.ifne(0x80000000));

        assertEqualsInt("iflt", 0, JumpOpcodes.iflt(0));
        assertEqualsInt("iflt", 0, JumpOpcodes.iflt(0x7fffffff));
        assertEqualsInt("iflt", 1, JumpOpcodes.iflt(0x80000000));

        assertEqualsInt("ifgt", 0, JumpOpcodes.ifgt(0));
        assertEqualsInt("ifgt", 1, JumpOpcodes.ifgt(0x7fffffff));
        assertEqualsInt("ifgt", 0, JumpOpcodes.ifgt(0x80000000));

        assertEqualsInt("ifle", 1, JumpOpcodes.ifle(0));
        assertEqualsInt("ifle", 0, JumpOpcodes.ifle(0x7fffffff));
        assertEqualsInt("ifle", 1, JumpOpcodes.ifle(0x80000000));

        assertEqualsInt("ifge", 1, JumpOpcodes.ifge(0));
        assertEqualsInt("ifge", 1, JumpOpcodes.ifge(0x7fffffff));
        assertEqualsInt("ifge", 0, JumpOpcodes.ifge(0x80000000));

        assertEqualsInt("if_icmpeq", 1, JumpOpcodes.if_icmpeq(0, 0));
        assertEqualsInt("if_icmpeq", 0, JumpOpcodes.if_icmpeq(0x7fffffff, 0));
        assertEqualsInt("if_icmpeq", 0, JumpOpcodes.if_icmpeq(0, 0x7fffffff));
        assertEqualsInt("if_icmpeq", 0, JumpOpcodes.if_icmpeq(0x80000000, 0));
        assertEqualsInt("if_icmpeq", 0, JumpOpcodes.if_icmpeq(0, 0x80000000));

        assertEqualsInt("if_icmpne", 0, JumpOpcodes.if_icmpne(0, 0));
        assertEqualsInt("if_icmpne", 1, JumpOpcodes.if_icmpne(0x7fffffff, 0));
        assertEqualsInt("if_icmpne", 1, JumpOpcodes.if_icmpne(0, 0x7fffffff));
        assertEqualsInt("if_icmpne", 1, JumpOpcodes.if_icmpne(0x80000000, 0));
        assertEqualsInt("if_icmpne", 1, JumpOpcodes.if_icmpne(0, 0x80000000));

        assertEqualsInt("if_icmplt", 0, JumpOpcodes.if_icmplt(0, 0));
        assertEqualsInt("if_icmplt", 0, JumpOpcodes.if_icmplt(0x7fffffff, 0));
        assertEqualsInt("if_icmplt", 1, JumpOpcodes.if_icmplt(0, 0x7fffffff));
        assertEqualsInt("if_icmplt", 1, JumpOpcodes.if_icmplt(0x80000000, 0));
        assertEqualsInt("if_icmplt", 0, JumpOpcodes.if_icmplt(0, 0x80000000));

        assertEqualsInt("if_icmpgt", 0, JumpOpcodes.if_icmpgt(0, 0));
        assertEqualsInt("if_icmpgt", 1, JumpOpcodes.if_icmpgt(0x7fffffff, 0));
        assertEqualsInt("if_icmpgt", 0, JumpOpcodes.if_icmpgt(0, 0x7fffffff));
        assertEqualsInt("if_icmpgt", 0, JumpOpcodes.if_icmpgt(0x80000000, 0));
        assertEqualsInt("if_icmpgt", 1, JumpOpcodes.if_icmpgt(0, 0x80000000));

        assertEqualsInt("if_icmple", 1, JumpOpcodes.if_icmple(0, 0));
        assertEqualsInt("if_icmple", 0, JumpOpcodes.if_icmple(0x7fffffff, 0));
        assertEqualsInt("if_icmple", 1, JumpOpcodes.if_icmple(0, 0x7fffffff));
        assertEqualsInt("if_icmple", 1, JumpOpcodes.if_icmple(0x80000000, 0));
        assertEqualsInt("if_icmple", 0, JumpOpcodes.if_icmple(0, 0x80000000));

        assertEqualsInt("if_icmpge", 1, JumpOpcodes.if_icmpge(0, 0));
        assertEqualsInt("if_icmpge", 1, JumpOpcodes.if_icmpge(0x7fffffff, 0));
        assertEqualsInt("if_icmpge", 0, JumpOpcodes.if_icmpge(0, 0x7fffffff));
        assertEqualsInt("if_icmpge", 0, JumpOpcodes.if_icmpge(0x80000000, 0));
        assertEqualsInt("if_icmpge", 1, JumpOpcodes.if_icmpge(0, 0x80000000));

        assertEqualsInt("ifnull", 1, JumpOpcodes.ifnull(null));
        assertEqualsInt("ifnull", 0, JumpOpcodes.ifnull(a));

        assertEqualsInt("ifnonnull", 0, JumpOpcodes.ifnonnull(null));
        assertEqualsInt("ifnonnull", 1, JumpOpcodes.ifnonnull(a));

        assertEqualsInt("if_acmpeq", 1, JumpOpcodes.if_acmpeq(null, null));
        assertEqualsInt("if_acmpeq", 0, JumpOpcodes.if_acmpeq(a, b));
        assertEqualsInt("if_acmpeq", 0, JumpOpcodes.if_acmpeq(b, a));
        assertEqualsInt("if_acmpeq", 1, JumpOpcodes.if_acmpeq(a, a));
        assertEqualsInt("if_acmpeq", 1, JumpOpcodes.if_acmpeq(b, b));

        assertEqualsInt("if_acmpne", 0, JumpOpcodes.if_acmpne(null, null));
        assertEqualsInt("if_acmpne", 1, JumpOpcodes.if_acmpne(a, b));
        assertEqualsInt("if_acmpne", 1, JumpOpcodes.if_acmpne(b, a));
        assertEqualsInt("if_acmpne", 0, JumpOpcodes.if_acmpne(a, a));
        assertEqualsInt("if_acmpne", 0, JumpOpcodes.if_acmpne(b, b));

        assertEqualsInt("tableswitch", 100, JumpOpcodes.tableswitch(0));
        assertEqualsInt("tableswitch", 200, JumpOpcodes.tableswitch(1));
        assertEqualsInt("tableswitch", 300, JumpOpcodes.tableswitch(2));
        assertEqualsInt("tableswitch", 0, JumpOpcodes.tableswitch(-1));
        assertEqualsInt("tableswitch", 0, JumpOpcodes.tableswitch(3));

        assertEqualsInt("lookupswitch", 0, JumpOpcodes.lookupswitch(0));
        assertEqualsInt("lookupswitch", 100, JumpOpcodes.lookupswitch(1));
        assertEqualsInt("lookupswitch", 200, JumpOpcodes.lookupswitch(10));
        assertEqualsInt("lookupswitch", 0, JumpOpcodes.lookupswitch(-1));
        assertEqualsInt("lookupswitch", 0, JumpOpcodes.lookupswitch(3));

        assertEqualsInt("jsr", 2, JumpOpcodes.jsr());
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new JumpTests().run());
    }
    
}
