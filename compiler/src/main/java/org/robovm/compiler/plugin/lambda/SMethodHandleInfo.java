/*
 * Copyright (C) 2014 RoboVM AB
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
package org.robovm.compiler.plugin.lambda;

/**
 * 
 */
public class SMethodHandleInfo {
    public static final int REF_invokeVirtual    = 5;
    public static final int REF_invokeStatic     = 6;
    public static final int REF_invokeSpecial    = 7;
    public static final int REF_newInvokeSpecial = 8;
    public static final int REF_invokeInterface  = 9;

    private final SClass<?> declaringClass;
    private final String name;
    private final SMethodType methodType;
    private final int referenceKind;

    public SMethodHandleInfo(SClass<?> declaringClass, String name, SMethodType methodType, int referenceKind) {
        this.declaringClass = declaringClass;
        this.name = name;
        this.methodType = methodType;
        this.referenceKind = referenceKind;
    }

    public SClass<?> getDeclaringClass() {
        return declaringClass;
    }
    public String getName() {
        return name;
    }
    public SMethodType getMethodType() {
        return methodType;
    }
    public int getReferenceKind() {
        return referenceKind;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (referenceKind) {
        case REF_invokeVirtual:
            sb.append("InvokeVirtual");
            break;
        case REF_invokeStatic:
            sb.append("InvokeStatic");
            break;
        case REF_invokeSpecial:
            sb.append("InvokeSpecial");
            break;
        case REF_newInvokeSpecial:
            sb.append("NewInvokeSpecial");
            break;
        case REF_invokeInterface:
            sb.append("InvokeInterface");
            break;
        }
        return sb.append('(')
            .append(declaringClass)
            .append('.')
            .append(name)
            .append(methodType)
            .append(')').toString();
    }
}
