/*
 * Copyright (C) 2014 RoboVM AB
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

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Serializable;

import org.junit.BeforeClass;
import org.junit.Test;

import soot.Scene;
import soot.options.Options;

/**
 * Tests {@link SClass}.
 */
public class SClassTest {

    @BeforeClass
    public static void initializeSoot() throws IOException {
        soot.G.reset();
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_include_all(true);
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(System.getProperty("sun.boot.class.path") + 
                ":" + System.getProperty("java.class.path"));
        Scene.v().loadNecessaryClasses();
        
        SClass.setLookup(new SootSClassLookup());
    }
    
    @Test
    public void testIsAssignableFrom() {
        assertEquals(int.class.isAssignableFrom(int.class), 
                SClass.intClass.isAssignableFrom(SClass.intClass));
        assertEquals(int.class.isAssignableFrom(long.class), 
                SClass.intClass.isAssignableFrom(SClass.longClass));
        assertEquals(int[].class.isAssignableFrom(int[].class), 
                SClass.lookup("[I").isAssignableFrom(SClass.lookup("[I")));
        assertEquals(Serializable.class.isAssignableFrom(int[].class), 
                SClass.SerializableClass.isAssignableFrom(SClass.lookup("[I")));
        assertEquals(Cloneable.class.isAssignableFrom(int[].class), 
                SClass.CloneableClass.isAssignableFrom(SClass.lookup("[I")));
        assertEquals(Object.class.isAssignableFrom(String.class), 
                SClass.ObjectClass.isAssignableFrom(SClass.lookup("Ljava/lang/String;")));
        assertEquals(Object[].class.isAssignableFrom(String[].class), 
                SClass.lookup("[Ljava/lang/Object;").isAssignableFrom(SClass.lookup("[Ljava/lang/String;")));
        assertEquals(Object[].class.isAssignableFrom(String[][].class), 
                SClass.lookup("[Ljava/lang/Object;").isAssignableFrom(SClass.lookup("[[Ljava/lang/String;")));
        assertEquals(Serializable[].class.isAssignableFrom(String[].class), 
                SClass.lookup("[Ljava/io/Serializable;").isAssignableFrom(SClass.lookup("[Ljava/lang/String;")));
        assertEquals(CharSequence.class.isAssignableFrom(String.class), 
                SClass.lookup("Ljava/lang/CharSequence;").isAssignableFrom(SClass.lookup("Ljava/lang/String;")));
        assertEquals(Serializable.class.isAssignableFrom(Integer.class), 
                SClass.SerializableClass.isAssignableFrom(SClass.IntegerClass));
    }

}
