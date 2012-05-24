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
public class Icmp extends BinaryOpInstruction {
    
    public enum Condition {eq, ne, ugt, uge, ult, ule, sgt, sge, slt, sle}
    
    private final Condition cond;

    public Icmp(Variable result, Condition cond, Value op1, Value op2) {
        super(result, op1, op2);
        if (result.getType() != Type.I1) {
            throw new IllegalArgumentException("i1 type expected as result");
        }
        if (!op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch");
        }
        if (!op1.isInteger() && !op1.isPointer()) {
            throw new IllegalArgumentException("Integer or pointer type expected");
        }
        this.cond = cond;
    }
    
    @Override
    public String toString() {
        return result + " = icmp " + cond + " " + op1.getType() + " " + op1 + ", " + op2;
    }
}
