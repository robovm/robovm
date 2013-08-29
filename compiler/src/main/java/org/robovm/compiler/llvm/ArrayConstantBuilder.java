/*
 * Copyright (C) 2012 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
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

    public ArrayConstantBuilder add(List<? extends Value> vs) {
        for (Value v : vs) {
            add(v);
        }
        return this;
    }

    public ArrayConstantBuilder add(int ... values) {
        for (int v : values) {
            add(new IntegerConstant(v));
        }
        return this;
    }

    public ArrayConstant build() {
        return new ArrayConstant(new ArrayType(values.size(), type), 
                values.toArray(new Value[values.size()]));
    }
    
}
