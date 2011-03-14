/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.util.HashSet;
import java.util.Set;



/**
 *
 * @version $Id$
 */
public class Store extends Instruction {
    private final Value value;
    private final Value pointer;

    public Store(Value value, Value pointer) {
        this.value = value;
        this.pointer = pointer;
    }

    @Override
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> result = new HashSet<VariableRef>();
        if (value instanceof VariableRef) {
            result.add((VariableRef) value);
        }
        if (pointer instanceof VariableRef) {
            result.add((VariableRef) pointer);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "store " + value.getType() + " " + value + ", " 
                + pointer.getType() + " " + pointer;
    }
}
