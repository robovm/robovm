/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class Ret extends Instruction {
    private final Value value;

    public Ret() {
        this.value = null;
    }
    
    public Ret(Value value) {
        this.value = value;
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (value instanceof VariableRef) {
            return Collections.singleton((VariableRef) value);
        }
        return super.getReadsFrom();
    }
    
    @Override
    public String toString() {
        if (value != null) {
            return "ret " + value.getType() + " " + value;
        }
        return "ret void";
    }
}
