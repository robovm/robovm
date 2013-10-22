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
package org.robovm.compiler.target.ios;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.Resource;
import org.robovm.compiler.target.AbstractTarget;
import org.robovm.compiler.target.LaunchParameters;
import org.robovm.compiler.target.Launcher;
import org.robovm.compiler.util.Executor;
import org.robovm.compiler.util.ToolchainUtil;
import org.robovm.compiler.util.io.OpenOnWriteFileOutputStream;
import org.robovm.libimobiledevice.AfcClient.UploadProgressCallback;
import org.robovm.libimobiledevice.IDevice;
import org.robovm.libimobiledevice.InstallationProxyClient.StatusCallback;
import org.robovm.libimobiledevice.util.AppLauncher;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;
import com.dd.plist.XMLPropertyListParser;


/**
 * @author niklas
 *
 */
public class IOSTarget extends AbstractTarget {

    private Arch arch;
    private SDK sdk;
    private File infoPList = null;
    private NSDictionary infoPListDict = null;
    private File resourceRulesPList;
    private File entitlementsPList;
    private SigningIdentity signIdentity;
    private ProvisioningProfile provisioningProfile;
    
    public IOSTarget() {
    }
    
    @Override
    public Arch getArch() {
        return arch;
    }
    
    @Override
    public LaunchParameters createLaunchParameters() {
        if (arch == Arch.x86) {
            return new IOSSimulatorLaunchParameters();
        }
        return new IOSDeviceLaunchParameters();
    }
    
    public List<SDK> getSDKs() {
        if (arch == Arch.x86) {
            return SDK.listSimulatorSDKs();
        } else {
            return SDK.listDeviceSDKs();
        }
    }

    @Override
    protected Launcher createLauncher(LaunchParameters launchParameters) throws IOException {
        if (arch == Arch.x86) {
            return createIOSSimLauncher(launchParameters);
        } else {
            return createIOSDevLauncher(launchParameters);
        }
    }
    
    private Launcher createIOSSimLauncher(LaunchParameters launchParameters)
            throws IOException {

        File dir = getAppDir();
        
        String iosSimPath = new File(config.getHome().getBinDir(), "ios-sim").getAbsolutePath();
        
        List<Object> args = new ArrayList<Object>();
        args.add("launch");
        args.add(dir);
        args.add("--unbuffered");
        if (((IOSSimulatorLaunchParameters) launchParameters).getSdk() != null) {
            args.add("--sdk");
            args.add(((IOSSimulatorLaunchParameters) launchParameters).getSdk());
        }
        args.add("--family");
        args.add(((IOSSimulatorLaunchParameters) launchParameters).getFamily().toString().toLowerCase());
        if (launchParameters.getStdoutFifo() != null) {
            args.add("--stdout");
            args.add(launchParameters.getStdoutFifo());
        }
        if (launchParameters.getStderrFifo() != null) {
            args.add("--stderr");
            args.add(launchParameters.getStderrFifo());
        }
        if (!launchParameters.getArguments().isEmpty()) {
            args.add("--args");
            args.addAll(launchParameters.getArguments());
        }
        
        File xcodePath = new File(ToolchainUtil.findXcodePath());
        Map<String, String> env = Collections.singletonMap("DYLD_FRAMEWORK_PATH", 
                new File(xcodePath, "Platforms/iPhoneSimulator.platform/Developer/Library/PrivateFrameworks").getAbsolutePath() + ":" +
                new File(xcodePath, "../OtherFrameworks").getAbsolutePath());
        return new Executor(config.getLogger(), iosSimPath)
            .args(args)
            .wd(launchParameters.getWorkingDirectory())
            .inheritEnv(false)
            .env(env);
    }
    
