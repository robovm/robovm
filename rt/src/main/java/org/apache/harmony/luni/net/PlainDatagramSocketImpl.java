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
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOptions;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.AccessController;

import org.apache.harmony.luni.platform.INetworkSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.internal.nls.Messages;
import org.apache.harmony.luni.util.PriviAction;

/**
 * The default, concrete instance of datagram sockets. This class does not
 * support security checks. Alternative types of DatagramSocketImpl's may be
 * used by setting the <code>impl.prefix</code> system property.
 */
public class PlainDatagramSocketImpl extends DatagramSocketImpl {

    static final int MULTICAST_IF = 1;

    static final int MULTICAST_TTL = 2;

    static final int TCP_NODELAY = 4;

    static final int FLAG_SHUTDOWN = 8;

    private final static int SO_BROADCAST = 32;

    final static int IP_MULTICAST_ADD = 19;

    final static int IP_MULTICAST_DROP = 20;

    final static int IP_MULTICAST_TTL = 17;

    /**
     * for datagram and multicast sockets we have to set REUSEADDR and REUSEPORT
     * when REUSEADDR is set for other types of sockets we need to just set
     * REUSEADDR therefore we have this other option which sets both if
     * supported by the platform. this cannot be in SOCKET_OPTIONS because since
     * it is a public interface it ends up being public even if it is not
     * declared public
     */
    static final int REUSEADDR_AND_REUSEPORT = 10001;

    // Ignored in native code
    private boolean bindToDevice = false;

    private byte[] ipaddress = { 0, 0, 0, 0 };

    private int ttl = 1;

    private INetworkSystem netImpl = Platform.getNetworkSystem();

    private volatile boolean isNativeConnected;

    public int receiveTimeout;

    public boolean streaming = true;

    public boolean shutdownInput;

    /**
     * used to keep address to which the socket was connected to at the native
     * level
     */
    private InetAddress connectedAddress;

    private int connectedPort = -1;

    /**
     * used to store the trafficClass value which is simply returned as the
     * value that was set. We also need it to pass it to methods that specify an
     * address packets are going to be sent to
     */
    private int trafficClass;

    public PlainDatagramSocketImpl(FileDescriptor fd, int localPort) {
        super();
        this.fd = fd;
        this.localPort = localPort;
    }

    public PlainDatagramSocketImpl() {
        super();
        fd = new FileDescriptor();
    }

    @Override
    public void bind(int port, InetAddress addr) throws SocketException {
        String prop = AccessController.doPrivileged(new PriviAction<String>("bindToDevice")); //$NON-NLS-1$
        boolean useBindToDevice = prop != null && prop.toLowerCase().equals("true"); //$NON-NLS-1$
        netImpl.bind(fd, addr, port);
        if (0 != port) {
            localPort = port;
        } else {
            localPort = netImpl.getSocketLocalPort(fd, NetUtil.preferIPv6Addresses());
        }

        try {
            // Ignore failures
            setOption(SO_BROADCAST, Boolean.TRUE);
        } catch (IOException e) {
        }
    }

    @Override
    public void close() {
        synchronized (fd) {
            if (fd.valid()) {
                try {
                    netImpl.socketClose(fd);
                } catch (IOException e) {
                }
                fd = new FileDescriptor();
            }
        }
    }

    @Override
    public void create() throws SocketException {
        netImpl.createDatagramSocket(fd, NetUtil.preferIPv4Stack());
    }

    @Override
    protected void finalize() {
        close();
    }

    public Object getOption(int optID) throws SocketException {
        if (optID == SocketOptions.SO_TIMEOUT) {
            return Integer.valueOf(receiveTimeout);
        } else if (optID == SocketOptions.IP_TOS) {
            return Integer.valueOf(trafficClass);
        } else {
            // Call the native first so there will be
            // an exception if the socket if closed.
            Object result = netImpl.getSocketOption(fd, optID);
            if (optID == SocketOptions.IP_MULTICAST_IF
                    && (netImpl.getSocketFlags() & MULTICAST_IF) != 0) {
                try {
                    return InetAddress.getByAddress(ipaddress);
                } catch (UnknownHostException e) {
                    return null;
                }
            }
            return result;
        }
    }

