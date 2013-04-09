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

package tests.api.java.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

//TODO: write a test to verify that the referent's finalize() happens
//      before the PhantomReference is enqueued.

public class PhantomReferenceTest extends junit.framework.TestCase {
    static Boolean bool;
    public boolean isCalled = false;
    protected void doneSuite() {
        bool = null;
    }

    /**
     * java.lang.ref.PhantomReference#get()
     */
    public void test_get() {
        ReferenceQueue rq = new ReferenceQueue();
        bool = new Boolean(false);
        PhantomReference pr = new PhantomReference(bool, rq);
        assertNull("get() should return null.", pr.get());
        pr.enqueue();
        assertNull("get() should return null.", pr.get());
        pr.clear();
        assertNull("get() should return null.", pr.get());
    }

    /**
     * java.lang.Runtime#gc()
     */
    public void test_gcInteraction() {
        class TestPhantomReference<T> extends PhantomReference<T> {
            public TestPhantomReference(T referent,
                    ReferenceQueue<? super T> q) {
                super(referent, q);
            }
            public boolean enqueue() {
                // Initiate another GC from inside enqueue() to
                // see if it causes any problems inside the VM.
                Runtime.getRuntime().gc();
                return super.enqueue();
            }
        }

        final ReferenceQueue rq = new ReferenceQueue();
        final PhantomReference[] tprs = new PhantomReference[4];

        class TestThread extends Thread {
            public void run() {
                // Create the object in a separate thread to ensure
                // it will be gc'ed.
                Object obj = new Object();
                tprs[0] = new TestPhantomReference(obj, rq);
                tprs[1] = new TestPhantomReference(obj, rq);
                tprs[2] = new TestPhantomReference(obj, rq);
                tprs[3] = new TestPhantomReference(obj, rq);
            }
        }

        try {
            Thread t = new TestThread();
            t.start();
            t.join();
            System.gc();
            System.runFinalization();

            assertNull("get() should return null.", tprs[0].get());
            assertNull("get() should return null.", tprs[1].get());
            assertNull("get() should return null.", tprs[2].get());
            assertNull("get() should return null.", tprs[3].get());

            for (int i = 0; i < 4; i++) {
                Reference r = rq.remove(100L);
                assertNotNull("Reference should have been enqueued.", r);
            }

            // These are to make sure that tprs and its elements don't get
            // optimized out.
            assertNull("get() should return null.", tprs[0].get());
            assertNull("get() should return null.", tprs[1].get());
            assertNull("get() should return null.", tprs[2].get());
            assertNull("get() should return null.", tprs[3].get());
        } catch (InterruptedException e) {
            fail("InterruptedException : " + e.getMessage());
        }
    }

    /**
     * java.lang.ref.PhantomReference#PhantomReference(java.lang.Object,
     *        java.lang.ref.ReferenceQueue)
     */
    public void test_ConstructorLjava_lang_ObjectLjava_lang_ref_ReferenceQueue() {
        ReferenceQueue rq = new ReferenceQueue();
        bool = new Boolean(true);
        try {
            PhantomReference pr = new PhantomReference(bool, rq);
            // Allow the finalizer to run to potentially enqueue
            Thread.sleep(1000);
            assertTrue("Initialization failed.", !pr.isEnqueued());
        } catch (Exception e) {
            fail("Exception during test : " + e.getMessage());
        }
        // need a reference to bool so the jit does not optimize it away
        assertTrue("should always pass", bool.booleanValue());

        boolean exception = false;
        try {
            new PhantomReference(bool, null);
        } catch (NullPointerException e) {
            exception = true;
        }
        assertTrue("Should not throw NullPointerException", !exception);
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }
}