    private Launcher createIOSDevLauncher(LaunchParameters launchParameters)
            throws IOException {
        
        String deviceId = ((IOSDeviceLaunchParameters) launchParameters).getDeviceId();
        if (deviceId == null) {
            String[] udids = IDevice.listUdids();
            if (udids.length == 0) {
                throw new RuntimeException("No devices connected");
            }
            if (udids.length > 1) {
                config.getLogger().warn("More than 1 device connected (%s). " 
                        + "Using %s.", Arrays.asList(udids), udids[0]);
            }
            deviceId = udids[0];
        }
        IDevice device = new IDevice(deviceId);
        
        OutputStream out = null;
        if (launchParameters.getStdoutFifo() != null) {
            out = new OpenOnWriteFileOutputStream(launchParameters.getStdoutFifo());
        } else {
            out = System.out;
        }
        
        Map<String, String> env = launchParameters.getEnvironment();
        if (env == null) {
            env = Collections.emptyMap();
        }
        
        AppLauncher launcher = new AppLauncher(device, getAppDir())
            .stdout(out)
            .closeOutOnExit(true)
            .args(launchParameters.getArguments().toArray(new String[0]))
            .env(env)
            .uploadProgressCallback(new UploadProgressCallback() {
                boolean first = true;
                public void success() {
                    config.getLogger().debug("[100%%] Upload complete");
                }
                public void progress(File path, int percentComplete) {
                    if (first) {
                        config.getLogger().debug("[  0%%] Beginning upload...");
                    }
                    first = false;
                    config.getLogger().debug("[%3d%%] Uploading %s...", percentComplete, path);
                }
                public void error(String message) {
                }
            })
            .installStatusCallback(new StatusCallback() {
                boolean first = true;
                public void success() {
                    config.getLogger().debug("[100%%] Install complete");
                }
                public void progress(String status, int percentComplete) {
                    if (first) {
                        config.getLogger().debug("[  0%%] Beginning installation...");
                    }
                    first = false;
                    config.getLogger().debug("[%3d%%] %s", percentComplete, status);
                }
                public void error(String message) {
                }
            });
        
        return new AppLauncherProcess(config.getLogger(), launcher, launchParameters);
    }

    @Override
    protected void doBuild(File outFile, List<String> ccArgs,
            List<File> objectFiles, List<String> libArgs)
            throws IOException {

        ccArgs.add("-miphoneos-version-min=5.0");
        ccArgs.add("-isysroot");
        ccArgs.add(sdk.getRoot().getAbsolutePath());
        super.doBuild(outFile, ccArgs, objectFiles, libArgs);
    }

    protected void prepareInstall(File installDir) throws IOException {
        createInfoPList(installDir);
        generateDsym(installDir, getExecutable());
        if (arch == Arch.thumbv7) {
            strip(installDir, getExecutable());
            copyResourcesPList(installDir);
            // Copy the provisioning profile
            config.getLogger().debug("Copying provisioning profile: %s (%s)", 
                    provisioningProfile.getName(), 
                    provisioningProfile.getEntitlements().objectForKey("application-identifier"));
            FileUtils.copyFile(provisioningProfile.getFile(), new File(installDir, "embedded.mobileprovision"));
            codesign(signIdentity, getOrCreateEntitlementsPList(false), installDir);
            // For some odd reason there needs to be a symbolic link in the root of
            // the app bundle named CodeResources pointing at _CodeSignature/CodeResources
            new Executor(config.getLogger(), "ln")
                .args("-f", "-s", "_CodeSignature/CodeResources", new File(installDir, "CodeResources"))
                .exec();
        }
    }
    
    protected void prepareLaunch(File appDir) throws IOException {
        super.doInstall(appDir, getExecutable());
        createInfoPList(appDir);
        generateDsym(appDir, getExecutable());
        if (arch == Arch.thumbv7) {
            copyResourcesPList(appDir);
            codesign(signIdentity, getOrCreateEntitlementsPList(true), appDir);
        }
    }
    
    private void codesign(SigningIdentity identity, File entitlementsPList, File appDir) throws IOException {
        List<Object> args = new ArrayList<Object>();
        args.add("-f");
        args.add("-s");
        args.add(identity.getName());
        if (entitlementsPList != null) {
            args.add("--entitlements");
            args.add(entitlementsPList);
        }
        args.add(appDir);
        new Executor(config.getLogger(), "codesign")
            .addEnv("CODESIGN_ALLOCATE", ToolchainUtil.findXcodeCommand("codesign_allocate", "iphoneos"))
            .args(args)
            .exec();
    }
    
    private void copyResourcesPList(File destDir) throws IOException {
        File destFile = new File(destDir, "ResourceRules.plist");
        if (resourceRulesPList != null) {
            FileUtils.copyFile(resourceRulesPList, destFile);
        } else {
            FileUtils.copyURLToFile(getClass().getResource("/ResourceRules.plist"), destFile);
        }
    }
    
