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
import java.io.ObjectInputStream;

/**
 * This class represents a socket endpoint described by a IP address and a port
 * number. It is a concrete implementation of {@code SocketAddress} for IP.
 */
public class InetSocketAddress extends SocketAddress {

    private static final long serialVersionUID = 5076001401234631237L;

    private String hostname;

    private InetAddress addr;

    private int port;

    private transient boolean gotHostname = false;

    /**
     * Creates a socket endpoint with the given port number {@code port} and the
     * wildcard address {@code InetAddress.ANY}. The range for valid port numbers
     * is between 0 and 65535 inclusive.
     * 
     * @param port
     *            the specified port number to which this socket is bound.
     */
    public InetSocketAddress(int port) {
        this((InetAddress) null, port);
    }

    /**
     * Creates a socket endpoint with the given port number {@code port} and
     * {@code address}. The range for valid port numbers is between 0 and 65535
     * inclusive. If {@code address} is {@code null} this socket is bound to the
     * wildcard address {@code InetAddress.ANY}.
     * 
     * @param port
     *            the specified port number to which this socket is bound.
     * @param address
     *            the specified address to which this socket is bound.
     */
    public InetSocketAddress(InetAddress address, int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException();
        }
        if (address == null) {
            addr = InetAddress.ANY;
        } else {
            addr = address;
        }
        hostname = addr.getHostName();
        this.port = port;
    }

    /**
     * Creates a socket endpoint with the given port number {@code port} and the
     * hostname {@code host}. The hostname is tried to be resolved and cannot be
     * {@code null}. The range for valid port numbers is between 0 and 65535
     * inclusive.
     * 
     * @param port
     *            the specified port number to which this socket is bound.
     * @param host
     *            the specified hostname to which this socket is bound.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkConnect()} method does not allow the resolving of the
     *             host name.
     */
    public InetSocketAddress(String host, int port) {
        this(host, port, true);
    }

    /*
     * Internal constructor for InetSocketAddress(String, int) and
     * createUnresolved(String, int);
     */
    InetSocketAddress(String host, int port, boolean needResolved) {
        if (host == null || port < 0 || port > 65535) {
            throw new IllegalArgumentException();
        }
        hostname = host;
        this.port = port;
        if (needResolved) {
            try {
                addr = InetAddress.getByName(hostname);
            } catch (UnknownHostException e) {
                // Ignored
            }
        } else {
            addr = null;
        }
    }

    /**
     * Creates an {@code InetSocketAddress} without trying to resolve the
     * hostname into an {@code InetAddress}. The address field is marked as
     * unresolved.
     * 
     * @param host
     *            the specified hostname to which this socket is bound.
     * @param port
     *            the specified port number to which this socket is bound.
     * @return the created InetSocketAddress instance.
     * @throws IllegalArgumentException
     *             if the hostname {@code host} is {@code null} or the port is
     *             not in the range between 0 and 65535.
     */
    public static InetSocketAddress createUnresolved(String host, int port) {
        return new InetSocketAddress(host, port, false);
    }

    /**
     * Gets the port number of this socket.
     * 
     * @return the socket endpoint port number.
     */
    public final int getPort() {
        return port;
    }

    /**
     * Gets the address of this socket.
     * 
     * @return the socket endpoint address.
     */
    public final InetAddress getAddress() {
        return addr;
    }

    /**
     * Gets the hostname of this socket.
     * 
     * @return the socket endpoint hostname.
     */
    public final String getHostName() {
        if (addr != null && !gotHostname) {
            gotHostname = true;
            hostname = addr.getHostName();
        }
        return  hostname;
    }

    /**
     * Returns whether this socket address is unresolved or not.
     * 
     * @return {@code true} if this socket address is unresolved, {@code false}
     *         otherwise.
     */
    public final boolean isUnresolved() {
        return addr == null;
    }

    /**
     * Gets a string representation of this socket included the address and the
     * port number.
     * 
     * @return the address and port number as a textual representation.
     */
    @Override
    public String toString() {
        String host;
        if (addr != null) {
            host = addr.toString();
        } else {
            host = hostname;
        }
        return host + ":" + port; //$NON-NLS-1$
    }

    /**
     * Compares two socket endpoints and returns true if they are equal. Two
     * socket endpoints are equal if the IP address or the hostname of both are
     * equal and they are bound to the same port.
     * 
     * @param socketAddr
     *            the object to be tested for equality.
     * @return {@code true} if this socket and the given socket object {@code
     *         socketAddr} are equal, {@code false} otherwise.
     */
    @Override
    public final boolean equals(Object socketAddr) {
        if (this == socketAddr) {
            return true;
        }
        if (!(socketAddr instanceof InetSocketAddress)) {
            return false;
        }
        InetSocketAddress iSockAddr = (InetSocketAddress) socketAddr;

        // check the ports as we always need to do this
        if (port != iSockAddr.port) {
            return false;
        }

        // we only use the hostnames in the comparison if the addrs were not
        // resolved
        if ((addr == null) && (iSockAddr.addr == null)) {
            return hostname.equals(iSockAddr.hostname);
        }

        // addrs were resolved so use them for the comparison
        if (addr == null) {
            // if we are here we know iSockAddr is not null so just return
            // false
            return false;
        }
        return addr.equals(iSockAddr.addr);
    }

    /**
     * Gets the hashcode of this socket.
     * 
     * @return the appropriate hashcode.
     */
    @Override
    public final int hashCode() {
        if (addr == null) {
            return hostname.hashCode() + port;
        }
        return addr.hashCode() + port;
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
    }
}
