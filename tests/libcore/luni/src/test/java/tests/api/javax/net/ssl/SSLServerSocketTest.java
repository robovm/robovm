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

import junit.framework.TestCase;

import libcore.io.Base64;

import tests.support.Support_PortManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

public class SSLServerSocketTest extends TestCase {

    // set to true if on Android, false if on RI
    boolean useBKS = true;

    /**
     * Additional class for SSLServerSocket constructor verification
     */
    class mySSLServerSocket extends SSLServerSocket {

        public mySSLServerSocket() throws IOException{
            super();
        }

        public mySSLServerSocket(int port) throws IOException{
            super(port);
        }

        public mySSLServerSocket(int port, int backlog) throws IOException{
            super(port, backlog);
        }

        public mySSLServerSocket(int port, int backlog, InetAddress address) throws IOException{
            super(port, backlog, address);
        }

        public String[] getSupportedCipherSuites() {
            return null;
        }

        public void setEnabledCipherSuites(String[] suites) {

        }

        public String[] getEnabledCipherSuites() {
            return null;
        }

        public String[] getSupportedProtocols() {
            return null;
        }

        public String[] getEnabledProtocols() {
            return null;
        }

        public void setEnabledProtocols(String[] protocols) {

        }

        public void setEnableSessionCreation(boolean flag) {

        }

        public boolean getEnableSessionCreation() {
            return false;
        }

        public void setNeedClientAuth(boolean need) {

        }

        public boolean getNeedClientAuth() {
            return false;
        }

        public boolean getUseClientMode() {
            return false;
        }

        public void setUseClientMode(boolean mode) {

        }

        public boolean getWantClientAuth() {
            return false;
        }
        public void setWantClientAuth(boolean mode) {

        }
    }

    /**
     * javax.net.ssl.SSLServerSocket#SSLServerSocket()
     */
    public void testConstructor_01() {
        try {
            SSLServerSocket ssl = new mySSLServerSocket();
        } catch (Exception ex) {
            fail("Unexpected exception was thrown " + ex);
        }
    }

    /**
     * javax.net.ssl.SSLServerSocket#SSLServerSocket(int port)
     */
    public void testConstructor_02() {
        SSLServerSocket ssl;
        int portNumber = Support_PortManager.getNextPort();
        int[] port_invalid = {-1, 65536, Integer.MIN_VALUE, Integer.MAX_VALUE};

        try {
            ssl = new mySSLServerSocket(portNumber);
            assertEquals(portNumber, ssl.getLocalPort());
        } catch (Exception ex) {
            fail("Unexpected exception was thrown " + ex);
        }

        for (int i = 0; i < port_invalid.length; i++) {
            try {
                ssl = new mySSLServerSocket(port_invalid[i]);
                fail("IllegalArgumentException should be thrown");
            } catch (IllegalArgumentException iae) {
                //expected
            } catch (Exception e) {
                fail(e + " was thrown instead of IllegalArgumentException");
            }
        }

        try {
            ssl = new mySSLServerSocket(portNumber);
            new mySSLServerSocket(portNumber);
            fail("IOException Expected when opening an already opened port");
        } catch (IOException ioe) {
            // expected
        } catch (Exception ex) {
            fail("Unexpected exception was thrown " + ex);
        }
    }

    /**
     * javax.net.ssl.SSLServerSocket#SSLServerSocket(int port, int backlog)
     */
    public void testConstructor_03() {
        mySSLServerSocket ssl;
        int portNumber = Support_PortManager.getNextPort();
        int[] port_invalid = {-1, Integer.MIN_VALUE, Integer.MAX_VALUE};

        try {
            ssl = new mySSLServerSocket(portNumber, 1);
            assertEquals(portNumber, ssl.getLocalPort());
        } catch (Exception ex) {
            fail("Unexpected exception was thrown");
        }

        for (int i = 0; i < port_invalid.length; i++) {
            try {
                ssl = new mySSLServerSocket(port_invalid[i], 1);
                fail("IllegalArgumentException should be thrown");
            } catch (IllegalArgumentException iae) {
                // expected
            } catch (Exception e) {
                fail(e + " was thrown instead of IllegalArgumentException");
            }
        }

        portNumber = Support_PortManager.getNextPort();
        try {
            ssl = new mySSLServerSocket(portNumber, 1);
            new mySSLServerSocket(portNumber, 1);
            fail("IOException should be thrown");
        } catch (IOException ioe) {
        }
    }

