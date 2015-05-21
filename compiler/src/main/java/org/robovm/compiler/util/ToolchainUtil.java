/*
 * Copyright (C) 2012 RoboVM AB
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

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.tools.TextureAtlas;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.target.ios.IOSTarget;

/**
 * @author niklas
 *
 */
public class ToolchainUtil {
    private static String IOS_DEV_CLANG;
    private static String IOS_SIM_CLANG;
    private static String PNGCRUSH;
    private static String PLUTIL;
    private static String LIPO;
    private static String PACKAGE_APPLICATION;
    private static String TEXTUREATLAS;
    private static String ACTOOL;
    private static String IBTOOL;

    private static String getIOSDevClang() throws IOException {
        if (IOS_DEV_CLANG == null) {
            IOS_DEV_CLANG = findXcodeCommand("clang++", "iphoneos");
        }
        return IOS_DEV_CLANG;
    }

    private static String getIOSSimClang() throws IOException {
        if (IOS_SIM_CLANG == null) {
            IOS_SIM_CLANG = findXcodeCommand("clang++", "iphonesimulator");
        }
        return IOS_SIM_CLANG;
    }

    private static String getPngCrush() throws IOException {
        if (PNGCRUSH == null) {
            PNGCRUSH = findXcodeCommand("pngcrush", "iphoneos");
        }
        return PNGCRUSH;
    }

    private static String getTextureAtlas() throws IOException {
        if (TEXTUREATLAS == null) {
            TEXTUREATLAS = findXcodeCommand("TextureAtlas", "iphoneos");
        }
        return TEXTUREATLAS;
    }

    private static String getACTool() throws IOException {
        if (ACTOOL == null) {
            ACTOOL = findXcodeCommand("actool", "iphoneos");
        }
        return ACTOOL;
    }

    private static String getIBTool() throws IOException {
        if (IBTOOL == null) {
            IBTOOL = findXcodeCommand("ibtool", "iphoneos");
        }
        return IBTOOL;
    }

    private static String getPlutil() throws IOException {
        if (PLUTIL == null) {
            PLUTIL = findXcodeCommand("plutil", "iphoneos");
        }
        return PLUTIL;
    }

    private static String getLipo() throws IOException {
        if (LIPO == null) {
            LIPO = findXcodeCommand("lipo", "iphoneos");
        }
        return LIPO;
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
        new Executor(config.getLogger(), getPngCrush()).args("-q", "-iphone", "-f", "0", inFile, outFile).exec();
    }

    public static void textureatlas(Config config, File inDir, File outDir) throws IOException {
        List<String> opts = new ArrayList<String>();
        int outputFormat = 1;
        int maxTextureDimension = 1;

        if (config.getTools() != null && config.getTools().getTextureAtlas() != null) {
            TextureAtlas atlasConfig = config.getTools().getTextureAtlas();
            outputFormat = 1 + atlasConfig.getOutputFormat().ordinal();
            maxTextureDimension = 1 + atlasConfig.getMaximumTextureDimension().ordinal();

            if (atlasConfig.usePowerOfTwo()) {
                opts.add("-p");
            }
        }

        new Executor(config.getLogger(), getTextureAtlas()).args(opts, "-f", outputFormat, "-s", maxTextureDimension,
                inDir, outDir).exec();
    }

    public static void actool(Config config, File partialInfoPlist, File inDir, File outDir) throws IOException {
        List<Object> opts = new ArrayList<>();

        String appIconSetName = null;
        String launchImagesName = null;

        final String appiconset = "appiconset";
        final String launchimage = "launchimage";

        for (String fileName : inDir.list()) {
            String ext = FilenameUtils.getExtension(fileName);
            if (ext.equals(appiconset)) {
                appIconSetName = FilenameUtils.getBaseName(fileName);
            } else if (ext.equals(launchimage)) {
                launchImagesName = FilenameUtils.getBaseName(fileName);
            }
        }
        if (appIconSetName != null || launchImagesName != null) {
            if (appIconSetName != null) {
                opts.add("--app-icon");
                opts.add(appIconSetName);
            }
            if (launchImagesName != null) {
                opts.add("--launch-image");
                opts.add(launchImagesName);
            }

            opts.add("--output-partial-info-plist");
            opts.add(partialInfoPlist);
        }

        opts.add("--platform");
        if (IOSTarget.isDeviceArch(config.getArch())) {
            opts.add("iphoneos");
        } else if (IOSTarget.isSimulatorArch(config.getArch())) {
            opts.add("iphonesimulator");
        }

        String minOSVersion = config.getOs().getMinVersion();
        if (config.getIosInfoPList() != null) {
            String v = config.getIosInfoPList().getMinimumOSVersion();
            if (v != null) {
                minOSVersion = v;
            }
        }

        new Executor(config.getLogger(), getACTool()).args("--output-format", "human-readable-text", opts,
                "--minimum-deployment-target", minOSVersion, "--target-device", "iphone", "--target-device", "ipad",
                "--compress-pngs", "--compile", outDir, inDir).exec();
    }

