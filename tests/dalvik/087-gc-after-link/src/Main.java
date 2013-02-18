/*
 * Copyright (C) 2010 The Android Open Source Project
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Class loader test.
 */
public class Main {
    /**
     * Thrown when an unexpected Exception is caught internally.
     */
    static class TestFailed extends Exception {
        public TestFailed(Throwable cause) {
            super(cause);
        }
    }

    /**
     * A class loader which loads classes from the dex file
     * "test.jar". However, it will return null when asked to load the
     * class InaccessibleSuper.
     *
     * When testing code calls BrokenDexLoader's findBrokenClass(),
     * a BrokenDexLoader will be the defining loader for the class
     * Inaccessible.  The VM will call the defining loader for
     * "InaccessibleSuper", which will return null, which the VM
     * should be able to deal with gracefully.
     *
     * Note that this depends heavily on the Dalvik test harness.
     */
    static class BrokenDexLoader extends ClassLoader {

        /** We return null when asked to load InaccessibleSuper. */
        private static class InaccessibleSuper {}
        private static class Inaccessible extends InaccessibleSuper {}

        private static final String SUPERCLASS_NAME =
                "Main$BrokenDexLoader$InaccessibleSuper";
        private static final String CLASS_NAME =
                "Main$BrokenDexLoader$Inaccessible";

        private static final String DEX_FILE = "test.jar";

        public BrokenDexLoader(ClassLoader parent) {
            super(parent);
        }

        /**
         * Finds the class with the specified binary name, from DEX_FILE.
         *
         * If we don't find a match, we throw an exception.
         */
        private Class<?> findDexClass(String name)
                throws TestFailed, InvocationTargetException
        {
            Object dexFile = null;
            Class dexClass = null;

            try {
                try {
                    /*
                     * Find the DexFile class, and construct a DexFile object
                     * through reflection, then call loadClass on it.
                     */
                    dexClass = ClassLoader.getSystemClassLoader().
                            loadClass("dalvik.system.DexFile");
                    Constructor ctor = dexClass.
                            getConstructor(new Class[] {String.class});
                    dexFile = ctor.newInstance(DEX_FILE);
                    Method meth = dexClass.getMethod("loadClass",
                            new Class[] { String.class, ClassLoader.class });
                    /*
                     * Invoking loadClass on CLASS_NAME is expected to
                     * throw an InvocationTargetException. Anything else
                     * is an error we can't recover from.
                     */
                    meth.invoke(dexFile, name, this);
                } finally {
                    if (dexFile != null) {
                        /* close the DexFile to make CloseGuard happy */
                        Method meth = dexClass.getMethod("close", (Class[]) null);
                        meth.invoke(dexFile);
                    }
                }
            } catch (NoSuchMethodException nsme) {
                throw new TestFailed(nsme);
            } catch (InstantiationException ie) {
                throw new TestFailed(ie);
            } catch (IllegalAccessException iae) {
                throw new TestFailed(iae);
            } catch (ClassNotFoundException cnfe) {
                throw new TestFailed(cnfe);
            }

            return null;
        }

        /**
         * Load a class.
         *
         * Return null if the class's name is SUPERCLASS_NAME;
         * otherwise invoke the super's loadClass method.
         */
        public Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            if (SUPERCLASS_NAME.equals(name)) {
                return null;
            }

            return super.loadClass(name, resolve);
        }

        /**
         * Attempt to find the class with the superclass we refuse to
         * load.  This is expected to throw an
         * InvocationTargetException, with a NullPointerException as
         * its cause.
         */
        public void findBrokenClass()
                throws TestFailed, InvocationTargetException
        {
            findDexClass(CLASS_NAME);
        }
    }

    /**
     * Main entry point.
     */
    public static void main(String[] args)
            throws TestFailed, ClassNotFoundException {
        /*
         * Run test.
         */
        testFailLoadAndGc();
    }

    /**
     * See if we can GC after a failed load.
     */
    static void testFailLoadAndGc() throws TestFailed {
        try {
            BrokenDexLoader loader;

            loader = new BrokenDexLoader(ClassLoader.getSystemClassLoader());
            loader.findBrokenClass();
            System.err.println("ERROR: Inaccessible was accessible");
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            if (cause instanceof NullPointerException) {
                System.err.println("Got expected ITE/NPE");
            } else {
                System.err.println("Got unexpected ITE");
                ite.printStackTrace();
            }
        }
        System.gc();
        System.out.println("GC complete.");
    }
}
