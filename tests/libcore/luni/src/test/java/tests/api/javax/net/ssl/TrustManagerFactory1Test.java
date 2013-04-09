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

import dalvik.annotation.KnownFailure;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import junit.framework.TestCase;
import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.security.tests.support.TestKeyPair;
import org.apache.harmony.xnet.tests.support.MyTrustManagerFactorySpi;

/**
 * Tests for <code>TrustManagerFactory</code> class constructors and methods.
 *
 */
public class TrustManagerFactory1Test extends TestCase {

    private static final String srvTrustManagerFactory = "TrustManagerFactory";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static String DEFAULT_ALGORITHM;
    private static String DEFAULT_PROVIDER_NAME;
    private static Provider DEFAULT_PROVIDER;
    private static String[] VALID_VALUES;

    private static String getDefaultAlgorithm() {
        init();
        return DEFAULT_ALGORITHM;
    }
    private static String getDefaultProviderName() {
        init();
        return DEFAULT_PROVIDER_NAME;
    }
    private static Provider getDefaultProvider() {
        init();
        return DEFAULT_PROVIDER;
    }
    private static String[] getValidValues() {
        init();
        return VALID_VALUES;
    }

    private static synchronized void init() {
        if (DEFAULT_ALGORITHM != null) {
            return;
        }
        DEFAULT_ALGORITHM = Security.getProperty("ssl.TrustManagerFactory.algorithm");
        assertNotNull(DEFAULT_ALGORITHM);
        DEFAULT_PROVIDER = SpiEngUtils.isSupport(DEFAULT_ALGORITHM, srvTrustManagerFactory);
        DEFAULT_PROVIDER_NAME = DEFAULT_PROVIDER.getName();
        VALID_VALUES = new String[] { DEFAULT_ALGORITHM,
                                      DEFAULT_ALGORITHM.toUpperCase(),
                                      DEFAULT_ALGORITHM.toLowerCase() };
    }

    private static TrustManagerFactory[] createTMFac() throws Exception {
        return new TrustManagerFactory[] {
            TrustManagerFactory.getInstance(getDefaultAlgorithm()),
            TrustManagerFactory.getInstance(getDefaultAlgorithm(), getDefaultProvider()),
            TrustManagerFactory.getInstance(getDefaultAlgorithm(), getDefaultProviderName())
        };
    }

