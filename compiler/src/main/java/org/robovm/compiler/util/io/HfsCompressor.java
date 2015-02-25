/* Copyright (c) 2014 Reinhard Pointner, All Rights Reserved
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
package org.robovm.compiler.util.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;

/**
 * Takes a file and compresses it via the <code>libhfscompressor.dylib</code> in
 * <code>robovm/bin</code>. Used on object files.</p>
 * 
 * Compression can be disabled completely by setting the <code>ROBOVM_DISABLE_COMPRESSION</code>
 * environment variable.
 * 
 * @author badlogic
 *
 */
public class HfsCompressor {
    private static volatile boolean isLoaded = false;
    
    private static void loadLib(Config config) {
        if(!isLoaded) {
            String hfscompressorLib = new File(config.getHome().getBinDir(), "libhfscompressor.dylib").getAbsolutePath();
            System.load(hfscompressorLib);
            isLoaded = true;
        }
    }
    
    /**
     * Writes the given data to the file, trying to compress it
     * via HFS+ compression on Mac OS X. Simply writes the data
     * to the file on other systems.
     */
    public void compress(File file, byte[] data, Config config) throws IOException, InterruptedException {
        if (OS.getDefaultOS() == OS.macosx && System.getenv("ROBOVM_DISABLE_COMPRESSION") == null) {
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new CompilerException("Couldn't create directory for " + file.getAbsolutePath());
                }
            }
            loadLib(config);
            if(!compressNative(file.getAbsolutePath(), data, data.length)) {
                config.getLogger().debug("Couldn't compress file %s", file.getAbsolutePath());
                FileUtils.writeByteArrayToFile(file, data);
            }
        } else {
            FileUtils.writeByteArrayToFile(file, data);
        }
    }
    
    public static native boolean compressNative(String fileName, byte[] data, int len);
}
