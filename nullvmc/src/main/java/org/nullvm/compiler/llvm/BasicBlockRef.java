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
    private final Label label;

    BasicBlockRef(Function function, Label label) {
        this.function = function;
        this.label = label;
    }
    
    public String getName() {
        return function.getLabel(this);
    }
    
    public Label getLabel() {
        return label;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
