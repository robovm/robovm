/**
 * 
 */
package org.nullvm.compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.plist.NSArray;
import net.sf.plist.NSObject;
import net.sf.plist.NSString;

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
public class IOSSimulatorTarget extends AbstractIOSTarget {

    private File iosSimBinPath;
    private Family family = Family.iPhone;
    
    IOSSimulatorTarget() {
    }
 
    @Override
    protected List<SDK> getSDKs() {
        return listSDKs(iosSimBinPath);
    }
    
    @Override
    protected void customizeInfoPList(Map<String, NSObject> dict) {
        dict.put("CFBundlePackageType", new NSString("APPL"));
        dict.put("CFBundleSupportedPlatforms", new NSArray(Arrays.asList((NSObject) new NSString("iPhoneSimulator"))));
        dict.put("DTPlatformName", new NSString("iphonesimulator"));
    }
    
    @Override
    protected CommandLine doGenerateCommandLine(LaunchParameters launchParameters) {
        File dir = getAppDir();
        
        String iosSimPath = "ios-sim";
        if (iosSimBinPath != null) {
            iosSimPath = iosSimBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("launch");
        args.add(dir.getAbsolutePath());
        args.add("--unbuffered");
        if (sdk != null) {
            args.add("--sdk");
            args.add(sdk.getVersion());
        }
        args.add("--family");
        args.add(family.toString().toLowerCase());
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
        
        return CompilerUtil.createCommandLine(iosSimPath, args);
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
            throw new RuntimeException(t);
        }
        
        return sdks;
    }
    
    public static enum Family {iPhone, iPad}
    
    public static class Builder extends AbstractIOSTarget.Builder {
        private final IOSSimulatorTarget target;

        public Builder() {
            super(new IOSSimulatorTarget());
            this.target = (IOSSimulatorTarget) super.target;
        }
        
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
            configBuilder.arch(Arch.x86);
            super.setup(configBuilder);
        }
        
    }
}
