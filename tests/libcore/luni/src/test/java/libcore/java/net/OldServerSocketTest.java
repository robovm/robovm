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

package libcore.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ServerSocketChannel;
import java.security.Permission;
import java.util.Properties;
import tests.support.Support_PortManager;

public class OldServerSocketTest extends OldSocketTestCase {

    boolean isCreateCalled = false;
    ServerSocket s;
    Socket sconn;
    Thread t;

    public void test_setPerformancePreference_Int_Int_Int() throws Exception {
        performancePreferenceTest(1, 0, 0);
        performancePreferenceTest(1, 1, 1);
        performancePreferenceTest(0, 1, 2);
        performancePreferenceTest(Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE);
    }

    void performancePreferenceTest(int connectionTime, int latency,
            int bandwidth) throws Exception {
        ServerSocket theSocket = new ServerSocket();
        theSocket.setPerformancePreferences(connectionTime, latency, bandwidth);

        InetSocketAddress theAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), 0);
        theSocket.bind(theAddress);
        int portNumber = theSocket.getLocalPort();
        assertTrue(
                "Returned incorrect InetSocketAddress(2):"
                        + theSocket.getLocalSocketAddress().toString()
                        + "Expected: "
                        + (new InetSocketAddress(InetAddress.getLocalHost(),
                                portNumber)).toString(), theSocket
                        .getLocalSocketAddress().equals(
                                new InetSocketAddress(InetAddress
                                        .getLocalHost(), portNumber)));
        assertTrue("Server socket not bound when it should be:", theSocket
                .isBound());

        // now make sure that it is actually bound and listening on the
        // address we provided
        Socket clientSocket = new Socket();
        InetSocketAddress clAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), portNumber);
        clientSocket.connect(clAddress);
        Socket servSock = theSocket.accept();

        assertEquals(clAddress, clientSocket.getRemoteSocketAddress());
        theSocket.close();
        servSock.close();
        clientSocket.close();
    }

    public void test_ConstructorII() throws IOException {
        int freePortNumber = Support_PortManager.getNextPort();
        s = new ServerSocket(freePortNumber, 1);
        s.setSoTimeout(2000);
        startClient(freePortNumber);
        sconn = s.accept();
        sconn.close();
        s.close();
    }

    static class SSClient implements Runnable {
        Socket cs;

        int port;

        public SSClient(int prt) {
            port = prt;
        }

        public void run() {
            try {
                // Go to sleep so the server can setup and wait for connection
                Thread.sleep(1000);
                cs = new Socket(InetAddress.getLocalHost().getHostName(), port);
                // Sleep again to allow server side processing. Thread is
                // stopped by server.
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return;
            } catch (Throwable e) {
                System.out.println("Error establishing client: " + e.toString());
            } finally {
                try {
                    if (cs != null)
                        cs.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void test_Constructor() throws IOException {
        ServerSocket ss = new ServerSocket();
        assertEquals(-1, ss.getLocalPort());
        ss.close();
    }

    public void test_ConstructorI() throws Exception {
        int portNumber = Support_PortManager.getNextPort();
        s = new ServerSocket(portNumber);
        try {
            new ServerSocket(portNumber);
            fail("IOException was not thrown.");
        } catch(IOException ioe) {
            //expected
        }
        try {
            startClient(s.getLocalPort());
            sconn = s.accept();
            assertNotNull("Was unable to accept connection", sconn);
            sconn.close();
        } finally {
            s.close();
        }

        s = new ServerSocket(0);
        try {
            startClient(s.getLocalPort());
            sconn = s.accept();
            assertNotNull("Was unable to accept connection", sconn);
            sconn.close();
        } finally {
            s.close();
        }
    }

    public void test_ConstructorIILjava_net_InetAddress() throws IOException {
        int freePortNumber = Support_PortManager.getNextPort();

        ServerSocket ss = new ServerSocket(freePortNumber, 10, InetAddress.getLocalHost());
        try {
            new ServerSocket(freePortNumber, 10, InetAddress.getLocalHost());
            fail("IOException was not thrown.");
        } catch(IOException expected) {
        }
        ss.close();

        try {
            new ServerSocket(65536, 10, InetAddress.getLocalHost());
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException expected) {
        }
    }

    public void test_LocalPort() throws IOException {
        ServerSocket ss1 = new ServerSocket(4242);
        assertEquals(ss1.getLocalPort(), 4242);
        ss1.close();

        ServerSocket ss2 = new ServerSocket();
        ss2.bind(new InetSocketAddress("127.0.0.1", 4343));
        assertEquals(ss2.getLocalPort(), 4343);
        ss2.close();

        ServerSocket ss3 = new ServerSocket(0);
        assertTrue(ss3.getLocalPort() != 0);
        ss3.close();
    }

    class MockSocketFactory implements SocketImplFactory {
        public SocketImpl createSocketImpl() {
            return new MockSocketImpl();
        }
    }

    public void test_ConstructorI_SocksSet() throws IOException {
        // Harmony-623 regression test
        ServerSocket ss = null;
        Properties props = (Properties) System.getProperties().clone();
        try {
            System.setProperty("socksProxyHost", "127.0.0.1");
            System.setProperty("socksProxyPort", "12345");
            ss = new ServerSocket(0);
        } finally {
            System.setProperties(props);
            if (null != ss) {
                ss.close();
            }
        }
    }

    public void test_accept() throws IOException {
        int portNumber = Support_PortManager.getNextPort();

        ServerSocket newSocket = new ServerSocket(portNumber);
        newSocket.setSoTimeout(500);
        try {
            Socket accepted = newSocket.accept();
            fail("SocketTimeoutException was not thrown: " + accepted);
        } catch(SocketTimeoutException expected) {
        }
        newSocket.close();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket ss = ssc.socket();

        try {
            ss.accept();
            fail("IllegalBlockingModeException was not thrown.");
        } catch(IllegalBlockingModeException ibme) {
            //expected
        } finally {
            ss.close();
            ssc.close();
        }
    }

    public void test_getSoTimeout_setSoTimeout() throws Exception {
        // TODO: a useful test would check that setSoTimeout actually causes timeouts!
        ServerSocket s = new ServerSocket();
        s.setSoTimeout(1500);
        int ms = s.getSoTimeout();
        if (ms < 1500-10 || ms > 1500+10) {
            fail("suspicious timeout: " + ms);
        }
        s.close();
        try {
            s.getSoTimeout();
            fail("SocketException was not thrown.");
        } catch (SocketException expected) {
        }
        try {
            s.setSoTimeout(1000);
            fail("SocketException was not thrown.");
        } catch (SocketException expected) {
        }
    }

    public void test_toString() throws Exception {
        s = new ServerSocket(0);
        int portNumber = s.getLocalPort();
        assertTrue(s.toString().contains("" + portNumber));
        s.close();
    }

    public void test_setReuseAddressZ() throws IOException {
        ServerSocket newSocket = new ServerSocket();
        newSocket.close();
        try {
            newSocket.setReuseAddress(true);
            fail("SocketException was not thrown.");
        } catch(SocketException expected) {
        }
    }

    public void test_getReuseAddress() throws IOException {
        ServerSocket newSocket = new ServerSocket();
        newSocket.close();
        try {
            newSocket.getReuseAddress();
            fail("SocketException was not thrown.");
        } catch(SocketException e) {
            //expected
        }
    }

    public void test_setReceiveBufferSizeI() throws IOException {
        ServerSocket newSocket = new ServerSocket();
        newSocket.close();
        try {
            newSocket.setReceiveBufferSize(10);
            fail("SocketException was not thrown.");
        } catch(SocketException se) {
            //expected
        }
    }

    public void test_getReceiveBufferSize() throws IOException {
        ServerSocket newSocket = new ServerSocket();
        newSocket.close();
        try {
            newSocket.getReceiveBufferSize();
            fail("SocketException was not thrown.");
        } catch (SocketException e) {
            //expected
        }
    }

    protected void tearDown() {
        try {
            if (s != null)
                s.close();
            if (sconn != null)
                sconn.close();
            if (t != null)
                t.interrupt();
        } catch (Exception e) {
        }
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void startClient(int port) {
        t = new Thread(new SSClient(port), "SSClient");
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Exception during startClinet()" + e.toString());
        }
    }

    class MockSocketImpl extends SocketImpl {
        public MockSocketImpl() {
            isCreateCalled = true;
        }

        protected void create(boolean arg0) throws IOException {
        }

        protected void connect(String arg0, int arg1) throws IOException {
        }

        protected void connect(InetAddress arg0, int arg1) throws IOException {
        }

        protected void connect(SocketAddress arg0, int arg1) throws IOException {
        }

        protected void bind(InetAddress arg0, int arg1) throws IOException {
        }

        protected void listen(int arg0) throws IOException {
        }

        protected void accept(SocketImpl arg0) throws IOException {
        }

        protected InputStream getInputStream() throws IOException {
            return null;
        }

        protected OutputStream getOutputStream() throws IOException {
            return null;
        }

        protected int available() throws IOException {
            return 0;
        }

        protected void close() throws IOException {
        }

        protected void sendUrgentData(int arg0) throws IOException {
        }

        public void setOption(int arg0, Object arg1) throws SocketException {
        }

        public Object getOption(int arg0) throws SocketException {
            return null;
        }
    }
}
