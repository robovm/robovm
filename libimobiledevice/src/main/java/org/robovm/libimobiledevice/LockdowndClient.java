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

import java.io.IOException;

import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.LockdowndClientRef;
import org.robovm.libimobiledevice.binding.LockdowndClientRefOut;
import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStruct;
import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStructOut;
import org.robovm.libimobiledevice.binding.PlistRef;
import org.robovm.libimobiledevice.binding.PlistRefOut;

import com.dd.plist.NSObject;

/**
 * Handles device connection communication.
 */
public class LockdowndClient implements AutoCloseable {
    protected LockdowndClientRef ref;

    LockdowndClient(LockdowndClientRef ref) {
        this.ref = ref;
    }
    
    /**
     * Creates a new {@link LockdowndClient} for the specified {@link IDevice}.
     * 
     * @param device the device to create a {@link LockdowndClient} for.
     * @param label the label to use for communication. Usually the program 
     *        name. Pass <code>null</code> to disable sending the label in 
     *        requests to lockdownd.
     * @param handshake <code>true</code> if initial handshake should be done.
     */
    public LockdowndClient(IDevice device, String label, boolean handshake) {
        if (device == null) {
            throw new NullPointerException("device");
        }
        LockdowndClientRefOut refOut = new LockdowndClientRefOut();
        try {
            checkResult(handshake ? 
                    LibIMobileDevice.lockdownd_client_new_with_handshake(device.getRef(), refOut, label) :
                    LibIMobileDevice.lockdownd_client_new(device.getRef(), refOut, label));
            this.ref = refOut.getValue();
        } finally {
            refOut.delete();
        }
    }
    
    protected LockdowndClientRef getRef() {
        checkDisposed();
        return ref;
    }
    
    /**
     * Requests to start a service.
     * 
     * @param identifier the name of the service to start.
     * @return the service descriptor.
     */
    public LockdowndServiceDescriptor startService(String identifier) {
        if (identifier == null) {
            throw new NullPointerException("identifier");
        }
        LockdowndServiceDescriptorStructOut serviceOut = new LockdowndServiceDescriptorStructOut();
        try {
            checkResult(LibIMobileDevice.lockdownd_start_service(getRef(), identifier, serviceOut));
            return new LockdowndServiceDescriptor(serviceOut.getValue());
        } finally {
            LockdowndServiceDescriptorStruct d = serviceOut.getValue();
            if (d != null) {
                LibIMobileDevice.lockdownd_service_descriptor_free(d);
            }
            serviceOut.delete();
        }
    }
    
    /**
     * Retrieves a preferences plist using an optional domain and/or key name.
     * 
     * @param domain the domain to query on or <code>null</code> for the global 
     *        domain.
     * @param key the key name to request or <code>null</code> to query for all 
     *        keys.
     * @return a plist node representing the result value node.
     */
    public NSObject getValue(String domain, String key) throws IOException {
        PlistRefOut plistOut = new PlistRefOut();
        try {
            checkResult(LibIMobileDevice.lockdownd_get_value(getRef(), domain, key, plistOut));
            PlistRef plist = plistOut.getValue();
            return PlistUtil.toJavaPlist(plist);
        } finally {
            plistOut.delete();
        }
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LibIMobileDeviceException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LibIMobileDevice.lockdownd_client_free(ref);
        ref = null;
    }
    
    @Override
    public void close() throws Exception {
        dispose();
    }

    private static void checkResult(int result) {
        switch (result) {
        case LOCKDOWN_E_SUCCESS: return;
        case LOCKDOWN_E_INVALID_ARG: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_INVALID_ARG");
        case LOCKDOWN_E_INVALID_CONF: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_INVALID_CONF");
        case LOCKDOWN_E_PLIST_ERROR: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_PLIST_ERROR");
        case LOCKDOWN_E_PAIRING_FAILED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_PAIRING_FAILED");
        case LOCKDOWN_E_SSL_ERROR: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_SSL_ERROR");
        case LOCKDOWN_E_DICT_ERROR: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_DICT_ERROR");
        case LOCKDOWN_E_START_SERVICE_FAILED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_START_SERVICE_FAILED");
        case LOCKDOWN_E_NOT_ENOUGH_DATA: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_NOT_ENOUGH_DATA");
        case LOCKDOWN_E_SET_VALUE_PROHIBITED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_SET_VALUE_PROHIBITED");
        case LOCKDOWN_E_GET_VALUE_PROHIBITED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_GET_VALUE_PROHIBITED");
        case LOCKDOWN_E_REMOVE_VALUE_PROHIBITED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_REMOVE_VALUE_PROHIBITED");
        case LOCKDOWN_E_MUX_ERROR: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_MUX_ERROR");
        case LOCKDOWN_E_ACTIVATION_FAILED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_ACTIVATION_FAILED");
        case LOCKDOWN_E_PASSWORD_PROTECTED: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_PASSWORD_PROTECTED");
        case LOCKDOWN_E_NO_RUNNING_SESSION: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_NO_RUNNING_SESSION");
        case LOCKDOWN_E_INVALID_HOST_ID: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_INVALID_HOST_ID");
        case LOCKDOWN_E_INVALID_SERVICE: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_INVALID_SERVICE");
        case LOCKDOWN_E_INVALID_ACTIVATION_RECORD: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_INVALID_ACTIVATION_RECORD");
        case LOCKDOWN_E_UNKNOWN_ERROR: throw new LibIMobileDeviceException(result, "LOCKDOWN_E_UNKNOWN_ERROR");
        default: throw new LibIMobileDeviceException(result);
        }
    }
}
