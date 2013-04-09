/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.java.security.AlgorithmParametersTest.MyAlgorithmParameters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Date;
import java.util.Enumeration;

public class KeyStore3Test extends TestCase {

    private KeyStore mockKeyStore;

    private KeyPair keyPair;

    private Certificate certificate;

    public KeyStore3Test() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPair = keyPairGenerator.generateKeyPair();

        String certificateData = "-----BEGIN CERTIFICATE-----\n"
                + "MIICZTCCAdICBQL3AAC2MA0GCSqGSIb3DQEBAgUAMF8xCzAJBgNVBAYTAlVTMSAw\n"
                + "HgYDVQQKExdSU0EgRGF0YSBTZWN1cml0eSwgSW5jLjEuMCwGA1UECxMlU2VjdXJl\n"
                + "IFNlcnZlciBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAeFw05NzAyMjAwMDAwMDBa\n"
                + "Fw05ODAyMjAyMzU5NTlaMIGWMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZv\n"
                + "cm5pYTESMBAGA1UEBxMJUGFsbyBBbHRvMR8wHQYDVQQKExZTdW4gTWljcm9zeXN0\n"
                + "ZW1zLCBJbmMuMSEwHwYDVQQLExhUZXN0IGFuZCBFdmFsdWF0aW9uIE9ubHkxGjAY\n"
                + "BgNVBAMTEWFyZ29uLmVuZy5zdW4uY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCB\n"
                + "iQKBgQCofmdY+PiUWN01FOzEewf+GaG+lFf132UpzATmYJkA4AEA/juW7jSi+LJk\n"
                + "wJKi5GO4RyZoyimAL/5yIWDV6l1KlvxyKslr0REhMBaD/3Z3EsLTTEf5gVrQS6sT\n"
                + "WMoSZAyzB39kFfsB6oUXNtV8+UKKxSxKbxvhQn267PeCz5VX2QIDAQABMA0GCSqG\n"
                + "SIb3DQEBAgUAA34AXl3at6luiV/7I9MN5CXYoPJYI8Bcdc1hBagJvTMcmlqL2uOZ\n"
                + "H9T5hNMEL9Tk6aI7yZPXcw/xI2K6pOR/FrMp0UwJmdxX7ljV6ZtUZf7pY492UqwC\n"
                + "1777XQ9UEZyrKJvF5ntleeO0ayBqLGVKCWzWZX9YsXCpv47FNLZbupE=\n"
                + "-----END CERTIFICATE-----\n";

        ByteArrayInputStream certArray = new ByteArrayInputStream(
                certificateData.getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        certificate = cf.generateCertificate(certArray);
    }

    public void test_load() throws Exception {
        // No exception should be thrown out.
        mockKeyStore.load(null);
    }

    public void test_store() throws Exception {
        try {
            mockKeyStore.store(null);
            fail("should throw KeyStoreException: not initialized");
        } catch (KeyStoreException e) {
            // expected
        }

        // No exception should be thrown out.
        mockKeyStore.load(null, null);
        mockKeyStore.store(null);
    }

    public void test_setKeyEntry_null() throws Exception {
        mockKeyStore.load(null, null);
        // No exception should be thrown out.
        mockKeyStore.setKeyEntry(null, null, null, null);
    }

    public void test_setKeyEntry_key_is_null() throws Exception {
        mockKeyStore.load(null, null);
        // No exception should be thrown out.
        mockKeyStore.setKeyEntry("Alias", null, null, new Certificate[]{certificate});
    }

