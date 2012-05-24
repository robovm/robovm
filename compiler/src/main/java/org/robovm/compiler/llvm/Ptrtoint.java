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
public class Ptrtoint extends ConversionInstruction {
    public Ptrtoint(Variable result, Value op, Type type) {
        super("ptrtoint", result, op, type);
    }
}
