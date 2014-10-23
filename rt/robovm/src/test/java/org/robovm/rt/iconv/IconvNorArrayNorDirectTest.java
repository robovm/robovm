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

public class IconvNorArrayNorDirectTest {

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        String toEncode = "Hhhhöädglpdågplgdäglh";
        CharBuffer charBuffer = ByteBuffer.allocate(toEncode.length()*2).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocate(toEncode.length()*2);
        
        charBuffer.put(toEncode);
        charBuffer.position(0);
        
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
    public void testDecode() throws UnsupportedEncodingException {
        byte[] toDecode = {72, 104, 104, 104, -61, -74, -61, -92, 100, 103, 108, 112, 100, -61, -91, 103, 112, 108, 103, 100, -61, -92, 103, 108, 104};
        char[] decoded = new char[toDecode.length];
        
        ByteBuffer inBuffer = ByteBuffer.allocate(toDecode.length);
        CharBuffer outBuffer = ByteBuffer.allocate(toDecode.length*2).asCharBuffer();
        
        inBuffer.put(toDecode);
        inBuffer.position(0);
 
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newDecoder().decode(inBuffer, outBuffer, true);
        
        outBuffer.flip();
        outBuffer.get(decoded, 0, outBuffer.remaining());
        assertTrue(new String(decoded).trim().equals("Hhhhöädglpdågplgdäglh"));
    }

}
