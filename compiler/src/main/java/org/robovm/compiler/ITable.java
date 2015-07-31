/*
 * Copyright (C) 2013 RoboVM AB
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
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.FunctionDeclaration;
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
            if (!method.isStatic() && method.isPublic()) {
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
        for (Entry entry : entries) {
            if (!Modifier.isAbstract(entry.getModifiers())) {
                table.add(new ConstantBitcast(entry.getFunctionRef(), I8_PTR));
            } else {
                table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
            }
        }
        return new StructureConstantBuilder()
                    .add(new IntegerConstant((short) entries.length))
                    .add(table.build())
                    .build();
    }
    
    public StructureConstant getStruct(ModuleBuilder mb, SootClass clazz) {
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("Expected a class got an interface: " + clazz.getName());
        }
        ArrayConstantBuilder table = new ArrayConstantBuilder(I8_PTR);
        for (Entry entry : entries) {
            ResolvedEntry resolvedEntry = entry.resolve(clazz);
            if (resolvedEntry == null) {
                FunctionRef defaultFunctionRef = entry.getFunctionRef();
                if (defaultFunctionRef != null) {
                    if (!mb.hasSymbol(defaultFunctionRef.getName())) {
                        mb.addFunctionDeclaration(new FunctionDeclaration(defaultFunctionRef));
                    }
                    table.add(new ConstantBitcast(defaultFunctionRef, I8_PTR));
                } else {
                    table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
                }
            } else if (Modifier.isAbstract(resolvedEntry.getModifiers())) {
                table.add(new ConstantBitcast(BC_ABSTRACT_METHOD_CALLED, I8_PTR));
            } else if (!Modifier.isPublic(resolvedEntry.getModifiers())) {
                table.add(new ConstantBitcast(BC_NON_PUBLIC_METHOD_CALLED, I8_PTR));
            } else {
                /*
                 * Found a non-abstract method implementation. Either on the
                 * class, in one of its super classes or a default method in an
                 * implemented interface.
                 */
                FunctionRef functionRef = resolvedEntry.getFunctionRef();
                if (!resolvedEntry.declaringClass.equals(clazz.getName())) {
                    if (!mb.hasSymbol(functionRef.getName())) {
                        mb.addFunctionDeclaration(new FunctionDeclaration(functionRef));
                    }
                }
                table.add(new ConstantBitcast(functionRef, I8_PTR));
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
        protected final int modifiers;
        protected final String declaringClass;
        protected final String name;
        protected final String desc;
        
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
        }

        /**
         * Finds the method represented by this {@link Entry} among the
         * immediate or inherited methods on the specified class or interface.
         */
        private ResolvedEntry resolve(final SootClass clazz) {
            SootClass c = clazz;
            if (!c.isInterface()) {
                /*
                 * This method calls itself recursively for interfaces after
                 * checking for an matching method in the interface. No need to
                 * check again.
                 */

                /*
                 * Find a match in the class and its super classes.
                 */
                while (c != null) {
                    ResolvedEntry e = resolveImmediate(c);
                    if (e != null) {
                        return e;
                    }
                    c = c.hasSuperclass() ? c.getSuperclass() : null;
                }
            }

            /*
             * No match found in the class hierarchy. Check for a match in
             * implemented interfaces.
             */
            c = clazz;
            while (c != null) {
                /*
                 * Search immediate interfaces first before descending into
                 * super interfaces. Depth first can potentially match an
                 * incorrect default method. See #1083.
                 */
                for (SootClass interfaze : c.getInterfaces()) {
                    ResolvedEntry e = resolveImmediate(interfaze);
                    if (e != null) {
                        return e;
                    }
                }
                /*
                 * Now recursively search through super interfaces.
                 */
                for (SootClass interfaze : c.getInterfaces()) {
                    ResolvedEntry e = resolve(interfaze);
                    if (e != null) {
                        return e;
                    }
                }
                c = c.hasSuperclass() ? c.getSuperclass() : null;
            }

            return null;
        }

        /**
         * Finds a method in the specified class or interface with the same
         * name and descriptor as the method represented by this {@link Entry}.
         */
        private ResolvedEntry resolveImmediate(SootClass clazz) {
            for (SootMethod m : clazz.getMethods()) {
                if (m.getName().equals(name) && desc.equals(Types.getDescriptor(m))) {
                    return new ResolvedEntry(this, m);
                }
            }
            return null;
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
            String owner = declaringClass.replace('.', '/');
            String functionName = Modifier.isSynchronized(modifiers) 
                    ? Symbols.synchronizedWrapperSymbol(owner, name, desc) 
                    : Symbols.methodSymbol(owner, name, desc);
            return new FunctionRef(functionName, getFunctionType(desc, Modifier.isStatic(modifiers)));
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
            builder.append("Entry [modifiers=").append(modifiers)
                    .append(", declaringClass=").append(declaringClass)
                    .append(", index=").append(index).append(", name=")
                    .append(name).append(", desc=").append(desc).append("]");
            return builder.toString();
        }
    }
    
    public static class ResolvedEntry extends Entry {
        public ResolvedEntry(Entry entry, SootMethod method) {
            super(entry.index, method);
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
