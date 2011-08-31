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
public class Sext extends ConversionInstruction {
    public Sext(Variable result, Value op, Type type) {
        super("sext", result, op, type);
    }
}
