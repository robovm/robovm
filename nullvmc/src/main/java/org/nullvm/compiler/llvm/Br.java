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
public class Br extends Instruction {
    private final Value cond;
    private final BasicBlockRef destTrue;
    private final BasicBlockRef destFalse;

    public Br(BasicBlockRef dest) {
        this.cond = null;
        this.destTrue = dest;
        this.destFalse = null;
    }
    
    public Br(Value cond, BasicBlockRef destTrue, BasicBlockRef destFalse) {
        if (cond.getType() != Type.I1) {
            throw new IllegalArgumentException("Condition must have type " + Type.I1);
        }
        this.cond = cond;
        this.destTrue = destTrue;
        this.destFalse = destFalse;
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (cond instanceof VariableRef) {
            return Collections.singleton((VariableRef) cond);
        }
        return super.getReadsFrom();
    }
    
    @Override
    public String toString() {
        if (cond != null) {
            return "br i1 " + cond + ", label %" + destTrue.getName() + ", label %" + destFalse.getName();
        }
        return "br label %" + destTrue.getName();
    }
}
