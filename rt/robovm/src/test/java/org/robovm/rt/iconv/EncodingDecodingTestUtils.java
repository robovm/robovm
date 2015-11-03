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

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Shared operations among tests for encoding decoding
 */
public class EncodingDecodingTestUtils {

    public static final int SMALL_BUFF_SIZE = 5;
    
    /**
     * decodes data
     * @param cs
     * @param byteBuffer
     * @param charBuffer
     * @return
     */
    public static String decode(Charset cs, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        CharsetDecoder decoder = cs.newDecoder();
        StringBuilder sb = new StringBuilder();

        CoderResult cr = null;
        do {
            cr = decoder.decode(byteBuffer, charBuffer, true);
            charBuffer.flip();
            
            sb.append(charBuffer);
            charBuffer.clear();
        } while (cr.isOverflow());
        
        return sb.toString();
    }
    
    /**
     * encodes data 
     * @param cs
     * @param charBuffer
     * @param byteBuffer
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(Charset cs, CharBuffer charBuffer, ByteBuffer byteBuffer, int byteBufferCapacity) throws UnsupportedEncodingException {
        CharsetEncoder encoder = cs.newEncoder();
        StringBuilder sb = new StringBuilder();
        
        byte[] array = new byte[byteBufferCapacity];
        CoderResult cr = null;
        do {
            cr = encoder.encode(charBuffer, byteBuffer, true);
            byteBuffer.flip();

            byteBuffer.get(array, 0, (charBuffer.remaining() > byteBufferCapacity) ? byteBufferCapacity : charBuffer.remaining());
            sb.append(new String(array, cs));
            byteBuffer.clear();

        } while (cr.isOverflow());
        return sb.toString();
    }
    
}
