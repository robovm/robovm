/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class Module {
    private final List<URL> includes = new ArrayList<URL>();
    private final List<Global> globals = new ArrayList<Global>();
    private final List<Function> functions = new ArrayList<Function>();
    private final List<FunctionDeclaration> functionDeclarations = new ArrayList<FunctionDeclaration>();
    private final List<UserType> types = new ArrayList<UserType>();
    private final List<String> asm = new ArrayList<String>();
    private int counter = 0;

    public void addInclude(URL resource) {
        includes.add(resource);
    }
    
    public Function newFunction(String name, FunctionType type, String ... parameterNames) {
        Function f = new Function(name, type, parameterNames);
        functions.add(f);
        return f;
    }
    
    public Function newFunction(Linkage linkage, String name, FunctionType type, String ... parameterNames) {
        Function f = new Function(linkage, name, type, parameterNames);
        functions.add(f);
        return f;
    }
    
    public Function newFunction(Linkage linkage, FunctionAttribute[] attributes, String name, FunctionType type, String ... parameterNames) {
        Function f = new Function(linkage, attributes, name, type, parameterNames);
        functions.add(f);
        return f;
    }
    
    public void addGlobal(Global global) {
        globals.add(global);
    }
    
    public Global newGlobal(Constant value) {
        return newGlobal(value, false);
    }
    
    public Global newGlobal(Constant value, boolean constant) {
        Global global = new Global("g" + (counter++), Linkage._private, value, constant);
        globals.add(global);
        return global;
    }
    
    public void addType(UserType type) {
        types.add(type);
    }
    
    public void addAsm(String s) {
        asm.add(s);
    }
    
    public void addFunctionDeclaration(FunctionDeclaration fd) {
        functionDeclarations.add(fd);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (URL g : includes) {
            InputStream in = null;
            try {
                in = g.openStream();
                sb.append(IOUtils.toString(in, "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(in);
            }
            sb.append("\n");
        }
        sb.append("\n");
        for (String s : asm) {
            sb.append("module asm \"");
            sb.append(s);
            sb.append("\"\n");
        }
        sb.append("\n");
        for (UserType type : types) {
            sb.append(type.getAlias());
            sb.append(" = type ");
            sb.append(type.getDefinition());
            sb.append("\n");
        }
        sb.append("\n");
        for (Global g : globals) {
            sb.append(g.getDefinition());
            sb.append("\n");
        }
        sb.append("\n");
        for (FunctionDeclaration fd : functionDeclarations) {
            sb.append(fd.toString());
            sb.append("\n");
        }
        sb.append("\n");
        for (Function f : functions) {
            sb.append(f.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
