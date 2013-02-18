/*
 * Copyright (C) 2008 The Android Open Source Project
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
import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * DexFile tests (Dalvik-specific).
 */
public class Main {
    private static final String CLASS_PATH = "test-ex.jar";
    private static final String ODEX_DIR = "/sdcard";
    //private static final String ODEX_DIR = ".";
    private static final String ODEX_ALT = "/tmp";
    private static final String LIB_DIR = "/nowhere/nothing/";

    /**
     * Prep the environment then run the test.
     */
    public static void main(String[] args) {
        Process p;
        try {
            /*
             * Create a sub-process to see if the ProcessManager wait
             * interferes with the dexopt invocation wait.
             *
             * /dev/random never hits EOF, so we're sure that we'll still
             * be waiting for the process to complete.  On the device it
             * stops pretty quickly (which means the child won't be
             * spinning).
             */
            ProcessBuilder pb = new ProcessBuilder("cat", "/dev/random");
            p = pb.start();
        } catch (IOException ioe) {
            System.err.println("cmd failed: " + ioe.getMessage());
            p = null;
        }

        try {
            testDexClassLoader();
        } finally {
            // shouldn't be necessary, but it's good to be tidy
            if (p != null)
                p.destroy();

            // let the ProcessManager's daemon thread finish before we shut down
            // (avoids the occasional segmentation fault)
            try {
                Thread.sleep(500);
            } catch (Exception ex) {}
        }

        System.out.println("done");
    }

    /**
     * Create a class loader, explicitly specifying the source DEX and
     * the location for the optimized DEX.
     */
    private static void testDexClassLoader() {
        ClassLoader dexClassLoader = getDexClassLoader();

        Class anotherClass;
        try {
            anotherClass = dexClassLoader.loadClass("Another");
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException("Another?");
        }

        Object another;
        try {
            another = anotherClass.newInstance();
        } catch (IllegalAccessException ie) {
            throw new RuntimeException("new another", ie);
        } catch (InstantiationException ie) {
            throw new RuntimeException("new another", ie);
        }

        // not expected to work; just exercises the call
        dexClassLoader.getResource("nonexistent");
    }

    /*
     * Create an instance of DexClassLoader.  The test harness doesn't
     * have visibility into dalvik.system.*, so we do this through
     * reflection.
     */
    private static ClassLoader getDexClassLoader() {
        String odexDir;

        /*
        String androidData = System.getenv("ANDROID_DATA");
        if (androidData == null)
            androidData = "";
        odexDir = androidData + "/" + ODEX_DIR;
        */

        File test = new File(ODEX_DIR);
        if (test.isDirectory())
            odexDir = ODEX_DIR;
        else
            odexDir = ODEX_ALT;
        //System.out.println("Output dir is " + odexDir);

        ClassLoader myLoader = Main.class.getClassLoader();
        Class dclClass;
        try {
            dclClass = myLoader.loadClass("dalvik.system.DexClassLoader");
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException("dalvik.system.DexClassLoader not found");
        }

        Constructor ctor;
        try {
            ctor = dclClass.getConstructor(String.class, String.class,
                String.class, ClassLoader.class);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException("DCL ctor", nsme);
        }

        // create an instance, using the path we found
        Object dclObj;
        try {
            dclObj = ctor.newInstance(CLASS_PATH, odexDir, LIB_DIR, myLoader);
        } catch (Exception ex) {
            throw new RuntimeException("DCL newInstance", ex);
        }

        return (ClassLoader) dclObj;
    }
}
