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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.libimobiledevice.binding.AfcFileMode;
import org.robovm.libimobiledevice.binding.AfcLinkType;
import org.robovm.libimobiledevice.binding.LibIMobileDeviceConstants;

/**
 * Tests {@link AfcClient}.
 */
public class AfcClientTest {

    static AfcClient client;
    static IDevice device;
    static LockdowndClient lockdowndClient;
    
    @BeforeClass
    public static void beforeClass() {
        String[] udids = IDevice.listUdids();
        if (udids.length > 0) {
            String udid = udids[0];
            device = new IDevice(udid);
            lockdowndClient = new LockdowndClient(device, null, true);
            LockdowndServiceDescriptor service = lockdowndClient.startService(AfcClient.SERVICE_NAME);
            client = new AfcClient(device, service);
        }
    }
    
    @AfterClass
    public static void cleanup() {
        if (client != null) {
            client.dispose();
            client = null;
        }
        if (lockdowndClient != null) {
            lockdowndClient.dispose();
            lockdowndClient = null;
        }
        if (device != null) {
            device.dispose();
            device = null;
        }
    }
    
    @Before
    public void setUp() {
        Assume.assumeTrue(client != null);
    }
    
    @Test
    public void testReadDirectory() {
        List<String> dirs = Arrays.asList(client.readDirectory("/"));
        System.out.println(dirs);
        assertFalse(dirs.isEmpty());
        assertTrue(dirs.contains("Books"));
        assertTrue(dirs.contains("DCIM"));
        assertTrue(dirs.contains("Safari"));
    }

    @Test
    public void testGetDeviceInfo() throws Exception {
        Map<String, String> devInfo = client.getDeviceInfo();
        assertFalse(devInfo.isEmpty());
        assertTrue(devInfo.containsKey(AfcClient.DEVICE_INFO_KEY_FS_BLOCK_SIZE));
        assertTrue(devInfo.containsKey(AfcClient.DEVICE_INFO_KEY_FS_FREE_BYTES));
        assertTrue(devInfo.containsKey(AfcClient.DEVICE_INFO_KEY_FS_TOTAL_BYTES));
        assertTrue(devInfo.containsKey(AfcClient.DEVICE_INFO_KEY_MODEL));
        assertEquals(devInfo.get(AfcClient.DEVICE_INFO_KEY_FS_BLOCK_SIZE), "" + client.getBlockSize());
        assertEquals(devInfo.get(AfcClient.DEVICE_INFO_KEY_FS_FREE_BYTES), "" + client.getFreeBytes());
        assertEquals(devInfo.get(AfcClient.DEVICE_INFO_KEY_FS_TOTAL_BYTES), "" + client.getTotalBytes());
        assertEquals(devInfo.get(AfcClient.DEVICE_INFO_KEY_MODEL), "" + client.getModel());
    }
    
//    @Test
//    @Ignore
//    public void testGetFileInfo() throws Exception {
//        list(client, "/", "", "", System.out);
//    }

    private void list() {
        list("/", "", "", new PrintWriter(System.out));
    }

    private void list(String path, String filename, String indent, PrintWriter out) {
        Map<String, String> info = client.getFileInfo(path);
        if ("S_IFDIR".equals(info.get(AfcClient.FILE_INFO_KEY_ST_IFMT))) {
            out.println(indent + filename + "/");
            out.flush();
            for (String f : client.readDirectory(path)) {
                if (f.equals("..") || f.equals(".")) {
                    continue;
                }
                String childPath = path + "/" + f;
                list(childPath, f, indent  + "  ", out);
            }
        } else if ("S_IFLNK".equals(info.get(AfcClient.FILE_INFO_KEY_ST_IFMT))) {
            out.println(indent + filename + " -> "
                    + info.get(AfcClient.FILE_INFO_KEY_LINK_TARGET));
            out.flush();
        } else {
            out.println(indent + filename + " "
                    + info.get(AfcClient.FILE_INFO_KEY_ST_SIZE) + " (" 
                    + info.get(AfcClient.FILE_INFO_KEY_ST_IFMT) + ")");
            out.flush();
        }
    }
    
    @Test
    public void testReadWriteRemove() throws Exception {
        byte[] buffer = new byte[4096];
        int n = 0;
        long seed = System.currentTimeMillis();
        int size = 1 << 20;
        long fd = 0;

        fd = client.fileOpen("/FOO.TXT", AfcFileMode.AFC_FOPEN_WRONLY);
        RandomInputStream ris = new RandomInputStream(size, seed);
        while ((n = ris.read(buffer)) != -1) {
            client.fileWrite(fd, buffer, 0, n);
        }
        ris.close();
        client.fileClose(fd);

        Map<String, String> fileInfo = client.getFileInfo("/FOO.TXT");
        assertEquals("" + size, fileInfo.get(AfcClient.FILE_INFO_KEY_ST_SIZE));
        
        Random random = new Random(seed);
        fd = client.fileOpen("/FOO.TXT", AfcFileMode.AFC_FOPEN_RDONLY);
        int totalRead = 0;
        while ((n = client.fileRead(fd, buffer, 0, buffer.length)) != -1) {
            for (int i = 0; i < n; i++) {
                assertEquals(nextCharAZ(random), buffer[i] & 0xff);
            }
            totalRead += n;
        }
        client.fileClose(fd);
        assertEquals(size, totalRead);
        
        client.removePath("/FOO.TXT");
        try {
            client.getFileInfo("/FOO.TXT");
            fail("LibIMobileDeviceException expected");
        } catch (LibIMobileDeviceException e) {
            assertEquals(LibIMobileDeviceConstants.AFC_E_OBJECT_NOT_FOUND, e.getErrorCode());
        }
    }
    
