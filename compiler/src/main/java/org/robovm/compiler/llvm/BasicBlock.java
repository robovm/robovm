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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class BasicBlock {
    private final Function function;
    private final Label label;
    private final List<Instruction> instructions = new ArrayList<Instruction>();

    BasicBlock(Function function, Label label) {
        this.function = function;
        this.label = label;
    }
    
    public String getName() {
        return function.getLabel(this);
    }
    
    public Label getLabel() {
        return label;
    }
    
    public Function getFunction() {
        return function;
    }
    
    public Set<Variable> getWritesTo() {
        Set<Variable> result = new HashSet<Variable>();
        for (Instruction i : instructions) {
            result.addAll(i.getWritesTo());
        }
        return result;
    }
    
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> result = new HashSet<VariableRef>();
        for (Instruction i : instructions) {
            result.addAll(i.getReadsFrom());
        }
        return result;
    }
    
    public void add(Instruction instruction) {
        instructions.add(instruction);
        instruction.basicBlock = this;
    }
    
    public Instruction last() {
        if (instructions.isEmpty()) {
            return null;
        }
        return instructions.get(instructions.size() - 1);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(":\n");
        for (Instruction instruction : instructions) {
            sb.append("    ");
            sb.append(instruction.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
