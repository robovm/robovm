/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class ConstantSub extends IntegerBinaryConstant {
    public ConstantSub(Constant op1, Constant op2) {
        super("sub", op1, op2);
    }
}
