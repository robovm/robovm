/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.environment.EnvironmentUtils;

/**
 * @author niklas
 *
 */
public class CompilerUtil {

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
  
        ArrayList<String> opts = new ArrayList<String>();
        opts.add("-march=" + config.getArch().getLlvmName());
        if (config.getCpu() != null) {
            opts.add("-mcpu=" + config.getCpu());
        }
    
        outFile.getParentFile().mkdirs();
        exec(config, llcPath, opts, "-o=" + outFile.toString(), inFile);
    }
    
    public static void assemble(Config config, File inFile, File outFile) throws IOException {
        String gccPath = "gcc";
        if (config.getGccBinPath() != null) {
            gccPath = config.getGccBinPath().getAbsolutePath();
        }

        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (config.getOs() == OS.darwin) {
            opts.add("-arch");            
            opts.add(config.getArch().toString());            
        }

        outFile.getParentFile().mkdirs();
        exec(config, gccPath, "-c", "-o", outFile, opts, inFile);
    }
    
    public static int exec(Config config, String cmd, Object ... args) throws IOException {
        return exec(config, null, cmd, args);
    }
    
    public static int exec(Config config, File wd, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, null, cmd, args);
    }

    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, env, null, cmd, args);
    }
    
    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, CommandLine commandLine) throws IOException {
        return execWithEnv(config, wd, env, null, commandLine);
    }
    
    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, ExecuteStreamHandler streamHandler, 
            String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, env, streamHandler, createCommandLine(cmd, args));
    }
    
    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, ExecuteStreamHandler streamHandler, 
            CommandLine commandLine) throws IOException {
        
        config.getLogger().debug("  " + commandLine.toString());
        
        Executor executor = new DefaultExecutor();
        if (wd != null) {
            executor.setWorkingDirectory(wd);
        }
        if (streamHandler != null) {
            executor.setStreamHandler(streamHandler);
        }
        if (env == null) {
            env = EnvironmentUtils.getProcEnvironment();
        }
        executor.setExitValue(0);
        return executor.execute(commandLine, env);
    }

    @SuppressWarnings("unchecked")
    public static CommandLine createCommandLine(String cmd, Object... args) {
        CommandLine commandLine = CommandLine.parse(cmd);
        for (Object a : args) {
            if (a instanceof Collection) {
                for (Object o : (Collection<Object>) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString());
                }
            } else if (a instanceof Object[]) {
                for (Object o : (Object[]) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString());
                }
            } else {
                commandLine.addArgument(a instanceof File ? ((File) a).getAbsolutePath() : a.toString());
            }
        }
        return commandLine;
    }
}
