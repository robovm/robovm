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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
 * 
 */
public class VTable {
    private Entry[] entries;

    private VTable(SootClass clazz, VTable parent) {
        LinkedList<Entry> entries = new LinkedList<Entry>();
        for (SootMethod method : clazz.getMethods()) {
            if (!method.isStatic() && !method.isPrivate()) {
                if (!"<init>".equals(method.getName())) {
                    entries.add(new Entry(entries.size(), method));
                }
            }
        }
        this.entries = merge(entries, parent);
    }

    private static Entry[] merge(LinkedList<Entry> childEntries, VTable parent) {
        if (parent == null) {
            return childEntries.toArray(new Entry[childEntries.size()]);
        }
        ArrayList<Entry> result = new ArrayList<Entry>();
        outer: for (Entry entryParent : parent.entries) {
            for (Iterator<Entry> it = childEntries.iterator(); it.hasNext();) {
                Entry entryChild = it.next();
                if (entryChild.overrides(entryParent)) {
                    entryChild.index = entryParent.index;
                    result.add(entryChild);
                    it.remove();
                    continue outer;
                }
            }
            // No overriding entry found. Use the parent one.
            result.add(entryParent);
        }
        
        // result now contains entries from the parent with overrides. Left in childEntries are
        // those which don't override anything in the parent. Just append to the back and make sure
        // the indexes are correct.
        for (Entry entry : childEntries) {
            entry.index = result.size();
            result.add(entry);
        }
        
        return result.toArray(new Entry[result.size()]);
    }
    
    public StructureConstant getStruct() {
        ArrayConstantBuilder table = new ArrayConstantBuilder(I8_PTR);
        for (VTable.Entry entry : entries) {
            if (Modifier.isAbstract(entry.getModifiers())) {
                table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
            } else {
                table.add(new ConstantBitcast(entry.getFunctionRef(), I8_PTR));
            }
        }
        return new StructureConstantBuilder()
                    .add(new IntegerConstant((short) entries.length))
                    .add(table.build())
                    .build();
    }
    
    public Entry[] getEntries() {
        return Arrays.copyOf(entries, entries.length);
    }
    
    public int size() {
        return entries.length;
    }
    
    Entry findEntry(String name, String desc) {
        return findEntry(null, name, desc);
    }

    Entry findEntry(String paket, String name, String desc) {
        for (Entry entry : entries) {
            if ((!isPackagePrivate(entry.modifiers) || entry.paket.equals(paket))
                    && entry.name.equals(name) && entry.desc.equals(desc)) {
                return entry;
            }
        }
        return null;
    }

    public Entry getEntry(SootMethod method) {
        return findEntry(method.getDeclaringClass().getPackageName(), method.getName(), Types.getDescriptor(method));
    }

    private static boolean isPackagePrivate(int modifiers) {
        return (modifiers & (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED)) == 0;
    }

    public static class Cache {
        Map<String, VTable> cache = new HashMap<String, VTable>();
        public VTable get(SootClass clazz) {
            if (clazz.isInterface()) {
                throw new IllegalArgumentException("Expected a class got an interface: " + clazz.getName());
            }
            VTable vtable = cache.get(clazz.getName());
            if (vtable != null) {
                return vtable;
            }
            VTable parent = null;
            if (clazz.hasSuperclass()) {
                parent = get(clazz.getSuperclass());
            }
            vtable = new VTable(clazz, parent);
            cache.put(clazz.getName(), vtable);
            return vtable;
        }
    }
    
    public static class Entry {
        private int index;
        private final int modifiers;
        private final String declaringClass;
        private final String paket;
        private final String name;
        private final String desc;
        
        Entry(int index, SootMethod method) {
            this(index, method.getModifiers(), method.getDeclaringClass().getName(), 
                    method.getName(), Types.getDescriptor(method));
        }
        Entry(int index, int modifiers, String declaringClass, String name, String desc) {
            this.index = index;
            this.modifiers = modifiers;
            this.declaringClass = declaringClass;
            this.name = name;
            this.desc = desc;
            int packEndIdx = declaringClass.lastIndexOf('.');
            paket = packEndIdx == -1 ? "" : declaringClass.substring(0, packEndIdx);
        }
        
        public FunctionRef getFunctionRef() {
            if (Modifier.isAbstract(modifiers)) {
                return null;
            }
            String functionName = mangleMethod(declaringClass.replace('.', '/'), name, desc);
            if (Modifier.isSynchronized(modifiers)) {
                functionName += "_synchronized";
            }
            return new FunctionRef(functionName, getFunctionType(desc, Modifier.isStatic(modifiers)));
        }
        
        /**
         * Returns <code>true</code> if this {@link Entry} overrides the specified entry.
         * <code>false</code> otherwise.
         */
        public boolean overrides(Entry other) {
            if (other.name.equals(name) && other.desc.equals(desc)) {
                // Could be. If the other entry is package private this one has to be in the same
                // package.
                if (isPackagePrivate(other.modifiers)) {
                    return other.paket.equals(paket);
                }
                return true;
            }
            return false;
        }
        
        public int getIndex() {
            return index;
        }
        
        public int getModifiers() {
            return modifiers;
        }
        
        public String getPackage() {
            return paket;
        }
        
        public String getDeclaringClass() {
            return declaringClass;
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
                    .append(", declaringClass=").append(declaringClass)
                    .append(", name=").append(name).append(", desc=")
                    .append(desc).append("]");
            return builder.toString();
        }
    }
}
