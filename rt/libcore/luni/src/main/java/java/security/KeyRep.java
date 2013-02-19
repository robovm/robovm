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

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * {@code KeyRep} is a standardized representation for serialized {@link Key}
 * objects.
 */
public class KeyRep implements Serializable {

    private static final long serialVersionUID = -4757683898830641853L;
    // Key type
    private final Type type;
    // Key algorithm name
    private final String algorithm;
    // Key encoding format
    private final String format;
    // Key encoding
    private byte[] encoded;

    /**
     * Constructs a new instance of {@code KeyRep} with the specified arguments.
     * The arguments should be obtained from the {@code Key} object that has to
     * be serialized.
     *
     * @param type
     *            the type of the key.
     * @param algorithm
     *            the algorithm (obtained by {@link Key#getAlgorithm()}).
     * @param format
     *            the format of the key (obtained by {@link Key#getFormat()}).
     * @param encoded
     *            the encoded {@code byte[]} (obtained by
     *            {@link Key#getEncoded()}).
     * @throws NullPointerException
     *             if {@code type, algorithm, format or encoded} is {@code null}
     *             .
     */
    public KeyRep(Type type, String algorithm, String format, byte[] encoded) {
        this.type = type;
        this.algorithm = algorithm;
        this.format = format;
        this.encoded = encoded;
        if(this.type == null) {
            throw new NullPointerException("type == null");
        }
        if(this.algorithm == null) {
            throw new NullPointerException("algorithm == null");
        }
        if(this.format == null) {
            throw new NullPointerException("format == null");
        }
        if(this.encoded == null) {
            throw new NullPointerException("encoded == null");
        }
    }

    /**
     * Resolves and returns the {@code Key} object. Three {@link Type}|format
     * combinations are supported:
     * <ul>
     * <li> {@code Type.PRIVATE} | "PKCS#8" : returns a {@link PrivateKey}
     * instance, generated from a key factory (suitable for the algorithm) that
     * is initialized with a {@link PKCS8EncodedKeySpec} using the encoded key
     * bytes.
     * <li> {@code Type.SECRET} | "RAW" : returns a {@link SecretKeySpec}
     * instance, created with the encoded key bytes and the algorithm.
     * <li> {@code Type.PUBLIC} | "X.509": returns a {@link PublicKey} instance,
     * generated from a key factory (suitable for the algorithm) that is
     * initialized with a {@link X509EncodedKeySpec} using the encoded key
     * bytes.
     * </ul>
     *
     * @return the resolved {@code Key} object.
     * @throws ObjectStreamException
     *             if the {@code Type}|format combination is not recognized, or
     *             the resolution of any key parameter fails.
     */
    protected Object readResolve() throws ObjectStreamException {
        switch (type) {
        case SECRET:
            if ("RAW".equals(format)) {
                try {
                    return new SecretKeySpec(encoded, algorithm);
                } catch (IllegalArgumentException e) {
                    throw new NotSerializableException("Could not create SecretKeySpec: " + e);
                }
            }
            throw new NotSerializableException("unrecognized type/format combination: " + type + "/" + format);
        case PUBLIC:
            if ("X.509".equals(format)) {
                try {
                    KeyFactory kf = KeyFactory.getInstance(algorithm);
                    return kf.generatePublic(new X509EncodedKeySpec(encoded));
                } catch (NoSuchAlgorithmException e) {
                    throw new NotSerializableException("Could not resolve key: " + e);
                }
                catch (InvalidKeySpecException e) {
                    throw new NotSerializableException("Could not resolve key: " + e);
                }
            }
            throw new NotSerializableException("unrecognized type/format combination: " + type + "/" + format);
        case PRIVATE:
            if ("PKCS#8".equals(format)) {
                try {
                    KeyFactory kf = KeyFactory.getInstance(algorithm);
                    return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                } catch (NoSuchAlgorithmException e) {
                    throw new NotSerializableException("Could not resolve key: " + e);
                }
                catch (InvalidKeySpecException e) {
                    throw new NotSerializableException("Could not resolve key: " + e);
                }
            }
            throw new NotSerializableException("unrecognized type/format combination: " + type + "/" + format);
        }
        throw new NotSerializableException("unrecognized key type: " + type);
    }

    // Makes defensive copy of key encoding
    private void readObject(ObjectInputStream is)
        throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        byte[] new_encoded = new byte[encoded.length];
        System.arraycopy(encoded, 0, new_encoded, 0, new_encoded.length);
        encoded = new_encoded;
    }

    /**
     * {@code Type} enumerates the supported key types.
     */
    public static enum Type {
        /**
         * Type for secret keys.
         */
        SECRET,
        /**
         * Type for public keys.
         */
        PUBLIC,
        /**
         * Type for private keys.
         */
        PRIVATE
    }
}
