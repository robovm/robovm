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
public class Alias {
    private final String name;
    private final Linkage linkage;
    private final Constant value;

    public Alias(String name, FunctionRef aliasee) {
        this(name, null, aliasee);
    }
    
    public Alias(String name, Linkage linkage, FunctionRef aliasee) {
        this.name = name;
        this.linkage = linkage;
        this.value = aliasee;
    }
    
    public AliasRef ref() {
        return new AliasRef(this);
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return value.getType();
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(name);
        sb.append(" = alias ");
        if (linkage != null) {
            sb.append(linkage);
            sb.append(' ');
        }
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
