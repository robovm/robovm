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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import libcore.io.ErrnoException;
import libcore.io.IoUtils;
import libcore.io.Libcore;
import static libcore.io.OsConstants.*;

/**
 * This class is used to represent a network interface of the local device. An
 * interface is defined by its address and a platform dependent name. The class
 * provides methods to get all information about the available interfaces of the
 * system or to identify the local interface of a joined multicast group.
 */
public final class NetworkInterface extends Object {
    private final String name;
    private final int interfaceIndex;
    private final List<InterfaceAddress> interfaceAddresses;
    private final List<InetAddress> addresses;

    private final List<NetworkInterface> children = new LinkedList<NetworkInterface>();

    private NetworkInterface parent = null;

    private NetworkInterface(String name, int interfaceIndex,
            List<InetAddress> addresses, List<InterfaceAddress> interfaceAddresses) {
        this.name = name;
        this.interfaceIndex = interfaceIndex;
        this.addresses = addresses;
        this.interfaceAddresses = interfaceAddresses;
    }

    static NetworkInterface forUnboundMulticastSocket() {
        // This is what the RI returns for a MulticastSocket that hasn't been constrained
        // to a specific interface.
        return new NetworkInterface(null, -1,
                Arrays.asList(Inet6Address.ANY), Collections.<InterfaceAddress>emptyList());
    }

    /**
     * Returns the index for the network interface, or -1 if unknown.
     *
     * @hide 1.7
     */
    public int getIndex() {
        return interfaceIndex;
    }

    /**
     * Returns the name of this network interface (such as "eth0" or "lo").
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an enumeration of the addresses bound to this network interface.
     */
    public Enumeration<InetAddress> getInetAddresses() {
        return Collections.enumeration(addresses);
    }

    /**
     * Returns a human-readable name for this network interface. On Android, this is the same
     * string as returned by {@link #getName}.
     */
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns the {@code NetworkInterface} corresponding to the named network interface, or null
     * if no interface has this name.
     *
     * @throws SocketException if an error occurs.
     * @throws NullPointerException if {@code interfaceName == null}.
     */
    public static NetworkInterface getByName(String interfaceName) throws SocketException {
        if (interfaceName == null) {
            throw new NullPointerException("interfaceName == null");
        }
        if (!isValidInterfaceName(interfaceName)) {
            return null;
        }

        int interfaceIndex = readIntFile("/sys/class/net/" + interfaceName + "/ifindex");
        List<InetAddress> addresses = new ArrayList<InetAddress>();
        List<InterfaceAddress> interfaceAddresses = new ArrayList<InterfaceAddress>();
        collectIpv6Addresses(interfaceName, interfaceIndex, addresses, interfaceAddresses);
        collectIpv4Address(interfaceName, addresses, interfaceAddresses);

        return new NetworkInterface(interfaceName, interfaceIndex, addresses, interfaceAddresses);
    }

