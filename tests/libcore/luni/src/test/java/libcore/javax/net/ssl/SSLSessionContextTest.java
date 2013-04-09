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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import junit.framework.TestCase;

public class SSLSessionContextTest extends TestCase {

    public static final void assertSSLSessionContextSize(int expected, TestSSLContext c) {
        assertSSLSessionContextSize(expected,
                                    c.clientContext.getClientSessionContext(),
                                    c.serverContext.getServerSessionContext());
        assertSSLSessionContextSize(0,
                                    c.serverContext.getClientSessionContext(),
                                    c.clientContext.getServerSessionContext());
    }

    public static final void assertSSLSessionContextSize(int expected,
                                                         SSLSessionContext client,
                                                         SSLSessionContext server) {
        assertSSLSessionContextSize(expected, client, false);
        assertSSLSessionContextSize(expected, server, true);
    }

    public static final void assertSSLSessionContextSize(int expected,
                                                         SSLSessionContext s,
                                                         boolean server) {
        int size = Collections.list(s.getIds()).size();
        if (server && TestSSLContext.sslServerSocketSupportsSessionTickets()) {
            assertEquals(0, size);
        } else {
            assertEquals(expected, size);
        }
    }

    public void test_SSLSessionContext_getIds() {
        TestSSLContext c = TestSSLContext.create();
        assertSSLSessionContextSize(0, c);
        c.close();

        TestSSLSocketPair s = TestSSLSocketPair.create();
        assertSSLSessionContextSize(1, s.c);
        Enumeration clientIds = s.c.clientContext.getClientSessionContext().getIds();
        Enumeration serverIds = s.c.serverContext.getServerSessionContext().getIds();
        byte[] clientId = (byte[]) clientIds.nextElement();
        assertEquals(32, clientId.length);
        if (TestSSLContext.sslServerSocketSupportsSessionTickets()) {
            assertFalse(serverIds.hasMoreElements());
        } else {
            byte[] serverId = (byte[]) serverIds.nextElement();
            assertEquals(32, serverId.length);
            assertTrue(Arrays.equals(clientId, serverId));
        }
        s.close();
    }

    public void test_SSLSessionContext_getSession() {
        TestSSLContext c = TestSSLContext.create();
        try {
            c.clientContext.getClientSessionContext().getSession(null);
            fail();
        } catch (NullPointerException expected) {
        }
        assertNull(c.clientContext.getClientSessionContext().getSession(new byte[0]));
        assertNull(c.clientContext.getClientSessionContext().getSession(new byte[1]));
        try {
            c.serverContext.getServerSessionContext().getSession(null);
            fail();
        } catch (NullPointerException expected) {
        }
        assertNull(c.serverContext.getServerSessionContext().getSession(new byte[0]));
        assertNull(c.serverContext.getServerSessionContext().getSession(new byte[1]));
        c.close();

        TestSSLSocketPair s = TestSSLSocketPair.create();
        SSLSessionContext client = s.c.clientContext.getClientSessionContext();
        SSLSessionContext server = s.c.serverContext.getServerSessionContext();
        byte[] clientId = (byte[]) client.getIds().nextElement();
        assertNotNull(client.getSession(clientId));
        assertTrue(Arrays.equals(clientId, client.getSession(clientId).getId()));
        if (TestSSLContext.sslServerSocketSupportsSessionTickets()) {
            assertFalse(server.getIds().hasMoreElements());
        } else {
            byte[] serverId = (byte[]) server.getIds().nextElement();
            assertNotNull(server.getSession(serverId));
            assertTrue(Arrays.equals(serverId, server.getSession(serverId).getId()));
        }
        s.close();
    }

