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
public class Mul extends IntegerBinaryInstruction {
    public Mul(Variable result, Value op1, Value op2) {
        super("mul", result, op1, op2);
    }
}
