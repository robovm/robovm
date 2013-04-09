/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package libcore.java.lang;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

public class OldSystemTest extends junit.framework.TestCase {

    public void test_arraycopyLjava_lang_ObjectILjava_lang_ObjectII() {
        // Test for method void java.lang.System.arraycopy(java.lang.Object,
        // int, java.lang.Object, int, int)
        Integer a[] = new Integer[20];
        Integer b[] = new Integer[20];

        try {
            // copy from non array object into Object array
            System.arraycopy(new Object(), 0, b, 0, 0);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from Object array into non array object
            System.arraycopy(a, 0, new Object(), 0, 0);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from primitive array into object array
            System.arraycopy(new char[] {'a'}, 0, new String[1], 0, 1);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from object array into primitive array
            System.arraycopy(new String[] {"a"}, 0, new char[1], 0, 1);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from primitive array into an array of another primitive type
            System.arraycopy(new char[] {'a'}, 0, new int[1], 0, 1);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from object array into an array of another Object type
            System.arraycopy(new Character[] {'a'}, 0, new Integer[1], 0, 1);
            fail("ArrayStoreException is not thrown.");
        } catch(ArrayStoreException  ase) {
            //expected
        }

        try {
            // copy from null into an array of a primitive type
            System.arraycopy(null, 0, new int[1], 0, 1);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            // copy from a primitive array into null
            System.arraycopy(new int[]{'1'}, 0, null, 0, 1);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            System.arraycopy(a, a.length + 1, b, 0, 1);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, -1, b, 0, 1);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, 0, b, -1, 1);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, 0, b, 0, -1);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, 11, b, 0, 10);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, Integer.MAX_VALUE, b, 0, 10);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, 0, b, Integer.MAX_VALUE, 10);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            System.arraycopy(a, 0, b, 10, Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_currentTimeMillis() {
        // Test for method long java.lang.System.currentTimeMillis()
        try {
            long firstRead = System.currentTimeMillis();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }
            long secondRead = System.currentTimeMillis();
            assertTrue("Incorrect times returned: " + firstRead + ", "
                    + secondRead, firstRead < secondRead);
        } catch (Exception e) {
            fail("Exception during test: " + e.toString());
        }
    }

    public void test_getProperties() {
        String [] props = {"java.vendor.url",
                "java.class.path", "user.home",
                "java.class.version", "os.version",
                "java.vendor", "user.dir",
                /*"user.timezone",*/ "path.separator",
                "os.name", "os.arch",
                "line.separator", "file.separator",
                "user.name", "java.version", "java.home" };

        Properties p = System.getProperties();
        assertTrue(p.size() > 0);

        // Ensure spec'ed properties are non-null. See System.getProperties()
        // spec.

        for (String prop : props) {
            assertNotNull("There is no property among returned properties: "
                    + prop, p.getProperty(prop));
            assertNotNull("System property is null: " + prop,
                    System.getProperty(prop));
        }
    }

    public void test_getPropertyLjava_lang_String() {
        try {
            System.getProperty(null);
            fail("NullPointerException should be thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            System.getProperty("");
            fail("IllegalArgumentException should be thrown.");
        } catch(IllegalArgumentException  iae) {
            //expected
        }
    }

    public void test_getPropertyLjava_lang_StringLjava_lang_String() {
        try {
            System.getProperty(null, "0.0");
            fail("NullPointerException should be thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            System.getProperty("", "0");
            fail("IllegalArgumentException should be thrown.");
        } catch(IllegalArgumentException  iae) {
            //expected
        }
    }

    public void test_inheritedChannel() throws IOException {
        Channel iChannel = System.inheritedChannel();
        assertNull("Incorrect value of channel", iChannel);
        SelectorProvider sp = SelectorProvider.provider();
        assertEquals("Incorrect value of channel",
                sp.inheritedChannel(), iChannel);
    }

    public void test_clearProperty() {
        System.setProperty("test", "value");
        System.clearProperty("test");
        assertNull("Property was not deleted.", System.getProperty("test"));

        try {
            System.clearProperty(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            System.clearProperty("");
            fail("IllegalArgumentException is not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_gc() {
        Runtime rt =  Runtime.getRuntime();
        Vector<StringBuffer> vec = new Vector<StringBuffer>();
        long beforeTest = rt.freeMemory();
        while(rt.freeMemory() < beforeTest * 2/3) {
             vec.add(new StringBuffer(1000));
        }
        long beforeGC = rt.freeMemory();
        System.gc();
        long afterGC = rt.freeMemory();
        assertTrue("memory was not released after calling System.gc()." +
                "before gc: " + beforeGC + "; after gc: " + afterGC,
                beforeGC < afterGC);
    }

    public void test_getenv() {
        // String[] props = { "PATH", "HOME", "USER"};
        // only PATH of these three exists on android
        String[] props = { "PATH" };

        Map<String,String> envMap = System.getenv();
        assertFalse("environment map is empty.", envMap.isEmpty());
        assertTrue("env map contains less than 3 keys.",
                props.length < envMap.keySet().size());
        for (String prop : props) {
            assertNotNull("There is no property: " + prop,
                    envMap.get(prop));
        }
    }

    public void test_getenvLString() {
        assertNotNull("PATH environment variable is not found",
                  System.getenv("PATH"));

        assertNull("Doesn't return NULL for non existent property",
                  System.getenv("nonexistent.property"));

        try {
            System.getenv(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_load() {
        try {
            Runtime.getRuntime().load("nonExistentLibrary");
            fail("UnsatisfiedLinkError was not thrown.");
        } catch(UnsatisfiedLinkError  e) {
            //expected
        }

        try {
            System.load("nonExistentLibrary");
            fail("UnsatisfiedLinkError was not thrown.");
        } catch(UnsatisfiedLinkError ule) {
            //expected
        }

        try {
            System.load(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_loadLibrary() {
        try {
            System.loadLibrary("nonExistentLibrary");
            fail("UnsatisfiedLinkError was not thrown.");
        } catch(UnsatisfiedLinkError ule) {
            //expected
        }

        try {
            System.loadLibrary(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_mapLibraryName() {
        assertEquals("libname.so", System.mapLibraryName("name"));

        try {
            System.mapLibraryName(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_nanoTime() {
        long sleepTime = 5000;
        long beginTime = System.nanoTime();
        try {
            Thread.sleep(sleepTime);
        } catch(Exception e) {
            fail("Unknown exception was thrown.");
        }
        long endTime = System.nanoTime();
        assertTrue((endTime - beginTime) > sleepTime * 1000000);
    }
}
