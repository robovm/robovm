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
 * Exceptions across method calls
 */
public class Main {
    public static void main (String args[]) {
        try {
            catchAndRethrow();
        } catch (NullPointerException npe) {
            System.out.print("Got an NPE: ");
            System.out.println(npe.getMessage());
            npe.printStackTrace();
        }
    }

    private static void catchAndRethrow() {
        try {
            throwNullPointerException();
        } catch (NullPointerException npe) {
            NullPointerException npe2;
            npe2 = new NullPointerException("second throw");
            npe2.initCause(npe);
            throw npe2;
        }
    }

    private static void throwNullPointerException() {
        throw new NullPointerException("first throw");
    }
}
