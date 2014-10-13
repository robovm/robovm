/*
 * Copyright (C) 2014 Trillian Mobile AB
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
        System.err.println("passed encoding");
        
        
        CharBuffer outBuffer = null;
        try {
            outBuffer = cs.newDecoder().decode(out);
        } catch (CharacterCodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String ss = outBuffer.toString();
        assertTrue(ss.equals(s));
    }

    @Test
    public void testIconvDecodeArraysBigBuffer() {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            String iconv = getSmallBufStringIconvDecode();
            String java = getSmallBufStringJavaDecode();
            assertEquals(iconv, java);
        }
    }

    private String getSmallBufStringIconvDecode() {
        byte[] bytes = new byte[] { 100, 101, 116, 32, 118, 97, 114, 32, 101, 110, 32, 103, -61, -91, 110, 103, 32,
                101, 110, 32, 118, -61, -92, 116, 116, 101, 32, 115, 111, 109, 32, 98, 111, 100, 100, 101, 32, 112,
                -61, -91, 32, 101, 110, 32, -61, -74, 32, 115, 111, 109, 32, 104, 101, 116, 116, 101, 32, 75, 111, 114,
                116, 101, 100, 97, 108, 97 };

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = CharBuffer.allocate(5);
        char[] array = new char[5];

        String utf8String = null;
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");

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
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
        
    }

    private String getSmallBufStringJavaDecode() {
        byte[] bytes = new byte[] { 100, 101, 116, 32, 118, 97, 114, 32, 101, 110, 32, 103, -61, -91, 110, 103, 32,
                101, 110, 32, 118, -61, -92, 116, 116, 101, 32, 115, 111, 109, 32, 98, 111, 100, 100, 101, 32, 112,
                -61, -91, 32, 101, 110, 32, -61, -74, 32, 115, 111, 109, 32, 104, 101, 116, 116, 101, 32, 75, 111, 114,
                116, 101, 100, 97, 108, 97 };

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = CharBuffer.allocate(5);
        char[] array = new char[5];

        String utf8String = null;
        Charset cs = Charset.forName("UTF-8");

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
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

}
