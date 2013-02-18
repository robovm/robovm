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
        Derived1 derived1 = new Derived1();
        Derived2 derived2 = new Derived2();
        Derived3 derived3 = new Derived3();

        derived1.start();
        derived2.start();
        derived3.start();

        try {
            derived1.join();
            derived2.join();
            derived3.join();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println(derived1.getValue());
        System.out.println(derived2.getValue());
        System.out.println(derived3.getValue());
    }
}
