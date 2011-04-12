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
    private final boolean constant;

    public Global(String name, Type type) {
        this.name = "@" + name;
        this.linkage = Linkage.external;
        this.value = null;
        this.type = type;
        constant = false;
    }
    
    public Global(String name, Constant value) {
        this(name, null, value, false);
    }
    
    public Global(String name, Constant value, boolean constant) {
        this(name, null, value, constant);
    }
    
    public Global(String name, Linkage linkage, Constant value) {
        this(name, linkage, value, false);
    }
    
    public Global(String name, Linkage linkage, Constant value, boolean constant) {
        this.name = "@" + name;
        this.linkage = linkage;
        this.value = value;
        this.type = value.getType();
        this.constant = constant;        
    }
    
    public GlobalRef ref() {
        return new GlobalRef(this);
    }
    
    public String getName() {
        return name;
    }
    
    public PointerType getType() {
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
        if (constant) {
            sb.append("constant ");
        } else {
            sb.append("global ");
        }
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
