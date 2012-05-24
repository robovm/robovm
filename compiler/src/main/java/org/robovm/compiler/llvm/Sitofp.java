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
public class Sitofp extends ConversionInstruction {
    public Sitofp(Variable result, Value op, Type type) {
        super("sitofp", result, op, type);
    }
}
