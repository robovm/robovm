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
import java.nio.channels.DatagramChannel;

import org.apache.harmony.luni.net.PlainDatagramSocketImpl;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.internal.nls.Messages;

/**
 * This class implements a UDP socket for sending and receiving {@code
 * DatagramPacket}. A {@code DatagramSocket} object can be used for both
 * endpoints of a connection for a packet delivery service.
 * 
 * @see DatagramPacket
 * @see DatagramSocketImplFactory
 */
public class DatagramSocket {

    DatagramSocketImpl impl;

    InetAddress address;

    int port = -1;

    static DatagramSocketImplFactory factory;

    boolean isBound = false;

    private boolean isConnected = false;

    private boolean isClosed = false;

    private static class Lock {
    }

    static {
        Platform.getNetworkSystem().oneTimeInitialization(true);
    }
    
    private Object lock = new Lock();

    /**
     * Constructs a UDP datagram socket which is bound to any available port on
     * the localhost.
     * 
     * @throws SocketException
     *             if an error occurs while creating or binding the socket.
     */
    public DatagramSocket() throws SocketException {
        this(0);
    }

    /**
     * Constructs a UDP datagram socket which is bound to the specific port
     * {@code aPort} on the localhost. Valid values for {@code aPort} are
     * between 0 and 65535 inclusive.
     * 
     * @param aPort
     *            the port to bind on the localhost.
     * @throws SocketException
     *             if an error occurs while creating or binding the socket.
     */
    public DatagramSocket(int aPort) throws SocketException {
        super();
        checkListen(aPort);
        createSocket(aPort, InetAddress.ANY);
    }

    /**
     * Constructs a UDP datagram socket which is bound to the specific local
     * address {@code addr} on port {@code aPort}. Valid values for {@code
     * aPort} are between 0 and 65535 inclusive.
     * 
     * @param aPort
     *            the port to bind on the localhost.
     * @param addr
     *            the address to bind on the localhost.
     * @throws SocketException
     *             if an error occurs while creating or binding the socket.
     */
    public DatagramSocket(int aPort, InetAddress addr) throws SocketException {
        super();
        checkListen(aPort);
        createSocket(aPort, null == addr ? InetAddress.ANY : addr);
    }

    /**
     * Sends prior to attempting to bind the socket, checks whether the port is
     * within the valid port range and verifies with the security manager that
     * the port may be bound by the current context.
     * 
     * @param aPort
     *            the port on the localhost that is to be bound.
     */
    void checkListen(int aPort) {
        if (aPort < 0 || aPort > 65535) {
            throw new IllegalArgumentException(Messages.getString("luni.56", aPort)); //$NON-NLS-1$
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkListen(aPort);
        }
    }

    /**
     * Closes this UDP datagram socket and all possibly associated channels.
     */
    // In the documentation jdk1.1.7a/guide/net/miscNet.html, this method is
    // noted as not being synchronized.
    public void close() {
        isClosed = true;
        impl.close();
    }

