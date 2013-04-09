/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.javax.crypto;

import com.android.org.bouncycastle.asn1.x509.KeyUsage;
import java.security.InvalidKeyException;
import java.security.cert.Certificate;
import javax.crypto.Cipher;
import junit.framework.TestCase;
import libcore.java.security.TestKeyStore;

public final class CipherTest extends TestCase {

    public void testCipherInitWithCertificate () throws Exception {
        // no key usage specified, everything is fine
        assertCipherInitWithKeyUsage(0,                         true,  true, true,  true);

        // common case is that encrypt/wrap is prohibited when special usage is specified
        assertCipherInitWithKeyUsage(KeyUsage.digitalSignature, false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.nonRepudiation,   false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.keyAgreement,     false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.keyCertSign,      false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.cRLSign,          false, true, false, true);

        // Note they encipherOnly/decipherOnly don't have to do with
        // ENCRYPT_MODE or DECRYPT_MODE, but restrict usage relative
        // to keyAgreement. There is not a *_MODE option that
        // corresponds to this in Cipher, the RI does not enforce
        // anything in Cipher.
        // http://code.google.com/p/android/issues/detail?id=12955
        assertCipherInitWithKeyUsage(KeyUsage.encipherOnly,     false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.decipherOnly,     false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.keyAgreement | KeyUsage.encipherOnly,
                                                                false, true, false, true);
        assertCipherInitWithKeyUsage(KeyUsage.keyAgreement | KeyUsage.decipherOnly,
                                                                false, true, false, true);

        // except when wrapping a key is specifically allowed or
        assertCipherInitWithKeyUsage(KeyUsage.keyEncipherment,  false, true, true,  true);
        // except when wrapping data encryption is specifically allowed
        assertCipherInitWithKeyUsage(KeyUsage.dataEncipherment, true,  true, false, true);
    }

    private void assertCipherInitWithKeyUsage (int keyUsage,
                                               boolean allowEncrypt,
                                               boolean allowDecrypt,
                                               boolean allowWrap,
                                               boolean allowUnwrap) throws Exception {
        Certificate certificate = certificateWithKeyUsage(keyUsage);
        assertCipherInitWithKeyUsage(certificate, allowEncrypt, Cipher.ENCRYPT_MODE);
        assertCipherInitWithKeyUsage(certificate, allowDecrypt, Cipher.DECRYPT_MODE);
        assertCipherInitWithKeyUsage(certificate, allowWrap,    Cipher.WRAP_MODE);
        assertCipherInitWithKeyUsage(certificate, allowUnwrap,  Cipher.UNWRAP_MODE);
    }

    private void assertCipherInitWithKeyUsage(Certificate certificate,
                                              boolean allowMode,
                                              int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        if (allowMode) {
            cipher.init(mode, certificate);
        } else {
            try {
                cipher.init(mode, certificate);
                String modeString;
                switch (mode) {
                    case Cipher.ENCRYPT_MODE:
                        modeString = "ENCRYPT_MODE";
                        break;
                    case Cipher.DECRYPT_MODE:
                        modeString = "DECRYPT_MODE";
                        break;
                    case Cipher.WRAP_MODE:
                        modeString = "WRAP_MODE";
                        break;
                    case Cipher.UNWRAP_MODE:
                        modeString = "UNWRAP_MODE";
                        break;
                    default:
                        throw new AssertionError("Unknown Cipher.*_MODE " + mode);
                }
                fail("Should have had InvalidKeyException for " + modeString
                     + " for " + certificate);
            } catch (InvalidKeyException expected) {
            }
        }
    }

    private Certificate certificateWithKeyUsage(int keyUsage) throws Exception {
        // note the rare usage of non-zero keyUsage
        return new TestKeyStore.Builder()
                .aliasPrefix("rsa-dsa-ec")
                .keyUsage(keyUsage)
                .build()
                .getPrivateKey("RSA", "RSA").getCertificate();
    }
}
