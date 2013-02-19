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

package java.security;

import java.nio.ByteBuffer;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.Set;
import org.apache.harmony.security.fortress.Engine;


/**
 * {@code Signature} is an engine class which is capable of creating and
 * verifying digital signatures, using different algorithms that have been
 * registered with the {@link Security} class.
 *
 * @see SignatureSpi
 */
public abstract class Signature extends SignatureSpi {

    // The service name.
    private static final String SERVICE = "Signature";

    // Used to access common engine functionality
    private static Engine ENGINE = new Engine(SERVICE);

    // The provider
    private Provider provider;

    // The algorithm.
    private String algorithm;

    /**
     * Constant that indicates that this {@code Signature} instance has not yet
     * been initialized.
     */
    protected static final int UNINITIALIZED = 0;

    /**
     * Constant that indicates that this {@code Signature} instance has been
     * initialized for signing.
     */
    protected static final int SIGN = 2;

    /**
     * Constant that indicates that this {@code Signature} instance has been
     * initialized for verification.
     */
    protected static final int VERIFY = 3;

    /**
     * Represents the current state of this {@code Signature}. The three
     * possible states are {@link #UNINITIALIZED}, {@link #SIGN} or
     * {@link #VERIFY}.
     */
    protected int state = UNINITIALIZED;

