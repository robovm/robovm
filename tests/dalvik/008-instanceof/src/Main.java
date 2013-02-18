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
 * Test instanceof
 */
public class Main {
    public static void main(String args[]) {
        Iface1 face1;
        ImplA aa = new ImplA();
        ImplBSub bb = new ImplBSub();
        boolean aaOkay, bbOkay;

        face1 = aa;
        face1 = bb;

        System.out.println("iface1.mFloaty = " + face1.mFloaty + " " + face1.mWahoo);
        System.out.println("aa.mFloaty = " + aa.mFloaty + " " + aa.mWahoo);
        System.out.println("bb.mWhoami = " + bb.mWhoami);

        aaOkay = face1 instanceof ImplA;
        System.out.print("aaOkay (false) = ");
        System.out.println(aaOkay);
        bbOkay = face1 instanceof ImplB;
        System.out.print("bbOkay (true) = ");
        System.out.println(bbOkay);

        bb = (ImplBSub) face1;
        try {
            aa = (ImplA) face1;
        }
        catch (ClassCastException cce) {
            System.out.println("Caught a ClassCastException (expected)");
        }
    }
}
