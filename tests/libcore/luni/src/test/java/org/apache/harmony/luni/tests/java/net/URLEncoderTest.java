/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.tests.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.UnsupportedCharsetException;
import junit.framework.TestCase;
import tests.support.Support_Configuration;

public class URLEncoderTest extends TestCase {

    /**
     * java.net.URLEncoder#encode(java.lang.String)
     */
    @SuppressWarnings("deprecation")
    public void test_encodeLjava_lang_String() {
        final String URL = "http://" + Support_Configuration.HomeAddress;
        final String URL2 = "telnet://justWantToHaveFun.com:400";
        final String URL3 = "file://myServer.org/a file with spaces.jpg";

        assertTrue("1. Incorrect encoding/decoding", URLDecoder.decode(
                URLEncoder.encode(URL)).equals(URL));
        assertTrue("2. Incorrect encoding/decoding", URLDecoder.decode(
                URLEncoder.encode(URL2)).equals(URL2));
        assertTrue("3. Incorrect encoding/decoding", URLDecoder.decode(
                URLEncoder.encode(URL3)).equals(URL3));
    }

    /**
     * URLEncoder#encode(String, String)
     */
    public void test_encodeLjava_lang_StringLjava_lang_String()
            throws Exception {
        // Regression for HARMONY-24
        try {
            URLEncoder.encode("str", "unknown_enc");
            fail("Assert 0: Should throw UEE for invalid encoding");
        } catch (UnsupportedEncodingException e) {
        } catch (UnsupportedCharsetException e) {
            // expected
        }

        // Regression for HARMONY-1233
        try {
            URLEncoder.encode(null, "harmony");
            fail("NullPointerException expected");
        } catch (NullPointerException expected) {
        } catch (UnsupportedCharsetException expected) {
        }
    }
}
