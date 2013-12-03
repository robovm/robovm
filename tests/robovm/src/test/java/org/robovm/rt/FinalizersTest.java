/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.rt;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * Tests that finalizers are run properly by the GC.
 */
public class FinalizersTest {
    private static final long GB = 1024 * 1024 * 1024;

    public static class TestObject {
        private final byte[] data;
        private final AtomicLong dealloced;
        public TestObject(int size, AtomicLong dealloced) {
            this.data = new byte[size];
            this.dealloced = dealloced;
        }
//        protected void finalize() throws Throwable {
//            dealloced.addAndGet(data.length);
//        }
    }
    
//    @Test
    public void testManyAllocations() throws Throwable {
        long start = System.currentTimeMillis();
        // Allocates 16 GB. We expect that at least 12 GB of those will be finalized.
        AtomicLong dealloced = new AtomicLong();
        int size = 4 * 1024;
        long count = 16L * GB;
        for (long i = 0; i < count; i += size) {
            new TestObject(size, dealloced);
        }
        System.out.println((double) dealloced.get() / GB);
//        assertTrue(dealloced.get() > 12L * GB);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

}
