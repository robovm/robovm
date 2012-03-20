/**
 * 
 */
package org.nullvm.compiler.clazz;

import java.util.Set;

/**
 * @author niklas
 *
 */
public class Package implements Comparable<Package> {
    private final String name;
    private final Set<Clazz> clazzSet;
    private final AbstractPath path;

    Package(String name, Set<Clazz> clazzSet, AbstractPath path) {
        this.name = name;
        this.clazzSet = clazzSet;
        this.path = path;
    }
    
    public String getName() {
        return name;
    }

    public Set<Clazz> listClasses() {
        return clazzSet;
    }
    
    public Path getPath() {
        return path;
    }
    
    @Override
    public int compareTo(Package o) {
        return name.compareTo(o.name);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Package other = (Package) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
