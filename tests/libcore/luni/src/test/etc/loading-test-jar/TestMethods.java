/*
 * Copyright (C) 2011 The Android Open Source Project
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

package test;

import test2.Target2;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class used as part of the class loading tests. This class uses
 * other classes, some of which should have come from the same jar/dex
 * file and others of which should have come from a different jar/dex
 * file. Each test method in this class is called from the same-named
 * method in {@code DexClassLoaderTest}.
 */
public class TestMethods {
    /*
     * The following are all simple utility methods which, under
     * normal circumstances, would be part of other libraries
     * (specifically, JUnit and the Android libcore support library).
     *
     * However, this file gets compiled as an independent unit without
     * any dependencies. We could theoretically add dependencies on other
     * libraries, but that would make things much more complicated and
     * fragile, and for very little benefit.
     */

    /**
     * Simple sameness assertion checker.
     */
    public static void assertSame(Object expected, Object actual) {
        if (expected != actual) {
            throw new RuntimeException(
                "EXPECTED: " + expected + "; ACTUAL: " + actual);
        }
    }

    /**
     * Simple sameness assertion checker.
     */
    public static void assertSame(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException(
                "EXPECTED: " + expected + "; ACTUAL: " + actual);
        }
    }

    /**
     * Fully read the contents of the given stream.
     */
    public static byte[] readFully(InputStream in) throws IOException {
        // This is a copy of the same-named method in libcore.io.Streams.
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        while (true) {
            int byteCount = in.read(buffer);
            if (byteCount == -1) {
                return bytes.toByteArray();
            }
            bytes.write(buffer, 0, byteCount);
        }
    }

    /*
     * Test methods that use another class from the same dex/jar file
     */

    /**
     * Test that an instance of a sibling class can be constructed.
     */
    public static void test_constructor() {
        new Target();
    }

    /**
     * Test calling a static method on a sibling class.
     */
    public static void test_callStaticMethod() {
        assertSame("blort", Target.blort());
    }

    /**
     * Test getting a static variable of a sibling class.
     */
    public static void test_getStaticVariable() {
        Target.setStaticVariable(22);
        assertSame(22, Target.staticVariable);
    }

    /**
     * Test calling an instance method on a sibling class.
     */
    public static void test_callInstanceMethod() {
        Target target = new Target();
        assertSame("zorch", target.zorch());
    }

    /**
     * Test getting an instance variable of a sibling class.
     */
    public static void test_getInstanceVariable() {
        Target target = new Target();
        target.setInstanceVariable(10098);
        assertSame(10098, target.instanceVariable);
    }

    /**
     * Test getting a resource which should be in the same jar
     * file as this class.
     */
    public static void test_getResourceAsStream() throws IOException {
        ClassLoader cl = TestMethods.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("test/Resource1.txt");
        byte[] contents = readFully(in);
        String s = new String(contents, "UTF-8");

        assertSame("Muffins are tasty!\n", s.intern());
    }

    /*
     * Test methods that use a class from a different dex/jar file
     */

    /**
     * Test that an instance of a cousin class can be constructed.
     */
    public static void test_diff_constructor() {
        new Target2();
    }

    /**
     * Test calling a static method on a cousin class.
     */
    public static void test_diff_callStaticMethod() {
        assertSame("frotz", Target2.frotz());
    }

    /**
     * Test getting a static variable of a cousin class.
     */
    public static void test_diff_getStaticVariable() {
        Target2.setStaticIgram(220);
        assertSame(220, Target2.staticIgram);
    }

    /**
     * Test calling an instance method on a cousin class.
     */
    public static void test_diff_callInstanceMethod() {
        Target2 target = new Target2();
        assertSame("fizmo", target.fizmo());
    }

    /**
     * Test getting an instance variable of a cousin class.
     */
    public static void test_diff_getInstanceVariable() {
        Target2 target = new Target2();
        target.setInstanceMagri(10098);
        assertSame(10098, target.instanceMagri);
    }

    /**
     * Test getting a resource which should be in a different jar
     * file as this class.
     */
    public static void test_diff_getResourceAsStream() throws IOException {
        ClassLoader cl = TestMethods.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("test2/Resource2.txt");
        byte[] contents = readFully(in);
        String s = new String(contents, "UTF-8");

        assertSame("Who doesn't like a good biscuit?\n", s.intern());
    }
}
