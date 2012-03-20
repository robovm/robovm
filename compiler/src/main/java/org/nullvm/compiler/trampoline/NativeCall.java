/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;

import static org.nullvm.compiler.Mangler.*;

import org.nullvm.compiler.Types;
import org.nullvm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class NativeCall extends Trampoline {
    private final String methodName;
    private final String methodDesc;
    private final boolean ztatic;

    public NativeCall(String callingClass, String targetClass, String methodName, String methodDesc, boolean ztatic) {
        super(callingClass, targetClass);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.ztatic = ztatic;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public boolean isStatic() {
        return ztatic;
    }
    
    @Override
    public FunctionType getFunctionType() {
        return Types.getNativeFunctionType(methodDesc, ztatic);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((methodDesc == null) ? 0 : methodDesc.hashCode());
        result = prime * result
                + ((methodName == null) ? 0 : methodName.hashCode());
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
        NativeCall other = (NativeCall) obj;
        if (methodDesc == null) {
            if (other.methodDesc != null) {
                return false;
            }
        } else if (!methodDesc.equals(other.methodDesc)) {
            return false;
        }
        if (methodName == null) {
            if (other.methodName != null) {
                return false;
            }
        } else if (!methodName.equals(other.methodName)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Trampoline o) {
        int c = super.compareTo(o);
        if (c == 0) {
            c = methodName.compareTo(((NativeCall) o).methodName);
            if (c == 0) {
                c = methodDesc.compareTo(((NativeCall) o).methodDesc);
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return super.toString() + "_" + mangleString(methodName) + "_" + mangleString(methodDesc);
    }
}
