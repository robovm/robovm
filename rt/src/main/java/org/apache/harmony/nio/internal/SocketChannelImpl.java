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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOptions;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
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

import org.apache.harmony.luni.net.PlainSocketImpl;
import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.INetworkSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.util.ErrorCodeException;
import org.apache.harmony.nio.AddressUtil;
import org.apache.harmony.nio.internal.nls.Messages;

/*
 * The default implementation class of java.nio.channels.SocketChannel.
 */
class SocketChannelImpl extends SocketChannel implements FileDescriptorHandler {

    private static final int EOF = -1;

    private static final int ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK = -211;

    // The singleton to do the native network operation.
    static final INetworkSystem networkSystem = Platform.getNetworkSystem();

    // Status un-init, not initialized.
    static final int SOCKET_STATUS_UNINIT = EOF;

    // Status before connect.
    static final int SOCKET_STATUS_UNCONNECTED = 0;

    // Status connection pending.
    static final int SOCKET_STATUS_PENDING = 1;

    // Status after connection success.
    static final int SOCKET_STATUS_CONNECTED = 2;

    // Status closed.
    static final int SOCKET_STATUS_CLOSED = 3;

    // Timeout used for non-block mode.
    private static final int TIMEOUT_NONBLOCK = 0;

    // Timeout used for block mode.
    private static final int TIMEOUT_BLOCK = EOF;

    // Step used for connect.
    private static final int HY_SOCK_STEP_START = 0;

    // Step used for finishConnect.
    private static final int HY_PORT_SOCKET_STEP_CHECK = 1;

    // Connect success.
    private static final int CONNECT_SUCCESS = 0;

    // The descriptor to interact with native code.
    FileDescriptor fd;

    // Our internal Socket.
    private Socket socket = null;

    // The address to be connected.
    InetSocketAddress connectAddress = null;

    // Local address of the this socket (package private for adapter).
    InetAddress localAddress = null;

    // Local port number.
    int localPort;

    // At first, uninitialized.
    int status = SOCKET_STATUS_UNINIT;

    // Whether the socket is bound.
    volatile boolean isBound = false;

    private static class ReadLock {}
    private final Object readLock = new ReadLock();

    private static class WriteLock {}
    private final Object writeLock = new WriteLock();

    // This content is a point used to set in connect_withtimeout() in pending
    // mode.
    // Must be a new instance of Long (i.e. not valueOf) as it's value may
    // be modified by native code.
    private Long connectContext = new Long(0L);

    // Used to store the trafficClass value which is simply returned
    // as the value that was set. We also need it to pass it to methods
    // that specify an address packets are going to be sent to.
    private int trafficClass = 0;

    /*
     * Constructor for creating a connected socket channel.
     */
    public SocketChannelImpl(SelectorProvider selectorProvider)
            throws IOException {
        this(selectorProvider, true);
    }

    /*
     * Constructor for creating an optionally connected socket channel.
     */
    public SocketChannelImpl(SelectorProvider selectorProvider, boolean connect)
            throws IOException {
        super(selectorProvider);
        fd = new FileDescriptor();
        status = SOCKET_STATUS_UNCONNECTED;
        if (connect) {
            networkSystem.createStreamSocket(fd, NetUtil.preferIPv4Stack());
        }
    }

    /*
     * For native call.
     */
    @SuppressWarnings("unused")
    private SocketChannelImpl() {
        super(SelectorProvider.provider());
        fd = new FileDescriptor();
        connectAddress = new InetSocketAddress(0);
        status = SOCKET_STATUS_CONNECTED;
    }

    // Keep this to see if need next version
    // SocketChannelImpl(SelectorProvider selectorProvider, FileDescriptor fd,
    // SocketImpl si) {
    // super(selectorProvider);
    // fd = fd;
    // networkSystem = OSNetworkSystem.getOSNetworkSystem();
    // status = SOCKET_STATUS_UNCONNECTED;
    // networkSystem.createSocket(fd, true);
    // }

    /*
     * Package private constructor.
     */
    SocketChannelImpl(Socket aSocket, FileDescriptor aFd) {
        super(SelectorProvider.provider());
        socket = aSocket;
        fd = aFd;
        status = SOCKET_STATUS_UNCONNECTED;
    }

