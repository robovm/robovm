/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class Fpext extends ConversionInstruction {
    public Fpext(Variable result, Value op, Type type) {
        super("fpext", result, op, type);
    }
}
