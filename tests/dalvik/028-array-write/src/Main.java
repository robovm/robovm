// Copyright 2007 The Android Open Source Project

/**
 * Array write speed test.
 */
public class Main {
    /** whether to report times */
    static boolean timing = false;

    static final int STORAGE_SIZE = 128*1024;
    static int[] mStorage = new int[STORAGE_SIZE];

    static public void report(long start, long end) {
        if (! timing) {
            return;
        }

        System.out.println("Finished in " + ((end - start) / 1000000.0)
            + " msec");
    }

    static void writeArray(int val) {
        for (int i = STORAGE_SIZE-1; i >= 0; i--)
            mStorage[i] = val;
    }

    static void writeTest() {
        long start, end;

        writeArray(0);  // touch all the memory

        System.out.println("Running writeTest...");
        start = System.nanoTime();
        for (int i = 1; i < 20; i++)
            writeArray(i);
        end = System.nanoTime();

        report(start, end);
    }

    static void copyTest() {
        long start, end;

        // touch once
        System.arraycopy(mStorage, 0, mStorage,
            STORAGE_SIZE/2, STORAGE_SIZE/2);

        System.out.println("Running copyTest...");
        start = System.nanoTime();
        for (int i = 1; i < 35; i++) {
            System.arraycopy(mStorage, 0, mStorage,
                STORAGE_SIZE/2, STORAGE_SIZE/2);
        }
        end = System.nanoTime();

        report(start, end);
    }

    public static void main(String[] args) {
        if ((args.length >= 1) && args[0].equals("--timing")) {
            timing = true;
        }

        writeTest();
        copyTest();
        System.out.println("Done!");
    }
}
