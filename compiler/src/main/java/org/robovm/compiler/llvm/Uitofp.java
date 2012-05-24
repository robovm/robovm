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
public class Uitofp extends ConversionInstruction {
    public Uitofp(Variable result, Value op, Type type) {
        super("uitofp", result, op, type);
    }
}
