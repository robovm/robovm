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
import java.io.StringWriter;
import java.io.Writer;
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

    public void write(Writer writer) throws IOException {
        for (URL g : includes) {
            InputStream in = null;
            try {
                in = g.openStream();
                IOUtils.copy(in, writer, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(in);
            }
            writer.write("\n");
        }
        writer.write("\n");
        for (String s : asm) {
            writer.write("module asm \"");
            writer.write(s);
            writer.write("\"\n");
        }
        writer.write("\n");
        for (UserType type : types) {
            writer.write(type.getAlias());
            writer.write(" = type ");
            writer.write(type.getDefinition());
            writer.write("\n");
        }
        writer.write("\n");
        for (FunctionDeclaration fd : functionDeclarations) {
            writer.write(fd.toString());
            writer.write("\n");
        }
        writer.write("\n");
        for (Global g : globals) {
            writer.write(g.getDefinition());
            writer.write("\n");
        }
        writer.write("\n");
        for (Alias a : aliases) {
            writer.write(a.getDefinition());
            writer.write("\n");
        }
        writer.write("\n");
        for (Function f : functions) {
            writer.write(f.toString());
            writer.write("\n");
        }
        writer.write("\n");
        for (NamedMetadata md : namedMetadata) {
            writer.write(md.toString());
            writer.write("\n");
        }
        writer.write("\n");
        for (UnnamedMetadata md : unnamedMetadata) {
            writer.write(md.getDefinition());
            writer.write("\n");
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        try {
            write(sw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }
}
