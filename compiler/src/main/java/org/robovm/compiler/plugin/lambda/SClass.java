/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.plugin.lambda;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 */
public abstract class SClass<T> {

    private static final Map<String, SClass<?>> BUILTINS = new HashMap<String, SClass<?>>();

    private static class BuiltinClass extends SClass {
        private final boolean primitive;
        private final boolean interfaze;
        private final String descriptor;

        BuiltinClass(boolean primitive, boolean interfaze, String descriptor) {
            this.primitive = primitive;
            this.interfaze = interfaze;
            this.descriptor = descriptor;
            BUILTINS.put(descriptor, this);
        }

        @Override
        public boolean isPrimitive() {
            return primitive;
        }

        @Override
        public boolean isInterface() {
            return interfaze;
        }

        @Override
        public boolean isArray() {
            return false;
        }

        @Override
        public SClass getComponentType() {
            return null;
        }
        
        @Override
        public SClass getSuperclass() {
            if (isPrimitive() || isInterface()) {
                return null;
            }
            return SClass.lookup0(descriptor).getSuperclass();
        }
        
        @Override
        public SClass[] getInterfaces() {
            return SClass.lookup0(descriptor).getInterfaces();
        }
        
        @Override
        public String getDescriptor() {
            return descriptor;
        }        
    }
    
    public static SClass<?> booleanClass = new BuiltinClass(true, false, "Z");
    public static SClass<?> BooleanClass = new BuiltinClass(false, false, "Ljava/lang/Boolean;");
    public static SClass<?> byteClass = new BuiltinClass(true, false, "B");
    public static SClass<?> ByteClass  = new BuiltinClass(false, false, "Ljava/lang/Byte;");
    public static SClass<?> shortClass = new BuiltinClass(true, false, "S");
    public static SClass<?> ShortClass = new BuiltinClass(false, false, "Ljava/lang/Short;");
    public static SClass<?> charClass = new BuiltinClass(true, false, "C");
    public static SClass<?> CharacterClass = new BuiltinClass(false, false, "Ljava/lang/Character;");
    public static SClass<?> intClass = new BuiltinClass(true, false, "I");
    public static SClass<?> IntegerClass = new BuiltinClass(false, false, "Ljava/lang/Integer;");
    public static SClass<?> longClass = new BuiltinClass(true, false, "J");
    public static SClass<?> LongClass = new BuiltinClass(false, false, "Ljava/lang/Long;");
    public static SClass<?> floatClass = new BuiltinClass(true, false, "F");
    public static SClass<?> FloatClass = new BuiltinClass(false, false, "Ljava/lang/Float;");
    public static SClass<?> doubleClass = new BuiltinClass(true, false, "D");
    public static SClass<?> DoubleClass = new BuiltinClass(false, false, "Ljava/lang/Double;");
    public static SClass<?> voidClass = new BuiltinClass(true, false, "V");
    public static SClass<?> VoidClass = new BuiltinClass(false, false, "Ljava/lang/Void;");

    public static SClass<?> ObjectClass = new BuiltinClass(false, false, "Ljava/lang/Object;");
    public static SClass<?> SerializableClass = new BuiltinClass(false, true, "Ljava/io/Serializable;");
    public static SClass<?> CloneableClass = new BuiltinClass(false, true, "Ljava/lang/Cloneable;");

    private static ThreadLocal<SClassLookup> lookupThreadLocal = new ThreadLocal<>();

    public static SClassLookup getLookup() {
        return lookupThreadLocal.get();
    }

    public static void setLookup(SClassLookup lookup) {
        lookupThreadLocal.set(lookup);
    }

    public static SClass<?> lookup(String descriptor) {
        SClass<?> cls = BUILTINS.get(descriptor);
        if (cls != null) {
            return cls;
        }
        return lookupThreadLocal.get().lookup(descriptor);
    }

    private static SClass<?> lookup0(String descriptor) {
        return lookupThreadLocal.get().lookup(descriptor);
    }

    public String getName() {
        if (isPrimitive()) {
            switch (getDescriptor().charAt(0)) {
            case 'Z': return "boolean";
            case 'B': return "byte";
            case 'S': return "short";
            case 'C': return "char";
            case 'I': return "int";
            case 'J': return "long";
            case 'F': return "float";
            case 'D': return "double";
            default: return "void";
            }
        } else if (isArray()) {
            return getDescriptor();
        } else {
            String d = getDescriptor();
            d = d.substring(1, d.length() - 1);
            return d.replace('/', '.');
        }
    }

    public abstract boolean isPrimitive();
    
    public abstract boolean isInterface();

    public abstract boolean isArray();

    public abstract SClass<?> getComponentType();

    public abstract SClass<?> getSuperclass();

    public abstract SClass<?>[] getInterfaces();

    public final boolean isAssignableFrom(SClass<?> that) {
        if (this == that) {
            return true;
        }

        String thisDescriptor = this.getDescriptor();
        String thatDescriptor = that.getDescriptor();

        if (thisDescriptor.equals(thatDescriptor)) {
            return true;
        }
        
        if (this.isPrimitive() || that.isPrimitive()) {
            return false;
        }

        if (ObjectClass.getDescriptor().equals(thisDescriptor)) {
            return true;
        }
        
        if (this.isArray()) {
            if (!that.isArray()) {
                return false;
            }
            return this.getComponentType().isAssignableFrom(that.getComponentType());
        }
        
        if (this.isInterface()) {
            return that.implementsInterface(this);
        }
        
        SClass<?> superclass = this.getSuperclass();
        while (superclass != null) {
            if (superclass.getDescriptor().equals(thatDescriptor)) {
                return true;
            }
            superclass = this.getSuperclass();
        }
        
        return false;
    }

    private boolean implementsInterface(SClass<?> interfaze) {
        if (this.equals(interfaze)) {
            return true;
        }
        for (SClass<?> ifs : this.getInterfaces()) {
            if (ifs.implementsInterface(interfaze)) {
                return true;
            }
        }
        SClass<?> superclass = getSuperclass();
        return superclass != null ? superclass.implementsInterface(interfaze) : false;
    }
    
    public String getSimpleName() {
        if (isPrimitive()) {
            return getName();
        }
        
        String name = getName();
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            return name.substring(dot + 1);
        }

        return name;
    }

    public abstract String getDescriptor();

    @Override
    public int hashCode() {
        return getDescriptor().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SClass)) {
            return false;
        }
        SClass that = (SClass) obj;
        return getDescriptor().equals(that.getDescriptor());
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
