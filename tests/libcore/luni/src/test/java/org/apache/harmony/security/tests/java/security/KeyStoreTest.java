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

package org.apache.harmony.security.tests.java.security;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.Security;
import java.security.SignatureException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;
import java.math.BigInteger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.harmony.security.tests.support.KeyStoreTestSupport;
import org.apache.harmony.security.tests.support.MyLoadStoreParams;
import org.apache.harmony.security.tests.support.SpiEngUtils;

import junit.framework.TestCase;

/**
 * Tests for <code>KeyStore</code> constructor and methods
 *
 */
public class KeyStoreTest extends TestCase {

    private static final String KeyStoreProviderClass = "org.apache.harmony.security.tests.support.MyKeyStore";

    private static final String defaultType = "KeyStore";

    public static boolean KSSupported = false;

    public static String defaultProviderName = null;

    public static Provider defaultProvider = null;

    private static String NotSupportMsg = "Default KeyStore type is not supported";

    Provider mProv;

    public KeyStore[] createKS() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);
        KeyStore[] kpg = new KeyStore[3];

        kpg[0] = KeyStore.getInstance(defaultType);
        kpg[1] = KeyStore.getInstance(defaultType, defaultProvider);
        kpg[2] = KeyStore.getInstance(defaultType, defaultProviderName);
        return kpg;
    }

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyKSProvider",
                "Testing provider", KeyStoreTestSupport.srvKeyStore.concat(".")
                        .concat(defaultType), KeyStoreProviderClass);
        Security.insertProviderAt(mProv, 2);
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                KeyStoreTestSupport.srvKeyStore);
        KSSupported = (defaultProvider != null);
        defaultProviderName = (KSSupported ? defaultProvider.getName() : null);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    /**
     * Test for <code>load(LoadStoreParameter param)</code>
     * <code>store(LoadStoreParameter param)</code>
     * methods
     * Assertions: throw IllegalArgumentException if param is null;
     */
    public void testLoadStore02() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);

        KeyStore[] kss = createKS();
        assertNotNull("KeyStore objects were not created", kss);

        for (int i = 0; i < kss.length; i++) {
            try {
                kss[i].load(null);
                fail("IOException or IllegalArgumentException should be thrown for null parameter");
            } catch (IOException e) {
            } catch (IllegalArgumentException e) {
            }
            kss[i].load(null, null);
            try {
                kss[i].store(null);
                fail("IOException or IllegalArgumentException should be thrown for null parameter");
            } catch (IOException e) {
            } catch (IllegalArgumentException e) {
            }
        }
        KeyStore.LoadStoreParameter lParam = new MyLoadStoreParams(
                new KeyStore.PasswordProtection(new char[0]));
        for (int i = 0; i < kss.length; i++) {
            kss[i].load(lParam);
            assertEquals("Incorrect result", kss[i].size(), 0);
            kss[i].store(lParam);
        }
    }


    /**
     * Test for <code>setKeyEntry(String alias, byte[] key, Certificate[] chain)</code>
     * method
     * Assertion: stores KeyEntry.
     */
    public void testSetKeyEntry() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);

        KeyStore[] kss = createKS();
        assertNotNull("KeyStore objects were not created", kss);
        byte[] kk = { (byte) 1, (byte) 2, (byte) 127, (byte) 77 };
        String alias = "keyEntry";
        char[] pwd = new char[0];
        byte[] res;
        Certificate certs[] = {
                new KeyStoreTestSupport.MCertificate(alias, kk),
                new KeyStoreTestSupport.MCertificate(alias, kk) };
        for (int i = 0; i < kss.length; i++) {
            kss[i].load(null, null);
            try {
                kss[i].setKeyEntry("proba", null, null);
                fail("KeyStoreException must be thrown");
            } catch (KeyStoreException e) {
            }
            kss[i].setKeyEntry(alias, kk, certs);
            res = kss[i].getKey(alias, pwd).getEncoded();
            assertEquals(kk.length, res.length);
            for (int j = 0; j < res.length; j++) {
                assertEquals(res[j], kk[j]);
            }
            assertEquals(kss[i].getCertificateChain(alias).length, certs.length);
            kss[i].setKeyEntry(alias, kk, null);
            res = kss[i].getKey(alias, pwd).getEncoded();
            assertEquals(kk.length, res.length);
            for (int j = 0; j < res.length; j++) {
                assertEquals(res[j], kk[j]);
            }
            assertNull(kss[i].getCertificateChain(alias));
        }
    }

    /**
     * Test for <code>getDefaultType()</code> method Assertion: returns
     * default security key store type or "jks" string
     */
    public void testKeyStore01() {
        String propName = "keystore.type";
        String defKSType = Security.getProperty(propName);
        String dType = KeyStore.getDefaultType();
        String resType = defKSType;
        if (resType == null) {
            resType = defaultType;
        }
        assertNotNull("Default type have not be null", dType);
        assertEquals("Incorrect default type", dType, resType);

        if (defKSType == null) {
            Security.setProperty(propName, defaultType);
            dType = KeyStore.getDefaultType();
            resType = Security.getProperty(propName);
            assertNotNull("Incorrect default type", resType);
            assertNotNull("Default type have not be null", dType);
            assertEquals("Incorrect default type", dType, resType);
        }
    }

    /**
     * Test for <code>getInstance(String type)</code> method
     * Assertion:
     * throws NullPointerException when type is null
     * throws KeyStoreException when type is not available
     *
     */
    public void testKeyStore02() throws KeyStoreException {
        String[] invalidValues =  SpiEngUtils.invalidValues;
        try {
            KeyStore.getInstance(null);
            fail("NullPointerException must be thrown when type is null");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyStore.getInstance(invalidValues[i]);
                fail("KeyStoreException must be thrown (type: ".concat(
                        invalidValues[i]).concat(" )"));
            } catch (KeyStoreException e) {
            }
        }
    }

    /**
     * @test java.security.KeyStore.PasswordProtection.getPassword()
     */
    public void testKeyStorePPGetPassword() {
        // Regression for HARMONY-1539
        // no exception expected
        assertNull(new KeyStore.PasswordProtection(null).getPassword());
        char[] password = new char[] {'a', 'b', 'c'};
        KeyStore.PasswordProtection pp = new KeyStore.PasswordProtection(password);
        assertNotSame(pp.getPassword(), password);
        assertSame(pp.getPassword(), pp.getPassword());

    }


    /*
     * java.security.KeyStoreSpi.engineEntryInstanceOf(String, Class<? extends Entry>)
     */
    public void testEngineEntryInstanceOf() throws Exception {
        //Regression for HARMONY-615

        // create a KeyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, "pwd".toCharArray());

        // generate a key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();

        // put the key into keystore
        String alias = "alias";
        keyStore.setKeyEntry(alias, secretKey, "pwd".toCharArray(), null);

        // check if it is a secret key
        assertTrue(keyStore.entryInstanceOf(alias,
                KeyStore.SecretKeyEntry.class));

        // check if it is NOT a private key
        assertFalse(keyStore.entryInstanceOf(alias,
                KeyStore.PrivateKeyEntry.class));
    }

    /**
     * java.security.KeyStore.TrustedCertificateEntry.toString()
     */
    public void testKeyStoreTCToString() {
           // Regression for HARMONY-1542
           // no exception expected
        class TestX509Certificate extends X509Certificate {
            private static final long serialVersionUID = 1L;
            public void checkValidity() throws CertificateExpiredException,CertificateNotYetValidException {}
            public void checkValidity(Date p) throws CertificateExpiredException, CertificateNotYetValidException {}
            public int getVersion() {
                    return 0;
            }
            public BigInteger getSerialNumber() {
                    return null;
            }
            public Principal getIssuerDN() {
                    return null;
            }
            public Principal getSubjectDN() {
                    return null;
            }
            public Date getNotBefore() {
                    return null;
            }
            public Date getNotAfter() {
                    return null;
            }
            public byte[] getTBSCertificate() throws CertificateEncodingException {
                    return null;
            }
            public byte[] getSignature() {
                    return null;
            }
            public String getSigAlgName() {
                    return null;
            }
            public String getSigAlgOID() {
                    return null;
            }
            public byte[] getSigAlgParams() {
                    return null;
            }
            public boolean[] getIssuerUniqueID() {
                    return null;
            }
            public boolean[] getSubjectUniqueID() {
                    return null;
            }
            public boolean[] getKeyUsage() {
                    return null;
            }
            public int getBasicConstraints() {
                    return 0;
            }
            public byte[] getEncoded() throws CertificateEncodingException {
                    return null;
            }
            public void verify(PublicKey p)
                    throws CertificateException,
                    NoSuchAlgorithmException,
                    InvalidKeyException,
                    NoSuchProviderException,
                    SignatureException
            {}
            public void verify(PublicKey p0, String p1)
                    throws CertificateException,
                    NoSuchAlgorithmException,
                    InvalidKeyException,
                    NoSuchProviderException,
                    SignatureException
            {}
            public String toString() {
                    return null;
            }
            public PublicKey getPublicKey() {
                    return null;
            }
            public boolean hasUnsupportedCriticalExtension() {
                    return false;
            }
            public Set getCriticalExtensionOIDs() {
                    return null;
            }
            public Set getNonCriticalExtensionOIDs() {
                    return null;
            }
            public byte[] getExtensionValue(String p) {
                    return null;
            }
        }
        assertNotNull(new KeyStore.TrustedCertificateEntry(new TestX509Certificate()).toString());
    }
}
