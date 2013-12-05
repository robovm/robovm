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

import org.junit.Test;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Array;
import org.robovm.rt.bro.annotation.ByRef;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.IntPtr;
import org.robovm.rt.bro.ptr.Ptr;

/**
 * 
 */
public class StructTest {

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
        public static Object toObject(Class<?> cls, long handle, boolean copy) {
            BytePtr ptr = Struct.toStruct(BytePtr.class, handle);
            return ptr != null ? ptr.toStringAsciiZ() : null;
        }
        public static void updateObject(Object o, long handle) {
        }
        public static @Pointer long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            BytePtr ptr = BytePtr.toBytePtrAsciiZ((String) o);
            return ptr.getHandle();
        }
        public static void updateNative(Object o, long handle) {
        }
    }
    
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
    
    public static final class TestUnion extends Struct<TestUnion> {
        @StructMember(0)
        public native byte b();
        @StructMember(0)
        public native TestUnion b(byte b);
        @StructMember(0)
        public native long l();
        @StructMember(0)
        public native TestUnion l(long l);
        @StructMember(0)
        public native double d();
        @StructMember(0)
        public native TestUnion d(double l);
        @StructMember(0)
        public native @ByVal Point p();
        @StructMember(0)
        public native TestUnion p(@ByVal Point p);
    }
    
    public static final class MixedStructUnion extends Struct<MixedStructUnion> {
        @StructMember(0)
        public native byte b1();
        @StructMember(0)
        public native MixedStructUnion b1(byte b);
        @StructMember(1)
        public native long l();
        @StructMember(1)
        public native MixedStructUnion l(long l);
        @StructMember(1)
        public native double d();
        @StructMember(1)
        public native MixedStructUnion d(double l);
        @StructMember(2)
        public native byte b2();
        @StructMember(2)
        public native MixedStructUnion b2(byte b);
    }
    
    @org.robovm.rt.bro.annotation.Marshaler(type = String.class, value = StringMarshaler.class) 
    public static final class TestStruct extends Struct<TestStruct> {
        @StructMember(0)
        public native byte b();
        @StructMember(0)
        public native TestStruct b(byte b);
        @StructMember(0)
        public native byte getB();
        @StructMember(0)
        public native void setB(byte b);
        @StructMember(1)
        public native long l();
        @StructMember(1)
        public native TestStruct l(long l);
        @StructMember(1)
        public native long getL();
        @StructMember(1)
        public native void setL(long b);
        @StructMember(2)
        public native char c();
        @StructMember(2)
        public native TestStruct c(char b);
        @StructMember(2)
        public native char getC();
        @StructMember(2)
        public native void setC(char b);
        @StructMember(3)
        public native int i();
        @StructMember(3)
        public native TestStruct i(int i);
        @StructMember(3)
        public native int getI();
        @StructMember(3)
        public native void setI(int i);
        
        @StructMember(4)
        public native @ByVal Point pointByVal();
        @StructMember(4)
        public native TestStruct pointByVal(@ByVal Point p);
        @StructMember(5)
        public native @ByRef Point pointByRef();
        @StructMember(5)
        public native TestStruct pointByRef(@ByRef Point p);
        
        @StructMember(6)
        public native TestStruct recursive();
        @StructMember(6)
        public native TestStruct recursive(TestStruct s);
        
        @StructMember(7)
        public native Ptr<Point> pointPtr();
        @StructMember(7)
        public native TestStruct pointPtr(Ptr<Point> ptr);
        
        @StructMember(8)
        public native Ptr<Ptr<Point>> pointPtrPtr();
        @StructMember(8)
        public native TestStruct pointPtrPtr(Ptr<Ptr<Point>> ptr);
        
        @StructMember(9)
        public native SimpleEnum simpleEnum();
        @StructMember(9)
        public native TestStruct simpleEnum(SimpleEnum e);
        
        @StructMember(10)
        public native TestValuedEnum valuedEnum();
        @StructMember(10)
        public native TestStruct valuedEnum(TestValuedEnum e);
        
        @StructMember(11)
        public native TestBits bits();
        @StructMember(11)
        public native TestStruct bits(TestBits bits);

        @StructMember(12)
        public native String string();
        @StructMember(12)
        public native TestStruct string(String s);

        @StructMember(13)
        public native @ByVal TestUnion unionByVal();
        @StructMember(13)
        public native TestStruct unionByVal(@ByVal TestUnion u);
        
        @StructMember(13)
        public native long unionByValAsLong();
        @StructMember(13)
        public native TestStruct unionByValAsLong(long l);
    }

    public static final class StructWithArray extends Struct<StructWithArray> {
        @StructMember(0)
        public native @Array({2, 3, 4}) @ByVal IntPtr intArray();
        @StructMember(0)
        public native StructWithArray intArray(@Array({2, 3, 4}) @ByVal IntPtr p);
    }
    
    @Test
    public void testSimpleMembers() {
        TestStruct s = new TestStruct();
        
        assertEquals(0, s.b());
        s.b((byte) 123);
        assertEquals(123, VM.getByte(s.getHandle()));
        assertEquals(123, s.b());
        
        assertEquals(0, s.l());
        s.l(0x9012345612345678L);
        assertEquals(0x9012345612345678L, VM.getLong(s.getHandle() + 4));
        assertEquals(0x9012345612345678L, s.l());
        
        assertEquals(0, s.c());
        s.c((char) 9876);
        assertEquals(9876, VM.getChar(s.getHandle() + 12));
        assertEquals(9876, s.c());

        assertEquals(0, s.i());
        s.i(0x12345678);
        assertEquals(0x12345678, VM.getInt(s.getHandle() + 16));
        assertEquals(0x12345678, s.i());
        
        assertEquals(123, s.b());
        assertEquals(0x9012345612345678L, s.l());
        assertEquals(9876, s.c());
        assertEquals(0x12345678, s.i());
    }
    
    @Test
    public void testByValMember() {
        TestStruct s = new TestStruct();
        
        assertEquals(0, s.pointByVal().x());
        assertEquals(0, s.pointByVal().y());
        s.pointByVal().x(1).y(2);
        assertEquals(1, s.pointByVal().x());
        assertEquals(2, s.pointByVal().y());
        Point p = new Point().x(20).y(40);
        s.pointByVal(p);
        assertTrue(s.pointByVal().getHandle() != p.getHandle());
        assertEquals(20, s.pointByVal().x());
        assertEquals(40, s.pointByVal().y());
        s.pointByVal().x(200).y(400);
        assertEquals(200, s.pointByVal().x());
        assertEquals(400, s.pointByVal().y());
        assertEquals(20, p.x());
        assertEquals(40, p.y());
    }
    
    @Test
    public void testByRefMember() {
        TestStruct s = new TestStruct();
        
        assertNull(s.pointByRef());
        Point p = new Point().x(20).y(40);
        s.pointByRef(p);
        assertEquals(s.pointByRef().getHandle(), p.getHandle());
        assertEquals(20, s.pointByRef().x());
        assertEquals(40, s.pointByRef().y());
        s.pointByRef().x(200).y(400);
        assertEquals(200, s.pointByRef().x());
        assertEquals(400, s.pointByRef().y());
        assertEquals(200, p.x());
        assertEquals(400, p.y());
    }
    
    @Test
    public void testSetterChaining() {
        TestStruct s = new TestStruct()
            .b((byte) 123)
            .l(0x9012345612345678L)
            .c((char) 9876)
            .i(0x12345678);

        assertEquals(123, s.b());
        assertEquals(0x9012345612345678L, s.l());
        assertEquals(9876, s.c());
        assertEquals(0x12345678, s.i());
        assertEquals(123, s.getB());
        assertEquals(0x9012345612345678L, s.getL());
        assertEquals(9876, s.getC());
        assertEquals(0x12345678, s.getI());
    }

    @Test
    public void testJavaBeanLikeSetters() {
        TestStruct s = new TestStruct();
        s.setB((byte) 123);
        s.setL(0x9012345612345678L);
        s.setC((char) 9876);
        s.setI(0x12345678);

        assertEquals(123, s.b());
        assertEquals(0x9012345612345678L, s.l());
        assertEquals(9876, s.c());
        assertEquals(0x12345678, s.i());
        assertEquals(123, s.getB());
        assertEquals(0x9012345612345678L, s.getL());
        assertEquals(9876, s.getC());
        assertEquals(0x12345678, s.getI());
    }

    @Test
    public void testPtrMember() {
        TestStruct s = new TestStruct();
        assertNull(s.pointPtr());
        Ptr<Point> ptr = Ptr.newPtr(Point.class);
        s.pointPtr(ptr);
        assertEquals(ptr, s.pointPtr());
        Point p = new Point().x(10).y(20);
        ptr.set(p);
        assertEquals(p, s.pointPtr().get());
        s.pointPtr().set(null);
        assertNull(ptr.get());
    }

    @Test
    public void testPtrPtrMember() {
        TestStruct s = new TestStruct();
        assertNull(s.pointPtrPtr());
        Ptr<Ptr<Point>> ptrOuter = Ptr.newPtrPtr(Point.class);
        s.pointPtrPtr(ptrOuter);
        assertEquals(ptrOuter, s.pointPtrPtr());
        assertNull(s.pointPtrPtr().get());
        Ptr<Point> ptrInner = Ptr.newPtr(Point.class);
        ptrOuter.set(ptrInner);
        assertEquals(ptrInner, s.pointPtrPtr().get());
        Point p = new Point().x(10).y(20);
        ptrInner.set(p);
        assertEquals(p, s.pointPtrPtr().get().get());
        s.pointPtrPtr().get().set(null);
        assertNull(ptrInner.get());
        s.pointPtrPtr().set(null);
        assertNull(ptrOuter.get());
    }
    
    @Test
    public void testRecursiveMember() {
        TestStruct s = new TestStruct();
        assertNull(s.recursive());
        s.recursive(s);
        assertEquals(s, s.recursive());
    }
    
    @Test
    public void testSimpleEnumMember() {
        TestStruct s = new TestStruct();
        assertEquals(SimpleEnum.V1, s.simpleEnum());
        s.simpleEnum(SimpleEnum.V2);
        assertEquals(SimpleEnum.V2, s.simpleEnum());
        s.simpleEnum(SimpleEnum.V3);
        assertEquals(SimpleEnum.V3, s.simpleEnum());
        s.simpleEnum(SimpleEnum.V1);
        assertEquals(SimpleEnum.V1, s.simpleEnum());
    }
    
    @Test
    public void testValuedEnumMember() {
        TestStruct s = new TestStruct();
        try {
            // No constant with value 0
            s.valuedEnum();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
        }
        s.valuedEnum(TestValuedEnum.V100);
        assertEquals(TestValuedEnum.V100, s.valuedEnum());
        s.valuedEnum(TestValuedEnum.V1000);
        assertEquals(TestValuedEnum.V1000, s.valuedEnum());
        s.valuedEnum(TestValuedEnum.V10000);
        assertEquals(TestValuedEnum.V10000, s.valuedEnum());
    }
    
    @Test
    public void testBitsMember() {
        TestStruct s = new TestStruct();
        assertEquals(0, s.bits().value());
        s.bits(TestBits.V1);
        assertEquals(TestBits.V1, s.bits());
        s.bits(s.bits().set(TestBits.V4));
        assertEquals(1 | 4, s.bits().value());
    }
    
    @Test
    public void testBytePtrMemberMarshaledAsString() {
        TestStruct s = new TestStruct();
        assertEquals(null, s.string());
        s.string("Foo bar");
        assertEquals("Foo bar", s.string());
    }

    @Test
    public void testSimpleUnion() {
        assertEquals(8, TestUnion.sizeOf());
        TestUnion s = new TestUnion();
        
        s.d(Math.PI);
        assertEquals(Math.PI, s.d(), 0.0001);
        assertEquals(Double.doubleToLongBits(Math.PI), s.l());
        assertEquals(Double.doubleToLongBits(Math.PI) & 0xff, s.b());
        assertEquals(Double.doubleToLongBits(Math.PI) & 0xffffffffl, s.p().x() & 0xffffffffl);
        assertEquals(Double.doubleToLongBits(Math.PI) >>> 32, s.p().y() & 0xffffffffl);
        
        s.l(0x123456789abcdef0l);
        assertEquals(Double.longBitsToDouble(0x123456789abcdef0l), s.d(), 0.0001);
        assertEquals(0x123456789abcdef0l, s.l());
        assertEquals(0xf0, s.b() & 0xffl);
        assertEquals(0x9abcdef0l, s.p().x() & 0xffffffffl);
        assertEquals(0x12345678l, s.p().y() & 0xffffffffl);
        
        s.b((byte) 0xe4);
        assertEquals(Double.longBitsToDouble(0x123456789abcdee4l), s.d(), 0.0001);
        assertEquals(0x123456789abcdee4l, s.l());
        assertEquals(0xe4, s.b() & 0xffl);
        assertEquals(0x9abcdee4l, s.p().x() & 0xffffffffl);
        assertEquals(0x12345678l, s.p().y() & 0xffffffffl);

        s.p().x(0x10002000).y(0x30004000);
        assertEquals(Double.longBitsToDouble(0x3000400010002000l), s.d(), 0.0001);
        assertEquals("6", 0x3000400010002000l, s.l());
        assertEquals(0x00, s.b() & 0xffl);
        assertEquals("7", 0x10002000l, s.p().x() & 0xffffffffl);
        assertEquals("8", 0x30004000l, s.p().y() & 0xffffffffl);
    }
    
    @Test
    public void testMixedStructUnion() {
        assertEquals(16, MixedStructUnion.sizeOf());
        MixedStructUnion s = new MixedStructUnion();

        assertEquals(0, s.b1());
        s.b1((byte) 0x34);
        assertEquals(0x34, s.b1());
        assertEquals(0x34, VM.getByte(s.getHandle()));

        assertEquals(0, s.l());
        s.l(0x1234567890abcdefl);
        assertEquals(0x34, s.b1());
        assertEquals(0x1234567890abcdefl, s.l());
        assertEquals(0x1234567890abcdefl, VM.getLong(s.getHandle() + 4));

        assertEquals(Double.longBitsToDouble(0x1234567890abcdefl), s.d(), 0.00001);
        s.d(Math.PI);
        assertEquals(0x34, s.b1());
        assertEquals(Double.doubleToLongBits(Math.PI), s.l());
        assertEquals(Math.PI, VM.getDouble(s.getHandle() + 4), 0.00001);

        assertEquals(0, s.b2());
        s.b2((byte) 0x43);
        assertEquals(0x43, s.b2());
        assertEquals(0x43, VM.getByte(s.getHandle() + 12));
    }
    
    @Test
    public void testUnionByVal() {
        TestStruct s = new TestStruct();
        TestUnion u = s.unionByVal();
        s.unionByValAsLong(0x1234567890abcdefL);
        assertEquals(0x1234567890abcdefL, s.unionByValAsLong());
        assertEquals(0x1234567890abcdefL, u.l());
        u.l(0xfedcba0987654321L);
        assertEquals(0xfedcba0987654321L, s.unionByValAsLong());
        assertEquals(0xfedcba0987654321L, u.l());
        u.d(Math.PI);
        assertEquals(Double.doubleToLongBits(Math.PI), s.unionByValAsLong());
        assertEquals(Math.PI, u.d(), 0.00001);
        
        TestUnion v = new TestUnion();
        v.l(0x6372819372612746L);
        s.unionByVal(v);
        assertEquals(0x6372819372612746L, s.unionByValAsLong());
        assertEquals(0x6372819372612746L, u.l());
    }
    
    @Test
    public void testStructWithArray() {
        assertEquals(96, StructWithArray.sizeOf());
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArray();
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < 24; i++) {
            p.next(i).set(i + 1);
        }
        
        IntPtr q = s.intArray();
        for (int i = 0; i < 24; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        IntPtr r = Struct.allocate(IntPtr.class, 24);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < 24; i++) {
            r.next(i).set(2 * (i + 1));
        }
        s.intArray(r);
        for (int i = 0; i < 24; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
    }
}
