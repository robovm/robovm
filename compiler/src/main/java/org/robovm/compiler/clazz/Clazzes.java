/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.clazz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.Config;

import soot.Body;
import soot.BodyTransformer;
import soot.Pack;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.Transform;
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
        addPaths(bootclasspath, bootclasspathPaths, seen);
        addPaths(classpath, classpathPaths, seen);
        paths.addAll(bootclasspathPaths);
        paths.addAll(classpathPaths);
        populateCache();
    }
    
    Config getConfig() {
        return config;
    }
    
    boolean isInBootClasspath(Path path) {
        return bootclasspathPaths.contains(path);
    }
    
    private static boolean isArchive(File f) {
        return f.getName().matches("(?i)^.*(\\.zip|\\.jar)$");
    }
    
    private void addPaths(List<File> files, List<Path> cp, Set<File> seen) throws IOException {
        for (File file : files) {
            if (!file.exists()) {
                throw new FileNotFoundException(file.getAbsolutePath());
            }
            if (file.isFile() && !isArchive(file)) {
                throw new IOException("File is not an archive file: " + file.getAbsolutePath());
            }
            if (file.isDirectory() && isEmpty(file)) {
                continue;
            }
            if (!seen.contains(file)) {
                Path p = file.isDirectory() ? new DirectoryPath(file, this, cp.size()) : new ZipFilePath(file, this, cp.size());
                cp.add(p);
                seen.add(file);
            }
        }

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
        if (clazz != null) {
            return clazz;
        }
        return null;
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
        Options.v().setPhaseOption("jap.npc", "enabled:true");
        Options.v().setPhaseOption("jap.abc", "enabled:true");
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(getSootClasspath(clazzes));

        Scene.v().loadNecessaryClasses();

        /*
         * Hack: Remove the DeadAssignmentEliminator since it removes LDC instructions
         * which would have thrown a NoClassDefFoundError.
         * TODO: Report this to soot as a bug?
         * 
         * Hack: Remove the UnreachableCodeEliminator since it seems to remove
         * try-catch blocks which catches a non-existing Throwable class. This
         * should generate a NoClassDefFoundError at runtime but with the UCE
         * in place to exception is thrown.
         */
        Pack pack = PackManager.v().getPack("jb");
        for (Iterator<?> it = pack.iterator(); it.hasNext();) {
            Transform t = (Transform) it.next();
            if ("jb.dae".equals(t.getPhaseName())) {
                it.remove();
            }
            if ("jb.uce".equals(t.getPhaseName())) {
                it.remove();
            }
        }
        pack.insertAfter(new Transform("jb.dae", new BodyTransformer() {
            @SuppressWarnings("rawtypes")
            @Override
            protected void internalTransform(Body b, String phaseName, Map options) {
            }
        }), "jb.cp");
        pack.insertAfter(new Transform("jb.uce", new BodyTransformer() {
            @SuppressWarnings("rawtypes")
            @Override
            protected void internalTransform(Body b, String phaseName, Map options) {
            }
        }), "jb.ne");
    }

}
