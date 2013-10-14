/*
 * Copyright (C) 2013 Trillian AB
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

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.robovm.libimobiledevice.IDevice.EventListener;

/**
 * Tests {@link IDevice}.
 */
public class IDeviceTest {

    @Before
    public void setUp() {
        Assume.assumeTrue(IDevice.listUdids().length > 0);
    }
    
    @Test
    public void testListUdids() {
        String[] udids = IDevice.listUdids();
        assertTrue(udids.length > 0);
        System.out.println(Arrays.asList(udids));
    }
    
    @Test
    public void testNewIDeviceThrows() throws Exception {
        try {
            new IDevice((String) null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}
        try {
            new IDevice("01234");
            fail("LibIMobileDeviceException expected");
        } catch (LibIMobileDeviceException e) {}
    }

    @Test
    public void testNewIDevice() throws Exception {
        String[] udids = IDevice.listUdids();
        try (IDevice device = new IDevice(udids[0])) {
            assertEquals(udids[0], device.getUdid());
        }
    }
    
    @Test
    public void testDeviceEvents() throws Exception {
        // We cannot automate the test of device removal. But deviceAdded()
        // will be called for all connected devices when we register the first
        // listener so that can be tested.
        Set<String> udids = new TreeSet<>(Arrays.asList(IDevice.listUdids()));
        final Set<String> eventUdids = new TreeSet<>();
        final CountDownLatch countDownLatch = new CountDownLatch(udids.size());
        EventListener listener = new EventListener() {
            public void deviceRemoved(String udid) {
                eventUdids.remove(udid);
            }
            public void deviceAdded(String udid) {
                eventUdids.add(udid);
                countDownLatch.countDown();
            }
        };
        IDevice.addEventListener(listener);
        assertEquals(1, IDevice.listeners.size());
        countDownLatch.await(5, TimeUnit.SECONDS);
        IDevice.removeEventListener(listener);
        assertEquals(0, IDevice.listeners.size());
        assertEquals(udids, eventUdids);
    }
}
