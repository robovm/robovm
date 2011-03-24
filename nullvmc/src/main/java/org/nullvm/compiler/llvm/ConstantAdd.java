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
public class ConstantAdd extends IntegerBinaryConstant {
    public ConstantAdd(Constant op1, Constant op2) {
        super("add", op1, op2);
    }
}
