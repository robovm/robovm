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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public enum OS {
    linux, macosx, ios;
    
    public enum Family {linux, darwin}
    
    private static Map<File, String> llvmHostStrings = new HashMap<File, String>();
    
    public Family getFamily() {
        return this == linux ? Family.linux : Family.darwin;
    }
    
    public static OS getDefaultOS(File llvmHomeDir) {
        String host = getHost(llvmHomeDir);
        if (host.contains("linux")) {
            return OS.linux;
        }
        if (host.contains("darwin") || host.contains("apple")) {
            return OS.macosx;
        }
        throw new IllegalArgumentException("Unrecognized OS in Host string: " + host);
    }
    
    @SuppressWarnings("rawtypes")
    static String getHost(File llvmHomeDir) {
        String llvmHostString = llvmHostStrings.get(llvmHomeDir);
        if (llvmHostString != null) {
            return llvmHostString;
        }
        String llcPath = "llc";
        if (llvmHomeDir != null) {
            llcPath = new File(new File(llvmHomeDir, "bin"), "llc").getAbsolutePath();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(baos);
        Executor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        CommandLine command = new CommandLine(llcPath).addArgument("--version");
        try {
            Map env = EnvironmentUtils.getProcEnvironment();
            executor.setExitValues(new int[] {0, 1});
            executor.execute(command, env);
            String output = new String(baos.toByteArray());
            Matcher m = Pattern.compile("(?m)(?:Host|Default target):\\s*(.*)$").matcher(output);
            if (m.find()) {
                llvmHostString = m.group(1).trim();
                llvmHostStrings.put(llvmHomeDir, llvmHostString);
                return llvmHostString;
            }
        } catch (IOException e) {
        }
        String msg = "Failed to determine OS and CPU architecture of current host system using ";
        if (llvmHomeDir != null) {
            msg += "LLVM in dir " + llvmHomeDir + ".";
        } else {
            msg += "system provided LLVM. Make sure the path of the LLVM binaries "
                 + "has been added to the $PATH environment variable.";
        }
        throw new CompilerException(msg);
    }
}
