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

/**
 * Class loader test.
 */
public class Main {
    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        FancyLoader loader;

        loader = new FancyLoader(ClassLoader.getSystemClassLoader());
        //System.out.println("SYSTEM: " + ClassLoader.getSystemClassLoader());
        //System.out.println("ALTERN: " + loader);

        /*
         * This statement has no effect on this program, but it can
         * change the point where a LinkageException is thrown in
         * testImplement().  When this is present the "reference
         * implementation" throws an exception from Class.newInstance(),
         * when it's absent the exception is deferred until the first time
         * we call a method that isn't actually implemented.
         *
         * This isn't the class that fails -- it's a class with the same
         * name in the "fancy" class loader --  but the VM thinks it has a
         * reference to one of these; presumably the difference is that
         * without this the VM finds itself holding a reference to an
         * instance of an uninitialized class.
         */
        System.out.println("base: " + DoubledImplement.class);
        System.out.println("base2: " + DoubledImplement2.class);

        /*
         * Run tests.
         */
        testAccess1(loader);
        testAccess2(loader);
        testAccess3(loader);

        testExtend(loader);
        testExtendOkay(loader);
        testInterface(loader);
        testAbstract(loader);
        testImplement(loader);
        testIfaceImplement(loader);
    }

    /**
     * See if we can load a class that isn't public to us.  We should be
     * able to load it but not instantiate it.
     */
    static void testAccess1(ClassLoader loader) {
        Class altClass;

        try {
            altClass = loader.loadClass("Inaccessible1");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed");
            cnfe.printStackTrace();
            return;
        }

        /* instantiate */
        Object obj;
        try {
            obj = altClass.newInstance();
            System.err.println("ERROR: Inaccessible1 was accessible");
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.out.println("Got expected access exception #1");
            //System.out.println("+++ " + iae);
            return;
        }
    }

    /**
     * See if we can load a class whose base class is not accessible to it
     * (though the base *is* accessible to us).
     */
    static void testAccess2(ClassLoader loader) {
        Class altClass;

        try {
            altClass = loader.loadClass("Inaccessible2");
            System.err.println("ERROR: Inaccessible2 was accessible");
        } catch (ClassNotFoundException cnfe) {
            Throwable cause = cnfe.getCause();
            if (cause instanceof IllegalAccessError) {
                System.out.println("Got expected CNFE/IAE #2");
            } else {
                System.err.println("Got unexpected CNFE/IAE #2");
                cnfe.printStackTrace();
            }
        }
    }

    /**
     * See if we can load a class with an inaccessible interface.
     */
    static void testAccess3(ClassLoader loader) {
        Class altClass;

        try {
            altClass = loader.loadClass("Inaccessible3");
            System.err.println("ERROR: Inaccessible3 was accessible");
        } catch (ClassNotFoundException cnfe) {
            Throwable cause = cnfe.getCause();
            if (cause instanceof IllegalAccessError) {
                System.out.println("Got expected CNFE/IAE #3");
            } else {
                System.err.println("Got unexpected CNFE/IAE #3");
                cnfe.printStackTrace();
            }
        }
    }

    /**
     * Test a doubled class that extends the base class.
     */
    static void testExtend(ClassLoader loader) {
        Class doubledExtendClass;
        Object obj;

        /* get the "alternate" version of DoubledExtend */
        try {
            doubledExtendClass = loader.loadClass("DoubledExtend");
            //System.out.println("+++ DoubledExtend is " + doubledExtendClass
            //    + " in " + doubledExtendClass.getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = doubledExtendClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            System.out.println("Got expected LinkageError on DE");
            return;
        }

        /* use the base class reference to get a CL-specific instance */
        Base baseRef = (Base) obj;
        DoubledExtend de = baseRef.getExtended();

        /* try to call through it */
        try {
            String result;

            result = Base.doStuff(de);
            System.err.println("ERROR: did not get LinkageError on DE");
            System.err.println("(result=" + result + ")");
        } catch (LinkageError le) {
            System.out.println("Got expected LinkageError on DE");
            return;
        }
    }

    /**
     * Test a doubled class that extends the base class, but is okay since
     * it doesn't override the base class method.
     */
    static void testExtendOkay(ClassLoader loader) {
        Class doubledExtendOkayClass;
        Object obj;

        /* get the "alternate" version of DoubledExtendOkay */
        try {
            doubledExtendOkayClass = loader.loadClass("DoubledExtendOkay");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = doubledExtendOkayClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            System.err.println("Got unexpected LinkageError on DEO");
            le.printStackTrace();
            return;
        }

        /* use the base class reference to get a CL-specific instance */
        BaseOkay baseRef = (BaseOkay) obj;
        DoubledExtendOkay de = baseRef.getExtended();

        /* try to call through it */
        try {
            String result;

            result = BaseOkay.doStuff(de);
            System.out.println("Got DEO result " + result);
        } catch (LinkageError le) {
            System.err.println("Got unexpected LinkageError on DEO");
            le.printStackTrace();
            return;
        }
    }

    /**
     * Try to access a doubled class through a class that implements
     * an interface declared in a different class.
     */
    static void testInterface(ClassLoader loader) {
        Class getDoubledClass;
        Object obj;

        /* get GetDoubled from the "alternate" class loader */
        try {
            getDoubledClass = loader.loadClass("GetDoubled");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = getDoubledClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            // Dalvik bails here
            System.out.println("Got LinkageError on GD");
            return;
        }

        /*
         * Cast the object to the interface, and try to use it.
         */
        IGetDoubled iface = (IGetDoubled) obj;
        try {
            /* "de" will be the wrong variety of DoubledExtendOkay */
            DoubledExtendOkay de = iface.getDoubled();
            // reference impl bails here
            String str = de.getStr();
        } catch (LinkageError le) {
            System.out.println("Got LinkageError on GD");
            return;
        }
        System.err.println("Should have failed by now on GetDoubled");
    }

    /**
     * Throw an abstract class into the middle and see what happens.
     */
    static void testAbstract(ClassLoader loader) {
        Class abstractGetClass;
        Object obj;

        /* get AbstractGet from the "alternate" loader */
        try {
            abstractGetClass = loader.loadClass("AbstractGet");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass ta failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = abstractGetClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            System.out.println("Got LinkageError on TA");
            return;
        }

        /* use the base class reference to get a CL-specific instance */
        BaseOkay baseRef = (BaseOkay) obj;
        DoubledExtendOkay de = baseRef.getExtended();

        /* try to call through it */
        try {
            String result;

            result = BaseOkay.doStuff(de);
        } catch (LinkageError le) {
            System.out.println("Got LinkageError on TA");
            return;
        }
        System.err.println("Should have failed by now in testAbstract");
    }

    /**
     * Test a doubled class that implements a common interface.
     */
    static void testImplement(ClassLoader loader) {
        Class doubledImplementClass;
        Object obj;

        useImplement(new DoubledImplement(), true);

        /* get the "alternate" version of DoubledImplement */
        try {
            doubledImplementClass = loader.loadClass("DoubledImplement");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = doubledImplementClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            System.out.println("Got LinkageError on DI (early)");
            return;
        }

        /* if we lived this long, try to do something with it */
        ICommon icommon = (ICommon) obj;
        useImplement(icommon.getDoubledInstance(), false);
    }

    /**
     * Do something with a DoubledImplement instance.
     */
    static void useImplement(DoubledImplement di, boolean isOne) {
        //System.out.println("useObject: " + di.toString() + " -- "
        //    + di.getClass().getClassLoader());
        try {
            di.one();
            if (!isOne) {
                System.err.println("ERROR: did not get LinkageError on DI");
            }
        } catch (LinkageError le) {
            if (!isOne) {
                System.out.println("Got LinkageError on DI (late)");
            } else {
                throw le;
            }
        }
    }


    /**
     * Test a class that implements an interface with a super-interface
     * that refers to a doubled class.
     */
    static void testIfaceImplement(ClassLoader loader) {
        Class ifaceImplClass;
        Object obj;

        /*
         * Create an instance of IfaceImpl.  We also pull in
         * DoubledImplement2 from the other class loader; without this
         * we don't fail in some implementations.
         */
        try {
            ifaceImplClass = loader.loadClass("IfaceImpl");
            ifaceImplClass = loader.loadClass("DoubledImplement2");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("loadClass failed: " + cnfe);
            return;
        }

        /* instantiate */
        try {
            obj = ifaceImplClass.newInstance();
        } catch (InstantiationException ie) {
            System.err.println("newInstance failed: " + ie);
            return;
        } catch (IllegalAccessException iae) {
            System.err.println("newInstance failed: " + iae);
            return;
        } catch (LinkageError le) {
            System.out.println("Got LinkageError on IDI (early)");
            //System.out.println(le);
            return;
        }

        /*
         * Without the pre-load of FancyLoader->DoubledImplement2, some
         * implementations will happily execute through this part.  "obj"
         * comes from FancyLoader, but the di2 returned from ifaceSuper
         * comes from the application class loader.
         */
        IfaceSuper ifaceSuper = (IfaceSuper) obj;
        DoubledImplement2 di2 = ifaceSuper.getDoubledInstance2();
        di2.one();
    }
}
