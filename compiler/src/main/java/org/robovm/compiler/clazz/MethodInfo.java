/*
 * Copyright (C) 2015 RoboVM AB
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
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.Triple;

public class MethodInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final ClazzInfo ci;
    private int modifiers;
    private String name;
    private String desc;
    private boolean callback;
    private boolean weaklyLinked;
    private Map<String, Dependency> dependencies = new HashMap<>();

    MethodInfo(ClazzInfo ci, int modifiers, String name, String desc, boolean callback, boolean weaklyLinked) {
        this.ci = ci;
        this.modifiers = modifiers;
        this.name = name;
        this.desc = desc;
        this.callback = callback;
        this.weaklyLinked = weaklyLinked;
    }
    
    public void addClassDependency(String className, boolean weak) {
        if (!dependencies.containsKey(className)) {
            Clazz clazz = this.ci.getClazz().clazzes.load(className);
            String path = clazz != null ? clazz.getPath().getFile().getAbsolutePath() : null;
            boolean inBootClasspath = clazz != null ? clazz.isInBootClasspath() : false;
            dependencies.put(className, new ClassDependency(className, path, inBootClasspath, weak));
        }
    }

    public void addClassDependencies(Collection<String> classNames, boolean weak) {
        for (String className : classNames) {
            addClassDependency(className, weak);
        }
    }

    public void addInvokeMethodDependency(String owner, String name, String desc, boolean weak) {
        String key = "Invoke." + owner + "." + name + desc;
        if (!dependencies.containsKey(key)) {
            Clazz clazz = this.ci.getClazz().clazzes.load(owner);
            String path = clazz != null ? clazz.getPath().getFile().getAbsolutePath() : null;
            boolean inBootClasspath = clazz != null ? clazz.isInBootClasspath() : false;
            dependencies.put(key, new InvokeMethodDependency(owner, name, desc, path, inBootClasspath, weak));
        }
    }
    
    public void addInvokeMethodDependencies(Collection<Triple<String, String, String>> deps, boolean weak) {
        for (Triple<String, String, String> d : deps) {
            addInvokeMethodDependency(d.getLeft(), d.getMiddle(), d.getRight(), weak);
        }
    }

    public void addSuperMethodDependency(String owner, String name, String desc, boolean weak) {
        String key = "Super." + owner + "." + name + desc;
        if (!dependencies.containsKey(key)) {
            Clazz clazz = this.ci.getClazz().clazzes.load(owner);
            String path = clazz != null ? clazz.getPath().getFile().getAbsolutePath() : null;
            boolean inBootClasspath = clazz != null ? clazz.isInBootClasspath() : false;
            dependencies.put(key, new SuperMethodDependency(owner, name, desc, path, inBootClasspath, weak));
        }
    }
    
    public void addSuperMethodDependencies(Collection<Triple<String, String, String>> deps, boolean weak) {
        for (Triple<String, String, String> d : deps) {
            addSuperMethodDependency(d.getLeft(), d.getMiddle(), d.getRight(), weak);
        }
    }

    public Set<Dependency> getDependencies() {
        return new HashSet<Dependency>(dependencies.values());
    }

    public String getName() {
        return name;
    }
    
    public String getDesc() {
        return desc;
    }

    public boolean isPublic() {
        return (modifiers & Modifier.PUBLIC) > 0;
    }
    
    public boolean isPrivate() {
        return (modifiers & Modifier.PRIVATE) > 0;
    }
    
    public boolean isProtected() {
        return (modifiers & Modifier.PROTECTED) > 0;
    }
    
    public boolean isStatic() {
        return (modifiers & Modifier.STATIC) > 0;
    }
    
    public boolean isFinal() {
        return (modifiers & Modifier.FINAL) > 0;
    }
    
    public boolean isSynchronized() {
        return (modifiers & Modifier.SYNCHRONIZED) > 0;
    }
    
    public boolean isNative() {
        return (modifiers & Modifier.NATIVE) > 0;
    }
    
    public boolean isAbstract() {
        return (modifiers & Modifier.ABSTRACT) > 0;
    }

    public boolean isCallback() {
        return callback;
    }

    public boolean isWeaklyLinked() {
        return weaklyLinked;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ci == null) ? 0 : ci.hashCode());
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + modifiers;
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
        MethodInfo other = (MethodInfo) obj;
        if (ci == null) {
            if (other.ci != null) {
                return false;
            }
        } else if (!ci.equals(other.ci)) {
            return false;
        }
        if (desc == null) {
            if (other.desc != null) {
                return false;
            }
        } else if (!desc.equals(other.desc)) {
            return false;
        }
        if (modifiers != other.modifiers) {
            return false;
        }
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
        return ToStringBuilder.reflectionToString(this);
    }
}