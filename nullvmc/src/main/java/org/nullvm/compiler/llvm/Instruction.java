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
public abstract class Instruction {
    BasicBlock basicBlock;

    public Set<Variable> getWritesTo() {
        return Collections.emptySet();
    }
    
    public Set<VariableRef> getReadsFrom() {
        return Collections.emptySet();
    }
    
}
