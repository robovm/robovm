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

import java.io.ObjectStreamException;

/**
 * This class represents a 32 bit long IPv4 address. The most common textual
 * representation of an IPv4 address follows the pattern {@code b.b.b.b} where
 * each {@code b} represents one of the four bytes. If only three parts are
 * specified ({@code b.b.bb} ), the last part is then 16 bit long, the address
 * represents a class B network address as <i>128.net.host</i>. A two part
 * address ({@code b.bbb} ) allows to represent a class A network address as
 * <i>net.host</i>. If there is only one part ({@code bbbb} ) the address is
 * represented without any byte rearrangement.
 */
public final class Inet4Address extends InetAddress {

    private static final long serialVersionUID = 3286316764910316507L;

    Inet4Address(byte[] address) {
        ipaddress = address;
    }

    Inet4Address(byte[] address, String name) {
        ipaddress = address;
        hostName = name;
    }

    /**
     * Returns whether the represented address is a multicast address or not.
     * Valid IPv4 multicast addresses are prefixed with 1110 = 0xE.
     * 
     * @return {@code true} if this instance represents a multicast address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isMulticastAddress() {
        return (ipaddress[0] & 0xF0) == 0xE0;
    }

    /**
     * Returns whether the represented address is the local wildcard ANY address
     * or not.
     * 
     * @return {@code true} if this instance represents the wildcard ANY
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isAnyLocalAddress() {
        for (int i = 0; i < ipaddress.length; i++) {
            if (ipaddress[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether the represented address is a loopback address or not.
     * Loopback IPv4 addresses are prefixed with: 011111111 = 127.
     * 
     * @return {@code true} if this instance represents a lookback address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isLoopbackAddress() {
        return (ipaddress[0] & 255) == 127;
    }

    /**
     * Returns whether this address has a link-local scope or not.
     * <p>
     * RFC 3484 <br>
     * Default Address Selection for Internet Protocol Version 6 (IPv6) states
     * IPv4 auto-configuration addresses, prefix 169.254/16, IPv4 loopback
     * addresses, prefix 127/8, are assigned link-local scope.
     *
     * @return {@code true} if this instance represents a link-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isLinkLocalAddress() {
        // The reference implementation does not return true for loopback
        // addresses even though RFC 3484 says to do so
        return (((ipaddress[0] & 255) == 169) && ((ipaddress[1] & 255) == 254));
    }

    /**
     * Returns whether this address has a site-local scope or not.
     * <p>
     * RFC 3484 <br>
     * Default Address Selection for Internet Protocol Version 6 (IPv6) states
     * IPv4 private addresses, prefixes 10/8, 172.16/12, and 192.168/16, are
     * assigned site-local scope.
     * 
     * @return {@code true} if this instance represents a site-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isSiteLocalAddress() {
        return ((ipaddress[0] & 255) == 10) || ((ipaddress[0] & 255) == 172)
                && (((ipaddress[1] & 255) > 15) && (ipaddress[1] & 255) < 32)
                || ((ipaddress[0] & 255) == 192)
                && ((ipaddress[1] & 255) == 168);
    }

    /**
     * Returns whether the address is a global multicast address or not. Valid
     * MCGlobal IPv4 addresses are 224.0.1.0 - 238.255.255.255.
     * 
     * @return {@code true} if the address is in the global multicast group,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isMCGlobal() {

        // Check if we have a prefix of 1110
        if (!isMulticastAddress()) {
            return false;
        }

        int address = InetAddress.bytesToInt(ipaddress, 0);
        /*
         * Now check the boundaries of the global space if we have an address
         * that is prefixed by something less than 111000000000000000000001
         * (fortunately we don't have to worry about sign after shifting 8 bits
         * right) it is not multicast. ( < 224.0.1.0)
         */
        if (address >>> 8 < 0xE00001) {
            return false;
        }

        /*
         * Now check the high boundary which is prefixed by 11101110 = 0xEE. If
         * the value is higher than this than it is not MCGlobal ( >
         * 238.255.255.255 )
         */
        if (address >>> 24 > 0xEE) {
            return false;
        }

        return true;
    }

    /**
     * Returns whether the address has a node-local scope or not. This method
     * returns always {@code false} because there are no valid IPv4 node-local
     * addresses.
     * 
     * @return {@code false} for all IPv4 addresses.
     */
    @Override
    public boolean isMCNodeLocal() {
        return false;
    }

    /**
     * Returns whether the address is a link-local multicast address or not. The
     * valid range for IPv4 link-local addresses is: 224.0.0.0 to 239.0.0.255
     * Hence a mask of 111000000000000000000000 = 0xE00000.
     * 
     * @return {@code true} if this instance represents a link-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isMCLinkLocal() {
        return InetAddress.bytesToInt(ipaddress, 0) >>> 8 == 0xE00000;
    }

    /**
     * Returns whether the address is a site-local multicast address or not. The
     * valid range for IPv4 site-local addresses is: 239.255.0.0 to
     * 239.255.255.255 Hence a mask of 11101111 11111111 = 0xEFFF.
     * 
     * @return {@code true} if this instance represents a site-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isMCSiteLocal() {
        return (InetAddress.bytesToInt(ipaddress, 0) >>> 16) == 0xEFFF;
    }

    /**
     * Returns whether the address is a organization-local multicast address or
     * not. The valid range for IPv4 organization-local addresses is:
     * 239.192.0.0 to 239.195.255.255 Hence masks of 11101111 11000000 to
     * 11101111 11000011 are valid. 0xEFC0 to 0xEFC3
     * 
     * @return {@code true} if this instance represents a organization-local
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCOrgLocal() {
        int prefix = InetAddress.bytesToInt(ipaddress, 0) >>> 16;
        return prefix >= 0xEFC0 && prefix <= 0xEFC3;
    }

    /**
     * Returns a textual representation of this IP address.
     * 
     * @return the textual representation of this host address.
     */
    @Override
    public String getHostAddress() {
        final StringBuilder hostAddress = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            hostAddress.append(ipaddress[i] & 255);
            if (i != 3) {
                hostAddress.append('.');
            }
        }
        return hostAddress.toString();
    }

    /**
     * Gets the hashcode of the represented IP address.
     * 
     * @return the appropriate hashcode value.
     */
    @Override
    public int hashCode() {
        return InetAddress.bytesToInt(ipaddress, 0);
    }

    /**
     * Compares this instance with the IP address in the object {@code obj} and
     * returns {@code true} if they are of the same type and represent the same
     * IP address, {@code false} otherwise.
     * 
     * @param obj
     *            the object to be tested for equality.
     * @return {@code true} if the addresses are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new InetAddress(ipaddress, hostName);
    }
}
