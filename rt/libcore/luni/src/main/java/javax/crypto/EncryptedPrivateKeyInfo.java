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

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import org.apache.harmony.security.asn1.ASN1Any;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.AlgorithmIdentifier;


/**
 * This class implements the {@code EncryptedPrivateKeyInfo} ASN.1 type as
 * specified in <a href="http://www.ietf.org/rfc/rfc5208.txt">PKCS
 * #8 - Private-Key Information Syntax Standard</a>.
 * <p>
 * The definition of ASN.1 is as follows:
 * <dl>
 * EncryptedPrivateKeyInfo ::= SEQUENCE {
 * <dd>encryptionAlgorithm AlgorithmIdentifier,</dd>
 * <dd>encryptedData OCTET STRING }</dd>
 * </dl>
 * <dl>
 * AlgorithmIdentifier ::= SEQUENCE {
 * <dd>algorithm OBJECT IDENTIFIER,</dd>
 * <dd>parameters ANY DEFINED BY algorithm OPTIONAL }</dd>
 * </dl>
 */
public class EncryptedPrivateKeyInfo {
    // Encryption algorithm name
    private String algName;
    // Encryption algorithm parameters
    private final AlgorithmParameters algParameters;
    // Encrypted private key data
    private final byte[] encryptedData;
    // Encryption algorithm OID
    private String oid;
    // This EncryptedPrivateKeyInfo ASN.1 DER encoding
    private volatile byte[] encoded;

    /**
     * Creates an {@code EncryptedPrivateKeyInfo} instance from its encoded
     * representation by parsing it.
     *
     * @param encoded
     *            the encoded representation of this object
     * @throws IOException
     *             if parsing the encoded representation fails.
     * @throws NullPointerException
     *             if {@code encoded} is {@code null}.
     */
    public EncryptedPrivateKeyInfo(byte[] encoded) throws IOException {
        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }
        this.encoded = new byte[encoded.length];
        System.arraycopy(encoded, 0, this.encoded, 0, encoded.length);
        Object[] values;

        values = (Object[])asn1.decode(encoded);

        AlgorithmIdentifier aId = (AlgorithmIdentifier) values[0];

        algName = aId.getAlgorithm();
        // algName == oid now
        boolean mappingExists = mapAlgName();
        // algName == name from map oid->name if mapping exists, or
        // algName == oid if mapping does not exist

        AlgorithmParameters aParams = null;
        byte[] params = aId.getParameters();
        if (params != null && !isNullValue(params)) {
            try {
                aParams = AlgorithmParameters.getInstance(algName);
                aParams.init(aId.getParameters());
                if (!mappingExists) {
                    algName = aParams.getAlgorithm();
                }
            } catch (NoSuchAlgorithmException e) {
            }
        }
        algParameters = aParams;

