/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public abstract class BinaryOpInstruction extends Instruction {
    protected final Variable result;
    protected final Value op1;
    protected final Value op2;

    protected BinaryOpInstruction(Variable result, Value op1, Value op2) {
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> refs = new HashSet<VariableRef>();
        if (op1 instanceof VariableRef) {
            refs.add((VariableRef) op1);
        }
        if (op2 instanceof VariableRef) {
            refs.add((VariableRef) op2);
        }
        return refs;
    }
}
