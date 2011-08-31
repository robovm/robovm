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
public class IntegerConstant extends Constant implements Comparable<IntegerConstant>{
    private final IntegerType type;
    private final Object value;

    private IntegerConstant(Object value, IntegerType type) {
        this.value = value;
        this.type = type;
    }
    
    public IntegerConstant(long value, IntegerType type) {
        this(new Long(value), type);
    }
    
    public IntegerConstant(byte value) {
        this(new Byte(value), Type.I8);
    }
    
    public IntegerConstant(short value) {
        this(new Short(value), Type.I16);
    }
    
    public IntegerConstant(char value) {
        this(new Integer(value), Type.I16);
    }
    
    public IntegerConstant(int value) {
        this(new Integer(value), Type.I32);
    }
    
    public IntegerConstant(long value) {
        this(new Long(value), Type.I64);
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        IntegerConstant other = (IntegerConstant) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
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

    @SuppressWarnings("unchecked")
    public int compareTo(IntegerConstant o) {
        return ((Comparable<Object>) value).compareTo(o.value);
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
