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

        assertTrue(cd != null);
        assertTrue(ce != null);
        
        assertTrue(ce instanceof IconvEncoder);
        assertTrue(cd instanceof IconvDecoder);
        
    }
}
