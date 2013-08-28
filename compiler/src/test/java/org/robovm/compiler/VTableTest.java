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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.robovm.compiler.VTable.Entry;

import soot.Scene;
import soot.SootClass;
import soot.options.Options;

/**
 * Tests {@link VTable}.
 */
public class VTableTest {

    @Before
    public void initializeSoot() {
        soot.G.reset();
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_include_all(true);
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(System.getProperty("sun.boot.class.path") + 
                ":" + System.getProperty("java.class.path"));
        Scene.v().loadNecessaryClasses();
    }
    
    private SootClass getSootClass(String name) {
        return Scene.v().loadClassAndSupport(name);
    }
    
    @Test
    public void testObject() {
        SootClass sc = getSootClass("java.lang.Object");
        VTable.Cache cache = new VTable.Cache();
        VTable vtable = cache.get(sc);
        assertEquals(11, vtable.size());
        assertNotNull(vtable.findEntry("toString", "()Ljava/lang/String;"));
    }

    @Test
    public void testA() {
        SootClass sc = getSootClass("org.robovm.compiler.a.A");
        VTable.Cache cache = new VTable.Cache();
        VTable vtable = cache.get(sc);
        assertEquals(14, vtable.size());
        
        Entry toStringEntry = vtable.findEntry("toString", "()Ljava/lang/String;");
        assertEquals(sc.getName(), toStringEntry.getDeclaringClass());
        Entry superToStringEntry = cache.get(getSootClass("java.lang.Object")).findEntry("toString", "()Ljava/lang/String;");
        assertEquals(superToStringEntry.getIndex(), toStringEntry.getIndex());
        assertNotSame(toStringEntry, superToStringEntry);

        Entry equalsEntry = vtable.findEntry("equals", "(Ljava/lang/Object;)Z");
        assertEquals(sc.getName(), equalsEntry.getDeclaringClass());
        Entry superEqualsEntry = cache.get(getSootClass("java.lang.Object")).findEntry("equals", "(Ljava/lang/Object;)Z");
        assertEquals(superEqualsEntry.getIndex(), equalsEntry.getIndex());
        assertNotSame(superEqualsEntry, equalsEntry);
        
        Entry cloneEntry = vtable.findEntry("clone", "()Ljava/lang/Object;");
        assertEquals("java.lang.Object", cloneEntry.getDeclaringClass());
        Entry superCloneEntry = cache.get(getSootClass("java.lang.Object")).findEntry("clone", "()Ljava/lang/Object;");
        assertSame(superCloneEntry, cloneEntry);
        
        assertNull(vtable.findEntry("foo", "()V"));
        
        Entry fooEntry = vtable.findEntry("org.robovm.compiler.a", "foo", "()V");
        assertEquals(sc.getName(), fooEntry.getDeclaringClass());
        assertEquals(11, fooEntry.getIndex());
    }
    
