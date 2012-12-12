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
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Mangler.*;


/**
 *
 * @version $Id$
 */
public abstract class FieldAccessor extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    protected final String fieldName;
    protected final String fieldDesc;

    protected FieldAccessor(String callingClass, String targetClass, String fieldName, String fieldDesc) {
        super(callingClass, targetClass);
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }
    
    public abstract boolean isGetter();
    
    public abstract boolean isStatic();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((fieldDesc == null) ? 0 : fieldDesc.hashCode());
        result = prime * result
                + ((fieldName == null) ? 0 : fieldName.hashCode());
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
        FieldAccessor other = (FieldAccessor) obj;
        if (fieldDesc == null) {
            if (other.fieldDesc != null) {
                return false;
            }
        } else if (!fieldDesc.equals(other.fieldDesc)) {
            return false;
        }
        if (fieldName == null) {
            if (other.fieldName != null) {
                return false;
            }
        } else if (!fieldName.equals(other.fieldName)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Trampoline o) {
        int c = super.compareTo(o);
        if (c == 0 && o instanceof FieldAccessor) {
            c = fieldName.compareTo(((FieldAccessor) o).fieldName);
            if (c == 0) {
                c = fieldDesc.compareTo(((FieldAccessor) o).fieldDesc);
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return super.toString() + "_" + mangleString(fieldName) + "_" + mangleString(fieldDesc);
    }
}
