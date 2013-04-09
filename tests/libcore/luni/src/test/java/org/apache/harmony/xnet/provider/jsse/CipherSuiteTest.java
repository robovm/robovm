/*
 * Copyright (C) 2010 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;
import org.apache.harmony.xnet.provider.jsse.CipherSuite;

public class CipherSuiteTest extends TestCase {
    public void test_getByName() throws Exception {
        for (String name : StandardNames.CIPHER_SUITES) {
            if (name.equals(StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION)) {
                assertNull(CipherSuite.getByName(name));
            } else {
                test_CipherSuite(name);
            }
        }

        assertNull(CipherSuite.getByName("bogus"));
        try {
            CipherSuite.getByName(null);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    private void test_CipherSuite(String name) throws Exception {
        CipherSuite cs = CipherSuite.getByName(name);
        assertNotNull(name, cs);
        assertEquals(name, cs.getName());
        test_CipherSuite(cs);
    }

    private void test_CipherSuite(CipherSuite cs) throws Exception {
        assertNotNull(cs);

        String name = cs.getName();
        assertNotNull(name);
        assertSame(name, cs, CipherSuite.getByName(name));
        assertTrue(name, StandardNames.CIPHER_SUITES.contains(name));
        assertTrue(name, name.startsWith("SSL_") || name.startsWith("TLS_"));

        assertEquals(cs.isAnonymous(), name.contains("_anon_"));

        byte[] bytes = cs.toBytes();
        assertNotNull(name, bytes);
        assertEquals(name, 2, bytes.length);
        assertTrue(name + bytes[0], bytes[0] == (byte) 0x00 || bytes[0] == (byte) 0xc0);
        assertSame(name, cs, CipherSuite.getByCode(bytes[0], bytes[1]));
        assertSame(name, cs, CipherSuite.getByCode((byte) 0, bytes[0], bytes[1]));

        assertTrue(name, cs.toString().contains(name));

        String bulkEncryptionAlgorithm = cs.getBulkEncryptionAlgorithm();
        int blockSize = cs.getBlockSize();
        if (bulkEncryptionAlgorithm == null) {
            assertTrue(name, name.contains("_NULL_"));
            assertEquals(name, 0, blockSize);
        } else {
            assertNotNull(name, Cipher.getInstance(cs.getBulkEncryptionAlgorithm()));
            assertTrue(name, blockSize == 0 || blockSize == 8 || blockSize == 16);
        }

        String hmacName = cs.getHmacName();
        assertNotNull(name, hmacName);
        assertNotNull(name, Mac.getInstance(hmacName));

        String hashName = cs.getHashName();
        assertNotNull(name, hashName);
        assertNotNull(name, MessageDigest.getInstance(hashName));

        int macLength = cs.getMACLength();
        assertTrue(name, macLength == 0 || macLength == 16 || macLength == 20);

        assertTrue(name,
                   cs.isExportable() == name.contains("_EXPORT_")
                   || cs.isExportable() == name.contains("_NULL_"));

        String keyType = cs.getServerKeyType();
        assertEquals(name, cs.isAnonymous(), keyType == null);
        assertTrue(name, keyType == null || StandardNames.KEY_TYPES.contains(keyType));
    }

    public void test_getByCode() {
        // CipherSuite.getByCode is also covered by test_CipherSuite
        assertUnknown(CipherSuite.getByCode((byte) 0x12, (byte) 0x34));
        assertUnknown(CipherSuite.getByCode((byte) 0x12, (byte) 0x34, (byte) 0x56));
        assertUnknown(CipherSuite.getByCode((byte) -1, (byte) -1));
        assertUnknown(CipherSuite.getByCode((byte) -1, (byte) -1, (byte) -1));
    }
    private void assertUnknown(CipherSuite cs) {
        assertNotNull(cs);
        assertNotNull(cs.getName().contains("UNKNOWN"));
    }

    public void test_getSupported() throws Exception {
        CipherSuite[] suites = CipherSuite.getSupported();
        List<String> names = new ArrayList<String>(suites.length);
        for (CipherSuite cs : suites) {
            test_CipherSuite(cs);
            names.add(cs.getName());
        }
        assertEquals(Arrays.asList(CipherSuite.getSupportedCipherSuiteNames()), names);
    }

    public void test_getSupportedCipherSuiteNames() throws Exception {
        String[] names = CipherSuite.getSupportedCipherSuiteNames();
        StandardNames.assertSupportedCipherSuites(StandardNames.CIPHER_SUITES_SSLENGINE, names);
        for (String name : names) {
            test_CipherSuite(name);
        }
    }

    public void test_getClientKeyType() throws Exception {
        byte b = Byte.MIN_VALUE;
        do {
            String byteString = Byte.toString(b);
            String keyType = CipherSuite.getClientKeyType(b);
            switch (b) {
                case 1:
                    assertEquals(byteString, "RSA", keyType);
                    break;
                case 2:
                    assertEquals(byteString, "DSA", keyType);
                    break;
                case 3:
                    assertEquals(byteString, "DH_RSA", keyType);
                    break;
                case 4:
                    assertEquals(byteString, "DH_DSA", keyType);
                    break;
                case 64:
                    assertEquals(byteString, "EC", keyType);
                    break;
                case 65:
                    assertEquals(byteString, "EC_RSA", keyType);
                    break;
                case 66:
                    assertEquals(byteString, "EC_EC", keyType);
                    break;
                default:
                    assertNull(byteString, keyType);
            }
            b++;
        } while (b != Byte.MIN_VALUE);
    }
}
