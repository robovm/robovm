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

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.conscrypt.util.EmptyArray;

public abstract class OpenSSLCipher extends CipherSpi {

    /**
     * Modes that a block cipher may support.
     */
    protected static enum Mode {
        CBC,
        CFB, CFB1, CFB8, CFB128,
        CTR,
        CTS,
        ECB,
        OFB, OFB64, OFB128,
        PCBC,
    }

    /**
     * Paddings that a block cipher may support.
     */
    protected static enum Padding {
        NOPADDING,
        PKCS5PADDING,
        ISO10126PADDING,
    }

    /**
     * Native pointer for the OpenSSL EVP_CIPHER context.
     */
    private OpenSSLCipherContext cipherCtx = new OpenSSLCipherContext(
            NativeCrypto.EVP_CIPHER_CTX_new());

    /**
     * The current cipher mode.
     */
    private Mode mode = Mode.ECB;

    /**
     * The current cipher padding.
     */
    private Padding padding = Padding.PKCS5PADDING;

    /**
     * The Initial Vector (IV) used for the current cipher.
     */
    private byte[] iv;

    /**
     * Current cipher mode: encrypting or decrypting.
     */
    private boolean encrypting;

    /**
     * The block size of the current cipher.
     */
    private int blockSize;

    /**
     * The block size of the current mode.
     */
    private int modeBlockSize;

    /**
     * Whether the cipher has processed any data yet. OpenSSL doesn't like
     * calling "doFinal()" in decryption mode without processing any updates.
     */
    private boolean calledUpdate;

    protected OpenSSLCipher() {
    }

    protected OpenSSLCipher(Mode mode, Padding padding) {
        this.mode = mode;
        this.padding = padding;
        blockSize = getCipherBlockSize();
    }

    /**
     * Returns the standard name for the particular algorithm.
     */
    protected abstract String getBaseCipherName();

    /**
     * Returns the OpenSSL cipher name for the particular {@code keySize} and
     * cipher {@code mode}.
     */
    protected abstract String getCipherName(int keySize, Mode mode);

    /**
     * Checks whether the cipher supports this particular {@code keySize} (in
     * bytes) and throws {@code InvalidKeyException} if it doesn't.
     */
    protected abstract void checkSupportedKeySize(int keySize) throws InvalidKeyException;

    /**
     * Checks whether the cipher supports this particular cipher {@code mode}
     * and throws {@code NoSuchAlgorithmException} if it doesn't.
     */
    protected abstract void checkSupportedMode(Mode mode) throws NoSuchAlgorithmException;

    /**
     * Checks whether the cipher supports this particular cipher {@code padding}
     * and throws {@code NoSuchPaddingException} if it doesn't.
     */
    protected abstract void checkSupportedPadding(Padding padding) throws NoSuchPaddingException;

    protected abstract int getCipherBlockSize();

    protected boolean supportsVariableSizeKey() {
        return false;
    }

