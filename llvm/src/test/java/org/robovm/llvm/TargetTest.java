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
package org.robovm.llvm;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Tests {@link Target}.
 */
public class TargetTest {

    @Test
    public void testGetTargets() throws Exception {
        List<Target> all = Target.getTargets();
        assertFalse(all.isEmpty());
    }

    @Test
    public void testGetTargetsMap() throws Exception {
        Map<String, Target> map = Target.getTargetsMap();
        assertFalse(map.isEmpty());
        Target arm = map.get("arm");
        assertNotNull(arm);
        Target thumb = map.get("thumb");
        assertNotNull(thumb);
        Target x86 = map.get("x86");
        assertNotNull(x86);
        Target x86_64 = map.get("x86-64");
        assertNotNull(x86_64);
    }

    @Test
    public void testGetTarget() throws Exception {
        Target arm = Target.getTarget("arm");
        assertNotNull(arm);
        assertEquals("arm", arm.getName());
        assertEquals("ARM", arm.getDescription());
        Target x86 = Target.getTarget("x86");
        assertNotNull(x86);
        assertEquals("x86", x86.getName());
        assertEquals("32-bit X86: Pentium-Pro and above", x86.getDescription());
        
        try {
            Target.getTarget("foobar");
            fail("LlvmException expected");
        } catch (LlvmException e) {}
    }
    
    @Test
    public void testLookupTarget() throws Exception {
        Target t = Target.lookupTarget("thumbv7-unknown-ios");
        assertNotNull(t);
        assertEquals("thumb", t.getName());
        assertEquals("Thumb", t.getDescription());
        
        try {
            Target.lookupTarget("foobar");
            fail("LlvmException expected");
        } catch (LlvmException e) {}
    }
    
    @Test
    public void testGetHostTarget() throws Exception {
        Target t = Target.getHostTarget();
        String archProp = System.getProperty("os.arch").toLowerCase();
        if (archProp.matches("amd64|x86[-_]64")) {
            assertEquals("x86-64", t.getName());
        } else if (archProp.matches("i386|x86")) {
            assertEquals("x86", t.getName());
        } else {
            fail("Unknown os.arch: " + archProp);
        }
    }
    
    @Test
    public void testCreateTargetMachine() throws Exception {
        Target t = Target.getTarget("thumb");
        TargetMachine tm = t.createTargetMachine("thumbv7-unknown-ios");
        assertNotNull(tm);
        assertEquals("thumbv7-unknown-ios", tm.getTriple());
        assertEquals(t, tm.getTarget());
    }
    
}
