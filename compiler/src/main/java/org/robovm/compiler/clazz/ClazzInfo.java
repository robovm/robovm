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

import org.robovm.compiler.Types;
import org.robovm.compiler.trampoline.Trampoline;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class ClazzInfo implements Serializable {
    private static final long serialVersionUID = 47L;
    
    private int modifiers;
    private String name;
    private String internalName;
    private String superclassName;
    private final List<String> interfaceNames = new ArrayList<String>();
    private final Set<Trampoline> trampolines = new HashSet<Trampoline>();
    private final List<FieldInfo> fields = new ArrayList<FieldInfo>();
    private final List<MethodInfo> methods = new ArrayList<MethodInfo>();
    private final Set<String> catchNames = new HashSet<String>();
    private Map<String, Dependency> dependencies = new HashMap<String,Dependency>();
    
    private transient Clazz clazz;
    private transient List<FieldInfo> classFields;
    private transient List<FieldInfo> instanceFields;
    
    ClazzInfo() {}
    
    ClazzInfo(Clazz clazz, SootClass sootClass) {
        this.clazz = clazz;
        modifiers = sootClass.getModifiers();
        name = sootClass.getName();
        internalName = Types.getInternalName(sootClass);
        if (sootClass.hasSuperclass()) {
            superclassName = Types.getInternalName(sootClass.getSuperclass());
        }
        for (SootClass ifs : sootClass.getInterfaces()) {
            interfaceNames.add(Types.getInternalName(ifs));
        }
        for (SootField field : sootClass.getFields()) {
            fields.add(new FieldInfo(field.getModifiers(), field.getName(), Types.getDescriptor(field)));
        }
        for (SootMethod method : sootClass.getMethods()) {
            methods.add(new MethodInfo(method.getModifiers(), method.getName(), Types.getDescriptor(method)));
        }
    }
    
    private ClazzInfo(String internalName) {
        this.internalName = internalName;
        this.name = internalName.replace('/', '.');
    }
    
    public boolean isPhantom() {
        return clazz == null;
    }
    
    void setClazz(Clazz clazz) {
        this.clazz = clazz;
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
                return c.getClazzInfo();
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
    
    public Set<Trampoline> getTrampolines() {
        return Collections.unmodifiableSet(trampolines);
    }
    
    public void setTrampolines(Set<Trampoline> trampolines) {
        this.trampolines.clear();
        this.trampolines.addAll(trampolines);
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
    
    public FieldInfo getField(String name, String desc) {
        for (FieldInfo f : fields) {
            if (f.getName().equals(name) && f.getDesc().equals(desc)) {
                return f;
            }
        }
        return null;
    }
    
    public List<FieldInfo> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void clearFields() {
        this.fields.clear();
    }

    public void addField(int modifiers, String name, String desc) {
        this.fields.add(new FieldInfo(modifiers, name, desc));
    }
    
    public List<FieldInfo> getClassFields() {
        if (classFields == null) {
            classFields = new ArrayList<FieldInfo>();
            for (FieldInfo f : fields) {
                if (f.isStatic()) {
                    classFields.add(f);
                }
            }
        }
        return classFields;
    }
    
    public List<FieldInfo> getInstanceFields() {
        if (instanceFields == null) {
            instanceFields = new ArrayList<FieldInfo>();
            for (FieldInfo f : fields) {
                if (!f.isStatic()) {
                    instanceFields.add(f);
                }
            }
        }
        return instanceFields;
    }
    
    public MethodInfo getMethod(String name, String desc) {
        for (MethodInfo m : methods) {
            if (m.getName().equals(name) && m.getDesc().equals(desc)) {
                return m;
            }
        }
        return null;
    }

    public List<MethodInfo> getMethods(String name) {
        List<MethodInfo> result = new ArrayList<MethodInfo>();
        for (MethodInfo m : methods) {
            if (m.getName().equals(name)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<MethodInfo> getMethods() {
        return Collections.unmodifiableList(methods);
    }
    
    public void clearMethods() {
        this.methods.clear();
    }
    
    public void addMethod(int flags, String name, String desc) {
        this.methods.add(new MethodInfo(flags, name, desc));
    }
    
    public void addDependency(String className) {
        if (!dependencies.containsKey(className)) {
            Clazz clazz = this.clazz.clazzes.load(className);
            String path = null;
            boolean inBootClasspath = false;
            if (clazz != null) {
                path = clazz.getPath().getFile().getAbsolutePath();
                inBootClasspath = clazz.isInBootClasspath();
            }
            dependencies.put(className, new Dependency(className, path, inBootClasspath));
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
        return new HashSet<Dependency>(dependencies.values());
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
    
    public class FieldInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private int modifiers;
        private String name;
        private String desc;
        
        private FieldInfo(int modifiers, String name, String desc) {
            this.modifiers = modifiers;
            this.name = name;
            this.desc = desc;
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
        
        public boolean isVolatile() {
            return (modifiers & Modifier.VOLATILE) > 0;
        }
    }
    
    public class MethodInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private int modifiers;
        private String name;
        private String desc;

        private MethodInfo(int modifiers, String name, String desc) {
            this.modifiers = modifiers;
            this.name = name;
            this.desc = desc;
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
    }
}
