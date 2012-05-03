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

package javax.crypto;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Set;
import org.apache.harmony.crypto.internal.NullCipherSpi;
import org.apache.harmony.security.fortress.Engine;

/**
 * This class provides access to implementations of cryptographic ciphers for
 * encryption and decryption. Cipher classes can not be instantiated directly,
 * one has to call the Cipher's {@code getInstance} method with the name of a
 * requested transformation, optionally with a provider. A transformation
 * specifies an operation (or a set of operations) as a string in the form:
 * <ul>
 * <li><i>"algorithm/mode/padding"</i></li> or
 * <li><i>"algorithm"</i></li>
 * </ul>
 * <i>algorithm</i> is the name of a cryptographic algorithm, <i>mode</i> is the
 * name of a feedback mode and <i>padding</i> is the name of a padding scheme.
 * If <i>mode</i> and/or <i>padding</i> values are omitted, provider specific
 * default values will be used.
 * <p>
 * A valid transformation would be:
 * <ul>
 * {@code Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");}
 * </ul>
 * When a block cipher is requested in in stream cipher mode, the number of bits
 * to be processed at a time can be optionally specified by appending it to the
 * mode name. e.g. <i>"AES/CFB8/NoPadding"</i>. If no number is specified, a
 * provider specific default value is used.
 */
public class Cipher {

    /**
     * Constant for decryption operation mode.
     */
    public static final int DECRYPT_MODE = 2;

    /**
     * Constant for encryption operation mode.
     */
    public static final int ENCRYPT_MODE = 1;

    /**
     * Constant indicating that the key to be unwrapped is a private key.
     */
    public static final int PRIVATE_KEY = 2;

    /**
     * Constant indicating that the key to be unwrapped is a public key.
     */
    public static final int PUBLIC_KEY = 1;

    /**
     * Constant indicating that the key to be unwrapped is a secret key.
     */
    public static final int SECRET_KEY = 3;

    /**
     * Constant for key unwrapping operation mode.
     */
    public static final int UNWRAP_MODE = 4;

    /**
     * Constant for key wrapping operation mode.
     */
    public static final int WRAP_MODE = 3;

    private int mode;

    /**
     * The service name.
     */
    private static final String SERVICE = "Cipher";

    /**
     * Used to access common engine functionality.
     */
    private static final Engine ENGINE = new Engine(SERVICE);

    /**
     * The provider.
     */
    private Provider provider;

    /**
     * The SPI implementation.
     */
    private CipherSpi spiImpl;

    /**
     * The transformation.
     */
    private String transformation;

    private static SecureRandom secureRandom;

    /**
     * Creates a new Cipher instance.
     *
     * @param cipherSpi
     *            the implementation delegate of the cipher.
     * @param provider
     *            the provider of the implementation of this cipher.
     * @param transformation
     *            the name of the transformation that this cipher performs.
     * @throws NullPointerException
     *             if either cipherSpi is {@code null} or provider is {@code
     *             null} and {@code cipherSpi} is a {@code NullCipherSpi}.
     */
    protected Cipher(CipherSpi cipherSpi, Provider provider,
            String transformation) {
        if (cipherSpi == null) {
            throw new NullPointerException();
        }
        if (!(cipherSpi instanceof NullCipherSpi) && provider == null) {
            throw new NullPointerException();
        }
        this.provider = provider;
        this.transformation = transformation;
        this.spiImpl = cipherSpi;
    }

