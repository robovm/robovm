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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * This class provides is used to pass the information required in an ip_mreq or
 * ip6_mreq structure to java natives. We don't have accessor methods as it is
 * more straight forward in the natives to simply access the fields directly
 */
final class GenericIPMreq {

    private InetAddress multiaddr;

    @SuppressWarnings("unused")
    private InetAddress interfaceAddr;

    @SuppressWarnings("unused")
    private boolean isIPV6Address;

    @SuppressWarnings("unused")
    private int interfaceIdx;

    /**
     * This constructor is used to create an instance of the object
     * 
     * @param addr multicast address to join/leave
     * 
     */
    GenericIPMreq(InetAddress addr) {
        multiaddr = addr;
        interfaceAddr = null;
        interfaceIdx = 0;
        init();
    }

    /**
     * This constructor is used to create an instance of the object
     * 
     * @param addr multicast address to join/leave
     * @param netInterface the NetworkInterface object identifying the interface
     *        on which to join/leave
     */
    GenericIPMreq(InetAddress addr, NetworkInterface netInterface) {
        multiaddr = addr;
        if (null != netInterface) {
            // TODO  check if necessary
            //interfaceIdx = netInterface.getIndex();

            /*
             * here we need to get the first IPV4 address as we only use it if
             * we are setting the interface for an IPV4 multicast socket. For
             * adds/drops on IPV6 addresses we use the index within the
             * networkInterface
             */
            interfaceAddr = null;
            Enumeration<InetAddress> theAddresses = netInterface.getInetAddresses();
            if ((addr instanceof Inet4Address) && (theAddresses != null)) {
                boolean found = false;
                while ((theAddresses.hasMoreElements()) && (found != true)) {
                    InetAddress theAddress = theAddresses.nextElement();
                    if (theAddress instanceof Inet4Address) {
                        interfaceAddr = theAddress;
                        found = true;
                    }
                }
            }
        } else {
            /*
             * network interface is null so we just want to defer the decision
             * to the system
             */
            interfaceIdx = 0;
            interfaceAddr = null;
        }
        init();
    }

    /**
     * This method does any required initialization for the constructors
     */
    private void init() {
        /*
         * set the flag indicating if the multicast address is an IPV6 address
         * or not
         */
        isIPV6Address = ((multiaddr != null) && (multiaddr instanceof Inet6Address));
    }
}
