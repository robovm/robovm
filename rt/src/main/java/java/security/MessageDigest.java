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

import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * {@code MessageDigest} is an engine class which is capable of generating one
 * way hash values for arbitrary input, utilizing the algorithm it was
 * initialized with.
 *
 * @see MessageDigestSpi
 */
public abstract class MessageDigest extends MessageDigestSpi {

    // The service name
    private static final String SERVICE = "MessageDigest"; //$NON-NLS-1$

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // The provider
    private Provider provider;

    // The algorithm.
    private String algorithm;

    /**
     * Constructs a new instance of {@code MessageDigest} with the name of
     * the algorithm to use.
     * 
     * @param algorithm
     *            the name of algorithm to use
     */
    protected MessageDigest(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Returns a new instance of {@code MessageDigest} that utilizes the
     * specified algorithm.
     * 
     * @param algorithm
     *            the name of the algorithm to use
     * @return a new instance of {@code MessageDigest} that utilizes the
     *         specified algorithm
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}
     */
    public static MessageDigest getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("security.01")); //$NON-NLS-1$
        }
        MessageDigest result;
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            if (engine.spi instanceof MessageDigest) {
                result = (MessageDigest) engine.spi;
                result.algorithm = algorithm;
                result.provider = engine.provider;
                return result;
            }
            return new MessageDigestImpl((MessageDigestSpi) engine.spi,
                    engine.provider, algorithm);
        }
    }

    /**
     * Returns a new instance of {@code MessageDigest} that utilizes the
     * specified algorithm from the specified provider.
     * 
     * @param algorithm
     *            the name of the algorithm to use
     * @param provider
     *            the name of the provider
     * @return a new instance of {@code MessageDigest} that utilizes the
     *         specified algorithm from the specified provider
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available
     * @throws NoSuchProviderException
     *             if the specified provider is not available
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}
     */
    public static MessageDigest getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(Messages
                    .getString("security.02")); //$NON-NLS-1$
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException(Messages.getString(
                    "security.03", provider)); //$NON-NLS-1$
        }
        return getInstance(algorithm, p);
    }

    /**
     * Returns a new instance of {@code MessageDigest} that utilizes the
     * specified algorithm from the specified provider.
     * 
     * @param algorithm
     *            the name of the algorithm to use
     * @param provider
     *            the provider
     * @return a new instance of {@code MessageDigest} that utilizes the
     *         specified algorithm from the specified provider
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}
     */
    public static MessageDigest getInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException(Messages
                    .getString("security.04")); //$NON-NLS-1$
        }
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("security.01")); //$NON-NLS-1$
        }
        MessageDigest result;
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            if (engine.spi instanceof MessageDigest) {
                result = (MessageDigest) engine.spi;
                result.algorithm = algorithm;
                result.provider = provider;
                return result;
            }
            result = new MessageDigestImpl((MessageDigestSpi) engine.spi,
                    provider, algorithm);
            return result;
        }
    }

    /**
     * Puts this {@code MessageDigest} back in an initial state, such that it is
     * ready to compute a one way hash value.
     */
    public void reset() {
        engineReset();
    }

    /**
     * Updates this {@code MessageDigest} using the given {@code byte}.
     * 
     * @param arg0
     *            the {@code byte} to update this {@code MessageDigest} with
     * @see #reset()
     */
    public void update(byte arg0) {
        engineUpdate(arg0);
    }

    /**
     * Updates this {@code MessageDigest} using the given {@code byte[]}.
     * 
     * @param input
     *            the {@code byte} array
     * @param offset
     *            the index of the first byte in {@code input} to update from
     * @param len
     *            the number of bytes in {@code input} to update from
     * @throws IllegalArgumentException
     *             if {@code offset} or {@code len} are not valid in respect to
     *             {@code input}
     */
    public void update(byte[] input, int offset, int len) {
        if (input == null ||
        // offset < 0 || len < 0 ||
                // checks for negative values are commented out intentionally
                // see HARMONY-1120 for details
                (long) offset + (long) len > input.length) {
            throw new IllegalArgumentException(Messages
                    .getString("security.05")); //$NON-NLS-1$
        }
        engineUpdate(input, offset, len);
    }

    /**
     * Updates this {@code MessageDigest} using the given {@code byte[]}.
     * 
     * @param input
     *            the {@code byte} array
     * @throws NullPointerException
     *             if {@code input} is {@code null}
     */
    public void update(byte[] input) {
        if (input == null) {
            throw new NullPointerException(Messages.getString("security.06")); //$NON-NLS-1$
        }
        engineUpdate(input, 0, input.length);
    }

    /**
     * Computes and returns the final hash value for this {@link MessageDigest}.
     * After the digest is computed the receiver is reset.
     * 
     * @return the computed one way hash value
     * @see #reset
     */
    public byte[] digest() {
        return engineDigest();
    }

    /**
     * Computes and stores the final hash value for this {@link MessageDigest}.
     * After the digest is computed the receiver is reset.
     * 
     * @param buf
     *            the buffer to store the result
     * @param offset
     *            the index of the first byte in {@code buf} to store
     * @param len
     *            the number of bytes allocated for the digest
     * @return the number of bytes written to {@code buf}
     * @throws DigestException
     *             if an error occures
     * @throws IllegalArgumentException
     *             if {@code offset} or {@code len} are not valid in respect to
     *             {@code buf}
     * @see #reset()
     */
    public int digest(byte[] buf, int offset, int len) throws DigestException {
        if (buf == null ||
        // offset < 0 || len < 0 ||
                // checks for negative values are commented out intentionally
                // see HARMONY-1148 for details
                (long) offset + (long) len > buf.length) {
            throw new IllegalArgumentException(Messages
                    .getString("security.05")); //$NON-NLS-1$
        }
        return engineDigest(buf, offset, len);
    }

    /**
     * Performs the final update and then computes and returns the final hash
     * value for this {@link MessageDigest}. After the digest is computed the
     * receiver is reset.
     * 
     * @param input
     *            the {@code byte} array
     * @return the computed one way hash value
     * @see #reset()
     */
    public byte[] digest(byte[] input) {
        update(input);
        return digest();
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code MessageDigest} including the name of its algorithm.
     * 
     * @return a printable representation for this {@code MessageDigest}
     */
    @Override
    public String toString() {
        return "MESSAGE DIGEST " + algorithm; //$NON-NLS-1$
    }

    /**
     * Indicates whether to digest are equal by performing a simply
     * byte-per-byte compare of the two digests.
     * 
     * @param digesta
     *            the first digest to be compared
     * @param digestb
     *            the second digest to be compared
     * @return {@code true} if the two hashes are equal, {@code false} otherwise
     */
    public static boolean isEqual(byte[] digesta, byte[] digestb) {
        if (digesta.length != digestb.length) {
            return false;
        }
        // No early return is allowed to avoid timing attack.
        // We must not return false until all elements are compared 
        // to keep the computing time constant
        boolean result = true;
        for (int i = 0; i < digesta.length; i++) {
            if (digesta[i] != digestb[i]) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Returns the name of the algorithm of this {@code MessageDigest}.
     * 
     * @return the name of the algorithm of this {@code MessageDigest}
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider associated with this {@code MessageDigest}.
     * 
     * @return the provider associated with this {@code MessageDigest}
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Returns the engine digest length in bytes. If the implementation does not
     * implement this function or is not an instance of {@code Cloneable},
     * {@code 0} is returned.
     * 
     * @return the digest length in bytes, or {@code 0}
     */
    public final int getDigestLength() {
        int l = engineGetDigestLength();
        if (l != 0) {
            return l;
        }
        if (!(this instanceof Cloneable)) {
            return 0;
        }
        try {
            MessageDigest md = (MessageDigest) clone();
            return md.digest().length;
        } catch (CloneNotSupportedException e) {
            return 0;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }

    /**
     * Updates this {@code MessageDigest} using the given {@code input}.
     * 
     * @param input
     *            the {@code ByteBuffer}
     */
    public final void update(ByteBuffer input) {
        engineUpdate(input);
    }

    /**
     * 
     * The internal MessageDigest implementation
     * 
     */
    private static class MessageDigestImpl extends MessageDigest {

        // MessageDigestSpi implementation
        private MessageDigestSpi spiImpl;

        // MessageDigestImpl ctor
        private MessageDigestImpl(MessageDigestSpi messageDigestSpi,
                Provider provider, String algorithm) {
            super(algorithm);
            super.provider = provider;
            spiImpl = messageDigestSpi;
        }

        // engineReset() implementation
        @Override
        protected void engineReset() {
            spiImpl.engineReset();
        }

        // engineDigest() implementation
        @Override
        protected byte[] engineDigest() {
            return spiImpl.engineDigest();
        }

        // engineGetDigestLength() implementation
        @Override
        protected int engineGetDigestLength() {
            return spiImpl.engineGetDigestLength();
        }

        // engineUpdate() implementation
        @Override
        protected void engineUpdate(byte arg0) {
            spiImpl.engineUpdate(arg0);
        }

        // engineUpdate() implementation
        @Override
        protected void engineUpdate(byte[] arg0, int arg1, int arg2) {
            spiImpl.engineUpdate(arg0, arg1, arg2);
        }

        // Returns a clone if the spiImpl is cloneable
        @Override
        public Object clone() throws CloneNotSupportedException {
            if (spiImpl instanceof Cloneable) {
                MessageDigestSpi spi = (MessageDigestSpi) spiImpl.clone();
                return new MessageDigestImpl(spi, getProvider(), getAlgorithm());
            }
            
            throw new CloneNotSupportedException();
        }
    }
}
