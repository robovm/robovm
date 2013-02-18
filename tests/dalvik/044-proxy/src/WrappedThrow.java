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

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/*
 * Create a Proxy class that blah.
 */
public class WrappedThrow {
    public static void main(String[] args) {
        WTMix mix = new WTMix();
        InvocationHandler handler = new WTInvocationHandler(mix);
        Object proxy;

        try {
            proxy = Proxy.newProxyInstance(WrappedThrow.class.getClassLoader(),
                new Class[] { InterfaceW1.class, InterfaceW2.class },
                handler);
        } catch (IllegalArgumentException iae) {
            System.out.println("WT init failed");
            return;
        }

        InterfaceW1 if1 = (InterfaceW1) proxy;
        InterfaceW2 if2 = (InterfaceW2) proxy;
        try {
            if1.throwFunky();
            System.err.println("No exception thrown");
        } catch (UndeclaredThrowableException ute) {
            System.out.println("Got expected UTE");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        try {
            if1.throwFunky2();
            System.err.println("No exception thrown");
        } catch (IOException ioe) {
            System.out.println("Got expected IOE");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        try {
            if2.throwFunky2();
            System.err.println("No exception thrown");
        } catch (IOException ioe) {
            System.out.println("Got expected IOE");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        /*
         * Throw exceptions, walking down the hierarchy.
         */
        try {
            if1.throwException();
            System.err.println("No exception thrown");
        } catch (UndeclaredThrowableException ute) {
            System.out.println("Got expected UTE");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        try {
            if1.throwBase();
            System.err.println("No exception thrown");
        } catch (UndeclaredThrowableException ute) {
            System.out.println("Got expected UTE");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        try {
            if2.throwSub();
            System.err.println("No exception thrown");
        } catch (SubException se) {
            System.out.println("Got expected exception");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        try {
            if2.throwSubSub();
            System.err.println("No exception thrown");
        } catch (SubException se) {
            System.out.println("Got expected exception");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }

        /*
         * Make sure that, if the class explicitly allows the base
         * class of an exception, that we still allow it.
         */
        try {
            if1.bothThrowBase();
            System.err.println("No exception thrown");
        } catch (BaseException se) {
            System.out.println("Got expected exception");
        } catch (Throwable t) {
            System.err.println("Got unexpected exception: " + t);
        }
    }
}

class BaseException extends Exception {}
class SubException extends BaseException {}
class SubSubException extends SubException {}

interface InterfaceW1 {
    public void throwFunky();

    public void throwFunky2() throws BaseException,
           NoSuchMethodException, IOException;

    public void throwException() throws BaseException;
    public void throwBase() throws BaseException;
    public void throwSub() throws BaseException;
    public void throwSubSub() throws BaseException;

    public void bothThrowBase() throws BaseException, SubException, SubSubException;
}

interface InterfaceW2 {
    public void throwFunky2() throws InterruptedException,
           NoSuchMethodException, IOException;

    public void throwException() throws SubException;
    public void throwBase() throws SubException;
    public void throwSub() throws SubException;
    public void throwSubSub() throws SubException;

    public void bothThrowBase() throws SubException, BaseException, SubSubException;
}

/**
 * Implement all of the proxied interfaces.
 */
class WTMix implements InterfaceW1, InterfaceW2 {
    public int dastardlyDeed() throws SubException {
        System.out.println("Throwing SubException");
        throw new SubException();
    }

    /* these don't actually get called; they just cause exceptions */
    public void throwFunky() {}
    public void throwFunky2() {}
    public void throwException() throws SubException {}
    public void throwBase() throws SubException {}
    public void throwSub() throws SubException {}
    public void throwSubSub() throws SubException {}

    public void bothThrowBase() throws BaseException, SubException {}
}

/**
 * Invocation handler for our proxy class.
 */
class WTInvocationHandler implements InvocationHandler {
    private Object mObj;

    public WTInvocationHandler(Object obj) {
        mObj = obj;
    }

    /*
     * This is called when anything gets invoked in the proxy object.
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        Object result = null;

        // Trap Object calls.  This is important here to avoid a recursive
        // invocation of toString() in the print statements below.
        if (method.getDeclaringClass() == java.lang.Object.class) {
            //System.out.println("!!! object " + method.getName());
            if (method.getName().equals("toString"))
                return super.toString();
            else if (method.getName().equals("hashCode"))
                return Integer.valueOf(super.hashCode());
            else if (method.getName().equals("equals"))
                return Boolean.valueOf(super.equals(args[0]));
            else
                throw new RuntimeException("huh?");
        }

        System.out.println("Invoke " + method);
        if (args == null || args.length == 0) {
            System.out.println(" (no args)");
        } else {
            for (int i = 0; i < args.length; i++)
                System.out.println(" " + i + ": " + args[i]);
        }

        try {
            if (method.getName().equals("throwFunky"))
                throw new InterruptedException("fake");
            if (method.getName().equals("throwFunky2"))
                throw new IOException("fake2");
            if (method.getName().equals("throwException"))
                throw new Exception();
            if (method.getName().equals("throwBase"))
                throw new BaseException();
            if (method.getName().equals("throwSub"))
                throw new SubException();
            if (method.getName().equals("throwSubSub"))
                throw new SubSubException();
            if (method.getName().equals("bothThrowBase"))
                throw new BaseException();

            if (true)
                result = method.invoke(mObj, args);
            else
                result = -1;
            System.out.println("Success: method " + method.getName()
                + " res=" + result);
        } catch (InvocationTargetException ite) {
            throw ite.getTargetException();
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
        return result;
    }
}