    public void test_SSLSessionContext_getSessionCacheSize() {
        TestSSLContext c = TestSSLContext.create();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_CLIENT_SSL_SESSION_CACHE_SIZE,
                     c.clientContext.getClientSessionContext().getSessionCacheSize());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SERVER_SSL_SESSION_CACHE_SIZE,
                     c.serverContext.getServerSessionContext().getSessionCacheSize());
        c.close();

        TestSSLSocketPair s = TestSSLSocketPair.create();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_CLIENT_SSL_SESSION_CACHE_SIZE,
                     s.c.clientContext.getClientSessionContext().getSessionCacheSize());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SERVER_SSL_SESSION_CACHE_SIZE,
                     s.c.serverContext.getServerSessionContext().getSessionCacheSize());
        s.close();
    }

    public void test_SSLSessionContext_setSessionCacheSize_noConnect() {
        TestSSLContext c = TestSSLContext.create();
        assertNoConnectSetSessionCacheSizeBehavior(
                TestSSLContext.EXPECTED_DEFAULT_CLIENT_SSL_SESSION_CACHE_SIZE,
                c.clientContext.getClientSessionContext());
        assertNoConnectSetSessionCacheSizeBehavior(
                TestSSLContext.EXPECTED_DEFAULT_SERVER_SSL_SESSION_CACHE_SIZE,
                c.serverContext.getServerSessionContext());
        c.close();
    }

    private static void assertNoConnectSetSessionCacheSizeBehavior(int expectedDefault,
                                                                   SSLSessionContext s) {
        try {
            s.setSessionCacheSize(-1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        assertEquals(expectedDefault, s.getSessionCacheSize());
        s.setSessionCacheSize(1);
        assertEquals(1, s.getSessionCacheSize());
    }

    public void test_SSLSessionContext_setSessionCacheSize_oneConnect() {
        TestSSLSocketPair s = TestSSLSocketPair.create();
        SSLSessionContext client = s.c.clientContext.getClientSessionContext();
        SSLSessionContext server = s.c.serverContext.getServerSessionContext();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_CLIENT_SSL_SESSION_CACHE_SIZE,
                     client.getSessionCacheSize());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SERVER_SSL_SESSION_CACHE_SIZE,
                     server.getSessionCacheSize());
        assertSSLSessionContextSize(1, s.c);
        s.close();
    }

    public void test_SSLSessionContext_setSessionCacheSize_dynamic() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLSessionContext client = c.clientContext.getClientSessionContext();
        SSLSessionContext server = c.serverContext.getServerSessionContext();

        String[] supportedCipherSuites = c.serverSocket.getSupportedCipherSuites();
        c.serverSocket.setEnabledCipherSuites(supportedCipherSuites);
        LinkedList<String> uniqueCipherSuites
            = new LinkedList(Arrays.asList(supportedCipherSuites));
        // only use RSA cipher suites which will work with our TrustProvider
        Iterator<String> i = uniqueCipherSuites.iterator();
        while (i.hasNext()) {
            String cipherSuite = i.next();

            // Certificate key length too long for export ciphers
            if (cipherSuite.startsWith("SSL_RSA_EXPORT_")) {
                i.remove();
                continue;
            }

            if (cipherSuite.startsWith("SSL_RSA_")) {
                continue;
            }
            if (cipherSuite.startsWith("TLS_RSA_")) {
                continue;
            }
            if (cipherSuite.startsWith("TLS_DHE_RSA_")) {
                continue;
            }
            if (cipherSuite.startsWith("SSL_DHE_RSA_")) {
                continue;
            }
            i.remove();
        }

        /*
         * having more than 3 uniqueCipherSuites is a test
         * requirement, not a requirement of the interface or
         * implementation. It simply allows us to make sure that we
         * will not get a cached session ID since we'll have to
         * renegotiate a new session due to the new cipher suite
         * requirement. even this test only really needs three if it
         * reused the unique cipher suites every time it resets the
         * session cache.
         */
        assertTrue(uniqueCipherSuites.size() >= 3);
        String cipherSuite1 = uniqueCipherSuites.get(0);
        String cipherSuite2 = uniqueCipherSuites.get(1);
        String cipherSuite3 = uniqueCipherSuites.get(2);

        List<SSLSocket[]> toClose = new ArrayList<SSLSocket[]>();
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite1 }, null));
        assertSSLSessionContextSize(1, c);
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite2 }, null));
        assertSSLSessionContextSize(2, c);
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite3 }, null));
        assertSSLSessionContextSize(3, c);

        client.setSessionCacheSize(1);
        server.setSessionCacheSize(1);
        assertEquals(1, client.getSessionCacheSize());
        assertEquals(1, server.getSessionCacheSize());
        assertSSLSessionContextSize(1, c);
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite1 }, null));
        assertSSLSessionContextSize(1, c);

        client.setSessionCacheSize(2);
        server.setSessionCacheSize(2);
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite2 }, null));
        assertSSLSessionContextSize(2, c);
        toClose.add(TestSSLSocketPair.connect(c, new String[] { cipherSuite3 }, null));
        assertSSLSessionContextSize(2, c);

        for (SSLSocket[] pair : toClose) {
            for (SSLSocket s : pair) {
                s.close();
            }
        }
        c.close();
    }

    public void test_SSLSessionContext_getSessionTimeout() {
        TestSSLContext c = TestSSLContext.create();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     c.clientContext.getClientSessionContext().getSessionTimeout());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     c.serverContext.getServerSessionContext().getSessionTimeout());
        c.close();

        TestSSLSocketPair s = TestSSLSocketPair.create();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     s.c.clientContext.getClientSessionContext().getSessionTimeout());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     s.c.serverContext.getServerSessionContext().getSessionTimeout());
        s.close();
    }

    public void test_SSLSessionContext_setSessionTimeout() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     c.clientContext.getClientSessionContext().getSessionTimeout());
        assertEquals(TestSSLContext.EXPECTED_DEFAULT_SSL_SESSION_CACHE_TIMEOUT,
                     c.serverContext.getServerSessionContext().getSessionTimeout());
        c.clientContext.getClientSessionContext().setSessionTimeout(0);
        c.serverContext.getServerSessionContext().setSessionTimeout(0);
        assertEquals(0, c.clientContext.getClientSessionContext().getSessionTimeout());
        assertEquals(0, c.serverContext.getServerSessionContext().getSessionTimeout());

        try {
            c.clientContext.getClientSessionContext().setSessionTimeout(-1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            c.serverContext.getServerSessionContext().setSessionTimeout(-1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        c.close();

        TestSSLSocketPair s = TestSSLSocketPair.create();
        assertSSLSessionContextSize(1, s.c);
        Thread.sleep(1 * 1000);
        s.c.clientContext.getClientSessionContext().setSessionTimeout(1);
        s.c.serverContext.getServerSessionContext().setSessionTimeout(1);
        assertSSLSessionContextSize(0, s.c);
        s.close();
    }
}
