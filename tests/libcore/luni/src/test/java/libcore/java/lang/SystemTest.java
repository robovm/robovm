/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.lang;

import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;

public class SystemTest extends TestCase {

    public void testLineSeparator() throws Exception {
        try {
            // Before Java 7, the small number of classes that wanted the line separator would
            // use System.getProperty. Now they should use System.lineSeparator instead, and the
            // "line.separator" property has no effect after the VM has started.

            // Test System.lineSeparator directly.
            assertEquals("\n", System.lineSeparator());
            System.setProperty("line.separator", "poop");
            assertEquals("\n", System.lineSeparator());
            assertFalse(System.lineSeparator().equals(System.getProperty("line.separator")));

            // java.io.BufferedWriter --- uses System.lineSeparator on Android but not on RI.
            StringWriter sw = new StringWriter();
            BufferedWriter bw = new BufferedWriter(sw);
            bw.newLine();
            bw.flush();
            assertEquals(System.lineSeparator(), sw.toString());
            assertFalse(System.lineSeparator().equals(System.getProperty("line.separator")));

            // java.io.PrintStream --- uses System.lineSeparator on Android but not on RI.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new PrintStream(baos).println();
            assertEquals(System.lineSeparator(), new String(baos.toByteArray(), "UTF-8"));
            assertFalse(System.lineSeparator().equals(System.getProperty("line.separator")));

            // java.io.PrintWriter --- uses System.lineSeparator on Android but not on RI.
            sw = new StringWriter();
            new PrintWriter(sw).println();
            assertEquals(System.lineSeparator(), sw.toString());
            assertFalse(System.lineSeparator().equals(System.getProperty("line.separator")));

            // java.util.Formatter --- uses System.lineSeparator on both.
            assertEquals(System.lineSeparator(), new Formatter().format("%n").toString());
            assertFalse(System.lineSeparator().equals(System.getProperty("line.separator")));
        } finally {
            System.setProperty("line.separator", "\n");
        }
    }

    public void testArrayCopyTargetNotArray() {
        try {
            System.arraycopy(new char[5], 0, "Hello", 0, 3);
            fail();
        } catch (ArrayStoreException e) {
            assertEquals("destination of type java.lang.String is not an array", e.getMessage());
        }
    }

    public void testArrayCopySourceNotArray() {
        try {
            System.arraycopy("Hello", 0, new char[5], 0, 3);
            fail();
        } catch (ArrayStoreException e) {
            assertEquals("source of type java.lang.String is not an array", e.getMessage());
        }
    }

    public void testArrayCopyArrayTypeMismatch() {
        try {
            System.arraycopy(new char[5], 0, new Object[5], 0, 3);
            fail();
        } catch (ArrayStoreException e) {
            assertEquals("char[] and java.lang.Object[] are incompatible array types", e.getMessage());
        }
    }

    public void testArrayCopyElementTypeMismatch() {
        try {
            System.arraycopy(new Object[] { null, 5, "hello" }, 0,
                    new Integer[] { 1, 2, 3, null, null }, 0, 3);
            fail();
        } catch (ArrayStoreException e) {
            assertEquals("source[2] of type java.lang.String cannot be stored in destination array of type java.lang.Integer[]", e.getMessage());
        }
    }

    public void testArrayCopyNull() {
        try {
            System.arraycopy(null, 0, new char[5], 0, 3);
            fail();
        } catch (NullPointerException e) {
            assertEquals("src == null", e.getMessage());
        }
        try {
            System.arraycopy(new char[5], 0, null, 0, 3);
            fail();
        } catch (NullPointerException e) {
            assertEquals("dst == null", e.getMessage());
        }
    }
}
