/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.rt;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests statically linked JNI.
 */
public class StaticJNITest {
    private static boolean called = false;

    @Test
    public void testShortName() {
        assertEquals(3, add(1, 2));
    }

    @Test
    public void testLongName() {
        assertEquals(20, mul(4, 5));
    }

    @Test
    public void testOverloadedMethodLongName() {
        assertEquals(3, sub(6, 3));
        assertEquals(9.0f, sub(13.0f, 4.0f), 0.0f);
    }

    @Test
    public void testNoArgsShortName() {
        called = false;
        noArgsShort();
        assertTrue(called);
    }

    @Test
    public void testNoArgsLongName() {
        called = false;
        noArgsLong();
        assertTrue(called);
    }

    @Test(expected = UnsatisfiedLinkError.class)
    public void testNotBoundMethod() {
        notBound();
    }

    private static native int add(int a, int b);
    private static native int mul(int a, int b);
    private static native int sub(int a, int b);
    private static native float sub(float a, float b);
    private static native void noArgsShort();
    private static native void noArgsLong();
    private static native void notBound();
}
