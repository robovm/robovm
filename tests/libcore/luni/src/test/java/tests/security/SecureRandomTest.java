/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import junit.framework.TestCase;

public abstract class SecureRandomTest extends TestCase {

    private final String algorithmName;

    private int counter = 0;

    protected SecureRandomTest(String name) {
        this.algorithmName = name;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testSecureRandom() {
        SecureRandom secureRandom1 = null;
        try {
            secureRandom1 = SecureRandom.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

        SecureRandom secureRandom2 = null;
        try {
            secureRandom2 = SecureRandom.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

        byte[] testRandom1 = getRandomBytes(secureRandom1);
        byte[] testRandom2 = getRandomBytes(secureRandom2);

        assertFalse(Arrays.equals(testRandom1, testRandom2));


    }

    private byte[] getRandomBytes(SecureRandom random) {
        byte[] randomData = new byte[64];

        random.setSeed(System.currentTimeMillis()+counter);
        counter++;

        random.nextBytes(randomData);

        return randomData;
    }


    public void testSecureRandomThreadSafety() throws Exception {
        final SecureRandom secureRandom = SecureRandom.getInstance(algorithmName);
        int threads = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        ExecutorCompletionService ecs = new ExecutorCompletionService(executor);
        for (int t = 0; t < threads; t++) {
            ecs.submit(new Callable<Void>() {
                public Void call () {
                    for (int i = 0; i < 1000; i++) {
                        secureRandom.generateSeed(1024);
                    }
                    return null;
                }
            });
        }
        executor.shutdown();
        for (int i = 0; i < threads; i++) {
            ecs.take().get();
        }
    }
}
