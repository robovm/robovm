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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.robovm.compiler.config.Config.Home;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.util.Executor;

/**
 * Simulator device types, consisting of the device type id and SDK version as
 * listed by ios-sim.
 * 
 * @author badlogic
 *
 */
public class DeviceType {
    public static final String PREFIX = "com.apple.CoreSimulator.SimDeviceType.";

    public static enum DeviceFamily {
        iPad,
        iPhone
    }

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

    public DeviceFamily getFamily() {
        if (deviceName.contains("iPhone")) {
            return DeviceFamily.iPhone;
        } else {
            return DeviceFamily.iPad;
        }
    }

    public static List<DeviceType> listDeviceTypes(Home home) {
        try {
            String capture = new Executor(Logger.NULL_LOGGER, new File(home.getBinDir(), "ios-sim")).args(
                    "showdevicetypes").execCapture();
            List<DeviceType> types = new ArrayList<DeviceType>();
            String[] deviceTypeIds = capture.split("\n");
            List<SDK> sdks = SDK.listSimulatorSDKs();
            Map<String, SDK> sdkMap = new HashMap<>();
            for (SDK sdk : sdks) {
                sdkMap.put(sdk.getVersion(), sdk);
            }
            for (String deviceTypeId : deviceTypeIds) {
                String[] tokens = deviceTypeId.split(",");
                tokens[0] = tokens[0].trim();
                tokens[1] = tokens[1].trim();
                SDK sdk = sdkMap.get(tokens[1]);
                if (sdk != null) {
                    types.add(new DeviceType(tokens[0], sdk));
                }
            }
            return types;
        } catch (IOException e) {
            return Collections.<DeviceType> emptyList();
        }
    }

    public static List<String> getSimpleDeviceTypeIds(Home home) {
        List<String> result = new ArrayList<>();
        for (DeviceType type : listDeviceTypes(home)) {
            result.add(type.getSimpleDeviceTypeId());
        }
        return result;
    }

    public static DeviceType getDeviceType(Home home, String deviceTypeId) {
        List<DeviceType> types = listDeviceTypes(home);
        if (deviceTypeId == null) {
            return null;
        }
        if (!deviceTypeId.startsWith(DeviceType.PREFIX)) {
            deviceTypeId = DeviceType.PREFIX + deviceTypeId;
        }
        for (DeviceType type : types) {
            if (deviceTypeId.equals(type.getDeviceTypeId())) {
                return type;
            }
        }
        return null;
    }

    /**
     * @return the iPhone {@link DeviceType} with the highest version number
     */
    public static DeviceType getBestDeviceType(Home home) {
        DeviceType best = null;
        for (DeviceType type : listDeviceTypes(home)) {
            if (type.getFamily() != DeviceFamily.iPhone) {
                continue;
            }
            if (best == null) {
                best = type;
            } else {
                int bestVersion = (best.getSdk().getMajor() << 8) | (best.getSdk().getMinor());
                int typeVersion = (type.getSdk().getMajor() << 8) | (type.getSdk().getMinor());
                if (bestVersion < typeVersion) {
                    best = type;
                }
            }
        }
        return best;
    }

    /**
     * @return the iPhone {@link DeviceType} with the highest version number
     */
    public static DeviceType getBestDeviceType(Home home, DeviceFamily family) {
        DeviceType best = null;
        for (DeviceType type : listDeviceTypes(home)) {
            if (type.getFamily() != family) {
                continue;
            }
            if (best == null) {
                best = type;
            } else {
                int bestVersion = (best.getSdk().getMajor() << 8) | (best.getSdk().getMinor());
                int typeVersion = (type.getSdk().getMajor() << 8) | (type.getSdk().getMinor());
                if (bestVersion < typeVersion) {
                    best = type;
                }
            }
        }
        return best;
    }
}
