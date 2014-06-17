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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import junit.framework.TestCase;
import libcore.util.SerializationTester;

public class ThrowableTest extends TestCase {
    private static class NoStackTraceException extends Exception {
        @Override
        public synchronized Throwable fillInStackTrace() {
            return null;
        }
    }
    public void testNullStackTrace() {
        try {
            throw new NoStackTraceException();
        } catch (NoStackTraceException ex) {
            // We used to throw NullPointerException when printing an exception with no stack trace.
            ex.printStackTrace(new PrintWriter(new StringWriter()));
        }
    }

    public void testNonWritableStackTrace() {
        try {
            // The 4th argument, writableStackTrace, is false...
            throw new SuppressionsThrowable("hi", null, true, false);
        } catch (Throwable th) {
            assertEquals("hi", th.getMessage());

            // We see an empty stack trace.
            assertEquals(0, th.getStackTrace().length);

            // setStackTrace is a no-op.
            th.setStackTrace(new StackTraceElement[] { new StackTraceElement("c", "m", "f", -2) });
            assertEquals(0, th.getStackTrace().length);

            // fillInStackTrace is a no-op.
            th.fillInStackTrace();
            assertEquals(0, th.getStackTrace().length);

            // It's still possible to print an exception with writableStackTrace == false.
            th.printStackTrace(new PrintWriter(new StringWriter()));
        }
    }

    private static class SuppressionsThrowable extends Throwable {
        private static final long serialVersionUID = 202649043897209143L;

        public SuppressionsThrowable(String detailMessage, Throwable throwable,
                boolean enableSuppression, boolean writableStackTrace) {
            super(detailMessage, throwable, enableSuppression, writableStackTrace);
        }
    }

    public void testAddSuppressed() {
        Throwable throwable = new Throwable();
        assertSuppressed(throwable);
        Throwable suppressedA = new Throwable();
        throwable.addSuppressed(suppressedA);
        assertSuppressed(throwable, suppressedA);
        Throwable suppressedB = new Throwable();
        throwable.addSuppressed(suppressedB);
        assertSuppressed(throwable, suppressedA, suppressedB);
    }

    public void testAddDuplicateSuppressed() {
        Throwable throwable = new Throwable();
        Throwable suppressedA = new Throwable();
        throwable.addSuppressed(suppressedA);
        throwable.addSuppressed(suppressedA);
        throwable.addSuppressed(suppressedA);
        assertSuppressed(throwable, suppressedA, suppressedA, suppressedA);
    }

    public void testGetSuppressedReturnsCopy() {
        Throwable throwable = new Throwable();
        Throwable suppressedA = new Throwable();
        Throwable suppressedB = new Throwable();
        throwable.addSuppressed(suppressedA);
        throwable.addSuppressed(suppressedB);
        Throwable[] mutable = throwable.getSuppressed();
        mutable[0] = null;
        mutable[1] = null;
        assertSuppressed(throwable, suppressedA, suppressedB);
    }

    public void testAddSuppressedWithSuppressionDisabled() {
        Throwable throwable = new SuppressionsThrowable("foo", null, false, true);
        assertSuppressed(throwable);
        throwable.addSuppressed(new Throwable());
        assertSuppressed(throwable);
        throwable.addSuppressed(new Throwable());
        assertSuppressed(throwable);
    }

