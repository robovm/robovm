/*
 * Copyright (C) 2010 The Android Open Source Project
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


/*
 * Entry point and tests that are expected to succeed.
 */
public class Main {
    /**
     * Drives tests.
     */
    public static void main(String[] args) {
        Main m = new Main();

        m.recursiveSync(0);

        m.nestedMayThrow(false);
        try {
            m.nestedMayThrow(true);
            System.err.println("nestedThrow(true) did not throw");
        } catch (MyException me) {}
        System.out.println("nestedMayThrow ok");

        m.constantLock();
        System.out.println("constantLock ok");

        m.notExcessiveNesting();
        if (false) {    // TODO: remove when verification is turned on
        try {
            TooDeep.excessiveNesting();
            System.err.println("excessiveNesting did not throw");
        } catch (VerifyError ve) {}
        }
        System.out.println("excessiveNesting ok");

        m.notNested();
        System.out.println("notNested ok");

        Object obj1 = new Object();
        Object obj2 = new Object();

        m.twoPath(obj1, obj2, 0);
        System.out.println("twoPath ok");

        m.triplet(obj1, obj2, 0);
        System.out.println("triplet ok");
    }

    /**
     * Recursive synchronized method.
     */
    synchronized void recursiveSync(int iter) {
        if (iter < 40) {
            recursiveSync(iter+1);
        } else {
            System.out.println("recursiveSync ok");
        }
    }

    /**
     * Tests simple nesting, with and without a throw.
     */
    void nestedMayThrow(boolean doThrow) {
        synchronized (this) {
            synchronized (Main.class) {
                synchronized (new Object()) {
                    synchronized(Class.class) {
                        if (doThrow) {
                            throw new MyException();
                        }
                    }
                }
            }
        }
    }

    /**
     * Exercises bug 3215458.
     */
    void constantLock() {
        Class thing = Thread.class;
        synchronized (Thread.class) {}
    }

    /**
     * Confirms that we can have 32 nested monitors on one method.
     */
    void notExcessiveNesting() {
        synchronized (this) {   // 1
        synchronized (this) {   // 2
        synchronized (this) {   // 3
        synchronized (this) {   // 4
        synchronized (this) {   // 5
        synchronized (this) {   // 6
        synchronized (this) {   // 7
        synchronized (this) {   // 8
        synchronized (this) {   // 9
        synchronized (this) {   // 10
        synchronized (this) {   // 11
        synchronized (this) {   // 12
        synchronized (this) {   // 13
        synchronized (this) {   // 14
        synchronized (this) {   // 15
        synchronized (this) {   // 16
        synchronized (this) {   // 17
        synchronized (this) {   // 18
        synchronized (this) {   // 19
        synchronized (this) {   // 20
        synchronized (this) {   // 21
        synchronized (this) {   // 22
        synchronized (this) {   // 23
        synchronized (this) {   // 24
        synchronized (this) {   // 25
        synchronized (this) {   // 26
        synchronized (this) {   // 27
        synchronized (this) {   // 28
        synchronized (this) {   // 29
        synchronized (this) {   // 30
        synchronized (this) {   // 31
        synchronized (this) {   // 32
        }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
    }

    /**
     * Confirms that we can have more than 32 non-nested monitors in one
     * method.
     */
    void notNested() {
        synchronized (this) {}  // 1
        synchronized (this) {}  // 2
        synchronized (this) {}  // 3
        synchronized (this) {}  // 4
        synchronized (this) {}  // 5
        synchronized (this) {}  // 6
        synchronized (this) {}  // 7
        synchronized (this) {}  // 8
        synchronized (this) {}  // 9
        synchronized (this) {}  // 10
        synchronized (this) {}  // 11
        synchronized (this) {}  // 12
        synchronized (this) {}  // 13
        synchronized (this) {}  // 14
        synchronized (this) {}  // 15
        synchronized (this) {}  // 16
        synchronized (this) {}  // 17
        synchronized (this) {}  // 18
        synchronized (this) {}  // 19
        synchronized (this) {}  // 20
        synchronized (this) {}  // 21
        synchronized (this) {}  // 22
        synchronized (this) {}  // 23
        synchronized (this) {}  // 24
        synchronized (this) {}  // 25
        synchronized (this) {}  // 26
        synchronized (this) {}  // 27
        synchronized (this) {}  // 28
        synchronized (this) {}  // 29
        synchronized (this) {}  // 30
        synchronized (this) {}  // 31
        synchronized (this) {}  // 32
        synchronized (this) {}  // 33
        synchronized (this) {}  // 34
    }

    /* does nothing but ensure that the compiler doesn't discard an object */
    private void doNothing(Object obj) {}

    /**
     * Conditionally uses one of the synchronized objects.
     */
    public void twoPath(Object obj1, Object obj2, int x) {
        Object localObj;

        synchronized (obj1) {
            synchronized(obj2) {
                if (x == 0) {
                    localObj = obj2;
                } else {
                    localObj = obj1;
                }
            }
        }

        doNothing(localObj);
    }

    /**
     * Lock the monitor two or three times, and make use of the locked or
     * unlocked object.
     */
    public void triplet(Object obj1, Object obj2, int x) {
        Object localObj;

        synchronized (obj1) {
            synchronized(obj1) {
                if (x == 0) {
                    synchronized(obj1) {
                        localObj = obj2;
                    }
                } else {
                    localObj = obj1;
                }
            }
        }

        doNothing(localObj);
    }
}
