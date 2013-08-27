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
package org.robovm.compiler.clazz;

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
    final Clazzes clazzes;
    private final String fileName;
    private final String className;
    private final String internalName;
    private final AbstractPath path;

    private ClazzInfo clazzInfo = null; 
    private SootClass sootClass = null;
    
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
    
    public ClazzInfo getClazzInfo() {
        if (clazzInfo == null) {
            File infoFile = clazzes.getConfig().getInfoFile(this);
            if (infoFile.exists()) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(
                            new BufferedInputStream(new FileInputStream(infoFile)));
                    clazzInfo = (ClazzInfo) ois.readObject();
                    clazzInfo.setClazz(this);
                } catch (IOException e) {
                } catch (ClassNotFoundException e) {
                } finally {
                    IOUtils.closeQuietly(ois);
                }
            }
        }
        return clazzInfo;
    }

    public ClazzInfo resetClazzInfo() {
        clazzInfo = new ClazzInfo(this, getSootClass());
        return clazzInfo;
    }
    
    public void saveClazzInfo() throws IOException {
        if (clazzInfo == null) {
            throw new IllegalStateException();
        }
        File infoFile = clazzes.getConfig().getInfoFile(this);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(infoFile)));
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
