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
public class And extends IntegerBinaryInstruction {
    public And(Variable result, Value op1, Value op2) {
        super("and", result, op1, op2);
    }
}
