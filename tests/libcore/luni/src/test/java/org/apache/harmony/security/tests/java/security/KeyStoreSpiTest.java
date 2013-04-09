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

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.MyKeyStoreSpi;
import org.apache.harmony.security.tests.support.MyLoadStoreParams;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.LoadStoreParameter;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.Date;

/**
 * Tests for <code>KeyStoreSpi</code> constructor and methods
 *
 */
public class KeyStoreSpiTest extends TestCase {

    @SuppressWarnings("cast")
    public void test_KeyStoreSpi() {

        try {
            MyKeyStoreSpi ksSpi = new MyKeyStoreSpi();
            assertNotNull(ksSpi);
            assertTrue(ksSpi instanceof KeyStoreSpi);
        } catch (Exception ex) {
            fail("Unexpected exception");
        }
    }

    /*
     * java.security.KeyStore.engineEntryInstanceOf(String, Class<?
     * extends Entry>)
     */
    public void test_engineEntryInstanceOf() throws Exception {

        KeyStoreSpi ksSpi = new MyKeyStoreSpi();

        assertTrue(ksSpi.engineEntryInstanceOf(
                "test_engineEntryInstanceOf_Alias1",
                KeyStore.PrivateKeyEntry.class));

        assertFalse(ksSpi.engineEntryInstanceOf(
                "test_engineEntryInstanceOf_Alias2",
                KeyStore.SecretKeyEntry.class));

        assertFalse(ksSpi.engineEntryInstanceOf(
                "test_engineEntryInstanceOf_Alias3",
                KeyStore.TrustedCertificateEntry.class));

        try {
            assertFalse(ksSpi.engineEntryInstanceOf(null,
                    KeyStore.TrustedCertificateEntry.class));
        } catch (NullPointerException e) {
            // ok
        }

        try {
            assertFalse(ksSpi.engineEntryInstanceOf(
                    "test_engineEntryInstanceOf_Alias1", null));
        } catch (NullPointerException e) {
            // ok
        }


    }

    public void testKeyStoteSpi01() throws IOException,
            NoSuchAlgorithmException, CertificateException,
            UnrecoverableEntryException, KeyStoreException {
        KeyStoreSpi ksSpi = new MyKeyStoreSpi();

        tmpEntry entry = new tmpEntry();
        tmpProtection pPar = new tmpProtection();

        try {
            ksSpi.engineStore(null);
        } catch (UnsupportedOperationException e) {
        }
        assertNull("Not null entry", ksSpi.engineGetEntry("aaa", null));
        assertNull("Not null entry", ksSpi.engineGetEntry(null, pPar));
        assertNull("Not null entry", ksSpi.engineGetEntry("aaa", pPar));

        try {
            ksSpi.engineSetEntry("", null, null);
            fail("KeyStoreException or NullPointerException must be thrown");
        } catch (KeyStoreException e) {
        } catch (NullPointerException e) {
        }

        try {
            ksSpi.engineSetEntry("", new KeyStore.TrustedCertificateEntry(
                    new MyCertificate("type", new byte[0])), null);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {
        }

        try {
            ksSpi.engineSetEntry("aaa", entry, null);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {
        }
    }

    /**
     * Test for <code>KeyStoreSpi()</code> constructor and abstract engine
     * methods. Assertion: creates new KeyStoreSpi object.
     */
    public void testKeyStoteSpi02() throws NoSuchAlgorithmException,
            UnrecoverableKeyException, CertificateException {
        KeyStoreSpi ksSpi = new MyKeyStoreSpi();
        assertNull("engineGetKey(..) must return null", ksSpi.engineGetKey("",
                new char[0]));
        assertNull("engineGetCertificateChain(..) must return null", ksSpi
                .engineGetCertificateChain(""));
        assertNull("engineGetCertificate(..) must return null", ksSpi
                .engineGetCertificate(""));
        assertEquals("engineGetCreationDate(..) must return Date(0)", new Date(
                0), ksSpi.engineGetCreationDate(""));
        try {
            ksSpi.engineSetKeyEntry("", null, new char[0], new Certificate[0]);
            fail("KeyStoreException must be thrown from engineSetKeyEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineSetKeyEntry("", new byte[0], new Certificate[0]);
            fail("KeyStoreException must be thrown from engineSetKeyEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineSetCertificateEntry("", null);
            fail("KeyStoreException must be thrown "
                    + "from engineSetCertificateEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineDeleteEntry("");
            fail("KeyStoreException must be thrown from engineDeleteEntry(..)");
        } catch (KeyStoreException e) {
        }
        assertNull("engineAliases() must return null", ksSpi.engineAliases());
        assertFalse("engineContainsAlias(..) must return false", ksSpi
                .engineContainsAlias(""));
        assertEquals("engineSize() must return 0", 0, ksSpi.engineSize());
        try {
            ksSpi.engineStore(null, null);
            fail("IOException must be thrown");
        } catch (IOException e) {
        }
    }

    /**
     * java.security.KeyStoreSpi#engineLoad(KeyStore.LoadStoreParameter)
     */
    public void test_engineLoadLjava_security_KeyStore_LoadStoreParameter()
            throws Exception {

        final String msg = "error";

        KeyStoreSpi ksSpi = new MyKeyStoreSpi() {
            public void engineLoad(InputStream stream, char[] password) {
                assertNull(stream);
                assertNull(password);
                throw new RuntimeException(msg);
            }
        };
        try {
            ksSpi.engineLoad(null);
            fail("Should throw exception");
        } catch (RuntimeException e) {
            assertSame(msg, e.getMessage());
        }

        // test: protection parameter is null
        try {
            ksSpi.engineLoad(new MyLoadStoreParams(null));
            fail("No expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }

        // test: protection parameter is not instanceof
        // PasswordProtection or CallbackHandlerProtection
        try {
            ksSpi.engineLoad(new MyLoadStoreParams(new tmpProtection()));
            fail("No expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }
}

/**
 * Additional class implements KeyStore.Entry interface
 */
class tmpEntry implements KeyStore.Entry {
}

class tmpProtection implements KeyStore.ProtectionParameter {
}

@SuppressWarnings("unused")
class MyCertificate extends Certificate {

    // MyCertificate encoding
    private final byte[] encoding;

    public MyCertificate(String type, byte[] encoding) {
        super(type);
        // don't copy to allow null parameter in test
        this.encoding = encoding;
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        // do copy to force NPE in test
        return encoding.clone();
    }

    public void verify(PublicKey key) throws CertificateException,
            NoSuchAlgorithmException, InvalidKeyException,
            NoSuchProviderException, SignatureException {
    }

    public void verify(PublicKey key, String sigProvider)
            throws CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchProviderException, SignatureException {
    }

    public String toString() {
        return "[My test Certificate, type: " + getType() + "]";
    }

    public PublicKey getPublicKey() {
        return new PublicKey() {
            public String getAlgorithm() {
                return "DSA";
            }

            public byte[] getEncoded() {
                return new byte[] {(byte) 1, (byte) 2, (byte) 3};
            }

            public String getFormat() {
                return "TEST_FORMAT";
            }
        };
    }
}