    /**
     * javax.net.ssl.SSLServerSocket#SSLServerSocket(int port, int backlog, InetAddress address)
     */
    public void testConstructor_04() {
        mySSLServerSocket ssl;
        InetAddress ia = null;
        int portNumber = Support_PortManager.getNextPort();
        int[] port_invalid = {-1, 65536, Integer.MIN_VALUE, Integer.MAX_VALUE};

        try {
            ssl = new mySSLServerSocket(portNumber, 0, ia);
            assertEquals(portNumber, ssl.getLocalPort());
        } catch (Exception ex) {
            fail("Unexpected exception was thrown");
        }

        portNumber = Support_PortManager.getNextPort();
        try {
            ssl = new mySSLServerSocket(portNumber, 0, InetAddress.getLocalHost());
            assertEquals(portNumber, ssl.getLocalPort());
        } catch (Exception ex) {
            fail("Unexpected exception was thrown");
        }

        for (int i = 0; i < port_invalid.length; i++) {
            try {
                ssl = new mySSLServerSocket(port_invalid[i], 1, InetAddress.getLocalHost());
                fail("IllegalArgumentException should be thrown");
            } catch (IllegalArgumentException iae) {
                // expected
            } catch (Exception e) {
                fail(e + " was thrown instead of IllegalArgumentException");
            }
        }

        portNumber = Support_PortManager.getNextPort();
        try {
           ssl = new mySSLServerSocket(portNumber, 0, InetAddress.getLocalHost());
           new mySSLServerSocket(portNumber, 0, InetAddress.getLocalHost());
           fail("IOException should be thrown for");
        } catch (IOException ioe) {
        }
    }

