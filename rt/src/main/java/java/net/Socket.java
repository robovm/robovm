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

package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;
import java.security.AccessController;

import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.net.PlainSocketImpl;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.internal.nls.Messages;
import org.apache.harmony.luni.util.PriviAction;

/**
 * Provides a client-side TCP socket.
 */
public class Socket {

    SocketImpl impl;

    static SocketImplFactory factory;

    private volatile boolean isCreated = false;

    private boolean isBound = false;

    private boolean isConnected = false;

    private boolean isClosed = false;

    private boolean isInputShutdown = false;

    private boolean isOutputShutdown = false;

    private static class ConnectLock {
    }

    private Object connectLock = new ConnectLock();

    private Proxy proxy;

    static final int MULTICAST_IF = 1;

    static final int MULTICAST_TTL = 2;

    static final int TCP_NODELAY = 4;

    static final int FLAG_SHUTDOWN = 8;
   
    static {
        Platform.getNetworkSystem().oneTimeInitialization(true);
    }

    /**
     * Creates a new unconnected socket. When a SocketImplFactory is defined it
     * creates the internal socket implementation, otherwise the default socket
     * implementation will be used for this socket.
     * 
     * @see SocketImplFactory
     * @see SocketImpl
     */
    public Socket() {
        impl = factory != null ? factory.createSocketImpl()
                : new PlainSocketImpl();
    }

    /**
     * Creates a new unconnected socket using the given proxy type. When a
     * {@code SocketImplFactory} is defined it creates the internal socket
     * implementation, otherwise the default socket implementation will be used
     * for this socket.
     * <p>
     * Example that will create a socket connection through a {@code SOCKS}
     * proxy server: <br>
     * {@code Socket sock = new Socket(new Proxy(Proxy.Type.SOCKS, new
     * InetSocketAddress("test.domain.org", 2130)));}
     * 
     * @param proxy
     *            the specified proxy for this socket.
     * @throws IllegalArgumentException
     *             if the argument {@code proxy} is {@code null} or of an
     *             invalid type.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given proxy.
     * @see SocketImplFactory
     * @see SocketImpl
     */
    public Socket(Proxy proxy) {
        if (null == proxy || Proxy.Type.HTTP == proxy.type()) {
            // luni.73=Proxy is null or invalid type
            throw new IllegalArgumentException(Messages.getString("luni.73")); //$NON-NLS-1$
        }
        InetSocketAddress address = (InetSocketAddress) proxy.address();
        if (null != address) {
            InetAddress addr = address.getAddress();
            String host;
            if (null != addr) {
                host = addr.getHostAddress();
            } else {
                host = address.getHostName();
            }
            int port = address.getPort();
            checkConnectPermission(host, port);
        }
        impl = factory != null ? factory.createSocketImpl()
                : new PlainSocketImpl(proxy);
        this.proxy = proxy;
    }

    /**
     * Creates a new streaming socket connected to the target host specified by
     * the parameters {@code dstName} and {@code dstPort}. The socket is bound
     * to any available port on the local host.
     * 
     * @param dstName
     *            the target host name or IP address to connect to.
     * @param dstPort
     *            the port on the target host to connect to.
     * @throws UnknownHostException
     *             if the host name could not be resolved into an IP address.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     */
    public Socket(String dstName, int dstPort) throws UnknownHostException,
            IOException {
        this();
        InetAddress dstAddress = InetAddress.getByName(dstName);
        checkDestination(dstAddress, dstPort);
        startupSocket(dstAddress, dstPort, null, 0, true);
    }

    /**
     * Creates a new streaming socket connected to the target host specified by
     * the parameters {@code dstName} and {@code dstPort}. On the local endpoint
     * the socket is bound to the given address {@code localAddress} on port
     * {@code localPort}.
     *
     * If {@code host} is {@code null} a loopback address is used to connect to.
     *
     * @param dstName
     *            the target host name or IP address to connect to.
     * @param dstPort
     *            the port on the target host to connect to.
     * @param localAddress
     *            the address on the local host to bind to.
     * @param localPort
     *            the port on the local host to bind to.
     * @throws UnknownHostException
     *             if the host name could not be resolved into an IP address.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     */
    public Socket(String dstName, int dstPort, InetAddress localAddress,
            int localPort) throws IOException {
        this();
        InetAddress dstAddress = InetAddress.getByName(dstName);
        checkDestination(dstAddress, dstPort);
        startupSocket(dstAddress, dstPort, localAddress, localPort, true);
    }

