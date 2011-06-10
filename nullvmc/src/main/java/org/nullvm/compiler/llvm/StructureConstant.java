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
public class StructureConstant extends Constant {
    private final StructureType type;
    private final Value[] values;

    public StructureConstant(StructureType type, Value ... values) {
        this.type = type;
        this.values = values;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(values[i].getType());
            sb.append(' ');
            sb.append(values[i]);
        }
        sb.append('}');
        return sb.toString();
    }
}
