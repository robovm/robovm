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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PlainSocketImpl;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketUtils;
import java.net.UnknownHostException;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import libcore.io.ErrnoException;
import libcore.io.Libcore;
import libcore.io.IoBridge;
import libcore.io.IoUtils;
import static libcore.io.OsConstants.*;

/*
 * The default implementation class of java.nio.channels.SocketChannel.
 */
class SocketChannelImpl extends SocketChannel implements FileDescriptorChannel {
    private static final int SOCKET_STATUS_UNINITIALIZED = -1;

    // Status before connect.
    private static final int SOCKET_STATUS_UNCONNECTED = 0;

    // Status connection pending.
    private static final int SOCKET_STATUS_PENDING = 1;

    // Status after connection success.
    private static final int SOCKET_STATUS_CONNECTED = 2;

    // Status closed.
    private static final int SOCKET_STATUS_CLOSED = 3;

    private final FileDescriptor fd;

    // Our internal Socket.
    private SocketAdapter socket = null;

    // The address to be connected.
    private InetSocketAddress connectAddress = null;

    private InetAddress localAddress = null;
    private int localPort;

    private int status = SOCKET_STATUS_UNINITIALIZED;

    // Whether the socket is bound.
    private volatile boolean isBound = false;

    private final Object readLock = new Object();

    private final Object writeLock = new Object();

    /*
     * Constructor for creating a connected socket channel.
     */
    public SocketChannelImpl(SelectorProvider selectorProvider) throws IOException {
        this(selectorProvider, true);
    }

    /*
     * Constructor for creating an optionally connected socket channel.
     */
    public SocketChannelImpl(SelectorProvider selectorProvider, boolean connect) throws IOException {
        super(selectorProvider);
        status = SOCKET_STATUS_UNCONNECTED;
        fd = (connect ? IoBridge.socket(true) : new FileDescriptor());
    }

    /*
     * Getting the internal Socket If we have not the socket, we create a new
     * one.
     */
    @Override
    synchronized public Socket socket() {
        if (socket == null) {
            try {
                InetAddress addr = null;
                int port = 0;
                if (connectAddress != null) {
                    addr = connectAddress.getAddress();
                    port = connectAddress.getPort();
                }
                socket = new SocketAdapter(new PlainSocketImpl(fd, localPort, addr, port), this);
            } catch (SocketException e) {
                return null;
            }
        }
        return socket;
    }

    @Override
    synchronized public boolean isConnected() {
        return status == SOCKET_STATUS_CONNECTED;
    }

    /*
     * Status setting used by other class.
     */
    synchronized void setConnected() {
        status = SOCKET_STATUS_CONNECTED;
    }

    void setBound(boolean flag) {
        isBound = flag;
    }

    @Override
    synchronized public boolean isConnectionPending() {
        return status == SOCKET_STATUS_PENDING;
    }

    @Override
    public boolean connect(SocketAddress socketAddress) throws IOException {
        // status must be open and unconnected
        checkUnconnected();

        // check the address
        InetSocketAddress inetSocketAddress = validateAddress(socketAddress);
        InetAddress normalAddr = inetSocketAddress.getAddress();
        int port = inetSocketAddress.getPort();

        // When connecting, map ANY address to localhost
        if (normalAddr.isAnyLocalAddress()) {
            normalAddr = InetAddress.getLocalHost();
        }

        boolean finished = false;
        try {
            if (isBlocking()) {
                begin();
            }
            finished = IoBridge.connect(fd, normalAddr, port);
            isBound = finished;
        } catch (IOException e) {
            if (e instanceof ConnectException && !isBlocking()) {
                status = SOCKET_STATUS_PENDING;
            } else {
                if (isOpen()) {
                    close();
                    finished = true;
                }
                throw e;
            }
        } finally {
            if (isBlocking()) {
                end(finished);
            }
        }

        initLocalAddressAndPort();
        connectAddress = inetSocketAddress;
        if (socket != null) {
            socket.socketImpl().initRemoteAddressAndPort(connectAddress.getAddress(),
                    connectAddress.getPort());
        }

        synchronized (this) {
            if (isBlocking()) {
                status = (finished ? SOCKET_STATUS_CONNECTED : SOCKET_STATUS_UNCONNECTED);
            } else {
                status = SOCKET_STATUS_PENDING;
            }
        }
        return finished;
    }

    private void initLocalAddressAndPort() {
        SocketAddress sa;
        try {
            sa = Libcore.os.getsockname(fd);
        } catch (ErrnoException errnoException) {
            throw new AssertionError(errnoException);
        }
        InetSocketAddress isa = (InetSocketAddress) sa;
        localAddress = isa.getAddress();
        localPort = isa.getPort();
        if (socket != null) {
            socket.socketImpl().initLocalPort(localPort);
        }
    }

