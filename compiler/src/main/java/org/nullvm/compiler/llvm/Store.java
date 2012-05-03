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
    private final Ordering ordering;
    private final int alignment;

    public Store(Value value, Value pointer) {
        this(value, pointer, null, -1);
    }

    public Store(Value value, Value pointer, Ordering ordering, int alignment) {
        this.value = value;
        this.pointer = pointer;
        this.ordering = ordering;
        this.alignment = alignment;
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
        StringBuilder sb = new StringBuilder();
        sb.append("store ");
        if (ordering != null) {
            sb.append("atomic ");
        }
        sb.append(value.getType());
        sb.append(" ");
        sb.append(value);
        sb.append(", ");
        sb.append(pointer.getType());
        sb.append(" ");
        sb.append(pointer);
        if (ordering != null) {
            sb.append(" ");
            sb.append(ordering);
        }
        if (alignment > 0) {
            sb.append(", align ");
            sb.append(alignment);            
        }
        return sb.toString();
    }
}
