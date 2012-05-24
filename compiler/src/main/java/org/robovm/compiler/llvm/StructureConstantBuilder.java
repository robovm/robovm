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
public class StructureConstantBuilder {
    private final List<Value> values = new ArrayList<Value>();

    public StructureConstantBuilder add(Value v) {
        values.add(v);
        return this;
    }
    
    public StructureConstant build() {
        Type[] types = new Type[values.size()];
        int i = 0;
        for (Value v : values) {
            types[i++] = v.getType();
        }
        return new StructureConstant(new StructureType(types), 
                values.toArray(new Value[values.size()]));
    }
    
}
