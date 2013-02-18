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

/**
 * Test access to private boolean fields.
 *
 * Accessing private boolean fields from an inner class causes the compiler
 * to generate an accessor method that performs the boolean operation.
 * Unfortunately the generated method takes an integer as an argument,
 * not a boolean, which makes the verifier upset when the result of the
 * operation is written back to a boolean field.
 */
public class Main {
    private boolean mInstance;
    private static boolean mStatic;

    public static void main(String[] args) {
        Main foo = new Main();
        foo.test();

        System.out.println("Done");
    }

    void test() {
        Innard innard = new Innard();
        innard.doStuff();
    }

    class Innard {
        void doStuff() {
            mInstance |= true;
            mStatic |= true;
        }
    }
}
