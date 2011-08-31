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
public class ConstantBitcast extends ConversionConstant {
    public ConstantBitcast(Constant cst, Type type) {
        super("bitcast", cst, type);
    }
}
