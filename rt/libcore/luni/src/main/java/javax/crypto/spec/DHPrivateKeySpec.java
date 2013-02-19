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
 * The key specification for a Diffie-Hellman private key.
 */
public class DHPrivateKeySpec implements KeySpec {

    private final BigInteger x;
    private final BigInteger p;
    private final BigInteger g;

    /**
     * Creates a new <code>DHPrivateKeySpec</code> with the specified <i>private
     * value</i> <code>x</code>. <i>prime modulus</i> <code>p</code> and <i>base
     * generator</i> <code>g</code>.
     *
     * @param x
     *            the private value.
     * @param p
     *            the prime modulus.
     * @param g
     *            the base generator.
     */
    public DHPrivateKeySpec(BigInteger x, BigInteger p, BigInteger g) {
        this.x = x;
        this.p = p;
        this.g = g;
    }

    /**
     * Returns the <i>private value</i> <code>x</code>.
     *
     * @return the private value <code>x</code>.
     */
    public BigInteger getX() {
        return x;
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
     * Returns the <i>base generator</i> <code>g</code>.
     *
     * @return the base generator <code>g</code>.
     */
    public BigInteger getG() {
        return g;
    }
}

