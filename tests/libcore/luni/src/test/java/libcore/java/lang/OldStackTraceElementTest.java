/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.lang;

import junit.framework.TestCase;

public class OldStackTraceElementTest extends TestCase {

    public class Original extends TestCase {

        public void pureJavaMethod(Object test) throws Exception {
            throw new Exception("pure java method");
        }

        native public void pureNativeMethod(Object test);
    }

    private Original original;

    @Override
    protected void setUp() throws Exception {
        original = new Original();
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_ConstructorLjava_lang_StringLjava_lang_StringLjava_lang_StringI() {
        StackTraceElement ste2 = null;
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            StackTraceElement ste1 = e.getStackTrace()[0];
            ste2 = new StackTraceElement(ste1.getClassName(),
                    ste1.getMethodName(),
                    ste1.getFileName(),
                    ste1.getLineNumber());
            assertEquals("Incorrect value of class name",
                    ste1.getClassName(), ste2.getClassName());
            assertEquals("Incorrect value of method name",
                    ste1.getMethodName(), ste2.getMethodName());
            assertEquals("Incorrect value of file name",
                    ste1.getFileName(), ste2.getFileName());
            assertEquals("Incorrect value of line number",
                    ste1.getLineNumber(), ste2.getLineNumber());
        }
        assertNotNull("Incorrect stack trace object", ste2);
        try {
            new StackTraceElement(null,
                    ste2.getMethodName(),
                    ste2.getFileName(),
                    ste2.getLineNumber());
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // expected
        }
        try {
            new StackTraceElement(ste2.getClassName(),
                    null,
                    ste2.getFileName(),
                    ste2.getLineNumber());
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // expected
        }
        try {
            new StackTraceElement(ste2.getClassName(),
                    ste2.getMethodName(),
                    null,
                    ste2.getLineNumber());
        } catch (NullPointerException e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_equalsLjava_lang_Object() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            StackTraceElement ste1 = e.getStackTrace()[0];
            StackTraceElement ste2 =
                new StackTraceElement(ste1.getClassName(),
                        ste1.getMethodName(),
                        ste1.getFileName(),
                        ste1.getLineNumber());
            assertEquals("Objects are equaled", ste1, ste2);
        }
    }

    public void test_getClassName() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect class name",
                    "libcore.java.lang.OldStackTraceElementTest$Original",
                    e.getStackTrace()[0].getClassName());
            assertEquals("Incorrect class name",
                    "libcore.java.lang.OldStackTraceElementTest",
                    e.getStackTrace()[1].getClassName());
        }
    }

    public void test_getFileName() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect file name",
                    "OldStackTraceElementTest.java",
                    e.getStackTrace()[0].getFileName());
            assertEquals("Incorrect file name",
                    "OldStackTraceElementTest.java",
                    e.getStackTrace()[1].getFileName());
        }
    }

    public void test_getLineNumber() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            // RoboVM note: We don't do line numbers at the moment (changed 26 -> -1)
            assertEquals("Incorrect line number",
                    -1, e.getStackTrace()[0].getLineNumber());
        }
    }

    public void test_getMethodName() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect method name",
                    "pureJavaMethod",
                    e.getStackTrace()[0].getMethodName());
            assertEquals("Incorrect method name",
                    "test_getMethodName",
                    e.getStackTrace()[1].getMethodName());
        }
    }

    public void test_hashCode() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            StackTraceElement ste1 = e.getStackTrace()[0];
            StackTraceElement ste2 =
                new StackTraceElement(ste1.getClassName(),
                        ste1.getMethodName(),
                        ste1.getFileName(),
                        ste1.getLineNumber());
            assertEquals("Incorrect value of hash code",
                    ste1.hashCode(), ste2.hashCode());
            assertFalse("Incorrect value of hash code",
                    ste1.hashCode() == e.getStackTrace()[1].hashCode());
        }
    }

    public void test_isNativeMethod() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            assertFalse("Incorrect method type",
                    e.getStackTrace()[0].isNativeMethod());
        }
        try {
            original.pureNativeMethod(new Object());
            fail();
        } catch (Error e) {
            assertTrue("Incorrect method type",
                    e.getStackTrace()[0].isNativeMethod());
        }
    }

    public void test_toString() {
        try {
            original.pureJavaMethod(new Object());
            fail();
        } catch (Exception e) {
            StackTraceElement ste = e.getStackTrace()[0];
            assertTrue("String representation doesn't contain a package name",
                    ste.toString().contains(getClass().getPackage().getName()));
            assertTrue("String representation doesn't contain a class name",
                    ste.toString().contains("Original"));
            assertTrue("String representation doesn't contain a file name",
                    ste.toString().contains("OldStackTraceElementTest.java"));
            // RoboVM note: We don't do line numbers at the moment (changed assertTrue() -> assertFalse)
            assertFalse("String representation doesn't contain a line number",
                    ste.toString().contains("26"));
            assertTrue("String representation doesn't contain a method name",
                    ste.toString().contains("pureJavaMethod"));
        }
    }
}
