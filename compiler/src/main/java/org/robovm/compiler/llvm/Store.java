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

import java.util.HashSet;
import java.util.Set;



/**
 *
 * @version $Id$
 */
public class Store extends Instruction {
    private final Value value;
    private final Value pointer;
    private final boolean _volatile;
    private final Ordering ordering;
    private final int alignment;

    public Store(Value value, Value pointer) {
        this(value, pointer, false, null, -1);
    }

    public Store(Value value, Value pointer, boolean _volatile) {
        this(value, pointer, _volatile, null, -1);
    }
    
    public Store(Value value, Value pointer, boolean _volatile, Ordering ordering, int alignment) {
        this.value = value;
        this.pointer = pointer;
        this._volatile = _volatile;
        this.ordering = ordering;
        this.alignment = alignment;
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> result = new HashSet<VariableRef>();
        if (value instanceof VariableRef) {
            result.add((VariableRef) value);
        }
        if (pointer instanceof VariableRef) {
            result.add((VariableRef) pointer);
        }
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("store ");
        if (_volatile) {
            sb.append("volatile ");
        }
        if (ordering != null) {
            sb.append("atomic ");
        }
        sb.append(value.getType());
        sb.append(" ");
        sb.append(value);
        sb.append(", ");
        sb.append(pointer.getType());
        sb.append(" ");
        sb.append(pointer);
        if (ordering != null) {
            sb.append(" ");
            sb.append(ordering);
        }
        if (alignment > 0) {
            sb.append(", align ");
            sb.append(alignment);            
        }
        return sb.toString();
    }
}