    @Override
    public int getTimeToLive() throws IOException {
        // Call the native first so there will be an exception if the socket if
        // closed.
        int result = (((Byte) getOption(IP_MULTICAST_TTL)).byteValue()) & 0xFF;
        if ((netImpl.getSocketFlags() & MULTICAST_TTL) != 0) {
            return ttl;
        }
        return result;
    }

    @Override
    public byte getTTL() throws IOException {
        // Call the native first so there will be an exception if the socket if
        // closed.
        byte result = ((Byte) getOption(IP_MULTICAST_TTL)).byteValue();
        if ((netImpl.getSocketFlags() & MULTICAST_TTL) != 0) {
            return (byte) ttl;
        }
        return result;
    }

    @Override
    public void join(InetAddress addr) throws IOException {
        setOption(IP_MULTICAST_ADD, new GenericIPMreq(addr));
    }

    @Override
    public void joinGroup(SocketAddress addr, NetworkInterface netInterface) throws IOException {
        if (addr instanceof InetSocketAddress) {
            InetAddress groupAddr = ((InetSocketAddress) addr).getAddress();
            setOption(IP_MULTICAST_ADD, new GenericIPMreq(groupAddr, netInterface));
        }
    }

    @Override
    public void leave(InetAddress addr) throws IOException {
        setOption(IP_MULTICAST_DROP, new GenericIPMreq(addr));
    }

    @Override
    public void leaveGroup(SocketAddress addr, NetworkInterface netInterface)
            throws IOException {
        if (addr instanceof InetSocketAddress) {
            InetAddress groupAddr = ((InetSocketAddress) addr).getAddress();
            setOption(IP_MULTICAST_DROP, new GenericIPMreq(groupAddr, netInterface));
        }
    }

    @Override
    protected int peek(InetAddress sender) throws IOException {
        if (isNativeConnected) {
            /*
             * in this case we know the port and address from which the data
             * must have be been received as the socket is connected. However,
             * we still need to do the receive in order to know that there was
             * data received. We use a short buffer as we don't actually need
             * the packet, only the knowledge that it is there
             */
            byte[] storageArray = new byte[10];
            DatagramPacket pack = new DatagramPacket(storageArray, storageArray.length);
            netImpl.recvConnectedDatagram(fd, pack, pack.getData(), pack.getOffset(), pack
                    .getLength(), receiveTimeout, true); // peek
            // to set the sender ,we now use a native function
            // sender.ipaddress = connectedAddress.getAddress();
            netImpl.setInetAddress(sender, connectedAddress.getAddress());
            return connectedPort;
        }
        return netImpl.peekDatagram(fd, sender, receiveTimeout);
    }

    @Override
    public void receive(DatagramPacket pack) throws java.io.IOException {
        try {
            if (isNativeConnected) {
                // do not peek
                netImpl.recvConnectedDatagram(fd, pack, pack.getData(), pack.getOffset(), pack
                        .getLength(), receiveTimeout, false);
                updatePacketRecvAddress(pack);
            } else {
                // receiveDatagramImpl2
                netImpl.receiveDatagram(fd, pack, pack.getData(), pack.getOffset(), pack
                        .getLength(), receiveTimeout, false);
            }
        } catch (InterruptedIOException e) {
            throw new SocketTimeoutException(e.getMessage());
        }
    }

    @Override
    public void send(DatagramPacket packet) throws IOException {

        if (isNativeConnected) {
            netImpl.sendConnectedDatagram(fd, packet.getData(), packet.getOffset(), packet
                    .getLength(), bindToDevice);
        } else {
            // sendDatagramImpl2
            netImpl.sendDatagram(fd, packet.getData(), packet.getOffset(), packet.getLength(),
                    packet.getPort(), bindToDevice, trafficClass, packet.getAddress());
        }
    }

