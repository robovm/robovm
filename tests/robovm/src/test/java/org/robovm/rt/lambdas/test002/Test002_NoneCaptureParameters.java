package org.robovm.rt.lambdas.test002;

import static org.junit.Assert.*;

import org.junit.Test;

interface B {
    boolean add(boolean a, boolean b);
}

interface By {
    byte add(byte a, byte b);
}

interface C {
    char add(char a, char b);
}

interface S {
    short add(short a, short b);
}

interface I {
    int add(int a, int b);
}

interface L {
    long add(long a, long b);
}

interface F {
    float add(float a, float b);
}

interface D {
    double add(double a, double b);
}

interface O {
    String add(String a, String b);
}

public class Test002_NoneCaptureParameters {
    @Test
    public void testNonCaptureParameters() {
        B bo = (a, b) -> a && b;
        assertEquals(true && true, bo.add(true, true));
        
        By by = (a, b) -> (byte)(a + b);
        assertEquals((byte)1 + (byte)2, by.add((byte)1, (byte)2));
        
        C c = (a, b) -> (char)(a + b);
        assertEquals((char)1 + (char)2, c.add((char)1, (char)2));
        
        S s = (a, b) -> (short)(a + b);
        assertEquals((short)1 + (short)2, s.add((short)1, (short)2));
        
        I i = (a, b) -> a + b;
        assertEquals(5, i.add(2, 3));
        
        L l = (a, b) -> a + b;
        assertEquals(2l + 3l, l.add(2l, 3l));
        
        F f = (a, b) -> a + b;
        assertEquals(2f + 3f, f.add(2f, 3f), 0);
        
        D d = (a, b) -> a + b;
        assertEquals(2.0 + 3.0, d.add(2.0, 3.0), 0);
        
        O o = (a, b) -> a + b;
        assertEquals("Hello" + "World", o.add("Hello", "World"));
    }
}
