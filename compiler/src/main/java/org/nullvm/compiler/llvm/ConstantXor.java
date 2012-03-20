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
public class ConstantXor extends IntegerBinaryConstant {
    public ConstantXor(Constant op1, Constant op2) {
        super("xor", op1, op2);
    }
}
