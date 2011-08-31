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
public class PointerType extends UserType {

    private final Type base;

    public PointerType(Type base) {
        this.base = base;
    }
    
    public PointerType(String alias, Type base) {
        super(alias);
        this.base = base;
    }
    
    public Type getBase() {
        return base;
    }
    
    @Override
    public String getDefinition() {
        return base.toString() + "*";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((base == null) ? 0 : base.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PointerType other = (PointerType) obj;
        if (base == null) {
            if (other.base != null) {
                return false;
            }
        } else if (!base.equals(other.base)) {
            return false;
        }
        return true;
    }

}
