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
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import junit.framework.AssertionFailedError;

public class ReferenceTest extends junit.framework.TestCase {
    Object tmpA, tmpB, tmpC, obj;

    volatile Reference r;

    /*
     * For test_subclass().
     */
    static TestWeakReference twr;
    static AssertionFailedError error;
    static boolean testObjectFinalized;
    static class TestWeakReference<T> extends WeakReference<T> {
        public volatile boolean clearSeen = false;
        public volatile boolean enqueueSeen = false;

        public TestWeakReference(T referent) {
            super(referent);
        }

        public TestWeakReference(T referent, ReferenceQueue<? super T> q) {
            super(referent, q);
        }

        public void clear() {
            clearSeen = true;
            if (testObjectFinalized) {
                error = new AssertionFailedError("Clear should happen " +
                        "before finalization.");
                throw error;
            }
            if (enqueueSeen) {
                error = new AssertionFailedError("Clear should happen " +
                        "before enqueue.");
                throw error;
            }
            super.clear();
        }

        public boolean enqueue() {
            enqueueSeen = true;
            if (!clearSeen) {
                error = new AssertionFailedError("Clear should happen " +
                        "before enqueue.");
                throw error;
            }

            /* Do this last;  it may notify the main test thread,
             * and anything we'd do after it (e.g., setting clearSeen)
             * wouldn't be seen.
             */
            return super.enqueue();
        }
    }

    protected void doneSuite() {
        tmpA = tmpB = obj = null;
    }

    /**
     * java.lang.ref.Reference#clear()
     */
    public void test_clear() {
        tmpA = new Object();
        tmpB = new Object();
        tmpC = new Object();
        SoftReference sr = new SoftReference(tmpA, new ReferenceQueue());
        WeakReference wr = new WeakReference(tmpB, new ReferenceQueue());
        PhantomReference pr = new PhantomReference(tmpC, new ReferenceQueue());
        assertTrue("Start: Object not cleared.", (sr.get() != null)
                && (wr.get() != null));
        assertNull("Referent is not null.", pr.get());
        sr.clear();
        wr.clear();
        pr.clear();
        assertTrue("End: Object cleared.", (sr.get() == null)
                && (wr.get() == null));
        assertNull("Referent is not null.", pr.get());
        // Must reference tmpA and tmpB so the jit does not optimize them away
        assertTrue("should always pass", tmpA != sr.get() && tmpB != wr.get());
    }

    /**
     * java.lang.ref.Reference#enqueue()
     */
    public void test_enqueue() {
        ReferenceQueue rq = new ReferenceQueue();
        obj = new Object();
        Reference ref = new SoftReference(obj, rq);
        assertTrue("Enqueue failed.", (!ref.isEnqueued())
                && ((ref.enqueue()) && (ref.isEnqueued())));
        assertTrue("Not properly enqueued.", rq.poll().get() == obj);
        // This fails...
        assertTrue("Should remain enqueued.", !ref.isEnqueued());
        assertTrue("Can not enqueue twice.", (!ref.enqueue())
                && (rq.poll() == null));

        rq = new ReferenceQueue();
        obj = new Object();

        ref = new WeakReference(obj, rq);
        assertTrue("Enqueue failed2.", (!ref.isEnqueued())
                && ((ref.enqueue()) && (ref.isEnqueued())));
        assertTrue("Not properly enqueued2.", rq.poll().get() == obj);
        assertTrue("Should remain enqueued2.", !ref.isEnqueued()); // This
        // fails.
        assertTrue("Can not enqueue twice2.", (!ref.enqueue())
                && (rq.poll() == null));

        ref = new PhantomReference(obj, rq);
        assertTrue("Enqueue failed3.", (!ref.isEnqueued())
                && ((ref.enqueue()) && (ref.isEnqueued())));
        assertNull("Not properly enqueued3.", rq.poll().get());
        assertTrue("Should remain enqueued3.", !ref.isEnqueued()); // This
        // fails.
        assertTrue("Can not enqueue twice3.", (!ref.enqueue())
                && (rq.poll() == null));
    }

    public void test_get_WeakReference() throws Exception {
        // Test the general/overall functionality of Reference.
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

        r = newWeakReference(queue);
        System.gc();
        System.runFinalization();
        Reference ref = queue.remove(10 * 1000); // RoboVM note: Don't wait indefinitely.
        assertNotNull("Object not enqueued.", ref);
        assertSame("Unexpected ref1", ref, r);
        assertNull("Object could not be reclaimed1.", r.get());

        r = newWeakReference(queue);
        System.gc();
        System.runFinalization();

        // wait for the reference queue thread to enqueue the newly-finalized object
        Thread.yield();
        Thread.sleep(200);

        ref = queue.poll();
        assertNotNull("Object not enqueued.", ref);
        assertSame("Unexpected ref2", ref, r);
        assertNull("Object could not be reclaimed.", ref.get());
        assertNull("Object could not be reclaimed.", r.get());
    }

