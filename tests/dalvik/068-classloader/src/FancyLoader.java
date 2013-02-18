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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * A class loader with atypical behavior: we try to load a private
 * class implementation before asking the system or boot loader.  This
 * is used to create multiple classes with identical names in a single VM.
 *
 * If DexFile is available, we use that; if not, we assume we're not in
 * Dalvik and instantiate the class with defineClass().
 *
 * The location of the DEX files and class data is dependent upon the
 * test framework.
 */
public class FancyLoader extends ClassLoader {
    /* this is where the "alternate" .class files live */
    static final String CLASS_PATH = "classes-ex/";

    /* this is the "alternate" DEX/Jar file */
    static final String DEX_FILE = "test-ex.jar";

    /* on Dalvik, this is a DexFile; otherwise, it's null */
    private Class mDexClass;

    private Object mDexFile;

    /**
     * Construct FancyLoader, grabbing a reference to the DexFile class
     * if we're running under Dalvik.
     */
    public FancyLoader(ClassLoader parent) {
        super(parent);

        try {
            mDexClass = parent.loadClass("dalvik.system.DexFile");
        } catch (ClassNotFoundException cnfe) {
            // ignore -- not running Dalvik
        }
    }

    /**
     * Finds the class with the specified binary name.
     *
     * We search for a file in CLASS_PATH or pull an entry from DEX_FILE.
     * If we don't find a match, we throw an exception.
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        if (mDexClass != null) {
            return findClassDalvik(name);
        } else {
            return findClassNonDalvik(name);
        }
    }

    /**
     * Finds the class with the specified binary name, from a DEX file.
     */
    private Class<?> findClassDalvik(String name)
        throws ClassNotFoundException {

        if (mDexFile == null) {
            synchronized (FancyLoader.class) {
                Constructor ctor;
                /*
                 * Construct a DexFile object through reflection.
                 */
                try {
                    ctor = mDexClass.getConstructor(new Class[] {String.class});
                } catch (NoSuchMethodException nsme) {
                    throw new ClassNotFoundException("getConstructor failed",
                        nsme);
                }

                try {
                    mDexFile = ctor.newInstance(DEX_FILE);
                } catch (InstantiationException ie) {
                    throw new ClassNotFoundException("newInstance failed", ie);
                } catch (IllegalAccessException iae) {
                    throw new ClassNotFoundException("newInstance failed", iae);
                } catch (InvocationTargetException ite) {
                    throw new ClassNotFoundException("newInstance failed", ite);
                }
            }
        }

        /*
         * Call DexFile.loadClass(String, ClassLoader).
         */
        Method meth;

        try {
            meth = mDexClass.getMethod("loadClass",
                    new Class[] { String.class, ClassLoader.class });
        } catch (NoSuchMethodException nsme) {
            throw new ClassNotFoundException("getMethod failed", nsme);
        }

        try {
            meth.invoke(mDexFile, name, this);
        } catch (IllegalAccessException iae) {
            throw new ClassNotFoundException("loadClass failed", iae);
        } catch (InvocationTargetException ite) {
            throw new ClassNotFoundException("loadClass failed",
                ite.getCause());
        }

        return null;
    }

    /**
     * Finds the class with the specified binary name, from .class files.
     */
    private Class<?> findClassNonDalvik(String name)
        throws ClassNotFoundException {

        String pathName = CLASS_PATH + name + ".class";
        //System.out.println("--- Fancy: looking for " + pathName);

        File path = new File(pathName);
        RandomAccessFile raf;

        try {
            raf = new RandomAccessFile(path, "r");
        } catch (FileNotFoundException fnfe) {
            throw new ClassNotFoundException("Not found: " + pathName);
        }

        /* read the entire file in */
        byte[] fileData;
        try {
            fileData = new byte[(int) raf.length()];
            raf.readFully(fileData);
        } catch (IOException ioe) {
            throw new ClassNotFoundException("Read error: " + pathName);
        } finally {
            try {
                raf.close();
            } catch (IOException ioe) {
                // drop
            }
        }

        /* create the class */
        //System.out.println("--- Fancy: defining " + name);
        try {
            return defineClass(name, fileData, 0, fileData.length);
        } catch (Throwable th) {
            throw new ClassNotFoundException("defineClass failed", th);
        }
    }

    /**
     * Load a class.
     *
     * Normally a class loader wouldn't override this, but we want our
     * version of the class to take precedence over an already-loaded
     * version.
     *
     * We still want the system classes (e.g. java.lang.Object) from the
     * bootstrap class loader.
     */
    protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        Class res;

        /*
         * 1. Invoke findLoadedClass(String) to check if the class has
         * already been loaded.
         *
         * This doesn't change.
         */
        res = findLoadedClass(name);
        if (res != null) {
            System.out.println("FancyLoader.loadClass: "
                + name + " already loaded");
            if (resolve)
                resolveClass(res);
            return res;
        }

        /*
         * 3. Invoke the findClass(String) method to find the class.
         */
        try {
            res = findClass(name);
            if (resolve)
                resolveClass(res);
        }
        catch (ClassNotFoundException e) {
            // we couldn't find it, so eat the exception and keep going
        }

        /*
         * 2. Invoke the loadClass method on the parent class loader.  If
         * the parent loader is null the class loader built-in to the
         * virtual machine is used, instead.
         *
         * (Since we're not in java.lang, we can't actually invoke the
         * parent's loadClass() method, but we passed our parent to the
         * super-class which can take care of it for us.)
         */
        res = super.loadClass(name, resolve);   // returns class or throws
        return res;
    }
}
