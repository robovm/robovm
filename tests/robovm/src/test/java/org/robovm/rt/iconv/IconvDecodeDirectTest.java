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
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for decoding direct buffers
 * 
 */
public class IconvDecodeDirectTest {

    @Test
    public void testIconvDecodeDirect() throws UnsupportedEncodingException {
        String s = "\u0077\u0078\u00F6";
        ByteBuffer in = ByteBuffer.allocateDirect(s.length() * 2);
        CharBuffer out = ByteBuffer.allocateDirect(s.length() * 4).asCharBuffer();

        in.put(s.getBytes("UTF-8"));
        in.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");

        cs.newDecoder().decode(in, out, true);
        out.flip();
        String outString = out.toString().trim();
        assertTrue("wxö".equals(outString));
    }

    @Test
    public void testIconvEncodeDecodeHybrid() {
        String s = "äsödfksöjgsoignduh g rguh irgh";
        CharBuffer in = ByteBuffer.allocateDirect(s.length() * 2).asCharBuffer();

        in.append(s);
        in.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        ByteBuffer out = null;
        try {
            out = cs.newEncoder().encode(in);
        } catch (CharacterCodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        
        CharBuffer outBuffer = null;
        try {
            outBuffer = cs.newDecoder().decode(out);
        } catch (CharacterCodingException e1) {
            fail(e1.getMessage());
        }

        String ss = outBuffer.toString();
        assertTrue(ss.equals(s));
    }

    @Test
    public void testIconvDecodeArraysBigBuffer() {
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

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = CharBuffer.allocate(5);
        char[] array = new char[5];

        String utf8String = null;
        CharsetDecoder decoder = cs.newDecoder();
        StringBuilder sb = new StringBuilder();
        try {
            CoderResult cr = null;
            do {
                cr = decoder.decode(byteBuffer, charBuffer, true);
                charBuffer.flip();

                charBuffer.get(array, 0, charBuffer.remaining());
                sb.append(new String(array));
                charBuffer.flip();
                charBuffer.clear();

                Arrays.fill(charBuffer.array(), (char) 0);
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        }
        return utf8String;
        
    }

}
