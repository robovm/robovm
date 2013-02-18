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

public class Main {
    public static void main(String args[]) {
        Derived derived = new Derived();

        derived.declaredInBase();
        derived.notDeclaredInBase();
        derived.wasOverridden();

        derived.callOverrideWithPublic();
        derived.callOverrideProtectedWithPublic();
        derived.callOverridePublicWithProtected();
        derived.callOverridePublicWithPrivate();
        derived.callOverridePrivateWithPublic();
        derived.overridePrivateWithPublic();
        derived.callOverrideVirtualWithStatic();
        derived.overrideVirtualWithStatic();
        derived.callOverrideStaticWithVirtual();
        derived.overrideStaticWithVirtual();

        try {
            ((Base)derived).overrideVirtualWithStatic();
        } catch (NoSuchMethodError nsme) {
            /* NSME is subclass of ICCE, so check it explicitly */
            System.err.println("Got NSME - ovws");
        } catch (IncompatibleClassChangeError icce) {
            System.out.println("Got expected exception - ovws");
        }

        try {
            ((Base)derived).overrideStaticWithVirtual();
        } catch (NoSuchMethodError nsme) {
            System.err.println("Got NSME - ovws");
        } catch (IncompatibleClassChangeError icce) {
            System.out.println("Got expected exception - oswv");
        }
    }
}