    @Override
    protected void engineSetMode(String modeStr) throws NoSuchAlgorithmException {
        final Mode mode;
        try {
            mode = Mode.valueOf(modeStr.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            NoSuchAlgorithmException newE = new NoSuchAlgorithmException("No such mode: "
                    + modeStr);
            newE.initCause(e);
            throw newE;
        }
        checkSupportedMode(mode);
        this.mode = mode;
    }

    @Override
    protected void engineSetPadding(String paddingStr) throws NoSuchPaddingException {
        final String paddingStrUpper = paddingStr.toUpperCase(Locale.US);
        final Padding padding;
        try {
            padding = Padding.valueOf(paddingStrUpper);
        } catch (IllegalArgumentException e) {
            NoSuchPaddingException newE = new NoSuchPaddingException("No such padding: "
                    + paddingStr);
            newE.initCause(e);
            throw newE;
        }
        checkSupportedPadding(padding);
        this.padding = padding;
    }

    @Override
    protected int engineGetBlockSize() {
        return blockSize;
    }

    /**
     * The size of output if {@code doFinal()} is called with this
     * {@code inputLen}. If padding is enabled and the size of the input puts it
     * right at the block size, it will add another block for the padding.
     */
    private int getOutputSize(int inputLen) {
        if (modeBlockSize == 1) {
            return inputLen;
        } else {
            final int buffered = NativeCrypto.get_EVP_CIPHER_CTX_buf_len(cipherCtx.getContext());
            if (padding == Padding.NOPADDING) {
                return buffered + inputLen;
            } else {
                final int totalLen = inputLen + buffered + modeBlockSize;
                return totalLen - (totalLen % modeBlockSize);
            }
        }
    }

    @Override
    protected int engineGetOutputSize(int inputLen) {
        return getOutputSize(inputLen);
    }

    @Override
    protected byte[] engineGetIV() {
        return iv;
    }

    @Override
    protected AlgorithmParameters engineGetParameters() {
        if (iv != null && iv.length > 0) {
            try {
                AlgorithmParameters params = AlgorithmParameters.getInstance(getBaseCipherName());
                params.init(iv);
                return params;
            } catch (NoSuchAlgorithmException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    private void engineInitInternal(int opmode, Key key, byte[] iv, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (opmode == Cipher.ENCRYPT_MODE || opmode == Cipher.WRAP_MODE) {
            encrypting = true;
        } else if (opmode == Cipher.DECRYPT_MODE || opmode == Cipher.UNWRAP_MODE) {
            encrypting = false;
        } else {
            throw new InvalidParameterException("Unsupported opmode " + opmode);
        }

        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Only SecretKey is supported");
        }

        final byte[] encodedKey = key.getEncoded();
        if (encodedKey == null) {
            throw new InvalidKeyException("key.getEncoded() == null");
        }

        checkSupportedKeySize(encodedKey.length);

        final long cipherType = NativeCrypto.EVP_get_cipherbyname(getCipherName(encodedKey.length,
                mode));
        if (cipherType == 0) {
            throw new InvalidAlgorithmParameterException("Cannot find name for key length = "
                    + (encodedKey.length * 8) + " and mode = " + mode);
        }

        final int ivLength = NativeCrypto.EVP_CIPHER_iv_length(cipherType);
        if (iv == null && ivLength != 0) {
            iv = new byte[ivLength];
            if (encrypting) {
                if (random == null) {
                    random = new SecureRandom();
                }
                random.nextBytes(iv);
            }
        } else if (iv != null && iv.length != ivLength) {
            throw new InvalidAlgorithmParameterException("expected IV length of " + ivLength);
        }

        this.iv = iv;

        if (supportsVariableSizeKey()) {
            NativeCrypto.EVP_CipherInit_ex(cipherCtx.getContext(), cipherType, null, null,
                    encrypting);
            NativeCrypto.EVP_CIPHER_CTX_set_key_length(cipherCtx.getContext(), encodedKey.length);
            NativeCrypto.EVP_CipherInit_ex(cipherCtx.getContext(), 0, encodedKey, iv, encrypting);
        } else {
            NativeCrypto.EVP_CipherInit_ex(cipherCtx.getContext(), cipherType, encodedKey, iv,
                    encrypting);
        }

        // OpenSSL only supports PKCS5 Padding.
        NativeCrypto.EVP_CIPHER_CTX_set_padding(cipherCtx.getContext(),
                padding == Padding.PKCS5PADDING);
        modeBlockSize = NativeCrypto.EVP_CIPHER_CTX_block_size(cipherCtx.getContext());
        calledUpdate = false;
    }

    @Override
    protected void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        try {
            engineInitInternal(opmode, key, null, random);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        final byte[] iv;
        if (params instanceof IvParameterSpec) {
            IvParameterSpec ivParams = (IvParameterSpec) params;
            iv = ivParams.getIV();
        } else {
            iv = null;
        }

        engineInitInternal(opmode, key, iv, random);
    }

    @Override
    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        final AlgorithmParameterSpec spec;
        try {
            spec = params.getParameterSpec(IvParameterSpec.class);
        } catch (InvalidParameterSpecException e) {
            throw new InvalidAlgorithmParameterException(e);
        }

        engineInit(opmode, key, spec, random);
    }

    private final int updateInternal(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset, int maximumLen) throws ShortBufferException {
        final int intialOutputOffset = outputOffset;

        final int bytesLeft = output.length - outputOffset;
        if (bytesLeft < maximumLen) {
            throw new ShortBufferException("output buffer too small during update: " + bytesLeft
                    + " < " + maximumLen);
        }

        outputOffset += NativeCrypto.EVP_CipherUpdate(cipherCtx.getContext(), output, outputOffset,
                input, inputOffset, inputLen);

        calledUpdate = true;

        return outputOffset - intialOutputOffset;
    }

    @Override
    protected byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        final int maximumLen = getOutputSize(inputLen);

        /* See how large our output buffer would need to be. */
        final byte[] output;
        if (maximumLen > 0) {
            output = new byte[maximumLen];
        } else {
            output = EmptyArray.BYTE;
        }

        final int bytesWritten;
        try {
            bytesWritten = updateInternal(input, inputOffset, inputLen, output, 0, maximumLen);
        } catch (ShortBufferException e) {
            /* This shouldn't happen. */
            throw new RuntimeException("calculated buffer size was wrong: " + maximumLen);
        }

        if (output.length == bytesWritten) {
            return output;
        } else if (bytesWritten == 0) {
            return EmptyArray.BYTE;
        } else {
            return Arrays.copyOfRange(output, 0, bytesWritten);
        }
    }

    @Override
    protected int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset) throws ShortBufferException {
        final int maximumLen = getOutputSize(inputLen);
        return updateInternal(input, inputOffset, inputLen, output, outputOffset, maximumLen);
    }

