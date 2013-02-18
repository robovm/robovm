/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static volatile boolean quit = false;
    public static final boolean DEBUG = false;

    private static final boolean WRITE_HPROF_DATA = false;
    private static final int TEST_TIME = 10;
    private static final String OUTPUT_FILE = "gc-thrash.hprof";

    public static void main(String[] args) {
        // dump heap before

        System.out.println("Running (" + TEST_TIME + " seconds) ...");
        runTests();

        Method dumpHprofDataMethod = null;
        String dumpFile = null;

        if (WRITE_HPROF_DATA) {
            dumpHprofDataMethod = getDumpHprofDataMethod();
            if (dumpHprofDataMethod != null) {
                dumpFile = getDumpFileName();
                System.out.println("Sending output to " + dumpFile);
            }
        }

        System.gc();
        System.runFinalization();
        System.gc();

        if (WRITE_HPROF_DATA && dumpHprofDataMethod != null) {
            try {
                dumpHprofDataMethod.invoke(null, dumpFile);
            } catch (IllegalAccessException iae) {
                System.err.println(iae);
            } catch (InvocationTargetException ite) {
                System.err.println(ite);
            }
        }

        System.out.println("Done.");
    }

    /**
     * Finds VMDebug.dumpHprofData() through reflection.  In the reference
     * implementation this will not be available.
     *
     * @return the reflection object, or null if the method can't be found
     */
    private static Method getDumpHprofDataMethod() {
        ClassLoader myLoader = Main.class.getClassLoader();
        Class vmdClass;
        try {
            vmdClass = myLoader.loadClass("dalvik.system.VMDebug");
        } catch (ClassNotFoundException cnfe) {
            return null;
        }

        Method meth;
        try {
            meth = vmdClass.getMethod("dumpHprofData",
                    new Class[] { String.class });
        } catch (NoSuchMethodException nsme) {
            System.err.println("Found VMDebug but not dumpHprofData method");
            return null;
        }

        return meth;
    }

    private static String getDumpFileName() {
        File tmpDir = new File("/tmp");
        if (tmpDir.exists() && tmpDir.isDirectory()) {
            return "/tmp/" + OUTPUT_FILE;
        }

        File sdcard = new File("/sdcard");
        if (sdcard.exists() && sdcard.isDirectory()) {
            return "/sdcard/" + OUTPUT_FILE;
        }

        return null;
    }


    /**
     * Run the various tests for a set period.
     */
    public static void runTests() {
        Robin robin = new Robin();
        Deep deep = new Deep();
        Large large = new Large();

        /* start all threads */
        robin.start();
        deep.start();
        large.start();

        /* let everybody run for 10 seconds */
        sleep(TEST_TIME * 1000);

        quit = true;

        try {
            /* wait for all threads to stop */
            robin.join();
            deep.join();
            large.join();
        } catch (InterruptedException ie) {
            System.err.println("join was interrupted");
        }
    }

    /**
     * Sleeps for the "ms" milliseconds.
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            System.err.println("sleep was interrupted");
        }
    }

    /**
     * Sleeps briefly, allowing other threads some CPU time to get started.
     */
    public static void startupDelay() {
        sleep(500);
    }
}


/**
 * Allocates useless objects and holds on to several of them.
 *
 * Uses a single large array of references, replaced repeatedly in round-robin
 * order.
 */
class Robin extends Thread {
    private static final int ARRAY_SIZE = 40960;
    int sleepCount = 0;

    public void run() {
        Main.startupDelay();

        String strings[] = new String[ARRAY_SIZE];
        int idx = 0;

        while (!Main.quit) {
            strings[idx] = makeString(idx);

            if (idx % (ARRAY_SIZE / 4) == 0) {
                Main.sleep(400);
                sleepCount++;
            }

            idx = (idx + 1) % ARRAY_SIZE;
        }

        if (Main.DEBUG)
            System.out.println("Robin: sleepCount=" + sleepCount);
    }

    private String makeString(int val) {
        return new String("Robin" + val);
    }
}


/**
 * Allocates useless objects in recursive calls.
 */
class Deep extends Thread {
    private static final int MAX_DEPTH = 61;

    private static String strong[] = new String[MAX_DEPTH];
    private static WeakReference weak[] = new WeakReference[MAX_DEPTH];

    public void run() {
        int iter = 0;
        boolean once = false;

        Main.startupDelay();

        while (!Main.quit) {
            dive(0, iter);
            once = true;
            iter += MAX_DEPTH;
        }

        if (!once) {
            System.err.println("not even once?");
            return;
        }

        /*
         * Check the results of the last trip through.  Everything in
         * "weak" should be matched in "strong", and the two should be
         * equivalent (object-wise, not just string-equality-wise).
         */
        for (int i = 0; i < MAX_DEPTH; i++) {
            if (strong[i] != weak[i].get()) {
                System.err.println("Deep: " + i + " strong=" + strong[i] +
                    ", weak=" + weak[i].get());
            }
        }

        /*
         * Wipe "strong", do a GC, see if "weak" got collected.
         */
        for (int i = 0; i < MAX_DEPTH; i++)
            strong[i] = null;

        System.gc();

        for (int i = 0; i < MAX_DEPTH; i++) {
            if (weak[i].get() != null) {
                System.err.println("Deep: weak still has " + i);
            }
        }

        if (Main.DEBUG)
            System.out.println("Deep: iters=" + iter / MAX_DEPTH);
    }

    /**
     * Recursively dive down, setting one or more local variables.
     *
     * We pad the stack out with locals, attempting to create a mix of
     * valid and invalid references on the stack.
     */
    private String dive(int depth, int iteration) {
        String str0;
        String str1;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String funStr;

        funStr = "";

        switch (iteration % 8) {
            case 0:
                funStr = str0 = makeString(iteration);
                break;
            case 1:
                funStr = str1 = makeString(iteration);
                break;
            case 2:
                funStr = str2 = makeString(iteration);
                break;
            case 3:
                funStr = str3 = makeString(iteration);
                break;
            case 4:
                funStr = str4 = makeString(iteration);
                break;
            case 5:
                funStr = str5 = makeString(iteration);
                break;
            case 6:
                funStr = str6 = makeString(iteration);
                break;
            case 7:
                funStr = str7 = makeString(iteration);
                break;
        }

        strong[depth] = funStr;
        weak[depth] = new WeakReference(funStr);

        if (depth+1 < MAX_DEPTH)
            dive(depth+1, iteration+1);
        else
            Main.sleep(100);

        return funStr;
    }

    private String makeString(int val) {
        return new String("Deep" + val);
    }
}


/**
 * Allocates large useless objects.
 */
class Large extends Thread {
    public void run() {
        byte[] chunk;
        int count = 0;
        int sleepCount = 0;

        Main.startupDelay();

        while (!Main.quit) {
            chunk = new byte[100000];
            pretendToUse(chunk);

            count++;
            if ((count % 500) == 0) {
                Main.sleep(400);
                sleepCount++;
            }
        }

        if (Main.DEBUG)
            System.out.println("Large: sleepCount=" + sleepCount);
    }

    public void pretendToUse(byte[] chunk) {}
}
