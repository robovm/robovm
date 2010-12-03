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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * This class is used to represent a network interface of the local device. An
 * interface is defined by its address and a platform dependent name. The class
 * provides methods to get all information about the available interfaces of the
 * system or to identify the local interface of a joined multicast group.
 */
public final class NetworkInterface extends Object {

    private static final int CHECK_CONNECT_NO_PORT = -1;

    static final int NO_INTERFACE_INDEX = 0;

    static final int UNSET_INTERFACE_INDEX = -1;

    private String name;

    private String displayName;

    List<InetAddress> addresses = new LinkedList<InetAddress>();

    // The interface index is a positive integer which is non-negative. Where
    // value is zero then we do not have an index for the interface (which
    // occurs in systems which only support IPV4)
    private int interfaceIndex;

    private int hashCode;

    private NetworkInterface parent = null;

    private List<NetworkInterface> children = new LinkedList<NetworkInterface>();

    /**
     * This {@code native} method returns the list of network interfaces
     * supported by the system. An array is returned which is easier to generate
     * and which can easily be converted into the required enumeration on the
     * java side.
     * 
     * @return an array of zero or more {@code NetworkInterface} objects
     * @throws SocketException
     *             if an error occurs when getting network interface information
     */
    private static native NetworkInterface[] getNetworkInterfacesImpl()
            throws SocketException;

    /**
     * This constructor is used by the native method in order to construct the
     * NetworkInterface objects in the array that it returns.
     * 
     * @param name
     *            internal name associated with the interface.
     * @param displayName
     *            a user interpretable name for the interface.
     * @param addresses
     *            the Internet addresses associated with the interface.
     * @param interfaceIndex
     *            an index for the interface. Only set for platforms that
     *            support IPV6.
     */
    NetworkInterface(String name, String displayName, InetAddress addresses[],
            int interfaceIndex) {
        this.name = name;
        this.displayName = displayName;
        this.interfaceIndex = interfaceIndex;
   
        if(addresses != null){
            for (int i = 0; i < addresses.length; i++) {
                this.addresses.add(addresses[i]);
            }
        }
    }

    /**
     * Returns the index for the network interface. Unless the system supports
     * IPV6 this will be 0.
     * 
     * @return the index
     */
    int getIndex() {
        return interfaceIndex;
    }

    /**
     * Returns the first address for the network interface. This is used in the
     * natives when we need one of the addresses for the interface and any one
     * will do
     * 
     * @return the first address if one exists, otherwise null.
     */
    InetAddress getFirstAddress() {
        if (addresses.size() >= 1) {
            return addresses.get(0);
        }
        return null;
    }

    /**
     * Gets the name associated with this network interface.
     * 
     * @return the name of this {@code NetworkInterface} instance.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of addresses bound to this network interface.
     * 
     * @return the address list of the represented network interface.
     */
    public Enumeration<InetAddress> getInetAddresses() {
        /*
         * create new vector from which Enumeration to be returned can be
         * generated set the initial capacity to be the number of addresses for
         * the network interface which is the maximum required size
         */

        /*
         * return an empty enumeration if there are no addresses associated with
         * the interface
         */
        if (0 == addresses.size()) {
            return new Vector<InetAddress>(0).elements();
        }

        /*
         * for those configuration that support the security manager we only
         * return addresses for which checkConnect returns true
         */
        Vector<InetAddress> accessibleAddresses = new Vector<InetAddress>(
                addresses.size());

        /*
         * get the security manager. If one does not exist just return the full
         * list
         */
        SecurityManager security = System.getSecurityManager();
        if (security == null) {
            return (new Vector<InetAddress>(addresses)).elements();
        }

        /*
         * ok security manager exists so check each address and return those
         * that pass
         */
        for (InetAddress element : addresses) {
            if (security != null) {
                try {
                    /*
                     * since we don't have a port in this case we pass in
                     * NO_PORT
                     */
                    String hostName = element.getHostName();
                    if(hostName.contains("::")){
                        hostName = getFullFormOfCompressedIPV6Address(hostName);
                    }
                    security.checkConnect(hostName,
                            CHECK_CONNECT_NO_PORT);
                    accessibleAddresses.add(element);
                } catch (SecurityException e) {
                }
            }
        }

        Enumeration<InetAddress> theAccessibleElements = accessibleAddresses
                .elements();
        if (theAccessibleElements.hasMoreElements()) {
            return accessibleAddresses.elements();
        }

        return new Vector<InetAddress>(0).elements();
    }
    
