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
public class Srem extends IntegerBinaryInstruction {
    public Srem(Variable result, Value op1, Value op2) {
        super("srem", result, op1, op2);
    }
}
