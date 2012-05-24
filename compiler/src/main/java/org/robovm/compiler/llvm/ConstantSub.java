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
public class ConstantSub extends IntegerBinaryConstant {
    public ConstantSub(Constant op1, Constant op2) {
        super("sub", op1, op2);
    }
}
