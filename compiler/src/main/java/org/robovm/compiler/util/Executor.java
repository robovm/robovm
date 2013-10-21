/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.compiler.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.exec.util.StringUtils;
import org.robovm.compiler.log.DebugOutputStream;
import org.robovm.compiler.log.ErrorOutputStream;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.target.Launcher;

/**
 * Builder style wrapper around <code>commons-exec</code> which also adds support for asynchronous 
 * execution.
 */
public class Executor implements Launcher {
    private final String cmd;
    private final Logger logger;
    private List<String> args = new ArrayList<String>();
    private Map<String, String> env = new HashMap<String, String>();
    private boolean inheritEnv = true;
    private File wd;
    private OutputStream out;
    private OutputStream err;
    private InputStream in;
    private boolean closeOutputStreams = false;
    private ExecuteStreamHandler streamHandler = null; 

    /**
     * Creates a new instance which will execute the specified command.
     * 
     * @param logger {@link Logger} used by this {@link Executor}.
     * @param cmd the command to be executed. Either the full path to an executable or the name of
     *            an executable which will be searched for in the search paths specified by the 
     *            <code>PATH</code> environment variable. 
     */
    public Executor(Logger logger, String cmd) {
        this.logger = logger;
        this.cmd = cmd;
    }
    
    /**
     * Creates a new instance which will execute the specified command.
     * 
     * @param logger {@link Logger} used by this {@link Executor}.
     * @param cmd the command to be executed. 
     */
    public Executor(Logger logger, File cmd) {
        this.logger = logger;
        this.cmd = cmd.getAbsolutePath();
    }
    
    /**
     * Adds arguments from the specified {@link Collection}. {@link File} arguments will be 
     * converted to absolute paths using {@link File#getAbsolutePath()}. All other types of args
     * will be converted to {@link String}s using {@link Object#toString()}.
     * 
     * @param args the arguments to add.
     * @return this {@link Executor}.
     */
    public Executor args(Collection<Object> args) {
        if (!args.isEmpty()) {
            return args(args.toArray(new Object[args.size()]));
        }
        return this;
    }
    
    /**
     * Adds one or more argument. {@link File} arguments will be converted to absolute paths using 
     * {@link File#getAbsolutePath()}. All other types of args will be converted to {@link String}s 
     * using {@link Object#toString()}.
     * 
     * @param args the argument(s) to add.
     * @return this {@link Executor}.
     */
    @SuppressWarnings("unchecked")
    public Executor args(Object ... args) {
        for (Object a : args) {
            if (a instanceof Collection) {
                args((Collection<Object>) a);
            } else if (a instanceof Object[]) {
                args((Object[]) a);
            } else {
                this.args.add(a instanceof File ? ((File) a).getAbsolutePath() : a.toString());
            }
        }
        return this;
    }
    
    /**
     * Sets the environment variables for the child process.
     * 
     * @param env the environment variables.
     * @return this {@link Executor}.
     */
    public Executor env(Map<String, String> env) {
        this.env = env;
        return this;
    }
    
    /**
     * Adds a single environment variable.
     * 
     * @param env the environment variables.
     * @return this {@link Executor}.
     */
    public Executor addEnv(String name, String value) {
        this.env.put(name, value);
        return this;
    }
    
    /**
     * Sets whether the parent's environment variables should be inherited by the child process.
     * Defaults to <code>true</code>.
     * 
     * @param b <code>true</code> or <code>false</code>.
     * @return this {@link Executor}.
     */
    public Executor inheritEnv(boolean b) {
        this.inheritEnv = b;
        return this;
    }
    
    /**
     * Sets the working directory of the child process. If not set the working directory will be
     * the same as the parent's.
     * 
     * @param wd the working directory.
     * @return this {@link Executor}.
     */
    public Executor wd(File wd) {
        this.wd = wd;
        return this;
    }
    
    /**
     * Redirects the stdout and stderr streams of the child process to the specified 
     * {@link OutputStream}. If not specified stdout and stderr will be inherited from the
     * parent process.
     * 
     * @param out the {@link OutputStream}.
     * @return this {@link Executor}.
     */
    public Executor errOut(OutputStream out) {
        this.out = out;
        this.err = out;
        return this;
    }
    
    /**
     * Redirects the stdout stream of the child process to the specified 
     * {@link OutputStream}. If not specified stdout will be inherited from the
     * parent process.
     * 
     * @param out the {@link OutputStream}.
     * @return this {@link Executor}.
     */
    public Executor out(OutputStream out) {
        this.out = out;
        return this;
    }
    
    /**
     * Redirects the stderr stream of the child process to the specified 
     * {@link OutputStream}. If not specified stderr will be inherited from the
     * parent process.
     * 
     * @param err the {@link OutputStream}.
     * @return this {@link Executor}.
     */
    public Executor err(OutputStream err) {
        this.err = err;
        return this;
    }
    
