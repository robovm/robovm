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

/**
 *
 * @version $Id$
 */
public class ArrayType extends AggregateType {

    private final long size;
    private final Type elementType;

    public ArrayType(long size, Type elementType) {
        this.size = size;
        this.elementType = elementType;
    }
    
    public ArrayType(String alias, long size, Type elementType) {
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
        result = prime * result + (int) (size ^ (size >>> 32));
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
