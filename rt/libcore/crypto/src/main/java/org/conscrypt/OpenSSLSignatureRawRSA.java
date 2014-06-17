/*
 * Copyright (C) 2012 The Android Open Source Project
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

package org.conscrypt;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Implements the JDK Signature interface needed for RAW RSA signature
 * generation and verification using OpenSSL.
 */
public class OpenSSLSignatureRawRSA extends Signature {
    /**
     * The current OpenSSL key we're operating on.
     */
    private OpenSSLKey key;

    /**
     * Buffer to hold value to be signed or verified.
     */
    private byte[] inputBuffer;

    /**
     * Current offset in input buffer.
     */
    private int inputOffset;

    /**
     * Provides a flag to specify when the input is too long.
     */
    private boolean inputIsTooLong;

    /**
     * Creates a new OpenSSLSignature instance for the given algorithm name.
     */
    public OpenSSLSignatureRawRSA() throws NoSuchAlgorithmException {
        super("NONEwithRSA");
    }

    @Override
    protected void engineUpdate(byte input) {
        final int oldOffset = inputOffset++;

        if (inputOffset > inputBuffer.length) {
            inputIsTooLong = true;
            return;
        }

        inputBuffer[oldOffset] = input;
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        final int oldOffset = inputOffset;
        inputOffset += len;

        if (inputOffset > inputBuffer.length) {
            inputIsTooLong = true;
            return;
        }

        System.arraycopy(input, offset, inputBuffer, oldOffset, len);
    }

    @Override
    protected Object engineGetParameter(String param) throws InvalidParameterException {
        return null;
    }

    @Override
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof OpenSSLRSAPrivateKey) {
            OpenSSLRSAPrivateKey rsaPrivateKey = (OpenSSLRSAPrivateKey) privateKey;
            key = rsaPrivateKey.getOpenSSLKey();
        } else if (privateKey instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey) privateKey;
            key = OpenSSLRSAPrivateCrtKey.getInstance(rsaPrivateKey);
        } else if (privateKey instanceof RSAPrivateKey) {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
            key = OpenSSLRSAPrivateKey.getInstance(rsaPrivateKey);
        } else {
            throw new InvalidKeyException("Need RSA private key");
        }

        // Allocate buffer according to RSA modulus size.
        int maxSize = NativeCrypto.RSA_size(key.getPkeyContext());
        inputBuffer = new byte[maxSize];
        inputOffset = 0;
    }

    @Override
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof OpenSSLRSAPublicKey) {
            OpenSSLRSAPublicKey rsaPublicKey = (OpenSSLRSAPublicKey) publicKey;
            key = rsaPublicKey.getOpenSSLKey();
        } else if (publicKey instanceof RSAPublicKey) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            key = OpenSSLRSAPublicKey.getInstance(rsaPublicKey);
        } else {
            throw new InvalidKeyException("Need RSA public key");
        }

        // Allocate buffer according to RSA modulus size.
        int maxSize = NativeCrypto.RSA_size(key.getPkeyContext());
        inputBuffer = new byte[maxSize];
        inputOffset = 0;
    }

    @Override
    protected void engineSetParameter(String param, Object value) throws InvalidParameterException {
    }

    @Override
    protected byte[] engineSign() throws SignatureException {
        if (key == null) {
            // This can't actually happen, but you never know...
            throw new SignatureException("Need RSA private key");
        }

        if (inputIsTooLong) {
            throw new SignatureException("input length " + inputOffset + " != "
                    + inputBuffer.length + " (modulus size)");
        }

        byte[] outputBuffer = new byte[inputBuffer.length];
        try {
            NativeCrypto.RSA_private_encrypt(inputOffset, inputBuffer, outputBuffer,
                    key.getPkeyContext(), NativeCrypto.RSA_PKCS1_PADDING);
            return outputBuffer;
        } catch (Exception ex) {
            throw new SignatureException(ex);
        } finally {
            inputOffset = 0;
        }
    }

    @Override
    protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
        if (key == null) {
            // This can't actually happen, but you never know...
            throw new SignatureException("Need RSA public key");
        }

        if (inputIsTooLong) {
            return false;
        }

        byte[] outputBuffer = new byte[inputBuffer.length];
        try {
            final int resultSize;
            try {
                resultSize = NativeCrypto.RSA_public_decrypt(sigBytes.length, sigBytes,
                        outputBuffer, key.getPkeyContext(), NativeCrypto.RSA_PKCS1_PADDING);
            } catch (SignatureException e) {
                throw e;
            } catch (Exception e) {
                return false;
            }
            /* Make this constant time by comparing every byte. */
            boolean matches = (resultSize == inputOffset);
            for (int i = 0; i < resultSize; i++) {
                if (inputBuffer[i] != outputBuffer[i]) {
                    matches = false;
                }
            }
            return matches;
        } catch (Exception ex) {
            throw new SignatureException(ex);
        } finally {
            inputOffset = 0;
        }
    }
}
