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
public class Uitofp extends ConversionInstruction {
    public Uitofp(Variable result, Value op, Type type) {
        super("uitofp", result, op, type);
    }
}
