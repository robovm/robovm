// Copyright 2007 The Android Open Source Project

/**
 * Exercise Object.wait(), comparing results against wall clock time.
 */
public class Main {
    /* delays, in milliseconds */
    private final static long[] DELAYS = {
        200, 500, 1000, 2000, 3500, 8000
    };

    public static void main(String[] args) {
        boolean timing = (args.length >= 1) && args[0].equals("--timing");
        doit(timing);
    }

    public static void doit(boolean timing) {
        Object sleepy = new Object();
        long start, end;

        synchronized (sleepy) {
            try {
                sleepy.wait(-500);
                System.out.println("HEY: didn't throw on negative arg");
            } catch (IllegalArgumentException iae) {
                System.out.println("Caught expected exception on neg arg");
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            for(long delay : DELAYS) {
                System.out.println("Waiting for " + delay + "ms...");

                start = System.currentTimeMillis();
                try {
                    sleepy.wait(delay);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                end = System.currentTimeMillis();

                long elapsed = end - start;
                boolean showTime = timing;

                if (! timing) {
                    long epsilon = delay / 10;
                    if (epsilon > 50) {
                        epsilon = 50;
                    }

                    long min = delay - epsilon;
                    long max = delay + epsilon;

                    if (elapsed < min) {
                        System.out.println("  Elapsed time was too short");
                        showTime = true;
                    } else if (elapsed > max) {
                        System.out.println("  Elapsed time was too long: "
                            + "elapsed=" + elapsed + " max=" + max);
                        showTime = true;
                    }
                }

                if (showTime) {
                    System.out.println("  Wall clock elapsed "
                            + elapsed + "ms");
                }
            }
        }
    }
}
