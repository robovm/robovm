/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Mangler.*;

import java.io.Serializable;

import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public abstract class Trampoline implements Comparable<Trampoline>, Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final String callingClass;
    protected final String target;

    protected Trampoline(String callingClass, String target) {
        this.callingClass = callingClass;
        this.target = target;
    }

    public String getCallingClass() {
        return callingClass;
    }
    
    public String getTarget() {
        return target;
    }
    
    public FunctionRef getFunctionRef() {
        return new FunctionRef(getFunctionName(), getFunctionType());
    }
    
    public String getFunctionName() {
        return toString();
    }
    
    public abstract FunctionType getFunctionType();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((callingClass == null) ? 0 : callingClass.hashCode());
        result = prime * result
                + ((target == null) ? 0 : target.hashCode());
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
        Trampoline other = (Trampoline) obj;
        if (callingClass == null) {
            if (other.callingClass != null) {
                return false;
            }
        } else if (!callingClass.equals(other.callingClass)) {
            return false;
        }
        if (target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!target.equals(other.target)) {
            return false;
        }
        return true;
    }

    public int compareTo(Trampoline o) {
        int c = this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
        if (c == 0) {
            c = callingClass.compareTo(o.callingClass);
            if (c == 0) {
                c = target.compareTo(o.target);
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + mangleString(callingClass) 
                + "_" + mangleString(target);
    }
}
