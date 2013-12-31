/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.rt.bro;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.AfterBridgeCall;
import org.robovm.rt.bro.annotation.AfterCallbackCall;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Marshalers;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.annotation.StructRet;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.CharPtr;
import org.robovm.rt.bro.ptr.DoublePtr;
import org.robovm.rt.bro.ptr.FloatPtr;
import org.robovm.rt.bro.ptr.IntPtr;
import org.robovm.rt.bro.ptr.LongPtr;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.ShortPtr;

/**
 * Tests {@link Bridge} and {@link Callback} methods.
 */
public class BridgeCallbackTest {

    public static final class Point extends Struct<Point> {
        @StructMember(0)
        public native int x();
        @StructMember(1)
        public native int y();
        @StructMember(0)
        public native Point x(int x);
        @StructMember(1)
        public native Point y(int y);
    }
    
    public static final class PointPtr extends Ptr<Point, PointPtr> {}
    public static final class PointPtrPtr extends Ptr<PointPtr, PointPtrPtr> {}

    public static final class Points extends Struct<Points> {
        @StructMember(0)
        public native @ByVal Point p1();
        @StructMember(0)
        public native Points p1(@ByVal Point p1);
        @StructMember(1)
        public native @ByVal Point p2();
        @StructMember(1)
        public native Points p2(@ByVal Point p2);
        @StructMember(2)
        public native @ByVal Point p3();
        @StructMember(2)
        public native Points p3(@ByVal Point p3);
        @StructMember(3)
        public native @ByVal Point p4();
        @StructMember(3)
        public native Points p4(@ByVal Point p4);
    }
    
    public enum SimpleEnum {
        V1, V2, V3
    }
    public enum TestValuedEnum implements ValuedEnum {
        VM1(-1), V100(100), V1000(1000), V10000(10000);
        
        private final int n;
        private TestValuedEnum(int n) {
            this.n = n;
        }
        public long value() {
            return n;
        }
    }

    public static final class TestBits extends Bits<TestBits> {
        public static final TestBits V1 = new TestBits(1);
        public static final TestBits V2 = new TestBits(2);
        public static final TestBits V4 = new TestBits(4);
        public static final TestBits V8 = new TestBits(8);

        private static final TestBits[] VALUES = _values(TestBits.class);
        
        private TestBits(long value) { super(value); }
        private TestBits(long value, long mask) { super(value, mask); }

        @Override
        protected TestBits wrap(long value, long mask) {
            return new TestBits(value, mask);
        }

        @Override
        protected TestBits[] values() {
            return VALUES;
        }
    }
    
