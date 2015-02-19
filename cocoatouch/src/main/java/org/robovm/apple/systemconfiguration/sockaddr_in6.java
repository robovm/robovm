/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.systemconfiguration;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;

class sockaddr_in6 extends Struct<sockaddr_in6> {
    private static final byte AF_INET6 = 30;
    
    public static class sockaddr_in6Ptr extends Ptr<sockaddr_in6, sockaddr_in6Ptr> {}
    
    public sockaddr_in6() {
        sin6_len((byte)sizeOf());
        sin6_family(AF_INET6);
    }
    
    public sockaddr_in6(InetSocketAddress address) {
        this();
        sin6_port((short)address.getPort());
        InetAddress addr = address.getAddress();
        if (!(addr instanceof Inet6Address)) {
            throw new IllegalArgumentException("address must be a valid IPv6 address!");
        }
        sin6_addr(addr.getAddress());
        sin6_scope_id(((Inet6Address)addr).getScopeId());
    }
    
    @StructMember(0) public native byte sin6_len();
    @StructMember(0) public native sockaddr_in6 sin6_len(byte len);
    @StructMember(1) public native byte sin6_family();
    @StructMember(1) public native sockaddr_in6 sin6_family(byte family);
    @StructMember(2) public native short sin6_port();
    @StructMember(2) public native sockaddr_in6 sin6_port(short port);
    @StructMember(3) public native @MachineSizedUInt long sin6_flowinfo();
    @StructMember(3) public native sockaddr_in6 sin6_flowinfo(@MachineSizedUInt long flowinfo);
    @StructMember(4) public native @Array(16) byte[] sin6_addr();
    @StructMember(4) public native sockaddr_in6 sin6_addr(@Array(16) byte[] addr);
    @StructMember(5) public native @MachineSizedUInt long sin6_scope_id();
    @StructMember(5) public native sockaddr_in6 sin6_scope_id(@MachineSizedUInt long scope_id);
    
    public InetSocketAddress toInetSocketAddress() {
        try {
            Inet6Address address = Inet6Address.getByAddress(null, sin6_addr(), (int)sin6_scope_id());
            return new InetSocketAddress(address, sin6_port());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
