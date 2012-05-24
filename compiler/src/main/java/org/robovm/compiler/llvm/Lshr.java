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
public class Lshr extends IntegerBinaryInstruction {
    public Lshr(Variable result, Value op1, Value op2) {
        super("lshr", result, op1, op2);
    }
}
