/*
 * Copyright (C) 2012 Trillian Mobile AB
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
    private static String PROPERTIES_RESOURCE = "/META-INF/robovm/version.properties";

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
            is = Version.class.getResourceAsStream(PROPERTIES_RESOURCE);
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
    static long toLong(String v) {
        String buildPart = "1";
        long buildType = 700;
        if (v.endsWith("-SNAPSHOT")) {
            buildPart = "";
            v = v.substring(0, v.indexOf("-SNAPSHOT"));
            buildType = 0;
        } else if (v.contains("-alpha-")) {
            buildPart = v.substring(v.lastIndexOf('-') + 1);
            v = v.substring(0, v.indexOf("-alpha-"));
            buildType = 100;
        } else if (v.contains("-beta-")) {
            buildPart = v.substring(v.lastIndexOf('-') + 1);
            v = v.substring(0, v.indexOf("-beta-"));
            buildType = 300;
        } else if (v.contains("-rc-")) {
            buildPart = v.substring(v.lastIndexOf('-') + 1);
            v = v.substring(0, v.indexOf("-rc-"));
            buildType = 500;
        }
        
        String[] parts = v.split("\\.");
        if (parts.length > 3) {
            throw new IllegalArgumentException("Illegal version number: " + v);
        }
        
        long major = parts.length > 0 ? Long.parseLong(parts[0]) : 0;
        long minor = parts.length > 1 ? Long.parseLong(parts[1]) : 0;
        long rev = parts.length > 2 ? Long.parseLong(parts[2]) : 0;
        long build = buildPart.isEmpty() ? 0 : Long.parseLong(buildPart);
        long result = (((major * 1000 + minor) * 1000 + rev) * 1000) + build + buildType;
        return result;
    }
    
    /**
     * Returns <code>true</code> if this version is less than the specified
     * version number.
     */
    public static boolean isOlderThan(String otherVersion) {
        return toLong(getVersion()) < toLong(otherVersion);
    }
    
    public static void main(String[] args) {
        System.out.println(toLong("1.0.0-alpha-01"));
    }
}
