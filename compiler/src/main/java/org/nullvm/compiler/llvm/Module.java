/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class Module {
    private final List<URL> includes;
    private final List<Global> globals;
    private final List<Alias> aliases;    
    private final List<Function> functions;
    private final List<FunctionDeclaration> functionDeclarations;
    private final List<UserType> types;
    private final List<String> asm;

    public Module(List<URL> includes, List<UserType> types,
            List<Global> globals, List<Alias> aliases,
            List<FunctionDeclaration> functionDeclarations, List<String> asm,
            List<Function> functions) {
        
        this.includes = includes;
        this.types = types;
        this.globals = globals;
        this.aliases = aliases;
        this.functionDeclarations = functionDeclarations;
        this.asm = asm;
        this.functions = functions;
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
        for (FunctionDeclaration fd : functionDeclarations) {
            sb.append(fd.toString());
            sb.append("\n");
        }
        sb.append("\n");
        for (Global g : globals) {
            sb.append(g.getDefinition());
            sb.append("\n");
        }
        sb.append("\n");
        for (Alias a : aliases) {
            sb.append(a.getDefinition());
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
