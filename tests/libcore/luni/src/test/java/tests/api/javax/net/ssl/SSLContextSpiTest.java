/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.api.javax.net.ssl;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyManagementException;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;

import junit.framework.TestCase;

import org.apache.harmony.xnet.tests.support.SSLContextSpiImpl;

public class SSLContextSpiTest extends TestCase {

    /**
     * javax.net.ssl.SSLContextSpi#SSLContextSpi()
     */
    public void test_Constructor() {
        try {
            SSLContextSpiImpl ssl = new SSLContextSpiImpl();
            assertTrue(ssl instanceof SSLContextSpi);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * javax.net.ssl.SSLContextSpi#engineCreateSSLEngine()
     * Verify exception when SSLContextSpi object wasn't initialiazed.
     */
    public void test_engineCreateSSLEngine_01() {
        SSLContextSpiImpl ssl = new SSLContextSpiImpl();
        try {
            SSLEngine sleng = ssl.engineCreateSSLEngine();
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }
    }

    /**
     * javax.net.ssl.SSLContextSpi#engineCreateSSLEngine(String host, int port)
     * Verify exception when SSLContextSpi object wasn't initialiazed.
     */
    public void test_engineCreateSSLEngine_02() {
        int[] invalid_port = {Integer.MIN_VALUE, -65535, -1, 65536, Integer.MAX_VALUE};
        SSLContextSpiImpl ssl = new SSLContextSpiImpl();
        try {
            SSLEngine sleng = ssl.engineCreateSSLEngine("localhost", 1080);
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }

        for (int i = 0; i < invalid_port.length; i++) {
            try {
                SSLEngine sleng = ssl.engineCreateSSLEngine("localhost", invalid_port[i]);
                fail("IllegalArgumentException wasn't thrown");
            } catch (IllegalArgumentException iae) {
                //expected
            }
        }
    }

    /**
     * SSLContextSpi#engineGetClientSessionContext()
     * SSLContextSpi#engineGetServerSessionContext()
     * SSLContextSpi#engineGetServerSocketFactory()
     * SSLContextSpi#engineGetSocketFactory()
     * Verify exception when SSLContextSpi object wasn't initialiazed.
     */
    public void test_commonTest_01() {
        SSLContextSpiImpl ssl = new SSLContextSpiImpl();

        try {
            SSLSessionContext slsc = ssl.engineGetClientSessionContext();
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }

        try {
            SSLSessionContext slsc = ssl.engineGetServerSessionContext();
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }

        try {
            SSLServerSocketFactory sssf = ssl.engineGetServerSocketFactory();
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }

        try {
            SSLSocketFactory ssf = ssl.engineGetSocketFactory();
            fail("RuntimeException wasn't thrown");
        } catch (RuntimeException re) {
            String str = re.getMessage();
            if (!str.equals("Not initialiazed"))
                fail("Incorrect exception message: " + str);
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }
    }

    /**
     * SSLContextSpi#engineInit(KeyManager[] km, TrustManager[] tm, SecureRandom sr)
     */
    public void test_engineInit() {
        SSLContextSpiImpl ssl = new SSLContextSpiImpl();
        String defaultAlgorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(defaultAlgorithm);
            char[] pass = "password".toCharArray();
            kmf.init(null, pass);
            KeyManager[] km = kmf.getKeyManagers();
            defaultAlgorithm = Security.getProperty("ssl.TrustManagerFactory.algorithm");
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(defaultAlgorithm);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            trustMF.init(ks);
            TrustManager[] tm = trustMF.getTrustManagers();
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            try {
                ssl.engineInit(km, tm, sr);
            } catch (KeyManagementException kme) {
                fail(kme + " was throw for engineInit method");
            }
            try {
                ssl.engineInit(km, tm, null);
                fail("KeyManagementException wasn't thrown");
            } catch (KeyManagementException kme) {
                //expected
            }
        } catch (Exception ex) {
            fail(ex + " unexpected exception");
        }
    }

    /**
     * SSLContextSpi#engineCreateSSLEngine()
     * SSLContextSpi#engineCreateSSLEngine(String host, int port)
     * SSLContextSpi#engineGetClientSessionContext()
     * SSLContextSpi#engineGetServerSessionContext()
     * SSLContextSpi#engineGetServerSocketFactory()
     * SSLContextSpi#engineGetSocketFactory()
     */
    public void test_commonTest_02() {
        SSLContextSpiImpl ssl = new SSLContextSpiImpl();
        String defaultAlgorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(defaultAlgorithm);
            char[] pass = "password".toCharArray();
            kmf.init(null, pass);
            KeyManager[] km = kmf.getKeyManagers();
            defaultAlgorithm = Security.getProperty("ssl.TrustManagerFactory.algorithm");
            TrustManagerFactory trustMF = TrustManagerFactory.getInstance(defaultAlgorithm);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            trustMF.init(ks);
            TrustManager[] tm = trustMF.getTrustManagers();
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            ssl.engineInit(km, tm, sr);
        } catch (Exception ex) {
            fail(ex + " unexpected exception");
        }

        try {
            assertNotNull("Subtest_01: Object is NULL", ssl.engineCreateSSLEngine());
            SSLEngine sleng = ssl.engineCreateSSLEngine("localhost", 1080);
            assertNotNull("Subtest_02: Object is NULL", sleng);
            assertEquals(sleng.getPeerPort(), 1080);
            assertEquals(sleng.getPeerHost(), "localhost");
            assertNull("Subtest_03: Object not NULL", ssl.engineGetClientSessionContext());
            assertNull("Subtest_04: Object not NULL", ssl.engineGetServerSessionContext());
            assertNull("Subtest_05: Object not NULL", ssl.engineGetServerSocketFactory());
            assertNull("Subtest_06: Object not NULL", ssl.engineGetSocketFactory());
        } catch (Exception e) {
            fail("Unexpected exception " + e);
        }
    }

}
