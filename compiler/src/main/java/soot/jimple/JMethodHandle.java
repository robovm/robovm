/* Soot - a J*va Optimization Framework
 * Copyright (C) 2014 RoboVM AB
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package soot.jimple;

import java.util.ArrayList;
import java.util.List;

import soot.RefType;
import soot.SootMethodHandle;
import soot.SootMethodRef;
import soot.SootMethodType;
import soot.Type;
import soot.util.Switch;

/**
 * {@link SootMethodHandle} implementation.
 * RoboVM note: Added in RoboVM.
 */
class JMethodHandle extends Constant implements SootMethodHandle {
    private static final long serialVersionUID = 1L;

    private final int referenceKind;
    private final SootMethodRef methodRef;

    public JMethodHandle(int referenceKind, SootMethodRef methodRef) {
        this.referenceKind = referenceKind;
        this.methodRef = methodRef;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public SootMethodRef getMethodRef() {
        return methodRef;
    }

    public SootMethodType getMethodType() {
        switch (referenceKind) {
        case REF_invokeStatic:
        case REF_newInvokeSpecial:
        case REF_getStatic:
            return new JMethodType(methodRef.returnType(), methodRef.parameterTypes());
        default:
            List<Type> parameterTypes = new ArrayList<>();
            parameterTypes.add(methodRef.declaringClass().getType());
            parameterTypes.addAll(methodRef.parameterTypes());
            return new JMethodType(methodRef.returnType(), parameterTypes);
        }
    }

    @Override
    public Type getType() {
        return RefType.v("java.lang.invoke.MethodHandle");
    }

    @Override
    public void apply(Switch sw) {
        ((ConstantSwitch) sw).defaultCase(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (referenceKind) {
        case REF_getField:
            sb.append("GetField");
            break;
        case REF_getStatic:
            sb.append("GetStatic");
            break;
        case REF_putField:
            sb.append("PutField");
            break;
        case REF_putStatic:
            sb.append("PutStatic");
            break;
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
        sb.append('(').append(methodRef).append(')');
        return sb.toString();
    }
}
