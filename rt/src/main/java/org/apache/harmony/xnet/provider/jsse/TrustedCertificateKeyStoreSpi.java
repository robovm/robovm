/*
 * Copyright (C) 2011 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreSpi;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

/**
 * A KeyStoreSpi wrapper for the TrustedCertificateStore.
 */
public final class TrustedCertificateKeyStoreSpi extends KeyStoreSpi {

    private final TrustedCertificateStore store = new TrustedCertificateStore();

    @Override public Key engineGetKey(String alias, char[] password) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        }
        return null;
    }

    @Override public Certificate[] engineGetCertificateChain(String alias) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        }
        return null;
    }

    @Override public Certificate engineGetCertificate(String alias) {
        return store.getCertificate(alias);
    }

    @Override public Date engineGetCreationDate(String alias) {
        return store.getCreationDate(alias);
    }

    @Override public void engineSetKeyEntry(
            String alias, Key key, char[] password, Certificate[] chain) {
        throw new UnsupportedOperationException();
    }

    @Override public void engineSetKeyEntry(String alias, byte[] key, Certificate[] chain) {
        throw new UnsupportedOperationException();
    }

    @Override public void engineSetCertificateEntry(String alias, Certificate cert) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        }
        throw new UnsupportedOperationException();
    }

    @Override public void engineDeleteEntry(String alias) {
        throw new UnsupportedOperationException();
    }

    @Override public Enumeration<String> engineAliases() {
        return Collections.enumeration(store.aliases());
    }

    @Override public boolean engineContainsAlias(String alias) {
        return store.containsAlias(alias);
    }

    @Override public int engineSize() {
        return store.aliases().size();
    }

    @Override public boolean engineIsKeyEntry(String alias) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        }
        return false;
    }

    @Override public boolean engineIsCertificateEntry(String alias) {
        return engineContainsAlias(alias);
    }

    @Override public String engineGetCertificateAlias(Certificate c) {
        return store.getCertificateAlias(c);
    }

    @Override public void engineStore(OutputStream stream, char[] password) {
        throw new UnsupportedOperationException();
    }

    @Override public void engineLoad(InputStream stream, char[] password) {
        if (stream != null) {
            throw new UnsupportedOperationException();
        }
    }
}