    /*
     * Getting the internal Socket If we have not the socket, we create a new
     * one.
     */
    @Override
    synchronized public Socket socket() {
        if (null == socket) {
            try {
                InetAddress addr = null;
                int port = 0;
                if (connectAddress != null) {
                    addr = connectAddress.getAddress();
                    port = connectAddress.getPort();
                }
                socket = new SocketAdapter(
                        new PlainSocketImpl(fd, localPort, addr, port), this);
            } catch (SocketException e) {
                return null;
            }
        }
        return socket;
    }

    /**
     * @see java.nio.channels.SocketChannel#isConnected()
     */
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

    /**
     * @see java.nio.channels.SocketChannel#isConnectionPending()
     */
    @Override
    synchronized public boolean isConnectionPending() {
        return status == SOCKET_STATUS_PENDING;
    }

    /**
     * @see java.nio.channels.SocketChannel#connect(java.net.SocketAddress)
     */
    @Override
    public boolean connect(SocketAddress socketAddress) throws IOException {
        // status must be open and unconnected
        checkUnconnected();

        // check the address
        InetSocketAddress inetSocketAddress = validateAddress(socketAddress);
        InetAddress normalAddr = inetSocketAddress.getAddress();

        // When connecting, map ANY address to Localhost
        if (normalAddr.isAnyLocalAddress()) {
            normalAddr = InetAddress.getLocalHost();
        }

        int port = inetSocketAddress.getPort();
        String hostName = normalAddr.getHostName();
        // security check
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkConnect(hostName, port);
        }

        // connect result
        int result = EOF;
        boolean finished = false;

        try {
            if (isBlocking()) {
                begin();
                result = networkSystem.connect(fd, trafficClass, normalAddr, port);
            } else {
                result = networkSystem.connectWithTimeout(fd, 0, trafficClass,
                        normalAddr, port, HY_SOCK_STEP_START, connectContext);
                // set back to nonblocking to work around with a bug in portlib
                if (!this.isBlocking()) {
                    networkSystem.setNonBlocking(fd, true);
                }
            }
            finished = (CONNECT_SUCCESS == result);
            isBound = finished;
        } catch (IOException e) {
            if (isOpen()) {
                close();
                finished = true;
            }
            throw e;
        } finally {
            if (isBlocking()) {
                end(finished);
            }
        }

        // set local port
        localPort = networkSystem.getSocketLocalPort(fd, false);
        localAddress = networkSystem.getSocketLocalAddress(fd, false);

