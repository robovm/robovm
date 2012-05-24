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
public class Sub extends IntegerBinaryInstruction {
    public Sub(Variable result, Value op1, Value op2) {
        super("sub", result, op1, op2);
    }
}
