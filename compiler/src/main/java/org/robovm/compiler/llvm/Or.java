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
public class Or extends IntegerBinaryInstruction {
    public Or(Variable result, Value op1, Value op2) {
        super("or", result, op1, op2);
    }
}
