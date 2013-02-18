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
 * duplicate methods (type tree with interface).
 */
public class Clash3 {
    public static void main(String[] args) {
        InvocationHandler handler = new Clash3InvocationHandler();

        try {
            Proxy.newProxyInstance(Clash.class.getClassLoader(),
                new Class[] {
                    Interface3a.class,
                    Interface3base.class,
                    Interface3aa.class,
                    Interface3b.class },
                handler);
            System.err.println("Clash3 did not throw expected exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Clash3 threw expected exception");
        }
    }
}

class R3base implements I3 { int mBlah; public void x() {} }
class R3a extends R3base { int mBlah_a;  }
class R3aa extends R3a { int mBlah_aa;  }
class R3b implements I3 { int mBlah_b; public void x() {} }

interface I3 {
    void x();
}

interface Interface3base {
    public R3base thisIsTrouble();
}

interface Interface3a {
    public R3a thisIsTrouble();
}
interface Interface3aa {
    public R3aa thisIsTrouble();
}
interface Interface3b {
    public R3b thisIsTrouble();
}

class Clash3InvocationHandler implements InvocationHandler {
    /* don't really need to do anything -- should never get this far */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        return null;
    }
}