        encryptedData = (byte[]) values[1];
    }

    private static boolean isNullValue(byte[] toCheck) {
        return toCheck[0] == 5 && toCheck[1] == 0;
    }

    /**
     * Creates an {@code EncryptedPrivateKeyInfo} instance from an algorithm
     * name and its encrypted data.
     *
     * @param encryptionAlgorithmName
     *            the name of an algorithm.
     * @param encryptedData
     *            the encrypted data.
     * @throws NoSuchAlgorithmException
     *             if the {@code encrAlgName} is not a supported algorithm.
     * @throws NullPointerException
     *             if {@code encrAlgName} or {@code encryptedData} is {@code
     *             null}.
     * @throws IllegalArgumentException
     *             if {@code encryptedData} is empty.
     */
    public EncryptedPrivateKeyInfo(String encryptionAlgorithmName, byte[] encryptedData)
        throws NoSuchAlgorithmException {
        if (encryptionAlgorithmName == null) {
            throw new NullPointerException("encryptionAlgorithmName == null");
        }
        this.algName = encryptionAlgorithmName;
        if (!mapAlgName()) {
            throw new NoSuchAlgorithmException("Unsupported algorithm: " + this.algName);
        }
        if (encryptedData == null) {
            throw new NullPointerException("encryptedData == null");
        }
        if (encryptedData.length == 0) {
            throw new IllegalArgumentException("encryptedData.length == 0");
        }
        this.encryptedData = new byte[encryptedData.length];
        System.arraycopy(encryptedData, 0,
                this.encryptedData, 0, encryptedData.length);
        this.algParameters = null;
    }

    /**
     * Creates an {@code EncryptedPrivateKeyInfo} instance from the
     * encryption algorithm parameters an its encrypted data.
     *
     * @param algParams
     *            the encryption algorithm parameters.
     * @param encryptedData
     *            the encrypted data.
     * @throws NoSuchAlgorithmException
     *             if the algorithm name of the specified {@code algParams}
     *             parameter is not supported.
     * @throws NullPointerException
     *             if {@code algParams} or {@code encryptedData} is
     *             {@code null}.
     */
    public EncryptedPrivateKeyInfo(AlgorithmParameters algParams, byte[] encryptedData)
        throws NoSuchAlgorithmException {
        if (algParams == null) {
            throw new NullPointerException("algParams == null");
        }
        this.algParameters = algParams;
        if (encryptedData == null) {
            throw new NullPointerException("encryptedData == null");
        }
        if (encryptedData.length == 0) {
            throw new IllegalArgumentException("encryptedData.length == 0");
        }
        this.encryptedData = new byte[encryptedData.length];
        System.arraycopy(encryptedData, 0,
                this.encryptedData, 0, encryptedData.length);
        this.algName = this.algParameters.getAlgorithm();
        if (!mapAlgName()) {
            throw new NoSuchAlgorithmException("Unsupported algorithm: " + this.algName);
        }
    }

    /**
     * Returns the name of the encryption algorithm.
     *
     * @return the name of the encryption algorithm.
     */
    public String getAlgName() {
        return algName;
    }

    /**
     * Returns the parameters used by the encryption algorithm.
     *
     * @return the parameters used by the encryption algorithm.
     */
    public AlgorithmParameters getAlgParameters() {
        return algParameters;
    }

    /**
     * Returns the encrypted data of this key.
     *
     * @return the encrypted data of this key, each time this method is called a
     *         new array is returned.
     */
    public byte[] getEncryptedData() {
        byte[] ret = new byte[encryptedData.length];
        System.arraycopy(encryptedData, 0, ret, 0, encryptedData.length);
        return ret;
    }

    /**
     * Returns the {@code PKCS8EncodedKeySpec} object extracted from the
     * encrypted data.
     * <p>
     * The cipher must be initialize in either {@code Cipher.DECRYPT_MODE} or
     * {@code Cipher.UNWRAP_MODE} with the same parameters and key used for
     * encrypting this.
     *
     * @param cipher
     *            the cipher initialized for decrypting the encrypted data.
     * @return the extracted {@code PKCS8EncodedKeySpec}.
     * @throws InvalidKeySpecException
     *             if the specified cipher is not suited to decrypt the
     *             encrypted data.
     * @throws NullPointerException
     *             if {@code cipher} is {@code null}.
     */
    public PKCS8EncodedKeySpec getKeySpec(Cipher cipher)
        throws InvalidKeySpecException {
        if (cipher == null) {
            throw new NullPointerException("cipher == null");
        }
        try {
            byte[] decryptedData = cipher.doFinal(encryptedData);
            try {
                ASN1PrivateKeyInfo.verify(decryptedData);
            } catch (IOException e1) {
                throw new InvalidKeySpecException("Decrypted data does not represent valid PKCS#8 PrivateKeyInfo");
            }
            return new PKCS8EncodedKeySpec(decryptedData);
        } catch (IllegalStateException e) {
            throw new InvalidKeySpecException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new InvalidKeySpecException(e.getMessage());
        } catch (BadPaddingException e) {
            throw new InvalidKeySpecException(e.getMessage());
        }
    }

    /**
     * Returns the {@code PKCS8EncodedKeySpec} object extracted from the
     * encrypted data.
     *
     * @param decryptKey
     *            the key to decrypt the encrypted data with.
     * @return the extracted {@code PKCS8EncodedKeySpec}.
     * @throws NoSuchAlgorithmException
     *             if no usable cipher can be found to decrypt the encrypted
     *             data.
     * @throws InvalidKeyException
     *             if {@code decryptKey} is not usable to decrypt the encrypted
     *             data.
     * @throws NullPointerException
     *             if {@code decryptKey} is {@code null}.
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey) throws NoSuchAlgorithmException,
               InvalidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey == null");
        }
        try {
            Cipher cipher = Cipher.getInstance(algName);
            if (algParameters == null) {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey, algParameters);
            }
            byte[] decryptedData = cipher.doFinal(encryptedData);
            try {
                ASN1PrivateKeyInfo.verify(decryptedData);
            } catch (IOException e1) {
                throw invalidKey();
            }
            return new PKCS8EncodedKeySpec(decryptedData);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (BadPaddingException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /**
     * Returns the {@code PKCS8EncodedKeySpec} object extracted from the
     * encrypted data.
     *
     * @param decryptKey
     *            the key to decrypt the encrypted data with.
     * @param providerName
     *            the name of a provider whose cipher implementation should be
     *            used.
     * @return the extracted {@code PKCS8EncodedKeySpec}.
     * @throws NoSuchProviderException
     *             if no provider with {@code providerName} can be found.
     * @throws NoSuchAlgorithmException
     *             if no usable cipher can be found to decrypt the encrypted
     *             data.
     * @throws InvalidKeyException
     *             if {@code decryptKey} is not usable to decrypt the encrypted
     *             data.
     * @throws NullPointerException
     *             if {@code decryptKey} or {@code providerName} is {@code null}
     *             .
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey, String providerName)
        throws NoSuchProviderException,
               NoSuchAlgorithmException,
               InvalidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey == null");
        }
        if (providerName == null) {
            throw new NullPointerException("providerName == null");
        }
        try {
            Cipher cipher = Cipher.getInstance(algName, providerName);
            if (algParameters == null) {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey, algParameters);
            }
            byte[] decryptedData = cipher.doFinal(encryptedData);
            try {
                ASN1PrivateKeyInfo.verify(decryptedData);
            } catch (IOException e1) {
                throw invalidKey();
            }
            return new PKCS8EncodedKeySpec(decryptedData);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (BadPaddingException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /**
     * Returns the {@code PKCS8EncodedKeySpec} object extracted from the
     * encrypted data.
     *
     * @param decryptKey
     *            the key to decrypt the encrypted data with.
     * @param provider
     *            the provider whose cipher implementation should be used.
     * @return the extracted {@code PKCS8EncodedKeySpec}.
     * @throws NoSuchAlgorithmException
     *             if no usable cipher can be found to decrypt the encrypted
     *             data.
     * @throws InvalidKeyException
     *             if {@code decryptKey} is not usable to decrypt the encrypted
     *             data.
     * @throws NullPointerException
     *             if {@code decryptKey} or {@code provider} is {@code null}.
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey, Provider provider)
        throws NoSuchAlgorithmException,
               InvalidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey == null");
        }
        if (provider == null) {
            throw new NullPointerException("provider == null");
        }
        try {
            Cipher cipher = Cipher.getInstance(algName, provider);
            if (algParameters == null) {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey, algParameters);
            }
            byte[] decryptedData = cipher.doFinal(encryptedData);
            try {
                ASN1PrivateKeyInfo.verify(decryptedData);
            } catch (IOException e1) {
                throw invalidKey();
            }
            return new PKCS8EncodedKeySpec(decryptedData);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (BadPaddingException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    private InvalidKeyException invalidKey() throws InvalidKeyException {
        throw new InvalidKeyException("Decrypted data does not represent valid PKCS#8 PrivateKeyInfo");
    }

    /**
     * Returns the ASN.1 encoded representation of this object.
     *
     * @return the ASN.1 encoded representation of this object.
     * @throws IOException
     *             if encoding this object fails.
     */
    public byte[] getEncoded() throws IOException {
        if (encoded == null) {
            // Generate ASN.1 encoding:
            encoded = asn1.encode(this);
        }
        byte[] ret = new byte[encoded.length];
        System.arraycopy(encoded, 0, ret, 0, encoded.length);
        return ret;
    }

    // Performs all needed alg name mappings.
    // Returns 'true' if mapping available 'false' otherwise
    private boolean mapAlgName() {
        if (AlgNameMapper.isOID(this.algName)) {
            // OID provided to the ctor
            // get rid of possible leading "OID."
            this.oid = AlgNameMapper.normalize(this.algName);
            // try to find mapping OID->algName
            this.algName = AlgNameMapper.map2AlgName(this.oid);
            // if there is no mapping OID->algName
            // set OID as algName
            if (this.algName == null) {
                this.algName = this.oid;
            }
        } else {
            String stdName = AlgNameMapper.getStandardName(this.algName);
            // Alg name provided to the ctor
            // try to find mapping algName->OID or
            // (algName->stdAlgName)->OID
            this.oid = AlgNameMapper.map2OID(this.algName);
            if (this.oid == null) {
                if (stdName == null) {
                    // no above mappings available
                    return false;
                }
                this.oid = AlgNameMapper.map2OID(stdName);
                if (this.oid == null) {
                    return false;
                }
                this.algName = stdName;
            } else if (stdName != null) {
                this.algName = stdName;
            }
        }
        return true;
    }

    //
    // EncryptedPrivateKeyInfo DER encoder/decoder.
    // EncryptedPrivateKeyInfo ASN.1 definition
    // (as defined in PKCS #8: Private-Key Information Syntax Standard
    //  http://www.ietf.org/rfc/rfc2313.txt)
    //
    // EncryptedPrivateKeyInfo ::=  SEQUENCE {
    //      encryptionAlgorithm   AlgorithmIdentifier,
    //      encryptedData   OCTET STRING }
    //

    private static final byte[] nullParam = new byte[] { 5, 0 };

    private static final ASN1Sequence asn1 = new ASN1Sequence(new ASN1Type[] {
            AlgorithmIdentifier.ASN1, ASN1OctetString.getInstance() }) {

                @Override
                protected void getValues(Object object, Object[] values) {

                    EncryptedPrivateKeyInfo epki = (EncryptedPrivateKeyInfo) object;

                    try {
                        byte[] algParmsEncoded = (epki.algParameters == null) ? nullParam
                                : epki.algParameters.getEncoded();
                        values[0] = new AlgorithmIdentifier(epki.oid, algParmsEncoded);
                        values[1] = epki.encryptedData;
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
    };

    // PrivateKeyInfo DER decoder.
    // PrivateKeyInfo ASN.1 definition
    // (as defined in PKCS #8: Private-Key Information Syntax Standard
    //  http://www.ietf.org/rfc/rfc2313.txt)
    //
    //
    //    PrivateKeyInfo ::= SEQUENCE {
    //        version Version,
    //        privateKeyAlgorithm PrivateKeyAlgorithmIdentifier,
    //        privateKey PrivateKey,
    //        attributes [0] IMPLICIT Attributes OPTIONAL }
    //
    //      Version ::= INTEGER
    //
    //      PrivateKeyAlgorithmIdentifier ::= AlgorithmIdentifier
    //
    //      PrivateKey ::= OCTET STRING
    //
    //      Attributes ::= SET OF Attribute

    private static final ASN1SetOf ASN1Attributes = new ASN1SetOf(ASN1Any.getInstance());

    private static final ASN1Sequence ASN1PrivateKeyInfo = new ASN1Sequence(
            new ASN1Type[] { ASN1Integer.getInstance(), AlgorithmIdentifier.ASN1,
                    ASN1OctetString.getInstance(),
                    new ASN1Implicit(0, ASN1Attributes) }) {
        {
            setOptional(3); //attributes are optional
        }
    };
}