    /**
     * Creates a new Cipher for the specified transformation. The installed
     * providers are searched in order for an implementation of the specified
     * transformation. The first found provider providing the transformation is
     * used to create the cipher. If no provider is found an exception is
     * thrown.
     *
     * @param transformation
     *            the name of the transformation to create a cipher for.
     * @return a cipher for the requested transformation.
     * @throws NoSuchAlgorithmException
     *             if no installed provider can provide the
     *             <i>transformation</i>, or it is {@code null}, empty or in an
     *             invalid format.
     * @throws NoSuchPaddingException
     *             if no installed provider can provide the padding scheme in
     *             the <i>transformation</i>.
     */
    public static final Cipher getInstance(String transformation)
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        return getCipher(transformation, null);
    }

    /**
     * Creates a new cipher for the specified transformation provided by the
     * specified provider.
     *
     * @param transformation
     *            the name of the transformation to create a cipher for.
     * @param provider
     *            the name of the provider to ask for the transformation.
     * @return a cipher for the requested transformation.
     * @throws NoSuchAlgorithmException
     *             if the specified provider can not provide the
     *             <i>transformation</i>, or it is {@code null}, empty or in an
     *             invalid format.
     * @throws NoSuchProviderException
     *             if no provider with the specified name can be found.
     * @throws NoSuchPaddingException
     *             if the requested padding scheme in the <i>transformation</i>
     *             is not available.
     * @throws IllegalArgumentException
     *             if the specified provider is {@code null}.
     */
    public static final Cipher getInstance(String transformation,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException {

        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }

        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("Provider not available: " + provider);
        }
        return getInstance(transformation, p);
    }

    /**
     * Creates a new cipher for the specified transformation.
     *
     * @param transformation
     *            the name of the transformation to create a cipher for.
     * @param provider
     *            the provider to ask for the transformation.
     * @return a cipher for the requested transformation.
     * @throws NoSuchAlgorithmException
     *             if the specified provider can not provide the
     *             <i>transformation</i>, or it is {@code null}, empty or in an
     *             invalid format.
     * @throws NoSuchPaddingException
     *             if the requested padding scheme in the <i>transformation</i>
     *             is not available.
     * @throws IllegalArgumentException
     *             if the provider is {@code null}.
     */
    public static final Cipher getInstance(String transformation,
            Provider provider) throws NoSuchAlgorithmException,
            NoSuchPaddingException {
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        Cipher c = getCipher(transformation, provider);
        return c;
    }

    private static NoSuchAlgorithmException invalidTransformation(String transformation)
            throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("Invalid transformation: " + transformation);
    }

    /**
     * Find appropriate Cipher according the specification rules
     *
     * @param transformation
     * @param provider
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    private static synchronized Cipher getCipher(String transformation, Provider provider)
            throws NoSuchAlgorithmException, NoSuchPaddingException {

        if (transformation == null || transformation.isEmpty()) {
            throw invalidTransformation(transformation);
        }

        String[] transf = checkTransformation(transformation);

        boolean needSetPadding = false;
        boolean needSetMode = false;
        Object engineSpi = null;
        Provider engineProvider = provider;
        if (transf[1] == null && transf[2] == null) { // "algorithm"
            if (provider == null) {
                Engine.SpiAndProvider sap = ENGINE.getInstance(transf[0], null);
                engineSpi = sap.spi;
                engineProvider = sap.provider;
            } else {
                engineSpi = ENGINE.getInstance(transf[0], provider, null);
            }
        } else {
            String[] searchOrder = {
                transf[0] + "/" + transf[1] + "/" + transf[2], // "algorithm/mode/padding"
                transf[0] + "/" + transf[1], // "algorithm/mode"
                transf[0] + "//" + transf[2], // "algorithm//padding"
                transf[0] // "algorithm"
            };
            int i;
            for (i = 0; i < searchOrder.length; i++) {
                try {
                    if (provider == null) {
                        Engine.SpiAndProvider sap = ENGINE.getInstance(searchOrder[i], null);
                        engineSpi = sap.spi;
                        engineProvider = sap.provider;
                    } else {
                        engineSpi = ENGINE.getInstance(searchOrder[i], provider, null);
                    }
                    break;
                } catch (NoSuchAlgorithmException e) {
                    if (i == searchOrder.length-1) {
                        throw new NoSuchAlgorithmException(transformation, e);
                    }
                }
            }
            switch (i) {
                case 1: // "algorithm/mode"
                    needSetPadding = true;
                    break;
                case 2: // "algorithm//padding"
                    needSetMode = true;
                    break;
                case 3: // "algorithm"
                    needSetPadding = true;
                    needSetMode = true;
            }
        }
        if (engineSpi == null || engineProvider == null) {
            throw new NoSuchAlgorithmException(transformation);
        }
        if (!(engineSpi instanceof CipherSpi)) {
            throw new NoSuchAlgorithmException(engineSpi.getClass().getName());
        }
        CipherSpi cspi = (CipherSpi) engineSpi;
        Cipher c = new Cipher(cspi, engineProvider, transformation);
        if (needSetMode) {
            c.spiImpl.engineSetMode(transf[1]);
        }
        if (needSetPadding) {
            c.spiImpl.engineSetPadding(transf[2]);
        }
        return c;
    }

    private static String[] checkTransformation(String transformation) throws NoSuchAlgorithmException {
        // ignore an extra prefix / characters such as in
        // "/DES/CBC/PKCS5Paddin" http://b/3387688
        if (transformation.startsWith("/")) {
            transformation = transformation.substring(1);
        }
        // 'transformation' should be of the form "algorithm/mode/padding".
        String[] pieces = transformation.split("/");
        if (pieces.length > 3) {
            throw invalidTransformation(transformation);
        }
        // Empty or missing pieces are represented by null.
        String[] result = new String[3];
        for (int i = 0; i < pieces.length; ++i) {
            String piece = pieces[i].trim();
            if (!piece.isEmpty()) {
                result[i] = piece;
            }
        }
        // You MUST specify an algorithm.
        if (result[0] == null) {
            throw invalidTransformation(transformation);
        }
        if (!(result[1] == null && result[2] == null) && (result[1] == null || result[2] == null)) {
            throw invalidTransformation(transformation);
        }
        return result;
    }

    /**
     * Returns the provider of this cipher instance.
     *
     * @return the provider of this cipher instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Returns the name of the algorithm of this cipher instance.
     * <p>
     * This is the name of the <i>transformation</i> argument used in the
     * {@code getInstance} call creating this object.
     *
     * @return the name of the algorithm of this cipher instance.
     */
    public final String getAlgorithm() {
        return transformation;
    }

    /**
     * Returns this ciphers block size (in bytes).
     *
     * @return this ciphers block size.
     */
    public final int getBlockSize() {
        return spiImpl.engineGetBlockSize();
    }

    /**
     * Returns the length in bytes an output buffer needs to be when this cipher
     * is updated with {@code inputLen} bytes.
     *
     * @param inputLen
     *            the number of bytes of the input.
     * @return the output buffer length for the input length.
     * @throws IllegalStateException
     *             if this cipher instance is in an invalid state.
     */
    public final int getOutputSize(int inputLen) {
        if (mode == 0) {
            throw new IllegalStateException("Cipher has not yet been initialized");
        }
        return spiImpl.engineGetOutputSize(inputLen);
    }

    /**
     * Returns the <i>initialization vector</i> for this cipher instance.
     *
     * @return the <i>initialization vector</i> for this cipher instance.
     */
    public final byte[] getIV() {
        return spiImpl.engineGetIV();
    }

    /**
     * Returns the parameters that where used to create this cipher instance.
     * <p>
     * These may be a the same parameters that were used to create this cipher
     * instance, or may be a combination of default and random parameters,
     * depending on the underlying cipher implementation.
     *
     * @return the parameters that where used to create this cipher instance, or
     *         {@code null} if this cipher instance does not have any
     *         parameters.
     */
    public final AlgorithmParameters getParameters() {
        return spiImpl.engineGetParameters();
    }

    /**
     * Returns the exemption mechanism associated with this cipher.
     *
     * @return currently {@code null}
     */
    public final ExemptionMechanism getExemptionMechanism() {
        //FIXME implement getExemptionMechanism

        //        try {
        //            return ExemptionMechanism.getInstance(transformation, provider);
        //        } catch (NoSuchAlgorithmException e) {
        return null;
        //        }

    }

    /**
     * Initializes this cipher instance with the specified key.
     * <p>
     * The cipher is initialized for the specified operational mode (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters or random values
     * that the specified key can not provide, the underlying implementation of
     * this cipher is supposed to generate the required parameters (using its
     * provider or random values).
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, meaning that it
     * is equivalent to creating a new instance and calling its {@code init}
     * method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     */
    public final void init(int opmode, Key key) throws InvalidKeyException {
        if (secureRandom == null) {
            // In theory it might be thread-unsafe but in the given case it's OK
            // since it does not matter which SecureRandom instance is passed
            // to the init()
            secureRandom = new SecureRandom();
        }
        init(opmode, key, secureRandom);
    }

    /**
     * Initializes this cipher instance with the specified key and a source of
     * randomness.
     * <p>
     * The cipher is initialized for the specified operational mode (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters or random values
     * that the specified key can not provide, the underlying implementation of
     * this cipher is supposed to generate the required parameters (using its
     * provider or random values). Random values are generated using {@code
     * random};
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     * @throws InvalidParameterException
     *             if the specified opmode is invalid.
     */
    public final void init(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        checkMode(opmode);
        //        FIXME InvalidKeyException
        //        if keysize exceeds the maximum allowable keysize
        //        (jurisdiction policy files)
        spiImpl.engineInit(opmode, key, random);
        mode = opmode;
    }

    private void checkMode(int mode) {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE
            && mode != UNWRAP_MODE && mode != WRAP_MODE) {
            throw new InvalidParameterException("Invalid mode: " + mode);
        }
    }

    /**
     * Initializes this cipher instance with the specified key and algorithm
     * parameters.
     * <p>
     * The cipher is initialized for the specified operational mode (one of:
     * encryption, decryption, key wrapping or key unwrapping).
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values).
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     * @throws InvalidAlgorithmParameterException
     *             it the specified parameters are inappropriate for this
     *             cipher.
     */
    public final void init(int opmode, Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        init(opmode, key, params, secureRandom);
    }

    /**
     * Initializes this cipher instance with the specified key, algorithm
     * parameters and a source of randomness.
     * <p>
     * The cipher is initialized for the specified operational mode (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values). Random values are generated using {@code random};
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, meaning that it
     * is equivalent to creating a new instance and calling it {@code init}
     * method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     * @throws InvalidAlgorithmParameterException
     *             it the specified parameters are inappropriate for this
     *             cipher.
     * @throws InvalidParameterException
     *             if the specified {@code opmode} is invalid.
     */
    public final void init(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        checkMode(opmode);
        //        FIXME InvalidKeyException
        //        if keysize exceeds the maximum allowable keysize
        //        (jurisdiction policy files)
        //        FIXME InvalidAlgorithmParameterException
        //        cryptographic strength exceed the legal limits
        //        (jurisdiction policy files)
        spiImpl.engineInit(opmode, key, params, random);
        mode = opmode;
    }

    /**
     * Initializes this cipher instance with the specified key and algorithm
     * parameters.
     * <p>
     * The cipher is initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values).
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, meaning that it
     * is equivalent to creating a new instance and calling it {@code init}
     * method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     * @throws InvalidAlgorithmParameterException
     *             it the specified parameters are inappropriate for this
     *             cipher.
     */
    public final void init(int opmode, Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        init(opmode, key, params, secureRandom);
    }

    /**
     * Initializes this cipher instance with the specified key, algorithm
     * parameters and a source of randomness.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values). Random values are generated using {@code random}.
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key can not be used to initialize this
     *             cipher instance.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters are inappropriate for this
     *             cipher.
     * @throws InvalidParameterException
     *             if the specified {@code opmode} is invalid.
     */
    public final void init(int opmode, Key key, AlgorithmParameters params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        checkMode(opmode);
        //        FIXME InvalidKeyException
        //        if keysize exceeds the maximum allowable keysize
        //        (jurisdiction policy files)
        //        FIXME InvalidAlgorithmParameterException
        //        cryptographic strength exceed the legal limits
        //        (jurisdiction policy files)
        spiImpl.engineInit(opmode, key, params, random);
        mode = opmode;
    }

    /**
     * Initializes this cipher instance with the public key from the specified
     * certificate.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * It the type of the certificate is X.509 and the certificate has a <i>key
     * usage</i> extension field marked as critical, the specified {@code
     * opmode} has the be enabled for this key, otherwise an {@code
     * InvalidKeyException} is thrown.
     * <p>
     * If this cipher instance needs any algorithm parameters that the key in
     * the certificate can not provide, the underlying implementation of this
     * cipher is supposed to generate the required parameters (using its
     * provider or random values).
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param certificate
     *            the certificate.
     * @throws InvalidKeyException
     *             if the public key in the certificate can not be used to
     *             initialize this cipher instance.
     */
    public final void init(int opmode, Certificate certificate)
            throws InvalidKeyException {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        init(opmode, certificate, secureRandom);
    }

    /**
     * Initializes this cipher instance with the public key from the specified
     * certificate and a source of randomness.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * It the type of the certificate is X.509 and the certificate has a <i>key
     * usage</i> extension field marked as critical, the specified {@code
     * opmode} has the be enabled for this key, otherwise an {@code
     * InvalidKeyException} is thrown.
     * <p>
     * If this cipher instance needs any algorithm parameters that the key in
     * the certificate can not provide, the underlying implementation of this
     * cipher is supposed to generate the required parameters (using its
     * provider or random values). Random values are generated using {@code
     * random}.
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param certificate
     *            the certificate.
     * @param random
     *            the source of randomness to be used.
     * @throws InvalidKeyException
     *             if the public key in the certificate can not be used to
     *             initialize this cipher instance.
     */
    public final void init(int opmode, Certificate certificate,
            SecureRandom random) throws InvalidKeyException {
        checkMode(opmode);
        if (certificate instanceof X509Certificate) {
            Set<String> ce = ((X509Certificate) certificate).getCriticalExtensionOIDs();
            boolean critical = false;
            if (ce != null && !ce.isEmpty()) {
                for (String oid : ce) {
                    if (oid.equals("2.5.29.15")) { // KeyUsage OID = 2.5.29.15
                        critical = true;
                        break;
                    }
                }
                if (critical) {
                    boolean[] keyUsage = ((X509Certificate) certificate).getKeyUsage();
                    // As specified in RFC 3280:
                    //   Internet X.509 Public Key Infrastructure
                    //   Certificate and Certificate Revocation List (CRL) Profile.
                    // Section 4.2.1.3  Key Usage
                    // http://www.ietf.org/rfc/rfc3280.txt
                    //
                    // KeyUsage ::= BIT STRING {digitalSignature (0),
                    //                          nonRepudiation   (1),
                    //                          keyEncipherment  (2),
                    //                          dataEncipherment (3),
                    //                          keyAgreement     (4),
                    //                          keyCertSign      (5),
                    //                          cRLSign          (6),
                    //                          encipherOnly     (7),
                    //                          decipherOnly     (8) }
                    if (keyUsage != null) {
                        if (opmode == ENCRYPT_MODE && !keyUsage[3]) {
                            throw new InvalidKeyException("The public key in the certificate "
                                                          + "cannot be used for ENCRYPT_MODE");
                        } else if (opmode == WRAP_MODE && !keyUsage[2]) {
                            throw new InvalidKeyException("The public key in the certificate "
                                                          + "cannot be used for WRAP_MODE");
                        }
                    }
                }
            }
        }
        //        FIXME InvalidKeyException
        //        if keysize exceeds the maximum allowable keysize
        //        (jurisdiction policy files)
        spiImpl.engineInit(opmode, certificate.getPublicKey(), random);
        mode = opmode;
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are returned.
     *
     * @param input
     *            the input bytes to transform.
     * @return the transformed bytes in a new buffer, or {@code null} if the
     *         input has zero length.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input is {@code null}.
     */
    public final byte[] update(byte[] input) {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        }
        if (input.length == 0) {
            return null;
        }
        return spiImpl.engineUpdate(input, 0, input.length);
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are returned.
     *
     * @param input
     *            the input bytes to transform.
     * @param inputOffset
     *            the offset in the input to start.
     * @param inputLen
     *            the length of the input to transform.
     * @return the transformed bytes in a new buffer, or {@code null} if the
     *         input has zero length.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input is {@code null}, or if {@code inputOffset} and
     *             {@code inputLen} do not specify a valid chunk in the input
     *             buffer.
     */
    public final byte[] update(byte[] input, int inputOffset, int inputLen) {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        }
        if (inputOffset < 0 || inputLen < 0
                || inputLen > input.length
                || inputOffset > input.length - inputLen) {
            throw new IllegalArgumentException("Incorrect inputOffset/inputLen parameters");
        }
        if (input.length == 0) {
            return null;
        }
        return spiImpl.engineUpdate(input, inputOffset, inputLen);
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are stored in the {@code output} buffer.
     * <p>
     * If the size of the {@code output} buffer is too small to hold the result,
     * a {@code ShortBufferException} is thrown. Use
     * {@link Cipher#getOutputSize getOutputSize} to check for the size of the
     * output buffer.
     *
     * @param input
     *            the input bytes to transform.
     * @param inputOffset
     *            the offset in the input to start.
     * @param inputLen
     *            the length of the input to transform.
     * @param output
     *            the output buffer.
     * @return the number of bytes placed in output.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input is {@code null}, the output is {@code null}, or
     *             if {@code inputOffset} and {@code inputLen} do not specify a
     *             valid chunk in the input buffer.
     */
    public final int update(byte[] input, int inputOffset, int inputLen,
            byte[] output) throws ShortBufferException {
        return update(input, inputOffset, inputLen, output, 0);
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are stored in the {@code output} buffer.
     * <p>
     * If the size of the {@code output} buffer is too small to hold the result,
     * a {@code ShortBufferException} is thrown. Use
     * {@link Cipher#getOutputSize getOutputSize} to check for the size of the
     * output buffer.
     *
     * @param input
     *            the input bytes to transform.
     * @param inputOffset
     *            the offset in the input to start.
     * @param inputLen
     *            the length of the input to transform.
     * @param output
     *            the output buffer.
     * @param outputOffset
     *            the offset in the output buffer.
     * @return the number of bytes placed in output.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input is {@code null}, the output is {@code null}, or
     *             if {@code inputOffset} and {@code inputLen} do not specify a
     *             valid chunk in the input buffer.
     */
    public final int update(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        }
        if (output == null) {
            throw new IllegalArgumentException("output == null");
        }
        if (outputOffset < 0) {
            throw new IllegalArgumentException("outputOffset < 0");
        }
        if (inputOffset < 0 || inputLen < 0 || inputLen > input.length
                || inputOffset > input.length - inputLen) {
            throw new IllegalArgumentException("Incorrect inputOffset/inputLen parameters");
        }
        if (input.length == 0) {
            return 0;
        }
        return spiImpl.engineUpdate(input, inputOffset, inputLen, output,
                outputOffset);
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * {@code input.remaining()} bytes starting at {@code input.position()} are
     * transformed and stored in the {@code output} buffer.
     * <p>
     * If the {@code output.remaining()} is too small to hold the transformed
     * bytes a {@code ShortBufferException} is thrown. Use
     * {@link Cipher#getOutputSize getOutputSize} to check for the size of the
     * output buffer.
     *
     * @param input
     *            the input buffer to transform.
     * @param output
     *            the output buffer to store the result within.
     * @return the number of bytes stored in the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input buffer and the output buffer are the identical
     *             object.
     */
    public final int update(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (input == output) {
            throw new IllegalArgumentException("input == output");
        }
        return spiImpl.engineUpdate(input, output);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes any bytes that may have been buffered in previous {@code
     * update} calls.
     *
     * @return the final bytes from the transformation.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     */
    public final byte[] doFinal() throws IllegalBlockSizeException,
            BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        return spiImpl.engineDoFinal(null, 0, 0);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes any bytes that may have been buffered in previous {@code
     * update} calls.
     * <p>
     * The final transformed bytes are stored in the {@code output} buffer.
     *
     * @param output
     *            the output buffer.
     * @param outputOffset
     *            the offset in the output buffer.
     * @return the number of bytes placed in the output buffer.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     */
    public final int doFinal(byte[] output, int outputOffset)
            throws IllegalBlockSizeException, ShortBufferException,
            BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (outputOffset < 0) {
            throw new IllegalArgumentException("outputOffset < 0");
        }
        return spiImpl.engineDoFinal(null, 0, 0, output, outputOffset);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the bytes in {@code input} buffer, and any bytes that have been
     * buffered in previous {@code update} calls.
     *
     * @param input
     *            the input buffer.
     * @return the final bytes from the transformation.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     */
    public final byte[] doFinal(byte[] input) throws IllegalBlockSizeException,
            BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        return spiImpl.engineDoFinal(input, 0, input.length);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code inputLen} bytes in {@code input} buffer at {@code
     * inputOffset}, and any bytes that have been buffered in previous {@code
     * update} calls.
     *
     * @param input
     *            the input buffer.
     * @param inputOffset
     *            the offset in the input buffer.
     * @param inputLen
     *            the length of the input
     * @return the final bytes from the transformation.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if {@code inputOffset} and {@code inputLen} do not specify an
     *             valid chunk in the input buffer.
     */
    public final byte[] doFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (inputOffset < 0 || inputLen < 0 || inputOffset + inputLen > input.length) {
            throw new IllegalArgumentException("Incorrect inputOffset/inputLen parameters");
        }
        return spiImpl.engineDoFinal(input, inputOffset, inputLen);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code inputLen} bytes in {@code input} buffer at {@code
     * inputOffset}, and any bytes that have been buffered in previous {@code
     * update} calls.
     *
     * @param input
     *            the input buffer.
     * @param inputOffset
     *            the offset in the input buffer.
     * @param inputLen
     *            the length of the input.
     * @param output
     *            the output buffer for the transformed bytes.
     * @return the number of bytes placed in the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if {@code inputOffset} and {@code inputLen} do not specify an
     *             valid chunk in the input buffer.
     */
    public final int doFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        return doFinal(input, inputOffset, inputLen, output, 0);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code inputLen} bytes in {@code input} buffer at {@code
     * inputOffset}, and any bytes that have been buffered in previous {@code
     * update} calls.
     *
     * @param input
     *            the input buffer.
     * @param inputOffset
     *            the offset in the input buffer.
     * @param inputLen
     *            the length of the input.
     * @param output
     *            the output buffer for the transformed bytes.
     * @param outputOffset
     *            the offset in the output buffer.
     * @return the number of bytes placed in the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if {@code inputOffset} and {@code inputLen} do not specify an
     *             valid chunk in the input buffer.
     */
    public final int doFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (inputOffset < 0 || inputLen < 0 || inputOffset + inputLen > input.length) {
            throw new IllegalArgumentException("Incorrect inputOffset/inputLen parameters");
        }
        return spiImpl.engineDoFinal(input, inputOffset, inputLen, output,
                outputOffset);
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code input.remaining()} bytes in {@code input} buffer at
     * {@code input.position()}, and any bytes that have been buffered in
     * previous {@code update} calls. The transformed bytes are placed into
     * {@code output} buffer.
     *
     * @param input
     *            the input buffer.
     * @param output
     *            the output buffer.
     * @return the number of bytes placed into the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalArgumentException
     *             if the input buffer and the output buffer are the same
     *             object.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     */
    public final int doFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            throw new IllegalStateException();
        }
        if (input == output) {
            throw new IllegalArgumentException("input == output");
        }
        return spiImpl.engineDoFinal(input, output);
    }

    /**
     * Wraps a key using this cipher instance.
     *
     * @param key
     *            the key to wrap.
     * @return the wrapped key.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws InvalidKeyException
     *             if this cipher instance can not wrap this key.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for wrapping.
     */
    public final byte[] wrap(Key key) throws IllegalBlockSizeException,
            InvalidKeyException {
        if (mode != WRAP_MODE) {
            throw new IllegalStateException();
        }
        return spiImpl.engineWrap(key);
    }

    /**
     * Unwraps a key using this cipher instance.
     *
     * @param wrappedKey
     *            the wrapped key to unwrap.
     * @param wrappedKeyAlgorithm
     *            the algorithm for the wrapped key.
     * @param wrappedKeyType
     *            the type of the wrapped key (one of: {@code SECRET_KEY
     *            <code>, <code>PRIVATE_KEY} or {@code PUBLIC_KEY})
     * @return the unwrapped key
     * @throws InvalidKeyException
     *             if the {@code wrappedKey} can not be unwrapped to a key of
     *             type {@code wrappedKeyType} for the {@code
     *             wrappedKeyAlgorithm}.
     * @throws NoSuchAlgorithmException
     *             if no provider can be found that can create a key of type
     *             {@code wrappedKeyType} for the {@code wrappedKeyAlgorithm}.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for unwrapping.
     */
    public final Key unwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        if (mode != UNWRAP_MODE) {
            throw new IllegalStateException();
        }
        return spiImpl.engineUnwrap(wrappedKey, wrappedKeyAlgorithm,
                wrappedKeyType);
    }

    /**
     * Returns the maximum key length for the specified transformation.
     *
     * @param transformation
     *            the transformation name.
     * @return the maximum key length, currently {@code Integer.MAX_VALUE}.
     * @throws NoSuchAlgorithmException
     *             if no provider for the specified {@code transformation} can
     *             be found.
     * @throws NullPointerException
     *             if {@code transformation} is {@code null}.
     */
    public static final int getMaxAllowedKeyLength(String transformation)
            throws NoSuchAlgorithmException {
        if (transformation == null) {
            throw new NullPointerException();
        }
        checkTransformation(transformation);
        //FIXME jurisdiction policy files
        return Integer.MAX_VALUE;
    }

    /**
     * Returns the maximum cipher parameter value for the specified
     * transformation. If there is no maximum limit, {@code null} is returned.
     *
     * @param transformation
     *            the transformation name.
     * @return a parameter spec holding the maximum value or {@code null}.
     *         Currently {@code null}.
     * @throws NoSuchAlgorithmException
     *             if no provider for the specified {@code transformation} can
     *             be found.
     * @throws NullPointerException
     *             if {@code transformation} is {@code null}.
     */
    public static final AlgorithmParameterSpec getMaxAllowedParameterSpec(
            String transformation) throws NoSuchAlgorithmException {
        if (transformation == null) {
            throw new NullPointerException();
        }
        checkTransformation(transformation);
        //FIXME jurisdiction policy files
        return null;
    }
}
