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
package org.robovm.compiler.target;

/**
 * {@link LaunchParameters} implementation used by {@link IOSSimulatorTarget}.
 */
public class IOSSimulatorLaunchParameters extends LaunchParameters {

    public static enum Family {iphone, ipad}
    
    private Family family = Family.iphone;
    private String sdk = null;
    
    public Family getFamily() {
        return family;
    }
    public void setFamily(Family family) {
        this.family = family;
    }
    public String getSdk() {
        return sdk;
    }
    public void setSdk(String sdk) {
        this.sdk = sdk;
    }
}