    public void test_ConstructorLjavax_net_ssl_TrustManagerFactorySpiLjava_security_ProviderLjava_lang_String()
            throws NoSuchAlgorithmException {
        TrustManagerFactorySpi spi = new MyTrustManagerFactorySpi();
        TrustManagerFactory tmF = new myTrustManagerFactory(spi, getDefaultProvider(),
                getDefaultAlgorithm());
        assertTrue("Not CertStore object", tmF instanceof TrustManagerFactory);
        assertEquals("Incorrect algorithm", tmF.getAlgorithm(),
                getDefaultAlgorithm());
        assertEquals("Incorrect provider", tmF.getProvider(), getDefaultProvider());
        assertNull("Incorrect result", tmF.getTrustManagers());

        tmF = new myTrustManagerFactory(null, null, null);
        assertTrue("Not CertStore object", tmF instanceof TrustManagerFactory);
        assertNull("Provider must be null", tmF.getProvider());
        assertNull("Algorithm must be null", tmF.getAlgorithm());
        try {
            tmF.getTrustManagers();
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     *  Test for <code>getAlgorithm()</code> method
     * Assertion: returns the algorithm name of this object
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public void test_getAlgorithm()
        throws NoSuchAlgorithmException, NoSuchProviderException {
        assertEquals("Incorrect algorithm",
                getDefaultAlgorithm(),
                TrustManagerFactory
                .getInstance(getDefaultAlgorithm()).getAlgorithm());
        assertEquals("Incorrect algorithm",
                getDefaultAlgorithm(),
                TrustManagerFactory
                .getInstance(getDefaultAlgorithm(), getDefaultProviderName())
                .getAlgorithm());
        assertEquals("Incorrect algorithm",
                getDefaultAlgorithm(),
                TrustManagerFactory.getInstance(getDefaultAlgorithm(), getDefaultProvider())
                .getAlgorithm());
    }

    /**
     *  Test for <code>getDefaultAlgorithm()</code> method
     * Assertion: returns value which is specifoed in security property
     */
    public void test_getDefaultAlgorithm() {
        String def = TrustManagerFactory.getDefaultAlgorithm();
        if (getDefaultAlgorithm() == null) {
            assertNull("DefaultAlgorithm must be null", def);
        } else {
            assertEquals("Invalid default algorithm", def, getDefaultAlgorithm());
        }
        String defA = "Proba.trustmanagerfactory.defaul.type";
        Security.setProperty("ssl.TrustManagerFactory.algorithm", defA);
        assertEquals("Incorrect getDefaultAlgorithm()",
                TrustManagerFactory.getDefaultAlgorithm(), defA);
        if (def == null) {
            def = "";
        }
        Security.setProperty("ssl.TrustManagerFactory.algorithm", def);
        assertEquals("Incorrect getDefaultAlgorithm()",
                TrustManagerFactory.getDefaultAlgorithm(), def);
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions: returns security property "ssl.TrustManagerFactory.algorithm";
     * returns instance of TrustManagerFactory
     */
    public void test_getInstanceLjava_lang_String01() throws NoSuchAlgorithmException {
        for (String validValue : getValidValues()) {
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(validValue);
            assertTrue("Not TrustManagerFactory object",
                       trustMF instanceof TrustManagerFactory);
            assertEquals("Invalid algorithm", trustMF.getAlgorithm(), validValue);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     */
    public void test_getInstanceLjava_lang_String02() {
        try {
            TrustManagerFactory.getInstance(null);
            fail();
        } catch (NoSuchAlgorithmException expected) {
        } catch (NullPointerException expected) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException was not thrown as expected for algorithm: "
                        .concat(invalidValues[i]));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion: throws IllegalArgumentException when provider is null
     * or empty
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String01() throws Exception {
        for (String validValue : getValidValues()) {
            try {
                TrustManagerFactory.getInstance(validValue, (String) null);
                fail();
            } catch (IllegalArgumentException expected) {
            }
            try {
                TrustManagerFactory.getInstance(validValue, "");
                fail();
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String02() throws Exception {
        try {
            TrustManagerFactory.getInstance(null, getDefaultProviderName());
            fail();
        } catch (NoSuchAlgorithmException expected) {
        } catch (NullPointerException expected) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i],
                        getDefaultProviderName());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion: throws NoSuchProviderException when provider has
     * invalid value
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String03() throws Exception {
        for (String invalidValue : invalidValues) {
            for (String validValue : getValidValues()) {
                try {
                    TrustManagerFactory.getInstance(validValue, invalidValue);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(validValue).concat(" provider: ")
                            .concat(invalidValue).concat(")"));
                } catch (NoSuchProviderException expected) {
                    assertFalse("".equals(invalidValue));
                } catch (IllegalArgumentException expected) {
                    assertEquals("", invalidValue);
                }
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertion: returns instance of TrustManagerFactory
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String04() throws Exception {
        for (String validValue : getValidValues()) {
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(validValue,
                                                                          getDefaultProviderName());
            assertTrue("Not TrustManagerFactory object",
                       trustMF instanceof TrustManagerFactory);
            assertEquals("Invalid algorithm", trustMF.getAlgorithm(), validValue);
            assertEquals("Invalid provider", trustMF.getProvider(), getDefaultProvider());
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion: throws IllegalArgumentException when provider is null
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider01() throws Exception {
        for (String validValue : getValidValues()) {
            try {
                TrustManagerFactory.getInstance(validValue, (Provider) null);
            } catch (IllegalArgumentException expected) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is not correct;
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider02() {
        try {
            TrustManagerFactory.getInstance(null, getDefaultProvider());
            fail("");
        } catch (NoSuchAlgorithmException expected) {
        } catch (NullPointerException expected) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                TrustManagerFactory.getInstance(invalidValues[i],
                        getDefaultProvider());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertion: returns instance of TrustManagerFactory
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider03() throws Exception {
        for (String validValue : getValidValues()) {
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(validValue,
                                                                          getDefaultProvider());
            assertTrue("Not TrustManagerFactory object",
                       trustMF instanceof TrustManagerFactory);
            assertEquals("Invalid algorithm", trustMF.getAlgorithm(), validValue);
            assertEquals("Invalid provider", trustMF.getProvider(), getDefaultProvider());
        }
    }

    /**
     * Test for <code>getProvider()</code>
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public void test_getProvider()
        throws NoSuchAlgorithmException, NoSuchProviderException {
        assertEquals("Incorrect provider",
                getDefaultProvider(),
                TrustManagerFactory
                .getInstance(getDefaultAlgorithm()).getProvider());
        assertEquals("Incorrect provider",
                getDefaultProvider(),
                TrustManagerFactory
                .getInstance(getDefaultAlgorithm(), getDefaultProviderName())
                .getProvider());
        assertEquals("Incorrect provider",
                getDefaultProvider(),
                TrustManagerFactory.getInstance(getDefaultAlgorithm(), getDefaultProvider())
                .getProvider());
    }

    /**
     * Test for <code>geTrustManagers()</code>
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     */
    public void test_getTrustManagers() {
        try {
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            trustMF.init(ks);
            TrustManager[] tm = trustMF.getTrustManagers();
            assertNotNull("Result has not be null", tm);
            assertTrue("Length of result TrustManager array should not be 0",
                    (tm.length > 0));
        } catch (Exception ex) {
            fail("Unexpected exception " + ex.toString());
        }
    }

    /**
     * Test for <code>init(KeyStore keyStore)</code>
     * Assertion: call method with null parameter
     */
    public void test_initLjava_security_KeyStore_01() throws Exception {
        KeyStore ksNull = null;
        TrustManagerFactory[] trustMF = createTMFac();
        assertNotNull("TrustManagerFactory objects were not created", trustMF);
        // null parameter
        try {
            trustMF[0].init(ksNull);
        } catch (Exception ex) {
            fail(ex + " unexpected exception was thrown for null parameter");
        }
    }

    /**
     * Test for <code>init(KeyStore keyStore)</code>
     * Assertion: call method with not null parameter
     */
    public void test_initLjava_security_KeyStore_02() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        TrustManagerFactory[] trustMF = createTMFac();
        assertNotNull("TrustManagerFactory objects were not created", trustMF);

        // not null parameter
        trustMF[0].init(ks);
    }

    /**
     * Test for <code>init(ManagerFactoryParameters params)</code>
     * Assertion:
     * throws InvalidAlgorithmParameterException when params is null
     */
    @KnownFailure("ManagerFactoryParameters object is not supported "
                  + "and InvalidAlgorithmParameterException was thrown.")
    public void test_initLjavax_net_ssl_ManagerFactoryParameters() throws Exception {
        ManagerFactoryParameters par = null;
        TrustManagerFactory[] trustMF = createTMFac();
        assertNotNull("TrustManagerFactory objects were not created", trustMF);
        for (int i = 0; i < trustMF.length; i++) {
            try {
                trustMF[i].init(par);
                fail("InvalidAlgorithmParameterException must be thrown");
            } catch (InvalidAlgorithmParameterException e) {
            }
        }

        String keyAlg = "DSA";
        String validCaNameRfc2253 = ("CN=Test CA,"
                                     + "OU=Testing Division,"
                                     + "O=Test It All,"
                                     + "L=Test Town,"
                                     + "ST=Testifornia,"
                                     + "C=Testland");

        try {
            KeyStore kStore = KeyStore.getInstance(KeyStore.getDefaultType());
            kStore.load(null, null);
            PublicKey pk = new TestKeyPair(keyAlg).getPublic();
            TrustAnchor ta = new TrustAnchor(validCaNameRfc2253, pk, getFullEncoding());
            Set<TrustAnchor> trustAnchors = new HashSet<TrustAnchor>();
            trustAnchors.add(ta);
            X509CertSelector xcs = new X509CertSelector();
            PKIXBuilderParameters pkixBP = new PKIXBuilderParameters(trustAnchors, xcs);
            CertPathTrustManagerParameters cptmp = new CertPathTrustManagerParameters(pkixBP);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(getDefaultAlgorithm());
            try {
                tmf.init(cptmp);
            } catch (Exception ex) {
                fail(ex + " was thrown for init(ManagerFactoryParameters spec)");
            }
        } catch (Exception e) {
            fail("Unexpected exception for configuration: " + e);
        }

    }

    private static final byte[] getFullEncoding() {
        // DO NOT MODIFY!
        return new byte[] {
                (byte)0x30,(byte)0x81,(byte)0x8c,(byte)0xa0,
                (byte)0x44,(byte)0x30,(byte)0x16,(byte)0x86,
                (byte)0x0e,(byte)0x66,(byte)0x69,(byte)0x6c,
                (byte)0x65,(byte)0x3a,(byte)0x2f,(byte)0x2f,
                (byte)0x66,(byte)0x6f,(byte)0x6f,(byte)0x2e,
                (byte)0x63,(byte)0x6f,(byte)0x6d,(byte)0x80,
                (byte)0x01,(byte)0x00,(byte)0x81,(byte)0x01,
                (byte)0x01,(byte)0x30,(byte)0x16,(byte)0x86,
                (byte)0x0e,(byte)0x66,(byte)0x69,(byte)0x6c,
                (byte)0x65,(byte)0x3a,(byte)0x2f,(byte)0x2f,
                (byte)0x62,(byte)0x61,(byte)0x72,(byte)0x2e,
                (byte)0x63,(byte)0x6f,(byte)0x6d,(byte)0x80,
                (byte)0x01,(byte)0x00,(byte)0x81,(byte)0x01,
                (byte)0x01,(byte)0x30,(byte)0x12,(byte)0x86,
                (byte)0x0a,(byte)0x66,(byte)0x69,(byte)0x6c,
                (byte)0x65,(byte)0x3a,(byte)0x2f,(byte)0x2f,
                (byte)0x6d,(byte)0x75,(byte)0x75,(byte)0x80,
                (byte)0x01,(byte)0x00,(byte)0x81,(byte)0x01,
                (byte)0x01,(byte)0xa1,(byte)0x44,(byte)0x30,
                (byte)0x16,(byte)0x86,(byte)0x0e,(byte)0x68,
                (byte)0x74,(byte)0x74,(byte)0x70,(byte)0x3a,
                (byte)0x2f,(byte)0x2f,(byte)0x66,(byte)0x6f,
                (byte)0x6f,(byte)0x2e,(byte)0x63,(byte)0x6f,
                (byte)0x6d,(byte)0x80,(byte)0x01,(byte)0x00,
                (byte)0x81,(byte)0x01,(byte)0x01,(byte)0x30,
                (byte)0x16,(byte)0x86,(byte)0x0e,(byte)0x68,
                (byte)0x74,(byte)0x74,(byte)0x70,(byte)0x3a,
                (byte)0x2f,(byte)0x2f,(byte)0x62,(byte)0x61,
                (byte)0x72,(byte)0x2e,(byte)0x63,(byte)0x6f,
                (byte)0x6d,(byte)0x80,(byte)0x01,(byte)0x00,
                (byte)0x81,(byte)0x01,(byte)0x01,(byte)0x30,
                (byte)0x12,(byte)0x86,(byte)0x0a,(byte)0x68,
                (byte)0x74,(byte)0x74,(byte)0x70,(byte)0x3a,
                (byte)0x2f,(byte)0x2f,(byte)0x6d,(byte)0x75,
                (byte)0x75,(byte)0x80,(byte)0x01,(byte)0x00,
                (byte)0x81,(byte)0x01,(byte)0x01
        };
    }
}

/**
 * Addifional class to verify TrustManagerFactory constructor
 */

class myTrustManagerFactory extends TrustManagerFactory {
    public myTrustManagerFactory(TrustManagerFactorySpi spi, Provider prov,
            String alg) {
        super(spi, prov, alg);
    }
}

