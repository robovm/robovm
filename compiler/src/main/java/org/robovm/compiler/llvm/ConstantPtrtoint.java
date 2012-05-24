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
public class ConstantPtrtoint extends ConversionConstant {
    public ConstantPtrtoint(Constant cst, Type type) {
        super("ptrtoint", cst, type);
    }
}
