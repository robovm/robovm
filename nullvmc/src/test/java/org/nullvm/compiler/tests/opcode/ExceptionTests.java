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
public class ExceptionTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsInt("_throw(0)", -1, ExceptionOpcodes._throw(0));
        assertEqualsInt("_throw(1)", 1, ExceptionOpcodes._throw(1));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new ExceptionTests().run());
    }
    
}
