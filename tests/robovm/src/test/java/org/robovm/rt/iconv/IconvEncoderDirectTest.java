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
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests encoding of direct buffers
 */
public class IconvEncoderDirectTest {

    @Before
    public void init() {
    }

    @Test
    public void testIconvEncodeDirect() throws UnsupportedEncodingException {
        String s = "äsödfksöjgsoignduh g rguh irgh";
        CharBuffer in = ByteBuffer.allocateDirect(s.length() * 2).asCharBuffer();
        ByteBuffer out = ByteBuffer.allocateDirect(s.length() * 2);
        in.append(s);
        in.position(0);
        out.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(in, out, true);

        out.flip();
        byte[] bb = new byte[out.remaining()];
        out.get(bb);
        String ss = null;
        ss = new String(bb, "UTF-8");

        assertTrue(ss.equals(s));
    }

    @Test
    public void testIconvEncodeDirectSwiftJIS() throws UnsupportedEncodingException {
        String s = "\uFF66\uFF73";
        CharBuffer in = ByteBuffer.allocateDirect(s.length() * 2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer out = ByteBuffer.allocateDirect(s.length() * 3);
        in.append(s);
        in.position(0);
        out.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        cs.newEncoder().encode(in, out, true);

        out.flip();
        byte[] byteArray = new byte[out.remaining()];
        out.get(byteArray);
        String ss = null;
        ss = new String(byteArray, "Shift_JIS");
 
        assertTrue(ss.equals(s));
    }

    @Test
    public void testIconvEncodeDirectNoErrorHandling() throws UnsupportedEncodingException {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
        String s = "lsdflsjfdösfäefk sdf jsfäsdfkäsökdf sdf hsdjfh sdfösädfi södfjs fd";
        CharBuffer charBuffer = ByteBuffer.allocateDirect(s.length() * 2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(s.length() * 3);
        charBuffer.append(s);
        charBuffer.position(0);
        byteBuffer.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        byteBuffer.flip();
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        String ss = null;
        ss = new String(byteArray, "UTF-8");

        assertTrue(ss.equals(s));
    }

    @Test
    public void testIconvEncodeEmptyInBuffer() {

        String s = "";
        CharBuffer charBuffer = ByteBuffer.allocateDirect(s.length() * 2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(s.length() * 3);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        charBuffer = CharBuffer.allocate(s.length());
        byteBuffer.position(0);

        charBuffer.flip();
        String output = charBuffer.toString();

        byteBuffer.flip();
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);

        assertTrue(s.equals(output));
    }
}
