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
public class Zext extends ConversionInstruction {
    public Zext(Variable result, Value op, Type type) {
        super("zext", result, op, type);
    }
}
