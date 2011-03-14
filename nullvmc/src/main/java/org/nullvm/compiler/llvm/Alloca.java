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
public class Alloca extends Instruction {
    private final Variable result;
    private final Type type;

    public Alloca(Variable result, Type type) {
        this.result = result;
        this.type = type;
    }

    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public String toString() {
        return result + " = alloca " + type;
    }
}
