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
public class Argument {
    private final Value value;
    private final ParameterAttribute[] attributes;

    public Argument(Value value, ParameterAttribute ... attributes) {
        this.value = value;
        this.attributes = attributes;
    }
    
    public Value getValue() {
        return value;
    }
    
    public Type getType() {
        return value.getType();
    }
    
    public ParameterAttribute[] getAttributes() {
        return attributes.clone();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Argument other = (Argument) obj;
        if (!Arrays.equals(attributes, other.attributes)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ParameterAttribute attribute : attributes) {
            sb.append(attribute);
            sb.append(' ');
        }
        sb.append(value);
        return sb.toString();
    }
}
