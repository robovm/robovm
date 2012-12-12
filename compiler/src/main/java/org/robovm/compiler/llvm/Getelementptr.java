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

import java.util.Collections;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class Getelementptr extends Instruction {
    private final Variable result;
    private final Value ptr;
    private final Value[] idx;

    public Getelementptr(Variable result, Value ptr, int ... idx) {
        if (!ptr.isPointer()) {
            throw new IllegalArgumentException("PointerType expected");
        }
        if (idx.length > 1 && !(((PointerType) ptr.getType()).getBase() instanceof AggregateType)) {
            throw new IllegalArgumentException("PointerType should point to AggregateType");
        }
        if (idx == null || idx.length == 0) {
            throw new IllegalArgumentException("No indexes");
        }
        this.result = result;
        this.ptr = ptr;
        this.idx = new Value[idx.length];
        for (int i = 0; i < idx.length; i++) {
            this.idx[i] = new IntegerConstant(idx[i]);
        }
    }

    public Getelementptr(Variable result, Value ptr, Value ... idx) {
        if (!ptr.isPointer()) {
            throw new IllegalArgumentException("PointerType expected");
        }
        if (idx.length > 1 && !(((PointerType) ptr.getType()).getBase() instanceof AggregateType)) {
            throw new IllegalArgumentException("PointerType should point to AggregateType");
        }
        if (idx == null || idx.length == 0) {
            throw new IllegalArgumentException("No indexes");
        }
        this.result = result;
        this.ptr = ptr;
        this.idx = idx;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        return Collections.singleton(result);
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (ptr instanceof VariableRef) {
            return Collections.singleton((VariableRef) ptr);
        }
        return super.getReadsFrom();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.append(" = getelementptr ");
        sb.append(ptr.getType());
        sb.append(' ');
        sb.append(ptr);
        for (int i = 0; i < idx.length; i++) {
            sb.append(", ");
            sb.append(idx[i].getType());
            sb.append(" ");
            sb.append(idx[i]);
        }
        return sb.toString();
    }
}
