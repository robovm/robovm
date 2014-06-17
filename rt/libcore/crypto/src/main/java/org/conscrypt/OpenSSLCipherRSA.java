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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.conscrypt.util.EmptyArray;

public abstract class OpenSSLCipherRSA extends CipherSpi {
    /**
     * The current OpenSSL key we're operating on.
     */
    private OpenSSLKey key;

    /**
     * Current key type: private or public.
     */
    private boolean usingPrivateKey;

    /**
     * Current cipher mode: encrypting or decrypting.
     */
    private boolean encrypting;

    /**
     * Buffer for operations
     */
    private byte[] buffer;

    /**
     * Current offset in the buffer.
     */
    private int bufferOffset;

    /**
     * Flag that indicates an exception should be thrown when the input is too
     * large during doFinal.
     */
    private boolean inputTooLarge;

    /**
     * Current padding mode
     */
    private int padding = NativeCrypto.RSA_PKCS1_PADDING;

    protected OpenSSLCipherRSA(int padding) {
        this.padding = padding;
    }

    @Override
    protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
        final String modeUpper = mode.toUpperCase(Locale.ROOT);
        if ("NONE".equals(modeUpper) || "ECB".equals(modeUpper)) {
            return;
        }

        throw new NoSuchAlgorithmException("mode not supported: " + mode);
    }

    @Override
    protected void engineSetPadding(String padding) throws NoSuchPaddingException {
        final String paddingUpper = padding.toUpperCase(Locale.ROOT);
        if ("PKCS1PADDING".equals(paddingUpper)) {
            this.padding = NativeCrypto.RSA_PKCS1_PADDING;
            return;
        }
        if ("NOPADDING".equals(paddingUpper)) {
            this.padding = NativeCrypto.RSA_NO_PADDING;
            return;
        }

        throw new NoSuchPaddingException("padding not supported: " + padding);
    }

    @Override
    protected int engineGetBlockSize() {
        if (encrypting) {
            return paddedBlockSizeBytes();
        }
        return keySizeBytes();
    }

    @Override
    protected int engineGetOutputSize(int inputLen) {
        if (encrypting) {
            return keySizeBytes();
        }
        return paddedBlockSizeBytes();
    }

    private int paddedBlockSizeBytes() {
        int paddedBlockSizeBytes = keySizeBytes();
        if (padding == NativeCrypto.RSA_PKCS1_PADDING) {
            paddedBlockSizeBytes--;  // for 0 prefix
            paddedBlockSizeBytes -= 10;  // PKCS1 padding header length
        }
        return paddedBlockSizeBytes;
    }

    private int keySizeBytes() {
        if (key == null) {
            throw new IllegalStateException("cipher is not initialized");
        }
        return NativeCrypto.RSA_size(this.key.getPkeyContext());
    }

    @Override
    protected byte[] engineGetIV() {
        return null;
    }

    @Override
    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    private void engineInitInternal(int opmode, Key key) throws InvalidKeyException {
        if (opmode == Cipher.ENCRYPT_MODE || opmode == Cipher.WRAP_MODE) {
            encrypting = true;
        } else if (opmode == Cipher.DECRYPT_MODE || opmode == Cipher.UNWRAP_MODE) {
            encrypting = false;
        } else {
            throw new InvalidParameterException("Unsupported opmode " + opmode);
        }

        if (key instanceof OpenSSLRSAPrivateKey) {
            OpenSSLRSAPrivateKey rsaPrivateKey = (OpenSSLRSAPrivateKey) key;
            usingPrivateKey = true;
            this.key = rsaPrivateKey.getOpenSSLKey();
        } else if (key instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey) key;
            usingPrivateKey = true;
            this.key = OpenSSLRSAPrivateCrtKey.getInstance(rsaPrivateKey);
        } else if (key instanceof RSAPrivateKey) {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) key;
            usingPrivateKey = true;
            this.key = OpenSSLRSAPrivateKey.getInstance(rsaPrivateKey);
        } else if (key instanceof OpenSSLRSAPublicKey) {
            OpenSSLRSAPublicKey rsaPublicKey = (OpenSSLRSAPublicKey) key;
            usingPrivateKey = false;
            this.key = rsaPublicKey.getOpenSSLKey();
        } else if (key instanceof RSAPublicKey) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) key;
            usingPrivateKey = false;
            this.key = OpenSSLRSAPublicKey.getInstance(rsaPublicKey);
        } else {
            throw new InvalidKeyException("Need RSA private or public key");
        }

        buffer = new byte[NativeCrypto.RSA_size(this.key.getPkeyContext())];
        inputTooLarge = false;
    }

    @Override
    protected void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        engineInitInternal(opmode, key);
    }

    @Override
    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("unknown param type: "
                    + params.getClass().getName());
        }

        engineInitInternal(opmode, key);
    }

    @Override
    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("unknown param type: "
                    + params.getClass().getName());
        }

        engineInitInternal(opmode, key);
    }

    @Override
    protected byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        if (bufferOffset + inputLen > buffer.length) {
            inputTooLarge = true;
            return EmptyArray.BYTE;
        }

        System.arraycopy(input, inputOffset, buffer, bufferOffset, inputLen);
        bufferOffset += inputLen;
        return EmptyArray.BYTE;
    }

    @Override
    protected int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset) throws ShortBufferException {
        engineUpdate(input, inputOffset, inputLen);
        return 0;
    }

    @Override
    protected byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        if (input != null) {
            engineUpdate(input, inputOffset, inputLen);
        }

        if (inputTooLarge) {
            throw new IllegalBlockSizeException("input must be under " + buffer.length + " bytes");
        }

        final byte[] tmpBuf;
        if (bufferOffset != buffer.length) {
            if (padding == NativeCrypto.RSA_NO_PADDING) {
                tmpBuf = new byte[buffer.length];
                System.arraycopy(buffer, 0, tmpBuf, buffer.length - bufferOffset, bufferOffset);
            } else {
                tmpBuf = Arrays.copyOf(buffer, bufferOffset);
            }
        } else {
            tmpBuf = buffer;
        }

        byte[] output = new byte[buffer.length];
        int resultSize;
        if (encrypting) {
            if (usingPrivateKey) {
                resultSize = NativeCrypto.RSA_private_encrypt(tmpBuf.length, tmpBuf, output,
                                                              key.getPkeyContext(), padding);
            } else {
                resultSize = NativeCrypto.RSA_public_encrypt(tmpBuf.length, tmpBuf, output,
                                                             key.getPkeyContext(), padding);
            }
        } else {
            try {
                if (usingPrivateKey) {
                    resultSize = NativeCrypto.RSA_private_decrypt(tmpBuf.length, tmpBuf, output,
                                                                  key.getPkeyContext(), padding);
                } else {
                    resultSize = NativeCrypto.RSA_public_decrypt(tmpBuf.length, tmpBuf, output,
                                                                 key.getPkeyContext(), padding);
                }
            } catch (SignatureException e) {
                IllegalBlockSizeException newE = new IllegalBlockSizeException();
                newE.initCause(e);
                throw newE;
            }
        }
        if (!encrypting && resultSize != output.length) {
            output = Arrays.copyOf(output, resultSize);
        }

        bufferOffset = 0;
        return output;
    }

    @Override
    protected int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset) throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] b = engineDoFinal(input, inputOffset, inputLen);

        final int lastOffset = outputOffset + b.length;
        if (lastOffset > output.length) {
            throw new ShortBufferException("output buffer is too small " + output.length + " < "
                    + lastOffset);
        }

        System.arraycopy(b, 0, output, outputOffset, b.length);
        return b.length;
    }

    @Override
    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        try {
            byte[] encoded = key.getEncoded();
            return engineDoFinal(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            IllegalBlockSizeException newE = new IllegalBlockSizeException();
            newE.initCause(e);
            throw newE;
        }
    }

    @Override
    protected Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException, NoSuchAlgorithmException {
        try {
            byte[] encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
            if (wrappedKeyType == Cipher.PUBLIC_KEY) {
                KeyFactory keyFactory = KeyFactory.getInstance(wrappedKeyAlgorithm);
                return keyFactory.generatePublic(new X509EncodedKeySpec(encoded));
            } else if (wrappedKeyType == Cipher.PRIVATE_KEY) {
                KeyFactory keyFactory = KeyFactory.getInstance(wrappedKeyAlgorithm);
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
            } else if (wrappedKeyType == Cipher.SECRET_KEY) {
                return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
            } else {
                throw new UnsupportedOperationException("wrappedKeyType == " + wrappedKeyType);
            }
        } catch (IllegalBlockSizeException e) {
            throw new InvalidKeyException(e);
        } catch (BadPaddingException e) {
            throw new InvalidKeyException(e);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeyException(e);
        }
    }

    public static class PKCS1 extends OpenSSLCipherRSA {
        public PKCS1() {
            super(NativeCrypto.RSA_PKCS1_PADDING);
        }
    }

    public static class Raw extends OpenSSLCipherRSA {
        public Raw() {
            super(NativeCrypto.RSA_NO_PADDING);
        }
    }
}
