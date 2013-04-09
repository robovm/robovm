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

package libcore.java.io;

import java.io.*;
import java.util.Arrays;
import junit.framework.TestCase;

public class OutputStreamWriterTest extends TestCase {
    private class FlushCountingOutputStream extends OutputStream {
        int flushCount;
        public void write(int b) {
        }
        @Override public void flush() throws IOException {
            ++flushCount;
        }
    }

    public void testFlushCount() throws Exception {
        FlushCountingOutputStream os = new FlushCountingOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
        char[] chars = new char[16*1024];
        Arrays.fill(chars, 'x');
        for (int i = 0; i < 10; ++i) {
            writer.write(chars);
        }
        assertEquals(0, os.flushCount);
        writer.flush();
        assertEquals(1, os.flushCount);
        writer.close();
        assertEquals(1, os.flushCount);
    }

    private void testFlush(boolean includeSecondHalf) throws Exception {
        // OutputStreamWriter has an internal 8KiB buffer.
        // We write enough characters to fill that, but leave data in the encoder.
        // (Specifically, half a surrogate pair.)
        // On flush/close, the writer needs to admit defeat and write the replacement character.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(baos, "UTF-32BE");
        char[] cs = new char[8192/4 + 1];
        Arrays.fill(cs, 'x');
        cs[cs.length - 1] = '\ud842'; // One half of a surrogate pair (the other being U+df9f).
        writer.write(cs, 0, cs.length);
        writer.flush();
        assertEquals(8192, baos.size()); // Just the 'x's so far.
        if (includeSecondHalf) {
            writer.write(0xdf9f);
        }
        writer.close();
        // We should have 8192 32-bit big-endian 'x's...
        byte[] bytes = baos.toByteArray();
        assertEquals(8196, bytes.length);
        int i = 0;
        while (i < 8192) {
            assertEquals((byte) 0, bytes[i++]);
            assertEquals((byte) 0, bytes[i++]);
            assertEquals((byte) 0, bytes[i++]);
            assertEquals((byte) 'x', bytes[i++]);
        }
        if (includeSecondHalf) {
            // ...followed by a 32-bit big-endian U+20b9f.
            assertEquals((byte) 0x00, bytes[i++]);
            assertEquals((byte) 0x02, bytes[i++]);
            assertEquals((byte) 0x0b, bytes[i++]);
            assertEquals((byte) 0x9f, bytes[i++]);
        } else {
            // ...followed by a 32-bit big-endian replacement character (U+fffd).
            assertEquals((byte) 0, bytes[i++]);
            assertEquals((byte) 0, bytes[i++]);
            assertEquals((byte) 0xff, bytes[i++]);
            assertEquals((byte) 0xfd, bytes[i++]);
        }
    }

    public void testFlush_halfSurrogate() throws Exception {
        testFlush(false);
    }

    public void testFlush_wholeSurrogate() throws Exception {
        testFlush(true);
    }
}
