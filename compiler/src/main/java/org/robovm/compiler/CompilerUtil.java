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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;

/**
 * @author niklas
 *
 */
public class CompilerUtil {
    private static String IOS_DEV_CLANG; 
    private static String IOS_SIM_CLANG; 

    private static String getIOSDevClang() throws IOException {
        if (IOS_DEV_CLANG == null) {
            IOS_DEV_CLANG = execCaptureOutput("xcrun", "-sdk", "iphoneos", "-f", "clang").trim();
        }
        return IOS_DEV_CLANG;
    }
    
    private static String getIOSSimClang() throws IOException {
        if (IOS_SIM_CLANG == null) {
            IOS_SIM_CLANG = execCaptureOutput("xcrun", "-sdk", "iphonesimulator", "-f", "clang").trim();
        }
        return IOS_SIM_CLANG;
    }
    
    public static void opt(Config config, File inFile, File outFile, String ... options) throws IOException {
        String optPath = "opt";
        if (config.getLlvmBinDir() != null) {
            optPath = new File(config.getLlvmBinDir(), "opt").getAbsolutePath();
        }

        outFile.getParentFile().mkdirs();
        exec(config, optPath, options, "-o=" + outFile.toString(), inFile);
    }
    
    public static void llc(Config config, File inFile, File outFile) throws IOException {
        String llcPath = "llc";
        if (config.getLlvmBinDir() != null) {
            llcPath = new File(config.getLlvmBinDir(), "llc").getAbsolutePath();
        }
  
        Arch arch = config.getArch();
        OS os = config.getOs();
        
        ArrayList<String> opts = new ArrayList<String>();
        opts.add("-mtriple=" + arch.getLlvmName() + "-unknown-" + os);
        if (os.getFamily() == OS.Family.darwin && arch.isArm()) {
            // clang uses gas to assemble files on macosx and the XCode gas doesn't handle cfi directives
            opts.add("-disable-cfi");
        }
        opts.add("-ffunction-sections");
        opts.add("-fdata-sections");
        opts.add("-disable-fp-elim");
    
        outFile.getParentFile().mkdirs();
        exec(config, llcPath, opts, "-o=" + outFile.toString(), inFile);
    }
    
    public static void assemble(Config config, File inFile, File outFile) throws IOException {
        String ccPath = config.getOs().getFamily() == OS.Family.darwin ? "clang" : "gcc";
        if (config.getCcBinPath() != null) {
            ccPath = config.getCcBinPath().getAbsolutePath();
        } else if (config.getOs() == OS.ios) {
            if (config.getArch() == Arch.x86) {
                ccPath = getIOSSimClang();
            } else {
                ccPath = getIOSDevClang();
            }
        }

        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (config.getOs().getFamily() == OS.Family.darwin) {
            opts.add("-arch");            
            opts.add(config.getArch().getClangName());
        } else {
            opts.add("-m32");            
        }

        outFile.getParentFile().mkdirs();
        exec(config, ccPath, "-c", "-o", outFile, opts, inFile);
    }
    
    public static void link(Config config, List<String> args, List<File> objectFiles, List<String> libs, File outFile) throws IOException {
        String ccPath = config.getOs().getFamily() == OS.Family.darwin ? "clang" : "gcc";
        if (config.getCcBinPath() != null) {
            ccPath = config.getCcBinPath().getAbsolutePath();
        } else if (config.getOs() == OS.ios) {
            if (config.getArch() == Arch.x86) {
                ccPath = getIOSSimClang();
            } else {
                ccPath = getIOSDevClang();
            }
        }

        File objectsFile = new File(config.getTmpDir(), "objects");
        FileUtils.writeLines(objectsFile, "UTF-8", objectFiles, "\n");
        
        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (config.getOs().getFamily() == OS.Family.darwin) {
            opts.add("-arch");            
            opts.add(config.getArch().getClangName());            
            opts.add("-Wl,-filelist," + objectsFile.getAbsolutePath());
        } else {
            opts.add("-m32");
            opts.add("@" + objectsFile.getAbsolutePath());
        }
        opts.addAll(args);

        CompilerUtil.exec(config, ccPath, "-o", outFile, opts, libs);
    }
    
    public static int exec(Config config, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, null, null, createCommandLine(cmd, args));
    }
    
    public static int exec(Config config, File wd, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, null, createCommandLine(cmd, args));
    }

    public static int execWithEnv(Config config, File wd, Map<String, String> env, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, env, createCommandLine(cmd, args));
    }
    
    @SuppressWarnings("unchecked")
    public static int execWithEnv(Config config, File wd, Map<String, String> env, CommandLine commandLine) throws IOException {
        
        debug(config.getLogger(), commandLine);
        
        Executor executor = new DefaultExecutor();
        if (wd != null) {
            executor.setWorkingDirectory(wd);
        }
        executor.setStreamHandler(new PumpStreamHandler(new DebugOutputStream(config.getLogger()), 
                new ErrorOutputStream(config.getLogger())));
        if (env == null) {
            env = EnvironmentUtils.getProcEnvironment();
        }
        executor.setExitValue(0);
        try {
            return executor.execute(commandLine, env);
        } catch (ExecuteException e) {
            ExecuteException ex = new ExecuteException("Command '" + commandLine + "' failed ", 
                    e.getExitValue());
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        }
    }

    public static String execCaptureOutput(String cmd, Object ... args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(baos);
        Executor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        CommandLine command = createCommandLine(cmd, args);
        executor.setExitValues(new int[] {0, 1});
        executor.execute(command, EnvironmentUtils.getProcEnvironment());
        return new String(baos.toByteArray());
    }
    
    static void debug(Logger logger, CommandLine commandLine) {
        String[] args = commandLine.getArguments();
        if (args.length == 0) {
            logger.debug(commandLine.toString());
            return;
        }
        
        StringBuilder result = new StringBuilder();

        result.append(StringUtils.quoteArgument(commandLine.getExecutable()));
        result.append(' ');

        boolean first = true;
        for (int i = 0; i < args.length; i++) {
            String currArgument = args[i];
            if( StringUtils.isQuoted(currArgument)) {
                result.append(currArgument);
            }
            else {
                result.append(StringUtils.quoteArgument(currArgument));
            }
            if(i<args.length-1) {
                result.append(' ');
            }
            
            if (i == args.length - 1 || result.length() > 2048) {
                logger.debug((first ? "" : "    ") + result.toString());
                result.delete(0, result.length());
                first = false;
            }
        }
    }
    
    public static CommandLine createCommandLine(String cmd, List<Object> args) {
        return createCommandLine(cmd, args.toArray(new Object[args.size()]));
    }
    
    @SuppressWarnings("unchecked")
    public static CommandLine createCommandLine(String cmd, Object... args) {
        CommandLine commandLine = new CommandLine(cmd);
        for (Object a : args) {
            if (a instanceof Collection) {
                for (Object o : (Collection<Object>) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString(), false);
                }
            } else if (a instanceof Object[]) {
                for (Object o : (Object[]) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString(), false);
                }
            } else {
                commandLine.addArgument(a instanceof File ? ((File) a).getAbsolutePath() : a.toString(), false);
            }
        }
        return commandLine;
    }
}
