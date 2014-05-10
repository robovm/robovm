/*
 * Copyright (C) 2012 Trillian Mobile AB
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import org.apache.commons.io.IOUtils;

/**
 *
 * @version $Id$
 */
public class Module {
    private final Collection<URL> includes;
    private final Collection<Global> globals;
    private final Collection<Alias> aliases;    
    private final Collection<Function> functions;
    private final Collection<FunctionDeclaration> functionDeclarations;
    private final Collection<UserType> types;
    private final Collection<String> asm;
    private final Collection<NamedMetadata> namedMetadata;
    private final Collection<UnnamedMetadata> unnamedMetadata;

    public Module(Collection<URL> includes, Collection<UserType> types,
            Collection<Global> globals, Collection<Alias> aliases,
            Collection<FunctionDeclaration> functionDeclarations, Collection<String> asm,
            Collection<Function> functions, Collection<NamedMetadata> namedMetadata,
            Collection<UnnamedMetadata> unnamedMetadata) {
        
        this.includes = includes;
        this.types = types;
        this.globals = globals;
        this.aliases = aliases;
        this.functionDeclarations = functionDeclarations;
        this.asm = asm;
        this.functions = functions;
        this.namedMetadata = namedMetadata;
        this.unnamedMetadata = unnamedMetadata;
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
        sb.append("\n");
        for (NamedMetadata md : namedMetadata) {
            sb.append(md.toString());
            sb.append("\n");
        }
        sb.append("\n");
        for (UnnamedMetadata md : unnamedMetadata) {
            sb.append(md.getDefinition());
            sb.append("\n");
        }
        return sb.toString();
    }
}
