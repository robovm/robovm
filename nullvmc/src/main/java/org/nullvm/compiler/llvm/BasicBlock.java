/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

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
    private final Object tag;
    private final List<Instruction> instructions = new ArrayList<Instruction>();

    BasicBlock(Function function, Object tag) {
        this.function = function;
        this.tag = tag;
    }
    
    public String getName() {
        return function.getLabel(this);
    }
    
    public Object getTag() {
        return tag;
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