    @Test
    public void testB() {
        SootClass scJLO = getSootClass("java.lang.Object");
        SootClass scA = getSootClass("org.robovm.compiler.a.A");
        SootClass scB = getSootClass("org.robovm.compiler.b.B");
        
        VTable.Cache cache = new VTable.Cache();
        VTable vtableJLO = cache.get(scJLO);
        VTable vtableA = cache.get(scA);
        VTable vtableB = cache.get(scB);
        assertEquals(16, vtableB.size());
        
        Entry toStringEntry = vtableB.findEntry("toString", "()Ljava/lang/String;");
        assertEquals(scA.getName(), toStringEntry.getDeclaringClass());
        Entry superToStringEntry = vtableA.findEntry("toString", "()Ljava/lang/String;");
        assertSame(toStringEntry, superToStringEntry);

        Entry equalsEntry = vtableB.findEntry("equals", "(Ljava/lang/Object;)Z");
        assertEquals(scA.getName(), equalsEntry.getDeclaringClass());
        Entry superEqualsEntry = vtableA.findEntry("equals", "(Ljava/lang/Object;)Z");
        assertSame(superEqualsEntry, equalsEntry);
        
        Entry cloneEntry = vtableB.findEntry("clone", "()Ljava/lang/Object;");
        assertEquals(scJLO.getName(), cloneEntry.getDeclaringClass());
        Entry superCloneEntry = vtableJLO.findEntry("clone", "()Ljava/lang/Object;");
        assertSame(superCloneEntry, cloneEntry);

        Entry fooInAEntry = vtableB.findEntry("org.robovm.compiler.a", "foo", "()V");
        assertEquals(scA.getName(), fooInAEntry.getDeclaringClass());
        assertEquals(11, fooInAEntry.getIndex());

        Entry fooInBEntry = vtableB.findEntry("org.robovm.compiler.b", "foo", "()V");
        assertEquals(scB.getName(), fooInBEntry.getDeclaringClass());
        assertEquals(14, fooInBEntry.getIndex());
        assertNotSame(fooInAEntry, fooInBEntry);
        
        Entry fooIVEntry = vtableB.findEntry("foo", "(I)V");
        assertEquals(scB.getName(), fooIVEntry.getDeclaringClass());
        Entry superFooIVEntry = vtableA.findEntry("foo", "(I)V");
        assertEquals(superFooIVEntry.getIndex(), fooIVEntry.getIndex());
        assertNotSame(superFooIVEntry, fooIVEntry);
        
        Entry barInAEntry = vtableB.findEntry("org.robovm.compiler.a", "bar", "()V");
        assertEquals(scA.getName(), barInAEntry.getDeclaringClass());
        assertEquals(12, barInAEntry.getIndex());

        Entry barInBEntry = vtableB.findEntry("org.robovm.compiler.b", "bar", "()V");
        assertEquals(scB.getName(), barInBEntry.getDeclaringClass());
        assertEquals(15, barInBEntry.getIndex());
        assertNotSame(barInAEntry, barInBEntry);
    }
    
    @Test
    public void testC() {
        SootClass scA = getSootClass("org.robovm.compiler.a.A");
        SootClass scC = getSootClass("org.robovm.compiler.a.C");
        
        VTable.Cache cache = new VTable.Cache();
        VTable vtableA = cache.get(scA);
        VTable vtableC = cache.get(scC);
        assertEquals(14, vtableC.size());
        
        Entry fooEntry = vtableC.findEntry("org.robovm.compiler.a", "foo", "()V");
        assertEquals(scC.getName(), fooEntry.getDeclaringClass());
        Entry superFooEntry = vtableA.findEntry("org.robovm.compiler.a", "foo", "()V");
        assertEquals(superFooEntry.getIndex(), fooEntry.getIndex());
        assertNotSame(superFooEntry, fooEntry);

        Entry barEntry = vtableC.findEntry("org.robovm.compiler.a", "bar", "()V");
        assertEquals(scA.getName(), barEntry.getDeclaringClass());
        Entry superBarEntry = vtableA.findEntry("org.robovm.compiler.a", "bar", "()V");
        assertSame(superBarEntry, barEntry);
    }
    
    @Test
    public void testD() {
        SootClass scA = getSootClass("org.robovm.compiler.a.A");
        SootClass scB = getSootClass("org.robovm.compiler.b.B");
        SootClass scD = getSootClass("org.robovm.compiler.b.D");
        
        VTable.Cache cache = new VTable.Cache();
        VTable vtableB = cache.get(scB);
        VTable vtableD = cache.get(scD);
        assertEquals(vtableB.size(), vtableD.size());
        
        Entry barInAEntry = vtableD.findEntry("org.robovm.compiler.a", "bar", "()V");
        assertEquals(scA.getName(), barInAEntry.getDeclaringClass());
        assertEquals(12, barInAEntry.getIndex());

        Entry barInDEntry = vtableD.findEntry("org.robovm.compiler.b", "bar", "()V");
        assertEquals(scD.getName(), barInDEntry.getDeclaringClass());
        assertEquals(15, barInDEntry.getIndex());
        assertNotSame(barInAEntry, barInDEntry);

        Entry barInBEntry = vtableD.findEntry("org.robovm.compiler.b", "bar", "()V");
        assertSame(barInBEntry, barInDEntry);
    }
    
    @Test
    public void testEmpty() {
        SootClass scJLO = getSootClass("java.lang.Object");
        SootClass scEmpty = getSootClass("org.robovm.compiler.a.Empty");
        
        VTable.Cache cache = new VTable.Cache();
        VTable vtableJLO = cache.get(scJLO);
        VTable vtableEmpty = cache.get(scEmpty);
        assertArrayEquals(vtableJLO.getEntries(), vtableEmpty.getEntries());
    }
}
