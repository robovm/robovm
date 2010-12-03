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


/**
 * Defines an interface for socket implementations to get and set socket
 * options. It is implemented by the classes {@code SocketImpl} and {@code
 * DatagramSocketImpl}.
 * 
 * @see SocketImpl
 * @see DatagramSocketImpl
 */
public interface SocketOptions {

    /**
     * This option specifies the behavior of the {@code close()} method if there
     * is still some buffered data to be sent while closing the socket. If the
     * value of this option is set to {@code 0} the method closes the TCP socket
     * forcefully and returns immediately. Is this value greater than {@code 0}
     * the method blocks this time in milliseconds. If all data could be sent
     * during this timeout the socket is closed normally otherwise forcefully.
     * Valid values for this option are in the range {@code 0 <= SO_LINGER <=
     * 65535}.
     */
    public static final int SO_LINGER = 128;

    /**
     * Timeout for blocking operations. The argument value is specified in
     * milliseconds. An {@code InterruptedIOException} is thrown if this timeout
     * expires.
     */
    public static final int SO_TIMEOUT = 4102;

    /**
     * This option specifies whether data is sent immediately on this socket, as
     * a side-effect though, this could lead to a low packet efficiency. The
     * socket implementation uses the Nagle's algorithm to try to reach a higher
     * packet efficiency if this option is disabled.
     */
    public static final int TCP_NODELAY = 1;

    // For 5 and 6 see MulticastSocket

    // For 7 see PlainDatagramSocketImpl
    
    /**
     * This option specifies the interface which is used to send multicast
     * packets. It's only available on a {@code MulticastSocket}.
     */
    public static final int IP_MULTICAST_IF = 16;

    /**
     * This option can be used to set one specific interface on a multihomed
     * host on which incoming connections are accepted. It's only available on
     * server-side sockets.
     */
    public static final int SO_BINDADDR = 15;

    /**
     * This option specifies whether a reuse of a local address is allowed even
     * if an other socket is not yet removed by the operating system. It's only
     * available on a {@code MulticastSocket}.
     */
    public static final int SO_REUSEADDR = 4;

    // 10 not currently used
    
    /**
     * Buffer size of the outgoing channel.
     */
    public static final int SO_SNDBUF = 4097;

    /**
     * Buffer size of the incoming channel.
     */
    public static final int SO_RCVBUF = 4098;

    // For 13, see DatagramSocket
    
    /**
     * This option specifies whether socket implementations can send keepalive
     * messages if no data has been sent for a longer time.
     */
    public static final int SO_KEEPALIVE = 8;
    
    /**
     * This option specifies the value for the Type-of-Service (TOS) field of
     * the IP header.
     */
    public static final int IP_TOS = 3;
    
    /**
     * This option specifies whether the local loopback of multicast packets is
     * enabled or disabled. This option is enabled by default on multicast
     * sockets.
     */
    public static final int IP_MULTICAST_LOOP = 18;
    
    /**
     * This option can be used to enable broadcasting on datagram sockets.
     */
    public static final int SO_BROADCAST = 32;
    
    /**
     * This option specifies whether sending TCP urgent data is supported on
     * this socket or not.
     */
    public static final int SO_OOBINLINE = 4099;
    
    /**
     * This option can be used to set one specific interface on a multihomed
     * host on which incoming connections are accepted. It's only available on
     * server-side sockets. This option supports setting outgoing interfaces
     * with either IPv4 or IPv6 addresses.
     */
    public static final int IP_MULTICAST_IF2 = 31;

    /**
     * Gets the value for the specified socket option.
     * 
     * @return the option value.
     * @param optID
     *            the option identifier.
     * @throws SocketException
     *             if an error occurs reading the option value.
     */
    public Object getOption(int optID) throws SocketException;

    /**
     * Sets the value of the specified socket option.
     * 
     * @param optID
     *            the option identifier.
     * @param val
     *            the value to be set for the option.
     * @throws SocketException
     *             if an error occurs setting the option value.
     */
    public void setOption(int optID, Object val) throws SocketException;
}
