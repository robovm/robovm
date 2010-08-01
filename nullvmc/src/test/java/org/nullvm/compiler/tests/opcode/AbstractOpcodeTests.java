/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @version $Id$
 */
public abstract class AbstractOpcodeTests {

    private StringWriter sw = new StringWriter();
    private PrintWriter out = new PrintWriter(sw);
    private int totalTests = 0;
    private int failedTests = 0;

    public void assertEqualsInt(String test, int expected, int actual) {
        totalTests++;
        if (expected != actual) {
            failedTests++;
            out.format("%d: ****** Test '%s' failed: %d != %d\n", totalTests, test, expected, actual);
        } else {
            out.format("%d: Test '%s' succeeded: %d == %d\n", totalTests, test, expected, actual);
        }
    }

    public void assertEqualsLong(String test, long expected, long actual) {
        totalTests++;
        if (expected != actual) {
            failedTests++;
            out.format("%d: ****** Test '%s' failed: %d != %d\n", totalTests, test, expected, actual);
        } else {
            out.format("%d: Test '%s' succeeded: %d == %d\n", totalTests, test, expected, actual);
        }
    }
    
    public void assertEqualsFloat(String test, float expected, float actual) {
        assertEqualsInt(test, Float.floatToIntBits(expected), Float.floatToIntBits(actual));
//        totalTests++;
//        if (expected == actual || Float.isNaN(expected) && Float.isNaN(actual)) {
//            out.format("%d: Test '%s' succeeded: %a == %a\n", totalTests, test, expected, actual);
//        } else {
//            failedTests++;
//            out.format("%d: ****** Test '%s' failed: %a != %a\n", totalTests, test, expected, actual);
//        }
    }
    
    public void assertEqualsDouble(String test, double expected, double actual) {
        assertEqualsLong(test, Double.doubleToLongBits(expected), Double.doubleToLongBits(actual));
//        totalTests++;
//        if (expected == actual || Double.isNaN(expected) && Double.isNaN(actual)) {
//            out.format("%d: Test '%s' succeeded: %a == %a\n", totalTests, test, expected, actual);
//        } else {
//            failedTests++;
//            out.format("%d: ****** Test '%s' failed: %a != %a\n", totalTests, test, expected, actual);
//        }
    }
    
    public void assertNotNull(String test, Object o) {
        totalTests++;
        if (o == null) {
            failedTests++;
            out.format("%d: ****** Test '%s' failed: Object == null\n", totalTests, test);
        } else {
            out.format("%d: Test '%s' succeeded: Object != null\n", totalTests, test);
        }
    }
    
    public void assertNull(String test, Object o) {
        totalTests++;
        if (o != null) {
            failedTests++;
            out.format("%d: ****** Test '%s' failed: Object != null\n", totalTests, test);
        } else {
            out.format("%d: Test '%s' succeeded: Object == null\n", totalTests, test);
        }
    }
    
    public void assertSameObject(String test, Object expected, Object actual) {
        totalTests++;
        if (expected != actual) {
            failedTests++;
            out.format("%d: ****** Test '%s' failed: o1 != o2\n", totalTests, test);
        } else {
            out.format("%d: Test '%s' succeeded: o1 == o2\n", totalTests, test);
        }
    }
    
    public String getTestResult() {
        out.format("\nTest result:\n");
        out.format("%d tests in total\n", totalTests);
        out.format("%d tests failed\n", failedTests);
        if (failedTests == 0) {
            out.format("All tests were successful!\n");
        }
        out.flush();
        return sw.toString();
    }
    
    public abstract String run();
}
