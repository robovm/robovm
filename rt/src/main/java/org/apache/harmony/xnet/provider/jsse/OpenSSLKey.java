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

class OpenSSLKey {
    private final int ctx;

    private final OpenSSLEngine engine;

    OpenSSLKey(int ctx) {
        this.ctx = ctx;
        engine = null;
    }

    OpenSSLKey(int ctx, OpenSSLEngine engine) {
        this.ctx = ctx;
        this.engine = engine;
    }

    int getPkeyContext() {
        return ctx;
    }

    OpenSSLEngine getEngine() {
        return engine;
    }

    boolean isEngineBased() {
        return engine != null;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (ctx != 0) {
                NativeCrypto.EVP_PKEY_free(ctx);
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof OpenSSLKey)) {
            return false;
        }

        OpenSSLKey other = (OpenSSLKey) o;
        if (ctx != other.getPkeyContext()) {
            return false;
        }

        if (engine == null) {
            return other.getEngine() == null;
        } else {
            return engine.equals(other.getEngine());
        }
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + ctx;
        hash = hash * 31 + (engine == null ? 0 : engine.getEngineContext());
        return hash;
    }
}
