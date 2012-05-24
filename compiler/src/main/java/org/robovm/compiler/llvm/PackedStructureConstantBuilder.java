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
public class PackedStructureConstantBuilder {
    private final List<Value> values = new ArrayList<Value>();

    public PackedStructureConstantBuilder add(Value v) {
        values.add(v);
        return this;
    }
    
    public PackedStructureConstant build() {
        Type[] types = new Type[values.size()];
        int i = 0;
        for (Value v : values) {
            types[i++] = v.getType();
        }
        return new PackedStructureConstant(new PackedStructureType(types), 
                values.toArray(new Value[values.size()]));
    }
    
}
