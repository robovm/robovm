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
public abstract class AggregateType extends UserType {

    protected AggregateType() {
        super();
    }
    
    protected AggregateType(String alias) {
        super(alias);
    }
    
    public abstract Type getTypeAt(int index);
}
