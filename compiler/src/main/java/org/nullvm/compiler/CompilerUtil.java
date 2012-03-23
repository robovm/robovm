/**
 * 
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.exec.util.StringUtils;

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
        opts.add("-ffunction-sections");
        opts.add("-fdata-sections");
    
        outFile.getParentFile().mkdirs();
        exec(config, llcPath, opts, "-o=" + outFile.toString(), inFile);
    }
    
    public static void assemble(Config config, File inFile, File outFile) throws IOException {
        String ccPath = config.getOs().getFamily() == OS.Family.darwin ? "clang" : "gcc";
        if (config.getCcBinPath() != null) {
            ccPath = config.getCcBinPath().getAbsolutePath();
        }

        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (config.getOs().getFamily() == OS.Family.darwin) {
            opts.add("-arch");            
            opts.add(config.getArch().toString());            
        }

        outFile.getParentFile().mkdirs();
        exec(config, ccPath, "-c", "-o", outFile, opts, inFile);
    }
    
    public static int exec(Config config, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, null, null, createCommandLine(cmd, args));
    }
    
    public static int exec(Config config, File wd, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, null, createCommandLine(cmd, args));
    }

    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, String cmd, Object ... args) throws IOException {
        return execWithEnv(config, wd, env, createCommandLine(cmd, args));
    }
    
    @SuppressWarnings("rawtypes")
    public static int execWithEnv(Config config, File wd, Map env, CommandLine commandLine) throws IOException {
        
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

    private static void debug(Logger logger, CommandLine commandLine) {
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
    
    @SuppressWarnings("unchecked")
    public static CommandLine createCommandLine(String cmd, Object... args) {
        CommandLine commandLine = new CommandLine(cmd);
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
    
    private static class DebugOutputStream extends LoggerOutputStream {
        DebugOutputStream(Logger logger) {
            super(logger);
        }
        @Override
        protected void log(byte[] message, int off, int length) {
            logger.debug(new String(message, off, length));
        }
    }
    
    private static class ErrorOutputStream extends LoggerOutputStream {
        ErrorOutputStream(Logger logger) {
            super(logger);
        }
        @Override
        protected void log(byte[] message, int off, int length) {
            logger.error(new String(message, off, length));
        }
    }
    
    private static abstract class LoggerOutputStream extends OutputStream {
        protected final Logger logger;
        private byte[] buffer = new byte[1024];
        private int start = 0;
        private int end = 0;

        LoggerOutputStream(Logger logger) {
            this.logger = logger;
        }
        
        protected abstract void log(byte[] message, int off, int length);
        
        @Override
        public void write(int b) throws IOException {
            if (b == '\r') {
                // Skip
                return;
            }
            if (b == '\n') {
                log(buffer, start, end - start);
                start = end = 0;
                return;
            }
            if (end == buffer.length) {
                if (start > 0) {
                    // Compact
                    System.arraycopy(buffer, start, buffer, 0, end - start);
                    end -= start;
                    start = 0;
                } else {
                    // Need a bigger buffer
                    byte[] newBuffer = new byte[buffer.length * 2];
                    System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
                    buffer = newBuffer;
                }
            }
            buffer[end++] = (byte) b;
        }
        
    }
}
