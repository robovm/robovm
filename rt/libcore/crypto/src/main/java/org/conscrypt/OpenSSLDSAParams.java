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

import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.spec.AlgorithmParameterSpec;

public class OpenSSLDSAParams implements DSAParams, AlgorithmParameterSpec {

    private OpenSSLKey key;

    private boolean fetchedParams;

    private BigInteger g;

    private BigInteger p;

    private BigInteger q;

    private BigInteger y;

    private BigInteger x;

    OpenSSLDSAParams(OpenSSLKey key) {
        this.key = key;
    }

    OpenSSLKey getOpenSSLKey() {
        return key;
    }

    private synchronized final void ensureReadParams() {
        if (fetchedParams) {
            return;
        }

        byte[][] params = NativeCrypto.get_DSA_params(key.getPkeyContext());
        if (params[0] != null) {
            g = new BigInteger(params[0]);
        }
        if (params[1] != null) {
            p = new BigInteger(params[1]);
        }
        if (params[2] != null) {
            q = new BigInteger(params[2]);
        }
        if (params[3] != null) {
            y = new BigInteger(params[3]);
        }
        if (params[4] != null) {
            x = new BigInteger(params[4]);
        }

        fetchedParams = true;
    }

    @Override
    public BigInteger getG() {
        ensureReadParams();
        return g;
    }

    @Override
    public BigInteger getP() {
        ensureReadParams();
        return p;
    }

    @Override
    public BigInteger getQ() {
        ensureReadParams();
        return q;
    }

    boolean hasParams() {
        ensureReadParams();
        return (g != null) && (p != null) && (q != null);
    }

    BigInteger getY() {
        ensureReadParams();
        return y;
    }

    BigInteger getX() {
        ensureReadParams();
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OpenSSLDSAParams) {
            OpenSSLDSAParams other = (OpenSSLDSAParams) o;

            /*
             * We can shortcut the true case, but it still may be equivalent but
             * different copies.
             */
            if (key == other.getOpenSSLKey()) {
                return true;
            }
        }

        if (!(o instanceof DSAParams)) {
            return false;
        }

        ensureReadParams();

        DSAParams other = (DSAParams) o;
        return g.equals(other.getG()) && p.equals(other.getP()) && q.equals(other.getQ());
    }

    @Override
    public int hashCode() {
        ensureReadParams();

        return g.hashCode() ^ p.hashCode() ^ q.hashCode();
    }

    @Override
    public String toString() {
        ensureReadParams();

        final StringBuilder sb = new StringBuilder("OpenSSLDSAParams{");
        sb.append("G=");
        sb.append(g.toString(16));
        sb.append(",P=");
        sb.append(p.toString(16));
        sb.append(",Q=");
        sb.append(q.toString(16));
        sb.append('}');

        return sb.toString();
    }
}
