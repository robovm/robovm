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
    private final Linkage linkage;
    private final Constant value;
    private final Type type;

    public Global(String name, Type type) {
        this.name = "@" + name;
        this.linkage = Linkage.external;
        this.value = null;
        this.type = type;
    }
    
    public Global(String name, Constant value) {
        this(name, null, value);
    }
    
    public Global(String name, Linkage linkage, Constant value) {
        this.name = "@" + name;
        this.linkage = linkage;
        this.value = value;
        this.type = value.getType();
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return new PointerType(type);
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" = ");
        if (linkage != null) {
            sb.append(linkage);
            sb.append(' ');
        }
        sb.append("global ");
        sb.append(type);
        if (value != null) {
            sb.append(' ');
            sb.append(value);
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return name;
    }
}
