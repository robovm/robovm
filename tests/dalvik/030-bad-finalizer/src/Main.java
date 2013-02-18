// Copyright 2007 The Android Open Source Project

/**
 * Test a class with a bad finalizer.
 */
public class Main {
    public static void main(String[] args) {
        BadFinalizer bf = new BadFinalizer();

        System.out.println("Constructed object.");
        bf = null;

        System.out.println("Nulled. Requestion gc.");
        System.gc();

        for (int i = 0; i < 8; i++) {
            BadFinalizer.snooze(4000);
            System.out.println("Requesting another GC.");
            System.gc();
        }

        System.out.println("Done waiting.");
        System.exit(0);
    }
}
