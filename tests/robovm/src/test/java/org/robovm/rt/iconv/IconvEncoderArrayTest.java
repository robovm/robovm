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
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * Tests array backed encoders
 * 
 */
public class IconvEncoderArrayTest {

    @Test
    public void testIconvEncodeArraysUTF8() {

        String toEncode = "Hhhhöädglpdågplgdäglh";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        if (!byteBuffer.hasArray()) {
            System.out.println("how did this happen?");
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);

        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }

    @Test
    public void testIconvEncodeEmptyInBuffer() {

        String toEncode = "";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);

        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }

    @Test
    public void testIconvEncodeArraysShiftJIS() {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
        String toEncode = "\uFF66\uFF73";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "Shift_JIS");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);

        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }

    @Test
    public void testIconvEncodeArraysNoErrorHandling() {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
        String toEncode = "lsdflsjfdösfäefk sdf jsfäsdfkäsökdf sdf hsdjfh sdfösädfi södfjs fd";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocate(toEncode.length() * 2);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");

        try {
            cs.newEncoder().encode(charBuffer, byteBuffer, true);
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);

        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
}
