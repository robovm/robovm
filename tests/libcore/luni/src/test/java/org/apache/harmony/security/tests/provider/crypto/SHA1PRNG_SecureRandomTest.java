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

package org.apache.harmony.security.tests.provider.crypto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import junit.framework.TestCase;

/**
 * Tests against methods in SecureRandom class object using
 * SHA1PRNG_SecureRandomImpl.
 */
public class SHA1PRNG_SecureRandomTest extends TestCase {

    private static final int LENGTH = 20; // constant defining loop limit

    private static final int INCR = 2; // constant defining loop increment

    private static final String algorithm = "SHA1PRNG"; // algorithm's name

    private static final String provider = "Crypto"; // provider's name

    private static SecureRandom sr; // fields used by tests

    private static SecureRandom sr2; //

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        sr = SecureRandom.getInstance(algorithm, provider);
        sr2 = SecureRandom.getInstance(algorithm, provider);
    }

    /**
     * test against the "void generateSeed(int)" method; it checks out that the
     * method throws NegativeArraySizeException if argument <0
     */
    public final void testGenerateSeedint01() {
        try {
            sr.generateSeed(-1);
            fail("generateSeed(-1) :: No NegativeArraySizeException");
        } catch (NegativeArraySizeException e) {
        }
    }

    /**
     * test against the "void generateSeed(int)" method; it checks out that
     * number of bits returned is equal to one requested; the check includes
     * case for argument's value == 0;
     */
    public final void testGenerateSeedint02() {
        for (int i = 0; i < LENGTH; i++) {
            byte[] myBytes = sr.generateSeed(i);
            assertFalse("unexpected: myBytes.length != i  :: i==" + i + " myBytes.length="
                    + myBytes.length, myBytes.length != i);
        }
    }

    /**
     * test against the "void generateSeed(int)" method; it checks out the
     * quality of entropy (# of different bytes in sequential calls is more or
     * equal to 50%)
     */
    public final void testGenerateSeedint03() {
        byte[] myBytes1;
        byte[] myBytes2;

        for (int i = 0; i < LENGTH; i += INCR) {
            int n = 0;
            myBytes1 = sr.generateSeed(i);
            myBytes2 = sr.generateSeed(i);

            for (int j = 0; j < i; j++) {
                if (myBytes1[j] == myBytes2[j]) {
                    n++;
                }
            }
            assertFalse("unexpected: n*2 > i  :: i=" + i + " n=" + n, n * 2 > i);
        }
    }

    /**
     * test against the "void nextBytes(byte[])" method; it checks out that the
     * method throws NPE if argument supplied is null
     */
    public final void testNextBytesbyteArray01() {
        try {
            sr.nextBytes(null);
            fail("unexpected: nextBytes(null) :: No NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    /**
     * test against the "void nextBytes(byte[])" method; it checks out that
     * different SecureRandom objects being supplied with the same seed return
     * the same sequencies of bytes as results of their "nextBytes(byte[])"
     * methods
     */
    public final void testNextBytesbyteArray02() {
        byte[] myBytes;
        byte[] myBytes1;
        byte[] myBytes2;

        // case1: sequencies are of the same length
        for (int i = 1; i < LENGTH; i += INCR) {
            myBytes = new byte[i];

            for (int j = 1; j < i; j++) {
                myBytes[j] = (byte) (j & 0xFF);
            }
            sr.setSeed(myBytes);
            sr2.setSeed(myBytes);

            for (int k = 1; k < LENGTH; k += INCR) {
                myBytes1 = new byte[k];
                myBytes2 = new byte[k];
                sr.nextBytes(myBytes1);
                sr2.nextBytes(myBytes2);

                for (int l = 0; l < k; l++) {
                    assertFalse("unexpected: myBytes1[l] != myBytes2[l]  :: l==" + l + " k=" + k
                            + " i=" + i + " myBytes1[l]=" + myBytes1[l] + " myBytes2[l]="
                            + myBytes2[l], myBytes1[l] != myBytes2[l]);
                }
            }
        }

        // case2: sequencies are of different lengths
        for (int n = 1; n < LENGTH; n += INCR) {
            int n1 = 10;
            int n2 = 20;
            int n3 = 100;
            byte[][] bytes1 = new byte[10][n1];
            byte[][] bytes2 = new byte[5][n2];

            for (int k = 0; k < bytes1.length; k++) {
                sr.nextBytes(bytes1[k]);
            }
            for (int k = 0; k < bytes2.length; k++) {
                sr2.nextBytes(bytes2[k]);
            }

            for (int k = 0; k < n3; k++) {
                int i1 = k / n1;
                int i2 = k % n1;
                int i3 = k / n2;
                int i4 = k % n2;
                assertTrue("non-equality: i1=" + i1 + " i2=" + i2 + " i3=" + i3 + " i4=" + i4,
                        bytes1[i1][i2] == bytes2[i3][i4]);
            }
        }
    }

    /**
     * test against the "void nextBytes(byte[])" method; it checks out that
     * different SecureRandom objects being supplied with seed by themselves
     * return different sequencies of bytes as results of their
     * "nextBytes(byte[])" methods
     */
    public final void testNextBytesbyteArray03() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        /* these are needed to test new SecureRandom objects in loop */
        SecureRandom sr1;
        SecureRandom sr2;

        byte[] myBytes1;
        byte[] myBytes2;

        for (int i = 1; i < LENGTH / 2; i += INCR) {
            sr1 = SecureRandom.getInstance(algorithm, provider);
            sr2 = SecureRandom.getInstance(algorithm, provider);

            boolean flag = true;

            myBytes1 = new byte[i];
            myBytes2 = new byte[i];

            sr1.nextBytes(myBytes1);
            sr2.nextBytes(myBytes2);
            for (int j = 0; j < i; j++) {
                flag &= myBytes1[j] == myBytes2[j];
            }

            // check again to avoid intermittent failures
            sr1.nextBytes(myBytes1);
            sr2.nextBytes(myBytes2);
            for (int j = 0; j < i; j++) {
                flag &= myBytes1[j] == myBytes2[j];
            }

            if (flag) {
                // probability of false failure is 1.5*10^-5 per run for i=1 or
                // less for i > 1
                fail("TESTING RANDOM NUMBER GENERATOR QUALITY: IGNORE THIS FAILURE IF INTERMITTENT :: i="
                        + i);
            }
        }
    }

    /**
     * test against the "void nextBytes(byte[])" method; it checks out behavior
     * of SecureRandom object in cases of passing byte array of zero length to
     * "nextBytes(byte[])" method. The test contains two testcases: - first
     * testcase checks out that if for two newly created SecureRandom objects
     * invocation of "nextBytes(new byte[0])" method are first ones then further
     * calls to nextBytes(..) methods return different byte arrays, that is,
     * first "nextBytes(new byte[0])" aslo randomizes internal state; - second
     * testcase checks out that if for two newly created SecureRandom objects
     * invocation of "setSeed(..)" methods are first ones then further calls to
     * "nextBytes(new byte[0])" methods has no effect
     */
    public final void testNextBytesbyteArray04() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        /*
         * these are needed to test new SecureRandom objects in loop
         */
        SecureRandom sr1;
        SecureRandom sr2;

        byte[] myBytes;
        byte[] myBytes1;
        byte[] myBytes2;

        // case 1:
        for (int i = 1; i < LENGTH / 2; i += INCR) {
            sr1 = SecureRandom.getInstance(algorithm, provider);
            sr2 = SecureRandom.getInstance(algorithm, provider);

            sr1.nextBytes(new byte[0]);
            sr2.nextBytes(new byte[0]);

            boolean flag = true;

            myBytes1 = new byte[i];
            myBytes2 = new byte[i];

            sr1.nextBytes(myBytes1);
            sr2.nextBytes(myBytes2);
            for (int j = 0; j < i; j++) {
                flag &= myBytes1[j] == myBytes2[j];
            }

            // check again to avoid intermittent failures
            sr1.nextBytes(myBytes1);
            sr2.nextBytes(myBytes2);
            for (int j = 0; j < i; j++) {
                flag &= myBytes1[j] == myBytes2[j];
            }

            if (flag) {
                // probability of false failure is 1.5*10^-5 per run for i=1 or
                // less for i > 1
                fail("TESTING RANDOM NUMBER GENERATOR QUALITY: IGNORE THIS FAILURE IF INTERMITTENT :: i="
                        + i);
            }
        }

        myBytes = new byte[] {
            (byte) 0
        };

        // case2:
        for (int n = 1; n < LENGTH; n += INCR) {
            byte[][] bytes1 = new byte[2][n];
            byte[][] bytes2 = new byte[2][n];

            sr1 = SecureRandom.getInstance(algorithm, provider);
            sr2 = SecureRandom.getInstance(algorithm, provider);

            sr1.setSeed(myBytes);
            sr2.setSeed(myBytes);

            sr1.nextBytes(bytes1[0]);
            sr1.nextBytes(bytes1[1]);

            sr2.nextBytes(bytes2[0]);
            sr2.nextBytes(new byte[0]);
            sr2.nextBytes(bytes2[1]);

            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < n; j++) {
                    assertTrue("non-equality: k=" + k + " j=" + j + " bytes1[k][j]=" + bytes1[k][j]
                            + " bytes2[k][j]=" + bytes2[k][j], bytes1[k][j] == bytes2[k][j]);
                }
            }
        }
    }

    /**
     * test against the "void setSeed(byte[])" method; it checks out that the
     * method throws NPE if argument supplied is null
     */
    public final void testSetSeedbyteArray01() {
        try {
            sr.setSeed(null);
            fail("setSeed(null) :: No NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    /**
     * test against the "void setSeed(byte[])" method; it checks out that
     * "setSeed(byte[])" method supplements its argument to current seed rather
     * than replaces current seed
     */
    public final void testSetSeedbyteArray02() throws NoSuchFieldException, SecurityException,
            IllegalAccessException {
        byte[] seed = new byte[LENGTH];
        byte[] bytes1 = new byte[LENGTH];
        byte[] bytes2 = new byte[LENGTH];
        boolean b;

        for (int i = 0; i < seed.length; i++) {
            seed[i] = (byte) i;
        }

        sr.setSeed(seed);
        sr.setSeed(seed);
        sr2.setSeed(seed);

        sr.nextBytes(bytes1);
        sr2.nextBytes(bytes2);

        b = true;
        for (int j = 0; j < bytes1.length; j++) {
            b &= bytes1[j] == bytes2[j];
        }
        assertFalse("unexpected: sequences are equal", b);
    }

    /**
     * test against the "void setSeed(byte[])" method; it checks out that the
     * "byte[0]" argument has no effect; there are two testcases: - if one of
     * two SecureRandom objects supplied with the same seed is additionally
     * supplied with such array, "nextBytes(..)" of both objects return the same
     * bytes; - two byte arrays returned by "nextBytes(..)" in following
     * sequence nextBytes(..); setSeed(new byte[0]); nextBytes(..); don't
     * contain the same byte sequencies.
     */
    public final void testSetSeedbyteArray03() throws NoSuchFieldException, SecurityException,
            IllegalAccessException {
        byte[] seed = new byte[LENGTH];
        byte[] bytes1;
        byte[] bytes2;

        for (int i = 0; i < seed.length; i++) {
            seed[i] = (byte) i;
        }

        // testcase begins with "bytes1" and "bytes2" of zero length
        for (int i = 0; i < LENGTH; i++) {
            bytes1 = new byte[i];
            bytes2 = new byte[i];

            sr.setSeed(seed);
            sr.setSeed(new byte[0]);
            sr.nextBytes(bytes1);

            sr2.setSeed(seed);
            sr2.nextBytes(bytes2);

            for (int j = 0; j < bytes1.length; j++) {
                assertEquals("bytes1[j] != bytes2[j] :: j=" + j, bytes1[j], bytes2[j]);
            }
        }

        for (int i = 1; i < LENGTH; i++) {
            bytes1 = new byte[i];
            bytes2 = new byte[i];

            sr.setSeed(seed);
            sr.nextBytes(bytes1);
            sr.setSeed(new byte[0]);
            sr.nextBytes(bytes2);

            boolean b = true;
            for (int j = 0; j < bytes1.length; j++) {
                b &= bytes1[j] == bytes2[j];
            }
            assertFalse("sequences are equal i=" + i, b);
        }
    }

    public void testSeedIsFullLength() throws Exception {
        Class<?> srClass = Class.forName(
                "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
        Field seedField = srClass.getDeclaredField("seed");
        seedField.setAccessible(true);

        Method nextBytesMethod = srClass.getDeclaredMethod("engineNextBytes", byte[].class);
        nextBytesMethod.setAccessible(true);

        byte[] bytes = new byte[1];

        // Iterate 8 times to make sure the probability of a false positive is
        // extremely rare.
        for (int i = 0; i < 8; i++) {
            Object sr = srClass.newInstance();
            nextBytesMethod.invoke(sr, bytes);
            int[] seed = (int[]) seedField.get(sr);

            // If the first integer is not zero, it is fixed.
            if (seed[0] != 0) {
                return; // Success
            }
        }

        fail("Fallback SHA1PRNG_SecureRandomImpl should not clobber seed internally");
    }
}
