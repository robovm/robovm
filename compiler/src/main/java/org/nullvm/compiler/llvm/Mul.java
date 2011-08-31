/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class Mul extends IntegerBinaryInstruction {
    public Mul(Variable result, Value op1, Value op2) {
        super("mul", result, op1, op2);
    }
}
