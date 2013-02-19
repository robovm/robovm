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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLException;

/**
 * This class provides functionality for computation
 * of PRF values for TLS (http://www.ietf.org/rfc/rfc2246.txt)
 * and SSL v3 (http://wp.netscape.com/eng/ssl3) protocols.
 */
public class PRF {
    private static Logger.Stream logger = Logger.getStream("prf");

    private static Mac md5_mac;
    private static Mac sha_mac;
    protected static MessageDigest md5;
    protected static MessageDigest sha;
    private static int md5_mac_length;
    private static int sha_mac_length;

    static private void init() {
        try {
            md5_mac = Mac.getInstance("HmacMD5");
            sha_mac = Mac.getInstance("HmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLException(
                "There is no provider of HmacSHA1 or HmacMD5 "
                + "algorithms installed in the system"));
        }
        md5_mac_length = md5_mac.getMacLength();
        sha_mac_length = sha_mac.getMacLength();
        try {
            md5 = MessageDigest.getInstance("MD5");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLException(
                    "Could not initialize the Digest Algorithms."));
        }
    }

    /**
     * Computes the value of SSLv3 pseudo random function.
     * @param   out:    the buffer to fill up with the value of the function.
     * @param   secret: the buffer containing the secret value to generate prf.
     * @param   seed:   the seed to be used.
     */
    static synchronized void computePRF_SSLv3(byte[] out, byte[] secret, byte[] seed) {
        if (sha == null) {
            init();
        }
        int pos = 0;
        int iteration = 1;
        byte[] digest;
        while (pos < out.length) {
            byte[] pref = new byte[iteration];
            Arrays.fill(pref, (byte) (64 + iteration++));
            sha.update(pref);
            sha.update(secret);
            sha.update(seed);
            md5.update(secret);
            md5.update(sha.digest());
            digest = md5.digest(); // length == 16
            if (pos + 16 > out.length) {
                System.arraycopy(digest, 0, out, pos, out.length - pos);
                pos = out.length;
            } else {
                System.arraycopy(digest, 0, out, pos, 16);
                pos += 16;
            }
        }
    }

    /**
     * Computes the value of TLS pseudo random function.
     * @param   out:    the buffer to fill up with the value of the function.
     * @param   secret: the buffer containing the secret value to generate prf.
     * @param   str_bytes:  the label bytes to be used.
     * @param   seed:   the seed to be used.
     */
    synchronized static void computePRF(byte[] out, byte[] secret,
            byte[] str_byts, byte[] seed) throws GeneralSecurityException {
        if (sha_mac == null) {
            init();
        }
        // Do concatenation of the label with the seed:
        // (metterings show that is is faster to concatenate the arrays
        // and to call HMAC.update on cancatenation, than twice call for
        // each of the part, i.e.:
        // time(HMAC.update(label+seed))
        //          < time(HMAC.update(label)) + time(HMAC.update(seed))
        // but it takes more memmory (approximaty on 4%)
        /*
        byte[] tmp_seed = new byte[seed.length + str_byts.length];
        System.arraycopy(str_byts, 0, tmp_seed, 0, str_byts.length);
        System.arraycopy(seed, 0, tmp_seed, str_byts.length, seed.length);
        seed = tmp_seed;
        */
        SecretKeySpec keyMd5;
        SecretKeySpec keySha1;
        if ((secret == null) || (secret.length == 0)) {
            secret = new byte[8];
            keyMd5 = new SecretKeySpec(secret, "HmacMD5");
            keySha1 = new SecretKeySpec(secret, "HmacSHA1");
        } else {
            int length = secret.length >> 1; // division by 2
            int offset = secret.length & 1;  // remainder
            keyMd5 = new SecretKeySpec(secret, 0, length + offset,
                    "HmacMD5");
            keySha1 = new SecretKeySpec(secret, length, length
                    + offset, "HmacSHA1");
        }

        //byte[] str_byts = label.getBytes();

        if (logger != null) {
            logger.println("secret["+secret.length+"]: ");
            logger.printAsHex(16, "", " ", secret);
            logger.println("label["+str_byts.length+"]: ");
            logger.printAsHex(16, "", " ", str_byts);
            logger.println("seed["+seed.length+"]: ");
            logger.printAsHex(16, "", " ", seed);
            logger.println("MD5 key:");
            logger.printAsHex(16, "", " ", keyMd5.getEncoded());
            logger.println("SHA1 key:");
            logger.printAsHex(16, "", " ", keySha1.getEncoded());
        }

        md5_mac.init(keyMd5);
        sha_mac.init(keySha1);

        int pos = 0;
        md5_mac.update(str_byts);
        byte[] hash = md5_mac.doFinal(seed); // A(1)
        while (pos < out.length) {
            md5_mac.update(hash);
            md5_mac.update(str_byts);
            md5_mac.update(seed);
            if (pos + md5_mac_length < out.length) {
                md5_mac.doFinal(out, pos);
                pos += md5_mac_length;
            } else {
                System.arraycopy(md5_mac.doFinal(), 0, out,
                        pos, out.length - pos);
                break;
            }
            // make A(i)
            hash = md5_mac.doFinal(hash);
        }
        if (logger != null) {
            logger.println("P_MD5:");
            logger.printAsHex(md5_mac_length, "", " ", out);
        }

        pos = 0;
        sha_mac.update(str_byts);
        hash = sha_mac.doFinal(seed); // A(1)
        byte[] sha1hash;
        while (pos < out.length) {
            sha_mac.update(hash);
            sha_mac.update(str_byts);
            sha1hash = sha_mac.doFinal(seed);
            for (int i = 0; (i < sha_mac_length) & (pos < out.length); i++) {
                out[pos++] ^= sha1hash[i];
            }
            // make A(i)
            hash = sha_mac.doFinal(hash);
        }

        if (logger != null) {
            logger.println("PRF:");
            logger.printAsHex(sha_mac_length, "", " ", out);
        }
    }
}
