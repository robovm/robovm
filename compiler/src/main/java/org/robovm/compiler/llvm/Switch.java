/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class Switch extends Instruction {
    private final Value value;
    private final BasicBlockRef def;
    private final Map<IntegerConstant, BasicBlockRef> alt;

    public Switch(Value value, BasicBlockRef def, Map<IntegerConstant, BasicBlockRef> alt) {
        if (!value.isInteger()) {
            throw new IllegalArgumentException("Integer type expected");
        }
        this.value = value;
        this.def = def;
        this.alt = alt;
    }
    
    @Override
    public Set<VariableRef> getReadsFrom() {
        if (value instanceof VariableRef) {
            return Collections.singleton((VariableRef) value);
        }
        return super.getReadsFrom();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("switch ");
        sb.append(value.getType());
        sb.append(' ');
        sb.append(value);
        sb.append(", label %");
        sb.append(def.getName());
        sb.append(" [ ");
        for (Entry<IntegerConstant, BasicBlockRef> pair : alt.entrySet()) {
            sb.append(pair.getKey().getType());
            sb.append(' ');
            sb.append(pair.getKey());
            sb.append(", label %");
            sb.append(pair.getValue().getName());
            sb.append(' ');
        }
        sb.append("]");
        return sb.toString();
    }
}
