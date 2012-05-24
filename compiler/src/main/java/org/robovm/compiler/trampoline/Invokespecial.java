/*
 * Copyright (C) 2012 RoboVM
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
