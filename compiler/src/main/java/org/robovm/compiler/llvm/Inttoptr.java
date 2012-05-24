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
public class Inttoptr extends ConversionInstruction {
    public Inttoptr(Variable result, Value op, Type type) {
        super("inttoptr", result, op, type);
    }
}
