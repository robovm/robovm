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
import java.util.Enumeration;

import org.apache.harmony.luni.net.PlainDatagramSocketImpl;
import org.apache.harmony.luni.internal.nls.Messages;

/**
 * This class implements a multicast socket for sending and receiving IP
 * multicast datagram packets.
 *
 * @see DatagramSocket
 */
public class MulticastSocket extends DatagramSocket {

    final static int SO_REUSEPORT = 512;

    private InetAddress interfaceSet;

    /**
     * Constructs a multicast socket, bound to any available port on the
     * localhost.
     * 
     * @throws IOException
     *             if an error occurs creating or binding the socket.
     */
    public MulticastSocket() throws IOException {
        super();
        setReuseAddress(true);
    }

    /**
     * Constructs a multicast socket, bound to the specified port on the
     * localhost.
     * 
     * @param aPort
     *            the port to bind on the localhost.
     * @throws IOException
     *             if an error occurs creating or binding the socket.
     */
    public MulticastSocket(int aPort) throws IOException {
        super(aPort);
        setReuseAddress(true);
    }

    /**
     * Gets the network address used by this socket. This is useful on
     * multihomed machines.
     * 
     * @return the address of the network interface through which the datagram
     *         packets are sent or received.
     * @throws SocketException
     *                if an error occurs while getting the interface address.
     */
    public InetAddress getInterface() throws SocketException {
        checkClosedAndBind(false);
        if (interfaceSet == null) {
            InetAddress ipvXaddress = (InetAddress) impl
                    .getOption(SocketOptions.IP_MULTICAST_IF);
            if (ipvXaddress.isAnyLocalAddress()) {
                // the address was not set at the IPV4 level so check the IPV6
                // level
                NetworkInterface theInterface = getNetworkInterface();
                if (theInterface != null) {
                    Enumeration<InetAddress> addresses = theInterface
                            .getInetAddresses();
                    if (addresses != null) {
                        while (addresses.hasMoreElements()) {
                            InetAddress nextAddress = addresses.nextElement();
                            if (nextAddress instanceof Inet6Address) {
                                return nextAddress;
                            }
                        }
                    }
                }
            }
            return ipvXaddress;
        }
        return interfaceSet;
    }

    /**
     * Gets the network interface used by this socket. This is useful on
     * multihomed machines.
     * 
     * @return the network interface used by this socket or {@code null} if no
     *         interface is set.
     * @throws SocketException
     *                if an error occurs while getting the interface.
     * @since 1.4
     */
    public NetworkInterface getNetworkInterface() throws SocketException {
        checkClosedAndBind(false);

        // check if it is set at the IPV6 level. If so then use that. Otherwise
        // do it at the IPV4 level
        Integer theIndex = Integer.valueOf(0);
        try {
            theIndex = (Integer) impl.getOption(SocketOptions.IP_MULTICAST_IF2);
        } catch (SocketException e) {
            // we may get an exception if IPV6 is not enabled.
        }

        if (theIndex.intValue() != 0) {
            Enumeration<NetworkInterface> theInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (theInterfaces.hasMoreElements()) {
                NetworkInterface nextInterface = theInterfaces.nextElement();
                if (nextInterface.getIndex() == theIndex.intValue()) {
                    return nextInterface;
                }
            }
        }

        // ok it was not set at the IPV6 level so try at the IPV4 level
        InetAddress theAddress = (InetAddress) impl
                .getOption(SocketOptions.IP_MULTICAST_IF);
        if (theAddress != null) {
            if (!theAddress.isAnyLocalAddress()) {
                return NetworkInterface.getByInetAddress(theAddress);
            }

            // not set as we got the any address so return a dummy network
            // interface with only the any address. We do this to be
            // compatible
            InetAddress theAddresses[] = new InetAddress[1];
            if ((Socket.preferIPv4Stack() == false)
                    && (InetAddress.preferIPv6Addresses() == true)) {
                theAddresses[0] = Inet6Address.ANY;
            } else {
                theAddresses[0] = InetAddress.ANY;
            }
            return new NetworkInterface(null, null, theAddresses,
                    NetworkInterface.UNSET_INTERFACE_INDEX);
        }

        // ok not set at all so return null
        return null;
    }

    /**
     * Gets the time-to-live (TTL) for multicast packets sent on this socket.
     * 
     * @return the default value for the time-to-life field.
     * @throws IOException
     *                if an error occurs reading the default value.
     */
    public int getTimeToLive() throws IOException {
        checkClosedAndBind(false);
        return impl.getTimeToLive();
    }

    /**
     * Gets the time-to-live (TTL) for multicast packets sent on this socket.
     * 
     * @return the default value for the time-to-life field.
     * @throws IOException
     *                if an error occurs reading the default value.
     * @deprecated Replaced by {@link #getTimeToLive}
     * @see #getTimeToLive()
     */
    @Deprecated
    public byte getTTL() throws IOException {
        checkClosedAndBind(false);
        return impl.getTTL();
    }

