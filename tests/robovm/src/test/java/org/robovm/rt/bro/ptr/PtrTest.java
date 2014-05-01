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
package org.robovm.rt.bro.ptr;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

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
    
    public static final class PointPtr extends Ptr<Point, PointPtr> {}
    public static final class PointPtrPtr extends Ptr<PointPtr, PointPtrPtr> {}
    

    @Test
    public void testNext() throws Exception {
        Point p1 = new Point().x(1).y(2);
        Point p2 = new Point().x(3).y(4);
        Point p3 = new Point().x(5).y(6);
        PointPtr ptr = Struct.allocate(PointPtr.class, 3);

        assertNull(ptr.get());
        assertNull(ptr.next().get());
        assertNull(ptr.next().next().get());
        
        ptr.set(p1);
        ptr.next().set(p2);
        ptr.next().next().set(p3);
        
        assertEquals(p1, ptr.get());
        assertEquals(p2, ptr.next().get());
        assertEquals(p3, ptr.next().next().get());
        
        PointPtr[] ptrs = ptr.toArray(3);
        assertEquals(p1, ptrs[0].get());
        assertEquals(p2, ptrs[1].get());
        assertEquals(p3, ptrs[2].get());
    }
    
    @Test
    public void testCopy() throws Exception {
        PointPtr ptr1 = Struct.allocate(PointPtr.class).set(new Point().x(1).y(2));
        PointPtr ptr2 = ptr1.copy();
        assertTrue(ptr1.getHandle() != ptr2.getHandle());
        assertEquals(ptr1.get(), ptr2.get());
    }
    
    @Test
    public void testCopyWithMalloc() throws Exception {
        PointPtr ptr1 = Struct.allocate(PointPtr.class).set(new Point().x(1).y(2));
        PointPtr ptr2 = ptr1.copyWithMalloc();
        try {
            assertTrue(ptr1.getHandle() != ptr2.getHandle());
            assertEquals(ptr1.get(), ptr2.get());
        } finally {
            ptr2.free();
        }
    }
}
