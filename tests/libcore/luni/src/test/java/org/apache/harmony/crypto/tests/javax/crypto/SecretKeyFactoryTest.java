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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package org.apache.harmony.crypto.tests.javax.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.harmony.crypto.tests.support.MySecretKeyFactorySpi;
import org.apache.harmony.security.tests.support.SpiEngUtils;

import junit.framework.TestCase;

/**
 * Tests for <code>SecretKeyFactory</code> class constructors and methods.
 *
 */
public class SecretKeyFactoryTest extends TestCase {

    public static final String srvSecretKeyFactory = "SecretKeyFactory";

    private static String defaultAlgorithm1 = "DESede";
    private static String defaultAlgorithm2 = "DES";

    public static String defaultAlgorithm = null;

    private static String defaultProviderName = null;

    private static Provider defaultProvider = null;

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    public static final String[] validValues = new String[2];
    private static boolean DEFSupported = false;

    private static final String NotSupportMsg = "Default algorithm is not supported";

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultAlgorithm1,
                srvSecretKeyFactory);
        DEFSupported = (defaultProvider != null);
        if (DEFSupported) {
            defaultAlgorithm = defaultAlgorithm1;
            validValues[0] = defaultAlgorithm.toUpperCase();
            validValues[1] = defaultAlgorithm.toLowerCase();
            defaultProviderName = defaultProvider.getName();
        } else {
            defaultProvider = SpiEngUtils.isSupport(defaultAlgorithm2,
                    srvSecretKeyFactory);
            DEFSupported = (defaultProvider != null);
            if (DEFSupported) {
                defaultAlgorithm = defaultAlgorithm2;
                validValues[0] = defaultAlgorithm.toUpperCase();
                validValues[2] = defaultAlgorithm.toLowerCase();
                defaultProviderName = defaultProvider.getName();
            } else {
                defaultAlgorithm = null;
                defaultProviderName = null;
                defaultProvider = null;
            }
        }
    }

    protected SecretKeyFactory[] createSKFac() {
        if (!DEFSupported) {
            fail(defaultAlgorithm + " algorithm is not supported");
            return null;
        }
        SecretKeyFactory[] skF = new SecretKeyFactory[3];
        try {
            skF[0] = SecretKeyFactory.getInstance(defaultAlgorithm);
            skF[1] = SecretKeyFactory.getInstance(defaultAlgorithm,
                    defaultProvider);
            skF[2] = SecretKeyFactory.getInstance(defaultAlgorithm,
                    defaultProviderName);
            return skF;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Test for <code>SecretKeyFactory</code> constructor
     * Assertion: returns SecretKeyFactory object
     */
    public void testSecretKeyFactory01() throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SecretKeyFactorySpi spi = new MySecretKeyFactorySpi();
        SecretKeyFactory secKF = new mySecretKeyFactory(spi, defaultProvider,
                defaultAlgorithm);
        assertEquals("Incorrect algorithm", secKF.getAlgorithm(),
                defaultAlgorithm);
        assertEquals("Incorrect provider", secKF.getProvider(), defaultProvider);
        assertNull("Incorrect result", secKF.generateSecret(null));
        assertNull("Incorrect result", secKF.getKeySpec(null, null));
        assertNull("Incorrect result", secKF.translateKey(null));
        secKF = new mySecretKeyFactory(null, null, null);
        assertNull("Algorithm must be null", secKF.getAlgorithm());
        assertNull("Provider must be null", secKF.getProvider());
        try {
            secKF.translateKey(null);
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm has invalid value
     */
    public void testSecretKeyFactory02() throws NoSuchAlgorithmException {
        try {
            SecretKeyFactory.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SecretKeyFactory.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException was not thrown as expected");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion: returns SecretKeyObject
     */
    public void testSecretKeyFactory03() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 0; i < validValues.length; i++) {
            SecretKeyFactory secKF = SecretKeyFactory
                    .getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", secKF.getAlgorithm(),
                    validValues[i]);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is invalid
     */
    public void testSecretKeyFactory04() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            SecretKeyFactory.getInstance(null, defaultProviderName);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SecretKeyFactory.getInstance(invalidValues[i],
                        defaultProviderName);
                fail("NoSuchAlgorithmException was not thrown as expected (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion:
     * throws IllegalArgumentException when provider is null or empty;
     * throws NoSuchProviderException when provider has invalid value
     */
    public void testSecretKeyFactory05() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                SecretKeyFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException was not thrown as expected (algorithm: "
                        .concat(validValues[i]).concat(" provider: null"));
            } catch (IllegalArgumentException e) {
            }
            try {
                SecretKeyFactory.getInstance(validValues[i], "");
                fail("IllegalArgumentException was not thrown as expected (algorithm: "
                        .concat(validValues[i]).concat(" provider: empty"));
            } catch (IllegalArgumentException e) {
            }
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    SecretKeyFactory.getInstance(validValues[i],
                            invalidValues[j]);
                    fail("NoSuchProviderException was not thrown as expected (algorithm: "
                            .concat(validValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion: returns SecretKeyFactory object
     */
    public void testSecretKeyFactory06() throws NoSuchProviderException,
            NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 0; i < validValues.length; i++) {
            SecretKeyFactory secKF = SecretKeyFactory.getInstance(
                    validValues[i], defaultProviderName);
            assertEquals("Incorrect algorithm", secKF.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", secKF.getProvider().getName(),
                    defaultProviderName);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion: throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is invalid
     */
    public void testSecretKeyFactory07() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            SecretKeyFactory.getInstance(null, defaultProvider);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SecretKeyFactory.getInstance(invalidValues[i], defaultProvider);
                fail("NoSuchAlgorithmException was not thrown as expected (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion: throws IllegalArgumentException when provider is null
     */
    public void testSecretKeyFactory08() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                SecretKeyFactory.getInstance(validValues[i], prov);
                fail("IllegalArgumentException was not thrown as expected (provider is null, algorithm: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion: returns SecretKeyFactory object
     */
    public void testSecretKeyFactory09() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 0; i < validValues.length; i++) {
            SecretKeyFactory secKF = SecretKeyFactory.getInstance(
                    validValues[i], defaultProvider);
            assertEquals("Incorrect algorithm", secKF.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", secKF.getProvider(),
                    defaultProvider);
        }
    }

    /**
     * Test for <code>generateSecret(KeySpec keySpec)</code> and
     * <code>getKeySpec(SecretKey key, Class keySpec)
     * methods
     * Assertion:
     * throw InvalidKeySpecException if parameter is inappropriate
     */
    public void testSecretKeyFactory10() throws InvalidKeyException,
            InvalidKeySpecException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        byte[] bb = new byte[24];
        KeySpec ks = (defaultAlgorithm.equals(defaultAlgorithm2) ? (KeySpec)new DESKeySpec(bb) :
            (KeySpec)new DESedeKeySpec(bb));
        KeySpec rks = null;
        SecretKeySpec secKeySpec = new SecretKeySpec(bb, defaultAlgorithm);
        SecretKey secKey = null;
        SecretKeyFactory[] skF = createSKFac();
        assertNotNull("SecretKeyFactory object were not created", skF);
        for (int i = 0; i < skF.length; i++) {
            try {
                skF[i].generateSecret(null);
                fail("generateSecret(null): InvalidKeySpecException must be thrown");
            } catch (InvalidKeySpecException e) {
            }

            secKey = skF[i].generateSecret(ks);
            try {
                skF[i].getKeySpec(null, null);
                fail("getKeySpec(null,null): InvalidKeySpecException must be thrown");
            } catch (InvalidKeySpecException e) {
            }
            try {
                skF[i].getKeySpec(null, ks.getClass());
                fail("getKeySpec(null, Class): InvalidKeySpecException must be thrown");
            } catch (InvalidKeySpecException e) {
            }
            try {
                skF[i].getKeySpec(secKey, null);
                fail("getKeySpec(secKey, null): NullPointerException or InvalidKeySpecException must be thrown");
            } catch (InvalidKeySpecException e) {
                // Expected
            } catch (NullPointerException e) {
                // Expected
            }

            try {
                Class c;
                if (defaultAlgorithm.equals(defaultAlgorithm2)) {
                    c = DESedeKeySpec.class;
                } else {
                    c = DESKeySpec.class;
                }
                skF[i].getKeySpec(secKeySpec, c);
                fail("getKeySpec(secKey, Class): InvalidKeySpecException must be thrown");
            } catch (InvalidKeySpecException e) {
            }
            rks = skF[i].getKeySpec(secKeySpec, ks.getClass());
            if (defaultAlgorithm.equals(defaultAlgorithm1)) {
                assertTrue("Incorrect getKeySpec() result 1",
                        rks instanceof DESedeKeySpec);
            } else {
                assertTrue("Incorrect getKeySpec() result 1",
                        rks instanceof DESKeySpec);
            }

            rks = skF[i].getKeySpec(secKey, ks.getClass());
            if (defaultAlgorithm.equals(defaultAlgorithm1)) {
                assertTrue("Incorrect getKeySpec() result 2",
                        rks instanceof DESedeKeySpec);
            } else {
                assertTrue("Incorrect getKeySpec() result 2",
                        rks instanceof DESKeySpec);
            }
        }
    }

    public void test_getAlgorithm() throws NoSuchAlgorithmException {
        for (int i = 0; i < validValues.length; i++) {
            SecretKeyFactory secKF = SecretKeyFactory
                    .getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", secKF.getAlgorithm(),
                    validValues[i]);
        }

        Mock_SecretKeyFactory msf = new Mock_SecretKeyFactory(null, null, null);
        assertNull(msf.getAlgorithm());
    }

    class Mock_SecretKeyFactory extends SecretKeyFactory{
        protected Mock_SecretKeyFactory(SecretKeyFactorySpi arg0, Provider arg1, String arg2) {
            super(arg0, arg1, arg2);
        }
    }

    public void test_getProvider() throws NoSuchAlgorithmException {
        for (int i = 0; i < validValues.length; i++) {
            SecretKeyFactory secKF = SecretKeyFactory
                    .getInstance(validValues[i]);
            assertNotNull(secKF.getProvider());
        }

        Mock_SecretKeyFactory msf = new Mock_SecretKeyFactory(null, null, null);
        assertNull(msf.getProvider());
    }

    public void test_translateKeyLjavax_crypto_SecretKey()
            throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator kg = null;
        Key key = null;
        SecretKeyFactory secKF = null;

        for (int i = 0; i < validValues.length; i++) {
            secKF = SecretKeyFactory
                    .getInstance(validValues[i]);
            assertNotNull(secKF.getProvider());
            kg = KeyGenerator.getInstance(secKF.getAlgorithm());
            kg.init(new SecureRandom());
            key = kg.generateKey();

            secKF.translateKey((SecretKey) key);
        }
        try {
            secKF.translateKey(null);
            fail("InvalidKeyException expected");
        } catch (InvalidKeyException e) {
            //expected
        }
    }
}

class mySecretKeyFactory extends SecretKeyFactory {
    public mySecretKeyFactory(SecretKeyFactorySpi spi, Provider prov, String alg) {
        super(spi, prov, alg);
    }
}
