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
public class Fpext extends ConversionInstruction {
    public Fpext(Variable result, Value op, Type type) {
        super("fpext", result, op, type);
    }
}
