/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.plist.NSDictionary;
import net.sf.plist.NSObject;
import net.sf.plist.NSString;
import net.sf.plist.io.PropertyListException;
import net.sf.plist.io.PropertyListParser;
import net.sf.plist.io.PropertyListWriter;
import net.sf.plist.io.PropertyListWriter.Format;

import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;


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
        prepareInstall(installDir);
        super.doInstall(installDir, getExecutable());
    }
    
    @Override
    protected int doLaunch(List<String> runArgs) throws IOException {
        prepareLaunch(getAppDir());
        return super.doLaunch(runArgs);
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
            NSObject bundleExecutable = infoPListDict.get("CFBundleExecutable");
            if (bundleExecutable != null) {
                return bundleExecutable.toString();
            }
        }
        return config.getExecutable();
    }

    protected void customizeInfoPList(Map<String, NSObject> dict) {
    }
    
    protected void createInfoPList(File dir) throws IOException {
        Map<String, NSObject> dict = null;
        if (infoPListDict != null) {
            dict = new HashMap<String, NSObject>(infoPListDict.getValue());
        } else {
            dict = new HashMap<String, NSObject>();
        }
        if (!dict.containsKey("CFBundleExecutable")) {
            dict.put("CFBundleExecutable", new NSString(config.getExecutable()));
        }
        if (!dict.containsKey("CFBundleName")) {
            dict.put("CFBundleName", new NSString(config.getExecutable()));
        }
        if (!dict.containsKey("CFBundleIdentifier")) {
            dict.put("CFBundleIdentifier", new NSString(config.getMainClass()));
        }
        customizeInfoPList(dict);

        // plist library doesn't support writing binary plist files yet. Write xml and convert using plutil.
        File tmpInfoPlistXml = new File(config.getTmpDir(), "Info.plist.xml");
        File tmpInfoPlist = new File(config.getTmpDir(), "Info.plist");
        try {
            PropertyListWriter.write(new NSDictionary(dict), tmpInfoPlistXml, Format.XML);
        } catch (PropertyListException e) {
            throw new CompilerException(e);
        } catch (ParserConfigurationException e) {
            throw new CompilerException(e);
        }
        
        CompilerUtil.exec(config, "plutil", "-convert", "binary1", "-o", tmpInfoPlist, tmpInfoPlistXml);
        
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
