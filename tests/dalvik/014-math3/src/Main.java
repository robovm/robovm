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
 * Test math exceptions
 */
public class Main {
    public static void main(String args[]) {
        int expectedThrows = 2;
        int i;
        long j;
        float f = 0.0f;
        double d = 0.0;

        try { i = 10 / 0; }
        catch (ArithmeticException ae) {
            expectedThrows--;
        }

        try { j = 10L / 0L; }
        catch (ArithmeticException ae) {
            expectedThrows--;
        }

        /*
         * Floating point divide by zero doesn't throw an exception -- the
         * result is just NaN.
         */
        try { f = 10.0f / f; }
        catch (ArithmeticException ae) {
            expectedThrows--;
        }

        try { d = 10.0 / d; }
        catch (ArithmeticException ae) {
            expectedThrows--;
        }

        if (expectedThrows != 0)
            System.out.println("HEY: expected throws is " + expectedThrows);
        else
            System.out.println("testMath3 success");
    }
}
