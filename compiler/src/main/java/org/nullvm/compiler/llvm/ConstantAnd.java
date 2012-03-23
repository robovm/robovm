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
public class ConstantAnd extends IntegerBinaryConstant {
    public ConstantAnd(Constant op1, Constant op2) {
        super("and", op1, op2);
    }
}
