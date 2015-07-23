/*
 * Copyright (C) 2015 The Android Open Source Project
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
package org.robovm.rt.lambdas.test001;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

interface V {
    void noop();
}

interface B {
    boolean getValue();
}

interface By {
    byte getValue();
}

interface C {
    char getValue();
}

interface S {
    short getValue();
}

interface I {
    int getValue();
}

interface L {
    long getValue();
}

interface F {
    float getValue();
}

interface D {
    double getValue();
}

interface O {
    String getValue();
}

/**
 * Lambda expression test without parameters.
 */
public class NoParametersTest {

    @Test
    public void test001() {
        PrintStream oldStream = System.out;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            V v = () -> {
                System.out.print("Empty lambda");
            };
            v.noop();
            assertEquals("Empty lambda", new String(out.toByteArray()));
        } finally {
            System.setOut(oldStream);
        }        

        B b = () -> {
            return true;
        };
        assertEquals(true, b.getValue());

        By by = () -> {
            return (byte) -127;
        };
        assertEquals(-127, by.getValue());

        C c = () -> {
            return (char) Character.MAX_VALUE;
        };
        assertEquals(Character.MAX_VALUE, c.getValue());

        S s = () -> {
            return (short) Short.MIN_VALUE;
        };
        assertEquals(Short.MIN_VALUE, s.getValue());

        I i = () -> {
            return 123;
        };
        assertEquals(123, i.getValue());

        L l = () -> {
            return 456;
        };
        assertEquals(456, l.getValue());

        F f = () -> {
            return 1.234f;
        };
        assertEquals(1.234f, f.getValue(), 0);

        D d = () -> {
            return 1.234;
        };
        assertEquals(1.234, d.getValue(), 0);

        O o = () -> {
            return "Test";
        };
        assertEquals("Test", o.getValue());
    }
}
