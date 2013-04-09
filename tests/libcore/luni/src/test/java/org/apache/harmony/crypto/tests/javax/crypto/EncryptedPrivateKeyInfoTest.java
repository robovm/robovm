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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package org.apache.harmony.crypto.tests.javax.crypto;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.AlgorithmParametersSpi;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.harmony.crypto.tests.support.EncryptedPrivateKeyInfoData;

import junit.framework.TestCase;

/**
 * Test for EncryptedPrivateKeyInfo class.
 *
 * All binary data for this test were generated using BEA JRockit j2sdk1.4.2_04
 * (http://www.bea.com) with security providers list extended by Bouncy Castle's
 * one (http://www.bouncycastle.org)
 */
public class EncryptedPrivateKeyInfoTest extends TestCase {

    private static final Provider[] provider = Security.getProviders();

    /**
     * Algorithm names/transformations used in roundtrip tests of
     * getKeySpec(...) methods
     */
    private static final String[][] algName = {
    // AES
            { "AES", null},
            //            {"AES", "AES/ECB/PKCS5Padding"},
            //            {"AES", "AES/CBC/PKCS5Padding"},
            //            {"AES", "AES/OFB/PKCS5Padding"},
            //            {"AES", "AES/CFB/PKCS5Padding"},
            //            {"2.16.840.1.101.3.4.1.1", null},
            //            {"2.16.840.1.101.3.4.1.2", null},
            //            {"2.16.840.1.101.3.4.1.3", null},
            //            {"2.16.840.1.101.3.4.1.4", null},
            //            {"2.16.840.1.101.3.4.1.5", null},
            //            {"2.16.840.1.101.3.4.1.21", null},
            //            {"2.16.840.1.101.3.4.1.22", null},
            //            {"2.16.840.1.101.3.4.1.23", null},
            //            {"2.16.840.1.101.3.4.1.24", null},
            //            {"2.16.840.1.101.3.4.1.25", null},
            //            {"2.16.840.1.101.3.4.1.41", null},
            //            {"2.16.840.1.101.3.4.1.42", null},
            //            {"2.16.840.1.101.3.4.1.43", null},
            //            {"2.16.840.1.101.3.4.1.44", null},
            //            {"2.16.840.1.101.3.4.1.45", null},

            // Blowfish
            // NO OIDs for Blowfish defined (?)
            { "Blowfish", null },
            //            {"Blowfish","Blowfish/CBC/PKCS5Padding"},
            //            {"Blowfish","Blowfish/CFB/PKCS5Padding"},
            //            {"Blowfish","Blowfish/OFB/PKCS5Padding"},
            //            {"Blowfish","Blowfish/PCBC/PKCS5Padding"},

            // DES: OIW OIDs only
            // {iso(1) identified-organization(3) oiw(14) secsig(3)
            // algorithms(2) desECB(6)}
            // 1.3.14.3.2.6
            // 1.3.14.3.2.7
            // 1.3.14.3.2.8
            // 1.3.14.3.2.9
            { "DES", null },
            //            {"DES", "DES/CBC/PKCS5Padding"},
            //            {"DES","DES/CFB/PKCS5Padding"},
            //            {"DES","DES/OFB/PKCS5Padding"},
            //            {"DES","DES/PCBC/PKCS5Padding"},

            // DESede (=TripleDes)
            //{iso(1) identified-organization(3) oiw(14) secsig(3)
            // algorithms(2) desEDE(17)}
            // 1.3.14.3.2.17
            //            {"DESede",null},
            //            {"DESede","DESede/CBC/PKCS5Padding"},
            //            {"DESede","DESede/CFB/PKCS5Padding"},
            //            {"DESede","DESede/OFB/PKCS5Padding"},
            //            {"DESede","DESede/PCBC/PKCS5Padding"},
            { "TripleDES", null },
            //            {"TripleDES","TripleDES/CBC/PKCS5Padding"},
            //            {"TripleDES","TripleDES/CFB/PKCS5Padding"},
            //            {"TripleDES","TripleDES/OFB/PKCS5Padding"},
            //            {"TripleDES","TripleDES/PCBC/PKCS5Padding"},

            // PBEWith<digest>And<encryption>
            { "PBEWithMD5AndTripleDES", null },
            // {iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs-5(5)
            // pbeWithMD5AndDES-CBC(3)}
            { "PBEWithMD5AndDES", "PBEWithMD5AndDES/CBC/PKCS5Padding", "PBEWithMD5AndDES"},
            { "PBEWithMD5AndDES", null, "PBEWithMD5AndDES"}, { "PBEWithHmacSHA1AndDESede", null },
            // more oids:
            // {iso(1) member-body(2) us(840) nortelnetworks(113533) entrust(7)
            // algorithms(66) pbeWithMD5AndCAST5-CBC(12)}
            //
            // also named pbeWithSHAAnd128BitRC4, pbeWithSHA1And128BitRC4:
            // {iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs-12(12)
            // pkcs-12-PbeIds(1) pkcs-12-OfflineTransportMode(1)}
            //
            // {iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs-12(12)
            // pkcs-12-PbeIds(1)} +
            // pbeWithSHAAnd40BitRC4(2) pbeWithSHAAnd3-KeyTripleDES-CBC(3)
            // pbeWithSHAAnd2-KeyTripleDES-CBC(4) pbeWithSHAAnd128BitRC2-CBC(5)
            // pbeWithSHAAnd40BitRC2-CBC(6)

            // DiffieHellman
            { "DiffieHellman", null }, // 1.2.840.10046.2.1
            //            {"DH",null}, // 1.2.840.10046.2.1
            //            {"1.2.840.113549.1.3.1", null},

            { "DSA", null }, // 1.2.840.10040.4.1

            { "RC2", null },

            { "RC4", null },

            { "RC5", null },

            //            {"1.2.840.113549.1.12.1.1",null},
            //            {"1.2.840.113549.1.12.1.2",null},
            //{ "1.2.840.113549.1.12.1.3", null, "PBEWithSHA1AndDESede"},
            //{ "PBEWithSHA1AndDESede", null, "PBEWithSHA1AndDESede"},
    //            {"1.2.840.113549.1.12.1.4",null},
    //            {"1.2.840.113549.1.12.1.5",null},
    //            {"1.2.840.113549.1.12.1.6",null},
    //            {"ELGAMAL/PKCS1", "ELGAMAL/ECB/PKCS1PADDING"},
    //            {"ELGAMAL/PKCS1","ELGAMAL/NONE/PKCS1PADDING"},
    //            {"PBEWITHSHAAND3-KEYTRIPLEDES-CBC", null},
    //            {"PBEWITHSHA1ANDDESEDE", null},
    //            {"PBEWithSHAAnd3KeyTripleDES",null},
    //            {"PBEWITHSHAAND3-KEYTRIPLEDES-CBC",null},
    //
    //            {"RC5-32",null},
    //
    //            {"RSA/1", "RSA/1/PKCS1PADDING"},
    //            {"RSA/2", "RSA/2/PKCS1PADDING"},
    //            {"RSA/ISO9796-1", "RSA/ECB/ISO9796-1PADDING"},
    //            {"RSA", "RSA/ECB/NOPADDING"},
    //            {"RSA/OAEP", "RSA/ECB/OAEPPADDING"},
    //            {"RSA/PKCS1", "RSA/ECB/PKCS1PADDING"},
    //            {"RSA/ISO9796-1", "RSA/NONE/ISO9796-1PADDING"},
    //            {"RSA", "RSA/NONE/NOPADDING"},
    //            {"RSA/OAEP", "RSA/NONE/OAEPPADDING"},
    //            {"RSA/PKCS1", "RSA/NONE/PKCS1PADDING"},
    //            {"RSA",null}, // 1.2.840.113549.1.1.1
    //            {"1.2.840.113549.1.1.1", null},
    };

    @Override protected void setUp() throws Exception {
        super.setUp();
    }

    public void test_getAlgName () {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            try {
                // generate test data
                TestDataGenerator g = new TestDataGenerator(algName[i][0],
                        algName[i][1], privateKeyInfoDamaged, null);

                // create test object
                EncryptedPrivateKeyInfo epki;
                if (g.ap() == null) {
                    epki = new EncryptedPrivateKeyInfo(algName[i][0], g.ct());
                } else {
                    epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                }

                // call methods under test
                if (algName[i].length == 3) {
                    assertEquals(algName[i][2], epki.getAlgName());
                }

                performed = true;
            } catch (TestDataGenerator.AllowedFailure allowedFailure) {
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #1 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: creates <code>EncryptedPrivateKeyInfo</code> instance <br>
     * Test preconditions: valid parameters passed <br>
     * Expected: must pass without any exceptions
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public final void testEncryptedPrivateKeyInfobyteArray1() throws Exception {
        new EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfoData
                .getValidEncryptedPrivateKeyInfoEncoding("DH"));
    }

    /**
     * Test #2 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: <code>NullPointerException</code> if encoding is
     * <code>null</code><br>
     * Test preconditions: <code>null</code> passed as a parameter <br>
     * Expected: <code>NullPointerException</code>
     *
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfobyteArray2()
            throws IOException {
        try {
            new EncryptedPrivateKeyInfo(null);
            fail(getName() + ": NullPointerException has not been thrown");
        } catch (NullPointerException ok) {
        }
    }

    /**
     * Test #3 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: <code>IOException</code> if encoding is wrong <br>
     * Test preconditions: wrong encoding passed as a parameter <br>
     * Expected: <code>IOException</code>
     */
    public final void testEncryptedPrivateKeyInfobyteArray3() {
        try {
            new EncryptedPrivateKeyInfo(new byte[0]);
            fail(getName() + ": IOException has not been thrown");
        } catch (IOException ok) {
        }
    }

    /**
     * Test #4 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: <code>IOException</code> if encoding is wrong <br>
     * Test preconditions: wrong encoding passed as a parameter <br>
     * Expected: <code>IOException</code>
     */
    public final void testEncryptedPrivateKeyInfobyteArray4() {
        try {
            new EncryptedPrivateKeyInfo(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10 });
            fail(getName() + ": IOException has not been thrown");
        } catch (IOException ok) {
        }
    }

