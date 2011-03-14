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
public class Sitofp extends ConversionInstruction {
    public Sitofp(Variable result, Value op, Type type) {
        super("sitofp", result, op, type);
    }
}
