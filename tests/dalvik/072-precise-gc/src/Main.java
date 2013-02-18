/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) {
        staleStackTest();
    }

    public static void staleStackTest() {
        WeakReference wrefs[] = new WeakReference[10];

        populate(wrefs);

        check(wrefs);
    }

    static void populate(WeakReference[] wrefs) {
        /*
         * Get a bunch of non-constant String objects into registers.  These
         * should be the first locals declared.
         */
        String str0 = generateString("String", 0);
        String str1 = generateString("String", 1);
        String str2 = generateString("String", 2);
        String str3 = generateString("String", 3);
        String str4 = generateString("String", 4);
        String str5 = generateString("String", 5);
        String str6 = generateString("String", 6);
        String str7 = generateString("String", 7);
        String str8 = generateString("String", 8);
        String str9 = generateString("String", 9);

        /* stuff them into the weak references array */
        wrefs[0] = new WeakReference(str0);
        wrefs[1] = new WeakReference(str1);
        wrefs[2] = new WeakReference(str2);
        wrefs[3] = new WeakReference(str3);
        wrefs[4] = new WeakReference(str4);
        wrefs[5] = new WeakReference(str5);
        wrefs[6] = new WeakReference(str6);
        wrefs[7] = new WeakReference(str7);
        wrefs[8] = new WeakReference(str8);
        wrefs[9] = new WeakReference(str9);
    }

    static String generateString(String base, int num) {
        return base + num;
    }

    static void check(WeakReference[] wrefs) {
        /*
         * Declare locals so that our stack overlaps the same region
         * that populate() did.
         */
        String str0;
        String str1;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        int numValid = 0;

        /*
         * This *should* blow out all the weakly-reference objects.  If
         * we still have stale copies of references on the stack, a
         * conservative GC will try to hold on to those objects and the
         * count will be nonzero.
         *
         * Getting a zero result here isn't conclusive, but it's a strong
         * indicator that precise GC is having an impact.
         */
        System.gc();

        for (int i = 0; i < wrefs.length; i++) {
            if (wrefs[i].get() != null)
                numValid++;
        }

        System.out.println("Valid refs: " + numValid);

        /* use the locals in case the compiler gets smart */
        str0 = generateString("String", 0);
        str1 = generateString("String", 1);
        str2 = generateString("String", 2);
        str3 = generateString("String", 3);
        str4 = generateString("String", 4);
        str5 = generateString("String", 5);
        str6 = generateString("String", 6);
        str7 = generateString("String", 7);
        str8 = generateString("String", 8);
        str9 = generateString("String", 9);
        System.out.println(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9);
    }
}
