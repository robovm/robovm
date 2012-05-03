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

/**
 * The algorithm parameter specification for a <i>password based encryption</i>
 * algorithm.
 * <p>
 * Password based encryption is described in <a
 * href="http://www.ietf.org/rfc/rfc2898.txt">PKCS #5</a>.
 *
 */
public class PBEParameterSpec implements AlgorithmParameterSpec {

    private final byte[] salt;
    private final int iterationCount;

    /**
     * Creates a new <code>PBEParameterSpec</code> with the specified salt and
     * iteration count.
     *
     * @param salt
     *            the salt.
     * @param iterationCount
     *            the iteration count.
     * @throws NullPointerException
     *             if salt is null.
     */
    public PBEParameterSpec(byte[] salt, int iterationCount) {
        if (salt == null) {
            throw new NullPointerException("salt == null");
        }
        this.salt = new byte[salt.length];
        System.arraycopy(salt, 0, this.salt, 0, salt.length);
        this.iterationCount = iterationCount;
    }

    /**
     * Returns a copy to the salt.
     *
     * @return a copy to the salt.
     */
    public byte[] getSalt() {
        byte[] result = new byte[salt.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        return result;
    }

    /**
     * Returns the iteration count.
     *
     * @return the iteration count.
     */
    public int getIterationCount() {
        return iterationCount;
    }
}
