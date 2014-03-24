/*
 * Copyright (C) 2012 Trillian Mobile AB
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
