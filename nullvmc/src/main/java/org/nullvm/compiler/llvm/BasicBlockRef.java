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
public class BasicBlockRef {
    private final Function function;
    private final Object tag;

    BasicBlockRef(Function function, Object tag) {
        this.function = function;
        this.tag = tag;
    }
    
    public String getName() {
        return function.getLabel(this);
    }
    
    public Object getTag() {
        return tag;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
