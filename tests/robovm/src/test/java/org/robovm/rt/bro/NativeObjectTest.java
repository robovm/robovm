/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/**
 * Tests {@link NativeObject}.
 */
public class NativeObjectTest {

    static class A extends NativeObject {
        A(long h) { setHandle(h); }
    }
    static class B extends NativeObject {
        B(long h) { setHandle(h); }
    }
    static class C extends B {
        C(long h) { super(h); }
    }
    
    @Test
    public void testAs() {
        A a = new A(123);
        B b = a.as(B.class);
        assertEquals(B.class, b.getClass());
        assertEquals(a.getHandle(), b.getHandle());
        C c = b.as(C.class);
        assertEquals(C.class, c.getClass());
        assertEquals(b.getHandle(), c.getHandle());
        assertTrue(c == c.as(C.class));
        assertTrue(c == c.as(B.class));
    }

}
