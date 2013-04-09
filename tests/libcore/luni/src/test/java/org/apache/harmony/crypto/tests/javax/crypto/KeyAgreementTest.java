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

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.harmony.crypto.tests.support.MyKeyAgreementSpi;
import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.security.tests.support.TestKeyPair;

import junit.framework.TestCase;


/**
 * Tests for KeyAgreement constructor and methods
 *
 */
public class KeyAgreementTest extends TestCase {

    public static final String srvKeyAgreement = "KeyAgreement";

    private static String defaultAlgorithm = "DH";

    private static String defaultProviderName = null;

    private static Provider defaultProvider = null;

    private static boolean DEFSupported = false;

    private static final String NotSupportMsg = "There is no suitable provider for KeyAgreement";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static String[] validValues = { "DH", "dH",
            "Dh", "dh" };

    private static PrivateKey privKey = null;

    private static PublicKey publKey = null;

    private static boolean initKeys = false;

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultAlgorithm,
                srvKeyAgreement);
        DEFSupported = (defaultProvider != null);
        defaultProviderName = (DEFSupported ? defaultProvider.getName() : null);
    }

    private void createKeys() throws Exception {
        if (!initKeys) {
            TestKeyPair tkp = new TestKeyPair(defaultAlgorithm);
            privKey = tkp.getPrivate();
            publKey = tkp.getPublic();
            initKeys = true;
        }

    }

    private KeyAgreement[] createKAs() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
        }

        KeyAgreement[] ka = new KeyAgreement[3];
        ka[0] = KeyAgreement.getInstance(defaultAlgorithm);
        ka[1] = KeyAgreement.getInstance(defaultAlgorithm, defaultProvider);
        ka[2] = KeyAgreement.getInstance(defaultAlgorithm,
                defaultProviderName);
        return ka;
    }

    public static String getDefAlg() {
        return defaultAlgorithm;
    }

    /**
     * Test for <code> getInstance(String algorithm) </code> method Assertions:
     * throws NullPointerException when algorithm is null throws
     * NoSuchAlgorithmException when algorithm isnot available
     */
    public void testGetInstanceString01() throws NoSuchAlgorithmException {
        try {
            KeyAgreement.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm) </code> method Assertions:
     * returns KeyAgreement object
     */
    public void testGetInstanceString02() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm is not available
     */
    public void testGetInstanceStringString01()
            throws NoSuchAlgorithmException, IllegalArgumentException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            KeyAgreement.getInstance(null, defaultProviderName);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], defaultProviderName);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: throws IllegalArgumentException when provider is null
     * or empty throws NoSuchProviderException when provider has not be
     * configured
     */
    public void testGetInstanceStringString02()
            throws IllegalArgumentException, NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        String provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                KeyAgreement.getInstance(validValues[i], provider);
                fail("IllegalArgumentException must be thrown when provider is null");
            } catch (IllegalArgumentException e) {
            }
            try {
                KeyAgreement.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown when provider is empty");
            } catch (IllegalArgumentException e) {
            }
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    KeyAgreement.getInstance(validValues[i], invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(validValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: returns KeyAgreement object
     */
    public void testGetInstanceStringString03()
            throws IllegalArgumentException, NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement
                    .getInstance(validValues[i], defaultProviderName);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyA.getProvider().getName(),
                    defaultProviderName);
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm isnot available
     */
    public void testGetInstanceStringProvider01()
            throws NoSuchAlgorithmException, IllegalArgumentException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            KeyAgreement.getInstance(null, defaultProvider);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], defaultProvider);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: throws IllegalArgumentException when provider is null
     */
    public void testGetInstanceStringProvider02()
            throws NoSuchAlgorithmException, IllegalArgumentException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        Provider provider = null;
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], provider);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: returns KeyAgreement object
     */
    public void testGetInstanceStringProvider03()
            throws IllegalArgumentException, NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i], defaultProvider);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyA.getProvider(),
                    defaultProvider);
        }
    }

    /**
     * Test for the methods: <code>init(Key key)</code>
     * <code>generateSecret()</code>
     * <code>generateSecret(byte[] sharedsecret, int offset)</code>
     * <code>generateSecret(String algorithm)</code>
     * Assertions: initializes KeyAgreement; returns sharedSecret; puts
     * sharedsecret in buffer and return numbers of bytes; returns SecretKey
     * object
     */
    public void testGenerateSecret03() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();

        byte[] bb;
        byte[] bb1 = new byte[10];
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bb = kAgs[i].generateSecret();
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bb1 = new byte[bb.length + 10];
            kAgs[i].generateSecret(bb1, 9);
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            kAgs[i].generateSecret("DES");
        }
    }

    /**
     * Test for <code>doPhase(Key key, boolean lastPhase)</code> method
     * Assertion: throws InvalidKeyException if key is not appropriate
     */
    public void testDoPhase() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();
        DHParameterSpec dhPs = ((DHPrivateKey) privKey).getParams();
        SecureRandom randomNull = null;
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < kAgs.length; i++) {
            try {
                kAgs[i].doPhase(publKey, true);
                fail("IllegalStateException expected");
            } catch (IllegalStateException e) {
                //expected
            }

            kAgs[i].init(privKey);

            try {
                kAgs[i].doPhase(privKey, false);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }

            try {
                kAgs[i].doPhase(privKey, true);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }

            kAgs[i].init(privKey, dhPs);
            kAgs[i].doPhase(publKey, true);
            kAgs[i].init(privKey, dhPs, random);
            kAgs[i].doPhase(publKey, true);
        }
    }

    /**
     * Test for the methods <code>init(Key key)</code>
     * <code>init(Key key, SecureRandom random)</code>
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * Assertion: throws InvalidKeyException when key is inappropriate
     */
    public void testInit01() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();

        SecureRandom random = null;
        AlgorithmParameterSpec aps = null;
        DHParameterSpec dhPs = new DHParameterSpec(new BigInteger("56"),
                new BigInteger("56"));
        for (int i = 0; i < kAgs.length; i++) {
            try {
                kAgs[i].init(publKey);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, new SecureRandom());
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, random);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, dhPs);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, aps);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, dhPs, new SecureRandom());
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
        }
    }

    /**
     * Test for the methods
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * Assertion: throws AlgorithmParameterException when params are
     * inappropriate
     */
    public void testInit02() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();

        SecureRandom random = null;
        DSAParameterSpec dsa = new DSAParameterSpec(new BigInteger("56"),
                new BigInteger("56"), new BigInteger("56"));
        for (int i = 0; i < kAgs.length; i++) {
            try {
                kAgs[i].init(privKey, dsa);
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(privKey, dsa, new SecureRandom());
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(privKey, dsa, random);
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
        }
    }

    /**
     * Test for the methods: <code>init(Key key)</code>
     * <code>init(Key key, SecureRandom random)</code>
     * <code>generateSecret()</code>
     * Assertions: initializes KeyAgreement and returns byte array
     */
    public void testInit03() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();

        byte[] bbRes1;
        byte[] bbRes2;
        byte[] bbRes3;
        SecureRandom randomNull = null;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bbRes1 = kAgs[i].generateSecret();
            kAgs[i].init(privKey, random);
            kAgs[i].doPhase(publKey, true);
            bbRes2 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes2.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes2[j]);
            }
            kAgs[i].init(privKey, randomNull);
            kAgs[i].doPhase(publKey, true);
            bbRes3 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes3.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes3[j]);
            }
        }
    }

    /**
     * Test for the methods:
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * <code>generateSecret()</code>
     * Assertions: initializes KeyAgreement and returns byte array
     */
    public void testInit04() throws Exception,
            InvalidAlgorithmParameterException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();

        DHParameterSpec dhPs = ((DHPrivateKey) privKey).getParams();
        AlgorithmParameterSpec aps = new RSAKeyGenParameterSpec(10, new BigInteger("10"));

        byte[] bbRes1;
        byte[] bbRes2;
        byte[] bbRes3;
        SecureRandom randomNull = null;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey, dhPs);
            kAgs[i].doPhase(publKey, true);
            bbRes1 = kAgs[i].generateSecret();
            kAgs[i].init(privKey, dhPs, random);
            kAgs[i].doPhase(publKey, true);
            bbRes2 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes2.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes2[j]);
            }
            kAgs[i].init(privKey, dhPs, randomNull);
            kAgs[i].doPhase(publKey, true);
            bbRes3 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes3.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes3[j]);
            }

            try {
                kAgs[i].init(publKey, dhPs, random);
                fail("InvalidKeyException expected");
            } catch (InvalidKeyException e) {
                //expected
            }
            try {
                kAgs[i].init(privKey, aps, random);
                fail("InvalidAlgorithmParameterException expected");
            } catch (InvalidAlgorithmParameterException e) {
                //expected
            }
        }
    }

    class Mock_KeyAgreement extends KeyAgreement {
        protected Mock_KeyAgreement(KeyAgreementSpi arg0, Provider arg1, String arg2) {
            super(arg0, arg1, arg2);
        }
    }

    public void test_constructor() {
        assertNotNull(new Mock_KeyAgreement(null, null, null));
    }

    public void test_getAlgorithm() throws NoSuchAlgorithmException {
        Mock_KeyAgreement mka = new Mock_KeyAgreement(null, null, null);
        assertNull(mka.getAlgorithm());

        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
        }
    }

    public void test_getProvider() throws NoSuchAlgorithmException {
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i]);
            assertNotNull(keyA.getProvider());
        }
    }

    public void test_generateSecret$BI() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();
        KeyAgreement ka = KeyAgreement.getInstance("DH");

        byte[] bb1 = new byte[1];
        try {
            ka.generateSecret(bb1, 0);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        ka.init(privKey);
        ka.doPhase(publKey, true);
        try {
            ka.generateSecret(bb1, 0);
            fail("ShortBufferException expected");
        } catch (ShortBufferException e) {
            //expected
        }
    }

    public void test_generateSecretLjava_lang_String() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();
        KeyAgreement ka = KeyAgreement.getInstance("DH");

        byte[] bb1 = new byte[1];
        try {
            ka.generateSecret("dh");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        ka.init(privKey);
        ka.doPhase(publKey, true);
        try {
            ka.generateSecret("Wrong alg name");
            fail("NoSuchAlgorithmException expected");
        } catch (NoSuchAlgorithmException e) {
            //expected
        }
    }

    public void test_initLjava_security_KeyLjava_security_SecureRandom() throws Exception {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        createKeys();
        KeyAgreement[] kAgs = createKAs();
        KeyAgreement ka = KeyAgreement.getInstance("DH");

        ka.init(privKey, new SecureRandom());
        try {
            ka.init(publKey, new SecureRandom());
            fail("InvalidKeyException expected");
        } catch (InvalidKeyException e) {
            //expected
        }
    }
}