    /**
     * Creates a new streaming or datagram socket connected to the target host
     * specified by the parameters {@code hostName} and {@code port}. The socket
     * is bound to any available port on the local host.
     * 
     * @param hostName
     *            the target host name or IP address to connect to.
     * @param port
     *            the port on the target host to connect to.
     * @param streaming
     *            if {@code true} a streaming socket is returned, a datagram
     *            socket otherwise.
     * @throws UnknownHostException
     *             if the host name could not be resolved into an IP address.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     * @deprecated Use {@code Socket(String, int)} instead of this for streaming
     *             sockets or an appropriate constructor of {@code
     *             DatagramSocket} for UDP transport.
     */
    @Deprecated
    public Socket(String hostName, int port, boolean streaming)
            throws IOException {
        this();
        InetAddress host = InetAddress.getByName(hostName);
        checkDestination(host, port);
        startupSocket(host, port, null, 0, streaming);
    }

    /**
     * Creates a new streaming socket connected to the target host specified by
     * the parameters {@code dstAddress} and {@code dstPort}. The socket is
     * bound to any available port on the local host.
     * 
     * @param dstAddress
     *            the target host address to connect to.
     * @param dstPort
     *            the port on the target host to connect to.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     */
    public Socket(InetAddress dstAddress, int dstPort) throws IOException {
        this();
        checkDestination(dstAddress, dstPort);
        startupSocket(dstAddress, dstPort, null, 0, true);
    }

    /**
     * Creates a new streaming socket connected to the target host specified by
     * the parameters {@code dstAddress} and {@code dstPort}. On the local
     * endpoint the socket is bound to the given address {@code localAddress} on
     * port {@code localPort}.
     * 
     * @param dstAddress
     *            the target host address to connect to.
     * @param dstPort
     *            the port on the target host to connect to.
     * @param localAddress
     *            the address on the local host to bind to.
     * @param localPort
     *            the port on the local host to bind to.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     */
    public Socket(InetAddress dstAddress, int dstPort,
            InetAddress localAddress, int localPort) throws IOException {
        this();
        checkDestination(dstAddress, dstPort);
        startupSocket(dstAddress, dstPort, localAddress, localPort, true);
    }

    /**
     * Creates a new streaming or datagram socket connected to the target host
     * specified by the parameters {@code addr} and {@code port}. The socket is
     * bound to any available port on the local host.
     * 
     * @param addr
     *            the Internet address to connect to.
     * @param port
     *            the port on the target host to connect to.
     * @param streaming
     *            if {@code true} a streaming socket is returned, a datagram
     *            socket otherwise.
     * @throws IOException
     *             if an error occurs while creating the socket.
     * @throws SecurityException
     *             if a security manager exists and it denies the permission to
     *             connect to the given address and port.
     * @deprecated Use {@code Socket(InetAddress, int)} instead of this for
     *             streaming sockets or an appropriate constructor of {@code
     *             DatagramSocket} for UDP transport.
     */
    @Deprecated
    public Socket(InetAddress addr, int port, boolean streaming)
            throws IOException {
        this();
        checkDestination(addr, port);
        startupSocket(addr, port, null, 0, streaming);
    }

    /**
     * Creates an unconnected socket with the given socket implementation.
     * 
     * @param anImpl
     *            the socket implementation to be used.
     * @throws SocketException
     *             if an error occurs while creating the socket.
     */
    protected Socket(SocketImpl anImpl) throws SocketException {
        impl = anImpl;
    }

