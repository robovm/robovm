/*
 * Copyright (C) 2012 RoboVM AB
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public abstract class AbstractPath implements Path {
    protected final File file;
    protected final Clazzes clazzes;
    protected final int index;
    protected Set<Clazz> clazzSet = null;
    protected Set<Package> packageSet = null;
    protected boolean inBootclasspath = false;
    protected Map<String, Clazz> generatedClasses = new HashMap<String, Clazz>();
    protected final File generatedClassDir;
    
    AbstractPath(File file, Clazzes clazzes, int index, boolean inBootclasspath) {
        this.file = file.getAbsoluteFile();
        this.clazzes = clazzes;
        this.index = index;
        this.inBootclasspath = inBootclasspath;
        this.generatedClassDir = clazzes.getConfig().getGeneratedClassDir(this);
    }

    public boolean isInBootClasspath() {
        return inBootclasspath;
    }
    
    public int getIndex() {
        return index;
    }
    
    public File getFile() {
        return file;
    }
    
    public Set<Clazz> listClasses() {
        if (clazzSet == null) {
            clazzSet = doListClasses();
        }
        return Collections.unmodifiableSet(clazzSet);
    }
    
    public File getGeneratedClassFile(String internalName) {
        return new File(generatedClassDir, internalName.replace('/', File.separatorChar) + ".class");
    }
    
    public Clazz loadGeneratedClass(String internalName) {
        // First check the cache
        Clazz clazz = generatedClasses.get(internalName);
        if (clazz == null) {
            File classFile = getGeneratedClassFile(internalName);
            if (classFile.exists() && classFile.isFile()) {
                clazz = new DirectoryPath.DirectoryPathClazz(clazzes, this, generatedClassDir, classFile);
            }
            if (clazz != null) {
                // Put the clazz in the cache
                generatedClasses.put(internalName, clazz);
            }
        }
        return clazz;
    }
    
    protected abstract Set<Clazz> doListClasses();
    
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
