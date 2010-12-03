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

package org.apache.harmony.luni.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOptions;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.harmony.luni.platform.INetworkSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.internal.nls.Messages;

/**
 * A concrete connected-socket implementation.
 */
public class PlainSocketImpl extends SocketImpl {

    // Const copy from socket

    static final int MULTICAST_IF = 1;

    static final int MULTICAST_TTL = 2;

    static final int TCP_NODELAY = 4;

    static final int FLAG_SHUTDOWN = 8;

    // For SOCKS support. A SOCKS bind() uses the last
    // host connected to in its request.
    static private InetAddress lastConnectedAddress;

    static private int lastConnectedPort;

    private static Field fdField;

    private static Field localportField;

    private boolean tcpNoDelay = true;

    /**
     * used to store the trafficClass value which is simply returned as the
     * value that was set. We also need it to pass it to methods that specify an
     * address packets are going to be sent to
     */
    private int trafficClass;

    protected INetworkSystem netImpl = Platform.getNetworkSystem();

    public int receiveTimeout = 0;

    public boolean streaming = true;

    public boolean shutdownInput;

    Proxy proxy;

    public PlainSocketImpl() {
        super();
        fd = new FileDescriptor();
    }

    public PlainSocketImpl(FileDescriptor fd) {
        super();
        this.fd = fd;
    }
    
    /**
     * creates an instance with specified proxy.
     */
    public PlainSocketImpl(Proxy proxy) {
        this();
        this.proxy = proxy;
    }
    
    public PlainSocketImpl(FileDescriptor fd, int localport, InetAddress addr, int port) {
        super();
        this.fd = fd;
        this.localport = localport;
        this.address = addr;
        this.port = port;
    }
    
    @Override
    protected void accept(SocketImpl newImpl) throws IOException {
        if (NetUtil.usingSocks(proxy)) {
            ((PlainSocketImpl) newImpl).socksBind();
            ((PlainSocketImpl) newImpl).socksAccept();
            return;
        }

        try {
            if (newImpl instanceof PlainSocketImpl) {
                PlainSocketImpl newPlainSocketImpl = (PlainSocketImpl) newImpl;
                netImpl.acceptStreamSocket(fd, newImpl, newPlainSocketImpl
                        .getFileDescriptor(), receiveTimeout);
                newPlainSocketImpl.setLocalport(getLocalPort());
            } else {
                // if newImpl is not an instance of PlainSocketImpl, use
                // reflection to get/set protected fields.
                if (null == fdField) {
                    fdField = getSocketImplField("fd"); //$NON-NLS-1$
                }
                FileDescriptor newFd = (FileDescriptor) fdField.get(newImpl);
                netImpl.acceptStreamSocket(fd, newImpl, newFd, receiveTimeout);

                if (null == localportField) {
                    localportField = getSocketImplField("localport"); //$NON-NLS-1$
                }
                localportField.setInt(newImpl, getLocalPort());
            }
        } catch (InterruptedIOException e) {
            throw new SocketTimeoutException(e.getMessage());
        } catch (IllegalAccessException e) {
            // empty
        }
    }

    /**
     * gets SocketImpl field by reflection.
     */
    private Field getSocketImplField(final String fieldName) {
        return AccessController.doPrivileged(new PrivilegedAction<Field>() {
            public Field run() {
                Field field = null;
                try {
                    field = SocketImpl.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    throw new Error(e);
                }
                return field;
            }
        });
    }

    @Override
    protected synchronized int available() throws IOException {
        // we need to check if the input has been shutdown. If so
        // we should return that there is no data to be read
        if (shutdownInput == true) {
            return 0;
        }
        return netImpl.availableStream(fd);
    }

    @Override
    protected void bind(InetAddress anAddr, int aPort) throws IOException {
        netImpl.bind(fd, anAddr, aPort);
        // PlainSocketImpl2.socketBindImpl2(fd, aPort, anAddr);
        address = anAddr;
        if (0 != aPort) {
            localport = aPort;
        } else {
            localport = netImpl.getSocketLocalPort(fd, NetUtil
                    .preferIPv6Addresses());
        }
    }