    /**
     * Test #5 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: <code>IOException</code> if encoding is wrong <br>
     * Test preconditions: wrong encoding passed as a parameter <br>
     * Expected: <code>IOException</code>
     */
    public final void testEncryptedPrivateKeyInfobyteArray5() throws Exception {
        byte[] enc = null;
        try {
            // 1: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");

            // ... and corrupt it (set wrong alg OID length)
            enc[9] = (byte) 6;

            new EncryptedPrivateKeyInfo(enc);
            fail(getName() + "(1): IOException has not been thrown");
        } catch (IOException ok) {
        }

        try {
            // 2: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");
            // ... and corrupt it (set wrong encrypted data tag)
            enc[307] = (byte) 6;
            new EncryptedPrivateKeyInfo(enc);
            fail(getName() + "(2): IOException has not been thrown");
        } catch (IOException ok) {
        }

        try {
            // 3: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");
            // ... and corrupt it (set wrong encrypted data length)
            enc[310] = (byte) 1;
            new EncryptedPrivateKeyInfo(enc);
            fail(getName() + "(3): IOException has not been thrown");
        } catch (IOException ok) {
        }

        try {
            // 4: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");
            // ... and corrupt it (set wrong tag for alg params sequence)
            enc[17] = (byte) 0x29;
            EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(enc);

            if (epki.getAlgParameters() == null) {
                // This kind of encoding corruption can
                // be only determined while AlgorithmParameters
                // initialization BUT No AlgorithmParameters instance
                // available for algName0[i][0].
                // So just skip this sub test
            } else {
                fail(getName() + "(4): IOException has not been thrown");
            }

        } catch (IOException ok) {
        }

        try {
            // 5: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");
            // ... and corrupt it (set wrong length for alg params sequence)
            enc[20] = (byte) 0x1d;
            new EncryptedPrivateKeyInfo(enc);
            fail(getName() + "(5): IOException has not been thrown");
        } catch (IOException ok) {
        }

        try {
            // 6: get valid encoding
            enc = EncryptedPrivateKeyInfoData
                    .getValidEncryptedPrivateKeyInfoEncoding("DSA");
            // ... and corrupt it (set wrong length for alg params sequence)
            enc[20] = (byte) 0x1f;
            new EncryptedPrivateKeyInfo(enc);
            fail(getName() + "(6): IOException has not been thrown");
        } catch (IOException ok) {
        }
    }

    /**
     * Test #6 for <code>EncryptedPrivateKeyInfo(byte[])</code> constructor
     * <br>
     * Assertion: byte array is copied to prevent subsequent modification <br>
     * Test preconditions: valid array passed then modified <br>
     * Expected: getEncoded(), invoked after above modification, must return
     * array as it was before the modification
     *
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfobyteArray6() throws Exception {
        byte[] encoded = EncryptedPrivateKeyInfoData
                .getValidEncryptedPrivateKeyInfoEncoding("DSA");
        byte[] encodedCopy = encoded.clone();
        // pass valid array
        EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(encodedCopy);
        // modify array passed
        encodedCopy[9] = (byte) 6;
        // check that internal state has not been affected
        assertTrue(Arrays.equals(encoded, epki.getEncoded()));
    }

    /**
     * Test #1 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: creates <code>EncryptedPrivateKeyInfo</code> instance <br>
     * Test preconditions: valid parameters passed <br>
     * Expected: must pass without any exceptions
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray1() {
        boolean performed = false;

        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);
                performed = true;
            } catch (NoSuchAlgorithmException allowed) {
            }
        }

        assertTrue("Test not performed", performed);
    }

    /**
     * Test #2 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: <code>NoSuchAlgorithmException</code>- if the specified
     * algorithm is not supported <br>
     * Test preconditions: pass nonexistent algorithm name <br>
     * Expected: <code>NoSuchAlgorithmException</code>
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray2() {
        try {
            new EncryptedPrivateKeyInfo("bla-bla",
                    EncryptedPrivateKeyInfoData.encryptedData);
            fail(getName() + ": NoSuchAlgorithmException has not been thrown");
        } catch (NoSuchAlgorithmException ok) {
        }

        try {
            new EncryptedPrivateKeyInfo("",
                    EncryptedPrivateKeyInfoData.encryptedData);
            fail(getName() + ": NoSuchAlgorithmException has not been thrown");
        } catch (NoSuchAlgorithmException ok) {
        }
    }

    /**
     * Test #3 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: <code>NullPointerException</code>- if the specified
     * algorithm or encrypted data is <code>null</code><br>
     * Test preconditions: pass <code>null</code> as algorithm name then as
     * encrypted data <br>
     * Expected: <code>NullPointerException</code> in both cases
     *
     * @throws NoSuchAlgorithmException
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray3()
            throws NoSuchAlgorithmException {
        // pass null as name
        try {
            new EncryptedPrivateKeyInfo((String) null,
                    EncryptedPrivateKeyInfoData.encryptedData);
            fail(getName() + ": NullPointerException has not been thrown");
        } catch (NullPointerException ok) {
        }

        // pass null as encrypted data
        try {
            new EncryptedPrivateKeyInfo("DSA", null);
            fail(getName() + ": NullPointerException has not been thrown");
        } catch (NullPointerException ok) {
        }
    }

    /**
     * Test #4 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: <code>IllegalArgumentException</code>- if encrypted data is
     * empty, i.e. 0-length <br>
     * Test preconditions: pass empty encrypted data <br>
     * Expected: <code>IllegalArgumentException</code>
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray4()
            throws Exception {
        try {
            new EncryptedPrivateKeyInfo("DSA", new byte[] {});
            fail(getName() + ": IllegalArgumentException has not been thrown");
        } catch (IllegalArgumentException ok) {
        }
    }

    /**
     * Test #5 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: byte array is copied to prevent subsequent modification <br>
     * Test preconditions: valid array passed then modified <br>
     * Expected: getEncryptedData(), invoked after above modification, must
     * return array as it was before the modification
     *
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray5()
            throws Exception {
        byte[] encryptedDataCopy = EncryptedPrivateKeyInfoData.encryptedData
                .clone();
        // pass valid array
        EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo("DSA",
                encryptedDataCopy);
        // modify array passed
        encryptedDataCopy[0] = (byte) 6;
        // check that internal state has not been affected
        assertTrue(Arrays.equals(EncryptedPrivateKeyInfoData.encryptedData,
                epki.getEncryptedData()));
    }

    /**
     * javax/crypto/EncryptedPrivateKeyInfo(String, byte[])
     * Checks exception order
     */
    public final void testEncryptedPrivateKeyInfoStringbyteArray6() {
        //Regression for HARMONY-768
        try {
            new EncryptedPrivateKeyInfo("0", new byte[] {});
            fail("NoSuchAlgorithmException expected");
        } catch (NoSuchAlgorithmException e) {
            //expected
        }
    }

