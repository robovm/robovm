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

package tests.api.javax.net.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManagerFactory;

import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.xnet.tests.support.MyTrustManagerFactorySpi;
import junit.framework.TestCase;

/**
 * Tests for TrustManagerFactory class constructors and methods
 *
 */
public class TrustManagerFactory2Test extends TestCase {
    private static final String srvTrustManagerFactory = "TrustManagerFactory";
    private static final String defaultAlg = "TMF";
    private static final String TrustManagerFactoryProviderClass = "org.apache.harmony.xnet.tests.support.MyTrustManagerFactorySpi";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static final String[] validValues;

    static {
        validValues = new String[4];
        validValues[0] = defaultAlg;
        validValues[1] = defaultAlg.toLowerCase();
        validValues[2] = "Tmf";
        validValues[3] = "tMF";
    }

    Provider mProv;

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyTMFProvider",
                "Provider for testing", srvTrustManagerFactory.concat(".")
                        .concat(defaultAlg), TrustManagerFactoryProviderClass);
        Security.insertProviderAt(mProv, 1);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    private void checkResult(TrustManagerFactory tmf) throws Exception {
        KeyStore kStore = null;
        ManagerFactoryParameters mfp = null;

        try {
            tmf.init(kStore);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {
        }
        try {
            tmf.init(mfp);
            fail("InvalidAlgorithmParameterException must be thrown");
        } catch (InvalidAlgorithmParameterException e) {
        }
        assertNull("getTrustManagers() should return null object", tmf
                .getTrustManagers());

        try {
            kStore = KeyStore.getInstance(KeyStore.getDefaultType());
            kStore.load(null, null);
        } catch (KeyStoreException e) {
            fail("default keystore is not supported");
            return;
        }
        tmf.init(kStore);
        mfp = (ManagerFactoryParameters) new MyTrustManagerFactorySpi.Parameters(null);
        try {
            tmf.init(mfp);
            fail("RuntimeException must be thrown");
        } catch (RuntimeException e) {
            assertTrue("Incorrect exception", e.getCause() instanceof KeyStoreException);
        }
        mfp = (ManagerFactoryParameters) new MyTrustManagerFactorySpi.Parameters(kStore);
        tmf.init(mfp);
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     * returns TrustManagerFactory object
     */
    public void test_getInstanceLjava_lang_String() throws Exception {
        try {
            TrustManagerFactory.getInstance(null);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        TrustManagerFactory tmf;
        for (int i = 0; i < validValues.length; i++) {
            tmf = TrustManagerFactory.getInstance(validValues[i]);
            assertTrue("Not instanceof TrustManagerFactory object",
                    tmf instanceof TrustManagerFactory);
            assertEquals("Incorrect algorithm", tmf.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", tmf.getProvider(), mProv);
            checkResult(tmf);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     * throws IllegalArgumentException when provider is null or empty;
     * throws NoSuchProviderException when provider is available;
     * returns TrustManagerFactory object
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String() throws Exception {
        try {
            TrustManagerFactory.getInstance(null, mProv.getName());
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i], mProv
                        .getName());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
            try {
                TrustManagerFactory.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown when provider is empty (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    TrustManagerFactory.getInstance(validValues[i],
                            invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(invalidValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        TrustManagerFactory tmf;
        for (int i = 0; i < validValues.length; i++) {
            tmf = TrustManagerFactory.getInstance(validValues[i], mProv
                    .getName());
            assertTrue("Not instanceof TrustManagerFactory object",
                    tmf instanceof TrustManagerFactory);
            assertEquals("Incorrect algorithm", tmf.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", tmf.getProvider().getName(),
                    mProv.getName());
            checkResult(tmf);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     * throws IllegalArgumentException when provider is null;
     * returns TrustManagerFactory object
     */
    public void testLjava_lang_StringLjava_security_Provider() throws Exception {
        try {
            TrustManagerFactory.getInstance(null, mProv);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        TrustManagerFactory tmf;
        for (int i = 0; i < validValues.length; i++) {
            tmf = TrustManagerFactory.getInstance(validValues[i], mProv);
            assertTrue("Not instanceof TrustManagerFactory object",
                    tmf instanceof TrustManagerFactory);
            assertEquals("Incorrect algorithm", tmf.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", tmf.getProvider(), mProv);
            checkResult(tmf);
        }
    }
}
