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

/**
 * Simulator device types, consisting of the device type id and SDK version as
 * listed by ios-sim.
 * 
 * @author badlogic
 *
 */
public class DeviceType {
    public static final String PREFIX = "com.apple.CoreSimulator.SimDeviceType.";
    
    private final String deviceName;
    private final SDK sdk;

    public DeviceType(String deviceName, SDK sdk) {
        this.deviceName = deviceName;
        this.sdk = sdk;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public SDK getSdk() {
        return sdk;
    }

    /**
     * @return id as understood by ios-sim, concatentation of type and sdk
     *         version
     */
    public String getDeviceTypeId() {
        return deviceName + ", " + sdk.getVersion();
    }

    /**
     * @return id as understood by the AppCompiler -simdevicetype flag
     */
    public String getSimpleDeviceTypeId() {
        return getSimpleDeviceName() + ", " + sdk.getVersion();
    }

    public String getSimpleDeviceName() {
        return deviceName.substring("com.apple.CoreSimulator.SimDeviceType.".length());
    }

    public boolean isPhone() {
        return deviceName.contains("iPhone");
    }

    public boolean isIpad() {
        return deviceName.contains("iPad");
    }
}
