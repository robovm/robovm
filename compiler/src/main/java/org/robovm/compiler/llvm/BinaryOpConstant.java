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
public abstract class BinaryOpConstant extends Constant {
    protected final Constant op1;
    protected final Constant op2;

    protected BinaryOpConstant(Constant op1, Constant op2) {
        this.op1 = op1;
        this.op2 = op2;
    }
}
