/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public class Clazzes {
    private final List<Path> bootclasspathPaths = new ArrayList<Path>();
    private final List<Path> classpathPaths = new ArrayList<Path>();
    private final List<Path> paths = new ArrayList<Path>();
    private final Map<String, Clazz> cache = new HashMap<String, Clazz>();
    private final List<Clazz> allClasses = new ArrayList<Clazz>();

    public Clazzes(List<File> bootclasspath, List<File> classpath) throws IOException {
        Set<File> seen = new HashSet<File>();
        addPaths(bootclasspath, bootclasspathPaths, seen);
        addPaths(classpath, classpathPaths, seen);
        paths.addAll(bootclasspathPaths);
        paths.addAll(classpathPaths);
        populateCache();
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
            for (Clazz clazz : p.list()) {
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
    
    public List<Clazz> list() {
        return Collections.unmodifiableList(allClasses);
    }
}