    public static class StringMarshaler {
        static List<String> calls = new ArrayList<String>();
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            BytePtr ptr = Struct.toStruct(BytePtr.class, handle);
            String s = ptr.toStringAsciiZ();
            calls.add("toObject(" + s + ", ?, " + Long.toHexString(flags) + ")");
            return s;
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            calls.add("toNative(" + s + ", ?, " + Long.toHexString(flags) + ")");
            BytePtr ptr = BytePtr.toBytePtrAsciiZ((String) s);
            return ptr.getHandle();
        }
        @AfterBridgeCall
        public static void afterToNative(String s, long handle, long flags) {
            calls.add("afterToNative(" + s + ", ?, " + Long.toHexString(flags) + ")");
        }
        @AfterCallbackCall
        public static void afterToObject(long handle, String s, long flags) {
            calls.add("afterToObject(?, " + s + ", " + Long.toHexString(flags) + ")");
        }
    }
    
    @Bridge
    public static native int addInts(int x, int y);
    @Callback
    public static int addInts_cb(int x, int y) {
        return x + y;
    }

    @Bridge
    public static native Point addPoints(Point p1, Point p2);
    @Callback
    public static Point addPoints_cb(Point p1, Point p2) {
        return new Point().x(p1.x() + p2.x()).y(p1.y() + p2.y());
    }
    
    @Bridge
    public static native void scalePoint1(Point p, int scale);
    @Callback
    public static void scalePoint1_cb(Point p, int scale) {
        p.x(p.x() * scale);
        p.y(p.y() * scale);
    }

    @Bridge
    public static native void scalePoint2(@ByVal Point p, int scale);
    @Callback
    public static void scalePoint2_cb(@ByVal Point p, int scale) {
        p.x(p.x() * scale);
        p.y(p.y() * scale);
    }
    
    @Bridge
    public static native @ByVal Point copyPoint(Point p);
    @Callback
    public static @ByVal Point copyPoint_cb(Point p) {
        return p;
    }
    
    @Bridge
    public static native void createPoints(@StructRet Points ps, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4);
    @Callback
    public static void createPoints_cb(@StructRet Points ps, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        ps.p1(new Point().x(x1).y(y1));
        ps.p2(new Point().x(x2).y(y2));
        ps.p3(new Point().x(x3).y(y3));
        ps.p4(new Point().x(x4).y(y4));
    }
    
    @Bridge
    public static native void createPoint(int x, int y, PointPtr ptr);
    @Callback
    public static void createPoint_cb(int x, int y, PointPtr ptr) {
        ptr.set(new Point().x(x).y(y));
    }
    
    @Bridge
    public static native Point passLargeStructByVal(@ByVal Points ps);
    @Callback
    public static Point passLargeStructByVal_cb(@ByVal Points ps) {
        int sumx = 0;
        int sumy = 0;
        sumx += ps.p1().x();
        sumy += ps.p1().y();
        sumx += ps.p2().x();
        sumy += ps.p2().y();
        sumx += ps.p3().x();
        sumy += ps.p3().y();
        sumx += ps.p4().x();
        sumy += ps.p4().y();
        ps.clear();
        return new Point().x(sumx).y(sumy);
    }
    
    @Bridge
    public static native @ByVal Points returnLargeStructByVal(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, LongPtr address);
    @Callback
    public static @ByVal Points returnLargeStructByVal_cb(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, LongPtr address) {
        Points ps = new Points();
        ps.p1(new Point().x(x1).y(y1));
        ps.p2(new Point().x(x2).y(y2));
        ps.p3(new Point().x(x3).y(y3));
        ps.p4(new Point().x(x4).y(y4));
        address.set(ps.getHandle());
        return ps;
    }
    
    @Bridge
    public static native @Marshaler(StringMarshaler.class) String append(
            @Marshaler(StringMarshaler.class) String a, 
            @Marshaler(StringMarshaler.class) String b);
    @Callback
    public static @Marshaler(StringMarshaler.class) String append_cb(
            @Marshaler(StringMarshaler.class) String a, 
            @Marshaler(StringMarshaler.class) String b) {
        
        return a + b;
    }
    
    @Marshaler(StringMarshaler.class)
    public static class Inner1 {
        @Bridge
        public static native String append(String a, String b);
        @Callback
        public static String append_cb(String a, String b) {
            return a + b;
        }
    }
    @Marshalers(@Marshaler(StringMarshaler.class))
    public static class Inner2 {
        @Bridge
        public static native String append(String a, String b);
        @Callback
        public static String append_cb(String a, String b) {
            return a + b;
        }
    }
    
    @Marshaler(StringMarshaler.class)
    public static class Inner3 {
        public static class Inner4 {
            @Bridge
            public static native String append(String a, String b);
            @Callback
            public static String append_cb(String a, String b) {
                return a + b;
            }
        }
    }
    
    @Bridge
    public static native SimpleEnum marshalSimpleEnum(SimpleEnum v);
    @Callback
    public static SimpleEnum marshalSimpleEnum_cb(SimpleEnum v) {
        SimpleEnum[] values = SimpleEnum.values();
        return values[(v.ordinal() + 1) % values.length];
    }
    
    @Bridge
    public static native TestValuedEnum marshalValuedEnum(TestValuedEnum v);
    @Callback
    public static TestValuedEnum marshalValuedEnum_cb(TestValuedEnum v) {
        TestValuedEnum[] values = TestValuedEnum.values();
        return values[(v.ordinal() + 1) % values.length];
    }

    @Bridge
    public static native @Marshaler(ValuedEnum.AsUnsignedIntMarshaler.class) TestValuedEnum marshalValuedEnumAsUnsignedInt(
            @Marshaler(ValuedEnum.AsUnsignedIntMarshaler.class) TestValuedEnum v);
    @Callback
    public static @Marshaler(ValuedEnum.AsUnsignedIntMarshaler.class) TestValuedEnum marshalValuedEnumAsUnsignedInt_cb(
            @Marshaler(ValuedEnum.AsUnsignedIntMarshaler.class) TestValuedEnum v) {
        TestValuedEnum[] values = TestValuedEnum.values();
        return values[(v.ordinal() + 1) % values.length];
    }

    @Bridge
    public static native TestBits marshalBits1(TestBits v1, TestBits v2);
    @Callback
    public static int marshalBits1_cb(int v1, int v2) {
        return v1 | v2;
    }
    @Bridge
    public static native int marshalBits2(int v1, int v2);
    @Callback
    public static TestBits marshalBits2_cb(TestBits v1, TestBits v2) {
        return v1.set(v2);
    }
    
    @Bridge
    public static native String marshalStringsWithDefaultMarshaler(String a, String b);
    @Callback
    public static BytePtr marshalStringsWithDefaultMarshaler_cb(String a, String b) {
        return a == null && b == null ? null : BytePtr.toBytePtrAsciiZ(String.format("a = %s, b = %s", a, b));
    }

    @Bridge
    public static native BytePtr marshalBuffersWithDefaultMarshaler(ByteBuffer a, ByteBuffer b);
    @Callback
    public static BytePtr marshalBuffersWithDefaultMarshaler_cb(BytePtr a, BytePtr b) {
        return a == null && b == null ? null 
                : BytePtr.toBytePtrAsciiZ(
                        String.format("a = %s, b = %s", 
                                a == null ? null : a.toStringAsciiZ(), 
                                b == null ? null : b.toStringAsciiZ()));
    }

    @Bridge
    public static native BytePtr marshal1DByteArrayWithDefaultMarshaler(byte[] a, byte[] b);
    @Callback
    public static BytePtr marshal1DByteArrayWithDefaultMarshaler_cb(BytePtr a, BytePtr b) {
        return a == null && b == null ? null 
                : BytePtr.toBytePtrAsciiZ(
                        String.format("a = %s, b = %s", 
                                a == null ? null : a.toStringAsciiZ(), 
                                b == null ? null : b.toStringAsciiZ()));
    }

    @Bridge
    public static native short marshal1DShortArrayWithDefaultMarshaler(short[] a);
    @Callback
    public static short marshal1DShortArrayWithDefaultMarshaler_cb(ShortPtr ptr) {
        if (ptr == null) {
            return -1;
        }
        short sum = 0;
        while (ptr.get() != 0) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }

    @Bridge
    public static native char marshal1DCharArrayWithDefaultMarshaler(char[] a);
    @Callback
    public static char marshal1DCharArrayWithDefaultMarshaler_cb(CharPtr ptr) {
        if (ptr == null) {
            return 0xffff;
        }
        char sum = 0;
        while (ptr.get() != 0) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }

    @Bridge
    public static native int marshal1DIntArrayWithDefaultMarshaler(int[] a);
    @Callback
    public static int marshal1DIntArrayWithDefaultMarshaler_cb(IntPtr ptr) {
        if (ptr == null) {
            return -1;
        }
        int sum = 0;
        while (ptr.get() != 0) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }
    
    @Bridge
    public static native long marshal1DLongArrayWithDefaultMarshaler(long[] a);
    @Callback
    public static long marshal1DLongArrayWithDefaultMarshaler_cb(LongPtr ptr) {
        if (ptr == null) {
            return -1;
        }
        long sum = 0;
        while (ptr.get() != 0) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }

    @Bridge
    public static native float marshal1DFloatArrayWithDefaultMarshaler(float[] a);
    @Callback
    public static float marshal1DFloatArrayWithDefaultMarshaler_cb(FloatPtr ptr) {
        if (ptr == null) {
            return -1.0f;
        }
        float sum = 0.0f;
        while (ptr.get() != 0.0f) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }

    @Bridge
    public static native double marshal1DDoubleArrayWithDefaultMarshaler(double[] a);
    @Callback
    public static double marshal1DDoubleArrayWithDefaultMarshaler_cb(DoublePtr ptr) {
        if (ptr == null) {
            return -1.0;
        }
        double sum = 0.0;
        while (ptr.get() != 0.0) {
            sum += ptr.get();
            ptr = ptr.next();
        }
        return sum;
    }

    private static Method find(String name) {
        for (Method m : BridgeCallbackTest.class.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    private static void bind(Class<?> cls) {
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getAnnotation(Bridge.class) != null) {
                Method callbackMethod = find(m.getName() + "_cb");
                if (callbackMethod != null) {
                    VM.bindBridgeMethod(m, VM.getCallbackMethodImpl(callbackMethod));
                }
            }
        }
    }
    
    static {
        bind(BridgeCallbackTest.class);
        bind(BridgeCallbackTest.Inner1.class);
        bind(BridgeCallbackTest.Inner2.class);
        bind(BridgeCallbackTest.Inner3.Inner4.class);
    }
    
    @Test
    public void testPrimitiveParameters() {
        assertEquals(8, addInts(5, 3));
    }

    @Test
    public void testMarshalStructParametersAndReturnValue() {
        Point p1 = new Point().x(1).y(2);
        Point p2 = new Point().x(3).y(4);
        Point sum = addPoints(p1, p2);
        assertEquals(4, sum.x());
        assertEquals(6, sum.y());
    }
    
    @Test
    public void testMarshalStructParametersByRef() {
        Point p = new Point().x(1).y(2);
        scalePoint1(p, 5);
        assertEquals(5, p.x());
        assertEquals(10, p.y());
    }
    
    @Test
    public void testMarshalStructParametersByVal() {
        Point p = new Point().x(1).y(2);
        scalePoint2(p, 5);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
    }
    
    @Test
    public void testMarshalStructStructRet() {
        Points ps = new Points();
        createPoints(ps, 1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(1, ps.p1().x());
        assertEquals(2, ps.p1().y());
        assertEquals(3, ps.p2().x());
        assertEquals(4, ps.p2().y());
        assertEquals(5, ps.p3().x());
        assertEquals(6, ps.p3().y());
        assertEquals(7, ps.p4().x());
        assertEquals(8, ps.p4().y());
    }
    
    @Test
    public void testNullByValParameter() {
        try {
            scalePoint2(null, 5);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
        }
    }
    
    @Test
    public void testNullStructRet() {
        try {
            createPoints(null, 1, 2, 3, 4, 5, 6, 7, 8);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testMarshalStructReturnValueByVal() {
        Point p1 = new Point().x(1).y(2);
        Point p2 = copyPoint(p1);
        assertEquals(1, p2.x());
        assertEquals(2, p2.y());
        assertFalse(p1.equals(p2));
    }

    @Test
    public void testMarshalStructPtr() {
        PointPtr ptr = new PointPtr();
        assertNull(ptr.get());
        createPoint(10, 20, ptr);
        Point p = ptr.get();
        assertNotNull(p);
        assertEquals(10, p.x());
        assertEquals(20, p.y());
    }
    
    @Test
    public void testMarshalLargeStructParameterByVal() {
        Points ps = new Points();
        ps.p1(new Point().x(1).y(2));
        ps.p2(new Point().x(2).y(4));
        ps.p3(new Point().x(3).y(6));
        ps.p4(new Point().x(4).y(8));
        Point sum = passLargeStructByVal(ps);
        assertEquals(1, ps.p1().x());
        assertEquals(2, ps.p1().y());
        assertEquals(2, ps.p2().x());
        assertEquals(4, ps.p2().y());
        assertEquals(3, ps.p3().x());
        assertEquals(6, ps.p3().y());
        assertEquals(4, ps.p4().x());
        assertEquals(8, ps.p4().y());
        assertEquals(10, sum.x());
        assertEquals(20, sum.y());
    }

    @Test
    public void testMarshalLargeStructReturnValueByVal() {
        LongPtr address = new LongPtr();
        Points ps = returnLargeStructByVal(1, 2, 3, 4, 5, 6, 7, 8, address);
        assertEquals(1, ps.p1().x());
        assertEquals(2, ps.p1().y());
        assertEquals(3, ps.p2().x());
        assertEquals(4, ps.p2().y());
        assertEquals(5, ps.p3().x());
        assertEquals(6, ps.p3().y());
        assertEquals(7, ps.p4().x());
        assertEquals(8, ps.p4().y());
        assertFalse(address.get() == ps.getHandle());
    }
    
    @Test
    public void testMarshalNonNativeTypeInlineMarshaler() {
        String s = append("foo", "bar");
        assertEquals("foobar", s);
    }
    
    @Test
    public void testMarshalNonNativeTypeMarshalerOnClass() {
        String s = Inner1.append("foo", "bar");
        assertEquals("foobar", s);
    }
    
    @Test
    public void testMarshalNonNativeTypeMarshalersOnClass() {
        String s = Inner2.append("foo", "bar");
        assertEquals("foobar", s);
    }
    
    @Test
    public void testMarshalerOnOuterClass() {
        String s = Inner3.Inner4.append("foo", "bar");
        assertEquals("foobar", s);
    }
    
    @Test
    public void testMarshalerCallSequence() {
        StringMarshaler.calls = new ArrayList<String>();
        append("foo", "bar");
        assertEquals(10, StringMarshaler.calls.size());
        assertEquals("toNative(foo, ?, 0)", StringMarshaler.calls.get(0));
        assertEquals("toNative(bar, ?, 0)", StringMarshaler.calls.get(1));
        assertEquals("toObject(foo, ?, 1)", StringMarshaler.calls.get(2));
        assertEquals("toObject(bar, ?, 1)", StringMarshaler.calls.get(3));
        assertEquals("afterToObject(?, foo, 1)", StringMarshaler.calls.get(4));
        assertEquals("afterToObject(?, bar, 1)", StringMarshaler.calls.get(5));
        assertEquals("toNative(foobar, ?, 1)", StringMarshaler.calls.get(6));
        assertEquals("afterToNative(foo, ?, 0)", StringMarshaler.calls.get(7));
        assertEquals("afterToNative(bar, ?, 0)", StringMarshaler.calls.get(8));
        assertEquals("toObject(foobar, ?, 0)", StringMarshaler.calls.get(9));
    }
    
    @Test
    public void testMarshalSimpleEnum() {
        assertEquals(SimpleEnum.V2, marshalSimpleEnum(SimpleEnum.V1));
        assertEquals(SimpleEnum.V3, marshalSimpleEnum(SimpleEnum.V2));
        assertEquals(SimpleEnum.V1, marshalSimpleEnum(SimpleEnum.V3));
    }

    @Test
    public void testMarshalValuedEnum() {
        assertEquals(TestValuedEnum.V100, marshalValuedEnum(TestValuedEnum.VM1));
        assertEquals(TestValuedEnum.V1000, marshalValuedEnum(TestValuedEnum.V100));
        assertEquals(TestValuedEnum.V10000, marshalValuedEnum(TestValuedEnum.V1000));
        assertEquals(TestValuedEnum.VM1, marshalValuedEnum(TestValuedEnum.V10000));
    }

    @Test
    public void testMarshalValuedEnumAsUnsignedInt() {
        try {
            marshalValuedEnumAsUnsignedInt(TestValuedEnum.VM1);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("" + 0xffffffffL));
            assertTrue(e.getMessage().contains("0xffffffff"));
        }
        try {
            marshalValuedEnumAsUnsignedInt(TestValuedEnum.V10000);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("" + 0xffffffffL));
            assertTrue(e.getMessage().contains("0xffffffff"));
        }
        assertEquals(TestValuedEnum.V1000, marshalValuedEnumAsUnsignedInt(TestValuedEnum.V100));
        assertEquals(TestValuedEnum.V10000, marshalValuedEnumAsUnsignedInt(TestValuedEnum.V1000));
    }

    @Test
    public void testMarshalBits1() {
        assertEquals(1 | 8, marshalBits1(TestBits.V1, TestBits.V8).value());
    }
    
    @Test
    public void testMarshalBits2() {
        assertEquals(1 | 8, marshalBits2(1, 8));
    }
    
    @Test
    public void testMarshalStringsWithDefaultMarshaler() {
        assertEquals("a = foo, b = bar", marshalStringsWithDefaultMarshaler("foo", "bar"));
        assertEquals("a = null, b = bar", marshalStringsWithDefaultMarshaler(null, "bar"));
        assertEquals("a = foo, b = null", marshalStringsWithDefaultMarshaler("foo", null));
        assertNull(marshalStringsWithDefaultMarshaler(null, null));
    }
    
    @Test
    public void testMarshalBuffersWithDefaultMarshaler() {
        assertEquals("a = foo, b = bar", marshalBuffersWithDefaultMarshaler(
                ByteBuffer.wrap("foo".getBytes()), 
                ByteBuffer.wrap("bar".getBytes())
                ).toStringAsciiZ());
        assertEquals("a = null, b = bar", marshalBuffersWithDefaultMarshaler(
                null, ByteBuffer.allocateDirect(3).put("bar".getBytes())).toStringAsciiZ());
        assertEquals("a = foo, b = null", marshalBuffersWithDefaultMarshaler(
                ByteBuffer.allocateDirect(3).put("foo".getBytes()), null).toStringAsciiZ());
        assertNull(marshalBuffersWithDefaultMarshaler(null, null));
    }
    
    @Test
    public void testMarshal1DByteArrayWithDefaultMarshaler() {
        assertEquals("a = foo, b = bar", marshal1DByteArrayWithDefaultMarshaler(
                new byte[] {'f', 'o', 'o', 0}, 
                new byte[] {'b', 'a', 'r', 0}
                ).toStringAsciiZ());
        assertEquals("a = null, b = bar", marshal1DByteArrayWithDefaultMarshaler(
                null, new byte[] {'b', 'a', 'r', 0}).toStringAsciiZ());
        assertEquals("a = foo, b = null", marshal1DByteArrayWithDefaultMarshaler(
                new byte[] {'f', 'o', 'o', 0}, null).toStringAsciiZ());
        assertNull(marshal1DByteArrayWithDefaultMarshaler(null, null));
    }

    @Test
    public void testMarshal1DShortArrayWithDefaultMarshaler() {
        assertEquals(0, marshal1DShortArrayWithDefaultMarshaler(
                new short[] {0}));
        assertEquals(12345, marshal1DShortArrayWithDefaultMarshaler(
                new short[] {10000, 2000, 300, 40, 5, 0}));
        assertEquals(-1, marshal1DShortArrayWithDefaultMarshaler(null));
    }

    @Test
    public void testMarshal1DCharArrayWithDefaultMarshaler() {
        assertEquals(0, marshal1DCharArrayWithDefaultMarshaler(
                new char[] {0}));
        assertEquals(12345, marshal1DCharArrayWithDefaultMarshaler(
                new char[] {10000, 2000, 300, 40, 5, 0}));
        assertEquals(0xffff, marshal1DCharArrayWithDefaultMarshaler(null));
    }

    @Test
    public void testMarshal1DIntArrayWithDefaultMarshaler() {
        assertEquals(0, marshal1DIntArrayWithDefaultMarshaler(
                new int[] {0}));
        assertEquals(1000002345, marshal1DIntArrayWithDefaultMarshaler(
                new int[] {1000000000, 2000, 300, 40, 5, 0}));
        assertEquals(-1, marshal1DIntArrayWithDefaultMarshaler(null));
    }

    @Test
    public void testMarshal1DLongArrayWithDefaultMarshaler() {
        assertEquals(0, marshal1DLongArrayWithDefaultMarshaler(
                new long[] {0}));
        assertEquals(1000000000000002345L, marshal1DLongArrayWithDefaultMarshaler(
                new long[] {1000000000000000000L, 2000, 300, 40, 5, 0}));
        assertEquals(-1, marshal1DLongArrayWithDefaultMarshaler(null));
    }
    
    @Test
    public void testMarshal1DFloatArrayWithDefaultMarshaler() {
        assertEquals(0.0f, marshal1DFloatArrayWithDefaultMarshaler(
                new float[] {0}), 0);
        assertEquals(1.2345f, marshal1DFloatArrayWithDefaultMarshaler(
                new float[] {1, 0.2f, 0.03f, 0.004f, 0.0005f, 0}), 0.001f);
        assertEquals(-1.0f, marshal1DFloatArrayWithDefaultMarshaler(null), 0);
    }

    @Test
    public void testMarshal1DDoubleArrayWithDefaultMarshaler() {
        assertEquals(0.0, marshal1DDoubleArrayWithDefaultMarshaler(
                new double[] {0}), 0);
        assertEquals(1.2345, marshal1DDoubleArrayWithDefaultMarshaler(
                new double[] {1, 0.2, 0.03, 0.004, 0.0005, 0}), 0.001);
        assertEquals(-1.0, marshal1DDoubleArrayWithDefaultMarshaler(null), 0);
    }
}