    /**
     * Makes sure that overridden versions of clear() and enqueue()
     * get called, and that clear/enqueue/finalize happen in the
     * right order for WeakReferences.
     *
     * java.lang.ref.Reference#clear()
     * java.lang.ref.Reference#enqueue()
     * java.lang.Object#finalize()
     */
    public void test_subclass() {
        error = null;
        testObjectFinalized = false;
        twr = null;

        class TestObject {
            public TestWeakReference testWeakReference = null;

            public void setTestWeakReference(TestWeakReference twr) {
                testWeakReference = twr;
            }

            protected void finalize() {
                testObjectFinalized = true;
            }
        }

        final ReferenceQueue rq = new ReferenceQueue();

        class TestThread extends Thread {
            public void run() {
                // Create the object in a separate thread to ensure it will be
                // gc'ed
                TestObject testObj = new TestObject();
                twr = new TestWeakReference(testObj, rq);
                testObj.setTestWeakReference(twr);
                testObj = null;
            }
        }

        Reference ref;

        try {
            Thread t = new TestThread();
            t.start();
            t.join();
            System.gc();
            System.runFinalization();
            ref = rq.remove(5000L);    // Give up after five seconds.

            assertNotNull("Object not garbage collected.", ref);
            assertTrue("Unexpected reference.", ref == twr);
            assertNull("Object could not be reclaimed.", twr.get());
            //assertTrue("Overridden clear() should have been called.",
            //       twr.clearSeen);
            //assertTrue("Overridden enqueue() should have been called.",
            //        twr.enqueueSeen);
            assertTrue("finalize() should have been called.",
                    testObjectFinalized);
        } catch (InterruptedException e) {
            fail("InterruptedException : " + e.getMessage());
        }

    }

    /**
     * java.lang.ref.Reference#get()
     */
    public void test_get() {
        WeakReference ref = newWeakReference(null);

        System.gc();
        System.runFinalization();
        assertNull("get() doesn't return null after gc for WeakReference", ref.get());

        obj = new Object();
        ref = new WeakReference<Object>(obj, new ReferenceQueue<Object>());
        ref.clear();
        assertNull("get() doesn't return null after clear for WeakReference", ref.get());
    }

    /**
     * Helper method to prevent live-precise bugs from interfering with analysis
     * of what is reachable. Do not inline this method; otherwise tests may fail
     * on VMs that are not live-precise. http://b/4191345
     */
    private WeakReference<Object> newWeakReference(ReferenceQueue<Object> queue) {
        Object o = new Object();
        WeakReference<Object> ref = new WeakReference<Object>(o, queue);
        assertSame(o, ref.get());
        return ref;
    }

    /**
     * java.lang.ref.Reference#isEnqueued()
     */
    public void test_isEnqueued() {
        ReferenceQueue rq = new ReferenceQueue();
        obj = new Object();
        Reference ref = new SoftReference(obj, rq);
        assertTrue("Should start off not enqueued.", !ref.isEnqueued());
        ref.enqueue();
        assertTrue("Should now be enqueued.", ref.isEnqueued());
        ref.enqueue();
        assertTrue("Should still be enqueued.", ref.isEnqueued());
        rq.poll();
        // This fails ...
        assertTrue("Should now be not enqueued.", !ref.isEnqueued());
    }

    /* Contrives a situation where the only reference to a string
     * is a WeakReference from an object that is being finalized.
     * Checks to make sure that the referent of the WeakReference
     * is still pointing to a valid object.
     */
    public void test_finalizeReferenceInteraction() {
        error = null;
        testObjectFinalized = false;

        class TestObject {
            WeakReference<String> stringRef;

            public TestObject(String referent) {
                stringRef = new WeakReference<String>(referent);
            }

            protected void finalize() {
                try {
                    /* If a VM bug has caused the referent to get
                     * freed without the reference getting cleared,
                     * looking it up, assigning it to a local and
                     * doing a GC should cause some sort of exception.
                     */
                    String s = stringRef.get();
                    System.gc();
                    testObjectFinalized = true;
                } catch (Throwable t) {
                    error = new AssertionFailedError("something threw '" + t +
                            "' in finalize()");
                }
            }
        }

        class TestThread extends Thread {
            public void run() {
                // Create the object in a separate thread to ensure it will be
                // gc'ed
                TestObject testObj = new TestObject(new String("sup /b/"));
            }
        }

        try {
            Thread t = new TestThread();
            t.start();
            t.join();
            System.gc();
            System.runFinalization();
            Thread.sleep(1000);
            if (error != null) {
                throw error;
            }
            assertTrue("finalize() should have been called.",
                    testObjectFinalized);
        } catch (InterruptedException e) {
            fail("InterruptedException : " + e.getMessage());
        }
    }


    protected void setUp() {
    }

    protected void tearDown() {
    }
}