    /**
     * Adds this socket to the specified multicast group. A socket must join a
     * group before data may be received. A socket may be a member of multiple
     * groups but may join any group only once.
     * 
     * @param groupAddr
     *            the multicast group to be joined.
     * @throws IOException
     *                if an error occurs while joining a group.
     */
    public void joinGroup(InetAddress groupAddr) throws IOException {
        checkClosedAndBind(false);
        if (!groupAddr.isMulticastAddress()) {
            throw new IOException(Messages.getString("luni.66")); //$NON-NLS-1$
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkMulticast(groupAddr);
        }
        impl.join(groupAddr);
    }

    /**
     * Adds this socket to the specified multicast group. A socket must join a
     * group before data may be received. A socket may be a member of multiple
     * groups but may join any group only once.
     * 
     * @param groupAddress
     *            the multicast group to be joined.
     * @param netInterface
     *            the network interface on which the datagram packets will be
     *            received.
     * @throws IOException
     *                if the specified address is not a multicast address.
     * @throws SecurityException
     *                if the caller is not authorized to join the group.
     * @throws IllegalArgumentException
     *                if no multicast group is specified.
     * @since 1.4
     */
    public void joinGroup(SocketAddress groupAddress,
            NetworkInterface netInterface) throws IOException {
        checkClosedAndBind(false);
        if (null == groupAddress) {
            throw new IllegalArgumentException(Messages.getString("luni.5D")); //$NON-NLS-1$
        }

        if ((netInterface != null) && (netInterface.getFirstAddress() == null)) {
            // this is ok if we could set it at the
            throw new SocketException(Messages.getString("luni.67")); //$NON-NLS-1$
        }

        if (!(groupAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException(Messages.getString(
                    "luni.49", groupAddress.getClass())); //$NON-NLS-1$
        }

        InetAddress groupAddr = ((InetSocketAddress) groupAddress).getAddress();

        if (groupAddr == null) {
            throw new SocketException(Messages.getString("luni.68")); //$NON-NLS-1$
        }

        if (!groupAddr.isMulticastAddress()) {
            throw new IOException(Messages.getString("luni.66")); //$NON-NLS-1$
        }

        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkMulticast(groupAddr);
        }
        impl.joinGroup(groupAddress, netInterface);
    }

    /**
     * Removes this socket from the specified multicast group.
     * 
     * @param groupAddr
     *            the multicast group to be left.
     * @throws NullPointerException
     *                if {@code groupAddr} is {@code null}.
     * @throws IOException
     *                if the specified group address is not a multicast address.
     * @throws SecurityException
     *                if the caller is not authorized to leave the group.
     */
    public void leaveGroup(InetAddress groupAddr) throws IOException {
        checkClosedAndBind(false);
        if (!groupAddr.isMulticastAddress()) {
            throw new IOException(Messages.getString("luni.69")); //$NON-NLS-1$
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkMulticast(groupAddr);
        }
        impl.leave(groupAddr);
    }

    /**
     * Removes this socket from the specified multicast group.
     * 
     * @param groupAddress
     *            the multicast group to be left.
     * @param netInterface
     *            the network interface on which the addresses should be
     *            dropped.
     * @throws IOException
     *                if the specified group address is not a multicast address.
     * @throws SecurityException
     *                if the caller is not authorized to leave the group.
     * @throws IllegalArgumentException
     *                if {@code groupAddress} is {@code null}.
     * @since 1.4
     */
    public void leaveGroup(SocketAddress groupAddress,
            NetworkInterface netInterface) throws IOException {
        checkClosedAndBind(false);
        if (null == groupAddress) {
            throw new IllegalArgumentException(Messages.getString("luni.5D")); //$NON-NLS-1$
        }

        if ((netInterface != null) && (netInterface.getFirstAddress() == null)) {
            // this is ok if we could set it at the
            throw new SocketException(Messages.getString("luni.67")); //$NON-NLS-1$
        }

        if (!(groupAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException(Messages.getString(
                    "luni.49", groupAddress.getClass())); //$NON-NLS-1$
        }

        InetAddress groupAddr = ((InetSocketAddress) groupAddress).getAddress();

        if (groupAddr == null) {
            throw new SocketException(Messages.getString("luni.68")); //$NON-NLS-1$
        }

        if (!groupAddr.isMulticastAddress()) {
            throw new IOException(Messages.getString("luni.69")); //$NON-NLS-1$
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkMulticast(groupAddr);
        }
        impl.leaveGroup(groupAddress, netInterface);
    }

    /**
     * Send the packet on this socket. The packet must satisfy the security
     * policy before it may be sent.
     * 
     * @param pack
     *            the {@code DatagramPacket} to send
     * @param ttl
     *            the TTL setting for this transmission, overriding the socket
     *            default
     * @throws IOException
     *                if an error occurs while sending data or setting options.
     * @deprecated use {@link #setTimeToLive}.
     */
    @Deprecated
    public void send(DatagramPacket pack, byte ttl) throws IOException {
        checkClosedAndBind(false);
        InetAddress packAddr = pack.getAddress();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            if (packAddr.isMulticastAddress()) {
                security.checkMulticast(packAddr, ttl);
            } else {
                security.checkConnect(packAddr.getHostName(), pack.getPort());
            }
        }
        int currTTL = getTimeToLive();
        if (packAddr.isMulticastAddress() && (byte) currTTL != ttl) {
            try {
                setTimeToLive(ttl & 0xff);
                impl.send(pack);
            } finally {
                setTimeToLive(currTTL);
            }
        } else {
            impl.send(pack);
        }
    }

