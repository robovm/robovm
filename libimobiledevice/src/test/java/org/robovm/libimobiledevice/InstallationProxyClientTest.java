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
package org.robovm.libimobiledevice;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.libimobiledevice.AfcClient.UploadProgressCallback;
import org.robovm.libimobiledevice.InstallationProxyClient.Options;
import org.robovm.libimobiledevice.InstallationProxyClient.Options.PackageType;
import org.robovm.libimobiledevice.InstallationProxyClient.StatusCallback;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;

/**
 * Tests {@link InstallationProxyClient}.
 */
public class InstallationProxyClientTest {

    static InstallationProxyClient client;
    static AfcClient afcClient;
    
    @BeforeClass
    public static void beforeClass() {
        String[] udids = IDevice.listUdids();
        if (udids.length > 0) {
            String udid = udids[0];
            IDevice device = new IDevice(udid);
            LockdowndClient lockdowndClient = new LockdowndClient(device, null, true);
            LockdowndServiceDescriptor instproxyService = lockdowndClient.startService(InstallationProxyClient.SERVICE_NAME);
            client = new InstallationProxyClient(device, instproxyService);
            LockdowndServiceDescriptor afcService = lockdowndClient.startService(AfcClient.SERVICE_NAME);
            afcClient = new AfcClient(device, afcService);
        }
    }
    
    @AfterClass
    public static void cleanup() {
        if (client != null) {
            client.dispose();
            client = null;
        }
    }
    
    @Before
    public void setUp() {
        Assume.assumeTrue(client != null);
    }
    
    @Test
    public void testBrowse() throws Exception {
        NSArray array = client.browse();
        NSDictionary safari = null;
        for (int i = 0; i < array.count(); i++) {
            NSDictionary dict = (NSDictionary) array.objectAtIndex(i);
            NSObject v = dict.objectForKey("CFBundleIdentifier");
            if (v != null && "com.apple.mobilesafari".equals(v.toString())) {
                safari = dict;
            }
        }
        assertNotNull(safari);
    }

    @Test
    public void testUpgradeIPA() throws Exception {
        Path tmpDir = Files.createTempDirectory(getClass().getSimpleName());
        Files.copy(getClass().getResourceAsStream("/demo.ipa"), tmpDir.resolve("demo.ipa"));
        afcClient.upload(tmpDir.resolve("demo.ipa").toFile(), "/TestStaging", new UploadProgressCallback() {
            public void progress(File path, int percentComplete) {
                System.out.format("[%3d%%] Uploading %s\n", percentComplete, path);
            }
            public void success() {
                System.out.format("[100%%] Upload done\n");
            }
            public void error(String message) {
                System.out.format("Error: %s\n", message);
            }
        });
        
        final boolean[] success = new boolean[] {false};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        client.upgrade("/TestStaging/demo.ipa", new Options(), new StatusCallback() {
            @Override
            public void progress(String status, int percentComplete) {
                System.out.format("[%3d%%] %s\n", percentComplete, status);
            }
            @Override
            public void success() {
                System.out.format("[100%%] Complete\n");
                success[0] = true;
                countDownLatch.countDown();
            }
            @Override
            public void error(String message) {
                System.out.println("Error: "+ message);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(10, TimeUnit.SECONDS);
        assertTrue(success[0]);
    }
    
    @Test
    public void testUpgradeAppBundle() throws Exception {
        Path tmpDir = Files.createTempDirectory(getClass().getSimpleName());
        Path ipaFile = tmpDir.resolve("demo.ipa");
        Files.copy(getClass().getResourceAsStream("/demo.ipa"), ipaFile);
        extractZip(ipaFile, tmpDir);
        Path appDir = tmpDir.resolve("Payload/demo.app");
        afcClient.upload(appDir.toFile(), "/TestStaging", new UploadProgressCallback() {
            public void progress(File path, int percentComplete) {
                System.out.format("[%3d%%] Uploading %s\n", percentComplete, path);
            }
            public void success() {
                System.out.format("[100%%] Upload done\n");
            }
            public void error(String message) {
                System.out.format("Error: %s\n", message);
            }
        });
        
        final boolean[] success = new boolean[] {false};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        client.upgrade("/TestStaging/demo.app", new Options().packageType(PackageType.Developer), 
                new StatusCallback() {
            
            @Override
            public void progress(String status, int percentComplete) {
                System.out.format("[%3d%%] %s\n", percentComplete, status);
            }
            @Override
            public void success() {
                System.out.format("[100%%] Complete\n");
                success[0] = true;
                countDownLatch.countDown();
            }
            @Override
            public void error(String message) {
                System.out.println("Error: "+ message);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(10, TimeUnit.SECONDS);
        assertTrue(success[0]);
    }
    
    protected void extractZip(Path zipFile, Path outDir) throws IOException {
        try (ZipFile archive = new ZipFile(zipFile.toFile())) {
            Enumeration<? extends ZipEntry> entries = archive.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Path outFile = outDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(outFile);
                } else {
                    if (!Files.exists(outFile.getParent())) {
                        Files.createDirectories(outFile.getParent());
                    }
                    try (InputStream in = archive.getInputStream(entry)) {
                        Files.copy(in, outFile);
                    }
                }
            }
        }
    }
}
