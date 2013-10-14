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

import org.robovm.libimobiledevice.binding.LockdowndServiceDescriptorStruct;

/**
 * Service descriptor returned by {@link LockdowndClient#startService(String)}.
 */
public class LockdowndServiceDescriptor {
    private final int port;
    private final boolean sslEnabled;
    
    LockdowndServiceDescriptor(LockdowndServiceDescriptorStruct descriptor) {
        this.port = descriptor.getPort() & 0xffff;
        this.sslEnabled = descriptor.getSslEnabled();
    }

    public int getPort() {
        return port;
    }
    
    public boolean isSslEnabled() {
        return sslEnabled;
    }
}