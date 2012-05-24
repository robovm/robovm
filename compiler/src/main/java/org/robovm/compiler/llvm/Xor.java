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
public class Xor extends IntegerBinaryInstruction {
    public Xor(Variable result, Value op1, Value op2) {
        super("xor", result, op1, op2);
    }
}
