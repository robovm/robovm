// Copyright 2006 The Android Open Source Project

import java.lang.reflect.*;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Reflection test.
 */
public class Main {
    private static boolean FULL_ACCESS_CHECKS = false;  // b/5861201
    public Main() {}
    public Main(ArrayList<Integer> stuff) {}

    void printMethodInfo(Method meth) {
        Class[] params, exceptions;
        int i;

        System.out.println("Method name is " + meth.getName());
        System.out.println(" Declaring class is "
            + meth.getDeclaringClass().getName());
        params = meth.getParameterTypes();
        for (i = 0; i < params.length; i++)
            System.out.println(" Arg " + i + ": " + params[i].getName());
        exceptions = meth.getExceptionTypes();
        for (i = 0; i < exceptions.length; i++)
            System.out.println(" Exc " + i + ": " + exceptions[i].getName());
        System.out.println(" Return type is " + meth.getReturnType().getName());
        System.out.println(" Access flags are 0x"
            + Integer.toHexString(meth.getModifiers()));
        //System.out.println(" GenericStr is " + meth.toGenericString());
    }

    void printFieldInfo(Field field) {
        System.out.println("Field name is " + field.getName());
        System.out.println(" Declaring class is "
            + field.getDeclaringClass().getName());
        System.out.println(" Field type is " + field.getType().getName());
        System.out.println(" Access flags are 0x"
            + Integer.toHexString(field.getModifiers()));
    }

    private void showStrings(Target instance)
        throws NoSuchFieldException, IllegalAccessException {

        Class target = Target.class;
        String one, two, three, four;
        Field field = null;

        field = target.getField("string1");
        one = (String) field.get(instance);

        field = target.getField("string2");
        two = (String) field.get(instance);

        field = target.getField("string3");
        three = (String) field.get(instance);

        System.out.println("  ::: " + one + ":" + two + ":" + three);
    }

    public static void checkAccess() {
        try {
            Class target = otherpackage.Other.class;
            Object instance = new otherpackage.Other();
            Method meth;

            meth = target.getMethod("publicMethod", (Class[]) null);
            meth.invoke(instance);

            try {
                meth = target.getMethod("packageMethod", (Class[]) null);
                System.err.println("succeeded on package-scope method");
            } catch (NoSuchMethodException nsme) {
                // good
            }


            instance = otherpackage.Other.getInnerClassInstance();
            target = instance.getClass();
            meth = target.getMethod("innerMethod", (Class[]) null);
            try {
                if (!FULL_ACCESS_CHECKS) { throw new IllegalAccessException(); }
                meth.invoke(instance);
                System.err.println("inner-method invoke unexpectedly worked");
            } catch (IllegalAccessException iae) {
                // good
            }

            Field field = target.getField("innerField");
            try {
                int x = field.getInt(instance);
                if (!FULL_ACCESS_CHECKS) { throw new IllegalAccessException(); }
                System.err.println("field get unexpectedly worked: " + x);
            } catch (IllegalAccessException iae) {
                // good
            }
        } catch (Exception ex) {
            System.out.println("----- unexpected exception -----");
            ex.printStackTrace();
        }
    }

