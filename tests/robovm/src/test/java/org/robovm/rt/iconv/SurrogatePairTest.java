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

import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;

import org.junit.Test;

/**
 * Tests surrogate pairs
 */
public class SurrogatePairTest {

    @Test
    public void surrogatePairTest() throws Exception{
        // little endian encoding
        IconvProvider p = new IconvProvider();
        CharsetDecoder decoder = p.charsetForName("UTF-16LE").newDecoder();

        // Code point: 120120
        ByteBuffer bytes = ByteBuffer.wrap(new byte[] {
                (byte) 0x35, (byte) 0xD8, (byte) 0x38, (byte) 0xDD
        });

        String decoded = null;
        decoded = decoder.decode(bytes).toString();
        String weirdoChar = new String(Character.toChars(120120));
        assertTrue(weirdoChar.equals(decoded));
    }
}
