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

import java.lang.reflect.*;

import org.junit.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;

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
        V100(100), V1000(1000), V10000(10000);
        
        private final int n;
        private TestValuedEnum(int n) {
            this.n = n;
        }
        public int value() {
            return n;
        }
    }

    public static class StringMarshaler {
        public static Object toObject(Class cls, long handle, boolean copy) {
            BytePtr ptr = Struct.toStruct(BytePtr.class, handle);
            return ptr.toStringAsciiZ();
        }
        public static void updateObject(Object o, long handle) {
        }
        public static @Pointer long toNative(Object o) {
            BytePtr ptr = BytePtr.toBytePtrAsciiZ((String) o);
            return ptr.getHandle();
        }
        public static void updateNative(Object o, long handle) {
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
    public static native void createPoint(int x, int y, Ptr<Point> ptr);
    @Callback
    public static void createPoint_cb(int x, int y, Ptr<Point> ptr) {
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
    
    @Marshaler(type = String.class, value = StringMarshaler.class)
    public static class Inner1 {
        @Bridge
        public static native String append(String a, String b);
        @Callback
        public static String append_cb(String a, String b) {
            return a + b;
        }
    }
    @Marshalers(@Marshaler(type = String.class, value = StringMarshaler.class))
    public static class Inner2 {
        @Bridge
        public static native String append(String a, String b);
        @Callback
        public static String append_cb(String a, String b) {
            return a + b;
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
    
    private static Method find(String name) {
        for (Method m : BridgeCallbackTest.class.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    private static void bind(Class cls) {
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
        Ptr<Point> ptr = Ptr.newPtr(Point.class);
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
        assertEquals(10, sum.x());
        assertEquals(20, sum.y());
        assertEquals(1, ps.p1().x());
        assertEquals(2, ps.p1().y());
        assertEquals(2, ps.p2().x());
        assertEquals(4, ps.p2().y());
        assertEquals(3, ps.p3().x());
        assertEquals(6, ps.p3().y());
        assertEquals(4, ps.p4().x());
        assertEquals(8, ps.p4().y());
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
    public void testMarshalSimpleEnum() {
        assertEquals(SimpleEnum.V2, marshalSimpleEnum(SimpleEnum.V1));
        assertEquals(SimpleEnum.V3, marshalSimpleEnum(SimpleEnum.V2));
        assertEquals(SimpleEnum.V1, marshalSimpleEnum(SimpleEnum.V3));
    }

    @Test
    public void testMarshalValuedEnum() {
        assertEquals(TestValuedEnum.V1000, marshalValuedEnum(TestValuedEnum.V100));
        assertEquals(TestValuedEnum.V10000, marshalValuedEnum(TestValuedEnum.V1000));
        assertEquals(TestValuedEnum.V100, marshalValuedEnum(TestValuedEnum.V10000));
    }
}
