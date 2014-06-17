/*
 * Copyright 2013 The Android Open Source Project
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

import java.security.PublicKey;
import java.util.Arrays;

/**
 * A simple but useless key class that holds X.509 public key information when
 * the appropriate KeyFactory for the key algorithm is not available.
 */
public class X509PublicKey implements PublicKey {
    private static final long serialVersionUID = -8610156854731664298L;

    private final String algorithm;

    private final byte[] encoded;

    public X509PublicKey(String algorithm, byte[] encoded) {
        this.algorithm = algorithm;
        this.encoded = encoded;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String getFormat() {
        return "X.509";
    }

    @Override
    public byte[] getEncoded() {
        return encoded;
    }

    @Override
    public String toString() {
        return "X509PublicKey [algorithm=" + algorithm + ", encoded=" + Arrays.toString(encoded)
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((algorithm == null) ? 0 : algorithm.hashCode());
        result = prime * result + Arrays.hashCode(encoded);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        X509PublicKey other = (X509PublicKey) obj;
        if (algorithm == null) {
            if (other.algorithm != null)
                return false;
        } else if (!algorithm.equals(other.algorithm))
            return false;
        if (!Arrays.equals(encoded, other.encoded))
            return false;
        return true;
    }
}
