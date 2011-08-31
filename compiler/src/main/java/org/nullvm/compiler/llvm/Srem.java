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
public class Srem extends IntegerBinaryInstruction {
    public Srem(Variable result, Value op1, Value op2) {
        super("srem", result, op1, op2);
    }
}
