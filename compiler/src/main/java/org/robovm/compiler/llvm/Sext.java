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
public class Sext extends ConversionInstruction {
    public Sext(Variable result, Value op, Type type) {
        super("sext", result, op, type);
    }
}
