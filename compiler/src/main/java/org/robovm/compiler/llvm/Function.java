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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @version $Id$
 */
public class Function {
    private final String name;
    private final Linkage linkage;
    private final FunctionAttribute[] attributes;
    private final ParameterAttribute[][] parameterAttributes;
    private final String section;
    private final FunctionType type;
    private final Map<Label, BasicBlock> basicBlockMap = new HashMap<Label, BasicBlock>();
    private final List<BasicBlock> basicBlockList = new ArrayList<BasicBlock>();
    private final Map<String, Variable> variablesMap = new HashMap<String, Variable>();
    
    private int counter = 0;
    private final String[] parameterNames;

    public Function(Linkage linkage, FunctionAttribute[] attributes, String section, String name, FunctionType type, String ... parameterNames) {
        this.linkage = linkage;
        this.attributes = attributes;
        this.section = section;
        this.name = name;
        this.type = type;
        if (parameterNames == null || parameterNames.length == 0 && type.getParameterTypes().length > 0) {
            parameterNames = new String[type.getParameterTypes().length];
            for (int i = 0; i < parameterNames.length; i++) {
                parameterNames[i] = "p" + i;
            }
        }
        this.parameterNames = parameterNames;
        this.parameterAttributes = new ParameterAttribute[type.getParameterTypes().length][];
    }

    public FunctionRef ref() {
        return new FunctionRef(this);
    }
    
    public String getName() {
        return name;
    }

    public FunctionType getType() {
        return type;
    }
    
    public VariableRef getParameterRef(int index) {
        return new VariableRef(parameterNames[index], type.getParameterTypes()[index]);
    }
    
    public VariableRef[] getParameterRefs() {
        VariableRef[] result = new VariableRef[parameterNames.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getParameterRef(i);
        }
        return result;
    }
    
    public String[] getParameterNames() {
        return parameterNames.clone();
    }
    
    public void setParameterAttributes(int paramIndex, ParameterAttribute ... attributes) {
        parameterAttributes[paramIndex] = attributes.clone();
    }
    
    String getLabel(BasicBlock bb) {
        return "label" + basicBlockList.indexOf(bb);
    }
    
    String getLabel(BasicBlockRef ref) {
        return getLabel(basicBlockMap.get(ref.getLabel()));
    }
    
    public BasicBlock newBasicBlock(Label label) {
        BasicBlock block = basicBlockMap.get(label);
        if (block != null) {
            throw new IllegalArgumentException("BasicBlock with label " + label + " already exists");
        }
        block = new BasicBlock(this, label);
        basicBlockMap.put(label, block);
        basicBlockList.add(block);
        return block;
    }

    public BasicBlockRef newBasicBlockRef(Label label) {
        return new BasicBlockRef(this, label);
    }
    
    public BasicBlock getCurrentBasicBlock() {
        if (basicBlockList.isEmpty()) {
            return newBasicBlock(new Label());
        }
        return basicBlockList.get(basicBlockList.size() - 1);
    }

    public Variable newVariable(Type type) {
        return newVariable("t" + (counter++), type);
    }
    
    public Variable newVariable(String name, Type type) {
        if (variablesMap.containsKey(name)) {
            throw new IllegalArgumentException("Variable with name '" + name + "' already exists");
        }
        Variable v = new Variable(name, type);
        variablesMap.put(name, v);
        return v;
    }
    
    public BasicBlock getDefinedIn(VariableRef ref) {
        Variable var = new Variable(ref);
        for (BasicBlock bb : basicBlockList) {
            if (bb.getWritesTo().contains(var)) {
                return bb;
            }
        }
        throw new IllegalStateException("Variable " + var + " not defined");
    }
    
    public void add(Instruction instruction) {
        getCurrentBasicBlock().add(instruction);
    }
    
    @Override
    public String toString() {
        Type returnType = type.getReturnType();
        Type[] parameterTypes = type.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append("define ");
        if (linkage != null) {
            sb.append(linkage);
            sb.append(' ');
        }
        sb.append(returnType.toString());
        sb.append(" @");
        sb.append(name);
        sb.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            if (type.isVarargs() || i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].toString());
            if (parameterAttributes[i] != null) {
                for (ParameterAttribute attrib : parameterAttributes[i]) {
                    sb.append(' ');
                    sb.append(attrib);
                }
            }
            sb.append(" %");
            sb.append(parameterNames[i]);
        }
        if (type.isVarargs()) {
            sb.append("...");
        }
        sb.append(")");
        if (attributes != null && attributes.length > 0) {
            for (FunctionAttribute attr : attributes) {
                sb.append(' ');
                sb.append(attr.toString());
            }
        }
        if (section != null) {
            sb.append(" section \"");
            sb.append(section);
            sb.append('"');
        }
        sb.append(" {\n");
        for (BasicBlock bb : basicBlockList) {
            sb.append(bb.toString());
        }
        sb.append("}\n");
        return sb.toString();
    }
}
