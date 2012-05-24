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
public class ConstantAdd extends IntegerBinaryConstant {
    public ConstantAdd(Constant op1, Constant op2) {
        super("add", op1, op2);
    }
}
