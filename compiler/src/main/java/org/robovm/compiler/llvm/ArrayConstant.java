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
public class ArrayConstant extends Constant {
    private final ArrayType type;
    private final Value[] values;

    public ArrayConstant(ArrayType type, Value ... values) {
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
        sb.append('[');
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(values[i].getType());
            sb.append(' ');
            sb.append(values[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}
