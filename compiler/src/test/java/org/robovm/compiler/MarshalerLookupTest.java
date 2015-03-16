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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.Marshaler;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.Config.Home;
import org.robovm.compiler.log.Logger;
import org.robovm.rt.bro.ArrayMarshalers;
import org.robovm.rt.bro.MarshalerFlags;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Array;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Marshalers;
import org.robovm.rt.bro.annotation.MarshalsArray;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.MarshalsValue;
import org.robovm.rt.bro.annotation.StructMember;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.options.Options;

/**
 * Tests {@link MarshalerLookup}.
 */
public class MarshalerLookupTest {

    private static Config config;
    
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
        
        Config.Builder configBuilder = new Config.Builder();
        for (String p : System.getProperty("sun.boot.class.path").split(File.pathSeparator)) {
            configBuilder.addBootClasspathEntry(new File(p));
        }
        for (String p : System.getProperty("java.class.path").split(File.pathSeparator)) {
            configBuilder.addClasspathEntry(new File(p));
        }
        configBuilder.skipInstall(true);
        configBuilder.skipLinking(true);
        configBuilder.home(new MockHome(new File(System.getProperty("java.io.tmpdir"))));
        configBuilder.logger(new Logger() {
            public void warn(String format, Object... args) {
                System.out.format("WARN: " + format, args);
                System.out.println();
            }
            public void info(String format, Object... args) {
                System.out.format("INFO: " + format, args);
                System.out.println();
            }
            public void error(String format, Object... args) {
                System.out.format("ERROR: " + format, args);
                System.out.println();
            }
            public void debug(String format, Object... args) {
                System.out.format("DEBUG: " + format, args);
                System.out.println();
            }
        });
        config = configBuilder.build();
    }
    
    private Clazz toClazz(Class<?> cls) {
        return config.getClazzes().load(cls.getName().replace('.', '/'));
    }

    private SootClass toSootClass(Class<?> cls) {
        return toClazz(cls).getSootClass();
    }

    @Test
    public void testFindMarshalersBuiltins() {
        MarshalerLookup lookup = new MarshalerLookup(config);
        List<Marshaler> l = lookup.findMarshalers(toSootClass(String.class));
        assertFalse(l.isEmpty());
        assertSame(toClazz(ArrayMarshalers.ByteArrayMarshaler.class), l.get(0).getClazz());
        assertSame(toClazz(ArrayMarshalers.ShortArrayMarshaler.class), l.get(1).getClazz());
    }

    @Test
    public void testFindMarshalersSkipBuiltins() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        List<Marshaler> l = lookup.findMarshalers(toSootClass(C1.class));
        assertEquals(1, l.size());
        assertSame(toClazz(M1.class), l.get(0).getClazz());
    }

    @Test
    public void testFindMarshalersSearchesSuperclasses() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        List<Marshaler> l = lookup.findMarshalers(toSootClass(C2.class));
        assertEquals(1, l.size());
        assertSame(toClazz(M1.class), l.get(0).getClazz());
    }

    @Test
    public void testFindMarshalersSearchesInterfaces() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        List<Marshaler> l = lookup.findMarshalers(toSootClass(C4.class));
        assertEquals(7, l.size());
        assertSame(toClazz(M7.class), l.get(0).getClazz());
        assertSame(toClazz(M1.class), l.get(1).getClazz());
        assertSame(toClazz(M3.class), l.get(2).getClazz());
        assertSame(toClazz(M6.class), l.get(3).getClazz());
        assertSame(toClazz(M2.class), l.get(4).getClazz());
        assertSame(toClazz(M4.class), l.get(5).getClazz());
        assertSame(toClazz(M5.class), l.get(6).getClazz());
    }

    @Test
    public void testFindMarshalersSearchesOuterClasses() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        List<Marshaler> l = lookup.findMarshalers(toSootClass(C2.Inner.InnerInner.class));
        assertEquals(1, l.size());
        assertSame(toClazz(M1.class), l.get(0).getClazz());
    }

    @Test
    public void testFindMarshalerMethodUnsuccessfulSearchNoMarshalers() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo1.class).getMethodByName("foo");
        try {
            lookup.findMarshalerMethod(new MarshalSite(method));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(method, 0));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }
    @Test
    public void testFindMarshalerMethodUnsuccessfulSearchNoMatchingMarshaler() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo2.class).getMethodByName("foo");
        try {
            lookup.findMarshalerMethod(new MarshalSite(method));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(method, 0));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testFindMarshalerMethodUnsuccessfulWithMarshalerAnnotation() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo3.class).getMethodByName("foo");
        try {
            lookup.findMarshalerMethod(new MarshalSite(method));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(method, 0));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testFindMarshalerMethodClassMatchPointer() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo1");
        assertEquals(toSootClass(M2.class).getMethodByName("stringToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("stringToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodClassMatchValue() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo2");
        assertEquals(toSootClass(M2.class).getMethodByName("integerToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("integerToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodInterfaceMatchPointer() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo3");
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodSuperclassMatchValue() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo4");
        assertEquals(toSootClass(M2.class).getMethodByName("numberToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("numberToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodUnsuccessfulSearchUnsupportedCallTypeCallback() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo5");
        try {
            lookup.findMarshalerMethod(new MarshalSite(method)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }
    
    @Test
    public void testFindMarshalerMethodClassMatchArray() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo6");
        assertEquals(toSootClass(M2.class).getMethodByName("stringArrayToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("stringArrayToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodSuperclassMatchArray() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo7");
        assertEquals(toSootClass(M2.class).getMethodByName("numberArrayToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("numberArrayToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodInterfaceMatchArray() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo8");
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceArrayToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceArrayToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodInterfaceDirectMatchPointer() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod method = toSootClass(Foo4.class).getMethodByName("foo9");
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(method)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("charSequenceToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(method, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerMethodUnsuccessfulSearchUnsupportedCallTypeStructMember() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV1");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV1");
        try {
            lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testFindMarshalerStructMemberPrimitiveArray1D() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV2");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV2");
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray1DToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray1DToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerStructMemberPrimitiveArray2D() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV3");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV3");
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray2DToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray2DToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerStructMemberPrimitiveArray3D() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV4");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV4");
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray3DToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("byteArray3DToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod());
    }
    
    @Test
    public void testFindMarshalerStructMemberByteBuffer1D() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV5");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV5");
        assertEquals(toSootClass(M2.class).getMethodByName("byteBuffer1DToObject"), 
                lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod());
        assertEquals(toSootClass(M2.class).getMethodByName("byteBuffer1DToNative"), 
                lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod());
    }

    @Test
    public void testFindMarshalerStructMemberUnsupportedArrayDimension() {
        MarshalerLookup lookup = new MarshalerLookup(config).searchBuiltins(false);
        SootMethod getter = toSootClass(TestStruct.class).getMethodByName("getV6");
        SootMethod setter = toSootClass(TestStruct.class).getMethodByName("setV6");
        try {
            lookup.findMarshalerMethod(new MarshalSite(getter)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        try {
            lookup.findMarshalerMethod(new MarshalSite(setter, 0)).getMethod();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
    }

    public static class M1 {}
    public static class M2 {
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static String stringToObject(Class<?> cls, long handle, long flags) {
            return null;
        }
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long stringToNative(String s, long flags) {
            return 0L;
        }
        @MarshalsPointer
        public static CharSequence charSequenceToObject(Class<?> cls, long handle, long flags) {
            return null;
        }
        @MarshalsPointer
        public static long charSequenceToNative(CharSequence s, long flags) {
            return 0L;
        }
        @MarshalsValue
        public static Integer integerToObject(Class<?> cls, int v, long flags) {
            return null;
        }
        @MarshalsValue
        public static int integerToNative(Integer s, long flags) {
            return 0;
        }
        @MarshalsValue
        public static Number numberToObject(Class<?> cls, double v, long flags) {
            return null;
        }
        @MarshalsValue
        public static double numberToNative(Number s, long flags) {
            return 0;
        }
        @MarshalsPointer
        public static String[] stringArrayToObject(Class<?> cls, long handle, long flags) {
            return null;
        }
        @MarshalsPointer
        public static long stringArrayToNative(String[] s, long flags) {
            return 0L;
        }
        @MarshalsPointer
        public static Number[] numberArrayToObject(Class<?> cls, long handle, long flags) {
            return null;
        }
        @MarshalsPointer
        public static long numberArrayToNative(Number[] s, long flags) {
            return 0;
        }
        @MarshalsPointer
        public static CharSequence[] charSequenceArrayToObject(Class<?> cls, long handle, long flags) {
            return null;
        }
        @MarshalsPointer
        public static long charSequenceArrayToNative(CharSequence[] s, long flags) {
            return 0L;
        }
        @MarshalsArray
        public static byte[] byteArray1DToObject(Class<?> cls, long handle, long flags, int d1) {
            return null;
        }
        @MarshalsArray
        public static void byteArray1DToNative(byte[] b, long handle, long flags, int d1) {
        }
        @MarshalsArray
        public static byte[][] byteArray2DToObject(Class<?> cls, long handle, long flags, int d1, int d2) {
            return null;
        }
        @MarshalsArray
        public static void byteArray2DToNative(byte[][] b, long handle, long flags, int d1, int d2) {
        }
        @MarshalsArray
        public static byte[][][] byteArray3DToObject(Class<?> cls, long handle, long flags, int d1, int d2, int d3) {
            return null;
        }
        @MarshalsArray
        public static void byteArray3DToNative(byte[][][] b, long handle, long flags, int d1, int d2, int d3) {
        }
        @MarshalsArray
        public static ByteBuffer byteBuffer1DToObject(Class<?> cls, long handle, long flags, int d1) {
            return null;
        }
        @MarshalsArray
        public static void byteBuffer1DToNative(ByteBuffer b, long handle, long flags, int d1) {
        }
    }
    public static class M3 {}
    public static class M4 {}
    public static class M5 {}
    public static class M6 {}
    public static class M7 {}
    public static class M8 {}
    
    @org.robovm.rt.bro.annotation.Marshaler(M1.class)
    public static class C1 {
    }

    public static class C2 extends C1 {
        public static class Inner {
            public static class InnerInner {
            }
        }
    }

    @org.robovm.rt.bro.annotation.Marshaler(M2.class)
    public interface I1 {}
    @org.robovm.rt.bro.annotation.Marshaler(M3.class)
    public interface I2 {}
    @org.robovm.rt.bro.annotation.Marshaler(M4.class)
    public interface I3 {}
    @Marshalers({
        @org.robovm.rt.bro.annotation.Marshaler(M5.class),
        @org.robovm.rt.bro.annotation.Marshaler(M1.class)
    })
    public interface I4 {}
    public interface I5 extends I3, I4 {}
    @org.robovm.rt.bro.annotation.Marshaler(M6.class)
    public interface I6 {}
    
    public static class C3 extends C2 implements I1, I5 {
    }

    @org.robovm.rt.bro.annotation.Marshaler(M7.class)
    public static class C4 extends C3 implements I2, I6 {
    }

    public static class Foo1 {
        @Bridge
        private static native String foo(String s);
    }
    @org.robovm.rt.bro.annotation.Marshaler(M1.class)
    public static class Foo2 {
        @Bridge
        private static native String foo(String s);
    }
    @org.robovm.rt.bro.annotation.Marshaler(M2.class)
    public static class Foo3 {
        @Bridge
        private static native @org.robovm.rt.bro.annotation.Marshaler(M1.class) String 
            foo(@org.robovm.rt.bro.annotation.Marshaler(M1.class) String s);
    }
    @org.robovm.rt.bro.annotation.Marshaler(M2.class)
    public static class Foo4 {
        @Bridge
        private static native String foo1(String s);
        @Bridge
        private static native Integer foo2(Integer i);
        @Bridge
        private static native StringBuilder foo3(StringBuilder s);
        @Bridge
        private static native Double foo4(Double d);
        @Callback
        private static String foo5(String s) { return null; }
        @Bridge
        private static native String[] foo6(String[] s);
        @Bridge
        private static native Double[] foo7(Double[] s);
        @Bridge
        private static native StringBuilder[] foo8(StringBuilder[] s);
        @Bridge
        private static native CharSequence foo9(CharSequence s);
    }
    
    @org.robovm.rt.bro.annotation.Marshaler(M2.class)
    public static class TestStruct extends Struct<TestStruct> {
        @StructMember(0)
        public native String getV1();
        @StructMember(0)
        public native void setV1(String s);
        @StructMember(0)
        public native @Array(10) byte[] getV2();
        @StructMember(0)
        public native void setV2(@Array(10) byte[] b);
        @StructMember(0)
        public native @Array({10, 20}) byte[][] getV3();
        @StructMember(0)
        public native void setV3(@Array({10, 20}) byte[][] b);
        @StructMember(0)
        public native @Array({10, 20, 30}) byte[][][] getV4();
        @StructMember(0)
        public native void setV4(@Array({10, 20, 30}) byte[][][] b);
        @StructMember(0)
        public native @Array(10) ByteBuffer getV5();
        @StructMember(0)
        public native void setV5(@Array(10) ByteBuffer b);
        @StructMember(0)
        public native @Array({10, 20}) ByteBuffer getV6();
        @StructMember(0)
        public native void setV6(@Array({10, 20}) ByteBuffer b);
    }
    
    public static class MockHome extends Home {
        public MockHome(File homeDir) {
            super(homeDir, false);
        }
    }
}
