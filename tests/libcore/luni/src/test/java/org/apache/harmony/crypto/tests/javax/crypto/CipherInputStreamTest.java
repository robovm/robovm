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

package org.apache.harmony.crypto.tests.javax.crypto;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NullCipher;

public class CipherInputStreamTest extends TestCase {

    /**
     * javax.crypto.CipherInputStream#read(byte[] b, int off, int len)
     */
    public void testReadBII() throws Exception {
        // Regression for HARMONY-1080
        CipherInputStream stream = new CipherInputStream(null, new NullCipher());
        try {
            stream.read(new byte[1], 1, 0);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * javax.crypto.CipherInputStream#close()
     */
    public void testClose() throws Exception {
        // Regression for HARMONY-1087
        try {
            new CipherInputStream(new ByteArrayInputStream(new byte[] { 1 }),
                    Cipher.getInstance("DES/CBC/PKCS5Padding")).close();
            fail("IllegalStateException expected!");
        } catch (IllegalStateException e) {
            // expected
        }
        try {
            new CipherInputStream(new BufferedInputStream((InputStream) null),
                    Cipher.getInstance("DES/CBC/PKCS5Padding")).close();
            fail("IllegalStateException expected!");
        } catch (IllegalStateException e) {
            // expected
        }
    }

}
