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
 * href="http://www.ietf.org/rfc/rfc2040.txt">RC5</a> algorithm.
 */
public class RC5ParameterSpec implements AlgorithmParameterSpec {

    private final int version;
    private final int rounds;
    private final int wordSize;
    private final byte[] iv;

    /**
     * Creates a new <code>RC5ParameterSpec</code> instance with the specified
     * version, round count an word size (in bits).
     *
     * @param version
     *            the version.
     * @param rounds
     *            the round count.
     * @param wordSize
     *            the word size (in bits).
     */
    public RC5ParameterSpec(int version, int rounds, int wordSize) {
        this.version = version;
        this.rounds = rounds;
        this.wordSize = wordSize;
        this.iv = null;
    }

    /**
     * Creates a new <code>RC5ParameterSpec</code> instance with the specified
     * version, round count, word size (in bits) and an <i>initialization
     * vector</i>.
     * <p>
     * The size of the <i>initialization vector</i> must be at least
     * <code>2 * (wordSize / 8)</code> bytes which are copied to protect them
     * against modification.
     *
     * @param version
     *            the version.
     * @param rounds
     *            the round count.
     * @param wordSize
     *            the word size (in bits).
     * @param iv
     *            the initialization vector.
     * @throws IllegalArgumentException
     *             if the initialization vector is null or shorter than <code>2
     *             * (wordSize / 8)</code>.
     */
    public RC5ParameterSpec(int version, int rounds, int wordSize, byte[] iv) {
        if (iv == null) {
            throw new IllegalArgumentException("iv == null");
        }
        if (iv.length < 2 * (wordSize / 8)) {
            throw new IllegalArgumentException("iv.length < 2 * (wordSize / 8)");
        }
        this.version = version;
        this.rounds = rounds;
        this.wordSize = wordSize;
        this.iv = new byte[2*(wordSize/8)];
        System.arraycopy(iv, 0, this.iv, 0, 2*(wordSize/8));
    }

    /**
     * Creates a new <code>RC5ParameterSpec</code> instance with the specified
     * version, round count, wordSize (in bits), an <i>initialization vector</i>
     * and an offset.
     * <p>
     * The size of the <i>initialization vector</i> must be at least
     * <code>offset + (2 * (wordSize / 8))</code> bytes. The bytes starting at
     * <code>offset</code> are copied to protect them against modification.
     *
     * @param version
     *            the version.
     * @param rounds
     *            the round count.
     * @param wordSize
     *            the word size (in bits).
     * @param iv
     *            the initialization vector.
     * @param offset
     *            the offset in the initialization vector.
     * @throws IllegalArgumentException
     *             if the initialization vector is null of shorter than
     *             <code>offset + (2 * (wordSize / 8))</code>.
     * @throws ArrayIndexOutOfBoundsException
     *             if <code>offset</code> is negative.
     */
    public RC5ParameterSpec(int version, int rounds, int wordSize, byte[] iv, int offset) {
        if (iv == null) {
            throw new IllegalArgumentException("iv == null");
        }
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException("offset < 0: " + offset);
        }
        if (iv.length - offset < 2 * (wordSize / 8)) {
            throw new IllegalArgumentException("iv.length - offset < 2 * (wordSize / 8)");
        }
        this.version = version;
        this.rounds = rounds;
        this.wordSize = wordSize;
        this.iv = new byte[offset+2*(wordSize/8)];
        System.arraycopy(iv, offset, this.iv, 0, 2*(wordSize/8));
    }

    /**
     * Returns the version.
     *
     * @return the version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Returns the round count.
     *
     * @return the round count.
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Returns the word size (in bits).
     *
     * @return the word size (in bits).
     */
    public int getWordSize() {
        return wordSize;
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
     * Compares the specified object with this <code>RC5ParameterSpec</code>
     * instance.
     *
     * @param obj
     *            the object to compare.
     * @return true if version, round count, word size and initializaion vector
     *         of both objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RC5ParameterSpec)) {
            return false;
        }
        RC5ParameterSpec ps = (RC5ParameterSpec) obj;
        return (version == ps.version)
            && (rounds == ps.rounds)
            && (wordSize == ps.wordSize)
            && (Arrays.equals(iv, ps.iv));
    }

    /**
     * Returns the hash code of this <code>RC5ParameterSpec</code> instance.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int result = version + rounds + wordSize;
        if (iv == null) {
            return result;
        }
        for (byte element : iv) {
            result += element & 0xFF;
        }
        return result;
    }
}