    @Override
    protected void close() throws IOException {
        synchronized (fd) {
            if (fd.valid()) {
                if ((netImpl.getSocketFlags() & FLAG_SHUTDOWN) != 0) {
                    try {
                        shutdownOutput();
                    } catch (Exception e) {
                    }
                }
                netImpl.socketClose(fd);
                fd = new FileDescriptor();
            }
        }
    }

    @Override
    protected void connect(String aHost, int aPort) throws IOException {
        InetAddress anAddr = netImpl.getHostByName(aHost, NetUtil
                .preferIPv6Addresses());
        connect(anAddr, aPort);
    }

    @Override
    protected void connect(InetAddress anAddr, int aPort) throws IOException {
        connect(anAddr, aPort, 0);
    }

    /**
     * Connects this socket to the specified remote host address/port.
     * 
     * @param anAddr
     *            the remote host address to connect to
     * @param aPort
     *            the remote port to connect to
     * @param timeout
     *            a timeout where supported. 0 means no timeout
     * @throws IOException
     *             if an error occurs while connecting
     */
    private void connect(InetAddress anAddr, int aPort, int timeout)
            throws IOException {
        
        InetAddress normalAddr = anAddr.isAnyLocalAddress() ? InetAddress.getLocalHost() : anAddr;
        try {
            if (streaming) {
                if (NetUtil.usingSocks(proxy)) {
                    socksConnect(anAddr, aPort, 0);
                } else {
                    if (timeout == 0) {
                        netImpl.connect(fd, trafficClass, normalAddr, aPort);
                    } else {
                        netImpl.connectStreamWithTimeoutSocket(fd, aPort,
                                timeout, trafficClass, normalAddr);
                    }
                }
            } else {
            	netImpl.connectDatagram(fd, aPort, trafficClass, normalAddr);
            }
        } catch (ConnectException e) {
            throw new ConnectException(anAddr + ":" + aPort + " - "
                    + e.getMessage());
        }
        super.address = normalAddr;
        super.port = aPort;
    }

    @Override
    protected void create(boolean streaming) throws IOException {
        this.streaming = streaming;
        if (streaming) {
            netImpl.createStreamSocket(fd, NetUtil.preferIPv4Stack());
        } else {
            netImpl.createDatagramSocket(fd, NetUtil.preferIPv4Stack());
        }
    }

    @Override
    protected void finalize() throws IOException {
        close();
    }

    @Override
    protected synchronized InputStream getInputStream() throws IOException {
        if (!fd.valid()) {
            throw new SocketException(Messages.getString("luni.0C"));
        }

        return new SocketInputStream(this);
    }

    @Override
    public Object getOption(int optID) throws SocketException {
        if (optID == SocketOptions.SO_TIMEOUT) {
            return Integer.valueOf(receiveTimeout);
        } else if (optID == SocketOptions.IP_TOS) {
            return Integer.valueOf(trafficClass);
        } else {
            // Call the native first so there will be
            // an exception if the socket if closed.
            Object result = netImpl.getSocketOption(fd, optID);
            if (optID == SocketOptions.TCP_NODELAY
                    && (netImpl.getSocketFlags() & TCP_NODELAY) != 0) {
                return Boolean.valueOf(tcpNoDelay);
            }
            return result;
        }
    }

    @Override
    protected synchronized OutputStream getOutputStream() throws IOException {
        if (!fd.valid()) {
            throw new SocketException(Messages.getString("luni.0C")); //$NON-NLS-1$
        }
        return new SocketOutputStream(this);
    }

    @Override
    protected void listen(int backlog) throws IOException {
        if (NetUtil.usingSocks(proxy)) {
            // Do nothing for a SOCKS connection. The listen occurs on the
            // server during the bind.
            return;
        }
        netImpl.listenStreamSocket(fd, backlog);
    }

