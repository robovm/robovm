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
public abstract class UnaryOpInstruction extends Instruction {
    protected final Variable result;
    protected final Value op;

    protected UnaryOpInstruction(Variable result, Value op) {
        this.result = result;
        this.op = op;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (op instanceof VariableRef) {
            return Collections.singleton((VariableRef) op);
        }
        return super.getReadsFrom();
    }
}