    /**
     * @throws Exception
     * javax.net.ssl.SSLServerSocket#getSupportedCipherSuites()
     */
    public void test_getSupportedCipherSuites() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        String[] res = sss.getSupportedCipherSuites();
        assertNotNull("NULL result", res);
        assertTrue("no supported cipher suites available.", res.length > 0);
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#getEnabledCipherSuites()
     * javax.net.ssl.SSLServerSocket#setEnabledCipherSuites(String[] suites)
     */
    public void test_EnabledCipherSuites() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        try {
            sss.setEnabledCipherSuites(null);
        } catch (IllegalArgumentException iae) {
            //expected
        }
        String[] unsupportedCipherSuites = {"unsupported"};
        try {
            sss.setEnabledCipherSuites(unsupportedCipherSuites);
        } catch (IllegalArgumentException iae) {
            //expected
        }
        int count = sss.getSupportedCipherSuites().length;
        assertTrue("No supported cipher suites", count > 0);
        sss.setEnabledCipherSuites(sss.getSupportedCipherSuites());
        String[] res = sss.getEnabledCipherSuites();
        assertNotNull("NULL result", res);
        assertEquals("not all supported cipher suites were enabled",
                     Arrays.asList(sss.getSupportedCipherSuites()),
                     Arrays.asList(res));
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#getSupportedProtocols()
     */
    public void test_getSupportedProtocols() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        String[] res = sss.getSupportedCipherSuites();
        assertNotNull("NULL result", res);
        assertTrue("no supported protocols available.", res.length > 0);
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#getEnabledProtocols()
     * javax.net.ssl.SSLServerSocket#setEnabledProtocols(String[] protocols)
     */
    public void test_EnabledProtocols() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        try {
            sss.setEnabledProtocols(null);
        } catch (IllegalArgumentException iae) {
            //expected
        }
        String[] unsupportedProtocols = {"unsupported"};
        try {
            sss.setEnabledProtocols(unsupportedProtocols);
        } catch (IllegalArgumentException iae) {
            //expected
        }
        int count = sss.getSupportedProtocols().length;
        assertTrue("No supported protocols", count > 0);
        sss.setEnabledProtocols(sss.getSupportedProtocols());
        String[] res = sss.getEnabledProtocols();
        assertNotNull("NULL result", res);
        assertTrue("no enabled protocols.", res.length == count);
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#setEnableSessionCreation(boolean flag)
     * javax.net.ssl.SSLServerSocket#getEnableSessionCreation()
     */
    public void test_EnableSessionCreation() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        assertTrue(sss.getEnableSessionCreation());
        sss.setEnableSessionCreation(false);
        assertFalse(sss.getEnableSessionCreation());
        sss.setEnableSessionCreation(true);
        assertTrue(sss.getEnableSessionCreation());
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#setNeedClientAuth(boolean need)
     * javax.net.ssl.SSLServerSocket#getNeedClientAuthCreation()
     */
    public void test_NeedClientAuth() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        sss.setNeedClientAuth(true);
        assertTrue(sss.getNeedClientAuth());
        sss.setNeedClientAuth(false);
        assertFalse(sss.getNeedClientAuth());
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#getUseClientMode()
     * javax.net.ssl.SSLServerSocket#setUseClientMode(boolean mode)
     */
    public void test_UseClientMode() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        sss.setUseClientMode(false);
        assertFalse(sss.getUseClientMode());
        sss.setUseClientMode(true);
        assertTrue(sss.getUseClientMode());
    }

    /**
     * @throws IOException
     * javax.net.ssl.SSLServerSocket#setWantClientAuth(boolean want)
     * javax.net.ssl.SSLServerSocket#getWantClientAuthCreation()
     */
    public void test_WantClientAuth() throws Exception {
        SSLServerSocket sss = getSSLServerSocket();
        sss.setWantClientAuth(true);
        assertTrue(sss.getWantClientAuth());
        sss.setWantClientAuth(false);
        assertFalse(sss.getWantClientAuth());
    }


    /**
     * Defines the keystore contents for the server, BKS version. Holds just a
     * single self-generated key. The subject name is "Test Server".
     */
    private static final String SERVER_KEYS_BKS =
        "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";

    /**
     * Defines the keystore contents for the server, JKS version. Holds just a
     * single self-generated key. The subject name is "Test Server".
     */
    private static final String SERVER_KEYS_JKS =
        "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";

    private String PASSWORD = "android";

    /**
     * Loads a keystore from a base64-encoded String. Returns the KeyManager[]
     * for the result.
     */
    private KeyManager[] getKeyManagers() throws Exception {
        String keys = (useBKS ? SERVER_KEYS_BKS : SERVER_KEYS_JKS);
        byte[] bytes = Base64.decode(keys.getBytes());
        InputStream inputStream = new ByteArrayInputStream(bytes);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(inputStream, PASSWORD.toCharArray());
        inputStream.close();

        String algorithm = KeyManagerFactory.getDefaultAlgorithm();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(algorithm);
        keyManagerFactory.init(keyStore, PASSWORD.toCharArray());

        return keyManagerFactory.getKeyManagers();
    }

    private SSLServerSocket getSSLServerSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(getKeyManagers(), null, null);
        SSLServerSocket sss = (SSLServerSocket) context.getServerSocketFactory()
                .createServerSocket();
        return sss;
    }

    public void test_creationStressTest() throws Exception {
        KeyManager[] keyManagers = getKeyManagers();
        // Test the default codepath, which uses /dev/urandom.
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagers, null, null);
        for (int i = 0; i < 2048; ++i) {
            sslContext.getServerSocketFactory().createServerSocket().close();
        }

        // Test the other codepath, which copies a seed from a byte[].
        sslContext.init(keyManagers, null, new SecureRandom());
        for (int i = 0; i < 2048; ++i) {
            sslContext.getServerSocketFactory().createServerSocket().close();
        }
    }
}
