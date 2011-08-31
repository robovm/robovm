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
public class Frem extends FloatingPointBinaryInstruction {
    public Frem(Variable result, Value op1, Value op2) {
        super("frem", result, op1, op2);
    }
}
