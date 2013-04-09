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

import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.xnet.tests.support.MySSLContextSpi;

/**
 * Tests for <code>SSLContext</code> class constructors and methods.
 *
 */
public class SSLContext1Test extends TestCase {

    private static String srvSSLContext = "SSLContext";
    public static String defaultProtocol = "TLS";
    private static final String NotSupportMsg = "Default protocol is not supported";
    private static String defaultProviderName = null;
    private static Provider defaultProvider = null;
    private static final String[] invalidValues = SpiEngUtils.invalidValues;
    private static boolean DEFSupported = false;
    private static String[] validValues = new String[3];
    static {
        defaultProvider = SpiEngUtils.isSupport(defaultProtocol, srvSSLContext);
        DEFSupported = (defaultProvider != null);
        if (DEFSupported) {
            defaultProviderName = (DEFSupported ? defaultProvider.getName()
                    : null);
            validValues[0] = defaultProtocol;
            validValues[1] = defaultProtocol.toUpperCase();
            validValues[2] = defaultProtocol.toLowerCase();
        } else {
            defaultProtocol = null;
        }
    }

    protected SSLContext[] createSSLCon() {
        if (!DEFSupported) {
            fail(defaultProtocol + " protocol is not supported");
            return null;
        }
        SSLContext[] sslC = new SSLContext[3];
        try {
            sslC[0] = SSLContext.getInstance(defaultProtocol);
            sslC[1] = SSLContext.getInstance(defaultProtocol, defaultProvider);
            sslC[2] = SSLContext.getInstance(defaultProtocol,
                    defaultProviderName);
            return sslC;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Test for <code>SSLContext</code> constructor Assertion: returns
     * SSLContext object
     */
    public void test_ConstructorLjavax_net_ssl_SSLContextSpiLjava_security_ProviderLjava_lang_String()
        throws NoSuchAlgorithmException,
            KeyManagementException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContextSpi spi = new MySSLContextSpi();
        SSLContext sslContext = new MySslContext(spi, defaultProvider,
                defaultProtocol);
        assertEquals("Incorrect protocol", defaultProtocol,
                sslContext.getProtocol());
        assertEquals("Incorrect provider", defaultProvider,
                sslContext.getProvider());
        TrustManager[] tm = null;
        KeyManager[] km = null;
        sslContext.init(km, tm, new SecureRandom());
        assertNotNull("No SSLEngine created",
                sslContext.createSSLEngine());
        assertNotNull("No SSLEngine created",
                sslContext.createSSLEngine("host", 8888));
        try {
            sslContext.init(km, tm, null);
            fail("KeyManagementException should be thrown for null "
                    + "SecureRandom");
        } catch (KeyManagementException e) {
        }

        sslContext = new MySslContext(null, null, null);
        assertNull("Incorrect protocol", sslContext.getProtocol());
        assertNull("Incorrect provider", sslContext.getProvider());
        try {
            sslContext.createSSLEngine();
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
        }
        try {
            sslContext.getSocketFactory();
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     * @throws KeyManagementException
     * javax.net.ssl.SSLContext#createSSLEngine()
     */
    public void test_createSSLEngine() throws KeyManagementException {
        if (!DEFSupported) fail(NotSupportMsg);
        SSLContextSpi spi = new MySSLContextSpi();
        SSLContext sslContext = new MySslContext(spi, defaultProvider,
                defaultProtocol);
        sslContext.init(null, null, new SecureRandom());
        SSLEngine sslEngine = sslContext.createSSLEngine();
        assertNotNull("SSL engine is null", sslEngine);
    }

    /**
     * @throws KeyManagementException
     * javax.net.ssl.SSLContext#createSSLEngine(java.lang.String, int)
     */
    public void test_createSSLEngineLjava_lang_StringI()
        throws KeyManagementException {
        if (!DEFSupported) fail(NotSupportMsg);
        SSLContextSpi spi = new MySSLContextSpi();
        SSLContext sslContext = new MySslContext(spi, defaultProvider,
                defaultProtocol);
        sslContext.init(null, null, new SecureRandom());
        SSLEngine sslEngine = sslContext.createSSLEngine("www.fortify.net", 80);
        assertNotNull("SSL engine is null", sslEngine);
    }

    /**
     * Test for <code>getClientSessionContext()</code>
     * <code>getServiceSessionContext()</code>
     * methods Assertion: returns correspondent object
     * @throws KeyManagementException
     */
    public void test_getClientSessionContext() throws NoSuchAlgorithmException, KeyManagementException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContext[] sslC = createSSLCon();
        assertNotNull("SSLContext objects were not created", sslC);
        for (int i = 0; i < sslC.length; i++) {
            sslC[i].init(null, null, null);
            assertNotNull("Client session is incorrectly instantiated: " + i,
                    sslC[i].getClientSessionContext());
            assertNotNull("Server session is incorrectly instantiated: " + i,
                    sslC[i].getServerSessionContext());
        }
    }

    /**
     * Test for <code>getInstance(String protocol)</code> method Assertion:
     * returns SSLContext object
     */
    public void test_getInstanceLjava_lang_String01()
            throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContext sslContext;
        for (int i = 0; i < validValues.length; i++) {
            sslContext = SSLContext.getInstance(validValues[i]);
            assertNotNull("No SSLContext created", sslContext);
            assertEquals("Invalid protocol", validValues[i],
                    sslContext.getProtocol());
        }
    }

    /**
     * Test for <code>getInstance(String protocol)</code> method Assertion:
     * throws NullPointerException when protocol is null; throws
     * NoSuchAlgorithmException when protocol is not correct;
     */
    public void test_getInstanceLjava_lang_String02() {
        try {
            SSLContext.getInstance(null);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (protocol is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SSLContext.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException was not thrown as expected for provider: "
                        .concat(invalidValues[i]));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, String provider)</code>
     * method Assertion: throws IllegalArgumentException when provider is null
     * or empty
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String01() throws NoSuchProviderException,
            NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        String provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                SSLContext.getInstance(defaultProtocol, provider);
                fail("IllegalArgumentException must be thrown when provider is null");
            } catch (IllegalArgumentException e) {
            }
            try {
                SSLContext.getInstance(defaultProtocol, "");
                fail("IllegalArgumentException must be thrown when provider is empty");
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, String provider)</code>
     * method Assertion: throws NullPointerException when protocol is null;
     * throws NoSuchAlgorithmException when protocol is not correct;
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String02() throws NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            SSLContext.getInstance(null, defaultProviderName);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (protocol is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SSLContext.getInstance(invalidValues[i], defaultProviderName);
                fail("NoSuchAlgorithmException was not thrown as expected (protocol: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, String provider)</code>
     * method Assertion: throws NoSuchProviderException when provider has
     * invalid value
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String03() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 1; i < invalidValues.length; i++) {
            for (int j = 0; j < validValues.length; j++) {
                try {
                    SSLContext.getInstance(validValues[j], invalidValues[i]);
                    fail("NuSuchProviderException must be thrown (protocol: "
                            .concat(validValues[j]).concat(" provider: ")
                            .concat(invalidValues[i]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, String provider)</code>
     * method Assertion: returns instance of SSLContext
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String04() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContext sslContext;
        for (int i = 0; i < validValues.length; i++) {
            sslContext = SSLContext.getInstance(validValues[i],
                    defaultProviderName);
            assertNotNull("Not SSLContext created", sslContext);
            assertEquals("Invalid protocol",
                    validValues[i], sslContext.getProtocol());
            assertEquals("Invalid provider",
                    defaultProvider, sslContext.getProvider());
        }
    }

    /**
     * Test for <code>getInstance(String protocol, Provider provider)</code>
     * method Assertion: throws IllegalArgumentException when provider is null
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider01() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        Provider provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                SSLContext.getInstance(validValues[i], provider);
                fail("IllegalArgumentException must be thrown when provider is null");
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, Provider provider)</code>
     * method Assertion: throws NullPointerException when protocol is null;
     * throws NoSuchAlgorithmException when protocol is not correct;
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider02() {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            SSLContext.getInstance(null, defaultProvider);
            fail("NoSuchAlgorithmException or NullPointerException should be thrown (protocol is null");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                SSLContext.getInstance(invalidValues[i], defaultProvider);
                fail("Expected NoSuchAlgorithmException was not thrown as expected");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String protocol, Provider provider)</code>
     * method Assertion: returns instance of SSLContext
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider03() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContext sslContext;
        for (int i = 0; i < validValues.length; i++) {
            sslContext = SSLContext
                    .getInstance(validValues[i], defaultProvider);
            assertNotNull("Not SSLContext created", sslContext);
            assertEquals("Invalid protocol", validValues[i], sslContext.getProtocol());
            assertEquals("Invalid provider", defaultProvider, sslContext.getProvider());
        }
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * javax.net.ssl.SSLContext#getProtocol()
     */
    public void test_getProtocol()
        throws NoSuchAlgorithmException, NoSuchProviderException {
        if (!DEFSupported) fail(NotSupportMsg);
        SSLContextSpi spi = new MySSLContextSpi();
        SSLContext sslContext = new MySslContext(spi, defaultProvider,
                defaultProtocol);
        assertEquals("Incorrect protocol",
                defaultProtocol, sslContext.getProtocol());
        sslContext = new MySslContext(spi, defaultProvider,
                null);
        assertNull("Incorrect protocol", sslContext.getProtocol());
        sslContext = SSLContext.getInstance(defaultProtocol);
        assertEquals("Incorrect protocol",
                defaultProtocol, sslContext.getProtocol());
        sslContext = SSLContext.getInstance(defaultProtocol, defaultProvider);
        assertEquals("Incorrect protocol",
                defaultProtocol, sslContext.getProtocol());
        sslContext = SSLContext.getInstance(defaultProtocol, defaultProviderName);
        assertEquals("Incorrect protocol",
                defaultProtocol, sslContext.getProtocol());
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * javax.net.ssl.SSLContext#getProvider()
     */
    public void test_getProvider()
        throws NoSuchAlgorithmException, NoSuchProviderException {
        if (!DEFSupported) fail(NotSupportMsg);
        SSLContextSpi spi = new MySSLContextSpi();
        SSLContext sslContext = new MySslContext(spi, defaultProvider,
                defaultProtocol);
        assertEquals("Incorrect provider",
                defaultProvider, sslContext.getProvider());
        sslContext = SSLContext.getInstance(defaultProtocol, defaultProvider);
        assertEquals("Incorrect provider",
                defaultProvider, sslContext.getProvider());
        sslContext = SSLContext.getInstance(defaultProtocol, defaultProviderName);
        assertEquals("Incorrect provider",
                defaultProvider, sslContext.getProvider());
    }

    /**
     * javax.net.ssl.SSLContext#getServletSessionContext()
     */
    public void test_getServerSessionContext() throws NoSuchAlgorithmException,
        KeyManagementException, KeyStoreException,
        UnrecoverableKeyException {
        if (!DEFSupported) fail(NotSupportMsg);
        SSLContext[] sslC = createSSLCon();
        assertNotNull("SSLContext objects were not created", sslC);
        String tAlg = TrustManagerFactory.getDefaultAlgorithm();
        String kAlg = KeyManagerFactory.getDefaultAlgorithm();
        if (tAlg == null)
            fail("TrustManagerFactory default algorithm is not defined");
        if (kAlg == null)
            fail("KeyManagerFactory default algorithm is not defined");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(kAlg);
        kmf.init(null, new char[11]);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tAlg);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        tmf.init(ks);
        TrustManager[] tms = tmf.getTrustManagers();
        for (SSLContext sslCi : sslC) {
            sslCi.init(kmf.getKeyManagers(), tms, new SecureRandom());
            assertNotNull("Server context is incorrectly instantiated", sslCi
                    .getServerSessionContext());
        }
    }

    /**
     * Test for <code>getServerSocketFactory()</code>
     * <code>getSocketFactory()</code>
     * <code>init(KeyManager[] km, TrustManager[] tm, SecureRandom random)</code>
     * methods Assertion: returns correspondent object
     *
     */
     public void test_getServerSocketFactory() throws NoSuchAlgorithmException,
            KeyManagementException, KeyStoreException,
            UnrecoverableKeyException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        SSLContext[] sslC = createSSLCon();
        assertNotNull("SSLContext objects were not created", sslC);
        String tAlg = TrustManagerFactory.getDefaultAlgorithm();
        String kAlg = KeyManagerFactory.getDefaultAlgorithm();
        if (tAlg == null) {
            fail("TrustManagerFactory default algorithm is not defined");
            return;
        }
        if (kAlg == null) {
            fail("KeyManagerFactory default algorithm is not defined");
            return;
        }
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(kAlg);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            ks.load(null, null);
        } catch (Exception e) {
            fail(e + " was thrown for method load(null, null)");
        }
        kmf.init(ks, new char[10]);
        KeyManager[] kms = kmf.getKeyManagers();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tAlg);
        tmf.init(ks);
        TrustManager[] tms = tmf.getTrustManagers();
        for (int i = 0; i < sslC.length; i++) {
            sslC[i].init(kms, tms, new SecureRandom());
            assertNotNull("No SSLServerSocketFactory available",
                    sslC[i].getServerSocketFactory());
            assertNotNull("No SSLSocketFactory available",
                    sslC[i].getSocketFactory());
        }
    }

     /**
      * javax.net.ssl.SSLContext#getSocketFactory()
      */
     public void test_getSocketFactory() throws NoSuchAlgorithmException,
         KeyManagementException, KeyStoreException,
         UnrecoverableKeyException {
         if (!DEFSupported) fail(NotSupportMsg);
         SSLContext[] sslC = createSSLCon();
         assertNotNull("SSLContext objects were not created", sslC);
         String tAlg = TrustManagerFactory.getDefaultAlgorithm();
         String kAlg = KeyManagerFactory.getDefaultAlgorithm();
         if (tAlg == null)
             fail("TrustManagerFactory default algorithm is not defined");
         if (kAlg == null)
             fail("KeyManagerFactory default algorithm is not defined");
         KeyManagerFactory kmf = KeyManagerFactory.getInstance(kAlg);
         kmf.init(null, new char[11]);
         TrustManagerFactory tmf = TrustManagerFactory.getInstance(tAlg);
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         tmf.init(ks);
         TrustManager[] tms = tmf.getTrustManagers();
         for (SSLContext sslCi : sslC) {
             sslCi.init(kmf.getKeyManagers(), tms, new SecureRandom());
             assertNotNull("Socket factory is incorrectly instantiated",
                     sslCi.getSocketFactory());
         }
     }

     /**
      * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws FileNotFoundException
     * @throws KeyManagementException
     * javax.net.ssl.SSLContext#
      *     init(javax.net.ssl.KeyManager[], javax.net.ssl.TrustManager[],
      *     java.security.SecureRandom)
      */
     public void test_init$Ljavax_net_ssl_KeyManager$Ljavax_net_ssl_TrustManagerLjava_security_SecureRandom()
         throws Exception {
         if (!DEFSupported) fail(NotSupportMsg);
         SSLContextSpi spi = new MySSLContextSpi();
         SSLContext sslContext = new MySslContext(spi, defaultProvider,
                 defaultProtocol);
         try {
             sslContext.createSSLEngine();
             fail("Expected RuntimeException was not thrown");
         } catch (RuntimeException rte) {
             // expected
         }

         try {
             sslContext.init(null, null, null);
             fail("KeyManagementException wasn't thrown");
         } catch (KeyManagementException kme) {
             //expected
         }

         try {
             String tAlg = TrustManagerFactory.getDefaultAlgorithm();
             String kAlg = KeyManagerFactory.getDefaultAlgorithm();
             if (tAlg == null)
                 fail("TrustManagerFactory default algorithm is not defined");
             if (kAlg == null)
                 fail("KeyManagerFactory default algorithm is not defined");
             KeyManagerFactory kmf = KeyManagerFactory.getInstance(kAlg);
             kmf.init(null, new char[11]);
             TrustManagerFactory tmf = TrustManagerFactory.getInstance(tAlg);
             KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
             tmf.init(ks);
             TrustManager[] tms = tmf.getTrustManagers();
             sslContext.init(kmf.getKeyManagers(), tms, new SecureRandom());
         } catch (Exception e) {
             System.out.println("EE = " + e);
         }
     }
}

/**
 * Addifional class to verify SSLContext constructor
 */

class MySslContext extends SSLContext {
    public MySslContext(SSLContextSpi spi, Provider prov, String alg) {
        super(spi, prov, alg);
    }
}
