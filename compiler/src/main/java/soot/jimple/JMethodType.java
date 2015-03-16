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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import soot.RefType;
import soot.SootMethodType;
import soot.Type;
import soot.util.Switch;

/**
 * {@link SootMethodType} implementation.
 * RoboVM note: Added in RoboVM.
 */
class JMethodType extends Constant implements SootMethodType {
    private static final long serialVersionUID = 1L;

    private final Type returnType;
    private final List<Type> parameterTypes;

    public JMethodType(Type returnType, List<Type> parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    public Type getReturnType() {
        return returnType;
    }
    
    public List<Type> getParameterTypes() {
        return Collections.unmodifiableList(parameterTypes);
    }

    @Override
    public Type getType() {
        return RefType.v("java.lang.invoke.MethodType");
    }

    @Override
    public void apply(Switch sw) {
        ((ConstantSwitch) sw).defaultCase(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('(');
        for (Iterator<Type> it = parameterTypes.iterator(); it.hasNext();) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        return sb.append(')').append(returnType).toString();
    }
}
