/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class which test Oracle Charsets vs IconvCharsets
 */
public class IconvVsOracleTest {

    @Test
    public void testIconvEncodeDirectNoErrorHandlingBigBuffer() throws Exception {
        Charset csBuiltIn = Charset.forName("UTF-8");
        Charset csIconv = new IconvProvider().charsetForName("UTF-8");

        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            String java = getSmallBufStringDirect(csBuiltIn);
            String iconv = getSmallBufStringDirect(csIconv);
            assertTrue(iconv.equals(java));
        }
    }

    private String getSmallBufStringDirect(Charset cs) throws UnsupportedEncodingException {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        String toEncode = "det var en gång en vätte";

        CharBuffer charBuffer = ByteBuffer.allocateDirect(toEncode.length() * 2).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(EncodingDecodingTestUtils.SMALL_BUFF_SIZE);
        charBuffer.append(toEncode);
        charBuffer.rewind();
        
        return EncodingDecodingTestUtils.encode(cs, charBuffer, byteBuffer, EncodingDecodingTestUtils.SMALL_BUFF_SIZE);
    }

    @Test
    public void testIconvEncodeArraysNoErrorHandlingBigBuffer() throws Exception {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            Charset csBuiltIn = Charset.forName("UTF-8");
            Charset csIconv = new IconvProvider().charsetForName("UTF-8");
            String iconv = getSmallBufStringArray(csIconv);
            String java = getSmallBufStringArray(csBuiltIn);
            assertTrue(iconv.equals(java));
        } else {
            assertTrue(true);
        }
    }

    private String getSmallBufStringArray(Charset cs) {
        String toEncode = "det var en gång en vätte som bodde på en ö som hette Kortedala";

        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocate(EncodingDecodingTestUtils.SMALL_BUFF_SIZE);

        return EncodingDecodingTestUtils.decode(cs, byteBuffer, charBuffer);
    }
    
    @Test
    public void testCompareIconvVsOracleDecoderWithBigInBufferAndSmallOutBuffer() {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            Charset csIconv = new IconvProvider().charsetForName("UTF-8");
            Charset builtIn = Charset.forName("UTF-8");
            String iconv = getSmallBufStringToDecode(csIconv);
            String oracle = getSmallBufStringToDecode(builtIn);
            assertEquals(iconv, oracle);
        }
    }

    private String getSmallBufStringToDecode(Charset cs) {
        byte[] bytes = new byte[] { 100, 101, 116, 32, 118, 97, 114, 32, 101, 110, 32, 103, -61, -91, 110, 103, 32,
                101, 110, 32, 118, -61, -92, 116, 116, 101, 32, 115, 111, 109, 32, 98, 111, 100, 100, 101, 32, 112,
                -61, -91, 32, 101, 110, 32, -61, -74, 32, 115, 111, 109, 32, 104, 101, 116, 116, 101, 32, 75, 111, 114,
                116, 101, 100, 97, 108, 97 };

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bytes.length);
        CharBuffer charBuffer = ByteBuffer.allocateDirect(EncodingDecodingTestUtils.SMALL_BUFF_SIZE).asCharBuffer();

        return EncodingDecodingTestUtils.decode(cs, byteBuffer, charBuffer);        
    }

    @Test
    @Ignore
    public void testCharsetsOracleVsIconv() {
        @SuppressWarnings("rawtypes")
        Iterator it = new IconvProvider().charsets();
        
        while (it.hasNext()) {
            Charset iconvCharset = (Charset) it.next();
            Charset oracleCharset = Charset.forName(iconvCharset.name());
            
            CharsetEncoder iconvEncoder = null;
            CharsetEncoder oracleEncoder = null;
            try {
                iconvEncoder = iconvCharset.newEncoder();
                oracleEncoder = oracleCharset.newEncoder();
                //some obscure encoders may fail during init 
            } catch (IllegalArgumentException e) {
                continue;
            }

            for (int code = 32; code <= 65533; code ++) {
                if (!(iconvEncoder.canEncode((char) code) == oracleEncoder.canEncode((char) code))) {
                    System.out.println("oracle: " + oracleEncoder.canEncode((char) code) + " iconv: "+ iconvEncoder.canEncode((char) code) + " " + iconvCharset.name() + ":" + code);
                }
            }
        }
    }
}
