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
 * duplicate methods (primitive vs. object).
 */
public class Clash {
    public static void main(String[] args) {
        InvocationHandler handler = new ClashInvocationHandler();

        /* try passing in the same interface twice */
        try {
            Proxy.newProxyInstance(Clash.class.getClassLoader(),
                new Class[] { Interface1A.class, Interface1A.class },
                handler);
            System.err.println("Dupe did not throw expected exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Dupe threw expected exception");
        }

        try {
            Proxy.newProxyInstance(Clash.class.getClassLoader(),
                new Class[] { Interface1A.class, Interface1B.class },
                handler);
            System.err.println("Clash did not throw expected exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Clash threw expected exception");
        }
    }
}

interface Interface1A {
    public int thisIsOkay();

    public float thisIsTrouble();
}

interface Interface1B {
    public int thisIsOkay();

    public Object thisIsTrouble();
}

class ClashInvocationHandler implements InvocationHandler {
    /* don't really need to do anything -- should never get this far */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        return null;
    }
}
