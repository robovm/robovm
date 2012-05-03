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

package org.apache.harmony.security.provider.crypto;

import java.security.Provider;

/**
 * Implementation of Provider for SecureRandom, MessageDigest and Signature
 * using a Secure Hash Algorithm, SHA-1;
 * see SECURE HASH STANDARD, FIPS PUB 180-1 (http://www.itl.nist.gov/fipspubs/fip180-1.htm) <BR>
 * <BR>
 * The implementation supports "SHA1PRNG", "SHA-1" and "SHA1withDSA" algorithms described in
 * JavaTM Cryptography Architecture, API Specification & Reference
 */

public final class CryptoProvider extends Provider {

    private static final long serialVersionUID = 7991202868423459598L;

    /**
     * Creates a Provider and puts parameters
     */
    public CryptoProvider() {

        super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");

        //  names of classes implementing services
        final String MD_NAME = "org.apache.harmony.security.provider.crypto.SHA1_MessageDigestImpl";
        final String SR_NAME = "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl";

        final String SIGN_NAME = "org.apache.harmony.security.provider.crypto.SHA1withDSA_SignatureImpl";

        final String SIGN_ALIAS = "SHA1withDSA";


        final String KEYF_NAME = "org.apache.harmony.security.provider.crypto.DSAKeyFactoryImpl";

        put("MessageDigest.SHA-1", MD_NAME);
        put("MessageDigest.SHA-1 ImplementedIn", "Software");
        put("Alg.Alias.MessageDigest.SHA1", "SHA-1");
        put("Alg.Alias.MessageDigest.SHA", "SHA-1");

        if (RandomBitsSupplier.isServiceAvailable()) {
            put("SecureRandom.SHA1PRNG", SR_NAME);
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }

        put("Signature.SHA1withDSA", SIGN_NAME);
        put("Signature.SHA1withDSA ImplementedIn", "Software");
        put("Alg.Alias.Signature.SHAwithDSA", SIGN_ALIAS);
        put("Alg.Alias.Signature.DSAwithSHA1", SIGN_ALIAS);
        put("Alg.Alias.Signature.SHA1/DSA", SIGN_ALIAS);
        put("Alg.Alias.Signature.SHA/DSA", SIGN_ALIAS);
        put("Alg.Alias.Signature.SHA-1/DSA", SIGN_ALIAS);
        put("Alg.Alias.Signature.DSA", SIGN_ALIAS);
        put("Alg.Alias.Signature.DSS", SIGN_ALIAS);

        put("Alg.Alias.Signature.OID.1.2.840.10040.4.3", SIGN_ALIAS);
        put("Alg.Alias.Signature.1.2.840.10040.4.3", SIGN_ALIAS);
        put("Alg.Alias.Signature.1.3.14.3.2.13", SIGN_ALIAS);
        put("Alg.Alias.Signature.1.3.14.3.2.27", SIGN_ALIAS);

        put("KeyFactory.DSA", KEYF_NAME);
        put("KeyFactory.DSA ImplementedIn", "Software");
        put("Alg.Alias.KeyFactory.1.3.14.3.2.12", "DSA");
        put("Alg.Alias.KeyFactory.1.2.840.10040.4.1", "DSA");
    }
}
