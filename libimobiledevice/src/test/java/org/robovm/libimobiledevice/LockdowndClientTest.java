/*
 * Copyright (C) 2013 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.libimobiledevice;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;

/**
 * Tests {@link LockdowndClient}.
 */
public class LockdowndClientTest {

    @Before
    public void setUp() {
        Assume.assumeTrue(NativeLibrary.supportedPlatform && IDevice.listUdids().length > 0);
    }

    @Test
    public void testGetValue() throws Exception {
        String udid = IDevice.listUdids()[0];
        try (
                IDevice device = new IDevice(udid);
                LockdowndClient client = new LockdowndClient(device, null, true);
                ) {
            NSObject node = client.getValue(null, null);
            assertTrue(node instanceof NSDictionary);
            NSDictionary dict = (NSDictionary) node;
            assertNotNull(dict.objectForKey("DeviceName"));
        }
    }

}
