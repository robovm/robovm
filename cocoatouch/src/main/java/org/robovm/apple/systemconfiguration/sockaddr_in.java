/*
 * Copyright (C) 2014 Trillian Mobile AB
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

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;

class sockaddr_in extends Struct<sockaddr_in> {
    private static final byte AF_INET = 2;
    
    public static class sockaddr_inPtr extends Ptr<sockaddr_in, sockaddr_inPtr> {}
    
    public sockaddr_in() {
        sin_len((byte)sizeOf());
        sin_family(AF_INET);
    }
    
    public sockaddr_in(InetSocketAddress address) {
        this();
        sin_port((short)address.getPort());
        InetAddress addr = address.getAddress();
        if (!(addr instanceof Inet4Address)) {
            throw new IllegalArgumentException("address must be a valid IPv4 address!");
        }
        sin_addr(addr.getAddress());
    }
    
    @StructMember(0) public native byte sin_len();
    @StructMember(0) public native sockaddr_in sin_len(byte len);
    @StructMember(1) public native byte sin_family();
    @StructMember(1) public native sockaddr_in sin_family(byte family);
    @StructMember(2) public native short sin_port();
    @StructMember(2) public native sockaddr_in sin_port(short port);
    @StructMember(3) public native @Array(4) byte[] sin_addr();
    @StructMember(3) public native sockaddr_in sin_addr(@Array(4) byte[] addr);
    @StructMember(4) public native @Array(8) char[] sin_zero();
    @StructMember(4) public native sockaddr_in sin_zero(@Array(8) char[] zero);
    
    public InetSocketAddress toInetSocketAddress() {
        try {
            return new InetSocketAddress(InetAddress.getByAddress(sin_addr()), sin_port());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