    private String getFullFormOfCompressedIPV6Address(String compressed) {
        StringBuilder fullForm = new StringBuilder(39);
        final int NUM_OF_IPV6_FIELDS = 8;

        String[] fields = compressed.split(":");
        // the number of compressed fields
        int numOfCompressedFields;
        
        if (compressed.startsWith("::")) { // compressed head part
            compressed = compressed.replace("::", "");
            fields = compressed.split(":");
            numOfCompressedFields = NUM_OF_IPV6_FIELDS - fields.length;
            restoreCompressedFieldsWithZero(fullForm, numOfCompressedFields);
            appendNonZeroFields(fullForm, fields);
        } else if (compressed.endsWith("::")) { // compressed tail part
            compressed = compressed.replace("::", "");
            fields = compressed.split(":");
            numOfCompressedFields = NUM_OF_IPV6_FIELDS - fields.length;
            appendNonZeroFields(fullForm, fields);
            restoreCompressedFieldsWithZero(fullForm, numOfCompressedFields);
        } else { // compressed middle part
            numOfCompressedFields = NUM_OF_IPV6_FIELDS - fields.length + 1;
            for (String field : fields) {
                if (field.equals("")) {
                    // for compressed fields add 0
                    restoreCompressedFieldsWithZero(fullForm, numOfCompressedFields);
                } else {
                    fullForm.append(field);
                    // add colon
                    fullForm.append(":");
                }
            }
        }
        // delete the excess colon
        fullForm.deleteCharAt(fullForm.length() - 1);

        return fullForm.toString();
    }

    private void appendNonZeroFields(StringBuilder fullForm,
            String[] fields) {
        for (int i = 0; i < fields.length; i++) {
            fullForm.append(fields[i]);
            fullForm.append(":");
        }
    }

    private void restoreCompressedFieldsWithZero(StringBuilder fullForm,
            int numOfCompressedFields) {
        for (int i = 0; i < numOfCompressedFields; i++) {
            fullForm.append("0");
            // add colon
            fullForm.append(":");
        }
    }

    /**
     * Gets the human-readable name associated with this network interface.
     * 
     * @return the display name of this network interface or the name if the
     *         display name is not available.
     */
    public String getDisplayName() {
        /*
         * we should return the display name unless it is blank in this case
         * return the name so that something is displayed.
         */
        if (!(displayName.equals(""))) { //$NON-NLS-1$
            return displayName;
        }
        return name;
    }

    /**
     * Gets the specific network interface according to a given name.
     * 
     * @param interfaceName
     *            the name to identify the searched network interface.
     * @return the network interface with the specified name if one exists or
     *         {@code null} otherwise.
     * @throws SocketException
     *             if an error occurs while getting the network interface
     *             information.
     * @throws NullPointerException
     *             if the given interface's name is {@code null}.
     */
    public static NetworkInterface getByName(String interfaceName)
            throws SocketException {

        if (interfaceName == null) {
            throw new NullPointerException(Messages.getString("luni.6D")); //$NON-NLS-1$
        }

        /*
         * get the list of interfaces, and then loop through the list to look
         * for one with a matching name
         */
        Enumeration<NetworkInterface> interfaces = getNetworkInterfaces();
        if (interfaces != null) {
            while (interfaces.hasMoreElements()) {
                NetworkInterface netif = interfaces.nextElement();
                if (netif.getName().equals(interfaceName)) {
                    return netif;
                }
            }
        }
        return null;
    }

