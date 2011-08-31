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
public class Shl extends IntegerBinaryInstruction {
    public Shl(Variable result, Value op1, Value op2) {
        super("shl", result, op1, op2);
    }
}
