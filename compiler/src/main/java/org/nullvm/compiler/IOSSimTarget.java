/**
 * 
 */
package org.nullvm.compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.plist.NSArray;
import net.sf.plist.NSDictionary;
import net.sf.plist.NSObject;
import net.sf.plist.NSString;
import net.sf.plist.io.PropertyListException;
import net.sf.plist.io.PropertyListWriter;
import net.sf.plist.io.PropertyListWriter.Format;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;


/**
 * @author niklas
 *
 */
public class IOSSimTarget extends AbstractTarget {

    private File iosSimBinPath;
    private SDK sdk;
    private Family family = Family.iPhone;
    
    IOSSimTarget() {
    }
    
    @Override
    protected CommandLine doGenerateCommandLine(List<String> runArgs) {
        File dir = config.getTmpDir();
        if (!config.isSkipInstall()) {
            dir = config.getInstallDir();
        }
        
        String iosSimPath = "ios-sim";
        if (iosSimBinPath != null) {
            iosSimPath = iosSimBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("launch");
        args.add(dir.getAbsolutePath());
        if (sdk != null) {
            args.add("--sdk");
            args.add(sdk.getVersion());
        }
        if (!runArgs.isEmpty()) {
            args.add("--args");
            args.addAll(runArgs);
        }
        args.add("--family");
        args.add(family.toString().toLowerCase());
        
        return CompilerUtil.createCommandLine(iosSimPath,
                args.toArray(new Object[args.size()]));
    }

    @Override
    protected void doBuild(String ccPath, File outFile, List<String> ccArgs,
            List<File> objectFiles, List<String> libArgs)
            throws IOException {

        ccArgs.add("-isysroot");
        if (sdk != null) {
            ccArgs.add(sdk.root.getAbsolutePath());
        } else {
            ccArgs.add(listSDKs(iosSimBinPath).get(0).root.getAbsolutePath());
        }
        super.doBuild(ccPath, outFile, ccArgs, objectFiles, libArgs);
    }
    
    @Override
    protected void doInstall(File installDir) throws IOException {
        createInfoPList();
        super.doInstall(installDir);
    }
    
    @Override
    protected int doLaunch(List<String> runArgs) throws IOException {
        createInfoPList();
        return super.doLaunch(runArgs);
    }
    
    private void createInfoPList() throws IOException {
        File dir = config.getTmpDir();
        if (!config.isSkipInstall()) {
            dir = config.getInstallDir();
        }

        Map<String, NSObject> dict = new HashMap<String, NSObject>();
        dict.put("CFBundleExecutable", new NSString(config.getExecutable()));
        dict.put("CFBundleDisplayName", new NSString(config.getExecutable()));
        dict.put("CFBundleName", new NSString(config.getExecutable()));
        dict.put("CFBundleIdentifier", new NSString(config.getMainClass()));
        dict.put("CFBundlePackageType", new NSString("APPL"));
        dict.put("CFBundleSupportedPlatforms", new NSArray(Arrays.asList((NSObject) new NSString("iPhoneSimulator"))));
        dict.put("CFBundleShortVersionString", new NSString("1.0"));
        dict.put("CFBundleVersion", new NSString("1.0"));
        dict.put("DTPlatformName", new NSString("iphonesimulator"));

        // plist library doesn't support writing binary plist files yet. Write xml and convert using plutil.
        File infoPlistXml = new File(dir, "Info.plist.xml");
        try {
            PropertyListWriter.write(new NSDictionary(dict), infoPlistXml, Format.XML);
        } catch (PropertyListException e) {
            throw new CompilerException(e);
        } catch (ParserConfigurationException e) {
            throw new CompilerException(e);
        }
        
        CompilerUtil.exec(config, "plutil", "-convert", "binary1", "-o", new File(dir, "Info.plist"), infoPlistXml);
    }
    
    public static List<SDK> listSDKs(File iosSimBinPath) {
        String iosSimPath = "ios-sim";
        if (iosSimBinPath != null) {
            iosSimPath = iosSimBinPath.getAbsolutePath();
        }
    
        List<SDK> sdks = new ArrayList<SDK>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(baos);
        Executor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        CommandLine command = new CommandLine(iosSimPath).addArgument("showsdks");
        try {
            @SuppressWarnings("rawtypes")
            Map env = EnvironmentUtils.getProcEnvironment();
            executor.setExitValues(new int[] {0, 1});
            executor.execute(command, env);
            String output = new String(baos.toByteArray());
            String[] lines = output.split("\n");
            for (int i = 1; i < lines.length; i += 2) {
                String nameVersion = lines[i];
                if (nameVersion.startsWith("[DEBUG]")) {
                    nameVersion = nameVersion.substring(7);
                }
                nameVersion = nameVersion.trim();
                String name = nameVersion.replaceAll(".*'([^']*)'.*", "$1");
                String version = nameVersion.replaceAll(".*\\(([^)]*)\\).*", "$1");
                String root = lines[i + 1].trim();
                sdks.add(new SDK(name, version , new File(root)));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        return sdks;
    }
    
    public static enum Family {iPhone, iPad}
    
    public static class SDK {
        private final String name;
        private final String version;
        private final File root;
        
        SDK(String name, String version, File root) {
            super();
            this.name = name;
            this.version = version;
            this.root = root;
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
    }
    
    public static class Builder implements Target.Builder {
        private IOSSimTarget target = new IOSSimTarget();

        public Builder iosSimBinPath(File iosSimBinPath) {
            target.iosSimBinPath = iosSimBinPath;
            return this;
        }
        
        public Builder sdk(SDK sdk) {
            target.sdk = sdk;
            return this;
        }
        
        public Builder family(Family family) {
            target.family = family;
            return this;
        }
        
        public void setup(Config.Builder configBuilder) {
            configBuilder.arch(Arch.i386);
            configBuilder.os(OS.ios);
        }
        
        public Target build(Config config) {
            target.config = config;
            return target.build(config);
        }
        
    }
}