    /**
     * Gets the specific network interface according to the given address.
     *
     * @param address
     *            the address to identify the searched network interface.
     * @return the network interface with the specified address if one exists or
     *         {@code null} otherwise.
     * @throws SocketException
     *             if an error occurs while getting the network interface
     *             information.
     * @throws NullPointerException
     *             if the given interface address is invalid.
     */
    public static NetworkInterface getByInetAddress(InetAddress address)
            throws SocketException {

        if (address == null) {
            throw new NullPointerException(Messages.getString("luni.68")); //$NON-NLS-1$
        }

        /*
         * get the list of interfaces, and then loop through the list. For each
         * interface loop through the associated set of internet addresses and
         * see if one matches. If so return that network interface
         */
        Enumeration<NetworkInterface> interfaces = getNetworkInterfaces();
        if (interfaces != null) {
            while (interfaces.hasMoreElements()) {
                NetworkInterface netif = interfaces.nextElement();
                /*
                 * to be compatible use the raw addresses without any security
                 * filtering
                 */
                // Enumeration netifAddresses = netif.getInetAddresses();
                if (netif.addresses.size() != 0) {
                    Enumeration<InetAddress> netifAddresses = new Vector<InetAddress>(
                            netif.addresses).elements();
                    if (netifAddresses != null) {
                        while (netifAddresses.hasMoreElements()) {
                            if (address.equals(netifAddresses.nextElement())) {
                                return netif;
                            }
                        }
                    }
                }
            }
        }
        return null;
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
    public static Enumeration<NetworkInterface> getNetworkInterfaces()
            throws SocketException {
        NetworkInterface[] interfaces = getNetworkInterfacesImpl();
        if (interfaces == null) {
            return null;
        }

        boolean peeked[] = new boolean[interfaces.length];
        for (boolean p : peeked) {
            p = false;
        }

        for (NetworkInterface netif : interfaces) {
            // Ensure that current NetworkInterface is bound to at least
            // one InetAddress before processing
            for (InetAddress addr : netif.addresses) {
                if (16 == addr.ipaddress.length) {
                    if (addr.isLinkLocalAddress() || addr.isSiteLocalAddress()) {
                        ((Inet6Address) addr).scopedIf = netif;
                        ((Inet6Address) addr).ifname = netif.name;
                        ((Inet6Address) addr).scope_ifname_set = true;
                    }
                }
            }
        }

        List<NetworkInterface> hlist = new ArrayList<NetworkInterface>();
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
                StringBuilder headBuilder = new StringBuilder();
                headBuilder.append(interfaces[counter].name);
                headBuilder.append(":"); //$NON-NLS-1$
                if (interfaces[counter2].name
                        .startsWith(headBuilder.toString())) {
                    // Tagged as peeked
                    peeked[counter2] = true;
                    interfaces[counter].children.add(interfaces[counter2]);
                    interfaces[counter2].parent = interfaces[counter];
                    for (int i = 0; i < interfaces[counter2].addresses.size(); i++) {
                        interfaces[counter].addresses
                                .add(interfaces[counter2].addresses.get(i));
                    }
                }
            }
            // Tagged as peeked
            hlist.add(interfaces[counter]);
            peeked[counter] = true;
        }
        return (new Vector<NetworkInterface>(hlist)).elements();
    }

    /**
     * Compares the specified object to this {@code NetworkInterface} and
     * returns whether they are equal or not. The object must be an instance of
     * {@code NetworkInterface} with the same name, {@code displayName} and list
     * of network interfaces to be equal.
     * 
     * @param obj
     *            the object to compare with this instance.
     * @return {@code true} if the specified object is equal to this {@code
     *         NetworkInterface}, {@code false} otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object obj) {
        // Return true if it is the exact same object.
        if (obj == this) {
            return true;
        }

        // Ensure it is the right type.
        if (!(obj instanceof NetworkInterface)) {
            return false;
        }

        /*
         * Make sure that some simple checks pass. If the name is not the same
         * then we are sure it is not the same one. We don't check the hashcode
         * as it is generated from the name which we check
         */
        NetworkInterface netif = (NetworkInterface) obj;

        if (netif.getIndex() != interfaceIndex) {
            return false;
        }

        if (!(name.equals("")) && (!netif.getName().equals(name))) { //$NON-NLS-1$
            return false;
        }

        if ((name.equals("")) && (!netif.getName().equals(displayName))) { //$NON-NLS-1$
            return false;
        }

        // Now check that the collection of internet addresses are equal.
        Enumeration<InetAddress> netifAddresses = netif.getInetAddresses();
        Enumeration<InetAddress> localifAddresses = getInetAddresses();

        // Check for both null (same), or one null (not same).
        if (netifAddresses == null) {
            return localifAddresses == null;
        }
        if (localifAddresses == null) {
            return false;
        }

        // Both are not null, check InetAddress elements.
        while (netifAddresses.hasMoreElements()
                && localifAddresses.hasMoreElements()) {
            if (!(localifAddresses.nextElement()).equals(
                    netifAddresses.nextElement())) {
                return false;
            }
        }

        /*
         * Now make sure that they had the same number of addresses, if not they
         * are not the same interface.
         */
        return !netifAddresses.hasMoreElements()
                && !localifAddresses.hasMoreElements();
    }

    /**
     * Gets the hashcode for this {@code NetworkInterface} instance. Since the
     * name should be unique for each network interface the hashcode is
     * generated using this name.
     * 
     * @return the hashcode value for this {@code NetworkInterface} instance.
     */
    @Override
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = name.hashCode();
        }
        return hashCode;
    }

    /**
     * Gets a string containing a concise, human-readable description of this
     * network interface.
     * 
     * @return the textual representation for this network interface.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(25);
        string.append("["); //$NON-NLS-1$
        string.append(name);
        string.append("]["); //$NON-NLS-1$
        string.append(displayName);
        string.append("]"); //$NON-NLS-1$

        /*
         * get the addresses through this call to make sure we only reveal those
         * that we should
         */
        Enumeration<InetAddress> theAddresses = getInetAddresses();
        if (theAddresses != null) {
            while (theAddresses.hasMoreElements()) {
                InetAddress nextAddress = theAddresses.nextElement();
                string.append("["); //$NON-NLS-1$
                string.append(nextAddress.toString());
                string.append("]"); //$NON-NLS-1$
            }
        }
        return string.toString();
    }

    /**
     * Get a List of all or a subset of the InterfaceAddresses of this network
     * interface.
     * 
     * If there is a security manager, its checkConnect method is called with
     * the InetAddress for each InterfaceAddress. Only InterfaceAddresses where
     * the checkConnect doesn't throw a SecurityException will be returned in
     * the List.
     * 
     * @return a List object with all or a subset of the InterfaceAddresss of
     *         this network interface
     * 
     * @since 1.6
     */
    public List<InterfaceAddress> getInterfaceAddresses() {
        InterfaceAddress[] ifAddrs = getInterfaceAddressesImpl(name,
                interfaceIndex);
        List<InterfaceAddress> ifList = new ArrayList<InterfaceAddress>();
        if (ifAddrs == null) {
            return ifList;
        }
        SecurityManager sm = System.getSecurityManager();

        for (InterfaceAddress ifAddr : ifAddrs) {
            if (sm != null) {
                try {
                    sm.checkConnect(ifAddr.getAddress().getHostName(),
                            CHECK_CONNECT_NO_PORT);
                } catch (SecurityException e) {
                    // if there is an exception, not add it into the list
                    continue;
                }
            }
            ifList.add(ifAddr);
        }
        return ifList;
    }

    private static native InterfaceAddress[] getInterfaceAddressesImpl(
            String n, int index);

    /**
     * Get an Enumeration with all the subinterfaces (also known as virtual
     * interfaces) attached to this network interface.
     * 
     * For instance eth0:1 will be a subinterface to eth0.
     * 
     * @return an Enumeration object with all of the subinterfaces of this
     *         network interface
     * @since 1.6
     */
    public Enumeration<NetworkInterface> getSubInterfaces() {
        return (new Vector<NetworkInterface>(children)).elements();
    }

    /**
     * Returns the parent NetworkInterface of this interface if this is a
     * subinterface, or null if it's a physical (non virtual) interface.
     * 
     * @return The NetworkInterface this interface is attached to.
     * 
     * @since 1.6
     */
    public NetworkInterface getParent() {
        return parent;
    }

    /**
     * Answers if a network interface is up and running.
     * 
     * @return true if the interface is up and running.
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public boolean isUp() throws SocketException {
        if (0 == addresses.size()) {
            return false;
        }
        return isUpImpl(name, interfaceIndex);
    }

    private native boolean isUpImpl(String n, int index) throws SocketException;

    /**
     * Answers if a network interface is a loopback interface.
     * 
     * @return true if the interface is a loopback interface.
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public boolean isLoopback() throws SocketException {
        if (0 == addresses.size()) {
            return false;
        }
        return isLoopbackImpl(name, interfaceIndex);
    }

    private native boolean isLoopbackImpl(String n, int index)
            throws SocketException;

    /**
     * Answers if a network interface is a point to point interface. Usually, a
     * point to point interface would be a PPP connection using a modem.
     * 
     * @return true if the interface is a point to point one.
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public boolean isPointToPoint() throws SocketException {
        if (0 == addresses.size()) {
            return false;
        }
        return isPoint2PointImpl(name, interfaceIndex);
    }

    private native boolean isPoint2PointImpl(String n, int index)
            throws SocketException;

    /**
     * Answers if a network interface supports multicasting.
     * 
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public boolean supportsMulticast() throws SocketException {
        if (0 == addresses.size()) {
            return false;
        }
        return supportMulticastImpl(name, interfaceIndex);
    }

    private native boolean supportMulticastImpl(String n, int index)
            throws SocketException;

    /**
     * Answers the (MAC) hardware address of the interface if it has one and the
     * user has privilege to access the address.
     * 
     * @return a byte array containing the address or null if the address
     *         doesn't exist or is not accessible.
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public byte[] getHardwareAddress() throws SocketException {
        if (0 == addresses.size()) {
            return new byte[0];
        }
        return getHardwareAddressImpl(name, interfaceIndex);
    }

    private native byte[] getHardwareAddressImpl(String n, int index)
            throws SocketException;

    /**
     * Answers the Maximum Transmission Unit (MTU) of the interface.
     * 
     * @return the value of the MTU for the interface.
     * @throws SocketException
     *             if an I/O error occurs.
     * @since 1.6
     */
    public int getMTU() throws SocketException {
        if (0 == addresses.size()) {
            return 0;
        }
        return getMTUImpl(name, interfaceIndex);
    }

    private native int getMTUImpl(String n, int index) throws SocketException;

    /**
     * Answers if this interface is a virtual interface (also called
     * subinterface). Virtual interfaces are, on some systems, interfaces
     * created as a child of a physical interface and given different settings
     * (like address or MTU). Usually the name of the interface will the name of
     * the parent followed by a colon (:) and a number identifying the child
     * since there can be several virtual interfaces attached to a single
     * physical interface.
     * 
     * @return true if this interface is a virtual interface.
     * 
     * @since 1.6
     */
    public boolean isVirtual() {
        return parent != null;
    }

}
