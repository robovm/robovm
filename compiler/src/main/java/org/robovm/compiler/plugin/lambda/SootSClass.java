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

import java.util.ArrayList;
import java.util.List;

import org.robovm.compiler.Types;

import soot.ArrayType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;

/**
 * 
 */
public class SootSClass extends SClass {
    final soot.Type type;

    public SootSClass(soot.Type type) {
        this.type = type;
    }

    @Override
    public String getDescriptor() {
        return Types.getDescriptor(type);
    }
    
    @Override
    public boolean isPrimitive() {
        return type instanceof PrimType;
    }

    @Override
    public boolean isInterface() {
        if (type instanceof RefType) {
            return ((RefType) type).getSootClass().isInterface();
        }
        return false;
    }

    @Override
    public boolean isArray() {
        return type instanceof ArrayType;
    }

    @Override
    public SClass getComponentType() {
        if (type instanceof ArrayType) {
            return forType(((ArrayType) type).getElementType());
        }
        return null;
    }

    @Override
    public SClass getSuperclass() {
        if (isPrimitive() || isInterface()) {
            return null;
        }
        if (isArray()) {
            return SClass.ObjectClass;
        }
        RefType refType = (RefType) type;
        SootClass sc = refType.getSootClass();
        if (sc.hasSuperclass()) {
            return forType(sc.getSuperclass().getType());
        }
        return null;
    }

    @Override
    public SClass[] getInterfaces() {
        if (isPrimitive()) {
            return null;
        }
        if (isArray()) {
            return new SClass[] {SClass.CloneableClass, SClass.SerializableClass};
        }
        RefType refType = (RefType) type;
        SootClass sc = refType.getSootClass();
        List<SClass<?>> result = new ArrayList<>();
        for (SootClass ifs : sc.getInterfaces()) {
            result.add(forType(ifs.getType()));
        }
        return result.toArray(new SClass[result.size()]);
    }
    
    public static SClass<?> forType(soot.Type type) {
        String descriptor = Types.getDescriptor(type);
        return SClass.lookup(descriptor);
    }

    public static List<SClass<?>> forTypes(soot.Type[] types) {
        List<SClass<?>> result = new ArrayList<>();
        for (soot.Type type : types) {
            result.add(forType(type));
        }
        return result;
    }

    public static List<SClass<?>> forTypes(List<soot.Type> types) {
        List<SClass<?>> result = new ArrayList<>();
        for (soot.Type type : types) {
            result.add(forType(type));
        }
        return result;
    }
}
