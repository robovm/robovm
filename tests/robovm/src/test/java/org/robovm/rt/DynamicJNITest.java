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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.rt.bro.Bro;

/**
 * Tests dynamically linked JNI.
 */
public class DynamicJNITest {
    private static boolean ignoreAllTests = false;
    private static boolean called = false;

    @BeforeClass
    public static void loadNativeLib() throws IOException {
        String lib = null;
        if (Bro.IS_IOS) {
            lib = "libDynamicJNITest-ios.dylib";
        } else if (Bro.IS_MACOSX) {
            lib = "libDynamicJNITest-macosx.dylib";
        } else if (Bro.IS_LINUX && Bro.IS_32BIT) {
            lib = "libDynamicJNITest-linux-x86.so";
        } else if (Bro.IS_LINUX && Bro.IS_64BIT) {
            lib = "libDynamicJNITest-linux-x86_64.so";
        }

        if (lib != null) {
            File libPath = File.createTempFile(lib.substring(0, lib.lastIndexOf('.')), lib.substring(lib.lastIndexOf('.')));
            try (InputStream is = DynamicJNITest.class.getResourceAsStream("/" + lib)) {
                try (FileOutputStream os = new FileOutputStream(libPath)) {
                    byte[] buf = new byte[8192];
                    int n;
                    while ((n = is.read(buf)) != -1) {
                        os.write(buf, 0, n);
                    }
                }
            }
            try {
                System.load(libPath.getAbsolutePath());
            } catch (UnsatisfiedLinkError e) {
                if (Bro.IS_IOS && Bro.IS_ARM) {
                    /*
                     * Running on device. This test class will only work on
                     * jailbroken devices. Just ignore the tests in this class
                     * if the UnsatisifedLinkError is due to running on
                     * non-jailbroken device. The exception message should look
                     * like: "... dlopen(...): no suitable image found...".
                     */
                    if (e.getMessage() != null && e.getMessage().contains("no suitable image found")) {
                        ignoreAllTests = true;
                    }
                }
            }
        }
    }
    
    @Before
    public void setUp() {
        Assume.assumeFalse(ignoreAllTests);
    }
    
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

    @Test
    public void testRegisterNatives() {
        called = false;
        try {
            boundUsingRegisterNatives();
            fail("UnsatisfiedLinkError");
        } catch (UnsatisfiedLinkError e) {
        }
        assertFalse(called);
        registerNatives();
        boundUsingRegisterNatives();
        assertTrue(called);
    }

    private static native int add(int a, int b);
    private static native int mul(int a, int b);
    private static native int sub(int a, int b);
    private static native float sub(float a, float b);
    private static native void noArgsShort();
    private static native void noArgsLong();
    private static native void notBound();
    private static native void registerNatives();
    private static native void boundUsingRegisterNatives();
}
