/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.javax.net.ssl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;
import libcore.java.security.TestKeyStore;

public class SSLSocketTest extends TestCase {

    public void test_SSLSocket_getSupportedCipherSuites_names() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        String[] cipherSuites = ssl.getSupportedCipherSuites();
        StandardNames.assertSupportedCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);
        assertNotSame(cipherSuites, ssl.getSupportedCipherSuites());
    }

    public void test_SSLSocket_getSupportedCipherSuites_connect() throws Exception {
        // note the rare usage of non-RSA keys
        TestKeyStore testKeyStore = new TestKeyStore.Builder()
                .keyAlgorithms("RSA", "DSA", "EC", "EC_RSA")
                .aliasPrefix("rsa-dsa-ec")
                .ca(true)
                .build();
        if (StandardNames.IS_RI) {
            test_SSLSocket_getSupportedCipherSuites_connect(testKeyStore,
                                                            StandardNames.JSSE_PROVIDER_NAME,
                                                            StandardNames.JSSE_PROVIDER_NAME,
                                                            true,
                                                            true);
        } else  {
            test_SSLSocket_getSupportedCipherSuites_connect(testKeyStore,
                                                            "HarmonyJSSE",
                                                            "HarmonyJSSE",
                                                            false,
                                                            false);
            test_SSLSocket_getSupportedCipherSuites_connect(testKeyStore,
                                                            "AndroidOpenSSL",
                                                            "AndroidOpenSSL",
                                                            true,
                                                            true);
            test_SSLSocket_getSupportedCipherSuites_connect(testKeyStore,
                                                            "HarmonyJSSE",
                                                            "AndroidOpenSSL",
                                                            false,
                                                            true);
            test_SSLSocket_getSupportedCipherSuites_connect(testKeyStore,
                                                            "AndroidOpenSSL",
                                                            "HarmonyJSSE",
                                                            true,
                                                            false);
        }

    }
    private void test_SSLSocket_getSupportedCipherSuites_connect(TestKeyStore testKeyStore,
                                                                 String clientProvider,
                                                                 String serverProvider,
                                                                 boolean clientSecureRenegotiation,
                                                                 boolean serverSecureRenegotiation)
            throws Exception {

        String clientToServerString = "this is sent from the client to the server...";
        String serverToClientString = "... and this from the server to the client";
        byte[] clientToServer = clientToServerString.getBytes();
        byte[] serverToClient = serverToClientString.getBytes();

        TestSSLContext c = TestSSLContext.create(testKeyStore, testKeyStore,
                                                 clientProvider, serverProvider);
        String[] cipherSuites;
        if (clientProvider.equals(serverProvider)) {
            cipherSuites = c.clientContext.getSocketFactory().getSupportedCipherSuites();
        } else {
            String[] clientSuites = c.clientContext.getSocketFactory().getSupportedCipherSuites();
            String[] serverSuites = c.serverContext.getSocketFactory().getSupportedCipherSuites();
            Set<String> ccs = new HashSet<String>(Arrays.asList(clientSuites));
            Set<String> scs = new HashSet<String>(Arrays.asList(serverSuites));
            Set<String> cs = new HashSet<String>(ccs);
            cs.retainAll(scs);
            cipherSuites = cs.toArray(new String[cs.size()]);
        }

        for (String cipherSuite : cipherSuites) {
            boolean errorExpected = StandardNames.IS_RI && cipherSuite.endsWith("_SHA256");
            try {
                /*
                 * TLS_EMPTY_RENEGOTIATION_INFO_SCSV cannot be used on
                 * its own, but instead in conjunction with other
                 * cipher suites.
                 */
                if (cipherSuite.equals(StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION)) {
                    continue;
                }
                /*
                 * Kerberos cipher suites require external setup. See "Kerberos Requirements" in
                 * https://java.sun.com/j2se/1.5.0/docs/guide/security/jsse/JSSERefGuide.html
                 * #KRBRequire
                 */
                if (cipherSuite.startsWith("TLS_KRB5_")) {
                    continue;
                }

                String[] clientCipherSuiteArray
                        = (clientSecureRenegotiation
                           ? new String[] { cipherSuite,
                                            StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION }
                           : new String[] { cipherSuite });
                String[] serverCipherSuiteArray
                        = (serverSecureRenegotiation
                           ? new String[] { cipherSuite,
                                            StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION }
                           : new String[] { cipherSuite });
                SSLSocket[] pair = TestSSLSocketPair.connect(c,
                                                             clientCipherSuiteArray,
                                                             serverCipherSuiteArray);

                SSLSocket server = pair[0];
                SSLSocket client = pair[1];
                server.getOutputStream().write(serverToClient);
                client.getOutputStream().write(clientToServer);
                // arrays are too big to make sure we get back only what we expect
                byte[] clientFromServer = new byte[serverToClient.length+1];
                byte[] serverFromClient = new byte[clientToServer.length+1];
                int readFromServer = client.getInputStream().read(clientFromServer);
                int readFromClient = server.getInputStream().read(serverFromClient);
                assertEquals(serverToClient.length, readFromServer);
                assertEquals(clientToServer.length, readFromClient);
                assertEquals(clientToServerString, new String(serverFromClient, 0, readFromClient));
                assertEquals(serverToClientString, new String(clientFromServer, 0, readFromServer));
                client.close();
                server.close();
                assertFalse(errorExpected);
            } catch (Exception maybeExpected) {
                if (!errorExpected) {
                    throw new Exception("Problem trying to connect cipher suite " + cipherSuite
                                        + " client=" + clientProvider
                                        + " server=" + serverProvider,
                                        maybeExpected);
                }
            }
        }
        c.close();
    }

    public void test_SSLSocket_getEnabledCipherSuites() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        String[] cipherSuites = ssl.getEnabledCipherSuites();
        StandardNames.assertValidCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);
        assertNotSame(cipherSuites, ssl.getEnabledCipherSuites());
    }

    public void test_SSLSocket_setEnabledCipherSuites() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();

        try {
            ssl.setEnabledCipherSuites(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            ssl.setEnabledCipherSuites(new String[1]);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            ssl.setEnabledCipherSuites(new String[] { "Bogus" } );
            fail();
        } catch (IllegalArgumentException expected) {
        }

        ssl.setEnabledCipherSuites(new String[0]);
        ssl.setEnabledCipherSuites(ssl.getEnabledCipherSuites());
        ssl.setEnabledCipherSuites(ssl.getSupportedCipherSuites());
    }

    public void test_SSLSocket_getSupportedProtocols() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        String[] protocols = ssl.getSupportedProtocols();
        StandardNames.assertSupportedProtocols(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);
        assertNotSame(protocols, ssl.getSupportedProtocols());
    }

    public void test_SSLSocket_getEnabledProtocols() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        String[] protocols = ssl.getEnabledProtocols();
        StandardNames.assertValidProtocols(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);
        assertNotSame(protocols, ssl.getEnabledProtocols());
    }

    public void test_SSLSocket_setEnabledProtocols() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();

        try {
            ssl.setEnabledProtocols(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            ssl.setEnabledProtocols(new String[1]);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            ssl.setEnabledProtocols(new String[] { "Bogus" } );
            fail();
        } catch (IllegalArgumentException expected) {
        }
        ssl.setEnabledProtocols(new String[0]);
        ssl.setEnabledProtocols(ssl.getEnabledProtocols());
        ssl.setEnabledProtocols(ssl.getSupportedProtocols());
    }

    public void test_SSLSocket_getSession() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        SSLSession session = ssl.getSession();
        assertNotNull(session);
        assertFalse(session.isValid());
    }

    public void test_SSLSocket_startHandshake() throws Exception {
        final TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.startHandshake();
                    assertNotNull(server.getSession());
                    try {
                        server.getSession().getPeerCertificates();
                        fail();
                    } catch (SSLPeerUnverifiedException expected) {
                    }
                    Certificate[] localCertificates = server.getSession().getLocalCertificates();
                    assertNotNull(localCertificates);
                    TestKeyStore.assertChainLength(localCertificates);
                    assertNotNull(localCertificates[0]);
                    TestSSLContext.assertServerCertificateChain(c.serverTrustManager,
                                                                localCertificates);
                    TestSSLContext.assertCertificateInKeyStore(localCertificates[0],
                                                               c.serverKeyStore);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        client.startHandshake();
        assertNotNull(client.getSession());
        assertNull(client.getSession().getLocalCertificates());
        Certificate[] peerCertificates = client.getSession().getPeerCertificates();
        assertNotNull(peerCertificates);
        TestKeyStore.assertChainLength(peerCertificates);
        assertNotNull(peerCertificates[0]);
        TestSSLContext.assertServerCertificateChain(c.clientTrustManager,
                                                    peerCertificates);
        TestSSLContext.assertCertificateInKeyStore(peerCertificates[0], c.serverKeyStore);
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_startHandshake_noKeyStore() throws Exception {
        TestSSLContext c = TestSSLContext.create(null, null, null, null, null, null, null, null,
                                                 SSLContext.getDefault(), SSLContext.getDefault());
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        // RI used to throw SSLException on accept, now throws on startHandshake
        if (StandardNames.IS_RI) {
            final SSLSocket server = (SSLSocket) c.serverSocket.accept();
            Thread thread = new Thread(new Runnable () {
                public void run() {
                    try {
                        server.startHandshake();
                    } catch (SSLHandshakeException expected) {
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
            try {
                client.startHandshake();
                fail();
            } catch (SSLHandshakeException expected) {
            }
            thread.join();
            server.close();
        } else {
            try {
                c.serverSocket.accept();
                fail();
            } catch (SSLException expected) {
            }
        }
        client.close();
        c.close();
    }

    public void test_SSLSocket_startHandshake_noClientCertificate() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLContext serverContext = c.serverContext;
        SSLContext clientContext = c.clientContext;
        SSLSocket client = (SSLSocket)
            clientContext.getSocketFactory().createSocket(c.host, c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.startHandshake();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        client.startHandshake();
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_HandshakeCompletedListener() throws Exception {
        final TestSSLContext c = TestSSLContext.create();
        final SSLSocket client = (SSLSocket)
                c.clientContext.getSocketFactory().createSocket(c.host, c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.startHandshake();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        final boolean[] handshakeCompletedListenerCalled = new boolean[1];
        client.addHandshakeCompletedListener(new HandshakeCompletedListener() {
            public void handshakeCompleted(HandshakeCompletedEvent event) {
                try {
                    SSLSession session = event.getSession();
                    String cipherSuite = event.getCipherSuite();
                    Certificate[] localCertificates = event.getLocalCertificates();
                    Certificate[] peerCertificates = event.getPeerCertificates();
                    javax.security.cert.X509Certificate[] peerCertificateChain
                            = event.getPeerCertificateChain();
                    Principal peerPrincipal = event.getPeerPrincipal();
                    Principal localPrincipal = event.getLocalPrincipal();
                    Socket socket = event.getSocket();

                    if (false) {
                        System.out.println("Session=" + session);
                        System.out.println("CipherSuite=" + cipherSuite);
                        System.out.println("LocalCertificates="
                                + Arrays.toString(localCertificates));
                        System.out.println("PeerCertificates="
                                + Arrays.toString(peerCertificates));
                        System.out.println("PeerCertificateChain="
                                + Arrays.toString(peerCertificateChain));
                        System.out.println("PeerPrincipal=" + peerPrincipal);
                        System.out.println("LocalPrincipal=" + localPrincipal);
                        System.out.println("Socket=" + socket);
                    }

                    assertNotNull(session);
                    byte[] id = session.getId();
                    assertNotNull(id);
                    assertEquals(32, id.length);
                    assertNotNull(c.clientContext.getClientSessionContext().getSession(id));

                    assertNotNull(cipherSuite);
                    assertTrue(Arrays.asList(
                            client.getEnabledCipherSuites()).contains(cipherSuite));
                    assertTrue(Arrays.asList(
                            c.serverSocket.getEnabledCipherSuites()).contains(cipherSuite));

                    assertNull(localCertificates);

                    assertNotNull(peerCertificates);
                    TestKeyStore.assertChainLength(peerCertificates);
                    assertNotNull(peerCertificates[0]);
                    TestSSLContext.assertServerCertificateChain(c.clientTrustManager,
                                                                peerCertificates);
                    TestSSLContext.assertCertificateInKeyStore(peerCertificates[0],
                                                               c.serverKeyStore);

                    assertNotNull(peerCertificateChain);
                    TestKeyStore.assertChainLength(peerCertificateChain);
                    assertNotNull(peerCertificateChain[0]);
                    TestSSLContext.assertCertificateInKeyStore(
                        peerCertificateChain[0].getSubjectDN(), c.serverKeyStore);

                    assertNotNull(peerPrincipal);
                    TestSSLContext.assertCertificateInKeyStore(peerPrincipal, c.serverKeyStore);

                    assertNull(localPrincipal);

                    assertNotNull(socket);
                    assertSame(client, socket);

                    synchronized (handshakeCompletedListenerCalled) {
                        handshakeCompletedListenerCalled[0] = true;
                        handshakeCompletedListenerCalled.notify();
                    }
                    handshakeCompletedListenerCalled[0] = true;
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        client.startHandshake();
        thread.join();
        if (!TestSSLContext.sslServerSocketSupportsSessionTickets()) {
            assertNotNull(c.serverContext.getServerSessionContext().getSession(
                    client.getSession().getId()));
        }
        synchronized (handshakeCompletedListenerCalled) {
            while (!handshakeCompletedListenerCalled[0]) {
                handshakeCompletedListenerCalled.wait();
            }
        }
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_HandshakeCompletedListener_RuntimeException() throws Exception {
        final TestSSLContext c = TestSSLContext.create();
        final SSLSocket client = (SSLSocket)
                c.clientContext.getSocketFactory().createSocket(c.host, c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.startHandshake();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        client.addHandshakeCompletedListener(new HandshakeCompletedListener() {
            public void handshakeCompleted(HandshakeCompletedEvent event) {
                throw new RuntimeException("RuntimeException from handshakeCompleted");
            }
        });
        client.startHandshake();
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_getUseClientMode() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        SSLSocket server = (SSLSocket) c.serverSocket.accept();
        assertTrue(client.getUseClientMode());
        assertFalse(server.getUseClientMode());
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_setUseClientMode() throws Exception {
        // client is client, server is server
        test_SSLSocket_setUseClientMode(true, false);
        // client is server, server is client
        test_SSLSocket_setUseClientMode(true, false);
        // both are client
        try {
            test_SSLSocket_setUseClientMode(true, true);
            fail();
        } catch (SSLProtocolException expected) {
            assertTrue(StandardNames.IS_RI);
        } catch (SSLHandshakeException expected) {
            assertFalse(StandardNames.IS_RI);
        }

        // both are server
        try {
            test_SSLSocket_setUseClientMode(false, false);
            fail();
        } catch (SocketTimeoutException expected) {
        }
    }

    public void test_SSLSocket_setUseClientMode_afterHandshake() throws Exception {

        // can't set after handshake
        TestSSLEnginePair pair = TestSSLEnginePair.create(null);
        try {
            pair.server.setUseClientMode(false);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pair.client.setUseClientMode(false);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    private void test_SSLSocket_setUseClientMode(final boolean clientClientMode,
                                                 final boolean serverClientMode)
            throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();

        final SSLHandshakeException[] sslHandshakeException = new SSLHandshakeException[1];
        final SocketTimeoutException[] socketTimeoutException = new SocketTimeoutException[1];
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    if (!serverClientMode) {
                        server.setSoTimeout(1 * 1000);
                    }
                    server.setUseClientMode(serverClientMode);
                    server.startHandshake();
                } catch (SSLHandshakeException e) {
                    sslHandshakeException[0] = e;
                } catch (SocketTimeoutException e) {
                    socketTimeoutException[0] = e;
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        if (!clientClientMode) {
            client.setSoTimeout(1 * 1000);
        }
        client.setUseClientMode(clientClientMode);
        client.startHandshake();
        thread.join();
        if (sslHandshakeException[0] != null) {
            throw sslHandshakeException[0];
        }
        if (socketTimeoutException[0] != null) {
            throw socketTimeoutException[0];
        }
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_untrustedServer() throws Exception {
        TestSSLContext c = TestSSLContext.create(TestKeyStore.getClientCA2(),
                                                 TestKeyStore.getServer());
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.startHandshake();
                } catch (SSLHandshakeException expected) {
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        try {
            client.startHandshake();
            fail();
        } catch (SSLHandshakeException expected) {
            assertTrue(expected.getCause() instanceof CertificateException);
        }
        client.close();
        server.close();
        thread.join();
    }

    public void test_SSLSocket_clientAuth() throws Exception {
        TestSSLContext c = TestSSLContext.create(TestKeyStore.getClientCertificate(),
                                                 TestKeyStore.getServer());
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    assertFalse(server.getWantClientAuth());
                    assertFalse(server.getNeedClientAuth());

                    // confirm turning one on by itself
                    server.setWantClientAuth(true);
                    assertTrue(server.getWantClientAuth());
                    assertFalse(server.getNeedClientAuth());

                    // confirm turning setting on toggles the other
                    server.setNeedClientAuth(true);
                    assertFalse(server.getWantClientAuth());
                    assertTrue(server.getNeedClientAuth());

                    // confirm toggling back
                    server.setWantClientAuth(true);
                    assertTrue(server.getWantClientAuth());
                    assertFalse(server.getNeedClientAuth());

                    server.startHandshake();

                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        client.startHandshake();
        assertNotNull(client.getSession().getLocalCertificates());
        TestKeyStore.assertChainLength(client.getSession().getLocalCertificates());
        TestSSLContext.assertClientCertificateChain(c.clientTrustManager,
                                                    client.getSession().getLocalCertificates());
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_clientAuth_bogusAlias() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLContext clientContext = SSLContext.getInstance("TLS");
        X509KeyManager keyManager = new X509KeyManager() {
            @Override public String chooseClientAlias(String[] keyType,
                                                      Principal[] issuers,
                                                      Socket socket) {
                return "bogus";
            }
            @Override public String chooseServerAlias(String keyType,
                                                      Principal[] issuers,
                                                      Socket socket) {
                throw new AssertionError();
            }
            @Override public X509Certificate[] getCertificateChain(String alias) {
                // return null for "bogus" alias
                return null;
            }
            @Override public String[] getClientAliases(String keyType, Principal[] issuers) {
                throw new AssertionError();
            }
            @Override public String[] getServerAliases(String keyType, Principal[] issuers) {
                throw new AssertionError();
            }
            @Override public PrivateKey getPrivateKey(String alias) {
                // return null for "bogus" alias
                return null;
            }
        };
        clientContext.init(new KeyManager[] { keyManager },
                           new TrustManager[] { c.clientTrustManager },
                           null);
        SSLSocket client = (SSLSocket) clientContext.getSocketFactory().createSocket(c.host,
                                                                                     c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.setNeedClientAuth(true);
                    server.startHandshake();
                    fail();
                } catch (SSLHandshakeException expected) {
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        try {
            client.startHandshake();
            fail();
        } catch (SSLHandshakeException expected) {
            // before we would get a NullPointerException from passing
            // due to the null PrivateKey return by the X509KeyManager.
        }
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_getEnableSessionCreation() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        SSLSocket server = (SSLSocket) c.serverSocket.accept();
        assertTrue(client.getEnableSessionCreation());
        assertTrue(server.getEnableSessionCreation());
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_setEnableSessionCreation_server() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    server.setEnableSessionCreation(false);
                    try {
                        server.startHandshake();
                        fail();
                    } catch (SSLException expected) {
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        try {
            client.startHandshake();
            fail();
        } catch (SSLException expected) {
        }
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_setEnableSessionCreation_client() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSocket client = (SSLSocket) c.clientContext.getSocketFactory().createSocket(c.host,
                                                                                       c.port);
        final SSLSocket server = (SSLSocket) c.serverSocket.accept();
        Thread thread = new Thread(new Runnable () {
            public void run() {
                try {
                    try {
                        server.startHandshake();
                        fail();
                    } catch (SSLException expected) {
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        client.setEnableSessionCreation(false);
        try {
            client.startHandshake();
            fail();
        } catch (SSLException expected) {
        }
        thread.join();
        client.close();
        server.close();
        c.close();
    }

    public void test_SSLSocket_getSSLParameters() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();

        SSLParameters p = ssl.getSSLParameters();
        assertNotNull(p);

        String[] cipherSuites = p.getCipherSuites();
        StandardNames.assertValidCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);
        assertNotSame(cipherSuites, ssl.getEnabledCipherSuites());
        assertEquals(Arrays.asList(cipherSuites), Arrays.asList(ssl.getEnabledCipherSuites()));

        String[] protocols = p.getProtocols();
        StandardNames.assertValidProtocols(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);
        assertNotSame(protocols, ssl.getEnabledProtocols());
        assertEquals(Arrays.asList(protocols), Arrays.asList(ssl.getEnabledProtocols()));

        assertEquals(p.getWantClientAuth(), ssl.getWantClientAuth());
        assertEquals(p.getNeedClientAuth(), ssl.getNeedClientAuth());
    }

    public void test_SSLSocket_setSSLParameters() throws Exception {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) sf.createSocket();
        String[] defaultCipherSuites = ssl.getEnabledCipherSuites();
        String[] defaultProtocols = ssl.getEnabledProtocols();
        String[] supportedCipherSuites = ssl.getSupportedCipherSuites();
        String[] supportedProtocols = ssl.getSupportedProtocols();

        {
            SSLParameters p = new SSLParameters();
            ssl.setSSLParameters(p);
            assertEquals(Arrays.asList(defaultCipherSuites),
                         Arrays.asList(ssl.getEnabledCipherSuites()));
            assertEquals(Arrays.asList(defaultProtocols),
                         Arrays.asList(ssl.getEnabledProtocols()));
        }

        {
            SSLParameters p = new SSLParameters(supportedCipherSuites,
                                                supportedProtocols);
            ssl.setSSLParameters(p);
            assertEquals(Arrays.asList(supportedCipherSuites),
                         Arrays.asList(ssl.getEnabledCipherSuites()));
            assertEquals(Arrays.asList(supportedProtocols),
                         Arrays.asList(ssl.getEnabledProtocols()));
        }
        {
            SSLParameters p = new SSLParameters();

            p.setNeedClientAuth(true);
            assertFalse(ssl.getNeedClientAuth());
            assertFalse(ssl.getWantClientAuth());
            ssl.setSSLParameters(p);
            assertTrue(ssl.getNeedClientAuth());
            assertFalse(ssl.getWantClientAuth());

            p.setWantClientAuth(true);
            assertTrue(ssl.getNeedClientAuth());
            assertFalse(ssl.getWantClientAuth());
            ssl.setSSLParameters(p);
            assertFalse(ssl.getNeedClientAuth());
            assertTrue(ssl.getWantClientAuth());

            p.setWantClientAuth(false);
            assertFalse(ssl.getNeedClientAuth());
            assertTrue(ssl.getWantClientAuth());
            ssl.setSSLParameters(p);
            assertFalse(ssl.getNeedClientAuth());
            assertFalse(ssl.getWantClientAuth());
        }
    }

    public void test_SSLSocket_close() throws Exception {
        TestSSLSocketPair pair = TestSSLSocketPair.create();
        SSLSocket server = pair.server;
        SSLSocket client = pair.client;
        assertFalse(server.isClosed());
        assertFalse(client.isClosed());
        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();
        server.close();
        client.close();
        assertTrue(server.isClosed());
        assertTrue(client.isClosed());

        // close after close is okay...
        server.close();
        client.close();

        // ...so are a lot of other operations...
        HandshakeCompletedListener l = new HandshakeCompletedListener () {
            public void handshakeCompleted(HandshakeCompletedEvent e) {}
        };
        client.addHandshakeCompletedListener(l);
        assertNotNull(client.getEnabledCipherSuites());
        assertNotNull(client.getEnabledProtocols());
        client.getEnableSessionCreation();
        client.getNeedClientAuth();
        assertNotNull(client.getSession());
        assertNotNull(client.getSSLParameters());
        assertNotNull(client.getSupportedProtocols());
        client.getUseClientMode();
        client.getWantClientAuth();
        client.removeHandshakeCompletedListener(l);
        client.setEnabledCipherSuites(new String[0]);
        client.setEnabledProtocols(new String[0]);
        client.setEnableSessionCreation(false);
        client.setNeedClientAuth(false);
        client.setSSLParameters(client.getSSLParameters());
        client.setWantClientAuth(false);

        // ...but some operations are expected to give SocketException...
        try {
            client.startHandshake();
            fail();
        } catch (SocketException expected) {
        }
        try {
            client.getInputStream();
            fail();
        } catch (SocketException expected) {
        }
        try {
            client.getOutputStream();
            fail();
        } catch (SocketException expected) {
        }
        try {
            input.read();
            fail();
        } catch (SocketException expected) {
        }
        try {
            input.read(null, -1, -1);
            fail();
        } catch (NullPointerException expected) {
            assertTrue(StandardNames.IS_RI);
        } catch (SocketException expected) {
            assertFalse(StandardNames.IS_RI);
        }
        try {
            output.write(-1);
            fail();
        } catch (SocketException expected) {
        }
        try {
            output.write(null, -1, -1);
            fail();
        } catch (NullPointerException expected) {
            assertTrue(StandardNames.IS_RI);
        } catch (SocketException expected) {
            assertFalse(StandardNames.IS_RI);
        }

        // ... and one gives IllegalArgumentException
        try {
            client.setUseClientMode(false);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        pair.close();
    }

    /**
     * b/3350645 Test to confirm that an SSLSocket.close() performing
     * an SSL_shutdown does not throw an IOException if the peer
     * socket has been closed.
     */
    public void test_SSLSocket_shutdownCloseOnClosedPeer() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        final Socket underlying = new Socket(c.host, c.port);
        final SSLSocket wrapping = (SSLSocket)
                c.clientContext.getSocketFactory().createSocket(underlying,
                                                                c.host.getHostName(),
                                                                c.port,
                                                                false);
        Thread clientThread = new Thread(new Runnable () {
            public void run() {
                try {
                    try {
                        wrapping.startHandshake();
                        wrapping.getOutputStream().write(42);
                        // close the underlying socket,
                        // so that no SSL shutdown is sent
                        underlying.close();
                        wrapping.close();
                    } catch (SSLException expected) {
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        clientThread.start();

        SSLSocket server = (SSLSocket) c.serverSocket.accept();
        server.startHandshake();
        server.getInputStream().read();
        // wait for thread to finish so we know client is closed.
        clientThread.join();
        // close should cause an SSL_shutdown which will fail
        // because the peer has closed, but it shouldn't throw.
        server.close();
    }

    public void test_SSLSocket_setSoTimeout_basic() throws Exception {
        ServerSocket listening = new ServerSocket(0);

        Socket underlying = new Socket(listening.getInetAddress(), listening.getLocalPort());
        assertEquals(0, underlying.getSoTimeout());

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket wrapping = sf.createSocket(underlying, null, -1, false);
        assertEquals(0, wrapping.getSoTimeout());

        // setting wrapper sets underlying and ...
        wrapping.setSoTimeout(10);
        assertEquals(10, wrapping.getSoTimeout());
        assertEquals(10, underlying.getSoTimeout());

        // ... getting wrapper inspects underlying
        underlying.setSoTimeout(0);
        assertEquals(0, wrapping.getSoTimeout());
        assertEquals(0, underlying.getSoTimeout());
    }

    public void test_SSLSocket_setSoTimeout_wrapper() throws Exception {
        if (StandardNames.IS_RI) {
            // RI cannot handle this case
            return;
        }
        ServerSocket listening = new ServerSocket(0);

        // setSoTimeout applies to read, not connect, so connect first
        Socket underlying = new Socket(listening.getInetAddress(), listening.getLocalPort());
        Socket server = listening.accept();

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket clientWrapping = sf.createSocket(underlying, null, -1, false);

        underlying.setSoTimeout(1);
        try {
            clientWrapping.getInputStream().read();
            fail();
        } catch (SocketTimeoutException expected) {
        }

        clientWrapping.close();
        server.close();
        underlying.close();
        listening.close();
    }

    public void test_SSLSocket_interrupt() throws Exception {
        ServerSocket listening = new ServerSocket(0);

        for (int i = 0; i < 3; i++) {
            Socket underlying = new Socket(listening.getInetAddress(), listening.getLocalPort());
            Socket server = listening.accept();

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket clientWrapping = sf.createSocket(underlying, null, -1, true);

            switch (i) {
                case 0:
                    test_SSLSocket_interrupt_case(underlying, underlying);
                    break;
                case 1:
                    test_SSLSocket_interrupt_case(underlying, clientWrapping);
                    break;
                case 2:
                    test_SSLSocket_interrupt_case(clientWrapping, underlying);
                    break;
                case 3:
                    test_SSLSocket_interrupt_case(clientWrapping, clientWrapping);
                    break;
                default:
                    fail();
            }

            server.close();
            underlying.close();
        }
        listening.close();
    }

    private void test_SSLSocket_interrupt_case(Socket toRead, final Socket toClose)
            throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1 * 1000);
                    toClose.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
        try {
            toRead.setSoTimeout(5 * 1000);
            toRead.getInputStream().read();
            fail();
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (SocketException expected) {
        }
    }

    public void test_TestSSLSocketPair_create() {
        TestSSLSocketPair test = TestSSLSocketPair.create();
        assertNotNull(test.c);
        assertNotNull(test.server);
        assertNotNull(test.client);
        assertTrue(test.server.isConnected());
        assertTrue(test.client.isConnected());
        assertFalse(test.server.isClosed());
        assertFalse(test.client.isClosed());
        assertNotNull(test.server.getSession());
        assertNotNull(test.client.getSession());
        assertTrue(test.server.getSession().isValid());
        assertTrue(test.client.getSession().isValid());
        test.close();
    }

    /**
     * Not run by default by JUnit, but can be run by Vogar by
     * specifying it explicitly (or with main method below)
     */
    public void stress_test_TestSSLSocketPair_create() {
        final boolean verbose = true;
        while (true) {
            TestSSLSocketPair test = TestSSLSocketPair.create();
            if (verbose) {
                System.out.println("client=" + test.client.getLocalPort()
                                   + " server=" + test.server.getLocalPort());
            } else {
                System.out.print("X");
            }

            /*
              We don't close on purpose in this stress test to add
              races in file descriptors reuse when the garbage
              collector runs concurrently and finalizes sockets
            */
            // test.close();

        }
    }

    public static void main (String[] args) {
        new SSLSocketTest().stress_test_TestSSLSocketPair_create();
    }
}
