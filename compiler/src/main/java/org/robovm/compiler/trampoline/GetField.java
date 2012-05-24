/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class GetField extends FieldAccessor {
    private static final long serialVersionUID = 1L;
    
    private final String runtimeClass;

    public GetField(String callingClass, String targetClass, String fieldName, String fieldDesc, String runtimeClass) {
        super(callingClass, targetClass, fieldName, fieldDesc);
        this.runtimeClass = runtimeClass;
    }

    @Override
    public boolean isGetter() {
        return true;
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
    
    public String getRuntimeClass() {
        return runtimeClass;
    }
    
    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(getType(fieldDesc), ENV_PTR, OBJECT_PTR);
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
        GetField other = (GetField) obj;
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
            c = runtimeClass.compareTo(((GetField) o).runtimeClass);
        }
        return c;
    }
    
    @Override
    public String toString() {
        return super.toString() + "_" + mangleString(runtimeClass);
    }
}
