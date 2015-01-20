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

import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Function;

import soot.SootClass;
import soot.SootMethod;

/**
 * Plugin interface which makes it possible to hook into the compilation
 * process and modify classes and methods during the compilation.
 */
public abstract class CompilerPlugin extends Plugin {
    /**
     * Called just before a class is about to be compiled. Modifications to the 
     * underlying {@link SootClass} ({@link Clazz#getSootClass()}) should be
     * done at this stage.
     * 
     * @param config the current {@link Config}.
     * @param clazz the {@link Clazz} being compiled.
     * @param moduleBuilder the {@link ModuleBuilder} holding the generated
     *        bitcode.
     */
    public abstract void beforeClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) 
            throws IOException;
    
    /**
     * Called just before a method is about to be compiled. Modifications to the 
     * underlying {@link SootMethod} should be done at this stage.
     * 
     * @param config the current {@link Config}.
     * @param clazz the {@link Clazz} being compiled.
     * @param method the method being compiled.
     * @param moduleBuilder the {@link ModuleBuilder} holding the generated
     *        bitcode.
     */
    public abstract void beforeMethod(Config config, Clazz clazz, SootMethod method, 
            ModuleBuilder moduleBuilder) throws IOException;
    
    /**
     * Called after a class has been compiled to LLVM bitcode but before it is
     * converted into machine code.
     * 
     * @param config the current {@link Config}.
     * @param clazz the {@link Clazz} being compiled.
     * @param moduleBuilder the {@link ModuleBuilder} holding the generated
     *        bitcode.
     */
    public abstract void afterClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) 
            throws IOException;
    
    /**
     * Called after a method has been compiled to LLVM bitcode but before it is
     * converted into machine code.
     * 
     * @param config the current {@link Config}.
     * @param clazz the {@link Clazz} being compiled.
     * @param method the method being compiled.
     * @param moduleBuilder the {@link ModuleBuilder} holding the generated
     *        bitcode.
     * @param function the function corresponding to the method.
     */
    public abstract void afterMethod(Config config, Clazz clazz, SootMethod method,
            ModuleBuilder moduleBuilder, Function function) throws IOException;
    
    /**
     * Called after the object file of a class has been compiled to an object file.
     * @param config the current {@link Config}
     * @param clazz the {@link Clazz} being compiled
     * @param objectFile the object file
     */
    public abstract void afterObjectFile(Config config, Clazz clazz, File objectFile) throws IOException;
}
