/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class Getelementptr extends Instruction {
    private final Variable result;
    private final Value ptr;
    private final Value[] idx;

    public Getelementptr(Variable result, Value ptr, int ... idx) {
        if (!ptr.isPointer()) {
            throw new IllegalArgumentException("PointerType expected");
        }
        if (idx.length > 1 && !(((PointerType) ptr.getType()).getBase() instanceof AggregateType)) {
            throw new IllegalArgumentException("PointerType should point to AggregateType");
        }
        if (idx == null || idx.length == 0) {
            throw new IllegalArgumentException("No indexes");
        }
        this.result = result;
        this.ptr = ptr;
        this.idx = new Value[idx.length];
        for (int i = 0; i < idx.length; i++) {
            this.idx[i] = new IntegerConstant(idx[i]);
        }
    }

    public Getelementptr(Variable result, Value ptr, Value ... idx) {
        if (!ptr.isPointer()) {
            throw new IllegalArgumentException("PointerType expected");
        }
        if (idx.length > 1 && !(((PointerType) ptr.getType()).getBase() instanceof AggregateType)) {
            throw new IllegalArgumentException("PointerType should point to AggregateType");
        }
        if (idx == null || idx.length == 0) {
            throw new IllegalArgumentException("No indexes");
        }
        this.result = result;
        this.ptr = ptr;
        this.idx = idx;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (ptr instanceof VariableRef) {
            return Collections.singleton((VariableRef) ptr);
        }
        return super.getReadsFrom();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.append(" = getelementptr ");
        sb.append(ptr.getType());
        sb.append(' ');
        sb.append(ptr);
        for (int i = 0; i < idx.length; i++) {
            sb.append(", ");
            sb.append(idx[i].getType());
            sb.append(" ");
            sb.append(idx[i]);
        }
        return sb.toString();
    }
}
