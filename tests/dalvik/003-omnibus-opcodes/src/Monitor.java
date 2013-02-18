// Copyright 2008 The Android Open Source Project



/**
 * Exercise monitors.
 */
public class Monitor {
    public static int mVal = 0;

    public synchronized void subTest() {
        Object obj = new Object();
        synchronized (obj) {
            mVal++;
            obj = null;     // does NOT cause a failure on exit
            assert(obj == null);
        }
    }


    public static void run() {
        System.out.println("Monitor.run");

        Object obj = null;

        try {
            synchronized (obj) {
                mVal++;
            }
            assert(false);
        } catch (NullPointerException npe) {
            /* expected */
        }

        obj = new Object();
        synchronized (obj) {
            mVal++;
        }

        new Monitor().subTest();

        assert(mVal == 2);
    }
}
