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
public abstract class ConversionConstant extends Constant {
    private final String name;
    private final Constant cst;
    private final Type type;

    protected ConversionConstant(String name, Constant cst, Type type) {
        this.name = name;
        this.cst = cst;
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " (" + cst.getType() + " " + cst + " to " + type + ")";
    }
}
