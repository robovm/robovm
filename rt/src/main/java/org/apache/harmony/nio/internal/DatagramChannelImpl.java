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

package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.spi.SelectorProvider;

import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.net.PlainDatagramSocketImpl;
import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.INetworkSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.util.ErrorCodeException;
import org.apache.harmony.nio.AddressUtil;

/*
 * The default implementation class of java.nio.channels.DatagramChannel.
 */
class DatagramChannelImpl extends DatagramChannel implements
        FileDescriptorHandler {

    // The singleton to do the native network operation.
    private static final INetworkSystem networkSystem = Platform
            .getNetworkSystem();

    // default timeout used to nonblocking mode.
    private static final int DEFAULT_TIMEOUT = 1;

    private static final int ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK = -211;

    private static final byte[] stubArray = new byte[0];

    // The fd to interact with native code
    private FileDescriptor fd;

    // Our internal DatagramSocket.
    private DatagramSocket socket = null;

    // The address to be connected.
    InetSocketAddress connectAddress = null;

    // local port
    private int localPort;

    // At first, uninitialized.
    boolean connected = false;

    // whether the socket is bound
    boolean isBound = false;

    private static class ReadLock {}
    private final Object readLock = new ReadLock();

    private static class WriteLock {}
    private final Object writeLock = new WriteLock();

    // used to store the trafficClass value which is simply returned
    // as the value that was set. We also need it to pass it to methods
    // that specify an address packets are going to be sent to
    private int trafficClass = 0;

    /*
     * Constructor
     */
    protected DatagramChannelImpl(SelectorProvider selectorProvider)
            throws IOException {
        super(selectorProvider);
        fd = new FileDescriptor();
        networkSystem.createDatagramSocket(fd, true);
    }

    /*
     * for native call
     */
    @SuppressWarnings("unused")
    private DatagramChannelImpl() {
        super(SelectorProvider.provider());
        fd = new FileDescriptor();
        connectAddress = new InetSocketAddress(0);
    }

    /*
     * Getting the internal DatagramSocket If we have not the socket, we create
     * a new one.
     */
    @Override
    synchronized public DatagramSocket socket() {
        if (null == socket) {
            socket = new DatagramSocketAdapter(
                    new PlainDatagramSocketImpl(fd, localPort), this);
        }
        return socket;
    }

    /**
     * Answer the local address from the IP stack. This method should not be
     * called directly as it does not check the security policy.
     * 
     * @return InetAddress the local address to which the socket is bound.
     * @see DatagramSocket
     */
    InetAddress getLocalAddress() {
        return networkSystem.getSocketLocalAddress(fd, NetUtil
                .preferIPv6Addresses());
    }

    /**
     * @see java.nio.channels.DatagramChannel#isConnected()
     */
    @Override
    synchronized public boolean isConnected() {
        return connected;
    }

    /**
     * @see java.nio.channels.DatagramChannel#connect(java.net.SocketAddress)
     */
    @Override
    synchronized public DatagramChannel connect(SocketAddress address)
            throws IOException {
        // must open
        checkOpen();
        // status must be un-connected.
        if (connected) {
            throw new IllegalStateException();
        }

        // check the address
        InetSocketAddress inetSocketAddress = SocketChannelImpl
                .validateAddress(address);

        // security check
        SecurityManager sm = System.getSecurityManager();
        if (null != sm) {
            if (inetSocketAddress.getAddress().isMulticastAddress()) {
                sm.checkMulticast(inetSocketAddress.getAddress());
            } else {
                sm.checkConnect(inetSocketAddress.getAddress().getHostName(),
                        inetSocketAddress.getPort());
            }
        }

        try {
            begin();
            networkSystem.connectDatagram(fd, inetSocketAddress.getPort(),
                    trafficClass, inetSocketAddress.getAddress());
        } catch (ConnectException e) {
            // ConnectException means connect fail, not exception
        } finally {
            end(true);
        }

        // set the connected address.
        connectAddress = inetSocketAddress;
        connected = true;
        isBound = true;
        return this;
    }

    /**
     * @see java.nio.channels.DatagramChannel#disconnect()
     */
    @Override
    synchronized public DatagramChannel disconnect() throws IOException {
        if (!isConnected() || !isOpen()) {
            return this;
        }
        connected = false;
        connectAddress = null;
        networkSystem.disconnectDatagram(fd);
        if (null != socket) {
            socket.disconnect();
        }
        return this;
    }

    /**
     * @see java.nio.channels.DatagramChannel#receive(java.nio.ByteBuffer)
     */
    @Override
    public SocketAddress receive(ByteBuffer target) throws IOException {
        // must not null and not readonly
        checkWritable(target);
        // must open
        checkOpen();

        if (!isBound) {
            return null;
        }

        SocketAddress retAddr = null;
        try {
            begin();

            // receive real data packet, (not peek)
            synchronized (readLock) {
                boolean loop = isBlocking();
                if (!target.isDirect()) {
                    retAddr = receiveImpl(target, loop);
                } else {
                    retAddr = receiveDirectImpl(target, loop);
                }
            }
        } catch (InterruptedIOException e) {
            // this line used in Linux
            return null;
        } finally {
            end(null != retAddr);
        }
        return retAddr;
    }

    private SocketAddress receiveImpl(ByteBuffer target, boolean loop)
            throws IOException {
        SocketAddress retAddr = null;
        DatagramPacket receivePacket;
        int oldposition = target.position();
        int received = 0;
        if (target.hasArray()) {
            receivePacket = new DatagramPacket(target.array(), target
                    .position()
                    + target.arrayOffset(), target.remaining());
        } else {
            receivePacket = new DatagramPacket(new byte[target.remaining()],
                    target.remaining());
        }
        do {
            if (isConnected()) {
                received = networkSystem.recvConnectedDatagram(fd,
                        receivePacket, receivePacket.getData(), receivePacket
                                .getOffset(), receivePacket.getLength(),
                        isBlocking() ? 0 : DEFAULT_TIMEOUT, false);
            } else {
                received = networkSystem.receiveDatagram(fd, receivePacket,
                        receivePacket.getData(), receivePacket.getOffset(),
                        receivePacket.getLength(), isBlocking() ? 0
                                : DEFAULT_TIMEOUT, false);
            }

            // security check
            SecurityManager sm = System.getSecurityManager();
            if (!isConnected() && null != sm) {
                try {
                    sm.checkAccept(receivePacket.getAddress().getHostAddress(),
                            receivePacket.getPort());
                } catch (SecurityException e) {
                    // do discard the datagram packet
                    receivePacket = null;
                }
            }
            if (null != receivePacket && null != receivePacket.getAddress()) {

                if (received > 0) {
                    if (target.hasArray()) {
                        target.position(oldposition + received);
                    } else {
                        // copy the data of received packet
                        target.put(receivePacket.getData(), 0, received);
                    }
                }
                retAddr = receivePacket.getSocketAddress();
                break;
            }
        } while (loop);
        return retAddr;
    }

    private SocketAddress receiveDirectImpl(ByteBuffer target, boolean loop)
            throws IOException {
        SocketAddress retAddr = null;
        DatagramPacket receivePacket = new DatagramPacket(stubArray, 0);
        int oldposition = target.position();
        int received = 0;
        do {
            long address = AddressUtil.getDirectBufferAddress(target);
            if (isConnected()) {
                received = networkSystem.recvConnectedDatagramDirect(fd,
                        receivePacket, address, target.position(), target
                                .remaining(), isBlocking() ? 0
                                : DEFAULT_TIMEOUT, false);
            } else {
                received = networkSystem.receiveDatagramDirect(fd,
                        receivePacket, address, target.position(), target
                                .remaining(), isBlocking() ? 0
                                : DEFAULT_TIMEOUT, false);
            }

            // security check
            SecurityManager sm = System.getSecurityManager();
            if (!isConnected() && null != sm) {
                try {
                    sm.checkAccept(receivePacket.getAddress().getHostAddress(),
                            receivePacket.getPort());
                } catch (SecurityException e) {
                    // do discard the datagram packet
                    receivePacket = null;
                }
            }
            if (null != receivePacket && null != receivePacket.getAddress()) {
                // copy the data of received packet
                if (received > 0) {
                    target.position(oldposition + received);
                }
                retAddr = receivePacket.getSocketAddress();
                break;
            }
        } while (loop);
        return retAddr;
    }

    /**
     * @see java.nio.channels.DatagramChannel#send(java.nio.ByteBuffer,
     *      java.net.SocketAddress)
     */
    @Override
    public int send(ByteBuffer source, SocketAddress socketAddress)
            throws IOException {
        // must not null
        checkNotNull(source);
        // must open
        checkOpen();

        // transfer socketAddress
        InetSocketAddress isa = (InetSocketAddress) socketAddress;
        if (null == isa.getAddress()) {
            throw new IOException();
        }

        if (isConnected()) {
            if (!connectAddress.equals(isa)) {
                throw new IllegalArgumentException();
            }
        } else {
            // not connected, check security
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                if (isa.getAddress().isMulticastAddress()) {
                    sm.checkMulticast(isa.getAddress());
                } else {
                    sm.checkConnect(isa.getAddress().getHostAddress(), isa
                            .getPort());
                }
            }
        }

        // the return value.
        int sendCount = 0;
        try {
            begin();
            byte[] array = null;
            int length = source.remaining();
            int oldposition = source.position();
            int start = oldposition;
            if (source.isDirect()) {
                synchronized (writeLock) {
                    long data_address = AddressUtil
                            .getDirectBufferAddress(source);
                    if (isConnected()) {
                        sendCount = networkSystem.sendConnectedDatagramDirect(
                                fd, data_address, start, length, false);
                    } else {
                        sendCount = networkSystem.sendDatagramDirect(fd,
                                data_address, start, length, isa.getPort(),
                                false, trafficClass, isa.getAddress());
                    }
                }
            } else {
                if (source.hasArray()) {
                    array = source.array();
                    start += source.arrayOffset();
                } else {
                    array = new byte[length];
                    source.get(array);
                    start = 0;
                }
                synchronized (writeLock) {
                    if (isConnected()) {
                        sendCount = networkSystem.sendConnectedDatagram(fd,
                                array, start, length, false);
                    } else {
                        sendCount = networkSystem.sendDatagram(fd, array,
                                start, length, isa.getPort(), false,
                                trafficClass, isa.getAddress());
                    }
                }
            }
            source.position(oldposition + sendCount);
            isBound = true;
            return sendCount;
        } finally {
            end(sendCount >= 0);
        }
    }

    /**
     * @see java.nio.channels.DatagramChannel#read(java.nio.ByteBuffer)
     */
    @Override
    public int read(ByteBuffer target) throws IOException {
        if (null == target) {
            throw new NullPointerException();
        }
        // status must be open and connected
        checkOpenConnected();
        // target buffer must be not null and not readonly
        checkWritable(target);

        if (!target.hasRemaining()) {
            return 0;
        }

        int readCount = 0;
        if (target.isDirect() || target.hasArray()) {
            readCount = readImpl(target);
            if (readCount > 0) {
                target.position(target.position() + readCount);
            }

        } else {
            byte[] readArray = new byte[target.remaining()];
            ByteBuffer readBuffer = ByteBuffer.wrap(readArray);
            readCount = readImpl(readBuffer);
            if (readCount > 0) {
                target.put(readArray, 0, readCount);
            }
        }
        return readCount;
    }

    /**
     * @see java.nio.channels.DatagramChannel#read(java.nio.ByteBuffer[], int,
     *      int)
     */
    @Override
    public long read(ByteBuffer[] targets, int offset, int length)
            throws IOException {
        if (length < 0 || offset < 0
                || (long) length + (long) offset > targets.length) {
            throw new IndexOutOfBoundsException();
        }

        // status must be open and connected
        checkOpenConnected();

        int totalCount = 0;
        for (int val = offset; val < length; val++) {
            // target buffer must be not null and not readonly
            checkWritable(targets[val]);
            totalCount += targets[val].remaining();
        }

        // read data to readBuffer, and then transfer data from readBuffer to
        // targets.
        ByteBuffer readBuffer = ByteBuffer.allocate(totalCount);
        int readCount;
        readCount = readImpl(readBuffer);
        int left = readCount;
        int index = offset;
        // transfer data from readBuffer to targets
        byte[] readArray = readBuffer.array();
        while (left > 0) {
            int putLength = Math.min(targets[index].remaining(), left);
            targets[index].put(readArray, readCount - left, putLength);
            index++;
            left -= putLength;
        }
        return readCount;
    }

    /*
     * read from channel, and store the result in the target.
     */
    private int readImpl(ByteBuffer readBuffer) throws IOException {
        synchronized (readLock) {
            int readCount = 0;
            try {
                begin();
                // timeout == 0 means block read.
                // DEFAULT_TIMEOUT is used in non-block mode.
                int timeout = isBlocking() ? 0 : DEFAULT_TIMEOUT;
                int start = readBuffer.position();
                int length = readBuffer.remaining();
                if (readBuffer.isDirect()) {
                    long address = AddressUtil
                            .getDirectBufferAddress(readBuffer);
                    if (isConnected()) {
                        readCount = networkSystem.recvConnectedDatagramDirect(
                                fd, null, address, start, length, timeout,
                                false);
                    } else {
                        readCount = networkSystem.receiveDatagramDirect(fd,
                                null, address, start, length, timeout, false);
                    }
                } else {
                    // the target is assured to have array.
                    byte[] target = readBuffer.array();
                    start += readBuffer.arrayOffset();
                    if (isConnected()) {
                        readCount = networkSystem.recvConnectedDatagram(fd,
                                null, target, start, length, timeout, false);
                    } else {
                        readCount = networkSystem.receiveDatagram(fd, null,
                                target, start, length, timeout, false);
                    }
                }
                return readCount;
            } catch (InterruptedIOException e) {
                // InterruptedIOException will be thrown when timeout.
                return 0;
            } finally {
                end(readCount > 0);
            }
        }
    }

    /**
     * @see java.nio.channels.DatagramChannel#write(java.nio.ByteBuffer)
     */
    @Override
    public int write(ByteBuffer source) throws IOException {
        // source buffer must be not null
        checkNotNull(source);
        // status must be open and connected
        checkOpenConnected();
        // return immediately if source is full
        if (!source.hasRemaining()) {
            return 0;
        }

        ByteBuffer writeBuffer = null;
        byte[] writeArray = null;
        int oldposition = source.position();
        int result;
        if (source.isDirect() || source.hasArray()) {
            writeBuffer = source;
        } else {
            writeArray = new byte[source.remaining()];
            source.get(writeArray);
            writeBuffer = ByteBuffer.wrap(writeArray);
        }
        result = writeImpl(writeBuffer);
        if (result > 0) {
            source.position(oldposition + result);
        }
        return result;
    }

    /**
     * @see java.nio.channels.DatagramChannel#write(java.nio.ByteBuffer[], int,
     *      int)
     */
    @Override
    public long write(ByteBuffer[] sources, int offset, int length)
            throws IOException {
        if (length < 0 || offset < 0
                || (long) length + (long) offset > sources.length) {
            throw new IndexOutOfBoundsException();
        }

        // status must be open and connected
        checkOpenConnected();
        int count = calculateByteBufferArray(sources, offset, length);
        if (0 == count) {
            return 0;
        }
        ByteBuffer writeBuf = ByteBuffer.allocate(count);
        for (int val = offset; val < length + offset; val++) {
            ByteBuffer source = sources[val];
            int oldPosition = source.position();
            writeBuf.put(source);
            source.position(oldPosition);
        }
        writeBuf.flip();
        int result = writeImpl(writeBuf);
        int val = offset;
        int written = result;
        while (result > 0) {
            ByteBuffer source = sources[val];
            int gap = Math.min(result, source.remaining());
            source.position(source.position() + gap);
            val++;
            result -= gap;
        }
        return written;
    }

    /*
     * Write the source. Return the count of bytes written.
     */
    private int writeImpl(ByteBuffer buf) throws IOException {
        synchronized (writeLock) {
            int result = 0;
            try {
                begin();
                int length = buf.remaining();
                int start = buf.position();

                if (buf.isDirect()) {
                    long address = AddressUtil.getDirectBufferAddress(buf);
                    result = networkSystem.sendConnectedDatagramDirect(fd,
                            address, start, length, isBound);
                } else {
                    // buf is assured to have array.
                    start += buf.arrayOffset();
                    result = networkSystem.sendConnectedDatagram(fd, buf
                            .array(), start, length, isBound);
                }
                return result;
            } catch (SocketException e) {
                if (e.getCause() instanceof ErrorCodeException) {
                    if (ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK == ((ErrorCodeException) e
                            .getCause()).getErrorCode()) {
                        return result;
                    }
                }
                throw e;
            } finally {
                end(result > 0);
            }
        }
    }

    /*
     * Do really closing action here.
     */
    @Override
    synchronized protected void implCloseSelectableChannel() throws IOException {
        connected = false;
        if (null != socket && !socket.isClosed()) {
            socket.close();
        } else {
            networkSystem.socketClose(fd);
        }
    }

    /**
     * @see java.nio.channels.spi.AbstractSelectableChannel#implConfigureBlocking(boolean)
     */
    @Override
    @SuppressWarnings("unused")
    protected void implConfigureBlocking(boolean blockingMode)
            throws IOException {
        // Do nothing here. For real read/write operation in nonblocking mode,
        // it uses select system call. Whether a channel is blocking can be
        // decided by isBlocking() method.
    }

    /*
     * Status check, must be open.
     */
    private void checkOpen() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    /*
     * Status check, must be open and connected, for read and write.
     */
    private void checkOpenConnected() throws IOException {
        checkOpen();
        if (!isConnected()) {
            throw new NotYetConnectedException();
        }
    }

    /*
     * Buffer check, must not null
     */
    private void checkNotNull(ByteBuffer source) {
        if (null == source) {
            throw new NullPointerException();
        }
    }

    /*
     * Buffer check, must not null and not read only buffer, for read and
     * receive.
     */
    private void checkWritable(ByteBuffer target) {
        // including checking of NPE.
        if (target.isReadOnly()) {
            throw new IllegalArgumentException();
        }
    }

    /*
     * Get the fd for internal use.
     */
    public FileDescriptor getFD() {
        return fd;
    }

    private int calculateByteBufferArray(ByteBuffer[] sources, int offset,
            int length) {
        int sum = 0;
        for (int val = offset; val < offset + length; val++) {
            sum += sources[val].remaining();
        }
        return sum;
    }

    /*
     * The adapter class of DatagramSocket
     */
    private static class DatagramSocketAdapter extends DatagramSocket {

        /*
         * The internal datagramChannelImpl.
         */
        private DatagramChannelImpl channelImpl;

        /*
         * Constructor initialize the datagramSocketImpl and datagramChannelImpl
         */
        DatagramSocketAdapter(DatagramSocketImpl socketimpl,
                DatagramChannelImpl channelImpl) {
            super(socketimpl);
            this.channelImpl = channelImpl;
        }

        /*
         * Get the internal datagramChannelImpl
         */
        @Override
        public DatagramChannel getChannel() {
            return channelImpl;
        }

        /**
         * @see java.net.DatagramSocket#isBound()
         */
        @Override
        public boolean isBound() {
            return channelImpl.isBound;
        }

        /**
         * @see java.net.DatagramSocket#isConnected()
         */
        @Override
        public boolean isConnected() {
            return channelImpl.isConnected();
        }

        /**
         * @see java.net.DatagramSocket#getInetAddress()
         */
        @Override
        public InetAddress getInetAddress() {
            if (null == channelImpl.connectAddress) {
                return null;
            }
            return channelImpl.connectAddress.getAddress();
        }

        /**
         * @see java.net.DatagramSocket#getLocalAddress()
         */
        @Override
        public InetAddress getLocalAddress() {
            return channelImpl.getLocalAddress();
        }

        /**
         * @see java.net.DatagramSocket#getPort()
         */
        @Override
        public int getPort() {
            if (null == channelImpl.connectAddress) {
                return -1;
            }
            return channelImpl.connectAddress.getPort();
        }

        /**
         * @see java.net.DatagramSocket#bind(java.net.SocketAddress)
         */
        @Override
        public void bind(SocketAddress localAddr) throws SocketException {
            if (channelImpl.isConnected()) {
                throw new AlreadyConnectedException();
            }
            super.bind(localAddr);
            channelImpl.isBound = true;
        }

        /**
         * @see java.net.DatagramSocket#receive(java.net.DatagramPacket)
         */
        @Override
        public void receive(DatagramPacket packet) throws IOException {
            if (!channelImpl.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            super.receive(packet);
        }

        /**
         * @see java.net.DatagramSocket#send(java.net.DatagramPacket)
         */
        @Override
        public void send(DatagramPacket packet) throws IOException {
            if (!channelImpl.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            super.send(packet);
        }

        /**
         * @see java.net.DatagramSocket#close()
         */
        @Override
        public void close() {
            synchronized (channelImpl) {
                if (channelImpl.isOpen()) {
                    try {
                        channelImpl.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
                super.close();
            }
        }

        /**
         * @see java.net.DatagramSocket#disconnect()
         */
        @Override
        public void disconnect() {
            try {
                channelImpl.disconnect();
            } catch (IOException e) {
                // Ignore
            }
            super.disconnect();
        }
    }
}