    /**
     * Uses the specified {@link InputStream} as the stdin stream for the child process.
     * 
     * @param in the {@link InputStream}.
     * @return this {@link Executor}.
     */
    public Executor in(InputStream in) {
        this.in = in;
        return this;
    }
    
    /**
     * Sets the {@link ExecuteStreamHandler} to be used by the underlying 
     * {@link org.apache.commons.exec.Executor}. If set any streams set by {@link #out(OutputStream)},
     * {@link #err(OutputStream)}, {@link #errOut(OutputStream)} or {@link #in(InputStream)} will be
     * ignored.
     * 
     * @param streamHandler the {@link ExecuteStreamHandler} to be used.
     * @return this {@link Executor}.
     */
    public Executor streamHandler(ExecuteStreamHandler streamHandler) {
        this.streamHandler = streamHandler;
        return this;
    }
    
    /**
     * Sets whether the stdout and stderr {@link OutputStream}s should be closed after the command
     * has finished.
     * 
     * @param b <code>true</code> or <code>false</code>.
     */
    public Executor closeOutputStreams(boolean b) {
        this.closeOutputStreams = b;
        return this;
    }
    
    private CommandLine generateCommandLine() {
        CommandLine commandLine = new CommandLine(cmd);
        for (String arg : args) {
            commandLine.addArgument(arg, false);
        }
        return commandLine;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> generateEnv() throws IOException {
        Map<String, String> mergedEnv = new HashMap<String, String>();
        if (inheritEnv) {
            mergedEnv.putAll(EnvironmentUtils.getProcEnvironment());
        }
        mergedEnv.putAll(env);
        return mergedEnv;
    }
    
    private <T extends org.apache.commons.exec.Executor> T initExecutor(T executor) {
        if (streamHandler == null) {
            OutputStream pumpOut = null;
            OutputStream pumpErr = null;
            InputStream pumpIn = null;
            if (out != null) {
                pumpOut = out;
            } else {
                pumpOut = new DebugOutputStream(logger);
            }
            if (err != null) {
                pumpErr = err;
            } else {
                pumpErr = new ErrorOutputStream(logger);
            }
            if (in != null) {
                pumpIn = in;
            }
            executor.setStreamHandler(new PumpStreamHandler(pumpOut, pumpErr, pumpIn) {
                @Override
                protected Thread createPump(InputStream is, OutputStream os,
                        boolean closeWhenExhausted) {
                    return super.createPump(is, os, closeOutputStreams ? true : closeWhenExhausted);
                }
            });
        } else {
            executor.setStreamHandler(streamHandler);
        }
        
        if (wd != null) {
            executor.setWorkingDirectory(wd);
        }
        executor.setExitValue(0);
        return executor;
    }
    
    private void logCommandLine(CommandLine commandLine) {
        if (logger == null) {
            return;
        }
        
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
            if (i<args.length-1) {
                result.append(' ');
            }
            
            if (i == args.length - 1 || result.length() > 2048) {
                logger.debug((first ? "" : "    ") + result.toString());
                result.delete(0, result.length());
                first = false;
            }
        }
    }
    
    public int exec() throws ExecuteException, IOException {
        CommandLine commandLine = generateCommandLine();
        logCommandLine(commandLine);
        try {
            return initExecutor(new DefaultExecutor()).execute(commandLine, generateEnv());
        } catch (ExecuteException e) {
            ExecuteException ex = new ExecuteException("Command '" + commandLine + "' failed ", 
                    e.getExitValue());
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        }
    }
    
    public Process execAsync() throws IOException {
        CommandLine commandLine = generateCommandLine();
        logCommandLine(commandLine);
        return initExecutor(new AsyncExecutor()).executeAsync(commandLine, generateEnv());
    }
    
    public String execCapture() throws IOException {
        ExecuteStreamHandler oldStreamHandler = streamHandler;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CommandLine commandLine = generateCommandLine();
        try {
            streamHandler(new PumpStreamHandler(baos));
            logCommandLine(commandLine);
            DefaultExecutor executor = initExecutor(new DefaultExecutor());
            executor.execute(commandLine, generateEnv());
            return new String(baos.toByteArray()).trim();
        } catch (ExecuteException e) {
            String output = new String(baos.toByteArray()).trim();
            if (output.length() > 0 && e.getMessage().startsWith("Process exited with an error")) {
                StackTraceElement[] origStackTrace = e.getStackTrace();
                e = new ExecuteException("Command '" + commandLine + "' failed with output: " 
                        + output + " ", e.getExitValue());
                e.setStackTrace(origStackTrace);
            }
            throw e;
        } finally {
            streamHandler = oldStreamHandler;
        }
    }
}
