/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * Reads the compiler version from auto generated <code>version.properties</code> file.
 */
public class Version {

    private static String version = null;

    /**
     * Returns the version number of the compiler by reading the <code>version.properties</code>
     * file.
     * 
     * @return the version.
     */
    public static String getVersion() {
        if (version != null) {
            return version;
        }
        InputStream is = null;
        try {
            is = Version.class.getResourceAsStream("/version.properties");
            Properties props = new Properties();
            props.load(is);
            version = props.getProperty("version");
            return version;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    /**
     * Converts a version string on the form x.y.z into an integer which can
     * be compared to other versions converted into integers.
     */
    private static int toInt(String v) {
        boolean snapshot = v.endsWith("-SNAPSHOT");
        if (snapshot) {
            v = v.substring(0, v.indexOf("-SNAPSHOT"));
        }
        
        String[] parts = v.split("\\.");
        if (parts.length > 3) {
            throw new IllegalArgumentException("Illegal version number: " + v);
        }
        
        int major = parts.length > 0 ? Integer.parseInt(parts[0]) : 0;
        int minor = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        int rev = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
        int result = major * 10000000 + minor * 10000 + rev * 10;
        if (!snapshot) {
            result += 1;
        }
        return result;
    }
    
    /**
     * Returns <code>true</code> if this version is less than the specified
     * version number.
     */
    public static boolean isOlderThan(String otherVersion) {
        return toInt(getVersion()) < toInt(otherVersion);
    }
}
