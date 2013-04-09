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

/**
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package tests.java.security;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import junit.framework.TestCase;
import org.apache.harmony.security.tests.support.RandomImpl;

/**
 * Tests for <code>SecureRandom</code> constructor and methods
 *
 */
public class SecureRandomTest extends TestCase {

    /**
     * SRProvider
     */
    Provider p;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        p = new SRProvider();
        Security.insertProviderAt(p, 1);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(p.getName());
    }

    public final void testNext() {
        MySecureRandom sr = new MySecureRandom();
        if (sr.nextElement(1) != 1 || sr.nextElement(2) != 3 || sr.nextElement(3) != 7) {
            fail("next failed");
        }
    }

    /*
     * Class under test for void setSeed(long)
     */
    public final void testSetSeedlong() {
        SecureRandom sr = new SecureRandom();
        sr.setSeed(12345);
        if (!RandomImpl.runEngineSetSeed) {
            fail("setSeed failed");
        }
    }

    public final void testNextBytes() {
        byte[] b = new byte[5];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        for (int i = 0; i < b.length; i++) {
            if (b[i] != (byte)(i + 0xF1)) {
                fail("nextBytes failed");
            }
        }

        try {
            sr.nextBytes(null);
            fail("expected exception");
        } catch (Exception e) {
            // ok
        }
    }

    /*
     * Class under test for void SecureRandom()
     */
    public final void testSecureRandom() {
        SecureRandom sr = new SecureRandom();
        if (!sr.getAlgorithm().equals("someRandom")  ||
                sr.getProvider()!= p) {
            fail("incorrect SecureRandom implementation" + p.getName());
        }
    }

    /*
     * Class under test for void SecureRandom(byte[])
     */
    public final void testSecureRandombyteArray() {
        byte[] b = {1,2,3};
        new SecureRandom(b);

        if (!RandomImpl.runEngineSetSeed) {
            fail("No setSeed");
        }


    }

    /*
     * Class under test for SecureRandom getInstance(String)
     */
    public final void testGetInstanceString() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("someRandom");
        } catch (NoSuchAlgorithmException e) {
            fail(e.toString());
        }
        if (sr.getProvider() != p || !"someRandom".equals(sr.getAlgorithm())) {
            fail("getInstance failed");
        }
    }

    /*
     * Class under test for SecureRandom getInstance(String, String)
     */
    public final void testGetInstanceStringString() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("someRandom", "SRProvider");
        if (sr.getProvider() != p || !"someRandom".equals(sr.getAlgorithm())) {
            fail("getInstance failed");
        }

        try {
            SecureRandom r = SecureRandom.getInstance("anotherRandom", "SRProvider");
            fail("expected NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
            // ok
        } catch (NoSuchProviderException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            fail("unexpected: " + e);
        } catch (NullPointerException e) {
            fail("unexpected: " + e);
        }

        try {
            SecureRandom r = SecureRandom.getInstance("someRandom", "UnknownProvider");
            fail("expected NoSuchProviderException");
        } catch (NoSuchProviderException e) {
            // ok
        } catch (NoSuchAlgorithmException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            fail("unexpected: " + e);
        } catch (NullPointerException e) {
            fail("unexpected: " + e);
        }

        try {
            SecureRandom r = SecureRandom.getInstance("someRandom", (String)null);
            fail("expected IllegalArgumentException");
        } catch (NoSuchProviderException e) {
            fail("unexpected: " + e);
        } catch (NoSuchAlgorithmException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            // ok
        } catch (NullPointerException e) {
            fail("unexpected: " + e);
        }

        try {
            SecureRandom r = SecureRandom.getInstance(null, "SRProvider");
            fail("expected NullPointerException");
        } catch (NoSuchProviderException e) {
            fail("unexpected: " + e);
        } catch (NoSuchAlgorithmException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            fail("unexpected: " + e);
        } catch (NullPointerException e) {
            // ok
        }
    }

    /*
     * Class under test for SecureRandom getInstance(String, Provider)
     */
    public final void testGetInstanceStringProvider() throws Exception {
        Provider p = new SRProvider();
        SecureRandom sr = SecureRandom.getInstance("someRandom", p);
        if (sr.getProvider() != p || !"someRandom".equals(sr.getAlgorithm())) {
            fail("getInstance failed");
        }

        try {
            SecureRandom r = SecureRandom.getInstance("unknownRandom", p);
            fail("expected NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
            // ok
        } catch (IllegalArgumentException e) {
            fail("unexpected: " + e);
        } catch (NullPointerException e) {
            fail("unexpected: " + e);
        }


        try {
            SecureRandom r = SecureRandom.getInstance(null, p);
            fail("expected NullPointerException");
        } catch (NoSuchAlgorithmException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            fail("unexpected: " + e);
        } catch (NullPointerException e) {
            // ok
        }

        try {
            SecureRandom r = SecureRandom.getInstance("anyRandom", (Provider)null);
            fail("expected IllegalArgumentException");
        } catch (NoSuchAlgorithmException e) {
            fail("unexpected: " + e);
        } catch (IllegalArgumentException e) {
            // ok
        } catch (NullPointerException e) {
            fail("unexpected: " + e);
        }


    }

    /*
     * Class under test for void setSeed(byte[])
     */
    public final void testSetSeedbyteArray() {
        byte[] b = {1,2,3};
        SecureRandom sr = new SecureRandom();
        sr.setSeed(b);
        if (!RandomImpl.runEngineSetSeed) {
            fail("setSeed failed");
        }

    }

    public final void testGetSeed() {
        byte[] b = SecureRandom.getSeed(4);
        if( b.length != 4) {
            fail("getSeed failed");
        }
    }

    public final void testGenerateSeed() {
        SecureRandom sr = new SecureRandom();
        byte[] b = sr.generateSeed(4);
        for (int i = 0; i < b.length; i++) {
            if (b[i] != (byte)i) {
                fail("generateSeed failed");
            }
        }
    }



    public class SRProvider extends Provider {
        public SRProvider() {
            super("SRProvider", 1.0, "SRProvider for testing");
            put("SecureRandom.someRandom",
                    "org.apache.harmony.security.tests.support.RandomImpl");
        }
    }

    class MySecureRandom extends SecureRandom {
        public MySecureRandom(){
            super();
        }

        public int nextElement(int numBits) {
            return super.next(numBits);
        }
    }
}
