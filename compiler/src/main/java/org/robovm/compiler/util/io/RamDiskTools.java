/*
 * Copyright (C) 2012 Trillian Mobile AB
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
package org.robovm.compiler.util.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.config.Config.Builder;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.util.Executor;

/**
 * Will modify cache and tmpdir paths given a {@link Config#builder()} and prun
 * the cache if necessary.
 * 
 * @author badlogic
 *
 */
public class RamDiskTools {
    private static final String ROBOVM_RAM_DISK_PATH = "/Volumes/RoboVM RAM Disk";
    private static final long MIN_FREE_SPACE = 1024 * 1024 * 200;

    /**
     * Checks if a RAM disk is available, pruns it if necessary and rewires the
     * {@link Builder} accordingly.
     * 
     * @param builder
     */
    public void setupRamDisk(Config.Builder builder, Config config) {
        if (OS.getDefaultOS() != OS.macosx) {
            return;
        }

        File volume = new File(ROBOVM_RAM_DISK_PATH);
        if (!volume.exists()) {
            try {
                FileStore store = Files.getFileStore(new File(System.getProperty("user.home"), ".robovm/cache")
                        .toPath());
                String plist = new Executor(Logger.NULL_LOGGER, "diskutil").args("info", "-plist", store.name())
                        .execCapture();
                if (!plist.contains("<key>SolidState</key>")) {
                    // @formatter:off
                    config.getLogger().warn("RoboVM has detected that you are running on a slow HDD. Please consider mounting a RAM disk.\n" +
                                            "To create a 2GB RAM disk, run this in your terminal:\n" +
                                            "SIZE=2048 ; diskutil erasevolume HFS+ 'RoboVM RAM Disk' `hdiutil attach -nomount ram://$((SIZE * 2048))`\n" +
                                            "See http://docs.robovm.com/ for more info");
                    // @formatter:on
                }
            } catch (Throwable t) {
                // nothing to do here, can't decide if we are on a SSD or HDD
                t.printStackTrace();
            }
            return;
        }

        File cacheDir = config.getCacheDir();
        File tmpDir = config.getTmpDir();
        try {
            FileStore store = Files.getFileStore(volume.toPath());
            if (store.getUsableSpace() < MIN_FREE_SPACE) {
                cleanRamDisk(store, volume);
                if (store.getUsableSpace() < MIN_FREE_SPACE) {
                    config.getLogger().debug("Couldn't free enough space on RAM disk, using hard drive");
                    return;
                }
            }

            File newCacheDir = new File(volume, "cache");
            if (!newCacheDir.exists() && !newCacheDir.mkdirs()) {
                config.getLogger().debug("Couldn't create cache directory on RAM disk, using hard drive");
                return;
            }
            File newTmpDir = new File(volume, "tmp");
            if (!newTmpDir.exists() && !newTmpDir.mkdirs()) {
                config.getLogger().debug("Couldn't create tmp directory on RAM disk, using hard drive");
                return;
            }
            newTmpDir = new File(newTmpDir, tmpDir.getAbsolutePath());
            config.getLogger().debug("Using RAM disk at %s for cache and tmp directory", ROBOVM_RAM_DISK_PATH);
            builder.cacheDir(newCacheDir);
            builder.tmpDir(newTmpDir);
        } catch (Throwable t) {
            config.getLogger().error("Couldn't setup RAM disk, using hard drive, %s", t.getMessage());
            builder.cacheDir(cacheDir);
            builder.tmpDir(tmpDir);
        }
    }

    private void cleanRamDisk(FileStore store, File volume) {
        // clean the cache/ and tmp/ dirs
        // FIXME be smarter as per the issue report
        try {
            FileUtils.deleteDirectory(new File(volume, "tmp"));
            // only clean the cache if killing the tmp dir didn't work
            if (store.getUsableSpace() < MIN_FREE_SPACE) {
                FileUtils.deleteDirectory(new File(volume, "cache"));
            }
        } catch (IOException e) {
            // nothing to do here
        }
    }
}
