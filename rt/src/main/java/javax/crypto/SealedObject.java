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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * A {@code SealedObject} is a wrapper around a {@code serializable} object
 * instance and encrypts it using a cryptographic cipher.
 * <p>
 * Since a {@code SealedObject} instance is a serializable object itself it can
 * either be stored or transmitted over an insecure channel.
 * <p>
 * The wrapped object can later be decrypted (unsealed) using the corresponding
 * key and then be deserialized to retrieve the original object.The sealed
 * object itself keeps track of the cipher and corresponding parameters.
 */
public class SealedObject implements Serializable {

    private static final long serialVersionUID = 4482838265551344752L;

    /**
     * The {@link AlgorithmParameters} in encoded format.
     */
    protected byte[] encodedParams;
    private byte[] encryptedContent;
    private String sealAlg;
    private String paramsAlg;

    private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
        encodedParams = (byte []) s.readUnshared();
        encryptedContent = (byte []) s.readUnshared();
        sealAlg = (String) s.readUnshared();
        paramsAlg = (String) s.readUnshared();
    }

    /**
     * Creates a new {@code SealedObject} instance wrapping the specified object
     * and sealing it using the specified cipher.
     * <p>
     * The cipher must be fully initialized.
     *
     * @param object
     *            the object to seal, can be {@code null}.
     * @param c
     *            the cipher to encrypt the object.
     * @throws IOException
     *             if the serialization fails.
     * @throws IllegalBlockSizeException
     *             if the specified cipher is a block cipher and the length of
     *             the serialized data is not a multiple of the ciphers block
     *             size.
     * @throws NullPointerException
     *             if the cipher is {@code null}.
     */
    public SealedObject(Serializable object, Cipher c)
                throws IOException, IllegalBlockSizeException {
        if (c == null) {
            throw new NullPointerException("c == null");
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            AlgorithmParameters ap = c.getParameters();
            this.encodedParams = (ap == null) ? null : ap.getEncoded();
            this.paramsAlg = (ap == null) ? null : ap.getAlgorithm();
            this.sealAlg = c.getAlgorithm();
            this.encryptedContent = c.doFinal(bos.toByteArray());
        } catch (BadPaddingException e) {
            // should be never thrown because the cipher
            // should be initialized for encryption
            throw new IOException(e.toString());
        }
    }

    /**
     * Creates a new {@code SealedObject} instance by copying the data from
     * the specified object.
     *
     * @param so
     *            the object to copy.
     */
    protected SealedObject(SealedObject so) {
        if (so == null) {
            throw new NullPointerException("so == null");
        }
        this.encryptedContent = so.encryptedContent;
        this.encodedParams = so.encodedParams;
        this.sealAlg = so.sealAlg;
        this.paramsAlg = so.paramsAlg;
    }

    /**
     * Returns the algorithm this object was sealed with.
     *
     * @return the algorithm this object was sealed with.
     */
    public final String getAlgorithm() {
        return sealAlg;
    }

    /**
     * Returns the wrapped object, decrypting it using the specified key.
     *
     * @param key
     *            the key to decrypt the data with.
     * @return the encapsulated object.
     * @throws IOException
     *             if deserialization fails.
     * @throws ClassNotFoundException
     *             if deserialization fails.
     * @throws NoSuchAlgorithmException
     *             if the algorithm to decrypt the data is not available.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to decrypt the data.
     */
    public final Object getObject(Key key)
                throws IOException, ClassNotFoundException,
                       NoSuchAlgorithmException, InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        try {
            Cipher cipher = Cipher.getInstance(sealAlg);
            if ((paramsAlg != null) && (paramsAlg.length() != 0)) {
                AlgorithmParameters params =
                    AlgorithmParameters.getInstance(paramsAlg);
                params.init(encodedParams);
                cipher.init(Cipher.DECRYPT_MODE, key, params);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
            byte[] serialized = cipher.doFinal(encryptedContent);
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new ByteArrayInputStream(serialized));
            return ois.readObject();
        } catch (NoSuchPaddingException e)  {
            // should not be thrown because cipher text was made
            // with existing padding
            throw new NoSuchAlgorithmException(e.toString());
        } catch (InvalidAlgorithmParameterException e) {
            // should not be thrown because cipher text was made
            // with correct algorithm parameters
            throw new NoSuchAlgorithmException(e.toString());
        } catch (IllegalBlockSizeException e) {
            // should not be thrown because the cipher text
            // was correctly made
            throw new NoSuchAlgorithmException(e.toString());
        } catch (BadPaddingException e) {
            // should not be thrown because the cipher text
            // was correctly made
            throw new NoSuchAlgorithmException(e.toString());
        } catch (IllegalStateException  e) {
            // should never be thrown because cipher is initialized
            throw new NoSuchAlgorithmException(e.toString());
        }
    }

    /**
     * Returns the wrapped object, decrypting it using the specified
     * cipher.
     *
     * @param c
     *            the cipher to decrypt the data.
     * @return the encapsulated object.
     * @throws IOException
     *             if deserialization fails.
     * @throws ClassNotFoundException
     *             if deserialization fails.
     * @throws IllegalBlockSizeException
     *             if the specified cipher is a block cipher and the length of
     *             the serialized data is not a multiple of the ciphers block
     *             size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     */
    public final Object getObject(Cipher c)
                throws IOException, ClassNotFoundException,
                       IllegalBlockSizeException, BadPaddingException {
        if (c == null) {
            throw new NullPointerException("c == null");
        }
        byte[] serialized = c.doFinal(encryptedContent);
        ObjectInputStream ois =
                new ObjectInputStream(
                        new ByteArrayInputStream(serialized));
        return ois.readObject();
    }

    /**
     * Returns the wrapped object, decrypting it using the specified key. The
     * specified provider is used to retrieve the cipher algorithm.
     *
     * @param key
     *            the key to decrypt the data.
     * @param provider
     *            the name of the provider that provides the cipher algorithm.
     * @return the encapsulated object.
     * @throws IOException
     *             if deserialization fails.
     * @throws ClassNotFoundException
     *             if deserialization fails.
     * @throws NoSuchAlgorithmException
     *             if the algorithm used to decrypt the data is not available.
     * @throws NoSuchProviderException
     *             if the specified provider is not available.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to decrypt the data.
     */
    public final Object getObject(Key key, String provider)
                throws IOException, ClassNotFoundException,
                       NoSuchAlgorithmException, NoSuchProviderException,
                       InvalidKeyException {
        if (provider == null || provider.isEmpty()) {
            throw new IllegalArgumentException("provider name empty or null");
        }
        try {
            Cipher cipher = Cipher.getInstance(sealAlg, provider);
            if ((paramsAlg != null) && (paramsAlg.length() != 0)) {
                AlgorithmParameters params =
                    AlgorithmParameters.getInstance(paramsAlg);
                params.init(encodedParams);
                cipher.init(Cipher.DECRYPT_MODE, key, params);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
            byte[] serialized = cipher.doFinal(encryptedContent);
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new ByteArrayInputStream(serialized));
            return ois.readObject();
        } catch (NoSuchPaddingException e)  {
            // should not be thrown because cipher text was made
            // with existing padding
            throw new NoSuchAlgorithmException(e.toString());
        } catch (InvalidAlgorithmParameterException e) {
            // should not be thrown because cipher text was made
            // with correct algorithm parameters
            throw new NoSuchAlgorithmException(e.toString());
        } catch (IllegalBlockSizeException e) {
            // should not be thrown because the cipher text
            // was correctly made
            throw new NoSuchAlgorithmException(e.toString());
        } catch (BadPaddingException e) {
            // should not be thrown because the cipher text
            // was correctly made
            throw new NoSuchAlgorithmException(e.toString());
        } catch (IllegalStateException  e) {
            // should never be thrown because cipher is initialized
            throw new NoSuchAlgorithmException(e.toString());
        }
    }
}
