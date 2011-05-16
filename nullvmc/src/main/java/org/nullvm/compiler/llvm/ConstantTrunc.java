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
public class ConstantTrunc extends ConversionConstant {
    public ConstantTrunc(Constant cst, Type type) {
        super("trunc", cst, type);
    }
}
