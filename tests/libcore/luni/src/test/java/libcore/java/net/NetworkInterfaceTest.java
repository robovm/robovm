/*
 * Copyright (C) 2011 The Android Open Source Project
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

package libcore.java.net;

import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

public class NetworkInterfaceTest extends TestCase {
    
    // RoboVM note: 'lo' is called 'lo0' on Darwin and 'eth0' is 'en0'.
    private static final String LO;
    private static final String ETH0;
    
    static {
        String osName = System.getProperty("os.name");
        if (osName.contains("Darwin") || osName.contains("Mac")) {
            LO = "lo0";
            ETH0 = "en0";
        } else {
            LO = "lo";
            ETH0 = "eth0";
        }
    }
    
    // http://code.google.com/p/android/issues/detail?id=13784
    public void testIPv6() throws Exception {
        NetworkInterface lo = NetworkInterface.getByName(LO);
        Set<InetAddress> actual = new HashSet<InetAddress>(Collections.list(lo.getInetAddresses()));

        // RoboVM note: The original test checks that lo has exactly 2 addresses
        // (127.0.0.1 and ::1) but on Darwin we get an extra one (fe80::1) so
        // we now check for those 2 and ignore everything else.
        assertTrue(actual.contains(Inet4Address.LOOPBACK));
        assertTrue(actual.contains(Inet6Address.getByAddress("localhost", new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, null)));
    }

    public void testLoopback() throws Exception {
        // We know lo shouldn't have a hardware address or an IPv4 broadcast address.
        NetworkInterface lo = NetworkInterface.getByName(LO);
        assertNull(lo.getHardwareAddress());
        for (InterfaceAddress ia : lo.getInterfaceAddresses()) {
            assertNull(ia.getBroadcast());
        }

        // But eth0, if it exists, should...
        NetworkInterface eth0 = NetworkInterface.getByName(ETH0);
        if (eth0 != null) {
            assertEquals(6, eth0.getHardwareAddress().length);
            for (InterfaceAddress ia : eth0.getInterfaceAddresses()) {
                if (ia.getAddress() instanceof Inet4Address) {
                    assertNotNull(ia.getBroadcast());
                }
            }
        }
    }

    public void testDumpAll() throws Exception {
        Set<String> allNames = new HashSet<String>();
        Set<Integer> allIndexes = new HashSet<Integer>();
        for (NetworkInterface nif : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            System.err.println(nif);
            System.err.println(nif.getInterfaceAddresses());
            String flags = nif.isUp() ? "UP" : "DOWN";
            if (nif.isLoopback()) {
                flags += " LOOPBACK";
            }
            if (nif.isPointToPoint()) {
                flags += " PTP";
            }
            if (nif.isVirtual()) {
                flags += " VIRTUAL";
            }
            if (nif.supportsMulticast()) {
                flags += " MULTICAST";
            }
            flags += " MTU=" + nif.getMTU();
            byte[] mac = nif.getHardwareAddress();
            if (mac != null) {
                flags += " HWADDR=";
                for (int i = 0; i < mac.length; ++i) {
                    if (i > 0) {
                        flags += ":";
                    }
                    flags += String.format("%02x", mac[i]);
                }
            }
            System.err.println(flags);
            System.err.println("-");

            assertFalse(allNames.contains(nif.getName()));
            allNames.add(nif.getName());

            assertFalse(allIndexes.contains(nif.getIndex()));
            allIndexes.add(nif.getIndex());
        }
    }
}
