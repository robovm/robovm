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

import java.security.spec.KeySpec;
import java.util.Arrays;
import libcore.util.EmptyArray;

/**
 * The key specification for a <i>password based encryption</i> key.
 * <p>
 * Password based encryption is described in <a
 * href="http://www.ietf.org/rfc/rfc2898.txt">PKCS #5</a>.
 */
public class PBEKeySpec implements KeySpec {

    private char[] password;
    private final byte[] salt;
    private final int iterationCount;
    private final int keyLength;

    /**
     * Creates a new <code>PBEKeySpec</code> with the specified password.
     *
     * @param password
     *            the password.
     */
    public PBEKeySpec(char[] password) {
        if (password == null) {
            this.password = EmptyArray.CHAR;
        } else {
            this.password = new char[password.length];
            System.arraycopy(password, 0, this.password, 0, password.length);
        }
        salt = null;
        iterationCount = 0;
        keyLength = 0;
    }

    /**
     * Creates a new <code>PBEKeySpec</code> with the specified password, salt,
     * iteration count and the desired length of the derived key.
     *
     * @param password
     *            the password.
     * @param salt
     *            the salt.
     * @param iterationCount
     *            the iteration count.
     * @param keyLength
     *            the desired key length of the derived key,
     * @throws NullPointerException
     *             if the salt is null.
     * @throws IllegalArgumentException
     *             if the salt is empty, iteration count is zero or negative or
     *             the key length is zero or negative.
     */
    public PBEKeySpec(char[] password, byte[] salt, int iterationCount,
                      int keyLength) {
        if (salt == null) {
            throw new NullPointerException("salt == null");
        }
        if (salt.length == 0) {
            throw new IllegalArgumentException("salt.length == 0");
        }
        if (iterationCount <= 0) {
            throw new IllegalArgumentException("iterationCount <= 0");
        }
        if (keyLength <= 0) {
            throw new IllegalArgumentException("keyLength <= 0");
        }

        if (password == null) {
            this.password = EmptyArray.CHAR;
        } else {
            this.password = new char[password.length];
            System.arraycopy(password, 0, this.password, 0, password.length);
        }
        this.salt = new byte[salt.length];
        System.arraycopy(salt, 0, this.salt, 0, salt.length);
        this.iterationCount = iterationCount;
        this.keyLength = keyLength;
    }

    /**
     * Creates a new <code>PBEKeySpec</code> with the specified password, salt
     * and iteration count.
     *
     * @param password
     *            the password.
     * @param salt
     *            the salt.
     * @param iterationCount
     *            the iteration count.
     * @throws NullPointerException
     *             if salt is null.
     * @throws IllegalArgumentException
     *             if the salt is empty or iteration count is zero or negative.
     */
    public PBEKeySpec(char[] password, byte[] salt, int iterationCount) {
        if (salt == null) {
            throw new NullPointerException("salt == null");
        }
        if (salt.length == 0) {
            throw new IllegalArgumentException("salt.length == 0");
        }
        if (iterationCount <= 0) {
            throw new IllegalArgumentException("iterationCount <= 0");
        }

        if (password == null) {
            this.password = EmptyArray.CHAR;
        } else {
            this.password = new char[password.length];
            System.arraycopy(password, 0, this.password, 0, password.length);
        }
        this.salt = new byte[salt.length];
        System.arraycopy(salt, 0, this.salt, 0, salt.length);
        this.iterationCount = iterationCount;
        this.keyLength = 0;
    }

    /**
     * Clears the password by overwriting it.
     */
    public final void clearPassword() {
        Arrays.fill(password, '?');
        password = null;
    }

    /**
     * Returns a copy of the password of this key specification.
     *
     * @return a copy of the password of this key specification.
     * @throws IllegalStateException
     *             if the password has been cleared before.
     */
    public final char[] getPassword() {
        if (password == null) {
            throw new IllegalStateException("The password has been cleared");
        }
        char[] result = new char[password.length];
        System.arraycopy(password, 0, result, 0, password.length);
        return result;
    }

    /**
     * Returns a copy of the salt of this key specification.
     *
     * @return a copy of the salt of this key specification or null if none is
     *         specified.
     */
    public final byte[] getSalt() {
        if (salt == null) {
            return null;
        }
        byte[] result = new byte[salt.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        return result;
    }

    /**
     * Returns the iteration count of this key specification.
     *
     * @return the iteration count of this key specification.
     */
    public final int getIterationCount() {
        return iterationCount;
    }

    /**
     * Returns the desired key length of the derived key.
     *
     * @return the desired key length of the derived key.
     */
    public final int getKeyLength() {
        return keyLength;
    }
}
