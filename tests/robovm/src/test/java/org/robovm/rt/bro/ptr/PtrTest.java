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
package org.robovm.rt.bro.ptr;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.Ptr.MarshalerCallback;

/**
 * Tests {@link Ptr}.
 */
public class PtrTest {

    public static final class Point extends Struct<Point> {
        @StructMember(0)
        public native int x();
        @StructMember(0)
        public native Point x(int x);
        @StructMember(1)
        public native int y();
        @StructMember(1)
        public native Point y(int y);
    }

    MarshalerCallback<Point> marshallerCallback = new MarshalerCallback<Point>() {
        @Override
        public Point toObject(Class<Point> cls, long handle) {
            return Struct.toStruct(Point.class, handle);
        }
    };
    
    @Test
    public void testToPtrTwoWrapCountZeroHandleAtDepth0() {
        assertNull(Ptr.toPtr(Point.class, 0, 2, marshallerCallback));
    }
    
    @Test
    public void testToPtrOneWrapCount() {
        Point p = new Point().x(10).y(20);
        Ptr<Point> ptr1 = Ptr.newPtr(Point.class).set(p);
        @SuppressWarnings("unchecked")
        Ptr<Point> ptr2 = (Ptr<Point>) Ptr.toPtr(Point.class, ptr1.getHandle(), 1, marshallerCallback);
        assertEquals(ptr1, ptr2);
        assertEquals(ptr1.get(), ptr2.get());
        assertEquals(p, ptr2.get());
    }

    @Test
    public void testToPtrTwoWrapCount() {
        Point p = new Point().x(10).y(20);
        Ptr<Ptr<Point>> ptr1 = Ptr.newPtrPtr(Point.class).set(Ptr.newPtr(Point.class).set(p));
        assertEquals(p, ptr1.get().get());
        @SuppressWarnings("unchecked")
        Ptr<Ptr<Point>> ptr2 = (Ptr<Ptr<Point>>) Ptr.toPtr(Point.class, ptr1.getHandle(), 2, marshallerCallback);
        assertEquals("assertEquals(ptr1, ptr2)", ptr1, ptr2);
        assertEquals("assertEquals(ptr1.get(), ptr2.get())", ptr1.get(), ptr2.get());
        assertEquals("assertEquals(ptr1.get().get(), ptr2.get().get())", ptr1.get().get(), ptr2.get().get());
        assertEquals("assertEquals(p, ptr2.get().get())", p, ptr2.get().get());
    }
    
    @Test
    public void testToPtrTwoWrapCountZeroHandleAtDepth1() {
        Ptr<Ptr<Point>> ptr1 = Ptr.newPtrPtr(Point.class).set(Ptr.newPtr(Point.class));
        assertNull(ptr1.get().get());
        @SuppressWarnings("unchecked")
        Ptr<Ptr<Point>> ptr2 = (Ptr<Ptr<Point>>) Ptr.toPtr(Point.class, ptr1.getHandle(), 2, marshallerCallback);
        assertEquals(ptr1, ptr2);
        assertEquals(ptr1.get(), ptr2.get());
        assertNull(ptr2.get().get());
    }
    
    @Test
    public void testUpdatePtrOneWrapCount() {
        Ptr<Point> ptr = Ptr.newPtr(Point.class).set(new Point().x(10).y(20));
        Point p = new Point().x(30).y(40);
        ptr.setValue(p.getHandle());
        Ptr.updatePtr(ptr, Point.class, 1, marshallerCallback);
        assertEquals(p, ptr.get());
    }
    
    @Test
    public void testUpdatePtrTwoWrapCount() {
        Ptr<Ptr<Point>> ptr = Ptr.newPtrPtr(Point.class).set(Ptr.newPtr(Point.class).set(new Point().x(10).y(20)));
        Point p = new Point().x(30).y(40);
        ptr.get().setValue(p.getHandle());
        Ptr.updatePtr(ptr, Point.class, 2, marshallerCallback);
        assertEquals(p, ptr.get().get());
    }
    
    @Test
    public void testUpdatePtrTwoWrapCountZeroHandleAtDepth1() {
        Ptr<Ptr<Point>> ptr = Ptr.newPtrPtr(Point.class).set(Ptr.newPtr(Point.class).set(new Point().x(10).y(20)));
        ptr.get().setValue(0L);
        Ptr.updatePtr(ptr, Point.class, 2, marshallerCallback);
        assertNull(ptr.get().get());
    }
    
    @Test
    public void testUpdatePtrTwoWrapCountZeroHandleAtDepth0() {
        Ptr<Ptr<Point>> ptr = Ptr.newPtrPtr(Point.class).set(Ptr.newPtr(Point.class).set(new Point().x(10).y(20)));
        ptr.setValue(0L);
        Ptr.updatePtr(ptr, Point.class, 2, marshallerCallback);
        assertNull(ptr.get());
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testUntyped() throws Exception {
        Ptr ptr = Struct.allocate(Ptr.class);
        assertNull(ptr.get());
        BytePtr bytePtr = new BytePtr();
        ptr.set(bytePtr);
        NativeObject value = ptr.get();
        assertEquals(VoidPtr.class, value.getClass());
        assertEquals(bytePtr.getHandle(), value.getHandle());
    }
    
    @Test
    public void testNext() throws Exception {
        Point p1 = new Point().x(1).y(2);
        Point p2 = new Point().x(3).y(4);
        Point p3 = new Point().x(5).y(6);
        Ptr<Point> ptr = Ptr.newPtr(Point.class, 3);

        assertNull(ptr.get());
        assertNull(ptr.next().get());
        assertNull(ptr.next().next().get());
        
        ptr.set(p1);
        ptr.next().set(p2);
        ptr.next().next().set(p3);
        
        assertEquals(p1, ptr.get());
        assertEquals(p2, ptr.next().get());
        assertEquals(p3, ptr.next().next().get());
        
        Ptr<Point>[] ptrs = ptr.toArray(3);
        assertEquals(p1, ptrs[0].get());
        assertEquals(p2, ptrs[1].get());
        assertEquals(p3, ptrs[2].get());
    }
    
    @Test
    public void testCopy() throws Exception {
        Ptr<Point> ptr1 = Ptr.newPtr(Point.class).set(new Point().x(1).y(2));
        Ptr<Point> ptr2 = ptr1.copy();
        assertTrue(ptr1.getHandle() != ptr2.getHandle());
        assertEquals(ptr1.get(), ptr2.get());
    }
    
    @Test
    public void testCopyWithMalloc() throws Exception {
        Ptr<Point> ptr1 = Ptr.newPtr(Point.class).set(new Point().x(1).y(2));
        Ptr<Point> ptr2 = ptr1.copyWithMalloc();
        try {
            assertTrue(ptr1.getHandle() != ptr2.getHandle());
            assertEquals(ptr1.get(), ptr2.get());
        } finally {
            ptr2.free();
        }
    }
}
