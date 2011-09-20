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
public class Ptrtoint extends ConversionInstruction {
    public Ptrtoint(Variable result, Value op, Type type) {
        super("ptrtoint", result, op, type);
    }
}