    @Override
    public boolean finishConnect() throws IOException {
        synchronized (this) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            if (status == SOCKET_STATUS_CONNECTED) {
                return true;
            }
            if (status != SOCKET_STATUS_PENDING) {
                throw new NoConnectionPendingException();
            }
        }

        boolean finished = false;
        try {
            begin();
            InetAddress inetAddress = connectAddress.getAddress();
            int port = connectAddress.getPort();
            finished = IoBridge.isConnected(fd, inetAddress, port, 0, 0); // Return immediately.
            isBound = finished;
        } catch (ConnectException e) {
            if (isOpen()) {
                close();
                finished = true;
            }
            throw e;
        } finally {
            end(finished);
        }

        synchronized (this) {
            status = (finished ? SOCKET_STATUS_CONNECTED : status);
            isBound = finished;
        }
        return finished;
    }

    void finishAccept() {
        initLocalAddressAndPort();
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        dst.checkWritable();
        checkOpenConnected();
        if (!dst.hasRemaining()) {
            return 0;
        }
        return readImpl(dst);
    }

    @Override
    public long read(ByteBuffer[] targets, int offset, int length) throws IOException {
        Arrays.checkOffsetAndCount(targets.length, offset, length);
        checkOpenConnected();
        int totalCount = FileChannelImpl.calculateTotalRemaining(targets, offset, length, true);
        if (totalCount == 0) {
            return 0;
        }
        byte[] readArray = new byte[totalCount];
        ByteBuffer readBuffer = ByteBuffer.wrap(readArray);
        int readCount;
        // read data to readBuffer, and then transfer data from readBuffer to targets.
        readCount = readImpl(readBuffer);
        readBuffer.flip();
        if (readCount > 0) {
            int left = readCount;
            int index = offset;
            // transfer data from readArray to targets
            while (left > 0) {
                int putLength = Math.min(targets[index].remaining(), left);
                targets[index].put(readArray, readCount - left, putLength);
                index++;
                left -= putLength;
            }
        }
        return readCount;
    }

    private int readImpl(ByteBuffer dst) throws IOException {
        synchronized (readLock) {
            int readCount = 0;
            try {
                if (isBlocking()) {
                    begin();
                }
                readCount = IoBridge.recvfrom(true, fd, dst, 0, null, false);
                if (readCount > 0) {
                    dst.position(dst.position() + readCount);
                }
            } finally {
                if (isBlocking()) {
                    end(readCount > 0);
                }
            }
            return readCount;
        }
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        if (src == null) {
            throw new NullPointerException();
        }
        checkOpenConnected();
        if (!src.hasRemaining()) {
            return 0;
        }
        return writeImpl(src);
    }

    @Override
    public long write(ByteBuffer[] sources, int offset, int length) throws IOException {
        Arrays.checkOffsetAndCount(sources.length, offset, length);
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

    private int writeImpl(ByteBuffer src) throws IOException {
        synchronized (writeLock) {
            if (!src.hasRemaining()) {
                return 0;
            }
            int writeCount = 0;
            try {
                if (isBlocking()) {
                    begin();
                }
                writeCount = IoBridge.sendto(fd, src, 0, null, 0);
                if (writeCount > 0) {
                    src.position(src.position() + writeCount);
                }
            } finally {
                if (isBlocking()) {
                    end(writeCount >= 0);
                }
            }
            return writeCount;
        }
    }

    /*
     * Status check, open and "connected", when read and write.
     */
    synchronized private void checkOpenConnected() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!isConnected()) {
            throw new NotYetConnectedException();
        }
    }

    /*
     * Status check, open and "unconnected", before connection.
     */
    synchronized private void checkUnconnected() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (status == SOCKET_STATUS_CONNECTED) {
            throw new AlreadyConnectedException();
        }
        if (status == SOCKET_STATUS_PENDING) {
            throw new ConnectionPendingException();
        }
    }

    /*
     * Shared by this class and DatagramChannelImpl, to do the address transfer
     * and check.
     */
    static InetSocketAddress validateAddress(SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new IllegalArgumentException();
        }
        if (!(socketAddress instanceof InetSocketAddress)) {
            throw new UnsupportedAddressTypeException();
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        if (inetSocketAddress.isUnresolved()) {
            throw new UnresolvedAddressException();
        }
        return inetSocketAddress;
    }

    /*
     * Get local address.
     */
    public InetAddress getLocalAddress() throws UnknownHostException {
        return isBound ? localAddress : Inet4Address.ANY;
    }

    /*
     * Do really closing action here.
     */
    @Override
    protected synchronized void implCloseSelectableChannel() throws IOException {
        if (status != SOCKET_STATUS_CLOSED) {
            status = SOCKET_STATUS_CLOSED;
            if (socket != null && !socket.isClosed()) {
                socket.close();
            } else {
                IoBridge.closeSocket(fd);
            }
        }
    }

    @Override protected void implConfigureBlocking(boolean blocking) throws IOException {
        synchronized (blockingLock()) {
            IoUtils.setBlocking(fd, blocking);
        }
    }

    /*
     * Get the fd.
     */
    public FileDescriptor getFD() {
        return fd;
    }

    /*
     * Adapter classes for internal socket.
     */
    private static class SocketAdapter extends Socket {
        private final SocketChannelImpl channel;
        private final PlainSocketImpl socketImpl;

        SocketAdapter(PlainSocketImpl socketImpl, SocketChannelImpl channel) throws SocketException {
            super(socketImpl);
            this.socketImpl = socketImpl;
            this.channel = channel;
            SocketUtils.setCreated(this);
        }

        PlainSocketImpl socketImpl() {
            return socketImpl;
        }

        @Override
        public SocketChannel getChannel() {
            return channel;
        }

        @Override
        public boolean isBound() {
            return channel.isBound;
        }

        @Override
        public boolean isConnected() {
            return channel.isConnected();
        }

        @Override
        public InetAddress getLocalAddress() {
            try {
                return channel.getLocalAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        @Override
        public void connect(SocketAddress remoteAddr, int timeout) throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            if (isConnected()) {
                throw new AlreadyConnectedException();
            }
            super.connect(remoteAddr, timeout);
            channel.initLocalAddressAndPort();
            if (super.isConnected()) {
                channel.setConnected();
                channel.isBound = super.isBound();
            }
        }

        @Override
        public void bind(SocketAddress localAddr) throws IOException {
            if (channel.isConnected()) {
                throw new AlreadyConnectedException();
            }
            if (SocketChannelImpl.SOCKET_STATUS_PENDING == channel.status) {
                throw new ConnectionPendingException();
            }
            super.bind(localAddr);
            channel.initLocalAddressAndPort();
            channel.isBound = true;
        }

        @Override
        public void close() throws IOException {
            synchronized (channel) {
                if (channel.isOpen()) {
                    channel.close();
                } else {
                    super.close();
                }
                channel.status = SocketChannelImpl.SOCKET_STATUS_CLOSED;
            }
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            checkOpenAndConnected();
            if (isOutputShutdown()) {
                throw new SocketException("Socket output is shutdown");
            }
            return new SocketChannelOutputStream(channel);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            checkOpenAndConnected();
            if (isInputShutdown()) {
                throw new SocketException("Socket input is shutdown");
            }
            return new SocketChannelInputStream(channel);
        }

        private void checkOpenAndConnected() throws SocketException {
            if (!channel.isOpen()) {
                throw new SocketException("Socket is closed");
            }
            if (!channel.isConnected()) {
                throw new SocketException("Socket is not connected");
            }
        }

        @Override
        public FileDescriptor getFileDescriptor$() {
            return socketImpl.getFD$();
        }
    }

    /*
     * This output stream delegates all operations to the associated channel.
     * Throws an IllegalBlockingModeException if the channel is in non-blocking
     * mode when performing write operations.
     */
    private static class SocketChannelOutputStream extends OutputStream {
        private final SocketChannel channel;

        public SocketChannelOutputStream(SocketChannel channel) {
            this.channel = channel;
        }

        /*
         * Closes this stream and channel.
         *
         * @exception IOException thrown if an error occurs during the close
         */
        @Override
        public void close() throws IOException {
            channel.close();
        }

        @Override
        public void write(byte[] buffer, int offset, int byteCount) throws IOException {
            Arrays.checkOffsetAndCount(buffer.length, offset, byteCount);
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, byteCount);
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            channel.write(buf);
        }

        @Override
        public void write(int oneByte) throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buffer = ByteBuffer.allocate(1);
            buffer.put(0, (byte) (oneByte & 0xFF));
            channel.write(buffer);
        }
    }

    /*
     * This input stream delegates all operations to the associated channel.
     * Throws an IllegalBlockingModeException if the channel is in non-blocking
     * mode when performing read operations.
     */
    private static class SocketChannelInputStream extends InputStream {
        private final SocketChannel channel;

        public SocketChannelInputStream(SocketChannel channel) {
            this.channel = channel;
        }

        /*
         * Closes this stream and channel.
         */
        @Override
        public void close() throws IOException {
            channel.close();
        }

        @Override
        public int read() throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.allocate(1);
            int result = channel.read(buf);
            return (result == -1) ? result : (buf.get(0) & 0xff);
        }

        @Override
        public int read(byte[] buffer, int offset, int byteCount) throws IOException {
            Arrays.checkOffsetAndCount(buffer.length, offset, byteCount);
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, byteCount);
            return channel.read(buf);
        }
    }
}
