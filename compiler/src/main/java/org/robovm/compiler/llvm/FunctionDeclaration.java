/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;


/**
 *
 * @version $Id$
 */
public class FunctionDeclaration {
    private final String name;
    private final FunctionType type;

    public FunctionDeclaration(String name, FunctionType type) {
        this.name = name;
        this.type = type;
    }

    public FunctionDeclaration(FunctionRef ref) {
        this(ref.getName(), ref.getType());
    }
    
    public String getName() {
        return name;
    }

    public FunctionType getType() {
        return type;
    }
    
    public FunctionRef ref() {
        return new FunctionRef(name, type);
    }
    
    @Override
    public String toString() {
        Type returnType = type.getReturnType();
        Type[] parameterTypes = type.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append("declare ");
        sb.append(returnType.toString());
        sb.append(" @");
        sb.append(name);
        sb.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            if (type.isVarargs() || i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].toString());
        }
        if (type.isVarargs()) {
            sb.append("...");
        }
        sb.append(")");
        return sb.toString();
    }
}
