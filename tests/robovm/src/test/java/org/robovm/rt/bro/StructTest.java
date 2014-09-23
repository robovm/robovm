/*
 * Copyright (C) 2012 Trillian Mobile AB
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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Array;
import org.robovm.rt.bro.annotation.ByRef;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.CharPtr;
import org.robovm.rt.bro.ptr.DoublePtr;
import org.robovm.rt.bro.ptr.FloatPtr;
import org.robovm.rt.bro.ptr.IntPtr;
import org.robovm.rt.bro.ptr.LongPtr;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.ShortPtr;

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
        protected TestBits[] _values() {
            return VALUES;
        }
    }
    
    public static class StringMarshaler {
        static List<String> calls = new ArrayList<String>();
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            BytePtr ptr = Struct.toStruct(BytePtr.class, handle);
            String o = ptr != null ? ptr.toStringAsciiZ() : null;
            String s = o == null ? null : "'" + o + "'";
            calls.add("toObject(" + s + ", ?, " + Long.toHexString(flags) + ")");
            return o;
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            String s = o == null ? null : "'" + o + "'";
            calls.add("toNative(" + s + ", ?, " + Long.toHexString(flags) + ")");
            if (o == null) {
                return 0L;
            }
            BytePtr ptr = BytePtr.toBytePtrAsciiZ((String) o);
            return ptr.getHandle();
        }
        public static void updateObject(Object o, long handle, long flags) {
            String s = o == null ? null : "'" + o + "'";
            calls.add("updateObject(" + s + ", ?, " + Long.toHexString(flags) + ")");
        }
        public static void updateNative(Object o, long handle, long flags) {
            String s = o == null ? null : "'" + o + "'";
            calls.add("updateNative(" + s + ", ?, " + Long.toHexString(flags) + ")");
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
    
    public static final class PointPtr extends Ptr<Point, PointPtr> {}
    public static final class PointPtrPtr extends Ptr<PointPtr, PointPtrPtr> {}
    
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
    
    @org.robovm.rt.bro.annotation.Marshaler(StringMarshaler.class) 
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
        public native PointPtr pointPtr();
        @StructMember(7)
        public native TestStruct pointPtr(PointPtr ptr);
        
        @StructMember(8)
        public native PointPtrPtr pointPtrPtr();
        @StructMember(8)
        public native TestStruct pointPtrPtr(PointPtrPtr ptr);
        
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
        public native @Array({2, 3, 4}) BytePtr byteArrayAsPtr();
        @StructMember(0)
        public native StructWithArray byteArrayAsPtr(@Array({2, 3, 4}) BytePtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) ByteBuffer byteArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray byteArrayAsBuffer(@Array({2, 3, 4}) ByteBuffer p);
        @StructMember(0)
        public native @Array(24) byte[] byteArray1D();
        @StructMember(0)
        public native StructWithArray byteArray1D(@Array(24) byte[] p);
        @StructMember(0)
        public native @Array({3, 8}) byte[][] byteArray2D();
        @StructMember(0)
        public native StructWithArray byteArray2D(@Array({3, 8}) byte[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) byte[][][] byteArray3D();
        @StructMember(0)
        public native StructWithArray byteArray3D(@Array({2, 3, 4}) byte[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) ShortPtr shortArrayAsPtr();
        @StructMember(0)
        public native StructWithArray shortArrayAsPtr(@Array({2, 3, 4}) ShortPtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) ShortBuffer shortArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray shortArrayAsBuffer(@Array({2, 3, 4}) ShortBuffer p);
        @StructMember(0)
        public native @Array(24) short[] shortArray1D();
        @StructMember(0)
        public native StructWithArray shortArray1D(@Array(24) short[] p);
        @StructMember(0)
        public native @Array({3, 8}) short[][] shortArray2D();
        @StructMember(0)
        public native StructWithArray shortArray2D(@Array({3, 8}) short[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) short[][][] shortArray3D();
        @StructMember(0)
        public native StructWithArray shortArray3D(@Array({2, 3, 4}) short[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) CharPtr charArrayAsPtr();
        @StructMember(0)
        public native StructWithArray charArrayAsPtr(@Array({2, 3, 4}) CharPtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) CharBuffer charArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray charArrayAsBuffer(@Array({2, 3, 4}) CharBuffer p);
        @StructMember(0)
        public native @Array(24) char[] charArray1D();
        @StructMember(0)
        public native StructWithArray charArray1D(@Array(24) char[] p);
        @StructMember(0)
        public native @Array({3, 8}) char[][] charArray2D();
        @StructMember(0)
        public native StructWithArray charArray2D(@Array({3, 8}) char[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) char[][][] charArray3D();
        @StructMember(0)
        public native StructWithArray charArray3D(@Array({2, 3, 4}) char[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) IntPtr intArrayAsPtr();
        @StructMember(0)
        public native StructWithArray intArrayAsPtr(@Array({2, 3, 4}) IntPtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) IntBuffer intArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray intArrayAsBuffer(@Array({2, 3, 4}) IntBuffer p);
        @StructMember(0)
        public native @Array(24) int[] intArray1D();
        @StructMember(0)
        public native StructWithArray intArray1D(@Array(24) int[] p);
        @StructMember(0)
        public native @Array({3, 8}) int[][] intArray2D();
        @StructMember(0)
        public native StructWithArray intArray2D(@Array({3, 8}) int[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) int[][][] intArray3D();
        @StructMember(0)
        public native StructWithArray intArray3D(@Array({2, 3, 4}) int[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) LongPtr longArrayAsPtr();
        @StructMember(0)
        public native StructWithArray longArrayAsPtr(@Array({2, 3, 4}) LongPtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) LongBuffer longArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray longArrayAsBuffer(@Array({2, 3, 4}) LongBuffer p);
        @StructMember(0)
        public native @Array(24) long[] longArray1D();
        @StructMember(0)
        public native StructWithArray longArray1D(@Array(24) long[] p);
        @StructMember(0)
        public native @Array({3, 8}) long[][] longArray2D();
        @StructMember(0)
        public native StructWithArray longArray2D(@Array({3, 8}) long[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) long[][][] longArray3D();
        @StructMember(0)
        public native StructWithArray longArray3D(@Array({2, 3, 4}) long[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) FloatPtr floatArrayAsPtr();
        @StructMember(0)
        public native StructWithArray floatArrayAsPtr(@Array({2, 3, 4}) FloatPtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) FloatBuffer floatArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray floatArrayAsBuffer(@Array({2, 3, 4}) FloatBuffer p);
        @StructMember(0)
        public native @Array(24) float[] floatArray1D();
        @StructMember(0)
        public native StructWithArray floatArray1D(@Array(24) float[] p);
        @StructMember(0)
        public native @Array({3, 8}) float[][] floatArray2D();
        @StructMember(0)
        public native StructWithArray floatArray2D(@Array({3, 8}) float[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) float[][][] floatArray3D();
        @StructMember(0)
        public native StructWithArray floatArray3D(@Array({2, 3, 4}) float[][][] p);

        @StructMember(0)
        public native @Array({2, 3, 4}) DoublePtr doubleArrayAsPtr();
        @StructMember(0)
        public native StructWithArray doubleArrayAsPtr(@Array({2, 3, 4}) DoublePtr p);
        @StructMember(0)
        public native @Array({2, 3, 4}) DoubleBuffer doubleArrayAsBuffer();
        @StructMember(0)
        public native StructWithArray doubleArrayAsBuffer(@Array({2, 3, 4}) DoubleBuffer p);
        @StructMember(0)
        public native @Array(24) double[] doubleArray1D();
        @StructMember(0)
        public native StructWithArray doubleArray1D(@Array(24) double[] p);
        @StructMember(0)
        public native @Array({3, 8}) double[][] doubleArray2D();
        @StructMember(0)
        public native StructWithArray doubleArray2D(@Array({3, 8}) double[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) double[][][] doubleArray3D();
        @StructMember(0)
        public native StructWithArray doubleArray3D(@Array({2, 3, 4}) double[][][] p);
        
        @StructMember(0)
        public native @Array({2, 3, 4}) Point pointArrayAsPtr();
        @StructMember(0)
        public native StructWithArray pointArrayAsPtr(@Array({2, 3, 4}) Point p);
        @StructMember(0)
        public native @Array(24) Point[] pointArray1D();
        @StructMember(0)
        public native StructWithArray pointArray1D(@Array(24) Point[] p);
        @StructMember(0)
        public native @Array({3, 8}) Point[][] pointArray2D();
        @StructMember(0)
        public native StructWithArray pointArray2D(@Array({3, 8}) Point[][] p);
        @StructMember(0)
        public native @Array({2, 3, 4}) Point[][][] pointArray3D();
        @StructMember(0)
        public native StructWithArray pointArray3D(@Array({2, 3, 4}) Point[][][] p);

        @StructMember(0)
        public native @Array(24) String byteArrayAsString();
        @StructMember(0)
        public native StructWithArray byteArrayAsString(@Array(24) String s);
    }
    
    public static final class MachineSizedStruct extends Struct<MachineSizedStruct> {
        @StructMember(0)
        public native @MachineSizedFloat double machineSizedFloatD();
        @StructMember(0)
        public native MachineSizedStruct machineSizedFloatD(@MachineSizedFloat double d);
        @StructMember(0)
        public native @MachineSizedFloat float machineSizedFloatF();
        @StructMember(0)
        public native MachineSizedStruct machineSizedFloatF(@MachineSizedFloat float f);
        @StructMember(0)
        public native @MachineSizedSInt long machineSizedSInt();
        @StructMember(0)
        public native MachineSizedStruct machineSizedSInt(@MachineSizedSInt long l);
        @StructMember(0)
        public native @MachineSizedUInt long machineSizedUInt();
        @StructMember(0)
        public native MachineSizedStruct machineSizedUInt(@MachineSizedUInt long l);
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
        PointPtr ptr = new PointPtr();
        s.pointPtr(ptr);
        assertEquals(ptr, s.pointPtr());
        Point p = new Point().x(10).y(20);
        ptr.set(p);
        assertEquals(p, s.pointPtr().get());
        s.pointPtr().set((Point) null);
        assertNull(ptr.get());
    }

    @Test
    public void testPtrPtrMember() {
        TestStruct s = new TestStruct();
        assertNull(s.pointPtrPtr());
        PointPtrPtr ptrOuter = new PointPtrPtr();
        s.pointPtrPtr(ptrOuter);
        assertEquals(ptrOuter, s.pointPtrPtr());
        assertNull(s.pointPtrPtr().get());
        PointPtr ptrInner = new PointPtr();
        ptrOuter.set(ptrInner);
        assertEquals(ptrInner, s.pointPtrPtr().get());
        Point p = new Point().x(10).y(20);
        ptrInner.set(p);
        assertEquals(p, s.pointPtrPtr().get().get());
        s.pointPtrPtr().get().set((Point) null);
        assertNull(ptrInner.get());
        s.pointPtrPtr().set((PointPtr) null);
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
    public void testStructWithArrayByteArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();
        BytePtr q;
        BytePtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((byte) (i + 1));
        }
        
        q = s.byteArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        r = Struct.allocate(BytePtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set((byte) (2 * (i + 1)));
        }
        s.byteArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xff);
        }
    }
    
    @Test
    public void testStructWithArrayByteArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();
        ByteBuffer b1;
        ByteBuffer b2;
        ByteBuffer b3;
        ByteBuffer b4;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((byte) (i + 1));
        }
        
        b1 = s.byteArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i));
        }
        
        b2 = ByteBuffer.allocateDirect(D1);
        for (int i = 0; i < D1; i++) {
            b2.put(i, (byte) (2 * (i + 1)));
        }
        s.byteArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xff);
        }
        
        b3 = ByteBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, (byte) (3 * (i + 1)));
        }
        s.byteArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get() & 0xff);
        }

        b4 = ByteBuffer.allocate(D1);
        assertFalse(b4.isDirect());
        for (int i = 0; i < D1; i++) {
            b4.put(i, (byte) (4 * (i + 1)));
        }
        s.byteArrayAsBuffer((ByteBuffer) b4.asReadOnlyBuffer().flip());
        for (int i = 0; i < D1; i++) {
            assertEquals(4 * (i + 1), p.next(i).get() & 0xff);
        }

        try {
            s.byteArrayAsBuffer(ByteBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayByteArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        byte[] array1;
        byte[] array2;
        byte[] array3;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();

        array1 = s.byteArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i]);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((byte) (i + 1));
        }

        array2 = s.byteArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
        }
        
        array3 = new byte[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = (byte) (2 * (i + 1));
        }
        s.byteArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xff);
        }

        try {
            s.byteArray1D(new byte[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayByteArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        byte[][] array1;
        byte[][] array2;
        byte[][] array3;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();
        
        array1 = s.byteArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j]);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set((byte) (i + 1));
        }

        array2 = s.byteArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j]);
            }
        }
        
        array3 = new byte[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = (byte) (2 * (i * D2 + j + 1));
            }
        }
        s.byteArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xff);
        }
        
        try {
            s.byteArray2D(new byte[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.byteArray2D(new byte[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayByteArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        byte[][][] array1;
        byte[][][] array2;
        byte[][][] array3;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();

        array1 = s.byteArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k]);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set((byte) (i + 1));
        }

        array2 = s.byteArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k]);
                }
            }
        }
        
        array3 = new byte[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = (byte) (2 * ((i * D2 + j) * D3 + k + 1));
                }
            }
        }
        s.byteArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xff);
        }
        
        try {
            s.byteArray3D(new byte[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.byteArray3D(new byte[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.byteArray3D(new byte[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayShortArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        ShortPtr p = s.shortArrayAsPtr();
        ShortPtr q;
        ShortPtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((short) (i + 1));
        }
        
        q = s.shortArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        r = Struct.allocate(ShortPtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set((short) (2 * (i + 1)));
        }
        s.shortArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
    }
    
    @Test
    public void testStructWithArrayShortArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        ShortPtr p = s.shortArrayAsPtr();
        ShortBuffer b1;
        ShortBuffer b2;
        ShortBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((short) (i + 1));
        }
        
        b1 = s.shortArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i));
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, (short) (2 * (i + 1)));
        }
        s.shortArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        b3 = ShortBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, (short) (3 * (i + 1)));
        }
        s.shortArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.shortArrayAsBuffer(ShortBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayShortArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        short[] array1;
        short[] array2;
        short[] array3;
        StructWithArray s = new StructWithArray();
        ShortPtr p = s.shortArrayAsPtr();

        array1 = s.shortArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i]);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((short) (i + 1));
        }

        array2 = s.shortArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
        }
        
        array3 = new short[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = (short) (2 * (i + 1));
        }
        s.shortArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }

        try {
            s.shortArray1D(new short[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayShortArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        short[][] array1;
        short[][] array2;
        short[][] array3;
        StructWithArray s = new StructWithArray();
        ShortPtr p = s.shortArrayAsPtr();
        
        array1 = s.shortArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j]);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set((short) (i + 1));
        }

        array2 = s.shortArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j]);
            }
        }
        
        array3 = new short[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = (short) (2 * (i * D2 + j + 1));
            }
        }
        s.shortArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.shortArray2D(new short[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.shortArray2D(new short[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayShortArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        short[][][] array1;
        short[][][] array2;
        short[][][] array3;
        StructWithArray s = new StructWithArray();
        ShortPtr p = s.shortArrayAsPtr();

        array1 = s.shortArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k]);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set((short) (i + 1));
        }

        array2 = s.shortArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k]);
                }
            }
        }
        
        array3 = new short[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = (short) (2 * ((i * D2 + j) * D3 + k + 1));
                }
            }
        }
        s.shortArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.shortArray3D(new short[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.shortArray3D(new short[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.shortArray3D(new short[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayCharArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        CharPtr p = s.charArrayAsPtr();
        CharPtr q;
        CharPtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((char) (i + 1));
        }
        
        q = s.charArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        r = Struct.allocate(CharPtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set((char) (2 * (i + 1)));
        }
        s.charArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
    }
    

    @Test
    public void testStructWithArrayCharArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        CharPtr p = s.charArrayAsPtr();
        CharBuffer b1;
        CharBuffer b2;
        CharBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((char) (i + 1));
        }
        
        b1 = s.charArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i));
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 2).order(ByteOrder.nativeOrder()).asCharBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, (char) (2 * (i + 1)));
        }
        s.charArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        b3 = CharBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, (char) (3 * (i + 1)));
        }
        s.charArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.charArrayAsBuffer(CharBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayCharArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        char[] array1;
        char[] array2;
        char[] array3;
        StructWithArray s = new StructWithArray();
        CharPtr p = s.charArrayAsPtr();

        array1 = s.charArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i]);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set((char) (i + 1));
        }

        array2 = s.charArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
        }
        
        array3 = new char[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = (char) (2 * (i + 1));
        }
        s.charArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }

        try {
            s.charArray1D(new char[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayCharArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        char[][] array1;
        char[][] array2;
        char[][] array3;
        StructWithArray s = new StructWithArray();
        CharPtr p = s.charArrayAsPtr();
        
        array1 = s.charArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j]);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set((char) (i + 1));
        }

        array2 = s.charArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j]);
            }
        }
        
        array3 = new char[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = (char) (2 * (i * D2 + j + 1));
            }
        }
        s.charArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.charArray2D(new char[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.charArray2D(new char[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayCharArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        char[][][] array1;
        char[][][] array2;
        char[][][] array3;
        StructWithArray s = new StructWithArray();
        CharPtr p = s.charArrayAsPtr();

        array1 = s.charArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k]);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set((char) (i + 1));
        }

        array2 = s.charArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k]);
                }
            }
        }
        
        array3 = new char[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = (char) (2 * ((i * D2 + j) * D3 + k + 1));
                }
            }
        }
        s.charArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get() & 0xffff);
        }
        
        try {
            s.charArray3D(new char[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.charArray3D(new char[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.charArray3D(new char[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayIntArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArrayAsPtr();
        IntPtr q;
        IntPtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        q = s.intArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        r = Struct.allocate(IntPtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set(2 * (i + 1));
        }
        s.intArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
    }
    
    @Test
    public void testStructWithArrayIntArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArrayAsPtr();
        IntBuffer b1;
        IntBuffer b2;
        IntBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        b1 = s.intArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i));
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, 2 * (i + 1));
        }
        s.intArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        b3 = IntBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, 3 * (i + 1));
        }
        s.intArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get());
        }
        
        try {
            s.intArrayAsBuffer(IntBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayIntArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        int[] array1;
        int[] array2;
        int[] array3;
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArrayAsPtr();

        array1 = s.intArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i]);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.intArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
        }
        
        array3 = new int[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = 2 * (i + 1);
        }
        s.intArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }

        try {
            s.intArray1D(new int[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayIntArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        int[][] array1;
        int[][] array2;
        int[][] array3;
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArrayAsPtr();
        
        array1 = s.intArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j]);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.intArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j]);
            }
        }
        
        array3 = new int[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = 2 * (i * D2 + j + 1);
            }
        }
        s.intArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        try {
            s.intArray2D(new int[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.intArray2D(new int[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayIntArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        int[][][] array1;
        int[][][] array2;
        int[][][] array3;
        StructWithArray s = new StructWithArray();
        IntPtr p = s.intArrayAsPtr();

        array1 = s.intArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k]);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.intArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k]);
                }
            }
        }
        
        array3 = new int[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = 2 * ((i * D2 + j) * D3 + k + 1);
                }
            }
        }
        s.intArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        try {
            s.intArray3D(new int[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.intArray3D(new int[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.intArray3D(new int[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayLongArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        LongPtr p = s.longArrayAsPtr();
        LongPtr q;
        LongPtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        q = s.longArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get());
        }
        
        r = Struct.allocate(LongPtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set(2 * (i + 1));
        }
        s.longArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
    }
    
    @Test
    public void testStructWithArrayLongArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        LongPtr p = s.longArrayAsPtr();
        LongBuffer b1;
        LongBuffer b2;
        LongBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        b1 = s.longArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i));
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 8).order(ByteOrder.nativeOrder()).asLongBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, 2 * (i + 1));
        }
        s.longArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        b3 = LongBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, 3 * (i + 1));
        }
        s.longArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get());
        }
        
        try {
            s.longArrayAsBuffer(LongBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayLongArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        long[] array1;
        long[] array2;
        long[] array3;
        StructWithArray s = new StructWithArray();
        LongPtr p = s.longArrayAsPtr();

        array1 = s.longArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i]);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.longArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
        }
        
        array3 = new long[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = 2 * (i + 1);
        }
        s.longArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }

        try {
            s.longArray1D(new long[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayLongArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        long[][] array1;
        long[][] array2;
        long[][] array3;
        StructWithArray s = new StructWithArray();
        LongPtr p = s.longArrayAsPtr();
        
        array1 = s.longArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j]);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.longArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j]);
            }
        }
        
        array3 = new long[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = 2 * (i * D2 + j + 1);
            }
        }
        s.longArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        try {
            s.longArray2D(new long[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.longArray2D(new long[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayLongArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        long[][][] array1;
        long[][][] array2;
        long[][][] array3;
        StructWithArray s = new StructWithArray();
        LongPtr p = s.longArrayAsPtr();

        array1 = s.longArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k]);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.longArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k]);
                }
            }
        }
        
        array3 = new long[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = 2 * ((i * D2 + j) * D3 + k + 1);
                }
            }
        }
        s.longArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get());
        }
        
        try {
            s.longArray3D(new long[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.longArray3D(new long[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.longArray3D(new long[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayFloatArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        FloatPtr p = s.floatArrayAsPtr();
        FloatPtr q;
        FloatPtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        q = s.floatArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get(), 0.0001f);
        }
        
        r = Struct.allocate(FloatPtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set(2 * (i + 1));
        }
        s.floatArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001f);
        }
    }
    
    @Test
    public void testStructWithArrayFloatArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        FloatPtr p = s.floatArrayAsPtr();
        FloatBuffer b1;
        FloatBuffer b2;
        FloatBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        b1 = s.floatArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i), 0.0001f);
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, 2 * (i + 1));
        }
        s.floatArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001f);
        }
        
        b3 = FloatBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, 3 * (i + 1));
        }
        s.floatArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get(), 0.0001f);
        }
        
        try {
            s.floatArrayAsBuffer(FloatBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayFloatArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        float[] array1;
        float[] array2;
        float[] array3;
        StructWithArray s = new StructWithArray();
        FloatPtr p = s.floatArrayAsPtr();

        array1 = s.floatArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i], 0.0001f);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.floatArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i], 0.0001f);
        }
        
        array3 = new float[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = 2 * (i + 1);
        }
        s.floatArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001f);
        }

        try {
            s.floatArray1D(new float[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayFloatArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        float[][] array1;
        float[][] array2;
        float[][] array3;
        StructWithArray s = new StructWithArray();
        FloatPtr p = s.floatArrayAsPtr();
        
        array1 = s.floatArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j], 0.0001f);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.floatArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j], 0.0001f);
            }
        }
        
        array3 = new float[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = 2 * (i * D2 + j + 1);
            }
        }
        s.floatArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001f);
        }
        
        try {
            s.floatArray2D(new float[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.floatArray2D(new float[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayFloatArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        float[][][] array1;
        float[][][] array2;
        float[][][] array3;
        StructWithArray s = new StructWithArray();
        FloatPtr p = s.floatArrayAsPtr();

        array1 = s.floatArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k], 0.0001f);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.floatArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k], 0.0001f);
                }
            }
        }
        
        array3 = new float[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = 2 * ((i * D2 + j) * D3 + k + 1);
                }
            }
        }
        s.floatArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001f);
        }
        
        try {
            s.floatArray3D(new float[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.floatArray3D(new float[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.floatArray3D(new float[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayDoubleArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        DoublePtr p = s.doubleArrayAsPtr();
        DoublePtr q;
        DoublePtr r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        q = s.doubleArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, q.next(i).get(), 0.0001);
        }
        
        r = Struct.allocate(DoublePtr.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).set(2 * (i + 1));
        }
        s.doubleArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001);
        }
    }
    
    @Test
    public void testStructWithArrayDoubleArrayAsBuffer() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        DoublePtr p = s.doubleArrayAsPtr();
        DoubleBuffer b1;
        DoubleBuffer b2;
        DoubleBuffer b3;
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }
        
        b1 = s.doubleArrayAsBuffer();
        assertEquals(D1, b1.capacity());
        assertEquals(D1, b1.limit());
        assertEquals(0, b1.position());
        for (int i = 0; i < D1; i++) {
            assertEquals(i + 1, b1.get(i), 0.0001);
        }
        
        b2 = ByteBuffer.allocateDirect(D1 * 8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
        for (int i = 0; i < D1; i++) {
            b2.put(i, 2 * (i + 1));
        }
        s.doubleArrayAsBuffer(b2);
        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001);
        }
        
        b3 = DoubleBuffer.allocate(D1);
        assertFalse(b3.isDirect());
        for (int i = 0; i < D1; i++) {
            b3.put(i, 3 * (i + 1));
        }
        s.doubleArrayAsBuffer(b3);
        for (int i = 0; i < D1; i++) {
            assertEquals(3 * (i + 1), p.next(i).get(), 0.0001);
        }
        
        try {
            s.doubleArrayAsBuffer(DoubleBuffer.allocate(D1 / 2));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayDoubleArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        double[] array1;
        double[] array2;
        double[] array3;
        StructWithArray s = new StructWithArray();
        DoublePtr p = s.doubleArrayAsPtr();

        array1 = s.doubleArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i], 0.0001);
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.doubleArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i], 0.0001);
        }
        
        array3 = new double[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = 2 * (i + 1);
        }
        s.doubleArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001);
        }

        try {
            s.doubleArray1D(new double[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayDoubleArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        double[][] array1;
        double[][] array2;
        double[][] array3;
        StructWithArray s = new StructWithArray();
        DoublePtr p = s.doubleArrayAsPtr();
        
        array1 = s.doubleArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j], 0.0001);
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.doubleArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(i * D2 + j + 1, array2[i][j], 0.0001);
            }
        }
        
        array3 = new double[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = 2 * (i * D2 + j + 1);
            }
        }
        s.doubleArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001);
        }
        
        try {
            s.doubleArray2D(new double[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.doubleArray2D(new double[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayDoubleArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        double[][][] array1;
        double[][][] array2;
        double[][][] array3;
        StructWithArray s = new StructWithArray();
        DoublePtr p = s.doubleArrayAsPtr();

        array1 = s.doubleArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k], 0.0001);
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).set(i + 1);
        }

        array2 = s.doubleArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals((i * D2 + j) * D3 + k + 1, array2[i][j][k], 0.0001);
                }
            }
        }
        
        array3 = new double[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = 2 * ((i * D2 + j) * D3 + k + 1);
                }
            }
        }
        s.doubleArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(2 * (i + 1), p.next(i).get(), 0.0001);
        }
        
        try {
            s.doubleArray3D(new double[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.doubleArray3D(new double[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.doubleArray3D(new double[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayPointArrayAsPtr() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        Point p = s.pointArrayAsPtr();
        Point q;
        Point r;
        assertEquals(s.getHandle(), p.getHandle());
        
        for (int i = 0; i < D1; i++) {
            p.next(i).x(100 * i).y(-100 * i);
        }
        
        q = s.pointArrayAsPtr();
        for (int i = 0; i < D1; i++) {
            assertEquals(100 * i, q.next(i).x());
            assertEquals(-100 * i, q.next(i).y());
        }
        
        r = Struct.allocate(Point.class, D1);
        assertNotEquals(s.getHandle(), r.getHandle());
        for (int i = 0; i < D1; i++) {
            r.next(i).x(-1000 * i).y(1000 * i);
        }
        s.pointArrayAsPtr(r);
        for (int i = 0; i < D1; i++) {
            assertEquals(-1000 * i, p.next(i).x());
            assertEquals(1000 * i, p.next(i).y());
        }
    }
    
    @Test
    public void testStructWithArrayPointArrayAs1D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 24;
        Point[] array1;
        Point[] array2;
        Point[] array3;
        StructWithArray s = new StructWithArray();
        Point p = s.pointArrayAsPtr();

        array1 = s.pointArray1D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(0, array1[i].x());
            assertEquals(0, array1[i].y());
        }
        
        for (int i = 0; i < D1; i++) {
            p.next(i).x(100 * i).y(-100 * i);
        }

        array2 = s.pointArray1D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(100 * i, array2[i].x());
            assertEquals(-100 * i, array2[i].y());
        }
        
        array3 = new Point[D1];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = new Point().x(-1000 * i).y(1000 * i);
        }
        s.pointArray1D(array3);

        for (int i = 0; i < D1; i++) {
            assertEquals(-1000 * i, p.next(i).x());
            assertEquals(1000 * i, p.next(i).y());
        }

        try {
            s.pointArray1D(new Point[D1 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayPointArrayAs2D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 3;
        final int D2 = 8;
        Point[][] array1;
        Point[][] array2;
        Point[][] array3;
        StructWithArray s = new StructWithArray();
        Point p = s.pointArrayAsPtr();
        
        array1 = s.pointArray2D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(0, array1[i][j].x());
                assertEquals(0, array1[i][j].y());
            }
        }
        
        for (int i = 0; i < D1 * D2; i++) {
            p.next(i).x(100 * i).y(-100 * i);
        }

        array2 = s.pointArray2D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(100 * (i * D2 + j), array2[i][j].x());
                assertEquals(-100 * (i * D2 + j), array2[i][j].y());
            }
        }
        
        array3 = new Point[D1][D2];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                array3[i][j] = new Point().x(-1000 * (i * D2 + j)).y(1000 * (i * D2 + j));
            }
        }
        s.pointArray2D(array3);

        for (int i = 0; i < D1 * D2; i++) {
            assertEquals(-1000 * i, p.next(i).x());
            assertEquals(1000 * i, p.next(i).y());
        }
        
        try {
            s.pointArray2D(new Point[D1 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.pointArray2D(new Point[D1][D2 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayPointArrayAs3D() {
        assertEquals(192, StructWithArray.sizeOf());
        
        final int D1 = 2;
        final int D2 = 3;
        final int D3 = 4;
        Point[][][] array1;
        Point[][][] array2;
        Point[][][] array3;
        StructWithArray s = new StructWithArray();
        Point p = s.pointArrayAsPtr();

        array1 = s.pointArray3D();
        assertEquals(D1, array1.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(D2, array1[i].length);
            for (int j = 0; j < array1[i].length; j++) {
                assertEquals(D3, array1[i][j].length);
                for (int k = 0; k < array1[i][j].length; k++) {
                    assertEquals(0, array1[i][j][k].x());
                    assertEquals(0, array1[i][j][k].y());
                }
            }
        }
        
        for (int i = 0; i < D1 * D2 * D3; i++) {
            p.next(i).x(100 * i).y(-100 * i);
        }

        array2 = s.pointArray3D();
        assertEquals(D1, array2.length);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(D2, array2[i].length);
            for (int j = 0; j < array2[i].length; j++) {
                assertEquals(D3, array2[i][j].length);
                for (int k = 0; k < array2[i][j].length; k++) {
                    assertEquals(100 * ((i * D2 + j) * D3 + k), array2[i][j][k].x());
                    assertEquals(-100 * ((i * D2 + j) * D3 + k), array2[i][j][k].y());
                }
            }
        }
        
        array3 = new Point[D1][D2][D3];
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[i].length; j++) {
                for (int k = 0; k < array3[i][j].length; k++) {
                    array3[i][j][k] = new Point().x(-1000 * ((i * D2 + j) * D3 + k)).y(1000 * ((i * D2 + j) * D3 + k));
                }
            }
        }
        s.pointArray3D(array3);

        for (int i = 0; i < D1 * D2 * D3; i++) {
            assertEquals(-1000 * i, p.next(i).x());
            assertEquals(1000 * i, p.next(i).y());
        }
        
        try {
            s.pointArray3D(new Point[D1 / 2][][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            s.pointArray3D(new Point[D1][D2 / 2][]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            s.pointArray3D(new Point[D1][D2][D3 / 2]);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testStructWithArrayByteArrayAsString() {
        assertEquals(192, StructWithArray.sizeOf());

        final int D1 = 24;
        StructWithArray s = new StructWithArray();
        BytePtr p = s.byteArrayAsPtr();
        
        assertEquals("", s.byteArrayAsString());
        p.set((byte) 'a');
        p.next(1).set((byte) 'b');
        p.next(2).set((byte) 'c');
        assertEquals("abc", s.byteArrayAsString());
        p.next(2).set((byte) 0);
        assertEquals("ab", s.byteArrayAsString());

        s.byteArrayAsString("foo bar");
        assertEquals("foo bar", p.toStringAsciiZ());
        s.byteArrayAsString("foo");
        assertEquals("foo", p.toStringAsciiZ());

        // Note: This assumes that the byte right after the byte array is 0. It should be.
        s.byteArrayAsString("012345678901234567890123456789");
        assertEquals("012345678901234567890123", s.byteArrayAsString());

        s.byteArrayAsPtr(BytePtr.toBytePtrAsciiZ("012345678901234567890123456789"));
        assertEquals("012345678901234567890123", s.byteArrayAsString());
        
        p.clear(D1);
        assertEquals("", s.byteArrayAsString());
    }
    
    @Test
    public void testMarshalerCallSequence() {
        StringMarshaler.calls = new ArrayList<String>();
        
        TestStruct s = new TestStruct();
        assertNull(s.string());
        s.string("foobar");
        assertEquals("foobar", s.string());
        
        assertEquals(3, StringMarshaler.calls.size());
        assertEquals("toObject(null, ?, 2)", StringMarshaler.calls.get(0));
        assertEquals("toNative('foobar', ?, 2)", StringMarshaler.calls.get(1));
        assertEquals("toObject('foobar', ?, 2)", StringMarshaler.calls.get(2));
    }
    
    
    float fpi = (float) Math.PI;
    @Test
    public void testMarshalMachineSizedFloatD() {
        // NOTE: 32-bit specific
        assertEquals(4, MachineSizedStruct.sizeOf());

        long ldpi = Double.doubleToLongBits(Math.PI);
        long lfpi = Double.doubleToLongBits(fpi);
        assertNotEquals(ldpi, lfpi);
        
        MachineSizedStruct s = new MachineSizedStruct();
        assertEquals(0.0, s.machineSizedFloatD(), 0);
        s.machineSizedFloatD(Math.PI);
        assertEquals(fpi, VM.getFloat(s.getHandle()), 0f);
        assertEquals(fpi, s.machineSizedFloatF(), 0f);
        assertEquals(lfpi, Double.doubleToLongBits(s.machineSizedFloatD()));
    }
    
    @Test
    public void testMachineSizedSInt() throws Exception {
        // NOTE: 32-bit specific
        assertEquals(4, MachineSizedStruct.sizeOf());
        
        MachineSizedStruct s = new MachineSizedStruct();
        assertEquals(0, s.machineSizedSInt());
        s.machineSizedSInt(-1);
        assertEquals(-1, s.machineSizedSInt());
        s.machineSizedSInt(0x80000000);
        assertEquals(0x80000000, VM.getInt(s.getHandle()));
        assertEquals(0xffffffff80000000L, s.machineSizedSInt());
        s.machineSizedSInt(0x1234567880000000L);
        assertEquals(0x80000000, VM.getInt(s.getHandle()));
        assertEquals(0xffffffff80000000L, s.machineSizedSInt());
    }
    
    @Test
    public void testMachineSizedUInt() throws Exception {
        // NOTE: 32-bit specific
        assertEquals(4, MachineSizedStruct.sizeOf());
        
        MachineSizedStruct s = new MachineSizedStruct();
        assertEquals(0, s.machineSizedUInt());
        s.machineSizedUInt(-1);
        assertEquals(0xffffffffL, s.machineSizedUInt());
        s.machineSizedUInt(0x80000000);
        assertEquals(0x80000000, VM.getInt(s.getHandle()));
        assertEquals(0x80000000L, s.machineSizedUInt());
        s.machineSizedUInt(0x1234567880000000L);
        assertEquals(0x80000000, VM.getInt(s.getHandle()));
        assertEquals(0x80000000L, s.machineSizedUInt());
    }
}
