/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class FloatingPointConstant extends Constant {
    private final FloatingPointType type;
    private final Object value;

    public FloatingPointConstant(float value) {
        this(new Float(value), Type.FLOAT);
    }
    
    public FloatingPointConstant(double value) {
        this(new Double(value), Type.DOUBLE);
    }
    
    public FloatingPointConstant(double value, FloatingPointType type) {
        this.value = new Double(value);
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        FloatingPointConstant other = (FloatingPointConstant) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
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
        if (type == Type.FLOAT) {
            float f = ((Number) value).floatValue();
            return "bitcast (i32 " + Float.floatToIntBits(f) + " to float)";
        } else {
            double d = ((Number) value).doubleValue();
            return "bitcast (i64 " + Double.doubleToLongBits(d) + " to double)";
        }
    }
}