    /**
     * Constructs a new instance of {@code Signature} with the name of
     * the algorithm to use.
     *
     * @param algorithm
     *            the name of algorithm to use.
     */
    protected Signature(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Returns a new instance of {@code Signature} that utilizes the specified
     * algorithm.
     *
     * @param algorithm
     *            the name of the algorithm to use.
     * @return a new instance of {@code Signature} that utilizes the specified
     *         algorithm.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}.
     */
    public static Signature getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Engine.SpiAndProvider sap = ENGINE.getInstance(algorithm, null);
        Object spi = sap.spi;
        Provider provider = sap.provider;
        if (spi instanceof Signature) {
            Signature result = (Signature) spi;
            result.algorithm = algorithm;
            result.provider = provider;
            return result;
        }
        return new SignatureImpl((SignatureSpi) spi, provider, algorithm);
    }

    /**
     * Returns a new instance of {@code Signature} that utilizes the specified
     * algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the algorithm to use.
     * @param provider
     *            the name of the provider.
     * @return a new instance of {@code Signature} that utilizes the specified
     *         algorithm from the specified provider.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available.
     * @throws NoSuchProviderException
     *             if the specified provider is not available.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}.
     * @throws IllegalArgumentException if {@code provider == null || provider.isEmpty()}
     */
    public static Signature getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        if (provider == null || provider.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException(provider);
        }
        return getSignatureInstance(algorithm, p);
    }

    /**
     * Returns a new instance of {@code Signature} that utilizes the specified
     * algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the algorithm to use.
     * @param provider
     *            the security provider.
     * @return a new instance of {@code Signature} that utilizes the specified
     *         algorithm from the specified provider.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}.
     * @throws IllegalArgumentException if {@code provider == null}
     */
    public static Signature getInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        if (provider == null) {
            throw new IllegalArgumentException();
        }
        return getSignatureInstance(algorithm, provider);
    }

    private static Signature getSignatureInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Object spi = ENGINE.getInstance(algorithm, provider, null);
        if (spi instanceof Signature) {
            Signature result = (Signature) spi;
            result.algorithm = algorithm;
            result.provider = provider;
            return result;
        }
        return new SignatureImpl((SignatureSpi) spi, provider, algorithm);
    }

    /**
     * Returns the provider associated with this {@code Signature}.
     *
     * @return the provider associated with this {@code Signature}.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Returns the name of the algorithm of this {@code Signature}.
     *
     * @return the name of the algorithm of this {@code Signature}.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Initializes this {@code Signature} instance for signature verification,
     * using the public key of the identity whose signature is going to be
     * verified.
     *
     * @param publicKey
     *            the public key.
     * @throws InvalidKeyException
     *             if {@code publicKey} is not valid.
     */
    public final void initVerify(PublicKey publicKey)
            throws InvalidKeyException {
        engineInitVerify(publicKey);
        state = VERIFY;
    }

    /**
     * Initializes this {@code Signature} instance for signature verification,
     * using the certificate of the identity whose signature is going to be
     * verified.
     * <p>
     * If the given certificate is an instance of {@link X509Certificate} and
     * has a key usage parameter that indicates, that this certificate is not to
     * be used for signing, an {@code InvalidKeyException} is thrown.
     *
     * @param certificate
     *            the certificate used to verify a signature.
     * @throws InvalidKeyException
     *             if the publicKey in the certificate is not valid or not to be
     *             used for signing.
     */
    public final void initVerify(Certificate certificate)
            throws InvalidKeyException {
        if (certificate instanceof X509Certificate) {
            Set ce = ((X509Certificate) certificate).getCriticalExtensionOIDs();
            boolean critical = false;
            if (ce != null && !ce.isEmpty()) {
                for (Iterator i = ce.iterator(); i.hasNext();) {
                    if ("2.5.29.15".equals(i.next())) {
                        //KeyUsage OID = 2.5.29.15
                        critical = true;
                        break;
                    }
                }
                if (critical) {
                    boolean[] keyUsage = ((X509Certificate) certificate)
                            .getKeyUsage();
                    // As specified in RFC 3280 -
                    // Internet X.509 Public Key Infrastructure
                    // Certificate and Certificate Revocation List (CRL) Profile.
                    // (http://www.ietf.org/rfc/rfc3280.txt)
                    //
                    // KeyUsage ::= BIT STRING { digitalSignature (0), <skipped> }
                    if ((keyUsage != null) && (!keyUsage[0])) { // digitalSignature
                        throw new InvalidKeyException("The public key in the certificate cannot be used for digital signature purposes");
                    }
                }
            }
        }
        engineInitVerify(certificate.getPublicKey());
        state = VERIFY;
    }

    /**
     * Initializes this {@code Signature} instance for signing, using the
     * private key of the identity whose signature is going to be generated.
     *
     * @param privateKey
     *            the private key.
     * @throws InvalidKeyException
     *             if {@code privateKey} is not valid.
     */
    public final void initSign(PrivateKey privateKey)
            throws InvalidKeyException {
        engineInitSign(privateKey);
        state = SIGN;
    }

    /**
     * Initializes this {@code Signature} instance for signing, using the
     * private key of the identity whose signature is going to be generated and
     * the specified source of randomness.
     *
     * @param privateKey
     *            the private key.
     * @param random
     *            the {@code SecureRandom} to use.
     * @throws InvalidKeyException
     *             if {@code privateKey} is not valid.
     */
    public final void initSign(PrivateKey privateKey, SecureRandom random)
            throws InvalidKeyException {
        engineInitSign(privateKey, random);
        state = SIGN;
    }

    /**
     * Generates and returns the signature of all updated data.
     * <p>
     * This {@code Signature} instance is reset to the state of its last
     * initialization for signing and thus can be used for another signature
     * from the same identity.
     *
     * @return the signature of all updated data.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final byte[] sign() throws SignatureException {
        if (state != SIGN) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        return engineSign();
    }

    /**
     * Generates and stores the signature of all updated data in the provided
     * {@code byte[]} at the specified position with the specified length.
     * <p>
     * This {@code Signature} instance is reset to the state of its last
     * initialization for signing and thus can be used for another signature
     * from the same identity.
     *
     * @param outbuf
     *            the buffer to store the signature.
     * @param offset
     *            the index of the first byte in {@code outbuf} to store.
     * @param len
     *            the number of bytes allocated for the signature.
     * @return the number of bytes stored in {@code outbuf}.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     * @throws IllegalArgumentException
     *             if {@code offset} or {@code len} are not valid in respect to
     *             {@code outbuf}.
     */
    public final int sign(byte[] outbuf, int offset, int len)
            throws SignatureException {
        if (outbuf == null || offset < 0 || len < 0 ||
                offset + len > outbuf.length) {
            throw new IllegalArgumentException();
        }
        if (state != SIGN) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        return engineSign(outbuf, offset, len);
    }

    /**
     * Indicates whether the given {@code signature} can be verified using the
     * public key or a certificate of the signer.
     * <p>
     * This {@code Signature} instance is reset to the state of its last
     * initialization for verifying and thus can be used to verify another
     * signature of the same signer.
     *
     * @param signature
     *            the signature to verify.
     * @return {@code true} if the signature was verified, {@code false}
     *         otherwise.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final boolean verify(byte[] signature) throws SignatureException {
        if (state != VERIFY) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        return engineVerify(signature);
    }

    /**
     * Indicates whether the given {@code signature} starting at index {@code
     * offset} with {@code length} bytes can be verified using the public key or
     * a certificate of the signer.
     * <p>
     * This {@code Signature} instance is reset to the state of its last
     * initialization for verifying and thus can be used to verify another
     * signature of the same signer.
     *
     * @param signature
     *            the {@code byte[]} containing the signature to verify.
     * @param offset
     *            the start index in {@code signature} of the signature.
     * @param length
     *            the number of bytes allocated for the signature.
     * @return {@code true} if the signature was verified, {@code false}
     *         otherwise.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     * @throws IllegalArgumentException
     *             if {@code offset} or {@code length} are not valid in respect
     *             to {@code signature}.
     */
    public final boolean verify(byte[] signature, int offset, int length)
            throws SignatureException {
        if (state != VERIFY) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        if (signature == null || offset < 0 || length < 0 ||
                offset + length > signature.length) {
            throw new IllegalArgumentException();
        }
        return engineVerify(signature, offset, length);
    }

    /**
     * Updates the data to be verified or to be signed, using the specified
     * {@code byte}.
     *
     * @param b
     *            the byte to update with.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final void update(byte b) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        engineUpdate(b);
    }

    /**
     * Updates the data to be verified or to be signed, using the specified
     * {@code byte[]}.
     *
     * @param data
     *            the byte array to update with.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final void update(byte[] data) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        engineUpdate(data, 0, data.length);
    }

    /**
     * Updates the data to be verified or to be signed, using the given {@code
     * byte[]}, starting form the specified index for the specified length.
     *
     * @param data
     *            the byte array to update with.
     * @param off
     *            the start index in {@code data} of the data.
     * @param len
     *            the number of bytes to use.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final void update(byte[] data, int off, int len)
            throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        if (data == null || off < 0 || len < 0 ||
                off + len > data.length) {
            throw new IllegalArgumentException();
        }
        engineUpdate(data, off, len);
    }

    /**
     * Updates the data to be verified or to be signed, using the specified
     * {@code ByteBuffer}.
     *
     * @param data
     *            the {@code ByteBuffer} to update with.
     * @throws SignatureException
     *             if this {@code Signature} instance is not initialized
     *             properly.
     */
    public final void update(ByteBuffer data) throws SignatureException {
        if (state == UNINITIALIZED) {
            throw new SignatureException("Signature object is not initialized properly");
        }
        engineUpdate(data);
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code Signature} including its algorithm and its state.
     *
     * @return a printable representation for this {@code Signature}.
     */
    @Override
    public String toString() {
        return "SIGNATURE " + algorithm + " state: " + stateToString(state);
    }

    // Convert state to string
    private String stateToString(int state) {
        switch (state) {
        case UNINITIALIZED:
            return "UNINITIALIZED";
        case SIGN:
            return "SIGN";
        case VERIFY:
            return "VERIFY";
        default:
            return "";
        }
    }

    /**
     * Sets the specified parameter to the given value.
     *
     * @param param
     *            the name of the parameter.
     * @param value
     *            the parameter value.
     * @throws InvalidParameterException
     *             if the parameter is invalid, already set or is not allowed to
     *             be changed.
     * @deprecated Use {@link #setParameter(AlgorithmParameterSpec)}
     */
    @Deprecated
    public final void setParameter(String param, Object value)
            throws InvalidParameterException {
        engineSetParameter(param, value);
    }

    /**
     * Sets the specified {@code AlgorithmParameterSpec}.
     *
     * @param params
     *            the parameter to set.
     * @throws InvalidAlgorithmParameterException
     *             if the parameter is invalid, already set or is not allowed to
     *             be changed.
     */
    public final void setParameter(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        engineSetParameter(params);
    }

    /**
     * Returns the {@code AlgorithmParameters} of this {@link Signature}
     * instance.
     *
     * @return the {@code AlgorithmParameters} of this {@link Signature}
     *         instance, maybe {@code null}.
     */
    public final AlgorithmParameters getParameters() {
        return engineGetParameters();
    }

    /**
     * Returns the value of the parameter with the specified name.
     *
     * @param param
     *            the name of the requested parameter value
     * @return the value of the parameter with the specified name, maybe {@code
     *         null}.
     * @throws InvalidParameterException
     *             if {@code param} is not a valid parameter for this {@code
     *             Signature} or an other error occures.
     * @deprecated There is no generally accepted parameter naming convention.
     */
    @Deprecated
    public final Object getParameter(String param)
            throws InvalidParameterException {
        return engineGetParameter(param);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }

    /**
     *
     * Internal Signature implementation
     *
     */
    private static class SignatureImpl extends Signature {

        private SignatureSpi spiImpl;

        // Constructor
        public SignatureImpl(SignatureSpi signatureSpi, Provider provider,
                String algorithm) {
            super(algorithm);
            super.provider = provider;
            spiImpl = signatureSpi;
        }

        // engineSign() implementation
        @Override
        protected byte[] engineSign() throws SignatureException {
            return spiImpl.engineSign();
        }

        //  engineUpdate() implementation
        @Override
        protected void engineUpdate(byte arg0) throws SignatureException {
            spiImpl.engineUpdate(arg0);
        }

        // engineVerify() implementation
        @Override
        protected boolean engineVerify(byte[] arg0) throws SignatureException {
            return spiImpl.engineVerify(arg0);
        }

        // engineUpdate() implementation
        @Override
        protected void engineUpdate(byte[] arg0, int arg1, int arg2)
                throws SignatureException {
            spiImpl.engineUpdate(arg0, arg1, arg2);
        }

        // engineInitSign() implementation
        @Override
        protected void engineInitSign(PrivateKey arg0)
                throws InvalidKeyException {
            spiImpl.engineInitSign(arg0);
        }

        // engineInitVerify() implementation
        @Override
        protected void engineInitVerify(PublicKey arg0)
                throws InvalidKeyException {
            spiImpl.engineInitVerify(arg0);
        }

        // engineGetParameter() implementation
        @Override
        protected Object engineGetParameter(String arg0)
                throws InvalidParameterException {
            return spiImpl.engineGetParameter(arg0);
        }

        // engineSetParameter() implementation
        @Override
        protected void engineSetParameter(String arg0, Object arg1)
                throws InvalidParameterException {
            spiImpl.engineSetParameter(arg0, arg1);
        }

        // Returns a clone if the spiImpl is cloneable
        @Override
        public Object clone() throws CloneNotSupportedException {
            if (spiImpl instanceof Cloneable) {
                SignatureSpi spi = (SignatureSpi) spiImpl.clone();
                return new SignatureImpl(spi, getProvider(), getAlgorithm());
            }
            throw new CloneNotSupportedException();
        }
    }
}
