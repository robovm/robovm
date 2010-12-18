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

import verify.VerificationException;
import verify.bytecode.BytecodeVerifier;
import verify.classfile.ClassFile;
import verify.type.ClassInfo;
import verify.type.ClassInfoProvider;
import verify.type.TypeContext;

/**
 *
 * @version $Id$
 */
public class Clazzes {
    private final List<Path> bootclasspathPaths = new ArrayList<Path>();
    private final List<Path> classpathPaths = new ArrayList<Path>();
    private final List<Path> paths = new ArrayList<Path>();
    private final Map<String, Clazz> cache = new HashMap<String, Clazz>();

    public Clazzes(List<File> bootclasspath, List<File> classpath) throws IOException {
        Set<File> seen = new HashSet<File>();
        addPaths(bootclasspath, bootclasspathPaths, seen);
        addPaths(classpath, classpathPaths, seen);
        paths.addAll(bootclasspathPaths);
        paths.addAll(classpathPaths);
        populateCache();
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
            if (!seen.contains(file)) {
                Path p = file.isDirectory() ? new DirectoryPath(file, this) : new ZipFilePath(file, this);
                cp.add(p);
                seen.add(file);
            }
        }

    }
    
    private void populateCache() {
        for (Path p : paths) {
            for (Clazz clazz : p.list()) {
                if (!cache.containsKey(clazz.getInternalName())) {
                    cache.put(clazz.getInternalName(), clazz);
                }
            }
        }
    }
    
    void verify(Clazz clazz) throws IOException {
        ClassInfoProvider provider = new ClassInfoProvider() {
            public ClassInfo provide(String name) {
                Clazz c = load(name);
                if (c != null) {
                    try {
                        final ClassFile cf = new ClassFile(c.getBytes());
                        return new ClassInfo() {
                            public String[] getSuperInterfaceNames() {
                                if (cf.interfaces_count == 0) {
                                    return null;
                                }
                                    
                                String[] result = new String[cf.interfaces_count];
                                for (int i = 0; i < cf.interfaces_count; i++) {
                                    result[i] = cf.pool.get_class (cf.interfaces[i]);
                                }
                                
                                return result;
                            }
                            public String getSuperClassName() {
                                return (cf.super_class != 0) ? cf.pool.get_class(cf.super_class) : null;
                            }
                            public String getClassName() {
                                return cf.pool.get_class(cf.this_class);
                            }
                            public int getAccessFlags() {
                                return cf.access_flags;
                            }
                        };
                    } catch (IOException e) {
                        return null;
                    }
                }
                return null;
            }
        };

        TypeContext context = new TypeContext(provider);
        BytecodeVerifier verifier = new BytecodeVerifier(context);
        try {
            verifier.verify(new ClassFile(clazz.getBytes()));
        } catch (VerificationException e) {
            throw (VerifyError) new VerifyError(e.getMessage()).initCause(e);
        } catch (ClassFormatError e) {
            throw (VerifyError) new VerifyError(e.getMessage()).initCause(e);
        } catch (Throwable t) {
            throw (VerifyError) new VerifyError("Internal error occurred during verification: " + t.getMessage()).initCause(t);
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
    
}
