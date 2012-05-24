/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;


/**
 *
 * @version $Id$
 */
public abstract class IntegerBinaryInstruction extends BinaryOpInstruction {
    private final String name;

    protected IntegerBinaryInstruction(String name, Variable result, Value op1, Value op2) {
        super(result, op1, op2);
        if (!result.isInteger() || !op1.isInteger() || !op2.isInteger()) {
            throw new IllegalArgumentException("Integer type expected");
        }
        if (!result.getType().equals(op1.getType()) || !op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch ");
        }
        this.name = name;
    }
    
    @Override
    public String toString() {
        return result + " = " + name + " " + op1.getType() + " " + op1 + ", " + op2;
    }
}
