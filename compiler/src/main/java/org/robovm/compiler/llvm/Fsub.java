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
public class Fsub extends FloatingPointBinaryInstruction {
    public Fsub(Variable result, Value op1, Value op2) {
        super("fsub", result, op1, op2);
    }
}
