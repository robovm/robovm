/*
 * Copyright (C) 2013 Trillian Mobile AB
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
package org.robovm.libimobiledevice.util;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

/**
 * Tests {@link AppLauncher}.
 */
public class AppLauncherTest {

    private File createDeveloperImage(Path dsDir, String version) throws Exception {
        return createDeveloperImage(dsDir, version, true);
    }
    private File createDeveloperImage(Path dsDir, String version, boolean createSignature) throws Exception {
        Files.createDirectory(dsDir.resolve(version));
        if (createSignature) {
            Files.createFile(dsDir.resolve(version + "/DeveloperDiskImage.dmg.signature"));
        }
        return Files.createFile(dsDir.resolve(version + "/DeveloperDiskImage.dmg")).toFile();
    }
    
    @SuppressWarnings("unused")
    @Test
    public void testFindDeveloperImage() throws Exception {
        Path dsDir = Files.createTempDirectory(getClass().getSimpleName());
        File f50 = createDeveloperImage(dsDir, "5.0");
        File f51 = createDeveloperImage(dsDir, "5.1");
        File f60 = createDeveloperImage(dsDir, "6.0");
        File f61 = createDeveloperImage(dsDir, "6.1");
        File f612 = createDeveloperImage(dsDir, "6.1.2");
        File f613 = createDeveloperImage(dsDir, "6.1.3", false);
        File f70_11A465 = createDeveloperImage(dsDir, "7.0 (11A465)");
        File f703_11B508 = createDeveloperImage(dsDir, "7.0.3 (11B508)");
        
        assertEquals(f703_11B508, AppLauncher.findDeveloperImage(dsDir.toFile(), "7.0.3", "11B508"));
        assertEquals(f61, AppLauncher.findDeveloperImage(dsDir.toFile(), "6.1.3", "10A123"));
        assertEquals(f61, AppLauncher.findDeveloperImage(dsDir.toFile(), "6.1.1", "10A123"));
        assertEquals(f612, AppLauncher.findDeveloperImage(dsDir.toFile(), "6.1.2", "10A123"));
        assertEquals(f703_11B508, AppLauncher.findDeveloperImage(dsDir.toFile(), "7.0.3", "12C123"));
        assertEquals(f70_11A465, AppLauncher.findDeveloperImage(dsDir.toFile(), "7.0.2", "12C123"));
    }

}
