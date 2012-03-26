/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Strings.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nullvm.compiler.llvm.Alias;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantGetelementptr;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionDeclaration;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.GlobalRef;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.Module;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.StringConstant;
import org.nullvm.compiler.llvm.UserType;

/**
 *
 * @version $Id$
 */
public class ModuleBuilder {
    private final List<URL> includes = new ArrayList<URL>();
    private final List<Global> globals = new ArrayList<Global>();
    private final List<Alias> aliases = new ArrayList<Alias>();    
    private final List<Function> functions = new ArrayList<Function>();
    private final List<FunctionDeclaration> functionDeclarations = new ArrayList<FunctionDeclaration>();
    private final List<UserType> types = new ArrayList<UserType>();
    private final List<String> asm = new ArrayList<String>();
    private final Set<String> symbols = new HashSet<String>();
    private int counter = 0;
    private Map<String, Global> strings = new HashMap<String, Global>();;

    public void addInclude(URL resource) {
        includes.add(resource);
    }
    
    public void addFunction(Function f) {
        if (symbols.contains(f.getName())) {
            throw new IllegalArgumentException("Symbol " + f.getName() + " already defined");
        }
        functions.add(f);
        symbols.add(f.getName());
    }
    
    public boolean hasSymbol(String name) {
        return symbols.contains(name);
    }
    
    public void addGlobal(Global global) {
        if (symbols.contains(global.getName())) {
            throw new IllegalArgumentException("Symbol " + global.getName() + " already defined");
        }
        globals.add(global);
        symbols.add(global.getName());
    }
    
    public Global newGlobal(Constant value) {
        return newGlobal(value, false);
    }
    
    public Global newGlobal(Constant value, boolean constant) {
        Global global = new Global("g" + (counter++), Linkage._private, value, constant);
        addGlobal(global);
        return global;
    }
    
    public void addAlias(Alias alias) {
        if (symbols.contains(alias.getName())) {
            throw new IllegalArgumentException("Symbol " + alias.getName() + " already defined");
        }
        aliases.add(alias);
        symbols.add(alias.getName());
    }
    
    public void addType(UserType type) {
        types.add(type);
    }
    
    public void addAsm(String s) {
        asm.add(s);
    }
    
    public void addFunctionDeclaration(FunctionDeclaration fd) {
        if (symbols.contains(fd.getName())) {
            throw new IllegalArgumentException("Symbol " + fd.getName() + " already defined");
        }
        functionDeclarations.add(fd);
        symbols.add(fd.getName());
    }
    
    public Constant getString(String string) {
        Global g = strings.get(string);
        if (g == null) {
            byte[] modUtf8 = stringToModifiedUtf8(string);
            g = new Global(getStringVarName(modUtf8), Linkage.weak, 
                    new StringConstant(modUtf8), true);
            addGlobal(g);
            strings.put(string, g);
        }
        return new ConstantGetelementptr(new GlobalRef(g), 0, 0);
    }
    
    public Constant getStringOrNull(String string) {
        if (string == null) {
            return new NullConstant(I8_PTR);
        }
        return getString(string);
    }
    
    public Module build() {
        return new Module(includes, types, globals, aliases, 
                functionDeclarations, asm, functions);
    }
}
