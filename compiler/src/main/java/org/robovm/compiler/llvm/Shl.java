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
public class Shl extends IntegerBinaryInstruction {
    public Shl(Variable result, Value op1, Value op2) {
        super("shl", result, op1, op2);
    }
}
