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
public class Bitcast extends ConversionInstruction {
    public Bitcast(Variable result, Value op, Type type) {
        super("bitcast", result, op, type);
    }
}
