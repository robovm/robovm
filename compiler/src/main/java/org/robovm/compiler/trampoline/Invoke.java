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
package org.robovm.compiler.trampoline;

import org.robovm.compiler.Symbols;
import org.robovm.compiler.Types;
import org.robovm.compiler.llvm.FunctionType;



/**
 *
 * @version $Id$
 */
public abstract class Invoke extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    private final String methodName;
    private final String methodDesc;

    protected Invoke(String callingClass, String targetClass, String methodName, String methodDesc) {
        super(callingClass, targetClass);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }
    
    public abstract boolean isStatic();
    
    @Override
    public FunctionType getFunctionType() {
        return Types.getFunctionType(methodDesc, isStatic());
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
        Invoke other = (Invoke) obj;
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
            c = methodName.compareTo(((Invoke) o).methodName);
            if (c == 0) {
                c = methodDesc.compareTo(((Invoke) o).methodDesc);
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return Symbols.trampolineMethodSymbol(this, getCallingClass(), getTarget(), getMethodName(), getMethodDesc());
    }
}
