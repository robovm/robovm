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

package libcore.java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class CharsetTest extends junit.framework.TestCase {
    public void test_guaranteedCharsetsAvailable() throws Exception {
        // All Java implementations must support these charsets.
        assertNotNull(Charset.forName("ISO-8859-1"));
        assertNotNull(Charset.forName("US-ASCII"));
        assertNotNull(Charset.forName("UTF-16"));
        assertNotNull(Charset.forName("UTF-16BE"));
        assertNotNull(Charset.forName("UTF-16LE"));
        assertNotNull(Charset.forName("UTF-8"));
    }

    public void test_allAvailableCharsets() throws Exception {
        // Check that we can instantiate every Charset, CharsetDecoder, and CharsetEncoder.
        for (String charsetName : Charset.availableCharsets().keySet()) {
            if (charsetName.equals("UTF-32")) {
                // Our UTF-32 is broken. http://b/2702411
                // TODO: remove this hack when UTF-32 is fixed.
                continue;
            }

            Charset cs = Charset.forName(charsetName);
            assertNotNull(cs.newDecoder());
            if (cs.canEncode()) {
                CharsetEncoder enc = cs.newEncoder();
                assertNotNull(enc);
                assertNotNull(enc.replacement());
            }
        }
    }

    public void test_EUC_JP() throws Exception {
        assertEncodes(Charset.forName("EUC-JP"), "\ufffd", 0xf4, 0xfe);
    }

    public void test_SCSU() throws Exception {
        assertEncodes(Charset.forName("SCSU"), "\ufffd", 14, 0xff, 0xfd);
    }

    public void test_Shift_JIS() throws Exception {
        assertEncodes(Charset.forName("Shift_JIS"), "\ufffd", 0xfc, 0xfc);
    }

    public void test_UTF_16() throws Exception {
        Charset cs = Charset.forName("UTF-16");
        // Writes big-endian, with a big-endian BOM.
        assertEncodes(cs, "a\u0666", 0xfe, 0xff, 0, 'a', 0x06, 0x66);
        // Reads whatever the BOM tells it to read...
        assertDecodes(cs, "a\u0666", 0xfe, 0xff, 0, 'a', 0x06, 0x66);
        assertDecodes(cs, "a\u0666", 0xff, 0xfe, 'a', 0, 0x66, 0x06);
        // ...and defaults to reading big-endian if there's no BOM.
        assertDecodes(cs, "a\u0666", 0, 'a', 0x06, 0x66);
    }

    public void test_UTF_16BE() throws Exception {
        Charset cs = Charset.forName("UTF-16BE");
        // Writes big-endian, with no BOM.
        assertEncodes(cs, "a\u0666", 0, 'a', 0x06, 0x66);
        // Treats a little-endian BOM as an error and continues to read big-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0xff, 0xfe, 0, 'a', 0x06, 0x66);
        // Accepts a big-endian BOM and includes U+FEFF in the decoded output.
        assertDecodes(cs, "\ufeffa\u0666", 0xfe, 0xff, 0, 'a', 0x06, 0x66);
        // Defaults to reading big-endian.
        assertDecodes(cs, "a\u0666", 0, 'a', 0x06, 0x66);
    }

    public void test_UTF_16LE() throws Exception {
        Charset cs = Charset.forName("UTF-16LE");
        // Writes little-endian, with no BOM.
        assertEncodes(cs, "a\u0666", 'a', 0, 0x66, 0x06);
        // Accepts a little-endian BOM and includes U+FEFF in the decoded output.
        assertDecodes(cs, "\ufeffa\u0666", 0xff, 0xfe, 'a', 0, 0x66, 0x06);
        // Treats a big-endian BOM as an error and continues to read little-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0xfe, 0xff, 'a', 0, 0x66, 0x06);
        // Defaults to reading little-endian.
        assertDecodes(cs, "a\u0666", 'a', 0, 0x66, 0x06);
    }

    public void test_x_UTF_16LE_BOM() throws Exception {
        Charset cs = Charset.forName("x-UTF-16LE-BOM");
        // Writes little-endian, with a BOM.
        assertEncodes(cs, "a\u0666", 0xff, 0xfe, 'a', 0, 0x66, 0x06);
        // Accepts a little-endian BOM and swallows the BOM.
        assertDecodes(cs, "a\u0666", 0xff, 0xfe, 'a', 0, 0x66, 0x06);
        // Swallows a big-endian BOM, but continues to read little-endian!
        assertDecodes(cs, "\u6100\u6606", 0xfe, 0xff, 'a', 0, 0x66, 0x06);
        // Defaults to reading little-endian.
        assertDecodes(cs, "a\u0666", 'a', 0, 0x66, 0x06);
    }

    public void test_UTF_32() throws Exception {
        Charset cs = Charset.forName("UTF-32");
        // Writes big-endian, with no BOM.
        assertEncodes(cs, "a\u0666", 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Reads whatever the BOM tells it to read...
        assertDecodes(cs, "a\u0666", 0, 0, 0xfe, 0xff, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        assertDecodes(cs, "a\u0666", 0xff, 0xfe, 0, 0, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // ...and defaults to reading big-endian if there's no BOM.
        assertDecodes(cs, "a\u0666", 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
    }

    public void test_UTF_32BE() throws Exception {
        Charset cs = Charset.forName("UTF-32BE");
        // Writes big-endian, with no BOM.
        assertEncodes(cs, "a\u0666", 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Treats a little-endian BOM as an error and continues to read big-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0xff, 0xfe, 0, 0, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Accepts a big-endian BOM and swallows the BOM.
        assertDecodes(cs, "a\u0666", 0, 0, 0xfe, 0xff, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Defaults to reading big-endian.
        assertDecodes(cs, "a\u0666", 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
    }

    public void test_UTF_32LE() throws Exception {
        Charset cs = Charset.forName("UTF-32LE");
        // Writes little-endian, with no BOM.
        assertEncodes(cs, "a\u0666", 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Accepts a little-endian BOM and swallows the BOM.
        assertDecodes(cs, "a\u0666", 0xff, 0xfe, 0, 0, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Treats a big-endian BOM as an error and continues to read little-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0, 0, 0xfe, 0xff, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Defaults to reading little-endian.
        assertDecodes(cs, "a\u0666", 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
    }

    public void test_X_UTF_32BE_BOM() throws Exception {
        Charset cs = Charset.forName("X-UTF-32BE-BOM");
        // Writes big-endian, with a big-endian BOM.
        assertEncodes(cs, "a\u0666", 0, 0, 0xfe, 0xff, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Treats a little-endian BOM as an error and continues to read big-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0xff, 0xfe, 0, 0, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Swallows a big-endian BOM, and continues to read big-endian.
        assertDecodes(cs, "a\u0666", 0, 0, 0xfe, 0xff, 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
        // Defaults to reading big-endian.
        assertDecodes(cs, "a\u0666", 0, 0, 0, 'a', 0, 0, 0x06, 0x66);
    }

    public void test_X_UTF_32LE_BOM() throws Exception {
        Charset cs = Charset.forName("X-UTF-32LE-BOM");
        // Writes little-endian, with a little-endian BOM.
        assertEncodes(cs, "a\u0666", 0xff, 0xfe, 0, 0, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Accepts a little-endian BOM and swallows the BOM.
        assertDecodes(cs, "a\u0666", 0xff, 0xfe, 0, 0, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Treats a big-endian BOM as an error and continues to read little-endian.
        // This test uses REPLACE mode, so we get the U+FFFD replacement character in the result.
        assertDecodes(cs, "\ufffda\u0666", 0, 0, 0xfe, 0xff, 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
        // Defaults to reading little-endian.
        assertDecodes(cs, "a\u0666", 'a', 0, 0, 0, 0x66, 0x06, 0, 0);
    }

    public void test_preNioAliases() throws Exception {
        // Various pre-nio java.lang/java.io encoding names are translated to nio charsets.
        assertEquals("UTF-16BE", Charset.forName("UnicodeBigUnmarked").name());
        assertEquals("UTF-16LE", Charset.forName("UnicodeLittleUnmarked").name());
        assertEquals("UTF-16", Charset.forName("Unicode").name());
        assertEquals("UTF-16", Charset.forName("UnicodeBig").name());
        assertEquals("x-UTF-16LE-BOM", Charset.forName("UnicodeLittle").name());
        assertEquals("X-UTF-32BE-BOM", Charset.forName("UTF_32BE_BOM").name());
        assertEquals("X-UTF-32LE-BOM", Charset.forName("UTF_32LE_BOM").name());
    }

    private byte[] toByteArray(int[] ints) {
        byte[] result = new byte[ints.length];
        for (int i = 0; i < ints.length; ++i) {
            result[i] = (byte) ints[i];
        }
        return result;
    }

    private void assertEncodes(Charset cs, String s, int... expectedByteInts) throws Exception {
        ByteBuffer out = cs.encode(s);
        byte[] bytes = new byte[out.remaining()];
        out.get(bytes);
        assertEquals(Arrays.toString(toByteArray(expectedByteInts)), Arrays.toString(bytes));
    }

    private void assertDecodes(Charset cs, String s, int... byteInts) throws Exception {
        ByteBuffer in = ByteBuffer.wrap(toByteArray(byteInts));
        CharBuffer out = cs.decode(in);
        assertEquals(s, out.toString());
    }
}
