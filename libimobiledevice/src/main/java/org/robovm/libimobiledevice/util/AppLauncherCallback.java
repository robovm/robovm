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
package org.robovm.libimobiledevice.util;

import org.robovm.libimobiledevice.IDevice;

/**
 * Can be set on {@link AppLauncher} so it returns the remote app path, product
 * version and build version after the application has been deployed to the device.
 * @author badlogic
 *
 */
public interface AppLauncherCallback {
    public void setAppLaunchInfo(AppLauncherInfo info);
    
    public byte[] filterOutput(byte[] data);
    
    static class AppLauncherInfo {
        final IDevice device;
        final String remoteAppPath;
        final String productVersion;
        final String buildVersion;
        
        public AppLauncherInfo(IDevice device, String remoteAppPath, String productVersion, String buildVersion) {
            this.device = device;
            this.remoteAppPath = remoteAppPath;
            this.productVersion = productVersion;
            this.buildVersion = buildVersion;
        }
        
        public IDevice getDevice() {
            return device;
        }

        public String getRemoteAppPath() {
            return remoteAppPath;
        }

        public String getProductVersion() {
            return productVersion;
        }

        public String getBuildVersion() {
            return buildVersion;
        }
    }
}
