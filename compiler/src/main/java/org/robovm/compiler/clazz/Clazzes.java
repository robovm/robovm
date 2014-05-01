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
package org.robovm.compiler.clazz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.config.Config;

import soot.Scene;
import soot.SootClass;
import soot.options.Options;

/**
 *
 * @version $Id$
 */
public class Clazzes {
    private final Config config;
    private final List<Path> bootclasspathPaths = new ArrayList<Path>();
    private final List<Path> classpathPaths = new ArrayList<Path>();
    private final List<Path> paths = new ArrayList<Path>();
    private final Map<String, Clazz> cache = new HashMap<String, Clazz>();
    private final List<Clazz> allClasses = new ArrayList<Clazz>();

    private boolean sootInitialized = false;
    
    public Clazzes(Config config, List<File> bootclasspath, List<File> classpath) throws IOException {
        this.config = config;
        Set<File> seen = new HashSet<File>();
        addPaths(bootclasspath, bootclasspathPaths, seen, true);
        addPaths(classpath, classpathPaths, seen, false);
        paths.addAll(bootclasspathPaths);
        paths.addAll(classpathPaths);
        populateCache();
    }
    
    Config getConfig() {
        return config;
    }
    
    private static boolean isArchive(File f) {
        String name = f.getName().toLowerCase();
        return name.endsWith(".zip") || name.endsWith(".jar");
    }
    
    private void addPaths(List<File> files, List<Path> cp, Set<File> seen, boolean inBootclasspath) throws IOException {
        for (File file : files) {
            if (!file.exists()) {
                config.getLogger().warn("Classpath entry %s does not exist", file);
                continue;
            }
            if (file.isFile() && !isArchive(file)) {
                throw new IOException("File is not an archive file: " + file.getAbsolutePath());
            }
            if (file.isDirectory() && isEmpty(file)) {
                continue;
            }
            if (!seen.contains(file)) {
                Path p = createPath(file, cp, inBootclasspath);
                cp.add(p);
                seen.add(file);
            }
        }

    }
    
    private Path createPath(File file, List<Path> cp, boolean inBootclasspath) throws IOException {
        return file.isDirectory() 
                ? new DirectoryPath(file, this, cp.size(), inBootclasspath) 
                : new ZipFilePath(file, this, cp.size(), inBootclasspath);
    }
    
    public Path createResourcesBootclasspathPath(File file) throws IOException {
        return createPath(file, bootclasspathPaths, true);
    }

    public Path createResourcesClasspathPath(File file) throws IOException {
        return createPath(file, classpathPaths, false);
    }
    
    private boolean isEmpty(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                return false;
            }
            if (!isEmpty(f)) {
                return false;
            }
        }
        return true;
    }
    
    private void populateCache() {
        for (Path p : paths) {
            for (Clazz clazz : p.listClasses()) {
                if (!cache.containsKey(clazz.getInternalName())) {
                    cache.put(clazz.getInternalName(), clazz);
                    allClasses.add(clazz);
                }
            }
        }
    }
    
    public Clazz load(String internalName) {
        Clazz clazz = cache.get(internalName);
        if (clazz == null) {
            // Could be a generated class
            for (Path p : paths) {
                clazz = p.loadGeneratedClass(internalName);
                if (clazz != null) {
                    break;
                }
            }
        }
        return clazz;
    }
    
    public List<Path> getBootclasspathPaths() {
        return Collections.unmodifiableList(bootclasspathPaths);
    }

    public List<Path> getClasspathPaths() {
        return Collections.unmodifiableList(classpathPaths);
    }
    
    public List<Path> getPaths() {
        return Collections.unmodifiableList(paths);
    }
    
    public List<Clazz> listClasses() {
        return Collections.unmodifiableList(allClasses);
    }
    
    SootClass getSootClass(Clazz clazz) {
        if (!sootInitialized) {
            initializeSoot(this);
            sootInitialized = true;
        }
        return Scene.v().loadClassAndSupport(clazz.getClassName());
    }
    
    private static String getSootClasspath(Clazzes clazzes) {
        StringBuilder sb = new StringBuilder();
        for (Path path : clazzes.getPaths()) {
            if (sb.length() > 0) {
                sb.append(File.pathSeparator);
            }
            try {
                sb.append(path.getFile().getCanonicalPath());
                sb.append(File.pathSeparator);
                sb.append(clazzes.config.getGeneratedClassDir(path).getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }
    
    private static void initializeSoot(Clazzes clazzes) {
        soot.G.reset();
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_include_all(true);
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(getSootClasspath(clazzes));

        /*
         * Disable the jb.dae phase (DeadAssignmentEliminator) since it removes 
         * LDC instructions which would have thrown a NoClassDefFoundError.
         * TODO: Report this to soot as a bug?
         */
        Options.v().setPhaseOption("jb.dae", "enabled:false");
        /* 
         * Disable the jb.uce phase (UnreachableCodeEliminator) since it seems 
         * to remove try-catch blocks which catches a non-existing Throwable 
         * class. This should generate a NoClassDefFoundError at runtime but 
         * with the UCE in place no exception is thrown.
         */
        Options.v().setPhaseOption("jb.uce", "enabled:false");
        
        /*
         * Enable jap.npc (NullPointerChecker) and jap.abc (ArrayBoundsChecker)
         * phases in the annotation pack. The annotation pack is enabled by 
         * default but all its phases are disabled by default.
         */
        Options.v().setPhaseOption("jap.npc", "enabled:true");
        Options.v().setPhaseOption("jap.abc", "enabled:true");

        /*
         * Enable the jop (Jimple optimization) pack but disable all phases
         * for now.
         */
        Options.v().setPhaseOption("jop", "enabled:true");
        Options.v().setPhaseOption("jop.cse", "enabled:false");
        Options.v().setPhaseOption("jop.bcm", "enabled:false");
        Options.v().setPhaseOption("jop.lcm", "enabled:false");
        Options.v().setPhaseOption("jop.cp", "enabled:false");
        Options.v().setPhaseOption("jop.cpf", "enabled:false");
        Options.v().setPhaseOption("jop.cbf", "enabled:false");
        Options.v().setPhaseOption("jop.dae", "enabled:false");
        Options.v().setPhaseOption("jop.nce", "enabled:false");
        Options.v().setPhaseOption("jop.uce1", "enabled:false");
        Options.v().setPhaseOption("jop.ubf1", "enabled:false");
        Options.v().setPhaseOption("jop.uce2", "enabled:false");
        Options.v().setPhaseOption("jop.ubf2", "enabled:false");
        Options.v().setPhaseOption("jop.ule", "enabled:false");
        
        Scene.v().loadNecessaryClasses();
    }

}
