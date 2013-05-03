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
import java.util.Collections;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;


/**
 * @author niklas
 *
 */
public abstract class AbstractIOSTarget extends AbstractTarget {

    protected SDK sdk;
    protected File infoPList = null;
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
        if (sdk != null) {
            ccArgs.add(sdk.getRoot().getAbsolutePath());
        } else {
            List<SDK> sdks = getSDKs();
            Collections.sort(sdks);
            ccArgs.add(sdks.get(sdks.size() - 1).getRoot().getAbsolutePath());
        }
        super.doBuild(outFile, ccArgs, objectFiles, libArgs);
    }

    protected void prepareInstall(File installDir) throws IOException {
        createInfoPList(installDir);
    }
    
    protected void prepareLaunch(File appDir) throws IOException {
        super.doInstall(appDir, getExecutable());
        createInfoPList(appDir);
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
        return config.getExecutable();
    }

    protected void customizeInfoPList(NSDictionary dict) {
    }
    
    protected void createInfoPList(File dir) throws IOException {
        NSDictionary dict = new NSDictionary();
        if (infoPListDict != null) {
            for (String key : infoPListDict.allKeys()) {
                dict.put(key, infoPListDict.objectForKey(key));
            }
        }
        if (dict.objectForKey("CFBundleExecutable") == null) {
            dict.put("CFBundleExecutable", config.getExecutable());
        }
        if (dict.objectForKey("CFBundleName") == null) {
            dict.put("CFBundleName", config.getExecutable());
        }
        if (dict.objectForKey("CFBundleIdentifier") == null) {
            String value = config.getMainClass() != null ? config.getMainClass() : config.getExecutable();
            dict.put("CFBundleIdentifier", value);
        }
        customizeInfoPList(dict);

        File tmpInfoPlist = new File(config.getTmpDir(), "Info.plist");
        PropertyListParser.saveAsBinary(dict, tmpInfoPlist);
        
        config.getLogger().debug("Installing Info.plist to %s", dir);
        FileUtils.copyFile(tmpInfoPlist, new File(dir, tmpInfoPlist.getName()));
    }
    
    public static class Builder implements Target.Builder {
        protected final AbstractIOSTarget target;
        
        protected Builder(AbstractIOSTarget target) {
            this.target = target;
        }

        public Builder sdk(SDK sdk) {
            target.sdk = sdk;
            return this;
        }

        public Builder infoPList(File infoPList) {
            target.infoPList = infoPList;
            return this;
        }
        
        public void setup(Config.Builder configBuilder) {
            configBuilder.os(OS.ios);
        }
        
        public Target build(Config config) {
            target.config = config;
            if (target.infoPList != null) {
                try {
                    target.infoPListDict = (NSDictionary) PropertyListParser.parse(target.infoPList);
                } catch (Throwable t) {
                    throw new IllegalArgumentException(t);
                }
            }
            return target.build(config);
        }
    }
    
    public static class SDK implements Comparable<SDK> {
        private final String name;
        private final String version;
        private final File root;
        private final int major;
        private final int minor;
        private final int revision;
        
        SDK(String name, String version, File root) {
            super();
            this.name = name;
            this.version = version;
            this.root = root;
            
            String[] parts = StringUtils.split(version, ".");
            this.major = Integer.parseInt(parts[0]);
            this.minor = parts.length >= 2 ? Integer.parseInt(parts[1]) : 0;
            this.revision = parts.length >= 3 ? Integer.parseInt(parts[2]) : 0;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        public File getRoot() {
            return root;
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
