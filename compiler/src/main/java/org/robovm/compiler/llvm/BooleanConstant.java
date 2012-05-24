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
public class BooleanConstant extends Constant {
    
    public static final BooleanConstant FALSE = new BooleanConstant(false);
    public static final BooleanConstant TRUE = new BooleanConstant(true);
    
    private final boolean value;

    private BooleanConstant(boolean value) {
        this.value = value;
    }
    
    @Override
    public Type getType() {
        return Type.I1;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
