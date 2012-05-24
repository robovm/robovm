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
package org.robovm.compiler.llvm;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public abstract class BinaryOpInstruction extends Instruction {
    protected final Variable result;
    protected final Value op1;
    protected final Value op2;

    protected BinaryOpInstruction(Variable result, Value op1, Value op2) {
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> refs = new HashSet<VariableRef>();
        if (op1 instanceof VariableRef) {
            refs.add((VariableRef) op1);
        }
        if (op2 instanceof VariableRef) {
            refs.add((VariableRef) op2);
        }
        return refs;
    }
}
