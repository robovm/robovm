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
public class Variable {
    private final String name;
    private final Type type;

    public Variable(VariableRef ref) {
        this(ref.getName(), ref.getType());
    }
    
    public Variable(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    
    public VariableRef ref() {
        return new VariableRef(this);
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean isInteger() {
        return getType() instanceof IntegerType;
    }
    
    public boolean isFloatingPoint() {
        return getType() instanceof FloatingPointType;
    }
    
    public boolean isPointer() {
        return getType() instanceof PointerType;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Variable other = (Variable) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "%" + name;
    }
}
