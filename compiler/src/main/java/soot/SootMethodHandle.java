/* Soot - a J*va Optimization Framework
 * Copyright (C) 2014 Trillian Mobile AB
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
package soot;

/**
 * Representation of a MethodHandle as used in the {@code invokedynamic}
 * bytecode.
 * RoboVM note: Added in RoboVM.
 */
public interface SootMethodHandle extends Value {
    public static final int REF_getField         = 1;
    public static final int REF_getStatic        = 2;
    public static final int REF_putField         = 3;
    public static final int REF_putStatic        = 4;
    public static final int REF_invokeVirtual    = 5;
    public static final int REF_invokeStatic     = 6;
    public static final int REF_invokeSpecial    = 7;
    public static final int REF_newInvokeSpecial = 8;
    public static final int REF_invokeInterface  = 9;
    
    /**
     * Returns the reference kind. One of the {@code REF_*} constants in the
     * {@link SootMethodHandle} interface.
     */
    public int getReferenceKind();

    /**
     * Returns the {@link SootMethodRef} this handle references.
     */
    public SootMethodRef getMethodRef();
    
    /**
     * Returns the type of this {@link SootMethodHandle}. If the referenced 
     * method is non-static the parameter types of the returned 
     * {@link SootMethodType} will include the receiver type at index 0.
     */
    public SootMethodType getMethodType();
}
