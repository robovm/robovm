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
 * Stuff the wrong type object into an array.
 */
public class Main {
    public static void main(String args[]) {
        String[] strArray = new String[1];

        Object[] objArray = strArray;

        try {
            objArray[0] = new Integer(1);
            System.out.println("Array store succeeded?!");
        } catch (ArrayStoreException ase) {
            System.out.println("Got correct array store exception");
        }
    }
}
