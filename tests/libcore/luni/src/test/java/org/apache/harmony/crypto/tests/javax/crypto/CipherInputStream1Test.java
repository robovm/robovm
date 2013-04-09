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

/**
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package org.apache.harmony.crypto.tests.javax.crypto;

import java.io.ByteArrayInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.NullCipher;
import javax.crypto.CipherInputStream;

import junit.framework.TestCase;

/**
 */
public class CipherInputStream1Test extends TestCase {

    private static class TestInputStream extends ByteArrayInputStream {
        private boolean closed = false;

        public TestInputStream(byte[] data) {
            super(data);
        }

        public void close() {
            closed = true;
        }

        public boolean wasClosed() {
            return closed;
        }
    }

    /**
     * CipherInputStream(InputStream is) method testing. Tests that
     * CipherInputStream uses NullCipher if Cipher is not specified
     * in the constructor.
     */
    public void testCipherInputStream() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis){};

        for (int i = 0; i < data.length; i++) {
            if ((byte) cis.read() != data[i]) {
                fail("NullCipher should be used "
                        + "if Cipher is not specified.");
            }
        }
        if (cis.read() != -1) {
            fail("NullCipher should be used if Cipher is not specified.");
        }
    }

    /**
     * read() method testing. Tests that method returns the correct value
     * (related to the InputStream) and that it returns -1 at the end of stream.
     */
    public void testRead1() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());
        byte res;
        for (int i = 0; i < data.length; i++) {
            if ((res = (byte) cis.read()) != data[i]) {
                fail("read() returned the incorrect value. " + "Expected: "
                        + data[i] + ", Got: " + res + ".");
            }
        }
        if (cis.read() != -1) {
            fail("read() should return -1 at the end of the stream.");
        }
    }

    /**
     * read(byte[] b) method testing. Tests that method returns the correct
     * value (related to the InputStream) and that it returns -1 at the end of
     * stream.
     */
    public void testRead2() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());

        int expected = data.length;
        byte[] result = new byte[expected];

        int ind = 0; // index into the data array (to check the got data)
        int got = cis.read(result); // the number of got bytes
        while (true) {
            for (int j = 0; j < got - ind; j++) {
                if (result[j] != data[ind + j]) {
                    fail("read(byte[] b) returned incorrect data.");
                }
            }
            if (got == expected) {
                break;
            } else if (got > expected) {
                fail("The data returned by read(byte[] b) "
                        + "is larger than expected.");
            } else {
                ind = got;
                got += cis.read(result);
            }
        }
        if (cis.read(result) != -1) {
            fail("read(byte[] b) should return -1 "
                    + "at the end of the stream.");
        }
    }

    /**
     * read(byte[] b, int off, int len) method testing. Tests that method
     * returns the correct value (related to the InputStream), that it discards
     * bytes in the case of null buffer, and that it returns -1 at the end of
     * stream.
     */
    public void testRead3() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());

        int expected = data.length;
        byte[] result = new byte[expected];

        int skip = 2;
        int ind = skip; // index into the data array (to check the got data)
        // should read and discard bytes;
        cis.read(null, 0, skip);
        int got = skip + cis.read(result, 0, 1); // the number of got bytes
        while (true) {
            for (int j = 0; j < got - ind; j++) {
                assertEquals("read(byte[] b, int off, int len) "
                        + "returned incorrect data.", result[j], data[ind + j]);
            }
            if (got == expected) {
                break;
            } else if (got > expected) {
                fail("The data returned by "
                        + "read(byte[] b, int off, int len) "
                        + "is larger than expected.");
            } else {
                ind = got;
                got += cis.read(result, 0, 3);
            }
        }
        if (cis.read(result, 0, 1) != -1) {
            fail("read() should return -1 at the end of the stream.");
        }
    }

    /**
     * skip(long n) method testing. Tests that the method correctly skips the
     * bytes.
     */
    public void testSkip() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());
        int expected = data.length;
        byte[] result = new byte[expected];

        int skipped = (int) cis.skip(2);
        int ind = skipped;
        int got = skipped + cis.read(result, 0, 1); // the number of got bytes
        while (true) {
            for (int j = 0; j < got - ind; j++) {
                if (result[j] != data[ind + j]) {
                    fail("read(byte[] b, int off, int len) "
                            + "returned incorrect data: Expected "
                            + data[ind + j] + ", got: " + result[j]);
                }
            }
            if (got == expected) {
                break;
            } else if (got > expected) {
                fail("The data returned by "
                        + "read(byte[] b, int off, int len) "
                        + "is larger than expected.");
            } else {
                ind = got;
                got += cis.read(result, 0, 1);
            }
        }
        if ((got = cis.read(result, 0, 1)) != -1) {
            fail("read() should return -1 at the end of the stream. "
                    + "Output is: " + got + ".");
        }
    }

    /**
     * available() method testing. Tests that the method always return 0.
     */
    public void testAvailable() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());
        assertEquals("The returned by available() method value "
                + "should be 0.", cis.available(), 0);
    }

    /**
     * close() method testing. Tests that the method calls the close()
     * method of the underlying input stream.
     */
    public void testClose() throws Exception {
        byte[] data = new byte[] { -127, -100, -50, -10, -1, 0, 1, 10, 50, 127 };
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());
        cis.close();
        assertTrue("The close() method should call the close() method "
                + "of its underlying input stream.", tis.wasClosed());
    }

    /**
     * markSupported() method testing. Tests that mark is not supported.
     */
    public void testMarkSupported() {
        byte[] data = new byte[] {-127, -100, -50, -10, -1, 0, 1, 10, 50, 127};
        TestInputStream tis = new TestInputStream(data);
        CipherInputStream cis = new CipherInputStream(tis, new NullCipher());
        assertFalse("The returned by markSupported() method value "
                + "should be false.", cis.markSupported());
    }

    public void test_ConstructorLjava_io_InputStreamLjavax_crypto_Cipher () throws
    NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[100]);

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56, new SecureRandom());
        Key key = kg.generateKey();

        Cipher c = Cipher.getInstance("DES/CBC/NoPadding");
        c.init(Cipher.ENCRYPT_MODE, key);

        CipherInputStream cis = new CipherInputStream(bais, c);

        assertNotNull(cis);
    }

}

