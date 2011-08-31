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
public class Trunc extends ConversionInstruction {
    public Trunc(Variable result, Value op, Type type) {
        super("trunc", result, op, type);
    }
}
