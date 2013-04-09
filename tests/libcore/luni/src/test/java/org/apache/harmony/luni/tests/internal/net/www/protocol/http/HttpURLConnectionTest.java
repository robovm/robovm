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

package org.apache.harmony.luni.tests.internal.net.www.protocol.http;

import dalvik.annotation.SideEffect;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.TestCase;


/**
 * Tests for <code>HTTPURLConnection</code> class constructors and methods.
 *
 */
public class HttpURLConnectionTest extends TestCase {

    private final static Object bound = new Object();

    static class MockServer extends Thread {
        ServerSocket serverSocket;
        boolean accepted = false;
        boolean started = false;

        public MockServer(String name) throws IOException {
            super(name);
            serverSocket = new ServerSocket(0);
            serverSocket.setSoTimeout(1000);
        }

        public int port() {
            return serverSocket.getLocalPort();
        }

        @Override
        public void run() {
            try {
                synchronized (bound) {
                    started = true;
                    bound.notify();
                }
                try {
                    serverSocket.accept().close();
                    accepted = true;
                } catch (SocketTimeoutException ignore) {
                }
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MockProxyServer extends MockServer {

        boolean acceptedAuthorizedRequest;

        public MockProxyServer(String name) throws Exception {
            super(name);
        }

        @Override
        public void run() {
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(1000);
                byte[] buff = new byte[1024];
                int num = socket.getInputStream().read(buff);
                socket.getOutputStream().write((
                    "HTTP/1.0 407 Proxy authentication required\n"
                  + "Proxy-authenticate: Basic realm=\"remotehost\"\n\n")
                        .getBytes());
                num = socket.getInputStream().read(buff);
                if (num == -1) {
                    // this connection was closed, create new one:
                    socket = serverSocket.accept();
                    socket.setSoTimeout(1000);
                    num = socket.getInputStream().read(buff);
                }
                String request = new String(buff, 0, num);
                acceptedAuthorizedRequest =
                    request.toLowerCase().indexOf("proxy-authorization:") > 0;
                if (acceptedAuthorizedRequest) {
                    socket.getOutputStream().write((
                            "HTTP/1.1 200 OK\n\n").getBytes());
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * ProxySelector implementation used in the test.
     */
    static class TestProxySelector extends ProxySelector {
        // proxy port
        private int proxy_port;
        // server port
        private int server_port;

        /**
         * Creates proxy selector instance.
         * Selector will return the proxy, only if the connection
         * is made to localhost:server_port. Otherwise it will
         * return NO_PROXY.
         * Address of the returned proxy will be localhost:proxy_port.
         */
        public TestProxySelector(int server_port, int proxy_port) {
            this.server_port = server_port;
            this.proxy_port = proxy_port;
        }

        @Override
        public java.util.List<Proxy> select(URI uri) {
            Proxy proxy = Proxy.NO_PROXY;
            if (("localhost".equals(uri.getHost()))
                    && (server_port == uri.getPort())) {
                proxy = new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress("localhost", proxy_port));
            }
            ArrayList<Proxy> result = new ArrayList<Proxy>();
            result.add(proxy);
            return result;
        }

        @Override
        public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
            // do nothing
        }
    }

    /**
     * org.apache.harmony.luni.internal.net.www.http.getOutputStream()
     */
    public void testGetOutputStream() throws Exception {
        // Regression for HARMONY-482
        MockServer httpServer =
            new MockServer("ServerSocket for HttpURLConnectionTest");
        httpServer.start();
        synchronized(bound) {
            if (!httpServer.started) {
                bound.wait(5000);
            }
        }
        HttpURLConnection c = (HttpURLConnection)
            new URL("http://localhost:" + httpServer.port()).openConnection();
        c.setDoOutput(true);
        //use new String("POST") instead of simple "POST" to obtain other
        //object instances then those that are in HttpURLConnection classes
        c.setRequestMethod(new String("POST"));
        c.getOutputStream();
        httpServer.join();
    }


    /**
     * Test checks if the proxy specified in openConnection
     * method will be used for connection to the server
     */
    public void testUsingProxy() throws Exception {
        // Regression for HARMONY-570
        MockServer server = new MockServer("server");
        MockServer proxy = new MockServer("proxy");

        URL url = new URL("http://localhost:" + server.port());

        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection(new Proxy(Proxy.Type.HTTP,
                        new InetSocketAddress("localhost",
                            proxy.port())));
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);

        server.start();
        synchronized(bound) {
            if (!server.started) bound.wait(5000);
        }
        proxy.start();
        synchronized(bound) {
            if (!proxy.started) bound.wait(5000);
        }

        connection.connect();

        // wait while server and proxy run
        server.join();
        proxy.join();

        assertTrue("Connection does not use proxy", connection.usingProxy());
        assertTrue("Proxy server was not used", proxy.accepted);

        HttpURLConnection huc = (HttpURLConnection)url.openConnection(Proxy.NO_PROXY);
        assertFalse(huc.usingProxy());
    }

    /**
     * Test checks if the proxy provided by proxy selector
     * will be used for connection to the server
     */
    public void testUsingProxySelector() throws Exception {
        // Regression for HARMONY-570
        MockServer server = new MockServer("server");
        MockServer proxy = new MockServer("proxy");

        URL url = new URL("http://localhost:" + server.port());

        // keep default proxy selector
        ProxySelector defPS = ProxySelector.getDefault();
        // replace selector
        ProxySelector.setDefault(
                new TestProxySelector(server.port(), proxy.port()));

        try {
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);

            server.start();
            synchronized(bound) {
                if (!server.started) bound.wait(5000);
            }
            proxy.start();
            synchronized(bound) {
                if (!proxy.started) bound.wait(5000);
            }
            connection.connect();

            // wait while server and proxy run
            server.join();
            proxy.join();

            assertTrue("Connection does not use proxy",
                                            connection.usingProxy());
            assertTrue("Proxy server was not used", proxy.accepted);
        } finally {
            // restore default proxy selector
            ProxySelector.setDefault(defPS);
        }
    }
    @SideEffect("Suffers from side effect of other, currently unknown test")
    public void testProxyAuthorization() throws Exception {
        // Set up test Authenticator
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    "user", "password".toCharArray());
            }
        });

        try {
            MockProxyServer proxy = new MockProxyServer("ProxyServer");

            URL url = new URL("http://remotehost:55555/requested.data");
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection(
                        new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress("localhost", proxy.port())));
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            proxy.start();

            connection.connect();
            assertEquals("unexpected response code",
                    200, connection.getResponseCode());
            proxy.join();
            assertTrue("Connection did not send proxy authorization request",
                    proxy.acceptedAuthorizedRequest);
        } finally {
            // remove previously set authenticator
            Authenticator.setDefault(null);
        }
    }

}
