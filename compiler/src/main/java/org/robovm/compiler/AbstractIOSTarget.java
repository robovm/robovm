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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.simpleframework.xml.Transient;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;


/**
 * @author niklas
 *
 */
public abstract class AbstractIOSTarget extends AbstractTarget {

    protected SDK sdk;
    protected File infoPList = null;
    @Transient
    protected NSDictionary infoPListDict = null;
    
    AbstractIOSTarget() {
    }
    
    protected abstract List<SDK> getSDKs();

    @Override
    protected void doBuild(File outFile, List<String> ccArgs,
            List<File> objectFiles, List<String> libArgs)
            throws IOException {

        ccArgs.add("-miphoneos-version-min=3.0");
        ccArgs.add("-isysroot");
        ccArgs.add(sdk.getRoot().getAbsolutePath());
        super.doBuild(outFile, ccArgs, objectFiles, libArgs);
    }

    protected void prepareInstall(File installDir) throws IOException {
        createInfoPList(installDir);
        generateDsym(installDir, getExecutable());
    }
    
    protected void prepareLaunch(File appDir) throws IOException {
        super.doInstall(appDir, getExecutable());
        createInfoPList(appDir);
        generateDsym(appDir, getExecutable());
    }
    
    private void generateDsym(File dir, String executable) throws IOException {
        File dsymDir = new File(dir.getParentFile(), dir.getName() + ".dSYM");
        FileUtils.deleteDirectory(dsymDir);
        CompilerUtil.exec(config, "xcrun", "dsymutil", "-o", dsymDir, new File(dir, executable));
    }

    @Override
    protected void doInstall(File installDir, String executable) throws IOException {
        super.doInstall(installDir, getExecutable());
        prepareInstall(installDir);
    }
    
    @Override
    protected Process doLaunch(LaunchParameters launchParameters) throws IOException {
        prepareLaunch(getAppDir());
        return super.doLaunch(launchParameters);
    }

    protected File getAppDir() {
        File dir = null;
        if (!config.isSkipInstall()) {
            dir = config.getInstallDir();
        } else {
            dir = new File(config.getTmpDir(), getExecutable() + ".app");
            dir.mkdirs();
        }
        return dir;
    }
    
    protected String getExecutable() {
        if (infoPListDict != null) {
            NSObject bundleExecutable = infoPListDict.objectForKey("CFBundleExecutable");
            if (bundleExecutable != null) {
                return bundleExecutable.toString();
            }
        }
        return config.getExecutableName();
    }

    protected void customizeInfoPList(NSDictionary dict) {
    }
    
    protected void createInfoPList(File dir) throws IOException {
        NSDictionary dict = new NSDictionary();
        if (infoPListDict != null) {
            for (String key : infoPListDict.allKeys()) {
                dict.put(key, infoPListDict.objectForKey(key));
            }
        } else {
            dict.put("CFBundleExecutable", config.getExecutableName());
            dict.put("CFBundleName", config.getExecutableName());
            String identifier = config.getMainClass() != null ? config.getMainClass() : config.getExecutableName();
            dict.put("CFBundleIdentifier", identifier);
            dict.put("CFBundlePackageType", "APPL");
            dict.put("LSRequiresIPhoneOS", true);
            if (sdk.getDefaultProperty("SUPPORTED_DEVICE_FAMILIES") != null) {
                // Values in SUPPORTED_DEVICE_FAMILIES are NSStrings while UIDeviceFamily
                // values should be NSNumbers.
                NSArray defFamilies = (NSArray) sdk.getDefaultProperty("SUPPORTED_DEVICE_FAMILIES");
                NSArray families = new NSArray(defFamilies.count());
                for (int i = 0; i < families.count(); i++) {
                    families.setValue(i, new NSNumber(defFamilies.objectAtIndex(i).toString()));
                }
                dict.put("UIDeviceFamily", families);
            }
            dict.put("UISupportedInterfaceOrientations", new NSArray(
                    new NSString("UIInterfaceOrientationPortrait"),
                    new NSString("UIInterfaceOrientationLandscapeLeft"),
                    new NSString("UIInterfaceOrientationLandscapeRight"),
                    new NSString("UIInterfaceOrientationPortraitUpsideDown")
            ));
            dict.put("UISupportedInterfaceOrientations~ipad", new NSArray(
                    new NSString("UIInterfaceOrientationPortrait"),
                    new NSString("UIInterfaceOrientationLandscapeLeft"),
                    new NSString("UIInterfaceOrientationLandscapeRight"),
                    new NSString("UIInterfaceOrientationPortraitUpsideDown")
            ));
            dict.put("UIRequiredDeviceCapabilities", new NSArray(new NSString("armv7")));
        }

        dict.put("DTPlatformName", sdk.getDefaultProperty("PLATFORM_NAME"));
        dict.put("DTPlatformVersion", sdk.getVersion());
        dict.put("DTSDKName", sdk.getCanonicalName());
        
        customizeInfoPList(dict);

        File tmpInfoPlist = new File(config.getTmpDir(), "Info.plist");
        PropertyListParser.saveAsBinary(dict, tmpInfoPlist);
        
        config.getLogger().debug("Installing Info.plist to %s", dir);
        FileUtils.copyFile(tmpInfoPlist, new File(dir, tmpInfoPlist.getName()));
    }
    
    public void setSDK(SDK sdk) {
        this.sdk = sdk;
    }

    public void setInfoPList(File infoPList) {
        this.infoPList = infoPList;
    }
    
    public void init(Config config) {
        super.init(config);
        if (this.infoPList != null) {
            try {
                this.infoPListDict = (NSDictionary) PropertyListParser.parse(this.infoPList);
            } catch (Throwable t) {
                throw new IllegalArgumentException(t);
            }
        }
        if (this.sdk == null) {
            List<SDK> sdks = getSDKs();
            Collections.sort(sdks);
            this.sdk = sdks.get(sdks.size() - 1);
        }
    }
    
    public static class SDK implements Comparable<SDK> {
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
        
        public static List<SDK> listSDKs(String platform) {
            try {
                List<SDK> sdks = new ArrayList<SDK>();
                List<File> sdksDirs = Arrays.asList(
                        new File(CompilerUtil.execCaptureOutput("xcode-select", "--print-path") + "/Platforms/" + platform + ".platform/Developer/SDKs"),
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
        
        SDK(String displayName, String minimalDisplayName, String canonicalName, String version, File root, NSDictionary defaultProperties) {
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
}
