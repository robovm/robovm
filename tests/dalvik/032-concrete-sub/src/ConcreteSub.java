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

import java.lang.reflect.Method;

/**
 * Test insertion of an abstract method in a superclass.
 */
public class ConcreteSub extends AbstractBase {
    private static void callBase(AbstractBase abs) {
        System.out.println("calling abs.doStuff()");
        abs.doStuff();
    }

    public static void main() {
        ConcreteSub sub = new ConcreteSub();

        try {
            callBase(sub);
        } catch (AbstractMethodError ame) {
            System.out.println("Got expected exception from abs.doStuff().");
        }

        /*
         * Check reflection stuff.
         */
        Class absClass = AbstractBase.class;
        Method meth;

        System.out.println("class modifiers=" + absClass.getModifiers());

        try {
            meth = absClass.getMethod("redefineMe", (Class[]) null);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
            return;
        }
        System.out.println("meth modifiers=" + meth.getModifiers());
    }
}
