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

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.junit.Test;

/**
 * Simple tests
 */
public class IconvCharsetTest {

    @Test
    public void testIconvCharset() {
                   
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Big5");

        assertTrue(cs != null);
        assertTrue(cs instanceof IconvCharset);
        
        CharsetEncoder ce = null;
        CharsetDecoder cd = null;
        
        if (cs.canEncode()) {
            ce = cs.newEncoder();
            cd = cs.newDecoder();
        }
        
        ce = cs.newEncoder();
        cd = cs.newDecoder();
        
        assertTrue(ce instanceof IconvEncoder);
        assertTrue(cd instanceof IconvDecoder);
        
        assertTrue(cd != null);
        assertTrue(ce != null);
    }
}
