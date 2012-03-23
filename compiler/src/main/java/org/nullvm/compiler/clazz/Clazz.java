/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.clazz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private final String packageName;
    private final AbstractPath path;
    
    private SootClass sootClass = null;
    private Package packag = null;
    private ClazzInfo clazzInfo = null;
    
    Clazz(Clazzes clazzes, String fileName, AbstractPath path) {
        this.clazzes = clazzes;
        this.fileName = fileName;
        this.className = fileName.replace(File.separatorChar, '.').substring(0, fileName.lastIndexOf(".class"));
        this.internalName = className.replace('.', '/');
        int index = this.className.lastIndexOf('.');
        this.packageName = index == -1 ? "" : this.className.substring(0, index);
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
    
    public String getPackageName() {
        return packageName;
    }
    
    public Package getPackage() {
        if (packag == null) {
            packag = path.getPackagesMap().get(packageName);
        }
        return packag;
    }
    
    public ClazzInfo resetClazzInfo() {
        File f = clazzes.getConfig().getInfoFile(this);
        if (f.exists()) {
            f.delete();
        }
        clazzInfo = new ClazzInfo();
        return clazzInfo;
    }
    
    public ClazzInfo getClazzInfo() {
        if (clazzInfo == null) {
            File f = clazzes.getConfig().getInfoFile(this);
            if (!f.exists()) {
                clazzInfo = new ClazzInfo();
            } else {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
                    clazzInfo = (ClazzInfo) ois.readObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    IOUtils.closeQuietly(ois);
                }
            }
        }
        return clazzInfo;
    }
    
    public void commitClazzInfo() throws IOException {
        if (clazzInfo == null) {
            throw new IllegalStateException("No ClazzInfo associated with the Clazz " + this);
        }
        File f = clazzes.getConfig().getInfoFile(this);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            oos.writeObject(clazzInfo);
        } finally {
            IOUtils.closeQuietly(oos);
        }
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
