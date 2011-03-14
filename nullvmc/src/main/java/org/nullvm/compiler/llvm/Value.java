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
public abstract class Value {
    
    public boolean isInteger() {
        return getType() instanceof IntegerType;
    }
    
    public boolean isFloatingPoint() {
        return getType() instanceof FloatingPointType;
    }
    
    public boolean isPointer() {
        return getType() instanceof PointerType;
    }
    
    public boolean isFunction() {
        return getType() instanceof FunctionType;
    }
    
    public abstract Type getType();
}
