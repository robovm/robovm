/*
 * Copyright (C) 2012 RoboVM
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import soot.SootClass;

/**
 *
 * @version $Id$
 */
public abstract class Clazz implements Comparable<Clazz> {
    private final Clazzes clazzes;
    private final String fileName;
    private final String className;
    private final String internalName;
    private final AbstractPath path;
    
    private SootClass sootClass = null;
    private Map<String, Dependency> dependencies = null;
    
    Clazz(Clazzes clazzes, String fileName, AbstractPath path) {
        this.clazzes = clazzes;
        this.fileName = fileName;
        this.className = fileName.replace(File.separatorChar, '.').substring(0, fileName.lastIndexOf(".class"));
        this.internalName = className.replace('.', '/');
        this.path = path;
    }
    
    public boolean isInBootClasspath() {
        return path.isInBootClasspath();
    }
    
    public Path getPath() {
        return path;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getInternalName() {
        return internalName;
    }
    
    @SuppressWarnings("unchecked")
    private void loadDependencies() {
        if (dependencies == null) {
            File depsFile = clazzes.getConfig().getDepsFile(this);
            if (depsFile.exists()) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(depsFile)));
                    dependencies = (HashMap<String, Dependency>) ois.readObject();
                } catch (IOException e) {
                } catch (ClassNotFoundException e) {
                } finally {
                    IOUtils.closeQuietly(ois);
                }
            }
            if (dependencies == null) {
                dependencies = new HashMap<String,Dependency>();
            }
        }
    }
    
    public void saveDependencies() throws IOException {
        if (dependencies == null) {
            return;
        }
        File depsFile = clazzes.getConfig().getDepsFile(this);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(depsFile)));
            oos.writeObject(dependencies);
        } finally {
            IOUtils.closeQuietly(oos);
        }
    }
    
    public void addDependency(String className) {
        loadDependencies();
        if (!dependencies.containsKey(className)) {
            Clazz clazz = clazzes.load(className);
            String path = null;
            boolean inBootClasspath = false;
            if (clazz != null) {
                path = clazz.getPath().getFile().getAbsolutePath();
                inBootClasspath = clazz.isInBootClasspath();
            }
            dependencies.put(className, new Dependency(className, path,inBootClasspath));
        }
    }

    public void addDependencies(Collection<String> classNames) {
        for (String className : classNames) {
            addDependency(className);
        }
    }

    public void clearDependencies() {
        dependencies = new HashMap<String, Dependency>();
    }

    public Set<Dependency> getDependencies() {
        loadDependencies();
        return new HashSet<Dependency>(dependencies.values());
    }
    
    public SootClass getSootClass() {
        if (sootClass == null) {
            sootClass = clazzes.getSootClass(this);
        }
        return sootClass;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Clazz other = (Clazz) obj;
        if (className == null) {
            if (other.className != null) {
                return false;
            }
        } else if (!className.equals(other.className)) {
            return false;
        }
        if (path == null) {
            if (other.path != null) {
                return false;
            }
        } else if (!path.equals(other.path)) {
            return false;
        }
        return true;
    }

    public abstract byte[] getBytes() throws IOException;
    
    public abstract long lastModified();
    
    @Override
    public int compareTo(Clazz o) {
        return className.compareTo(o.className);
    }
    
    @Override
    public String toString() {
        return className;
    }
}
