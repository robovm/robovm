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
public class ConstantXor extends IntegerBinaryConstant {
    public ConstantXor(Constant op1, Constant op2) {
        super("xor", op1, op2);
    }
}
