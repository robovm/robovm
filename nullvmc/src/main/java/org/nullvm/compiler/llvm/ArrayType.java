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
public class ArrayType extends AggregateType {

    private final int size;
    private final Type elementType;

    public ArrayType(int size, Type elementType) {
        this.size = size;
        this.elementType = elementType;
    }
    
    public ArrayType(String alias, int size, Type elementType) {
        super(alias);
        this.size = size;
        this.elementType = elementType;
    }

    @Override
    public Type getTypeAt(int index) {
        return elementType;
    }
    
    @Override
    public String getDefinition() {
        return "[" + size + " x " + elementType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((elementType == null) ? 0 : elementType.hashCode());
        result = prime * result + size;
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
        ArrayType other = (ArrayType) obj;
        if (elementType == null) {
            if (other.elementType != null) {
                return false;
            }
        } else if (!elementType.equals(other.elementType)) {
            return false;
        }
        if (size != other.size) {
            return false;
        }
        return true;
    }

}
