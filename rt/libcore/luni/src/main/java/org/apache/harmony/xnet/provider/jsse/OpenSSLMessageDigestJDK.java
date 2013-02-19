/*
 * Copyright (C) 2008 The Android Open Source Project
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
import java.security.NoSuchAlgorithmException;

/**
 * Implements the JDK MessageDigest interface using OpenSSL's EVP API.
 */
public class OpenSSLMessageDigestJDK extends MessageDigest implements Cloneable {

    /**
     * Holds a pointer to the native message digest context.
     */
    private int ctx;

    /**
     * Holds the EVP_MD for the hashing algorithm, e.g. EVP_get_digestbyname("sha1");
     */
    private final int evp_md;

    /**
     * Holds the output size of the message digest.
     */
    private final int size;

    /**
     * Holds a dummy buffer for writing single bytes to the digest.
     */
    private final byte[] singleByte = new byte[1];

    /**
     * Creates a new OpenSSLMessageDigest instance for the given algorithm
     * name.
     */
    private OpenSSLMessageDigestJDK(String algorithm, int evp_md, int size)
            throws NoSuchAlgorithmException {
        super(algorithm);
        this.evp_md = evp_md;
        this.size = size;
    }

    @Override
    protected void engineReset() {
        free();
    }

    @Override
    protected int engineGetDigestLength() {
        return size;
    }

    @Override
    protected void engineUpdate(byte input) {
        singleByte[0] = input;
        engineUpdate(singleByte, 0, 1);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        NativeCrypto.EVP_DigestUpdate(getCtx(), input, offset, len);
    }

    @Override
    protected byte[] engineDigest() {
        byte[] result = new byte[size];
        NativeCrypto.EVP_DigestFinal(getCtx(), result, 0);
        ctx = 0; // EVP_DigestFinal frees the context as a side effect
        return result;
    }

    public Object clone() throws CloneNotSupportedException {
        OpenSSLMessageDigestJDK d = (OpenSSLMessageDigestJDK) super.clone();
        d.ctx = NativeCrypto.EVP_MD_CTX_copy(getCtx());
        return d;
    }

    private int getCtx() {
        if (ctx == 0) {
            ctx = NativeCrypto.EVP_DigestInit(evp_md);
        }
        return ctx;
    }

    private void free() {
        if (ctx != 0) {
            NativeCrypto.EVP_MD_CTX_destroy(ctx);
            ctx = 0;
        }
    }

    @Override protected void finalize() throws Throwable {
        try {
            free();
        } finally {
            super.finalize();
        }
    }

    public static class MD5 extends OpenSSLMessageDigestJDK {
        private static final int EVP_MD = NativeCrypto.EVP_get_digestbyname("md5");
        private static final int SIZE = NativeCrypto.EVP_MD_size(EVP_MD);
        public MD5() throws NoSuchAlgorithmException {
            super("MD5",EVP_MD, SIZE);
        }
    }

    public static class SHA1 extends OpenSSLMessageDigestJDK {
        private static final int EVP_MD = NativeCrypto.EVP_get_digestbyname("sha1");
        private static final int SIZE = NativeCrypto.EVP_MD_size(EVP_MD);
        public SHA1() throws NoSuchAlgorithmException {
            super("SHA-1", EVP_MD, SIZE);
        }
    }

    public static class SHA256 extends OpenSSLMessageDigestJDK {
        private static final int EVP_MD = NativeCrypto.EVP_get_digestbyname("sha256");
        private static final int SIZE = NativeCrypto.EVP_MD_size(EVP_MD);
        public SHA256() throws NoSuchAlgorithmException {
            super("SHA-256", EVP_MD, SIZE);
        }
    }

    public static class SHA384 extends OpenSSLMessageDigestJDK {
        private static final int EVP_MD = NativeCrypto.EVP_get_digestbyname("sha384");
        private static final int SIZE = NativeCrypto.EVP_MD_size(EVP_MD);
        public SHA384() throws NoSuchAlgorithmException {
            super("SHA-384", EVP_MD, SIZE);
        }
    }

    public static class SHA512 extends OpenSSLMessageDigestJDK {
        private static final int EVP_MD = NativeCrypto.EVP_get_digestbyname("sha512");
        private static final int SIZE = NativeCrypto.EVP_MD_size(EVP_MD);
        public SHA512() throws NoSuchAlgorithmException {
            super("SHA-512", EVP_MD, SIZE);
        }
    }
}