    /**
     * Sets the interface address used by this socket. This allows to send
     * multicast packets on a different interface than the default interface of
     * the local system. This is useful on multihomed machines.
     * 
     * @param addr
     *            the multicast interface network address to set.
     * @throws SocketException
     *                if an error occurs while setting the network interface
     *                address option.
     */
    public void setInterface(InetAddress addr) throws SocketException {
        checkClosedAndBind(false);
        if (addr == null) {
            throw new NullPointerException();
        }
        if (addr.isAnyLocalAddress()) {
            impl.setOption(SocketOptions.IP_MULTICAST_IF, InetAddress.ANY);
        } else if (addr instanceof Inet4Address) {
            impl.setOption(SocketOptions.IP_MULTICAST_IF, addr);
            // keep the address used to do the set as we must return the same
            // value and for IPv6 we may not be able to get it back uniquely
            interfaceSet = addr;
        }

        /*
         * now we should also make sure this works for IPV6 get the network
         * interface for the address and set the interface using its index
         * however if IPV6 is not enabled then we may get an exception. if IPV6
         * is not enabled
         */
        NetworkInterface theInterface = NetworkInterface.getByInetAddress(addr);
        if ((theInterface != null) && (theInterface.getIndex() != 0)) {
            try {
                impl.setOption(SocketOptions.IP_MULTICAST_IF2, Integer
                        .valueOf(theInterface.getIndex()));
            } catch (SocketException e) {
                // Ignored
            }
        } else if (addr.isAnyLocalAddress()) {
            try {
                impl.setOption(SocketOptions.IP_MULTICAST_IF2, Integer
                        .valueOf(0));
            } catch (SocketException e) {
                // Ignored
            }
        } else if (addr instanceof Inet6Address) {
            throw new SocketException(Messages.getString("luni.6A")); //$NON-NLS-1$
        }
    }

    /**
     * Sets the network interface used by this socket. This is useful for
     * multihomed machines.
     * 
     * @param netInterface
     *            the multicast network interface to set.
     * @throws SocketException
     *                if an error occurs while setting the network interface
     *                option.
     * @since 1.4
     */
    public void setNetworkInterface(NetworkInterface netInterface)
            throws SocketException {

        checkClosedAndBind(false);

        if (netInterface == null) {
            // throw a socket exception indicating that we do not support this
            throw new SocketException(Messages.getString("luni.6B")); //$NON-NLS-1$
        }

        InetAddress firstAddress = netInterface.getFirstAddress();
        if (firstAddress == null) {
            // this is ok if we could set it at the
            throw new SocketException(Messages.getString("luni.67")); //$NON-NLS-1$
        }

        if (netInterface.getIndex() == NetworkInterface.UNSET_INTERFACE_INDEX) {
            // set the address using IP_MULTICAST_IF to make sure this
            // works for both IPV4 and IPV6
            impl.setOption(SocketOptions.IP_MULTICAST_IF, InetAddress.ANY);

            try {
                // we have the index so now we pass set the interface
                // using IP_MULTICAST_IF2. This is what is used to set
                // the interface on systems which support IPV6
                impl.setOption(SocketOptions.IP_MULTICAST_IF2, Integer
                        .valueOf(NetworkInterface.NO_INTERFACE_INDEX));
            } catch (SocketException e) {
                // for now just do this, -- could be narrowed?
            }
        }

        /*
         * Now try to set using IPV4 way. However, if interface passed in has no
         * IP addresses associated with it then we cannot do it. first we have
         * to make sure there is an IPV4 address that we can use to call set
         * interface otherwise we will not set it
         */
        Enumeration<InetAddress> theAddresses = netInterface.getInetAddresses();
        boolean found = false;
        firstAddress = null;
        while ((theAddresses.hasMoreElements()) && (found != true)) {
            InetAddress theAddress = theAddresses.nextElement();
            if (theAddress instanceof Inet4Address) {
                firstAddress = theAddress;
                found = true;
            }
        }
        if (netInterface.getIndex() == NetworkInterface.NO_INTERFACE_INDEX) {
            // the system does not support IPV6 and does not provide
            // indexes for the network interfaces. Just pass in the
            // first address for the network interface
            if (firstAddress != null) {
                impl.setOption(SocketOptions.IP_MULTICAST_IF, firstAddress);
            } else {
                /*
                 * we should never get here as there should not be any network
                 * interfaces which have no IPV4 address and which does not have
                 * the network interface index not set correctly
                 */
                throw new SocketException(Messages.getString("luni.67")); //$NON-NLS-1$
            }
        } else {
            // set the address using IP_MULTICAST_IF to make sure this
            // works for both IPV4 and IPV6
            if (firstAddress != null) {
                impl.setOption(SocketOptions.IP_MULTICAST_IF, firstAddress);
            }

            try {
                // we have the index so now we pass set the interface
                // using IP_MULTICAST_IF2. This is what is used to set
                // the interface on systems which support IPV6
                impl.setOption(SocketOptions.IP_MULTICAST_IF2, Integer
                        .valueOf(netInterface.getIndex()));
            } catch (SocketException e) {
                // for now just do this -- could be narrowed?
            }
        }

        interfaceSet = null;
    }

