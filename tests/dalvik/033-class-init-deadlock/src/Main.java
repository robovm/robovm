// Copyright 2006 The Android Open Source Project

/**
 * This causes most VMs to lock up.
 *
 * Interrupting threads in class initialization should NOT work.
 */
public class Main {
    public static boolean aInitialized = false;
    public static boolean bInitialized = false;

    static public void main(String[] args) {
        Thread thread1, thread2;

        System.out.println("Deadlock test starting.");
        thread1 = new Thread() { public void run() { new A(); } };
        thread2 = new Thread() { public void run() { new B(); } };
        thread1.start();
        // Give thread1 a chance to start before starting thread2.
        try { Thread.sleep(1000); } catch (InterruptedException ie) { }
        thread2.start();

        try { Thread.sleep(6000); } catch (InterruptedException ie) { }

        System.out.println("Deadlock test interrupting threads.");
        thread1.interrupt();
        thread2.interrupt();
        System.out.println("Deadlock test main thread bailing.");
        System.out.println("A initialized: " + aInitialized);
        System.out.println("B initialized: " + bInitialized);
        System.exit(0);
    }
}

class A {
    static {
        System.out.println("A initializing...");
        try { Thread.sleep(3000); } catch (InterruptedException ie) { }
        new B();
        System.out.println("A initialized");
        Main.aInitialized = true;
    }
}

class B {
    static {
        System.out.println("B initializing...");
        try { Thread.sleep(3000); } catch (InterruptedException ie) { }
        new A();
        System.out.println("B initialized");
        Main.bInitialized = true;
    }
}
