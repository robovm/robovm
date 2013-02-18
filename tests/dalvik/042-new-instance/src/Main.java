/*
 * Copyright (C) 2007 The Android Open Source Project
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

/**
 * Test instance creation.
 */
public class Main {
    private static boolean FULL_ACCESS_CHECKS = false;  // b/5861201

    public static void main(String[] args) {
        testClassNewInstance();
        testConstructorNewInstance();
    }

    /**
     * Tests Class.newInstance().
     */
    static void testClassNewInstance() {
        // should succeed
        try {
            Class c = Class.forName("LocalClass");
            Object obj = c.newInstance();
            System.out.println("LocalClass succeeded");
        } catch (Exception ex) {
            System.err.println("LocalClass failed");
            ex.printStackTrace();
        }

        // should fail
        try {
            Class c = Class.forName("otherpackage.PackageAccess");
            Object obj = c.newInstance();
            System.err.println("ERROR: PackageAccess succeeded unexpectedly");
        } catch (IllegalAccessException iae) {
            System.out.println("Got expected PackageAccess complaint");
        } catch (Exception ex) {
            System.err.println("Got unexpected PackageAccess failure");
            ex.printStackTrace();
        }

        LocalClass3.main();

        try {
            MaybeAbstract ma = new MaybeAbstract();
            System.err.println("ERROR: MaybeAbstract succeeded unexpectedly");
        } catch (InstantiationError ie) {
            System.out.println("Got expected InstantationError");
        } catch (Exception ex) {
            System.err.println("Got unexpected MaybeAbstract failure");
        }
    }

    /**
     * Tests Constructor.newInstance().
     */
    static void testConstructorNewInstance() {
        // should fail -- getConstructor only returns public constructors
        try {
            Class c = Class.forName("LocalClass");
            Constructor cons = c.getConstructor(new Class[0] /*(Class[])null*/);
            System.err.println("Cons LocalClass succeeded unexpectedly");
        } catch (NoSuchMethodException nsme) {
            System.out.println("Cons LocalClass failed as expected");
        } catch (Exception ex) {
            System.err.println("Cons LocalClass failed strangely");
            ex.printStackTrace();
        }

        // should succeed
        try {
            Class c = Class.forName("LocalClass2");
            Constructor cons = c.getConstructor((Class[]) null);
            Object obj = cons.newInstance();
            System.out.println("Cons LocalClass2 succeeded");
        } catch (Exception ex) {
            System.err.println("Cons LocalClass2 failed");
            ex.printStackTrace();
        }

        // should fail
        try {
            Class c = Class.forName("otherpackage.PackageAccess");
            Constructor cons = c.getConstructor(new Class[0] /*(Class[])null*/);
            System.err.println("ERROR: Cons PackageAccess succeeded unexpectedly");
        } catch (NoSuchMethodException nsme) {
            // constructor isn't public
            System.out.println("Cons got expected PackageAccess complaint");
        } catch (Exception ex) {
            System.err.println("Cons got unexpected PackageAccess failure");
            ex.printStackTrace();
        }

        // should fail
        try {
            Class c = Class.forName("MaybeAbstract");
            Constructor cons = c.getConstructor(new Class[0] /*(Class[])null*/);
            Object obj = cons.newInstance();
            System.err.println("ERROR: Cons MaybeAbstract succeeded unexpectedly");
        } catch (InstantiationException ie) {
            // note InstantiationException vs. InstantiationError
            System.out.println("Cons got expected InstantationException");
        } catch (Exception ex) {
            System.err.println("Cons got unexpected MaybeAbstract failure");
            ex.printStackTrace();
        }

        // should fail
        try {
            Class c = Class.forName("otherpackage.PackageAccess2");
            Constructor cons = c.getConstructor((Class[]) null);
            if (!FULL_ACCESS_CHECKS) { throw new IllegalAccessException(); }
            Object obj = cons.newInstance();
            System.err.println("ERROR: Cons PackageAccess2 succeeded unexpectedly");
        } catch (IllegalAccessException iae) {
            // constructor is public, but class has package scope
            System.out.println("Cons got expected PackageAccess2 complaint");
        } catch (Exception ex) {
            System.err.println("Cons got unexpected PackageAccess2 failure");
            ex.printStackTrace();
        }

    }
}

class LocalClass {
    // this class has a default constructor with package visibility
}

class LocalClass2 {
    public LocalClass2() {}
}


class LocalClass3 {
    public static void main() {
        try {
            CC.newInstance();
            System.out.println("LocalClass3 succeeded");
        } catch (Exception ex) {
            System.err.println("Got unexpected LocalClass3 failure");
            ex.printStackTrace();
        }
    }

    static class CC {
        private CC() {}

        static Object newInstance() {
            try {
                Class c = CC.class;
                return c.newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
