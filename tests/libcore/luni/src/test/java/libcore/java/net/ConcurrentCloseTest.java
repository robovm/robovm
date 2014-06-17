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

package libcore.java.net;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import tests.net.StuckServer;

/**
 * Test that Socket.close called on another thread interrupts a thread that's blocked doing
 * network I/O.
 */
public class ConcurrentCloseTest extends junit.framework.TestCase {
    public void test_accept() throws Exception {
        ServerSocket ss = new ServerSocket(0);
        new Killer(ss).start();
        try {
            System.err.println("accept...");
            Socket s = ss.accept();
            fail("accept returned " + s + "!");
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        }
    }

    public void test_connect() throws Exception {
        StuckServer ss = new StuckServer(false);
        Socket s = new Socket();
        new Killer(s).start();
        try {
            System.err.println("connect...");
            s.connect(ss.getLocalSocketAddress());
            fail("connect returned: " + s + "!");
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        } finally {
            ss.close();
        }
    }

    public void test_connect_timeout() throws Exception {
        StuckServer ss = new StuckServer(false);
        Socket s = new Socket();
        new Killer(s).start();
        try {
            System.err.println("connect (with timeout)...");
            s.connect(ss.getLocalSocketAddress(), 3600 * 1000);
            fail("connect returned: " + s + "!");
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        } finally {
            ss.close();
        }
    }

    public void test_connect_nonBlocking() throws Exception {
        StuckServer ss = new StuckServer(false);
        SocketChannel s = SocketChannel.open();
        new Killer(s.socket()).start();
        try {
            System.err.println("connect (non-blocking)...");
            s.configureBlocking(false);
            s.connect(ss.getLocalSocketAddress());
            while (!s.finishConnect()) {
                // Spin like a mad thing!
            }
            fail("connect returned: " + s + "!");
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        } catch (AsynchronousCloseException alsoOkay) {
            // See below.
        } catch (ClosedChannelException alsoOkay) {
            // For now, I'm assuming that we're happy as long as we get any reasonable exception.
            // It may be that we're supposed to guarantee only one or the other.
        } finally {
            ss.close();
        }
    }

    public void test_read() throws Exception {
        SilentServer ss = new SilentServer();
        Socket s = new Socket();
        s.connect(ss.getLocalSocketAddress());
        new Killer(s).start();
        try {
            System.err.println("read...");
            int i = s.getInputStream().read();
            fail("read returned: " + i);
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        }
        ss.close();
    }

    public void test_read_multiple() throws Throwable {
        SilentServer ss = new SilentServer();
        final Socket s = new Socket();
        s.connect(ss.getLocalSocketAddress());

        // We want to test that we unblock *all* the threads blocked on a socket, not just one.
        // We know the implementation uses the same mechanism for all blocking calls, so we just
        // test read(2) because it's the easiest to test. (recv(2), for example, is only accessible
        // from Java via a synchronized method.)
        final ArrayList<Thread> threads = new ArrayList<Thread>();
        final List<Throwable> thrownExceptions = new CopyOnWriteArrayList<Throwable>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            System.err.println("read...");
                            int i = s.getInputStream().read();
                            fail("read returned: " + i);
                        } catch (SocketException expected) {
                            assertEquals("Socket closed", expected.getMessage());
                        }
                    } catch (Throwable ex) {
                        thrownExceptions.add(ex);
                    }
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }
        new Killer(s).start();
        for (Thread t : threads) {
            t.join();
        }
        for (Throwable exception : thrownExceptions) {
            throw exception;
        }

        ss.close();
    }

    public void test_recv() throws Exception {
        DatagramSocket s = new DatagramSocket();
        byte[] buf = new byte[200];
        DatagramPacket p = new DatagramPacket(buf, 200);
        new Killer(s).start();
        try {
            System.err.println("receive...");
            s.receive(p);
            fail("receive returned!");
        } catch (SocketException expected) {
            assertEquals("Socket closed", expected.getMessage());
        }
    }

    public void test_write() throws Exception {
        final SilentServer ss = new SilentServer(128); // Minimal receive buffer size.
        Socket s = new Socket();

        // Set the send buffer size really small, to ensure we block.
        int sendBufferSize = 1024;
        s.setSendBufferSize(sendBufferSize);
        sendBufferSize = s.getSendBufferSize(); // How big is the buffer really, Linux?

        // Linux still seems to accept more than it should.
        // How much seems to differ from device to device, but I've yet to see anything accept
        // twice as much again.
        sendBufferSize *= 2;

        s.connect(ss.getLocalSocketAddress());
        new Killer(s).start();
        try {
            System.err.println("write...");
            // Write too much so the buffer is full and we block,
            // waiting for the server to read (which it never will).
            // If the asynchronous close fails, we'll see a test timeout here.
            byte[] buf = new byte[sendBufferSize];
            s.getOutputStream().write(buf);
            fail();
        } catch (SocketException expected) {
            // We throw "Connection reset by peer", which I don't _think_ is a problem.
            // assertEquals("Socket closed", expected.getMessage());
        }
        ss.close();
    }

    // This server accepts connections, but doesn't read or write anything.
    // It holds on to the Socket connecting to the client so it won't be GCed.
    // Call "close" to close both the server socket and its client connection.
    static class SilentServer {
        private final ServerSocket ss;
        private Socket client;

        public SilentServer() throws IOException {
            this(0);
        }

        public SilentServer(int receiveBufferSize) throws IOException {
            ss = new ServerSocket(0);
            if (receiveBufferSize != 0) {
                ss.setReceiveBufferSize(receiveBufferSize);
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        client = ss.accept();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }

        public SocketAddress getLocalSocketAddress() {
            return ss.getLocalSocketAddress();
        }

        public void close() throws IOException {
            client.close();
            ss.close();
        }
    }

    // This thread calls the "close" method on the supplied T after 2s.
    static class Killer<T> extends Thread {
        private final T s;

        public Killer(T s) {
            this.s = s;
        }

        public void run() {
            try {
                System.err.println("sleep...");
                Thread.sleep(2000);
                System.err.println("close...");
                // RoboVM note: The original code just called invoke() here
                // without setAccessible(true). For the test_connect_nonBlocking()
                // test s is a SocketChannelImpl$SocketAdapter which isn't
                // accessible to Killer so an IllegalAccessException was thrown
                // on RoboVM. invoke() on Android doesn't seem to do enough
                // security checks since the code apparently worked on Dalvik.
                Method m = s.getClass().getMethod("close");
                m.setAccessible(true);
                m.invoke(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
