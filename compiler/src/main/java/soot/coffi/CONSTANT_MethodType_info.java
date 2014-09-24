/* Soot - a J*va Optimization Framework
 * Copyright (C) 2012 Eric Bodden
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
package soot.coffi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import soot.Value;
import soot.jimple.Jimple;

/**
 * A constant pool entry of type CONSTANT_MethodType.
 *
 * RoboVM note: Added in RoboVM.
 */
class CONSTANT_MethodType_info extends cp_info {

    public int descriptor_index;

    public int size() {
        return 3;
    }

    /**
     * Returns a String representation of this entry.
     * 
     * @param constant_pool
     *            constant pool of ClassFile.
     * @return String representation of this entry.
     * @see cp_info#toString
     */
    public String toString(cp_info constant_pool[]) {
        cp_info target = constant_pool[descriptor_index];
        return target.toString(constant_pool);
    }

    /**
     * Returns a String description of what kind of entry this is.
     * 
     * @return the String "methodtype".
     * @see cp_info#typeName()
     */
    public String typeName() {
        return "methodtype";
    }

    /**
     * Compares this entry with another cp_info object (which may reside in a
     * different constant pool).
     * 
     * @param constant_pool
     *            constant pool of ClassFile for this.
     * @param cp
     *            constant pool entry to compare against.
     * @param cp_constant_pool
     *            constant pool of ClassFile for cp.
     * @return a value <0, 0, or >0 indicating whether this is smaller, the same
     *         or larger than cp.
     * @see cp_info#compareTo
     */
    public int compareTo(cp_info constant_pool[], cp_info cp, cp_info cp_constant_pool[]) {
        int i;
        if (tag != cp.tag)
            return tag - cp.tag;
        CONSTANT_MethodType_info cu = (CONSTANT_MethodType_info) cp;
        i = constant_pool[descriptor_index].
                compareTo(constant_pool, cp_constant_pool[cu.descriptor_index], cp_constant_pool);
        return i;
    }

    public Value createJimpleConstantValue(cp_info[] constant_pool) {
        String desc = ((CONSTANT_Utf8_info) constant_pool[descriptor_index]).convert();
        soot.Type[] types = Util.v().jimpleTypesOfFieldOrMethodDescriptor(desc);
        soot.Type returnType = types[types.length - 1];
        List<soot.Type> parameterTypes = new ArrayList<soot.Type>(Arrays.asList(types).subList(0, types.length - 1));
        return Jimple.v().newMethodType(returnType, parameterTypes);
    }
}
