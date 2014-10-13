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

import org.junit.Test;

/**
 * Tests array backed decoders
 */
public class IconvDecoderArrayTest {

    @Test
    public void testIconvDecoderUTF8() throws Exception {
        String toDecode = "\u0078\u0079\u00D6";

        ByteBuffer byteBuffer = ByteBuffer.wrap(toDecode.getBytes("UTF-8"));

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        CharBuffer charBuffer = null;
        try {
            charBuffer = cs.newDecoder().decode(byteBuffer);
        } catch (CharacterCodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        String output = charBuffer.toString();
        assertTrue("xyÖ".equals(output));
    }

    @Test
    public void testIconvDecoderShiftJIS() {
        String s = "\uFF66\uFF73";

        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = ByteBuffer.wrap(s.getBytes("Shift_JIS"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        
        System.out.println(byteBuffer.isDirect());
        
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        CharBuffer charBuffer = null;
        try {
            charBuffer = cs.newDecoder().decode(byteBuffer);
        } catch (CharacterCodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        
        String output = charBuffer.toString();
        String checkValue = null;

        checkValue = new String("\uFF66\uFF73");

        assertTrue(output.equals(checkValue));
    }

}