    /**
     * Reset this Cipher instance state to process a new chunk of data.
     */
    private void reset() {
        NativeCrypto.EVP_CipherInit_ex(cipherCtx.getContext(), 0, null, null, encrypting);
        calledUpdate = false;
    }

    private int doFinalInternal(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset, int maximumLen) throws IllegalBlockSizeException,
            BadPaddingException, ShortBufferException {
        /* Remember this so we can tell how many characters were written. */
        final int initialOutputOffset = outputOffset;

        if (inputLen > 0) {
            final int updateBytesWritten = updateInternal(input, inputOffset, inputLen, output,
                    outputOffset, maximumLen);
            outputOffset += updateBytesWritten;
            maximumLen -= updateBytesWritten;
        }

        /*
         * If we're decrypting and haven't had any input, we should return null.
         * Otherwise OpenSSL will complain if we call final.
         */
        if (!encrypting && !calledUpdate) {
            return 0;
        }

        /* Allow OpenSSL to pad if necessary and clean up state. */
        final int bytesLeft = output.length - outputOffset;
        final int writtenBytes;
        if (bytesLeft >= maximumLen) {
            writtenBytes = NativeCrypto.EVP_CipherFinal_ex(cipherCtx.getContext(), output,
                    outputOffset);
        } else {
            final byte[] lastBlock = new byte[maximumLen];
            writtenBytes = NativeCrypto.EVP_CipherFinal_ex(cipherCtx.getContext(), lastBlock, 0);
            if (writtenBytes > bytesLeft) {
                throw new ShortBufferException("buffer is too short: " + writtenBytes + " > "
                        + bytesLeft);
            } else if (writtenBytes > 0) {
                System.arraycopy(lastBlock, 0, output, outputOffset, writtenBytes);
            }
        }
        outputOffset += writtenBytes;

        reset();

        return outputOffset - initialOutputOffset;
    }

    @Override
    protected byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        /*
         * Other implementations return null if we've never called update()
         * while decrypting.
         */
        if (!encrypting && !calledUpdate && inputLen == 0) {
            reset();
            return null;
        }

        final int maximumLen = getOutputSize(inputLen);
        /* Assume that we'll output exactly on a byte boundary. */
        final byte[] output = new byte[maximumLen];
        final int bytesWritten;
        try {
            bytesWritten = doFinalInternal(input, inputOffset, inputLen, output, 0, maximumLen);
        } catch (ShortBufferException e) {
            /* This should not happen since we sized our own buffer. */
            throw new RuntimeException("our calculated buffer was too small", e);
        }

