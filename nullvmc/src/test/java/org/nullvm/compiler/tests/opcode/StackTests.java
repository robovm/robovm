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
public class StackTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsInt("swap_int", -1, StackOpcodes.swap_int(3, 2));

        assertEqualsInt("dup_int", 100, StackOpcodes.dup_int(10));

        assertEqualsInt("dup2_int", 81, StackOpcodes.dup2_int(3));
        assertEqualsInt("dup2_int", 0, StackOpcodes.dup2_int(0x80000000));

        assertEqualsDouble("dup2_double", 9.0, StackOpcodes.dup2_double(3.0));
        assertEqualsDouble("dup2_double", 1.0/0.0, StackOpcodes.dup2_double(0x1.fffffffffffffP+1023));
        assertEqualsLong("dup2_long", 9L, StackOpcodes.dup2_long(3L));
        assertEqualsLong("dup2_long", 0L, StackOpcodes.dup2_long(0x8000000000000000L));

        assertEqualsInt("dup_x1", -88, StackOpcodes.dup_x1(9, 11));
        assertEqualsInt("dup_x1", 168, StackOpcodes.dup_x1(-20, 8));

        assertEqualsInt("dup_x2(int, int, int)", -121, StackOpcodes.dup_x2(9, 11, 13));
        assertEqualsInt("dup_x2(int, int, int)", -34, StackOpcodes.dup_x2(-20, 8, 2));
        assertEqualsInt("dup_x2(long, int)", -88, StackOpcodes.dup_x2(9L, 11));

        assertEqualsInt("dup2_x1(int, int, int)", -64, StackOpcodes.dup2_x1(2, 16, 2));
        assertEqualsInt("dup2_x1(int, long)", -88, StackOpcodes.dup2_x1(9, 11L));

        assertEqualsInt("dup2_x2(int, int, int, int)", 8, StackOpcodes.dup2_x2(2, 2, 16, 2));
        assertEqualsDouble("dup2_x2(double, double)", -88.0, StackOpcodes.dup2_x2(9.0, 11.0));
        assertEqualsDouble("dup2_x2(double, double)", 168.0, StackOpcodes.dup2_x2(-20.0, 8.0));
        assertEqualsInt("dup2_x2(int, int, long)", -32, StackOpcodes.dup2_x2(2, 16, 2L));
        assertEqualsInt("dup2_x2(long, int, int)", -512, StackOpcodes.dup2_x2(2L, 16, 2));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new StackTests().run());
    }
    
}
