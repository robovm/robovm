/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class Fcmp extends BinaryOpInstruction {
    
    public enum Condition {oeq, ogt, oge, olt, ole, one, ord, 
        ueq, ugt, uge, ult, ule, une, uno}
    
    private final Condition cond;

    public Fcmp(Variable result, Condition cond, Value op1, Value op2) {
        super(result, op1, op2);
        if (result.getType() != Type.I1) {
            throw new IllegalArgumentException("i1 type expected as result");
        }
        if (!op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch");
        }
        if (!op1.isFloatingPoint()) {
            throw new IllegalArgumentException("Floating point type expected");
        }
        this.cond = cond;
    }
    
    @Override
    public String toString() {
        return result + " = fcmp " + cond + " " + op1.getType() + " " + op1 + ", " + op2;
    }
}
