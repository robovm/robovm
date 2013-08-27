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
package org.robovm.compiler;

import static org.robovm.compiler.Strings.*;
import static org.robovm.compiler.llvm.Type.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.llvm.Alias;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantGetelementptr;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionDeclaration;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.Linkage;
import org.robovm.compiler.llvm.Module;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.StringConstant;
import org.robovm.compiler.llvm.UserType;

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
    
    public GlobalRef getGlobalRef(String name) {
        for (Global g : globals) {
            if (name.equals(g.getName())) {
                return g.ref();
            }
        }
        throw new IllegalArgumentException("Global with name " + name + " not found");
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
            byte[] modUtf8 = stringToModifiedUtf8Z(string);
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
