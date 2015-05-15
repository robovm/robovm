/*
 * Copyright (C) 2014 RoboVM AB
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
import static org.robovm.rt.LineNumbersInStackTracesTestMethods.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

/**
 * Tests that we get proper line numbers in stack traces.
 */
public class LineNumbersInStackTracesTest {

    @Test
    public void testLineNumbers() {
        ArrayList<StackTraceElement> stackTrace = new ArrayList<>();
        try {
            LineNumbersInStackTracesTestMethods.m1();
        } catch (Error e) {
            stackTrace.addAll(Arrays.asList(e.getStackTrace()));
        }
        
        assertFalse(stackTrace.isEmpty());
        
        // Just consider the stack trace elements referring to the 
        // LineNumbersInStackTracesTestMethods class and its Java file.
        for (Iterator<StackTraceElement> it = stackTrace.iterator(); it.hasNext();) {
            StackTraceElement e = it.next();
            if (!e.getClassName().equals(CLASS_NAME) || !e.getFileName().equals(FILE_NAME)) {
                it.remove();
            }
        }
        
        assertEquals(11, stackTrace.size());

        assertEquals("m1", stackTrace.get(10).getMethodName());
        assertEquals(M1_OFFSET + 0, stackTrace.get(10).getLineNumber());
        assertEquals("m2", stackTrace.get(9).getMethodName());
        assertEquals(M1_OFFSET + 2, stackTrace.get(9).getLineNumber());
        assertEquals("m3", stackTrace.get(8).getMethodName());
        assertEquals(M1_OFFSET + 6, stackTrace.get(8).getLineNumber());
        assertEquals("m4", stackTrace.get(7).getMethodName());
        assertEquals(M1_OFFSET + 10, stackTrace.get(7).getLineNumber());
        assertEquals("m5", stackTrace.get(6).getMethodName());
        assertEquals(M1_OFFSET + 15, stackTrace.get(6).getLineNumber());
        assertEquals("m6", stackTrace.get(5).getMethodName());
        assertEquals(M1_OFFSET + 19, stackTrace.get(5).getLineNumber());
        assertEquals("m7", stackTrace.get(4).getMethodName());
        assertEquals(M1_OFFSET + 32, stackTrace.get(4).getLineNumber());
        assertEquals("m8", stackTrace.get(3).getMethodName());
        assertEquals(M1_OFFSET + 97, stackTrace.get(3).getLineNumber());
        assertEquals("m9", stackTrace.get(2).getMethodName());
        assertEquals(M1_OFFSET + 359, stackTrace.get(2).getLineNumber());
        assertEquals("m10", stackTrace.get(1).getMethodName()); // Line for this is undefined as its > 65536
        assertEquals("m100", stackTrace.get(0).getMethodName()); // Line for this is undefined as its > 65536
    }
}
