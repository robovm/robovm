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

public class ReferenceQueueTest extends junit.framework.TestCase {
    static Boolean b;

    static Integer integer;
    boolean isThrown = false;

    protected void doneSuite() {
        b = null;
        integer = null;
    }

    public class ChildThread implements Runnable {
        public ChildThread() {
        }

        public void run() {
            try {
                rq.wait(1000);
            } catch (Exception e) {
            }
            synchronized (rq) {
                // store in a static so it won't be gc'ed because the jit
                // optimized it out
                integer = new Integer(667);
                SoftReference sr = new SoftReference(integer, rq);
                sr.enqueue();
                rq.notify();
            }
        }
    }

    ReferenceQueue rq;

    /**
     * java.lang.ref.ReferenceQueue#poll()
     */
    public void test_poll() {
        // store in a static so it won't be gc'ed because the jit
        // optimized it out
        b = new Boolean(true);
        Object obj = new Object();
        String str = "Test";

        SoftReference sr = new SoftReference(b, rq);
        WeakReference wr = new WeakReference(obj, rq);
        PhantomReference pr = new PhantomReference(str, rq);
        assertNull(rq.poll());
        sr.enqueue();
        wr.enqueue();
        pr.enqueue();

        try {
            assertNull("Remove failed.", rq.poll().get());
        } catch (Exception e) {
            fail("Exception during the test : " + e.getMessage());
        }

        try {
            assertEquals("Remove failed.", obj, (rq.poll().get()));
        } catch (Exception e) {
            fail("Exception during the test : " + e.getMessage());
        }

        try {
            assertTrue("Remove failed.", ((Boolean) rq.poll().get())
                    .booleanValue());
        } catch (Exception e) {
            fail("Exception during the test : " + e.getMessage());
        }
        assertNull(rq.poll());

        sr.enqueue();
        wr.enqueue();

        System.gc();
        System.runFinalization();

        assertNull(rq.poll());
    }

    /**
     * java.lang.ref.ReferenceQueue#remove()
     */
    public void test_remove() {
        // store in a static so it won't be gc'ed because the jit
        // optimized it out
        b = new Boolean(true);

        SoftReference sr = new SoftReference(b, rq);
        sr.enqueue();
        try {
            assertTrue("Remove failed.", ((Boolean) rq.remove().get())
                    .booleanValue());
        } catch (Exception e) {
            fail("Exception during the test : " + e.getMessage());
        }

        assertNull(rq.poll());

        sr.enqueue();

        class RemoveThread extends Thread {
            public void run() {
                try {
                    rq.remove();
                } catch(InterruptedException ie) {
                    isThrown = true;
                }
            }
        }
        RemoveThread rt = new RemoveThread();
        rt.start();
        try {
            Thread.sleep(100);
        } catch(InterruptedException ie) {

        }
        rt.interrupt();
        try {
            Thread.sleep(100);
        } catch(InterruptedException ie) {

        }
        assertTrue(isThrown);
        assertNull(rq.poll());
    }

    /**
     * java.lang.ref.ReferenceQueue#remove(long)
     */
    public void test_removeJ() {
        try {
            assertNull("Queue should be empty. (poll)", rq.poll());
            assertNull("Queue should be empty. (remove(1))",
                    rq.remove((long) 1));
            Thread ct = new Thread(new ChildThread());
            ct.start();
            Reference ret = rq.remove(0L);
            assertNotNull("Delayed remove failed.", ret);
        } catch (InterruptedException e) {
            fail("InterruptedExeException during test : " + e.getMessage());
        }
        catch (Exception e) {
            fail("Exception during test : " + e.getMessage());
        }

        Object obj = new Object();
        WeakReference wr = new WeakReference(obj, rq);
        Boolean b = new Boolean(true);
        SoftReference sr = new SoftReference(b, rq);
        String str = "Test";
        PhantomReference pr = new PhantomReference(str, rq);

        pr.enqueue();
        wr.enqueue();
        sr.enqueue();

        try {
            Reference result = rq.remove(1L);
            assertTrue((Boolean)result.get());
            result = rq.remove(1L);
            assertEquals(obj, result.get());
            result = rq.remove(1L);
            assertNull(result.get());
        } catch (IllegalArgumentException e1) {
            fail("IllegalArgumentException was thrown.");
        } catch (InterruptedException e1) {
            fail("InterruptedException was thrown.");
        }
        rq = new ReferenceQueue();
        isThrown = false;
        assertNull(rq.poll());

        class RemoveThread extends Thread {
            public void run() {
                try {
                    rq.remove(1000L);
                } catch(InterruptedException ie) {
                    isThrown = true;
                }
            }
        }
        RemoveThread rt = new RemoveThread();
        rt.start();
        try {
            Thread.sleep(10);
        } catch(InterruptedException ie) {

        }
        rt.interrupt();
        try {
            Thread.sleep(10);
        } catch(InterruptedException ie) {

        }
        assertTrue(isThrown);
        assertNull(rq.poll());

        try {
            rq.remove(-1);
            fail("IllegalArgumentException expected.");
        } catch(IllegalArgumentException iae) {
            //expected
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException.");
        }
    }

    /**
     * java.lang.ref.ReferenceQueue#ReferenceQueue()
     */
    public void test_Constructor() {
        ReferenceQueue rq = new ReferenceQueue();
        assertNull(rq.poll());
        try {
            rq.remove(100L);
        } catch (InterruptedException e) {
            fail("InterruptedException was thrown.");
        }
    }

    protected void setUp() {
        rq = new ReferenceQueue();
    }

    protected void tearDown() {
    }
}
