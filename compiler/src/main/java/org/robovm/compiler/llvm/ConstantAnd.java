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
public class ConstantAnd extends IntegerBinaryConstant {
    public ConstantAnd(Constant op1, Constant op2) {
        super("and", op1, op2);
    }
}
