import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Main {
    private static Unsafe UNSAFE;

    public static void main(String[] args) throws Exception {
        setUp();

        ParkTester test = new ParkTester();

        System.out.println("Test starting");

        test.start();
        UNSAFE.unpark(test);
        clearStack(10);

        System.out.println("GC'ing");
        System.gc();
        System.gc();

        System.out.println("Asking thread to park");
        test.parkNow = true;

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            // Ignore it.
        }

        if (test.success) {
            System.out.println("Test succeeded!");
        } else {
            System.out.println("Test failed.");
        }
    }

    /**
     * Set up {@link #UNSAFE}.
     */
    public static void setUp() {
        /*
         * Subvert the access check to get the unique Unsafe instance.
         * We can do this because there's no security manager
         * installed when running the test.
         */
        try {
            Field field = Unsafe.class.getDeclaredField("THE_ONE");
            field.setAccessible(true);

            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Scribbles on the stack to help ensure we don't have a fake
     * pointer that would keep would-be garbage alive.
     */
    private static void clearStack(int depth) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;
        int i = 0;
        int j = 0;

        if (depth > 0) {
            clearStack(depth - 1);
        }
    }

    private static class ParkTester extends Thread {
        public volatile boolean parkNow = false;
        public volatile boolean success = false;

        public void run() {
            while (!parkNow) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // Ignore it.
                }
            }

            long start = System.currentTimeMillis();
            UNSAFE.park(false, 500 * 1000000); // 500 msec
            long elapsed = System.currentTimeMillis() - start;

            if (elapsed > 200) {
                System.out.println("park()ed for " + elapsed + " msec");
                success = false;
            } else {
                System.out.println("park() returned quickly");
                success = true;
            }
        }
    }
}
