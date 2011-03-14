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
public class StructureType extends AggregateType {
    private final Type[] types;
    
    public StructureType(Type ... types) {
        this.types = types.clone();
    }
    
    public StructureType(String alias, Type ... types) {
        super(alias);
        this.types = types.clone();
    }

    @Override
    public Type getTypeAt(int index) {
        return types[index];
    }
    
    @Override
    public String getDefinition() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(types[i].toString());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(types);
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
        StructureType other = (StructureType) obj;
        if (!Arrays.equals(types, other.types)) {
            return false;
        }
        return true;
    }
}
