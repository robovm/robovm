/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.plist.NSArray;
import net.sf.plist.NSBoolean;
import net.sf.plist.NSDictionary;
import net.sf.plist.NSObject;
import net.sf.plist.NSString;
import net.sf.plist.io.PropertyListException;
import net.sf.plist.io.PropertyListParser;
import net.sf.plist.io.PropertyListWriter;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;


/**
 * @author niklas
 *
 */
public class IOSDeviceTarget extends AbstractIOSTarget {

    private File fruitstrapBinPath;
    private File resourceRulesPList;
    private File entitlementsPList;
    private String signingIdentity = "iPhone Developer";
    
    IOSDeviceTarget() {
    }
 
    @Override
    protected List<SDK> getSDKs() {
        return listSDKs();
    }
    
    @Override
    protected void customizeInfoPList(Map<String, NSObject> dict) {
        dict.put("CFBundleSupportedPlatforms", new NSArray(Arrays.asList((NSObject) new NSString("iPhoneOS"))));
        dict.put("CFBundleResourceSpecification", new NSString("ResourceRules.plist"));
        dict.put("LSRequiresIPhoneOS", NSBoolean.TRUE);
    }

    @Override
    protected CommandLine doGenerateCommandLine(List<String> runArgs) {
        File dir = getAppDir();
        
        String fruitstrapPath = "fruitstrap";
        if (fruitstrapBinPath != null) {
            fruitstrapPath = fruitstrapBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("-u");
        args.add("-d");
        args.add("-b");
        args.add(dir.getAbsolutePath());
        
        return CompilerUtil.createCommandLine(fruitstrapPath,
                args.toArray(new Object[args.size()]));
    }
    
    @Override
    protected void prepareInstall(File installDir) throws IOException {
        super.prepareInstall(installDir);
        copyResourcesPList(installDir);
        codesign(signingIdentity, entitlementsPList, installDir);
    }

    @Override
    protected void prepareLaunch(File appDir) throws IOException {
        super.prepareLaunch(appDir);
        copyResourcesPList(appDir);
        codesign(signingIdentity, getEntitlementsPList(), appDir);
    }
    
    private void codesign(String identity, File entitlementsPList, File appDir) throws IOException {
        List<Object> args = new ArrayList<Object>();
        args.add("-f");
        args.add("-s");
        args.add(identity);
        if (entitlementsPList != null) {
            args.add("--entitlements");
            args.add(entitlementsPList);
        }
        args.add(appDir);
        CompilerUtil.exec(config, "codesign", args);        
    }
    
    private void copyResourcesPList(File destDir) throws IOException {
        File destFile = new File(destDir, "ResourceRules.plist");
        if (resourceRulesPList != null) {
            FileUtils.copyFile(resourceRulesPList, destFile);
        } else {
            FileUtils.copyURLToFile(getClass().getResource("/ResourceRules.plist"), destFile);
        }
    }
    
    private File getEntitlementsPList() throws IOException {
        try {
            File destFile = new File(config.getTmpDir(), "Entitlements.plist");
            if (entitlementsPList != null) {
                NSDictionary dict = (NSDictionary) PropertyListParser.parse(entitlementsPList);
                dict.getValue().put("get-task-allow", NSBoolean.TRUE);
                PropertyListWriter.write(dict, destFile);
            } else {
                FileUtils.copyURLToFile(getClass().getResource("/Entitlements.plist"), destFile);
            }
            return destFile;
        } catch (PropertyListException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static List<SDK> listSDKs() {
        try {
            List<SDK> sdks = new ArrayList<SDK>();
            File sdksDir = new File("/Developer/Platforms/iPhoneOS.platform/Developer/SDKs/");
            for (File root : sdksDir.listFiles()) {
                File settingsFile = new File(root, "SDKSettings.plist");
                if (settingsFile.exists()) {
                    NSDictionary settings = (NSDictionary) PropertyListParser.parse(settingsFile);
                    NSObject displayName = settings.get("DisplayName");
                    NSObject version = settings.get("Version");
                    if (displayName != null && version != null) {
                        sdks.add(new SDK(displayName.toString(), version.toString(), root));
                    }
                }
            }
            
            return sdks;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    public static class Builder extends AbstractIOSTarget.Builder {
        private final IOSDeviceTarget target;

        public Builder() {
            super(new IOSDeviceTarget());
            this.target = (IOSDeviceTarget) super.target;
        }
        
        public Builder fruitstrapBinPath(File fruitstrapBinPath) {
            target.fruitstrapBinPath = fruitstrapBinPath;
            return this;
        }

        public Builder resourceRulesPList(File resourceRulesPList) {
            target.resourceRulesPList = resourceRulesPList;
            return this;
        }
        
        public Builder entitlementsPList(File entitlementsPList) {
            target.entitlementsPList = entitlementsPList;
            return this;
        }
        
        public Builder signingIdentity(String signingIdentity) {
            target.signingIdentity = signingIdentity;
            return this;
        }
        
        public void setup(Config.Builder configBuilder) {
            super.setup(configBuilder);
        }
        
    }
}