    public void testAddSuppressedSelf() {
        Throwable throwable = new Throwable();
        try {
            throwable.addSuppressed(throwable);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testAddSuppressedNull() {
        Throwable throwable = new Throwable();
        try {
            throwable.addSuppressed(null);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void testPrintStackTraceWithCause() {
        Throwable throwable = newThrowable("Throwable", "A", "B");
        throwable.initCause(newThrowable("Cause", "A", "B", "C", "D"));

        assertEquals("java.lang.Throwable: Throwable\n"
                + "\tat ClassB.doB(ClassB.java:1)\n"
                + "\tat ClassA.doA(ClassA.java:0)\n"
                + "Caused by: java.lang.Throwable: Cause\n"
                + "\tat ClassD.doD(ClassD.java:3)\n"
                + "\tat ClassC.doC(ClassC.java:2)\n"
                + "\t... 2 more\n", printStackTraceToString(throwable));
    }

    public void testPrintStackTraceWithCauseAndSuppressed() {
        Throwable throwable = newThrowable("Throwable", "A", "B");
        throwable.initCause(newThrowable("Cause", "A", "B", "C", "D"));
        throwable.addSuppressed(newThrowable("Suppressed", "A", "B", "E", "F"));
        throwable.addSuppressed(newThrowable("Suppressed", "A", "B", "G", "H"));

        assertEquals("java.lang.Throwable: Throwable\n"
                + "\tat ClassB.doB(ClassB.java:1)\n"
                + "\tat ClassA.doA(ClassA.java:0)\n"
                + "\tSuppressed: java.lang.Throwable: Suppressed\n"
                + "\t\tat ClassF.doF(ClassF.java:3)\n"
                + "\t\tat ClassE.doE(ClassE.java:2)\n"
                + "\t\t... 2 more\n"
                + "\tSuppressed: java.lang.Throwable: Suppressed\n"
                + "\t\tat ClassH.doH(ClassH.java:3)\n"
                + "\t\tat ClassG.doG(ClassG.java:2)\n"
                + "\t\t... 2 more\n"
                + "Caused by: java.lang.Throwable: Cause\n"
                + "\tat ClassD.doD(ClassD.java:3)\n"
                + "\tat ClassC.doC(ClassC.java:2)\n"
                + "\t... 2 more\n", printStackTraceToString(throwable));
    }

    public void testPrintStackTraceWithEverything() {
        Throwable throwable = newThrowable("Throwable", "A", "B");
        Throwable cause = newThrowable("Cause", "A", "B", "C", "D");
        Throwable suppressed = newThrowable("Suppressed", "A", "B", "E", "F");

        throwable.addSuppressed(suppressed);
        suppressed.addSuppressed(newThrowable("Suppressed/Suppressed", "A", "B", "E", "G"));
        suppressed.initCause(newThrowable("Suppressed/Cause", "A", "B", "E", "H"));

        throwable.initCause(cause);
        cause.addSuppressed(newThrowable("Cause/Suppressed", "A", "B", "C", "I"));
        cause.initCause(newThrowable("Cause/Cause", "A", "B", "C", "J"));

        assertEquals("java.lang.Throwable: Throwable\n"
                + "\tat ClassB.doB(ClassB.java:1)\n"
                + "\tat ClassA.doA(ClassA.java:0)\n"
                + "\tSuppressed: java.lang.Throwable: Suppressed\n"
                + "\t\tat ClassF.doF(ClassF.java:3)\n"
                + "\t\tat ClassE.doE(ClassE.java:2)\n"
                + "\t\t... 2 more\n"
                + "\t\tSuppressed: java.lang.Throwable: Suppressed/Suppressed\n"
                + "\t\t\tat ClassG.doG(ClassG.java:3)\n"
                + "\t\t\t... 3 more\n"
                + "\tCaused by: java.lang.Throwable: Suppressed/Cause\n"
                + "\t\tat ClassH.doH(ClassH.java:3)\n"
                + "\t\t... 3 more\n"
                + "Caused by: java.lang.Throwable: Cause\n"
                + "\tat ClassD.doD(ClassD.java:3)\n"
                + "\tat ClassC.doC(ClassC.java:2)\n"
                + "\t... 2 more\n"
                + "\tSuppressed: java.lang.Throwable: Cause/Suppressed\n"
                + "\t\tat ClassI.doI(ClassI.java:3)\n"
                + "\t\t... 3 more\n"
                + "Caused by: java.lang.Throwable: Cause/Cause\n"
                + "\tat ClassJ.doJ(ClassJ.java:3)\n"
                + "\t... 3 more\n", printStackTraceToString(throwable));
    }

    public void testSetStackTraceWithNullElement() {
        Throwable throwable = new Throwable();
        try {
            throwable.setStackTrace(new StackTraceElement[]{ null });
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void testCauseSerialization() {
        String s = "aced0005737200136a6176612e6c616e672e5468726f7761626c65d5c635273977b8cb0300034c0"
                + "00563617573657400154c6a6176612f6c616e672f5468726f7761626c653b4c000d64657461696c4"
                + "d6573736167657400124c6a6176612f6c616e672f537472696e673b5b000a737461636b547261636"
                + "574001e5b4c6a6176612f6c616e672f537461636b5472616365456c656d656e743b78707371007e0"
                + "00071007e000574000543617573657572001e5b4c6a6176612e6c616e672e537461636b547261636"
                + "5456c656d656e743b02462a3c3cfd22390200007870000000047372001b6a6176612e6c616e672e5"
                + "37461636b5472616365456c656d656e746109c59a2636dd8502000449000a6c696e654e756d62657"
                + "24c000e6465636c6172696e67436c61737371007e00024c000866696c654e616d6571007e00024c0"
                + "00a6d6574686f644e616d6571007e0002787000000003740006436c6173734474000b436c6173734"
                + "42e6a617661740003646f447371007e000900000002740006436c6173734374000b436c617373432"
                + "e6a617661740003646f437371007e000900000001740006436c6173734274000b436c617373422e6"
                + "a617661740003646f427371007e000900000000740006436c6173734174000b436c617373412e6a6"
                + "17661740003646f41787400095468726f7761626c657571007e0007000000027371007e000900000"
                + "001740006436c6173734274000b436c617373422e6a617661740003646f427371007e00090000000"
                + "0740006436c6173734174000b436c617373412e6a617661740003646f4178";
        Throwable throwable = newThrowable("Throwable", "A", "B");
        throwable.initCause(newThrowable("Cause", "A", "B", "C", "D"));
        assertSerialized(throwable, s);
    }

    public void testSuppressedSerialization() {
        String s = "aced0005737200136a6176612e6c616e672e5468726f7761626c65d5c635273977b8cb0300044c0"
                + "00563617573657400154c6a6176612f6c616e672f5468726f7761626c653b4c000d64657461696c4"
                + "d6573736167657400124c6a6176612f6c616e672f537472696e673b5b000a737461636b547261636"
                + "574001e5b4c6a6176612f6c616e672f537461636b5472616365456c656d656e743b4c00147375707"
                + "0726573736564457863657074696f6e737400104c6a6176612f7574696c2f4c6973743b787071007"
                + "e000574000a53657269616c697a65647572001e5b4c6a6176612e6c616e672e537461636b5472616"
                + "365456c656d656e743b02462a3c3cfd22390200007870000000027372001b6a6176612e6c616e672"
                + "e537461636b5472616365456c656d656e746109c59a2636dd8502000449000a6c696e654e756d626"
                + "5724c000e6465636c6172696e67436c61737371007e00024c000866696c654e616d6571007e00024"
                + "c000a6d6574686f644e616d6571007e0002787000000001740006436c6173734274000b436c61737"
                + "3422e6a617661740003646f427371007e000900000000740006436c6173734174000b436c6173734"
                + "12e6a617661740003646f41737200136a6176612e7574696c2e41727261794c6973747881d21d99c"
                + "7619d03000149000473697a657870000000017704000000017371007e000071007e001474000a537"
                + "570707265737365647571007e0007000000047371007e000900000003740006436c6173734474000"
                + "b436c617373442e6a617661740003646f447371007e000900000002740006436c6173734374000b4"
                + "36c617373432e6a617661740003646f437371007e000900000001740006436c6173734274000b436"
                + "c617373422e6a617661740003646f427371007e000900000000740006436c6173734174000b436c6"
                + "17373412e6a617661740003646f41737200266a6176612e7574696c2e436f6c6c656374696f6e732"
                + "4556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c69737471007e00047"
                + "872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6"
                + "c6c656374696f6e19420080cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6"
                + "c656374696f6e3b78707371007e0012000000007704000000007871007e002b787878";
        Throwable throwable = newThrowable("Serialized", "A", "B");
        throwable.addSuppressed(newThrowable("Suppressed", "A", "B", "C", "D"));
        assertSerialized(throwable, s);
    }

    public void testDisableSuppressionSerialization() {
        String s = "aced0005737200356c6962636f72652e6a6176612e6c616e672e5468726f7761626c65546573742"
                + "45375707072657373696f6e735468726f7761626c6502cff43b5390d137020000787200136a61766"
                + "12e6c616e672e5468726f7761626c65d5c635273977b8cb0300044c000563617573657400154c6a6"
                + "176612f6c616e672f5468726f7761626c653b4c000d64657461696c4d6573736167657400124c6a6"
                + "176612f6c616e672f537472696e673b5b000a737461636b547261636574001e5b4c6a6176612f6c6"
                + "16e672f537461636b5472616365456c656d656e743b4c00147375707072657373656445786365707"
                + "4696f6e737400104c6a6176612f7574696c2f4c6973743b787070740003666f6f7572001e5b4c6a6"
                + "176612e6c616e672e537461636b5472616365456c656d656e743b02462a3c3cfd223902000078700"
                + "00000007078";
        Throwable throwable = new SuppressionsThrowable("foo", null, false, true);
        throwable.setStackTrace(new StackTraceElement[0]);
        new SerializationTester<Throwable>(throwable, s) {
            @Override protected boolean equals(Throwable a, Throwable b) {
                return printStackTraceToString(a).equals(printStackTraceToString(b));
            }
            @Override protected void verify(Throwable deserialized) {
                // the suppressed exception is silently discarded
                deserialized.addSuppressed(newThrowable("Suppressed"));
                assertSuppressed(deserialized);
            }
        }.test();
    }

    public void testEnableSuppressionSerialization() {
        String s = "aced0005737200356c6962636f72652e6a6176612e6c616e672e5468726f7761626c65546573742"
                + "45375707072657373696f6e735468726f7761626c6502cff43b5390d137020000787200136a61766"
                + "12e6c616e672e5468726f7761626c65d5c635273977b8cb0300044c000563617573657400154c6a6"
                + "176612f6c616e672f5468726f7761626c653b4c000d64657461696c4d6573736167657400124c6a6"
                + "176612f6c616e672f537472696e673b5b000a737461636b547261636574001e5b4c6a6176612f6c6"
                + "16e672f537461636b5472616365456c656d656e743b4c00147375707072657373656445786365707"
                + "4696f6e737400104c6a6176612f7574696c2f4c6973743b787070740003666f6f7572001e5b4c6a6"
                + "176612e6c616e672e537461636b5472616365456c656d656e743b02462a3c3cfd223902000078700"
                + "0000000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626"
                + "c654c697374fc0f2531b5ec8e100200014c00046c69737471007e00057872002c6a6176612e75746"
                + "96c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e1942008"
                + "0cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6c656374696f6e3b7870737"
                + "200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657"
                + "870000000007704000000007871007e000f78";
        Throwable throwable = new SuppressionsThrowable("foo", null, true, true);
        throwable.setStackTrace(new StackTraceElement[0]);
        new SerializationTester<Throwable>(throwable, s) {
            @Override protected boolean equals(Throwable a, Throwable b) {
                return printStackTraceToString(a).equals(printStackTraceToString(b));
            }
            @Override protected void verify(Throwable deserialized) {
                // the suppressed exception is permitted
                Throwable suppressed = newThrowable("Suppressed");
                deserialized.addSuppressed(suppressed);
                assertSuppressed(deserialized, suppressed);
            }
        }.test();
    }

    private void assertSerialized(final Throwable throwable, String golden) {
        new SerializationTester<Throwable>(throwable, golden) {
            @Override protected boolean equals(Throwable a, Throwable b) {
                return printStackTraceToString(a).equals(printStackTraceToString(b));
            }
        }.test();
    }

    private Throwable newThrowable(String message, String... stackTraceElements) {
        StackTraceElement[] array = new StackTraceElement[stackTraceElements.length];
        for (int i = 0; i < stackTraceElements.length; i++) {
            String s = stackTraceElements[i];
            array[stackTraceElements.length - 1 - i]
                    = new StackTraceElement("Class" + s, "do" + s, "Class" + s + ".java", i);
        }
        Throwable result = new Throwable(message);
        result.setStackTrace(array);
        return result;
    }

    private String printStackTraceToString(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    private void assertSuppressed(Throwable throwable, Throwable... expectedSuppressed) {
        assertEquals(Arrays.asList(throwable.getSuppressed()), Arrays.asList(expectedSuppressed));
    }
}
