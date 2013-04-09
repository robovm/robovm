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
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.ManagerFactoryParameters;

import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.xnet.tests.support.MyKeyManagerFactorySpi;
import junit.framework.TestCase;

/**
 * Tests for KeyManagerFactory class constructors and methods
 *
 */
public class KeyManagerFactory2Test extends TestCase {
    private static final String srvKeyManagerFactory = "KeyManagerFactory";

    private static final String defaultAlg = "KeyMF";

    private static final String KeyManagerFactoryProviderClass = "org.apache.harmony.xnet.tests.support.MyKeyManagerFactorySpi";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static final String[] validValues;

    static {
        validValues = new String[4];
        validValues[0] = defaultAlg;
        validValues[1] = defaultAlg.toLowerCase();
        validValues[2] = "Keymf";
        validValues[3] = "kEYMF";
    }

    Provider mProv;

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyKMFProvider",
                "Provider for testing", srvKeyManagerFactory.concat(".")
                        .concat(defaultAlg), KeyManagerFactoryProviderClass);
        Security.insertProviderAt(mProv, 2);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    private void checkResult(KeyManagerFactory keyMF)
        throws Exception {
        KeyStore kStore = null;
        ManagerFactoryParameters mfp = null;

        char[] pass = { 'a', 'b', 'c' };

        try {
            keyMF.init(kStore, null);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {
        }
        try {
            keyMF.init(kStore, pass);
            fail("UnrecoverableKeyException must be thrown");
        } catch (UnrecoverableKeyException e) {
        }
        try {
            keyMF.init(mfp);
            fail("InvalidAlgorithmParameterException must be thrown");
        } catch (InvalidAlgorithmParameterException e) {
        }
        assertNull("getKeyManagers() should return null object", keyMF
                .getKeyManagers());

        try {
            kStore = KeyStore.getInstance(KeyStore.getDefaultType());
            kStore.load(null, null);
        } catch (KeyStoreException e) {
            fail("default keystore is not supported");
            return;
        }
        keyMF.init(kStore, pass);

        mfp = new MyKeyManagerFactorySpi.Parameters(kStore, null);
        try {
            keyMF.init(mfp);
            fail("InvalidAlgorithmParameterException must be thrown");
        } catch (InvalidAlgorithmParameterException e) {
        }
        mfp = new MyKeyManagerFactorySpi.Parameters(kStore, pass);
        keyMF.init(mfp);
    }
    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     * returns KeyManagerFactory object
     */
    public void test_getInstanceLjava_lang_String() throws Exception {
        try {
            KeyManagerFactory.getInstance(null);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyManagerFactory.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        KeyManagerFactory keyMF;
        for (int i = 0; i < validValues.length; i++) {
            keyMF = KeyManagerFactory.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", keyMF.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyMF.getProvider(), mProv);
            checkResult(keyMF);
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
     * returns KeyManagerFactory object
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String()
        throws Exception
    {
        try {
            KeyManagerFactory.getInstance(null, mProv.getName());
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyManagerFactory
                        .getInstance(invalidValues[i], mProv.getName());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                KeyManagerFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
            try {
                KeyManagerFactory.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown when provider is empty (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    KeyManagerFactory.getInstance(validValues[i],
                            invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(invalidValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        KeyManagerFactory keyMF;
        for (int i = 0; i < validValues.length; i++) {
            keyMF = KeyManagerFactory.getInstance(validValues[i], mProv
                    .getName());
            assertEquals("Incorrect algorithm", keyMF.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyMF.getProvider().getName(),
                    mProv.getName());
            checkResult(keyMF);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     * throws IllegalArgumentException when provider is null;
     * returns KeyManagerFactory object
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider()
        throws Exception
    {
        try {
            KeyManagerFactory.getInstance(null, mProv);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (algorithm is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyManagerFactory.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                KeyManagerFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        KeyManagerFactory keyMF;
        for (int i = 0; i < validValues.length; i++) {
            keyMF = KeyManagerFactory.getInstance(validValues[i], mProv);
            assertEquals("Incorrect algorithm", keyMF.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyMF.getProvider(), mProv);
            checkResult(keyMF);
       }
    }
}
