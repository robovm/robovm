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
public class Bitcast extends ConversionInstruction {
    public Bitcast(Variable result, Value op, Type type) {
        super("bitcast", result, op, type);
    }
}