    public void test_setKeyEntry_key_is_private() throws Exception {
        mockKeyStore.load(null, null);
        Key key = keyPair.getPrivate();
        try {
            mockKeyStore.setKeyEntry("Alias", key, null, null);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            mockKeyStore.setKeyEntry("Alias", key, null,
                    new Certificate[0]);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        mockKeyStore.setKeyEntry("Alias", key, null, new Certificate[]{certificate});
    }

    public void test_setKeyEntry_key_is_public() throws Exception
    {
        mockKeyStore.load(null, null);
        Key key = keyPair.getPublic();
        mockKeyStore.setKeyEntry("Alias1", key, null, null);
        mockKeyStore.setKeyEntry("Alias2", key, null,
                new Certificate[0]);
        mockKeyStore.setKeyEntry("Alias3", key, null, new Certificate[]{certificate});
    }

    public void test_setCertificateEntry_null() throws Exception {
        mockKeyStore.load(null, null);

        mockKeyStore.setCertificateEntry(null, null);

        mockKeyStore.setCertificateEntry(null, certificate);

        mockKeyStore.setCertificateEntry("Alias", null);
    }

    @SuppressWarnings("cast")
    public void test_KeyStore() {
        Provider p = new MyProvider();
        try {
            MyKeyStore ks = new MyKeyStore(new MyKeyStoreSpi(), p, "MyKeyStore");
            assertNotNull(ks);
            assertTrue(ks instanceof KeyStore);
        } catch (Exception e) {
            fail("Exception should be not thrown");
        }

        try {
            MyKeyStore ks = new MyKeyStore(null, null, null);
            assertNotNull(ks);
            assertTrue(ks instanceof KeyStore);
        } catch (Exception e) {
            fail("Exception should be not thrown");
        }

    }

    protected void setUp() throws Exception {
        super.setUp();
        mockKeyStore = new MyKeyStore(new MyKeyStoreSpi(), null, "MyKeyStore");
    }

    private static class MyKeyStore extends KeyStore {

        public MyKeyStore(KeyStoreSpi keyStoreSpi, Provider provider,
                String type) {
            super(keyStoreSpi, provider, type);
        }
    }

    @SuppressWarnings("unused")
    private static class MyKeyStoreSpi extends KeyStoreSpi {

        public Enumeration<String> engineAliases() {
            return null;
        }

        public boolean engineContainsAlias(String arg0) {
            return false;
        }

        public void engineDeleteEntry(String arg0) throws KeyStoreException {
        }

        public Certificate engineGetCertificate(String arg0) {
            return null;
        }

        public String engineGetCertificateAlias(Certificate arg0) {
            return null;
        }

        public Certificate[] engineGetCertificateChain(String arg0) {
            return null;
        }

        public Date engineGetCreationDate(String arg0) {
            return null;
        }

        public Key engineGetKey(String arg0, char[] arg1)
                throws NoSuchAlgorithmException, UnrecoverableKeyException {
            return null;
        }

        public boolean engineIsCertificateEntry(String arg0) {
            return false;
        }

        public boolean engineIsKeyEntry(String arg0) {
            return false;
        }

        public void engineLoad(InputStream arg0, char[] arg1)
                throws IOException, NoSuchAlgorithmException,
                CertificateException {
            return;
        }

        public void engineSetCertificateEntry(String arg0, Certificate arg1)
                throws KeyStoreException {
            return;
        }

        public void engineSetKeyEntry(String arg0, byte[] arg1,
                Certificate[] arg2) throws KeyStoreException {
            return;
        }

        public void engineSetKeyEntry(String arg0, Key arg1, char[] arg2,
                Certificate[] arg3) throws KeyStoreException {
            return;
        }

        public int engineSize() {
            return 0;
        }

        public void engineStore(KeyStore.LoadStoreParameter param){
            return;
        }

        public void engineStore(OutputStream arg0, char[] arg1)
                throws IOException, NoSuchAlgorithmException,
                CertificateException {
            return;
        }
    }

    @SuppressWarnings("serial")
    private class MyProvider extends Provider {
        MyProvider() {
            super("MyProvider", 1.0, "Provider for testing");
            put("AlgorithmParameters.ABC", MyAlgorithmParameters.class
                    .getName());
        }

        MyProvider(String name, double version, String info) {
            super(name, version, info);
        }
    }

}
