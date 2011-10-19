/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.util.Arrays;

/**
 *
 * @version $Id$
 */
public class Argument {
    private final Value value;
    private final ParameterAttribute[] attributes;

    public Argument(Value value, ParameterAttribute ... attributes) {
        this.value = value;
        this.attributes = attributes;
    }
    
    public Value getValue() {
        return value;
    }
    
    public Type getType() {
        return value.getType();
    }
    
    public ParameterAttribute[] getAttributes() {
        return attributes.clone();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Argument other = (Argument) obj;
        if (!Arrays.equals(attributes, other.attributes)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        for (ParameterAttribute attribute : attributes) {
            sb.append(' ');
            sb.append(attribute);
        }
        return sb.toString();
    }
}
