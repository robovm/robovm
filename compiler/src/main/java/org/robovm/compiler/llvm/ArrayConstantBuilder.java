/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @version $Id$
 */
public class ArrayConstantBuilder {
    private final Type type;
    private final List<Value> values = new ArrayList<Value>();

    public ArrayConstantBuilder(Type type) {
        this.type = type;
    }
    
    public ArrayConstantBuilder add(Value v) {
        if (!v.getType().equals(type)) {
            throw new IllegalArgumentException("Wrong type. Was " + v.getType() 
                    + ". Expected " + type);
        }
        values.add(v);
        return this;
    }
    
    public ArrayConstant build() {
        return new ArrayConstant(new ArrayType(values.size(), type), 
                values.toArray(new Value[values.size()]));
    }
    
}
