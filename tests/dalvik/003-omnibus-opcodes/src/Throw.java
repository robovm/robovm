/*
 * Copyright (C) 2008 The Android Open Source Project
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
 * Test exception throwing.
 */
public class Throw {
    public void throwNullPointerException() {
        throw new NullPointerException("npe!");
    }

    public void throwArithmeticException() {
        throw new ArithmeticException();
    }

    public void one() {
        System.out.println("Throw.one");
        try {
            throwNullPointerException();
            assert(false);
        } catch (Exception ex) {
            // good
            return;
        }

        assert(false);
    }

    public void twoA() {
        System.out.println("Throw.twoA");
        boolean gotN = false;
        boolean gotA = false;
        boolean gotWeird = false;

        try {
            try {
                throwArithmeticException();
                gotWeird = true;
            } catch (ArithmeticException ae) {
                gotA = true;
            }
        } catch (NullPointerException npe) {
            gotN = true;
        }

        assert(gotA);
        assert(!gotN);
        assert(!gotWeird);
    }

    public void twoN() {
        System.out.println("Throw.twoN");
        boolean gotN = false;
        boolean gotA = false;
        boolean gotWeird = false;

        try {
            try {
                throwNullPointerException();
                gotWeird = true;
            } catch (ArithmeticException ae) {
                gotA = true;
            }
        } catch (NullPointerException npe) {
            gotN = true;
        }

        assert(!gotA);
        assert(gotN);
        assert(!gotWeird);
    }

    public void rethrow() {
        System.out.println("Throw.rethrow");
        boolean caught = false;
        boolean lly = false;
        boolean second = false;

        try {
            try {
                throwNullPointerException();
                assert(false);
            } catch (Exception ex) {
                if (ex instanceof ArithmeticException) {
                    assert(false);
                }
                if (ex instanceof NullPointerException) {
                    caught = true;
                    throw (NullPointerException) ex;
                }
            } finally {
                lly = true;
            }
        } catch (Exception ex) {
            second = true;
        }

        assert(caught);
        assert(lly);
        assert(second);
    }

    public static void run() {
        Throw th = new Throw();

        th.one();
        th.twoA();
        th.twoN();
        th.rethrow();
    }
}
