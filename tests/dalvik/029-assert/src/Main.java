// Copyright 2007 The Android Open Source Project

/**
 * Test Java language asserts.
 */
public class Main {
    public static void main(String[] args) {
        assert true;
        try {
            assert false;
            System.out.println("GLITCH: didn't assert (is '-ea' set?)");
        } catch (AssertionError ae) {
            System.out.println("caught expected assert exception");
        }

        // exercise this code path
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }
}
