/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @version $Id$
 */
public abstract class AbstractPath implements Path {
    protected final File file;
    protected final Clazzes clazzes;
    protected final int index;
    protected Set<Clazz> clazzSet = null;
    protected Map<String, Package> packageMap = null;
    protected Set<Package> packageSet = null;
    
    AbstractPath(File file, Clazzes clazzes, int index) {
        this.file = file;
        this.clazzes = clazzes;
        this.index = index;
    }

    public boolean isInBootClasspath() {
        return clazzes.isInBootClasspath(this);
    }
    
    public int getIndex() {
        return index;
    }
    
    public File getFile() {
        return file;
    }
    
    public Set<Package> listPackages() {
        if (packageSet == null) {
            packageSet = new TreeSet<Package>(getPackagesMap().values());
        }
        return Collections.unmodifiableSet(packageSet);
    }
    
    public Set<Clazz> listClasses() {
        if (clazzSet == null) {
            clazzSet = doListClasses();
        }
        return Collections.unmodifiableSet(clazzSet);
    }
    
    protected abstract Set<Clazz> doListClasses();
    
    protected Map<String, Package> getPackagesMap() {
        if (packageMap == null) {
            Map<String, Set<Clazz>> m = new HashMap<String, Set<Clazz>>();
            for (Clazz clazz : listClasses()) {
                Set<Clazz> s = m.get(clazz.getPackageName());
                if (s == null) {
                    s = new HashSet<Clazz>();
                    m.put(clazz.getPackageName(), s);
                }
                s.add(clazz);
            }
            packageMap = new HashMap<String, Package>();
            for (Entry<String, Set<Clazz>> entry : m.entrySet()) {
                packageMap.put(entry.getKey(), new Package(entry.getKey(), entry.getValue(), this));
            }
        }
        return packageMap;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((file == null) ? 0 : file.hashCode());
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
        AbstractPath other = (AbstractPath) obj;
        if (file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!file.equals(other.file)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return file.toString();
    }
    
}
