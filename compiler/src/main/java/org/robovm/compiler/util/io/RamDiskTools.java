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
package org.robovm.compiler.util.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.util.Executor;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Will modify cache and tmpdir paths given a {@link Config#builder()} and prun
 * the cache if necessary.
 * 
 * @author badlogic
 *
 */
public class RamDiskTools {
    private static final String ROBOVM_RAM_DISK_PATH = "/Volumes/RoboVM RAM Disk";
    private static final long MIN_FREE_SPACE = 1024 * 1024 * 400;

    private File newCacheDir;
    private File newTmpDir;

    public File getCacheDir() {
        return newCacheDir;
    }

    public File getTmpDir() {
        return newTmpDir;
    }

    /**
     * Checks if a RAM disk is available and prunes it if necessary.
     */
    public void setupRamDisk(Config config, File cacheDir, File tmpDir) {
        this.newCacheDir = cacheDir;
        this.newTmpDir = tmpDir;

        if (OS.getDefaultOS() != OS.macosx) {
            return;
        }

        File volume = new File(ROBOVM_RAM_DISK_PATH);
        if (!volume.exists()) {
            try {
                FileStore store = Files.getFileStore(new File(System.getProperty("user.home"))
                        .toPath());
                
                String plist = new Executor(Logger.NULL_LOGGER, "diskutil").args("info", "-plist", store.name())
                        .execCapture();
                NSDictionary dict = (NSDictionary)PropertyListParser.parse(plist.getBytes("UTF-8"));
                NSObject value = dict.objectForKey("SolidState");
                if(value == null || (value instanceof NSNumber && !((NSNumber)value).boolValue())) {
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

        try {
            FileStore store = Files.getFileStore(volume.toPath());
            if (store.getUsableSpace() < MIN_FREE_SPACE) {
                cleanRamDisk(store, volume, config);
                if (store.getUsableSpace() < MIN_FREE_SPACE) {
                    config.getLogger().info("Couldn't free enough space on RAM disk, using hard drive");
                    return;
                }
            }

            File newCacheDir = new File(volume, "cache");
            if (!newCacheDir.exists() && !newCacheDir.mkdirs()) {
                config.getLogger().info("Couldn't create cache directory on RAM disk, using hard drive");
                return;
            }
            File newTmpDir = new File(volume, "tmp");
            if (!newTmpDir.exists() && !newTmpDir.mkdirs()) {
                config.getLogger().info("Couldn't create tmp directory on RAM disk, using hard drive");
                return;
            }
            newTmpDir = new File(newTmpDir, tmpDir.getAbsolutePath());
            config.getLogger().info("Using RAM disk at %s for cache and tmp directory", ROBOVM_RAM_DISK_PATH);
            this.newCacheDir = newCacheDir;
            this.newTmpDir = newTmpDir;
        } catch (Throwable t) {
            config.getLogger().error("Couldn't setup RAM disk, using hard drive, %s", t.getMessage());
            this.newCacheDir = cacheDir;
            this.newTmpDir = tmpDir;
        }
    }

    private void cleanRamDisk(FileStore store, File volume, Config config) {
        // clean the cache/ and tmp/ dirs
        // FIXME be smarter as per the issue report
        try {
//            FileUtils.deleteDirectory(new File(volume, "tmp"));
            // only clean the cache if killing the tmp dir didn't work
            if (store.getUsableSpace() < MIN_FREE_SPACE) {
                cleanCache(store, volume, config);
            }
        } catch (IOException e) {
            // nothing to do here
        }
    }

    private void cleanCache(FileStore store, File volume, Config config) throws IOException {
        OS currOs = config.getOs();
        Arch currArch = config.getArch();
        CacheDir currCacheDir = constructCacheDir(volume, currOs, currArch, config.isDebug());

        // Enumerate all directories that are not our current cache
        // dir
        List<CacheDir> cacheDirs = new ArrayList<CacheDir>();
        for (OS os : OS.values()) {
            for (Arch arch : Arch.values()) {
                for (boolean isDebug : new boolean[] { false, true }) {
                    CacheDir cacheDir = constructCacheDir(volume, os, arch, isDebug);
                    if (cacheDir != null && !cacheDir.directory.equals(currCacheDir.directory)) {
                        cacheDirs.add(cacheDir);
                    }
                }
            }
        }
        
        // sort the directories by their last modified
        // date in ascending order (oldest first). We
        // start deleting  the oldest cache files first
        // before we start deleting the cache files for
        // the current os/arch
        Collections.sort(cacheDirs, new Comparator<CacheDir>() {
            @Override
            public int compare(CacheDir o1, CacheDir o2) {
                return new Date(o1.lastModified).compareTo(new Date(o2.lastModified));
            }
        });

        // add our current target dir last if it already
        // exists. This way we delete its cache files last
        if(currCacheDir != null) {
            cacheDirs.add(currCacheDir);
        }
        
        // start deleting files until we have enough
        // space
        for(CacheDir dir: cacheDirs) {
            for(File file: dir.objFiles) {
                file.delete();
                if(store.getUsableSpace() > MIN_FREE_SPACE) {
                    return;
                }
            }            
        }                
        
        // nuclear option, we couldn't delete enough files
        FileUtils.deleteDirectory(new File(volume, "cache"));
    }

    private CacheDir constructCacheDir(File volume, OS os, Arch arch, boolean isDebug) {
        File dir = new File(volume, "cache/" + os.toString() + "/" + arch.toString() + "/"
                + (isDebug ? "debug" : "release"));
        if (!dir.exists())
            return null;
        List<File> objFiles = new ArrayList<File>((Collection<File>) FileUtils.listFiles(dir, new String[] { "o" },
                true));
        Collections.sort(objFiles, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return new Date(f2.lastModified()).compareTo(new Date(f1.lastModified()));
            }
        });
        long lastModified = 0;
        for (File file : objFiles) {
            lastModified = Math.max(lastModified, file.lastModified());
        }
        return new CacheDir(dir, objFiles, lastModified);
    }

    static class CacheDir {
        File directory;
        List<File> objFiles;
        long lastModified;

        public CacheDir(File directory, List<File> objFiles, long lastModified) {
            this.directory = directory;
            this.objFiles = objFiles;
            this.lastModified = lastModified;
        }
    }
}