    class Mock_AlgorithmParameters extends AlgorithmParameters {
        protected Mock_AlgorithmParameters(AlgorithmParametersSpi paramSpi, Provider provider, String algorithm) {
            super(paramSpi, provider, algorithm);
        }
    }

/**
     * Test #1 for
     * <code>EncryptedPrivateKeyInfo(java.security.AlgorithmParameters, byte[])
     * </code>
     * constructor <br>
     * Assertion: creates <code>EncryptedPrivateKeyInfo</code> instance <br>
     * Test preconditions: valid parameters passed <br>
     * Expected: must pass without any exceptions
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersbyteArray1()
            throws IOException, NoSuchAlgorithmException {
        AlgorithmParameters ap = null;

        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                ap = AlgorithmParameters
                        .getInstance(EncryptedPrivateKeyInfoData.algName0[i][0]);
                // use pregenerated AlgorithmParameters encodings
                ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding(
                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                new EncryptedPrivateKeyInfo(ap,
                        EncryptedPrivateKeyInfoData.encryptedData);

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);

        ap = new Mock_AlgorithmParameters(null, null, "Wrong alg name");

        try {
            new EncryptedPrivateKeyInfo(ap,
                EncryptedPrivateKeyInfoData.encryptedData);
            fail("NoSuchAlgorithmException expected");
        } catch (NoSuchAlgorithmException e) {
            //expected
        }
    }

    /**
     * Test #2 for
     * <code>EncryptedPrivateKeyInfo(java.security.AlgorithmParameters, byte[])
     * </code>
     * constructor <br>
     * Assertion: <code>NullPointerException</code>- if the specified
     * algorithm parameters or encrypted data is <code>null</code><br>
     * Test preconditions: pass <code>null</code> as algorithm parameters then
     * as encrypted data <br>
     * Expected: <code>NullPointerException</code> in both cases
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersbyteArray2()
            throws NoSuchAlgorithmException, IOException {
        // 1: pass null as AlgorithmParameters
        try {
            new EncryptedPrivateKeyInfo((AlgorithmParameters) null,
                    EncryptedPrivateKeyInfoData.encryptedData);
            fail(getName() + ": NullPointerException has not been thrown");
        } catch (NullPointerException ok) {
        }

        // 2: pass null as encrypted data
        try {
            AlgorithmParameters ap = AlgorithmParameters.getInstance("DSA");
            // use pregenerated AlgorithmParameters encodings
            ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding("DSA"));
            new EncryptedPrivateKeyInfo(ap, null);
            fail(getName() + ": NullPointerException has not been thrown");
        } catch (NullPointerException ok) {
        }
    }

    /**
     * Test #3 for
     * <code>EncryptedPrivateKeyInfo(java.security.AlgorithmParameters, byte[])
     * </code>
     * constructor <br>
     * Assertion: <code>IllegalArgumentException</code>- if encrypted data is
     * empty, i.e. 0-length <br>
     * Test preconditions: pass empty encrypted data <br>
     * Expected: <code>IllegalArgumentException</code>
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersbyteArray3()
            throws Exception {
        try {
            AlgorithmParameters ap = AlgorithmParameters.getInstance("DSA");
            // use pregenerated AlgorithmParameters encodings
            ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding("DSA"));

            new EncryptedPrivateKeyInfo(ap, new byte[] {});
            fail(getName() + ": IllegalArgumentException has not been thrown");

        } catch (IllegalArgumentException ok) {
        }
    }

    /**
     * Test #4 for
     * <code>EncryptedPrivateKeyInfo(java.security.AlgorithmParameters, byte[])
     * </code>
     * constructor <br>
     * Assertion: byte array is copied to prevent subsequent modification <br>
     * Test preconditions: valid array passed then modified <br>
     * Expected: getEncryptedData(), invoked after above modification, must
     * return array as it was before the modification
     *
     * @throws IOException
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersbyteArray4()
            throws Exception {
        AlgorithmParameters ap = AlgorithmParameters.getInstance("DSA");
        // use pregenerated AlgorithmParameters encodings
        ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding("DSA"));

        byte[] encryptedDataCopy = EncryptedPrivateKeyInfoData.encryptedData.clone();
        // pass valid array
        EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(ap,
                encryptedDataCopy);

        // modify array passed
        encryptedDataCopy[0] = (byte) 6;

        // check that internal state has not been affected
        assertTrue(Arrays.equals(EncryptedPrivateKeyInfoData.encryptedData,
                epki.getEncryptedData()));
    }

    /**
     * Test #1 for <code>getAlgParameters()</code> method <br>
     * Assertion: returns the algorithm parameters <br>
     * Test preconditions: test object created using ctor which takes encoded
     * form as the only parameter; encoded form passed contains algorithm
     * parameters encoding <br>
     * Expected: corresponding algorithm parameters must be returned
     *
     * @throws IOException
     */
    public final void testGetAlgParameters01() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData
                                .getValidEncryptedPrivateKeyInfoEncoding(
                                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                AlgorithmParameters apar = epki.getAlgParameters();
                if (apar == null) {
                    continue;
                }

                // check that method under test returns
                // parameters with the same encoded form
                assertTrue(Arrays
                        .equals(
                                EncryptedPrivateKeyInfoData
                                        .getParametersEncoding(EncryptedPrivateKeyInfoData.algName0[i][0]),
                                apar.getEncoded()));
                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    public final void testGetAlgParameters01_01() throws Exception {
        byte[] validEncodingWithUnknownAlgOID = EncryptedPrivateKeyInfoData
                .getValidEncryptedPrivateKeyInfoEncoding("DH");
        // correct oid value
        validEncodingWithUnknownAlgOID[18] = 0;
        EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                validEncodingWithUnknownAlgOID);

        assertNull(epki.getAlgParameters());
    }

