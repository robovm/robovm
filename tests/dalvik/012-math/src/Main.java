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

/**
 * test simple math opers
 */
public class Main {
    public static void main(String args[]) {
        int pad0, pad1, pad2, pad3, pad4, pad5, pad6, pad7;
        int pad8, pad9, pad10, pad11, pad12, pad13, pad14, pad15;
        int a, b, res;
        //long a, b, res;

        a = 3;
        b = 7;

        res = a + b;
        System.out.println("res:" +res);
        res = a - b;
        System.out.println("res:" +res);
        res = 5 - a;
        System.out.println("res:" +res);
        res = a - 5;
        System.out.println("res:" +res);
        res = a * b;
        System.out.println("res:" +res);
        res = a / b;
        System.out.println("res:" +res);
        res = a % b;
        System.out.println("res:" +res);
        res = a ^ b;
        System.out.println("res:" +res);
        res = a << b;
        System.out.println("res:" +res);
        res = a >> b;
        System.out.println("res:" +res);
        res = a >>> b;
        System.out.println("res:" +res);

        a += b;
        System.out.println("a:" +a);
        a -= b;
        System.out.println("a:" +a);
        a = 5 - a;
        System.out.println("a:" +a);
        a -= 5;
        System.out.println("a:" +a);
        a *= b;
        System.out.println("a:" +a);
        a /= b;
        System.out.println("a:" +a);
        a %= b;
        System.out.println("a:" +a);
        a ^= b;
        System.out.println("a:" +a);
        a <<= b;
        System.out.println("a:" +a);
        a >>= b;
        System.out.println("a:" +a);
        a >>>= b;
        System.out.println("a:" +a);

        double f, g, fres;

        f = 3.0f;
        g = 7.0f;

        fres = f + g;
        System.out.println("fres:" +fres);
        fres = f - g;
        System.out.println("fres:" +fres);
        fres = f * g;
        System.out.println("fres:" +fres);
        fres = f / g;
        System.out.println("fres:" +fres);
        fres = f % g;
        System.out.println("fres:" +fres);
        f += g;
        System.out.println("f:" +f);
        f -= g;
        System.out.println("f:" +f);
        f *= g;
        System.out.println("f:" +f);
        f /= g;
        System.out.println("f:" +f);
        f %= g;
        System.out.println("f:" +f);
    }
}
