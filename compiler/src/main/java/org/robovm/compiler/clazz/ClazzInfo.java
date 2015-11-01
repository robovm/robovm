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

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.Annotations;
import org.robovm.compiler.Types;

import soot.SootClass;
import soot.SootMethod;

/**
 *
 */
public class ClazzInfo implements Serializable {
    private static final long serialVersionUID = 99L;
    
    private int modifiers;
    private String name;
    private String internalName;
    private String superclassName;
    private final List<String> interfaceNames = new ArrayList<String>();
    private final List<MethodInfo> methods = new ArrayList<MethodInfo>();
    private final Set<String> catchNames = new HashSet<String>();
    private Map<String, Dependency> dependencies = new HashMap<String,Dependency>();
    private final Set<String> checkcasts = new HashSet<String>();
    private final Set<String> instanceofs = new HashSet<String>();
    private final Set<String> invokes = new HashSet<String>();
    private boolean isStruct;
    private boolean isEnum;
    
    private transient Clazz clazz;
    
    ClazzInfo() {}
    
    ClazzInfo(Clazz clazz, SootClass sootClass) {
        this.clazz = clazz;
        modifiers = sootClass.getModifiers();
        name = sootClass.getName();
        internalName = Types.getInternalName(sootClass);
    }
    
    private ClazzInfo(String internalName) {
        this.internalName = internalName;
        this.name = internalName.replace('/', '.');
    }
    
    public void initClassInfo() {
        if (isPhantom()) {
            return;
        }
        SootClass sootClass = clazz.getSootClass();
        isStruct = Types.isStruct(sootClass);
        isEnum = Types.isEnum(sootClass);
        if (sootClass.hasSuperclass()) {
            superclassName = Types.getInternalName(sootClass.getSuperclass());
        }
        interfaceNames.clear();
        for (SootClass ifs : sootClass.getInterfaces()) {
            interfaceNames.add(Types.getInternalName(ifs));
        }
        methods.clear();
        boolean classWeaklyLinked = Annotations.hasWeaklyLinkedAnnotation(sootClass);
        boolean classStronglyLinked = Annotations.hasStronglyLinkedAnnotation(sootClass);
        for (SootMethod method : sootClass.getMethods()) {
            boolean methodWeaklyLinked = Annotations.hasWeaklyLinkedAnnotation(method);
            boolean methodStronglyLinked = Annotations.hasStronglyLinkedAnnotation(method);
            methods.add(new MethodInfo(this, method.getModifiers(), method.getName(), 
                    Types.getDescriptor(method), Annotations.hasCallbackAnnotation(method),
                    methodWeaklyLinked || (classWeaklyLinked && !methodStronglyLinked), 
                    methodStronglyLinked || (classStronglyLinked && !methodWeaklyLinked)));
        }
    }
    
    public boolean isPhantom() {
        return clazz == null;
    }
    
    void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
    
    /**
     * may be null if this is a phantom class
     */
    public Clazz getClazz() {
        return clazz;
    }
    
    public boolean isStruct() {
        return isStruct;
    }
    
    public boolean isEnum() {
        return isEnum;
    }
    
    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getInternalName() {
        return internalName;
    }
    
    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
    
    public String getPackageName() {
        int idx = name.lastIndexOf('.');
        if (idx == -1) {
            return "";
        }
        return name.substring(0, idx);
    }
    
    public String getSuperclassName() {
        return superclassName;
    }
    
    public void setSuperclassName(String superclassName) {
        this.superclassName = superclassName;
    }
    
    public boolean hasSuperclass() {
        return superclassName != null;
    }
    
    private ClazzInfo loadClazzInfo(String n) {
        if (n != null) {
            Clazz c = clazz.clazzes.load(n);
            if (c != null) {
                ClazzInfo ci = c.getClazzInfo();
                if (ci != null) {
                    return ci;
                }
            }
        }
        return new ClazzInfo(n);
    }
    
    public ClazzInfo getSuperclass() {
        return loadClazzInfo(superclassName);
    }
    
    public List<String> getInterfaceNames() {
        return Collections.unmodifiableList(interfaceNames);
    }
    
