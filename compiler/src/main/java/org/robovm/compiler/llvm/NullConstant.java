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
public class NullConstant extends Constant {
    
    private final Type type;

    public NullConstant(Type type) {
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "null";
    }
}
