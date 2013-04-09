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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import junit.framework.TestCase;

public final class GZIPInputStreamTest extends TestCase {
    public void testShortMessage() throws IOException {
        byte[] data = new byte[] {
            31, -117, 8, 0, 0, 0, 0, 0, 0, 0, -13, 72, -51, -55, -55, 87, 8, -49,
            47, -54, 73, 1, 0, 86, -79, 23, 74, 11, 0, 0, 0
        };
        assertEquals("Hello World", new String(gunzip(data), "UTF-8"));
    }

    public void testLongMessage() throws IOException {
        byte[] data = new byte[1024 * 1024];
        new Random().nextBytes(data);
        assertTrue(Arrays.equals(data, gunzip(GZIPOutputStreamTest.gzip(data))));
    }

    /** http://b/3042574 GzipInputStream.skip() causing CRC failures */
    public void testSkip() throws IOException {
        byte[] data = new byte[1024 * 1024];
        byte[] gzipped = GZIPOutputStreamTest.gzip(data);
        GZIPInputStream in = new GZIPInputStream(new ByteArrayInputStream(gzipped));
        long totalSkipped = 0;

        long count;
        do {
            count = in.skip(Long.MAX_VALUE);
            totalSkipped += count;
        } while (count > 0);

        assertEquals(data.length, totalSkipped);
        in.close();
    }

    public static byte[] gunzip(byte[] bytes) throws IOException {
        InputStream in = new GZIPInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        in.close();
        return out.toByteArray();
    }
}
