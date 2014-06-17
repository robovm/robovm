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

package org.apache.harmony.nio.tests.java.nio.channels;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.spi.SelectorProvider;
import java.security.Permission;

import junit.framework.TestCase;
import tests.support.Support_PortManager;

/**
 * Test for DatagramChannel
 *
 */
public class DatagramChannelTest extends TestCase {

    private static final int CAPACITY_NORMAL = 200;

    private static final int CAPACITY_1KB = 1024;

    private static final int CAPACITY_64KB = 65536;

    private static final int CAPACITY_ZERO = 0;

    private static final int CAPACITY_ONE = 1;

    private static final int TIME_UNIT = 500;

    private InetSocketAddress localAddr1;

    private InetSocketAddress localAddr2;

    private DatagramChannel channel1;

    private DatagramChannel channel2;

    private DatagramSocket datagramSocket1;

    private DatagramSocket datagramSocket2;

    // The port to be used in test cases.
    private int testPort;

    protected void setUp() throws Exception {
        super.setUp();
        this.channel1 = DatagramChannel.open();
        this.channel2 = DatagramChannel.open();
        int[] ports = Support_PortManager.getNextPortsForUDP(5);
        this.localAddr1 = new InetSocketAddress("127.0.0.1", ports[0]);
        this.localAddr2 = new InetSocketAddress("127.0.0.1", ports[1]);
        this.datagramSocket1 = new DatagramSocket(ports[2]);
        this.datagramSocket2 = new DatagramSocket(ports[3]);
        testPort = ports[4];
    }

