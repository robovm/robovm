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
public class Frem extends FloatingPointBinaryInstruction {
    public Frem(Variable result, Value op1, Value op2) {
        super("frem", result, op1, op2);
    }
}
