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

package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Digest;

public class AndroidDigestFactoryOpenSSL implements AndroidDigestFactoryInterface {
    public Digest getMD5() {
        return new OpenSSLDigest.MD5();
    }
    public Digest getSHA1() {
        return new OpenSSLDigest.SHA1();
    }
    public Digest getSHA256() {
        return new OpenSSLDigest.SHA256();
    }
    public Digest getSHA384() {
        return new OpenSSLDigest.SHA384();
    }
    public Digest getSHA512() {
        return new OpenSSLDigest.SHA512();
    }
}
