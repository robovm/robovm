/**
 * 
 */
package org.nullvm.compiler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
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
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommand;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommandInjector;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIProxy;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIRecord;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIUserInteraction;


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
    protected CommandLine doGenerateCommandLine(LaunchParameters launchParameters) {
        File dir = getAppDir();
        
        String fruitstrapPath = "fruitstrap";
        if (fruitstrapBinPath != null) {
            fruitstrapPath = fruitstrapBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("-u");
        args.add("-d");
        
        if (!launchParameters.getArguments().isEmpty()) {
            args.add("--args");
            args.add(joinArgs(launchParameters.getArguments()));
        }

        args.add("-b");
        args.add(dir.getAbsolutePath());
        
        return CompilerUtil.createCommandLine(fruitstrapPath, args);
    }
    
    @Override
    protected void initStreams(AsyncExecutor executor,
            LaunchParameters launchParameters) throws IOException {
        
        OutputStream out = null;
        OutputStream err = null;
        if (launchParameters.getStdoutFifo() != null) {
            out = new FileOutputStream(launchParameters.getStdoutFifo());
        } else {
            out = new DebugOutputStream(config.getLogger());
        }
        if (launchParameters.getStderrFifo() != null) {
            err = new FileOutputStream(launchParameters.getStderrFifo());
        } else {
            err = new ErrorOutputStream(config.getLogger());
        }
        
        executor.setStreamHandler(new FruitstrapStreamHandler(out, err));
    }
    
    private String joinArgs(List<String> args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(StringUtils.quoteArgument(arg));
        }
        return sb.toString();
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
    
    private static class FruitstrapStreamHandler implements ExecuteStreamHandler {
        private PrintStream processIn;
        private InputStream processErr;
        private InputStream processOut;
        private Thread errThread;
        private Thread outThread;
        private final OutputStream out;
        private final OutputStream err;

        FruitstrapStreamHandler(OutputStream out, OutputStream err) {
            this.out = out;
            this.err = err;
        }
        
        @Override
        public void setProcessInputStream(OutputStream os) throws IOException {
            this.processIn = new PrintStream(os);
        }

        @Override
        public void setProcessErrorStream(InputStream is) throws IOException {
            this.processErr = is;
        }

        @Override
        public void setProcessOutputStream(InputStream is) throws IOException {
            this.processOut = is;
        }

        private String unescape(String s) {
            StringBuilder sb = new StringBuilder(s.length());
            boolean escaped = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (escaped) {
                    switch (c) {
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    default:
                        sb.append(c);
                        break;
                    }
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        
        private void startGdbProcessor(InputStream gdbStream) throws IOException {
            
            MICommandInjector injector = new MICommandInjector() {
                public void log(String data) {
                }
                public void inject(String data) {
                    processIn.print(data);
                    processIn.flush();
                }
            };

            MIProxy proxy = new MIProxy(injector, "(gdb)", System.getProperty("file.encoding")) {
                boolean started = false;
                @Override
                protected void targetStreamOutput(MIRecord record) {
                    String s = unescape(record.stream());
                    try {
                        out.write(s.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
//                @Override
//                protected void consoleStreamOutput(MIRecord record) {
//                    targetStreamOutput(record);
//                }
//                @Override
//                protected void logStreamOutput(MIRecord record) {
//                    consoleStreamOutput(record);
//                }
                @Override
                protected void prompt() {
                    if (!started) {
                        started = true;
                        send(new SimpleMICommand(0, "-exec-continue") {
                            @Override
                            protected void onStopped(MIRecord record) {
                                send(new SimpleMICommand(0, "-gdb-exit"));
                            }
                        });                        
                    }
                }
            };
        
            BufferedReader gdbReader = new BufferedReader(new InputStreamReader(gdbStream));
            String line = null;
            while ((line = gdbReader.readLine()) != null) {
                proxy.processLine(line);
            }
        }
        
        @Override
        public void start() throws IOException {
            if (processErr != null) {
                errThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            byte[] buf = new byte[4096];
                            int n;
                            while ((n = processErr.read(buf)) > 0) {
                                err.write(buf, 0, n);
                            }
                        } catch (IOException e) {
                        }
                    }
                };
                errThread.start();
            }
            if (processOut != null) {
                outThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            byte[] buf = new byte[4096];
                            int n = 0;
                            boolean gdb = false;
                            int dashes = 0;
                            outer: while ((n = processOut.read(buf)) > 0) {
                                for (int i = 0; i < n; i++) {
                                    if (buf[i] == '-') {
                                        dashes++;
                                    } else if (dashes == 25 && buf[i] == '\n') {
                                        // gdb started
                                        out.write(buf, 0, i + 1);
                                        n -= i + 1;
                                        if (n > 0) {
                                            System.arraycopy(buf, i + 1, buf, 0, n);
                                        }
                                        gdb = true;
                                        break outer;
                                    } else {
                                       dashes = 0;
                                    }
                                }
                                out.write(buf, 0, n);
                            }

                            if (gdb) {
                                startGdbProcessor(new SequenceInputStream(new ByteArrayInputStream(buf, 0, n), processOut));
                            }
                        
                        } catch (IOException e) {
                        }
                        
                    }
                };
                outThread.start();
            }
        }

        @Override
        public void stop() {
            if (errThread != null) {
                errThread.interrupt();
                try {
                    errThread.join();
                } catch (InterruptedException e) {
                }
            }
            if (outThread != null) {
                outThread.interrupt();
                try {
                    outThread.join();
                } catch (InterruptedException e) {
                }
            }
        }
        
    }
    
    private static class SimpleMICommand extends MICommand {

        public SimpleMICommand(int routingToken, String command) {
            super(routingToken, command);
        }

        @Override
        protected void onDone(MIRecord record) {
        }

        @Override
        protected void onRunning(MIRecord record) {
        }

        @Override
        protected void onError(MIRecord record) {
        }

        @Override
        protected void onExit(MIRecord record) {
        }

        @Override
        protected void onStopped(MIRecord record) {
        }

        @Override
        protected void onOther(MIRecord record) {
        }

        @Override
        protected void onUserInteraction(MIUserInteraction ui) {
        }
        
    }
}
