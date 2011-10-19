/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

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
    private final String section;
    private final FunctionType type;
    private final Map<Label, BasicBlock> basicBlockMap = new HashMap<Label, BasicBlock>();
    private final List<BasicBlock> basicBlockList = new ArrayList<BasicBlock>();
    private final Map<String, Variable> variablesMap = new HashMap<String, Variable>();
    
    private int counter = 0;
    private final String[] parameterNames;

    public Function(String name, FunctionType type, String ... parameterNames) {
        this(null, name, type, parameterNames);
    }
    
    public Function(Linkage linkage, String name, FunctionType type, String ... parameterNames) {
        this(linkage, null, null, name, type, parameterNames);
    }
    
    public Function(Linkage linkage, String section, String name, FunctionType type, String ... parameterNames) {
        this(linkage, null, section, name, type, parameterNames);
    }
    
    public Function(Linkage linkage, FunctionAttribute[] attributes, String name, FunctionType type, String ... parameterNames) {
        this(linkage, attributes, null, name, type, parameterNames);
    }
    
    public Function(Linkage linkage, FunctionAttribute[] attributes, String section, String name, FunctionType type, String ... parameterNames) {
        this.linkage = linkage;
        this.attributes = attributes;
        this.section = section;
        this.name = "@" + name;
        this.type = type;
        this.parameterNames = parameterNames;
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
    
    public String[] getParameterNames() {
        return parameterNames.clone();
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
        sb.append(' ');
        sb.append(name);
        sb.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            if (type.isVarargs() || i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].toString());
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