    /**
     * Sets the time-to-live (TTL) for multicast packets sent on this socket.
     * Valid TTL values are between 0 and 255 inclusive.
     *
     * @param ttl
     *            the default time-to-live field value for packets sent on this
     *            socket. {@code 0 <= ttl <= 255}.
     * @throws IOException
     *                if an error occurs while setting the TTL option value.
     */
    public void setTimeToLive(int ttl) throws IOException {
        checkClosedAndBind(false);
        if (ttl < 0 || ttl > 255) {
            throw new IllegalArgumentException(Messages.getString("luni.6C")); //$NON-NLS-1$
        }
        impl.setTimeToLive(ttl);
    }

    /**
     * Sets the time-to-live (TTL) for multicast packets sent on this socket.
     * Valid TTL values are between 0 and 255 inclusive.
     *
     * @param ttl
     *            the default time-to-live field value for packets sent on this
     *            socket: {@code 0 <= ttl <= 255}.
     * @throws IOException
     *                if an error occurs while setting the TTL option value.
     * @deprecated Replaced by {@link #setTimeToLive}
     * @see #setTimeToLive(int)
     */
    @Deprecated
    public void setTTL(byte ttl) throws IOException {
        checkClosedAndBind(false);
        impl.setTTL(ttl);
    }

    @Override
    synchronized void createSocket(int aPort, InetAddress addr)
            throws SocketException {
        impl = factory != null ? factory.createDatagramSocketImpl()
                : new PlainDatagramSocketImpl();
        impl.create();
        try {
            impl.setOption(SocketOptions.SO_REUSEADDR, Boolean.TRUE);
            impl.bind(aPort, addr);
            isBound = true;
        } catch (SocketException e) {
            close();
            throw e;
        }
    }

    /**
     * Constructs a {@code MulticastSocket} bound to the host/port specified by
     * the {@code SocketAddress}, or an unbound {@code DatagramSocket} if the
     * {@code SocketAddress} is {@code null}.
     * 
     * @param localAddr
     *            the local machine address and port to bind to.
     * @throws IllegalArgumentException
     *             if the {@code SocketAddress} is not supported.
     * @throws IOException
     *             if an error occurs creating or binding the socket.
     * @since 1.4
     */
    public MulticastSocket(SocketAddress localAddr) throws IOException {
        super(localAddr);
        setReuseAddress(true);
    }

    /**
     * Gets the state of the {@code SocketOptions.IP_MULTICAST_LOOP}.
     * 
     * @return {@code true} if the IP multicast loop is enabled, {@code false}
     *         otherwise.
     * @throws SocketException
     *             if the socket is closed or the option is invalid.
     * @since 1.4
     */
    public boolean getLoopbackMode() throws SocketException {
        checkClosedAndBind(false);
        return !((Boolean) impl.getOption(SocketOptions.IP_MULTICAST_LOOP))
                .booleanValue();
    }

    /**
     * Sets the {@code SocketOptions.IP_MULTICAST_LOOP}.
     * 
     * @param loop
     *            the value for the socket option socket {@code
     *            SocketOptions.IP_MULTICAST_LOOP}.
     * @throws SocketException
     *             if the socket is closed or the option is invalid.
     * @since 1.4
     */
    public void setLoopbackMode(boolean loop) throws SocketException {
        checkClosedAndBind(false);
        impl.setOption(SocketOptions.IP_MULTICAST_LOOP, loop ? Boolean.FALSE
                : Boolean.TRUE);
    }
}
