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
public class ConstantPtrtoint extends ConversionConstant {
    public ConstantPtrtoint(Constant cst, Type type) {
        super("ptrtoint", cst, type);
    }
}
