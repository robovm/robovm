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
 * duplicate methods (tree of types).
 */
public class Clash4 {
    public static void main(String[] args) {
        InvocationHandler handler = new Clash4InvocationHandler();

        try {
            Proxy.newProxyInstance(Clash.class.getClassLoader(),
                new Class[] {
                    Interface4a.class,
                    Interface4aa.class,
                    Interface4base.class,
                    Interface4b.class,
                    Interface4bb.class },
                handler);
            System.err.println("Clash4 did not throw expected exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Clash4 threw expected exception");
            //System.out.println(iae);
        }
    }
}

class R4base { int mBlah;  }
class R4a extends R4base { int mBlah_a;  }
class R4aa extends R4a { int mBlah_aa;  }
class R4b extends R4base { int mBlah_b;  }
class R4bb extends R4b { int mBlah_bb;  }

interface Interface4base {
    public R4base thisIsTrouble();
}

interface Interface4a {
    public R4a thisIsTrouble();
}
interface Interface4aa {
    public R4aa thisIsTrouble();
}
interface Interface4b {
    public R4b thisIsTrouble();
}
interface Interface4bb {
    public R4bb thisIsTrouble();
}

class Clash4InvocationHandler implements InvocationHandler {
    /* don't really need to do anything -- should never get this far */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        return null;
    }
}
