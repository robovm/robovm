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

import static org.robovm.libimobiledevice.binding.LibIMobileDeviceConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.robovm.libimobiledevice.binding.IDeviceConnectionRefOut;
import org.robovm.libimobiledevice.binding.IDeviceRef;
import org.robovm.libimobiledevice.binding.IDeviceRefOut;
import org.robovm.libimobiledevice.binding.IntOut;
import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.StringArray;
import org.robovm.libimobiledevice.binding.StringArrayOut;
import org.robovm.libimobiledevice.binding.StringOut;

/**
 * Handles device connection communication.
 */
public class IDevice implements AutoCloseable {
    static List<EventListener> listeners = new ArrayList<>();
    
    protected IDeviceRef ref;

    IDevice(IDeviceRef ref) {
        this.ref = ref;
    }
    
    /**
     * Creates a new {@link IDevice} for the device with the UDID specified by 
     * {@code udid}, if the device is available.
     * 
     * @param udid the UDID.
     * @see #listUdids()
     */
    public IDevice(String udid) {
        if (udid == null) {
            throw new NullPointerException("udid");
        }
        IDeviceRefOut refOut = new IDeviceRefOut();
        try {
            checkResult(LibIMobileDevice.idevice_new(refOut, udid));
            this.ref = refOut.getValue();
        } finally {
            refOut.delete();
        }
    }
    
    protected IDeviceRef getRef() {
        checkDisposed();
        return ref;
    }
    
    /**
     * Returns the unique id (UDID) for this device.
     * 
     * @return the UDID.
     */
    public String getUdid() {
        StringOut out = new StringOut();
        try {
            checkResult(LibIMobileDevice.idevice_get_udid(getRef(), out));
            return out.getValue();
        } finally {
            out.delete();
        }
    }
    
    /**
     * Set up a connection to the device.
     *
     * @param port the destination port to connect to.
     * @return the connection.
     */
    public IDeviceConnection connect(int port) {
        if (port < 0 || port > 0xffff) {
            throw new IllegalArgumentException("port out of range");
        }
        IDeviceConnectionRefOut connOut = new IDeviceConnectionRefOut();
        try {
            checkResult(LibIMobileDevice.idevice_connect(getRef(), (short) port, connOut));
            return new IDeviceConnection(connOut.getValue());
        } finally {
            connOut.delete();
        }
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LibIMobileDeviceException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LibIMobileDevice.idevice_free(ref);
        ref = null;
    }
    
    @Override
    public void close() throws Exception {
        dispose();
    }

    /**
     * Sets the level of debugging. 0 turns it off.
     * 
     * @param level the debug level.
     */
    public static void setDebugLevel(int level) {
        // Only 0 and 1 is supported at the moment by libimobiledevice.
        LibIMobileDevice.idevice_set_debug_level(level > 0 ? 1 : 0);
    }
    
    /**
     * Lists UDIDs of currently available devices.
     * 
     * @return the UDIDs of the currently available devices.
     */
    public static String[] listUdids() {
        StringArrayOut devicesOut = new StringArrayOut();
        IntOut countOut = new IntOut();
        try {
            checkResult(LibIMobileDevice.idevice_get_device_list(devicesOut, countOut));
            StringArray devices = devicesOut.getValue();
            int count = countOut.getValue();
            String[] udids = new String[count];
            for (int i = 0; i < count; i++) {
                udids[i] = devices.get(i);
            }
            return udids;
        } catch (LibIMobileDeviceException e) {
        	if (e.getErrorCode() == IDEVICE_E_NO_DEVICE) {
        		// This happens when usbmuxd isn't running
        		return new String[0];
        	}
        	throw e;
        } finally {
            devicesOut.delete();
            countOut.delete();
        }
    }

    /**
     * Registers a new {@link EventListener} which will be called when devices
     * are added and removed.
     * 
     * @param listener the listener to add.
     */
    public static void addEventListener(EventListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        synchronized (listeners) {
            listeners.add(listener);
            if (listeners.size() == 1) {
                LibIMobileDevice.idevice_event_subscribe(LibIMobileDevice.get_global_idevice_event_cb(), 0);
            }
        }
    }

    /**
     * Removes a previously added {@link EventListener}.
     * 
     * @param listener the listener to remove.
     */
    public static void removeEventListener(EventListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        synchronized (listeners) {
            if (listeners.remove(listener)) {
                if (listeners.isEmpty()) {
                    LibIMobileDevice.idevice_event_unsubscribe();
                }
            }
        }
    }

    static void checkResult(int result) {
        switch (result) {
        case IDEVICE_E_SUCCESS: return;
        case IDEVICE_E_BAD_HEADER: throw new LibIMobileDeviceException(result, "IDEVICE_E_BAD_HEADER");
        case IDEVICE_E_INVALID_ARG: throw new LibIMobileDeviceException(result, "IDEVICE_E_INVALID_ARG");
        case IDEVICE_E_NO_DEVICE: throw new LibIMobileDeviceException(result, "IDEVICE_E_NO_DEVICE");
        case IDEVICE_E_NOT_ENOUGH_DATA: throw new LibIMobileDeviceException(result, "IDEVICE_E_NOT_ENOUGH_DATA");
        case IDEVICE_E_SSL_ERROR: throw new LibIMobileDeviceException(result, "IDEVICE_E_SSL_ERROR");
        case IDEVICE_E_UNKNOWN_ERROR: throw new LibIMobileDeviceException(result, "IDEVICE_E_UNKNOWN_ERROR");
        default: throw new LibIMobileDeviceException(result);
        }
    }
    
    /**
     * Listener which gets called when devices are added and removed.
     */
    public interface EventListener {
        /**
         * Device with the specified UDID was added.
         * 
         * @param udid the UDID of the added device.
         */
        void deviceAdded(String udid);
        /**
         * Device with the specified UDID was removed.
         * 
         * @param udid the UDID of the removed device.
         */
        void deviceRemoved(String udid);
    }
    
    public static void main(String[] args) throws Exception {
        String[] udids = IDevice.listUdids();
        if (udids.length == 0) {
            System.err.println("No devices connected");
            return;
        }
        for (int i = 0; i < udids.length; i++) {
            System.out.format("%d: %s\n", i, udids[i]);
        }
    }
}
