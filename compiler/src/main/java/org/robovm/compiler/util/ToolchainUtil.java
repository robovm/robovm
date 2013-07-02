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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

    private static String getIOSDevClang() throws IOException {
        if (IOS_DEV_CLANG == null) {
            IOS_DEV_CLANG = new Executor(Logger.NULL_LOGGER, "xcrun").args("-sdk", "iphoneos", "-f", "clang").execCapture();
        }
        return IOS_DEV_CLANG;
    }
    
    private static String getIOSSimClang() throws IOException {
        if (IOS_SIM_CLANG == null) {
            IOS_SIM_CLANG = new Executor(Logger.NULL_LOGGER, "xcrun").args("-sdk", "iphonesimulator", "-f", "clang").execCapture();
        }
        return IOS_SIM_CLANG;
    }
    
    public static void link(Config config, List<String> args, List<File> objectFiles, List<String> libs, File outFile) throws IOException {
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