    public void run() {
        Class target = Target.class;
        Method meth = null;
        Field field = null;
        boolean excep;

        try {
            meth = target.getMethod("myMethod", new Class[] { int.class });

            if (meth.getDeclaringClass() != target)
                throw new RuntimeException();
            printMethodInfo(meth);

            meth = target.getMethod("myMethod", new Class[] { float.class });
            printMethodInfo(meth);

            meth = target.getMethod("myNoargMethod", (Class[]) null);
            printMethodInfo(meth);

            meth = target.getMethod("myMethod",
                new Class[] { String[].class, float.class, char.class });
            printMethodInfo(meth);

            Target instance = new Target();
            Object[] argList = new Object[] {
                new String[] { "hi there" },
                new Float(3.1415926f),
                new Character('Q')
            };
            System.out.println("Before, float is "
                + ((Float)argList[1]).floatValue());

            Integer boxval;
            boxval = (Integer) meth.invoke(instance, argList);
            System.out.println("Result of invoke: " + boxval.intValue());

            System.out.println("Calling no-arg void-return method");
            meth = target.getMethod("myNoargMethod", (Class[]) null);
            meth.invoke(instance, (Object[]) null);

            /* try invoking a method that throws an exception */
            meth = target.getMethod("throwingMethod", (Class[]) null);
            try {
                meth.invoke(instance, (Object[]) null);
                System.out.println("GLITCH: didn't throw");
            } catch (InvocationTargetException ite) {
                System.out.println("Invoke got expected exception:");
                System.out.println(ite.getClass().getName());
                System.out.println(ite.getCause());
            }
            catch (Exception ex) {
                System.out.println("GLITCH: invoke got wrong exception:");
                ex.printStackTrace();
            }
            System.out.println("");


            field = target.getField("string1");
            if (field.getDeclaringClass() != target)
                throw new RuntimeException();
            printFieldInfo(field);
            String strVal = (String) field.get(instance);
            System.out.println("  string1 value is '" + strVal + "'");

            showStrings(instance);

            field.set(instance, new String("a new string"));
            strVal = (String) field.get(instance);
            System.out.println("  string1 value is now '" + strVal + "'");

            showStrings(instance);

            try {
                field.set(instance, new Object());
                System.out.println("WARNING: able to store Object into String");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected illegal obj store exc");
            }


            try {
                String four;
                field = target.getField("string4");
                four = (String) field.get(instance);
                System.out.println("WARNING: able to access string4: "
                    + four);
            }
            catch (IllegalAccessException iae) {
                System.out.println("  got expected access exc");
            }
            catch (NoSuchFieldException nsfe) {
                System.out.println("  got the other expected access exc");
            }
            try {
                String three;
                field = target.getField("string3");
                three = (String) field.get(this);
                System.out.println("WARNING: able to get string3 in wrong obj: "
                    + three);
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected arg exc");
            }

            /*
             * Try setting a field to null.
             */
            String four;
            field = target.getDeclaredField("string3");
            field.set(instance, null);

            /*
             * Do some stuff with long.
             */
            long longVal;
            field = target.getField("pubLong");
            longVal = field.getLong(instance);
            System.out.println("pubLong initial value is " +
                Long.toHexString(longVal));
            field.setLong(instance, 0x9988776655443322L);
            longVal = field.getLong(instance);
            System.out.println("pubLong new value is " +
                Long.toHexString(longVal));


            field = target.getField("superInt");
            if (field.getDeclaringClass() == target)
                throw new RuntimeException();
            printFieldInfo(field);
            int intVal = field.getInt(instance);
            System.out.println("  superInt value is " + intVal);
            Integer boxedIntVal = (Integer) field.get(instance);
            System.out.println("  superInt boxed is " + boxedIntVal);

            field.set(instance, new Integer(20202));
            intVal = field.getInt(instance);
            System.out.println("  superInt value is now " + intVal);
            field.setShort(instance, (short)30303);
            intVal = field.getInt(instance);
            System.out.println("  superInt value (from short) is now " +intVal);
            field.setInt(instance, 40404);
            intVal = field.getInt(instance);
            System.out.println("  superInt value is now " + intVal);
            try {
                field.set(instance, new Long(123));
                System.out.println("FAIL: expected exception not thrown");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected long->int failure");
            }
            try {
                field.setLong(instance, 123);
                System.out.println("FAIL: expected exception not thrown");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected long->int failure");
            }
            try {
                field.set(instance, new String("abc"));
                System.out.println("FAIL: expected exception not thrown");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected string->int failure");
            }

            try {
                field.getShort(instance);
                System.out.println("FAIL: expected exception not thrown");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected int->short failure");
            }

            field = target.getField("superClassInt");
            printFieldInfo(field);
            int superClassIntVal = field.getInt(instance);
            System.out.println("  superClassInt value is " + superClassIntVal);

            field = target.getField("staticDouble");
            printFieldInfo(field);
            double staticDoubleVal = field.getDouble(null);
            System.out.println("  staticDoubleVal value is " + staticDoubleVal);

            try {
                field.getLong(instance);
                System.out.println("FAIL: expected exception not thrown");
            }
            catch (IllegalArgumentException iae) {
                System.out.println("  got expected double->long failure");
            }

            excep = false;
            try {
                field = target.getField("aPrivateInt");
                printFieldInfo(field);
            }
            catch (NoSuchFieldException nsfe) {
                System.out.println("as expected: aPrivateInt not found");
                excep = true;
            }
            if (!excep)
                System.out.println("BUG: got aPrivateInt");


            field = target.getField("constantString");
            printFieldInfo(field);
            String val = (String) field.get(instance);
            System.out.println("  Constant test value is " + val);


            field = target.getField("cantTouchThis");
            printFieldInfo(field);
            intVal = field.getInt(instance);
            System.out.println("  cantTouchThis is " + intVal);
            try {
                field.setInt(instance, 99);
                System.out.println("ERROR: set-final succeeded");
            } catch (IllegalAccessException iae) {
                System.out.println("  got expected set-final failure");
            }
            intVal = field.getInt(instance);
            System.out.println("  cantTouchThis is now " + intVal);

            field.setAccessible(true);
            field.setInt(instance, 87);     // exercise int version
            field.set(instance, 88);        // exercise Object version
            intVal = field.getInt(instance);
            System.out.println("  cantTouchThis is now " + intVal);

            Constructor<Target> cons;
            Target targ;
            Object[] args;

            cons = target.getConstructor(new Class[] { int.class,float.class });
            args = new Object[] { new Integer(7), new Float(3.3333) };
            System.out.println("cons modifiers=" + cons.getModifiers());
            targ = cons.newInstance(args);
            targ.myMethod(17);

        } catch (Exception ex) {
            System.out.println("----- unexpected exception -----");
            ex.printStackTrace();
        }

        System.out.println("ReflectTest done!");
    }

