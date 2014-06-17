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

class OpenSSLCipherContext {
    private final long context;

    OpenSSLCipherContext(long ctx) {
        if (ctx == 0) {
            throw new NullPointerException("ctx == 0");
        }

        this.context = ctx;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            NativeCrypto.EVP_CIPHER_CTX_cleanup(context);
        } finally {
            super.finalize();
        }
    }

    long getContext() {
        return context;
    }
}
