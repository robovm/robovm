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
public abstract class UserType extends Type {
    protected final String alias;
    
    UserType() {
        alias = null;
    }
    
    UserType(String alias) {
        this.alias = "%" + alias;
    }
    
    public String getAlias() {
        return alias;
    }
    
    public boolean hasAlias() {
        return alias != null;
    }
    
    public abstract String getDefinition();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alias == null) ? 0 : alias.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserType other = (UserType) obj;
        if (alias == null) {
            if (other.alias != null) {
                return false;
            }
        } else if (!alias.equals(other.alias)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return alias != null ? alias : getDefinition();
    }
}
