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
public class Global {
    private final String name;
    private final Constant value;

    public Global(String name, Constant value) {
        this.name = "@" + name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return new PointerType(value.getType());
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" = global ");
        sb.append(value.getType());
        sb.append(' ');
        sb.append(value);
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return name;
    }
}
