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
        charBuffer = cs.newDecoder().decode(byteBuffer);

        String output = charBuffer.toString();
        assertTrue("xyÖ".equals(output));
    }

    @Test
    public void testIconvDecoderShiftJIS() throws UnsupportedEncodingException, CharacterCodingException {
        String s = "\uFF66\uFF73";

        ByteBuffer byteBuffer = null;
        byteBuffer = ByteBuffer.wrap(s.getBytes("Shift_JIS"));

        
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        CharBuffer charBuffer = null;
        charBuffer = cs.newDecoder().decode(byteBuffer);
   
        String output = charBuffer.toString();
        
        assertTrue(output.equals(s));
    }


}
