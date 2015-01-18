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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.config.Arch;
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
public class DeviceType implements Comparable<DeviceType> {
    public static final String PREFIX = "com.apple.CoreSimulator.SimDeviceType.";

    public static enum DeviceFamily {
        iPhone,
        iPad
    }

    private final String deviceName;
    private final SDK sdk;
    private final Set<Arch> archs;

    DeviceType(String deviceName, SDK sdk, Set<Arch> archs) {
        this.deviceName = deviceName;
        this.sdk = sdk;
        this.archs = archs;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public SDK getSdk() {
        return sdk;
    }

    public Set<Arch> getArchs() {
        return Collections.unmodifiableSet(archs);
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
        return deviceName.substring(PREFIX.length());
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
                tokens[2] = tokens[2].trim();
                SDK sdk = sdkMap.get(tokens[1]);
                if (sdk != null) {
                    Set<Arch> archs = new HashSet<>();
                    for (String s : tokens[2].replaceAll("[\\(\\)]", "").split(" ")) {
                        switch (s) {
                        case "i386":
                            archs.add(Arch.x86);
                            break;
                        case "x86_64":
                            archs.add(Arch.x86_64);
                            break;
                        }
                    }
                    types.add(new DeviceType(tokens[0], sdk, archs));
                }
            }
            // Sort. Make sure that devices that have an id which is a prefix of
            // another id comes before in the list.
            Collections.sort(types);
            return types;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int compareTo(DeviceType that) {
        int c = this.sdk.compareTo(that.sdk);
        if (c == 0) {
            c = this.getFamily().compareTo(that.getFamily());
            if (c == 0) {
                c = this.deviceName.compareToIgnoreCase(that.deviceName);
            }
        }
        return c;
    }
    
    private static List<DeviceType> filter(List<DeviceType> deviceTypes, Arch arch, 
            DeviceFamily family, String deviceName, String sdkVersion) {
        
        deviceName = deviceName == null ? null : deviceName.toLowerCase();
        
        List<DeviceType> result = new ArrayList<>();
        for (DeviceType type : deviceTypes) {
            if (arch == null || type.getArchs().contains(arch)) {
                if (family == null || family == type.getFamily()) {
                    if (deviceName == null || type.getSimpleDeviceName().toLowerCase().contains(deviceName)) {
                        if (sdkVersion == null || type.getSdk().getVersion().equals(sdkVersion)) {
                            result.add(type);
                        }
                    }
                }
            }
        }
        return result;
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

    public static DeviceType getBestDeviceType(Home home) {
        return getBestDeviceType(home, null, null, null, null);
    }

    public static DeviceType getBestDeviceType(Home home, DeviceFamily family) {
        return getBestDeviceType(home, null, family, null, null);
    }

    /**
     * Returns the best {@link DeviceType} matching the parameters. If multiple
     * device types match the parameters the first one with the highest SDK
     * number will be returned. If no device name and no {@link DeviceFamily} is
     * specified this method will default to {@link DeviceFamily#iPhone}.
     */
    public static DeviceType getBestDeviceType(Home home, Arch arch, DeviceFamily family,
            String deviceName, String sdkVersion) {

        if (deviceName == null && family == null) {
            family = DeviceFamily.iPhone;
        }
        
        DeviceType best = null;
        for (DeviceType type : filter(listDeviceTypes(home), arch, family, deviceName, sdkVersion)) {
            if (best == null) {
                best = type;
            } else if (type.getSdk().compareTo(best.getSdk()) > 0) {
                best = type;
            }
        }
        if (best == null) {
            throw new IllegalArgumentException("Unable to find a matching device " 
                    + "[arch=" + arch + ", family=" + family 
                    + ", name=" + deviceName + ", sdk=" + sdkVersion + "]");
        }
        return best;
    }

    @Override
    public String toString() {
        return "DeviceType [deviceName=" + deviceName + ", sdk=" + sdk + ", archs=" + archs + "]";
    }
}
