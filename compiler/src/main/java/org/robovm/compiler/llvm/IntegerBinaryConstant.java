/*
 * Copyright (C) 2012 Trillian Mobile AB
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
public abstract class IntegerBinaryConstant extends BinaryOpConstant {
    private final String name;

    protected IntegerBinaryConstant(String name, Constant op1, Constant op2) {
        super(op1, op2);
        if (!op1.isInteger() || !op2.isInteger()) {
            throw new IllegalArgumentException("Integer type expected");
        }
        if (!op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch ");
        }
        this.name = name;
    }
    
    @Override
    public Type getType() {
        return op1.getType();
    }
    
    @Override
    public String toString() {
        return name + " (" + op1.getType() + " " + op1 + ", " + op2.getType() + " " + op2 + ")";
    }
}
