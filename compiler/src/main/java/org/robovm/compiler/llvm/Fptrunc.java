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
public class Fptrunc extends ConversionInstruction {
    public Fptrunc(Variable result, Value op, Type type) {
        super("fptrunc", result, op, type);
    }
}
