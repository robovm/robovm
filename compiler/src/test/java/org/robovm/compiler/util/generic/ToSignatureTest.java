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
package org.robovm.compiler.util.generic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Comparator;

import org.junit.BeforeClass;
import org.junit.Test;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.options.Options;

/**
 * Tests {@link Type#toSignature()} for the various implementations of the
 * {@link Type} interface. 
 */
public class ToSignatureTest {

    public static abstract class A<U, V> {
    }
    
    public static abstract class B<T> extends A<T, String> 
        implements Comparable<T>, Comparator<Integer> {
        class Inner<U> {}
    }

    public static abstract class Methods {
        abstract void m1(Object o, String s, A<Number, ?> a1, A<Number, ? extends Comparable<String>> a2, B<Integer>.Inner<String> b);
        abstract void m2(B<String> i);
    }
    
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
    }

    private SootClass toSootClass(Class<?> cls) {
        return SootResolver.v().resolveClass(cls.getName(), SootClass.SIGNATURES);
    }

    private Type methodParamType(String name, int paramIdx) {
        SootMethod m = toSootClass(Methods.class).getMethodByName(name);
        SootMethodType mType = new SootMethodType(m);
        return mType.getGenericParameterTypes()[paramIdx];
    }

    @Test
    public void testMethodParamSignature() {
        Type type = null; 
        type = methodParamType("m1", 0);
        assertEquals("Ljava/lang/Object;", type.toGenericSignature());
        type = methodParamType("m1", 1);
        assertEquals("Ljava/lang/String;", type.toGenericSignature());
        type = methodParamType("m1", 2);
        assertEquals("Lorg/robovm/compiler/util/generic/ToSignatureTest$A<Ljava/lang/Number;*>;", 
                type.toGenericSignature());
        type = methodParamType("m1", 3);
        assertEquals("Lorg/robovm/compiler/util/generic/ToSignatureTest$A<Ljava/lang/Number;+Ljava/lang/Comparable<Ljava/lang/String;>;>;", 
                type.toGenericSignature());
        type = methodParamType("m1", 4);
        assertEquals("Lorg/robovm/compiler/util/generic/ToSignatureTest$B<Ljava/lang/Integer;>.Inner<Ljava/lang/String;>;", 
                type.toGenericSignature());
    }

}
