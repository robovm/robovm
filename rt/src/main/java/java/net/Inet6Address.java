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
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Enumeration;

import org.apache.harmony.luni.util.Inet6Util;
import org.apache.harmony.luni.internal.nls.Messages;

/**
 * This class represents a 128 bit long IPv6 address.
 */
public final class Inet6Address extends InetAddress {

    private static final long serialVersionUID = 6880410070516793377L;

    static final byte[] any_bytes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0 };

    static final byte[] localhost_bytes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 1 };

    static final InetAddress ANY = new Inet6Address(any_bytes);

    static final InetAddress LOOPBACK = new Inet6Address(localhost_bytes,
            "localhost"); //$NON-NLS-1$

    int scope_id;

    boolean scope_id_set;

    boolean scope_ifname_set;

    String ifname;

    /*
     * scoped interface.
     */
    transient NetworkInterface scopedIf;

    Inet6Address(byte address[]) {
        ipaddress = address;
        scope_id = 0;
    }

    Inet6Address(byte address[], String name) {
        hostName = name;
        ipaddress = address;
        scope_id = 0;
    }

    /**
     * Constructs an {@code InetAddress} representing the {@code address} and
     * {@code name} and {@code scope_id}.
     * 
     * @param address
     *            the network address.
     * @param name
     *            the name associated with the address.
     * @param scope_id
     *            the scope id for link- or site-local addresses.
     */
    Inet6Address(byte address[], String name, int scope_id) {
        hostName = name;
        ipaddress = address;
        this.scope_id = scope_id;
        if (scope_id != 0) {
            scope_id_set = true;
        }
    }

    /**
     * Constructs an IPv6 address according to the given {@code host}, {@code
     * addr} and {@code scope_id}.
     * 
     * @param host
     *            the host name associated with the address.
     * @param addr
     *            the network address.
     * @param scope_id
     *            the scope id for link- or site-local addresses.
     * @return the Inet6Address instance representing the IP address.
     * @throws UnknownHostException
     *             if the address is null or has an invalid length.
     */
    public static Inet6Address getByAddress(String host, byte[] addr,
            int scope_id) throws UnknownHostException {
        if (null == addr || 16 != addr.length) {
            // luni.62=Illegal IPv6 address
            throw new UnknownHostException(Messages.getString("luni.62")); //$NON-NLS-1$
        }
        if (scope_id < 0) {
            scope_id = 0;
        }
        return new Inet6Address(addr, host, scope_id);
    }

    /**
     * Gets an IPv6 address instance according to the given {@code host},
     * {@code addr} and {@code nif}. {@code scope_id} is set according to the
     * given {@code nif} and the {@code addr} type (for example site-local or
     * link-local).
     * 
     * @param host
     *            the hostname associated with the address.
     * @param addr
     *            the network address.
     * @param nif
     *            the network interface that this address is associated with.
     * @return the Inet6Address instance representing the IP address.
     * @throws UnknownHostException
     *             if the address is {@code null} or has an invalid length or
     *             the interface doesn't have a numeric scope id for the given
     *             address type.
     */
    public static Inet6Address getByAddress(String host, byte[] addr,
            NetworkInterface nif) throws UnknownHostException {

        Inet6Address address = Inet6Address.getByAddress(host, addr, 0);

        // if nif is null, nothing needs to be set.
        if (null == nif) {
            return address;
        }

        // find the first address which matches the type addr,
        // then set the scope_id, ifname and scopedIf.
        Enumeration<InetAddress> addressList = nif.getInetAddresses();
        while (addressList.hasMoreElements()) {
            InetAddress ia = addressList.nextElement();
            if (ia.getAddress().length == 16) {
                Inet6Address v6ia = (Inet6Address) ia;
                boolean isSameType = v6ia.compareLocalType(address);
                if (isSameType) {
                    address.scope_id_set = true;
                    address.scope_id = v6ia.scope_id;
                    address.scope_ifname_set = true;
                    address.ifname = nif.getName();
                    address.scopedIf = nif;
                    break;
                }
            }
        }
        // if no address matches the type of addr, throws an
        // UnknownHostException.
        if (!address.scope_id_set) {
            // luni.63=Scope id is not found for the given address
            throw new UnknownHostException(Messages.getString("luni.63")); //$NON-NLS-1$
        }
        return address;
    }

    /**
     * Returns {@code true} if one of following cases applies:
     * <p>
     * <ol>
     *  <li>both addresses are site local</li>
     *  <li>both addresses are link local</li>
     *  <li>{@code ia} is neither site local nor link local</li>
     * </ol>
     */
    private boolean compareLocalType(Inet6Address ia) {
        if (ia.isSiteLocalAddress() && isSiteLocalAddress()) {
            return true;
        }
        if (ia.isLinkLocalAddress() && isLinkLocalAddress()) {
            return true;
        }
        if (!ia.isSiteLocalAddress() && !ia.isLinkLocalAddress()) {
            return true;
        }
        return false;
    }

    /**
     * Constructs an {@code InetAddress} representing the {@code address} and
     * {@code scope_id}.
     * 
     * @param address
     *            the network address.
     * @param scope_id
     *            the scope id for link- or site-local addresses.
     */
    Inet6Address(byte address[], int scope_id) {
        ipaddress = address;
        this.scope_id = scope_id;
        if (scope_id != 0) {
            scope_id_set = true;
        }
    }

    /**
     * Returns whether this address is an IP multicast address or not. Valid
     * IPv6 multicast addresses are binary prefixed with 11111111 or FF (hex).
     * 
     * @return {@code true} if this address is in the multicast group, {@code
     *         false} otherwise.
     */
    @Override
    public boolean isMulticastAddress() {
        // Multicast addresses are prefixed with 11111111 (255)
        return ipaddress[0] == -1;
    }

    /**
     * Returns whether this address is a unspecified wildcard address "::" or
     * not.
     * 
     * @return {@code true} if this instance represents a wildcard address,
     *         {@code false} otherwise.
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
     * Returns whether this address is the loopback address or not. The only
     * valid IPv6 loopback address is "::1".
     * 
     * @return {@code true} if this instance represents the loopback address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isLoopbackAddress() {

        // The last word must be 1
        if (ipaddress[15] != 1) {
            return false;
        }

        // All other words must be 0
        for (int i = 0; i < 15; i++) {
            if (ipaddress[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether this address is a link-local address or not. A valid IPv6
     * link-local address is prefixed with 1111111010.
     * 
     * @return {@code true} if this instance represents a link-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isLinkLocalAddress() {

        // the first 10 bits need to be 1111111010 (1018)
        return (ipaddress[0] == -2) && ((ipaddress[1] & 255) >>> 6) == 2;
    }

    /**
     * Returns whether this address is a site-local address or not. A valid IPv6
     * site-local address is prefixed with 1111111011.
     * 
     * @return {@code true} if this instance represents a site-local address,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isSiteLocalAddress() {

        // the first 10 bits need to be 1111111011 (1019)
        return (ipaddress[0] == -2) && ((ipaddress[1] & 255) >>> 6) == 3;
    }

    /**
     * Returns whether this address is a global multicast address or not. A
     * valid IPv6 global multicast address is 11111111xxxx1110 or FF0E hex.
     * 
     * @return {@code true} if this instance represents a global multicast
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCGlobal() {
        // the first byte should be 0xFF and the lower 4 bits
        // of the second byte should be 0xE
        return (ipaddress[0] == -1) && (ipaddress[1] & 15) == 14;
    }

    /**
     * Returns whether this address is a node-local multicast address or not. A
     * valid IPv6 node-local multicast address is prefixed with
     * 11111111xxxx0001.
     * 
     * @return {@code true} if this instance represents a node-local multicast
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCNodeLocal() {
        // the first byte should be 0xFF and the lower 4 bits
        // of the second byte should be 0x1
        return (ipaddress[0] == -1) && (ipaddress[1] & 15) == 1;
    }

    /**
     * Returns whether this address is a link-local multicast address or not. A
     * valid IPv6 link-local multicast address is prefixed with
     * 11111111xxxx0010.
     * 
     * @return {@code true} if this instance represents a link-local multicast
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCLinkLocal() {
        // the first byte should be 0xFF and the lower 4 bits
        // of the second byte should be 0x2
        return (ipaddress[0] == -1) && (ipaddress[1] & 15) == 2;
    }

    /**
     * Returns whether this address is a site-local multicast address or not. A
     * valid IPv6 site-local multicast address is prefixed with
     * 11111111xxxx0101.
     * 
     * @return {@code true} if this instance represents a site-local multicast
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCSiteLocal() {
        // the first byte should be 0xFF and the lower 4 bits
        // of the second byte should be 0x5
        return (ipaddress[0] == -1) && (ipaddress[1] & 15) == 5;
    }

    /**
     * Returns whether this address is a organization-local multicast address or
     * not. A valid IPv6 org-local multicast address is prefixed with
     * 11111111xxxx1000.
     * 
     * @return {@code true} if this instance represents a org-local multicast
     *         address, {@code false} otherwise.
     */
    @Override
    public boolean isMCOrgLocal() {
        // the first byte should be 0xFF and the lower 4 bits
        // of the second byte should be 0x8
        return (ipaddress[0] == -1) && (ipaddress[1] & 15) == 8;
    }

    /**
     * Gets the textual representation of this IP address.
     * 
     * @return the as a dotted string formatted IP address.
     */
    @Override
    public String getHostAddress() {
        return Inet6Util.createIPAddrStringFromByteArray(ipaddress);
    }

    /**
     * Gets the scope id as a number if this address is linked to an interface.
     * Otherwise returns {@code 0}.
     * 
     * @return the scope_id of this address or 0 when not linked with an
     *         interface.
     */
    public int getScopeId() {
        if (scope_id_set) {
            return scope_id;
        }
        return 0;
    }

    /**
     * Gets the network interface if this address is instanced with a scoped
     * network interface. Otherwise returns {@code null}.
     * 
     * @return the scoped network interface of this address.
     */
    public NetworkInterface getScopedInterface() {
        if (scope_ifname_set) {
            return scopedIf;
        }
        return null;
    }

    /**
     * Gets the hashcode of the represented IP address.
     * 
     * @return the appropriate hashcode value.
     */
    @Override
    public int hashCode() {
        /* Returns the low order int as the hash code */
        return bytesToInt(ipaddress, 12);
    }

    /**
     * Compares this instance with the IP address in the object {@code obj} and
     * returns {@code true} if they are of the same type and represent the same
     * IP address, {@code false} otherwise. The scope id does not seem to be
     * part of the comparison.
     * 
     * @param obj
     *            the object to be tested for equality.
     * @return {@code true} if the addresses are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns whether this address is IPv4 compatible or not. An IPv4
     * compatible address is prefixed with 96 bits of 0's. The last 32-bits are
     * varied corresponding with the 32-bit IPv4 address space.
     * 
     * @return {@code true} if this instance represents an IPv4 compatible
     *         address, {@code false} otherwise.
     */
    public boolean isIPv4CompatibleAddress() {
        for (int i = 0; i < 12; i++) {
            if (ipaddress[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("ipaddress", new byte[0].getClass()), //$NON-NLS-1$
            new ObjectStreamField("scope_id", Integer.TYPE), //$NON-NLS-1$
            new ObjectStreamField("scope_id_set", Boolean.TYPE), //$NON-NLS-1$
            new ObjectStreamField("scope_ifname_set", Boolean.TYPE), //$NON-NLS-1$
            new ObjectStreamField("ifname", String.class), }; //$NON-NLS-1$

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        if (ipaddress == null) {
            fields.put("ipaddress", null); //$NON-NLS-1$
        } else {
            fields.put("ipaddress", ipaddress); //$NON-NLS-1$
        }

        fields.put("scope_id", scope_id); //$NON-NLS-1$
        fields.put("scope_id_set", scope_id_set); //$NON-NLS-1$
        fields.put("scope_ifname_set", scope_ifname_set); //$NON-NLS-1$
        fields.put("ifname", ifname); //$NON-NLS-1$
        stream.writeFields();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        ipaddress = (byte[]) fields.get("ipaddress", null); //$NON-NLS-1$
        scope_id = fields.get("scope_id", 0); //$NON-NLS-1$
        scope_id_set = fields.get("scope_id_set", false); //$NON-NLS-1$
        ifname = (String) fields.get("ifname", null); //$NON-NLS-1$
        scope_ifname_set = fields.get("scope_ifname_set", false); //$NON-NLS-1$
        if (scope_ifname_set && null != ifname) {
            scopedIf = NetworkInterface.getByName(ifname);
        }
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * IP address.
     * 
     * @return the description, as host/address.
     */
    @Override
    public String toString() {
        if (ifname != null) {
            return super.toString() + "%" + ifname; //$NON-NLS-1$
        }
        if (scope_id != 0) {
            return super.toString() + "%" + scope_id; //$NON-NLS-1$
        }
        return super.toString();
    }
}
