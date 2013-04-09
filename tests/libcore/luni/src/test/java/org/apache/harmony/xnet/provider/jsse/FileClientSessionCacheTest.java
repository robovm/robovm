/*
 * Copyright (C) 2009 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import libcore.javax.net.ssl.FakeSSLSession;

public class FileClientSessionCacheTest extends TestCase {

    public void testMaxSize() throws IOException, InterruptedException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        if (tmpDir == null) {
            fail("Please set 'java.io.tmpdir' system property.");
        }
        File cacheDir = new File(tmpDir
                + "/" + FileClientSessionCacheTest.class.getName() + "/cache");
        final SSLClientSessionCache cache
                = FileClientSessionCache.usingDirectory(cacheDir);
        Thread[] threads = new Thread[10];
        final int iterations = FileClientSessionCache.MAX_SIZE * 10;
        for (int i = 0; i < threads.length; i++) {
            final int id = i;
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < iterations; i++) {
                        cache.putSessionData(new FakeSSLSession(id + "" + i), new byte[10]);
                    }
                }
            };
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        assertEquals(FileClientSessionCache.MAX_SIZE, cacheDir.list().length);
    }
}
