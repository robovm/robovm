/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package libcore.java.lang;

import dalvik.annotation.SideEffect;
import java.util.Vector;
import junit.framework.TestCase;

public class OldObjectTest extends TestCase {

    public boolean isCalled = false;

    /**
     * Test objects.
     */
    Object obj1 = new Object();

    /**
     * Generic state indicator.
     */
    int status = 0;

    int ready = 0;
    TestThread1 thr1;
    TestThread2 thr2;

    public void test_clone() {
        MockCloneableObject mco = new MockCloneableObject();
        try {
            assertFalse(mco.equals(mco.clone()));
            assertEquals(mco.getClass(), mco.clone().getClass());
        } catch(CloneNotSupportedException cnse) {
            fail("CloneNotSupportedException was thrown.");
        }

        MockObject mo = new MockObject();
        try {
            mo.clone();
            fail("CloneNotSupportedException was not thrown.");
        } catch(CloneNotSupportedException cnse) {
            //expected
        }
    }

    class MockCloneableObject extends Object implements Cloneable {
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class MockObject extends Object {

        boolean isCalled = false;

        public void finalize() throws Throwable {
            super.finalize();
            isCalled = true;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public void test_notify() {
        try {
            Object obj = new Object();
            obj.notify();
            fail("IllegalMonitorStateException was not thrown.");
        } catch(IllegalMonitorStateException imse) {
            //expected
        }
    }

    public void test_notifyAll() {
        try {
            Object obj = new Object();
            obj.notifyAll();
            fail("IllegalMonitorStateException was not thrown.");
        } catch(IllegalMonitorStateException imse) {
            //expected
        }
    }

    public void test_wait() {

        try {
            Object obj = new Object();
            obj.wait();
            fail("IllegalMonitorStateException was not thrown.");
        } catch(IllegalMonitorStateException imse) {
            //expected
        } catch(InterruptedException ex) {
            fail("InterruptedException was thrown.");
        }

       try {
           thr1 = new TestThread1(TestThread1.CASE_WAIT);
           thr2 = new TestThread2();
           thr1.start();
           thr2.start();
           thr2.join();
           thr1.join();
           thr1 = null;
           thr2 = null;
        } catch(InterruptedException e) {
            fail("InterruptedException was thrown.");
        }
        assertEquals(3, status);
    }

    class TestThread1 extends Thread {

        static final int CASE_WAIT = 0;
        static final int CASE_WAIT_LONG = 1;
        static final int CASE_WAIT_LONG_INT = 2;

        int testCase = CASE_WAIT;

        public TestThread1(int option) {
            testCase = option;
        }

        public void run() {
            synchronized (obj1) {
                try {
                    switch(testCase) {
                        case CASE_WAIT:
                            obj1.wait();// Wait for ever.
                            break;
                        case CASE_WAIT_LONG:
                            obj1.wait(5000L);
                            break;
                        case CASE_WAIT_LONG_INT:
                            obj1.wait(10000L, 999999);
                            break;
                    }

                } catch (InterruptedException ex) {
                    status = 3;
                }
            }
        }
    }

    class TestThread2 extends Thread {
        public void run() {
            thr1.interrupt();
        }
    }

    public void test_waitJI() {
        try {
            Object obj = new Object();
            obj.wait(5000L, 1);
            fail("IllegalMonitorStateException was not thrown.");
        } catch(IllegalMonitorStateException imse) {
            //expected
        } catch(InterruptedException ex) {
            fail("InterruptedException was thrown.");
        }

       try {
           thr1 = new TestThread1(TestThread1.CASE_WAIT_LONG_INT);
           thr2 = new TestThread2();
           thr1.start();
           thr2.start();
           thr2.join();
           thr1.join();
           thr1 = null;
           thr2 = null;
        } catch(InterruptedException e) {
            fail("InterruptedException was thrown.");
        }
        assertEquals(3, status);

    }

    public void test_waitJ() {
        try {
            Object obj = new Object();
            obj.wait(5000L);
            fail("IllegalMonitorStateException was not thrown.");
        } catch(IllegalMonitorStateException imse) {
            //expected
        } catch(InterruptedException ex) {
            fail("InterruptedException was thrown.");
        }

       try {
           thr1 = new TestThread1(TestThread1.CASE_WAIT_LONG);
           thr2 = new TestThread2();
           thr1.start();
           thr2.start();
           thr2.join();
           thr1.join();
           thr1 = null;
           thr2 = null;
        } catch(InterruptedException e) {
            fail("InterruptedException was thrown.");
        }
        assertEquals(3, status);
    }
}
