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
public class ConstantBitcast extends ConversionConstant {
    public ConstantBitcast(Constant cst, Type type) {
        super("bitcast", cst, type);
    }
}