        if (bytesWritten == output.length) {
            return output;
        } else if (bytesWritten == 0) {
            return EmptyArray.BYTE;
        } else {
            return Arrays.copyOfRange(output, 0, bytesWritten);
        }
    }

    @Override
    protected int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output,
            int outputOffset) throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        if (output == null) {
            throw new NullPointerException("output == null");
        }

        final int maximumLen = getOutputSize(inputLen);
        return doFinalInternal(input, inputOffset, inputLen, output, outputOffset, maximumLen);
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
    protected Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType)
            throws InvalidKeyException, NoSuchAlgorithmException {
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

    public static class AES extends OpenSSLCipher {
        private static final int AES_BLOCK_SIZE = 16;

        protected AES(Mode mode, Padding padding) {
            super(mode, padding);
        }

        public static class CBC extends AES {
            public CBC(Padding padding) {
                super(Mode.CBC, padding);
            }

            public static class NoPadding extends CBC {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends CBC {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class CFB extends AES {
            public CFB(Padding padding) {
                super(Mode.CFB, padding);
            }

            public static class NoPadding extends CFB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends CFB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class CTR extends AES {
            public CTR(Padding padding) {
                super(Mode.CTR, padding);
            }

            public static class NoPadding extends CTR {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends CTR {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class ECB extends AES {
            public ECB(Padding padding) {
                super(Mode.ECB, padding);
            }

            public static class NoPadding extends ECB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends ECB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class OFB extends AES {
            public OFB(Padding padding) {
                super(Mode.OFB, padding);
            }

            public static class NoPadding extends OFB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends OFB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        @Override
        protected void checkSupportedKeySize(int keyLength) throws InvalidKeyException {
            switch (keyLength) {
                case 16: // AES 128
                case 24: // AES 192
                case 32: // AES 256
                    return;
                default:
                    throw new InvalidKeyException("Unsupported key size: " + keyLength + " bytes");
            }
        }

        @Override
        protected void checkSupportedMode(Mode mode) throws NoSuchAlgorithmException {
            switch (mode) {
                case CBC:
                case CFB:
                case CFB1:
                case CFB8:
                case CFB128:
                case CTR:
                case ECB:
                case OFB:
                    return;
                default:
                    throw new NoSuchAlgorithmException("Unsupported mode " + mode.toString());
            }
        }

        @Override
        protected void checkSupportedPadding(Padding padding) throws NoSuchPaddingException {
            switch (padding) {
                case NOPADDING:
                case PKCS5PADDING:
                    return;
                default:
                    throw new NoSuchPaddingException("Unsupported padding " + padding.toString());
            }
        }

        @Override
        protected String getBaseCipherName() {
            return "AES";
        }

        @Override
        protected String getCipherName(int keyLength, Mode mode) {
            return "aes-" + (keyLength * 8) + "-" + mode.toString().toLowerCase(Locale.US);
        }

        @Override
        protected int getCipherBlockSize() {
            return AES_BLOCK_SIZE;
        }
    }

    public static class DESEDE extends OpenSSLCipher {
        private static int DES_BLOCK_SIZE = 8;

        public DESEDE(Mode mode, Padding padding) {
            super(mode, padding);
        }

        public static class CBC extends DESEDE {
            public CBC(Padding padding) {
                super(Mode.CBC, padding);
            }

            public static class NoPadding extends CBC {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends CBC {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class CFB extends DESEDE {
            public CFB(Padding padding) {
                super(Mode.CFB, padding);
            }

            public static class NoPadding extends CFB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends CFB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class ECB extends DESEDE {
            public ECB(Padding padding) {
                super(Mode.ECB, padding);
            }

            public static class NoPadding extends ECB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends ECB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        public static class OFB extends DESEDE {
            public OFB(Padding padding) {
                super(Mode.OFB, padding);
            }

            public static class NoPadding extends OFB {
                public NoPadding() {
                    super(Padding.NOPADDING);
                }
            }

            public static class PKCS5Padding extends OFB {
                public PKCS5Padding() {
                    super(Padding.PKCS5PADDING);
                }
            }
        }

        @Override
        protected String getBaseCipherName() {
            return "DESede";
        }

        @Override
        protected String getCipherName(int keySize, Mode mode) {
            final String baseCipherName;
            if (keySize == 16) {
                baseCipherName = "des-ede";
            } else {
                baseCipherName = "des-ede3";
            }

            if (mode == Mode.ECB) {
                return baseCipherName;
            } else {
                return baseCipherName + "-" + mode.toString().toLowerCase(Locale.US);
            }
        }

        @Override
        protected void checkSupportedKeySize(int keySize) throws InvalidKeyException {
            if (keySize != 16 && keySize != 24) {
                throw new InvalidKeyException("key size must be 128 or 192 bits");
            }
        }

        @Override
        protected void checkSupportedMode(Mode mode) throws NoSuchAlgorithmException {
            switch (mode) {
                case CBC:
                case CFB:
                case CFB1:
                case CFB8:
                case ECB:
                case OFB:
                    return;
                default:
                    throw new NoSuchAlgorithmException("Unsupported mode " + mode.toString());
            }
        }

        @Override
        protected void checkSupportedPadding(Padding padding) throws NoSuchPaddingException {
            switch (padding) {
                case NOPADDING:
                case PKCS5PADDING:
                    return;
                default:
                    throw new NoSuchPaddingException("Unsupported padding " + padding.toString());
            }
        }

        @Override
        protected int getCipherBlockSize() {
            return DES_BLOCK_SIZE;
        }
    }

    public static class ARC4 extends OpenSSLCipher {
        public ARC4() {
        }

        @Override
        protected String getBaseCipherName() {
            return "ARCFOUR";
        }

        @Override
        protected String getCipherName(int keySize, Mode mode) {
            return "rc4";
        }

        @Override
        protected void checkSupportedKeySize(int keySize) throws InvalidKeyException {
        }

        @Override
        protected void checkSupportedMode(Mode mode) throws NoSuchAlgorithmException {
            throw new NoSuchAlgorithmException("ARC4 does not support modes");
        }

        @Override
        protected void checkSupportedPadding(Padding padding) throws NoSuchPaddingException {
            throw new NoSuchPaddingException("ARC4 does not support padding");
        }

        @Override
        protected int getCipherBlockSize() {
            return 0;
        }

        @Override
        protected boolean supportsVariableSizeKey() {
            return true;
        }
    }
}
