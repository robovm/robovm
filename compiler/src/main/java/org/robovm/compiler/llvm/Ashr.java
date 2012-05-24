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
public class Ashr extends IntegerBinaryInstruction {
    public Ashr(Variable result, Value op1, Value op2) {
        super("ashr", result, op1, op2);
    }
}
