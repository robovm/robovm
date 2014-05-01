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
public class Icmp extends BinaryOpInstruction {
    
    public enum Condition {eq, ne, ugt, uge, ult, ule, sgt, sge, slt, sle}
    
    private final Condition cond;

    public Icmp(Variable result, Condition cond, Value op1, Value op2) {
        super(result, op1, op2);
        if (result.getType() != Type.I1) {
            throw new IllegalArgumentException("i1 type expected as result");
        }
        if (!op1.getType().equals(op2.getType())) {
            throw new IllegalArgumentException("Type mismatch");
        }
        if (!op1.isInteger() && !op1.isPointer()) {
            throw new IllegalArgumentException("Integer or pointer type expected");
        }
        this.cond = cond;
    }
    
    @Override
    public String toString() {
        return result + " = icmp " + cond + " " + op1.getType() + " " + op1 + ", " + op2;
    }
}