    @Override
    public void setOption(int optID, Object val) throws SocketException {
        if (optID == SocketOptions.SO_TIMEOUT) {
            receiveTimeout = ((Integer) val).intValue();
        } else {
            try {
                netImpl.setSocketOption(fd, optID, val);
                if (optID == SocketOptions.TCP_NODELAY
                        && (netImpl.getSocketFlags() & TCP_NODELAY) != 0) {
                    tcpNoDelay = ((Boolean) val).booleanValue();
                }
            } catch (SocketException e) {
                // we don't throw an exception for IP_TOS even if the platform
                // won't let us set the requested value
                if (optID != SocketOptions.IP_TOS) {
                    throw e;
                }
            }

            /*
             * save this value as it is actually used differently for IPv4 and
             * IPv6 so we cannot get the value using the getOption. The option
             * is actually only set for IPv4 and a masked version of the value
             * will be set as only a subset of the values are allowed on the
             * socket. Therefore we need to retain it to return the value that
             * was set. We also need the value to be passed into a number of
             * natives so that it can be used properly with IPv6
             */
            if (optID == SocketOptions.IP_TOS) {
                trafficClass = ((Integer) val).intValue();
            }
        }
    }

    /**
     * Gets the SOCKS proxy server port.
     */
    private int socksGetServerPort() {
        // get socks server port from proxy. It is unnecessary to check
        // "socksProxyPort" property, since proxy setting should only be
        // determined by ProxySelector.
        InetSocketAddress addr = (InetSocketAddress) proxy.address();
        return addr.getPort();

    }

    /**
     * Gets the InetAddress of the SOCKS proxy server.
     */
    private InetAddress socksGetServerAddress() throws UnknownHostException {
        String proxyName;
        // get socks server address from proxy. It is unnecessary to check
        // "socksProxyHost" property, since all proxy setting should be
        // determined by ProxySelector.
        InetSocketAddress addr = (InetSocketAddress) proxy.address();
        proxyName = addr.getHostName();
        if (null == proxyName) {
            proxyName = addr.getAddress().getHostAddress();
        }

        InetAddress anAddr = netImpl.getHostByName(proxyName, NetUtil
                .preferIPv6Addresses());
        return anAddr;
    }

    /**
     * Connect using a SOCKS server.
     */
    private void socksConnect(InetAddress applicationServerAddress,
            int applicationServerPort, int timeout) throws IOException {
        try {
            if (timeout == 0) {
                netImpl.connect(fd, trafficClass, socksGetServerAddress(),
                        socksGetServerPort());
            } else {
                netImpl.connectStreamWithTimeoutSocket(fd,
                        socksGetServerPort(), timeout, trafficClass,
                        socksGetServerAddress());
            }

        } catch (Exception e) {
            throw new SocketException(Messages.getString("luni.0D", e)); //$NON-NLS-1$
        }

        socksRequestConnection(applicationServerAddress, applicationServerPort);

        lastConnectedAddress = applicationServerAddress;
        lastConnectedPort = applicationServerPort;
    }

    /**
     * Request a SOCKS connection to the application server given. If the
     * request fails to complete successfully, an exception is thrown.
     */
    private void socksRequestConnection(InetAddress applicationServerAddress,
            int applicationServerPort) throws IOException {
        socksSendRequest(Socks4Message.COMMAND_CONNECT,
                applicationServerAddress, applicationServerPort);
        Socks4Message reply = socksReadReply();
        if (reply.getCommandOrResult() != Socks4Message.RETURN_SUCCESS) {
            throw new IOException(reply.getErrorString(reply
                    .getCommandOrResult()));
        }
    }

    /**
     * Perform an accept for a SOCKS bind.
     */
    public void socksAccept() throws IOException {
        Socks4Message reply = socksReadReply();
        if (reply.getCommandOrResult() != Socks4Message.RETURN_SUCCESS) {
            throw new IOException(reply.getErrorString(reply
                    .getCommandOrResult()));
        }
    }

    /**
     * Shutdown the input portion of the socket.
     */
    @Override
    protected void shutdownInput() throws IOException {
        shutdownInput = true;
        netImpl.shutdownInput(fd);
    }

    /**
     * Shutdown the output portion of the socket.
     */
    @Override
    protected void shutdownOutput() throws IOException {
        netImpl.shutdownOutput(fd);
    }

