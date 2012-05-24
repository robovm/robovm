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
public class Fdiv extends FloatingPointBinaryInstruction {
    public Fdiv(Variable result, Value op1, Value op2) {
        super("fdiv", result, op1, op2);
    }
}
