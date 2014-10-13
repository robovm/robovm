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

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;

import org.junit.Test;

/**
 * Tests surrogate pairs
 */
public class SurrogatePairTest {

    @Test
    public void surrogatePairTest() {
        // little endian encoding
        IconvProvider p = new IconvProvider();
        CharsetDecoder decoder = p.charsetForName("UTF-16LE").newDecoder();

        // Code point: 120120
        ByteBuffer bytes = ByteBuffer.wrap(new byte[] {
                (byte) 0x35, (byte) 0xD8, (byte) 0x38, (byte) 0xDD
        });

        String decoded = null;
        try {
            decoded = decoder.decode(bytes).toString();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
        String weirdoChar = new String(Character.toChars(120120));
        assertTrue(weirdoChar.equals(decoded));
    }
}
