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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public class OpenSSLEngine {
    static {
        NativeCrypto.ENGINE_load_dynamic();
    }

    private static final Object mLoadingLock = new Object();

    /** The ENGINE's native handle. */
    private final long ctx;

    public static OpenSSLEngine getInstance(String engine) throws IllegalArgumentException {
        if (engine == null) {
            throw new NullPointerException("engine == null");
        }

        final long engineCtx;
        synchronized (mLoadingLock) {
            engineCtx = NativeCrypto.ENGINE_by_id(engine);
            if (engineCtx == 0) {
                throw new IllegalArgumentException("Unknown ENGINE id: " + engine);
            }

            NativeCrypto.ENGINE_add(engineCtx);
        }

        return new OpenSSLEngine(engineCtx);
    }

    private OpenSSLEngine(long engineCtx) {
        ctx = engineCtx;

        if (NativeCrypto.ENGINE_init(engineCtx) == 0) {
            NativeCrypto.ENGINE_free(engineCtx);
            throw new IllegalArgumentException("Could not initialize engine");
        }
    }

    public PrivateKey getPrivateKeyById(String id) throws InvalidKeyException {
        if (id == null) {
            throw new NullPointerException("id == null");
        }

        final long keyRef = NativeCrypto.ENGINE_load_private_key(ctx, id);
        if (keyRef == 0) {
            return null;
        }

        OpenSSLKey pkey = new OpenSSLKey(keyRef, this, id);
        try {
            return pkey.getPrivateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidKeyException(e);
        }
    }

    public SecretKey getSecretKeyById(String id, String algorithm) throws InvalidKeyException {
        if (id == null) {
            throw new NullPointerException("id == null");
        }

        final long keyRef = NativeCrypto.ENGINE_load_private_key(ctx, id);
        if (keyRef == 0) {
            return null;
        }

        OpenSSLKey pkey = new OpenSSLKey(keyRef, this, id);
        try {
            return pkey.getSecretKey(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidKeyException(e);
        }
    }

    long getEngineContext() {
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

        if (other.getEngineContext() == ctx) {
            return true;
        }

        final String id = NativeCrypto.ENGINE_get_id(ctx);
        if (id == null) {
            return false;
        }

        return id.equals(NativeCrypto.ENGINE_get_id(other.getEngineContext()));
    }

    @Override
    public int hashCode() {
      return (int) ctx;
    }
}
