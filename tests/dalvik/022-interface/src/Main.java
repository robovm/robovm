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
 * test calling through an interface
 */
public class Main {
    public static void main(String args[]) {
        int result = 0;
        Iface2Sub1 faceObj;
        ImplA faceObj2;

        faceObj = new ImplBSub();

        result = faceObj.iFunc2(5);
        System.out.print("ImplBSub intf: ");
        System.out.println(result);

        faceObj2 = new ImplA();
        result = faceObj2.iFunc2(5);
        System.out.print("ImplA: ");
        System.out.println(result);
    }
}
