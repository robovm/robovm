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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.robovm.libimobiledevice.IDevice.EventListener;
import org.robovm.libimobiledevice.InstallationProxyClient.StatusCallback;
import org.robovm.libimobiledevice.binding.IDeviceEventType;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Internal class used to handle callbacks from native code. A callback is 
 * registered with a unique integer id in a map. The native code will call
 * the call* methods in this class and pass in the id. This class will then
 * look up the corresponding callback and call it.
 */
class Callbacks {

    static int nextId = 0;
    static HashMap<Integer, StatusCallback> instProxyStatusCallbacks = new HashMap<>();
    
    static synchronized int registerInstproxyCallback(StatusCallback callback) {
        int id = nextId;
        nextId++;
        instProxyStatusCallbacks.put(id, callback);
        return id;
    }
    
    static void callInstproxyCallback(String operation, byte[] statusPlistBin, int id) throws Exception {
        StatusCallback callback = null;
        synchronized (Callbacks.class) {
            callback = instProxyStatusCallbacks.get(id);
        }
        if (callback != null) {
            boolean done = false;
            try {
                NSDictionary dict = (NSDictionary) PropertyListParser.parse(statusPlistBin);
                NSObject status = dict.objectForKey("Status");
                NSObject percentComplete = dict.objectForKey("PercentComplete");
                NSObject error = dict.objectForKey("Error");
                if (error != null) {
                    done = true;
                    callback.error(error.toString());
                } else {
                    if ("Complete".equals(status.toString())) {
                        done = true;
                        callback.success();
                    } else {
                        callback.progress(status.toString(), ((NSNumber) percentComplete).intValue());
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                if (done) {
                    synchronized (Callbacks.class) {
                        instProxyStatusCallbacks.remove(id);
                    }
                }
            }
        }
    }
    
    static void callIDeviceEventCallback(int eventType, String udid) {
        List<EventListener> listeners = new ArrayList<>();
        synchronized (IDevice.listeners) {
            listeners.addAll(IDevice.listeners);
        }
        try {
            for (EventListener l : listeners) {
                if (eventType == IDeviceEventType.IDEVICE_DEVICE_ADD.swigValue()) {
                    l.deviceAdded(udid);
                } else {
                    l.deviceRemoved(udid);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
