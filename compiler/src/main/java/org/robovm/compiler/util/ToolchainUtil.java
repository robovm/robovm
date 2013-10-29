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
package org.robovm.compiler.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.log.Logger;

/**
 * @author niklas
 *
 */
public class ToolchainUtil {
    private static String IOS_DEV_CLANG; 
    private static String IOS_SIM_CLANG; 
    private static String PNGCRUSH;
    private static String PACKAGE_APPLICATION;

    private static String getIOSDevClang() throws IOException {
        if (IOS_DEV_CLANG == null) {
            IOS_DEV_CLANG = findXcodeCommand("clang", "iphoneos");
        }
        return IOS_DEV_CLANG;
    }
    
    private static String getIOSSimClang() throws IOException {
        if (IOS_SIM_CLANG == null) {
            IOS_SIM_CLANG = findXcodeCommand("clang", "iphonesimulator");
        }
        return IOS_SIM_CLANG;
    }
    
    private static String getPngCrush() throws IOException {
        if (PNGCRUSH == null) {
            PNGCRUSH = findXcodeCommand("pngcrush", "iphoneos");
        }
        return PNGCRUSH;
    }

    private static String getPackageApplication() throws IOException {
        if (PACKAGE_APPLICATION == null) {
            PACKAGE_APPLICATION = findXcodeCommand("PackageApplication", "iphoneos");
        }
        return PACKAGE_APPLICATION;
    }

    private static void handleExecuteException(ExecuteException e) {
        if (e.getExitValue() == 2) {
            throw new IllegalArgumentException("No Xcode is selected. Is Xcode installed? " 
                    + "If yes, use 'sudo xcode-select -switch <path-to-xcode>' from a Terminal " 
                    + "to switch to the correct Xcode path.");
        }
        if (e.getExitValue() == 69) {
            throw new IllegalArgumentException("You must agree to the Xcode/iOS license. " 
                    + "Please open Xcode once or run 'sudo xcrun clang' from a Terminal to agree to the terms.");
        }
        throw new IllegalArgumentException(e.getMessage());
    }
    
    public static String findXcodePath() throws IOException {
        try {
            String path = new Executor(Logger.NULL_LOGGER, "xcode-select").args("--print-path").execCapture();
            File f = new File(path);
            if (f.exists() && f.isDirectory()) {
                if (new File(f, "Platforms").exists() && new File(f, "Toolchains").exists()) {
                    return path;
                }
            }
            throw new IllegalArgumentException(String.format(
                    "The path '%s' does not appear to be a valid Xcode path. Use " 
                    + "'sudo xcode-select -switch <path-to-xcode>' from a Terminal " 
                    + "to switch to the correct Xcode path.", path));
        } catch (ExecuteException e) {
            handleExecuteException(e);
            return null;
        }
    }
    
    public static String findXcodeCommand(String cmd, String sdk) throws IOException {
        try {
            return new Executor(Logger.NULL_LOGGER, "xcrun").args("-sdk", sdk, "-f", cmd).execCapture();
        } catch (ExecuteException e) {
            handleExecuteException(e);
            return null;
        }
    }
    
    public static void pngcrush(Config config, File inFile, File outFile) throws IOException {
        new Executor(config.getLogger(), getPngCrush())
            .args("-q", "-iphone", "-f", "0", inFile, outFile)
            .exec();
    }

    public static void packageApplication(Config config, File appDir, File outFile) throws IOException {
        new Executor(config.getLogger(), getPackageApplication())
            .args(appDir, "-o", outFile)
            .exec();
    }

    public static void link(Config config, List<String> args, List<File> objectFiles, List<String> libs, File outFile) throws IOException {
        File objectsFile = new File(config.getTmpDir(), "objects");
        if (config.getOs().getFamily() == OS.Family.darwin) {
            // The Xcode linker doesn't need paths with spaces to be quoted and 
            // will fail if we do quote
            FileUtils.writeLines(objectsFile, objectFiles, "\n");
        } else {
            // The linker on Linux will fail if we don't quote paths with spaces
            BufferedOutputStream objectsOut = null;
            try {
                objectsOut = new BufferedOutputStream(new FileOutputStream(objectsFile));
                for (File f : objectFiles) {
                    objectsOut.write('"');
                    objectsOut.write(f.getAbsolutePath().getBytes());
                    objectsOut.write('"');
                    objectsOut.write('\n');
                }
            } finally {
                IOUtils.closeQuietly(objectsOut);
            }
        }
        
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

        new Executor(config.getLogger(), getCcPath(config))
            .args("-o", outFile, opts, libs)
            .exec();
    }

    private static String getCcPath(Config config) throws IOException {
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
        return ccPath;
    }
}
