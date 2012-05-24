/**
 * 
 */
package org.robovm.compiler.clazz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.robovm.compiler.trampoline.Trampoline;

import soot.Modifier;

/**
 * @author niklas
 *
 */
public class ClazzInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int modifiers;
    private String name;
    private String superclass;
    private final List<String> interfaces = new ArrayList<String>();
    private final Set<String> attributeDependencies = new HashSet<String>();
    private final Set<Trampoline> trampolines = new HashSet<Trampoline>();
    private final Set<String> structDependencies = new HashSet<String>();
    private final List<FieldInfo> fields = new ArrayList<FieldInfo>();
    private final List<MethodInfo> methods = new ArrayList<MethodInfo>();
    private boolean struct;
    private boolean hasAttributes;
    
    private transient List<FieldInfo> classFields;
    private transient List<FieldInfo> instanceFields;
    
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
    
    public String getSuperclass() {
        return superclass;
    }
    
    public void setSuperclass(String superclass) {
        this.superclass = superclass;
    }
    
    public boolean isStruct() {
        return struct;
    }
    
    public void setStruct(boolean struct) {
        this.struct = struct;
    }
    
    public boolean hasAttributes() {
        return hasAttributes;
    }

    public void setHasAttributes(boolean b) {
        this.hasAttributes = b;
    }
    
    public List<String> getInterfaces() {
        return Collections.unmodifiableList(interfaces);
    }
    
    public void setInterfaces(List<String> interfaces) {
        this.interfaces.clear();
        this.interfaces.addAll(interfaces);
    }
    
    public Set<String> getAttributeDependencies() {
        return Collections.unmodifiableSet(attributeDependencies);
    }

    public void setAttributeDependencies(Set<String> attributeDependencies) {
        this.attributeDependencies.clear();
        this.attributeDependencies.addAll(attributeDependencies);
    }
    
    public Set<String> getStructDependencies() {
        return Collections.unmodifiableSet(structDependencies);
    }
    
    public void setStructDependencies(Set<String> structDependencies) {
        this.structDependencies.clear();
        this.structDependencies.addAll(structDependencies);
    }
    
    public boolean hasStructDependencies() {
        return !structDependencies.isEmpty();
    }
    
    public Set<Trampoline> getTrampolines() {
        return Collections.unmodifiableSet(trampolines);
    }
    
    public void setTrampolines(Set<Trampoline> trampolines) {
        this.trampolines.clear();
        this.trampolines.addAll(trampolines);
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

    public void setFields(List<FieldInfo> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
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
    
    public void setMethods(List<MethodInfo> methods) {
        this.methods.clear();
        this.methods.addAll(methods);
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
    
    public static class FieldInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private int modifiers;
        private String name;
        private String desc;
        private boolean hasAttributes;
        
        public FieldInfo(int modifiers, String name, String desc) {
            this.modifiers = modifiers;
            this.name = name;
            this.desc = desc;
        }
        
        public int getModifiers() {
            return modifiers;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDesc() {
            return desc;
        }
        
        public boolean hasAttributes() {
            return hasAttributes;
        }

        public void setHasAttributes(boolean b) {
            this.hasAttributes = b;
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
    
    public static class MethodInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private int modifiers;
        private String name;
        private String desc;
        private boolean hasAttributes;
        private boolean bridge;
        private boolean callback;
        
        public MethodInfo(int modifiers, String name, String desc) {
            this.modifiers = modifiers;
            this.name = name;
            this.desc = desc;
        }
        
        public int getModifiers() {
            return modifiers;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDesc() {
            return desc;
        }
        
        public boolean hasAttributes() {
            return hasAttributes;
        }

        public void setHasAttributes(boolean b) {
            this.hasAttributes = b;
        }
        
        public boolean isBridge() {
            return bridge;
        }
        
        public void setBridge(boolean bridge) {
            this.bridge = bridge;
        }
        
        public boolean isCallback() {
            return callback;
        }
        
        public void setCallback(boolean callback) {
            this.callback = callback;
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