    public static void ibtool(Config config, File partialInfoPlist, File inFile, File outFile) throws IOException {
        String minOSVersion = config.getOs().getMinVersion();
        if (config.getIosInfoPList() != null) {
            String v = config.getIosInfoPList().getMinimumOSVersion();
            if (v != null) {
                minOSVersion = v;
            }
        }

        Executor executor = new Executor(config.getLogger(), getIBTool()).args("--target-device", "iphone",
                "--target-device", "ipad", "--minimum-deployment-target", minOSVersion,
                "--output-partial-info-plist", partialInfoPlist, "--auto-activate-custom-fonts", "--output-format",
                "human-readable-text");
        if (outFile.isDirectory()) {
            executor.args("--compilation-directory", outFile);
        } else {
            executor.args("--compile", outFile);
        }
        executor.args(inFile).exec();
    }

    public static void compileStrings(Config config, File inFile, File outFile) throws IOException {
        new Executor(config.getLogger(), getPlutil()).args("-convert", "binary1", inFile, "-o", outFile).exec();
    }

    public static void lipo(Config config, File outFile, List<File> inFiles) throws IOException {
        new Executor(config.getLogger(), getLipo()).args(inFiles, "-create", "-output", outFile).exec();
    }

    public static void packageApplication(Config config, File appDir, File outFile) throws IOException {
        new Executor(config.getLogger(), getPackageApplication()).args(appDir, "-o", outFile).exec();
    }

    private static List<File> writeObjectsFiles(Config config, List<File> objectFiles, int maxObjectsPerFile,
            boolean quote) throws IOException {

        ArrayList<File> files = new ArrayList<>();
        for (int i = 0, start = 0; start < objectFiles.size(); i++, start += maxObjectsPerFile) {
            List<File> partition = objectFiles.subList(start, Math.min(objectFiles.size(), start + maxObjectsPerFile));
            List<String> paths = new ArrayList<>();
            for (File f : partition) {
                paths.add((quote ? "\"" : "") + f.getAbsolutePath() + (quote ? "\"" : ""));
            }

            File objectsFile = new File(config.getTmpDir(), "objects" + i);
            FileUtils.writeLines(objectsFile, paths, "\n");
            files.add(objectsFile);
        }

        return files;
    }

    public static void link(Config config, List<String> args, List<File> objectFiles, List<String> libs, File outFile)
            throws IOException {
        
        boolean isDarwin = config.getOs().getFamily() == OS.Family.darwin;
        /*
         * The Xcode linker doesn't need paths with spaces to be quoted and will
         * fail if we do quote. The Xcode linker will crash if we pass more than
         * 65535 files in an objects file.
         * 
         * The linker on Linux will fail if we don't quote paths with spaces.
         */
        List<File> objectsFiles = writeObjectsFiles(config, objectFiles, isDarwin ? 0xffff : Integer.MAX_VALUE,
                !isDarwin);

        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (isDarwin) {
            opts.add("-arch");
            opts.add(config.getArch().getClangName());
            for (File objectsFile : objectsFiles) {
                opts.add("-Wl,-filelist," + objectsFile.getAbsolutePath());
            }
        } else {
            opts.add(config.getArch().is32Bit() ? "-m32" : "-m64");
            for (File objectsFile : objectsFiles) {
                opts.add("@" + objectsFile.getAbsolutePath());
            }
        }
        opts.addAll(args);

        new Executor(config.getLogger(), getCcPath(config)).args("-o", outFile, opts, libs).exec();
    }

    private static String getCcPath(Config config) throws IOException {
        String ccPath = config.getOs().getFamily() == OS.Family.darwin ? "clang++" : "g++";
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
