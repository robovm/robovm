/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public abstract class ConversionInstruction extends UnaryOpInstruction {
    private final String name;
    private final Type type;
    
    protected ConversionInstruction(String name, Variable result, Value op, Type type) {
        super(result, op);
        this.name = name;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return result + " = " + name + " " + op.getType() + " " + op + " to " + type;
    }
}
