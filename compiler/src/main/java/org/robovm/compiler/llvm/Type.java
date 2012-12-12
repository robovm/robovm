/*
 * Copyright (C) 2012 Trillian AB
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

/**
 *
 * @version $Id$
 */
public abstract class Type {
    public static final IntegerType I1 = new IntegerType(1);
    public static final IntegerType I8 = new IntegerType(8);
    public static final IntegerType I16 = new IntegerType(16);
    public static final IntegerType I32 = new IntegerType(32);
    public static final IntegerType I64 = new IntegerType(64);
    public static final FloatingPointType FLOAT = new FloatingPointType("float");
    public static final FloatingPointType DOUBLE = new FloatingPointType("double");
    public static final PrimitiveType VOID = new PrimitiveType("void");
    public static final PointerType I8_PTR = new PointerType(I8);
    public static final PointerType I8_PTR_PTR = new PointerType(I8_PTR);
}