        // set the connected address.
        connectAddress = inetSocketAddress;
        synchronized (this) {
            if (finished) {
                status = SOCKET_STATUS_CONNECTED;
            } else {
                status = isBlocking() ? SOCKET_STATUS_UNCONNECTED
                        : SOCKET_STATUS_PENDING;
            }
        }
        return finished;
    }

    /**
     * @see java.nio.channels.SocketChannel#finishConnect()
     */
    @Override
    public boolean finishConnect() throws IOException {
        // status check
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

        // finish result
        int result = EOF;
        boolean finished = false;

        try {
            begin();
            result = networkSystem.connectWithTimeout(fd,
                    isBlocking() ? -1 : 0, trafficClass, connectAddress
                            .getAddress(), connectAddress.getPort(),
                    HY_PORT_SOCKET_STEP_CHECK, connectContext);
            finished = (result == CONNECT_SUCCESS);
            isBound = finished;
            localAddress = networkSystem.getSocketLocalAddress(fd, false);
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
            // TPE: Workaround for bug that turns socket back to blocking
            if (!isBlocking()) implConfigureBlocking(false);
        }
        return finished;
    }

    /**
     * @see java.nio.channels.SocketChannel#read(java.nio.ByteBuffer)
     */
    @Override
    public int read(ByteBuffer target) throws IOException {
        if (null == target) {
            throw new NullPointerException();
        }
        checkOpenConnected();
        if (!target.hasRemaining()) {
            return 0;
        }

        int readCount;
        if (target.isDirect() || target.hasArray()) {
            readCount = readImpl(target);
            if (readCount > 0) {
                target.position(target.position() + readCount);
            }
        } else {
            ByteBuffer readBuffer = null;
            byte[] readArray = null;
            readArray = new byte[target.remaining()];
            readBuffer = ByteBuffer.wrap(readArray);
            readCount = readImpl(readBuffer);
            if (readCount > 0) {
                target.put(readArray, 0, readCount);
            }
        }
        return readCount;
    }

    /**
     * @see java.nio.channels.SocketChannel#read(java.nio.ByteBuffer[], int,
     *      int)
     */
    @Override
    public long read(ByteBuffer[] targets, int offset, int length)
            throws IOException {
        if (!isIndexValid(targets, offset, length)) {
            throw new IndexOutOfBoundsException();
        }

        checkOpenConnected();
        int totalCount = calculateTotalRemaining(targets, offset, length);
        if (0 == totalCount) {
            return 0;
        }
        byte[] readArray = new byte[totalCount];
        ByteBuffer readBuffer = ByteBuffer.wrap(readArray);
        int readCount;
        // read data to readBuffer, and then transfer data from readBuffer to
        // targets.
        readCount = readImpl(readBuffer);
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

    private boolean isIndexValid(ByteBuffer[] targets, int offset, int length) {
        return (length >= 0) && (offset >= 0)
                && ((long) length + (long) offset <= targets.length);
    }

    /**
     * Read from channel, and store the result in the target.
     * 
     * @param target
     *            output parameter
     */
    private int readImpl(ByteBuffer target) throws IOException {
        synchronized (readLock) {
            int readCount = 0;
            try {
                if (isBlocking()) {
                    begin();
                }
                int offset = target.position();
                int length = target.remaining();
                if (target.isDirect()) {
                    long address = AddressUtil.getDirectBufferAddress(target);
                    readCount = networkSystem.readDirect(fd, address + offset,
                            length, (isBlocking() ? TIMEOUT_BLOCK
                                    : TIMEOUT_NONBLOCK));
                } else {
                    // target is assured to have array.
                    byte[] array = target.array();
                    offset += target.arrayOffset();
                    readCount = networkSystem.read(fd, array, offset, length,
                            (isBlocking() ? TIMEOUT_BLOCK : TIMEOUT_NONBLOCK));
                }
                return readCount;
            } finally {
                if (isBlocking()) {
                    end(readCount > 0);
                }
            }
        }
    }

    /**
     * @see java.nio.channels.SocketChannel#write(java.nio.ByteBuffer)
     */
    @Override
    public int write(ByteBuffer source) throws IOException {
        if (null == source) {
            throw new NullPointerException();
        }
        checkOpenConnected();
        if (!source.hasRemaining()) {
            return 0;
        }
        return writeImpl(source);
    }

    /**
     * @see java.nio.channels.SocketChannel#write(java.nio.ByteBuffer[], int,
     *      int)
     */
    @Override
    public long write(ByteBuffer[] sources, int offset, int length)
            throws IOException {
        if (!isIndexValid(sources, offset, length)) {
            throw new IndexOutOfBoundsException();
        }

        checkOpenConnected();
        if (calculateTotalRemaining(sources, offset, length) == 0) {
            return 0;
        }

        Object[] src = new Object[length];
        int[] offsets = new int[length];
        int[] lengths = new int[length];
        for (int i = 0; i < length; i++) {
            ByteBuffer buffer = sources[i + offset];
            if (!buffer.isDirect()) {
                if (buffer.hasArray()) {
                    src[i] = buffer.array();
                    offsets[i] = buffer.position();
                } else {
                    ByteBuffer directBuffer = ByteBuffer.allocateDirect(buffer.remaining());
                    int oldPosition = buffer.position();
                    directBuffer.put(buffer);
                    buffer.position(oldPosition);
                    directBuffer.flip();
                    src[i] = directBuffer;
                    offsets[i] = 0;
                }
            } else {
                src[i] = buffer;
                offsets[i] = buffer.position();
            }
            lengths[i] = buffer.remaining();
        }

        long bytesWritten = writevImpl(src, offsets, lengths);
        long bytesRemaining = bytesWritten;
        for (int i = offset; i < length + offset; i++) {
            if (bytesRemaining > sources[i].remaining()) {
                int pos = sources[i].limit();
                bytesRemaining -= sources[i].remaining();
                sources[i].position(pos);
            } else {
                int pos = sources[i].position() + (int) bytesRemaining;
                sources[i].position(pos);
                break;
            }
        }
        return bytesWritten;
    }

    /*
     * Write the source. return the count of bytes written.
     */
    private long writevImpl(Object[] sources, int[] offsets, int[] lengths) throws IOException {
        long writeCount = 0;
        try {
            if (isBlocking()) {
                begin();
            }

            synchronized (writeLock) {
                writeCount = networkSystem.writev(fd, sources, offsets, lengths, sources.length);
            }
        } catch (SocketException e) {
            if (e.getCause() instanceof ErrorCodeException) {
                if (ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK == ((ErrorCodeException) e
                        .getCause()).getErrorCode()) {
                    return writeCount;
                }
            }
            throw e;
        } finally {
            if (isBlocking()) {
                end(writeCount >= 0);
            }
        }
        return writeCount;
    }

    private int calculateTotalRemaining(ByteBuffer[] buffers, int offset,
            int length) {
        int count = 0;
        for (int i = offset; i < offset + length; i++) {
            count += buffers[i].remaining();
        }
        return count;
    }

    /*
     * Write the source. return the count of bytes written.
     */
    private int writeImpl(ByteBuffer source) throws IOException {
        synchronized (writeLock) {
            if (!source.hasRemaining()) {
                return 0;
            }
            int writeCount = 0;
            try {
                int pos = source.position();
                int length = source.remaining();
                if (isBlocking()) {
                    begin();
                }
                if (source.isDirect()) {
                    long address = AddressUtil.getDirectBufferAddress(source);
                    writeCount = networkSystem.writeDirect(fd, address + pos,
                            length);
                } else if (source.hasArray()) {
                    pos += source.arrayOffset();
                    writeCount = networkSystem.write(fd, source.array(), pos,
                            length);
                } else {
                    byte[] array = new byte[length];
                    source.get(array);
                    writeCount = networkSystem.write(fd, array, 0, length);
                }
                source.position(pos + writeCount);
            } catch (SocketException e) {
                if (e.getCause() instanceof ErrorCodeException) {
                    if (ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK == ((ErrorCodeException) e
                            .getCause()).getErrorCode()) {
                        return writeCount;
                    }
                }
                throw e;
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
    synchronized private void checkOpenConnected()
            throws ClosedChannelException {
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
        if (null == socketAddress) {
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
        byte[] any_bytes = { 0, 0, 0, 0 };
        if (!isBound) {
            return InetAddress.getByAddress(any_bytes);
        }
        return localAddress;
    }

    /*
     * Do really closing action here.
     */
    @Override
    synchronized protected void implCloseSelectableChannel() throws IOException {
        if (SOCKET_STATUS_CLOSED != status) {
            status = SOCKET_STATUS_CLOSED;
            if (null != socket && !socket.isClosed()) {
                socket.close();
            } else {
                networkSystem.socketClose(fd);
            }
        }
    }

    /**
     * @see java.nio.channels.spi.AbstractSelectableChannel#implConfigureBlocking(boolean)
     */
    @Override
    protected void implConfigureBlocking(boolean blockMode) throws IOException {
        synchronized (blockingLock()) {
            networkSystem.setNonBlocking(fd, !blockMode);
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

        SocketChannelImpl channel;

        SocketImpl socketImpl;

        SocketAdapter(SocketImpl socketimpl, SocketChannelImpl channel)
                throws SocketException {
            super(socketimpl);
            socketImpl = socketimpl;
            this.channel = channel;
        }

        /**
         * @see java.net.Socket#getChannel()
         */
        @Override
        public SocketChannel getChannel() {
            return channel;
        }

        /**
         * @see java.net.Socket#isBound()
         */
        @Override
        public boolean isBound() {
            return channel.isBound;
        }

        /**
         * @see java.net.Socket#isConnected()
         */
        @Override
        public boolean isConnected() {
            return super.isConnected() || channel.isConnected();
        }

        /**
         * @see java.net.Socket#getLocalAddress()
         */
        @Override
        public InetAddress getLocalAddress() {
            try {
                return channel.getLocalAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        /**
         * @see java.net.Socket#connect(java.net.SocketAddress, int)
         */
        @Override
        public void connect(SocketAddress remoteAddr, int timeout)
                throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            if (isConnected()) {
                throw new AlreadyConnectedException();
            }
            super.connect(remoteAddr, timeout);
            channel.localAddress = networkSystem.getSocketLocalAddress(
                    channel.fd, false);
            if (super.isConnected()) {
                channel.setConnected();
                channel.isBound = super.isBound();
            }
        }

        /**
         * @see java.net.Socket#bind(java.net.SocketAddress)
         */
        @Override
        public void bind(SocketAddress localAddr) throws IOException {
            if (channel.isConnected()) {
                throw new AlreadyConnectedException();
            }
            if (SocketChannelImpl.SOCKET_STATUS_PENDING == channel.status) {
                throw new ConnectionPendingException();
            }
            super.bind(localAddr);
            channel.isBound = true;
            channel.localAddress = super.getLocalAddress();
            channel.localPort = super.getLocalPort();
        }

        /**
         * @see java.net.Socket#close()
         */
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
        public boolean getReuseAddress() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_REUSEADDR))
                    .booleanValue();
        }

        @Override
        public synchronized int getReceiveBufferSize() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_RCVBUF))
                    .intValue();
        }

        @Override
        public synchronized int getSendBufferSize() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_SNDBUF))
                    .intValue();
        }

        @Override
        public synchronized int getSoTimeout() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_TIMEOUT))
                    .intValue();
        }

        @Override
        public int getTrafficClass() throws SocketException {
            checkOpen();
            return ((Number) socketImpl.getOption(SocketOptions.IP_TOS))
                    .intValue();
        }

        /**
         * @see java.net.Socket#getKeepAlive()
         */
        @Override
        public boolean getKeepAlive() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_KEEPALIVE))
                    .booleanValue();
        }

        /**
         * @see java.net.Socket#getOOBInline()
         */
        @Override
        public boolean getOOBInline() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_OOBINLINE))
                    .booleanValue();
        }

        /**
         * @see java.net.Socket#getSoLinger()
         */
        @Override
        public int getSoLinger() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_LINGER))
                    .intValue();
        }

        /**
         * @see java.net.Socket#getTcpNoDelay()
         */
        @Override
        public boolean getTcpNoDelay() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.TCP_NODELAY))
                    .booleanValue();
        }

        @Override
        public void setKeepAlive(boolean value) throws SocketException {
            checkOpen();
            socketImpl.setOption(SocketOptions.SO_KEEPALIVE, value ? Boolean.TRUE
                    : Boolean.FALSE);
        }

        @Override
        public void setOOBInline(boolean oobinline) throws SocketException {
            checkOpen();
            socketImpl.setOption(SocketOptions.SO_OOBINLINE, oobinline ? Boolean.TRUE
                    : Boolean.FALSE);
        }

        @Override
        public synchronized void setReceiveBufferSize(int size)
                throws SocketException {
            checkOpen();
            if (size < 1) {
                throw new IllegalArgumentException(Messages.getString("nio.0E")); //$NON-NLS-1$
            }
            socketImpl
                    .setOption(SocketOptions.SO_RCVBUF, Integer.valueOf(size));
        }

        @Override
        public void setReuseAddress(boolean reuse) throws SocketException {
            checkOpen();
            socketImpl.setOption(SocketOptions.SO_REUSEADDR, reuse ? Boolean.TRUE
                    : Boolean.FALSE);
        }

        @Override
        public synchronized void setSendBufferSize(int size) throws SocketException {
            checkOpen();
            if (size < 1) {
                throw new IllegalArgumentException(Messages.getString("nio.0E")); //$NON-NLS-1$
            }
            socketImpl.setOption(SocketOptions.SO_SNDBUF, Integer.valueOf(size));
        }

        @Override
        public void setSoLinger(boolean on, int timeout) throws SocketException {
            checkOpen();
            if (on && timeout < 0) {
                throw new IllegalArgumentException(Messages.getString("nio.0F")); //$NON-NLS-1$
            }
            int val = on ? (65535 < timeout ? 65535 : timeout) : -1;
            socketImpl.setOption(SocketOptions.SO_LINGER, Integer.valueOf(val));
        }

        @Override
        public synchronized void setSoTimeout(int timeout) throws SocketException {
            checkOpen();
            if (timeout < 0) {
                throw new IllegalArgumentException(Messages.getString("nio.10")); //$NON-NLS-1$
            }
            socketImpl.setOption(SocketOptions.SO_TIMEOUT, Integer.valueOf(timeout));
        }

        @Override
        public void setTcpNoDelay(boolean on) throws SocketException {
            checkOpen();
            socketImpl.setOption(SocketOptions.TCP_NODELAY, Boolean.valueOf(on));
        }

        @Override
        public void setTrafficClass(int value) throws SocketException {
            checkOpen();
            if (value < 0 || value > 255) {
                throw new IllegalArgumentException();
            }
            socketImpl.setOption(SocketOptions.IP_TOS, Integer.valueOf(value));
        }

        /**
         * @see java.net.Socket#getOutputStream()
         */
        @Override
        public OutputStream getOutputStream() throws IOException {
            if (!channel.isOpen()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
            if (!channel.isConnected()) {
                // nio.01=Socket is not connected
                throw new SocketException(Messages.getString("nio.01")); //$NON-NLS-1$
            }
            if (isOutputShutdown()) {
                // nio.02=Socket output is shutdown
                throw new SocketException(Messages.getString("nio.02")); //$NON-NLS-1$
            }
            return new SocketChannelOutputStream(channel);
        }

        /**
         * @see java.net.Socket#getInputStream()
         */
        @Override
        public InputStream getInputStream() throws IOException {
            if (!channel.isOpen()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
            if (!channel.isConnected()) {
                // nio.01=Socket is not connected
                throw new SocketException(Messages.getString("nio.01")); //$NON-NLS-1$
            }
            if (isInputShutdown()) {
                // nio.03=Socket input is shutdown
                throw new SocketException(Messages.getString("nio.03")); //$NON-NLS-1$
            }
            return new SocketChannelInputStream(channel);
        }

        @Override
        public InetAddress getInetAddress() {
            if (!isConnected()) {
                return null;
            }

            if (channel.connectAddress == null && super.getInetAddress() != null) {
                channel.connectAddress = new InetSocketAddress(super.getInetAddress(), super.getPort());
            }
            if (channel.connectAddress == null) {
                return null;
            }
            return channel.connectAddress.getAddress();
        }

        @Override
        public SocketAddress getRemoteSocketAddress() {
            if (!isConnected()) {
                return null;
            }
            if (channel.connectAddress == null && super.getInetAddress() != null) {
                channel.connectAddress = new InetSocketAddress(super.getInetAddress(), super.getPort());
            }
            return channel.connectAddress;
        }

        @Override
        public int getPort() {
            if (!isConnected()) {
                return 0;
            }
            if (channel.connectAddress == null && super.getInetAddress() != null) {
                channel.connectAddress = new InetSocketAddress(super.getInetAddress(), super.getPort());
            }
            if (channel.connectAddress == null) {
                return 0;
            }
            return channel.connectAddress.getPort();
        }

        @Override
        public int getLocalPort() {
            if (!isBound()) {
                return -1;
            }
            if (channel.localPort == 0 && super.getLocalPort() != -1) {
                channel.localPort = super.getLocalPort();
            }
            return channel.localPort;
        }

        /*
         * Checks whether the channel is open.
         */
        private void checkOpen() throws SocketException {
            if (isClosed()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
        }

        /*
         * Used for net and nio exchange.
         */
        public SocketImpl getImpl() {
            return socketImpl;
        }
    }

    /*
     * This output stream delegates all operations to the associated channel.
     * Throws an IllegalBlockingModeException if the channel is in non-blocking
     * mode when performing write operations.
     */
    private static class SocketChannelOutputStream extends OutputStream {
        SocketChannel channel;

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

        /**
         * @see java.io.OutputStream#write(byte[], int, int)
         */
        @Override
        public void write(byte[] buffer, int offset, int count)
                throws IOException {
            if (0 > offset || 0 > count || count + offset > buffer.length) {
                throw new IndexOutOfBoundsException();
            }
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, count);
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            channel.write(buf);
        }

        /**
         * @see java.io.OutputStream#write(int)
         */
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
        SocketChannel channel;

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

        /**
         * @see java.io.InputStream#read()
         */
        @Override
        public int read() throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.allocate(1);
            int result = channel.read(buf);
            return (-1 == result) ? result : buf.get() & 0xFF;
        }

        /**
         * @see java.io.InputStream#read(byte[], int, int)
         */
        @Override
        public int read(byte[] buffer, int offset, int count)
                throws IOException {
            if (0 > offset || 0 > count || count + offset > buffer.length) {
                throw new IndexOutOfBoundsException();
            }
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, count);
            return channel.read(buf);
        }
    }
}
