/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.rt.bro;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.dispatch.DispatchQueue;
import org.robovm.rt.bro.annotation.Callback;

/**
 * Tests that attaching of native threads in {@link Callback}s are only done
 * once even if the {@link Callback} is called multiple times from native code.
 */
public class CallbackAttachTest {

    @Before
    public void setUp() {
        // Only run this test on OSX/iOS since it relies on GCD
        String os = System.getProperty("os.name").toLowerCase();
        Assume.assumeTrue(os.contains("mac") || os.contains("ios"));
    }
    
    @Test
    public void testAttachesOnlyOnce() throws Exception {
        final Set<Thread> threads = Collections.synchronizedSet(new HashSet<Thread>());
        final CountDownLatch latch = new CountDownLatch(20);
        DispatchQueue q1 = DispatchQueue.create("q1", null);
        DispatchQueue q2 = DispatchQueue.create("q2", null);
        for (int i = 0; i < 20; i++) {
            ((i & 1) == 0 ? q1 : q2).async(new Runnable() {
                public void run() {
                    threads.add(Thread.currentThread());
                    latch.countDown();
                    try {
                        // Sleep some to prevent dispatch from using the same
                        // worker thread for both queues
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        latch.await();
        
        assertEquals(2, threads.size());
        List<Thread> l = new ArrayList<>(threads);
        Thread t1 = l.get(0);
        Thread t2 = l.get(1);
        assertTrue(t1.isDaemon());
        assertTrue(t2.isDaemon());
        assertTrue(t1.isAlive());
        assertTrue(t2.isAlive());
        assertEquals(0, t1.getStackTrace().length);
        assertEquals(0, t2.getStackTrace().length);

        q1.release();
        q2.release();
        
        t1.join(15000);
        t2.join(15000);

        assertFalse(t1.isAlive());
        assertFalse(t2.isAlive());
    }

}