    /**
     * Bind using a SOCKS server.
     */
    private void socksBind() throws IOException {
        try {
            netImpl.connect(fd, trafficClass, socksGetServerAddress(),
                    socksGetServerPort());
        } catch (Exception e) {
            throw new IOException(Messages.getString("luni.0E", e)); //$NON-NLS-1$
        }

        // There must be a connection to an application host for the bind to
        // work.
        if (lastConnectedAddress == null) {
            throw new SocketException(Messages.getString("luni.0F")); //$NON-NLS-1$
        }

        // Use the last connected address and port in the bind request.
        socksSendRequest(Socks4Message.COMMAND_BIND, lastConnectedAddress,
                lastConnectedPort);
        Socks4Message reply = socksReadReply();

        if (reply.getCommandOrResult() != Socks4Message.RETURN_SUCCESS) {
            throw new IOException(reply.getErrorString(reply
                    .getCommandOrResult()));
        }

        // A peculiarity of socks 4 - if the address returned is 0, use the
        // original socks server address.
        if (reply.getIP() == 0) {
            address = socksGetServerAddress();
        } else {
            // IPv6 support not yet required as
            // currently the Socks4Message.getIP() only returns int,
            // so only works with IPv4 4byte addresses
            byte[] replyBytes = new byte[4];
            NetUtil.intToBytes(reply.getIP(), replyBytes, 0);
            address = InetAddress.getByAddress(replyBytes);
        }
        localport = reply.getPort();
    }

    /**
     * Send a SOCKS V4 request.
     */
    private void socksSendRequest(int command, InetAddress address, int port)
            throws IOException {
        Socks4Message request = new Socks4Message();
        request.setCommandOrResult(command);
        request.setPort(port);
        request.setIP(address.getAddress());
        request.setUserId("default"); //$NON-NLS-1$

        getOutputStream().write(request.getBytes(), 0, request.getLength());
    }

    /**
     * Read a SOCKS V4 reply.
     */
    private Socks4Message socksReadReply() throws IOException {
        Socks4Message reply = new Socks4Message();
        int bytesRead = 0;
        while (bytesRead < Socks4Message.REPLY_LENGTH) {
            int count = getInputStream().read(reply.getBytes(), bytesRead,
                    Socks4Message.REPLY_LENGTH - bytesRead);
            if (-1 == count) {
                break;
            }
            bytesRead += count;
        }
        if (Socks4Message.REPLY_LENGTH != bytesRead) {
            throw new SocketException(Messages.getString("luni.10")); //$NON-NLS-1$
        }
        return reply;
    }

    @Override
    protected void connect(SocketAddress remoteAddr, int timeout)
            throws IOException {
        InetSocketAddress inetAddr = (InetSocketAddress) remoteAddr;
        connect(inetAddr.getAddress(), inetAddr.getPort(), timeout);
    }

    /**
     * Answer if the socket supports urgent data.
     */
    @Override
    protected boolean supportsUrgentData() {
        return !streaming || netImpl.supportsUrgentData(fd);
    }

    @Override
    protected void sendUrgentData(int value) throws IOException {
        netImpl.sendUrgentData(fd, (byte) value);
    }

    FileDescriptor getFD() {
        return fd;
    }

    private void setLocalport(int localport) {
        this.localport = localport;
    }

    int read(byte[] buffer, int offset, int count) throws IOException {
        if (shutdownInput) {
            return -1;
        }
        int read = netImpl.read(fd, buffer, offset, count, receiveTimeout);
        // Return of zero bytes for a blocking socket means a timeout occurred
        if (read == 0) {
            throw new SocketTimeoutException();
        }
        // Return of -1 indicates the peer was closed
        if (read == -1) {
            shutdownInput = true;
        }
        return read;
    }

    int write(byte[] buffer, int offset, int count) throws IOException {
        if (!streaming) {
            return netImpl.sendDatagram2(fd, buffer, offset, count, port,
                    address);
        }
        return netImpl.write(fd, buffer, offset, count);
    }
}
