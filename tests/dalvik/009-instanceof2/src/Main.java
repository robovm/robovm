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
 * more instanceof cases
 */
public class Main {
    public static void main(String args[]) {
        Iface1[] faceArray;
        ImplA[] aaArray = new ImplA[5];
        ImplBSub[] bbArray = new ImplBSub[5];
        boolean aaOkay, bbOkay;

        faceArray = aaArray;
        faceArray = bbArray;

        System.out.print("instanceof Serializable = ");
        System.out.println((Object)aaArray instanceof java.io.Serializable);
        System.out.print("instanceof Cloneable = ");
        System.out.println((Object)aaArray instanceof java.lang.Cloneable);
        System.out.print("instanceof Runnable = ");
        System.out.println((Object)aaArray instanceof java.lang.Runnable);

        aaOkay = faceArray instanceof ImplA[];
        System.out.print("aaOkay (false) = ");
        System.out.println(aaOkay);
        bbOkay = faceArray instanceof ImplB[];
        System.out.print("bbOkay (true) = ");
        System.out.println(bbOkay);
    }
}
