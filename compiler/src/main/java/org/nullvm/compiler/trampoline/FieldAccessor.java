/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;

import static org.nullvm.compiler.Mangler.*;


/**
 *
 * @version $Id$
 */
public abstract class FieldAccessor extends Trampoline {
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
