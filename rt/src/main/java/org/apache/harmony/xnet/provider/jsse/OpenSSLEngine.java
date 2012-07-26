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

package org.apache.harmony.xnet.provider.jsse;

import java.security.InvalidKeyException;
import java.security.PrivateKey;

public class OpenSSLEngine {
    static {
        NativeCrypto.ENGINE_load_dynamic();
    }

    /** The ENGINE's native handle. */
    private final int ctx;

    public static OpenSSLEngine getInstance(String engine) throws IllegalArgumentException {
        if (engine == null) {
            throw new NullPointerException("engine == null");
        }

        final int engineCtx = NativeCrypto.ENGINE_by_id(engine);

        if (engineCtx == 0) {
            throw new IllegalArgumentException("Unknown ENGINE id: " + engine);
        }

        return new OpenSSLEngine(engineCtx);
    }

    private OpenSSLEngine(int engineCtx) {
        ctx = engineCtx;

        if (NativeCrypto.ENGINE_init(engineCtx) == 0) {
            throw new IllegalArgumentException("Could not initialize engine");
        }
    }

    public PrivateKey getPrivateKeyById(String id) throws InvalidKeyException {
        if (id == null) {
            throw new NullPointerException("id == null");
        }

        final int keyRef = NativeCrypto.ENGINE_load_private_key(ctx, id);
        if (keyRef == 0) {
            return null;
        }

        final int keyType = NativeCrypto.EVP_PKEY_type(keyRef);
        switch (keyType) {
            case NativeCrypto.EVP_PKEY_RSA:
                return OpenSSLRSAPrivateKey.getInstance(new OpenSSLKey(keyRef, this));
            case NativeCrypto.EVP_PKEY_DSA:
                return new OpenSSLDSAPrivateKey(new OpenSSLKey(keyRef, this));
            default:
                throw new InvalidKeyException("Unknown key type: " + keyType);
        }
    }

    int getEngineContext() {
        return ctx;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            NativeCrypto.ENGINE_finish(ctx);
            NativeCrypto.ENGINE_free(ctx);
        } finally {
            super.finalize();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof OpenSSLEngine)) {
            return false;
        }

        OpenSSLEngine other = (OpenSSLEngine) o;

        return other.getEngineContext() == ctx;
    }

    @Override
    public int hashCode() {
        return ctx;
    }
}
