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
public class Sdiv extends IntegerBinaryInstruction {
    public Sdiv(Variable result, Value op1, Value op2) {
        super("sdiv", result, op1, op2);
    }
}
