/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.compiler;

import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.StructureConstant;
import org.robovm.compiler.llvm.StructureConstantBuilder;

import soot.SootClass;
import soot.SootMethod;

/**
 * Creates an interface table for a specific interface class.
 */
public class ITable {
    private Entry[] entries;

    private ITable(SootClass clazz) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (SootMethod method : clazz.getMethods()) {
            if (!method.isStatic()) {
                // Interface methods may have a <clinit> method.
                entries.add(new Entry(entries.size(), method));
            }
        }
        this.entries = entries.toArray(new Entry[entries.size()]);
    }

    public int size() {
        return entries.length;
    }
    
    Entry findEntry(String name, String desc) {
        for (Entry entry : entries) {
            if (entry.name.equals(name) && entry.desc.equals(desc)) {
                return entry;
            }
        }
        return null;
    }

    public Entry getEntry(SootMethod method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        return findEntry(method.getName(), Types.getDescriptor(method));
    }
    
    public StructureConstant getStruct() {
        ArrayConstantBuilder table = new ArrayConstantBuilder(I8_PTR);
        for (int i = 0; i < entries.length; i++) {
            table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
        }
        return new StructureConstantBuilder()
                    .add(new IntegerConstant((short) entries.length))
                    .add(table.build())
                    .build();
    }
    
    public StructureConstant getStruct(SootClass clazz) {
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("Expected a class got an interface: " + clazz.getName());
        }
        ArrayConstantBuilder table = new ArrayConstantBuilder(I8_PTR);
        for (Entry entry : entries) {
            ResolvedEntry resolvedEntry = entry.resolve(clazz);
            if (resolvedEntry == null || Modifier.isAbstract(resolvedEntry.getModifiers())) {
                table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
            } else if (!Modifier.isPublic(resolvedEntry.getModifiers())) {
                table.add(new ConstantBitcast(BC_NON_PUBLIC_METHOD_CALLED, I8_PTR));
            } else {
                table.add(new ConstantBitcast(resolvedEntry.getFunctionRef(), I8_PTR));
            }
        }
        return new StructureConstantBuilder()
                    .add(new IntegerConstant((short) entries.length))
                    .add(table.build())
                    .build();
    }
    
    public static class Cache {
        Map<String, ITable> cache = new HashMap<String, ITable>();
        public ITable get(SootClass clazz) {
            if (!clazz.isInterface()) {
                throw new IllegalArgumentException("Not an interface: " + clazz.getName());
            }
            ITable itable = cache.get(clazz.getName());
            if (itable != null) {
                return itable;
            }
            itable = new ITable(clazz);
            cache.put(clazz.getName(), itable);
            return itable;
        }
    }
    
    public static class Entry {
        protected int index;
        protected final String name;
        protected final String desc;
        
        Entry(int index, SootMethod method) {
            this(index, method.getName(), Types.getDescriptor(method));
        }
        Entry(int index, String name, String desc) {
            this.index = index;
            this.name = name;
            this.desc = desc;
        }
        
        public ResolvedEntry resolve(SootClass clazz) {
            while (clazz != null) {
                for (SootMethod m : clazz.getMethods()) {
                    if (m.getName().equals(name) && desc.equals(Types.getDescriptor(m))) {
                        return new ResolvedEntry(this, m);
                    }
                }
                clazz = clazz.hasSuperclass() ? clazz.getSuperclass() : null;
            }
            return null;
        }
        
        public int getIndex() {
            return index;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDesc() {
            return desc;
        }
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Entry [index=").append(index)
                    .append(", name=").append(name).append(", desc=")
                    .append(desc).append("]");
            return builder.toString();
        }
    }
    
    public static class ResolvedEntry extends Entry {
        private final int modifiers;
        private final String declaringClass;
        
        public ResolvedEntry(Entry entry, SootMethod method) {
            super(entry.index, entry.name, entry.desc);
            this.modifiers = method.getModifiers();
            this.declaringClass = method.getDeclaringClass().getName();
        }
        
        public int getModifiers() {
            return modifiers;
        }
        
        public String getDeclaringClass() {
            return declaringClass;
        }
        
        public FunctionRef getFunctionRef() {
            if (Modifier.isAbstract(modifiers) || !Modifier.isPublic(modifiers)) {
                return null;
            }
            String functionName = mangleMethod(declaringClass.replace('.', '/'), name, desc);
            if (Modifier.isSynchronized(modifiers)) {
                functionName += "_synchronized";
            }
            return new FunctionRef(functionName, getFunctionType(desc, Modifier.isStatic(modifiers)));
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ResolvedEntry [modifiers=").append(modifiers)
                    .append(", declaringClass=").append(declaringClass)
                    .append(", index=").append(index).append(", name=")
                    .append(name).append(", desc=").append(desc).append("]");
            return builder.toString();
        }
    }

}
