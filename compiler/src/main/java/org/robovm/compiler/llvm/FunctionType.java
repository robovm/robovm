/*
 * Copyright (C) 2012 RoboVM AB
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
public class FunctionType extends PointerType {
    private final Type returnType;
    private final Type[] parameterTypes;
    private final boolean varargs;
    
    public FunctionType(Type returnType, Type ... parameterTypes) {
        this(returnType, false, parameterTypes);
    }
    
    public FunctionType(Type returnType, boolean varargs, Type ... parameterTypes) {
        super(null);
        this.returnType = returnType;
        this.varargs = varargs;
        this.parameterTypes = parameterTypes.clone();
    }
    
    public FunctionType(String alias, Type returnType, Type ... parameterTypes) {
        this(alias, returnType, false, parameterTypes);
    }
    
    public FunctionType(String alias, Type returnType, boolean varargs, Type ... parameterTypes) {
        super(alias, null);
        this.returnType = returnType;
        this.varargs = varargs;
        this.parameterTypes = parameterTypes.clone();
    }

    @Override
    public Type getBase() {
        throw new UnsupportedOperationException("Function pointers cannot be dereferenced");
    }
    
    public Type getReturnType() {
        return returnType;
    }
    
    public boolean isVarargs() {
        return varargs;
    }

    public Type[] getParameterTypes() {
        return parameterTypes;
    }
    
    @Override
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append(returnType.toString());
        sb.append(" (");
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].toString());
        }
        if (varargs) {
            if (parameterTypes.length > 0) {
                sb.append(", ");
            }
            sb.append("...");            
        }
        sb.append(")*");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(parameterTypes);
        result = prime * result
                + ((returnType == null) ? 0 : returnType.hashCode());
        result = prime * result + (varargs ? 1231 : 1237);
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
        FunctionType other = (FunctionType) obj;
        if (!Arrays.equals(parameterTypes, other.parameterTypes)) {
            return false;
        }
        if (returnType == null) {
            if (other.returnType != null) {
                return false;
            }
        } else if (!returnType.equals(other.returnType)) {
            return false;
        }
        if (varargs != other.varargs) {
            return false;
        }
        return true;
    }
}
