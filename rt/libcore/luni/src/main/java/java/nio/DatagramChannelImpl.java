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

package java.nio;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PlainDatagramSocketImpl;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import libcore.io.ErrnoException;
import libcore.io.IoBridge;
import libcore.io.IoUtils;
import libcore.io.Libcore;
import libcore.util.EmptyArray;

/*
 * The default implementation class of java.nio.channels.DatagramChannel.
 */
class DatagramChannelImpl extends DatagramChannel implements FileDescriptorChannel {
    // The fd to interact with native code
    private final FileDescriptor fd;

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

    private final Object readLock = new Object();
    private final Object writeLock = new Object();

    /*
     * Constructor
     */
    protected DatagramChannelImpl(SelectorProvider selectorProvider) throws IOException {
        super(selectorProvider);
        fd = IoBridge.socket(false);
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
        if (socket == null) {
            socket = new DatagramSocketAdapter(new PlainDatagramSocketImpl(fd, localPort), this);
        }
        return socket;
    }

    /**
     * Returns the local address to which the socket is bound.
     */
    InetAddress getLocalAddress() {
        return IoBridge.getSocketLocalAddress(fd);
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
    synchronized public DatagramChannel connect(SocketAddress address) throws IOException {
        // must open
        checkOpen();
        // status must be un-connected.
        if (connected) {
            throw new IllegalStateException();
        }

        // check the address
        InetSocketAddress inetSocketAddress = SocketChannelImpl.validateAddress(address);
        try {
            begin();
            IoBridge.connect(fd, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
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
        try {
            Libcore.os.connect(fd, InetAddress.UNSPECIFIED, 0);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
        if (socket != null) {
            socket.disconnect();
        }
        return this;
    }

    @Override
    public SocketAddress receive(ByteBuffer target) throws IOException {
        target.checkWritable();
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
            end(retAddr != null);
        }
        return retAddr;
    }

    private SocketAddress receiveImpl(ByteBuffer target, boolean loop) throws IOException {
        SocketAddress retAddr = null;
        DatagramPacket receivePacket;
        int oldposition = target.position();
        int received = 0;
        // TODO: disallow mapped buffers and lose this conditional?
        if (target.hasArray()) {
            receivePacket = new DatagramPacket(target.array(), target.position() + target.arrayOffset(), target.remaining());
        } else {
            receivePacket = new DatagramPacket(new byte[target.remaining()], target.remaining());
        }
        do {
            received = IoBridge.recvfrom(false, fd, receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), 0, receivePacket, isConnected());
            if (receivePacket != null && receivePacket.getAddress() != null) {
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

    private SocketAddress receiveDirectImpl(ByteBuffer target, boolean loop) throws IOException {
        SocketAddress retAddr = null;
        DatagramPacket receivePacket = new DatagramPacket(EmptyArray.BYTE, 0);
        int oldposition = target.position();
        int received = 0;
        do {
            received = IoBridge.recvfrom(false, fd, target, 0, receivePacket, isConnected());
            if (receivePacket != null && receivePacket.getAddress() != null) {
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

    @Override
    public int send(ByteBuffer source, SocketAddress socketAddress) throws IOException {
        checkNotNull(source);
        checkOpen();

        InetSocketAddress isa = (InetSocketAddress) socketAddress;
        if (isa.getAddress() == null) {
            throw new IOException();
        }

        if (isConnected() && !connectAddress.equals(isa)) {
            throw new IllegalArgumentException();
        }

        synchronized (writeLock) {
            int sendCount = 0;
            try {
                begin();
                int oldPosition = source.position();
                sendCount = IoBridge.sendto(fd, source, 0, isa.getAddress(), isa.getPort());
                if (sendCount > 0) {
                    source.position(oldPosition + sendCount);
                }
            } finally {
                end(sendCount >= 0);
            }
            return sendCount;
        }
    }

    @Override
    public int read(ByteBuffer target) throws IOException {
        target.checkWritable();
        checkOpenConnected();

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

    @Override
    public long read(ByteBuffer[] targets, int offset, int length) throws IOException {
        Arrays.checkOffsetAndCount(targets.length, offset, length);

        // status must be open and connected
        checkOpenConnected();
        int totalCount = FileChannelImpl.calculateTotalRemaining(targets, offset, length, true);
        if (totalCount == 0) {
            return 0;
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
    private int readImpl(ByteBuffer dst) throws IOException {
        synchronized (readLock) {
            int readCount = 0;
            try {
                begin();
                readCount = IoBridge.recvfrom(false, fd, dst, 0, null, isConnected());
            } catch (InterruptedIOException e) {
                // InterruptedIOException will be thrown when timeout.
                return 0;
            } finally {
                end(readCount > 0);
            }
            return readCount;
        }
    }

    @Override public int write(ByteBuffer src) throws IOException {
        checkNotNull(src);
        checkOpenConnected();
        if (!src.hasRemaining()) {
            return 0;
        }

        int writeCount = writeImpl(src);
        if (writeCount > 0) {
            src.position(src.position() + writeCount);
        }
        return writeCount;
    }

    /**
     * @see java.nio.channels.DatagramChannel#write(java.nio.ByteBuffer[], int,
     *      int)
     */
    @Override
    public long write(ByteBuffer[] sources, int offset, int length) throws IOException {
        Arrays.checkOffsetAndCount(sources.length, offset, length);

        // status must be open and connected
        checkOpenConnected();
        int count = FileChannelImpl.calculateTotalRemaining(sources, offset, length, false);
        if (count == 0) {
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

    private int writeImpl(ByteBuffer buf) throws IOException {
        synchronized (writeLock) {
            int result = 0;
            try {
                begin();
                result = IoBridge.sendto(fd, buf, 0, null, 0);
            } finally {
                end(result > 0);
            }
            return result;
        }
    }

    @Override protected synchronized void implCloseSelectableChannel() throws IOException {
        connected = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        } else {
            IoBridge.closeSocket(fd);
        }
    }

    @Override protected void implConfigureBlocking(boolean blocking) throws IOException {
        synchronized (blockingLock()) {
            IoUtils.setBlocking(fd, blocking);
        }
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
        if (source == null) {
            throw new NullPointerException();
        }
    }

    /*
     * Get the fd for internal use.
     */
    public FileDescriptor getFD() {
        return fd;
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
            if (channelImpl.connectAddress == null) {
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
            if (channelImpl.connectAddress == null) {
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
