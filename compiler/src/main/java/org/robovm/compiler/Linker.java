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

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.hash.HashTableGenerator;
import org.robovm.compiler.hash.ModifiedUtf8HashFunction;
import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.ConstantGetelementptr;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Type;

/**
 *
 */
public class Linker {
    private final Config config;

    public Linker(Config config) {
        this.config = config;
    }
    
    public void link(Set<Clazz> classes) throws IOException {
        Set<Clazz> linkClasses = new TreeSet<Clazz>(classes);
        config.getLogger().info("Linking %d classes", linkClasses.size());

        ModuleBuilder mb = new ModuleBuilder();
        mb.addInclude(getClass().getClassLoader().getResource(String.format("header-%s-%s.ll", config.getOs().getFamily(), config.getArch())));
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));

        mb.addGlobal(new Global("_bcDynamicJNI", new IntegerConstant(config.isUseDynamicJni() ? (byte) 1 : (byte) 0)));

        HashTableGenerator<String, Constant> bcpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        HashTableGenerator<String, Constant> cpHashGen = new HashTableGenerator<String, Constant>(new ModifiedUtf8HashFunction());
        for (Clazz clazz : linkClasses) {
            Global info = new Global(mangleClass(clazz.getInternalName()) + "_info_struct", external, I8_PTR, false);
            mb.addGlobal(info);
            if (clazz.isInBootClasspath()) {
                bcpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            } else {
                cpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            }
        }
        mb.addGlobal(new Global("_bcBootClassesHash", new ConstantGetelementptr(mb.newGlobal(bcpHashGen.generate(), true).ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClassesHash", new ConstantGetelementptr(mb.newGlobal(cpHashGen.generate(), true).ref(), 0, 0)));
        
        ArrayConstantBuilder bootClasspathValues = new ArrayConstantBuilder(I8_PTR);
        ArrayConstantBuilder classpathValues = new ArrayConstantBuilder(I8_PTR);
        List<Path> allPaths = new ArrayList<Path>();
        allPaths.addAll(config.getClazzes().getPaths());
        allPaths.addAll(config.getResourcesPaths());
        for (Path path : allPaths) {
            String entryName = null;
            if (config.isSkipInstall() && config.getTarget().canLaunchInPlace()) {
                entryName = path.getFile().getAbsolutePath();
            } else {
                entryName = config.getTarget().getInstallRelativeArchivePath(path);
            }
            if (path.isInBootClasspath()) {
                bootClasspathValues.add(mb.getString(entryName));
            } else {
                classpathValues.add(mb.getString(entryName));
            }
        }
        bootClasspathValues.add(new NullConstant(Type.I8_PTR));
        classpathValues.add(new NullConstant(Type.I8_PTR));
        mb.addGlobal(new Global("_bcBootclasspath", new ConstantGetelementptr(mb.newGlobal(bootClasspathValues.build()).ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClasspath", new ConstantGetelementptr(mb.newGlobal(classpathValues.build()).ref(), 0, 0)));

        if (config.getMainClass() != null) {
            mb.addGlobal(new Global("_bcMainClass", mb.getString(config.getMainClass())));
        }        
        
        File linkerLl = new File(config.getTmpDir(), "linker.ll");
        FileUtils.writeStringToFile(linkerLl, mb.build().toString(), "UTF-8");
        File linkerBc = new File(config.getTmpDir(), "linker.bc");
        CompilerUtil.opt(config, linkerLl, linkerBc, "-mem2reg", "-always-inline");
        File linkerS = new File(config.getTmpDir(), "linker.s");
        CompilerUtil.llc(config, linkerBc, linkerS);
        File linkerO = new File(config.getTmpDir(), "linker.o");
        CompilerUtil.assemble(config, linkerS, linkerO);

        List<File> objectFiles = new ArrayList<File>();
        objectFiles.add(linkerO);
        
        for (Clazz clazz : linkClasses) {
            objectFiles.add(config.getOFile(clazz));
        }
        config.getTarget().build(objectFiles);
    }
}
