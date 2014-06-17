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

package libcore.java.math;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerTest extends junit.framework.TestCase {
    // http://code.google.com/p/android/issues/detail?id=18452
    public void test_hashCode() {
        BigInteger firstBig  = new BigInteger("30003543667898318853");
        BigInteger secondBig = new BigInteger("3298535022597");
        BigInteger andedBigs = firstBig.and(secondBig);
        BigInteger toCompareBig = BigInteger.valueOf(andedBigs.longValue());
        assertEquals(andedBigs, toCompareBig);
        assertEquals(andedBigs.hashCode(), toCompareBig.hashCode());
    }

    // http://b/2981072 - off-by-one error in BigInteger.valueOf
    public void test_valueOf() {
        // I assume here that we'll never cache more than 1024 values.
        // (At the moment, we cache -1 to 10.)
        for (int i = -1024; i <= 1024; ++i) {
            assertEquals(i, BigInteger.valueOf(i).intValue());
        }
    }

    // http://code.google.com/p/android/issues/detail?id=7036
    public void test_invalidBigIntegerStringConversions() {
        // Check we don't disallow related reasonable strings...
        new BigInteger("1", 10);
        new BigInteger("1a", 16);
        new BigInteger("-1", 10);
        new BigInteger("-1a", 16);
        new BigInteger("\u0661", 10);
        new BigInteger("\u0661a", 16);
        new BigInteger("-\u0661", 10);
        new BigInteger("-\u0661a", 16);
        // This is allowed from Java 7 on.
        new BigInteger("+1");
        // Now check the invalid cases...
        try {
            new BigInteger("-a"); // no digits from other bases.
            fail();
        } catch (NumberFormatException expected) {
        }
        try {
            new BigInteger("-1a"); // no trailing digits from other bases.
            fail();
        } catch (NumberFormatException expected) {
        }
        try {
            new BigInteger("-1 hello"); // no trailing junk at all.
            fail();
        } catch (NumberFormatException expected) {
        }
        try {
            new BigInteger("--1"); // only one sign.
            fail();
        } catch (NumberFormatException expected) {
        }
        try {
            new BigInteger(""); // at least one digit.
            fail();
        } catch (NumberFormatException expected) {
        }
        try {
            new BigInteger("-"); // at least one digit, even after a sign.
            fail();
        } catch (NumberFormatException expected) {
        }
    }

    public void test_Constructor_ILjava_util_Random() throws Exception {
      Random rand = new Random();
      BigInteger b;
      for (int rep = 0; rep < 1024; ++rep) { // Manual flakiness protection for random tests.
        b = new BigInteger(128, rand);
        assertTrue(b.toString() + " " + b.bitLength(), b.bitLength() <= 128);

        b = new BigInteger(16, rand);
        assertTrue(b.toString() + " " + b.bitLength(), b.bitLength() <= 16);

        b = new BigInteger(5, rand);
        assertTrue(b.toString() + " " + b.bitLength(), b.bitLength() <= 5);
      }
    }

    public void test_Constructor_IILjava_util_Random() throws Exception {
      Random rand = new Random();
      BigInteger b;
      for (int rep = 0; rep < 1024; ++rep) { // Manual flakiness protection for random tests.
        b = new BigInteger(128, 100, rand);
        assertEquals(b.toString(), 128, b.bitLength());
        assertTrue(b.isProbablePrime(100));

        b = new BigInteger(16, 100, rand);
        assertEquals(b.toString(), 16, b.bitLength());
        assertTrue(b.isProbablePrime(100));

        b = new BigInteger(5, 100, rand);
        assertEquals(b.toString(), 5, b.bitLength());
        assertTrue(b.isProbablePrime(100));
      }

      // Two bits is an interesting special case because there's an even 2-bit prime.
      int[] primes = new int[1024];
      boolean saw2 = false;
      boolean saw3 = false;
      for (int rep = 0; rep < primes.length; ++rep) { // Manual flakiness protection for random tests.
        b = new BigInteger(2, 100, rand);
        assertEquals(b.toString(), 2, b.bitLength());
        assertTrue(b.isProbablePrime(100));
        primes[rep] = b.intValue();
      }
      for (int i = 0; i < primes.length; ++i) {
        if (primes[i] == 2) {
          saw2 = true;
        } else if (primes[i] == 3) {
          saw3 = true;
        } else {
          fail();
        }
      }
      assertTrue(saw2 && saw3);
    }

    public void test_probablePrime() throws Exception {
      Random rand = new Random();
      BigInteger b;
      for (int rep = 0; rep < 1024; ++rep) { // Manual flakiness protection for random tests.
        b = BigInteger.probablePrime(128, rand);
        assertEquals(b.toString(), 128, b.bitLength());
        assertTrue(b.isProbablePrime(100));

        b = BigInteger.probablePrime(16, rand);
        assertEquals(b.toString(), 16, b.bitLength());
        assertTrue(b.isProbablePrime(100));

        b = BigInteger.probablePrime(5, rand);
        assertEquals(b.toString(), 5, b.bitLength());
        assertTrue(b.isProbablePrime(100));
      }
    }
}
