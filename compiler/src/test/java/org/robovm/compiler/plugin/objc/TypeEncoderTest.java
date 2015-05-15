/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.compiler.plugin.objc;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Array;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

import soot.Scene;
import soot.SootClass;
import soot.SootResolver;
import soot.options.Options;

/**
 * Tests {@link TypeEncoder}.
 */
public class TypeEncoderTest {

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

    private SootClass toSootClass(Class<?> cls) {
        return SootResolver.v().resolveClass(cls.getName(), SootClass.SIGNATURES);
    }

    public static class IPoint extends Struct<IPoint> {
        @StructMember(0)
        public native int getX();

        @StructMember(0)
        public native void setX(int x);

        @StructMember(1)
        public native int getY();

        @StructMember(1)
        public native void setY(int y);
    }

    public static class FPoint extends Struct<FPoint> {
        @StructMember(0)
        public native @MachineSizedFloat double getX();

        @StructMember(0)
        public native void setX(@MachineSizedFloat double x);

        @StructMember(1)
        public native @MachineSizedFloat double getY();

        @StructMember(1)
        public native void setY(@MachineSizedFloat double y);
    }

    public static class FRect extends Struct<FRect> {
        @StructMember(0)
        public native @ByVal FPoint getP1();

        @StructMember(0)
        public native void setP1(@ByVal FPoint x);

        @StructMember(1)
        public native @ByVal FPoint getP2();

        @StructMember(1)
        public native void setP2(@ByVal FPoint x);
    }

    public static class Union extends Struct<Union> {
        @StructMember(0)
        public native @ByVal FPoint getV1();

        @StructMember(0)
        public native void setV1(@ByVal FPoint x);

        @StructMember(0)
        public native int getV2();

        @StructMember(0)
        public native void setV2(int x);
    }

    public static class Unsupported1 extends Struct<Unsupported1> {
        @StructMember(0)
        public native @Array(10) ByteBuffer getV();

        @StructMember(0)
        public native void setV(@Array(10) ByteBuffer x);
    }

    public static class Unsupported2 extends Struct<Unsupported2> {
        @StructMember(0)
        public native @Array(10) byte[] getV();

        @StructMember(0)
        public native void setV(@Array(10) byte[] x);
    }

    public abstract static class Methods {
        abstract void m1();

        abstract boolean m2(byte b, short s, char c, int i, long l, float f, double d);

        abstract @Pointer long m3(@Pointer long a, @MachineSizedSInt long b, @MachineSizedUInt long c,
                @MachineSizedFloat float d, @MachineSizedFloat double e);

        abstract IPoint m4(@ByVal IPoint p1, @ByVal FPoint p2);

        abstract void m5(@ByVal FRect r);

        abstract void m6(@ByVal Union u);

        abstract String m7(String s);

        abstract void m8(@ByVal Unsupported1 u);

        abstract void m9(@ByVal Unsupported2 u);
    }

    @Test
    public void testVoidReturnTypeNoParameters() {
        assertEquals("v", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m1"), false));
    }

    @Test
    public void testSimplePrimitiveTypes() {
        assertEquals("ccsSiqfd", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m2"), false));
        assertEquals("ccsSiqfd", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m2"), true));
    }

    @Test
    public void testArchDependentPrimitiveTypes() {
        assertEquals("^v^viIff", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m3"), false));
        assertEquals("^v^vqQdd", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m3"), true));
    }

    @Test
    public void testSimpleStructTypes() {
        assertEquals("^v{?=ii}{?=ff}",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m4"), false));
        assertEquals("^v{?=ii}{?=dd}", new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m4"), true));
    }

    @Test
    public void testNestedStructTypes() {
        assertEquals("v{?={?=ff}{?=ff}}",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m5"), false));
        assertEquals("v{?={?=dd}{?=dd}}",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m5"), true));
    }

    @Test
    public void testUnionTypes() {
        assertEquals("v(?=i{?=ff})",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m6"), false));
        assertEquals("v(?=i{?=dd})",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m6"), true));
    }

    @Test
    public void testObjectType() {
        assertEquals("@@",
                new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m7"), false));
    }

    @Test
    public void testArrayInStructTypeFails1() {
        // We don't support arrays in structs yet
        try {
            new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m8"), false);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testArrayInStructTypeFails2() {
        // We don't support arrays in structs yet
        try {
            new TypeEncoder().encode(toSootClass(Methods.class).getMethodByName("m9"), false);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
}
