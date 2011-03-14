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
public class Zext extends ConversionInstruction {
    public Zext(Variable result, Value op, Type type) {
        super("zext", result, op, type);
    }
}