    private File getOrCreateEntitlementsPList(boolean getTaskAllow) throws IOException {
        try {
            File destFile = new File(config.getTmpDir(), "Entitlements.plist");
            NSDictionary dict = null;
            if (entitlementsPList != null) {
                dict = (NSDictionary) PropertyListParser.parse(entitlementsPList);
            } else {
                dict = (NSDictionary) PropertyListParser.parse(IOUtils.toByteArray(getClass().getResourceAsStream("/Entitlements.plist")));
            }
            NSDictionary profileEntitlements = provisioningProfile.getEntitlements();
            for (String key : profileEntitlements.allKeys()) {
                if (dict.objectForKey(key) == null) {
                    dict.put(key, profileEntitlements.objectForKey(key));
                }
            }
            dict.put("application-identifier", provisioningProfile.getAppIdPrefix() + "." + getBundleId());
            dict.put("get-task-allow", getTaskAllow);
            PropertyListParser.saveAsXML(dict, destFile);
            return destFile;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void generateDsym(File dir, String executable) throws IOException {
        File dsymDir = new File(dir.getParentFile(), dir.getName() + ".dSYM");
        FileUtils.deleteDirectory(dsymDir);
        new Executor(config.getLogger(), "xcrun")
            .args("dsymutil", "-o", dsymDir, new File(dir, executable))
            .exec();
    }

    private void strip(File dir, String executable) throws IOException {
        new Executor(config.getLogger(), "xcrun")
            .args("strip", new File(dir, executable))
            .exec();
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

    public void createIpa() throws IOException {
        config.getLogger().debug("Creating IPA in %s", config.getInstallDir());
        config.getInstallDir().mkdirs();
        File tmpDir = new File(config.getInstallDir(), getExecutable() + ".app");
        FileUtils.deleteDirectory(tmpDir);
        tmpDir.mkdirs();
        super.doInstall(tmpDir, getExecutable());
        prepareInstall(tmpDir);
        ToolchainUtil.packageApplication(config, tmpDir, new File(config.getInstallDir(), getExecutable() + ".ipa"));
    }
    
    @Override
    protected void copyFile(Resource resource, File file, File destDir)
            throws IOException {
        
        if (arch == Arch.thumbv7 && !resource.isSkipPngCrush() 
                && file.getName().toLowerCase().endsWith(".png")) {
            
            destDir.mkdirs();
            File outFile = new File(destDir, file.getName());
            ToolchainUtil.pngcrush(config, file, outFile);
        } else {
            super.copyFile(resource, file, destDir);
        }
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

    protected String getBundleId() {
        if (infoPListDict != null) {
            NSObject bundleIdentifier = infoPListDict.objectForKey("CFBundleIdentifier");
            if (bundleIdentifier != null) {
                return bundleIdentifier.toString();
            }
        }
        return config.getMainClass() != null ? config.getMainClass() : config.getExecutableName();
    }
    
    protected void customizeInfoPList(NSDictionary dict) {
        if (arch == Arch.x86) {
            dict.put("CFBundleSupportedPlatforms", new NSArray(new NSString("iPhoneSimulator")));
        } else {
            dict.put("CFBundleResourceSpecification", "ResourceRules.plist");
            dict.put("CFBundleSupportedPlatforms", new NSArray(new NSString("iPhoneOS")));
            dict.put("DTPlatformVersion", sdk.getPlatformVersion());
            dict.put("DTPlatformBuild", sdk.getPlatformBuild());
            dict.put("DTSDKBuild", sdk.getBuild());
            // Validation fails without these. Let's pretend the app was built with Xcode 4.6.3
            dict.put("DTXcode", "0463");
            dict.put("DTXcodeBuild", "4H1503");
        }
    }
    
    protected void createInfoPList(File dir) throws IOException {
        NSDictionary dict = new NSDictionary();
        if (infoPListDict != null) {
            for (String key : infoPListDict.allKeys()) {
                dict.put(key, infoPListDict.objectForKey(key));
            }
        } else {
            dict.put("CFBundleVersion", "1.0");
            dict.put("CFBundleExecutable", config.getExecutableName());
            dict.put("CFBundleName", config.getExecutableName());
            dict.put("CFBundleIdentifier", getBundleId());
            dict.put("CFBundlePackageType", "APPL");
            dict.put("LSRequiresIPhoneOS", true);
            NSObject supportedDeviceFamilies = sdk.getDefaultProperty("SUPPORTED_DEVICE_FAMILIES");
            if (supportedDeviceFamilies != null) {
                // SUPPORTED_DEVICE_FAMILIES is either a NSString of comma separated numbers
                // or an NSArray with NSStrings. UIDeviceFamily values should be NSNumbers.
                NSArray families = null;
                if (supportedDeviceFamilies instanceof NSString) {
                    NSString defFamilies = (NSString) supportedDeviceFamilies;
                    String[] parts = defFamilies.toString().split(",");
                    families = new NSArray(parts.length);
                    for (int i = 0; i < families.count(); i++) {
                        families.setValue(i, new NSNumber(parts[i].trim()));
                    }
                } else {
                    NSArray defFamilies = (NSArray) supportedDeviceFamilies;
                    families = new NSArray(defFamilies.count());
                    for (int i = 0; i < families.count(); i++) {
                        families.setValue(i, new NSNumber(defFamilies.objectAtIndex(i).toString()));
                    }
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

        dict.put("DTPlatformName", sdk.getPlatformName());
        dict.put("DTSDKName", sdk.getCanonicalName());

        if (dict.objectForKey("MinimumOSVersion") == null) {
            // This is required
            dict.put("MinimumOSVersion", "5.0");
        }
        
        customizeInfoPList(dict);

        File tmpInfoPlist = new File(config.getTmpDir(), "Info.plist");
        PropertyListParser.saveAsBinary(dict, tmpInfoPlist);
        
        config.getLogger().debug("Installing Info.plist to %s", dir);
        FileUtils.copyFile(tmpInfoPlist, new File(dir, tmpInfoPlist.getName()));
    }
    
    public void init(Config config) {
        super.init(config);
        
        if (config.getArch() == null) {
            arch = Arch.thumbv7;
        } else {
            if (config.getArch() != Arch.x86 && config.getArch() != Arch.thumbv7) {
                throw new IllegalArgumentException("Arch '" + config.getArch() 
                        + "' is unsupported for iOS target");
            }
            arch = config.getArch();
        }

        if (arch == Arch.thumbv7) {
            signIdentity = config.getIosSignIdentity();
            if (signIdentity == null) {
                signIdentity = SigningIdentity.find(SigningIdentity.list(), "iPhone Developer");
            }
        }
        
        infoPList = config.getIosInfoPList();
        if (infoPList != null) {
            try {
                infoPListDict = (NSDictionary) parsePropertyList(infoPList, config.getProperties());
            } catch (Throwable t) {
                throw new IllegalArgumentException(t);
            }
        }

        if (arch == Arch.thumbv7) {
            provisioningProfile = config.getIosProvisioningProfile();
            if (provisioningProfile == null) {
                NSString bundleId = infoPListDict != null ? (NSString) infoPListDict.objectForKey("CFBundleIdentifier") : null;
                if (bundleId == null) {
                    bundleId = new NSString("*");
                }
                provisioningProfile = ProvisioningProfile.find(ProvisioningProfile.list(), signIdentity, bundleId.toString());
            }
        }

        String sdkVersion = config.getIosSdkVersion();
        List<SDK> sdks = getSDKs();
        if (sdkVersion == null) {
            if (sdks.isEmpty()) {
                throw new IllegalArgumentException("No " + (arch == Arch.thumbv7 ? "device" : "simulator") + " SDKs installed");
            }
            Collections.sort(sdks);
            this.sdk = sdks.get(sdks.size() - 1);
        } else {
            for (SDK sdk : sdks) {
                if (sdk.getVersion().equals(sdkVersion)) {
                    this.sdk = sdk;
                    break;
                }
            }
            if (sdk == null) {
                throw new IllegalArgumentException("No SDK found matching version string " + sdkVersion);
            }
        }
    }

    @Override
    public OS getOs() {
        return OS.ios;
    }

    @Override
    public boolean canLaunchInPlace() {
        return false;
    }
    
    private final static Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");
    
    static void replacePropertyRefs(Node node, Properties props) {
        if (node instanceof Text) {
            Text el = (Text) node;
            String value = el.getNodeValue();
            if (value != null && value.trim().length() > 0) {
                Matcher matcher = VARIABLE_PATTERN.matcher(value);
                StringBuilder sb = new StringBuilder();
                int pos = 0;
                while (matcher.find()) {
                    if (pos < matcher.start()) {
                        sb.append(value.substring(pos, matcher.start()));
                    }
                    String key = matcher.group(1);
                    sb.append(props.getProperty(key, matcher.group()));
                    pos = matcher.end();
                }
                if (pos < value.length()) {
                    sb.append(value.substring(pos));
                }
                el.setNodeValue(sb.toString());
            }
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            replacePropertyRefs(children.item(i), props);
        }
    }
    
    static NSObject parsePropertyList(File file, Properties props) throws Exception {
        Properties allProps = new Properties(System.getProperties());
        allProps.putAll(props);
        
        Method getDocBuilder = XMLPropertyListParser.class.getDeclaredMethod("getDocBuilder");
        getDocBuilder.setAccessible(true);
        Method parseDocument = XMLPropertyListParser.class.getDeclaredMethod("parseDocument", Document.class);
        parseDocument.setAccessible(true);
        DocumentBuilder docBuilder = (DocumentBuilder) getDocBuilder.invoke(null);
        Document doc = docBuilder.parse(file);
        replacePropertyRefs(doc, allProps);
        return (NSObject) parseDocument.invoke(null, doc);
    }
}
