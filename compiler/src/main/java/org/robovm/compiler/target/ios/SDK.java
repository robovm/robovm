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
package org.robovm.compiler.target.ios;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.util.Executor;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Contains info on an SDK installed on the system.
 */
public class SDK implements Comparable<SDK> {
    private final String displayName;
    private final String minimalDisplayName;
    private final String canonicalName;
    private final String version;
    private final File root;
    private final NSDictionary defaultProperties;
    private final int major;
    private final int minor;
    private final int revision;
    
    public static SDK create(File root) throws Exception {
        File settingsFile = new File(root, "SDKSettings.plist");
        if (settingsFile.exists()) {
            NSDictionary settings = (NSDictionary) PropertyListParser.parse(settingsFile);
            NSObject displayName = settings.objectForKey("DisplayName");
            NSObject minimalDisplayName = settings.objectForKey("MinimalDisplayName");
            NSObject canonicalName = settings.objectForKey("CanonicalName");
            NSObject version = settings.objectForKey("Version");
            NSDictionary defaultProperties = (NSDictionary) settings.objectForKey("DefaultProperties");
            if (displayName != null && version != null) {
                return new SDK(displayName.toString(), minimalDisplayName.toString(), 
                        canonicalName.toString(), version.toString(), root, defaultProperties);
            }
        }
        throw new IllegalArgumentException(root.getAbsolutePath() + " is not an SDK root path");
    }
    
    private static List<SDK> listSDKs(String platform) {
        try {
            List<SDK> sdks = new ArrayList<SDK>();
            List<File> sdksDirs = Arrays.asList(
                    new File(new Executor(Logger.NULL_LOGGER, "xcode-select").args("--print-path").execCapture() + "/Platforms/" + platform + ".platform/Developer/SDKs"),
                    new File("/Applications/Xcode.app/Contents/Developer/Platforms/" + platform + ".platform/Developer/SDKs"),
                    new File("/Developer/Platforms/" + platform + ".platform/Developer/SDKs"));
            for (File sdksDir : sdksDirs) {
                if (sdksDir.exists() && sdksDir.isDirectory()) {
                    for (File root : sdksDir.listFiles()) {
                        try {
                            sdks.add(SDK.create(root));
                        } catch (Exception e) {
                        }
                    }
                    if (!sdks.isEmpty()) {
                        return sdks;
                    }
                }
            }
            
            return sdks;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    public static List<SDK> listDeviceSDKs() {
        return listSDKs("iPhoneOS");
    }
    
    public static List<SDK> listSimulatorSDKs() {
        return listSDKs("iPhoneSimulator");
    }
    
    public SDK(String displayName, String minimalDisplayName, String canonicalName, String version, File root, NSDictionary defaultProperties) {
        super();
        this.displayName = displayName;
        this.minimalDisplayName = minimalDisplayName;
        this.canonicalName = canonicalName;
        this.version = version;
        this.root = root;
        this.defaultProperties = defaultProperties;
        
        String[] parts = StringUtils.split(version, ".");
        this.major = Integer.parseInt(parts[0]);
        this.minor = parts.length >= 2 ? Integer.parseInt(parts[1]) : 0;
        this.revision = parts.length >= 3 ? Integer.parseInt(parts[2]) : 0;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMinimalDisplayName() {
        return minimalDisplayName;
    }
    
    public String getCanonicalName() {
        return canonicalName;
    }
    
    public String getVersion() {
        return version;
    }

    public File getRoot() {
        return root;
    }

    public NSDictionary getDefaultProperties() {
        return defaultProperties;
    }
    
    public NSObject getDefaultProperty(String key) {
        return defaultProperties.objectForKey(key);
    }
    
    public int getMajor() {
        return major;
    }
    
    public int getMinor() {
        return minor;
    }
    
    public int getRevision() {
        return revision;
    }
    
    @Override
    public int compareTo(SDK o) {
        int c = major < o.major ? -1 : (major > o.major ? 1 : 0);
        if (c == 0) {
            c = minor < o.minor ? -1 : (minor > o.minor ? 1 : 0);
            if (c == 0) {
                c = revision < o.revision ? -1 : (revision > o.revision ? 1 : 0);                    
            }
        }
        return c;
    }
}