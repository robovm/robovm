/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.rt;

import static org.junit.Assert.assertTrue;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.junit.Test;

public class Issue1137Test {
    @Test
    public void testIssue1137() throws SocketException {
        // Tests the new implementation of {@link NetworkInterface#getInterfaces()} 
        // based on getifaddr() instead of ioctl to retrieve ipv4 addresses.
        // Just a smoke test checking if the loopback device is available.
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        boolean found = false;
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface itf = networkInterfaces.nextElement();
            if (itf.isLoopback()) {
                for (InterfaceAddress addr : itf.getInterfaceAddresses()) {
                    if (addr.getAddress() instanceof Inet4Address) {
                        assertTrue(addr.getAddress().isLoopbackAddress());
                        found = true;
                        break;
                    }
                }
            }
        }
        assertTrue(found);   
    }
}
