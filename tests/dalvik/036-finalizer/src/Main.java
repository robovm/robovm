// Copyright 2008 The Android Open Source Project

import java.lang.ref.WeakReference;

/**
 * Some finalizer tests.
 *
 * This only works if System.runFinalization() causes finalizers to run
 * immediately or very soon.
 */
public class Main {
    private static void snooze(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            System.out.println("Snooze: " + ie.getMessage());
        }
    }

    public static WeakReference makeRef() {
        /*
         * Make ft in another thread, so there is no danger of
         * a conservative reference leaking onto the main thread's
         * stack.
         */

        final WeakReference[] wimp = new WeakReference[1];
        Thread t = new Thread() {
                public void run() {
                    FinalizerTest ft = new FinalizerTest("wahoo");
                    wimp[0] = new WeakReference(ft);
                    ft = null;
                }
            };

        t.start();

        try {
            t.join();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        return wimp[0];
    }

    public static String wimpString(final WeakReference wimp) {
        /*
         * Do the work in another thread, so there is no danger of a
         * conservative reference to ft leaking onto the main thread's
         * stack.
         */

        final String[] s = new String[1];
        Thread t = new Thread() {
                public void run() {
                    Object ref = wimp.get();
                    if (ref != null) {
                        s[0] = ref.toString();
                    }
                }
            };

        t.start();

        try {
            t.join();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        return s[0];
    }

    public static void main(String[] args) {
        WeakReference wimp = makeRef();

        System.out.println("wimp: " + wimpString(wimp));

        /* this will try to collect and finalize ft */
        System.out.println("gc");
        System.gc();

        System.out.println("wimp: " + wimpString(wimp));
        System.out.println("finalize");
        System.runFinalization();
        System.out.println("wimp: " + wimpString(wimp));

        System.out.println("sleep");
        snooze(1000);

        System.out.println("reborn: " + FinalizerTest.mReborn);
        System.out.println("wimp: " + wimpString(wimp));
        System.out.println("reset reborn");
        System.gc();
        FinalizerTest.mReborn = FinalizerTest.mNothing;
        System.out.println("gc + finalize");
        System.gc();
        System.runFinalization();

        System.out.println("sleep");
        snooze(1000);

        System.out.println("reborn: " + FinalizerTest.mReborn);
        System.out.println("wimp: " + wimpString(wimp));
    }
}
