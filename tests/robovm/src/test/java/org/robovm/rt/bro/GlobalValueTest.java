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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Array;
import org.robovm.rt.bro.annotation.ByRef;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.GlobalValue;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.Ptr;

/**
 * Tests {@link GlobalValue} methods.
 */
public class GlobalValueTest {

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
        protected TestBits[] _values() {
            return VALUES;
        }
    }
    
    private static void bind(Class<?> cls) {
        long ptr = VM.getLong(VM.getObjectAddress(memory) + EFFECTIVE_DIRECT_ADDRESS_OFFSET);
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getAnnotation(GlobalValue.class) != null) {
                VM.bindBridgeMethod(m, ptr);
            }
        }
    }
    
    static final ByteBuffer memory = ByteBuffer.allocateDirect(1024);
    
    private static final int EFFECTIVE_DIRECT_ADDRESS_OFFSET;

    static {
        try {
            Field f1 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            if (f1.getType() != long.class) {
                throw new Error("java.nio.Buffer.effectiveDirectAddress should be a long");
            }
            EFFECTIVE_DIRECT_ADDRESS_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f1));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
        bind(GlobalValueTest.class);
    }
    
    @Before
    public void setup() {
        memory.clear();
        while (memory.hasRemaining()) {
            memory.put((byte) 0);
        }
        memory.clear();
    }
    
    @GlobalValue public static native byte byteGetter(); 
    @GlobalValue public static native void byteSetter(byte b); 
    @GlobalValue public static native short shortGetter(); 
    @GlobalValue public static native void shortSetter(short b); 
    @GlobalValue public static native char charGetter(); 
    @GlobalValue public static native void charSetter(char b); 
    @GlobalValue public static native int intGetter(); 
    @GlobalValue public static native void intSetter(int b); 
    @GlobalValue public static native long longGetter(); 
    @GlobalValue public static native void longSetter(long b); 
    @GlobalValue public static native float floatGetter(); 
    @GlobalValue public static native void floatSetter(float b); 
    @GlobalValue public static native double doubleGetter(); 
    @GlobalValue public static native void doubleSetter(double b); 
    @GlobalValue public static native @ByVal Point structByValGetter(); 
    @GlobalValue public static native void structByValSetter(@ByVal Point b); 
    @GlobalValue public static native @ByRef Point structByRefGetter(); 
    @GlobalValue public static native void structByRefSetter(@ByRef Point b); 
    @GlobalValue public static native SimpleEnum enumGetter(); 
    @GlobalValue public static native void enumSetter(SimpleEnum b); 
    @GlobalValue public static native TestValuedEnum valuedEnumGetter(); 
    @GlobalValue public static native void valuedEnumSetter(TestValuedEnum b); 
    @GlobalValue public static native TestBits bitsGetter(); 
    @GlobalValue public static native void bitsSetter(TestBits b); 
    @GlobalValue public static native PointPtr ptrGetter(); 
    @GlobalValue public static native void ptrSetter(PointPtr b); 
    @GlobalValue public static native @Array(10) String arrayAsStringGetter(); 
    @GlobalValue public static native void arrayAsStringSetter(@Array(10) String b); 
    @GlobalValue public static native @Array(10) ByteBuffer arrayAsByteBufferGetter(); 
    @GlobalValue public static native void arrayAsByteBufferSetter(@Array(10) ByteBuffer b); 
    @GlobalValue public static native @Array(10) byte[] arrayAsByteArrayGetter(); 
    @GlobalValue public static native void arrayAsByteArraySetter(@Array(10) byte[] b); 
    @GlobalValue public static native @MachineSizedFloat double machineSizedFloatGetterD(); 
    @GlobalValue public static native void machineSizedFloatSetterD(@MachineSizedFloat double b); 
    @GlobalValue public static native @MachineSizedFloat float machineSizedFloatGetterF(); 
    @GlobalValue public static native void machineSizedFloatSetterF(@MachineSizedFloat float b); 
    @GlobalValue public static native @MachineSizedSInt long machineSizedSIntGetter(); 
    @GlobalValue public static native void machineSizedSIntSetter(@MachineSizedSInt long b); 
    @GlobalValue public static native @MachineSizedUInt long machineSizedUIntGetter(); 
    @GlobalValue public static native void machineSizedUIntSetter(@MachineSizedUInt long b); 
    
    @Test
    public void testByte() throws Exception {
        assertEquals(0, memory.get(0));
        assertEquals(0, byteGetter());
        byteSetter((byte) 0xff);
        assertEquals(0xff, memory.get(0) & 0xff);
        assertEquals(0xff, byteGetter() & 0xff);
    }
    @Test
    public void testShort() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asShortBuffer().get(0));
        assertEquals(0, shortGetter());
        shortSetter((short) 0xffff);
        assertEquals(0xffff, memory.order(ByteOrder.nativeOrder()).asShortBuffer().get(0) & 0xffff);
        assertEquals(0xffff, shortGetter() & 0xffff);
    }
    @Test
    public void testChar() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asCharBuffer().get(0));
        assertEquals(0, charGetter());
        charSetter((char) 0xffff);
        assertEquals(0xffff, memory.order(ByteOrder.nativeOrder()).asCharBuffer().get(0));
        assertEquals(0xffff, charGetter());
    }
    @Test
    public void testInt() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0, intGetter());
        intSetter(0xffffffff);
        assertEquals(0xffffffff, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0) & 0xffffffff);
        assertEquals(0xffffffff, intGetter() & 0xffffffff);
    }
    @Test
    public void testLong() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asLongBuffer().get(0));
        assertEquals(0, longGetter());
        longSetter(0xffffffffffffffffL);
        assertEquals(0xffffffffffffffffL, memory.order(ByteOrder.nativeOrder()).asLongBuffer().get(0));
        assertEquals(0xffffffffffffffffL, longGetter());
    }
    @Test
    public void testFloat() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asFloatBuffer().get(0), 0);
        assertEquals(0, floatGetter(), 0);
        floatSetter((float) Math.PI);
        assertEquals((float) Math.PI, memory.order(ByteOrder.nativeOrder()).asFloatBuffer().get(0), 0.00001f);
        assertEquals((float) Math.PI, floatGetter(), 0.00001f);
    }
    @Test
    public void testDouble() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asDoubleBuffer().get(0), 0);
        assertEquals(0, doubleGetter(), 0);
        doubleSetter(Math.PI);
        assertEquals(Math.PI, memory.order(ByteOrder.nativeOrder()).asDoubleBuffer().get(0), 0.00001);
        assertEquals(Math.PI, doubleGetter(), 0.00001);
    }
    @Test
    public void testStructByVal() throws Exception {
        IntBuffer memoryAsIntBuffer = memory.order(ByteOrder.nativeOrder()).asIntBuffer();
        assertEquals(0, memoryAsIntBuffer.get(0));
        assertEquals(0, memoryAsIntBuffer.get(1));
        assertEquals(0, structByValGetter().x());
        assertEquals(0, structByValGetter().y());
        structByValGetter().x(9876);
        structByValGetter().y(5432);
        assertEquals(9876, memoryAsIntBuffer.get(0));
        assertEquals(5432, memoryAsIntBuffer.get(1));
        assertEquals(9876, structByValGetter().x());
        assertEquals(5432, structByValGetter().y());
        structByValSetter(new Point().x(1234).y(5678));
        assertEquals(1234, memoryAsIntBuffer.get(0));
        assertEquals(5678, memoryAsIntBuffer.get(1));
        assertEquals(1234, structByValGetter().x());
        assertEquals(5678, structByValGetter().y());
    }
    @Test
    public void testStructByRef() throws Exception {
        LongBuffer memoryAsLongBuffer = memory.order(ByteOrder.nativeOrder()).asLongBuffer();
        assertEquals(0, memoryAsLongBuffer.get(0));
        assertNull(structByRefGetter());
        Point p = new Point();
        structByRefSetter(p);
        assertEquals(p.getHandle(), memoryAsLongBuffer.get(0));
    }
    @Test
    public void testEnum() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(SimpleEnum.V1, enumGetter());
        enumSetter(SimpleEnum.V3);
        assertEquals(2, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(SimpleEnum.V3, enumGetter());
    }
    @Test
    public void testValuedEnum() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        try {
            valuedEnumGetter();
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {}
        valuedEnumSetter(TestValuedEnum.V100);
        assertEquals(100, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(TestValuedEnum.V100, valuedEnumGetter());
    }
    @Test
    public void testBits() throws Exception {
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0, bitsGetter().value());
        bitsSetter(TestBits.V8);
        assertEquals(8, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(TestBits.V8, bitsGetter());
    }
    @Test
    public void testPtr() {
        LongBuffer memoryAsLongBuffer = memory.order(ByteOrder.nativeOrder()).asLongBuffer();
        assertEquals(0, memoryAsLongBuffer.get(0));
        assertNull(ptrGetter());
        PointPtr ptr = new PointPtr();
        assertNull(ptr.get());
        Point p = new Point();
        ptr.set(p);
        ptrSetter(ptr);
        assertEquals(ptr.getHandle(), memoryAsLongBuffer.get(0));
        assertEquals(ptr, ptrGetter());
        assertEquals(ptr.get(), ptrGetter().get());
    }
    @Test
    public void testArrayAsString() throws Exception {
        assertEquals(0, memory.get(0));
        assertEquals("", arrayAsStringGetter());
        arrayAsStringSetter("foobar");
        byte[] bytes = new byte[7];
        memory.get(bytes);
        assertEquals("foobar", new String(bytes, 0, 6));
        assertEquals("foobar", arrayAsStringGetter());
        assertEquals(0, bytes[6]);
    }
    @Test
    public void testArrayAsByteBuffer() throws Exception {
        byte[] bytes1 = new byte[10];
        byte[] bytes2 = new byte[10];
        
        memory.get(bytes1); memory.clear();
        assertTrue(Arrays.equals(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, bytes1));
        arrayAsByteBufferGetter().get(bytes2);
        assertTrue(Arrays.equals(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, bytes2));
        
        arrayAsByteBufferSetter(ByteBuffer.wrap(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        
        memory.get(bytes1); memory.clear();
        assertTrue(Arrays.equals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, bytes1));
        arrayAsByteBufferGetter().get(bytes2);
        assertTrue(Arrays.equals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, bytes2));
    }
    @Test
    public void testArrayAsByteArray() throws Exception {
        byte[] bytes1 = new byte[10];
        
        memory.get(bytes1); memory.clear();
        assertTrue(Arrays.equals(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, bytes1));
        assertTrue(Arrays.equals(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, arrayAsByteArrayGetter()));
        
        arrayAsByteArraySetter(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        
        memory.get(bytes1); memory.clear();
        assertTrue(Arrays.equals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, bytes1));
        assertTrue(Arrays.equals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arrayAsByteArrayGetter()));
    }

    float fpi = (float) Math.PI;
    @Test
    public void testMachineSizedFloatD() throws Exception {
        // NOTE: 32-bit specific
        long ldpi = Double.doubleToLongBits(Math.PI);
        long lfpi = Double.doubleToLongBits(fpi);
        assertNotEquals(ldpi, lfpi);
        
        assertEquals(0f, memory.order(ByteOrder.nativeOrder()).asFloatBuffer().get(0), 0f);
        assertEquals(0.0, machineSizedFloatGetterD(), 0);
        machineSizedFloatSetterD(Math.PI);
        assertEquals(fpi, memory.order(ByteOrder.nativeOrder()).asFloatBuffer().get(0), 0f);
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
        assertEquals(lfpi, Double.doubleToLongBits(machineSizedFloatGetterD()));
    }
    @Test
    public void testMachineSizedSInt() throws Exception {
        // NOTE: 32-bit specific
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0, machineSizedSIntGetter());
        machineSizedSIntSetter(-1);
        assertEquals(-1, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(-1, machineSizedSIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
        machineSizedSIntSetter(0x80000000);
        assertEquals(0x80000000, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0xffffffff80000000L, machineSizedSIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
        machineSizedSIntSetter(0x1234567880000000L);
        assertEquals(0x80000000, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0xffffffff80000000L, machineSizedSIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
    }
    @Test
    public void testMachineSizedUInt() throws Exception {
        // NOTE: 32-bit specific
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0, machineSizedUIntGetter());
        machineSizedUIntSetter(-1);
        assertEquals(-1, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0xffffffffL, machineSizedUIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
        machineSizedUIntSetter(0x80000000);
        assertEquals(0x80000000, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0x80000000L, machineSizedUIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
        machineSizedUIntSetter(0x1234567880000000L);
        assertEquals(0x80000000, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(0));
        assertEquals(0x80000000L, machineSizedUIntGetter());
        assertEquals(0, memory.order(ByteOrder.nativeOrder()).asIntBuffer().get(1));
    }
}