    protected void tearDown() throws Exception {
        if (null != this.channel1) {
            try {
                this.channel1.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.channel2) {
            try {
                this.channel2.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.datagramSocket1) {
            try {
                this.datagramSocket1.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.datagramSocket2) {
            try {
                this.datagramSocket2.close();
            } catch (Exception e) {
                //ignore
            }
        }
        localAddr1 = null;
        localAddr2 = null;
        super.tearDown();
    }

    // -------------------------------------------------------------------
    // Test for methods in abstract class.
    // -------------------------------------------------------------------
    /*
     * Test method for 'java.nio.channels.DatagramChannel.validOps()'
     */
    public void testValidOps() {
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);
        int val = this.channel1.validOps();
        assertEquals(5, val);
        assertEquals(val, testMock.validOps());
        assertEquals(val, testMocknull.validOps());
    }

    /*
     * Test method for 'java.nio.channels.DatagramChannel.open()'
     */
    public void testOpen() {
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);
        assertNull(testMocknull.provider());
        assertNotNull(testMock.provider());
        assertEquals(this.channel1.provider(), testMock.provider());
        assertEquals(5, testMock.validOps());
    }

    /*
     * Test method for 'java.nio.channels.DatagramChannel.read(ByteBuffer)'
     */
    public void testReadByteBufferArray() throws IOException {
        final int testNum = 0;
        long readres = testNum;
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);
        int bufSize = 10;
        ByteBuffer[] readBuf = null;
        try {
            this.channel1.read(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            readres = testMock.read(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        readBuf = new ByteBuffer[bufSize];
        try {
            readres = this.channel1.read(readBuf);
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        readres = testMock.read(readBuf);
        assertEquals(testNum, readres);
        readres = testMocknull.read(readBuf);
        assertEquals(testNum, readres);
    }

    /*
     * Test method for 'java.nio.channels.DatagramChannel.read(ByteBuffer)'
     */
    public void testReadByteBufferArray_BufNull() throws IOException {
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);

        ByteBuffer[] readBuf = null;
        try {
            this.channel1.read(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMock.read(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMocknull.read(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /*
     * Test method for 'java.nio.channels.DatagramChannel.write(ByteBuffer)'
     */
    public void testWriteByteBuffer() throws IOException {
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);
        int bufSize = 10;
        ByteBuffer[] readBuf = null;
        try {
            this.channel1.write(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMock.write(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        readBuf = new ByteBuffer[bufSize];
        try {
            this.channel1.write(readBuf);
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        long writeres = 0;
        writeres = testMock.write(readBuf);

        assertEquals(0, writeres);
        writeres = testMocknull.write(readBuf);
        assertEquals(0, writeres);
    }

    /*
     * Test method for 'java.nio.channels.DatagramChannel.write(ByteBuffer)'
     */
    public void testWriteByteBuffer_Bufnull() throws IOException {
        MockDatagramChannel testMock = new MockDatagramChannel(SelectorProvider
                .provider());
        MockDatagramChannel testMocknull = new MockDatagramChannel(null);
        ByteBuffer[] readBuf = null;
        try {
            this.channel1.write(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMock.write(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMocknull.write(readBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    // -------------------------------------------------------------------
    // Test for socket()
    // -------------------------------------------------------------------

    /**
     * Test method for 'DatagramChannelImpl.socket()'
     *
     * @throws SocketException
     */
    public void testSocket_BasicStatusBeforeConnect() throws SocketException {
        assertFalse(this.channel1.isConnected());// not connected
        DatagramSocket s1 = this.channel1.socket();
        assertSocketBeforeConnect(s1);
        DatagramSocket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    /**
     * Test method for 'DatagramChannelImpl.socket()'
     *
     * @throws IOException
     */
    public void testSocket_Block_BasicStatusAfterConnect() throws IOException {
        this.channel1.connect(localAddr1);
        DatagramSocket s1 = this.channel1.socket();
        assertSocketAfterConnect(s1);
        DatagramSocket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    public void testSocket_NonBlock_BasicStatusAfterConnect()
            throws IOException {
        this.channel1.connect(localAddr1);
        this.channel1.configureBlocking(false);
        DatagramSocket s1 = this.channel1.socket();
        assertSocketAfterConnect(s1);
        DatagramSocket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    /**
     * Test method for 'DatagramChannelImpl.socket()'
     *
     * @throws IOException
     */
    public void testSocket_ActionsBeforeConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        DatagramSocket s = this.channel1.socket();
        assertSocketActionBeforeConnect(s);
    }

    /**
     * Test method for 'DatagramChannelImpl.socket()'
     *
     * @throws IOException
     */
    public void testSocket_Block_ActionsAfterConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        this.channel1.connect(localAddr1);
        DatagramSocket s = this.channel1.socket();
        assertSocketActionAfterConnect(s);
    }

    public void testSocket_NonBlock_ActionsAfterConnect() throws IOException {
        this.channel1.connect(localAddr1);
        this.channel1.configureBlocking(false);
        DatagramSocket s = this.channel1.socket();
        assertSocketActionAfterConnect(s);
    }

    private void assertSocketBeforeConnect(DatagramSocket s)
            throws SocketException {
        assertFalse(s.isBound());
        assertFalse(s.isClosed());
        assertFalse(s.isConnected());
        assertFalse(s.getBroadcast());
        assertFalse(s.getReuseAddress());
        assertNull(s.getInetAddress());
        assertTrue(s.getLocalAddress().isAnyLocalAddress());
        assertEquals(s.getLocalPort(), 0);
        assertNull(s.getLocalSocketAddress());
        assertEquals(s.getPort(), -1);
        assertTrue(s.getReceiveBufferSize() >= 8192);
        assertNull(s.getRemoteSocketAddress());
        assertFalse(s.getReuseAddress());
        assertTrue(s.getSendBufferSize() >= 8192);
        assertEquals(s.getSoTimeout(), 0);
        assertEquals(s.getTrafficClass(), 0);
    }

    private void assertSocketAfterConnect(DatagramSocket s)
            throws SocketException {
        assertTrue(s.isBound());
        assertFalse(s.isClosed());
        assertTrue(s.isConnected());
        assertFalse(s.getBroadcast());
        assertFalse(s.getReuseAddress());
        assertSame(s.getInetAddress(), localAddr1.getAddress());
        assertEquals(s.getLocalAddress(), localAddr1.getAddress());
        assertNotNull(s.getLocalSocketAddress());
        assertEquals(s.getPort(), localAddr1.getPort());
        assertTrue(s.getReceiveBufferSize() >= 8192);
        // not same , but equals
        assertNotSame(s.getRemoteSocketAddress(), (SocketAddress) localAddr1);
        assertEquals(s.getRemoteSocketAddress(), (SocketAddress) localAddr1);
        assertFalse(s.getReuseAddress());
        assertTrue(s.getSendBufferSize() >= 8192);
        assertEquals(s.getSoTimeout(), 0);
        assertEquals(s.getTrafficClass(), 0);
    }

    private void assertSocketActionBeforeConnect(DatagramSocket s)
            throws IOException {
        s.connect(localAddr2);
        assertFalse(this.channel1.isConnected());
        assertFalse(s.isConnected());

        s.disconnect();
        assertFalse(this.channel1.isConnected());
        assertFalse(s.isConnected());

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    private void assertSocketActionAfterConnect(DatagramSocket s)
            throws IOException {
        assertEquals(s.getPort(), localAddr1.getPort());
        s.connect(localAddr2);
        assertTrue(this.channel1.isConnected());
        assertTrue(s.isConnected());
        // not changed
        assertEquals(s.getPort(), localAddr1.getPort());

        s.disconnect();
        assertFalse(this.channel1.isConnected());
        assertFalse(s.isConnected());

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    // -------------------------------------------------------------------
    // Test for configureBlocking()
    // -------------------------------------------------------------------

    public void testConfigureBlocking_Read() throws Exception {
        assertTrue(this.channel1.isBlocking());
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY_1KB);
        new Thread() {
            public void run() {
                try {
                    sleep(TIME_UNIT * 5);
                    channel1.configureBlocking(false);
                    assertFalse(channel1.isBlocking());
                    datagramSocket1.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }.start();
        SocketAddress addr = channel1.receive(buf);
        assertNull(addr);
    }

    // -------------------------------------------------------------------
    // Test for isConnected()
    // -------------------------------------------------------------------

    /**
     * Test method for 'DatagramChannelImpl.isConnected()'
     *
     * @throws IOException
     */
    public void testIsConnected_WithServer() throws IOException {
        connectLocalServer();
        disconnectAfterConnected();
        this.datagramSocket1.close();
        this.channel1.close();
        assertFalse(this.channel1.isConnected());
    }

    // -------------------------------------------------------------------
    // Test for connect()
    // -------------------------------------------------------------------

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     */
    public void testConnect_BlockWithServer() throws IOException {
        // blocking mode
        assertTrue(this.channel1.isBlocking());
        connectLocalServer();
        datagramSocket1.close();
        disconnectAfterConnected();
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     */
    public void testConnect_BlockNoServer() throws IOException {
        connectWithoutServer();
        disconnectAfterConnected();
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     */
    public void testConnect_NonBlockWithServer() throws IOException {
        // Non blocking mode
        this.channel1.configureBlocking(false);
        connectLocalServer();
        datagramSocket1.close();
        disconnectAfterConnected();
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     */
    public void testConnect_Null() throws IOException {
        assertFalse(this.channel1.isConnected());
        try {
            this.channel1.connect(null);
            fail("Should throw an IllegalArgumentException here."); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     */
    public void testConnect_UnsupportedType() throws IOException {
        assertFalse(this.channel1.isConnected());
        class SubSocketAddress extends SocketAddress {
            private static final long serialVersionUID = 1L;

            public SubSocketAddress() {
                super();
            }
        }
        SocketAddress newTypeAddress = new SubSocketAddress();
        try {
            this.channel1.connect(newTypeAddress);
            fail("Should throw an UnsupportedAddressTypeException here.");
        } catch (UnsupportedAddressTypeException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     */
    public void testConnect_Unresolved() throws IOException {
        assertFalse(this.channel1.isConnected());
        InetSocketAddress unresolved = new InetSocketAddress(
                "unresolved address", 1080);
        try {
            this.channel1.connect(unresolved);
            fail("Should throw an UnresolvedAddressException here."); //$NON-NLS-1$
        } catch (UnresolvedAddressException e) {
            // OK.
        }
    }

    public void testConnect_EmptyHost() throws Exception {
        assertFalse(this.channel1.isConnected());

        assertEquals(this.channel1, this.channel1
                .connect(new InetSocketAddress("", 1081))); //$NON-NLS-1$

    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     *
     */
    public void testConnect_ClosedChannelException() throws IOException {
        assertFalse(this.channel1.isConnected());
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw ClosedChannelException."); //$NON-NLS-1$
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     *
     */
    public void testConnect_IllegalStateException() throws IOException {
        assertFalse(this.channel1.isConnected());
        this.channel1.connect(localAddr1);
        assertTrue(this.channel1.isConnected());
        // connect after connected.
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw IllegalStateException."); //$NON-NLS-1$
        } catch (IllegalStateException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.connect(SocketAddress)'
     *
     * @throws IOException
     *
     */
    public void testConnect_CheckOpenBeforeStatus() throws IOException {
        assertFalse(this.channel1.isConnected());
        this.channel1.connect(localAddr1);
        assertTrue(this.channel1.isConnected());
        // connect after connected.
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        // checking open is before checking status.
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw ClosedChannelException."); //$NON-NLS-1$
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    private void disconnectAfterConnected() throws IOException {
        assertTrue(this.channel1.isConnected());
        this.channel1.disconnect();
        assertFalse(this.channel1.isConnected());
    }

    private void disconnectAfterClosed() throws IOException {
        assertFalse(this.channel1.isOpen());
        assertFalse(this.channel1.isConnected());
        this.channel1.disconnect();
        assertFalse(this.channel1.isConnected());
    }

    private void connectLocalServer() throws IOException {
        assertFalse(this.channel1.isConnected());
        assertTrue(this.datagramSocket1.isBound());
        assertSame(this.channel1, this.channel1.connect(localAddr1));
        assertTrue(this.channel1.isConnected());
    }

    private void connectWithoutServer() throws IOException {
        assertFalse(this.channel1.isConnected());
        this.datagramSocket1.close();
        assertTrue(this.datagramSocket1.isClosed());
        assertSame(this.channel1, this.channel1.connect(localAddr1));
        assertTrue(this.channel1.isConnected());
    }

    // -------------------------------------------------------------------
    // Test for disconnect()
    // -------------------------------------------------------------------

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_BeforeConnect() throws IOException {
        assertFalse(this.channel1.isConnected());
        assertEquals(this.channel1, this.channel1.disconnect());
        assertFalse(this.channel1.isConnected());
    }

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_UnconnectedClosed() throws IOException {
        assertFalse(this.channel1.isConnected());
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        assertEquals(this.channel1, this.channel1.disconnect());
        assertFalse(this.channel1.isConnected());
    }

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_BlockWithServerChannelClosed()
            throws IOException {
        assertTrue(this.channel1.isBlocking());
        connectLocalServer();
        // disconnect after channel close
        this.channel1.close();
        disconnectAfterClosed();
    }

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_NonBlockWithServerChannelClosed()
            throws IOException {
        this.channel1.configureBlocking(false);
        connectLocalServer();
        // disconnect after channel close
        this.channel1.close();
        disconnectAfterClosed();
    }

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_BlockWithServerServerClosed() throws IOException {
        assertTrue(this.channel1.isBlocking());
        connectLocalServer();
        // disconnect after server close
        this.datagramSocket1.close();
        assertTrue(this.channel1.isOpen());
        assertTrue(this.channel1.isConnected());
        disconnectAfterConnected();
    }

    /**
     * Test method for 'DatagramChannelImpl.disconnect()'
     *
     * @throws IOException
     */
    public void testDisconnect_NonBlockWithServerServerClosed()
            throws IOException {
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.isBlocking());
        connectLocalServer();
        // disconnect after server close
        this.datagramSocket1.close();
        assertTrue(this.channel1.isOpen());
        assertTrue(this.channel1.isConnected());
        disconnectAfterConnected();
    }

    // -------------------------------------------------------------------
    // Test for receive(): Behavior Without Server.
    // -------------------------------------------------------------------

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedNull() throws Exception {
        assertFalse(this.channel1.isConnected());
        try {
            this.channel1.receive(null);
            fail("Should throw a NPE here."); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedReadonly() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL)
                .asReadOnlyBuffer();
        assertTrue(dst.isReadOnly());
        try {
            this.channel1.receive(dst);
            fail("Should throw an IllegalArgumentException here."); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedBufEmpty() throws Exception {
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        assertNull(this.channel1.receive(dst));
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedBufZero() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_ZERO);
        assertNull(this.channel1.receive(dst));
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedBufNotEmpty() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        // buf is not empty
        dst.put((byte) 88);
        assertEquals(dst.position() + CAPACITY_NORMAL - 1, dst.limit());
        assertNull(this.channel1.receive(dst));
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedBufFull() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_ONE);
        // buf is full
        dst.put((byte) 88);
        assertEquals(dst.position(), dst.limit());
        assertNull(this.channel1.receive(dst));
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedClose() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        try {
            assertNull(this.channel1.receive(dst));
            fail("Should throw a ClosedChannelException here."); //$NON-NLS-1$
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedCloseNull() throws Exception {
        assertFalse(this.channel1.isConnected());
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        // checking buffer before checking open
        try {
            this.channel1.receive(null);
            fail("Should throw a NPE here."); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_UnconnectedCloseReadonly() throws Exception {
        assertFalse(this.channel1.isConnected());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL)
                .asReadOnlyBuffer();
        assertTrue(dst.isReadOnly());
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        try {
            this.channel1.receive(dst);
            fail("Should throw an IllegalArgumentException here."); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerBufEmpty() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNonBlockNoServer(CAPACITY_NORMAL);
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_BlockNoServerNull() throws Exception {
        assertTrue(this.channel1.isBlocking());
        receiveNoServerNull();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerNull() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNoServerNull();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_BlockNoServerReadonly() throws Exception {
        assertTrue(this.channel1.isBlocking());
        receiveNoServerReadonly();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerReadonly() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNoServerReadonly();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerBufZero() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNonBlockNoServer(CAPACITY_ZERO);
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerBufNotEmpty() throws Exception {
        this.channel1.configureBlocking(false);
        connectWithoutServer();
        ByteBuffer dst = allocateNonEmptyBuf();
        assertNull(this.channel1.receive(dst));
    }


    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerBufFull() throws Exception {
        this.channel1.configureBlocking(false);
        connectWithoutServer();
        ByteBuffer dst = allocateFullBuf();
        assertNull(this.channel1.receive(dst));
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_BlockNoServerChannelClose() throws Exception {
        assertTrue(this.channel1.isBlocking());
        receiveNoServerChannelClose();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerChannelClose() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNoServerChannelClose();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_BlockNoServerCloseNull() throws Exception {
        assertTrue(this.channel1.isBlocking());
        receiveNoServerChannelCloseNull();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerCloseNull() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNoServerChannelCloseNull();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_NonBlockNoServerCloseReadonly() throws Exception {
        this.channel1.configureBlocking(false);
        receiveNoServerChannelCloseReadonly();
    }

    /**
     * Test method for 'DatagramChannelImpl.receive(ByteBuffer)'
     *
     * @throws Exception
     */
    public void testReceive_BlockNoServerCloseReadonly() throws Exception {
        assertTrue(this.channel1.isBlocking());
        receiveNoServerChannelCloseReadonly();
    }

    private void receiveNoServerNull() throws IOException {
        connectWithoutServer();
        try {
            this.channel1.receive(null);
            fail("Should throw a NPE here."); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // OK.
        }
    }

    private void receiveNoServerReadonly() throws IOException {
        connectWithoutServer();
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL)
                .asReadOnlyBuffer();
        assertTrue(dst.isReadOnly());
        try {
            this.channel1.receive(dst);
            fail("Should throw an IllegalArgumentException here."); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    private void receiveNonBlockNoServer(int size) throws IOException {
        connectWithoutServer();
        ByteBuffer dst = ByteBuffer.allocateDirect(size);
        assertNull(this.channel1.receive(dst));
    }

    private void receiveNoServerChannelClose() throws IOException {
        connectWithoutServer();
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        try {
            assertNull(this.channel1.receive(dst));
            fail("Should throw a ClosedChannelException here."); //$NON-NLS-1$
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    private void receiveNoServerChannelCloseNull() throws IOException {
        connectWithoutServer();
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        try {
            this.channel1.receive(null);
            fail("Should throw a NPE here."); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // OK.
        }
    }

    private void receiveNoServerChannelCloseReadonly() throws IOException {
        connectWithoutServer();
        this.channel1.close();
        assertFalse(this.channel1.isOpen());
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL)
                .asReadOnlyBuffer();
        assertTrue(dst.isReadOnly());
        try {
            this.channel1.receive(dst);
            fail("Should throw an IllegalArgumentException here."); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    private ByteBuffer allocateFullBuf() {
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_ONE);
        // buf is full
        dst.put((byte) 88);
        assertEquals(dst.position(), dst.limit());
        return dst;
    }

    private ByteBuffer allocateNonEmptyBuf() {
        ByteBuffer dst = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        // buf is not empty
        dst.put((byte) 88);
        dst.put((byte) 99);
        assertEquals(dst.position() + CAPACITY_NORMAL - 2, dst.limit());
        return dst;
    }

    // -------------------------------------------------------------------
    // Test for send(): Behavior without server.
    // -------------------------------------------------------------------

    private void sendDataBlocking(InetSocketAddress addr, ByteBuffer writeBuf)
            throws IOException {
        InetSocketAddress ipAddr = addr;
        assertEquals(CAPACITY_NORMAL, this.channel1.send(writeBuf, ipAddr));
        assertTrue(this.channel1.isOpen());
        assertTrue(this.channel1.isBlocking());
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
    }

    private void sendDataNonBlocking(InetSocketAddress addr, ByteBuffer writeBuf)
            throws IOException {
        InetSocketAddress ipAddr = addr;
        this.channel1.configureBlocking(false);
        assertEquals(CAPACITY_NORMAL, this.channel1.send(writeBuf, ipAddr));
        assertTrue(this.channel1.isOpen());
        assertFalse(this.channel1.isBlocking());
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
    }

    /*
     * Test method for 'DatagramChannelImpl.send(ByteBuffer, SocketAddress)'
     */
    public void testSend_NoServerBlockingCommon() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        sendDataBlocking(localAddr1, writeBuf);
    }

    public void testSend_NoServerNonblockingCommon() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        sendDataNonBlocking(localAddr1, writeBuf);
    }

    public void testSend_NoServerTwice() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        sendDataBlocking(localAddr1, writeBuf);
        // can not buffer twice!
        assertEquals(0, this.channel1.send(writeBuf, localAddr1));
        try {
            channel1.send(writeBuf, localAddr2);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
    }

    public void testSend_NoServerNonBlockingTwice() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        sendDataNonBlocking(localAddr1, writeBuf);
        // can not buffer twice!
        assertEquals(0, this.channel1.send(writeBuf, localAddr1));
        try {
            channel1.send(writeBuf, localAddr2);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
    }

    public void testSend_NoServerBufNull() throws IOException {
        try {
            sendDataBlocking(localAddr1, null);
            fail("Should throw a NPE here.");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testSend_NoServerBufNullTwice() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        try {
            sendDataBlocking(localAddr1, null);
            fail("Should throw a NPE here.");
        } catch (NullPointerException e) {
            // correct
        }
        sendDataBlocking(localAddr1, writeBuf);
        try {
            channel1.send(null, localAddr2);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testSend_NoServerAddrNull() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        try {
            sendDataBlocking(null, writeBuf);
            fail("Should throw a NPE here.");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testSend_NoServerAddrNullTwice() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        try {
            sendDataBlocking(null, writeBuf);
            fail("Should throw a NPE here.");
        } catch (NullPointerException e) {
            // correct
        }
        sendDataBlocking(localAddr1, writeBuf);
        try {
            channel1.send(writeBuf, null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    // -------------------------------------------------------------------
    // Test for receive()and send(): Send and Receive with Real Data
    // -------------------------------------------------------------------

    public void testReceiveSend_Block_Normal() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByChannel("some normal string in testReceiveSend_Normal",
                localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2,
                "some normal string in testReceiveSend_Normal");
    }

    public void testReceiveSend_Block_NotBound() throws Exception {
        // not bound
        sendByChannel("some normal string in testReceiveSend_Normal",
                localAddr2);
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY_NORMAL);
        assertNull(channel1.receive(buf));
        assertFalse(channel1.socket().isBound());
    }

    public void testReceiveSend_NonBlock_NotBound() throws Exception {
        // not bound
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        sendByChannel("some normal string in testReceiveSend_Normal",
                localAddr2);
        ByteBuffer buf = ByteBuffer.wrap(new byte[CAPACITY_NORMAL]);
        assertNull((InetSocketAddress) this.channel1.receive(buf));
    }

    public void testReceiveSend_Block_Normal_S2C() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByDatagramSocket(
                "some normal string in testReceiveSend_Normal_S2C", localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2,
                "some normal string in testReceiveSend_Normal_S2C");
    }

    public void testReceiveSend_Block_Normal_C2S() throws Exception {
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        String str1 = "some normal string in testReceiveSend_Normal_C2S";
        sendByChannel(str1, localAddr2);
        receiveByDatagramSocket(CAPACITY_NORMAL, localAddr2, str1);
    }

    public void testReceiveSend_NonBlock_Normal_C2S() throws Exception {
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        String str1 = "some normal string in testReceiveSend_Normal_C2S";
        sendByChannel(str1, localAddr2);
        receiveByDatagramSocket(CAPACITY_NORMAL, localAddr2, str1);
    }

    public void testReceiveSend_Normal_S2S() throws Exception {
        String msg = "normal string in testReceiveSend_Normal_S2S";
        this.datagramSocket1 = new DatagramSocket(testPort);
        DatagramPacket rdp = new DatagramPacket(msg.getBytes(), msg.length(),
                localAddr2);
        datagramSocket2 = new DatagramSocket(localAddr2.getPort());
        this.datagramSocket1.send(rdp);
        byte[] buf = new byte[CAPACITY_NORMAL];
        this.datagramSocket2.setSoTimeout(TIME_UNIT);
        rdp = new DatagramPacket(buf, buf.length);
        this.datagramSocket2.receive(rdp);
        assertEquals(new String(buf, 0, CAPACITY_NORMAL).trim(), msg);
    }

    public void testReceiveSend_Block_Empty() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByChannel("", localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_NonBlock_Empty() throws Exception {
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        this.channel1.socket().bind(localAddr2);
        sendByChannel("", localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_Block_Empty_S2C() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByDatagramSocket("", localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_NonBlock_Empty_S2C() throws Exception {
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        this.channel1.socket().bind(localAddr2);
        sendByDatagramSocket("", localAddr2);
        receiveByChannel(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_Block_Empty_C2S() throws Exception {
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        sendByChannel("", localAddr2);
        receiveByDatagramSocket(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_NonBlock_Empty_C2S() throws Exception {
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        sendByChannel("", localAddr2);
        receiveByDatagramSocket(CAPACITY_NORMAL, localAddr2, "");
    }

    public void testReceiveSend_Empty_S2S() throws Exception {
        String msg = "";
        this.datagramSocket1 = new DatagramSocket(testPort);
        DatagramPacket rdp = new DatagramPacket(msg.getBytes(), msg.length(),
                localAddr2);
        datagramSocket2 = new DatagramSocket(localAddr2.getPort());
        this.datagramSocket1.send(rdp);
        byte[] buf = new byte[CAPACITY_NORMAL];
        this.datagramSocket2.setSoTimeout(TIME_UNIT);
        rdp = new DatagramPacket(buf, buf.length);
        this.datagramSocket2.receive(rdp);
        assertEquals(new String(buf, 0, CAPACITY_NORMAL).trim(), msg);
    }

    public void testReceiveSend_Block_Oversize() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByChannel("0123456789", localAddr2);
        receiveByChannel(5, localAddr2, "01234");
    }

    public void testReceiveSend_Block_Oversize_C2S() throws Exception {
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        sendByChannel("0123456789", localAddr2);
        receiveByDatagramSocket(5, localAddr2, "01234");
    }

    public void testReceiveSend_NonBlock_Oversize_C2S() throws Exception {
        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);
        this.datagramSocket1 = new DatagramSocket(localAddr2.getPort());
        sendByChannel("0123456789", localAddr2);
        receiveByDatagramSocket(5, localAddr2, "01234");
    }

    public void testReceiveSend_Block_Oversize_S2C() throws Exception {
        this.channel1.socket().bind(localAddr2);
        sendByDatagramSocket("0123456789", localAddr2);
        receiveByChannel(5, localAddr2, "01234");
    }

    public void testReceiveSend_8K() throws Exception {
        StringBuffer str8k = new StringBuffer();
        for (int i = 0; i < 8 * CAPACITY_1KB; i++) {
            str8k.append('a');
        }
        String str = str8k.toString();
        this.channel1.socket().bind(localAddr2);
        sendByChannel(str, localAddr2);
        receiveByChannel(8 * CAPACITY_1KB, localAddr2, str);
    }

    public void testReceiveSend_64K() throws Exception {
        StringBuffer str64k = new StringBuffer();
        for (int i = 0; i < CAPACITY_64KB; i++) {
            str64k.append('a');
        }
        String str = str64k.toString();
        try {
            Thread.sleep(TIME_UNIT);
            channel2.send(ByteBuffer.wrap(str.getBytes()), localAddr1);
            fail("Should throw SocketException!");
        } catch (SocketException e) {
            //expected
        }
    }

    private void sendByChannel(String data, InetSocketAddress address)
            throws Exception {
        try {
            assertEquals(data.length(), this.channel2.send(ByteBuffer.wrap(data
                    .getBytes()), address));
        } finally {
            this.channel2.close();
        }
    }

    private void sendByDatagramSocket(String data, InetSocketAddress address)
            throws Exception {
        this.datagramSocket1 = new DatagramSocket(testPort);
        DatagramPacket rdp = new DatagramPacket(data.getBytes(), data.length(),
                address);
        this.datagramSocket1.send(rdp);
    }

    private void receiveByChannel(int bufSize, InetSocketAddress address,
            String expectedString) throws IOException {
        try {
            ByteBuffer buf = ByteBuffer.wrap(new byte[bufSize]);
            InetSocketAddress returnAddr = null;
            long startTime = System.currentTimeMillis();
            do {
                returnAddr = (InetSocketAddress) this.channel1.receive(buf);
                // continue loop when channel1 is non-blocking and no data was
                // received.
                if (channel1.isBlocking() || null != returnAddr) {
                    break;
                }
                // avoid dead loop
                assertTimeout(startTime, 10000);
            } while (true);
            int length = returnAddr.getAddress().getAddress().length;
            for (int i = 0; i < length; i++) {
                assertEquals(returnAddr.getAddress().getAddress()[i],
                        InetAddress.getByName("127.0.0.1").getAddress()[i]);
            }
            // port is NOT equal
            assertFalse(returnAddr.getPort() == address.getPort());
            assertEquals(new String(buf.array(), 0, bufSize).trim(),
                    expectedString);
        } finally {
            this.channel1.close();
        }
    }

    /*
     * Fails if the difference between current time and start time is greater
     * than timeout.
     */
    private void assertTimeout(long startTime, long timeout) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) > timeout) {
            fail("Timeout");
        }
    }

    private void receiveByDatagramSocket(int bufSize,
            InetSocketAddress address, String expectedString)
            throws IOException {
        byte[] buf = new byte[bufSize];
        this.datagramSocket1.setSoTimeout(6000);
        DatagramPacket rdp = new DatagramPacket(buf, buf.length);
        this.datagramSocket1.receive(rdp);
        assertEquals(new String(buf, 0, bufSize).trim(), expectedString);
    }

    public void testRead_NoSecurity() throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY_NORMAL);
        String strHello = "hello";
        localAddr1 = new InetSocketAddress("127.0.0.1", testPort);
        this.channel1.socket().bind(localAddr1);
        this.channel2.socket().bind(localAddr2);
        this.channel1.connect(localAddr2);
        this.channel2.send(ByteBuffer.wrap(strHello.getBytes()), localAddr1);
        assertEquals(strHello.length(), this.channel1.read(buf));
        assertAscii(buf, strHello);
    }

    public void testReceive_Peek_NoSecurity_Nonblocking() throws Exception {
        String strHello = "hello";
        localAddr1 = new InetSocketAddress("127.0.0.1", testPort);
        this.channel1.socket().bind(localAddr1);
        sendByChannel(strHello, localAddr1);
        this.channel1.configureBlocking(false);
        // for accepted addr, no problem.
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY_NORMAL);
        InetSocketAddress source = (InetSocketAddress) this.channel1.receive(buf);
        assertEquals(localAddr1.getAddress(), source.getAddress());
        assertAscii(buf, strHello);
    }

    private static void assertAscii(ByteBuffer b, String s) {
        assertEquals(s.length(), b.position());
        for (int i = 0; i < s.length(); ++i) {
            assertEquals(s.charAt(i), b.get(i));
        }
    }

    // -------------------------------------------------------------------
    // Test for write()
    // -------------------------------------------------------------------

    private void connectWriteBuf(InetSocketAddress ipAddr, ByteBuffer buf)
            throws IOException {
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        assertEquals(CAPACITY_NORMAL, this.channel1.write(buf));
        assertEquals(0, this.channel1.write(buf));
    }

    private void noconnectWrite(ByteBuffer buf) throws IOException {
        try {
            this.channel1.write(buf);
            fail("should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
    }

    /*
     * Test method for 'DatagramChannelImpl.write(ByteBuffer)'
     */
    public void testWriteByteBuffer_Block() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        connectWriteBuf(localAddr1, writeBuf);
    }

    public void testWriteByteBuffer_NonBlock() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        this.channel1.configureBlocking(false);
        connectWriteBuf(localAddr1, writeBuf);
    }

    public void testWriteByteBuffer_Block_closed() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        noconnectWrite(writeBuf);
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        this.channel1.close();
        try {
            channel1.write(writeBuf);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testWriteByteBuffer_NonBlock_closed() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        // non block mode
        this.channel1.configureBlocking(false);
        noconnectWrite(writeBuf);
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        this.channel1.close();
        try {
            channel1.write(writeBuf);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testWriteByteBuffer_Block_BufNull() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(0);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
        assertEquals(0, this.channel1.write(writeBuf));
        datagramSocket1.close();
        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testWriteByteBuffer_NonBlock_BufNull() throws IOException {
        ByteBuffer writeBuf = ByteBuffer.allocateDirect(0);
        InetSocketAddress ipAddr = localAddr1;

        // non block mode
        this.channel1.configureBlocking(false);

        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
        assertEquals(0, this.channel1.write(writeBuf));
        datagramSocket1.close();
        try {
            this.channel1.write((ByteBuffer) null);
            fail("Should throw NPE.");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /*
     * Test method for 'DatagramChannelImpl.write(ByteBuffer[], int, int)'
     */
    public void testWriteByteBufferArrayIntInt_Block() throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        writeBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.write(writeBuf, 0, 2);
            fail("Should throw NotYetConnectedException.");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(writeBuf, 0, 2));
        // cannot be buffered again!
        assertEquals(0, this.channel1.write(writeBuf, 0, 1));

    }

    public void testWriteByteBufferArrayIntInt_NonBlock() throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        writeBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        // non-block mode
        this.channel1.configureBlocking(false);
        try {
            this.channel1.write(writeBuf, 0, 2);
            fail("Should throw NotYetConnectedException.");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(writeBuf, 0, 2));
        // cannot be buffered again!
        assertEquals(0, this.channel1.write(writeBuf, 0, 1));

    }

    public void testWriteByteBufferArrayIntInt_NoConnectIndexBad()
            throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        writeBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.write(writeBuf, -1, 2);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            this.channel1.write(writeBuf, 0, -1);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(writeBuf, 0, 2));
        // cannot be buffered again!
        assertEquals(0, this.channel1.write(writeBuf, 0, 1));
    }

    public void testWriteByteBufferArrayIntInt_ConnectedIndexBad()
            throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        writeBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        try {
            this.channel1.write(writeBuf, -1, 2);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            this.channel1.write(writeBuf, 0, -1);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
    }

    public void testWriteByteBufferArrayIntInt_BufNullNoConnect()
            throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        try {
            this.channel1.write(null, 0, 2);
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            this.channel1.write(writeBuf, -1, 2);
            fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            this.channel1.write(writeBuf, 0, 3);
            fail();
        } catch (IndexOutOfBoundsException expected) {
        }
    }

    public void testWriteByteBufferArrayIntInt_BufNullConnect()
            throws IOException {
        ByteBuffer[] writeBuf = new ByteBuffer[2];
        writeBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        try {
            this.channel1.write(null, 0, 2);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            this.channel1.write(writeBuf, 0, 3);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        datagramSocket1.close();
        try {
            this.channel1.write(null, 0, 2);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    // -------------------------------------------------------------------
    // Test for read()
    // -------------------------------------------------------------------

    /*
     * Test method for 'DatagramChannelImpl.read(ByteBuffer)'
     */
    public void testReadByteBuffer() throws IOException {
        ByteBuffer readBuf = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        try {
            this.channel1.read(readBuf);
            fail("should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertTrue(this.channel1.isConnected());
        this.channel1.configureBlocking(false);
        // note : blocking-mode will make the read process endless!
        assertEquals(0, this.channel1.read(readBuf));
        this.channel1.close();
        try {
            this.channel1.read(readBuf);
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    public void testReadByteBuffer_bufNull() throws IOException {
        ByteBuffer readBuf = ByteBuffer.allocateDirect(0);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.read(readBuf);
            fail("should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        try {
            channel1.read((ByteBuffer) null);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.configureBlocking(false);
        // note : blocking-mode will make the read process endless!
        assertEquals(0, this.channel1.read(readBuf));
        datagramSocket1.close();
    }

    /*
     * Test method for 'DatagramChannelImpl.read(ByteBuffer[], int, int)'
     */
    public void testReadByteBufferArrayIntInt() throws IOException {
        ByteBuffer[] readBuf = new ByteBuffer[2];
        readBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        readBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.read(readBuf, 0, 2);
            fail("should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        this.channel1.configureBlocking(false);
        // note : blocking-mode will make the read process endless!
        assertEquals(0, this.channel1.read(readBuf, 0, 1));
        assertEquals(0, this.channel1.read(readBuf, 0, 2));
        datagramSocket1.close();
    }

    public void testReadByteBufferArrayIntInt_exceptions() throws IOException {
        //regression test for HARMONY-932
        try {
            DatagramChannel.open().read(new ByteBuffer[] {}, 2, Integer.MAX_VALUE);
            fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            DatagramChannel.open().read(new ByteBuffer[] {}, -1, 0);
            fail();
        } catch (IndexOutOfBoundsException expected) {
        }
        try {
            DatagramChannel.open().read((ByteBuffer[]) null, 0, 0);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void testReadByteBufferArrayIntInt_BufNull() throws IOException {
        ByteBuffer[] readBuf = new ByteBuffer[2];
        readBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        InetSocketAddress ipAddr = localAddr1;
        try {
            this.channel1.read(null, 0, 0);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.connect(ipAddr);
        assertTrue(this.channel1.isConnected());
        this.channel1.configureBlocking(false);
        // note : blocking-mode will make the read process endless!
        try {
            this.channel1.read(null, 0, 0);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        assertEquals(0, this.channel1.read(readBuf, 0, 1));
        try {
            this.channel1.read(readBuf, 0, 2);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            this.channel1.read(readBuf, 0, 3);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        datagramSocket1.close();
    }

    // -------------------------------------------------------------------
    // test read and write
    // -------------------------------------------------------------------

    public void testReadWrite_configureBlock() throws Exception {
        byte[] targetArray = new byte[2];
        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME_UNIT);
                    channel1.configureBlocking(false);
                    channel1.close();
                } catch (Exception e) {
                    //ignore
                }
            }
        }.start();
        try {
            this.channel1.read(targetBuf);
            fail("should throw AsynchronousCloseException");
        } catch (AsynchronousCloseException e) {
            // ok
        }
    }

    public void testReadWrite_Block_Zero() throws Exception {
        byte[] sourceArray = new byte[0];
        byte[] targetArray = new byte[0];
        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(0, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        int readCount = this.channel2.read(targetBuf);

        assertEquals(0, readCount);
    }

    public void testReadWrite_Block_Normal() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        readWriteReadData(this.channel1, sourceArray, this.channel2,
                targetArray, CAPACITY_NORMAL, "testReadWrite_Block_Normal");
    }

    public void testReadWrite_Block_Empty() throws Exception {
        // empty buf
        byte[] sourceArray = "".getBytes();
        byte[] targetArray = new byte[CAPACITY_NORMAL];

        // bind and connect

        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(0, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        // empty message let the reader blocked
        closeBlockedReaderChannel2(targetBuf);
    }

    public void testReadWrite_changeBlock_Empty() throws Exception {
        // empty buf
        byte[] sourceArray = "".getBytes();
        byte[] targetArray = new byte[CAPACITY_NORMAL];

        // bind and connect

        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(0, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        // empty message let the reader blocked
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME_UNIT);
                    channel2.configureBlocking(false);
                    Thread.sleep(TIME_UNIT * 5);
                    channel2.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }.start();
        try {
            assertTrue(this.channel2.isBlocking());
            this.channel2.read(targetBuf);
            fail("Should throw AsynchronousCloseException");
        } catch (AsynchronousCloseException e) {
            assertFalse(this.channel2.isBlocking());
            // OK.
        }
    }

    public void testReadWrite_Block_8KB() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_1KB * 8];
        byte[] targetArray = new byte[CAPACITY_1KB * 8];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        readWriteReadData(this.channel1, sourceArray, this.channel2,
                targetArray, 8 * CAPACITY_1KB, "testReadWrite_Block_8KB");
    }

    /*
     * sender write the sourceArray whose size is dataSize, and receiver read
     * the data into targetArray
     */
    private void readWriteReadData(DatagramChannel sender, byte[] sourceArray,
            DatagramChannel receiver, byte[] targetArray, int dataSize,
            String methodName) throws IOException {
        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(dataSize, sender.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);

        int count = 0;
        int total = 0;
        long beginTime = System.currentTimeMillis();
        while (total < dataSize && (count = receiver.read(targetBuf)) != -1) {
            total = total + count;
            // 3s timeout to avoid dead loop
            if (System.currentTimeMillis() - beginTime > 3000){
                break;
            }
        }

        assertEquals(dataSize, total);
        assertEquals(targetBuf.position(), total);
        targetBuf.flip();
        targetArray = targetBuf.array();
        for (int i = 0; i < targetArray.length; i++) {
            assertEquals(targetArray[i], (byte) i);
        }
    }

    public void testReadWrite_Block_64K() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_64KB];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        try {
            channel1.write(sourceBuf);
            fail("Should throw IOException");
        } catch (IOException e) {
            // too big
        }
    }

    public void testReadWrite_Block_DifferentAddr() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr1);// the different addr

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        // the wrong connected addr will make the read blocked.
        // we close the blocked channel
        closeBlockedReaderChannel2(targetBuf);
    }

    public void testReadWrite_Block_WriterNotBind() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        closeBlockedReaderChannel2(targetBuf);
    }

    public void testReadWrite_Block_WriterBindLater() throws Exception {

        byte[] targetArray = new byte[CAPACITY_NORMAL];

        // bind and connect
        // writer channel1 is bound later
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME_UNIT);
                    // bind later
                    byte[] sourceArray = new byte[CAPACITY_NORMAL];
                    for (int i = 0; i < sourceArray.length; i++) {
                        sourceArray[i] = (byte) i;
                    }
                    channel1.socket().bind(localAddr2);
                    channel1.connect(localAddr1);
                    // write later
                    ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
                    assertEquals(CAPACITY_NORMAL, channel1.write(sourceBuf));
                } catch (Exception e) {
                    // do nothing
                }
            }
        }.start();

        int count = 0;
        int total = 0;
        long beginTime = System.currentTimeMillis();
        while (total < CAPACITY_NORMAL && (count = channel2.read(targetBuf)) != -1) {
            total = total + count;
            // 3s timeout to avoid dead loop
            if (System.currentTimeMillis() - beginTime > 3000){
                break;
            }
        }

        assertEquals(CAPACITY_NORMAL, total);
        assertEquals(targetBuf.position(), total);
        targetBuf.flip();
        targetArray = targetBuf.array();
        for (int i = 0; i < targetArray.length; i++) {
            assertEquals(targetArray[i], (byte) i);
        }

    }

    public void testReadWrite_Block_ReaderNotBind() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        // reader channel2 is not bound
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        closeBlockedReaderChannel2(targetBuf);

    }

    private void closeBlockedReaderChannel2(ByteBuffer targetBuf)
            throws IOException {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME_UNIT);
                    channel2.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }.start();
        try {
            assertTrue(this.channel2.isBlocking());
            this.channel2.read(targetBuf);
            fail("Should throw AsynchronousCloseException");
        } catch (AsynchronousCloseException e) {
            // OK.
        }
    }

    // -------------------------------------------------------------------
    // Test read and write in non-block mode.
    // -------------------------------------------------------------------
    public void testReadWrite_NonBlock_Normal() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        readWriteReadData(this.channel1, sourceArray, this.channel2,
                targetArray, CAPACITY_NORMAL, "testReadWrite_NonBlock_Normal");
    }

    public void testReadWrite_NonBlock_8KB() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_1KB * 8];
        byte[] targetArray = new byte[CAPACITY_1KB * 8];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        readWriteReadData(this.channel1, sourceArray, this.channel2,
                targetArray, 8 * CAPACITY_1KB, "testReadWrite_NonBlock_8KB");
    }

    public void testReadWrite_NonBlock_DifferentAddr() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr1);// the different addr

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        assertEquals(0, this.channel2.read(targetBuf));
    }

    public void testReadWrite_NonBlock_WriterNotBind() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);

        // bind and connect
        this.channel1.connect(localAddr1);
        this.channel2.socket().bind(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        assertEquals(0, this.channel2.read(targetBuf));
    }

    public void testReadWrite_NonBlock_ReaderNotBind() throws Exception {
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        byte[] targetArray = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < sourceArray.length; i++) {
            sourceArray[i] = (byte) i;
        }

        this.channel1.configureBlocking(false);
        this.channel2.configureBlocking(false);

        // bind and connect
        this.channel1.socket().bind(localAddr2);
        this.channel1.connect(localAddr1);
        this.channel2.connect(localAddr2);

        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        assertEquals(CAPACITY_NORMAL, this.channel1.write(sourceBuf));

        // read
        ByteBuffer targetBuf = ByteBuffer.wrap(targetArray);
        assertEquals(0, this.channel2.read(targetBuf));
    }

    public void test_write_LBuffer_positioned() throws Exception {
        // Regression test for Harmony-683
        int position = 16;
        DatagramChannel dc = DatagramChannel.open();
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        dc.connect(localAddr1);
        // write
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        sourceBuf.position(position);
        assertEquals(CAPACITY_NORMAL - position, dc.write(sourceBuf));
    }

    public void test_send_LBuffer_LSocketAddress_PositionNotZero()
            throws Exception {
        // regression test for Harmony-701
        int CAPACITY_NORMAL = 256;
        int position = 16;
        DatagramChannel dc = DatagramChannel.open();
        byte[] sourceArray = new byte[CAPACITY_NORMAL];
        // send ByteBuffer whose position is not zero
        ByteBuffer sourceBuf = ByteBuffer.wrap(sourceArray);
        sourceBuf.position(position);
        int ret = dc.send(sourceBuf, localAddr1);
        // assert send (256 - 16) bytes
        assertEquals(CAPACITY_NORMAL - position, ret);
        // assert the position of ByteBuffer has been set
        assertEquals(CAPACITY_NORMAL, sourceBuf.position());
    }

    /**
     * @tests DatagramChannel#read(ByteBuffer[])
     */
    public void test_read_$LByteBuffer() throws Exception {
        // regression test for Harmony-754
        channel2.socket().bind(localAddr1);
        channel1.socket().bind(localAddr2);
        channel1.connect(localAddr1);
        channel2.connect(localAddr2);
        channel2.write(ByteBuffer.allocate(CAPACITY_NORMAL));

        ByteBuffer[] readBuf = new ByteBuffer[2];
        readBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        readBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);

        channel1.configureBlocking(true);
        assertEquals(CAPACITY_NORMAL, channel1.read(readBuf));
    }

    /**
     * @tests DatagramChannel#read(ByteBuffer[],int,int)
     */
    public void test_read_$LByteBufferII() throws Exception {
        // regression test for Harmony-754
        channel2.socket().bind(localAddr1);
        channel1.socket().bind(localAddr2);
        channel1.connect(localAddr1);
        channel2.connect(localAddr2);
        channel2.write(ByteBuffer.allocate(CAPACITY_NORMAL));

        ByteBuffer[] readBuf = new ByteBuffer[2];
        readBuf[0] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        readBuf[1] = ByteBuffer.allocateDirect(CAPACITY_NORMAL);

        channel1.configureBlocking(true);
        assertEquals(CAPACITY_NORMAL, channel1.read(readBuf,0,2));
    }

    /**
     * @tests DatagramChannel#read(ByteBuffer)
     */
    public void test_read_LByteBuffer_closed_nullBuf() throws Exception {
        // regression test for Harmony-754
        ByteBuffer c = null;
        DatagramChannel channel = DatagramChannel.open();
        channel.close();
        try{
            channel.read(c);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e){
            // expected
        }
    }

    /**
     * @tests DatagramChannel#read(ByteBuffer)
     */
    public void test_read_LByteBuffer_NotConnected_nullBuf() throws Exception {
        // regression test for Harmony-754
        ByteBuffer c = null;
        DatagramChannel channel = DatagramChannel.open();
        try{
            channel.read(c);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e){
            // expected
        }
    }

    /**
     * @tests DatagramChannel#read(ByteBuffer)
     */
    public void test_read_LByteBuffer_readOnlyBuf() throws Exception {
        // regression test for Harmony-754
        ByteBuffer c = ByteBuffer.allocate(1);
        DatagramChannel channel = DatagramChannel.open();
        try{
            channel.read(c.asReadOnlyBuffer());
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e){
        } catch (IllegalArgumentException e){
            // expected
        }
        channel.connect(localAddr1);
        try{
            channel.read(c.asReadOnlyBuffer());
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e){
            // expected
        }
    }

    /**
     * @tests DatagramChannel#send(ByteBuffer, SocketAddress)
     */
    public void test_send_LByteBuffer_LSocketAddress_closed() throws IOException{
        // regression test for Harmony-913
        channel1.close();
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY_NORMAL);
        try {
            channel1.send(buf, localAddr1);
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            //pass
        }
        try {
            channel1.send(null,localAddr1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            //pass
        }
        try {
            channel1.send(buf, null);
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            //pass
        }
        try {
            channel1.send(null, null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            //pass
        }
    }

    /**
     * @tests DatagramChannel#socket()
     */
    public void test_socket_IllegalBlockingModeException() throws Exception {
        // regression test for Harmony-1036
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        DatagramSocket socket = channel.socket();
        try {
            socket.send(null);
            fail("should throw IllegalBlockingModeException");
        } catch (IllegalBlockingModeException e) {
            // expected
        }
        try {
            socket.receive(null);
            fail("should throw IllegalBlockingModeException");
        } catch (IllegalBlockingModeException e) {
            // expected
        }
    }

    public void test_bounded_harmony6493() throws IOException {
        DatagramChannel server = DatagramChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 0);
        server.socket().bind(addr);
        SocketAddress boundedAddress = server.socket().getLocalSocketAddress();

        DatagramChannel client = DatagramChannel.open();
        ByteBuffer sent = ByteBuffer.allocate(1024);
        sent.put("test".getBytes());
        sent.flip();
        client.send(sent, boundedAddress);
        assertTrue(client.socket().isBound());

        server.close();
        client.close();
    }
}