    /**
     * Connects this UDP datagram socket to the specific target host with the
     * address {@code anAdress} on port {@code aPort}. The host and port are
     * validated, thereafter the only validation on {@code send()} and {@code
     * receive()} is to check whether the packet address/port matches the
     * connected target.
     * 
     * @param anAddress
     *            the target address of this socket.
     * @param aPort
     *            the target port of this socket.
     */
    public void connect(InetAddress anAddress, int aPort) {
        if (anAddress == null || aPort < 0 || aPort > 65535) {
            throw new IllegalArgumentException(Messages.getString("luni.38")); //$NON-NLS-1$
        }

        synchronized (lock) {
            if (isClosed()) {
                return;
            }
            try {
                checkClosedAndBind(true);
            } catch (SocketException e) {
                // Ignored
            }

            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                if (anAddress.isMulticastAddress()) {
                    security.checkMulticast(anAddress);
                } else {
                    security.checkConnect(anAddress.getHostName(), aPort);
                }
            }

            try {
                impl.connect(anAddress, aPort);
            } catch (SocketException e) {
                // not connected at the native level just do what we did before
            }
            address = anAddress;
            port = aPort;
            isConnected = true;
        }
    }

    /**
     * Disconnects this UDP datagram socket from the remote host. This method
     * called on an unconnected socket does nothing.
     */
    public void disconnect() {
        if (isClosed() || !isConnected()) {
            return;
        }
        impl.disconnect();
        address = null;
        port = -1;
        isConnected = false;
    }

    synchronized void createSocket(int aPort, InetAddress addr)
            throws SocketException {
        impl = factory != null ? factory.createDatagramSocketImpl()
                : new PlainDatagramSocketImpl();
        impl.create();
        try {
            impl.bind(aPort, addr);
            isBound = true;
        } catch (SocketException e) {
            close();
            throw e;
        }
    }

    /**
     * Gets the {@code InetAddress} instance representing the remote address to
     * which this UDP datagram socket is connected.
     * 
     * @return the remote address this socket is connected to or {@code null} if
     *         this socket is not connected.
     */
    public InetAddress getInetAddress() {
        return address;
    }

    /**
     * Gets the {@code InetAddress} instance representing the bound local
     * address of this UDP datagram socket.
     * 
     * @return the local address to which this socket is bound to or {@code
     *         null} if this socket is closed.
     */
    public InetAddress getLocalAddress() {
        if (isClosed()) {
            return null;
        }
        if (!isBound()) {
            return InetAddress.ANY;
        }
        InetAddress anAddr = impl.getLocalAddress();
        try {
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                security.checkConnect(anAddr.getHostName(), -1);
            }
        } catch (SecurityException e) {
            return InetAddress.ANY;
        }
        return anAddr;
    }

    /**
     * Gets the local port which this socket is bound to.
     * 
     * @return the local port of this socket or {@code -1} if this socket is
     *         closed and {@code 0} if it is unbound.
     */
    public int getLocalPort() {
        if (isClosed()) {
            return -1;
        }
        if (!isBound()) {
            return 0;
        }
        return impl.getLocalPort();
    }

    /**
     * Gets the remote port which this socket is connected to.
     * 
     * @return the remote port of this socket. The return value {@code -1}
     *         indicates that this socket is not connected.
     */
    public int getPort() {
        return port;
    }

    /**
     * Indicates whether this socket is multicast or not.
     * 
     * @return the return value is always {@code false}.
     */
    boolean isMulticastSocket() {
        return false;
    }

    /**
     * Gets the socket receive buffer size. ( {@code SocketOptions.SO_RCVBUF} )
     * 
     * @return the input buffer size.
     * @throws SocketException
     *                if an error occurs while getting the option value.
     */
    public synchronized int getReceiveBufferSize() throws SocketException {
        checkClosedAndBind(false);
        return ((Integer) impl.getOption(SocketOptions.SO_RCVBUF)).intValue();
    }

    /**
     * Gets the socket send buffer size. ( {@code SocketOptions.SO_SNDBUF} )
     * 
     * @return the output buffer size.
     * @throws SocketException
     *                if an error occurs while getting the option value.
     */
    public synchronized int getSendBufferSize() throws SocketException {
        checkClosedAndBind(false);
        return ((Integer) impl.getOption(SocketOptions.SO_SNDBUF)).intValue();
    }

    /**
     * Gets the socket receive timeout in milliseconds. The return value {@code
     * 0} implies the timeout is disabled/infinitive. ( {@code
     * SocketOptions.SO_TIMEOUT} )
     * 
     * @return the socket receive timeout.
     * @throws SocketException
     *                if an error occurs while getting the option value.
     */
    public synchronized int getSoTimeout() throws SocketException {
        checkClosedAndBind(false);
        return ((Integer) impl.getOption(SocketOptions.SO_TIMEOUT)).intValue();
    }

    /**
     * Receives a packet from this socket and stores it in the argument {@code
     * pack}. All fields of {@code pack} must be set according to the data
     * received. If the received data is longer than the packet buffer size it
     * is truncated. This method blocks until a packet is received or a timeout
     * has expired. If a security manager exists, its {@code checkAccept} method
     * determines whether or not a packet is discarded. Any packets from
     * unacceptable origins are silently discarded.
     * 
     * @param pack
     *            the {@code DatagramPacket} to store the received data.
     * @throws IOException
     *                if an error occurs while receiving the packet.
     */
    public synchronized void receive(DatagramPacket pack) throws IOException {
        checkClosedAndBind(true);

        InetAddress senderAddr;
        int senderPort;
        DatagramPacket tempPack = new DatagramPacket(new byte[1], 1);
        
        // means that we have received the packet into the temporary buffer
        boolean copy = false;

        SecurityManager security = System.getSecurityManager();

        if (address != null || security != null) { 
            // The socket is connected or we need to check security permissions

            // Check pack before peeking
            if (pack == null) {
                throw new NullPointerException();
            }
 
            // iterate over incoming packets
            while (true) {
                copy = false;
                
                // let's get sender's address and port
                try {
                    senderPort = impl.peekData(tempPack);
                    senderAddr = tempPack.getAddress();
                } catch (SocketException e) {
                    if (e.getMessage().equals(
                            "The socket does not support the operation")) { //$NON-NLS-1$
                        // receive packet to temporary buffer
                        tempPack = new DatagramPacket(new byte[pack.getCapacity()],
                                pack.getCapacity());
                        impl.receive(tempPack);
                        // tempPack's length field is now updated, capacity is unchanged 
                        // let's extract address & port
                        senderAddr = tempPack.getAddress();
                        senderPort = tempPack.getPort();
                        copy = true;
                    } else {
                        throw e;
                    }
                }
                
                if (address == null) {
                    // if we are not connected let's check if we are allowed to
                    // receive packets from sender's address and port 
                    try {
                        security.checkAccept(senderAddr.getHostName(),
                                senderPort);
                        // address & port are valid
                        break;
                    } catch (SecurityException e) {
                        if (!copy) {
                            // drop this packet and continue
                            impl.receive(tempPack);
                        }
                    }
                } else if (port == senderPort && address.equals(senderAddr)) {
                    // we are connected and the packet came
                    // from the address & port we are connected to
                    break;
                } else if (!copy) {
                    // drop packet and continue
                    impl.receive(tempPack);
                }
            }
        }

        if (copy) {
            System.arraycopy(tempPack.getData(), 0, pack.getData(), pack
                    .getOffset(), tempPack.getLength());
            // we shouldn't update the pack's capacity field in order to be
            // compatible with RI
            pack.setLengthOnly(tempPack.getLength());
            pack.setAddress(tempPack.getAddress());
            pack.setPort(tempPack.getPort());
        } else {
            pack.setLength(pack.getCapacity());
            impl.receive(pack);
            // pack's length field is now updated by native code call;
            // pack's capacity field is unchanged 
        }
    }

    /**
     * Sends a packet over this socket. The packet must satisfy the security
     * policy before it may be sent. If a security manager is installed, this
     * method checks whether it is allowed to send this packet to the specified
     * address.
     * 
     * @param pack
     *            the {@code DatagramPacket} which has to be sent.
     * @throws IOException
     *                if an error occurs while sending the packet.
     */
    public void send(DatagramPacket pack) throws IOException {
        checkClosedAndBind(true);

        InetAddress packAddr = pack.getAddress();
        if (address != null) { // The socket is connected
            if (packAddr != null) {
                if (!address.equals(packAddr) || port != pack.getPort()) {
                    throw new IllegalArgumentException(Messages.getString("luni.58")); //$NON-NLS-1$
                }
            } else {
                pack.setAddress(address);
                pack.setPort(port);
            }
        } else {
            // not connected so the target address is not allowed to be null
            if (packAddr == null) {
                    // KA019 Destination address is null
                    throw new NullPointerException(Messages.getString("luni.59")); //$NON-NLS-1$
            }
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                if (packAddr.isMulticastAddress()) {
                    security.checkMulticast(packAddr);
                } else {
                    security.checkConnect(packAddr.getHostName(), pack
                            .getPort());
                }
            }
        }
        impl.send(pack);
    }

    /**
     * Sets the socket send buffer size. This buffer size determines which the
     * maximum packet size is that can be sent over this socket. It depends on
     * the network implementation what will happen if the packet is bigger than
     * the buffer size. ( {@code SocketOptions.SO_SNDBUF} )
     * 
     * @param size
     *            the buffer size in bytes. The size must be at least one byte.
     * @throws SocketException
     *                if an error occurs while setting the option.
     */
    public synchronized void setSendBufferSize(int size) throws SocketException {
        if (size < 1) {
            throw new IllegalArgumentException(Messages.getString("luni.5A")); //$NON-NLS-1$
        }
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.SO_SNDBUF, Integer.valueOf(size));
    }

    /**
     * Sets the socket receive buffer size. This buffer size determines which
     * the maximum packet size is that can be received over this socket. It
     * depends on the network implementation what will happen if the packet is
     * bigger than the buffer size. ( {@code SocketOptions.SO_RCVBUF} )
     * 
     * @param size
     *            the buffer size in bytes. The size must be at least one byte.
     * @throws SocketException
     *                if an error occurs while setting the option.
     */
    public synchronized void setReceiveBufferSize(int size)
            throws SocketException {
        if (size < 1) {
            throw new IllegalArgumentException(Messages.getString("luni.5A")); //$NON-NLS-1$
        }
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.SO_RCVBUF, Integer.valueOf(size));
    }

    /**
     * Sets the timeout period in milliseconds for the {@code receive()} method.
     * This receive timeout defines the period the socket will block waiting to
     * receive data before throwing an {@code InterruptedIOException}. The value
     * {@code 0} (default) is used to set an infinite timeout. To have effect
     * this option must be set before the blocking method was called. ( {@code
     * SocketOptions.SO_TIMEOUT} )
     * 
     * @param timeout
     *            the timeout period in milliseconds or {@code 0} for infinite.
     * @throws SocketException
     *                if an error occurs while setting the option.
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (timeout < 0) {
            throw new IllegalArgumentException(Messages.getString("luni.5B")); //$NON-NLS-1$
        }
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.SO_TIMEOUT, Integer.valueOf(timeout));
    }

    /**
     * Sets the socket implementation factory. This may only be invoked once
     * over the lifetime of the application. This factory is used to create
     * a new datagram socket implementation. If a security manager is set its
     * method {@code checkSetFactory()} is called to check if the operation is
     * allowed. A {@code SecurityException} is thrown if the operation is not
     * allowed.
     * 
     * @param fac
     *            the socket factory to use.
     * @throws IOException
     *                if the factory has already been set.
     * @see DatagramSocketImplFactory
     */
    public static synchronized void setDatagramSocketImplFactory(
            DatagramSocketImplFactory fac) throws IOException {
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
     * Constructs a new {@code DatagramSocket} using the specific datagram
     * socket implementation {@code socketImpl}. The created {@code
     * DatagramSocket} will not be bound.
     * 
     * @param socketImpl
     *            the DatagramSocketImpl to use.
     */
    protected DatagramSocket(DatagramSocketImpl socketImpl) {
        if (socketImpl == null) {
            throw new NullPointerException();
        }
        impl = socketImpl;
    }

    /**
     * Constructs a new {@code DatagramSocket} bound to the host/port specified
     * by the {@code SocketAddress} {@code localAddr} or an unbound {@code
     * DatagramSocket} if the {@code SocketAddress} is {@code null}.
     * 
     * @param localAddr
     *            the local machine address and port to bind to.
     * @throws IllegalArgumentException
     *             if the SocketAddress is not supported
     * @throws SocketException
     *             if a problem occurs creating or binding the socket.
     */
    public DatagramSocket(SocketAddress localAddr) throws SocketException {
        if (localAddr != null) {
            if (!(localAddr instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(Messages.getString(
                        "luni.49", localAddr.getClass())); //$NON-NLS-1$
            }
            checkListen(((InetSocketAddress) localAddr).getPort());
        }
        impl = factory != null ? factory.createDatagramSocketImpl()
                : new PlainDatagramSocketImpl();
        impl.create();
        if (localAddr != null) {
            try {
                bind(localAddr);
            } catch (SocketException e) {
                close();
                throw e;
            }
        }
        // SocketOptions.SO_BROADCAST is set by default for DatagramSocket
        setBroadcast(true);
    }

    void checkClosedAndBind(boolean bind) throws SocketException {
        if (isClosed()) {
            throw new SocketException(Messages.getString("luni.0C")); //$NON-NLS-1$
        }
        if (bind && !isBound()) {
            checkListen(0);
            impl.bind(0, InetAddress.ANY);
            isBound = true;
        }
    }

    /**
     * Binds this socket to the local address and port specified by {@code
     * localAddr}. If this value is {@code null} any free port on a valid local
     * address is used.
     * 
     * @param localAddr
     *            the local machine address and port to bind on.
     * @throws IllegalArgumentException
     *             if the SocketAddress is not supported
     * @throws SocketException
     *             if the socket is already bound or a problem occurs during
     *             binding.
     */
    public void bind(SocketAddress localAddr) throws SocketException {
        checkClosedAndBind(false);
        int localPort = 0;
        InetAddress addr = InetAddress.ANY;
        if (localAddr != null) {
            if (!(localAddr instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(Messages.getString(
                        "luni.49", localAddr.getClass())); //$NON-NLS-1$
            }
            InetSocketAddress inetAddr = (InetSocketAddress) localAddr;
            addr = inetAddr.getAddress();
            if (addr == null) {
                throw new SocketException(Messages.getString(
                        "luni.1A", inetAddr.getHostName())); //$NON-NLS-1$
            }
            localPort = inetAddr.getPort();
            checkListen(localPort);
        }
        impl.bind(localPort, addr);
        isBound = true;
    }

    /**
     * Connects this datagram socket to the remote host and port specified by
     * {@code remoteAddr}. The host and port are validated, thereafter the only
     * validation on {@code send()} and {@code receive()} is that the packet
     * address/port matches the connected target.
     * 
     * @param remoteAddr
     *            the address and port of the target host.
     * @throws SocketException
     *                if an error occurs during connecting.
     */
    public void connect(SocketAddress remoteAddr) throws SocketException {
        if (remoteAddr == null) {
            throw new IllegalArgumentException(Messages.getString("luni.5D")); //$NON-NLS-1$
        }

        if (!(remoteAddr instanceof InetSocketAddress)) {
            throw new IllegalArgumentException(Messages.getString(
                    "luni.49", remoteAddr.getClass())); //$NON-NLS-1$
        }

        InetSocketAddress inetAddr = (InetSocketAddress) remoteAddr;
        if (inetAddr.getAddress() == null) {
            throw new SocketException(Messages.getString(
                    "luni.1A", inetAddr.getHostName())); //$NON-NLS-1$
        }

        synchronized (lock) {
            // make sure the socket is open
            checkClosedAndBind(true);

            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                if (inetAddr.getAddress().isMulticastAddress()) {
                    security.checkMulticast(inetAddr.getAddress());
                } else {
                    security.checkConnect(inetAddr.getAddress().getHostName(),
                            inetAddr.getPort());
                }
            }

            // now try to do the connection at the native level. To be
            // compatible for the case when the address is inaddr_any we just
            // eat the exception an act as if we are connected at the java level
            try {
                impl.connect(inetAddr.getAddress(), inetAddr.getPort());
            } catch (Exception e) {
                // not connected at the native level just do what we did before
            }

            // if we get here then we connected ok
            address = inetAddr.getAddress();
            port = inetAddr.getPort();
            isConnected = true;
        }
    }

    /**
     * Determines whether the socket is bound to an address or not.
     * 
     * @return {@code true} if the socket is bound, {@code false} otherwise.
     */
    public boolean isBound() {
        return isBound;
    }

    /**
     * Determines whether the socket is connected to a target host.
     * 
     * @return {@code true} if the socket is connected, {@code false} otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Gets the address and port of the connected remote host. If this socket is
     * not connected yet, {@code null} is returned.
     * 
     * @return the remote socket address.
     */
    public SocketAddress getRemoteSocketAddress() {
        if (!isConnected()) {
            return null;
        }
        return new InetSocketAddress(getInetAddress(), getPort());
    }

    /**
     * Gets the bound local address and port of this socket. If the socket is
     * unbound, {@code null} is returned.
     * 
     * @return the local socket address.
     */
    public SocketAddress getLocalSocketAddress() {
        if (!isBound()) {
            return null;
        }
        return new InetSocketAddress(getLocalAddress(), getLocalPort());
    }

    /**
     * Sets the socket option {@code SocketOptions.SO_REUSEADDR}. This option
     * has to be enabled if more than one UDP socket wants to be bound to the
     * same address. That could be needed for receiving multicast packets.
     * <p>
     * There is an undefined behavior if this option is set after the socket is
     * already bound.
     * 
     * @param reuse
     *            the socket option value to enable or disable this option.
     * @throws SocketException
     *             if the socket is closed or the option could not be set.
     */
    public void setReuseAddress(boolean reuse) throws SocketException {
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.SO_REUSEADDR, reuse ? Boolean.TRUE
                : Boolean.FALSE);
    }

    /**
     * Gets the state of the socket option {@code SocketOptions.SO_REUSEADDR}.
     * 
     * @return {@code true} if the option is enabled, {@code false} otherwise.
     * @throws SocketException
     *             if the socket is closed or the option is invalid.
     */
    public boolean getReuseAddress() throws SocketException {
        checkClosedAndBind(false);
        return ((Boolean) impl.getOption(SocketOptions.SO_REUSEADDR))
                .booleanValue();
    }

    /**
     * Sets the socket option {@code SocketOptions.SO_BROADCAST}. This option
     * must be enabled to send broadcast messages.
     * 
     * @param broadcast
     *            the socket option value to enable or disable this option.
     * @throws SocketException
     *             if the socket is closed or the option could not be set.
     */
    public void setBroadcast(boolean broadcast) throws SocketException {
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.SO_BROADCAST, broadcast ? Boolean.TRUE
                : Boolean.FALSE);
    }

    /**
     * Gets the state of the socket option {@code SocketOptions.SO_BROADCAST}.
     * 
     * @return {@code true} if the option is enabled, {@code false} otherwise.
     * @throws SocketException
     *             if the socket is closed or the option is invalid.
     */
    public boolean getBroadcast() throws SocketException {
        checkClosedAndBind(false);
        return ((Boolean) impl.getOption(SocketOptions.SO_BROADCAST))
                .booleanValue();
    }

    /**
     * Sets the socket option {@code SocketOptions.IP_TOS}. This option defines
     * the value of the type-of-service field of the IP-header for every packet
     * sent by this socket. The value could be ignored by the underlying network
     * implementation.
     * <p>
     * Values between {@code 0} and {@code 255} inclusive are valid for this
     * option.
     *
     * @param value
     *            the socket option value to be set as type-of-service.
     * @throws SocketException
     *             if the socket is closed or the option could not be set.
     */
    public void setTrafficClass(int value) throws SocketException {
        checkClosedAndBind(false);
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException();
        }
        impl.setOption(SocketOptions.IP_TOS, Integer.valueOf(value));
    }

    /**
     * Gets the value of the type-of-service socket option {@code
     * SocketOptions.IP_TOS}.
     * 
     * @return the type-of-service socket option value.
     * @throws SocketException
     *             if the socket is closed or the option is invalid.
     */
    public int getTrafficClass() throws SocketException {
        checkClosedAndBind(false);
        return ((Number) impl.getOption(SocketOptions.IP_TOS)).intValue();
    }

    /**
     * Gets the state of this socket.
     * 
     * @return {@code true} if the socket is closed, {@code false} otherwise.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Gets the related DatagramChannel of this socket. This implementation
     * returns always {@code null}.
     * 
     * @return the related DatagramChannel or {@code null} if this socket was
     *         not created by a {@code DatagramChannel} object.
     */
    public DatagramChannel getChannel() {
        return null;
    }
}
