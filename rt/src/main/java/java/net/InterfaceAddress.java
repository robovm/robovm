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
 * This class is a Network Interface address. If the address is an IPv4 one, it
 * contains an IP address, a subnet mask and a broadcast address. If it is an
 * IPv6 one, there is a network prefix length, an IPv6 prefix and an IP address.
 * 
 * @since 1.6
 */
public class InterfaceAddress {

    private InetAddress addr;

    // the subnet mask when IPv4 or null when IPv6
    private InetAddress mask;

    // cache broadcast address for performance concern
    private InetAddress cacheBroadcast = new InetAddress(null);
    
    private short prefixLength;
    
    InterfaceAddress(InetAddress address, short prefix) {
        addr = address;
        prefixLength = prefix;
        
        if (addr != null) {
            mask = calSubnetMask();            
        }
    }
    
    private InetAddress calSubnetMask() {
        // if the address is ipv4, calculate its subnet mask address
        if (addr instanceof Inet4Address) {
            byte[] maskAddr = new byte[4];
            for (int i = 0; i < prefixLength; i++) {
                maskAddr[i / 8] |= 1 << (7 - i % 8);
            }
            return new Inet4Address(maskAddr);
        }
        return null;
    }

    /**
     * Answers whether this object is equal to another one. Returns true when
     * the address, broadcast address and prefix length are all equal.
     * 
     * @param obj
     *            the object to be compared.
     * @return true if two interface addresses equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if (obj instanceof InterfaceAddress) {
            InterfaceAddress anotherInterAddr = (InterfaceAddress) obj;
            boolean equals = addr == null ? anotherInterAddr.getAddress() == null
                    : addr.equals(anotherInterAddr.getAddress());
            if (equals
                    && (anotherInterAddr.getNetworkPrefixLength() == prefixLength)) {
                InetAddress boardcast = cacheBroadcast.ipaddress == null ? 
                        getBroadcast() : cacheBroadcast;
                InetAddress anotherBoardcast = anotherInterAddr.getBroadcast();
                equals = boardcast == null ? anotherBoardcast == null
                        : boardcast.equals(anotherBoardcast);
            }
            return equals;
        }
        return false;
    }

    /**
     * Answers a hashcode for this Interface address.
     * 
     * @return a hash code value.
     */
    @Override
    public int hashCode() {
        int hashCode = addr == null ? 0 : -addr.hashCode();
        InetAddress boardcast =  cacheBroadcast.ipaddress == null ? 
                getBroadcast() : cacheBroadcast;
        hashCode += boardcast == null ? 0 : boardcast.hashCode();
        hashCode += prefixLength;
        return hashCode;
    }

    /**
     * Answers the string representation for this interface address. The string
     * follows the form: InetAddress / prefix length [ broadcast address ].
     * 
     * @return a string representation of this interface address.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (addr != null) {
            builder.append(addr.toString());
        }
        builder.append("/"); //$NON-NLS-1$
        builder.append(prefixLength);
        builder.append(" ["); //$NON-NLS-1$
        InetAddress broadcast = cacheBroadcast.ipaddress == null ? 
                getBroadcast() : cacheBroadcast;
        if (broadcast != null) {
            builder.append(broadcast.toString());
        } else {
            builder.append("null"); //$NON-NLS-1$
        }
        builder.append("]"); //$NON-NLS-1$
        return builder.toString();
    }

    /**
     * Answers an InetAddress for this address.
     * 
     * @return the InetAddress for this address.
     */
    public InetAddress getAddress() {
        return addr;
    }

    /**
     * Answers an InetAddress for the brodcast address.
     * 
     * If the address is an IPv6 one, returns null.
     * 
     * @return the InetAddress that represents the broadcast address or null if
     *         there is no broadcast address.
     */
    public InetAddress getBroadcast() {        
        if (addr instanceof Inet4Address && mask instanceof Inet4Address) {
            if (cacheBroadcast.ipaddress == null) {
                byte[] broadcast = new byte[4];
                if (prefixLength > 0) {
                    byte[] maskBytes = mask.getAddress();
                    byte[] addrBytes = addr.getAddress();
                    for (int i = 0; i < broadcast.length; i++) {
                        broadcast[i] = (byte) (addrBytes[i] | ~maskBytes[i]);
                    }                    
                }                
                cacheBroadcast = new InetAddress(broadcast);
            }
            return cacheBroadcast;
        }
        return null;
    }

    /**
     * Answers the network prefix length for the InetAddress.
     * 
     * If the address is an IPv4 one, returns the length of the subnet mask.
     * 
     * @return a short representing the prefix length for the subnet mask of
     *         this address.
     * 
     */
    public short getNetworkPrefixLength() {
        return prefixLength;
    }
}
