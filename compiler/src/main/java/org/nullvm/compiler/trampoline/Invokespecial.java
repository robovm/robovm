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
public class Invokespecial extends Invoke {
    private static final long serialVersionUID = 1L;
    
    private final String runtimeClass;

    public Invokespecial(String callingClass, String targetClass, String methodName, String methodDesc, String runtimeClass) {
        super(callingClass, targetClass, methodName, methodDesc);
        this.runtimeClass = runtimeClass;
    }

    public String getRuntimeClass() {
        return runtimeClass;
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((runtimeClass == null) ? 0 : runtimeClass.hashCode());
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
        Invokespecial other = (Invokespecial) obj;
        if (runtimeClass == null) {
            if (other.runtimeClass != null) {
                return false;
            }
        } else if (!runtimeClass.equals(other.runtimeClass)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Trampoline o) {
        int c = super.compareTo(o);
        if (c == 0) {
            c = runtimeClass.compareTo(((Invokespecial) o).runtimeClass);
        }
        return c;
    }
    
    @Override
    public String toString() {
        return super.toString() + "_" + mangleString(runtimeClass);
    }
}