    /**
     * Set the nominated socket option. As the timeouts are not set as options
     * in the IP stack, the value is stored in an instance field.
     * 
     * @throws SocketException thrown if the option value is unsupported or
     *         invalid
     */
    public void setOption(int optID, Object val) throws SocketException {
        /*
         * for datagram sockets on some platforms we have to set both the
         * REUSEADDR AND REUSEPORT so for REUSEADDR set this option which
         * tells the VM to set the two values as appropriate for the platform
         */
        if (optID == SocketOptions.SO_REUSEADDR) {
            optID = REUSEADDR_AND_REUSEPORT;
        }

        if (optID == SocketOptions.SO_TIMEOUT) {
            receiveTimeout = ((Integer) val).intValue();
        } else {
            int flags = netImpl.getSocketFlags();
            try {
                netImpl.setSocketOption(fd, optID | (flags << 16), val);
            } catch (SocketException e) {
                // we don't throw an exception for IP_TOS even if the platform
                // won't let us set the requested value
                if (optID != SocketOptions.IP_TOS) {
                    throw e;
                }
            }
            if (optID == SocketOptions.IP_MULTICAST_IF && (flags & MULTICAST_IF) != 0) {
                InetAddress inet = (InetAddress) val;
                if (NetUtil.bytesToInt(inet.getAddress(), 0) == 0 || inet.isLoopbackAddress()) {
                    ipaddress = ((InetAddress) val).getAddress();
                } else {
                    InetAddress local = null;
                    try {
                        local = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        throw new SocketException("getLocalHost(): " + e.toString());
                    }
                    if (inet.equals(local)) {
                        ipaddress = ((InetAddress) val).getAddress();
                    } else {
                        throw new SocketException(val + " != getLocalHost(): " + local);
                    }
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

    @Override
    public void setTimeToLive(int ttl) throws java.io.IOException {
        setOption(IP_MULTICAST_TTL, Byte.valueOf((byte) (ttl & 0xFF)));
        if ((netImpl.getSocketFlags() & MULTICAST_TTL) != 0) {
            this.ttl = ttl;
        }
    }

    @Override
    public void setTTL(byte ttl) throws java.io.IOException {
        setOption(IP_MULTICAST_TTL, Byte.valueOf(ttl));
        if ((netImpl.getSocketFlags() & MULTICAST_TTL) != 0) {
            this.ttl = ttl;
        }
    }

    @Override
    public void connect(InetAddress inetAddr, int port) throws SocketException {

        // connectDatagram impl2
        netImpl.connectDatagram(fd, port, trafficClass, inetAddr);

        // if we get here then we are connected at the native level
        try {
            connectedAddress = InetAddress.getByAddress(inetAddr.getAddress());
        } catch (UnknownHostException e) {
            // this is never expected to happen as we should not have gotten
            // here if the address is not resolvable
            throw new SocketException(Messages.getString("luni.1A", inetAddr.getHostName())); //$NON-NLS-1$
        }
        connectedPort = port;
        isNativeConnected = true;
    }

    @Override
    public void disconnect() {
        try {
            netImpl.disconnectDatagram(fd);
        } catch (Exception e) {
            // there is currently no way to return an error so just eat any
            // exception
        }
        connectedPort = -1;
        connectedAddress = null;
        isNativeConnected = false;
    }

    @Override
    public int peekData(DatagramPacket pack) throws IOException {
        try {
            if (isNativeConnected) {
                netImpl.recvConnectedDatagram(fd, pack, pack.getData(), pack.getOffset(), pack
                        .getLength(), receiveTimeout, true); // peek
                updatePacketRecvAddress(pack);
            } else {
                // receiveDatagram 2
                netImpl.receiveDatagram(fd, pack, pack.getData(), pack.getOffset(), pack
                        .getLength(), receiveTimeout, true); // peek
            }
        } catch (InterruptedIOException e) {
            throw new SocketTimeoutException(e.toString());
        }
        return pack.getPort();
    }

    /**
     * Set the received address and port in the packet. We do this when the
     * Datagram socket is connected at the native level and the
     * recvConnnectedDatagramImpl does not update the packet with address from
     * which the packet was received
     * 
     * @param packet
     *            the packet to be updated
     */
    private void updatePacketRecvAddress(DatagramPacket packet) {
        packet.setAddress(connectedAddress);
        packet.setPort(connectedPort);
    }
}
