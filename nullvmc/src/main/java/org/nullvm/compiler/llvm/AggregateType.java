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
public abstract class AggregateType extends UserType {

    protected AggregateType() {
        super();
    }
    
    protected AggregateType(String alias) {
        super(alias);
    }
    
    public abstract Type getTypeAt(int index);
}
