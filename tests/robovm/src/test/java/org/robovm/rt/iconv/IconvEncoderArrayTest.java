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

import org.junit.Test;

/**
 * Tests array backed encoders
 */
public class IconvEncoderArrayTest {

    @Test
    public void testIconvEncodeArraysUTF8() {

        String toEncode = "Hhhhöädglpdågplgdäglh";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
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
            fail(e.getMessage());
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
            fail(e.getMessage());
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
            fail(e.getMessage());
        }

        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);

        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
}
