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

import java.security.DigestException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLException;
import libcore.util.EmptyArray;

/**
 * This class represents Signature type, as described in TLS v 1.0 Protocol
 * specification, 7.4.3. It allow to init, update and sign hash. Hash algorithm
 * depends on SignatureAlgorithm.
 *
 * select (SignatureAlgorithm)
 *       {   case anonymous: struct { };
 *           case rsa:
 *               digitally-signed struct {
 *                   opaque md5_hash[16];
 *                   opaque sha_hash[20];
 *               };
 *           case dsa:
 *               digitally-signed struct {
 *                   opaque sha_hash[20];
 *               };
 *       } Signature;
 *
 * Digital signing description see in TLS spec., 4.7.
 * (http://www.ietf.org/rfc/rfc2246.txt)
 *
 */
public class DigitalSignature {

    private final MessageDigest md5;
    private final MessageDigest sha;
    private final Signature signature;
    private final Cipher cipher;

    private byte[] md5_hash;
    private byte[] sha_hash;

    /**
     * Create Signature type
     * @param keyExchange
     */
    public DigitalSignature(String authType) {
        try {
            sha = MessageDigest.getInstance("SHA-1");

            if ("RSA".equals(authType)) {
                md5 = MessageDigest.getInstance("MD5");
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                signature = null;
            } else if ("DSA".equals(authType)) {
                // SignatureAlgorithm is dsa
                signature = Signature.getInstance("NONEwithDSA");
                cipher = null;
                md5 = null;
            } else {
                cipher = null;
                signature = null;
                md5 = null;
            }
        } catch (NoSuchAlgorithmException e) {
            // this should never happen
            throw new AssertionError(e);
        } catch (NoSuchPaddingException e) {
            // this should never happen
            throw new AssertionError(e);
        }
    }

    /**
     * Initiate Signature type by private key
     * @param key
     */
    public void init(PrivateKey key) {
        try {
            if (signature != null) {
                signature.initSign(key);
            } else if (cipher != null) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
        } catch (InvalidKeyException e){
            throw new AlertException(AlertProtocol.BAD_CERTIFICATE,
                    new SSLException("init - invalid private key", e));
        }
    }

    /**
     * Initiate Signature type by certificate
     * @param cert
     */
    public void init(Certificate cert) {
        try {
            if (signature != null) {
                signature.initVerify(cert);
            } else if (cipher != null) {
                cipher.init(Cipher.DECRYPT_MODE, cert);
            }
        } catch (InvalidKeyException e){
            throw new AlertException(AlertProtocol.BAD_CERTIFICATE,
                    new SSLException("init - invalid certificate", e));
        }
    }

    /**
     * Update Signature hash
     * @param data
     */
    public void update(byte[] data) {
        if (sha != null) {
            sha.update(data);
        }
        if (md5 != null) {
            md5.update(data);
        }
    }

    /**
     * Sets MD5 hash
     * @param data
     */
    public void setMD5(byte[] data) {
        md5_hash = data;
    }

    /**
     * Sets SHA hash
     * @param data
     */
    public void setSHA(byte[] data) {
        sha_hash = data;
    }

    /**
     * Sign hash
     * @return Signature bytes
     */
    public byte[] sign() {
        try {
            if (md5 != null && md5_hash == null) {
                md5_hash = new byte[16];
                md5.digest(md5_hash, 0, md5_hash.length);
            }
            if (md5_hash != null) {
                if (signature != null) {
                    signature.update(md5_hash);
                } else if (cipher != null) {
                    cipher.update(md5_hash);
                }
            }
            if (sha != null && sha_hash == null) {
                sha_hash = new byte[20];
                sha.digest(sha_hash, 0, sha_hash.length);
            }
            if (sha_hash != null) {
                if (signature != null) {
                    signature.update(sha_hash);
                } else if (cipher != null) {
                    cipher.update(sha_hash);
                }
            }
            if (signature != null) {
                return signature.sign();
            } else if (cipher != null) {
                return cipher.doFinal();
            }
            return EmptyArray.BYTE;
        } catch (DigestException e){
            return EmptyArray.BYTE;
        } catch (SignatureException e){
            return EmptyArray.BYTE;
        } catch (BadPaddingException e){
            return EmptyArray.BYTE;
        } catch (IllegalBlockSizeException e){
            return EmptyArray.BYTE;
        }
    }

    /**
     * Verifies the signature data.
     * @param data - the signature bytes
     * @return true if verified
     */
    public boolean verifySignature(byte[] data) {
        if (signature != null) {
            try {
                signature.update(sha_hash);
                return signature.verify(data);
            } catch (SignatureException e) {
                return false;
            }
        }

        if (cipher != null) {
            final byte[] decrypt;
            try {
                decrypt = cipher.doFinal(data);
            } catch (IllegalBlockSizeException e) {
                return false;
            } catch (BadPaddingException e) {
                return false;
            }

            final byte[] md5_sha;
            if (md5_hash != null && sha_hash != null) {
                md5_sha = new byte[md5_hash.length + sha_hash.length];
                System.arraycopy(md5_hash, 0, md5_sha, 0, md5_hash.length);
                System.arraycopy(sha_hash, 0, md5_sha, md5_hash.length, sha_hash.length);
            } else if (md5_hash != null) {
                md5_sha = md5_hash;
            } else {
                md5_sha = sha_hash;
            }

            return Arrays.equals(decrypt, md5_sha);
        } else if (data == null || data.length == 0) {
            return true;
        } else {
            return false;
        }
    }

}
