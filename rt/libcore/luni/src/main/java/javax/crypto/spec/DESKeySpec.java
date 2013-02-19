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

import java.security.InvalidKeyException;
import java.security.spec.KeySpec;

/**
 * The key specification for a DES key.
 */
public class DESKeySpec implements KeySpec {

    /**
     * The length of a DES key in bytes.
     */
    public static final int DES_KEY_LEN = 8;

    private final byte[] key;

    // DES weak and semi-weak keys
    // Got from:
    // FIP PUB 74
    // FEDERAL INFORMATION PROCESSING STANDARDS PUBLICATION 1981
    // GUIDELINES FOR IMPLEMENTING AND USING THE NBS DATA ENCRYPTION STANDARD
    // http://www.dice.ucl.ac.be/crypto/standards/fips/fip74/fip74-1.pdf
    private static final byte[][] SEMIWEAKS = {
                {(byte) 0xE0, (byte) 0x01, (byte) 0xE0, (byte) 0x01,
                 (byte) 0xF1, (byte) 0x01, (byte) 0xF1, (byte) 0x01},

                {(byte) 0x01, (byte) 0xE0, (byte) 0x01, (byte) 0xE0,
                 (byte) 0x01, (byte) 0xF1, (byte) 0x01, (byte) 0xF1},

                {(byte) 0xFE, (byte) 0x1F, (byte) 0xFE, (byte) 0x1F,
                 (byte) 0xFE, (byte) 0x0E, (byte) 0xFE, (byte) 0x0E},

                {(byte) 0x1F, (byte) 0xFE, (byte) 0x1F, (byte) 0xFE,
                 (byte) 0x0E, (byte) 0xFE, (byte) 0x0E, (byte) 0xFE},

                {(byte) 0xE0, (byte) 0x1F, (byte) 0xE0, (byte) 0x1F,
                 (byte) 0xF1, (byte) 0x0E, (byte) 0xF1, (byte) 0x0E},

                {(byte) 0x1F, (byte) 0xE0, (byte) 0x1F, (byte) 0xE0,
                 (byte) 0x0E, (byte) 0xF1, (byte) 0x0E, (byte) 0xF1},

                {(byte) 0x01, (byte) 0xFE, (byte) 0x01, (byte) 0xFE,
                 (byte) 0x01, (byte) 0xFE, (byte) 0x01, (byte) 0xFE},

                {(byte) 0xFE, (byte) 0x01, (byte) 0xFE, (byte) 0x01,
                 (byte) 0xFE, (byte) 0x01, (byte) 0xFE, (byte) 0x01},

                {(byte) 0x01, (byte) 0x1F, (byte) 0x01, (byte) 0x1F,
                 (byte) 0x01, (byte) 0x0E, (byte) 0x01, (byte) 0x0E},

                {(byte) 0x1F, (byte) 0x01, (byte) 0x1F, (byte) 0x01,
                 (byte) 0x0E, (byte) 0x01, (byte) 0x0E, (byte) 0x01},

                {(byte) 0xE0, (byte) 0xFE, (byte) 0xE0, (byte) 0xFE,
                 (byte) 0xF1, (byte) 0xFE, (byte) 0xF1, (byte) 0xFE},

                {(byte) 0xFE, (byte) 0xE0, (byte) 0xFE, (byte) 0xE0,
                 (byte) 0xFE, (byte) 0xF1, (byte) 0xFE, (byte) 0xF1},

                {(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                 (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01},

                {(byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE,
                 (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE},

                {(byte) 0xE0, (byte) 0xE0, (byte) 0xE0, (byte) 0xE0,
                 (byte) 0xF1, (byte) 0xF1, (byte) 0xF1, (byte) 0xF1},

                {(byte) 0x1F, (byte) 0x1F, (byte) 0x1F, (byte) 0x1F,
                 (byte) 0x0E, (byte) 0x0E, (byte) 0x0E, (byte) 0x0E},

                };

    /**
     * Creates a new <code>DESKeySpec</code> from the first 8 bytes of the
     * specified key data.
     *
     * @param key
     *            the key data.
     * @throws InvalidKeyException
     *             if the length of the specified key data is less than 8.
     */
    public DESKeySpec(byte[] key) throws InvalidKeyException {
        this(key, 0);
    }

    /**
     * Creates a new <code>DESKeySpec</code> from the first 8 bytes of the
     * specified key data starting at <code>offset</code>.
     *
     * @param key
     *            the key data
     * @param offset
     *            the offset to start at.
     * @throws InvalidKeyException
     *             if the length of the specified key data starting at offset is
     *             less than 8.
     */
    public DESKeySpec(byte[] key, int offset) throws InvalidKeyException {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        if (key.length - offset < DES_KEY_LEN) {
            throw new InvalidKeyException("key too short");
        }
        this.key = new byte[DES_KEY_LEN];
        System.arraycopy(key, offset, this.key, 0, DES_KEY_LEN);
    }

    /**
     * Returns a copy of the key.
     *
     * @return a copy of the key.
     */
    public byte[] getKey() {
        byte[] result = new byte[DES_KEY_LEN];
        System.arraycopy(this.key, 0, result, 0, DES_KEY_LEN);
        return result;
    }

    /**
     * Returns whether the specified key data starting at <code>offset</code> is
     * <i>parity-adjusted</i>.
     *
     * @param key
     *            the key data.
     * @param offset
     *            the offset to start checking at.
     * @return {@code true} if the specified key data is parity-adjusted,
     *            {@code false} otherwise.
     * @throws InvalidKeyException
     *             if the length of the key data starting at offset is less than
     *             8, or the key is null.
     */
    public static boolean isParityAdjusted(byte[] key, int offset) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        if (key.length - offset < DES_KEY_LEN) {
            throw new InvalidKeyException("key too short");
        }

        int byteKey = 0;

        for (int i = offset; i < DES_KEY_LEN; i++) {
            byteKey = key[i];

            byteKey ^= byteKey >> 1;
            byteKey ^= byteKey >> 2;
            byteKey ^= byteKey >> 4;

            if ((byteKey & 1) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether the specified key data starting at <code>offset</code> is
     * weak or semi-weak.
     *
     * @param key
     *            the key data.
     * @param offset
     *            the offset to start checking at.
     * @return {@code true} if the specified key data is weak or semi-weak.
     * @throws InvalidKeyException
     *             if the length of the key data starting at offset is less than
     *             8, or it is null.
     */
    public static boolean isWeak(byte[] key, int offset) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        if (key.length - offset < DES_KEY_LEN) {
            throw new InvalidKeyException("key too short");
        }
        I:
        for (int i=0; i<SEMIWEAKS.length; i++) {
            for (int j=0; j<DES_KEY_LEN; j++) {
                if (SEMIWEAKS[i][j] != key[offset+j]) {
                    continue I;
                }
            }
            return true;
        }
        return false;
    }
}
