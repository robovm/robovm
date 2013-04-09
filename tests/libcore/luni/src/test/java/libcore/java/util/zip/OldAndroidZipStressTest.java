/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.util.zip;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import junit.framework.TestCase;

public final class OldAndroidZipStressTest extends TestCase {
    /**
     * JarEntry.getCertificates() is really slow. http://b/1046174
     */
    public void checkJarCertificates(File file) throws Exception {
        JarFile jarFile = new JarFile(file);
        JarEntry je = jarFile.getJarEntry("AndroidManifest.xml");
        byte[] readBuffer = new byte[1024];

        long t0 = System.currentTimeMillis();

        // We must read the stream for the JarEntry to retrieve its certificates.
        InputStream is = jarFile.getInputStream(je);
        while (is.read(readBuffer, 0, readBuffer.length) != -1) {
        }
        is.close();
        Certificate[] certs = je != null ? je.getCertificates() : null;

        long t1 = System.currentTimeMillis();
        System.out.println("loadCertificates() took " + (t1 - t0) + " ms");
        if (certs == null) {
            System.out.println("We have no certificates");
        } else {
            System.out.println("We have " + certs.length + " certificates");
        }
    }

    private File[] getFiles() {
        File[] result = new File("/system/app").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".apk");
            }
        });
        return result != null ? result : new File[0];
    }

    public void testJarCertificates() throws Exception {
        for (File file : getFiles()) {
            checkJarCertificates(file);
        }
    }

    // Boot-time package scan is slow. Not expected to fail. Please see log if
    // you are interested in the results. http://b/1212257
    public void testZipStressManifest() throws Exception {
        long time0 = System.currentTimeMillis();
        byte[] buffer = new byte[512];
        for (File file : getFiles()) {
            System.out.println("ZIP stress test processing " + file + "...");

            ZipFile zip = new ZipFile(file);
            ZipEntry entry = zip.getEntry("AndroidManifest.xml");
            InputStream stream = zip.getInputStream(entry);
            int j = stream.read(buffer);
            while (j != -1) {
                j = stream.read(buffer);
            }
            stream.close();
        }
        long time1 = System.currentTimeMillis();
        System.out.println("ZIP stress test finished, time was " + (time1- time0) + "ms");
    }

    public void testZipStressAllFiles() throws Exception {
        long time0 = System.currentTimeMillis();
        byte[] buffer = new byte[512];
        for (File file : getFiles()) {
            System.out.println("ZIP stress test processing " + file + "...");
            ZipFile zip = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                InputStream stream = zip.getInputStream(entries.nextElement());
                int j = stream.read(buffer);
                while (j != -1) {
                    j = stream.read(buffer);
                }
                stream.close();
            }
        }
        long time1 = System.currentTimeMillis();
        System.out.println("ZIP stress test finished, time was " + (time1- time0) + "ms");
    }

    private void assertEquals(byte[] a, byte[] b) {
        assertTrue(Arrays.equals(a, b));
    }

    /**
     * Native memory allocated by Deflater in system_server. The fix reduced
     * some internal ZLIB buffers in size, so this test is trying to execute a
     * lot of deflating to ensure that things are still working properly.
     * http://b/1185084
     */
    public void testZipDeflateInflateStress() throws Exception {
        final int DATA_SIZE = 16384;
        Random random = new Random(42); // Seed makes test reproducible
        // Outer loop selects "mode" of test.
        for (int j = 1; j <= 2; j++) {
            byte[] input = new byte[DATA_SIZE];

            if (j == 1) {
                // Totally random content
                random.nextBytes(input);
            } else {
                // Random contents with longer repetitions
                int pos = 0;
                while (pos < input.length) {
                    byte what = (byte)random.nextInt(256);
                    int howMany = random.nextInt(32);
                    if (pos + howMany >= input.length) {
                        howMany = input.length - pos;
                    }
                    Arrays.fill(input, pos, pos + howMany, what);
                    pos += howMany;
                }
            }

            // Inner loop tries all 9 compression levels.
            for (int i = 1; i <= 9; i++) {
                System.out.println("ZipDeflateInflateStress test (" + j + "," + i + ")...");
                byte[] zipped = new byte[2 * DATA_SIZE]; // Just to make sure...

                Deflater deflater = new Deflater(i);
                deflater.setInput(input);
                deflater.finish();
                deflater.deflate(zipped);
                deflater.end();

                byte[] output = new byte[DATA_SIZE];

                Inflater inflater = new Inflater();
                inflater.setInput(zipped);
                inflater.finished();
                inflater.inflate(output);
                inflater.end();
                assertEquals(input, output);
            }
        }
    }
}
