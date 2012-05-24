/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;


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
    private final String section;

    public Global(String name, Type type) {
        this(name, Linkage.external, type, false);
    }
    
    public Global(String name, Type type, boolean constant) {
        this(name, Linkage.external, type, constant);
    }
    
    public Global(String name, Linkage linkage, Type type, boolean constant) {
        this.name = name;
        this.linkage = linkage;
        this.value = null;
        this.type = type;
        this.constant = constant;
        this.section = null;
    }
    
    public Global(String name, Constant value) {
        this(name, null, value, false, null);
    }
    
    public Global(String name, Constant value, boolean constant) {
        this(name, null, value, constant, null);
    }
    
    public Global(String name, Linkage linkage, Constant value) {
        this(name, linkage, value, false, null);
    }
    
    public Global(String name, Linkage linkage, Constant value, boolean constant) {
        this(name, linkage, value, constant, null);        
    }
    
    public Global(String name, Constant value, boolean constant, String section) {
        this(name, null, value, constant, section);        
    }
    
    public Global(String name, Linkage linkage, Constant value, boolean constant, String section) {
        this.name = name;
        this.linkage = linkage;
        this.value = value;
        this.type = value.getType();
        this.constant = constant;
        this.section = section;
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
        sb.append('@');
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
        if (section != null) {
            sb.append(", section \"");
            sb.append(section);
            sb.append('"');
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "@" + name;
    }
}
