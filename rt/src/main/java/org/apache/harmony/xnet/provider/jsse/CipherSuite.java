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

package org.apache.harmony.xnet.provider.jsse;

import java.security.GeneralSecurityException;
import java.util.Hashtable;
import javax.crypto.Cipher;

/**
 * Represents Cipher Suite as defined in TLS 1.0 spec.,
 * A.5. The CipherSuite;
 * C. CipherSuite definitions.
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec.</a>
 *
 */
public class CipherSuite {

    /**
     * true if this cipher suite is supported
     */
    boolean supported = true;

    /**
     * cipher suite key exchange
     */
    final int keyExchange;

    /**
     * algorithm used for authentication ("RSA", "DSA", "DH", null for anonymous)
     */
    final String authType;

    /**
     * cipher
     */
    final String cipherName;

    /**
     * Cipher information
     */
    final int keyMaterial;
    final int expandedKeyMaterial;
    final int effectiveKeyBytes;
    final int ivSize;
    final private int blockSize;

    // cipher suite code
    private final byte[] cipherSuiteCode;

    // cipher suite name
    private final String name;

    // true if cipher suite is exportable
    private final boolean isExportable;

    // Hash algorithm
    final private String hashName;

    // MAC algorithm
    final private String hmacName;

    // Hash size
    final private int hashSize;

    /**
     * key exchange values
     */
    static final int KEY_EXCHANGE_RSA = 1;
    static final int KEY_EXCHANGE_RSA_EXPORT = 2;
    static final int KEY_EXCHANGE_DHE_DSS = 3;
    static final int KEY_EXCHANGE_DHE_DSS_EXPORT = 4;
    static final int KEY_EXCHANGE_DHE_RSA = 5;
    static final int KEY_EXCHANGE_DHE_RSA_EXPORT = 6;
    // BEGIN android-removed
    // static final int KEY_EXCHANGE_DH_DSS = 7;
    // static final int KEY_EXCHANGE_DH_RSA = 8;
    // END android-removed
    static final int KEY_EXCHANGE_DH_anon = 9;
    static final int KEY_EXCHANGE_DH_anon_EXPORT = 10;
    // BEGIN android-removed
    // static final int KEY_EXCHANGE_DH_DSS_EXPORT = 11;
    // static final int KEY_EXCHANGE_DH_RSA_EXPORT = 12;
    // END android-removed
    static final int KEY_EXCHANGE_ECDH_ECDSA = 13;
    static final int KEY_EXCHANGE_ECDHE_ECDSA = 14;
    static final int KEY_EXCHANGE_ECDH_RSA = 15;
    static final int KEY_EXCHANGE_ECDHE_RSA = 16;
    static final int KEY_EXCHANGE_ECDH_anon = 17;