    @Test
    public void testWriteSpeed() throws Exception {
        byte[] buffer = new byte[64 * 1024];
        long fd = client.fileOpen("/FOO.DAT", AfcFileMode.AFC_FOPEN_WRONLY);
        int n = 0;
        long start = System.currentTimeMillis();
        while (n < (1 << 24)) {
            n += client.fileWrite(fd, buffer, 0, buffer.length);
        }
        long duration = System.currentTimeMillis() - start;
        client.fileClose(fd);

        System.out.format("%d bytes written in %d ms (%f kB/s)\n", n, 
                duration, n / 1024.0 / (duration / 1000.0));
        
        client.removePath("/FOO.DAT");
    }
    
    @Test
    public void testMakeDirectory() throws Exception {
        client.makeDirectory("/FOO");
        client.makeDirectory("/FOO/BAR");
        client.makeDirectory("/FOO/BAR/WOOZ/BAZ");
        assertEquals("S_IFDIR", client.getFileInfo("/FOO/BAR/WOOZ/BAZ").get(AfcClient.FILE_INFO_KEY_ST_IFMT));
        try {
            client.removePath("/FOO");
            fail("LibIMobileDeviceException expected");
        } catch (LibIMobileDeviceException e) {
            assertEquals(LibIMobileDeviceConstants.AFC_E_DIR_NOT_EMPTY, e.getErrorCode());
        }
        client.removePath("/FOO", true);
        try {
            client.getFileInfo("/FOO");
            fail("LibIMobileDeviceException expected");
        } catch (LibIMobileDeviceException e) {
            assertEquals(LibIMobileDeviceConstants.AFC_E_OBJECT_NOT_FOUND, e.getErrorCode());
        }
    }
    
    @Test
    public void testMakeLink() throws Exception {
        try {
            client.makeDirectory("/FOOBAR");
            client.makeLink(AfcLinkType.AFC_SYMLINK, "../Safari", "/FOOBAR/KAZ");
            assertEquals("S_IFLNK", client.getFileInfo("/FOOBAR/KAZ").get(AfcClient.FILE_INFO_KEY_ST_IFMT));
            assertEquals("../Safari", client.getFileInfo("/FOOBAR/KAZ").get(AfcClient.FILE_INFO_KEY_LINK_TARGET));
        } finally {
            try {
                client.removePath("/FOOBAR", true);
            } catch (LibIMobileDeviceException e) {}
        }
    }
    
    @Test
    public void testUpload() throws Exception {
        Path dir = Files.createTempDirectory(AfcClientTest.class.getSimpleName());
        try {
            Path foo = Files.createDirectory(dir.resolve("foo"));
            Path bar = Files.createDirectory(foo.resolve("bar"));
            Path wooz = Files.createFile(bar.resolve("wooz"));
            Files.createSymbolicLink(foo.resolve("rooz"), foo.relativize(wooz));
            try {
                client.upload(foo.toFile(), "/baz");
                StringWriter sw = new StringWriter();
                list("/baz", "baz", "", new PrintWriter(sw));
                assertEquals(
                        "baz/\n"
                      + "  foo/\n"
                      + "    bar/\n"
                      + "      wooz 0 (S_IFREG)\n"
                      + "    rooz -> bar/wooz\n", 
                      sw.toString());
            } finally {
                client.removePath("/baz", true);
            }
        } finally {
            FileUtils.deleteDirectory(dir.toFile());
        }
    }
    
    @Test
    public void testUploadSpeed() throws Exception {
        Path dir = Files.createTempDirectory(AfcClientTest.class.getSimpleName());
        try {
            Path foo = Files.createFile(dir.resolve("foo"));
            byte[] data = new byte[1 << 24];
            Files.write(foo, data);
            long start = System.currentTimeMillis();
            try {
                client.upload(foo.toFile(), "/wooz");
            } finally {
                long duration = System.currentTimeMillis() - start;
                System.out.format("%d bytes uploaded in %d ms (%f kB/s)\n", data.length, 
                        duration, data.length / 1024.0 / (duration / 1000.0));
                client.removePath("/wooz", true);
            }
        } finally {
            FileUtils.deleteDirectory(dir.toFile());
        }
    }
    
    private static int nextCharAZ(Random r) {
        return r.nextInt('z' - 'a') + 'a';
    }
    
    private static class RandomInputStream extends InputStream {
        private final int size;
        private final Random random;
        private int position = 0;

        RandomInputStream(int size, long seed) {
            this.size = size;
            random = new Random(seed);
        }
        
        @Override
        public int read() throws IOException {
            if (position < size) {
                position++;
                return nextCharAZ(random);
            }
            return -1;
        }
    }
}
