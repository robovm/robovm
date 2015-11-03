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

import org.junit.Test;

/**
 * Tests for decoding direct buffers
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
    public void testIconvEncodeDecodeHybrid() throws CharacterCodingException {
        String s = "äsödfksöjgsoignduh g rguh irgh";
        CharBuffer in = ByteBuffer.allocateDirect(s.length() * 2).asCharBuffer();

        in.append(s);
        in.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        ByteBuffer out = null;

        out = cs.newEncoder().encode(in);

        CharBuffer outBuffer = null;
        outBuffer = cs.newDecoder().decode(out);

        String ss = outBuffer.toString();
        assertTrue(ss.equals(s));
    }

}