    /**
     * TLS cipher suite codes
     */
    static final byte[] CODE_SSL_NULL_WITH_NULL_NULL = { 0x00, 0x00 };
    static final byte[] CODE_SSL_RSA_WITH_NULL_MD5 = { 0x00, 0x01 };
    static final byte[] CODE_SSL_RSA_WITH_NULL_SHA = { 0x00, 0x02 };
    static final byte[] CODE_SSL_RSA_EXPORT_WITH_RC4_40_MD5 = { 0x00, 0x03 };
    static final byte[] CODE_SSL_RSA_WITH_RC4_128_MD5 = { 0x00, 0x04 };
    static final byte[] CODE_SSL_RSA_WITH_RC4_128_SHA = { 0x00, 0x05 };
    static final byte[] CODE_SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5 = { 0x00, 0x06 };
    // BEGIN android-removed
    // static final byte[] CODE_TLS_RSA_WITH_IDEA_CBC_SHA = { 0x00, 0x07 };
    // END android-removed
    static final byte[] CODE_SSL_RSA_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x08 };
    static final byte[] CODE_SSL_RSA_WITH_DES_CBC_SHA = { 0x00, 0x09 };
    static final byte[] CODE_SSL_RSA_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x0A };
    // BEGIN android-removed
    // static final byte[] CODE_SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x0B };
    // static final byte[] CODE_SSL_DH_DSS_WITH_DES_CBC_SHA = { 0x00, 0x0C };
    // static final byte[] CODE_SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x0D };
    // static final byte[] CODE_SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x0E };
    // static final byte[] CODE_SSL_DH_RSA_WITH_DES_CBC_SHA = { 0x00, 0x0F };
    // static final byte[] CODE_SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x10 };
    // END android-removed
    static final byte[] CODE_SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x11 };
    static final byte[] CODE_SSL_DHE_DSS_WITH_DES_CBC_SHA = { 0x00, 0x12 };
    static final byte[] CODE_SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x13 };
    static final byte[] CODE_SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x14 };
    static final byte[] CODE_SSL_DHE_RSA_WITH_DES_CBC_SHA = { 0x00, 0x15 };
    static final byte[] CODE_SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x16 };
    static final byte[] CODE_SSL_DH_anon_EXPORT_WITH_RC4_40_MD5 = { 0x00, 0x17 };
    static final byte[] CODE_SSL_DH_anon_WITH_RC4_128_MD5 = { 0x00, 0x18 };
    static final byte[] CODE_SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA = { 0x00, 0x19 };
    static final byte[] CODE_SSL_DH_anon_WITH_DES_CBC_SHA = { 0x00, 0x1A };
    static final byte[] CODE_SSL_DH_anon_WITH_3DES_EDE_CBC_SHA = { 0x00, 0x1B };

    // AES Cipher Suites from RFC 3268 - http://www.ietf.org/rfc/rfc3268.txt
    static final byte[] CODE_TLS_RSA_WITH_AES_128_CBC_SHA = { 0x00, 0x2F };
    //static final byte[] CODE_TLS_DH_DSS_WITH_AES_128_CBC_SHA = { 0x00, 0x30 };
    //static final byte[] CODE_TLS_DH_RSA_WITH_AES_128_CBC_SHA = { 0x00, 0x31 };
    static final byte[] CODE_TLS_DHE_DSS_WITH_AES_128_CBC_SHA = { 0x00, 0x32 };
    static final byte[] CODE_TLS_DHE_RSA_WITH_AES_128_CBC_SHA = { 0x00, 0x33 };
    static final byte[] CODE_TLS_DH_anon_WITH_AES_128_CBC_SHA = { 0x00, 0x34 };
    static final byte[] CODE_TLS_RSA_WITH_AES_256_CBC_SHA = { 0x00, 0x35 };
    //static final byte[] CODE_TLS_DH_DSS_WITH_AES_256_CBC_SHA = { 0x00, 0x36 };
    //static final byte[] CODE_TLS_DH_RSA_WITH_AES_256_CBC_SHA = { 0x00, 0x37 };
    static final byte[] CODE_TLS_DHE_DSS_WITH_AES_256_CBC_SHA = { 0x00, 0x38 };
    static final byte[] CODE_TLS_DHE_RSA_WITH_AES_256_CBC_SHA = { 0x00, 0x39 };
    static final byte[] CODE_TLS_DH_anon_WITH_AES_256_CBC_SHA = { 0x00, 0x3A };

    // EC Cipher Suites from RFC 4492 - http://www.ietf.org/rfc/rfc4492.txt
    static final byte[] CODE_TLS_ECDH_ECDSA_WITH_NULL_SHA = { (byte) 0xc0, 0x01};
    static final byte[] CODE_TLS_ECDH_ECDSA_WITH_RC4_128_SHA = { (byte) 0xc0, 0x02};
    static final byte[] CODE_TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = { (byte) 0xc0, 0x03};
    static final byte[] CODE_TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = { (byte) 0xc0, 0x04};
    static final byte[] CODE_TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = { (byte) 0xc0, 0x05};
    static final byte[] CODE_TLS_ECDHE_ECDSA_WITH_NULL_SHA = { (byte) 0xc0, 0x06};
    static final byte[] CODE_TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = { (byte) 0xc0, 0x07};
    static final byte[] CODE_TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = { (byte) 0xc0, 0x08};
    static final byte[] CODE_TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = { (byte) 0xc0, 0x09};
    static final byte[] CODE_TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = { (byte) 0xc0, 0x0A};
    static final byte[] CODE_TLS_ECDH_RSA_WITH_NULL_SHA = { (byte) 0xc0, 0x0B};
    static final byte[] CODE_TLS_ECDH_RSA_WITH_RC4_128_SHA = { (byte) 0xc0, 0x0C};
    static final byte[] CODE_TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = { (byte) 0xc0, 0x0D};
    static final byte[] CODE_TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = { (byte) 0xc0, 0x0E};
    static final byte[] CODE_TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = { (byte) 0xc0, 0x0F};
    static final byte[] CODE_TLS_ECDHE_RSA_WITH_NULL_SHA = { (byte) 0xc0, 0x10};
    static final byte[] CODE_TLS_ECDHE_RSA_WITH_RC4_128_SHA = { (byte) 0xc0, 0x11};
    static final byte[] CODE_TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = { (byte) 0xc0, 0x12};
    static final byte[] CODE_TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = { (byte) 0xc0, 0x13};
    static final byte[] CODE_TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = { (byte) 0xc0, 0x14};
    static final byte[] CODE_TLS_ECDH_anon_WITH_NULL_SHA = { (byte) 0xc0, 0x15};
    static final byte[] CODE_TLS_ECDH_anon_WITH_RC4_128_SHA = { (byte) 0xc0, 0x16};
    static final byte[] CODE_TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = { (byte) 0xc0, 0x17};
    static final byte[] CODE_TLS_ECDH_anon_WITH_AES_128_CBC_SHA = { (byte) 0xc0, 0x18};
    static final byte[] CODE_TLS_ECDH_anon_WITH_AES_256_CBC_SHA = { (byte) 0xc0, 0x19};

    static final CipherSuite SSL_NULL_WITH_NULL_NULL = new CipherSuite(
            "SSL_NULL_WITH_NULL_NULL", true, 0, null, null, null,
            CODE_SSL_NULL_WITH_NULL_NULL);

    static final CipherSuite SSL_RSA_WITH_NULL_MD5 = new CipherSuite(
            "SSL_RSA_WITH_NULL_MD5", true, KEY_EXCHANGE_RSA, "RSA", null, "MD5",
            CODE_SSL_RSA_WITH_NULL_MD5);

    static final CipherSuite SSL_RSA_WITH_NULL_SHA = new CipherSuite(
            "SSL_RSA_WITH_NULL_SHA", true, KEY_EXCHANGE_RSA, "RSA", null, "SHA",
            CODE_SSL_RSA_WITH_NULL_SHA);

    static final CipherSuite SSL_RSA_EXPORT_WITH_RC4_40_MD5 = new CipherSuite(
            "SSL_RSA_EXPORT_WITH_RC4_40_MD5", true, KEY_EXCHANGE_RSA_EXPORT,
            "RSA", "RC4_40", "MD5", CODE_SSL_RSA_EXPORT_WITH_RC4_40_MD5);

    static final CipherSuite SSL_RSA_WITH_RC4_128_MD5 = new CipherSuite(
            "SSL_RSA_WITH_RC4_128_MD5", false, KEY_EXCHANGE_RSA, "RSA", "RC4_128",
            "MD5", CODE_SSL_RSA_WITH_RC4_128_MD5);

    static final CipherSuite SSL_RSA_WITH_RC4_128_SHA = new CipherSuite(
            "SSL_RSA_WITH_RC4_128_SHA", false, KEY_EXCHANGE_RSA, "RSA", "RC4_128",
            "SHA", CODE_SSL_RSA_WITH_RC4_128_SHA);

    static final CipherSuite SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5 = new CipherSuite(
            "SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5", true, KEY_EXCHANGE_RSA_EXPORT,
            "RSA", "RC2_CBC_40", "MD5", CODE_SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5);

    // BEGIN android-removed
    // static final CipherSuite TLS_RSA_WITH_IDEA_CBC_SHA = new CipherSuite(
    //         "TLS_RSA_WITH_IDEA_CBC_SHA", false, KEY_EXCHANGE_RSA, "RSA", "IDEA_CBC",
    //         "SHA", CODE_TLS_RSA_WITH_IDEA_CBC_SHA);
    // END android-removed

    static final CipherSuite SSL_RSA_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
            "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", true, KEY_EXCHANGE_RSA_EXPORT,
            "RSA", "DES40_CBC", "SHA", CODE_SSL_RSA_EXPORT_WITH_DES40_CBC_SHA);

    static final CipherSuite SSL_RSA_WITH_DES_CBC_SHA = new CipherSuite(
            "SSL_RSA_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_RSA, "RSA", "DES_CBC",
            "SHA", CODE_SSL_RSA_WITH_DES_CBC_SHA);

    static final CipherSuite SSL_RSA_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
            "SSL_RSA_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_RSA,
            "RSA", "3DES_EDE_CBC", "SHA", CODE_SSL_RSA_WITH_3DES_EDE_CBC_SHA);

    // BEGIN android-removed
    // static final CipherSuite SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
    //         "SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA", true,
    //         KEY_EXCHANGE_DH_DSS_EXPORT, "DH", "DES40_CBC", "SHA",
    //         CODE_SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA);
    //
    // static final CipherSuite SSL_DH_DSS_WITH_DES_CBC_SHA = new CipherSuite(
    //         "SSL_DH_DSS_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_DH_DSS,
    //         "DH", "DES_CBC", "SHA", CODE_SSL_DH_DSS_WITH_DES_CBC_SHA);
    //
    // static final CipherSuite SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
    //         "SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_DH_DSS,
    //         "DH", "3DES_EDE_CBC", "SHA", CODE_SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA);
    //
    // static final CipherSuite SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
    //         "SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA", true,
    //         KEY_EXCHANGE_DH_RSA_EXPORT, "DH", "DES40_CBC", "SHA",
    //         CODE_SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA);
    //
    // static final CipherSuite SSL_DH_RSA_WITH_DES_CBC_SHA = new CipherSuite(
    //         "SSL_DH_RSA_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_DH_RSA,
    //         "DH", "DES_CBC", "SHA", CODE_SSL_DH_RSA_WITH_DES_CBC_SHA);
    //
    // static final CipherSuite SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
    //         "SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_DH_RSA,
    //         "DH", "3DES_EDE_CBC", "SHA", CODE_SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA);
    // END android-removed

    static final CipherSuite SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
            "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", true,
            KEY_EXCHANGE_DHE_DSS_EXPORT, "DSA", "DES40_CBC", "SHA",
            CODE_SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA);

    static final CipherSuite SSL_DHE_DSS_WITH_DES_CBC_SHA = new CipherSuite(
            "SSL_DHE_DSS_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_DHE_DSS,
            "DSA", "DES_CBC", "SHA", CODE_SSL_DHE_DSS_WITH_DES_CBC_SHA);

    static final CipherSuite SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
            "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_DHE_DSS,
            "DSA", "3DES_EDE_CBC", "SHA", CODE_SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA);

    static final CipherSuite SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
            "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", true,
            KEY_EXCHANGE_DHE_RSA_EXPORT, "RSA", "DES40_CBC", "SHA",
            CODE_SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA);

    static final CipherSuite SSL_DHE_RSA_WITH_DES_CBC_SHA = new CipherSuite(
            "SSL_DHE_RSA_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_DHE_RSA,
            "RSA", "DES_CBC", "SHA", CODE_SSL_DHE_RSA_WITH_DES_CBC_SHA);

    static final CipherSuite SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
            "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_DHE_RSA,
            "RSA", "3DES_EDE_CBC", "SHA", CODE_SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA);

    static final CipherSuite SSL_DH_anon_EXPORT_WITH_RC4_40_MD5 = new CipherSuite(
            "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", true,
            KEY_EXCHANGE_DH_anon_EXPORT, "DH", "RC4_40", "MD5",
            CODE_SSL_DH_anon_EXPORT_WITH_RC4_40_MD5);

    static final CipherSuite SSL_DH_anon_WITH_RC4_128_MD5 = new CipherSuite(
            "SSL_DH_anon_WITH_RC4_128_MD5", false, KEY_EXCHANGE_DH_anon,
            "DH", "RC4_128", "MD5", CODE_SSL_DH_anon_WITH_RC4_128_MD5);

    static final CipherSuite SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA = new CipherSuite(
            "SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", true,
            KEY_EXCHANGE_DH_anon_EXPORT, "DH", "DES40_CBC", "SHA",
            CODE_SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA);

    static final CipherSuite SSL_DH_anon_WITH_DES_CBC_SHA = new CipherSuite(
            "SSL_DH_anon_WITH_DES_CBC_SHA", false, KEY_EXCHANGE_DH_anon,
            "DH", "DES_CBC", "SHA", CODE_SSL_DH_anon_WITH_DES_CBC_SHA);

    static final CipherSuite SSL_DH_anon_WITH_3DES_EDE_CBC_SHA = new CipherSuite(
            "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", false, KEY_EXCHANGE_DH_anon,
            "DH", "3DES_EDE_CBC", "SHA", CODE_SSL_DH_anon_WITH_3DES_EDE_CBC_SHA);

    static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_RSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_RSA,
                              "RSA",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_RSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DHE_DSS,
                              "DSA",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_DHE_DSS_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DHE_RSA,
                              "RSA",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_DHE_RSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_DH_anon_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DH_anon,
                              "DH",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_DH_anon_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_RSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_RSA,
                              "RSA",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_RSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DHE_DSS,
                              "DSA",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_DHE_DSS_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DHE_RSA,
                              "RSA",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_DHE_RSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_DH_anon_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_DH_anon,
                              "DH",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_DH_anon_WITH_AES_256_CBC_SHA);

    static final CipherSuite TLS_ECDH_ECDSA_WITH_NULL_SHA
            = new CipherSuite("TLS_ECDH_ECDSA_WITH_NULL_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_ECDSA,
                              "EC",
                              null,
                              "SHA",
                              CODE_TLS_ECDH_ECDSA_WITH_NULL_SHA);
    static final CipherSuite TLS_ECDH_ECDSA_WITH_RC4_128_SHA
            = new CipherSuite("TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_ECDSA,
                              "EC",
                              "RC4_128",
                              "SHA",
                              CODE_TLS_ECDH_ECDSA_WITH_RC4_128_SHA);
    static final CipherSuite TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA
            = new CipherSuite("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_ECDSA,
                              "EC",
                              "3DES_EDE_CBC",
                              "SHA",
                              CODE_TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA);
    static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_ECDSA,
                              "EC",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_ECDSA,
                              "EC",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_ECDHE_ECDSA_WITH_NULL_SHA
            = new CipherSuite("TLS_ECDHE_ECDSA_WITH_NULL_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_ECDSA,
                              "EC",
                              null,
                              "SHA",
                              CODE_TLS_ECDHE_ECDSA_WITH_NULL_SHA);
    static final CipherSuite TLS_ECDHE_ECDSA_WITH_RC4_128_SHA
            = new CipherSuite("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_ECDSA,
                              "EC",
                              "RC4_128",
                              "SHA",
                              CODE_TLS_ECDHE_ECDSA_WITH_RC4_128_SHA);
    static final CipherSuite TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA
            = new CipherSuite("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_ECDSA,
                              "EC",
                              "3DES_EDE_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA);
    static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_ECDSA,
                              "EC",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_ECDSA,
                              "EC",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_ECDH_RSA_WITH_NULL_SHA
            = new CipherSuite("TLS_ECDH_RSA_WITH_NULL_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_RSA,
                              "EC",
                              null,
                              "SHA",
                              CODE_TLS_ECDH_RSA_WITH_NULL_SHA);
    static final CipherSuite TLS_ECDH_RSA_WITH_RC4_128_SHA
            = new CipherSuite("TLS_ECDH_RSA_WITH_RC4_128_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_RSA,
                              "EC",
                              "RC4_128",
                              "SHA",
                              CODE_TLS_ECDH_RSA_WITH_RC4_128_SHA);
    static final CipherSuite TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA
            = new CipherSuite("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_RSA,
                              "EC",
                              "3DES_EDE_CBC",
                              "SHA",
                              CODE_TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA);
    static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_RSA,
                              "EC",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_ECDH_RSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_RSA,
                              "EC",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_ECDH_RSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_ECDHE_RSA_WITH_NULL_SHA
            = new CipherSuite("TLS_ECDHE_RSA_WITH_NULL_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_RSA,
                              "EC",
                              null,
                              "SHA",
                              CODE_TLS_ECDHE_RSA_WITH_NULL_SHA);
    static final CipherSuite TLS_ECDHE_RSA_WITH_RC4_128_SHA
            = new CipherSuite("TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_RSA,
                              "EC",
                              "RC4_128",
                              "SHA",
                              CODE_TLS_ECDHE_RSA_WITH_RC4_128_SHA);
    static final CipherSuite TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA
            = new CipherSuite("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_RSA,
                              "EC",
                              "3DES_EDE_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA);
    static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_RSA,
                              "EC",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDHE_RSA,
                              "EC",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
    static final CipherSuite TLS_ECDH_anon_WITH_NULL_SHA
            = new CipherSuite("TLS_ECDH_anon_WITH_NULL_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_anon,
                              "EC",
                              null,
                              "SHA",
                              CODE_TLS_ECDH_anon_WITH_NULL_SHA);
    static final CipherSuite TLS_ECDH_anon_WITH_RC4_128_SHA
            = new CipherSuite("TLS_ECDH_anon_WITH_RC4_128_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_anon,
                              "EC",
                              "RC4_128",
                              "SHA",
                              CODE_TLS_ECDH_anon_WITH_RC4_128_SHA);
    static final CipherSuite TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA
            = new CipherSuite("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_anon,
                              "EC",
                              "3DES_EDE_CBC",
                              "SHA",
                              CODE_TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA);
    static final CipherSuite TLS_ECDH_anon_WITH_AES_128_CBC_SHA
            = new CipherSuite("TLS_ECDH_anon_WITH_AES_128_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_anon,
                              "EC",
                              "AES_128_CBC",
                              "SHA",
                              CODE_TLS_ECDH_anon_WITH_AES_128_CBC_SHA);
    static final CipherSuite TLS_ECDH_anon_WITH_AES_256_CBC_SHA
            = new CipherSuite("TLS_ECDH_anon_WITH_AES_256_CBC_SHA",
                              false,
                              KEY_EXCHANGE_ECDH_anon,
                              "EC",
                              "AES_256_CBC",
                              "SHA",
                              CODE_TLS_ECDH_anon_WITH_AES_256_CBC_SHA);

    // arrays for quick access to cipher suite by code
    private static final CipherSuite[] SUITES_BY_CODE_0x00 = {
        // http://www.iana.org/assignments/tls-parameters/tls-parameters.xml
        SSL_NULL_WITH_NULL_NULL,                          // { 0x00, 0x00 };
        SSL_RSA_WITH_NULL_MD5,                            // { 0x00, 0x01 };
        SSL_RSA_WITH_NULL_SHA,                            // { 0x00, 0x02 };
        SSL_RSA_EXPORT_WITH_RC4_40_MD5,                   // { 0x00, 0x03 };
        SSL_RSA_WITH_RC4_128_MD5,                         // { 0x00, 0x04 };
        SSL_RSA_WITH_RC4_128_SHA,                         // { 0x00, 0x05 };
        // BEGIN android-changed
        null, // SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5,      // { 0x00, 0x06 };
        null, // TLS_RSA_WITH_IDEA_CBC_SHA,               // { 0x00, 0x07 };
        // END android-changed
        SSL_RSA_EXPORT_WITH_DES40_CBC_SHA,                // { 0x00, 0x08 };
        SSL_RSA_WITH_DES_CBC_SHA,                         // { 0x00, 0x09 };
        SSL_RSA_WITH_3DES_EDE_CBC_SHA,                    // { 0x00, 0x0a };
        // BEGIN android-changed
        null, // SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA     // { 0x00, 0x0b };
        null, // SSL_DH_DSS_WITH_DES_CBC_SHA,             // { 0x00, 0x0c };
        null, // SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA,        // { 0x00, 0x0d };
        null, // SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA,    // { 0x00, 0x0e };
        null, // SSL_DH_RSA_WITH_DES_CBC_SHA,             // { 0x00, 0x0f };
        null, // SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA,        // { 0x00, 0x10 };
        // END android-changed
        SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA,            // { 0x00, 0x11 };
        SSL_DHE_DSS_WITH_DES_CBC_SHA,                     // { 0x00, 0x12 };
        SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA,                // { 0x00, 0x13 };
        SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA,            // { 0x00, 0x14 };
        SSL_DHE_RSA_WITH_DES_CBC_SHA,                     // { 0x00, 0x15 };
        SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,                // { 0x00, 0x16 };
        SSL_DH_anon_EXPORT_WITH_RC4_40_MD5,               // { 0x00, 0x17 };
        SSL_DH_anon_WITH_RC4_128_MD5,                     // { 0x00, 0x18 };
        SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA,            // { 0x00, 0x19 };
        SSL_DH_anon_WITH_DES_CBC_SHA,                     // { 0x00, 0x1A };
        SSL_DH_anon_WITH_3DES_EDE_CBC_SHA,                // { 0x00, 0x1B };
        // BEGIN android-added
        null, // SSL_FORTEZZA_KEA_WITH_NULL_SHA           // { 0x00, 0x1C };
        null, // SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA   // { 0x00, 0x1D };
        null, // TLS_KRB5_WITH_DES_CBC_SHA                // { 0x00, 0x1E };
        null, // TLS_KRB5_WITH_3DES_EDE_CBC_SHA           // { 0x00, 0x1F };
        null, // TLS_KRB5_WITH_RC4_128_SHA                // { 0x00, 0x20 };
        null, // TLS_KRB5_WITH_IDEA_CBC_SHA               // { 0x00, 0x21 };
        null, // TLS_KRB5_WITH_DES_CBC_MD5                // { 0x00, 0x22 };
        null, // TLS_KRB5_WITH_3DES_EDE_CBC_MD5           // { 0x00, 0x23 };
        null, // TLS_KRB5_WITH_RC4_128_MD5                // { 0x00, 0x24 };
        null, // TLS_KRB5_WITH_IDEA_CBC_MD5               // { 0x00, 0x25 };
        null, // TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA      // { 0x00, 0x26 };
        null, // TLS_KRB5_EXPORT_WITH_RC2_CBC_40_SHA      // { 0x00, 0x27 };
        null, // TLS_KRB5_EXPORT_WITH_RC4_40_SHA          // { 0x00, 0x28 };
        null, // TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5      // { 0x00, 0x29 };
        null, // TLS_KRB5_EXPORT_WITH_RC2_CBC_40_MD5      // { 0x00, 0x2A };
        null, // TLS_KRB5_EXPORT_WITH_RC4_40_MD5          // { 0x00, 0x2B };
        null, // TLS_PSK_WITH_NULL_SHA                    // { 0x00, 0x2C };
        null, // TLS_DHE_PSK_WITH_NULL_SHA                // { 0x00, 0x2D };
        null, // TLS_RSA_PSK_WITH_NULL_SHA                // { 0x00, 0x2E };
        TLS_RSA_WITH_AES_128_CBC_SHA,                     // { 0x00, 0x2F };
        null, // TLS_DH_DSS_WITH_AES_128_CBC_SHA          // { 0x00, 0x30 };
        null, // TLS_DH_RSA_WITH_AES_128_CBC_SHA          // { 0x00, 0x31 };
        TLS_DHE_DSS_WITH_AES_128_CBC_SHA,                 // { 0x00, 0x32 };
        TLS_DHE_RSA_WITH_AES_128_CBC_SHA,                 // { 0x00, 0x33 };
        TLS_DH_anon_WITH_AES_128_CBC_SHA,                 // { 0x00, 0x34 };
        TLS_RSA_WITH_AES_256_CBC_SHA,                     // { 0x00, 0x35 };
        null, // TLS_DH_DSS_WITH_AES_256_CBC_SHA,         // { 0x00, 0x36 };
        null, // TLS_DH_RSA_WITH_AES_256_CBC_SHA,         // { 0x00, 0x37 };
        TLS_DHE_DSS_WITH_AES_256_CBC_SHA,                 // { 0x00, 0x38 };
        TLS_DHE_RSA_WITH_AES_256_CBC_SHA,                 // { 0x00, 0x39 };
        TLS_DH_anon_WITH_AES_256_CBC_SHA,                 // { 0x00, 0x3A };
        // END android-added
    };
    private static final CipherSuite[] SUITES_BY_CODE_0xc0 = {
        null,                                             // { 0xc0, 0x00};
        TLS_ECDH_ECDSA_WITH_NULL_SHA,                     // { 0xc0, 0x01};
        TLS_ECDH_ECDSA_WITH_RC4_128_SHA,                  // { 0xc0, 0x02};
        TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA,             // { 0xc0, 0x03};
        TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA,              // { 0xc0, 0x04};
        TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA,              // { 0xc0, 0x05};
        TLS_ECDHE_ECDSA_WITH_NULL_SHA,                    // { 0xc0, 0x06};
        TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,                 // { 0xc0, 0x07};
        TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA,            // { 0xc0, 0x08};
        TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,             // { 0xc0, 0x09};
        TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,             // { 0xc0, 0x0A};
        TLS_ECDH_RSA_WITH_NULL_SHA,                       // { 0xc0, 0x0B};
        TLS_ECDH_RSA_WITH_RC4_128_SHA,                    // { 0xc0, 0x0C};
        TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA,               // { 0xc0, 0x0D};
        TLS_ECDH_RSA_WITH_AES_128_CBC_SHA,                // { 0xc0, 0x0E};
        TLS_ECDH_RSA_WITH_AES_256_CBC_SHA,                // { 0xc0, 0x0F};
        TLS_ECDHE_RSA_WITH_NULL_SHA,                      // { 0xc0, 0x10};
        TLS_ECDHE_RSA_WITH_RC4_128_SHA,                   // { 0xc0, 0x11};
        TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,              // { 0xc0, 0x12};
        TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,               // { 0xc0, 0x13};
        TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,               // { 0xc0, 0x14};
        TLS_ECDH_anon_WITH_NULL_SHA,                      // { 0xc0, 0x15};
        TLS_ECDH_anon_WITH_RC4_128_SHA,                   // { 0xc0, 0x16};
        TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA,              // { 0xc0, 0x17};
        TLS_ECDH_anon_WITH_AES_128_CBC_SHA,               // { 0xc0, 0x18};
        TLS_ECDH_anon_WITH_AES_256_CBC_SHA,               // { 0xc0, 0x19};
        // TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA,             // { 0xc0, 0x1A};
        // TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA,         // { 0xc0, 0x1B};
        // TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA,         // { 0xc0, 0x1C};
        // TLS_SRP_SHA_WITH_AES_128_CBC_SHA,              // { 0xc0, 0x1D};
        // TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA,          // { 0xc0, 0x1E};
        // TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA,          // { 0xc0, 0x1F};
        // TLS_SRP_SHA_WITH_AES_256_CBC_SHA,              // { 0xc0, 0x20};
        // TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA,          // { 0xc0, 0x21};
        // TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA,          // { 0xc0, 0x22};
        // TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256,       // { 0xc0, 0x23};
        // TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384,       // { 0xc0, 0x24};
        // TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256,        // { 0xc0, 0x25};
        // TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384,        // { 0xc0, 0x26};
        // TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,         // { 0xc0, 0x27};
        // TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,         // { 0xc0, 0x28};
        // TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256,          // { 0xc0, 0x29};
        // TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384,          // { 0xc0, 0x2A};
        // TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,       // { 0xc0, 0x2B};
        // TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,       // { 0xc0, 0x2C};
        // TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256,        // { 0xc0, 0x2D};
        // TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384,        // { 0xc0, 0x2E};
        // TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,         // { 0xc0, 0x2F};
        // TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,         // { 0xc0, 0x30};
        // TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256,          // { 0xc0, 0x31};
        // TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384,          // { 0xc0, 0x32};
        // TLS_ECDHE_PSK_WITH_RC4_128_SHA,                // { 0xc0, 0x33};
        // TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA,           // { 0xc0, 0x34};
        // TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA,            // { 0xc0, 0x35};
        // TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA,            // { 0xc0, 0x36};
        // TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256,         // { 0xc0, 0x37};
        // TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384,         // { 0xc0, 0x38};
        // TLS_ECDHE_PSK_WITH_NULL_SHA,                   // { 0xc0, 0x39};
        // TLS_ECDHE_PSK_WITH_NULL_SHA256,                // { 0xc0, 0x3A};
        // TLS_ECDHE_PSK_WITH_NULL_SHA384,                // { 0xc0, 0x3B};
    };

    // hash for quick access to cipher suite by name
    private static final Hashtable<String, CipherSuite> SUITES_BY_NAME;

    /**
     * array of supported cipher suites.
     * Set of supported suites is defined at the moment provider's start
     */
    //  TODO Dynamically supported suites: new providers may be dynamically
    //  added/removed and the set of supported suites may be changed
    static final CipherSuite[] SUPPORTED_CIPHER_SUITES;

    /**
     * array of supported cipher suites names
     */
    static final String[] SUPPORTED_CIPHER_SUITE_NAMES;

    /**
     * default cipher suites
     */
    static final CipherSuite[] DEFAULT_CIPHER_SUITES;

    static {
        SUITES_BY_NAME = new Hashtable<String, CipherSuite>();
        int count_0x00 = registerCipherSuitesByCode(SUITES_BY_CODE_0x00);
        int count_0xc0 = registerCipherSuitesByCode(SUITES_BY_CODE_0xc0);
        int count = count_0x00 + count_0xc0;
        SUPPORTED_CIPHER_SUITES = new CipherSuite[count];
        SUPPORTED_CIPHER_SUITE_NAMES = new String[count];
        registerSupportedCipherSuites(0, SUITES_BY_CODE_0x00);
        registerSupportedCipherSuites(count_0x00, SUITES_BY_CODE_0xc0);

        CipherSuite[] defaultCipherSuites = {
                SSL_RSA_WITH_RC4_128_MD5,
                SSL_RSA_WITH_RC4_128_SHA,
                TLS_RSA_WITH_AES_128_CBC_SHA,
                TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                SSL_RSA_WITH_3DES_EDE_CBC_SHA,
                SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,
                SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA,
                SSL_RSA_WITH_DES_CBC_SHA,
                SSL_DHE_RSA_WITH_DES_CBC_SHA,
                SSL_DHE_DSS_WITH_DES_CBC_SHA,
                SSL_RSA_EXPORT_WITH_RC4_40_MD5,
                SSL_RSA_EXPORT_WITH_DES40_CBC_SHA,
                SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA,
                SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                };
        count = 0;
        for (int i = 0; i < defaultCipherSuites.length; i++) {
            if (defaultCipherSuites[i].supported) {
                count++;
            }
        }
        DEFAULT_CIPHER_SUITES = new CipherSuite[count];
        count = 0;
        for (int i = 0; i < defaultCipherSuites.length; i++) {
            if (defaultCipherSuites[i].supported) {
                DEFAULT_CIPHER_SUITES[count++] = defaultCipherSuites[i];
            }
        }
    }
    private static int registerCipherSuitesByCode(CipherSuite[] cipherSuites) {
        int count = 0;
        for (int i = 0; i < cipherSuites.length; i++) {
            if (cipherSuites[i] == SSL_NULL_WITH_NULL_NULL) {
                continue;
            }
            if (cipherSuites[i] == null) {
                continue;
            }
            SUITES_BY_NAME.put(cipherSuites[i].getName(), cipherSuites[i]);
            if (cipherSuites[i].supported) {
                count++;
            }
        }
        return count;
    }
    private static void registerSupportedCipherSuites(int offset, CipherSuite[] cipherSuites) {
        int count = offset;
        for (int i = 0; i < cipherSuites.length; i++) {
            if (cipherSuites[i] == SSL_NULL_WITH_NULL_NULL) {
                continue;
            }
            if (cipherSuites[i] == null) {
                continue;
            }
            if (cipherSuites[i].supported) {
                SUPPORTED_CIPHER_SUITES[count] = cipherSuites[i];
                SUPPORTED_CIPHER_SUITE_NAMES[count] = SUPPORTED_CIPHER_SUITES[count].getName();
                count++;
            }
        }
    }

    /**
     * Returns CipherSuite by name
     */
    public static CipherSuite getByName(String name) {
        return SUITES_BY_NAME.get(name);
    }

    /**
     * Returns CipherSuite based on TLS CipherSuite code
     * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., A.5. The CipherSuite</a>
     */
    public static CipherSuite getByCode(byte b1, byte b2) {
        int i1 = b1 & 0xff;
        int i2 = b2 & 0xff;
        CipherSuite cs = getCipherSuiteByCode(0, i1, i2);
        if (cs != null) {
            return cs;
        }
        return new CipherSuite("UNKNOWN_" + i1 + "_" + i2, false, 0, null,
                               null, null, new byte[] { b1, b2 });
    }

    /**
     * Returns CipherSuite based on V2CipherSpec code
     * as described in TLS 1.0 spec., E. Backward Compatibility With SSL
     */
    public static CipherSuite getByCode(byte b1, byte b2, byte b3) {
        int i1 = b1 & 0xff;
        int i2 = b2 & 0xff;
        int i3 = b3 & 0xff;
        CipherSuite cs = getCipherSuiteByCode(i1, i2, i3);
        if (cs != null) {
            return cs;
        }
        return new CipherSuite("UNKNOWN_" + i1 + "_" + i2 + "_" + i3, false, 0,
                               null, null, null, new byte[] { b1, b2, b3 });
    }

    private static CipherSuite getCipherSuiteByCode(int i1, int i2, int i3) {
        CipherSuite[] cipherSuites;
        if (i1 == 0x00 && i2 == 0x00) {
            cipherSuites = SUITES_BY_CODE_0x00;
        } else if (i1 == 0x00 && i2 == 0xc0) {
            cipherSuites = SUITES_BY_CODE_0xc0;
        } else {
            return null;
        }
        if (i3 >= cipherSuites.length) {
            return null;
        }
        return cipherSuites[i3];
    }

    /**
     * Creates CipherSuite
     */
    private CipherSuite(String name, boolean isExportable, int keyExchange,
            String authType, String cipherName, String hash, byte[] code) {
        this.name = name;
        this.keyExchange = keyExchange;
        this.authType = authType;
        this.isExportable = isExportable;
        if (cipherName == null) {
            this.cipherName = null;
            keyMaterial = 0;
            expandedKeyMaterial = 0;
            effectiveKeyBytes = 0;
            ivSize = 0;
            blockSize = 0;
        // BEGIN android-removed
        // } else if ("IDEA_CBC".equals(cipherName)) {
        //     this.cipherName = "IDEA/CBC/NoPadding";
        //     keyMaterial = 16;
        //     expandedKeyMaterial = 16;
        //     effectiveKeyBytes = 16;
        //     ivSize = 8;
        //     blockSize = 8;
        // } else if ("RC2_CBC_40".equals(cipherName)) {
        //     this.cipherName = "RC2/CBC/NoPadding";
        //     keyMaterial = 5;
        //     expandedKeyMaterial = 16;
        //     effectiveKeyBytes = 5;
        //     ivSize = 8;
        //     blockSize = 8;
        // END android-removed
        } else if ("RC4_40".equals(cipherName)) {
            this.cipherName = "RC4";
            keyMaterial = 5;
            expandedKeyMaterial = 16;
            effectiveKeyBytes = 5;
            ivSize = 0;
            blockSize = 0;
        } else if ("RC4_128".equals(cipherName)) {
            this.cipherName = "RC4";
            keyMaterial = 16;
            expandedKeyMaterial = 16;
            effectiveKeyBytes = 16;
            ivSize = 0;
            blockSize = 0;
        } else if ("DES40_CBC".equals(cipherName)) {
            this.cipherName = "DES/CBC/NoPadding";
            keyMaterial = 5;
            expandedKeyMaterial = 8;
            effectiveKeyBytes = 5;
            ivSize = 8;
            blockSize = 8;
        } else if ("DES_CBC".equals(cipherName)) {
            this.cipherName = "DES/CBC/NoPadding";
            keyMaterial = 8;
            expandedKeyMaterial = 8;
            effectiveKeyBytes = 7;
            ivSize = 8;
            blockSize = 8;
        } else if ("3DES_EDE_CBC".equals(cipherName)) {
            this.cipherName = "DESede/CBC/NoPadding";
            keyMaterial = 24;
            expandedKeyMaterial = 24;
            effectiveKeyBytes = 24;
            ivSize = 8;
            blockSize = 8;
        } else if ("AES_128_CBC".equals(cipherName)) {
            this.cipherName = "AES/CBC/NoPadding";
            keyMaterial = 16;
            expandedKeyMaterial = 16;
            effectiveKeyBytes = 16;
            ivSize = 16;
            blockSize = 16;
        } else if ("AES_256_CBC".equals(cipherName)) {
            this.cipherName = "AES/CBC/NoPadding";
            keyMaterial = 32;
            expandedKeyMaterial = 32;
            effectiveKeyBytes = 32;
            ivSize = 16;
            blockSize = 16;
        } else {
            this.cipherName = cipherName;
            keyMaterial = 0;
            expandedKeyMaterial = 0;
            effectiveKeyBytes = 0;
            ivSize = 0;
            blockSize = 0;
        }

        if ("MD5".equals(hash)) {
            this.hmacName = "HmacMD5";
            this.hashName = "MD5";
            hashSize = 16;
        } else if ("SHA".equals(hash)) {
            this.hmacName = "HmacSHA1";
            this.hashName = "SHA-1";
            hashSize = 20;
        } else {
            this.hmacName = null;
            this.hashName = null;
            hashSize = 0;
        }

        cipherSuiteCode = code;

        if (this.cipherName != null) {
            try {
                Cipher.getInstance(this.cipherName);
            } catch (GeneralSecurityException e) {
                supported = false;
            }
        }

        // We define the Elliptic Curve cipher suites for use with
        // code shared by OpenSSL, but they are not supported by
        // SSLEngine or SSLSocket's built with SSLEngine.
        if (this.name.startsWith("TLS_EC")) {
            supported = false;
        }
    }

    /**
     * Returns true if cipher suite is anonymous
     * @return
     */
    public boolean isAnonymous() {
        if (keyExchange == KEY_EXCHANGE_DH_anon
                || keyExchange == KEY_EXCHANGE_DH_anon_EXPORT
                || keyExchange == KEY_EXCHANGE_ECDH_anon) {
            return true;
        }
        return false;
    }

    /**
     * Returns array of supported CipherSuites
     * @return
     */
    public static CipherSuite[] getSupported() {
        return SUPPORTED_CIPHER_SUITES;
    }

    /**
     * Returns array of supported cipher suites names
     * @return
     */
    public static String[] getSupportedCipherSuiteNames() {
        return SUPPORTED_CIPHER_SUITE_NAMES.clone();
    }

    /**
     * Returns cipher suite name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns cipher suite code as byte array
     * @return
     */
    public byte[] toBytes() {
        return cipherSuiteCode;
    }

    /**
     * Returns cipher suite description
     */
    @Override
    public String toString() {
        return name + ": " + cipherSuiteCode[0] + " " + cipherSuiteCode[1];
    }

    /**
     * Returns cipher algorithm name
     * @return
     */
    public String getBulkEncryptionAlgorithm() {
        return cipherName;
    }

    /**
     * Returns cipher block size
     * @return
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * Returns MAC algorithm name
     * @return
     */
    public String getHmacName() {
        return hmacName;
    }

    /**
     * Returns hash algorithm name
     * @return
     */
    public String getHashName() {
        return hashName;
    }

    /**
     * Returns hash size
     * @return
     */
    public int getMACLength() {
        return hashSize;
    }

    /**
     * Indicates whether this cipher suite is exportable
     * @return
     */
    public boolean isExportable() {
        return isExportable;
    }

    static final String KEY_TYPE_RSA = "RSA";
    static final String KEY_TYPE_DSA = "DSA";
    static final String KEY_TYPE_DH_RSA = "DH_RSA";
    static final String KEY_TYPE_DH_DSA = "DH_DSA";
    static final String KEY_TYPE_EC = "EC";
    static final String KEY_TYPE_EC_EC = "EC_EC";
    static final String KEY_TYPE_EC_RSA = "EC_RSA";

    /**
     * Returns key type constant suitable for calling
     * X509KeyManager.chooseServerAlias or
     * X509ExtendedKeyManager.chooseEngineServerAlias.
     */
    public String getServerKeyType() {
        switch (keyExchange) {
            case KEY_EXCHANGE_DHE_RSA:
            case KEY_EXCHANGE_DHE_RSA_EXPORT:
            case KEY_EXCHANGE_ECDHE_RSA:
            case KEY_EXCHANGE_RSA:
            case KEY_EXCHANGE_RSA_EXPORT:
                return KEY_TYPE_RSA;
            case KEY_EXCHANGE_DHE_DSS:
            case KEY_EXCHANGE_DHE_DSS_EXPORT:
                return KEY_TYPE_DSA;
            case KEY_EXCHANGE_ECDH_ECDSA:
            case KEY_EXCHANGE_ECDHE_ECDSA:
                return KEY_TYPE_EC_EC;
            case KEY_EXCHANGE_ECDH_RSA:
                return KEY_TYPE_EC_RSA;
            case KEY_EXCHANGE_DH_anon:
            case KEY_EXCHANGE_DH_anon_EXPORT:
            case KEY_EXCHANGE_ECDH_anon:
                return null;
            default:
                throw new IllegalStateException("Unknown key type for key exchange " + keyExchange);
        }
    }

    /**
     * Client certificate types as defined in
     * TLS 1.0 spec., 7.4.4. Certificate request.
     * EC constants from RFC 4492.
     * Names match openssl constants.
     */
    static final byte TLS_CT_RSA_SIGN = 1;
    static final byte TLS_CT_DSS_SIGN = 2;
    static final byte TLS_CT_RSA_FIXED_DH = 3;
    static final byte TLS_CT_DSS_FIXED_DH = 4;
    static final byte TLS_CT_ECDSA_SIGN = 64;
    static final byte TLS_CT_RSA_FIXED_ECDH = 65;
    static final byte TLS_CT_ECDSA_FIXED_ECDH = 66;

    /**
     * Similar to getServerKeyType, but returns value given TLS
     * ClientCertificateType byte values from a CertificateRequest
     * message for use with X509KeyManager.chooseClientAlias or
     * X509ExtendedKeyManager.chooseEngineClientAlias.
     */
    public static String getClientKeyType(byte keyType) {
        // See also http://www.ietf.org/assignments/tls-parameters/tls-parameters.xml
        switch (keyType) {
            case TLS_CT_RSA_SIGN:
                return KEY_TYPE_RSA; // RFC rsa_sign
            case TLS_CT_DSS_SIGN:
                return KEY_TYPE_DSA; // RFC dss_sign
            case TLS_CT_RSA_FIXED_DH:
                return KEY_TYPE_DH_RSA; // RFC rsa_fixed_dh
            case TLS_CT_DSS_FIXED_DH:
                return KEY_TYPE_DH_DSA; // RFC dss_fixed_dh
            case TLS_CT_ECDSA_SIGN:
                return KEY_TYPE_EC; // RFC ecdsa_sign
            case TLS_CT_RSA_FIXED_ECDH:
                return KEY_TYPE_EC_RSA; // RFC rsa_fixed_ecdh
            case TLS_CT_ECDSA_FIXED_ECDH:
                return KEY_TYPE_EC_EC; // RFC ecdsa_fixed_ecdh
            default:
                return null;
        }
    }

    private static final String AUTH_TYPE_RSA = "RSA";
    private static final String AUTH_TYPE_RSA_EXPORT = "RSA_EXPORT";
    private static final String AUTH_TYPE_DHE_DSS = "DHE_DSS";
    private static final String AUTH_TYPE_DHE_RSA = "DHE_RSA";
    private static final String AUTH_TYPE_DH_DSS = "DH_DSS";
    private static final String AUTH_TYPE_DH_RSA = "DH_RSA";
    private static final String AUTH_TYPE_ECDH_ECDSA = "ECDH_ECDSA";
    private static final String AUTH_TYPE_ECDH_RSA = "ECDH_RSA";
    private static final String AUTH_TYPE_ECDHE_ECDSA = "ECDHE_ECDSA";
    private static final String AUTH_TYPE_ECDHE_RSA = "ECDHE_RSA";

    /**
     * Returns auth type constant suitable for calling X509TrustManager.checkServerTrusted.
     */
    public String getAuthType(boolean emphemeral) {
        switch (keyExchange) {
            case KEY_EXCHANGE_RSA:
                return AUTH_TYPE_RSA;
            case KEY_EXCHANGE_RSA_EXPORT:
                return emphemeral ? AUTH_TYPE_RSA_EXPORT : AUTH_TYPE_RSA;
            case KEY_EXCHANGE_DHE_DSS:
            case KEY_EXCHANGE_DHE_DSS_EXPORT:
                return AUTH_TYPE_DHE_DSS;
            case KEY_EXCHANGE_DHE_RSA:
            case KEY_EXCHANGE_DHE_RSA_EXPORT:
                return AUTH_TYPE_DHE_RSA;
            case KEY_EXCHANGE_ECDH_ECDSA:
                return AUTH_TYPE_ECDH_ECDSA;
            case KEY_EXCHANGE_ECDHE_ECDSA:
                return AUTH_TYPE_ECDHE_ECDSA;
            case KEY_EXCHANGE_ECDH_RSA:
                return AUTH_TYPE_ECDH_RSA;
            case KEY_EXCHANGE_ECDHE_RSA:
                return AUTH_TYPE_ECDHE_RSA;
            case KEY_EXCHANGE_DH_anon:
            case KEY_EXCHANGE_DH_anon_EXPORT:
            case KEY_EXCHANGE_ECDH_anon:
                return null;
            default:
                throw new IllegalStateException("Unknown auth type for key exchange " + keyExchange);
        }
    }
}
