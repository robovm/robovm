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
import org.robovm.rt.bro.annotation.ByRef;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.Ptr;

/**
 * 
 */
public class StructTest {

    public enum SimpleEnum {
        V1, V2, V3
    }
    public enum TestValuedEnum implements IntValuedEnum {
        V100(100), V1000(1000), V10000(10000);
        
        private final int n;
        private TestValuedEnum(int n) {
            this.n = n;
        }
        public int value() {
            return n;
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
    
    public static final class TestStruct extends Struct<TestStruct> {
        @StructMember(0)
        public native byte b();
        @StructMember(0)
        public native TestStruct b(byte b);
        @StructMember(1)
        public native long l();
        @StructMember(1)
        public native TestStruct l(long l);
        @StructMember(2)
        public native char c();
        @StructMember(2)
        public native TestStruct c(char b);
        @StructMember(3)
        public native int i();
        @StructMember(3)
        public native TestStruct i(int i);
        
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
}
