/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Function;

import soot.SootMethod;

/**
 * Abstract implementation for {@link CompilerPlugin} with empty 
 * implementations for all methods.
 */
public abstract class AbstractCompilerPlugin extends CompilerPlugin {
    @Override
    public PluginArguments getArguments() {
        return new PluginArguments("", Collections.<PluginArgument>emptyList());
    }
    
    @Override
    public void beforeClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) 
            throws IOException {
    }
    
    @Override
    public void beforeMethod(Config config, Clazz clazz, SootMethod method, 
            ModuleBuilder moduleBuilder) throws IOException {
    }
    
    @Override
    public void afterClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) 
            throws IOException {
    }
    
    @Override
    public void afterMethod(Config config, Clazz clazz, SootMethod method, 
            ModuleBuilder moduleBuilder, Function function) throws IOException {
    }

    @Override
    public void afterObjectFile(Config config, Clazz clazz, File objectFile) throws IOException {
    }
}
