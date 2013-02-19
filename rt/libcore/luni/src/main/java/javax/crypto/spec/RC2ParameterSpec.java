/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * The algorithm parameter specification for the <a
 * href="http://www.ietf.org/rfc/rfc2268.txt">RC2</a> algorithm.
 */
public class RC2ParameterSpec implements AlgorithmParameterSpec {

    private final int effectiveKeyBits;
    private final byte[] iv;

    /**
     * Creates a new <code>RC2ParameterSpec</code> instance with the specified
     * effective key length (in bits),
     *
     * @param effectiveKeyBits
     *            the effective key length (in bits).
     */
    public RC2ParameterSpec(int effectiveKeyBits) {
        this.effectiveKeyBits = effectiveKeyBits;
        iv = null;
    }

    /**
     * Creates a new <code>RC2ParameterSpec</code> instance with the specified
     * effective key length (in bits) and <i>initialization vector</i>.
     * <p>
     * The size of the <i>initialization vector</i> must be at least 8 bytes
     * which are copied to protect them against modification.
     *
     * @param effectiveKeyBits
     *            the effective key length (in bits).
     * @param iv
     *            the initialization vector.
     * @throws IllegalArgumentException
     *             if the initialization vector is null or shorter than 8 bytes.
     */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv) {
        if (iv == null) {
            throw new IllegalArgumentException("iv == null");
        }
        if (iv.length < 8) {
            throw new IllegalArgumentException("iv.length < 8");
        }
        this.effectiveKeyBits = effectiveKeyBits;
        this.iv = new byte[8];
        System.arraycopy(iv, 0, this.iv, 0, 8);
    }

    /**
     * Creates a new <code>RC2ParameterSpec</code> instance with the specified
     * effective key length (in bits) and <i>initialization vector<i>.
     * <p>
     * The size of the <i>initialization vector</i> starting at
     * <code>offset</code> must be at least 8 bytes which are copied to protect
     * them against modification.
     *
     * @param effectiveKeyBits
     *            the effective key length (in bits).
     * @param iv
     *            the initialization vector.
     * @param offset
     *            the offset in the initialization vector to start at.
     * @throws IllegalArgumentException
     *             if the initialization vector is null or starting at
     *             <code>offset</code> is shorter than 8 bytes.
     */
    public RC2ParameterSpec(int effectiveKeyBits, byte[] iv, int offset) {
        if (iv == null) {
            throw new IllegalArgumentException("iv == null");
        }
        if (iv.length - offset < 8) {
            throw new IllegalArgumentException("iv.length - offset < 8");
        }
        this.effectiveKeyBits = effectiveKeyBits;
        this.iv = new byte[8];
        System.arraycopy(iv, offset, this.iv, 0, 8);
    }

    /**
     * Returns the effective key length (in bits).
     *
     * @return the effective key length (in bits).
     */
    public int getEffectiveKeyBits() {
        return effectiveKeyBits;
    }

    /**
     * Returns a copy of the initialization vector.
     *
     * @return a copy of the initialization vector, or null if none specified.
     */
    public byte[] getIV() {
        if (iv == null) {
            return null;
        }
        byte[] result = new byte[iv.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        return result;
    }

    /**
     * Compares the specified object to this <code>RC2ParameterSpec</code>
     * instance.
     *
     * @param obj
     *            the object to compare.
     * @return true if the effective key length and the initialization vector of
     *         both objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RC2ParameterSpec)) {
            return false;
        }
        RC2ParameterSpec ps = (RC2ParameterSpec) obj;
        return (effectiveKeyBits == ps.effectiveKeyBits)
            && (Arrays.equals(iv, ps.iv));
    }

    /**
     * Returns the hash code of this <code>RC2ParameterSpec</code> instance.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int result = effectiveKeyBits;
        if (iv == null) {
            return result;
        }
        for (byte element : iv) {
            result += element;
        }
        return result;
    }
}
