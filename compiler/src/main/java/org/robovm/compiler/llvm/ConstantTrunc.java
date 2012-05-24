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
public class ConstantTrunc extends ConversionConstant {
    public ConstantTrunc(Constant cst, Type type) {
        super("trunc", cst, type);
    }
}