    public void setInterfaceNames(List<String> interfaceNames) {
        this.interfaceNames.clear();
        this.interfaceNames.addAll(interfaceNames);
    }
    
    public List<ClazzInfo> getInterfaces() {
        List<ClazzInfo> result = new ArrayList<ClazzInfo>();
        for (String ifname : interfaceNames) {
            result.add(loadClazzInfo(ifname));
        }
        return result;
    }
    
    public Set<String> getCatchNames() {
        return catchNames;
    }
    
    public void setCatchNames(Set<String> catchNames) {
        this.catchNames.clear();
        this.catchNames.addAll(catchNames);
    }

    public List<ClazzInfo> getCatches() {
        List<ClazzInfo> result = new ArrayList<ClazzInfo>();
        for (String n : catchNames) {
            result.add(loadClazzInfo(n));
        }
        return result;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public MethodInfo getMethod(String name, String desc) {
        for (MethodInfo m : methods) {
            if (m.getName().equals(name) && m.getDesc().equals(desc)) {
                return m;
            }
        }
        return null;
    }

    public List<MethodInfo> getMethods() {
        return Collections.unmodifiableList(methods);
    }
    
    public void addClassDependency(String className, boolean weak) {
        if (!dependencies.containsKey(className)) {
            Clazz clazz = this.clazz.clazzes.load(className);
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
            Clazz clazz = this.clazz.clazzes.load(owner);
            String path = clazz != null ? clazz.getPath().getFile().getAbsolutePath() : null;
            boolean inBootClasspath = clazz != null ? clazz.isInBootClasspath() : false;
            dependencies.put(key, new InvokeMethodDependency(owner, name, desc, path, inBootClasspath, weak));
        }
    }

    public void addSuperMethodDependency(String owner, String name, String desc, boolean weak) {
        String key = "Super." + owner + "." + name + desc;
        if (!dependencies.containsKey(key)) {
            Clazz clazz = this.clazz.clazzes.load(owner);
            String path = clazz != null ? clazz.getPath().getFile().getAbsolutePath() : null;
            boolean inBootClasspath = clazz != null ? clazz.isInBootClasspath() : false;
            dependencies.put(key, new SuperMethodDependency(owner, name, desc, path, inBootClasspath, weak));
        }
    }
    
    public void clearDependencies() {
        dependencies = new HashMap<String, Dependency>();
    }

    public Set<Dependency> getDependencies() {
        return new HashSet<Dependency>(dependencies.values());
    }

    public Set<Dependency> getAllDependencies() {
        Set<Dependency> result = new HashSet<>(dependencies.values());
        for (MethodInfo mi : methods) {
            result.addAll(mi.getDependencies());
        }
        return result;
    }

    public Set<String> getCheckcasts() {
        return checkcasts;
    }

    public void addCheckcast(String className) {
        checkcasts.add(className);
    }

    public Set<String> getInstanceofs() {
        return instanceofs;
    }

    public void addInstanceof(String className) {
        instanceofs.add(className);
    }

    public Set<String> getInvokes() {
        return invokes;
    }

    public void addInvoke(String className) {
        invokes.add(className);
    }

    public boolean isPublic() {
        return (modifiers & Modifier.PUBLIC) > 0;
    }
    
    public boolean isFinal() {
        return (modifiers & Modifier.FINAL) > 0;
    }
    
    public boolean isInterface() {
        return (modifiers & Modifier.INTERFACE) > 0;
    }
    
    public boolean isAbstract() {
        return (modifiers & Modifier.ABSTRACT) > 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        result = prime * result + ((internalName == null) ? 0 : internalName.hashCode());
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
        ClazzInfo other = (ClazzInfo) obj;
        if (clazz == null) {
            if (other.clazz != null) {
                return false;
            }
        } else if (!clazz.equals(other.clazz)) {
            return false;
        }
        if (internalName == null) {
            if (other.internalName != null) {
                return false;
            }
        } else if (!internalName.equals(other.internalName)) {
            return false;
        }
        return true;
    }
}
