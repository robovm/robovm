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
public class Add extends IntegerBinaryInstruction {
    public Add(Variable result, Value op1, Value op2) {
        super("add", result, op1, op2);
    }
}
