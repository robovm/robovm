/*
 * Copyright (C) 2013 Trillian Mobile AB
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
package org.robovm.compiler.target.ios;

import org.robovm.compiler.target.LaunchParameters;
import org.robovm.libimobiledevice.util.AppPathCallback;

/**
 * {@link LaunchParameters} implementation used by {@link IOSTarget} when
 * launching on device. Also used to receive the remote app path from a device.
 */
public class IOSDeviceLaunchParameters extends LaunchParameters {
    private AppPathCallback appPathCallback;
    private String deviceId;
    private int forwardPort = -1;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getForwardPort() {
        return forwardPort;
    }

    public void setForwardPort(int forwardPort) {
        this.forwardPort = forwardPort;
    }

    public AppPathCallback getAppPathCallback() {
        return appPathCallback;
    }

    public void setAppPathCallback(AppPathCallback appPathCallback) {
        this.appPathCallback = appPathCallback;
    }
}