    /**
     * Test #2 for <code>getAlgParameters()</code> method <br>
     * Assertion: returns the algorithm parameters <br>
     * Test preconditions: test object created using ctor which takes encoded
     * form as the only parameter; encoded form passed does not contain
     * algorithm parameters encoding <br>
     * Expected: <code>null</code> must be returned
     *
     * @throws IOException
     */
    public final void testGetAlgParameters02() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData
                                .getValidEncryptedPrivateKeyInfoEncoding(
                                        EncryptedPrivateKeyInfoData.algName0[i][0],
                                        false));

                // check that method under test returns null
                assertNull(epki.getAlgParameters());

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #3 for <code>getAlgParameters()</code> method <br>
     * Assertion: returns the algorithm parameters <br>
     * Test #6 for <code>EncryptedPrivateKeyInfo(String, byte[])</code>
     * constructor <br>
     * Assertion: ...This constructor will use null as the value of the
     * algorithm parameters. <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: <code>null</code> must be returned
     *
     * @throws IOException
     */
    public final void testGetAlgParameters03() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns null
                // for object constructed in such a way
                assertNull(epki.getAlgParameters());

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #4 for <code>getAlgParameters()</code> method <br>
     * Assertion: returns the algorithm parameters <br>
     * Test preconditions: test object created using ctor which takes
     * AlgorithmParameters and encrypted data as a parameters; <br>
     * Expected: the same algorithm parameters as ones passed to the ctor must be
     * returned
     *
     * @throws IOException
     */
    public final void testGetAlgParameters04() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                AlgorithmParameters ap = AlgorithmParameters
                        .getInstance(EncryptedPrivateKeyInfoData.algName0[i][0]);
                // use pregenerated AlgorithmParameters encodings
                ap
                        .init(EncryptedPrivateKeyInfoData
                                .getParametersEncoding(
                                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(ap,
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // the same parameters instance
                assertSame(ap, epki.getAlgParameters());

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #1 for <code>getEncryptedData()</code> method <br>
     * Assertion: returns the encrypted data <br>
     * Test preconditions: test object created using ctor which takes encoded
     * form as the only parameter; encoded form passed contains encrypted data
     * <br>
     * Expected: the equivalent encrypted data must be returned
     *
     * @throws IOException
     */
    public final void testGetEncryptedData01() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData
                                .getValidEncryptedPrivateKeyInfoEncoding(
                                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                // check that method under test returns
                // valid encrypted data
                assertTrue(Arrays.equals(
                        EncryptedPrivateKeyInfoData.encryptedData, epki
                                .getEncryptedData()));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #2 for <code>getEncryptedData()</code> method <br>
     * Assertion: returns the encrypted data <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: the equivalent encrypted data must be returned
     */
    public final void testGetEncryptedData02() {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // valid encrypted data
                assertTrue(Arrays.equals(
                        EncryptedPrivateKeyInfoData.encryptedData, epki
                                .getEncryptedData()));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #3 for <code>getEncryptedData()</code> method <br>
     * Assertion: returns the encrypted data <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * parameters and encrypted data as a parameters <br>
     * Expected: the equivalent encrypted data must be returned
     *
     * @throws IOException
     */
    public final void testGetEncryptedData03() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                AlgorithmParameters ap = AlgorithmParameters
                        .getInstance(EncryptedPrivateKeyInfoData.algName0[i][0]);
                // use pregenerated AlgorithmParameters encodings
                ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding(
                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(ap,
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // valid encrypted data
                assertTrue(Arrays.equals(
                        EncryptedPrivateKeyInfoData.encryptedData, epki
                                .getEncryptedData()));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #4 for <code>getEncryptedData()</code> method <br>
     * Assertion: returns a new array each time this method is called <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: refs to encrypted data byte array passed to the ctor and
     * returned by the method under test must be different
     */
    public final void testGetEncryptedData04() {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // new array each time
                byte[] ecd1 = epki.getEncryptedData();
                byte[] ecd2 = epki.getEncryptedData();
                assertNotSame(EncryptedPrivateKeyInfoData.encryptedData, ecd1);
                assertNotSame(EncryptedPrivateKeyInfoData.encryptedData, ecd2);
                assertNotSame(ecd1, ecd2);

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #1 for <code>getEncoded()</code> method <br>
     * Assertion: returns the ASN.1 encoding of this object <br>
     * Test preconditions: test object created using ctor which takes encoded
     * form as the only parameter <br>
     * Expected: equivalent encoded form must be returned
     *
     * @throws IOException
     */
    public final void testGetEncoded01() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                byte[] enc = EncryptedPrivateKeyInfoData
                        .getValidEncryptedPrivateKeyInfoEncoding(
                                EncryptedPrivateKeyInfoData.algName0[i][0]);
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(enc);

                // check that method under test returns
                // valid encoded form
                assertTrue(Arrays.equals(enc, epki.getEncoded()));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #2 for <code>getEncoded()</code> method <br>
     * Assertion: returns the ASN.1 encoding of this object <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: equivalent encoded form (without alg params) must be returned
     *
     * @throws IOException
     */
    public final void testGetEncoded02() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // valid encoded form
                byte[] refEnc = EncryptedPrivateKeyInfoData
                        .getValidEncryptedPrivateKeyInfoEncoding(
                                EncryptedPrivateKeyInfoData.algName0[i][0],
                                false);
                //                System.out.println(Array.toString(refEnc, " "));
                byte[] actEnc = epki.getEncoded();
                //                System.out.println(Array.toString(actEnc, " "));
                assertTrue(Arrays.equals(refEnc, actEnc));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #3 for <code>getEncoded()</code> method <br>
     * Assertion: returns the ASN.1 encoding of this object <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: equivalent encoded form (without alg params) must be returned
     *
     * @throws IOException
     */
    public final void testGetEncoded03() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                AlgorithmParameters ap = AlgorithmParameters
                        .getInstance(EncryptedPrivateKeyInfoData.algName0[i][0]);
                // use pregenerated AlgorithmParameters encodings
                ap.init(EncryptedPrivateKeyInfoData.getParametersEncoding(
                        EncryptedPrivateKeyInfoData.algName0[i][0]));

                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(ap,
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // valid encoded form
                assertTrue(Arrays.equals(
                        EncryptedPrivateKeyInfoData
                            .getValidEncryptedPrivateKeyInfoEncoding(
                                EncryptedPrivateKeyInfoData.algName0[i][0]),
                                epki.getEncoded()));

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Test #4 for <code>getEncoded()</code> method <br>
     * Assertion: returns a new array each time this method is called <br>
     * Test preconditions: test object created using ctor which takes algorithm
     * name and encrypted data as a parameters <br>
     * Expected: several refs to byte array returned by the method under test
     * must be different
     *
     * @throws IOException
     */
    public final void testGetEncoded04() throws IOException {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                // check that method under test returns
                // new array each time
                byte[] ec1 = epki.getEncoded();
                byte[] ec2 = epki.getEncoded();
                byte[] ec3 = epki.getEncoded();
                assertNotSame(ec1, ec2);
                assertNotSame(ec2, ec3);
                assertNotSame(ec1, ec3);

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    public final void testGetKeySpecCipher01() {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                try {

                    // check that method under test throws NPE
                    epki.getKeySpec((Cipher) null);
                    fail(getName() + "NullPointerException has not been thrown");

                } catch (NullPointerException ok) {
                } catch (InvalidKeySpecException e) {
                    fail(getName() + "Unexpected exception: " + e);
                }

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains valid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecCipher01() {
        boolean performed = false;

        for (int i = 0; i < algName.length; i++) {
            try {
                // generate test data
                TestDataGenerator g = new TestDataGenerator(algName[i][0],
                        algName[i][1], privateKeyInfo, null);

                // create test object
                EncryptedPrivateKeyInfo epki;
                if (g.ap() == null) {
                    epki = new EncryptedPrivateKeyInfo(algName[i][0], g.ct());
                } else {
                    epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                }

                // call methods under test
                try {

                    PKCS8EncodedKeySpec eks = epki.getKeySpec(g.c());

                    if (!Arrays.equals(privateKeyInfo, eks.getEncoded())) {
                        fail(algName[i][0] + " != " + algName[i][1]);
                    }
                } catch (InvalidKeySpecException e) {
                    fail(algName[i][0] + ", " + algName[i][1] + e + "\n");
                }
                performed = true;

            } catch (TestDataGenerator.AllowedFailure allowedFailure) {
            } catch (NoSuchAlgorithmException allowed) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains invalid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecCipher02() {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            try {
                // generate test data
                TestDataGenerator g = new TestDataGenerator(algName[i][0],
                        algName[i][1], privateKeyInfoDamaged, null);

                // create test object
                EncryptedPrivateKeyInfo epki;
                if (g.ap() == null) {
                    epki = new EncryptedPrivateKeyInfo(algName[i][0], g.ct());
                } else {
                    epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                }

                // call methods under test
                try {
                    epki.getKeySpec(g.c());

                    // must not get here because decrypted data does
                    // not represent valid PKCS8 encoding
                    fail(algName[i][0] + ", " + algName[i][1]);
                } catch (InvalidKeySpecException ok) {
                }

                performed = true;
            } catch (TestDataGenerator.AllowedFailure allowedFailure) {
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    public final void testGetKeySpecKey01() {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                try {

                    // check that method under test throws NPE
                    epki.getKeySpec((Key) null);
                    fail(getName() + "NullPointerException has not been thrown");

                } catch (NullPointerException ok) {
                } catch (InvalidKeyException e) {
                    fail(getName() + "Unexpected exception: " + e);
                }

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains valid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKey01() {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            try {
                // generate test data
                TestDataGenerator g = new TestDataGenerator(algName[i][0],
                        algName[i][1], privateKeyInfo, null);

                // create test object
                EncryptedPrivateKeyInfo epki;
                if (g.ap() == null) {
                    epki = new EncryptedPrivateKeyInfo(algName[i][0], g.ct());
                } else {
                    epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                }

                try {
                    PKCS8EncodedKeySpec eks = epki
                            .getKeySpec(g.pubK() == null ? g.k() : g.pubK());

                    if (!Arrays.equals(privateKeyInfo, eks.getEncoded())) {
                        fail(algName[i][0] + " != " + algName[i][1]);
                    }
                } catch (InvalidKeyException e) {
                    fail(algName[i][0] + ", " + algName[i][1] + ": " + e);
                }

                performed = true;
            } catch (TestDataGenerator.AllowedFailure allowedFailure) {
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains invalid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKey02() {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            try {
                // generate test data
                TestDataGenerator g = new TestDataGenerator(algName[i][0],
                        algName[i][1], privateKeyInfoDamaged, null);

                // create test object
                EncryptedPrivateKeyInfo epki;
                if (g.ap() == null) {
                    epki = new EncryptedPrivateKeyInfo(algName[i][0], g.ct());
                } else {
                    epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                }

                try {
                    epki.getKeySpec(g.pubK() == null ? g.k() : g.pubK());
                    fail(algName[i][0] + ", " + algName[i][1]);
                } catch (InvalidKeyException e) {
                }

                performed = true;
            } catch (TestDataGenerator.AllowedFailure allowedFailure) {
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    public final void testGetKeySpecKeyString01() throws Exception {
        boolean performed = false;
        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                try {
                    // check that method under test throws NPE
                    epki.getKeySpec((Key) null, "SomeProviderName");
                    fail(getName() + "NullPointerException has not been thrown");

                } catch (NullPointerException ok) {
                }

                try {
                    epki.getKeySpec(new Key() {
                        public String getAlgorithm() {
                            return "alg";
                        }

                        public String getFormat() {
                            return "fmt";
                        }

                        public byte[] getEncoded() {
                            return new byte[] {};
                        }
                    }, "StrangeProviderName");
                    fail(getName() + "NoSuchProviderException has not been thrown");
                } catch (NoSuchProviderException ok) {
                    //expected
                }

                try {

                    // check that method under test throws NPE
                    epki.getKeySpec(new Key() {
                        public String getAlgorithm() {
                            return "alg";
                        }

                        public String getFormat() {
                            return "fmt";
                        }

                        public byte[] getEncoded() {
                            return new byte[] {};
                        }
                    }, (String) null);

                    fail(getName() + "NullPointerException has not been thrown");

                } catch (NullPointerException ok) {
                }

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains valid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKeyString01() throws Exception {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            for (int l = 0; l < provider.length; l++) {
                if (provider[l] == null) {
                    continue;
                }
                TestDataGenerator g;
                try {
                    // generate test data
                    g = new TestDataGenerator(algName[i][0], algName[i][1],
                            privateKeyInfo, provider[l]);
                } catch (TestDataGenerator.AllowedFailure allowedFailure) {
                    continue;
                }

                try {
                    // create test object
                    EncryptedPrivateKeyInfo epki;
                    if (g.ap() == null) {
                        epki = new EncryptedPrivateKeyInfo(algName[i][0], g
                                .ct());
                    } else {
                        epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                    }
                    try {

                        PKCS8EncodedKeySpec eks = epki.getKeySpec(
                                g.pubK() == null ? g.k() : g.pubK(),
                                provider[l].getName());

                        if (!Arrays.equals(privateKeyInfo, eks.getEncoded())) {
                            fail(algName[i][0] + " != " + algName[i][1]);
                        }
                    } catch (InvalidKeyException e) {
                        fail(algName[i][0] + ", " + algName[i][1] + ": " + e);
                    }

                    performed = true;
                } catch (NoSuchAlgorithmException allowedFailure) {
                }
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains invalid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKeyString02() throws Exception {
        boolean performed = false;
        for (int i = 0; i < algName.length; i++) {
            for (int l = 0; l < provider.length; l++) {
                if (provider[l] == null) {
                    continue;
                }
                TestDataGenerator g;
                try {
                    // generate test data
                    g = new TestDataGenerator(algName[i][0], algName[i][1],
                            privateKeyInfoDamaged, provider[l]);
                } catch (TestDataGenerator.AllowedFailure allowedFailure) {
                    continue;
                }

                try {
                    // create test object
                    EncryptedPrivateKeyInfo epki;
                    if (g.ap() == null) {
                        epki = new EncryptedPrivateKeyInfo(algName[i][0], g
                                .ct());
                    } else {
                        epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                    }

                    try {

                        epki.getKeySpec(g.pubK() == null ? g.k() : g.pubK(),
                                provider[l].getName());

                        fail(algName[i][0] + ", " + algName[i][1]);

                    } catch (InvalidKeyException e) {
                    }

                    performed = true;
                } catch (NoSuchAlgorithmException allowedFailure) {
                }
            }
        }
        assertTrue("Test not performed", performed);
    }

    public final void testGetKeySpecKeyProvider01() throws Exception {
        boolean performed = false;

        for (int i = 0; i < EncryptedPrivateKeyInfoData.algName0.length; i++) {
            try {
                EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(
                        EncryptedPrivateKeyInfoData.algName0[i][0],
                        EncryptedPrivateKeyInfoData.encryptedData);

                try {

                    // check that method under test throws NPE
                    epki.getKeySpec((Key) null, (Provider) null);
                    fail(getName() + "NullPointerException has not been thrown");
                } catch (NullPointerException ok) {
                }

                try {

                    // check that method under test throws NPE
                    epki.getKeySpec(new Key() {
                        public String getAlgorithm() {
                            return "alg";
                        }

                        public String getFormat() {
                            return "fmt";
                        }

                        public byte[] getEncoded() {
                            return new byte[] {};
                        }
                    }, (Provider) null);

                    fail(getName() + "NullPointerException has not been thrown");
                } catch (NullPointerException ok) {
                }

                performed = true;
            } catch (NoSuchAlgorithmException allowedFailure) {
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains valid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKeyProvider01() {
        boolean performed = false;

        for (int i = 0; i < algName.length; i++) {
            for (int l = 0; l < provider.length; l++) {
                if (provider[l] == null) {
                    continue;
                }
                TestDataGenerator g;
                try {
                    // generate test data
                    g = new TestDataGenerator(algName[i][0], algName[i][1],
                            privateKeyInfo, provider[l]);
                } catch (TestDataGenerator.AllowedFailure allowedFailure) {
                    continue;
                }
                try {
                    // create test object
                    EncryptedPrivateKeyInfo epki;
                    if (g.ap() == null) {
                        epki = new EncryptedPrivateKeyInfo(algName[i][0], g
                                .ct());
                    } else {
                        epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                    }
                    try {

                        PKCS8EncodedKeySpec eks = epki.getKeySpec(
                                g.pubK() == null ? g.k() : g.pubK(),
                                provider[l]);

                        if (!Arrays.equals(privateKeyInfo, eks.getEncoded())) {
                            fail(algName[i][0] + " != " + algName[i][1]);
                        }
                    } catch (InvalidKeyException e) {
                        fail(algName[i][0] + ", " + algName[i][1] + ": " + e);
                    }
                    performed = true;

                } catch (NoSuchAlgorithmException allowedFailure) {
                }
            }
        }
        assertTrue("Test not performed", performed);
    }

    /**
     * Encrypted data contains invalid PKCS8 key info encoding
     */
    public final void test_ROUNDTRIP_GetKeySpecKeyProvider02() {
        boolean performed = false;

        for (int i = 0; i < algName.length; i++) {
            for (int l = 0; l < provider.length; l++) {
                if (provider[l] == null) {
                    continue;
                }
                TestDataGenerator g;
                try {
                    // generate test data
                    g = new TestDataGenerator(algName[i][0], algName[i][1],
                            privateKeyInfoDamaged, provider[l]);
                } catch (TestDataGenerator.AllowedFailure allowedFailure) {
                    continue;
                }

                try {
                    // create test object
                    EncryptedPrivateKeyInfo epki;
                    if (g.ap() == null) {
                        epki = new EncryptedPrivateKeyInfo(algName[i][0], g
                                .ct());
                    } else {
                        epki = new EncryptedPrivateKeyInfo(g.ap(), g.ct());
                    }
                    try {

                        epki.getKeySpec(g.pubK() == null ? g.k() : g.pubK(),
                                provider[l]);

                        fail(algName[i][0] + ", " + algName[i][1]);

                    } catch (InvalidKeyException e) {
                    }
                    performed = true;
                } catch (NoSuchAlgorithmException allowedFailure) {
                }
            }
        }
        assertTrue("Test not performed", performed);
    }

    public static class TestDataGenerator {

        public static class AllowedFailure extends Exception {
            AllowedFailure(String msg) {
                super(msg);
            }
        }

        private Cipher c = null;

        private Key k = null, pubK = null;

        private AlgorithmParameters ap = null;

        byte[] ct;

        public TestDataGenerator(String algName, String transformation,
                byte[] privateKeyInfo, Provider provider) throws AllowedFailure {
            try {
                c = (provider == null) ? Cipher
                        .getInstance(transformation != null ? transformation
                                : algName) : Cipher.getInstance(
                        transformation != null ? transformation : algName,
                        provider);
            } catch (NoSuchAlgorithmException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (NoSuchPaddingException e) {
                throw new AllowedFailure(e.getMessage());
            }

            try {
                KeyGenerator kg = (provider == null) ? KeyGenerator
                        .getInstance(algName) : KeyGenerator.getInstance(
                        algName, provider);
                k = kg.generateKey();
            } catch (NoSuchAlgorithmException e) {
            }

            if (k == null) {
                try {
                    KeyPairGenerator kpg = (provider == null) ? KeyPairGenerator
                            .getInstance(algName)
                            : KeyPairGenerator.getInstance(algName, provider);
                    KeyPair kp = kpg.genKeyPair();
                    k = kp.getPrivate();
                    pubK = kp.getPublic();
                } catch (NoSuchAlgorithmException e) {
                }
            }

            PBEParameterSpec pbeParamSpec = null;
            if (k == null) {
                try {
                    pbeParamSpec = new PBEParameterSpec(new byte[] { 1, 2, 3,
                            4, 5, 6, 7, 8 }, 10);
                    SecretKeyFactory skf = (provider == null) ? SecretKeyFactory
                            .getInstance(algName)
                            : SecretKeyFactory.getInstance(algName, provider);
                    PBEKeySpec ks = new PBEKeySpec("12345678".toCharArray());
                    try {
                        k = skf.generateSecret(ks);
                    } catch (InvalidKeySpecException e) {
                        throw new AllowedFailure(e.getMessage());
                    }

                } catch (NoSuchAlgorithmException e) {
                    throw new AllowedFailure(e.getMessage());
                }
            }

            try {
                if (pbeParamSpec == null) {
                    c.init(Cipher.ENCRYPT_MODE, k);
                } else {
                    c.init(Cipher.ENCRYPT_MODE, k, pbeParamSpec);
                }
            } catch (InvalidKeyException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (SecurityException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (InvalidAlgorithmParameterException e) {
                throw new AllowedFailure(e.getMessage());
            }

            ap = c.getParameters();

            try {
                ct = c.doFinal(privateKeyInfo);
            } catch (IllegalStateException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (IllegalBlockSizeException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (BadPaddingException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (RuntimeException e) {
                throw new AllowedFailure(e.getMessage());
            }

            try {
                // try to convert pbeParamSpec->ap
                if (pbeParamSpec != null) {
                    try {
                        ap = (provider == null) ? AlgorithmParameters
                                .getInstance(algName) : AlgorithmParameters
                                .getInstance(algName, provider);
                        ap.init(pbeParamSpec);
                        pbeParamSpec = null;
                    } catch (NoSuchAlgorithmException e) {
                        // couldn't convert
                        throw new AllowedFailure(e.getMessage());
                    } catch (InvalidParameterSpecException e) {
                        // couldn't convert
                        throw new AllowedFailure(e.getMessage());
                    }
                }

                if (ap == null) {
                    c.init(Cipher.DECRYPT_MODE, pubK == null ? k : pubK);
                } else {
                    c.init(Cipher.DECRYPT_MODE, pubK == null ? k : pubK, ap);
                }

            } catch (InvalidKeyException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (SecurityException e) {
                throw new AllowedFailure(e.getMessage());
            } catch (InvalidAlgorithmParameterException e) {
                throw new AllowedFailure(e.getMessage());
            }
        }

        public Key k() {
            return k;
        }

        public Key pubK() {
            return pubK;
        }

        public Cipher c() {
            return c;
        }

        public byte[] ct() {
            return ct;
        }

        public AlgorithmParameters ap() {
            return ap;
        }
    }

    // valid PrivateKeyInfo encoding
    private static final byte[] privateKeyInfo = { (byte) 0x30, (byte) 0x82,
            (byte) 0x02, (byte) 0x77, (byte) 0x02, (byte) 0x01, (byte) 0x00,
            (byte) 0x30, (byte) 0x0d, (byte) 0x06, (byte) 0x09, (byte) 0x2a,
            (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xf7, (byte) 0x0d,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x05, (byte) 0x00,
            (byte) 0x04, (byte) 0x82, (byte) 0x02, (byte) 0x61, (byte) 0x30,
            (byte) 0x82, (byte) 0x02, (byte) 0x5d, (byte) 0x02, (byte) 0x01,
            (byte) 0x00, (byte) 0x02, (byte) 0x81, (byte) 0x81, (byte) 0x00,
            (byte) 0xb2, (byte) 0x4a, (byte) 0x9b, (byte) 0x5b, (byte) 0xba,
            (byte) 0x01, (byte) 0xc0, (byte) 0xcd, (byte) 0x65, (byte) 0x09,
            (byte) 0x63, (byte) 0x70, (byte) 0x0b, (byte) 0x5a, (byte) 0x1b,
            (byte) 0x92, (byte) 0x08, (byte) 0xf8, (byte) 0x55, (byte) 0x5e,
            (byte) 0x7c, (byte) 0x1b, (byte) 0x50, (byte) 0x17, (byte) 0xec,
            (byte) 0x44, (byte) 0x4c, (byte) 0x58, (byte) 0x42, (byte) 0x2b,
            (byte) 0x41, (byte) 0x09, (byte) 0x59, (byte) 0xf2, (byte) 0xe1,
            (byte) 0x5d, (byte) 0x43, (byte) 0x71, (byte) 0x4d, (byte) 0x92,
            (byte) 0x03, (byte) 0x1d, (byte) 0xb6, (byte) 0x6c, (byte) 0x7f,
            (byte) 0x5d, (byte) 0x48, (byte) 0xcd, (byte) 0x17, (byte) 0xec,
            (byte) 0xd7, (byte) 0x4c, (byte) 0x39, (byte) 0xb1, (byte) 0x7b,
            (byte) 0xe2, (byte) 0xbf, (byte) 0x96, (byte) 0x77, (byte) 0xbe,
            (byte) 0xd0, (byte) 0xa0, (byte) 0xf0, (byte) 0x2d, (byte) 0x6b,
            (byte) 0x24, (byte) 0xaa, (byte) 0x14, (byte) 0xba, (byte) 0x82,
            (byte) 0x79, (byte) 0x10, (byte) 0x9b, (byte) 0x16, (byte) 0x68,
            (byte) 0x47, (byte) 0x81, (byte) 0x54, (byte) 0xa2, (byte) 0xfa,
            (byte) 0x91, (byte) 0x9e, (byte) 0x0a, (byte) 0x2a, (byte) 0x53,
            (byte) 0xa6, (byte) 0xe7, (byte) 0x9e, (byte) 0x7d, (byte) 0x29,
            (byte) 0x33, (byte) 0xd8, (byte) 0x05, (byte) 0xfc, (byte) 0x02,
            (byte) 0x3f, (byte) 0xbd, (byte) 0xc7, (byte) 0x6e, (byte) 0xed,
            (byte) 0xaa, (byte) 0x30, (byte) 0x6c, (byte) 0x5f, (byte) 0x52,
            (byte) 0xed, (byte) 0x35, (byte) 0x65, (byte) 0x4b, (byte) 0x0e,
            (byte) 0xc8, (byte) 0xa7, (byte) 0x12, (byte) 0x10, (byte) 0x56,
            (byte) 0x37, (byte) 0xaf, (byte) 0x11, (byte) 0xfa, (byte) 0x21,
            (byte) 0x0e, (byte) 0x99, (byte) 0xff, (byte) 0xfa, (byte) 0x8c,
            (byte) 0x65, (byte) 0x8e, (byte) 0x6d, (byte) 0x02, (byte) 0x03,
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x81,
            (byte) 0x80, (byte) 0x78, (byte) 0x41, (byte) 0x72, (byte) 0x40,
            (byte) 0x90, (byte) 0x59, (byte) 0x96, (byte) 0x5d, (byte) 0xf3,
            (byte) 0x84, (byte) 0x3d, (byte) 0x99, (byte) 0xd9, (byte) 0x4e,
            (byte) 0x51, (byte) 0xc2, (byte) 0x52, (byte) 0x62, (byte) 0x8d,
            (byte) 0xd2, (byte) 0x49, (byte) 0x0b, (byte) 0x73, (byte) 0x1e,
            (byte) 0x6f, (byte) 0xb2, (byte) 0x31, (byte) 0x7c, (byte) 0x66,
            (byte) 0x45, (byte) 0x1e, (byte) 0x7c, (byte) 0xdc, (byte) 0x3a,
            (byte) 0xc2, (byte) 0x5f, (byte) 0x51, (byte) 0x9a, (byte) 0x1e,
            (byte) 0xa4, (byte) 0x19, (byte) 0x8d, (byte) 0xf4, (byte) 0xf9,
            (byte) 0x81, (byte) 0x7e, (byte) 0xbe, (byte) 0x17, (byte) 0xf7,
            (byte) 0xc7, (byte) 0x3c, (byte) 0x00, (byte) 0xa1, (byte) 0xf9,
            (byte) 0x60, (byte) 0x82, (byte) 0x34, (byte) 0x8f, (byte) 0x9c,
            (byte) 0xfd, (byte) 0x0b, (byte) 0x63, (byte) 0x42, (byte) 0x1b,
            (byte) 0x7f, (byte) 0x45, (byte) 0xf1, (byte) 0x31, (byte) 0xc3,
            (byte) 0x63, (byte) 0x47, (byte) 0x5c, (byte) 0xc1, (byte) 0xb2,
            (byte) 0x5f, (byte) 0x57, (byte) 0xee, (byte) 0x02, (byte) 0x9f,
            (byte) 0x5e, (byte) 0x08, (byte) 0x48, (byte) 0xba, (byte) 0x74,
            (byte) 0xba, (byte) 0x81, (byte) 0xb7, (byte) 0x30, (byte) 0xac,
            (byte) 0x4c, (byte) 0x01, (byte) 0x35, (byte) 0xce, (byte) 0x46,
            (byte) 0x47, (byte) 0x8c, (byte) 0xe4, (byte) 0x62, (byte) 0x36,
            (byte) 0x1a, (byte) 0x65, (byte) 0x0e, (byte) 0x33, (byte) 0x56,
            (byte) 0xf9, (byte) 0xb7, (byte) 0xa0, (byte) 0xc4, (byte) 0xb6,
            (byte) 0x82, (byte) 0x55, (byte) 0x7d, (byte) 0x36, (byte) 0x55,
            (byte) 0xc0, (byte) 0x52, (byte) 0x5e, (byte) 0x35, (byte) 0x54,
            (byte) 0xbd, (byte) 0x97, (byte) 0x01, (byte) 0x00, (byte) 0xbf,
            (byte) 0x10, (byte) 0xdc, (byte) 0x1b, (byte) 0x51, (byte) 0x02,
            (byte) 0x41, (byte) 0x00, (byte) 0xe7, (byte) 0x68, (byte) 0x03,
            (byte) 0x3e, (byte) 0x21, (byte) 0x64, (byte) 0x68, (byte) 0x24,
            (byte) 0x7b, (byte) 0xd0, (byte) 0x31, (byte) 0xa0, (byte) 0xa2,
            (byte) 0xd9, (byte) 0x87, (byte) 0x6d, (byte) 0x79, (byte) 0x81,
            (byte) 0x8f, (byte) 0x8f, (byte) 0x2d, (byte) 0x7a, (byte) 0x95,
            (byte) 0x2e, (byte) 0x55, (byte) 0x9f, (byte) 0xd7, (byte) 0x86,
            (byte) 0x29, (byte) 0x93, (byte) 0xbd, (byte) 0x04, (byte) 0x7e,
            (byte) 0x4f, (byte) 0xdb, (byte) 0x56, (byte) 0xf1, (byte) 0x75,
            (byte) 0xd0, (byte) 0x4b, (byte) 0x00, (byte) 0x3a, (byte) 0xe0,
            (byte) 0x26, (byte) 0xf6, (byte) 0xab, (byte) 0x9e, (byte) 0x0b,
            (byte) 0x2a, (byte) 0xf4, (byte) 0xa8, (byte) 0xd7, (byte) 0xff,
            (byte) 0xbe, (byte) 0x01, (byte) 0xeb, (byte) 0x9b, (byte) 0x81,
            (byte) 0xc7, (byte) 0x5f, (byte) 0x02, (byte) 0x73, (byte) 0xe1,
            (byte) 0x2b, (byte) 0x02, (byte) 0x41, (byte) 0x00, (byte) 0xc5,
            (byte) 0x3d, (byte) 0x78, (byte) 0xab, (byte) 0xe6, (byte) 0xab,
            (byte) 0x3e, (byte) 0x29, (byte) 0xfd, (byte) 0x98, (byte) 0xd0,
            (byte) 0xa4, (byte) 0x3e, (byte) 0x58, (byte) 0xee, (byte) 0x48,
            (byte) 0x45, (byte) 0xa3, (byte) 0x66, (byte) 0xac, (byte) 0xe9,
            (byte) 0x4d, (byte) 0xbd, (byte) 0x60, (byte) 0xea, (byte) 0x24,
            (byte) 0xff, (byte) 0xed, (byte) 0x0c, (byte) 0x67, (byte) 0xc5,
            (byte) 0xfd, (byte) 0x36, (byte) 0x28, (byte) 0xea, (byte) 0x74,
            (byte) 0x88, (byte) 0xd1, (byte) 0xd1, (byte) 0xad, (byte) 0x58,
            (byte) 0xd7, (byte) 0xf0, (byte) 0x67, (byte) 0x20, (byte) 0xc1,
            (byte) 0xe3, (byte) 0xb3, (byte) 0xdb, (byte) 0x52, (byte) 0xad,
            (byte) 0xf3, (byte) 0xc4, (byte) 0x21, (byte) 0xd8, (byte) 0x8c,
            (byte) 0x4c, (byte) 0x41, (byte) 0x27, (byte) 0xdb, (byte) 0xd0,
            (byte) 0x35, (byte) 0x92, (byte) 0xc7, (byte) 0x02, (byte) 0x41,
            (byte) 0x00, (byte) 0xe0, (byte) 0x99, (byte) 0x42, (byte) 0xb4,
            (byte) 0x76, (byte) 0x02, (byte) 0x97, (byte) 0x55, (byte) 0xf9,
            (byte) 0xda, (byte) 0x3b, (byte) 0xa0, (byte) 0xd7, (byte) 0x0e,
            (byte) 0xdc, (byte) 0xf4, (byte) 0x33, (byte) 0x7f, (byte) 0xbd,
            (byte) 0xcf, (byte) 0xd0, (byte) 0xeb, (byte) 0x6e, (byte) 0x89,
            (byte) 0xf7, (byte) 0x4f, (byte) 0x5a, (byte) 0x07, (byte) 0x7c,
            (byte) 0xa9, (byte) 0x49, (byte) 0x47, (byte) 0x68, (byte) 0x35,
            (byte) 0xa8, (byte) 0x05, (byte) 0x3d, (byte) 0xfd, (byte) 0x04,
            (byte) 0x7b, (byte) 0x17, (byte) 0x31, (byte) 0x0d, (byte) 0xc8,
            (byte) 0xa3, (byte) 0x98, (byte) 0x34, (byte) 0xa0, (byte) 0x50,
            (byte) 0x44, (byte) 0x00, (byte) 0xf1, (byte) 0x0c, (byte) 0xe6,
            (byte) 0xe5, (byte) 0xc4, (byte) 0x41, (byte) 0x3d, (byte) 0xf8,
            (byte) 0x3d, (byte) 0x4e, (byte) 0x0b, (byte) 0x1c, (byte) 0xdb,
            (byte) 0x02, (byte) 0x41, (byte) 0x00, (byte) 0x82, (byte) 0x9b,
            (byte) 0x8a, (byte) 0xfd, (byte) 0xa1, (byte) 0x98, (byte) 0x41,
            (byte) 0x68, (byte) 0xc2, (byte) 0xd1, (byte) 0xdf, (byte) 0x4e,
            (byte) 0xf3, (byte) 0x2e, (byte) 0x26, (byte) 0x53, (byte) 0x5b,
            (byte) 0x31, (byte) 0xb1, (byte) 0x7a, (byte) 0xcc, (byte) 0x5e,
            (byte) 0xbb, (byte) 0x09, (byte) 0xa2, (byte) 0xe2, (byte) 0x6f,
            (byte) 0x4a, (byte) 0x04, (byte) 0x0d, (byte) 0xef, (byte) 0x90,
            (byte) 0x15, (byte) 0xbe, (byte) 0x10, (byte) 0x4a, (byte) 0xac,
            (byte) 0x92, (byte) 0xeb, (byte) 0xda, (byte) 0x72, (byte) 0xdb,
            (byte) 0x43, (byte) 0x08, (byte) 0xb7, (byte) 0x2b, (byte) 0x4c,
            (byte) 0xe1, (byte) 0xbb, (byte) 0x58, (byte) 0xcb, (byte) 0x71,
            (byte) 0x80, (byte) 0xad, (byte) 0xbc, (byte) 0xdc, (byte) 0x62,
            (byte) 0x5e, (byte) 0x3e, (byte) 0xcb, (byte) 0x92, (byte) 0xda,
            (byte) 0xf6, (byte) 0xdf, (byte) 0x02, (byte) 0x40, (byte) 0x4d,
            (byte) 0x81, (byte) 0x90, (byte) 0xc5, (byte) 0x77, (byte) 0x30,
            (byte) 0xb7, (byte) 0x29, (byte) 0x00, (byte) 0xa8, (byte) 0xf1,
            (byte) 0xb4, (byte) 0xae, (byte) 0x52, (byte) 0x63, (byte) 0x00,
            (byte) 0xb2, (byte) 0x2d, (byte) 0x3e, (byte) 0x7d, (byte) 0xd6,
            (byte) 0x4d, (byte) 0xf9, (byte) 0x8a, (byte) 0xc1, (byte) 0xb1,
            (byte) 0x98, (byte) 0x89, (byte) 0x52, (byte) 0x40, (byte) 0x14,
            (byte) 0x1b, (byte) 0x0e, (byte) 0x61, (byte) 0x8f, (byte) 0xf4,
            (byte) 0xbe, (byte) 0x59, (byte) 0x79, (byte) 0x79, (byte) 0x95,
            (byte) 0x19, (byte) 0x5c, (byte) 0x51, (byte) 0x08, (byte) 0x66,
            (byte) 0xc1, (byte) 0x42, (byte) 0x30, (byte) 0xb3, (byte) 0x7a,
            (byte) 0x86, (byte) 0x9f, (byte) 0x3e, (byte) 0xf5, (byte) 0x19,
            (byte) 0xa3, (byte) 0xae, (byte) 0x64, (byte) 0x69, (byte) 0x14,
            (byte) 0x07, (byte) 0x50, (byte) 0x97, };

    // valid PrivateKeyInfo encoding (Damaged)
    private static final byte[] privateKeyInfoDamaged = { (byte) 0x30,
            (byte) 0x82, (byte) 0x02, (byte) 0x77, (byte) 0x02, (byte) 0x01,
            (byte) 0x00, (byte) 0x30, (byte) 0x0d, (byte) 0x06, (byte) 0x09,
            (byte) 0x2a, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xf7,
            (byte) 0x0d, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x05,
            (byte) 0x00, (byte) 0x04, // private key octet str
            (byte) 0x82, (byte) 0x02, (byte) 0x62, // Damage: l=460->461
                                                   // (0x61->0x62)
            (byte) 0x30, (byte) 0x82, (byte) 0x02, (byte) 0x5d, (byte) 0x02,
            (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x81, (byte) 0x81,
            (byte) 0x00, (byte) 0xb2, (byte) 0x4a, (byte) 0x9b, (byte) 0x5b,
            (byte) 0xba, (byte) 0x01, (byte) 0xc0, (byte) 0xcd, (byte) 0x65,
            (byte) 0x09, (byte) 0x63, (byte) 0x70, (byte) 0x0b, (byte) 0x5a,
            (byte) 0x1b, (byte) 0x92, (byte) 0x08, (byte) 0xf8, (byte) 0x55,
            (byte) 0x5e, (byte) 0x7c, (byte) 0x1b, (byte) 0x50, (byte) 0x17,
            (byte) 0xec, (byte) 0x44, (byte) 0x4c, (byte) 0x58, (byte) 0x42,
            (byte) 0x2b, (byte) 0x41, (byte) 0x09, (byte) 0x59, (byte) 0xf2,
            (byte) 0xe1, (byte) 0x5d, (byte) 0x43, (byte) 0x71, (byte) 0x4d,
            (byte) 0x92, (byte) 0x03, (byte) 0x1d, (byte) 0xb6, (byte) 0x6c,
            (byte) 0x7f, (byte) 0x5d, (byte) 0x48, (byte) 0xcd, (byte) 0x17,
            (byte) 0xec, (byte) 0xd7, (byte) 0x4c, (byte) 0x39, (byte) 0xb1,
            (byte) 0x7b, (byte) 0xe2, (byte) 0xbf, (byte) 0x96, (byte) 0x77,
            (byte) 0xbe, (byte) 0xd0, (byte) 0xa0, (byte) 0xf0, (byte) 0x2d,
            (byte) 0x6b, (byte) 0x24, (byte) 0xaa, (byte) 0x14, (byte) 0xba,
            (byte) 0x82, (byte) 0x79, (byte) 0x10, (byte) 0x9b, (byte) 0x16,
            (byte) 0x68, (byte) 0x47, (byte) 0x81, (byte) 0x54, (byte) 0xa2,
            (byte) 0xfa, (byte) 0x91, (byte) 0x9e, (byte) 0x0a, (byte) 0x2a,
            (byte) 0x53, (byte) 0xa6, (byte) 0xe7, (byte) 0x9e, (byte) 0x7d,
            (byte) 0x29, (byte) 0x33, (byte) 0xd8, (byte) 0x05, (byte) 0xfc,
            (byte) 0x02, (byte) 0x3f, (byte) 0xbd, (byte) 0xc7, (byte) 0x6e,
            (byte) 0xed, (byte) 0xaa, (byte) 0x30, (byte) 0x6c, (byte) 0x5f,
            (byte) 0x52, (byte) 0xed, (byte) 0x35, (byte) 0x65, (byte) 0x4b,
            (byte) 0x0e, (byte) 0xc8, (byte) 0xa7, (byte) 0x12, (byte) 0x10,
            (byte) 0x56, (byte) 0x37, (byte) 0xaf, (byte) 0x11, (byte) 0xfa,
            (byte) 0x21, (byte) 0x0e, (byte) 0x99, (byte) 0xff, (byte) 0xfa,
            (byte) 0x8c, (byte) 0x65, (byte) 0x8e, (byte) 0x6d, (byte) 0x02,
            (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x02,
            (byte) 0x81, (byte) 0x80, (byte) 0x78, (byte) 0x41, (byte) 0x72,
            (byte) 0x40, (byte) 0x90, (byte) 0x59, (byte) 0x96, (byte) 0x5d,
            (byte) 0xf3, (byte) 0x84, (byte) 0x3d, (byte) 0x99, (byte) 0xd9,
            (byte) 0x4e, (byte) 0x51, (byte) 0xc2, (byte) 0x52, (byte) 0x62,
            (byte) 0x8d, (byte) 0xd2, (byte) 0x49, (byte) 0x0b, (byte) 0x73,
            (byte) 0x1e, (byte) 0x6f, (byte) 0xb2, (byte) 0x31, (byte) 0x7c,
            (byte) 0x66, (byte) 0x45, (byte) 0x1e, (byte) 0x7c, (byte) 0xdc,
            (byte) 0x3a, (byte) 0xc2, (byte) 0x5f, (byte) 0x51, (byte) 0x9a,
            (byte) 0x1e, (byte) 0xa4, (byte) 0x19, (byte) 0x8d, (byte) 0xf4,
            (byte) 0xf9, (byte) 0x81, (byte) 0x7e, (byte) 0xbe, (byte) 0x17,
            (byte) 0xf7, (byte) 0xc7, (byte) 0x3c, (byte) 0x00, (byte) 0xa1,
            (byte) 0xf9, (byte) 0x60, (byte) 0x82, (byte) 0x34, (byte) 0x8f,
            (byte) 0x9c, (byte) 0xfd, (byte) 0x0b, (byte) 0x63, (byte) 0x42,
            (byte) 0x1b, (byte) 0x7f, (byte) 0x45, (byte) 0xf1, (byte) 0x31,
            (byte) 0xc3, (byte) 0x63, (byte) 0x47, (byte) 0x5c, (byte) 0xc1,
            (byte) 0xb2, (byte) 0x5f, (byte) 0x57, (byte) 0xee, (byte) 0x02,
            (byte) 0x9f, (byte) 0x5e, (byte) 0x08, (byte) 0x48, (byte) 0xba,
            (byte) 0x74, (byte) 0xba, (byte) 0x81, (byte) 0xb7, (byte) 0x30,
            (byte) 0xac, (byte) 0x4c, (byte) 0x01, (byte) 0x35, (byte) 0xce,
            (byte) 0x46, (byte) 0x47, (byte) 0x8c, (byte) 0xe4, (byte) 0x62,
            (byte) 0x36, (byte) 0x1a, (byte) 0x65, (byte) 0x0e, (byte) 0x33,
            (byte) 0x56, (byte) 0xf9, (byte) 0xb7, (byte) 0xa0, (byte) 0xc4,
            (byte) 0xb6, (byte) 0x82, (byte) 0x55, (byte) 0x7d, (byte) 0x36,
            (byte) 0x55, (byte) 0xc0, (byte) 0x52, (byte) 0x5e, (byte) 0x35,
            (byte) 0x54, (byte) 0xbd, (byte) 0x97, (byte) 0x01, (byte) 0x00,
            (byte) 0xbf, (byte) 0x10, (byte) 0xdc, (byte) 0x1b, (byte) 0x51,
            (byte) 0x02, (byte) 0x41, (byte) 0x00, (byte) 0xe7, (byte) 0x68,
            (byte) 0x03, (byte) 0x3e, (byte) 0x21, (byte) 0x64, (byte) 0x68,
            (byte) 0x24, (byte) 0x7b, (byte) 0xd0, (byte) 0x31, (byte) 0xa0,
            (byte) 0xa2, (byte) 0xd9, (byte) 0x87, (byte) 0x6d, (byte) 0x79,
            (byte) 0x81, (byte) 0x8f, (byte) 0x8f, (byte) 0x2d, (byte) 0x7a,
            (byte) 0x95, (byte) 0x2e, (byte) 0x55, (byte) 0x9f, (byte) 0xd7,
            (byte) 0x86, (byte) 0x29, (byte) 0x93, (byte) 0xbd, (byte) 0x04,
            (byte) 0x7e, (byte) 0x4f, (byte) 0xdb, (byte) 0x56, (byte) 0xf1,
            (byte) 0x75, (byte) 0xd0, (byte) 0x4b, (byte) 0x00, (byte) 0x3a,
            (byte) 0xe0, (byte) 0x26, (byte) 0xf6, (byte) 0xab, (byte) 0x9e,
            (byte) 0x0b, (byte) 0x2a, (byte) 0xf4, (byte) 0xa8, (byte) 0xd7,
            (byte) 0xff, (byte) 0xbe, (byte) 0x01, (byte) 0xeb, (byte) 0x9b,
            (byte) 0x81, (byte) 0xc7, (byte) 0x5f, (byte) 0x02, (byte) 0x73,
            (byte) 0xe1, (byte) 0x2b, (byte) 0x02, (byte) 0x41, (byte) 0x00,
            (byte) 0xc5, (byte) 0x3d, (byte) 0x78, (byte) 0xab, (byte) 0xe6,
            (byte) 0xab, (byte) 0x3e, (byte) 0x29, (byte) 0xfd, // 88
            (byte) 0x98, (byte) 0xd0, (byte) 0xa4, (byte) 0x3e, (byte) 0x58,
            (byte) 0xee, (byte) 0x48, (byte) 0x45, (byte) 0xa3, (byte) 0x66,
            (byte) 0xac, (byte) 0xe9, (byte) 0x4d, (byte) 0xbd, (byte) 0x60,
            (byte) 0xea, (byte) 0x24, (byte) 0xff, (byte) 0xed, (byte) 0x0c,
            (byte) 0x67, (byte) 0xc5, (byte) 0xfd, (byte) 0x36, (byte) 0x28,
            (byte) 0xea, (byte) 0x74, (byte) 0x88, (byte) 0xd1, (byte) 0xd1,
            (byte) 0xad, (byte) 0x58, (byte) 0xd7, (byte) 0xf0, (byte) 0x67,
            (byte) 0x20, (byte) 0xc1, (byte) 0xe3, (byte) 0xb3, (byte) 0xdb,
            (byte) 0x52, (byte) 0xad, (byte) 0xf3, (byte) 0xc4, (byte) 0x21,
            (byte) 0xd8, (byte) 0x8c, (byte) 0x4c, (byte) 0x41, (byte) 0x27,
            (byte) 0xdb, (byte) 0xd0, (byte) 0x35, (byte) 0x92, (byte) 0xc7,
            (byte) 0x02, (byte) 0x41, (byte) 0x00, (byte) 0xe0, (byte) 0x99,
            (byte) 0x42, (byte) 0xb4, (byte) 0x76, (byte) 0x02, (byte) 0x97,
            (byte) 0x55, (byte) 0xf9, (byte) 0xda, (byte) 0x3b, (byte) 0xa0,
            (byte) 0xd7, (byte) 0x0e, (byte) 0xdc, (byte) 0xf4, (byte) 0x33,
            (byte) 0x7f, (byte) 0xbd, (byte) 0xcf, (byte) 0xd0, (byte) 0xeb,
            (byte) 0x6e, (byte) 0x89, (byte) 0xf7, (byte) 0x4f, (byte) 0x5a,
            (byte) 0x07, (byte) 0x7c, (byte) 0xa9, (byte) 0x49, (byte) 0x47,
            (byte) 0x68, (byte) 0x35, (byte) 0xa8, (byte) 0x05, (byte) 0x3d,
            (byte) 0xfd, (byte) 0x04, (byte) 0x7b, (byte) 0x17, (byte) 0x31,
            (byte) 0x0d, (byte) 0xc8, (byte) 0xa3, (byte) 0x98, (byte) 0x34,
            (byte) 0xa0, (byte) 0x50, (byte) 0x44, (byte) 0x00, (byte) 0xf1,
            (byte) 0x0c, (byte) 0xe6, (byte) 0xe5, (byte) 0xc4, (byte) 0x41,
            (byte) 0x3d, (byte) 0xf8, (byte) 0x3d, (byte) 0x4e, (byte) 0x0b, // 118
            (byte) 0x1c, (byte) 0xdb, (byte) 0x02, (byte) 0x41, (byte) 0x00,
            (byte) 0x82, (byte) 0x9b, (byte) 0x8a, (byte) 0xfd, (byte) 0xa1,
            (byte) 0x98, (byte) 0x41, (byte) 0x68, (byte) 0xc2, (byte) 0xd1,
            (byte) 0xdf, (byte) 0x4e, (byte) 0xf3, (byte) 0x2e, (byte) 0x26,
            (byte) 0x53, (byte) 0x5b, (byte) 0x31, (byte) 0xb1, (byte) 0x7a,
            (byte) 0xcc, (byte) 0x5e, (byte) 0xbb, (byte) 0x09, (byte) 0xa2,
            (byte) 0xe2, (byte) 0x6f, (byte) 0x4a, (byte) 0x04, (byte) 0x0d,
            (byte) 0xef, (byte) 0x90, (byte) 0x15, (byte) 0xbe, (byte) 0x10,
            (byte) 0x4a, (byte) 0xac, (byte) 0x92, (byte) 0xeb, (byte) 0xda,
            (byte) 0x72, (byte) 0xdb, (byte) 0x43, (byte) 0x08, (byte) 0xb7,
            (byte) 0x2b, (byte) 0x4c, (byte) 0xe1, (byte) 0xbb, (byte) 0x58,
            (byte) 0xcb, (byte) 0x71, (byte) 0x80, (byte) 0xad, (byte) 0xbc,
            (byte) 0xdc, (byte) 0x62, (byte) 0x5e, (byte) 0x3e, (byte) 0xcb,
            (byte) 0x92, (byte) 0xda, (byte) 0xf6, (byte) 0xdf, (byte) 0x02,
            (byte) 0x40, (byte) 0x4d, (byte) 0x81, (byte) 0x90, (byte) 0xc5,
            (byte) 0x77, (byte) 0x30, (byte) 0xb7, (byte) 0x29, (byte) 0x00,
            (byte) 0xa8, (byte) 0xf1, (byte) 0xb4, (byte) 0xae, (byte) 0x52,
            (byte) 0x63, (byte) 0x00, (byte) 0xb2, // 140
            (byte) 0x2d, (byte) 0x3e, (byte) 0x7d, (byte) 0xd6, (byte) 0x4d,
            (byte) 0xf9, (byte) 0x8a, (byte) 0xc1, (byte) 0xb1, (byte) 0x98,
            (byte) 0x89, (byte) 0x52, (byte) 0x40, (byte) 0x14, (byte) 0x1b,
            (byte) 0x0e, (byte) 0x61, (byte) 0x8f, (byte) 0xf4, (byte) 0xbe,
            (byte) 0x59, (byte) 0x79, (byte) 0x79, (byte) 0x95, (byte) 0x19,
            (byte) 0x5c, (byte) 0x51, (byte) 0x08, (byte) 0x66, (byte) 0xc1,
            (byte) 0x42, (byte) 0x30, (byte) 0xb3, (byte) 0x7a, (byte) 0x86,
            (byte) 0x9f, (byte) 0x3e, (byte) 0xf5, (byte) 0x19, (byte) 0xa3, // 150
            (byte) 0xae, (byte) 0x64, (byte) 0x69, (byte) 0x14, (byte) 0x07,
            (byte) 0x50, (byte) 0x97, };
}
