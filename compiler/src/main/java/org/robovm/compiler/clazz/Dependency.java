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

import java.io.Serializable;

/**
 * Records a dependency on {@link Clazz} <code>A</code> for a {@link Clazz} <code>B</code> and 
 * the path where <code>A</code> was located when <code>B</code> was built.
 */
public class Dependency implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private final String className;
    private final String path;
    private final boolean inBootClasspath;
    
    Dependency(String className, String path, boolean inBootClasspath) {
        this.className = className;
        this.path = path;
        this.inBootClasspath = inBootClasspath;
    }

    public String getClassName() {
        return className;
    }

    public String getPath() {
        return path;
    }
    
    public boolean isInBootClasspath() {
        return inBootClasspath;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((className == null) ? 0 : className.hashCode());
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
        Dependency other = (Dependency) obj;
        if (className == null) {
            if (other.className != null) {
                return false;
            }
        } else if (!className.equals(other.className)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return Dependency.class.getName() 
                + "{className=" + className 
                + ", path=" + path 
                + ", inBootClasspath=" + inBootClasspath + "}";
    }
}
