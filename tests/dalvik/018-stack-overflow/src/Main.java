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
 * generate a stack overflow condition and catch it
 */
public class Main {
    public static void main(String args[]) {
        try {
            stackOverflowTestSub(0.0, 0.0, 0.0);
        }
        catch (StackOverflowError soe) {
            System.out.println("caught SOE");
        }
        System.out.println("SOE test done");
    }

    private static void stackOverflowTestSub(double pad1, double pad2,
            double pad3) {
        stackOverflowTestSub(pad1, pad2, pad3);
    }
}
