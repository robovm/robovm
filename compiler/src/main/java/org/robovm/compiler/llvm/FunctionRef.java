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
public class FunctionRef extends Constant {
    private final String name;
    private final FunctionType type;

    public FunctionRef(Function f) {
        this(f.getName(), f.getType());
    }
    
    public FunctionRef(String name, FunctionType type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public FunctionType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "@" + name;
    }
}