    private static void collectIpv6Addresses(String interfaceName, int interfaceIndex,
            List<InetAddress> addresses, List<InterfaceAddress> interfaceAddresses) throws SocketException {
        // Format of /proc/net/if_inet6 (all numeric fields are implicit hex).
        // 1. IPv6 address
        // 2. interface index
        // 3. prefix length
        // 4. scope
        // 5. flags
        // 6. interface name
        // "00000000000000000000000000000001 01 80 10 80       lo"
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("/proc/net/if_inet6"));
            String suffix = " " + interfaceName;
            String line;
            while ((line = in.readLine()) != null) {
                if (!line.endsWith(suffix)) {
                    continue;
                }
                byte[] addressBytes = new byte[16];
                for (int i = 0; i < addressBytes.length; ++i) {
                    addressBytes[i] = (byte) Integer.parseInt(line.substring(2*i, 2*i + 2), 16);
                }
                short prefixLength = Short.parseShort(line.substring(36, 38), 16);
                Inet6Address inet6Address = new Inet6Address(addressBytes, null, interfaceIndex);

                addresses.add(inet6Address);
                interfaceAddresses.add(new InterfaceAddress(inet6Address, prefixLength));
            }
        } catch (Exception ex) {
            throw rethrowAsSocketException(ex);
        } finally {
            IoUtils.closeQuietly(in);
        }
    }

    private static void collectIpv4Address(String interfaceName, List<InetAddress> addresses,
            List<InterfaceAddress> interfaceAddresses) throws SocketException {
        FileDescriptor fd = null;
        try {
            fd = Libcore.os.socket(AF_INET, SOCK_DGRAM, 0);
            InetAddress address = Libcore.os.ioctlInetAddress(fd, SIOCGIFADDR, interfaceName);
            InetAddress broadcast = Libcore.os.ioctlInetAddress(fd, SIOCGIFBRDADDR, interfaceName);
            InetAddress netmask = Libcore.os.ioctlInetAddress(fd, SIOCGIFNETMASK, interfaceName);
            if (broadcast.equals(Inet4Address.ANY)) {
                broadcast = null;
            }

            addresses.add(address);
            interfaceAddresses.add(new InterfaceAddress((Inet4Address) address,
                    (Inet4Address) broadcast, (Inet4Address) netmask));
        } catch (ErrnoException errnoException) {
            if (errnoException.errno != EADDRNOTAVAIL) {
                // EADDRNOTAVAIL just means no IPv4 address for this interface.
                // Anything else is a real error.
                throw rethrowAsSocketException(errnoException);
            }
        } catch (Exception ex) {
            throw rethrowAsSocketException(ex);
        } finally {
            IoUtils.closeQuietly(fd);
        }
    }

    @FindBugsSuppressWarnings("DMI_HARDCODED_ABSOLUTE_FILENAME")
    private static boolean isValidInterfaceName(String interfaceName) {
        // Don't just stat because a crafty user might have / or .. in the supposed interface name.
        for (String validName : new File("/sys/class/net").list()) {
            if (interfaceName.equals(validName)) {
                return true;
            }
        }
        return false;
    }

    private static int readIntFile(String path) throws SocketException {
        try {
            String s = IoUtils.readFileAsString(path).trim();
            if (s.startsWith("0x")) {
                return Integer.parseInt(s.substring(2), 16);
            } else {
                return Integer.parseInt(s);
            }
        } catch (Exception ex) {
            throw rethrowAsSocketException(ex);
        }
    }

    private static SocketException rethrowAsSocketException(Exception ex) throws SocketException {
        SocketException result = new SocketException();
        result.initCause(ex);
        throw result;
    }

    /**
     * Returns the {@code NetworkInterface} corresponding to the given address, or null if no
     * interface has this address.
     *
     * @throws SocketException if an error occurs.
     * @throws NullPointerException if {@code address == null}.
     */
    public static NetworkInterface getByInetAddress(InetAddress address) throws SocketException {
        if (address == null) {
            throw new NullPointerException("address == null");
        }
        for (NetworkInterface networkInterface : getNetworkInterfacesList()) {
            if (networkInterface.addresses.contains(address)) {
                return networkInterface;
            }
        }
        return null;
    }

    /**
     * Returns the NetworkInterface corresponding to the given interface index, or null if no
     * interface has this index.
     *
     * @throws SocketException if an error occurs.
     * @hide 1.7
     */
    public static NetworkInterface getByIndex(int index) throws SocketException {
        String name = Libcore.os.if_indextoname(index);
        if (name == null) {
            return null;
        }
        return NetworkInterface.getByName(name);
    }

    /**
     * Gets a list of all network interfaces available on the local system or
     * {@code null} if no interface is available.
     *
     * @return the list of {@code NetworkInterface} instances representing the
     *         available interfaces.
     * @throws SocketException
     *             if an error occurs while getting the network interface
     *             information.
     */
    public static Enumeration<NetworkInterface> getNetworkInterfaces() throws SocketException {
        return Collections.enumeration(getNetworkInterfacesList());
    }

    @FindBugsSuppressWarnings("DMI_HARDCODED_ABSOLUTE_FILENAME")
    private static List<NetworkInterface> getNetworkInterfacesList() throws SocketException {
        String[] interfaceNames = new File("/sys/class/net").list();
        NetworkInterface[] interfaces = new NetworkInterface[interfaceNames.length];
        for (int i = 0; i < interfaceNames.length; ++i) {
            interfaces[i] = NetworkInterface.getByName(interfaceNames[i]);
        }

        List<NetworkInterface> result = new ArrayList<NetworkInterface>();
        boolean[] peeked = new boolean[interfaces.length];
        for (int counter = 0; counter < interfaces.length; counter++) {
            // If this interface has been touched, continue.
            if (peeked[counter]) {
                continue;
            }
            int counter2 = counter;
            // Checks whether the following interfaces are children.
            for (; counter2 < interfaces.length; counter2++) {
                if (peeked[counter2]) {
                    continue;
                }
                if (interfaces[counter2].name.startsWith(interfaces[counter].name + ":")) {
                    // Tagged as peeked
                    peeked[counter2] = true;
                    interfaces[counter].children.add(interfaces[counter2]);
                    interfaces[counter2].parent = interfaces[counter];
                    interfaces[counter].addresses.addAll(interfaces[counter2].addresses);
                }
            }
            // Tagged as peeked
            result.add(interfaces[counter]);
            peeked[counter] = true;
        }
        return result;
    }

    /**
     * Compares the specified object to this {@code NetworkInterface} and
     * returns whether they are equal or not. The object must be an instance of
     * {@code NetworkInterface} with the same name, display name, and list
     * of interface addresses.
     *
     * @param obj
     *            the object to compare with this instance.
     * @return {@code true} if the specified object is equal to this {@code
     *         NetworkInterface}, {@code false} otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NetworkInterface)) {
            return false;
        }
        NetworkInterface rhs = (NetworkInterface) obj;
        // TODO: should the order of the addresses matter (we use List.equals)?
        return interfaceIndex == rhs.interfaceIndex &&
                name.equals(rhs.name) &&
                addresses.equals(rhs.addresses);
    }

    /**
     * Returns the hash code for this {@code NetworkInterface}. Since the
     * name should be unique for each network interface the hash code is
     * generated using the name.
     */
    @Override public int hashCode() {
        return name.hashCode();
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder(25);
        sb.append("[");
        sb.append(name);
        sb.append("][");
        sb.append(interfaceIndex);
        sb.append("]");
        for (InetAddress address : addresses) {
            sb.append("[");
            sb.append(address.toString());
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * Returns a List of the InterfaceAddresses for this network interface.
     * @since 1.6
     */
    public List<InterfaceAddress> getInterfaceAddresses() {
        return Collections.unmodifiableList(interfaceAddresses);
    }

    /**
     * Returns an enumeration of all the sub-interfaces of this network interface.
     * Sub-interfaces are also known as virtual interfaces.
     *
     * <p>For example, {@code eth0:1} would be a sub-interface of {@code eth0}.
     *
     * @return an Enumeration of all the sub-interfaces of this network interface
     * @since 1.6
     */
    public Enumeration<NetworkInterface> getSubInterfaces() {
        return Collections.enumeration(children);
    }

    /**
     * Returns the parent NetworkInterface of this interface if this is a
     * sub-interface, or null if it's a physical (non virtual) interface.
     *
     * @return the NetworkInterface this interface is attached to.
     * @since 1.6
     */
    public NetworkInterface getParent() {
        return parent;
    }

    /**
     * Returns true if this network interface is up.
     *
     * @return true if the interface is up.
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public boolean isUp() throws SocketException {
        return hasFlag(IFF_UP);
    }

    /**
     * Returns true if this network interface is a loopback interface.
     *
     * @return true if the interface is a loopback interface.
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public boolean isLoopback() throws SocketException {
        return hasFlag(IFF_LOOPBACK);
    }

    /**
     * Returns true if this network interface is a point-to-point interface.
     * (For example, a PPP connection using a modem.)
     *
     * @return true if the interface is point-to-point.
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public boolean isPointToPoint() throws SocketException {
        return hasFlag(IFF_POINTOPOINT);
    }

    /**
     * Returns true if this network interface supports multicast.
     *
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public boolean supportsMulticast() throws SocketException {
        return hasFlag(IFF_MULTICAST);
    }

    private boolean hasFlag(int mask) throws SocketException {
        int flags = readIntFile("/sys/class/net/" + name + "/flags");
        return (flags & mask) != 0;
    }

    /**
     * Returns the hardware address of the interface, if it has one, or null otherwise.
     *
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public byte[] getHardwareAddress() throws SocketException {
        try {
            // Parse colon-separated bytes with a trailing newline: "aa:bb:cc:dd:ee:ff\n".
            String s = IoUtils.readFileAsString("/sys/class/net/" + name + "/address");
            byte[] result = new byte[s.length()/3];
            for (int i = 0; i < result.length; ++i) {
                result[i] = (byte) Integer.parseInt(s.substring(3*i, 3*i + 2), 16);
            }
            // We only want to return non-zero hardware addresses.
            for (int i = 0; i < result.length; ++i) {
                if (result[i] != 0) {
                    return result;
                }
            }
            return null;
        } catch (Exception ex) {
            throw rethrowAsSocketException(ex);
        }
    }

    /**
     * Returns the Maximum Transmission Unit (MTU) of this interface.
     *
     * @return the value of the MTU for the interface.
     * @throws SocketException if an I/O error occurs.
     * @since 1.6
     */
    public int getMTU() throws SocketException {
        return readIntFile("/sys/class/net/" + name + "/mtu");
    }

    /**
     * Returns true if this interface is a virtual interface (also called
     * a sub-interface). Virtual interfaces are, on some systems, interfaces
     * created as a child of a physical interface and given different settings
     * (like address or MTU). Usually the name of the interface will the name of
     * the parent followed by a colon (:) and a number identifying the child,
     * since there can be several virtual interfaces attached to a single
     * physical interface.
     *
     * @return true if this interface is a virtual interface.
     * @since 1.6
     */
    public boolean isVirtual() {
        return parent != null;
    }
}
