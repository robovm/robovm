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
public class Fadd extends FloatingPointBinaryInstruction {
    public Fadd(Variable result, Value op1, Value op2) {
        super("fadd", result, op1, op2);
    }
}
