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

import java.math.BigInteger;
import java.security.spec.KeySpec;

/**
 * The key specification for a Diffie-Hellman public key.
 */
public class DHPublicKeySpec implements KeySpec {

    private final BigInteger y;
    private final BigInteger p;
    private final BigInteger g;

    /**
     * Creates a new <code>DHPublicKeySpec</code> instance with the specified
     * <i>public value</i> <code>y</code>, the <i>prime modulus</i>
     * <code>p</code> and the <i>base generator</i> <code>g</code>.
     *
     * @param y
     *            the public value.
     * @param p
     *            the prime modulus.
     * @param g
     *            the base generator.
     */
    public DHPublicKeySpec(BigInteger y, BigInteger p, BigInteger g) {
        this.y = y;
        this.p = p;
        this.g = g;
    }

    /**
     * Returns the <i>public value</i> <code>y</code>.
     *
     * @return the public value <code>y</code>.
     */
    public BigInteger getY() {
        return y;
    }

    /**
     * Returns the <i>prime modulus</i> <code>p</code>.
     *
     * @return the prime modulus <code>p</code>.
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * Returns the <i>base generator</i> <code>g</code>;
     *
     * @return the base generator <code>g</code>;
     */
    public BigInteger getG() {
        return g;
    }
}

