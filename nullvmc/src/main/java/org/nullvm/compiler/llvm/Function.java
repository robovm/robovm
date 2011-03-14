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
    private final FunctionType type;
    private final Map<Object, BasicBlock> basicBlockMap = new HashMap<Object, BasicBlock>();
    private final List<BasicBlock> basicBlockList = new ArrayList<BasicBlock>();
    private final Map<String, Variable> variablesMap = new HashMap<String, Variable>();
    
    private int counter = 0;
    private final String[] parameterNames;

    public Function(String name, FunctionType type, String ... parameterNames) {
        this.name = "@" + name;
        this.type = type;
        this.parameterNames = parameterNames;
    }
    
    public String getName() {
        return name;
    }

    public FunctionType getType() {
        return type;
    }
    
    public String[] getParameterNames() {
        return parameterNames.clone();
    }
    
    String getLabel(BasicBlock bb) {
        return "label" + basicBlockList.indexOf(bb);
    }
    
    String getLabel(BasicBlockRef ref) {
        return getLabel(basicBlockMap.get(ref.getTag()));
    }
    
    public BasicBlock newBasicBlock(Object tag) {
        BasicBlock block = basicBlockMap.get(tag);
        if (block != null) {
            throw new IllegalArgumentException("BasicBlock with tag " + tag + " already exists");
        }
        block = new BasicBlock(this, tag);
        basicBlockMap.put(tag, block);
        basicBlockList.add(block);
        return block;
    }

    public BasicBlockRef newBasicBlockRef(Object tag) {
        return new BasicBlockRef(this, tag);
    }
    
    public BasicBlock getCurrentBasicBlock() {
        if (basicBlockList.isEmpty()) {
            return null;
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
        sb.append(") {\n");
        for (BasicBlock bb : basicBlockList) {
            sb.append(bb.toString());
        }
        sb.append("}\n");
        return sb.toString();
    }
}
