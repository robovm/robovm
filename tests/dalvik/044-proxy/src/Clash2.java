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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * Try to instantiate a proxy class with interfaces that have conflicting
 * duplicate methods (primitive types).
 */
public class Clash2 {
    public static void main(String[] args) {
        InvocationHandler handler = new Clash2InvocationHandler();

        try {
            Proxy.newProxyInstance(Clash.class.getClassLoader(),
                new Class[] { Interface2A.class, Interface2B.class },
                handler);
            System.err.println("Clash2 did not throw expected exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Clash2 threw expected exception");
        }
    }
}

interface Interface2A {
    public int thisIsOkay();

    public int thisIsTrouble();
}

interface Interface2B {
    public int thisIsOkay();

    public short thisIsTrouble();
}

class Clash2InvocationHandler implements InvocationHandler {
    /* don't really need to do anything -- should never get this far */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        return null;
    }
}
