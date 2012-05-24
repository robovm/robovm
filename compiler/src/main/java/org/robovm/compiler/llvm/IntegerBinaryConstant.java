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
public abstract class IntegerBinaryConstant extends BinaryOpConstant {
    private final String name;

    protected IntegerBinaryConstant(String name, Constant op1, Constant op2) {
        super(op1, op2);
        if (!op1.isInteger() || !op2.isInteger()) {
            throw new IllegalArgumentException("Integer type expected");
        }
        if (!op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch ");
        }
        this.name = name;
    }
    
    @Override
    public Type getType() {
        return op1.getType();
    }
    
    @Override
    public String toString() {
        return name + " (" + op1.getType() + " " + op1 + ", " + op2.getType() + " " + op2 + ")";
    }
}
