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

import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

public class CipherOutputStreamTest extends junit.framework.TestCase {

    /**
     * javax.crypto.CipherOutputStream#close()
     */
    public void test_close() throws Exception {
        // regression test for HARMONY-1139
        try {
            new CipherOutputStream((OutputStream) null, Cipher
                    .getInstance("DES/CBC/PKCS5Padding")).close();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // expected
        }

        CipherOutputStream ch = new CipherOutputStream((OutputStream) null) {};
        try {
            new CipherOutputStream(ch, Cipher
                    .getInstance("DES/CBC/PKCS5Padding")).close();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // expected
        }
    }
}