    /**
     * Checks whether the connection destination satisfies the security policy
     * and the validity of the port range.
     * 
     * @param destAddr
     *            the destination host address.
     * @param dstPort
     *            the port on the destination host.
     */
    void checkDestination(InetAddress destAddr, int dstPort) {
        if (dstPort < 0 || dstPort > 65535) {
            throw new IllegalArgumentException(Messages.getString("luni.38")); //$NON-NLS-1$
        }
        checkConnectPermission(destAddr.getHostName(), dstPort);
    }

    /**
     * Checks whether the connection destination satisfies the security policy.
     * 
     * @param hostname
     *            the destination hostname.
     * @param dstPort
     *            the port on the destination host.
     */
    private void checkConnectPermission(String hostname, int dstPort) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkConnect(hostname, dstPort);
        }
    }

    /**
     * Closes the socket. It is not possible to reconnect or rebind to this
     * socket thereafter which means a new socket instance has to be created.
     * 
     * @throws IOException
     *             if an error occurs while closing the socket.
     */
    public synchronized void close() throws IOException {
        isClosed = true;
        impl.close();
    }

    /**
     * Gets the IP address of the target host this socket is connected to.
     * 
     * @return the IP address of the connected target host or {@code null} if
     *         this socket is not yet connected.
     */
    public InetAddress getInetAddress() {
        if (!isConnected()) {
            return null;
        }
        return impl.getInetAddress();
    }

    /**
     * Gets an input stream to read data from this socket.
     * 
     * @return the byte-oriented input stream.
     * @throws IOException
     *             if an error occurs while creating the input stream or the
     *             socket is in an invalid state.
     */
    public InputStream getInputStream() throws IOException {
        checkClosedAndCreate(false);
        if (isInputShutdown()) {
            throw new SocketException(Messages.getString("luni.74")); //$NON-NLS-1$
        }
        return impl.getInputStream();
    }

    /**
     * Gets the setting of the socket option {@code SocketOptions.SO_KEEPALIVE}.
     * 
     * @return {@code true} if the {@code SocketOptions.SO_KEEPALIVE} is
     *         enabled, {@code false} otherwise.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_KEEPALIVE
     */
    public boolean getKeepAlive() throws SocketException {
        checkClosedAndCreate(true);
        return ((Boolean) impl.getOption(SocketOptions.SO_KEEPALIVE))
                .booleanValue();
    }

    /**
     * Gets the local IP address this socket is bound to.
     * 
     * @return the local IP address of this socket or {@code InetAddress.ANY} if
     *         the socket is unbound.
     */
    public InetAddress getLocalAddress() {
        if (!isBound()) {
            return InetAddress.ANY;
        }
        return Platform.getNetworkSystem().getSocketLocalAddress(impl.fd,
                InetAddress.preferIPv6Addresses());
    }

    /**
     * Gets the local port this socket is bound to.
     * 
     * @return the local port of this socket or {@code -1} if the socket is
     *         unbound.
     */
    public int getLocalPort() {
        if (!isBound()) {
            return -1;
        }
        return impl.getLocalPort();
    }

    /**
     * Gets an output stream to write data into this socket.
     * 
     * @return the byte-oriented output stream.
     * @throws IOException
     *             if an error occurs while creating the output stream or the
     *             socket is in an invalid state.
     */
    public OutputStream getOutputStream() throws IOException {
        checkClosedAndCreate(false);
        if (isOutputShutdown()) {
            throw new SocketException(Messages.getString("luni.75")); //$NON-NLS-1$
        }
        return impl.getOutputStream();
    }

    /**
     * Gets the port number of the target host this socket is connected to.
     * 
     * @return the port number of the connected target host or {@code 0} if this
     *         socket is not yet connected.
     */
    public int getPort() {
        if (!isConnected()) {
            return 0;
        }
        return impl.getPort();
    }

    /**
     * Gets the value of the socket option {@code SocketOptions.SO_LINGER}.
     * 
     * @return the current value of the option {@code SocketOptions.SO_LINGER}
     *         or {@code -1} if this option is disabled.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_LINGER
     */
    public int getSoLinger() throws SocketException {
        checkClosedAndCreate(true);
        return ((Integer) impl.getOption(SocketOptions.SO_LINGER)).intValue();
    }

    /**
     * Gets the receive buffer size of this socket.
     * 
     * @return the current value of the option {@code SocketOptions.SO_RCVBUF}.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_RCVBUF
     */
    public synchronized int getReceiveBufferSize() throws SocketException {
        checkClosedAndCreate(true);
        return ((Integer) impl.getOption(SocketOptions.SO_RCVBUF)).intValue();
    }

    /**
     * Gets the send buffer size of this socket.
     * 
     * @return the current value of the option {@code SocketOptions.SO_SNDBUF}.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_SNDBUF
     */
    public synchronized int getSendBufferSize() throws SocketException {
        checkClosedAndCreate(true);
        return ((Integer) impl.getOption(SocketOptions.SO_SNDBUF)).intValue();
    }

    /**
     * Gets the timeout for this socket during which a reading operation shall
     * block while waiting for data.
     * 
     * @return the current value of the option {@code SocketOptions.SO_TIMEOUT}
     *         or {@code 0} which represents an infinite timeout.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_TIMEOUT
     */
    public synchronized int getSoTimeout() throws SocketException {
        checkClosedAndCreate(true);
        return ((Integer) impl.getOption(SocketOptions.SO_TIMEOUT)).intValue();
    }

    /**
     * Gets the setting of the socket option {@code SocketOptions.TCP_NODELAY}.
     *
     * @return {@code true} if the {@code SocketOptions.TCP_NODELAY} is enabled,
     *         {@code false} otherwise.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#TCP_NODELAY
     */
    public boolean getTcpNoDelay() throws SocketException {
        checkClosedAndCreate(true);
        return ((Boolean) impl.getOption(SocketOptions.TCP_NODELAY))
                .booleanValue();
    }

    /**
     * Sets the state of the {@code SocketOptions.SO_KEEPALIVE} for this socket.
     * 
     * @param value
     *            the state whether this option is enabled or not.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#SO_KEEPALIVE
     */
    public void setKeepAlive(boolean value) throws SocketException {
        if (impl != null) {
            checkClosedAndCreate(true);
            impl.setOption(SocketOptions.SO_KEEPALIVE, value ? Boolean.TRUE
                    : Boolean.FALSE);
        }
    }

    /**
     * Sets the internal factory for creating socket implementations. This may
     * only be executed once during the lifetime of the application.
     * 
     * @param fac
     *            the socket implementation factory to be set.
     * @throws IOException
     *             if the factory has been already set.
     */
    public static synchronized void setSocketImplFactory(SocketImplFactory fac)
            throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkSetFactory();
        }
        if (factory != null) {
            throw new SocketException(Messages.getString("luni.5C")); //$NON-NLS-1$
        }
        factory = fac;
    }

    /**
     * Sets the send buffer size of this socket.
     * 
     * @param size
     *            the buffer size in bytes. This value must be a positive number
     *            greater than {@code 0}.
     * @throws SocketException
     *             if an error occurs while setting the size or the given value
     *             is an invalid size.
     * @see SocketOptions#SO_SNDBUF
     */
    public synchronized void setSendBufferSize(int size) throws SocketException {
        checkClosedAndCreate(true);
        if (size < 1) {
            throw new IllegalArgumentException(Messages.getString("luni.5A")); //$NON-NLS-1$
        }
        impl.setOption(SocketOptions.SO_SNDBUF, Integer.valueOf(size));
    }

    /**
     * Sets the receive buffer size of this socket.
     * 
     * @param size
     *            the buffer size in bytes. This value must be a positive number
     *            greater than {@code 0}.
     * @throws SocketException
     *             if an error occurs while setting the size or the given value
     *             is an invalid size.
     * @see SocketOptions#SO_RCVBUF
     */
    public synchronized void setReceiveBufferSize(int size)
            throws SocketException {
        checkClosedAndCreate(true);
        if (size < 1) {
            throw new IllegalArgumentException(Messages.getString("luni.5A")); //$NON-NLS-1$
        }
        impl.setOption(SocketOptions.SO_RCVBUF, Integer.valueOf(size));
    }

    /**
     * Sets the state of the {@code SocketOptions.SO_LINGER} with the given
     * timeout in seconds. The timeout value for this option is silently limited
     * to the maximum of {@code 65535}.
     * 
     * @param on
     *            the state whether this option is enabled or not.
     * @param timeout
     *            the linger timeout value in seconds.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#SO_LINGER
     */
    public void setSoLinger(boolean on, int timeout) throws SocketException {
        checkClosedAndCreate(true);
        if (on && timeout < 0) {
            throw new IllegalArgumentException(Messages.getString("luni.76")); //$NON-NLS-1$
        }
        int val = on ? (65535 < timeout ? 65535 : timeout) : -1;
        impl.setOption(SocketOptions.SO_LINGER, Integer.valueOf(val));
    }

    /**
     * Sets the reading timeout in milliseconds for this socket. The read
     * operation will block indefinitely if this option value is set to {@code
     * 0}. The timeout must be set before calling the read operation. A
     * {@code SocketTimeoutException} is thrown when this timeout expires.
     * 
     * @param timeout
     *            the reading timeout value as number greater than {@code 0} or
     *            {@code 0} for an infinite timeout.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#SO_TIMEOUT
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        checkClosedAndCreate(true);
        if (timeout < 0) {
            throw new IllegalArgumentException(Messages.getString("luni.5B")); //$NON-NLS-1$
        }
        impl.setOption(SocketOptions.SO_TIMEOUT, Integer.valueOf(timeout));
    }

    /**
     * Sets the state of the {@code SocketOptions.TCP_NODELAY} for this socket.
     * 
     * @param on
     *            the state whether this option is enabled or not.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#TCP_NODELAY
     */
    public void setTcpNoDelay(boolean on) throws SocketException {
        checkClosedAndCreate(true);
        impl.setOption(SocketOptions.TCP_NODELAY, Boolean.valueOf(on));
    }

    /**
     * Creates a stream socket, binds it to the nominated local address/port,
     * then connects it to the nominated destination address/port.
     * 
     * @param dstAddress
     *            the destination host address.
     * @param dstPort
     *            the port on the destination host.
     * @param localAddress
     *            the address on the local machine to bind.
     * @param localPort
     *            the port on the local machine to bind.
     * @throws IOException
     *             thrown if an error occurs during the bind or connect
     *             operations.
     */
    void startupSocket(InetAddress dstAddress, int dstPort,
            InetAddress localAddress, int localPort, boolean streaming)
            throws IOException {

        if (localPort < 0 || localPort > 65535) {
            throw new IllegalArgumentException(Messages.getString("luni.77")); //$NON-NLS-1$
        }

        InetAddress addr = localAddress == null ? InetAddress.ANY
                : localAddress;
        synchronized (this) {
            impl.create(streaming);
            isCreated = true;
            try {
                if (!streaming || !NetUtil.usingSocks(proxy)) {
                    impl.bind(addr, localPort);
                }
                isBound = true;
                impl.connect(dstAddress, dstPort);
                isConnected = true;
            } catch (IOException e) {
                impl.close();
                throw e;
            }
        }
    }

    /**
     * Returns a {@code String} containing a concise, human-readable description of the
     * socket.
     * 
     * @return the textual representation of this socket.
     */
    @Override
    public String toString() {
        if (!isConnected()) {
            return "Socket[unconnected]"; //$NON-NLS-1$
        }
        return impl.toString();
    }

    /**
     * Closes the input stream of this socket. Any further data sent to this
     * socket will be discarded. Reading from this socket after this method has
     * been called will return the value {@code EOF}.
     * 
     * @throws IOException
     *             if an error occurs while closing the socket input stream.
     * @throws SocketException
     *             if the input stream is already closed.
     */
    public void shutdownInput() throws IOException {
        if (isInputShutdown()) {
            throw new SocketException(Messages.getString("luni.74")); //$NON-NLS-1$
        }
        checkClosedAndCreate(false);
        impl.shutdownInput();
        isInputShutdown = true;
    }

    /**
     * Closes the output stream of this socket. All buffered data will be sent
     * followed by the termination sequence. Writing to the closed output stream
     * will cause an {@code IOException}.
     * 
     * @throws IOException
     *             if an error occurs while closing the socket output stream.
     * @throws SocketException
     *             if the output stream is already closed.
     */
    public void shutdownOutput() throws IOException {
        if (isOutputShutdown()) {
            throw new SocketException(Messages.getString("luni.75")); //$NON-NLS-1$
        }
        checkClosedAndCreate(false);
        impl.shutdownOutput();
        isOutputShutdown = true;
    }

    /**
     * Checks whether the socket is closed, and throws an exception. Otherwise
     * creates the underlying SocketImpl.
     * 
     * @throws SocketException
     *             if the socket is closed.
     */
    private void checkClosedAndCreate(boolean create) throws SocketException {
        if (isClosed()) {
            throw new SocketException(Messages.getString("luni.0C")); //$NON-NLS-1$
        }
        if (!create) {
            if (!isConnected()) {
                throw new SocketException(Messages.getString("luni.78")); //$NON-NLS-1$
                // a connected socket must be created
            }

            /*
             * return directly to fix a possible bug, if !create, should return
             * here
             */
            return;
        }
        if (isCreated) {
            return;
        }
        synchronized (this) {
            if (isCreated) {
                return;
            }
            try {
                impl.create(true);
            } catch (SocketException e) {
                throw e;
            } catch (IOException e) {
                throw new SocketException(e.toString());
            }
            isCreated = true;
        }
    }

    /**
     * Gets the local address and port of this socket as a SocketAddress or
     * {@code null} if the socket is unbound. This is useful on multihomed
     * hosts.
     * 
     * @return the bound local socket address and port.
     */
    public SocketAddress getLocalSocketAddress() {
        if (!isBound()) {
            return null;
        }
        return new InetSocketAddress(getLocalAddress(), getLocalPort());
    }

    /**
     * Gets the remote address and port of this socket as a {@code
     * SocketAddress} or {@code null} if the socket is not connected.
     * 
     * @return the remote socket address and port.
     */
    public SocketAddress getRemoteSocketAddress() {
        if (!isConnected()) {
            return null;
        }
        return new InetSocketAddress(getInetAddress(), getPort());
    }

    /**
     * Returns whether this socket is bound to a local address and port.
     * 
     * @return {@code true} if the socket is bound to a local address, {@code
     *         false} otherwise.
     */
    public boolean isBound() {
        return isBound;
    }

    /**
     * Returns whether this socket is connected to a remote host.
     * 
     * @return {@code true} if the socket is connected, {@code false} otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Returns whether this socket is closed.
     * 
     * @return {@code true} if the socket is closed, {@code false} otherwise.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Binds this socket to the given local host address and port specified by
     * the SocketAddress {@code localAddr}. If {@code localAddr} is set to
     * {@code null}, this socket will be bound to an available local address on
     * any free port.
     * 
     * @param localAddr
     *            the specific address and port on the local machine to bind to.
     * @throws IllegalArgumentException
     *             if the given SocketAddress is invalid or not supported.
     * @throws IOException
     *             if the socket is already bound or an error occurs while
     *             binding.
     */
    public void bind(SocketAddress localAddr) throws IOException {
        checkClosedAndCreate(true);
        if (isBound()) {
            throw new BindException(Messages.getString("luni.71")); //$NON-NLS-1$
        }

        int port = 0;
        InetAddress addr = InetAddress.ANY;
        if (localAddr != null) {
            if (!(localAddr instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(Messages.getString(
                        "luni.49", localAddr.getClass())); //$NON-NLS-1$
            }
            InetSocketAddress inetAddr = (InetSocketAddress) localAddr;
            if ((addr = inetAddr.getAddress()) == null) {
                throw new SocketException(Messages.getString(
                        "luni.1A", inetAddr.getHostName())); //$NON-NLS-1$
            }
            port = inetAddr.getPort();
        }

        synchronized (this) {
            try {
                impl.bind(addr, port);
                isBound = true;
            } catch (IOException e) {
                impl.close();
                throw e;
            }
        }
    }

    /**
     * Connects this socket to the given remote host address and port specified
     * by the SocketAddress {@code remoteAddr}.
     * 
     * @param remoteAddr
     *            the address and port of the remote host to connect to.
     * @throws IllegalArgumentException
     *             if the given SocketAddress is invalid or not supported.
     * @throws IOException
     *             if the socket is already connected or an error occurs while
     *             connecting.
     */
    public void connect(SocketAddress remoteAddr) throws IOException {
        connect(remoteAddr, 0);
    }

    /**
     * Connects this socket to the given remote host address and port specified
     * by the SocketAddress {@code remoteAddr} with the specified timeout. The
     * connecting method will block until the connection is established or an
     * error occurred.
     * 
     * @param remoteAddr
     *            the address and port of the remote host to connect to.
     * @param timeout
     *            the timeout value in milliseconds or {@code 0} for an infinite
     *            timeout.
     * @throws IllegalArgumentException
     *             if the given SocketAddress is invalid or not supported or the
     *             timeout value is negative.
     * @throws IOException
     *             if the socket is already connected or an error occurs while
     *             connecting.
     */
    public void connect(SocketAddress remoteAddr, int timeout)
            throws IOException {
        checkClosedAndCreate(true);
        if (timeout < 0) {
            throw new IllegalArgumentException(Messages.getString("luni.5B")); //$NON-NLS-1$
        }
        if (isConnected()) {
            throw new SocketException(Messages.getString("luni.5F")); //$NON-NLS-1$
        }
        if (remoteAddr == null) {
            throw new IllegalArgumentException(Messages.getString("luni.5D")); //$NON-NLS-1$
        }

        if (!(remoteAddr instanceof InetSocketAddress)) {
            throw new IllegalArgumentException(Messages.getString(
                    "luni.49", remoteAddr.getClass())); //$NON-NLS-1$
        }
        InetSocketAddress inetAddr = (InetSocketAddress) remoteAddr;
        InetAddress addr;
        if ((addr = inetAddr.getAddress()) == null) {
            throw new UnknownHostException(Messages.getString("luni.1A", remoteAddr));//$NON-NLS-1$
        }
        int port = inetAddr.getPort();

        checkDestination(addr, port);
        synchronized (connectLock) {
            try {
                if (!isBound()) {
                    // socket allready created at this point by earlier call or
                    // checkClosedAndCreate this caused us to lose socket
                    // options on create
                    // impl.create(true);
                    if (!NetUtil.usingSocks(proxy)) {
                        impl.bind(InetAddress.ANY, 0);
                    }
                    isBound = true;
                }
                impl.connect(remoteAddr, timeout);
                isConnected = true;
            } catch (IOException e) {
                impl.close();
                throw e;
            }
        }
    }

    /**
     * Returns whether the incoming channel of the socket has already been
     * closed.
     * 
     * @return {@code true} if reading from this socket is not possible anymore,
     *         {@code false} otherwise.
     */
    public boolean isInputShutdown() {
        return isInputShutdown;
    }

    /**
     * Returns whether the outgoing channel of the socket has already been
     * closed.
     * 
     * @return {@code true} if writing to this socket is not possible anymore,
     *         {@code false} otherwise.
     */
    public boolean isOutputShutdown() {
        return isOutputShutdown;
    }

    /**
     * Sets the state of the {@code SocketOptions.SO_REUSEADDR} for this socket.
     * 
     * @param reuse
     *            the state whether this option is enabled or not.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#SO_REUSEADDR
     */
    public void setReuseAddress(boolean reuse) throws SocketException {
        checkClosedAndCreate(true);
        impl.setOption(SocketOptions.SO_REUSEADDR, reuse ? Boolean.TRUE
                : Boolean.FALSE);
    }

    /**
     * Gets the setting of the socket option {@code SocketOptions.SO_REUSEADDR}.
     * 
     * @return {@code true} if the {@code SocketOptions.SO_REUSEADDR} is
     *         enabled, {@code false} otherwise.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_REUSEADDR
     */
    public boolean getReuseAddress() throws SocketException {
        checkClosedAndCreate(true);
        return ((Boolean) impl.getOption(SocketOptions.SO_REUSEADDR))
                .booleanValue();
    }

    /**
     * Sets the state of the {@code SocketOptions.SO_OOBINLINE} for this socket.
     * When this option is enabled urgent data can be received in-line with
     * normal data.
     * 
     * @param oobinline
     *            whether this option is enabled or not.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#SO_OOBINLINE
     */
    public void setOOBInline(boolean oobinline) throws SocketException {
        checkClosedAndCreate(true);
        impl.setOption(SocketOptions.SO_OOBINLINE, oobinline ? Boolean.TRUE
                : Boolean.FALSE);
    }

    /**
     * Gets the setting of the socket option {@code SocketOptions.SO_OOBINLINE}.
     * 
     * @return {@code true} if the {@code SocketOptions.SO_OOBINLINE} is
     *         enabled, {@code false} otherwise.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#SO_OOBINLINE
     */
    public boolean getOOBInline() throws SocketException {
        checkClosedAndCreate(true);
        return ((Boolean) impl.getOption(SocketOptions.SO_OOBINLINE))
                .booleanValue();
    }

    /**
     * Sets the value of the {@code SocketOptions.IP_TOS} for this socket. See
     * the specification RFC 1349 for more information about the type of service
     * field.
     * 
     * @param value
     *            the value to be set for this option with a valid range of
     *            {@code 0-255}.
     * @throws SocketException
     *             if an error occurs while setting the option.
     * @see SocketOptions#IP_TOS
     */
    public void setTrafficClass(int value) throws SocketException {
        checkClosedAndCreate(true);
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException();
        }
        impl.setOption(SocketOptions.IP_TOS, Integer.valueOf(value));
    }

    /**
     * Gets the value of the socket option {@code SocketOptions.IP_TOS}.
     * 
     * @return the value which represents the type of service.
     * @throws SocketException
     *             if an error occurs while reading the socket option.
     * @see SocketOptions#IP_TOS
     */
    public int getTrafficClass() throws SocketException {
        checkClosedAndCreate(true);
        return ((Number) impl.getOption(SocketOptions.IP_TOS)).intValue();
    }

    /**
     * Sends the given single byte data which is represented by the lowest octet
     * of {@code value} as "TCP urgent data".
     * 
     * @param value
     *            the byte of urgent data to be sent.
     * @throws IOException
     *             if an error occurs while sending urgent data.
     */
    public void sendUrgentData(int value) throws IOException {
        if (!impl.supportsUrgentData()) {
            throw new SocketException(Messages.getString("luni.79")); //$NON-NLS-1$
        }
        impl.sendUrgentData(value);
    }

    /**
     * Set the appropriate flags for a socket created by {@code
     * ServerSocket.accept()}.
     * 
     * @see ServerSocket#implAccept
     */
    void accepted() {
        isCreated = isBound = isConnected = true;
    }

    static boolean preferIPv4Stack() {
        return NetUtil.preferIPv4Stack();
    }

    /**
     * Gets the SocketChannel of this socket, if one is available. The current
     * implementation of this method returns always {@code null}.
     * 
     * @return the related SocketChannel or {@code null} if no channel exists.
     */
    public SocketChannel getChannel() {
        return null;
    }

    /**
     * Sets performance preferences for connectionTime, latency and bandwidth.
     * <p>
     * This method does currently nothing.
     *
     * @param connectionTime
     *            the value representing the importance of a short connecting
     *            time.
     * @param latency
     *            the value representing the importance of low latency.
     * @param bandwidth
     *            the value representing the importance of high bandwidth.
     */
    public void setPerformancePreferences(int connectionTime, int latency,
            int bandwidth) {
        // Our socket implementation only provide one protocol: TCP/IP, so
        // we do nothing for this method
    }
}
