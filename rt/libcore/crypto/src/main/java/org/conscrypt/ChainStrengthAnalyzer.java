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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

public final class ChainStrengthAnalyzer {

    private static final int MIN_MODULUS = 1024;
    private static final String[] OID_BLACKLIST = {"1.2.840.113549.1.1.4"}; // MD5withRSA

    public static final void check(X509Certificate[] chain) throws CertificateException {
        for (X509Certificate cert : chain) {
            checkCert(cert);
        }
    }

    private static final void checkCert(X509Certificate cert) throws CertificateException {
        checkModulusLength(cert);
        checkNotMD5(cert);
    }

    private static final void checkModulusLength(X509Certificate cert) throws CertificateException {
        Object pubkey = cert.getPublicKey();
        if (pubkey instanceof RSAPublicKey) {
            int modulusLength = ((RSAPublicKey) pubkey).getModulus().bitLength();
            if(!(modulusLength >= MIN_MODULUS)) {
                throw new CertificateException("Modulus is < 1024 bits");
            }
        }
    }

    private static final void checkNotMD5(X509Certificate cert) throws CertificateException {
        String oid = cert.getSigAlgOID();
        for (String blacklisted : OID_BLACKLIST) {
            if (oid.equals(blacklisted)) {
                throw new CertificateException("Signature uses an insecure hash function");
            }
        }
    }
}

