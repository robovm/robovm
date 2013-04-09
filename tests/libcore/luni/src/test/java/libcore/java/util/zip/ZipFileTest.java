/*
 * Copyright (C) 2010 The Android Open Source Project
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import junit.framework.TestCase;

public final class ZipFileTest extends TestCase {

    /**
     * Exercise Inflater's ability to refill the zlib's input buffer. As of this
     * writing, this buffer's max size is 64KiB compressed bytes. We'll write a
     * full megabyte of uncompressed data, which should be sufficient to exhaust
     * the buffer. http://b/issue?id=2734751
     */
    public void testInflatingFilesRequiringZipRefill() throws IOException {
        int originalSize = 1024 * 1024;
        byte[] readBuffer = new byte[8192];
        ZipFile zipFile = new ZipFile(createZipFile(originalSize));
        for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
            ZipEntry zipEntry = e.nextElement();
            assertTrue("This test needs >64 KiB of compressed data to exercise Inflater",
                    zipEntry.getCompressedSize() > (64 * 1024));
            InputStream is = zipFile.getInputStream(zipEntry);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {}
            is.close();
        }
        zipFile.close();
    }

    public void testInflatingStreamsRequiringZipRefill() throws IOException {
        int originalSize = 1024 * 1024;
        byte[] readBuffer = new byte[8192];
        ZipInputStream in = new ZipInputStream(new FileInputStream(createZipFile(originalSize)));
        while (in.getNextEntry() != null) {
            while (in.read(readBuffer, 0, readBuffer.length) != -1) {}
        }
        in.close();
    }

    /**
     * Compresses a single random file into a .zip archive.
     */
    private File createZipFile(int uncompressedSize) throws IOException {
        File result = File.createTempFile("ZipFileTest", "zip");
        result.deleteOnExit();

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(result));
        ZipEntry entry = new ZipEntry("random");
        out.putNextEntry(entry);

        byte[] writeBuffer = new byte[8192];
        Random random = new Random();
        for (int i = 0; i < uncompressedSize; i += writeBuffer.length) {
            random.nextBytes(writeBuffer);
            out.write(writeBuffer, 0, Math.min(writeBuffer.length, uncompressedSize - i));
        }

        out.closeEntry();
        out.close();
        return result;
      }

      public void testHugeZipFile() throws IOException {
          int expectedEntryCount = 64*1024 - 1;
          File f = createHugeZipFile(expectedEntryCount);
          ZipFile zipFile = new ZipFile(f);
          int entryCount = 0;
          for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
              ZipEntry zipEntry = e.nextElement();
              ++entryCount;
          }
          assertEquals(expectedEntryCount, entryCount);
          zipFile.close();
      }

      public void testZip64Support() throws IOException {
          try {
              createHugeZipFile(64*1024);
              fail(); // Make this test more like testHugeZipFile when we have Zip64 support.
          } catch (ZipException expected) {
          }
      }

      /**
       * Compresses the given number of empty files into a .zip archive.
       */
      private File createHugeZipFile(int count) throws IOException {
          File result = File.createTempFile("ZipFileTest", "zip");
          result.deleteOnExit();

          ZipOutputStream out = new ZipOutputStream(new FileOutputStream(result));
          for (int i = 0; i < count; ++i) {
              ZipEntry entry = new ZipEntry(Integer.toString(i));
              out.putNextEntry(entry);
              out.closeEntry();
          }

          out.close();
          return result;
      }
}
