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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommand;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MICommandInjector;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIProxy;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIRecord;
import org.netbeans.modules.cnd.debugger.gdb2.mi.MIUserInteraction;
import org.robovm.compiler.io.OpenOnWriteFileOutputStream;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;


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
    public boolean canLaunchInPlace() {
        return false;
    }
    
    @Override
    protected List<SDK> getSDKs() {
        return listSDKs();
    }
    
    @Override
    protected void customizeInfoPList(NSDictionary dict) {
        dict.put("CFBundleResourceSpecification", "ResourceRules.plist");
        dict.put("CFBundleSupportedPlatforms", new NSArray(new NSString("iPhoneOS")));
    }

    @Override
    protected CommandLine doGenerateCommandLine(LaunchParameters launchParameters) {
        File dir = getAppDir();
        
        String fruitstrapPath = new File(config.getHome().getBinDir(), "fruitstrap").getAbsolutePath();
        if (fruitstrapBinPath != null) {
            fruitstrapPath = fruitstrapBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("--verbose");
        args.add("--unbuffered");
        args.add("--debug");
        args.add("--gdbargs");
        args.add("-i mi -q");
        args.add("--nostart");
        
        if (!launchParameters.getArguments().isEmpty()) {
            args.add("--args");
            args.add(joinArgs(launchParameters.getArguments()));
        }

        args.add("--bundle");
        args.add(dir.getAbsolutePath());
        
        return CompilerUtil.createCommandLine(fruitstrapPath, args);
    }
    
    @Override
    protected void initStreams(AsyncExecutor executor,
            LaunchParameters launchParameters) throws IOException {
        
        OutputStream fruitstrapOut = new DebugOutputStream(config.getLogger());
        OutputStream fruitstrapErr = new ErrorOutputStream(config.getLogger());
        OutputStream out = null;
        OutputStream err = null;
        if (launchParameters.getStdoutFifo() != null) {
            out = new OpenOnWriteFileOutputStream(launchParameters.getStdoutFifo());
        } else if (launchParameters.isRedirectStreamsToLogger()) {
            out = fruitstrapOut;
        } else {
            out = System.out;
        }
        if (launchParameters.getStderrFifo() != null) {
            err = new OpenOnWriteFileOutputStream(launchParameters.getStderrFifo());
        } else if (launchParameters.isRedirectStreamsToLogger()) {
            err = fruitstrapErr;
        } else {
            err = System.err;
        }
        
        executor.setStreamHandler(new FruitstrapStreamHandler(out, err, fruitstrapOut, fruitstrapErr));
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
        @SuppressWarnings("unchecked")
        Map<String, String> env = new HashMap<String, String>(EnvironmentUtils.getProcEnvironment());
        env.put("CODESIGN_ALLOCATE", CompilerUtil.execCaptureOutput("xcrun", "-sdk", "iphoneos", "-f", "codesign_allocate").trim());
        CompilerUtil.execWithEnv(config, null, env, "codesign", args);        
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
                dict.put("get-task-allow", true);
                PropertyListParser.saveAsXML(dict, destFile);
            } else {
                FileUtils.copyURLToFile(getClass().getResource("/Entitlements.plist"), destFile);
            }
            return destFile;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static List<SDK> listSDKs() {
        return SDK.listSDKs("iPhoneOS");
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
        private final OutputStream fruitstrapOut;
        private final OutputStream fruitstrapErr;

        FruitstrapStreamHandler(OutputStream out, OutputStream err, OutputStream fruitstrapOut, OutputStream fruitstrapErr) {
            this.out = out;
            this.err = err;
            this.fruitstrapOut = fruitstrapOut;
            this.fruitstrapErr = fruitstrapErr;
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
                        send(new SimpleMICommand(0, "-interpreter-exec console \"handle all nostop noprint\"") {
                            protected void onDone(MIRecord record) {
                                send(new SimpleMICommand(0, "-exec-continue") {
                                    @Override
                                    protected void onStopped(MIRecord record) {
                                        send(new SimpleMICommand(0, "-gdb-exit"));
                                    }
                                });
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
                                fruitstrapErr.write(buf, 0, n);
                            }
                        } catch (IOException e) {
                        }
                        
                        IOUtils.closeQuietly(processErr);
                        IOUtils.closeQuietly(fruitstrapErr);
                        IOUtils.closeQuietly(err);
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
                                        fruitstrapOut.write(buf, 0, i + 1);
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
                                fruitstrapOut.write(buf, 0, n);
                            }

                            if (gdb) {
                                startGdbProcessor(new SequenceInputStream(new ByteArrayInputStream(buf, 0, n), processOut));
                            }
                        
                        } catch (IOException e) {
                        }
                        
                        IOUtils.closeQuietly(processOut);
                        IOUtils.closeQuietly(fruitstrapOut);
                        IOUtils.closeQuietly(out);
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
