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
public class Fmul extends FloatingPointBinaryInstruction {
    public Fmul(Variable result, Value op1, Value op2) {
        super("fmul", result, op1, op2);
    }
}