    public static void checkType() {
        Method m;

        try {
            m = Collections.class.getDeclaredMethod("checkType",
                            Object.class, Class.class);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
            return;
        }

        m.setAccessible(true);
        try {
            m.invoke(null, new Object(), Object.class);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return;
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
            return;
        }

        try {
            System.out.println("checkType invoking null");
            m.invoke(null, new Object(), int.class);
            System.out.println("ERROR: should throw InvocationTargetException");
        } catch (InvocationTargetException ite) {
            System.out.println("checkType got expected exception");
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return;
        }
    }

    public static void checkInit() {
        Class niuClass = NoisyInitUser.class;
        Method[] methods;

        methods = niuClass.getDeclaredMethods();
        System.out.println("got methods");
        /* neither NoisyInit nor NoisyInitUser should be initialized yet */
        NoisyInitUser niu = new NoisyInitUser();
        NoisyInit ni = new NoisyInit();

        System.out.println("");
    }


    /*
     * Test some generic type stuff.
     */
    public List<String> dummy;
    public Map<Integer,String> fancyMethod(ArrayList<String> blah) { return null; }
    public static void checkGeneric() {
        Field field;
        try {
            field = Main.class.getField("dummy");
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe);
        }
        Type listType = field.getGenericType();
        System.out.println("generic field: " + listType);

        Method method;
        try {
            method = Main.class.getMethod("fancyMethod",
                new Class[] { ArrayList.class });
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
        Type[] parmTypes = method.getGenericParameterTypes();
        Type ret = method.getGenericReturnType();
        System.out.println("generic method " + method.getName() + " params='"
            + stringifyTypeArray(parmTypes) + "' ret='" + ret + "'");

        Constructor ctor;
        try {
            ctor = Main.class.getConstructor(new Class[] { ArrayList.class });
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
        parmTypes = ctor.getGenericParameterTypes();
        System.out.println("generic ctor " + ctor.getName() + " params='"
            + stringifyTypeArray(parmTypes) + "'");
    }

    /*
     * Convert an array of Type into a string.  Start with an array count.
     */
    private static String stringifyTypeArray(Type[] types) {
        StringBuilder stb = new StringBuilder();
        boolean first = true;

        stb.append("[" + types.length + "]");

        for (Type t: types) {
            if (first) {
                stb.append(" ");
                first = false;
            } else {
                stb.append(", ");
            }
            stb.append(t.toString());
        }

        return stb.toString();
    }


    public static void main(String[] args) {
        Main test = new Main();
        test.run();

        checkAccess();
        checkType();
        checkInit();
        checkGeneric();
    }
}


class SuperTarget {
    public SuperTarget() {
        System.out.println("SuperTarget constructor ()V");
        superInt = 1010101;
        superClassInt = 1010102;
    }

    public int myMethod(float floatArg) {
        System.out.println("myMethod (F)I " + floatArg);
        return 6;
    }

    public int superInt;
    public static int superClassInt;
}

class Target extends SuperTarget {
    public Target() {
        System.out.println("Target constructor ()V");
    }

    public Target(int ii, float ff) {
        System.out.println("Target constructor (IF)V : ii="
            + ii + " ff=" + ff);
        anInt = ii;
    }

    public int myMethod(int intarg) throws NullPointerException, IOException {
        System.out.println("myMethod (I)I");
        System.out.println(" arg=" + intarg + " anInt=" + anInt);
        return 5;
    }

    public int myMethod(String[] strarg, float f, char c) {
        System.out.println("myMethod: " + strarg[0] + " " + f + " " + c + " !");
        return 7;
    }

    public static void myNoargMethod() {
        System.out.println("myNoargMethod ()V");
    }

    public void throwingMethod() {
        System.out.println("throwingMethod");
        throw new NullPointerException("gratuitous throw!");
    }

    public void misc() {
        System.out.println("misc");
    }

    public int anInt;
    public String string1 = "hey";
    public String string2 = "yo";
    public String string3 = "there";
    private String string4 = "naughty";
    public static final String constantString = "a constant string";
    private int aPrivateInt;

    public final int cantTouchThis = 77;

    public long pubLong = 0x1122334455667788L;

    public static double staticDouble = 3.3;
}

class NoisyInit {
    static {
        System.out.println("NoisyInit is initializing");
        //Throwable th = new Throwable();
        //th.printStackTrace();
    }
}

class NoisyInitUser {
    static {
        System.out.println("NoisyInitUser is initializing");
    }
    public void createNoisyInit(NoisyInit ni) {}
}
