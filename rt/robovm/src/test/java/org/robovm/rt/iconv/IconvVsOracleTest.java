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
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

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
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
        charBuffer.append(toEncode);
        charBuffer.rewind();

        byte[] array = new byte[5];

        String utf8String = null;
        CharsetEncoder encoder = cs.newEncoder();
        StringBuilder sb = new StringBuilder();
        try {
            CoderResult cr = null;
            do {
                cr = encoder.encode(charBuffer, byteBuffer, true);
                byteBuffer.flip();

                byteBuffer.get(array, 0, byteBuffer.remaining());
                sb.append(new String(array, "UTF-8"));
                byteBuffer.flip();
                byteBuffer.clear();
                // Arrays.fill(byteBuffer.array(), (byte) 0);
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
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
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byte[] array = new byte[5];

        String utf8String = null;
        CharsetEncoder encoder = cs.newEncoder();
        StringBuilder sb = new StringBuilder();
        try {
            CoderResult cr = null;
            do {
                cr = encoder.encode(charBuffer, byteBuffer, true);
                byteBuffer.flip();

                byteBuffer.get(array, 0, byteBuffer.remaining());
                sb.append(new String(array, "UTF-8"));
                byteBuffer.flip();
                byteBuffer.clear();
                Arrays.fill(byteBuffer.array(), (byte) 0);
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        }
        return utf8String;
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
